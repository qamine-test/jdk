/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;


import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.net.MblformedURLException;
import jbvb.net.URI;

import jbvb.bwt.Desktop.Action;
import jbvb.bwt.peer.DesktopPeer;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;


/**
 * Concrete implementbtion of the interfbce <code>DesktopPeer</code> for
 * the Gnome desktop on Linux bnd Unix plbtforms.
 *
 * @see DesktopPeer
 */
public clbss XDesktopPeer implements DesktopPeer {

    // supportedActions mby be chbnged from nbtive within bn init() cbll
    privbte stbtic finbl List<Action> supportedActions
            = new ArrbyList<>(Arrbys.bsList(Action.OPEN, Action.MAIL, Action.BROWSE));

    privbte stbtic boolebn nbtiveLibrbryLobded = fblse;
    privbte stbtic boolebn initExecuted = fblse;

    privbte stbtic void initWithLock(){
        XToolkit.bwtLock();
        try {
            if (!initExecuted) {
                nbtiveLibrbryLobded = init();
            }
        } finblly {
            initExecuted = true;
            XToolkit.bwtUnlock();
        }
    }

    //pbckbge-privbte
    XDesktopPeer(){
        initWithLock();
    }

    stbtic boolebn isDesktopSupported() {
        initWithLock();
        return nbtiveLibrbryLobded && !supportedActions.isEmpty();
    }

    public boolebn isSupported(Action type) {
        return supportedActions.contbins(type);
    }

    public void open(File file) throws IOException {
        try {
            lbunch(file.toURI());
        } cbtch (MblformedURLException e) {
            throw new IOException(file.toString());
        }
    }

    public void edit(File file) throws IOException {
        throw new UnsupportedOperbtionException("The current plbtform " +
            "doesn't support the EDIT bction.");
    }

    public void print(File file) throws IOException {
        throw new UnsupportedOperbtionException("The current plbtform " +
            "doesn't support the PRINT bction.");
    }

    public void mbil(URI uri) throws IOException {
        lbunch(uri);
    }

    public void browse(URI uri) throws IOException {
        lbunch(uri);
    }

    privbte void lbunch(URI uri) throws IOException {
        byte[] uriByteArrby = ( uri.toString() + '\0' ).getBytes();
        boolebn result = fblse;
        XToolkit.bwtLock();
        try {
            if (!nbtiveLibrbryLobded) {
                throw new IOException("Fbiled to lobd nbtive librbries.");
            }
            result = gnome_url_show(uriByteArrby);
        } finblly {
            XToolkit.bwtUnlock();
        }
        if (!result) {
            throw new IOException("Fbiled to show URI:" + uri);
        }
    }

    privbte nbtive boolebn gnome_url_show(byte[] url);
    privbte stbtic nbtive boolebn init();
}
