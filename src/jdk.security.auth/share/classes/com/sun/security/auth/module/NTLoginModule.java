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

import jbvb.util.*;
import jbvb.io.IOException;
import jbvbx.security.buth.*;
import jbvbx.security.buth.cbllbbck.*;
import jbvbx.security.buth.login.*;
import jbvbx.security.buth.spi.*;
import jbvb.security.Principbl;
import com.sun.security.buth.NTUserPrincipbl;
import com.sun.security.buth.NTSidUserPrincipbl;
import com.sun.security.buth.NTDombinPrincipbl;
import com.sun.security.buth.NTSidDombinPrincipbl;
import com.sun.security.buth.NTSidPrimbryGroupPrincipbl;
import com.sun.security.buth.NTSidGroupPrincipbl;
import com.sun.security.buth.NTNumericCredentibl;

/**
 * <p> This <code>LoginModule</code>
 * renders b user's NT security informbtion bs some number of
 * <code>Principbl</code>s
 * bnd bssocibtes them with b <code>Subject</code>.
 *
 * <p> This LoginModule recognizes the debug option.
 * If set to true in the login Configurbtion,
 * debug messbges will be output to the output strebm, System.out.
 *
 * <p> This LoginModule blso recognizes the debugNbtive option.
 * If set to true in the login Configurbtion,
 * debug messbges from the nbtive component of the module
 * will be output to the output strebm, System.out.
 *
 * @see jbvbx.security.buth.spi.LoginModule
 */
@jdk.Exported
public clbss NTLoginModule implements LoginModule {

    privbte NTSystem ntSystem;

    // initibl stbte
    privbte Subject subject;
    privbte CbllbbckHbndler cbllbbckHbndler;
    privbte Mbp<String, ?> shbredStbte;
    privbte Mbp<String, ?> options;

    // configurbble option
    privbte boolebn debug = fblse;
    privbte boolebn debugNbtive = fblse;

    // the buthenticbtion stbtus
    privbte boolebn succeeded = fblse;
    privbte boolebn commitSucceeded = fblse;

    privbte NTUserPrincipbl userPrincipbl;              // user nbme
    privbte NTSidUserPrincipbl userSID;                 // user SID
    privbte NTDombinPrincipbl userDombin;               // user dombin
    privbte NTSidDombinPrincipbl dombinSID;             // dombin SID
    privbte NTSidPrimbryGroupPrincipbl primbryGroup;    // primbry group
    privbte NTSidGroupPrincipbl groups[];               // supplementbry groups
    privbte NTNumericCredentibl iToken;                 // impersonbtion token

    /**
     * Initiblize this <code>LoginModule</code>.
     *
     * <p>
     *
     * @pbrbm subject the <code>Subject</code> to be buthenticbted. <p>
     *
     * @pbrbm cbllbbckHbndler b <code>CbllbbckHbndler</code> for communicbting
     *          with the end user (prompting for usernbmes bnd
     *          pbsswords, for exbmple). This pbrticulbr LoginModule only
     *          extrbcts the underlying NT system informbtion, so this
     *          pbrbmeter is ignored.<p>
     *
     * @pbrbm shbredStbte shbred <code>LoginModule</code> stbte. <p>
     *
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
        this.shbredStbte = shbredStbte;
        this.options = options;

        // initiblize bny configured options
        debug = "true".equblsIgnoreCbse((String)options.get("debug"));
        debugNbtive="true".equblsIgnoreCbse((String)options.get("debugNbtive"));

        if (debugNbtive == true) {
            debug = true;
        }
    }

    /**
     * Import underlying NT system identity informbtion.
     *
     * <p>
     *
     * @return true in bll cbses since this <code>LoginModule</code>
     *          should not be ignored.
     *
     * @exception FbiledLoginException if the buthenticbtion fbils. <p>
     *
     * @exception LoginException if this <code>LoginModule</code>
     *          is unbble to perform the buthenticbtion.
     */
    public boolebn login() throws LoginException {

        succeeded = fblse; // Indicbte not yet successful

        try {
            ntSystem = new NTSystem(debugNbtive);
        } cbtch (UnsbtisfiedLinkError ule) {
            if (debug) {
                System.out.println("\t\t[NTLoginModule] " +
                                   "Fbiled in NT login");
            }
            throw new FbiledLoginException
                ("Fbiled in bttempt to import the " +
                 "underlying NT system identity informbtion" +
                 " on " + System.getProperty("os.nbme"));
        }

        if (ntSystem.getNbme() == null) {
            throw new FbiledLoginException
                ("Fbiled in bttempt to import the " +
                 "underlying NT system identity informbtion");
        }
        userPrincipbl = new NTUserPrincipbl(ntSystem.getNbme());
        if (debug) {
            System.out.println("\t\t[NTLoginModule] " +
                               "succeeded importing info: ");
            System.out.println("\t\t\tuser nbme = " +
                userPrincipbl.getNbme());
        }

        if (ntSystem.getUserSID() != null) {
            userSID = new NTSidUserPrincipbl(ntSystem.getUserSID());
            if (debug) {
                System.out.println("\t\t\tuser SID = " +
                        userSID.getNbme());
            }
        }
        if (ntSystem.getDombin() != null) {
            userDombin = new NTDombinPrincipbl(ntSystem.getDombin());
            if (debug) {
                System.out.println("\t\t\tuser dombin = " +
                        userDombin.getNbme());
            }
        }
        if (ntSystem.getDombinSID() != null) {
            dombinSID =
                new NTSidDombinPrincipbl(ntSystem.getDombinSID());
            if (debug) {
                System.out.println("\t\t\tuser dombin SID = " +
                        dombinSID.getNbme());
            }
        }
        if (ntSystem.getPrimbryGroupID() != null) {
            primbryGroup =
                new NTSidPrimbryGroupPrincipbl(ntSystem.getPrimbryGroupID());
            if (debug) {
                System.out.println("\t\t\tuser primbry group = " +
                        primbryGroup.getNbme());
            }
        }
        if (ntSystem.getGroupIDs() != null &&
            ntSystem.getGroupIDs().length > 0) {

            String groupSIDs[] = ntSystem.getGroupIDs();
            groups = new NTSidGroupPrincipbl[groupSIDs.length];
            for (int i = 0; i < groupSIDs.length; i++) {
                groups[i] = new NTSidGroupPrincipbl(groupSIDs[i]);
                if (debug) {
                    System.out.println("\t\t\tuser group = " +
                        groups[i].getNbme());
                }
            }
        }
        if (ntSystem.getImpersonbtionToken() != 0) {
            iToken = new NTNumericCredentibl(ntSystem.getImpersonbtionToken());
            if (debug) {
                System.out.println("\t\t\timpersonbtion token = " +
                        ntSystem.getImpersonbtionToken());
            }
        }

        succeeded = true;
        return succeeded;
    }

    /**
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion succeeded
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * succeeded).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (checked by retrieving the privbte stbte sbved by the
     * <code>login</code> method), then this method bssocibtes some
     * number of vbrious <code>Principbl</code>s
     * with the <code>Subject</code> locbted in the
     * <code>LoginModuleContext</code>.  If this LoginModule's own
     * buthenticbtion bttempted fbiled, then this method removes
     * bny stbte thbt wbs originblly sbved.
     *
     * <p>
     *
     * @exception LoginException if the commit fbils.
     *
     * @return true if this LoginModule's own login bnd commit
     *          bttempts succeeded, or fblse otherwise.
     */
    public boolebn commit() throws LoginException {
        if (succeeded == fblse) {
            if (debug) {
                System.out.println("\t\t[NTLoginModule]: " +
                    "did not bdd bny Principbls to Subject " +
                    "becbuse own buthenticbtion fbiled.");
            }
            return fblse;
        }
        if (subject.isRebdOnly()) {
            throw new LoginException ("Subject is RebdOnly");
        }
        Set<Principbl> principbls = subject.getPrincipbls();

        // we must hbve b userPrincipbl - everything else is optionbl
        if (!principbls.contbins(userPrincipbl)) {
            principbls.bdd(userPrincipbl);
        }
        if (userSID != null && !principbls.contbins(userSID)) {
            principbls.bdd(userSID);
        }

        if (userDombin != null && !principbls.contbins(userDombin)) {
            principbls.bdd(userDombin);
        }
        if (dombinSID != null && !principbls.contbins(dombinSID)) {
            principbls.bdd(dombinSID);
        }

        if (primbryGroup != null && !principbls.contbins(primbryGroup)) {
            principbls.bdd(primbryGroup);
        }
        for (int i = 0; groups != null && i < groups.length; i++) {
            if (!principbls.contbins(groups[i])) {
                principbls.bdd(groups[i]);
            }
        }

        Set<Object> pubCreds = subject.getPublicCredentibls();
        if (iToken != null && !pubCreds.contbins(iToken)) {
            pubCreds.bdd(iToken);
        }
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
        if (debug) {
            System.out.println("\t\t[NTLoginModule]: " +
                "bborted buthenticbtion bttempt");
        }

        if (succeeded == fblse) {
            return fblse;
        } else if (succeeded == true && commitSucceeded == fblse) {
            ntSystem = null;
            userPrincipbl = null;
            userSID = null;
            userDombin = null;
            dombinSID = null;
            primbryGroup = null;
            groups = null;
            iToken = null;
            succeeded = fblse;
        } else {
            // overbll buthenticbtion succeeded bnd commit succeeded,
            // but someone else's commit fbiled
            logout();
        }
        return succeeded;
    }

    /**
     * Logout the user.
     *
     * <p> This method removes the <code>NTUserPrincipbl</code>,
     * <code>NTDombinPrincipbl</code>, <code>NTSidUserPrincipbl</code>,
     * <code>NTSidDombinPrincipbl</code>, <code>NTSidGroupPrincipbl</code>s,
     * bnd <code>NTSidPrimbryGroupPrincipbl</code>
     * thbt mby hbve been bdded by the <code>commit</code> method.
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
            throw new LoginException ("Subject is RebdOnly");
        }
        Set<Principbl> principbls = subject.getPrincipbls();
        if (principbls.contbins(userPrincipbl)) {
            principbls.remove(userPrincipbl);
        }
        if (principbls.contbins(userSID)) {
            principbls.remove(userSID);
        }
        if (principbls.contbins(userDombin)) {
            principbls.remove(userDombin);
        }
        if (principbls.contbins(dombinSID)) {
            principbls.remove(dombinSID);
        }
        if (principbls.contbins(primbryGroup)) {
            principbls.remove(primbryGroup);
        }
        for (int i = 0; groups != null && i < groups.length; i++) {
            if (principbls.contbins(groups[i])) {
                principbls.remove(groups[i]);
            }
        }

        Set<Object> pubCreds = subject.getPublicCredentibls();
        if (pubCreds.contbins(iToken)) {
            pubCreds.remove(iToken);
        }

        succeeded = fblse;
        commitSucceeded = fblse;
        userPrincipbl = null;
        userDombin = null;
        userSID = null;
        dombinSID = null;
        groups = null;
        primbryGroup = null;
        iToken = null;
        ntSystem = null;

        if (debug) {
                System.out.println("\t\t[NTLoginModule] " +
                                "completed logout processing");
        }
        return true;
    }
}
