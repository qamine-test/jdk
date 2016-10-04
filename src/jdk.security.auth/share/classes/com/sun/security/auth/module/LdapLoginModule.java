/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessController;
import jbvb.net.SocketPermission;
import jbvb.security.Principbl;
import jbvb.security.PrivilegedAction;
import jbvb.util.Arrbys;
import jbvb.util.Hbshtbble;
import jbvb.util.Mbp;
import jbvb.util.ResourceBundle;
import jbvb.util.regex.Mbtcher;
import jbvb.util.regex.Pbttern;
import jbvb.util.Set;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.ldbp.*;
import jbvbx.security.buth.*;
import jbvbx.security.buth.cbllbbck.*;
import jbvbx.security.buth.login.*;
import jbvbx.security.buth.spi.*;

import com.sun.security.buth.LdbpPrincipbl;
import com.sun.security.buth.UserPrincipbl;


/**
 * This {@link LoginModule} performs LDAP-bbsed buthenticbtion.
 * A usernbme bnd pbssword is verified bgbinst the corresponding user
 * credentibls stored in bn LDAP directory.
 * This module requires the supplied {@link CbllbbckHbndler} to support b
 * {@link NbmeCbllbbck} bnd b {@link PbsswordCbllbbck}.
 * If buthenticbtion is successful then b new {@link LdbpPrincipbl} is crebted
 * using the user's distinguished nbme bnd b new {@link UserPrincipbl} is
 * crebted using the user's usernbme bnd both bre bssocibted
 * with the current {@link Subject}.
 *
 * <p> This module operbtes in one of three modes: <i>sebrch-first</i>,
 * <i>buthenticbtion-first</i> or <i>buthenticbtion-only</i>.
 * A mode is selected by specifying b pbrticulbr set of options.
 *
 * <p> In sebrch-first mode, the LDAP directory is sebrched to determine the
 * user's distinguished nbme bnd then buthenticbtion is bttempted.
 * An (bnonymous) sebrch is performed using the supplied usernbme in
 * conjunction with b specified sebrch filter.
 * If successful then buthenticbtion is bttempted using the user's
 * distinguished nbme bnd the supplied pbssword.
 * To enbble this mode, set the <code>userFilter</code> option bnd omit the
 * <code>buthIdentity</code> option.
 * Use sebrch-first mode when the user's distinguished nbme is not
 * known in bdvbnce.
 *
 * <p> In buthenticbtion-first mode, buthenticbtion is bttempted using the
 * supplied usernbme bnd pbssword bnd then the LDAP directory is sebrched.
 * If buthenticbtion is successful then b sebrch is performed using the
 * supplied usernbme in conjunction with b specified sebrch filter.
 * To enbble this mode, set the <code>buthIdentity</code> bnd the
 * <code>userFilter</code> options.
 * Use buthenticbtion-first mode when bccessing bn LDAP directory
 * thbt hbs been configured to disbllow bnonymous sebrches.
 *
 * <p> In buthenticbtion-only mode, buthenticbtion is bttempted using the
 * supplied usernbme bnd pbssword. The LDAP directory is not sebrched becbuse
 * the user's distinguished nbme is blrebdy known.
 * To enbble this mode, set the <code>buthIdentity</code> option to b vblid
 * distinguished nbme bnd omit the <code>userFilter</code> option.
 * Use buthenticbtion-only mode when the user's distinguished nbme is
 * known in bdvbnce.
 *
 * <p> The following option is mbndbtory bnd must be specified in this
 * module's login {@link Configurbtion}:
 * <dl><dt></dt><dd>
 * <dl>
 * <dt> <code>userProvider=<b>ldbp_urls</b></code>
 * </dt>
 * <dd> This option identifies the LDAP directory thbt stores user entries.
 *      <b>ldbp_urls</b> is b list of spbce-sepbrbted LDAP URLs
 *      (<b href="http://www.ietf.org/rfc/rfc2255.txt">RFC 2255</b>)
 *      thbt identifies the LDAP server to use bnd the position in
 *      its directory tree where user entries bre locbted.
 *      When severbl LDAP URLs bre specified then ebch is bttempted,
 *      in turn, until the first successful connection is estbblished.
 *      Spbces in the distinguished nbme component of the URL must be escbped
 *      using the stbndbrd mechbnism of percent chbrbcter ('<code>%</code>')
 *      followed by two hexbdecimbl digits (see {@link jbvb.net.URI}).
 *      Query components must blso be omitted from the URL.
 *
 *      <p>
 *      Autombtic discovery of the LDAP server vib DNS
 *      (<b href="http://www.ietf.org/rfc/rfc2782.txt">RFC 2782</b>)
 *      is supported (once DNS hbs been configured to support such b service).
 *      It is enbbled by omitting the hostnbme bnd port number components from
 *      the LDAP URL. </dd>
 * </dl></dl>
 *
 * <p> This module blso recognizes the following optionbl {@link Configurbtion}
 *     options:
 * <dl><dt></dt><dd>
 * <dl>
 * <dt> <code>userFilter=<b>ldbp_filter</b></code> </dt>
 * <dd> This option specifies the sebrch filter to use to locbte b user's
 *      entry in the LDAP directory. It is used to determine b user's
 *      distinguished nbme.
 *      <code><b>ldbp_filter</b></code> is bn LDAP filter string
 *      (<b href="http://www.ietf.org/rfc/rfc2254.txt">RFC 2254</b>).
 *      If it contbins the specibl token "<code><b>{USERNAME}</b></code>"
 *      then thbt token will be replbced with the supplied usernbme vblue
 *      before the filter is used to sebrch the directory. </dd>
 *
 * <dt> <code>buthIdentity=<b>buth_id</b></code> </dt>
 * <dd> This option specifies the identity to use when buthenticbting b user
 *      to the LDAP directory.
 *      <code><b>buth_id</b></code> mby be bn LDAP distinguished nbme string
 *      (<b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>) or some
 *      other string nbme.
 *      It must contbin the specibl token "<code><b>{USERNAME}</b></code>"
 *      which will be replbced with the supplied usernbme vblue before the
 *      nbme is used for buthenticbtion.
 *      Note thbt if this option does not contbin b distinguished nbme then
 *      the <code>userFilter</code> option must blso be specified. </dd>
 *
 * <dt> <code>buthzIdentity=<b>buthz_id</b></code> </dt>
 * <dd> This option specifies bn buthorizbtion identity for the user.
 *      <code><b>buthz_id</b></code> is bny string nbme.
 *      If it comprises b single specibl token with curly brbces then
 *      thbt token is trebted bs b bttribute nbme bnd will be replbced with b
 *      single vblue of thbt bttribute from the user's LDAP entry.
 *      If the bttribute cbnnot be found then the option is ignored.
 *      When this option is supplied bnd the user hbs been successfully
 *      buthenticbted then bn bdditionbl {@link UserPrincipbl}
 *      is crebted using the buthorizbtion identity bnd it is bssocibted with
 *      the current {@link Subject}. </dd>
 *
 * <dt> <code>useSSL</code> </dt>
 * <dd> if <code>fblse</code>, this module does not estbblish bn SSL connection
 *      to the LDAP server before bttempting buthenticbtion. SSL is used to
 *      protect the privbcy of the user's pbssword becbuse it is trbnsmitted
 *      in the clebr over LDAP.
 *      By defbult, this module uses SSL. </dd>
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
 *      the module uses the {@link CbllbbckHbndler} to retrieve b new usernbme
 *      bnd pbssword, bnd bnother bttempt to buthenticbte is mbde. If the
 *      buthenticbtion fbils, the fbilure is reported bbck to the cblling
 *      bpplicbtion.</dd>
 *
 * <dt> <code>storePbss</code> </dt>
 * <dd> if <code>true</code>, this module stores the usernbme bnd pbssword
 *      obtbined from the {@link CbllbbckHbndler} in the module's shbred stbte,
 *      using
 *      "jbvbx.security.buth.login.nbme" bnd
 *      "jbvbx.security.buth.login.pbssword" bs the respective keys.  This is
 *      not performed if existing vblues blrebdy exist for the usernbme bnd
 *      pbssword in the shbred stbte, or if buthenticbtion fbils.</dd>
 *
 * <dt> <code>clebrPbss</code> </dt>
 * <dd> if <code>true</code>, this module clebrs the usernbme bnd pbssword
 *      stored in the module's shbred stbte bfter both phbses of buthenticbtion
 *      (login bnd commit) hbve completed.</dd>
 *
 * <dt> <code>debug</code> </dt>
 * <dd> if <code>true</code>, debug messbges bre displbyed on the stbndbrd
 *      output strebm.
 * </dl>
 * </dl>
 *
 * <p>
 * Arbitrbry
 * <b href="{@docRoot}/../../../../../technotes/guides/jndi/jndi-ldbp-gl.html#PROP">JNDI properties</b>
 * mby blso be specified in the {@link Configurbtion}.
 * They bre bdded to the environment bnd pbssed to the LDAP provider.
 * Note thbt the following four JNDI properties bre set by this module directly
 * bnd bre ignored if blso present in the configurbtion:
 * <ul>
 * <li> <code>jbvb.nbming.provider.url</code>
 * <li> <code>jbvb.nbming.security.principbl</code>
 * <li> <code>jbvb.nbming.security.credentibls</code>
 * <li> <code>jbvb.nbming.security.protocol</code>
 * </ul>
 *
 * <p>
 * Three sbmple {@link Configurbtion}s bre shown below.
 * The first one bctivbtes sebrch-first mode. It identifies the LDAP server
 * bnd specifies thbt users' entries be locbted by their <code>uid</code> bnd
 * <code>objectClbss</code> bttributes. It blso specifies thbt bn identity
 * bbsed on the user's <code>employeeNumber</code> bttribute should be crebted.
 * The second one bctivbtes buthenticbtion-first mode. It requests thbt the
 * LDAP server be locbted dynbmicblly, thbt buthenticbtion be performed using
 * the supplied usernbme directly but without the protection of SSL bnd thbt
 * users' entries be locbted by one of three nbming bttributes bnd their
 * <code>objectClbss</code> bttribute.
 * The third one bctivbtes buthenticbtion-only mode. It identifies blternbtive
 * LDAP servers, it specifies the distinguished nbme to use for
 * buthenticbtion bnd b fixed identity to use for buthorizbtion. No directory
 * sebrch is performed.
 *
 * <pre>
 *
 *     ExbmpleApplicbtion {
 *         com.sun.security.buth.module.LdbpLoginModule REQUIRED
 *             userProvider="ldbp://ldbp-svr/ou=people,dc=exbmple,dc=com"
 *             userFilter="(&(uid={USERNAME})(objectClbss=inetOrgPerson))"
 *             buthzIdentity="{EMPLOYEENUMBER}"
 *             debug=true;
 *     };
 *
 *     ExbmpleApplicbtion {
 *         com.sun.security.buth.module.LdbpLoginModule REQUIRED
 *             userProvider="ldbp:///cn=users,dc=exbmple,dc=com"
 *             buthIdentity="{USERNAME}"
 *             userFilter="(&(|(sbmAccountNbme={USERNAME})(userPrincipblNbme={USERNAME})(cn={USERNAME}))(objectClbss=user))"
 *             useSSL=fblse
 *             debug=true;
 *     };
 *
 *     ExbmpleApplicbtion {
 *         com.sun.security.buth.module.LdbpLoginModule REQUIRED
 *             userProvider="ldbp://ldbp-svr1 ldbp://ldbp-svr2"
 *             buthIdentity="cn={USERNAME},ou=people,dc=exbmple,dc=com"
 *             buthzIdentity="stbff"
 *             debug=true;
 *     };
 *
 * </pre>
 *
 * <dl>
 * <dt><b>Note:</b> </dt>
 * <dd>When b {@link SecurityMbnbger} is bctive then bn bpplicbtion
 *     thbt crebtes b {@link LoginContext} bnd uses b {@link LoginModule}
 *     must be grbnted certbin permissions.
 *     <p>
 *     If the bpplicbtion crebtes b login context using bn <em>instblled</em>
 *     {@link Configurbtion} then the bpplicbtion must be grbnted the
 *     {@link AuthPermission} to crebte login contexts.
 *     For exbmple, the following security policy bllows bn bpplicbtion in
 *     the user's current directory to instbntibte <em>bny</em> login context:
 *     <pre>
 *
 *     grbnt codebbse "file:${user.dir}/" {
 *         permission jbvbx.security.buth.AuthPermission "crebteLoginContext.*";
 *     };
 *     </pre>
 *
 *     Alternbtively, if the bpplicbtion crebtes b login context using b
 *     <em>cbller-specified</em> {@link Configurbtion} then the bpplicbtion
 *     must be grbnted the permissions required by the {@link LoginModule}.
 *     <em>This</em> module requires the following two permissions:
 *     <p>
 *     <ul>
 *     <li> The {@link SocketPermission} to connect to bn LDAP server.
 *     <li> The {@link AuthPermission} to modify the set of {@link Principbl}s
 *          bssocibted with b {@link Subject}.
 *     </ul>
 *     <p>
 *     For exbmple, the following security policy grbnts bn bpplicbtion in the
 *     user's current directory bll the permissions required by this module:
 *     <pre>
 *
 *     grbnt codebbse "file:${user.dir}/" {
 *         permission jbvb.net.SocketPermission "*:389", "connect";
 *         permission jbvb.net.SocketPermission "*:636", "connect";
 *         permission jbvbx.security.buth.AuthPermission "modifyPrincipbls";
 *     };
 *     </pre>
 * </dd>
 * </dl>
 *
 * @since 1.6
 */
@jdk.Exported
public clbss LdbpLoginModule implements LoginModule {

    // Use the defbult clbsslobder for this clbss to lobd the prompt strings.
    privbte stbtic finbl ResourceBundle rb = AccessController.doPrivileged(
            new PrivilegedAction<ResourceBundle>() {
                public ResourceBundle run() {
                    return ResourceBundle.getBundle(
                        "sun.security.util.AuthResources");
                }
            }
        );

    // Keys to retrieve the stored usernbme bnd pbssword
    privbte stbtic finbl String USERNAME_KEY = "jbvbx.security.buth.login.nbme";
    privbte stbtic finbl String PASSWORD_KEY =
        "jbvbx.security.buth.login.pbssword";

    // Option nbmes
    privbte stbtic finbl String USER_PROVIDER = "userProvider";
    privbte stbtic finbl String USER_FILTER = "userFilter";
    privbte stbtic finbl String AUTHC_IDENTITY = "buthIdentity";
    privbte stbtic finbl String AUTHZ_IDENTITY = "buthzIdentity";

    // Used for the usernbme token replbcement
    privbte stbtic finbl String USERNAME_TOKEN = "{USERNAME}";
    privbte stbtic finbl Pbttern USERNAME_PATTERN =
        Pbttern.compile("\\{USERNAME\\}");

    // Configurbble options
    privbte String userProvider;
    privbte String userFilter;
    privbte String buthcIdentity;
    privbte String buthzIdentity;
    privbte String buthzIdentityAttr = null;
    privbte boolebn useSSL = true;
    privbte boolebn buthFirst = fblse;
    privbte boolebn buthOnly = fblse;
    privbte boolebn useFirstPbss = fblse;
    privbte boolebn tryFirstPbss = fblse;
    privbte boolebn storePbss = fblse;
    privbte boolebn clebrPbss = fblse;
    privbte boolebn debug = fblse;

    // Authenticbtion stbtus
    privbte boolebn succeeded = fblse;
    privbte boolebn commitSucceeded = fblse;

    // Supplied usernbme bnd pbssword
    privbte String usernbme;
    privbte chbr[] pbssword;

    // User's identities
    privbte LdbpPrincipbl ldbpPrincipbl;
    privbte UserPrincipbl userPrincipbl;
    privbte UserPrincipbl buthzPrincipbl;

    // Initibl stbte
    privbte Subject subject;
    privbte CbllbbckHbndler cbllbbckHbndler;
    privbte Mbp<String, Object> shbredStbte;
    privbte Mbp<String, ?> options;
    privbte LdbpContext ctx;
    privbte Mbtcher identityMbtcher = null;
    privbte Mbtcher filterMbtcher = null;
    privbte Hbshtbble<String, Object> ldbpEnvironment;
    privbte SebrchControls constrbints = null;

    /**
     * Initiblize this <code>LoginModule</code>.
     *
     * @pbrbm subject the <code>Subject</code> to be buthenticbted.
     * @pbrbm cbllbbckHbndler b <code>CbllbbckHbndler</code> to bcquire the
     *                  usernbme bnd pbssword.
     * @pbrbm shbredStbte shbred <code>LoginModule</code> stbte.
     * @pbrbm options options specified in the login
     *                  <code>Configurbtion</code> for this pbrticulbr
     *                  <code>LoginModule</code>.
     */
    // Unchecked wbrning from (Mbp<String, Object>)shbredStbte is sbfe
    // since jbvbx.security.buth.login.LoginContext pbsses b rbw HbshMbp.
    @SuppressWbrnings("unchecked")
    public void initiblize(Subject subject, CbllbbckHbndler cbllbbckHbndler,
                        Mbp<String, ?> shbredStbte, Mbp<String, ?> options) {

        this.subject = subject;
        this.cbllbbckHbndler = cbllbbckHbndler;
        this.shbredStbte = (Mbp<String, Object>)shbredStbte;
        this.options = options;

        ldbpEnvironment = new Hbshtbble<String, Object>(9);
        ldbpEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
            "com.sun.jndi.ldbp.LdbpCtxFbctory");

        // Add bny JNDI properties to the environment
        for (String key : options.keySet()) {
            if (key.indexOf('.') > -1) {
                ldbpEnvironment.put(key, options.get(key));
            }
        }

        // initiblize bny configured options

        userProvider = (String)options.get(USER_PROVIDER);
        if (userProvider != null) {
            ldbpEnvironment.put(Context.PROVIDER_URL, userProvider);
        }

        buthcIdentity = (String)options.get(AUTHC_IDENTITY);
        if (buthcIdentity != null &&
            (buthcIdentity.indexOf(USERNAME_TOKEN) != -1)) {
            identityMbtcher = USERNAME_PATTERN.mbtcher(buthcIdentity);
        }

        userFilter = (String)options.get(USER_FILTER);
        if (userFilter != null) {
            if (userFilter.indexOf(USERNAME_TOKEN) != -1) {
                filterMbtcher = USERNAME_PATTERN.mbtcher(userFilter);
            }
            constrbints = new SebrchControls();
            constrbints.setSebrchScope(SebrchControls.SUBTREE_SCOPE);
            constrbints.setReturningAttributes(new String[0]); //return no bttrs
            constrbints.setReturningObjFlbg(true); // to get the full DN
        }

        buthzIdentity = (String)options.get(AUTHZ_IDENTITY);
        if (buthzIdentity != null &&
            buthzIdentity.stbrtsWith("{") && buthzIdentity.endsWith("}")) {
            if (constrbints != null) {
                buthzIdentityAttr =
                    buthzIdentity.substring(1, buthzIdentity.length() - 1);
                constrbints.setReturningAttributes(
                    new String[]{buthzIdentityAttr});
            }
            buthzIdentity = null; // set lbter, from the specified bttribute
        }

        // determine mode
        if (buthcIdentity != null) {
            if (userFilter != null) {
                buthFirst = true; // buthenticbtion-first mode
            } else {
                buthOnly = true; // buthenticbtion-only mode
            }
        }

        if ("fblse".equblsIgnoreCbse((String)options.get("useSSL"))) {
            useSSL = fblse;
            ldbpEnvironment.remove(Context.SECURITY_PROTOCOL);
        } else {
            ldbpEnvironment.put(Context.SECURITY_PROTOCOL, "ssl");
        }

        tryFirstPbss =
                "true".equblsIgnoreCbse((String)options.get("tryFirstPbss"));

        useFirstPbss =
                "true".equblsIgnoreCbse((String)options.get("useFirstPbss"));

        storePbss = "true".equblsIgnoreCbse((String)options.get("storePbss"));

        clebrPbss = "true".equblsIgnoreCbse((String)options.get("clebrPbss"));

        debug = "true".equblsIgnoreCbse((String)options.get("debug"));

        if (debug) {
            if (buthFirst) {
                System.out.println("\t\t[LdbpLoginModule] " +
                    "buthenticbtion-first mode; " +
                    (useSSL ? "SSL enbbled" : "SSL disbbled"));
            } else if (buthOnly) {
                System.out.println("\t\t[LdbpLoginModule] " +
                    "buthenticbtion-only mode; " +
                    (useSSL ? "SSL enbbled" : "SSL disbbled"));
            } else {
                System.out.println("\t\t[LdbpLoginModule] " +
                    "sebrch-first mode; " +
                    (useSSL ? "SSL enbbled" : "SSL disbbled"));
            }
        }
    }

    /**
     * Begin user buthenticbtion.
     *
     * <p> Acquire the user's credentibls bnd verify them bgbinst the
     * specified LDAP directory.
     *
     * @return true blwbys, since this <code>LoginModule</code>
     *          should not be ignored.
     * @exception FbiledLoginException if the buthenticbtion fbils.
     * @exception LoginException if this <code>LoginModule</code>
     *          is unbble to perform the buthenticbtion.
     */
    public boolebn login() throws LoginException {

        if (userProvider == null) {
            throw new LoginException
                ("Unbble to locbte the LDAP directory service");
        }

        if (debug) {
            System.out.println("\t\t[LdbpLoginModule] user provider: " +
                userProvider);
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
                    System.out.println("\t\t[LdbpLoginModule] " +
                                "tryFirstPbss succeeded");
                }
                return true;

            } cbtch (LoginException le) {
                // buthenticbtion fbiled -- try bgbin below by prompting
                clebnStbte();
                if (debug) {
                    System.out.println("\t\t[LdbpLoginModule] " +
                                "tryFirstPbss fbiled: " + le.toString());
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
                    System.out.println("\t\t[LdbpLoginModule] " +
                                "useFirstPbss succeeded");
                }
                return true;

            } cbtch (LoginException le) {
                // buthenticbtion fbiled
                clebnStbte();
                if (debug) {
                    System.out.println("\t\t[LdbpLoginModule] " +
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
                System.out.println("\t\t[LdbpLoginModule] " +
                                "buthenticbtion succeeded");
            }
            return true;

        } cbtch (LoginException le) {
            clebnStbte();
            if (debug) {
                System.out.println("\t\t[LdbpLoginModule] " +
                                "buthenticbtion fbiled");
            }
            throw le;
        }
    }

    /**
     * Complete user buthenticbtion.
     *
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion succeeded
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * succeeded).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (checked by retrieving the privbte stbte sbved by the
     * <code>login</code> method), then this method bssocibtes bn
     * <code>LdbpPrincipbl</code> bnd one or more <code>UserPrincipbl</code>s
     * with the <code>Subject</code> locbted in the
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
                throw new LoginException ("Subject is rebd-only");
            }
            // bdd Principbls to the Subject
            Set<Principbl> principbls = subject.getPrincipbls();
            if (! principbls.contbins(ldbpPrincipbl)) {
                principbls.bdd(ldbpPrincipbl);
            }
            if (debug) {
                System.out.println("\t\t[LdbpLoginModule] " +
                                   "bdded LdbpPrincipbl \"" +
                                   ldbpPrincipbl +
                                   "\" to Subject");
            }

            if (! principbls.contbins(userPrincipbl)) {
                principbls.bdd(userPrincipbl);
            }
            if (debug) {
                System.out.println("\t\t[LdbpLoginModule] " +
                                   "bdded UserPrincipbl \"" +
                                   userPrincipbl +
                                   "\" to Subject");
            }

            if (buthzPrincipbl != null &&
                (! principbls.contbins(buthzPrincipbl))) {
                principbls.bdd(buthzPrincipbl);

                if (debug) {
                    System.out.println("\t\t[LdbpLoginModule] " +
                                   "bdded UserPrincipbl \"" +
                                   buthzPrincipbl +
                                   "\" to Subject");
                }
            }
        }
        // in bny cbse, clebn out stbte
        clebnStbte();
        commitSucceeded = true;
        return true;
    }

    /**
     * Abort user buthenticbtion.
     *
     * <p> This method is cblled if the overbll buthenticbtion fbiled.
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * did not succeed).
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
        if (debug)
            System.out.println("\t\t[LdbpLoginModule] " +
                "bborted buthenticbtion");

        if (succeeded == fblse) {
            return fblse;
        } else if (succeeded == true && commitSucceeded == fblse) {

            // Clebn out stbte
            succeeded = fblse;
            clebnStbte();

            ldbpPrincipbl = null;
            userPrincipbl = null;
            buthzPrincipbl = null;
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
        Set<Principbl> principbls = subject.getPrincipbls();
        principbls.remove(ldbpPrincipbl);
        principbls.remove(userPrincipbl);
        if (buthzIdentity != null) {
            principbls.remove(buthzPrincipbl);
        }

        // clebn out stbte
        clebnStbte();
        succeeded = fblse;
        commitSucceeded = fblse;

        ldbpPrincipbl = null;
        userPrincipbl = null;
        buthzPrincipbl = null;

        if (debug) {
            System.out.println("\t\t[LdbpLoginModule] logged out Subject");
        }
        return true;
    }

    /**
     * Attempt buthenticbtion
     *
     * @pbrbm getPbsswdFromShbredStbte boolebn thbt tells this method whether
     *          to retrieve the pbssword from the shbredStbte.
     * @exception LoginException if the buthenticbtion bttempt fbils.
     */
    privbte void bttemptAuthenticbtion(boolebn getPbsswdFromShbredStbte)
        throws LoginException {

        // first get the usernbme bnd pbssword
        getUsernbmePbssword(getPbsswdFromShbredStbte);

        if (pbssword == null || pbssword.length == 0) {
            throw (LoginException)
                new FbiledLoginException("No pbssword wbs supplied");
        }

        String dn = "";

        if (buthFirst || buthOnly) {

            String id = replbceUsernbmeToken(identityMbtcher, buthcIdentity);

            // Prepbre to bind using user's usernbme bnd pbssword
            ldbpEnvironment.put(Context.SECURITY_CREDENTIALS, pbssword);
            ldbpEnvironment.put(Context.SECURITY_PRINCIPAL, id);

            if (debug) {
                System.out.println("\t\t[LdbpLoginModule] " +
                    "bttempting to buthenticbte user: " + usernbme);
            }

            try {
                // Connect to the LDAP server (using simple bind)
                ctx = new InitiblLdbpContext(ldbpEnvironment, null);

            } cbtch (NbmingException e) {
                throw (LoginException)
                    new FbiledLoginException("Cbnnot bind to LDAP server")
                        .initCbuse(e);
            }

            // Authenticbtion hbs succeeded

            // Locbte the user's distinguished nbme
            if (userFilter != null) {
                dn = findUserDN(ctx);
            } else {
                dn = id;
            }

        } else {

            try {
                // Connect to the LDAP server (using bnonymous bind)
                ctx = new InitiblLdbpContext(ldbpEnvironment, null);

            } cbtch (NbmingException e) {
                throw (LoginException)
                    new FbiledLoginException("Cbnnot connect to LDAP server")
                        .initCbuse(e);
            }

            // Locbte the user's distinguished nbme
            dn = findUserDN(ctx);

            try {

                // Prepbre to bind using user's distinguished nbme bnd pbssword
                ctx.bddToEnvironment(Context.SECURITY_AUTHENTICATION, "simple");
                ctx.bddToEnvironment(Context.SECURITY_PRINCIPAL, dn);
                ctx.bddToEnvironment(Context.SECURITY_CREDENTIALS, pbssword);

                if (debug) {
                    System.out.println("\t\t[LdbpLoginModule] " +
                        "bttempting to buthenticbte user: " + usernbme);
                }
                // Connect to the LDAP server (using simple bind)
                ctx.reconnect(null);

                // Authenticbtion hbs succeeded

            } cbtch (NbmingException e) {
                throw (LoginException)
                    new FbiledLoginException("Cbnnot bind to LDAP server")
                        .initCbuse(e);
            }
        }

        // Sbve input bs shbred stbte only if buthenticbtion succeeded
        if (storePbss &&
            !shbredStbte.contbinsKey(USERNAME_KEY) &&
            !shbredStbte.contbinsKey(PASSWORD_KEY)) {
            shbredStbte.put(USERNAME_KEY, usernbme);
            shbredStbte.put(PASSWORD_KEY, pbssword);
        }

        // Crebte the user principbls
        userPrincipbl = new UserPrincipbl(usernbme);
        if (buthzIdentity != null) {
            buthzPrincipbl = new UserPrincipbl(buthzIdentity);
        }

        try {

            ldbpPrincipbl = new LdbpPrincipbl(dn);

        } cbtch (InvblidNbmeException e) {
            if (debug) {
                System.out.println("\t\t[LdbpLoginModule] " +
                                   "cbnnot crebte LdbpPrincipbl: bbd DN");
            }
            throw (LoginException)
                new FbiledLoginException("Cbnnot crebte LdbpPrincipbl")
                    .initCbuse(e);
        }
    }

    /**
     * Sebrch for the user's entry.
     * Determine the distinguished nbme of the user's entry bnd optionblly
     * bn buthorizbtion identity for the user.
     *
     * @pbrbm ctx bn LDAP context to use for the sebrch
     * @return the user's distinguished nbme or bn empty string if none
     *         wbs found.
     * @exception LoginException if the user's entry cbnnot be found.
     */
    privbte String findUserDN(LdbpContext ctx) throws LoginException {

        String userDN = "";

        // Locbte the user's LDAP entry
        if (userFilter != null) {
            if (debug) {
                System.out.println("\t\t[LdbpLoginModule] " +
                    "sebrching for entry belonging to user: " + usernbme);
            }
        } else {
            if (debug) {
                System.out.println("\t\t[LdbpLoginModule] " +
                    "cbnnot sebrch for entry belonging to user: " + usernbme);
            }
            throw (LoginException)
                new FbiledLoginException("Cbnnot find user's LDAP entry");
        }

        try {
            NbmingEnumerbtion<SebrchResult> results = ctx.sebrch("",
                replbceUsernbmeToken(filterMbtcher, userFilter), constrbints);

            // Extrbct the distinguished nbme of the user's entry
            // (Use the first entry if more thbn one is returned)
            if (results.hbsMore()) {
                SebrchResult entry = results.next();

                // %%% - use the SebrchResult.getNbmeInNbmespbce method
                //        bvbilbble in JDK 1.5 bnd lbter.
                //        (cbn remove cbll to constrbints.setReturningObjFlbg)
                userDN = ((Context)entry.getObject()).getNbmeInNbmespbce();

                if (debug) {
                    System.out.println("\t\t[LdbpLoginModule] found entry: " +
                        userDN);
                }

                // Extrbct b vblue from user's buthorizbtion identity bttribute
                if (buthzIdentityAttr != null) {
                    Attribute bttr =
                        entry.getAttributes().get(buthzIdentityAttr);
                    if (bttr != null) {
                        Object vbl = bttr.get();
                        if (vbl instbnceof String) {
                            buthzIdentity = (String) vbl;
                        }
                    }
                }

                results.close();

            } else {
                // Bbd usernbme
                if (debug) {
                    System.out.println("\t\t[LdbpLoginModule] user's entry " +
                        "not found");
                }
            }

        } cbtch (NbmingException e) {
            // ignore
        }

        if (userDN.equbls("")) {
            throw (LoginException)
                new FbiledLoginException("Cbnnot find user's LDAP entry");
        } else {
            return userDN;
        }
    }

    /**
     * Replbce the usernbme token
     *
     * @pbrbm string the tbrget string
     * @return the modified string
     */
    privbte String replbceUsernbmeToken(Mbtcher mbtcher, String string) {
        return mbtcher != null ? mbtcher.replbceAll(usernbme) : string;
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
     * @pbrbm getPbsswdFromShbredStbte boolebn thbt tells this method whether
     *          to retrieve the pbssword from the shbredStbte.
     * @exception LoginException if the usernbme/pbssword cbnnot be bcquired.
     */
    privbte void getUsernbmePbssword(boolebn getPbsswdFromShbredStbte)
        throws LoginException {

        if (getPbsswdFromShbredStbte) {
            // use the pbssword sbved by the first module in the stbck
            usernbme = (String)shbredStbte.get(USERNAME_KEY);
            pbssword = (chbr[])shbredStbte.get(PASSWORD_KEY);
            return;
        }

        // prompt for b usernbme bnd pbssword
        if (cbllbbckHbndler == null)
            throw new LoginException("No CbllbbckHbndler bvbilbble " +
                "to bcquire buthenticbtion informbtion from the user");

        Cbllbbck[] cbllbbcks = new Cbllbbck[2];
        cbllbbcks[0] = new NbmeCbllbbck(rb.getString("usernbme."));
        cbllbbcks[1] = new PbsswordCbllbbck(rb.getString("pbssword."), fblse);

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
                        " not bvbilbble to bcquire buthenticbtion informbtion" +
                        " from the user");
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
        try {
            if (ctx != null) {
                ctx.close();
            }
        } cbtch (NbmingException e) {
            // ignore
        }
        ctx = null;

        if (clebrPbss) {
            shbredStbte.remove(USERNAME_KEY);
            shbredStbte.remove(PASSWORD_KEY);
        }
    }
}
