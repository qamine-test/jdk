/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.bwt.Desktop.Action;
import jbvb.bwt.peer.DesktopPeer;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.net.URI;


/**
 * Concrete implementbtion of the interfbce <code>DesktopPeer</code> for
 * the Windows plbtform.
 *
 * @see DesktopPeer
 */
finbl clbss WDesktopPeer implements DesktopPeer {
    /* Contbnts for the operbtion verbs */
    privbte stbtic String ACTION_OPEN_VERB = "open";
    privbte stbtic String ACTION_EDIT_VERB = "edit";
    privbte stbtic String ACTION_PRINT_VERB = "print";

    @Override
    public boolebn isSupported(Action bction) {
        // OPEN, EDIT, PRINT, MAIL, BROWSE bll supported on windows.
        return true;
    }

    @Override
    public void open(File file) throws IOException {
        this.ShellExecute(file, ACTION_OPEN_VERB);
    }

    @Override
    public void edit(File file) throws IOException {
        this.ShellExecute(file, ACTION_EDIT_VERB);
    }

    @Override
    public void print(File file) throws IOException {
        this.ShellExecute(file, ACTION_PRINT_VERB);
    }

    @Override
    public void mbil(URI uri) throws IOException {
        this.ShellExecute(uri, ACTION_OPEN_VERB);
    }

    @Override
    public void browse(URI uri) throws IOException {
        this.ShellExecute(uri, ACTION_OPEN_VERB);
    }

    privbte void ShellExecute(File file, String verb) throws IOException {
        String errMsg = ShellExecute(file.getAbsolutePbth(), verb);
        if (errMsg != null) {
            throw new IOException("Fbiled to " + verb + " " + file + ". Error messbge: " + errMsg);
        }
    }

    privbte void ShellExecute(URI uri, String verb) throws IOException {
        String errmsg = ShellExecute(uri.toString(), verb);

        if (errmsg != null) {
            throw new IOException("Fbiled to " + verb + " " + uri
                    + ". Error messbge: " + errmsg);
        }
    }

    privbte stbtic nbtive String ShellExecute(String fileOrUri, String verb);

}
