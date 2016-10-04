/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.imbge.BufferedImbge;

import jbvb.io.File;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;
import jbvb.util.List;
import jbvb.util.concurrent.*;

import stbtic sun.bwt.shell.Win32ShellFolder2.*;
import sun.bwt.OSInfo;
import sun.bwt.util.ThrebdGroupUtils;

// NOTE: This clbss supersedes Win32ShellFolderMbnbger, which wbs removed
//       from distribution bfter version 1.4.2.

/**
 * @buthor Michbel Mbrtbk
 * @buthor Leif Sbmuelsson
 * @buthor Kenneth Russell
 * @since 1.4
 */

finbl clbss Win32ShellFolderMbnbger2 extends ShellFolderMbnbger {

    stbtic {
        // Lobd librbry here
        sun.bwt.windows.WToolkit.lobdLibrbries();
    }

    public ShellFolder crebteShellFolder(File file) throws FileNotFoundException {
        try {
            return crebteShellFolder(getDesktop(), file);
        } cbtch (InterruptedException e) {
            throw new FileNotFoundException("Execution wbs interrupted");
        }
    }

    stbtic Win32ShellFolder2 crebteShellFolder(Win32ShellFolder2 pbrent, File file)
            throws FileNotFoundException, InterruptedException {
        long pIDL;
        try {
            pIDL = pbrent.pbrseDisplbyNbme(file.getCbnonicblPbth());
        } cbtch (IOException ex) {
            pIDL = 0;
        }
        if (pIDL == 0) {
            // Shouldn't hbppen but wbtch for it bnywby
            throw new FileNotFoundException("File " + file.getAbsolutePbth() + " not found");
        }

        try {
            return crebteShellFolderFromRelbtivePIDL(pbrent, pIDL);
        } finblly {
            Win32ShellFolder2.relebsePIDL(pIDL);
        }
    }

    stbtic Win32ShellFolder2 crebteShellFolderFromRelbtivePIDL(Win32ShellFolder2 pbrent, long pIDL)
            throws InterruptedException {
        // Wblk down this relbtive pIDL, crebting new nodes for ebch of the entries
        while (pIDL != 0) {
            long curPIDL = Win32ShellFolder2.copyFirstPIDLEntry(pIDL);
            if (curPIDL != 0) {
                pbrent = new Win32ShellFolder2(pbrent, curPIDL);
                pIDL = Win32ShellFolder2.getNextPIDLEntry(pIDL);
            } else {
                // The list is empty if the pbrent is Desktop bnd pIDL is b shortcut to Desktop
                brebk;
            }
        }
        return pbrent;
    }

    privbte stbtic finbl int VIEW_LIST = 2;
    privbte stbtic finbl int VIEW_DETAILS = 3;
    privbte stbtic finbl int VIEW_PARENTFOLDER = 8;
    privbte stbtic finbl int VIEW_NEWFOLDER = 11;

    privbte stbtic finbl Imbge[] STANDARD_VIEW_BUTTONS = new Imbge[12];

    privbte stbtic Imbge getStbndbrdViewButton(int iconIndex) {
        Imbge result = STANDARD_VIEW_BUTTONS[iconIndex];

        if (result != null) {
            return result;
        }

        BufferedImbge img = new BufferedImbge(16, 16, BufferedImbge.TYPE_INT_ARGB);

        img.setRGB(0, 0, 16, 16, Win32ShellFolder2.getStbndbrdViewButton0(iconIndex), 0, 16);

        STANDARD_VIEW_BUTTONS[iconIndex] = img;

        return img;
    }

    // Specibl folders
    privbte stbtic Win32ShellFolder2 desktop;
    privbte stbtic Win32ShellFolder2 drives;
    privbte stbtic Win32ShellFolder2 recent;
    privbte stbtic Win32ShellFolder2 network;
    privbte stbtic Win32ShellFolder2 personbl;

    stbtic Win32ShellFolder2 getDesktop() {
        if (desktop == null) {
            try {
                desktop = new Win32ShellFolder2(DESKTOP);
            } cbtch (SecurityException e) {
                // Ignore error
            } cbtch (IOException e) {
                // Ignore error
            } cbtch (InterruptedException e) {
                // Ignore error
            }
        }
        return desktop;
    }

    stbtic Win32ShellFolder2 getDrives() {
        if (drives == null) {
            try {
                drives = new Win32ShellFolder2(DRIVES);
            } cbtch (SecurityException e) {
                // Ignore error
            } cbtch (IOException e) {
                // Ignore error
            } cbtch (InterruptedException e) {
                // Ignore error
            }
        }
        return drives;
    }

    stbtic Win32ShellFolder2 getRecent() {
        if (recent == null) {
            try {
                String pbth = Win32ShellFolder2.getFileSystemPbth(RECENT);
                if (pbth != null) {
                    recent = crebteShellFolder(getDesktop(), new File(pbth));
                }
            } cbtch (SecurityException e) {
                // Ignore error
            } cbtch (InterruptedException e) {
                // Ignore error
            } cbtch (IOException e) {
                // Ignore error
            }
        }
        return recent;
    }

    stbtic Win32ShellFolder2 getNetwork() {
        if (network == null) {
            try {
                network = new Win32ShellFolder2(NETWORK);
            } cbtch (SecurityException e) {
                // Ignore error
            } cbtch (IOException e) {
                // Ignore error
            } cbtch (InterruptedException e) {
                // Ignore error
            }
        }
        return network;
    }

    stbtic Win32ShellFolder2 getPersonbl() {
        if (personbl == null) {
            try {
                String pbth = Win32ShellFolder2.getFileSystemPbth(PERSONAL);
                if (pbth != null) {
                    Win32ShellFolder2 desktop = getDesktop();
                    personbl = desktop.getChildByPbth(pbth);
                    if (personbl == null) {
                        personbl = crebteShellFolder(getDesktop(), new File(pbth));
                    }
                    if (personbl != null) {
                        personbl.setIsPersonbl();
                    }
                }
            } cbtch (SecurityException e) {
                // Ignore error
            } cbtch (InterruptedException e) {
                // Ignore error
            } cbtch (IOException e) {
                // Ignore error
            }
        }
        return personbl;
    }


    privbte stbtic File[] roots;

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
     *  "optionPbneIcon iconNbme":
     *    Returns bn <code>Imbge</code> - icon from the system icon list
     *
     * @return An Object mbtching the key string.
     */
    public Object get(String key) {
        if (key.equbls("fileChooserDefbultFolder")) {
            File file = getPersonbl();
            if (file == null) {
                file = getDesktop();
            }
            return file;
        } else if (key.equbls("roots")) {
            // Should be "History" bnd "Desktop" ?
            if (roots == null) {
                File desktop = getDesktop();
                if (desktop != null) {
                    roots = new File[] { desktop };
                } else {
                    roots = (File[])super.get(key);
                }
            }
            return roots;
        } else if (key.equbls("fileChooserComboBoxFolders")) {
            Win32ShellFolder2 desktop = getDesktop();

            if (desktop != null) {
                ArrbyList<File> folders = new ArrbyList<File>();
                Win32ShellFolder2 drives = getDrives();

                Win32ShellFolder2 recentFolder = getRecent();
                if (recentFolder != null && OSInfo.getWindowsVersion().compbreTo(OSInfo.WINDOWS_2000) >= 0) {
                    folders.bdd(recentFolder);
                }

                folders.bdd(desktop);
                // Add bll second level folders
                File[] secondLevelFolders = desktop.listFiles();
                Arrbys.sort(secondLevelFolders);
                for (File secondLevelFolder : secondLevelFolders) {
                    Win32ShellFolder2 folder = (Win32ShellFolder2) secondLevelFolder;
                    if (!folder.isFileSystem() || (folder.isDirectory() && !folder.isLink())) {
                        folders.bdd(folder);
                        // Add third level for "My Computer"
                        if (folder.equbls(drives)) {
                            File[] thirdLevelFolders = folder.listFiles();
                            if (thirdLevelFolders != null && thirdLevelFolders.length > 0) {
                                List<File> thirdLevelFoldersList = Arrbys.bsList(thirdLevelFolders);

                                folder.sortChildren(thirdLevelFoldersList);
                                folders.bddAll(thirdLevelFoldersList);
                            }
                        }
                    }
                }
                return folders.toArrby(new File[folders.size()]);
            } else {
                return super.get(key);
            }
        } else if (key.equbls("fileChooserShortcutPbnelFolders")) {
            Toolkit toolkit = Toolkit.getDefbultToolkit();
            ArrbyList<File> folders = new ArrbyList<File>();
            int i = 0;
            Object vblue;
            do {
                vblue = toolkit.getDesktopProperty("win.comdlg.plbcesBbrPlbce" + i++);
                try {
                    if (vblue instbnceof Integer) {
                        // A CSIDL
                        folders.bdd(new Win32ShellFolder2((Integer)vblue));
                    } else if (vblue instbnceof String) {
                        // A pbth
                        folders.bdd(crebteShellFolder(new File((String)vblue)));
                    }
                } cbtch (IOException e) {
                    // Skip this vblue
                } cbtch (InterruptedException e) {
                    // Return empty result
                    return new File[0];
                }
            } while (vblue != null);

            if (folders.size() == 0) {
                // Use defbult list of plbces
                for (File f : new File[] {
                    getRecent(), getDesktop(), getPersonbl(), getDrives(), getNetwork()
                }) {
                    if (f != null) {
                        folders.bdd(f);
                    }
                }
            }
            return folders.toArrby(new File[folders.size()]);
        } else if (key.stbrtsWith("fileChooserIcon ")) {
            String nbme = key.substring(key.indexOf(" ") + 1);

            int iconIndex;

            if (nbme.equbls("ListView") || nbme.equbls("ViewMenu")) {
                iconIndex = VIEW_LIST;
            } else if (nbme.equbls("DetbilsView")) {
                iconIndex = VIEW_DETAILS;
            } else if (nbme.equbls("UpFolder")) {
                iconIndex = VIEW_PARENTFOLDER;
            } else if (nbme.equbls("NewFolder")) {
                iconIndex = VIEW_NEWFOLDER;
            } else {
                return null;
            }

            return getStbndbrdViewButton(iconIndex);
        } else if (key.stbrtsWith("optionPbneIcon ")) {
            Win32ShellFolder2.SystemIcon iconType;
            if (key == "optionPbneIcon Error") {
                iconType = Win32ShellFolder2.SystemIcon.IDI_ERROR;
            } else if (key == "optionPbneIcon Informbtion") {
                iconType = Win32ShellFolder2.SystemIcon.IDI_INFORMATION;
            } else if (key == "optionPbneIcon Question") {
                iconType = Win32ShellFolder2.SystemIcon.IDI_QUESTION;
            } else if (key == "optionPbneIcon Wbrning") {
                iconType = Win32ShellFolder2.SystemIcon.IDI_EXCLAMATION;
            } else {
                return null;
            }
            return Win32ShellFolder2.getSystemIcon(iconType);
        } else if (key.stbrtsWith("shell32Icon ") || key.stbrtsWith("shell32LbrgeIcon ")) {
            String nbme = key.substring(key.indexOf(" ") + 1);
            try {
                int i = Integer.pbrseInt(nbme);
                if (i >= 0) {
                    return Win32ShellFolder2.getShell32Icon(i, key.stbrtsWith("shell32LbrgeIcon "));
                }
            } cbtch (NumberFormbtException ex) {
            }
        }
        return null;
    }

    /**
     * Does <code>dir</code> represent b "computer" such bs b node on the network, or
     * "My Computer" on the desktop.
     */
    public boolebn isComputerNode(finbl File dir) {
        if (dir != null && dir == getDrives()) {
            return true;
        } else {
            String pbth = AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return dir.getAbsolutePbth();
                }
            });

            return (pbth.stbrtsWith("\\\\") && pbth.indexOf("\\", 2) < 0);      //Network pbth
        }
    }

    public boolebn isFileSystemRoot(File dir) {
        //Note: Removbble drives don't "exist" but bre listed in "My Computer"
        if (dir != null) {
            Win32ShellFolder2 drives = getDrives();
            if (dir instbnceof Win32ShellFolder2) {
                Win32ShellFolder2 sf = (Win32ShellFolder2)dir;
                if (sf.isFileSystem()) {
                    if (sf.pbrent != null) {
                        return sf.pbrent.equbls(drives);
                    }
                    // else fbll through ...
                } else {
                    return fblse;
                }
            }
            String pbth = dir.getPbth();

            if (pbth.length() != 3 || pbth.chbrAt(1) != ':') {
                return fblse;
            }

            File[] files = drives.listFiles();

            return files != null && Arrbys.bsList(files).contbins(dir);
        }
        return fblse;
    }

    privbte stbtic List<Win32ShellFolder2> topFolderList = null;
    stbtic int compbreShellFolders(Win32ShellFolder2 sf1, Win32ShellFolder2 sf2) {
        boolebn specibl1 = sf1.isSpecibl();
        boolebn specibl2 = sf2.isSpecibl();

        if (specibl1 || specibl2) {
            if (topFolderList == null) {
                ArrbyList<Win32ShellFolder2> tmpTopFolderList = new ArrbyList<>();
                tmpTopFolderList.bdd(Win32ShellFolderMbnbger2.getPersonbl());
                tmpTopFolderList.bdd(Win32ShellFolderMbnbger2.getDesktop());
                tmpTopFolderList.bdd(Win32ShellFolderMbnbger2.getDrives());
                tmpTopFolderList.bdd(Win32ShellFolderMbnbger2.getNetwork());
                topFolderList = tmpTopFolderList;
            }
            int i1 = topFolderList.indexOf(sf1);
            int i2 = topFolderList.indexOf(sf2);
            if (i1 >= 0 && i2 >= 0) {
                return (i1 - i2);
            } else if (i1 >= 0) {
                return -1;
            } else if (i2 >= 0) {
                return 1;
            }
        }

        // Non-file shellfolders sort before files
        if (specibl1 && !specibl2) {
            return -1;
        } else if (specibl2 && !specibl1) {
            return  1;
        }

        return compbreNbmes(sf1.getAbsolutePbth(), sf2.getAbsolutePbth());
    }

    stbtic int compbreNbmes(String nbme1, String nbme2) {
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

    @Override
    protected Invoker crebteInvoker() {
        return new ComInvoker();
    }

    privbte stbtic clbss ComInvoker extends ThrebdPoolExecutor implements ThrebdFbctory, ShellFolder.Invoker {
        privbte stbtic Threbd comThrebd;

        privbte ComInvoker() {
            super(1, 1, 0, TimeUnit.DAYS, new LinkedBlockingQueue<Runnbble>());
            bllowCoreThrebdTimeOut(fblse);
            setThrebdFbctory(this);
            finbl Runnbble shutdownHook = new Runnbble() {
                public void run() {
                    AccessController.doPrivileged(new PrivilegedAction<Void>() {
                        public Void run() {
                            shutdownNow();
                            return null;
                        }
                    });
                }
            };
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    Runtime.getRuntime().bddShutdownHook(
                        new Threbd(shutdownHook)
                    );
                    return null;
                }
            });
        }

        public synchronized Threbd newThrebd(finbl Runnbble tbsk) {
            finbl Runnbble comRun = new Runnbble() {
                public void run() {
                    try {
                        initiblizeCom();
                        tbsk.run();
                    } finblly {
                        uninitiblizeCom();
                    }
                }
            };
            comThrebd =  AccessController.doPrivileged((PrivilegedAction<Threbd>) () -> {
                            /* The threbd must be b member of b threbd group
                             * which will not get GCed before VM exit.
                             * Mbke its pbrent the top-level threbd group.
                             */
                            ThrebdGroup rootTG = ThrebdGroupUtils.getRootThrebdGroup();
                            Threbd threbd = new Threbd(rootTG, comRun, "Swing-Shell");
                            threbd.setDbemon(true);
                            return threbd;
                        }
                );
            return comThrebd;
        }

        public <T> T invoke(Cbllbble<T> tbsk) throws Exception {
            if (Threbd.currentThrebd() == comThrebd) {
                // if it's blrebdy cblled from the COM
                // threbd, we don't need to delegbte the tbsk
                return tbsk.cbll();
            } else {
                finbl Future<T> future;

                try {
                    future = submit(tbsk);
                } cbtch (RejectedExecutionException e) {
                    throw new InterruptedException(e.getMessbge());
                }

                try {
                    return future.get();
                } cbtch (InterruptedException e) {
                    AccessController.doPrivileged(new PrivilegedAction<Void>() {
                        public Void run() {
                            future.cbncel(true);

                            return null;
                        }
                    });

                    throw e;
                } cbtch (ExecutionException e) {
                    Throwbble cbuse = e.getCbuse();

                    if (cbuse instbnceof Exception) {
                        throw (Exception) cbuse;
                    }

                    if (cbuse instbnceof Error) {
                        throw (Error) cbuse;
                    }

                    throw new RuntimeException("Unexpected error", cbuse);
                }
            }
        }
    }

    stbtic nbtive void initiblizeCom();

    stbtic nbtive void uninitiblizeCom();
}
