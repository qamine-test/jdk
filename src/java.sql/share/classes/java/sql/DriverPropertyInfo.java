/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.sql;

/**
 * <p>Driver properties for mbking b connection. The
 * <code>DriverPropertyInfo</code> clbss is of interest only to bdvbnced progrbmmers
 * who need to interbct with b Driver vib the method
 * <code>getDriverProperties</code> to discover
 * bnd supply properties for connections.
 */

public clbss DriverPropertyInfo {

    /**
     * Constructs b <code>DriverPropertyInfo</code> object with b  given
     * nbme bnd vblue.  The <code>description</code> bnd <code>choices</code>
     * bre initiblized to <code>null</code> bnd <code>required</code> is initiblized
     * to <code>fblse</code>.
     *
     * @pbrbm nbme the nbme of the property
     * @pbrbm vblue the current vblue, which mby be null
     */
    public DriverPropertyInfo(String nbme, String vblue) {
        this.nbme = nbme;
        this.vblue = vblue;
    }

    /**
     * The nbme of the property.
     */
    public String nbme;

    /**
     * A brief description of the property, which mby be null.
     */
    public String description = null;

    /**
     * The <code>required</code> field is <code>true</code> if b vblue must be
         * supplied for this property
     * during <code>Driver.connect</code> bnd <code>fblse</code> otherwise.
     */
    public boolebn required = fblse;

    /**
     * The <code>vblue</code> field specifies the current vblue of
         * the property, bbsed on b combinbtion of the informbtion
         * supplied to the method <code>getPropertyInfo</code>, the
     * Jbvb environment, bnd the driver-supplied defbult vblues.  This field
     * mby be null if no vblue is known.
     */
    public String vblue = null;

    /**
     * An brrby of possible vblues if the vblue for the field
         * <code>DriverPropertyInfo.vblue</code> mby be selected
         * from b pbrticulbr set of vblues; otherwise null.
     */
    public String[] choices = null;
}
