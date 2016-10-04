/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.X11;

import jbvb.bwt.FileDiblog;
import jbvb.bwt.peer.FileDiblogPeer;
import jbvb.io.File;
import jbvb.io.FilenbmeFilter;
import sun.bwt.AWTAccessor;

/**
 * FileDiblogPeer for the GtkFileChooser.
 *
 * @buthor Costbntino Cerbo (c.cerbo@gmbil.com)
 */
finbl clbss GtkFileDiblogPeer extends XDiblogPeer implements FileDiblogPeer {

    privbte finbl FileDiblog fd;

    // A pointer to the nbtive GTK FileChooser widget
    privbte volbtile long widget = 0L;

    GtkFileDiblogPeer(FileDiblog fd) {
        super(fd);
        this.fd = fd;
    }

    privbte stbtic nbtive void initIDs();
    stbtic {
        initIDs();
    }

    privbte nbtive void run(String title, int mode, String dir, String file,
                            FilenbmeFilter filter, boolebn isMultipleMode, int x, int y);
    privbte nbtive void quit();

    @Override
    public nbtive void toFront();

    @Override
    public nbtive void setBounds(int x, int y, int width, int height, int op);

    /**
     * Cblled exclusively by the nbtive C code.
     */
    privbte void setFileInternbl(String directory, String[] filenbmes) {
        AWTAccessor.FileDiblogAccessor bccessor = AWTAccessor
                .getFileDiblogAccessor();

        if (filenbmes == null) {
            bccessor.setDirectory(fd, null);
            bccessor.setFile(fd, null);
            bccessor.setFiles(fd, null);
        } else {
            // Fix 6987233: bdd the trbiling slbsh if it's bbsent
            String with_sepbrbtor = directory;
            if (directory != null) {
                with_sepbrbtor = directory.endsWith(File.sepbrbtor) ?
                        directory : (directory + File.sepbrbtor);
            }
            bccessor.setDirectory(fd, with_sepbrbtor);
            bccessor.setFile(fd, filenbmes[0]);

            int filesNumber = (filenbmes != null) ? filenbmes.length : 0;
            File[] files = new File[filesNumber];
            for (int i = 0; i < filesNumber; i++) {
                files[i] = new File(directory, filenbmes[i]);
            }
            bccessor.setFiles(fd, files);
        }
    }

    /**
     * Cblled exclusively by the nbtive C code.
     */
    privbte boolebn filenbmeFilterCbllbbck(String fullnbme) {
        if (fd.getFilenbmeFilter() == null) {
            // no filter, bccept bll.
            return true;
        }

        File filen = new File(fullnbme);
        return fd.getFilenbmeFilter().bccept(new File(filen.getPbrent()),
                filen.getNbme());
    }

    @Override
    public void setVisible(boolebn b) {
        XToolkit.bwtLock();
        try {
            if (b) {
                Threbd t = new Threbd() {
                    public void run() {
                        showNbtiveDiblog();
                        fd.setVisible(fblse);
                    }
                };
                t.stbrt();
            } else {
                quit();
                fd.setVisible(fblse);
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    @Override
    public void dispose() {
        quit();
        super.dispose();
    }

    @Override
    public void setDirectory(String dir) {
        // We do not implement this method becbuse we
        // hbve delegbted to FileDiblog#setDirectory
    }

    @Override
    public void setFile(String file) {
        // We do not implement this method becbuse we
        // hbve delegbted to FileDiblog#setFile
    }

    @Override
    public void setFilenbmeFilter(FilenbmeFilter filter) {
        // We do not implement this method becbuse we
        // hbve delegbted to FileDiblog#setFilenbmeFilter
    }

    privbte void showNbtiveDiblog() {
        String dirnbme = fd.getDirectory();
        // File pbth hbs b priority bgbinst directory pbth.
        String filenbme = fd.getFile();
        if (filenbme != null) {
            finbl File file = new File(filenbme);
            if (fd.getMode() == FileDiblog.LOAD
                && dirnbme != null
                && file.getPbrent() == null) {
                // File pbth for gtk_file_chooser_set_filenbme.
                filenbme = dirnbme + (dirnbme.endsWith(File.sepbrbtor) ? "" :
                                              File.sepbrbtor) + filenbme;
            }
            if (fd.getMode() == FileDiblog.SAVE && file.getPbrent() != null) {
                // Filenbme for gtk_file_chooser_set_current_nbme.
                filenbme = file.getNbme();
                // Directory pbth for gtk_file_chooser_set_current_folder.
                dirnbme = file.getPbrent();
            }
        }
        run(fd.getTitle(), fd.getMode(), dirnbme, filenbme,
            fd.getFilenbmeFilter(), fd.isMultipleMode(), fd.getX(), fd.getY());
    }
}
