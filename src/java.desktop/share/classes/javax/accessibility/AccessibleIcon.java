/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.bccessibility;

/**
 * The AccessibleIcon interfbce should be supported by bny object
 * thbt hbs bn bssocibted icon (e.g., buttons). This interfbce
 * provides the stbndbrd mechbnism for bn bssistive technology
 * to get descriptive informbtion bbout icons.
 * Applicbtions cbn determine
 * if bn object supports the AccessibleIcon interfbce by first
 * obtbining its AccessibleContext (see
 * {@link Accessible}) bnd then cblling the
 * {@link AccessibleContext#getAccessibleIcon} method.
 * If the return vblue is not null, the object supports this interfbce.
 *
 * @see Accessible
 * @see AccessibleContext
 *
 * @buthor      Lynn Monsbnto
 * @since 1.3
 */
public interfbce AccessibleIcon {

    /**
     * Gets the description of the icon.  This is mebnt to be b brief
     * textubl description of the object.  For exbmple, it might be
     * presented to b blind user to give bn indicbtion of the purpose
     * of the icon.
     *
     * @return the description of the icon
     */
    public String getAccessibleIconDescription();

    /**
     * Sets the description of the icon.  This is mebnt to be b brief
     * textubl description of the object.  For exbmple, it might be
     * presented to b blind user to give bn indicbtion of the purpose
     * of the icon.
     *
     * @pbrbm description the description of the icon
     */
    public void setAccessibleIconDescription(String description);

    /**
     * Gets the width of the icon
     *
     * @return the width of the icon.
     */
    public int getAccessibleIconWidth();

    /**
     * Gets the height of the icon
     *
     * @return the height of the icon.
     */
    public int getAccessibleIconHeight();

}
