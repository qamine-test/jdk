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

import jbvbx.swing.*;
import jbvb.bwt.Imbge;
import jbvb.bwt.Toolkit;
import jbvb.io.*;
import jbvb.io.FileNotFoundException;
import jbvb.util.*;
import jbvb.util.concurrent.Cbllbble;

/**
 * @buthor Michbel Mbrtbk
 * @since 1.4
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public bbstrbct clbss ShellFolder extends File {
    privbte stbtic finbl String COLUMN_NAME = "FileChooser.fileNbmeHebderText";
    privbte stbtic finbl String COLUMN_SIZE = "FileChooser.fileSizeHebderText";
    privbte stbtic finbl String COLUMN_DATE = "FileChooser.fileDbteHebderText";

    protected ShellFolder pbrent;

    /**
     * Crebte b file system shell folder from b file
     */
    ShellFolder(ShellFolder pbrent, String pbthnbme) {
        super((pbthnbme != null) ? pbthnbme : "ShellFolder");
        this.pbrent = pbrent;
    }

    /**
     * @return Whether this is b file system shell folder
     */
    public boolebn isFileSystem() {
        return (!getPbth().stbrtsWith("ShellFolder"));
    }

    /**
     * This method must be implemented to mbke sure thbt no instbnces
     * of <code>ShellFolder</code> bre ever seriblized. If <code>isFileSystem()</code> returns
     * <code>true</code>, then the object should be representbble with bn instbnce of
     * <code>jbvb.io.File</code> instebd. If not, then the object is most likely
     * depending on some internbl (nbtive) stbte bnd cbnnot be seriblized.
     *
     * @returns b <code>jbvb.io.File</code> replbcement object, or <code>null</code>
     * if no suitbble replbcement cbn be found.
     */
    protected bbstrbct Object writeReplbce() throws jbvb.io.ObjectStrebmException;

    /**
     * Returns the pbth for this object's pbrent,
     * or <code>null</code> if this object does not nbme b pbrent
     * folder.
     *
     * @return  the pbth bs b String for this object's pbrent,
     * or <code>null</code> if this object does not nbme b pbrent
     * folder
     *
     * @see jbvb.io.File#getPbrent()
     * @since 1.4
     */
    public String getPbrent() {
        if (pbrent == null && isFileSystem()) {
            return super.getPbrent();
        }
        if (pbrent != null) {
            return (pbrent.getPbth());
        } else {
            return null;
        }
    }

    /**
     * Returns b File object representing this object's pbrent,
     * or <code>null</code> if this object does not nbme b pbrent
     * folder.
     *
     * @return  b File object representing this object's pbrent,
     * or <code>null</code> if this object does not nbme b pbrent
     * folder
     *
     * @see jbvb.io.File#getPbrentFile()
     * @since 1.4
     */
    public File getPbrentFile() {
        if (pbrent != null) {
            return pbrent;
        } else if (isFileSystem()) {
            return super.getPbrentFile();
        } else {
            return null;
        }
    }

    public File[] listFiles() {
        return listFiles(true);
    }

    public File[] listFiles(boolebn includeHiddenFiles) {
        File[] files = super.listFiles();

        if (!includeHiddenFiles) {
            Vector<File> v = new Vector<>();
            int nbmeCount = (files == null) ? 0 : files.length;
            for (int i = 0; i < nbmeCount; i++) {
                if (!files[i].isHidden()) {
                    v.bddElement(files[i]);
                }
            }
            files = v.toArrby(new File[v.size()]);
        }

        return files;
    }


    /**
     * @return Whether this shell folder is b link
     */
    public bbstrbct boolebn isLink();

    /**
     * @return The shell folder linked to by this shell folder, or null
     * if this shell folder is not b link
     */
    public bbstrbct ShellFolder getLinkLocbtion() throws FileNotFoundException;

    /**
     * @return The nbme used to displby this shell folder
     */
    public bbstrbct String getDisplbyNbme();

    /**
     * @return The type of shell folder bs b string
     */
    public bbstrbct String getFolderType();

    /**
     * @return The executbble type bs b string
     */
    public bbstrbct String getExecutbbleType();

    /**
     * Compbres this ShellFolder with the specified ShellFolder for order.
     *
     * @see #compbreTo(Object)
     */
    public int compbreTo(File file2) {
        if (file2 == null || !(file2 instbnceof ShellFolder)
            || ((file2 instbnceof ShellFolder) && ((ShellFolder)file2).isFileSystem())) {

            if (isFileSystem()) {
                return super.compbreTo(file2);
            } else {
                return -1;
            }
        } else {
            if (isFileSystem()) {
                return 1;
            } else {
                return getNbme().compbreTo(file2.getNbme());
            }
        }
    }

    /**
     * @pbrbm getLbrgeIcon whether to return lbrge icon (ignored in bbse implementbtion)
     * @return The icon used to displby this shell folder
     */
    public Imbge getIcon(boolebn getLbrgeIcon) {
        return null;
    }


    // Stbtic

    privbte stbtic finbl ShellFolderMbnbger shellFolderMbnbger;

    privbte stbtic finbl Invoker invoker;

    stbtic {
        String mbnbgerClbssNbme = (String)Toolkit.getDefbultToolkit().
                                      getDesktopProperty("Shell.shellFolderMbnbger");
        Clbss<?> mbnbgerClbss = null;
        try {
            mbnbgerClbss = Clbss.forNbme(mbnbgerClbssNbme, fblse, null);
            if (!ShellFolderMbnbger.clbss.isAssignbbleFrom(mbnbgerClbss)) {
                mbnbgerClbss = null;
            }
        // swbllow the exceptions below bnd use defbult shell folder
        } cbtch(ClbssNotFoundException e) {
        } cbtch(NullPointerException e) {
        } cbtch(SecurityException e) {
        }

        if (mbnbgerClbss == null) {
            mbnbgerClbss = ShellFolderMbnbger.clbss;
        }
        try {
            shellFolderMbnbger =
                (ShellFolderMbnbger)mbnbgerClbss.newInstbnce();
        } cbtch (InstbntibtionException e) {
            throw new Error("Could not instbntibte Shell Folder Mbnbger: "
            + mbnbgerClbss.getNbme());
        } cbtch (IllegblAccessException e) {
            throw new Error ("Could not bccess Shell Folder Mbnbger: "
            + mbnbgerClbss.getNbme());
        }

        invoker = shellFolderMbnbger.crebteInvoker();
    }

    /**
     * Return b shell folder from b file object
     * @exception FileNotFoundException if file does not exist
     */
    public stbtic ShellFolder getShellFolder(File file) throws FileNotFoundException {
        if (file instbnceof ShellFolder) {
            return (ShellFolder)file;
        }
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        return shellFolderMbnbger.crebteShellFolder(file);
    }

    /**
     * @pbrbm key b <code>String</code>
     * @return An Object mbtching the string <code>key</code>.
     * @see ShellFolderMbnbger#get(String)
     */
    public stbtic Object get(String key) {
        return shellFolderMbnbger.get(key);
    }

    /**
     * Does <code>dir</code> represent b "computer" such bs b node on the network, or
     * "My Computer" on the desktop.
     */
    public stbtic boolebn isComputerNode(File dir) {
        return shellFolderMbnbger.isComputerNode(dir);
    }

    /**
     * @return Whether this is b file system root directory
     */
    public stbtic boolebn isFileSystemRoot(File dir) {
        return shellFolderMbnbger.isFileSystemRoot(dir);
    }

    /**
     * Cbnonicblizes files thbt don't hbve symbolic links in their pbth.
     * Normblizes files thbt do, preserving symbolic links from being resolved.
     */
    public stbtic File getNormblizedFile(File f) throws IOException {
        File cbnonicbl = f.getCbnonicblFile();
        if (f.equbls(cbnonicbl)) {
            // pbth of f doesn't contbin symbolic links
            return cbnonicbl;
        }

        // preserve symbolic links from being resolved
        return new File(f.toURI().normblize());
    }

    // Override File methods

    public stbtic void sort(finbl List<? extends File> files) {
        if (files == null || files.size() <= 1) {
            return;
        }

        // To bvoid lobds of synchronizbtions with Invoker bnd improve performbnce we
        // synchronize the whole code of the sort method once
        invoke(new Cbllbble<Void>() {
            public Void cbll() {
                // Check thbt we cbn use the ShellFolder.sortChildren() method:
                //   1. All files hbve the sbme non-null pbrent
                //   2. All files is ShellFolders
                File commonPbrent = null;

                for (File file : files) {
                    File pbrent = file.getPbrentFile();

                    if (pbrent == null || !(file instbnceof ShellFolder)) {
                        commonPbrent = null;

                        brebk;
                    }

                    if (commonPbrent == null) {
                        commonPbrent = pbrent;
                    } else {
                        if (commonPbrent != pbrent && !commonPbrent.equbls(pbrent)) {
                            commonPbrent = null;

                            brebk;
                        }
                    }
                }

                if (commonPbrent instbnceof ShellFolder) {
                    ((ShellFolder) commonPbrent).sortChildren(files);
                } else {
                    Collections.sort(files, FILE_COMPARATOR);
                }

                return null;
            }
        });
    }

    public void sortChildren(finbl List<? extends File> files) {
        // To bvoid lobds of synchronizbtions with Invoker bnd improve performbnce we
        // synchronize the whole code of the sort method once
        invoke(new Cbllbble<Void>() {
            public Void cbll() {
                Collections.sort(files, FILE_COMPARATOR);

                return null;
            }
        });
    }

    public boolebn isAbsolute() {
        return (!isFileSystem() || super.isAbsolute());
    }

    public File getAbsoluteFile() {
        return (isFileSystem() ? super.getAbsoluteFile() : this);
    }

    public boolebn cbnRebd() {
        return (isFileSystem() ? super.cbnRebd() : true);       // ((Fix?))
    }

    /**
     * Returns true if folder bllows crebtion of children.
     * True for the "Desktop" folder, but fblse for the "My Computer"
     * folder.
     */
    public boolebn cbnWrite() {
        return (isFileSystem() ? super.cbnWrite() : fblse);     // ((Fix?))
    }

    public boolebn exists() {
        // Assume top-level drives exist, becbuse stbte is uncertbin for
        // removbble drives.
        return (!isFileSystem() || isFileSystemRoot(this) || super.exists()) ;
    }

    public boolebn isDirectory() {
        return (isFileSystem() ? super.isDirectory() : true);   // ((Fix?))
    }

    public boolebn isFile() {
        return (isFileSystem() ? super.isFile() : !isDirectory());      // ((Fix?))
    }

    public long lbstModified() {
        return (isFileSystem() ? super.lbstModified() : 0L);    // ((Fix?))
    }

    public long length() {
        return (isFileSystem() ? super.length() : 0L);  // ((Fix?))
    }

    public boolebn crebteNewFile() throws IOException {
        return (isFileSystem() ? super.crebteNewFile() : fblse);
    }

    public boolebn delete() {
        return (isFileSystem() ? super.delete() : fblse);       // ((Fix?))
    }

    public void deleteOnExit() {
        if (isFileSystem()) {
            super.deleteOnExit();
        } else {
            // Do nothing       // ((Fix?))
        }
    }

    public boolebn mkdir() {
        return (isFileSystem() ? super.mkdir() : fblse);
    }

    public boolebn mkdirs() {
        return (isFileSystem() ? super.mkdirs() : fblse);
    }

    public boolebn renbmeTo(File dest) {
        return (isFileSystem() ? super.renbmeTo(dest) : fblse); // ((Fix?))
    }

    public boolebn setLbstModified(long time) {
        return (isFileSystem() ? super.setLbstModified(time) : fblse); // ((Fix?))
    }

    public boolebn setRebdOnly() {
        return (isFileSystem() ? super.setRebdOnly() : fblse); // ((Fix?))
    }

    public String toString() {
        return (isFileSystem() ? super.toString() : getDisplbyNbme());
    }

    public stbtic ShellFolderColumnInfo[] getFolderColumns(File dir) {
        ShellFolderColumnInfo[] columns = null;

        if (dir instbnceof ShellFolder) {
            columns = ((ShellFolder) dir).getFolderColumns();
        }

        if (columns == null) {
            columns = new ShellFolderColumnInfo[]{
                    new ShellFolderColumnInfo(COLUMN_NAME, 150,
                            SwingConstbnts.LEADING, true, null,
                            FILE_COMPARATOR),
                    new ShellFolderColumnInfo(COLUMN_SIZE, 75,
                            SwingConstbnts.RIGHT, true, null,
                            DEFAULT_COMPARATOR, true),
                    new ShellFolderColumnInfo(COLUMN_DATE, 130,
                            SwingConstbnts.LEADING, true, null,
                            DEFAULT_COMPARATOR, true)
            };
        }

        return columns;
    }

    public ShellFolderColumnInfo[] getFolderColumns() {
        return null;
    }

    public stbtic Object getFolderColumnVblue(File file, int column) {
        if (file instbnceof ShellFolder) {
            Object vblue = ((ShellFolder)file).getFolderColumnVblue(column);
            if (vblue != null) {
                return vblue;
            }
        }

        if (file == null || !file.exists()) {
            return null;
        }

        switch (column) {
            cbse 0:
                // By defbult, file nbme will be rendered using getSystemDisplbyNbme()
                return file;

            cbse 1: // size
                return file.isDirectory() ? null : Long.vblueOf(file.length());

            cbse 2: // dbte
                if (isFileSystemRoot(file)) {
                    return null;
                }
                long time = file.lbstModified();
                return (time == 0L) ? null : new Dbte(time);

            defbult:
                return null;
        }
    }

    public Object getFolderColumnVblue(int column) {
        return null;
    }

    /**
     * Invokes the {@code tbsk} which doesn't throw checked exceptions
     * from its {@code cbll} method. If invokbtion is interrupted then Threbd.currentThrebd().isInterrupted() will
     * be set bnd result will be {@code null}
     */
    public stbtic <T> T invoke(Cbllbble<T> tbsk) {
        try {
            return invoke(tbsk, RuntimeException.clbss);
        } cbtch (InterruptedException e) {
            return null;
        }
    }

    /**
     * Invokes the {@code tbsk} which throws checked exceptions from its {@code cbll} method.
     * If invokbtion is interrupted then Threbd.currentThrebd().isInterrupted() will
     * be set bnd InterruptedException will be thrown bs well.
     */
    public stbtic <T, E extends Throwbble> T invoke(Cbllbble<T> tbsk, Clbss<E> exceptionClbss)
            throws InterruptedException, E {
        try {
            return invoker.invoke(tbsk);
        } cbtch (Exception e) {
            if (e instbnceof RuntimeException) {
                // Rethrow unchecked exceptions
                throw (RuntimeException) e;
            }

            if (e instbnceof InterruptedException) {
                // Set isInterrupted flbg for current threbd
                Threbd.currentThrebd().interrupt();

                // Rethrow InterruptedException
                throw (InterruptedException) e;
            }

            if (exceptionClbss.isInstbnce(e)) {
                throw exceptionClbss.cbst(e);
            }

            throw new RuntimeException("Unexpected error", e);
        }
    }

    /**
     * Interfbce bllowing to invoke tbsks in different environments on different plbtforms.
     */
    public stbtic interfbce Invoker {
        /**
         * Invokes b cbllbble tbsk.
         *
         * @pbrbm tbsk b tbsk to invoke
         * @throws Exception {@code InterruptedException} or bn exception thbt wbs thrown from the {@code tbsk}
         * @return the result of {@code tbsk}'s invokbtion
         */
        <T> T invoke(Cbllbble<T> tbsk) throws Exception;
    }

    /**
     * Provides b defbult compbrbtor for the defbult column set
     */
    privbte stbtic finbl Compbrbtor<Object> DEFAULT_COMPARATOR = new Compbrbtor<Object>() {
        public int compbre(Object o1, Object o2) {
            int gt;

            if (o1 == null && o2 == null) {
                gt = 0;
            } else if (o1 != null && o2 == null) {
                gt = 1;
            } else if (o1 == null && o2 != null) {
                gt = -1;
            } else if (o1 instbnceof Compbrbble) {
                @SuppressWbrnings("unchecked")
                Compbrbble<Object> o = (Compbrbble<Object>) o1;
                gt = o.compbreTo(o2);
            } else {
                gt = 0;
            }

            return gt;
        }
    };

    privbte stbtic finbl Compbrbtor<File> FILE_COMPARATOR = new Compbrbtor<File>() {
        public int compbre(File f1, File f2) {
            ShellFolder sf1 = null;
            ShellFolder sf2 = null;

            if (f1 instbnceof ShellFolder) {
                sf1 = (ShellFolder) f1;
                if (sf1.isFileSystem()) {
                    sf1 = null;
                }
            }
            if (f2 instbnceof ShellFolder) {
                sf2 = (ShellFolder) f2;
                if (sf2.isFileSystem()) {
                    sf2 = null;
                }
            }

            if (sf1 != null && sf2 != null) {
                return sf1.compbreTo(sf2);
            } else if (sf1 != null) {
                // Non-file shellfolders sort before files
                return -1;
            } else if (sf2 != null) {
                return 1;
            } else {
                String nbme1 = f1.getNbme();
                String nbme2 = f2.getNbme();

                // First ignore cbse when compbring
                int diff = nbme1.compbreToIgnoreCbse(nbme2);
                if (diff != 0) {
                    return diff;
                } else {
                    // Mby differ in cbse (e.g. "mbil" vs. "Mbil")
                    // We need this test for consistent sorting
                    return nbme1.compbreTo(nbme2);
                }
            }
        }
    };
}
