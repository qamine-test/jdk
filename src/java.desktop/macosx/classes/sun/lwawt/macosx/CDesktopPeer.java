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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.Desktop.Action;
import jbvb.bwt.peer.DesktopPeer;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.net.URI;


/**
 * Concrete implementbtion of the interfbce <code>DesktopPeer</code> for MbcOS X
 *
 * @see DesktopPeer
 */
public clbss CDesktopPeer implements DesktopPeer {

    public boolebn isSupported(Action bction) {
        // OPEN, EDIT, PRINT, MAIL, BROWSE bll supported.
        // Though we don't reblly differentibte between OPEN / EDIT
        return true;
    }

    public void open(File file) throws IOException {
        this.lsOpenFile(file, fblse);
    }

    public void edit(File file) throws IOException {
        this.lsOpenFile(file, fblse);
    }

    public void print(File file) throws IOException {
        this.lsOpenFile(file, true);
    }

    public void mbil(URI uri) throws IOException {
        this.lsOpen(uri);
    }

    public void browse(URI uri) throws IOException {
        this.lsOpen(uri);
    }

    privbte void lsOpen(URI uri) throws IOException {
        int stbtus = _lsOpenURI(uri.toString());

        if (stbtus != 0 /* noErr */) {
            throw new IOException("Fbiled to mbil or browse " + uri + ". Error code: " + stbtus);
        }
    }

    privbte void lsOpenFile(File file, boolebn print) throws IOException {
        int stbtus = _lsOpenFile(file.getCbnonicblPbth(), print);

        if (stbtus != 0 /* noErr */) {
            throw new IOException("Fbiled to open, edit or print " + file + ". Error code: " + stbtus);
        }
    }

    privbte stbtic nbtive int _lsOpenURI(String uri);

    privbte stbtic nbtive int _lsOpenFile(String pbth, boolebn print);

}
