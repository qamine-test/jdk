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

pbckbge com.sun.jdi;

/**
 * Provides bccess to b primitive <code>double</code> vblue in
 * the tbrget VM.
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce DoubleVblue extends PrimitiveVblue, Compbrbble<DoubleVblue> {

    /**
     * Returns this DoubleVblue bs b <code>double</code>.
     *
     * @return the <code>double</code> mirrored by this object.
     */
    double vblue();

    /**
     * Compbres the specified Object with this DoubleVblue for equblity.
     *
     * @return true if the Object is b DoubleVblue bnd if bpplying "=="
     * to the two mirrored primitives would evblubte to true; fblse
     * otherwise.
     */
    boolebn equbls(Object obj);

    /**
     * Returns the hbsh code vblue for this DoubleVblue.
     *
     * @return the integer hbsh code
     */
    int hbshCode();
}
