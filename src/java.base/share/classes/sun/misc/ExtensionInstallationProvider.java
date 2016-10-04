/*
 * Copyright (c) 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

/**
 * This interfbce defines the contrbct b extension instbllbtion cbpbble
 * provided to the extension instbllbtion dependency mechbnism to
 * instbll new extensions on the user's disk
 *
 * @buthor  Jerome Dochez
 */
public interfbce ExtensionInstbllbtionProvider {

    /*
     * <p>
     * Request the instbllbtion of bn extension in the extension directory
     * </p>
     *
     * @pbrbm requestExtInfo informbtion on the extension thbt need to be
     * instblled
     * @pbrbm instblledExtInfo informbtion on the current compbtible instblled
     * extension. Cbn be null if no current instbllbtion hbs been found.
     * @return true if the instbllbtion is successful, fblse if the
     * instbllbtion could not be bttempted.
     * @exception ExtensionInstbllbtionException if bn instbllbtion wbs
     * bttempted but did not succeed.
     */
    boolebn instbllExtension(ExtensionInfo requestExtInfo,
                             ExtensionInfo instblledExtInfo)
        throws ExtensionInstbllbtionException;
}
