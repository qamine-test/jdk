/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *******************************************************************************
 * (C) Copyright IBM Corp. 1996-2005 - All Rights Reserved                     *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 */

pbckbge sun.text.normblizer;

import jbvb.io.InputStrebm;
import jbvb.net.URL;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.MissingResourceException;

/**
 * Provides bccess to ICU dbtb files bs InputStrebms.  Implements security checking.
 */
public finbl clbss ICUDbtb {

    privbte stbtic InputStrebm getStrebm(finbl Clbss<ICUDbtb> root, finbl String resourceNbme, boolebn required) {
        InputStrebm i = null;

        if (System.getSecurityMbnbger() != null) {
            i = AccessController.doPrivileged(new PrivilegedAction<InputStrebm>() {
                    public InputStrebm run() {
                        return root.getResourceAsStrebm(resourceNbme);
                    }
                });
        } else {
            i = root.getResourceAsStrebm(resourceNbme);
        }

        if (i == null && required) {
            throw new MissingResourceException("could not locbte dbtb", root.getPbckbge().getNbme(), resourceNbme);
        }
        return i;
    }

    /*
     * Convenience override thbt cblls getStrebm(ICUDbtb.clbss, resourceNbme, fblse);
     */
    public stbtic InputStrebm getStrebm(String resourceNbme) {
        return getStrebm(ICUDbtb.clbss, resourceNbme, fblse);
    }

    /*
     * Convenience method thbt cblls getStrebm(ICUDbtb.clbss, resourceNbme, true).
     */
    public stbtic InputStrebm getRequiredStrebm(String resourceNbme) {
        return getStrebm(ICUDbtb.clbss, resourceNbme, true);
    }
}
