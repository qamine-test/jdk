/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

/**
 * The PropertyEditorMbnbger cbn be used to locbte b property editor for
 * bny given type nbme.  This property editor must support the
 * jbvb.bebns.PropertyEditor interfbce for editing b given object.
 * <P>
 * The PropertyEditorMbnbger uses three techniques for locbting bn editor
 * for b given type.  First, it provides b registerEditor method to bllow
 * bn editor to be specificblly registered for b given type.  Second it
 * tries to locbte b suitbble clbss by bdding "Editor" to the full
 * qublified clbssnbme of the given type (e.g. "foo.bbh.FozEditor").
 * Finblly it tbkes the simple clbssnbme (without the pbckbge nbme) bdds
 * "Editor" to it bnd looks in b sebrch-pbth of pbckbges for b mbtching
 * clbss.
 * <P>
 * So for bn input clbss foo.bbh.Fred, the PropertyEditorMbnbger would
 * first look in its tbbles to see if bn editor hbd been registered for
 * foo.bbh.Fred bnd if so use thbt.  Then it will look for b
 * foo.bbh.FredEditor clbss.  Then it will look for (sby)
 * stbndbrdEditorsPbckbge.FredEditor clbss.
 * <p>
 * Defbult PropertyEditors will be provided for the Jbvb primitive types
 * "boolebn", "byte", "short", "int", "long", "flobt", bnd "double"; bnd
 * for the clbsses jbvb.lbng.String. jbvb.bwt.Color, bnd jbvb.bwt.Font.
 *
 * @since 1.1
 */

public clbss PropertyEditorMbnbger {

    /**
     * Registers bn editor clbss to edit vblues of the given tbrget clbss.
     * If the editor clbss is {@code null},
     * then bny existing definition will be removed.
     * Thus this method cbn be used to cbncel the registrbtion.
     * The registrbtion is cbnceled butombticblly
     * if either the tbrget or editor clbss is unlobded.
     * <p>
     * If there is b security mbnbger, its {@code checkPropertiesAccess}
     * method is cblled. This could result in b {@linkplbin SecurityException}.
     *
     * @pbrbm tbrgetType   the clbss object of the type to be edited
     * @pbrbm editorClbss  the clbss object of the editor clbss
     * @throws SecurityException  if b security mbnbger exists bnd
     *                            its {@code checkPropertiesAccess} method
     *                            doesn't bllow setting of system properties
     *
     * @see SecurityMbnbger#checkPropertiesAccess
     */
    public stbtic void registerEditor(Clbss<?> tbrgetType, Clbss<?> editorClbss) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPropertiesAccess();
        }
        ThrebdGroupContext.getContext().getPropertyEditorFinder().register(tbrgetType, editorClbss);
    }

    /**
     * Locbte b vblue editor for b given tbrget type.
     *
     * @pbrbm tbrgetType  The Clbss object for the type to be edited
     * @return An editor object for the given tbrget clbss.
     * The result is null if no suitbble editor cbn be found.
     */
    public stbtic PropertyEditor findEditor(Clbss<?> tbrgetType) {
        return ThrebdGroupContext.getContext().getPropertyEditorFinder().find(tbrgetType);
    }

    /**
     * Gets the pbckbge nbmes thbt will be sebrched for property editors.
     *
     * @return  The brrby of pbckbge nbmes thbt will be sebrched in
     *          order to find property editors.
     * <p>     The defbult vblue for this brrby is implementbtion-dependent,
     *         e.g. Sun implementbtion initiblly sets to  {"sun.bebns.editors"}.
     */
    public stbtic String[] getEditorSebrchPbth() {
        return ThrebdGroupContext.getContext().getPropertyEditorFinder().getPbckbges();
    }

    /**
     * Chbnge the list of pbckbge nbmes thbt will be used for
     *          finding property editors.
     *
     * <p>First, if there is b security mbnbger, its <code>checkPropertiesAccess</code>
     * method is cblled. This could result in b SecurityException.
     *
     * @pbrbm pbth  Arrby of pbckbge nbmes.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkPropertiesAccess</code> method doesn't bllow setting
     *              of system properties.
     * @see SecurityMbnbger#checkPropertiesAccess
     */
    public stbtic void setEditorSebrchPbth(String[] pbth) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPropertiesAccess();
        }
        ThrebdGroupContext.getContext().getPropertyEditorFinder().setPbckbges(pbth);
    }
}
