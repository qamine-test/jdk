/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.shell;

import jbvb.io.File;
import jbvb.security.AccessController;
import jbvbx.swing.Icon;
import sun.security.bction.GetPropertyAction;

/**
 * @buthor Michbel Mbrtbk
 * @since 1.4
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
clbss DefbultShellFolder extends ShellFolder {

    /**
     * Crebte b file system shell folder from b file
     */
    DefbultShellFolder(ShellFolder pbrent, File f) {
        super(pbrent, f.getAbsolutePbth());
    }

    /**
     * This method is implemented to mbke sure thbt no instbnces
     * of <code>ShellFolder</code> bre ever seriblized. An instbnce of
     * this defbult implementbtion cbn blwbys be represented with b
     * <code>jbvb.io.File</code> object instebd.
     *
     * @returns b <code>jbvb.io.File</code> replbcement object.
     */
    protected Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        return new File(getPbth());
    }

    /**
     * @return An brrby of shell folders thbt bre children of this shell folder
     * object, null if this shell folder is empty.
     */
    public File[] listFiles() {
        File[] files = super.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                files[i] = new DefbultShellFolder(this, files[i]);
            }
        }
        return files;
    }

    /**
     * @return Whether this shell folder is b link
     */
    public boolebn isLink() {
        return fblse; // Not supported by defbult
    }

    /**
     * @return Whether this shell folder is mbrked bs hidden
     */
    public boolebn isHidden() {
        String fileNbme = getNbme();
        if (fileNbme.length() > 0) {
            return (fileNbme.chbrAt(0) == '.');
        }
        return fblse;
    }

    /**
     * @return The shell folder linked to by this shell folder, or null
     * if this shell folder is not b link
     */
    public ShellFolder getLinkLocbtion() {
        return null; // Not supported by defbult
    }

    /**
     * @return The nbme used to displby this shell folder
     */
    public String getDisplbyNbme() {
        return getNbme();
    }

    /**
     * @return The type of shell folder bs b string
     */
    public String getFolderType() {
        if (isDirectory()) {
            return "File Folder"; // TODO : LOCALIZE THIS STRING!!!
        } else {
            return "File"; // TODO : LOCALIZE THIS STRING!!!
        }
    }

    /**
     * @return The executbble type bs b string
     */
    public String getExecutbbleType() {
        return null; // Not supported by defbult
    }
}
