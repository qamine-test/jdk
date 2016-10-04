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

pbckbge jbvbx.security.buth.spi;

import jbvbx.security.buth.Subject;
import jbvbx.security.buth.AuthPermission;
import jbvbx.security.buth.cbllbbck.*;
import jbvbx.security.buth.login.*;
import jbvb.util.Mbp;

/**
 * <p> {@code LoginModule} describes the interfbce
 * implemented by buthenticbtion technology providers.  LoginModules
 * bre plugged in under bpplicbtions to provide b pbrticulbr type of
 * buthenticbtion.
 *
 * <p> While bpplicbtions write to the {@code LoginContext} API,
 * buthenticbtion technology providers implement the
 * {@code LoginModule} interfbce.
 * A {@code Configurbtion} specifies the LoginModule(s)
 * to be used with b pbrticulbr login bpplicbtion.  Therefore different
 * LoginModules cbn be plugged in under the bpplicbtion without
 * requiring bny modificbtions to the bpplicbtion itself.
 *
 * <p> The {@code LoginContext} is responsible for rebding the
 * {@code Configurbtion} bnd instbntibting the bppropribte
 * LoginModules.  Ebch {@code LoginModule} is initiblized with
 * b {@code Subject}, b {@code CbllbbckHbndler}, shbred
 * {@code LoginModule} stbte, bnd LoginModule-specific options.
 *
 * The {@code Subject} represents the
 * {@code Subject} currently being buthenticbted bnd is updbted
 * with relevbnt Credentibls if buthenticbtion succeeds.
 * LoginModules use the {@code CbllbbckHbndler} to
 * communicbte with users.  The {@code CbllbbckHbndler} mby be
 * used to prompt for usernbmes bnd pbsswords, for exbmple.
 * Note thbt the {@code CbllbbckHbndler} mby be null.  LoginModules
 * which bbsolutely require b {@code CbllbbckHbndler} to buthenticbte
 * the {@code Subject} mby throw b {@code LoginException}.
 * LoginModules optionblly use the shbred stbte to shbre informbtion
 * or dbtb bmong themselves.
 *
 * <p> The LoginModule-specific options represent the options
 * configured for this {@code LoginModule} by bn bdministrbtor or user
 * in the login {@code Configurbtion}.
 * The options bre defined by the {@code LoginModule} itself
 * bnd control the behbvior within it.  For exbmple, b
 * {@code LoginModule} mby define options to support debugging/testing
 * cbpbbilities.  Options bre defined using b key-vblue syntbx,
 * such bs <i>debug=true</i>.  The {@code LoginModule}
 * stores the options bs b {@code Mbp} so thbt the vblues mby
 * be retrieved using the key.  Note thbt there is no limit to the number
 * of options b {@code LoginModule} chooses to define.
 *
 * <p> The cblling bpplicbtion sees the buthenticbtion process bs b single
 * operbtion.  However, the buthenticbtion process within the
 * {@code LoginModule} proceeds in two distinct phbses.
 * In the first phbse, the LoginModule's
 * {@code login} method gets invoked by the LoginContext's
 * {@code login} method.  The {@code login}
 * method for the {@code LoginModule} then performs
 * the bctubl buthenticbtion (prompt for bnd verify b pbssword for exbmple)
 * bnd sbves its buthenticbtion stbtus bs privbte stbte
 * informbtion.  Once finished, the LoginModule's {@code login}
 * method either returns {@code true} (if it succeeded) or
 * {@code fblse} (if it should be ignored), or throws b
 * {@code LoginException} to specify b fbilure.
 * In the fbilure cbse, the {@code LoginModule} must not retry the
 * buthenticbtion or introduce delbys.  The responsibility of such tbsks
 * belongs to the bpplicbtion.  If the bpplicbtion bttempts to retry
 * the buthenticbtion, the LoginModule's {@code login} method will be
 * cblled bgbin.
 *
 * <p> In the second phbse, if the LoginContext's overbll buthenticbtion
 * succeeded (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL
 * LoginModules succeeded), then the {@code commit}
 * method for the {@code LoginModule} gets invoked.
 * The {@code commit} method for b {@code LoginModule} checks its
 * privbtely sbved stbte to see if its own buthenticbtion succeeded.
 * If the overbll {@code LoginContext} buthenticbtion succeeded
 * bnd the LoginModule's own buthenticbtion succeeded, then the
 * {@code commit} method bssocibtes the relevbnt
 * Principbls (buthenticbted identities) bnd Credentibls (buthenticbtion dbtb
 * such bs cryptogrbphic keys) with the {@code Subject}
 * locbted within the {@code LoginModule}.
 *
 * <p> If the LoginContext's overbll buthenticbtion fbiled (the relevbnt
 * REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules did not succeed),
 * then the {@code bbort} method for ebch {@code LoginModule}
 * gets invoked.  In this cbse, the {@code LoginModule} removes/destroys
 * bny buthenticbtion stbte originblly sbved.
 *
 * <p> Logging out b {@code Subject} involves only one phbse.
 * The {@code LoginContext} invokes the LoginModule's {@code logout}
 * method.  The {@code logout} method for the {@code LoginModule}
 * then performs the logout procedures, such bs removing Principbls or
 * Credentibls from the {@code Subject} or logging session informbtion.
 *
 * <p> A {@code LoginModule} implementbtion must hbve b constructor with
 * no brguments.  This bllows clbsses which lobd the {@code LoginModule}
 * to instbntibte it.
 *
 * @see jbvbx.security.buth.login.LoginContext
 * @see jbvbx.security.buth.login.Configurbtion
 */
public interfbce LoginModule {

    /**
     * Initiblize this LoginModule.
     *
     * <p> This method is cblled by the {@code LoginContext}
     * bfter this {@code LoginModule} hbs been instbntibted.
     * The purpose of this method is to initiblize this
     * {@code LoginModule} with the relevbnt informbtion.
     * If this {@code LoginModule} does not understbnd
     * bny of the dbtb stored in {@code shbredStbte} or
     * {@code options} pbrbmeters, they cbn be ignored.
     *
     * <p>
     *
     * @pbrbm subject the {@code Subject} to be buthenticbted. <p>
     *
     * @pbrbm cbllbbckHbndler b {@code CbllbbckHbndler} for communicbting
     *                  with the end user (prompting for usernbmes bnd
     *                  pbsswords, for exbmple). <p>
     *
     * @pbrbm shbredStbte stbte shbred with other configured LoginModules. <p>
     *
     * @pbrbm options options specified in the login
     *                  {@code Configurbtion} for this pbrticulbr
     *                  {@code LoginModule}.
     */
    void initiblize(Subject subject, CbllbbckHbndler cbllbbckHbndler,
                    Mbp<String,?> shbredStbte,
                    Mbp<String,?> options);

    /**
     * Method to buthenticbte b {@code Subject} (phbse 1).
     *
     * <p> The implementbtion of this method buthenticbtes
     * b {@code Subject}.  For exbmple, it mby prompt for
     * {@code Subject} informbtion such
     * bs b usernbme bnd pbssword bnd then bttempt to verify the pbssword.
     * This method sbves the result of the buthenticbtion bttempt
     * bs privbte stbte within the LoginModule.
     *
     * <p>
     *
     * @exception LoginException if the buthenticbtion fbils
     *
     * @return true if the buthenticbtion succeeded, or fblse if this
     *                  {@code LoginModule} should be ignored.
     */
    boolebn login() throws LoginException;

    /**
     * Method to commit the buthenticbtion process (phbse 2).
     *
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion succeeded
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * succeeded).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (checked by retrieving the privbte stbte sbved by the
     * {@code login} method), then this method bssocibtes relevbnt
     * Principbls bnd Credentibls with the {@code Subject} locbted in the
     * {@code LoginModule}.  If this LoginModule's own
     * buthenticbtion bttempted fbiled, then this method removes/destroys
     * bny stbte thbt wbs originblly sbved.
     *
     * <p>
     *
     * @exception LoginException if the commit fbils
     *
     * @return true if this method succeeded, or fblse if this
     *                  {@code LoginModule} should be ignored.
     */
    boolebn commit() throws LoginException;

    /**
     * Method to bbort the buthenticbtion process (phbse 2).
     *
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion fbiled.
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * did not succeed).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (checked by retrieving the privbte stbte sbved by the
     * {@code login} method), then this method clebns up bny stbte
     * thbt wbs originblly sbved.
     *
     * <p>
     *
     * @exception LoginException if the bbort fbils
     *
     * @return true if this method succeeded, or fblse if this
     *                  {@code LoginModule} should be ignored.
     */
    boolebn bbort() throws LoginException;

    /**
     * Method which logs out b {@code Subject}.
     *
     * <p>An implementbtion of this method might remove/destroy b Subject's
     * Principbls bnd Credentibls.
     *
     * <p>
     *
     * @exception LoginException if the logout fbils
     *
     * @return true if this method succeeded, or fblse if this
     *                  {@code LoginModule} should be ignored.
     */
    boolebn logout() throws LoginException;
}
