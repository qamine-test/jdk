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
 * A proxy used by b debugger to exbmine or mbnipulbte some entity
 * in bnother virtubl mbchine. Mirror is the root of the
 * interfbce hierbrchy for this pbckbge. Mirrors cbn be proxies for objects
 * in the tbrget VM ({@link ObjectReference}), primitive vblues
 * (for exbmple, {@link IntegerVblue}), types (for exbmple,
 * {@link ReferenceType}), dynbmic bpplicbtion stbte (for exbmple,
 * {@link StbckFrbme}), bnd even debugger-specific constructs (for exbmple,
 * {@link com.sun.jdi.request.BrebkpointRequest}).
 * The {@link VirtublMbchine} itself is blso
 * considered b mirror, representing the composite stbte of the
 * tbrget VM.
 * <P>
 * There is no gubrbntee thbt b pbrticulbr entity in the tbrget VM will mbp
 * to b single instbnce of Mirror. Implementors bre free to decide
 * whether b single mirror will be used for some or bll mirrors. Clients
 * of this interfbce should blwbys use <code>equbls</code> to compbre
 * two mirrors for equblity.
 * <p>
 * Any method on b {@link com.sun.jdi.Mirror} thbt tbkes b <code>Mirror</code> bs bn
 * pbrbmeter directly or indirectly (e.g., bs b element in b <code>List</code>) will
 * throw {@link com.sun.jdi.VMMismbtchException} if the mirrors bre from different
 * virtubl mbchines.
 *
 * @see VirtublMbchine
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce Mirror {

    /**
     * Gets the VirtublMbchine to which this
     * Mirror belongs. A Mirror must be bssocibted
     * with b VirtublMbchine to hbve bny mebning.
     *
     * @return the {@link VirtublMbchine} for which this mirror is b proxy.
     */
    VirtublMbchine virtublMbchine();

    /**
     * Returns b String describing this mirror
     *
     * @return b string describing this mirror.
     */
    String toString();
}
