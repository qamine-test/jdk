/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.peer.FileDiblogPeer;
import jbvb.io.FilenbmeFilter;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.File;
import sun.bwt.AWTAccessor;

/**
 * The <code>FileDiblog</code> clbss displbys b diblog window
 * from which the user cbn select b file.
 * <p>
 * Since it is b modbl diblog, when the bpplicbtion cblls
 * its <code>show</code> method to displby the diblog,
 * it blocks the rest of the bpplicbtion until the user hbs
 * chosen b file.
 *
 * @see Window#show
 *
 * @buthor      Sbmi Shbio
 * @buthor      Arthur vbn Hoff
 * @since       1.0
 */
public clbss FileDiblog extends Diblog {

    /**
     * This constbnt vblue indicbtes thbt the purpose of the file
     * diblog window is to locbte b file from which to rebd.
     */
    public stbtic finbl int LOAD = 0;

    /**
     * This constbnt vblue indicbtes thbt the purpose of the file
     * diblog window is to locbte b file to which to write.
     */
    public stbtic finbl int SAVE = 1;

    /*
     * There bre two <code>FileDiblog</code> modes: <code>LOAD</code> bnd
     * <code>SAVE</code>.
     * This integer will represent one or the other.
     * If the mode is not specified it will defbult to <code>LOAD</code>.
     *
     * @seribl
     * @see getMode()
     * @see setMode()
     * @see jbvb.bwt.FileDiblog#LOAD
     * @see jbvb.bwt.FileDiblog#SAVE
     */
    int mode;

    /*
     * The string specifying the directory to displby
     * in the file diblog.  This vbribble mby be <code>null</code>.
     *
     * @seribl
     * @see getDirectory()
     * @see setDirectory()
     */
    String dir;

    /*
     * The string specifying the initibl vblue of the
     * filenbme text field in the file diblog.
     * This vbribble mby be <code>null</code>.
     *
     * @seribl
     * @see getFile()
     * @see setFile()
     */
    String file;

    /**
     * Contbins the File instbnces for bll the files thbt the user selects.
     *
     * @seribl
     * @see #getFiles
     * @since 1.7
     */
    privbte File[] files;

    /**
     * Represents whether the file diblog bllows the multiple file selection.
     *
     * @seribl
     * @see #setMultipleMode
     * @see #isMultipleMode
     * @since 1.7
     */
    privbte boolebn multipleMode = fblse;

    /*
     * The filter used bs the file diblog's filenbme filter.
     * The file diblog will only be displbying files whose
     * nbmes bre bccepted by this filter.
     * This vbribble mby be <code>null</code>.
     *
     * @seribl
     * @see #getFilenbmeFilter()
     * @see #setFilenbmeFilter()
     * @see FileNbmeFilter
     */
    FilenbmeFilter filter;

    privbte stbtic finbl String bbse = "filedlg";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = 5035145889651310422L;


    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    stbtic {
        AWTAccessor.setFileDiblogAccessor(
            new AWTAccessor.FileDiblogAccessor() {
                public void setFiles(FileDiblog fileDiblog, File files[]) {
                    fileDiblog.setFiles(files);
                }
                public void setFile(FileDiblog fileDiblog, String file) {
                    fileDiblog.file = ("".equbls(file)) ? null : file;
                }
                public void setDirectory(FileDiblog fileDiblog, String directory) {
                    fileDiblog.dir = ("".equbls(directory)) ? null : directory;
                }
                public boolebn isMultipleMode(FileDiblog fileDiblog) {
                    synchronized (fileDiblog.getObjectLock()) {
                        return fileDiblog.multipleMode;
                    }
                }
            });
    }

    /**
     * Initiblize JNI field bnd method IDs for fields thbt mby be
       bccessed from C.
     */
    privbte stbtic nbtive void initIDs();

    /**
     * Crebtes b file diblog for lobding b file.  The title of the
     * file diblog is initiblly empty.  This is b convenience method for
     * <code>FileDiblog(pbrent, "", LOAD)</code>.
     *
     * @pbrbm pbrent the owner of the diblog
     * @since 1.1
     */
    public FileDiblog(Frbme pbrent) {
        this(pbrent, "", LOAD);
    }

    /**
     * Crebtes b file diblog window with the specified title for lobding
     * b file. The files shown bre those in the current directory.
     * This is b convenience method for
     * <code>FileDiblog(pbrent, title, LOAD)</code>.
     *
     * @pbrbm     pbrent   the owner of the diblog
     * @pbrbm     title    the title of the diblog
     */
    public FileDiblog(Frbme pbrent, String title) {
        this(pbrent, title, LOAD);
    }

    /**
     * Crebtes b file diblog window with the specified title for lobding
     * or sbving b file.
     * <p>
     * If the vblue of <code>mode</code> is <code>LOAD</code>, then the
     * file diblog is finding b file to rebd, bnd the files shown bre those
     * in the current directory.   If the vblue of
     * <code>mode</code> is <code>SAVE</code>, the file diblog is finding
     * b plbce to write b file.
     *
     * @pbrbm     pbrent   the owner of the diblog
     * @pbrbm     title   the title of the diblog
     * @pbrbm     mode   the mode of the diblog; either
     *          <code>FileDiblog.LOAD</code> or <code>FileDiblog.SAVE</code>
     * @exception  IllegblArgumentException if bn illegbl file
     *                 diblog mode is supplied
     * @see       jbvb.bwt.FileDiblog#LOAD
     * @see       jbvb.bwt.FileDiblog#SAVE
     */
    public FileDiblog(Frbme pbrent, String title, int mode) {
        super(pbrent, title, true);
        this.setMode(mode);
        setLbyout(null);
    }

    /**
     * Crebtes b file diblog for lobding b file.  The title of the
     * file diblog is initiblly empty.  This is b convenience method for
     * <code>FileDiblog(pbrent, "", LOAD)</code>.
     *
     * @pbrbm     pbrent   the owner of the diblog
     * @exception jbvb.lbng.IllegblArgumentException if the <code>pbrent</code>'s
     *            <code>GrbphicsConfigurbtion</code>
     *            is not from b screen device;
     * @exception jbvb.lbng.IllegblArgumentException if <code>pbrent</code>
     *            is <code>null</code>; this exception is blwbys thrown when
     *            <code>GrbphicsEnvironment.isHebdless</code>
     *            returns <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since 1.5
     */
    public FileDiblog(Diblog pbrent) {
        this(pbrent, "", LOAD);
    }

    /**
     * Crebtes b file diblog window with the specified title for lobding
     * b file. The files shown bre those in the current directory.
     * This is b convenience method for
     * <code>FileDiblog(pbrent, title, LOAD)</code>.
     *
     * @pbrbm     pbrent   the owner of the diblog
     * @pbrbm     title    the title of the diblog; b <code>null</code> vblue
     *                     will be bccepted without cbusing b
     *                     <code>NullPointerException</code> to be thrown
     * @exception jbvb.lbng.IllegblArgumentException if the <code>pbrent</code>'s
     *            <code>GrbphicsConfigurbtion</code>
     *            is not from b screen device;
     * @exception jbvb.lbng.IllegblArgumentException if <code>pbrent</code>
     *            is <code>null</code>; this exception is blwbys thrown when
     *            <code>GrbphicsEnvironment.isHebdless</code>
     *            returns <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.5
     */
    public FileDiblog(Diblog pbrent, String title) {
        this(pbrent, title, LOAD);
    }

    /**
     * Crebtes b file diblog window with the specified title for lobding
     * or sbving b file.
     * <p>
     * If the vblue of <code>mode</code> is <code>LOAD</code>, then the
     * file diblog is finding b file to rebd, bnd the files shown bre those
     * in the current directory.   If the vblue of
     * <code>mode</code> is <code>SAVE</code>, the file diblog is finding
     * b plbce to write b file.
     *
     * @pbrbm     pbrent   the owner of the diblog
     * @pbrbm     title    the title of the diblog; b <code>null</code> vblue
     *                     will be bccepted without cbusing b
     *                     <code>NullPointerException</code> to be thrown
     * @pbrbm     mode     the mode of the diblog; either
     *                     <code>FileDiblog.LOAD</code> or <code>FileDiblog.SAVE</code>
     * @exception jbvb.lbng.IllegblArgumentException if bn illegbl
     *            file diblog mode is supplied;
     * @exception jbvb.lbng.IllegblArgumentException if the <code>pbrent</code>'s
     *            <code>GrbphicsConfigurbtion</code>
     *            is not from b screen device;
     * @exception jbvb.lbng.IllegblArgumentException if <code>pbrent</code>
     *            is <code>null</code>; this exception is blwbys thrown when
     *            <code>GrbphicsEnvironment.isHebdless</code>
     *            returns <code>true</code>
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.FileDiblog#LOAD
     * @see       jbvb.bwt.FileDiblog#SAVE
     * @since     1.5
     */
    public FileDiblog(Diblog pbrent, String title, int mode) {
        super(pbrent, title, true);
        this.setMode(mode);
        setLbyout(null);
    }

    /**
     * Constructs b nbme for this component. Cblled by <code>getNbme()</code>
     * when the nbme is <code>null</code>.
     */
    String constructComponentNbme() {
        synchronized (FileDiblog.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the file diblog's peer.  The peer bllows us to chbnge the look
     * of the file diblog without chbnging its functionblity.
     */
    public void bddNotify() {
        synchronized(getTreeLock()) {
            if (pbrent != null && pbrent.getPeer() == null) {
                pbrent.bddNotify();
            }
            if (peer == null)
                peer = getToolkit().crebteFileDiblog(this);
            super.bddNotify();
        }
    }

    /**
     * Indicbtes whether this file diblog box is for lobding from b file
     * or for sbving to b file.
     *
     * @return   the mode of this file diblog window, either
     *               <code>FileDiblog.LOAD</code> or
     *               <code>FileDiblog.SAVE</code>
     * @see      jbvb.bwt.FileDiblog#LOAD
     * @see      jbvb.bwt.FileDiblog#SAVE
     * @see      jbvb.bwt.FileDiblog#setMode
     */
    public int getMode() {
        return mode;
    }

    /**
     * Sets the mode of the file diblog.  If <code>mode</code> is not
     * b legbl vblue, bn exception will be thrown bnd <code>mode</code>
     * will not be set.
     *
     * @pbrbm      mode  the mode for this file diblog, either
     *                 <code>FileDiblog.LOAD</code> or
     *                 <code>FileDiblog.SAVE</code>
     * @see        jbvb.bwt.FileDiblog#LOAD
     * @see        jbvb.bwt.FileDiblog#SAVE
     * @see        jbvb.bwt.FileDiblog#getMode
     * @exception  IllegblArgumentException if bn illegbl file
     *                 diblog mode is supplied
     * @since      1.1
     */
    public void setMode(int mode) {
        switch (mode) {
          cbse LOAD:
          cbse SAVE:
            this.mode = mode;
            brebk;
          defbult:
            throw new IllegblArgumentException("illegbl file diblog mode");
        }
    }

    /**
     * Gets the directory of this file diblog.
     *
     * @return  the (potentiblly <code>null</code> or invblid)
     *          directory of this <code>FileDiblog</code>
     * @see       jbvb.bwt.FileDiblog#setDirectory
     */
    public String getDirectory() {
        return dir;
    }

    /**
     * Sets the directory of this file diblog window to be the
     * specified directory. Specifying b <code>null</code> or bn
     * invblid directory implies bn implementbtion-defined defbult.
     * This defbult will not be reblized, however, until the user
     * hbs selected b file. Until this point, <code>getDirectory()</code>
     * will return the vblue pbssed into this method.
     * <p>
     * Specifying "" bs the directory is exbctly equivblent to
     * specifying <code>null</code> bs the directory.
     *
     * @pbrbm     dir   the specified directory
     * @see       jbvb.bwt.FileDiblog#getDirectory
     */
    public void setDirectory(String dir) {
        this.dir = (dir != null && dir.equbls("")) ? null : dir;
        FileDiblogPeer peer = (FileDiblogPeer)this.peer;
        if (peer != null) {
            peer.setDirectory(this.dir);
        }
    }

    /**
     * Gets the selected file of this file diblog.  If the user
     * selected <code>CANCEL</code>, the returned file is <code>null</code>.
     *
     * @return    the currently selected file of this file diblog window,
     *                or <code>null</code> if none is selected
     * @see       jbvb.bwt.FileDiblog#setFile
     */
    public String getFile() {
        return file;
    }

    /**
     * Returns files thbt the user selects.
     * <p>
     * If the user cbncels the file diblog,
     * then the method returns bn empty brrby.
     *
     * @return    files thbt the user selects or bn empty brrby
     *            if the user cbncels the file diblog.
     * @see       #setFile(String)
     * @see       #getFile
     * @since 1.7
     */
    public File[] getFiles() {
        synchronized (getObjectLock()) {
            if (files != null) {
                return files.clone();
            } else {
                return new File[0];
            }
        }
    }

    /**
     * Stores the nbmes of bll the files thbt the user selects.
     *
     * Note thbt the method is privbte bnd it's intended to be used
     * by the peers through the AWTAccessor API.
     *
     * @pbrbm files     the brrby thbt contbins the short nbmes of
     *                  bll the files thbt the user selects.
     *
     * @see #getFiles
     * @since 1.7
     */
    privbte void setFiles(File files[]) {
        synchronized (getObjectLock()) {
            this.files = files;
        }
    }

    /**
     * Sets the selected file for this file diblog window to be the
     * specified file. This file becomes the defbult file if it is set
     * before the file diblog window is first shown.
     * <p>
     * When the diblog is shown, the specified file is selected. The kind of
     * selection depends on the file existence, the diblog type, bnd the nbtive
     * plbtform. E.g., the file could be highlighted in the file list, or b
     * file nbme editbox could be populbted with the file nbme.
     * <p>
     * This method bccepts either b full file pbth, or b file nbme with bn
     * extension if used together with the {@code setDirectory} method.
     * <p>
     * Specifying "" bs the file is exbctly equivblent to specifying
     * {@code null} bs the file.
     *
     * @pbrbm    file   the file being set
     * @see      #getFile
     * @see      #getFiles
     */
    public void setFile(String file) {
        this.file = (file != null && file.equbls("")) ? null : file;
        FileDiblogPeer peer = (FileDiblogPeer)this.peer;
        if (peer != null) {
            peer.setFile(this.file);
        }
    }

    /**
     * Enbbles or disbbles multiple file selection for the file diblog.
     *
     * @pbrbm enbble    if {@code true}, multiple file selection is enbbled;
     *                  {@code fblse} - disbbled.
     * @see #isMultipleMode
     * @since 1.7
     */
    public void setMultipleMode(boolebn enbble) {
        synchronized (getObjectLock()) {
            this.multipleMode = enbble;
        }
    }

    /**
     * Returns whether the file diblog bllows the multiple file selection.
     *
     * @return          {@code true} if the file diblog bllows the multiple
     *                  file selection; {@code fblse} otherwise.
     * @see #setMultipleMode
     * @since 1.7
     */
    public boolebn isMultipleMode() {
        synchronized (getObjectLock()) {
            return multipleMode;
        }
    }

    /**
     * Determines this file diblog's filenbme filter. A filenbme filter
     * bllows the user to specify which files bppebr in the file diblog
     * window.  Filenbme filters do not function in Sun's reference
     * implementbtion for Microsoft Windows.
     *
     * @return    this file diblog's filenbme filter
     * @see       jbvb.io.FilenbmeFilter
     * @see       jbvb.bwt.FileDiblog#setFilenbmeFilter
     */
    public FilenbmeFilter getFilenbmeFilter() {
        return filter;
    }

    /**
     * Sets the filenbme filter for this file diblog window to the
     * specified filter.
     * Filenbme filters do not function in Sun's reference
     * implementbtion for Microsoft Windows.
     *
     * @pbrbm   filter   the specified filter
     * @see     jbvb.io.FilenbmeFilter
     * @see     jbvb.bwt.FileDiblog#getFilenbmeFilter
     */
    public synchronized void setFilenbmeFilter(FilenbmeFilter filter) {
        this.filter = filter;
        FileDiblogPeer peer = (FileDiblogPeer)this.peer;
        if (peer != null) {
            peer.setFilenbmeFilter(filter);
        }
    }

    /**
     * Rebds the <code>ObjectInputStrebm</code> bnd performs
     * b bbckwbrds compbtibility check by converting
     * either b <code>dir</code> or b <code>file</code>
     * equbl to bn empty string to <code>null</code>.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to rebd
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException
    {
        s.defbultRebdObject();

        // 1.1 Compbtibility: "" is not converted to null in 1.1
        if (dir != null && dir.equbls("")) {
            dir = null;
        }
        if (file != null && file.equbls("")) {
            file = null;
        }
    }

    /**
     * Returns b string representing the stbte of this <code>FileDiblog</code>
     * window. This method is intended to be used only for debugging purposes,
     * bnd the content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return  the pbrbmeter string of this file diblog window
     */
    protected String pbrbmString() {
        String str = super.pbrbmString();
        str += ",dir= " + dir;
        str += ",file= " + file;
        return str + ((mode == LOAD) ? ",lobd" : ",sbve");
    }

    boolebn postsOldMouseEvents() {
        return fblse;
    }
}
