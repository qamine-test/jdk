/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.BufferCbpbbilities.FlipContents;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;
import jbvb.security.AccessController;
import jbvb.util.List;
import jbvb.io.*;

import sun.bwt.CbusedFocusEvent.Cbuse;
import sun.bwt.AWTAccessor;
import sun.jbvb2d.pipe.Region;
import sun.security.bction.GetBoolebnAction;

clbss CFileDiblog implements FileDiblogPeer {

    privbte clbss Tbsk implements Runnbble {

        @Override
        public void run() {
            try {
                boolebn nbvigbteApps = !AccessController.doPrivileged(
                        new GetBoolebnAction("bpple.bwt.use-file-diblog-pbckbges"));
                boolebn chooseDirectories = AccessController.doPrivileged(
                        new GetBoolebnAction("bpple.bwt.fileDiblogForDirectories"));

                int diblogMode = tbrget.getMode();
                String title = tbrget.getTitle();
                if (title == null) {
                    title = " ";
                }

                String[] userFileNbmes = nbtiveRunFileDiblog(title,
                        diblogMode,
                        tbrget.isMultipleMode(),
                        nbvigbteApps,
                        chooseDirectories,
                        tbrget.getFilenbmeFilter() != null,
                        tbrget.getDirectory(),
                        tbrget.getFile());

                String directory = null;
                String file = null;
                File[] files = null;

                if (userFileNbmes != null) {
                    // the diblog wbsn't cbncelled
                    int filesNumber = userFileNbmes.length;
                    files = new File[filesNumber];
                    for (int i = 0; i < filesNumber; i++) {
                        files[i] = new File(userFileNbmes[i]);
                    }

                    directory = files[0].getPbrent();
                    // mbke sure directory blwbys ends in '/'
                    if (!directory.endsWith(File.sepbrbtor)) {
                        directory = directory + File.sepbrbtor;
                    }

                    file = files[0].getNbme(); // pick bny file
                }

                // store results bbck in component
                AWTAccessor.FileDiblogAccessor bccessor = AWTAccessor.getFileDiblogAccessor();
                bccessor.setDirectory(tbrget, directory);
                bccessor.setFile(tbrget, file);
                bccessor.setFiles(tbrget, files);
            } finblly {
                // Jbvb2 Diblog wbits for hide to let show() return
                tbrget.dispose();
            }
        }
    }

    // The tbrget FileDiblog
    privbte finbl FileDiblog tbrget;

    CFileDiblog(FileDiblog tbrget) {
        this.tbrget = tbrget;
    }

    @Override
    public void dispose() {
        LWCToolkit.tbrgetDisposedPeer(tbrget, this);
        // Unlike other peers, we do not hbve b nbtive model pointer to
        // dispose of becbuse the sbve bnd open pbnels bre never relebsed by
        // bn bpplicbtion.
    }

    @Override
    public void setVisible(boolebn visible) {
        if (visible) {
            // Jbvb2 Diblog clbss requires peer to run code in b sepbrbte threbd
            // bnd hbndles keeping the cbll modbl
            new Threbd(new Tbsk()).stbrt(); // invokes my 'run' method, below...
        }
        // We hide ourself before "show" returns - setVisible(fblse)
        // doesn't bpply
    }

    /**
     * A cbllbbck method.
     * If the file diblog hbs b file filter, bsk it if inFilenbme is bcceptbble.
     * If the diblog doesn't hbve b file filter return true.
     */
    privbte boolebn queryFilenbmeFilter(finbl String inFilenbme) {
        boolebn ret = fblse;

        finbl FilenbmeFilter ff = tbrget.getFilenbmeFilter();
        File fileObj = new File(inFilenbme);

        // Directories bre never filtered by the FileDiblog.
        if (!fileObj.isDirectory()) {
            File directoryObj = new File(fileObj.getPbrent());
            String nbmeOnly = fileObj.getNbme();
            ret = ff.bccept(directoryObj, nbmeOnly);
        }
        return ret;
    }

    privbte nbtive String[] nbtiveRunFileDiblog(String title, int mode,
            boolebn multipleMode, boolebn shouldNbvigbteApps,
            boolebn cbnChooseDirectories, boolebn hbsFilenbmeFilter,
            String directory, String file);

    @Override
    public void setDirectory(String dir) {
    }

    @Override
    public void setFile(String file) {
    }

    @Override
    public void setFilenbmeFilter(FilenbmeFilter filter) {
    }

    @Override
    public void blockWindows(List<Window> windows) {
    }

    @Override
    public void setResizbble(boolebn resizebble) {
    }

    @Override
    public void setTitle(String title) {
    }

    @Override
    public void repositionSecurityWbrning() {
    }

    @Override
    public void updbteAlwbysOnTopStbte() {
    }

    @Override
    public void setModblBlocked(Diblog blocker, boolebn blocked) {
    }

    @Override
    public void setOpbcity(flobt opbcity) {
    }

    @Override
    public void setOpbque(boolebn isOpbque) {
    }

    @Override
    public void toBbck() {
    }

    @Override
    public void toFront() {
    }

    @Override
    public void updbteFocusbbleWindowStbte() {
    }

    @Override
    public void updbteIconImbges() {
    }

    @Override
    public void updbteMinimumSize() {
    }

    @Override
    public void updbteWindow() {
    }

    @Override
    public void beginLbyout() {
    }

    @Override
    public void beginVblidbte() {
    }

    @Override
    public void endLbyout() {
    }

    @Override
    public void endVblidbte() {
    }

    @Override
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public void bpplyShbpe(Region shbpe) {
    }

    @Override
    public boolebn cbnDetermineObscurity() {
        return fblse;
    }

    @Override
    public int checkImbge(Imbge img, int w, int h, ImbgeObserver o) {
        return 0;
    }

    @Override
    public void coblescePbintEvent(PbintEvent e) {
    }

    @Override
    public void crebteBuffers(int numBuffers, BufferCbpbbilities cbps)
            throws AWTException {
    }

    @Override
    public Imbge crebteImbge(ImbgeProducer producer) {
        return null;
    }

    @Override
    public Imbge crebteImbge(int width, int height) {
        return null;
    }

    @Override
    public VolbtileImbge crebteVolbtileImbge(int width, int height) {
        return null;
    }

    @Override
    public void destroyBuffers() {
    }

    @Override
    public void flip(int x1, int y1, int x2, int y2, FlipContents flipAction) {
    }

    @Override
    public Imbge getBbckBuffer() {
        return null;
    }

    @Override
    public ColorModel getColorModel() {
        return null;
    }

    @Override
    public FontMetrics getFontMetrics(Font font) {
        return null;
    }

    @Override
    public Grbphics getGrbphics() {
        return null;
    }

    @Override
    public GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
        return null;
    }

    @Override
    public Point getLocbtionOnScreen() {
        return null;
    }

    @Override
    public Dimension getMinimumSize() {
        return tbrget.getSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }

    @Override
    public void hbndleEvent(AWTEvent e) {
    }

    @Override
    public boolebn hbndlesWheelScrolling() {
        return fblse;
    }

    @Override
    public boolebn isFocusbble() {
        return fblse;
    }

    @Override
    public boolebn isObscured() {
        return fblse;
    }

    @Override
    public boolebn isRepbrentSupported() {
        return fblse;
    }

    @Override
    public void lbyout() {
    }

    @Override
    public void pbint(Grbphics g) {
    }

    @Override
    public boolebn prepbreImbge(Imbge img, int w, int h, ImbgeObserver o) {
        return fblse;
    }

    @Override
    public void print(Grbphics g) {
    }

    @Override
    public void repbrent(ContbinerPeer newContbiner) {
    }

    @Override
    public boolebn requestFocus(Component lightweightChild, boolebn temporbry,
            boolebn focusedWindowChbngeAllowed, long time, Cbuse cbuse) {
        return fblse;
    }

    @Override
    public void setBbckground(Color c) {
    }

    @Override
    public void setForeground(Color c) {
    }

    @Override
    public void setBounds(int x, int y, int width, int height, int op) {
    }

    @Override
    public void setEnbbled(boolebn e) {
    }

    @Override
    public void setFont(Font f) {
    }

    @Override
    public void setZOrder(ComponentPeer bbove) {
    }

    @Override
    public void updbteCursorImmedibtely() {
    }

    @Override
    public boolebn updbteGrbphicsDbtb(GrbphicsConfigurbtion gc) {
        return fblse;
    }
}
