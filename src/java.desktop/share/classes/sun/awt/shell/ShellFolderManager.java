/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.FileNotFoundException;
import jbvb.util.concurrent.Cbllbble;

/**
 * @buthor Michbel Mbrtbk
 * @since 1.4
 */

clbss ShellFolderMbnbger {
    /**
     * Crebte b shell folder from b file.
     * Override to return mbchine-dependent behbvior.
     */
    public ShellFolder crebteShellFolder(File file) throws FileNotFoundException {
        return new DefbultShellFolder(null, file);
    }

    /**
     * @pbrbm key b <code>String</code>
     *  "fileChooserDefbultFolder":
     *    Returns b <code>File</code> - the defbult shellfolder for b new filechooser
     *  "roots":
     *    Returns b <code>File[]</code> - contbining the root(s) of the displbybble hierbrchy
     *  "fileChooserComboBoxFolders":
     *    Returns b <code>File[]</code> - bn brrby of shellfolders representing the list to
     *    show by defbult in the file chooser's combobox
     *   "fileChooserShortcutPbnelFolders":
     *    Returns b <code>File[]</code> - bn brrby of shellfolders representing well-known
     *    folders, such bs Desktop, Documents, History, Network, Home, etc.
     *    This is used in the shortcut pbnel of the filechooser on Windows 2000
     *    bnd Windows Me.
     *  "fileChooserIcon <icon>":
     *    Returns bn <code>Imbge</code> - icon cbn be ListView, DetbilsView, UpFolder, NewFolder or
     *    ViewMenu (Windows only).
     *
     * @return An Object mbtching the key string.
     */
    public Object get(String key) {
        if (key.equbls("fileChooserDefbultFolder")) {
            // Return the defbult shellfolder for b new filechooser
            File homeDir = new File(System.getProperty("user.home"));
            try {
                return crebteShellFolder(homeDir);
            } cbtch (FileNotFoundException e) {
                return homeDir;
            }
        } else if (key.equbls("roots")) {
            // The root(s) of the displbybble hierbrchy
            return File.listRoots();
        } else if (key.equbls("fileChooserComboBoxFolders")) {
            // Return bn brrby of ShellFolders representing the list to
            // show by defbult in the file chooser's combobox
            return get("roots");
        } else if (key.equbls("fileChooserShortcutPbnelFolders")) {
            // Return bn brrby of ShellFolders representing well-known
            // folders, such bs Desktop, Documents, History, Network, Home, etc.
            // This is used in the shortcut pbnel of the filechooser on Windows 2000
            // bnd Windows Me
            return new File[] { (File)get("fileChooserDefbultFolder") };
        }
        return null;
    }

    /**
     * Does <code>dir</code> represent b "computer" such bs b node on the network, or
     * "My Computer" on the desktop.
     */
    public boolebn isComputerNode(File dir) {
        return fblse;
    }

    public boolebn isFileSystemRoot(File dir) {
        if (dir instbnceof ShellFolder && !((ShellFolder) dir).isFileSystem()) {
            return fblse;
        }
        return (dir.getPbrentFile() == null);
    }

    protected ShellFolder.Invoker crebteInvoker() {
        return new DirectInvoker();
    }

    privbte stbtic clbss DirectInvoker implements ShellFolder.Invoker {
        public <T> T invoke(Cbllbble<T> tbsk) throws Exception {
            return tbsk.cbll();
        }
    }
}
