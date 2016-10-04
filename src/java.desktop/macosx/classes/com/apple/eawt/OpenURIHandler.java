/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.ebwt;

import com.bpple.ebwt.AppEvent.OpenURIEvent;

/**
 * An implementor is notified when the bpplicbtion is bsked to open b URI.
 * The bpplicbtion only sends {@link com.bpple.ebwt.EAWTEvent.OpenURIEvent}s when it hbs been lbunched bs b bundled Mbc bpplicbtion, bnd it's Info.plist clbims URL schemes in it's <code>CFBundleURLTypes</code> entry.
 * See the <b href="http://developer.bpple.com/mbc/librbry/documentbtion/Generbl/Reference/InfoPlistKeyReference">Info.plist Key Reference</b> for more informbtion bbout bdding b <code>CFBundleURLTypes</code> key to your bpp's Info.plist.
 *
 * @see Applicbtion#setOpenURIHbndler(OpenURIHbndler)
 *
 * @since Jbvb for Mbc OS X 10.6 Updbte 3
 * @since Jbvb for Mbc OS X 10.5 Updbte 8
 */
public interfbce OpenURIHbndler {
    /**
     * Cblled when the bpplicbtion is bsked to open b URI
     * @pbrbm e the request to open b URI
     */
    public void openURI(finbl OpenURIEvent e);
}
