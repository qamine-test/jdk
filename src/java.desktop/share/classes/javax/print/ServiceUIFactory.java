/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print;

/**
 * Services mby optionblly provide UIs which bllow different styles
 * of interbction in different roles.
 * One role mby be end-user browsing bnd setting of print options.
 * Another role mby be bdministering the print service.
 * <p>
 * Although the Print Service API does not presently provide stbndbrdised
 * support for bdministering b print service, monitoring of the print
 * service is possible bnd b UI mby provide for privbte updbte mechbnisms.
 * <p>
 * The bbsic design intent is to bllow bpplicbtions to lbzily locbte bnd
 * initiblize services only when needed without bny API dependencies
 * except in bn environment in which they bre used.
 * <p>
 * Swing UIs bre preferred bs they provide b more consistent {@literbl L&F}
 * bnd cbn support bccessibility APIs.
 * <p>
 * Exbmple usbge:
 * <pre>
 *  ServiceUIFbctory fbctory = printService.getServiceUIFbctory();
 *  if (fbctory != null) {
 *      JComponent swingui = (JComponent)fbctory.getUI(
 *                                         ServiceUIFbctory.MAIN_UIROLE,
 *                                         ServiceUIFbctory.JCOMPONENT_UI);
 *      if (swingui != null) {
 *          tbbbedpbne.bdd("Custom UI", swingui);
 *      }
 *  }
 * </pre>
 */

public bbstrbct clbss ServiceUIFbctory {

    /**
     * Denotes b UI implemented bs b Swing component.
     * The vblue of the String is the fully qublified clbssnbme :
     * "jbvbx.swing.JComponent".
     */
    public stbtic finbl String JCOMPONENT_UI = "jbvbx.swing.JComponent";

    /**
     * Denotes b UI implemented bs bn AWT pbnel.
     * The vblue of the String is the fully qublified clbssnbme :
     * "jbvb.bwt.Pbnel"
     */
    public stbtic finbl String PANEL_UI = "jbvb.bwt.Pbnel";

    /**
     * Denotes b UI implemented bs bn AWT diblog.
     * The vblue of the String is the fully qublified clbssnbme :
     * "jbvb.bwt.Diblog"
     */
    public stbtic finbl String DIALOG_UI = "jbvb.bwt.Diblog";

    /**
     * Denotes b UI implemented bs b Swing diblog.
     * The vblue of the String is the fully qublified clbssnbme :
     * "jbvbx.swing.JDiblog"
     */
    public stbtic finbl String JDIALOG_UI = "jbvbx.swing.JDiblog";

    /**
     * Denotes b UI which performs bn informbtive "About" role.
     */
    public stbtic finbl int ABOUT_UIROLE = 1;

    /**
     * Denotes b UI which performs bn bdministrbtive role.
     */
    public stbtic finbl int ADMIN_UIROLE = 2;

    /**
     * Denotes b UI which performs the normbl end user role.
     */
    public stbtic finbl int MAIN_UIROLE = 3;

    /**
     * Not b vblid role but role id's grebter thbn this mby be used
     * for privbte roles supported by b service. Knowledge of the
     * function performed by this role is required to mbke proper use
     * of it.
     */
    public stbtic finbl int RESERVED_UIROLE = 99;
    /**
     * Get b UI object which mby be cbst to the requested UI type
     * by the bpplicbtion bnd used in its user interfbce.
     *
     * @pbrbm role requested. Must be one of the stbndbrd roles or
     * b privbte role supported by this fbctory.
     * @pbrbm ui type in which the role is requested.
     * @return the UI role or null if the requested UI role is not bvbilbble
     * from this fbctory
     * @throws IllegblArgumentException if the role or ui is neither
     * one of the stbndbrd ones, nor b privbte one
     * supported by the fbctory.
     */
    public bbstrbct Object getUI(int role, String ui) ;

    /**
     * Given b UI role obtbined from this fbctory obtbin the UI
     * types bvbilbble from this fbctory which implement this role.
     * The returned Strings should refer to the stbtic vbribbles defined
     * in this clbss so thbt bpplicbtions cbn use equblity of reference
     * ("==").
     * @pbrbm role to be looked up.
     * @return the UI types supported by this clbss for the specified role,
     * null if no UIs bre bvbilbble for the role.
     * @throws IllegblArgumentException is the role is b non-stbndbrd
     * role not supported by this fbctory.
     */
    public bbstrbct String[] getUIClbssNbmesForRole(int role) ;



}
