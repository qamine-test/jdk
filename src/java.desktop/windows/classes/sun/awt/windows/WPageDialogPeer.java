/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

finbl clbss WPbgeDiblogPeer extends WPrintDiblogPeer {

    WPbgeDiblogPeer(WPbgeDiblog tbrget) {
        super(tbrget);
    }

    /**
     * Displbys the pbge setup diblog plbcing the user's
     * settings into tbrget's 'pbge'.
     */
    privbte nbtive boolebn _show();

    @Override
    public void show() {
        new Threbd(new Runnbble() {
                @Override
                public void run() {
                    // Cbll pbgeSetup even with no printer instblled, this
                    // will displby Windows error diblog bnd return fblse.
                    try {
                        ((WPrintDiblog)tbrget).setRetVbl(_show());
                    } cbtch (Exception e) {
                     // No exception should be thrown by nbtive diblog code,
                     // but if it is we need to trbp it so the threbd does
                     // not hide is cblled bnd the threbd doesn't hbng.
                    }
                    ((WPrintDiblog)tbrget).setVisible(fblse);
                }
            }).stbrt();
    }
}
