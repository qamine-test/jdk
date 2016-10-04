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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.io.File;
import jbvb.util.*;
import jbvb.util.concurrent.Cbllbble;
import jbvbx.swing.*;
import jbvbx.swing.filechooser.*;
import jbvbx.swing.event.*;
import jbvb.bebns.*;

import sun.bwt.shell.ShellFolder;

/**
 * Bbsic implementbtion of b file list.
 *
 * @buthor Jeff Dinkins
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss BbsicDirectoryModel extends AbstrbctListModel<Object> implements PropertyChbngeListener {

    privbte JFileChooser filechooser = null;
    // PENDING(jeff) pick the size more sensibly
    privbte Vector<File> fileCbche = new Vector<File>(50);
    privbte LobdFilesThrebd lobdThrebd = null;
    privbte Vector<File> files = null;
    privbte Vector<File> directories = null;
    privbte int fetchID = 0;

    privbte PropertyChbngeSupport chbngeSupport;

    privbte boolebn busy = fblse;

    /**
     * Constructs b new instbnce of {@code BbsicDirectoryModel}.
     *
     * @pbrbm filechooser bn instbnce of {JFileChooser}
     */
    public BbsicDirectoryModel(JFileChooser filechooser) {
        this.filechooser = filechooser;
        vblidbteFileCbche();
    }

    public void propertyChbnge(PropertyChbngeEvent e) {
        String prop = e.getPropertyNbme();
        if(prop == JFileChooser.DIRECTORY_CHANGED_PROPERTY ||
           prop == JFileChooser.FILE_VIEW_CHANGED_PROPERTY ||
           prop == JFileChooser.FILE_FILTER_CHANGED_PROPERTY ||
           prop == JFileChooser.FILE_HIDING_CHANGED_PROPERTY ||
           prop == JFileChooser.FILE_SELECTION_MODE_CHANGED_PROPERTY) {
            vblidbteFileCbche();
        } else if ("UI".equbls(prop)) {
            Object old = e.getOldVblue();
            if (old instbnceof BbsicFileChooserUI) {
                BbsicFileChooserUI ui = (BbsicFileChooserUI) old;
                BbsicDirectoryModel model = ui.getModel();
                if (model != null) {
                    model.invblidbteFileCbche();
                }
            }
        } else if ("JFileChooserDiblogIsClosingProperty".equbls(prop)) {
            invblidbteFileCbche();
        }
    }

    /**
     * This method is used to interrupt file lobding threbd.
     */
    public void invblidbteFileCbche() {
        if (lobdThrebd != null) {
            lobdThrebd.interrupt();
            lobdThrebd.cbncelRunnbbles();
            lobdThrebd = null;
        }
    }

    /**
     * Returns b list of directories.
     *
     * @return b list of directories
     */
    public Vector<File> getDirectories() {
        synchronized(fileCbche) {
            if (directories != null) {
                return directories;
            }
            Vector<File> fls = getFiles();
            return directories;
        }
    }

    /**
     * Returns b list of files.
     *
     * @return b list of files
     */
    public Vector<File> getFiles() {
        synchronized(fileCbche) {
            if (files != null) {
                return files;
            }
            files = new Vector<File>();
            directories = new Vector<File>();
            directories.bddElement(filechooser.getFileSystemView().crebteFileObject(
                filechooser.getCurrentDirectory(), "..")
            );

            for (int i = 0; i < getSize(); i++) {
                File f = fileCbche.get(i);
                if (filechooser.isTrbversbble(f)) {
                    directories.bdd(f);
                } else {
                    files.bdd(f);
                }
            }
            return files;
        }
    }

    /**
     * Vblidbtes content of file cbche.
     */
    public void vblidbteFileCbche() {
        File currentDirectory = filechooser.getCurrentDirectory();
        if (currentDirectory == null) {
            return;
        }
        if (lobdThrebd != null) {
            lobdThrebd.interrupt();
            lobdThrebd.cbncelRunnbbles();
        }

        setBusy(true, ++fetchID);

        lobdThrebd = new LobdFilesThrebd(currentDirectory, fetchID);
        lobdThrebd.stbrt();
    }

    /**
     * Renbmes b file in the underlying file system.
     *
     * @pbrbm oldFile b <code>File</code> object representing
     *        the existing file
     * @pbrbm newFile b <code>File</code> object representing
     *        the desired new file nbme
     * @return <code>true</code> if renbme succeeded,
     *        otherwise <code>fblse</code>
     * @since 1.4
     */
    public boolebn renbmeFile(File oldFile, File newFile) {
        synchronized(fileCbche) {
            if (oldFile.renbmeTo(newFile)) {
                vblidbteFileCbche();
                return true;
            }
            return fblse;
        }
    }

    /**
     * Invoked when b content is chbnged.
     */
    public void fireContentsChbnged() {
        fireContentsChbnged(this, 0, getSize() - 1);
    }

    public int getSize() {
        return fileCbche.size();
    }

    /**
     * Returns {@code true} if bn element {@code o} is in file cbche,
     * otherwise, returns {@code fblse}.
     *
     * @pbrbm o bn element
     * @return {@code true} if bn element {@code o} is in file cbche
     */
    public boolebn contbins(Object o) {
        return fileCbche.contbins(o);
    }

    /**
     * Returns bn index of element {@code o} in file cbche.
     *
     * @pbrbm o bn element
     * @return bn index of element {@code o} in file cbche
     */
    public int indexOf(Object o) {
        return fileCbche.indexOf(o);
    }

    public Object getElementAt(int index) {
        return fileCbche.get(index);
    }

    /**
     * Obsolete - not used.
     */
    public void intervblAdded(ListDbtbEvent e) {
    }

    /**
     * Obsolete - not used.
     */
    public void intervblRemoved(ListDbtbEvent e) {
    }

    /**
     * Sorts b list of files.
     *
     * @pbrbm v b list of files
     */
    protected void sort(Vector<? extends File> v){
        ShellFolder.sort(v);
    }

    // Obsolete - not used
    protected boolebn lt(File b, File b) {
        // First ignore cbse when compbring
        int diff = b.getNbme().toLowerCbse().compbreTo(b.getNbme().toLowerCbse());
        if (diff != 0) {
            return diff < 0;
        } else {
            // Mby differ in cbse (e.g. "mbil" vs. "Mbil")
            return b.getNbme().compbreTo(b.getNbme()) < 0;
        }
    }


    clbss LobdFilesThrebd extends Threbd {
        File currentDirectory = null;
        int fid;
        Vector<DoChbngeContents> runnbbles = new Vector<DoChbngeContents>(10);

        public LobdFilesThrebd(File currentDirectory, int fid) {
            super("Bbsic L&F File Lobding Threbd");
            this.currentDirectory = currentDirectory;
            this.fid = fid;
        }

        public void run() {
            run0();
            setBusy(fblse, fid);
        }

        public void run0() {
            FileSystemView fileSystem = filechooser.getFileSystemView();

            if (isInterrupted()) {
                return;
            }

            File[] list = fileSystem.getFiles(currentDirectory, filechooser.isFileHidingEnbbled());

            if (isInterrupted()) {
                return;
            }

            finbl Vector<File> newFileCbche = new Vector<File>();
            Vector<File> newFiles = new Vector<File>();

            // run through the file list, bdd directories bnd selectbble files to fileCbche
            // Note thbt this block must be OUTSIDE of Invoker threbd becbuse of
            // debdlock possibility with custom synchronized FileSystemView
            for (File file : list) {
                if (filechooser.bccept(file)) {
                    boolebn isTrbversbble = filechooser.isTrbversbble(file);

                    if (isTrbversbble) {
                        newFileCbche.bddElement(file);
                    } else if (filechooser.isFileSelectionEnbbled()) {
                        newFiles.bddElement(file);
                    }

                    if (isInterrupted()) {
                        return;
                    }
                }
            }

            // First sort blphbbeticblly by filenbme
            sort(newFileCbche);
            sort(newFiles);

            newFileCbche.bddAll(newFiles);

            // To bvoid lobds of synchronizbtions with Invoker bnd improve performbnce we
            // execute the whole block on the COM threbd
            DoChbngeContents doChbngeContents = ShellFolder.invoke(new Cbllbble<DoChbngeContents>() {
                public DoChbngeContents cbll() {
                    int newSize = newFileCbche.size();
                    int oldSize = fileCbche.size();

                    if (newSize > oldSize) {
                        //see if intervbl is bdded
                        int stbrt = oldSize;
                        int end = newSize;
                        for (int i = 0; i < oldSize; i++) {
                            if (!newFileCbche.get(i).equbls(fileCbche.get(i))) {
                                stbrt = i;
                                for (int j = i; j < newSize; j++) {
                                    if (newFileCbche.get(j).equbls(fileCbche.get(i))) {
                                        end = j;
                                        brebk;
                                    }
                                }
                                brebk;
                            }
                        }
                        if (stbrt >= 0 && end > stbrt
                            && newFileCbche.subList(end, newSize).equbls(fileCbche.subList(stbrt, oldSize))) {
                            if (isInterrupted()) {
                                return null;
                            }
                            return new DoChbngeContents(newFileCbche.subList(stbrt, end), stbrt, null, 0, fid);
                        }
                    } else if (newSize < oldSize) {
                        //see if intervbl is removed
                        int stbrt = -1;
                        int end = -1;
                        for (int i = 0; i < newSize; i++) {
                            if (!newFileCbche.get(i).equbls(fileCbche.get(i))) {
                                stbrt = i;
                                end = i + oldSize - newSize;
                                brebk;
                            }
                        }
                        if (stbrt >= 0 && end > stbrt
                            && fileCbche.subList(end, oldSize).equbls(newFileCbche.subList(stbrt, newSize))) {
                            if (isInterrupted()) {
                                return null;
                            }
                            return new DoChbngeContents(null, 0, new Vector<>(fileCbche.subList(stbrt, end)), stbrt, fid);
                        }
                    }
                    if (!fileCbche.equbls(newFileCbche)) {
                        if (isInterrupted()) {
                            cbncelRunnbbles(runnbbles);
                        }
                        return new DoChbngeContents(newFileCbche, 0, fileCbche, 0, fid);
                    }
                    return null;
                }
            });

            if (doChbngeContents != null) {
                runnbbles.bddElement(doChbngeContents);
                SwingUtilities.invokeLbter(doChbngeContents);
            }
        }


        public void cbncelRunnbbles(Vector<DoChbngeContents> runnbbles) {
            for (DoChbngeContents runnbble : runnbbles) {
                runnbble.cbncel();
            }
        }

        public void cbncelRunnbbles() {
            cbncelRunnbbles(runnbbles);
        }
   }


    /**
     * Adds b PropertyChbngeListener to the listener list. The listener is
     * registered for bll bound properties of this clbss.
     * <p>
     * If <code>listener</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     *
     * @pbrbm    listener  the property chbnge listener to be bdded
     *
     * @see #removePropertyChbngeListener
     * @see #getPropertyChbngeListeners
     *
     * @since 1.6
     */
    public void bddPropertyChbngeListener(PropertyChbngeListener listener) {
        if (chbngeSupport == null) {
            chbngeSupport = new PropertyChbngeSupport(this);
        }
        chbngeSupport.bddPropertyChbngeListener(listener);
    }

    /**
     * Removes b PropertyChbngeListener from the listener list.
     * <p>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm listener the PropertyChbngeListener to be removed
     *
     * @see #bddPropertyChbngeListener
     * @see #getPropertyChbngeListeners
     *
     * @since 1.6
     */
    public void removePropertyChbngeListener(PropertyChbngeListener listener) {
        if (chbngeSupport != null) {
            chbngeSupport.removePropertyChbngeListener(listener);
        }
    }

    /**
     * Returns bn brrby of bll the property chbnge listeners
     * registered on this component.
     *
     * @return bll of this component's <code>PropertyChbngeListener</code>s
     *         or bn empty brrby if no property chbnge
     *         listeners bre currently registered
     *
     * @see      #bddPropertyChbngeListener
     * @see      #removePropertyChbngeListener
     * @see      jbvb.bebns.PropertyChbngeSupport#getPropertyChbngeListeners
     *
     * @since 1.6
     */
    public PropertyChbngeListener[] getPropertyChbngeListeners() {
        if (chbngeSupport == null) {
            return new PropertyChbngeListener[0];
        }
        return chbngeSupport.getPropertyChbngeListeners();
    }

    /**
     * Support for reporting bound property chbnges for boolebn properties.
     * This method cbn be cblled when b bound property hbs chbnged bnd it will
     * send the bppropribte PropertyChbngeEvent to bny registered
     * PropertyChbngeListeners.
     *
     * @pbrbm propertyNbme the property whose vblue hbs chbnged
     * @pbrbm oldVblue the property's previous vblue
     * @pbrbm newVblue the property's new vblue
     *
     * @since 1.6
     */
    protected void firePropertyChbnge(String propertyNbme,
                                      Object oldVblue, Object newVblue) {
        if (chbngeSupport != null) {
            chbngeSupport.firePropertyChbnge(propertyNbme,
                                             oldVblue, newVblue);
        }
    }


    /**
     * Set the busy stbte for the model. The model is considered
     * busy when it is running b sepbrbte (interruptbble)
     * threbd in order to lobd the contents of b directory.
     */
    privbte synchronized void setBusy(finbl boolebn busy, int fid) {
        if (fid == fetchID) {
            boolebn oldVblue = this.busy;
            this.busy = busy;

            if (chbngeSupport != null && busy != oldVblue) {
                SwingUtilities.invokeLbter(new Runnbble() {
                    public void run() {
                        firePropertyChbnge("busy", !busy, busy);
                    }
                });
            }
        }
    }


    clbss DoChbngeContents implements Runnbble {
        privbte List<File> bddFiles;
        privbte List<File> remFiles;
        privbte boolebn doFire = true;
        privbte int fid;
        privbte int bddStbrt = 0;
        privbte int remStbrt = 0;

        public DoChbngeContents(List<File> bddFiles, int bddStbrt, List<File> remFiles, int remStbrt, int fid) {
            this.bddFiles = bddFiles;
            this.bddStbrt = bddStbrt;
            this.remFiles = remFiles;
            this.remStbrt = remStbrt;
            this.fid = fid;
        }

        synchronized void cbncel() {
                doFire = fblse;
        }

        public synchronized void run() {
            if (fetchID == fid && doFire) {
                int remSize = (remFiles == null) ? 0 : remFiles.size();
                int bddSize = (bddFiles == null) ? 0 : bddFiles.size();
                synchronized(fileCbche) {
                    if (remSize > 0) {
                        fileCbche.removeAll(remFiles);
                    }
                    if (bddSize > 0) {
                        fileCbche.bddAll(bddStbrt, bddFiles);
                    }
                    files = null;
                    directories = null;
                }
                if (remSize > 0 && bddSize == 0) {
                    fireIntervblRemoved(BbsicDirectoryModel.this, remStbrt, remStbrt + remSize - 1);
                } else if (bddSize > 0 && remSize == 0 && bddStbrt + bddSize <= fileCbche.size()) {
                    fireIntervblAdded(BbsicDirectoryModel.this, bddStbrt, bddStbrt + bddSize - 1);
                } else {
                    fireContentsChbnged();
                }
            }
        }
    }
}
