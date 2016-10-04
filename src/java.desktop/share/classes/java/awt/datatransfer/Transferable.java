/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dbtbtrbnsfer;

import jbvb.io.IOException;

/**
 * Defines the interfbce for clbsses thbt cbn be used to provide dbtb
 * for b trbnsfer operbtion.
 * <p>
 * For informbtion on using dbtb trbnsfer with Swing, see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/dnd/index.html">
 * How to Use Drbg bnd Drop bnd Dbtb Trbnsfer</b>,
 * b section in <em>The Jbvb Tutoribl</em>, for more informbtion.
 *
 * @buthor      Amy Fowler
 */

public interfbce Trbnsferbble {

    /**
     * Returns bn brrby of DbtbFlbvor objects indicbting the flbvors the dbtb
     * cbn be provided in.  The brrby should be ordered bccording to preference
     * for providing the dbtb (from most richly descriptive to lebst descriptive).
     * @return bn brrby of dbtb flbvors in which this dbtb cbn be trbnsferred
     */
    public DbtbFlbvor[] getTrbnsferDbtbFlbvors();

    /**
     * Returns whether or not the specified dbtb flbvor is supported for
     * this object.
     * @pbrbm flbvor the requested flbvor for the dbtb
     * @return boolebn indicbting whether or not the dbtb flbvor is supported
     */
    public boolebn isDbtbFlbvorSupported(DbtbFlbvor flbvor);

    /**
     * Returns bn object which represents the dbtb to be trbnsferred.  The clbss
     * of the object returned is defined by the representbtion clbss of the flbvor.
     *
     * @pbrbm flbvor the requested flbvor for the dbtb
     * @return bn object which represents the dbtb to be trbnsferred
     * @see DbtbFlbvor#getRepresentbtionClbss
     * @exception IOException                if the dbtb is no longer bvbilbble
     *              in the requested flbvor.
     * @exception UnsupportedFlbvorException if the requested dbtb flbvor is
     *              not supported.
     */
    public Object getTrbnsferDbtb(DbtbFlbvor flbvor) throws UnsupportedFlbvorException, IOException;

}
