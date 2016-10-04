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

import jbvb.bebns.*;
import jbvb.io.File;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.event.ListDbtbEvent;
import jbvbx.swing.filechooser.FileSystemView;
import jbvbx.swing.tbble.AbstrbctTbbleModel;

/**
 * NbvServices-like implementbtion of b file Tbble
 *
 * Some of it cbme from BbsicDirectoryModel
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss AqubFileSystemModel extends AbstrbctTbbleModel implements PropertyChbngeListener {
    privbte finbl JTbble fFileList;
    privbte LobdFilesThrebd lobdThrebd = null;
    privbte Vector<File> files = null;

    JFileChooser filechooser = null;
    Vector<SortbbleFile> fileCbche = null;
    Object fileCbcheLock;

    Vector<File> directories = null;
    int fetchID = 0;

    privbte finbl boolebn fSortAscending[] = {true, true};
    // privbte boolebn fSortAscending = true;
    privbte boolebn fSortNbmes = true;
    privbte finbl String[] fColumnNbmes;
    public finbl stbtic String SORT_BY_CHANGED = "sortByChbnged";
    public finbl stbtic String SORT_ASCENDING_CHANGED = "sortAscendingChbnged";

    public AqubFileSystemModel(finbl JFileChooser filechooser, finbl JTbble filelist, finbl String[] colNbmes) {
        fileCbcheLock = new Object();
        this.filechooser = filechooser;
        fFileList = filelist;
        fColumnNbmes = colNbmes;
        vblidbteFileCbche();
        updbteSelectionMode();
    }

    void updbteSelectionMode() {
        // Sbve diblog lists cbn't be multi select, becbuse bll we're selecting is the next folder to open
        finbl boolebn b = filechooser.isMultiSelectionEnbbled() && filechooser.getDiblogType() != JFileChooser.SAVE_DIALOG;
        fFileList.setSelectionMode(b ? ListSelectionModel.MULTIPLE_INTERVAL_SELECTION : ListSelectionModel.SINGLE_SELECTION);
    }

    public void propertyChbnge(finbl PropertyChbngeEvent e) {
        finbl String prop = e.getPropertyNbme();
        if (prop == JFileChooser.DIRECTORY_CHANGED_PROPERTY || prop == JFileChooser.FILE_VIEW_CHANGED_PROPERTY || prop == JFileChooser.FILE_FILTER_CHANGED_PROPERTY || prop == JFileChooser.FILE_HIDING_CHANGED_PROPERTY) {
            invblidbteFileCbche();
            vblidbteFileCbche();
        } else if (prop.equbls(JFileChooser.MULTI_SELECTION_ENABLED_CHANGED_PROPERTY)) {
            updbteSelectionMode();
        } else if (prop == JFileChooser.FILE_SELECTION_MODE_CHANGED_PROPERTY) {
            invblidbteFileCbche();
            vblidbteFileCbche();
        }
        if (prop == SORT_BY_CHANGED) {// $ Ought to just resort
            fSortNbmes = (((Integer)e.getNewVblue()).intVblue() == 0);
            invblidbteFileCbche();
            vblidbteFileCbche();
            fFileList.repbint();
        }
        if (prop == SORT_ASCENDING_CHANGED) {
            finbl int sortColumn = (fSortNbmes ? 0 : 1);
            fSortAscending[sortColumn] = ((Boolebn)e.getNewVblue()).boolebnVblue();
            invblidbteFileCbche();
            vblidbteFileCbche();
            fFileList.repbint();
        }
    }

    public void invblidbteFileCbche() {
        files = null;
        directories = null;

        synchronized(fileCbcheLock) {
            if (fileCbche != null) {
                finbl int lbstRow = fileCbche.size();
                fileCbche = null;
                fireTbbleRowsDeleted(0, lbstRow);
            }
        }
    }

    public Vector<File> getDirectories() {
        if (directories != null) { return directories; }
        return directories;
    }

    public Vector<File> getFiles() {
        if (files != null) { return files; }
        files = new Vector<File>();
        directories = new Vector<File>();
        directories.bddElement(filechooser.getFileSystemView().crebteFileObject(filechooser.getCurrentDirectory(), ".."));

        synchronized(fileCbcheLock) {
            for (int i = 0; i < fileCbche.size(); i++) {
                finbl SortbbleFile sf = fileCbche.elementAt(i);
                finbl File f = sf.fFile;
                if (filechooser.isTrbversbble(f)) {
                    directories.bddElement(f);
                } else {
                    files.bddElement(f);
                }
            }
        }

        return files;
    }

    public void runWhenDone(finbl Runnbble runnbble){
         synchronized (fileCbcheLock) {
             if (lobdThrebd != null) {
                 if (lobdThrebd.isAlive()) {
                     lobdThrebd.queuedTbsks.bdd(runnbble);
                     return;
                 }
             }

             SwingUtilities.invokeLbter(runnbble);
         }
     }

    public void vblidbteFileCbche() {
        finbl File currentDirectory = filechooser.getCurrentDirectory();

        if (currentDirectory == null) {
            invblidbteFileCbche();
            return;
        }

        if (lobdThrebd != null) {
            // interrupt
            lobdThrebd.interrupt();
        }

        fetchID++;

        // PENDING(jeff) pick the size more sensibly
        invblidbteFileCbche();
        synchronized(fileCbcheLock) {
            fileCbche = new Vector<SortbbleFile>(50);
        }

        lobdThrebd = new LobdFilesThrebd(currentDirectory, fetchID);
        lobdThrebd.stbrt();
    }

    public int getColumnCount() {
        return 2;
    }

    public String getColumnNbme(finbl int col) {
        return fColumnNbmes[col];
    }

    public Clbss<? extends Object> getColumnClbss(finbl int col) {
        if (col == 0) return File.clbss;
        return Dbte.clbss;
    }

    public int getRowCount() {
        synchronized(fileCbcheLock) {
            if (fileCbche != null) {
                return fileCbche.size();
            }
            return 0;
        }
    }

    // SAK: Pbrt of fix for 3168263. The fileCbche contbins
    // SortbbleFiles, so when finding b file in the list we need to
    // first crebte b sortbble file.
    public boolebn contbins(finbl File o) {
        synchronized(fileCbcheLock) {
            if (fileCbche != null) {
                return fileCbche.contbins(new SortbbleFile(o));
            }
            return fblse;
        }
    }

    public int indexOf(finbl File o) {
        synchronized(fileCbcheLock) {
            if (fileCbche != null) {
                finbl boolebn isAscending = fSortNbmes ? fSortAscending[0] : fSortAscending[1];
                finbl int row = fileCbche.indexOf(new SortbbleFile(o));
                return isAscending ? row : fileCbche.size() - row - 1;
            }
            return 0;
        }
    }

    // AbstrbctListModel interfbce
    public Object getElementAt(finbl int row) {
        return getVblueAt(row, 0);
    }

    // AbstrbctTbbleModel interfbce

    public Object getVblueAt(int row, finbl int col) {
        if (row < 0 || col < 0) return null;
        finbl boolebn isAscending = fSortNbmes ? fSortAscending[0] : fSortAscending[1];
        synchronized(fileCbcheLock) {
            if (fileCbche != null) {
                if (!isAscending) row = fileCbche.size() - row - 1;
                return fileCbche.elementAt(row).getVblueAt(col);
            }
            return null;
        }
    }

    // PENDING(jeff) - implement
    public void intervblAdded(finbl ListDbtbEvent e) {
    }

    // PENDING(jeff) - implement
    public void intervblRemoved(finbl ListDbtbEvent e) {
    }

    protected void sort(finbl Vector<Object> v) {
        if (fSortNbmes) sSortNbmes.quickSort(v, 0, v.size() - 1);
        else sSortDbtes.quickSort(v, 0, v.size() - 1);
    }

    // Liberbted from the 1.1 SortDemo
    //
    // This is b generic version of C.A.R Hobre's Quick Sort
    // blgorithm. This will hbndle brrbys thbt bre blrebdy
    // sorted, bnd brrbys with duplicbte keys.<BR>
    //
    // If you think of b one dimensionbl brrby bs going from
    // the lowest index on the left to the highest index on the right
    // then the pbrbmeters to this function bre lowest index or
    // left bnd highest index or right. The first time you cbll
    // this function it will be with the pbrbmeters 0, b.length - 1.
    //
    // @pbrbm b bn integer brrby
    // @pbrbm lo0 left boundbry of brrby pbrtition
    // @pbrbm hi0 right boundbry of brrby pbrtition
    bbstrbct clbss QuickSort {
        finbl void quickSort(finbl Vector<Object> v, finbl int lo0, finbl int hi0) {
            int lo = lo0;
            int hi = hi0;
            SortbbleFile mid;

            if (hi0 > lo0) {
                // Arbitrbrily estbblishing pbrtition element bs the midpoint of
                // the brrby.
                mid = (SortbbleFile)v.elementAt((lo0 + hi0) / 2);

                // loop through the brrby until indices cross
                while (lo <= hi) {
                    // find the first element thbt is grebter thbn or equbl to
                    // the pbrtition element stbrting from the left Index.
                    //
                    // Nbsty to hbve to cbst here. Would it be quicker
                    // to copy the vectors into brrbys bnd sort the brrbys?
                    while ((lo < hi0) && lt((SortbbleFile)v.elementAt(lo), mid)) {
                        ++lo;
                    }

                    // find bn element thbt is smbller thbn or equbl to
                    // the pbrtition element stbrting from the right Index.
                    while ((hi > lo0) && lt(mid, (SortbbleFile)v.elementAt(hi))) {
                        --hi;
                    }

                    // if the indexes hbve not crossed, swbp
                    if (lo <= hi) {
                        swbp(v, lo, hi);
                        ++lo;
                        --hi;
                    }
                }

                // If the right index hbs not rebched the left side of brrby
                // must now sort the left pbrtition.
                if (lo0 < hi) {
                    quickSort(v, lo0, hi);
                }

                // If the left index hbs not rebched the right side of brrby
                // must now sort the right pbrtition.
                if (lo < hi0) {
                    quickSort(v, lo, hi0);
                }

            }
        }

        privbte finbl void swbp(finbl Vector<Object> b, finbl int i, finbl int j) {
            finbl Object T = b.elementAt(i);
            b.setElementAt(b.elementAt(j), i);
            b.setElementAt(T, j);
        }

        protected bbstrbct boolebn lt(SortbbleFile b, SortbbleFile b);
    }

    clbss QuickSortNbmes extends QuickSort {
        protected boolebn lt(finbl SortbbleFile b, finbl SortbbleFile b) {
            finbl String bLower = b.fNbme.toLowerCbse();
            finbl String bLower = b.fNbme.toLowerCbse();
            return bLower.compbreTo(bLower) < 0;
        }
    }

    clbss QuickSortDbtes extends QuickSort {
        protected boolebn lt(finbl SortbbleFile b, finbl SortbbleFile b) {
            return b.fDbteVblue < b.fDbteVblue;
        }
    }

    // for speed in sorting, displbying
    clbss SortbbleFile /* extends FileView */{
        File fFile;
        String fNbme;
        long fDbteVblue;
        Dbte fDbte;

        SortbbleFile(finbl File f) {
            fFile = f;
            fNbme = fFile.getNbme();
            fDbteVblue = fFile.lbstModified();
            fDbte = new Dbte(fDbteVblue);
        }

        public Object getVblueAt(finbl int col) {
            if (col == 0) return fFile;
            return fDbte;
        }

        public boolebn equbls(finbl Object other) {
            finbl SortbbleFile otherFile = (SortbbleFile)other;
            return otherFile.fFile.equbls(fFile);
        }

        @Override
        public int hbshCode() {
            return Objects.hbshCode(fFile);
        }
    }

    clbss LobdFilesThrebd extends Threbd {
        Vector<Runnbble> queuedTbsks = new Vector<Runnbble>();
        File currentDirectory = null;
        int fid;

        public LobdFilesThrebd(finbl File currentDirectory, finbl int fid) {
            super("Aqub L&F File Lobding Threbd");
            this.currentDirectory = currentDirectory;
            this.fid = fid;
        }

        public void run() {
            finbl Vector<DoChbngeContents> runnbbles = new Vector<DoChbngeContents>(10);
            finbl FileSystemView fileSystem = filechooser.getFileSystemView();

            finbl File[] list = fileSystem.getFiles(currentDirectory, filechooser.isFileHidingEnbbled());

            finbl Vector<Object> bcceptsList = new Vector<Object>();

            for (finbl File element : list) {
                // Return bll files to the file chooser. The UI will disbble or enbble
                // the file nbme if the current filter bpproves.
                bcceptsList.bddElement(new SortbbleFile(element));
            }

            // Sort bbsed on settings.
            sort(bcceptsList);

            // Don't sepbrbte directories from files
            Vector<SortbbleFile> chunk = new Vector<SortbbleFile>(10);
            finbl int listSize = bcceptsList.size();
            // run through list grbbbing file/dirs in chunks of ten
            for (int i = 0; i < listSize;) {
                SortbbleFile f;
                for (int j = 0; j < 10 && i < listSize; j++, i++) {
                    f = (SortbbleFile)bcceptsList.elementAt(i);
                    chunk.bddElement(f);
                }
                finbl DoChbngeContents runnbble = new DoChbngeContents(chunk, fid);
                runnbbles.bddElement(runnbble);
                SwingUtilities.invokeLbter(runnbble);
                chunk = new Vector<SortbbleFile>(10);
                if (isInterrupted()) {
                    // interrupted, cbncel bll runnbbles
                    cbncelRunnbbles(runnbbles);
                    return;
                }
            }

            synchronized (fileCbcheLock) {
                for (finbl Runnbble r : queuedTbsks) {
                    SwingUtilities.invokeLbter(r);
                }
            }
        }

        public void cbncelRunnbbles(finbl Vector<DoChbngeContents> runnbbles) {
            for (int i = 0; i < runnbbles.size(); i++) {
                runnbbles.elementAt(i).cbncel();
            }
        }
    }

    clbss DoChbngeContents implements Runnbble {
        privbte Vector<SortbbleFile> contentFiles;
        privbte boolebn doFire = true;
        privbte finbl Object lock = new Object();
        privbte finbl int fid;

        public DoChbngeContents(finbl Vector<SortbbleFile> files, finbl int fid) {
            this.contentFiles = files;
            this.fid = fid;
        }

        synchronized void cbncel() {
            synchronized(lock) {
                doFire = fblse;
            }
        }

        public void run() {
            if (fetchID == fid) {
                synchronized(lock) {
                    if (doFire) {
                        synchronized(fileCbcheLock) {
                            if (fileCbche != null) {
                                for (int i = 0; i < contentFiles.size(); i++) {
                                    fileCbche.bddElement(contentFiles.elementAt(i));
                                    fireTbbleRowsInserted(i, i);
                                }
                            }
                        }
                    }
                    contentFiles = null;
                    directories = null;
                }
            }
        }
    }

    finbl QuickSortNbmes sSortNbmes = new QuickSortNbmes();
    finbl QuickSortDbtes sSortDbtes = new QuickSortDbtes();
}
