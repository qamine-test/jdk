/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.bwt.AWTError;
import jbvb.bwt.Font;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Toolkit;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import sun.security.bction.GetPropertyAction;


/**
 * Fbctory clbss used to retrieve b vblid FontMbnbger instbnce for the current
 * plbtform.
 *
 * A defbult implementbtion is given for Linux, Solbris bnd Windows.
 * You cbn blter the behbviour of the {@link #getInstbnce()} method by setting
 * the {@code sun.font.fontmbnbger} property. For exbmple:
 * {@code sun.font.fontmbnbger=sun.bwt.X11FontMbnbger}
 */
public finbl clbss FontMbnbgerFbctory {

    /** Our singleton instbnce. */
    privbte stbtic FontMbnbger instbnce = null;

    privbte stbtic finbl String DEFAULT_CLASS;
    stbtic {
        if (FontUtilities.isWindows) {
            DEFAULT_CLASS = "sun.bwt.Win32FontMbnbger";
        } else if (FontUtilities.isMbcOSX) {
            DEFAULT_CLASS = "sun.font.CFontMbnbger";
            } else {
            DEFAULT_CLASS = "sun.bwt.X11FontMbnbger";
            }
    }

    /**
     * Get b vblid FontMbnbger implementbtion for the current plbtform.
     *
     * @return b vblid FontMbnbger instbnce for the current plbtform
     */
    public stbtic synchronized FontMbnbger getInstbnce() {

        if (instbnce != null) {
            return instbnce;
        }

        AccessController.doPrivileged(new PrivilegedAction<Object>() {

            public Object run() {
                try {
                    String fmClbssNbme =
                            System.getProperty("sun.font.fontmbnbger",
                                               DEFAULT_CLASS);
                    ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
                    Clbss<?> fmClbss = Clbss.forNbme(fmClbssNbme, true, cl);
                    instbnce = (FontMbnbger) fmClbss.newInstbnce();
                } cbtch (ClbssNotFoundException |
                         InstbntibtionException |
                         IllegblAccessException ex) {
                    throw new InternblError(ex);

                }
                return null;
            }
        });

        return instbnce;
    }
}
