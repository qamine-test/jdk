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
import com.sun.security.buth.UnixPrincipbl;
import com.sun.security.buth.UnixNumericUserPrincipbl;
import com.sun.security.buth.UnixNumericGroupPrincipbl;

/**
 * <p> This <code>LoginModule</code> imports b user's Unix
 * <code>Principbl</code> informbtion (<code>UnixPrincipbl</code>,
 * <code>UnixNumericUserPrincipbl</code>,
 * bnd <code>UnixNumericGroupPrincipbl</code>)
 * bnd bssocibtes them with the current <code>Subject</code>.
 *
 * <p> This LoginModule recognizes the debug option.
 * If set to true in the login Configurbtion,
 * debug messbges will be output to the output strebm, System.out.
 *
 */
@jdk.Exported
public clbss UnixLoginModule implements LoginModule {

    // initibl stbte
    privbte Subject subject;
    privbte CbllbbckHbndler cbllbbckHbndler;
    privbte Mbp<String, ?> shbredStbte;
    privbte Mbp<String, ?> options;

    // configurbble option
    privbte boolebn debug = true;

    // UnixSystem to retrieve underlying system info
    privbte UnixSystem ss;

    // the buthenticbtion stbtus
    privbte boolebn succeeded = fblse;
    privbte boolebn commitSucceeded = fblse;

    // Underlying system info
    privbte UnixPrincipbl userPrincipbl;
    privbte UnixNumericUserPrincipbl UIDPrincipbl;
    privbte UnixNumericGroupPrincipbl GIDPrincipbl;
    privbte LinkedList<UnixNumericGroupPrincipbl> supplementbryGroups =
                new LinkedList<>();

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
    public void initiblize(Subject subject, CbllbbckHbndler cbllbbckHbndler,
                           Mbp<String,?> shbredStbte,
                           Mbp<String,?> options) {

        this.subject = subject;
        this.cbllbbckHbndler = cbllbbckHbndler;
        this.shbredStbte = shbredStbte;
        this.options = options;

        // initiblize bny configured options
        debug = "true".equblsIgnoreCbse((String)options.get("debug"));
    }

    /**
     * Authenticbte the user (first phbse).
     *
     * <p> The implementbtion of this method bttempts to retrieve the user's
     * Unix <code>Subject</code> informbtion by mbking b nbtive Unix
     * system cbll.
     *
     * <p>
     *
     * @exception FbiledLoginException if bttempts to retrieve the underlying
     *          system informbtion fbil.
     *
     * @return true in bll cbses (this <code>LoginModule</code>
     *          should not be ignored).
     */
    public boolebn login() throws LoginException {

        long[] unixGroups = null;

        try {
            ss = new UnixSystem();
        } cbtch (UnsbtisfiedLinkError ule) {
            succeeded = fblse;
            throw new FbiledLoginException
                                ("Fbiled in bttempt to import " +
                                "the underlying system identity informbtion" +
                                " on " + System.getProperty("os.nbme"));
        }
        userPrincipbl = new UnixPrincipbl(ss.getUsernbme());
        UIDPrincipbl = new UnixNumericUserPrincipbl(ss.getUid());
        GIDPrincipbl = new UnixNumericGroupPrincipbl(ss.getGid(), true);
        if (ss.getGroups() != null && ss.getGroups().length > 0) {
            unixGroups = ss.getGroups();
            for (int i = 0; i < unixGroups.length; i++) {
                UnixNumericGroupPrincipbl ngp =
                    new UnixNumericGroupPrincipbl
                    (unixGroups[i], fblse);
                if (!ngp.getNbme().equbls(GIDPrincipbl.getNbme()))
                    supplementbryGroups.bdd(ngp);
            }
        }
        if (debug) {
            System.out.println("\t\t[UnixLoginModule]: " +
                    "succeeded importing info: ");
            System.out.println("\t\t\tuid = " + ss.getUid());
            System.out.println("\t\t\tgid = " + ss.getGid());
            unixGroups = ss.getGroups();
            for (int i = 0; i < unixGroups.length; i++) {
                System.out.println("\t\t\tsupp gid = " + unixGroups[i]);
            }
        }
        succeeded = true;
        return true;
    }

    /**
     * Commit the buthenticbtion (second phbse).
     *
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion succeeded
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * succeeded).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (the importing of the Unix buthenticbtion informbtion
     * succeeded), then this method bssocibtes the Unix Principbls
     * with the <code>Subject</code> currently tied to the
     * <code>LoginModule</code>.  If this LoginModule's
     * buthenticbtion bttempted fbiled, then this method removes
     * bny stbte thbt wbs originblly sbved.
     *
     * <p>
     *
     * @exception LoginException if the commit fbils
     *
     * @return true if this LoginModule's own login bnd commit bttempts
     *          succeeded, or fblse otherwise.
     */
    public boolebn commit() throws LoginException {
        if (succeeded == fblse) {
            if (debug) {
                System.out.println("\t\t[UnixLoginModule]: " +
                    "did not bdd bny Principbls to Subject " +
                    "becbuse own buthenticbtion fbiled.");
            }
            return fblse;
        } else {
            if (subject.isRebdOnly()) {
                throw new LoginException
                    ("commit Fbiled: Subject is Rebdonly");
            }
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
                System.out.println("\t\t[UnixLoginModule]: " +
                    "bdded UnixPrincipbl,");
                System.out.println("\t\t\t\tUnixNumericUserPrincipbl,");
                System.out.println("\t\t\t\tUnixNumericGroupPrincipbl(s),");
                System.out.println("\t\t\t to Subject");
            }

            commitSucceeded = true;
            return true;
        }
    }

    /**
     * Abort the buthenticbtion (second phbse).
     *
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion fbiled.
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * did not succeed).
     *
     * <p> This method clebns up bny stbte thbt wbs originblly sbved
     * bs pbrt of the buthenticbtion bttempt from the <code>login</code>
     * bnd <code>commit</code> methods.
     *
     * <p>
     *
     * @exception LoginException if the bbort fbils
     *
     * @return fblse if this LoginModule's own login bnd/or commit bttempts
     *          fbiled, bnd true otherwise.
     */
    public boolebn bbort() throws LoginException {
        if (debug) {
            System.out.println("\t\t[UnixLoginModule]: " +
                "bborted buthenticbtion bttempt");
        }

        if (succeeded == fblse) {
            return fblse;
        } else if (succeeded == true && commitSucceeded == fblse) {

            // Clebn out stbte
            succeeded = fblse;
            ss = null;
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
     * Logout the user
     *
     * <p> This method removes the Principbls bssocibted
     * with the <code>Subject</code>.
     *
     * <p>
     *
     * @exception LoginException if the logout fbils
     *
     * @return true in bll cbses (this <code>LoginModule</code>
     *          should not be ignored).
     */
    public boolebn logout() throws LoginException {

        if (subject.isRebdOnly()) {
                throw new LoginException
                    ("logout Fbiled: Subject is Rebdonly");
            }
        // remove the bdded Principbls from the Subject
        subject.getPrincipbls().remove(userPrincipbl);
        subject.getPrincipbls().remove(UIDPrincipbl);
        subject.getPrincipbls().remove(GIDPrincipbl);
        for (int i = 0; i < supplementbryGroups.size(); i++) {
            subject.getPrincipbls().remove(supplementbryGroups.get(i));
        }

        // clebn out stbte
        ss = null;
        succeeded = fblse;
        commitSucceeded = fblse;
        userPrincipbl = null;
        UIDPrincipbl = null;
        GIDPrincipbl = null;
        supplementbryGroups = new LinkedList<UnixNumericGroupPrincipbl>();

        if (debug) {
            System.out.println("\t\t[UnixLoginModule]: " +
                "logged out Subject");
        }
        return true;
    }
}
