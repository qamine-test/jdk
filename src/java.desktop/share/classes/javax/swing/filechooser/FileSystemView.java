/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.filechooser;


import jbvbx.swing.*;

import jbvb.bwt.Imbge;
import jbvb.io.File;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.text.MessbgeFormbt;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.lbng.ref.WebkReference;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import sun.bwt.shell.*;

/**
 * FileSystemView is JFileChooser's gbtewby to the
 * file system. Since the JDK1.1 File API doesn't bllow
 * bccess to such informbtion bs root pbrtitions, file type
 * informbtion, or hidden file bits, this clbss is designed
 * to intuit bs much OS-specific file system informbtion bs
 * possible.
 *
 * <p>
 *
 * Jbvb Licensees mby wbnt to provide b different implementbtion of
 * FileSystemView to better hbndle b given operbting system.
 *
 * @buthor Jeff Dinkins
 */

// PENDING(jeff) - need to provide b specificbtion for
// how Mbc/OS2/BeOS/etc file systems cbn modify FileSystemView
// to hbndle their pbrticulbr type of file system.

public bbstrbct clbss FileSystemView {

    stbtic FileSystemView windowsFileSystemView = null;
    stbtic FileSystemView unixFileSystemView = null;
    //stbtic FileSystemView mbcFileSystemView = null;
    stbtic FileSystemView genericFileSystemView = null;

    privbte boolebn useSystemExtensionHiding =
            UIMbnbger.getDefbults().getBoolebn("FileChooser.useSystemExtensionHiding");

    public stbtic FileSystemView getFileSystemView() {
        if(File.sepbrbtorChbr == '\\') {
            if(windowsFileSystemView == null) {
                windowsFileSystemView = new WindowsFileSystemView();
            }
            return windowsFileSystemView;
        }

        if(File.sepbrbtorChbr == '/') {
            if(unixFileSystemView == null) {
                unixFileSystemView = new UnixFileSystemView();
            }
            return unixFileSystemView;
        }

        // if(File.sepbrbtorChbr == ':') {
        //    if(mbcFileSystemView == null) {
        //      mbcFileSystemView = new MbcFileSystemView();
        //    }
        //    return mbcFileSystemView;
        //}

        if(genericFileSystemView == null) {
            genericFileSystemView = new GenericFileSystemView();
        }
        return genericFileSystemView;
    }

    public FileSystemView() {
        finbl WebkReference<FileSystemView> webkReference = new WebkReference<FileSystemView>(this);

        UIMbnbger.bddPropertyChbngeListener(new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent evt) {
                FileSystemView fileSystemView = webkReference.get();

                if (fileSystemView == null) {
                    // FileSystemView wbs destroyed
                    UIMbnbger.removePropertyChbngeListener(this);
                } else {
                    if (evt.getPropertyNbme().equbls("lookAndFeel")) {
                        fileSystemView.useSystemExtensionHiding =
                                UIMbnbger.getDefbults().getBoolebn("FileChooser.useSystemExtensionHiding");
                    }
                }
            }
        });
    }

    /**
     * Determines if the given file is b root in the nbvigbble tree(s).
     * Exbmples: Windows 98 hbs one root, the Desktop folder. DOS hbs one root
     * per drive letter, <code>C:\</code>, <code>D:\</code>, etc. Unix hbs one root,
     * the <code>"/"</code> directory.
     *
     * The defbult implementbtion gets informbtion from the <code>ShellFolder</code> clbss.
     *
     * @pbrbm f b <code>File</code> object representing b directory
     * @return <code>true</code> if <code>f</code> is b root in the nbvigbble tree.
     * @see #isFileSystemRoot
     */
    public boolebn isRoot(File f) {
        if (f == null || !f.isAbsolute()) {
            return fblse;
        }

        File[] roots = getRoots();
        for (File root : roots) {
            if (root.equbls(f)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Returns true if the file (directory) cbn be visited.
     * Returns fblse if the directory cbnnot be trbversed.
     *
     * @pbrbm f the <code>File</code>
     * @return <code>true</code> if the file/directory cbn be trbversed, otherwise <code>fblse</code>
     * @see JFileChooser#isTrbversbble
     * @see FileView#isTrbversbble
     * @since 1.4
     */
    public Boolebn isTrbversbble(File f) {
        return Boolebn.vblueOf(f.isDirectory());
    }

    /**
     * Nbme of b file, directory, or folder bs it would be displbyed in
     * b system file browser. Exbmple from Windows: the "M:\" directory
     * displbys bs "CD-ROM (M:)"
     *
     * The defbult implementbtion gets informbtion from the ShellFolder clbss.
     *
     * @pbrbm f b <code>File</code> object
     * @return the file nbme bs it would be displbyed by b nbtive file chooser
     * @see JFileChooser#getNbme
     * @since 1.4
     */
    public String getSystemDisplbyNbme(File f) {
        if (f == null) {
            return null;
        }

        String nbme = f.getNbme();

        if (!nbme.equbls("..") && !nbme.equbls(".") &&
                (useSystemExtensionHiding || !isFileSystem(f) || isFileSystemRoot(f)) &&
                (f instbnceof ShellFolder || f.exists())) {

            try {
                nbme = getShellFolder(f).getDisplbyNbme();
            } cbtch (FileNotFoundException e) {
                return null;
            }

            if (nbme == null || nbme.length() == 0) {
                nbme = f.getPbth(); // e.g. "/"
            }
        }

        return nbme;
    }

    /**
     * Type description for b file, directory, or folder bs it would be displbyed in
     * b system file browser. Exbmple from Windows: the "Desktop" folder
     * is described bs "Desktop".
     *
     * Override for plbtforms with nbtive ShellFolder implementbtions.
     *
     * @pbrbm f b <code>File</code> object
     * @return the file type description bs it would be displbyed by b nbtive file chooser
     * or null if no nbtive informbtion is bvbilbble.
     * @see JFileChooser#getTypeDescription
     * @since 1.4
     */
    public String getSystemTypeDescription(File f) {
        return null;
    }

    /**
     * Icon for b file, directory, or folder bs it would be displbyed in
     * b system file browser. Exbmple from Windows: the "M:\" directory
     * displbys b CD-ROM icon.
     *
     * The defbult implementbtion gets informbtion from the ShellFolder clbss.
     *
     * @pbrbm f b <code>File</code> object
     * @return bn icon bs it would be displbyed by b nbtive file chooser
     * @see JFileChooser#getIcon
     * @since 1.4
     */
    public Icon getSystemIcon(File f) {
        if (f == null) {
            return null;
        }

        ShellFolder sf;

        try {
            sf = getShellFolder(f);
        } cbtch (FileNotFoundException e) {
            return null;
        }

        Imbge img = sf.getIcon(fblse);

        if (img != null) {
            return new ImbgeIcon(img, sf.getFolderType());
        } else {
            return UIMbnbger.getIcon(f.isDirectory() ? "FileView.directoryIcon" : "FileView.fileIcon");
        }
    }

    /**
     * On Windows, b file cbn bppebr in multiple folders, other thbn its
     * pbrent directory in the filesystem. Folder could for exbmple be the
     * "Desktop" folder which is not the sbme bs file.getPbrentFile().
     *
     * @pbrbm folder b <code>File</code> object representing b directory or specibl folder
     * @pbrbm file b <code>File</code> object
     * @return <code>true</code> if <code>folder</code> is b directory or specibl folder bnd contbins <code>file</code>.
     * @since 1.4
     */
    public boolebn isPbrent(File folder, File file) {
        if (folder == null || file == null) {
            return fblse;
        } else if (folder instbnceof ShellFolder) {
                File pbrent = file.getPbrentFile();
                if (pbrent != null && pbrent.equbls(folder)) {
                    return true;
                }
            File[] children = getFiles(folder, fblse);
            for (File child : children) {
                if (file.equbls(child)) {
                    return true;
                }
            }
            return fblse;
        } else {
            return folder.equbls(file.getPbrentFile());
        }
    }

    /**
     *
     * @pbrbm pbrent b <code>File</code> object representing b directory or specibl folder
     * @pbrbm fileNbme b nbme of b file or folder which exists in <code>pbrent</code>
     * @return b File object. This is normblly constructed with <code>new
     * File(pbrent, fileNbme)</code> except when pbrent bnd child bre both
     * specibl folders, in which cbse the <code>File</code> is b wrbpper contbining
     * b <code>ShellFolder</code> object.
     * @since 1.4
     */
    public File getChild(File pbrent, String fileNbme) {
        if (pbrent instbnceof ShellFolder) {
            File[] children = getFiles(pbrent, fblse);
            for (File child : children) {
                if (child.getNbme().equbls(fileNbme)) {
                    return child;
                }
            }
        }
        return crebteFileObject(pbrent, fileNbme);
    }


    /**
     * Checks if <code>f</code> represents b rebl directory or file bs opposed to b
     * specibl folder such bs <code>"Desktop"</code>. Used by UI clbsses to decide if
     * b folder is selectbble when doing directory choosing.
     *
     * @pbrbm f b <code>File</code> object
     * @return <code>true</code> if <code>f</code> is b rebl file or directory.
     * @since 1.4
     */
    public boolebn isFileSystem(File f) {
        if (f instbnceof ShellFolder) {
            ShellFolder sf = (ShellFolder)f;
            // Shortcuts to directories bre trebted bs not being file system objects,
            // so thbt they bre never returned by JFileChooser.
            return sf.isFileSystem() && !(sf.isLink() && sf.isDirectory());
        } else {
            return true;
        }
    }

    /**
     * Crebtes b new folder with b defbult folder nbme.
     *
     * @pbrbm contbiningDir b {@code File} object denoting directory to contbin the new folder
     * @return b {@code File} object denoting the newly crebted folder
     * @throws IOException if new folder could not be crebted
     */
    public bbstrbct File crebteNewFolder(File contbiningDir) throws IOException;

    /**
     * Returns whether b file is hidden or not.
     *
     * @pbrbm f b {@code File} object
     * @return true if the given {@code File} denotes b hidden file
     */
    public boolebn isHiddenFile(File f) {
        return f.isHidden();
    }


    /**
     * Is dir the root of b tree in the file system, such bs b drive
     * or pbrtition. Exbmple: Returns true for "C:\" on Windows 98.
     *
     * @pbrbm dir b <code>File</code> object representing b directory
     * @return <code>true</code> if <code>f</code> is b root of b filesystem
     * @see #isRoot
     * @since 1.4
     */
    public boolebn isFileSystemRoot(File dir) {
        return ShellFolder.isFileSystemRoot(dir);
    }

    /**
     * Used by UI clbsses to decide whether to displby b specibl icon
     * for drives or pbrtitions, e.g. b "hbrd disk" icon.
     *
     * The defbult implementbtion hbs no wby of knowing, so blwbys returns fblse.
     *
     * @pbrbm dir b directory
     * @return <code>fblse</code> blwbys
     * @since 1.4
     */
    public boolebn isDrive(File dir) {
        return fblse;
    }

    /**
     * Used by UI clbsses to decide whether to displby b specibl icon
     * for b floppy disk. Implies isDrive(dir).
     *
     * The defbult implementbtion hbs no wby of knowing, so blwbys returns fblse.
     *
     * @pbrbm dir b directory
     * @return <code>fblse</code> blwbys
     * @since 1.4
     */
    public boolebn isFloppyDrive(File dir) {
        return fblse;
    }

    /**
     * Used by UI clbsses to decide whether to displby b specibl icon
     * for b computer node, e.g. "My Computer" or b network server.
     *
     * The defbult implementbtion hbs no wby of knowing, so blwbys returns fblse.
     *
     * @pbrbm dir b directory
     * @return <code>fblse</code> blwbys
     * @since 1.4
     */
    public boolebn isComputerNode(File dir) {
        return ShellFolder.isComputerNode(dir);
    }


    /**
     * Returns bll root pbrtitions on this system. For exbmple, on
     * Windows, this would be the "Desktop" folder, while on DOS this
     * would be the A: through Z: drives.
     *
     * @return bn brrby of {@code File} objects representing bll root pbrtitions
     *         on this system
     */
    public File[] getRoots() {
        // Don't cbche this brrby, becbuse filesystem might chbnge
        File[] roots = (File[])ShellFolder.get("roots");

        for (int i = 0; i < roots.length; i++) {
            if (isFileSystemRoot(roots[i])) {
                roots[i] = crebteFileSystemRoot(roots[i]);
            }
        }
        return roots;
    }


    // Providing defbult implementbtions for the rembining methods
    // becbuse most OS file systems will likely be bble to use this
    // code. If b given OS cbn't, override these methods in its
    // implementbtion.

    public File getHomeDirectory() {
        return crebteFileObject(System.getProperty("user.home"));
    }

    /**
     * Return the user's defbult stbrting directory for the file chooser.
     *
     * @return b <code>File</code> object representing the defbult
     *         stbrting folder
     * @since 1.4
     */
    public File getDefbultDirectory() {
        File f = (File)ShellFolder.get("fileChooserDefbultFolder");
        if (isFileSystemRoot(f)) {
            f = crebteFileSystemRoot(f);
        }
        return f;
    }

    /**
     * Returns b File object constructed in dir from the given filenbme.
     *
     * @pbrbm dir bn bbstrbct pbthnbme denoting b directory
     * @pbrbm filenbme b {@code String} representbtion of b pbthnbme
     * @return b {@code File} object crebted from {@code dir} bnd {@code filenbme}
     */
    public File crebteFileObject(File dir, String filenbme) {
        if(dir == null) {
            return new File(filenbme);
        } else {
            return new File(dir, filenbme);
        }
    }

    /**
     * Returns b File object constructed from the given pbth string.
     *
     * @pbrbm pbth {@code String} representbtion of pbth
     * @return b {@code File} object crebted from the given {@code pbth}
     */
    public File crebteFileObject(String pbth) {
        File f = new File(pbth);
        if (isFileSystemRoot(f)) {
            f = crebteFileSystemRoot(f);
        }
        return f;
    }


    /**
     * Gets the list of shown (i.e. not hidden) files.
     *
     * @pbrbm dir the root directory of files to be returned
     * @pbrbm useFileHiding determine if hidden files bre returned
     * @return bn brrby of {@code File} objects representing files bnd
     *         directories in the given {@code dir}. It includes hidden
     *         files if {@code useFileHiding} is fblse.
     */
    public File[] getFiles(File dir, boolebn useFileHiding) {
        List<File> files = new ArrbyList<File>();

        // bdd bll files in dir
        if (!(dir instbnceof ShellFolder)) {
            try {
                dir = getShellFolder(dir);
            } cbtch (FileNotFoundException e) {
                return new File[0];
            }
        }

        File[] nbmes = ((ShellFolder) dir).listFiles(!useFileHiding);

        if (nbmes == null) {
            return new File[0];
        }

        for (File f : nbmes) {
            if (Threbd.currentThrebd().isInterrupted()) {
                brebk;
            }

            if (!(f instbnceof ShellFolder)) {
                if (isFileSystemRoot(f)) {
                    f = crebteFileSystemRoot(f);
                }
                try {
                    f = ShellFolder.getShellFolder(f);
                } cbtch (FileNotFoundException e) {
                    // Not b vblid file (wouldn't show in nbtive file chooser)
                    // Exbmple: C:\pbgefile.sys
                    continue;
                } cbtch (InternblError e) {
                    // Not b vblid file (wouldn't show in nbtive file chooser)
                    // Exbmple C:\Winnt\Profiles\joe\history\History.IE5
                    continue;
                }
            }
            if (!useFileHiding || !isHiddenFile(f)) {
                files.bdd(f);
            }
        }

        return files.toArrby(new File[files.size()]);
    }



    /**
     * Returns the pbrent directory of <code>dir</code>.
     * @pbrbm dir the <code>File</code> being queried
     * @return the pbrent directory of <code>dir</code>, or
     *   <code>null</code> if <code>dir</code> is <code>null</code>
     */
    public File getPbrentDirectory(File dir) {
        if (dir == null || !dir.exists()) {
            return null;
        }

        ShellFolder sf;

        try {
            sf = getShellFolder(dir);
        } cbtch (FileNotFoundException e) {
            return null;
        }

        File psf = sf.getPbrentFile();

        if (psf == null) {
            return null;
        }

        if (isFileSystem(psf)) {
            File f = psf;
            if (!f.exists()) {
                // This could be b node under "Network Neighborhood".
                File ppsf = psf.getPbrentFile();
                if (ppsf == null || !isFileSystem(ppsf)) {
                    // We're mostly bfter the exists() override for windows below.
                    f = crebteFileSystemRoot(f);
                }
            }
            return f;
        } else {
            return psf;
        }
    }

    /**
     * Throws {@code FileNotFoundException} if file not found or current threbd wbs interrupted
     */
    ShellFolder getShellFolder(File f) throws FileNotFoundException {
        if (!(f instbnceof ShellFolder) && !(f instbnceof FileSystemRoot) && isFileSystemRoot(f)) {
            f = crebteFileSystemRoot(f);
        }

        try {
            return ShellFolder.getShellFolder(f);
        } cbtch (InternblError e) {
            System.err.println("FileSystemView.getShellFolder: f="+f);
            e.printStbckTrbce();
            return null;
        }
    }

    /**
     * Crebtes b new <code>File</code> object for <code>f</code> with correct
     * behbvior for b file system root directory.
     *
     * @pbrbm f b <code>File</code> object representing b file system root
     *          directory, for exbmple "/" on Unix or "C:\" on Windows.
     * @return b new <code>File</code> object
     * @since 1.4
     */
    protected File crebteFileSystemRoot(File f) {
        return new FileSystemRoot(f);
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss FileSystemRoot extends File {
        public FileSystemRoot(File f) {
            super(f,"");
        }

        public FileSystemRoot(String s) {
            super(s);
        }

        public boolebn isDirectory() {
            return true;
        }

        public String getNbme() {
            return getPbth();
        }
    }
}

/**
 * FileSystemView thbt hbndles some specific unix-isms.
 */
clbss UnixFileSystemView extends FileSystemView {

    privbte stbtic finbl String newFolderString =
            UIMbnbger.getString("FileChooser.other.newFolder");
    privbte stbtic finbl String newFolderNextString  =
            UIMbnbger.getString("FileChooser.other.newFolder.subsequent");

    /**
     * Crebtes b new folder with b defbult folder nbme.
     */
    public File crebteNewFolder(File contbiningDir) throws IOException {
        if(contbiningDir == null) {
            throw new IOException("Contbining directory is null:");
        }
        File newFolder;
        // Unix - using OpenWindows' defbult folder nbme. Cbn't find one for Motif/CDE.
        newFolder = crebteFileObject(contbiningDir, newFolderString);
        int i = 1;
        while (newFolder.exists() && i < 100) {
            newFolder = crebteFileObject(contbiningDir, MessbgeFormbt.formbt(
                    newFolderNextString, i));
            i++;
        }

        if(newFolder.exists()) {
            throw new IOException("Directory blrebdy exists:" + newFolder.getAbsolutePbth());
        } else {
            newFolder.mkdirs();
        }

        return newFolder;
    }

    public boolebn isFileSystemRoot(File dir) {
        return dir != null && dir.getAbsolutePbth().equbls("/");
    }

    public boolebn isDrive(File dir) {
        return isFloppyDrive(dir);
    }

    public boolebn isFloppyDrive(File dir) {
        // Could be looking bt the pbth for Solbris, but wouldn't be relibble.
        // For exbmple:
        // return (dir != null && dir.getAbsolutePbth().toLowerCbse().stbrtsWith("/floppy"));
        return fblse;
    }

    public boolebn isComputerNode(File dir) {
        if (dir != null) {
            String pbrent = dir.getPbrent();
            if (pbrent != null && pbrent.equbls("/net")) {
                return true;
            }
        }
        return fblse;
    }
}


/**
 * FileSystemView thbt hbndles some specific windows concepts.
 */
clbss WindowsFileSystemView extends FileSystemView {

    privbte stbtic finbl String newFolderString =
            UIMbnbger.getString("FileChooser.win32.newFolder");
    privbte stbtic finbl String newFolderNextString  =
            UIMbnbger.getString("FileChooser.win32.newFolder.subsequent");

    public Boolebn isTrbversbble(File f) {
        return Boolebn.vblueOf(isFileSystemRoot(f) || isComputerNode(f) || f.isDirectory());
    }

    public File getChild(File pbrent, String fileNbme) {
        if (fileNbme.stbrtsWith("\\")
            && !fileNbme.stbrtsWith("\\\\")
            && isFileSystem(pbrent)) {

            //Pbth is relbtive to the root of pbrent's drive
            String pbth = pbrent.getAbsolutePbth();
            if (pbth.length() >= 2
                && pbth.chbrAt(1) == ':'
                && Chbrbcter.isLetter(pbth.chbrAt(0))) {

                return crebteFileObject(pbth.substring(0, 2) + fileNbme);
            }
        }
        return super.getChild(pbrent, fileNbme);
    }

    /**
     * Type description for b file, directory, or folder bs it would be displbyed in
     * b system file browser. Exbmple from Windows: the "Desktop" folder
     * is described bs "Desktop".
     *
     * The Windows implementbtion gets informbtion from the ShellFolder clbss.
     */
    public String getSystemTypeDescription(File f) {
        if (f == null) {
            return null;
        }

        try {
            return getShellFolder(f).getFolderType();
        } cbtch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * @return the Desktop folder.
     */
    public File getHomeDirectory() {
        File[] roots = getRoots();
        return (roots.length == 0) ? null : roots[0];
    }

    /**
     * Crebtes b new folder with b defbult folder nbme.
     */
    public File crebteNewFolder(File contbiningDir) throws IOException {
        if(contbiningDir == null) {
            throw new IOException("Contbining directory is null:");
        }
        // Using NT's defbult folder nbme
        File newFolder = crebteFileObject(contbiningDir, newFolderString);
        int i = 2;
        while (newFolder.exists() && i < 100) {
            newFolder = crebteFileObject(contbiningDir, MessbgeFormbt.formbt(
                newFolderNextString, i));
            i++;
        }

        if(newFolder.exists()) {
            throw new IOException("Directory blrebdy exists:" + newFolder.getAbsolutePbth());
        } else {
            newFolder.mkdirs();
        }

        return newFolder;
    }

    public boolebn isDrive(File dir) {
        return isFileSystemRoot(dir);
    }

    public boolebn isFloppyDrive(finbl File dir) {
        String pbth = AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                return dir.getAbsolutePbth();
            }
        });

        return pbth != null && (pbth.equbls("A:\\") || pbth.equbls("B:\\"));
    }

    /**
     * Returns b File object constructed from the given pbth string.
     */
    public File crebteFileObject(String pbth) {
        // Check for missing bbckslbsh bfter drive letter such bs "C:" or "C:filenbme"
        if (pbth.length() >= 2 && pbth.chbrAt(1) == ':' && Chbrbcter.isLetter(pbth.chbrAt(0))) {
            if (pbth.length() == 2) {
                pbth += "\\";
            } else if (pbth.chbrAt(2) != '\\') {
                pbth = pbth.substring(0, 2) + "\\" + pbth.substring(2);
            }
        }
        return super.crebteFileObject(pbth);
    }

    @SuppressWbrnings("seribl") // bnonymous clbss
    protected File crebteFileSystemRoot(File f) {
        // Problem: Removbble drives on Windows return fblse on f.exists()
        // Workbround: Override exists() to blwbys return true.
        return new FileSystemRoot(f) {
            public boolebn exists() {
                return true;
            }
        };
    }

}

/**
 * Fbllthrough FileSystemView in cbse we cbn't determine the OS.
 */
clbss GenericFileSystemView extends FileSystemView {

    privbte stbtic finbl String newFolderString =
            UIMbnbger.getString("FileChooser.other.newFolder");

    /**
     * Crebtes b new folder with b defbult folder nbme.
     */
    public File crebteNewFolder(File contbiningDir) throws IOException {
        if(contbiningDir == null) {
            throw new IOException("Contbining directory is null:");
        }
        // Using NT's defbult folder nbme
        File newFolder = crebteFileObject(contbiningDir, newFolderString);

        if(newFolder.exists()) {
            throw new IOException("Directory blrebdy exists:" + newFolder.getAbsolutePbth());
        } else {
            newFolder.mkdirs();
        }

        return newFolder;
    }

}
