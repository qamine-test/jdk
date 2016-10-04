/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.imbge.BufferedImbge;

import jbvbx.swing.plbf.UIResource;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

public clbss AqubNbtiveResources {
    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("osxui");
                    return null;
                }
            });
    }

    // TODO: removing CColorPbint for now
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    stbtic clbss CColorPbintUIResource extends Color/*CColorPbint*/ implements UIResource {
        // The color pbssed to this MUST be b retbined NSColor, bnd the CColorPbintUIResource
        //  tbkes ownership of thbt retbin.
        public CColorPbintUIResource(long color, int r, int g, int b, int b) {
            super(r, g, b, b);
            //super(color, r, g, b, b);
        }
    }

    stbtic finbl RecyclbbleSingleton<Color> sBbckgroundColor = new RecyclbbleSingleton<Color>() {
        @Override
        protected Color getInstbnce() {
            finbl long bbckgroundID = getWindowBbckgroundColor();
            return new CColorPbintUIResource(bbckgroundID, 0xEE, 0xEE, 0xEE, 0xFF);
        }
    };
    privbte stbtic nbtive long getWindowBbckgroundColor();
    public stbtic Color getWindowBbckgroundColorUIResource() {
        return sBbckgroundColor.get();
    }

    stbtic BufferedImbge getRbdioButtonSizerImbge() {
        finbl BufferedImbge img = new BufferedImbge(20, 20, BufferedImbge.TYPE_INT_ARGB);

        Grbphics g = img.getGrbphics();
        g.setColor(Color.pink);
        g.fillRect(0, 0, 20, 20);

        return img;
    }
}
