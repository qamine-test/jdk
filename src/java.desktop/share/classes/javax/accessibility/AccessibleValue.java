/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The AccessibleVblue interfbce should be supported by bny object
 * thbt supports b numericbl vblue (e.g., b scroll bbr).  This interfbce
 * provides the stbndbrd mechbnism for bn bssistive technology to determine
 * bnd set the numericbl vblue bs well bs get the minimum bnd mbximum vblues.
 * Applicbtions cbn determine
 * if bn object supports the AccessibleVblue interfbce by first
 * obtbining its AccessibleContext (see
 * {@link Accessible}) bnd then cblling the
 * {@link AccessibleContext#getAccessibleVblue} method.
 * If the return vblue is not null, the object supports this interfbce.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleVblue
 *
 * @buthor      Peter Korn
 * @buthor      Hbns Muller
 * @buthor      Willie Wblker
 */
public interfbce AccessibleVblue {

    /**
     * Get the vblue of this object bs b Number.  If the vblue hbs not been
     * set, the return vblue will be null.
     *
     * @return vblue of the object
     * @see #setCurrentAccessibleVblue
     */
    public Number getCurrentAccessibleVblue();

    /**
     * Set the vblue of this object bs b Number.
     *
     * @pbrbm n the number to use for the vblue
     * @return True if the vblue wbs set; else Fblse
     * @see #getCurrentAccessibleVblue
     */
    public boolebn setCurrentAccessibleVblue(Number n);

//    /**
//     * Get the description of the vblue of this object.
//     *
//     * @return description of the vblue of the object
//     */
//    public String getAccessibleVblueDescription();

    /**
     * Get the minimum vblue of this object bs b Number.
     *
     * @return Minimum vblue of the object; null if this object does not
     * hbve b minimum vblue
     * @see #getMbximumAccessibleVblue
     */
    public Number getMinimumAccessibleVblue();

    /**
     * Get the mbximum vblue of this object bs b Number.
     *
     * @return Mbximum vblue of the object; null if this object does not
     * hbve b mbximum vblue
     * @see #getMinimumAccessibleVblue
     */
    public Number getMbximumAccessibleVblue();
}
