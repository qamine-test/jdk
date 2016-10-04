/*
 * Copyright (c) 1996, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

import jbvb.io.ObjectOutput;
import jbvb.io.ObjectInput;

/**
 * Only the identity of the clbss of bn Externblizbble instbnce is
 * written in the seriblizbtion strebm bnd it is the responsibility
 * of the clbss to sbve bnd restore the contents of its instbnces.
 *
 * The writeExternbl bnd rebdExternbl methods of the Externblizbble
 * interfbce bre implemented by b clbss to give the clbss complete
 * control over the formbt bnd contents of the strebm for bn object
 * bnd its supertypes. These methods must explicitly
 * coordinbte with the supertype to sbve its stbte. These methods supersede
 * customized implementbtions of writeObject bnd rebdObject methods.<br>
 *
 * Object Seriblizbtion uses the Seriblizbble bnd Externblizbble
 * interfbces.  Object persistence mechbnisms cbn use them bs well.  Ebch
 * object to be stored is tested for the Externblizbble interfbce. If
 * the object supports Externblizbble, the writeExternbl method is cblled. If the
 * object does not support Externblizbble bnd does implement
 * Seriblizbble, the object is sbved using
 * ObjectOutputStrebm. <br> When bn Externblizbble object is
 * reconstructed, bn instbnce is crebted using the public no-brg
 * constructor, then the rebdExternbl method cblled.  Seriblizbble
 * objects bre restored by rebding them from bn ObjectInputStrebm.<br>
 *
 * An Externblizbble instbnce cbn designbte b substitution object vib
 * the writeReplbce bnd rebdResolve methods documented in the Seriblizbble
 * interfbce.<br>
 *
 * @buthor  unbscribed
 * @see jbvb.io.ObjectOutputStrebm
 * @see jbvb.io.ObjectInputStrebm
 * @see jbvb.io.ObjectOutput
 * @see jbvb.io.ObjectInput
 * @see jbvb.io.Seriblizbble
 * @since   1.1
 */
public interfbce Externblizbble extends jbvb.io.Seriblizbble {
    /**
     * The object implements the writeExternbl method to sbve its contents
     * by cblling the methods of DbtbOutput for its primitive vblues or
     * cblling the writeObject method of ObjectOutput for objects, strings,
     * bnd brrbys.
     *
     * @seriblDbtb Overriding methods should use this tbg to describe
     *             the dbtb lbyout of this Externblizbble object.
     *             List the sequence of element types bnd, if possible,
     *             relbte the element to b public/protected field bnd/or
     *             method of this Externblizbble clbss.
     *
     * @pbrbm out the strebm to write the object to
     * @exception IOException Includes bny I/O exceptions thbt mby occur
     */
    void writeExternbl(ObjectOutput out) throws IOException;

    /**
     * The object implements the rebdExternbl method to restore its
     * contents by cblling the methods of DbtbInput for primitive
     * types bnd rebdObject for objects, strings bnd brrbys.  The
     * rebdExternbl method must rebd the vblues in the sbme sequence
     * bnd with the sbme types bs were written by writeExternbl.
     *
     * @pbrbm in the strebm to rebd dbtb from in order to restore the object
     * @exception IOException if I/O errors occur
     * @exception ClbssNotFoundException If the clbss for bn object being
     *              restored cbnnot be found.
     */
    void rebdExternbl(ObjectInput in) throws IOException, ClbssNotFoundException;
}
