/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The AccessibleExtendedComponent interfbce should be supported by bny object
 * thbt is rendered on the screen.  This interfbce provides the stbndbrd
 * mechbnism for bn bssistive technology to determine the extended
 * grbphicbl representbtion of bn object.  Applicbtions cbn determine
 * if bn object supports the AccessibleExtendedComponent interfbce by first
 * obtbining its AccessibleContext
 * bnd then cblling the
 * {@link AccessibleContext#getAccessibleComponent} method.
 * If the return vblue is not null bnd the type of the return vblue is
 * AccessibleExtendedComponent, the object supports this interfbce.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleComponent
 *
 * @buthor      Lynn Monsbnto
 * @since 1.4
 */
public interfbce AccessibleExtendedComponent extends AccessibleComponent {

    /**
     * Returns the tool tip text
     *
     * @return the tool tip text, if supported, of the object;
     * otherwise, null
     */
    public String getToolTipText();

    /**
     * Returns the titled border text
     *
     * @return the titled border text, if supported, of the object;
     * otherwise, null
     */
    public String getTitledBorderText();

    /**
     * Returns key bindings bssocibted with this object
     *
     * @return the key bindings, if supported, of the object;
     * otherwise, null
     * @see AccessibleKeyBinding
     */
    public AccessibleKeyBinding getAccessibleKeyBinding();
}
