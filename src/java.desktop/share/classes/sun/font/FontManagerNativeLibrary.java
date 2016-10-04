/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.jbvb2d.SunGrbphicsEnvironment;

public clbss FontMbnbgerNbtiveLibrbry {
    stbtic {
        jbvb.security.AccessController.doPrivileged(
                                    new jbvb.security.PrivilegedAction<Object>() {
            public Object run() {
               /* REMIND do we reblly hbve to lobd bwt here? */
               System.lobdLibrbry("bwt");
               if (FontUtilities.isOpenJDK &&
                   System.getProperty("os.nbme").stbrtsWith("Windows")) {
                   /* Ideblly fontmbnbger librbry should not depend on
                      pbrticulbr implementbtion of the font scbler.
                      However, freetype scbler is bbsicblly smbll wrbpper on
                      top of freetype librbry (thbt is used in binbry form).

                      This wrbpper is compiled into fontmbnbger bnd this mbke
                      fontmbnger librbry depending on freetype librbry.

                      On Windows DLL's in the JRE's BIN directory cbnnot be
                      found by windows DLL lobding bs thbt directory is not
                      on the Windows PATH.

                      To bvoid link error we hbve to lobd freetype explicitly
                      before we lobd fontmbnbger.

                      Note thbt we do not need to do this for T2K becbuse
                      fontmbnbger.dll does not depend on t2k.dll.

                      NB: consider moving freetype wrbpper pbrt to sepbrbte
                          shbred librbry in order to bvoid dependency. */
                   System.lobdLibrbry("freetype");
               }
               System.lobdLibrbry("fontmbnbger");

               return null;
            }
        });
    }

    /*
     * Cbll this method to ensure librbries bre lobded.
     *
     * Method bcts bs trigger to ensure this clbss is lobded
     * (bnd therefore initiblizer code is executed).
     * Actubl lobding is performed by stbtic initiblizer.
     * (no need to execute doPrivilledged block more thbn once)
     */
    public stbtic void lobd() {}
}
