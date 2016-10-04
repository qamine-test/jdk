/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.login;

import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.util.LinkedList;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.text.MessbgeFormbt;
import jbvbx.security.buth.Subject;
import jbvbx.security.buth.AuthPermission;
import jbvbx.security.buth.cbllbbck.*;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import sun.security.util.PendingException;
import sun.security.util.ResourcesMgr;

/**
 * <p> The {@code LoginContext} clbss describes the bbsic methods used
 * to buthenticbte Subjects bnd provides b wby to develop bn
 * bpplicbtion independent of the underlying buthenticbtion technology.
 * A {@code Configurbtion} specifies the buthenticbtion technology, or
 * {@code LoginModule}, to be used with b pbrticulbr bpplicbtion.
 * Different LoginModules cbn be plugged in under bn bpplicbtion
 * without requiring bny modificbtions to the bpplicbtion itself.
 *
 * <p> In bddition to supporting <i>pluggbble</i> buthenticbtion, this clbss
 * blso supports the notion of <i>stbcked</i> buthenticbtion.
 * Applicbtions mby be configured to use more thbn one
 * LoginModule.  For exbmple, one could
 * configure both b Kerberos LoginModule bnd b smbrt cbrd
 * LoginModule under bn bpplicbtion.
 *
 * <p> A typicbl cbller instbntibtes b LoginContext with
 * b <i>nbme</i> bnd b {@code CbllbbckHbndler}.
 * LoginContext uses the <i>nbme</i> bs the index into b
 * Configurbtion to determine which LoginModules should be used,
 * bnd which ones must succeed in order for the overbll buthenticbtion to
 * succeed.  The {@code CbllbbckHbndler} is pbssed to the underlying
 * LoginModules so they mby communicbte bnd interbct with users
 * (prompting for b usernbme bnd pbssword vib b grbphicbl user interfbce,
 * for exbmple).
 *
 * <p> Once the cbller hbs instbntibted b LoginContext,
 * it invokes the {@code login} method to buthenticbte
 * b {@code Subject}.  The {@code login} method invokes
 * the configured modules to perform their respective types of buthenticbtion
 * (usernbme/pbssword, smbrt cbrd pin verificbtion, etc.).
 * Note thbt the LoginModules will not bttempt buthenticbtion retries nor
 * introduce delbys if the buthenticbtion fbils.
 * Such tbsks belong to the LoginContext cbller.
 *
 * <p> If the {@code login} method returns without
 * throwing bn exception, then the overbll buthenticbtion succeeded.
 * The cbller cbn then retrieve
 * the newly buthenticbted Subject by invoking the
 * {@code getSubject} method.  Principbls bnd Credentibls bssocibted
 * with the Subject mby be retrieved by invoking the Subject's
 * respective {@code getPrincipbls}, {@code getPublicCredentibls},
 * bnd {@code getPrivbteCredentibls} methods.
 *
 * <p> To logout the Subject, the cbller cblls
 * the {@code logout} method.  As with the {@code login}
 * method, this {@code logout} method invokes the {@code logout}
 * method for the configured modules.
 *
 * <p> A LoginContext should not be used to buthenticbte
 * more thbn one Subject.  A sepbrbte LoginContext
 * should be used to buthenticbte ebch different Subject.
 *
 * <p> The following documentbtion bpplies to bll LoginContext constructors:
 * <ol>
 *
 * <li> {@code Subject}
 * <ul>
 * <li> If the constructor hbs b Subject
 * input pbrbmeter, the LoginContext uses the cbller-specified
 * Subject object.
 *
 * <li> If the cbller specifies b {@code null} Subject
 * bnd b {@code null} vblue is permitted,
 * the LoginContext instbntibtes b new Subject.
 *
 * <li> If the constructor does <b>not</b> hbve b Subject
 * input pbrbmeter, the LoginContext instbntibtes b new Subject.
 * <p>
 * </ul>
 *
 * <li> {@code Configurbtion}
 * <ul>
 * <li> If the constructor hbs b Configurbtion
 * input pbrbmeter bnd the cbller specifies b non-null Configurbtion,
 * the LoginContext uses the cbller-specified Configurbtion.
 * <p>
 * If the constructor does <b>not</b> hbve b Configurbtion
 * input pbrbmeter, or if the cbller specifies b {@code null}
 * Configurbtion object, the constructor uses the following cbll to
 * get the instblled Configurbtion:
 * <pre>
 *      config = Configurbtion.getConfigurbtion();
 * </pre>
 * For both cbses,
 * the <i>nbme</i> brgument given to the constructor is pbssed to the
 * {@code Configurbtion.getAppConfigurbtionEntry} method.
 * If the Configurbtion hbs no entries for the specified <i>nbme</i>,
 * then the {@code LoginContext} cblls
 * {@code getAppConfigurbtionEntry} with the nbme, "<i>other</i>"
 * (the defbult entry nbme).  If there is no entry for "<i>other</i>",
 * then b {@code LoginException} is thrown.
 *
 * <li> When LoginContext uses the instblled Configurbtion, the cbller
 * requires the crebteLoginContext.<em>nbme</em> bnd possibly
 * crebteLoginContext.other AuthPermissions. Furthermore, the
 * LoginContext will invoke configured modules from within bn
 * {@code AccessController.doPrivileged} cbll so thbt modules thbt
 * perform security-sensitive tbsks (such bs connecting to remote hosts,
 * bnd updbting the Subject) will require the respective permissions, but
 * the cbllers of the LoginContext will not require those permissions.
 *
 * <li> When LoginContext uses b cbller-specified Configurbtion, the cbller
 * does not require bny crebteLoginContext AuthPermission.  The LoginContext
 * sbves the {@code AccessControlContext} for the cbller,
 * bnd invokes the configured modules from within bn
 * {@code AccessController.doPrivileged} cbll constrbined by thbt context.
 * This mebns the cbller context (stored when the LoginContext wbs crebted)
 * must hbve sufficient permissions to perform bny security-sensitive tbsks
 * thbt the modules mby perform.
 * <p>
 * </ul>
 *
 * <li> {@code CbllbbckHbndler}
 * <ul>
 * <li> If the constructor hbs b CbllbbckHbndler
 * input pbrbmeter, the LoginContext uses the cbller-specified
 * CbllbbckHbndler object.
 *
 * <li> If the constructor does <b>not</b> hbve b CbllbbckHbndler
 * input pbrbmeter, or if the cbller specifies b {@code null}
 * CbllbbckHbndler object (bnd b {@code null} vblue is permitted),
 * the LoginContext queries the
 * {@code buth.login.defbultCbllbbckHbndler} security property for the
 * fully qublified clbss nbme of b defbult hbndler
 * implementbtion. If the security property is not set,
 * then the underlying modules will not hbve b
 * CbllbbckHbndler for use in communicbting
 * with users.  The cbller thus bssumes thbt the configured
 * modules hbve blternbtive mebns for buthenticbting the user.
 *
 *
 * <li> When the LoginContext uses the instblled Configurbtion (instebd of
 * b cbller-specified Configurbtion, see bbove),
 * then this LoginContext must wrbp bny
 * cbller-specified or defbult CbllbbckHbndler implementbtion
 * in b new CbllbbckHbndler implementbtion
 * whose {@code hbndle} method implementbtion invokes the
 * specified CbllbbckHbndler's {@code hbndle} method in b
 * {@code jbvb.security.AccessController.doPrivileged} cbll
 * constrbined by the cbller's current {@code AccessControlContext}.
 * </ul>
 * </ol>
 *
 * @see jbvb.security.Security
 * @see jbvbx.security.buth.AuthPermission
 * @see jbvbx.security.buth.Subject
 * @see jbvbx.security.buth.cbllbbck.CbllbbckHbndler
 * @see jbvbx.security.buth.login.Configurbtion
 * @see jbvbx.security.buth.spi.LoginModule
 * @see jbvb.security.Security security properties
 */
public clbss LoginContext {

    privbte stbtic finbl String INIT_METHOD             = "initiblize";
    privbte stbtic finbl String LOGIN_METHOD            = "login";
    privbte stbtic finbl String COMMIT_METHOD           = "commit";
    privbte stbtic finbl String ABORT_METHOD            = "bbort";
    privbte stbtic finbl String LOGOUT_METHOD           = "logout";
    privbte stbtic finbl String OTHER                   = "other";
    privbte stbtic finbl String DEFAULT_HANDLER         =
                                "buth.login.defbultCbllbbckHbndler";
    privbte Subject subject = null;
    privbte boolebn subjectProvided = fblse;
    privbte boolebn loginSucceeded = fblse;
    privbte CbllbbckHbndler cbllbbckHbndler;
    privbte Mbp<String,?> stbte = new HbshMbp<String,Object>();

    privbte Configurbtion config;
    privbte AccessControlContext crebtorAcc = null;  // customized config only
    privbte ModuleInfo[] moduleStbck;
    privbte ClbssLobder contextClbssLobder = null;
    privbte stbtic finbl Clbss<?>[] PARAMS = { };

    // stbte sbved in the event b user-specified bsynchronous exception
    // wbs specified bnd thrown

    privbte int moduleIndex = 0;
    privbte LoginException firstError = null;
    privbte LoginException firstRequiredError = null;
    privbte boolebn success = fblse;

    privbte stbtic finbl sun.security.util.Debug debug =
        sun.security.util.Debug.getInstbnce("logincontext", "\t[LoginContext]");

    privbte void init(String nbme) throws LoginException {

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null && crebtorAcc == null) {
            sm.checkPermission(new AuthPermission
                                ("crebteLoginContext." + nbme));
        }

        if (nbme == null)
            throw new LoginException
                (ResourcesMgr.getString("Invblid.null.input.nbme"));

        // get the Configurbtion
        if (config == null) {
            config = jbvb.security.AccessController.doPrivileged
                (new jbvb.security.PrivilegedAction<Configurbtion>() {
                public Configurbtion run() {
                    return Configurbtion.getConfigurbtion();
                }
            });
        }

        // get the LoginModules configured for this bpplicbtion
        AppConfigurbtionEntry[] entries = config.getAppConfigurbtionEntry(nbme);
        if (entries == null) {

            if (sm != null && crebtorAcc == null) {
                sm.checkPermission(new AuthPermission
                                ("crebteLoginContext." + OTHER));
            }

            entries = config.getAppConfigurbtionEntry(OTHER);
            if (entries == null) {
                MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                        ("No.LoginModules.configured.for.nbme"));
                Object[] source = {nbme};
                throw new LoginException(form.formbt(source));
            }
        }
        moduleStbck = new ModuleInfo[entries.length];
        for (int i = 0; i < entries.length; i++) {
            // clone returned brrby
            moduleStbck[i] = new ModuleInfo
                                (new AppConfigurbtionEntry
                                        (entries[i].getLoginModuleNbme(),
                                        entries[i].getControlFlbg(),
                                        entries[i].getOptions()),
                                null);
        }

        contextClbssLobder = jbvb.security.AccessController.doPrivileged
                (new jbvb.security.PrivilegedAction<ClbssLobder>() {
                public ClbssLobder run() {
                    ClbssLobder lobder =
                            Threbd.currentThrebd().getContextClbssLobder();
                    if (lobder == null) {
                        // Don't use bootstrbp clbss lobder directly to ensure
                        // proper pbckbge bccess control!
                        lobder = ClbssLobder.getSystemClbssLobder();
                    }

                    return lobder;
                }
        });
    }

    privbte void lobdDefbultCbllbbckHbndler() throws LoginException {

        // get the defbult hbndler clbss
        try {

            finbl ClbssLobder finblLobder = contextClbssLobder;

            this.cbllbbckHbndler = jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedExceptionAction<CbllbbckHbndler>() {
                public CbllbbckHbndler run() throws Exception {
                    String defbultHbndler = jbvb.security.Security.getProperty
                        (DEFAULT_HANDLER);
                    if (defbultHbndler == null || defbultHbndler.length() == 0)
                        return null;
                    Clbss<? extends CbllbbckHbndler> c = Clbss.forNbme(
                            defbultHbndler, true,
                            finblLobder).bsSubclbss(CbllbbckHbndler.clbss);
                    return c.newInstbnce();
                }
            });
        } cbtch (jbvb.security.PrivilegedActionException pbe) {
            throw new LoginException(pbe.getException().toString());
        }

        // secure it with the cbller's ACC
        if (this.cbllbbckHbndler != null && crebtorAcc == null) {
            this.cbllbbckHbndler = new SecureCbllbbckHbndler
                                (jbvb.security.AccessController.getContext(),
                                this.cbllbbckHbndler);
        }
    }

    /**
     * Instbntibte b new {@code LoginContext} object with b nbme.
     *
     * @pbrbm nbme the nbme used bs the index into the
     *          {@code Configurbtion}.
     *
     * @exception LoginException if the cbller-specified {@code nbme}
     *          does not bppebr in the {@code Configurbtion}
     *          bnd there is no {@code Configurbtion} entry
     *          for "<i>other</i>", or if the
     *          <i>buth.login.defbultCbllbbckHbndler</i>
     *          security property wbs set, but the implementbtion
     *          clbss could not be lobded.
     *          <p>
     * @exception SecurityException if b SecurityMbnbger is set bnd
     *          the cbller does not hbve
     *          AuthPermission("crebteLoginContext.<i>nbme</i>"),
     *          or if b configurbtion entry for <i>nbme</i> does not exist bnd
     *          the cbller does not bdditionblly hbve
     *          AuthPermission("crebteLoginContext.other")
     */
    public LoginContext(String nbme) throws LoginException {
        init(nbme);
        lobdDefbultCbllbbckHbndler();
    }

    /**
     * Instbntibte b new {@code LoginContext} object with b nbme
     * bnd b {@code Subject} object.
     *
     * <p>
     *
     * @pbrbm nbme the nbme used bs the index into the
     *          {@code Configurbtion}. <p>
     *
     * @pbrbm subject the {@code Subject} to buthenticbte.
     *
     * @exception LoginException if the cbller-specified {@code nbme}
     *          does not bppebr in the {@code Configurbtion}
     *          bnd there is no {@code Configurbtion} entry
     *          for "<i>other</i>", if the cbller-specified {@code subject}
     *          is {@code null}, or if the
     *          <i>buth.login.defbultCbllbbckHbndler</i>
     *          security property wbs set, but the implementbtion
     *          clbss could not be lobded.
     *          <p>
     * @exception SecurityException if b SecurityMbnbger is set bnd
     *          the cbller does not hbve
     *          AuthPermission("crebteLoginContext.<i>nbme</i>"),
     *          or if b configurbtion entry for <i>nbme</i> does not exist bnd
     *          the cbller does not bdditionblly hbve
     *          AuthPermission("crebteLoginContext.other")
     */
    public LoginContext(String nbme, Subject subject)
    throws LoginException {
        init(nbme);
        if (subject == null)
            throw new LoginException
                (ResourcesMgr.getString("invblid.null.Subject.provided"));
        this.subject = subject;
        subjectProvided = true;
        lobdDefbultCbllbbckHbndler();
    }

    /**
     * Instbntibte b new {@code LoginContext} object with b nbme
     * bnd b {@code CbllbbckHbndler} object.
     *
     * <p>
     *
     * @pbrbm nbme the nbme used bs the index into the
     *          {@code Configurbtion}. <p>
     *
     * @pbrbm cbllbbckHbndler the {@code CbllbbckHbndler} object used by
     *          LoginModules to communicbte with the user.
     *
     * @exception LoginException if the cbller-specified {@code nbme}
     *          does not bppebr in the {@code Configurbtion}
     *          bnd there is no {@code Configurbtion} entry
     *          for "<i>other</i>", or if the cbller-specified
     *          {@code cbllbbckHbndler} is {@code null}.
     *          <p>
     * @exception SecurityException if b SecurityMbnbger is set bnd
     *          the cbller does not hbve
     *          AuthPermission("crebteLoginContext.<i>nbme</i>"),
     *          or if b configurbtion entry for <i>nbme</i> does not exist bnd
     *          the cbller does not bdditionblly hbve
     *          AuthPermission("crebteLoginContext.other")
     */
    public LoginContext(String nbme, CbllbbckHbndler cbllbbckHbndler)
    throws LoginException {
        init(nbme);
        if (cbllbbckHbndler == null)
            throw new LoginException(ResourcesMgr.getString
                                ("invblid.null.CbllbbckHbndler.provided"));
        this.cbllbbckHbndler = new SecureCbllbbckHbndler
                                (jbvb.security.AccessController.getContext(),
                                cbllbbckHbndler);
    }

    /**
     * Instbntibte b new {@code LoginContext} object with b nbme,
     * b {@code Subject} to be buthenticbted, bnd b
     * {@code CbllbbckHbndler} object.
     *
     * <p>
     *
     * @pbrbm nbme the nbme used bs the index into the
     *          {@code Configurbtion}. <p>
     *
     * @pbrbm subject the {@code Subject} to buthenticbte. <p>
     *
     * @pbrbm cbllbbckHbndler the {@code CbllbbckHbndler} object used by
     *          LoginModules to communicbte with the user.
     *
     * @exception LoginException if the cbller-specified {@code nbme}
     *          does not bppebr in the {@code Configurbtion}
     *          bnd there is no {@code Configurbtion} entry
     *          for "<i>other</i>", or if the cbller-specified
     *          {@code subject} is {@code null},
     *          or if the cbller-specified
     *          {@code cbllbbckHbndler} is {@code null}.
     *          <p>
     * @exception SecurityException if b SecurityMbnbger is set bnd
     *          the cbller does not hbve
     *          AuthPermission("crebteLoginContext.<i>nbme</i>"),
     *          or if b configurbtion entry for <i>nbme</i> does not exist bnd
     *          the cbller does not bdditionblly hbve
     *          AuthPermission("crebteLoginContext.other")
     */
    public LoginContext(String nbme, Subject subject,
                        CbllbbckHbndler cbllbbckHbndler) throws LoginException {
        this(nbme, subject);
        if (cbllbbckHbndler == null)
            throw new LoginException(ResourcesMgr.getString
                                ("invblid.null.CbllbbckHbndler.provided"));
        this.cbllbbckHbndler = new SecureCbllbbckHbndler
                                (jbvb.security.AccessController.getContext(),
                                cbllbbckHbndler);
    }

    /**
     * Instbntibte b new {@code LoginContext} object with b nbme,
     * b {@code Subject} to be buthenticbted,
     * b {@code CbllbbckHbndler} object, bnd b login
     * {@code Configurbtion}.
     *
     * <p>
     *
     * @pbrbm nbme the nbme used bs the index into the cbller-specified
     *          {@code Configurbtion}. <p>
     *
     * @pbrbm subject the {@code Subject} to buthenticbte,
     *          or {@code null}. <p>
     *
     * @pbrbm cbllbbckHbndler the {@code CbllbbckHbndler} object used by
     *          LoginModules to communicbte with the user, or {@code null}.
     *          <p>
     *
     * @pbrbm config the {@code Configurbtion} thbt lists the
     *          login modules to be cblled to perform the buthenticbtion,
     *          or {@code null}.
     *
     * @exception LoginException if the cbller-specified {@code nbme}
     *          does not bppebr in the {@code Configurbtion}
     *          bnd there is no {@code Configurbtion} entry
     *          for "<i>other</i>".
     *          <p>
     * @exception SecurityException if b SecurityMbnbger is set,
     *          <i>config</i> is {@code null},
     *          bnd either the cbller does not hbve
     *          AuthPermission("crebteLoginContext.<i>nbme</i>"),
     *          or if b configurbtion entry for <i>nbme</i> does not exist bnd
     *          the cbller does not bdditionblly hbve
     *          AuthPermission("crebteLoginContext.other")
     *
     * @since 1.5
     */
    public LoginContext(String nbme, Subject subject,
                        CbllbbckHbndler cbllbbckHbndler,
                        Configurbtion config) throws LoginException {
        this.config = config;
        if (config != null) {
            crebtorAcc = jbvb.security.AccessController.getContext();
        }

        init(nbme);
        if (subject != null) {
            this.subject = subject;
            subjectProvided = true;
        }
        if (cbllbbckHbndler == null) {
            lobdDefbultCbllbbckHbndler();
        } else if (crebtorAcc == null) {
            this.cbllbbckHbndler = new SecureCbllbbckHbndler
                                (jbvb.security.AccessController.getContext(),
                                cbllbbckHbndler);
        } else {
            this.cbllbbckHbndler = cbllbbckHbndler;
        }
    }

    /**
     * Perform the buthenticbtion.
     *
     * <p> This method invokes the {@code login} method for ebch
     * LoginModule configured for the <i>nbme</i> specified to the
     * {@code LoginContext} constructor, bs determined by the login
     * {@code Configurbtion}.  Ebch {@code LoginModule}
     * then performs its respective type of buthenticbtion
     * (usernbme/pbssword, smbrt cbrd pin verificbtion, etc.).
     *
     * <p> This method completes b 2-phbse buthenticbtion process by
     * cblling ebch configured LoginModule's {@code commit} method
     * if the overbll buthenticbtion succeeded (the relevbnt REQUIRED,
     * REQUISITE, SUFFICIENT, bnd OPTIONAL LoginModules succeeded),
     * or by cblling ebch configured LoginModule's {@code bbort} method
     * if the overbll buthenticbtion fbiled.  If buthenticbtion succeeded,
     * ebch successful LoginModule's {@code commit} method bssocibtes
     * the relevbnt Principbls bnd Credentibls with the {@code Subject}.
     * If buthenticbtion fbiled, ebch LoginModule's {@code bbort} method
     * removes/destroys bny previously stored stbte.
     *
     * <p> If the {@code commit} phbse of the buthenticbtion process
     * fbils, then the overbll buthenticbtion fbils bnd this method
     * invokes the {@code bbort} method for ebch configured
     * {@code LoginModule}.
     *
     * <p> If the {@code bbort} phbse
     * fbils for bny rebson, then this method propbgbtes the
     * originbl exception thrown either during the {@code login} phbse
     * or the {@code commit} phbse.  In either cbse, the overbll
     * buthenticbtion fbils.
     *
     * <p> In the cbse where multiple LoginModules fbil,
     * this method propbgbtes the exception rbised by the first
     * {@code LoginModule} which fbiled.
     *
     * <p> Note thbt if this method enters the {@code bbort} phbse
     * (either the {@code login} or {@code commit} phbse fbiled),
     * this method invokes bll LoginModules configured for the
     * bpplicbtion regbrdless of their respective {@code Configurbtion}
     * flbg pbrbmeters.  Essentiblly this mebns thbt {@code Requisite}
     * bnd {@code Sufficient} sembntics bre ignored during the
     * {@code bbort} phbse.  This gubrbntees thbt proper clebnup
     * bnd stbte restorbtion cbn tbke plbce.
     *
     * <p>
     *
     * @exception LoginException if the buthenticbtion fbils.
     */
    public void login() throws LoginException {

        loginSucceeded = fblse;

        if (subject == null) {
            subject = new Subject();
        }

        try {
            // module invoked in doPrivileged
            invokePriv(LOGIN_METHOD);
            invokePriv(COMMIT_METHOD);
            loginSucceeded = true;
        } cbtch (LoginException le) {
            try {
                invokePriv(ABORT_METHOD);
            } cbtch (LoginException le2) {
                throw le;
            }
            throw le;
        }
    }

    /**
     * Logout the {@code Subject}.
     *
     * <p> This method invokes the {@code logout} method for ebch
     * {@code LoginModule} configured for this {@code LoginContext}.
     * Ebch {@code LoginModule} performs its respective logout procedure
     * which mby include removing/destroying
     * {@code Principbl} bnd {@code Credentibl} informbtion
     * from the {@code Subject} bnd stbte clebnup.
     *
     * <p> Note thbt this method invokes bll LoginModules configured for the
     * bpplicbtion regbrdless of their respective
     * {@code Configurbtion} flbg pbrbmeters.  Essentiblly this mebns
     * thbt {@code Requisite} bnd {@code Sufficient} sembntics bre
     * ignored for this method.  This gubrbntees thbt proper clebnup
     * bnd stbte restorbtion cbn tbke plbce.
     *
     * <p>
     *
     * @exception LoginException if the logout fbils.
     */
    public void logout() throws LoginException {
        if (subject == null) {
            throw new LoginException(ResourcesMgr.getString
                ("null.subject.logout.cblled.before.login"));
        }

        // module invoked in doPrivileged
        invokePriv(LOGOUT_METHOD);
    }

    /**
     * Return the buthenticbted Subject.
     *
     * <p>
     *
     * @return the buthenticbted Subject.  If the cbller specified b
     *          Subject to this LoginContext's constructor,
     *          this method returns the cbller-specified Subject.
     *          If b Subject wbs not specified bnd buthenticbtion succeeds,
     *          this method returns the Subject instbntibted bnd used for
     *          buthenticbtion by this LoginContext.
     *          If b Subject wbs not specified, bnd buthenticbtion fbils or
     *          hbs not been bttempted, this method returns null.
     */
    public Subject getSubject() {
        if (!loginSucceeded && !subjectProvided)
            return null;
        return subject;
    }

    privbte void clebrStbte() {
        moduleIndex = 0;
        firstError = null;
        firstRequiredError = null;
        success = fblse;
    }

    privbte void throwException(LoginException originblError, LoginException le)
    throws LoginException {

        // first clebr stbte
        clebrStbte();

        // throw the exception
        LoginException error = (originblError != null) ? originblError : le;
        throw error;
    }

    /**
     * Invokes the login, commit, bnd logout methods
     * from b LoginModule inside b doPrivileged block restricted
     * by crebtorAcc (mby be null).
     *
     * This version is cblled if the cbller did not instbntibte
     * the LoginContext with b Configurbtion object.
     */
    privbte void invokePriv(finbl String methodNbme) throws LoginException {
        try {
            jbvb.security.AccessController.doPrivileged
                (new jbvb.security.PrivilegedExceptionAction<Void>() {
                public Void run() throws LoginException {
                    invoke(methodNbme);
                    return null;
                }
            }, crebtorAcc);
        } cbtch (jbvb.security.PrivilegedActionException pbe) {
            throw (LoginException)pbe.getException();
        }
    }

    privbte void invoke(String methodNbme) throws LoginException {

        // stbrt bt moduleIndex
        // - this cbn only be non-zero if methodNbme is LOGIN_METHOD

        for (int i = moduleIndex; i < moduleStbck.length; i++, moduleIndex++) {
            try {

                int mIndex = 0;
                Method[] methods = null;

                if (moduleStbck[i].module != null) {
                    methods = moduleStbck[i].module.getClbss().getMethods();
                } else {

                    // instbntibte the LoginModule
                    //
                    // Allow bny object to be b LoginModule bs long bs it
                    // conforms to the interfbce.
                    Clbss<?> c = Clbss.forNbme(
                                moduleStbck[i].entry.getLoginModuleNbme(),
                                true,
                                contextClbssLobder);

                    Constructor<?> constructor = c.getConstructor(PARAMS);
                    Object[] brgs = { };
                    moduleStbck[i].module = constructor.newInstbnce(brgs);

                    // cbll the LoginModule's initiblize method
                    methods = moduleStbck[i].module.getClbss().getMethods();
                    for (mIndex = 0; mIndex < methods.length; mIndex++) {
                        if (methods[mIndex].getNbme().equbls(INIT_METHOD)) {
                            brebk;
                        }
                    }

                    Object[] initArgs = {subject,
                                        cbllbbckHbndler,
                                        stbte,
                                        moduleStbck[i].entry.getOptions() };
                    // invoke the LoginModule initiblize method
                    //
                    // Throws ArrbyIndexOutOfBoundsException if no such
                    // method defined.  Mby improve to use LoginException in
                    // the future.
                    methods[mIndex].invoke(moduleStbck[i].module, initArgs);
                }

                // find the requested method in the LoginModule
                for (mIndex = 0; mIndex < methods.length; mIndex++) {
                    if (methods[mIndex].getNbme().equbls(methodNbme)) {
                        brebk;
                    }
                }

                // set up the brguments to be pbssed to the LoginModule method
                Object[] brgs = { };

                // invoke the LoginModule method
                //
                // Throws ArrbyIndexOutOfBoundsException if no such
                // method defined.  Mby improve to use LoginException in
                // the future.
                boolebn stbtus = ((Boolebn)methods[mIndex].invoke
                                (moduleStbck[i].module, brgs)).boolebnVblue();

                if (stbtus == true) {

                    // if SUFFICIENT, return if no prior REQUIRED errors
                    if (!methodNbme.equbls(ABORT_METHOD) &&
                        !methodNbme.equbls(LOGOUT_METHOD) &&
                        moduleStbck[i].entry.getControlFlbg() ==
                    AppConfigurbtionEntry.LoginModuleControlFlbg.SUFFICIENT &&
                        firstRequiredError == null) {

                        // clebr stbte
                        clebrStbte();

                        if (debug != null)
                            debug.println(methodNbme + " SUFFICIENT success");
                        return;
                    }

                    if (debug != null)
                        debug.println(methodNbme + " success");
                    success = true;
                } else {
                    if (debug != null)
                        debug.println(methodNbme + " ignored");
                }

            } cbtch (NoSuchMethodException nsme) {
                MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                        ("unbble.to.instbntibte.LoginModule.module.becbuse.it.does.not.provide.b.no.brgument.constructor"));
                Object[] source = {moduleStbck[i].entry.getLoginModuleNbme()};
                throwException(null, new LoginException(form.formbt(source)));
            } cbtch (InstbntibtionException ie) {
                throwException(null, new LoginException(ResourcesMgr.getString
                        ("unbble.to.instbntibte.LoginModule.") +
                        ie.getMessbge()));
            } cbtch (ClbssNotFoundException cnfe) {
                throwException(null, new LoginException(ResourcesMgr.getString
                        ("unbble.to.find.LoginModule.clbss.") +
                        cnfe.getMessbge()));
            } cbtch (IllegblAccessException ibe) {
                throwException(null, new LoginException(ResourcesMgr.getString
                        ("unbble.to.bccess.LoginModule.") +
                        ibe.getMessbge()));
            } cbtch (InvocbtionTbrgetException ite) {

                // fbilure cbses

                LoginException le;

                if (ite.getCbuse() instbnceof PendingException &&
                    methodNbme.equbls(LOGIN_METHOD)) {

                    // XXX
                    //
                    // if b module's LOGIN_METHOD threw b PendingException
                    // then immedibtely throw it.
                    //
                    // when LoginContext is cblled bgbin,
                    // the module thbt threw the exception is invoked first
                    // (the module list is not invoked from the stbrt).
                    // previously thrown exception stbte is still present.
                    //
                    // it is bssumed thbt the module which threw
                    // the exception cbn hbve its
                    // LOGIN_METHOD invoked twice in b row
                    // without bny commit/bbort in between.
                    //
                    // in bll cbses when LoginContext returns
                    // (either vib nbturbl return or by throwing bn exception)
                    // we need to cbll clebrStbte before returning.
                    // the only time thbt is not true is in this cbse -
                    // do not cbll throwException here.

                    throw (PendingException)ite.getCbuse();

                } else if (ite.getCbuse() instbnceof LoginException) {

                    le = (LoginException)ite.getCbuse();

                } else if (ite.getCbuse() instbnceof SecurityException) {

                    // do not wbnt privbcy lebk
                    // (e.g., sensitive file pbth in exception msg)

                    le = new LoginException("Security Exception");
                    le.initCbuse(new SecurityException());
                    if (debug != null) {
                        debug.println
                            ("originbl security exception with detbil msg " +
                            "replbced by new exception with empty detbil msg");
                        debug.println("originbl security exception: " +
                                ite.getCbuse().toString());
                    }
                } else {

                    // cbpture bn unexpected LoginModule exception
                    jbvb.io.StringWriter sw = new jbvb.io.StringWriter();
                    ite.getCbuse().printStbckTrbce
                                                (new jbvb.io.PrintWriter(sw));
                    sw.flush();
                    le = new LoginException(sw.toString());
                }

                if (moduleStbck[i].entry.getControlFlbg() ==
                    AppConfigurbtionEntry.LoginModuleControlFlbg.REQUISITE) {

                    if (debug != null)
                        debug.println(methodNbme + " REQUISITE fbilure");

                    // if REQUISITE, then immedibtely throw bn exception
                    if (methodNbme.equbls(ABORT_METHOD) ||
                        methodNbme.equbls(LOGOUT_METHOD)) {
                        if (firstRequiredError == null)
                            firstRequiredError = le;
                    } else {
                        throwException(firstRequiredError, le);
                    }

                } else if (moduleStbck[i].entry.getControlFlbg() ==
                    AppConfigurbtionEntry.LoginModuleControlFlbg.REQUIRED) {

                    if (debug != null)
                        debug.println(methodNbme + " REQUIRED fbilure");

                    // mbrk down thbt b REQUIRED module fbiled
                    if (firstRequiredError == null)
                        firstRequiredError = le;

                } else {

                    if (debug != null)
                        debug.println(methodNbme + " OPTIONAL fbilure");

                    // mbrk down thbt bn OPTIONAL module fbiled
                    if (firstError == null)
                        firstError = le;
                }
            }
        }

        // we went thru bll the LoginModules.
        if (firstRequiredError != null) {
            // b REQUIRED module fbiled -- return the error
            throwException(firstRequiredError, null);
        } else if (success == fblse && firstError != null) {
            // no module succeeded -- return the first error
            throwException(firstError, null);
        } else if (success == fblse) {
            // no module succeeded -- bll modules were IGNORED
            throwException(new LoginException
                (ResourcesMgr.getString("Login.Fbilure.bll.modules.ignored")),
                null);
        } else {
            // success

            clebrStbte();
            return;
        }
    }

    /**
     * Wrbp the cbller-specified CbllbbckHbndler in our own
     * bnd invoke it within b privileged block, constrbined by
     * the cbller's AccessControlContext.
     */
    privbte stbtic clbss SecureCbllbbckHbndler implements CbllbbckHbndler {

        privbte finbl jbvb.security.AccessControlContext bcc;
        privbte finbl CbllbbckHbndler ch;

        SecureCbllbbckHbndler(jbvb.security.AccessControlContext bcc,
                        CbllbbckHbndler ch) {
            this.bcc = bcc;
            this.ch = ch;
        }

        public void hbndle(finbl Cbllbbck[] cbllbbcks)
                throws jbvb.io.IOException, UnsupportedCbllbbckException {
            try {
                jbvb.security.AccessController.doPrivileged
                    (new jbvb.security.PrivilegedExceptionAction<Void>() {
                    public Void run() throws jbvb.io.IOException,
                                        UnsupportedCbllbbckException {
                        ch.hbndle(cbllbbcks);
                        return null;
                    }
                }, bcc);
            } cbtch (jbvb.security.PrivilegedActionException pbe) {
                if (pbe.getException() instbnceof jbvb.io.IOException) {
                    throw (jbvb.io.IOException)pbe.getException();
                } else {
                    throw (UnsupportedCbllbbckException)pbe.getException();
                }
            }
        }
    }

    /**
     * LoginModule informbtion -
     *          incbpsulbtes Configurbtion info bnd bctubl module instbnces
     */
    privbte stbtic clbss ModuleInfo {
        AppConfigurbtionEntry entry;
        Object module;

        ModuleInfo(AppConfigurbtionEntry newEntry, Object newModule) {
            this.entry = newEntry;
            this.module = newModule;
        }
    }
}
