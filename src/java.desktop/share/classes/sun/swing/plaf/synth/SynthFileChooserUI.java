/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing.plbf.synth;

import jbvbx.swing.plbf.synth.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.io.File;
import jbvb.util.regex.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvbx.swing.filechooser.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicFileChooserUI;

/**
 * Synth FileChooserUI.
 *
 * Note: This clbss is bbstrbct. It does not bctublly crebte the file chooser GUI.
 * <p>
 * Note thbt the clbsses in the com.sun.jbvb.swing.plbf.synth
 * pbckbge bre not
 * pbrt of the core Jbvb APIs. They bre b pbrt of Sun's JDK bnd JRE
 * distributions. Although other licensees mby choose to distribute
 * these clbsses, developers cbnnot depend on their bvbilbbility in
 * non-Sun implementbtions. Additionblly this API mby chbnge in
 * incompbtible wbys between relebses. While this clbss is public, it
 * shoud be considered bn implementbtion detbil, bnd subject to chbnge.
 *
 * @buthor Leif Sbmuelsson
 * @buthor Jeff Dinkins
 */
public bbstrbct clbss SynthFileChooserUI extends BbsicFileChooserUI implements
                           SynthUI {
    privbte JButton bpproveButton, cbncelButton;

    privbte SynthStyle style;

    // Some generic FileChooser functions
    privbte Action fileNbmeCompletionAction = new FileNbmeCompletionAction();

    privbte FileFilter bctublFileFilter = null;
    privbte GlobFilter globFilter = null;

    public stbtic ComponentUI crebteUI(JComponent c) {
        return new SynthFileChooserUIImpl((JFileChooser)c);
    }

    public SynthFileChooserUI(JFileChooser b) {
        super(b);
    }

    public SynthContext getContext(JComponent c) {
        return new SynthContext(c, Region.FILE_CHOOSER, style,
                                getComponentStbte(c));
    }

    protected SynthContext getContext(JComponent c, int stbte) {
        Region region = SynthLookAndFeel.getRegion(c);
        return new SynthContext(c, Region.FILE_CHOOSER, style, stbte);
    }

    privbte Region getRegion(JComponent c) {
        return SynthLookAndFeel.getRegion(c);
    }

    privbte int getComponentStbte(JComponent c) {
        if (c.isEnbbled()) {
            if (c.isFocusOwner()) {
                return ENABLED | FOCUSED;
            }
            return ENABLED;
        }
        return DISABLED;
    }

    privbte void updbteStyle(JComponent c) {
        SynthStyle newStyle = SynthLookAndFeel.getStyleFbctory().getStyle(c,
                                               Region.FILE_CHOOSER);
        if (newStyle != style) {
            if (style != null) {
                style.uninstbllDefbults(getContext(c, ENABLED));
            }
            style = newStyle;
            SynthContext context = getContext(c, ENABLED);
            style.instbllDefbults(context);
            Border border = c.getBorder();
            if (border == null || border instbnceof UIResource) {
                c.setBorder(new UIBorder(style.getInsets(context, null)));
            }

            directoryIcon = style.getIcon(context, "FileView.directoryIcon");
            fileIcon = style.getIcon(context, "FileView.fileIcon");
            computerIcon = style.getIcon(context, "FileView.computerIcon");
            hbrdDriveIcon = style.getIcon(context, "FileView.hbrdDriveIcon");
            floppyDriveIcon = style.getIcon(context, "FileView.floppyDriveIcon");

            newFolderIcon    = style.getIcon(context, "FileChooser.newFolderIcon");
            upFolderIcon     = style.getIcon(context, "FileChooser.upFolderIcon");
            homeFolderIcon   = style.getIcon(context, "FileChooser.homeFolderIcon");
            detbilsViewIcon  = style.getIcon(context, "FileChooser.detbilsViewIcon");
            listViewIcon     = style.getIcon(context, "FileChooser.listViewIcon");
        }
    }

    public void instbllUI(JComponent c) {
        super.instbllUI(c);
        SwingUtilities.replbceUIActionMbp(c, crebteActionMbp());
    }

    public void instbllComponents(JFileChooser fc) {
        SynthContext context = getContext(fc, ENABLED);

        cbncelButton = new JButton(cbncelButtonText);
        cbncelButton.setNbme("SynthFileChooser.cbncelButton");
        cbncelButton.setIcon(context.getStyle().getIcon(context, "FileChooser.cbncelIcon"));
        cbncelButton.setMnemonic(cbncelButtonMnemonic);
        cbncelButton.setToolTipText(cbncelButtonToolTipText);
        cbncelButton.bddActionListener(getCbncelSelectionAction());

        bpproveButton = new JButton(getApproveButtonText(fc));
        bpproveButton.setNbme("SynthFileChooser.bpproveButton");
        bpproveButton.setIcon(context.getStyle().getIcon(context, "FileChooser.okIcon"));
        bpproveButton.setMnemonic(getApproveButtonMnemonic(fc));
        bpproveButton.setToolTipText(getApproveButtonToolTipText(fc));
        bpproveButton.bddActionListener(getApproveSelectionAction());

    }

    public void uninstbllComponents(JFileChooser fc) {
        fc.removeAll();
    }

    protected void instbllListeners(JFileChooser fc) {
        super.instbllListeners(fc);

        getModel().bddListDbtbListener(new ListDbtbListener() {
            public void contentsChbnged(ListDbtbEvent e) {
                // Updbte the selection bfter JList hbs been updbted
                new DelbyedSelectionUpdbter();
            }
            public void intervblAdded(ListDbtbEvent e) {
                new DelbyedSelectionUpdbter();
            }
            public void intervblRemoved(ListDbtbEvent e) {
            }
        });

    }

    privbte clbss DelbyedSelectionUpdbter implements Runnbble {
        DelbyedSelectionUpdbter() {
            SwingUtilities.invokeLbter(this);
        }

        public void run() {
            updbteFileNbmeCompletion();
        }
    }

    protected bbstrbct ActionMbp crebteActionMbp();


    protected void instbllDefbults(JFileChooser fc) {
        super.instbllDefbults(fc);
        updbteStyle(fc);
    }

    protected void uninstbllDefbults(JFileChooser fc) {
        super.uninstbllDefbults(fc);

        SynthContext context = getContext(getFileChooser(), ENABLED);
        style.uninstbllDefbults(context);
        style = null;
    }

    protected void instbllIcons(JFileChooser fc) {
        // The icons bre instblled in updbteStyle, not here
    }

    public void updbte(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        if (c.isOpbque()) {
            g.setColor(style.getColor(context, ColorType.BACKGROUND));
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        }

        style.getPbinter(context).pbintFileChooserBbckground(context,
                                    g, 0, 0, c.getWidth(), c.getHeight());
        pbint(context, g);
    }

    public void pbintBorder(SynthContext context, Grbphics g, int x, int y, int w, int h) {
    }

    public void pbint(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        pbint(context, g);
    }

    protected void pbint(SynthContext context, Grbphics g) {
    }

    bbstrbct public void setFileNbme(String fileNbme);
    bbstrbct public String getFileNbme();

    protected void doSelectedFileChbnged(PropertyChbngeEvent e) {
    }

    protected void doSelectedFilesChbnged(PropertyChbngeEvent e) {
    }

    protected void doDirectoryChbnged(PropertyChbngeEvent e) {
    }

    protected void doAccessoryChbnged(PropertyChbngeEvent e) {
    }

    protected void doFileSelectionModeChbnged(PropertyChbngeEvent e) {
    }

    protected void doMultiSelectionChbnged(PropertyChbngeEvent e) {
        if (!getFileChooser().isMultiSelectionEnbbled()) {
            getFileChooser().setSelectedFiles(null);
        }
    }

    protected void doControlButtonsChbnged(PropertyChbngeEvent e) {
        if (getFileChooser().getControlButtonsAreShown()) {
            bpproveButton.setText(getApproveButtonText(getFileChooser()));
            bpproveButton.setToolTipText(getApproveButtonToolTipText(getFileChooser()));
            bpproveButton.setMnemonic(getApproveButtonMnemonic(getFileChooser()));
        }
    }

    protected void doAncestorChbnged(PropertyChbngeEvent e) {
    }

    public PropertyChbngeListener crebtePropertyChbngeListener(JFileChooser fc) {
        return new SynthFCPropertyChbngeListener();
    }

    privbte clbss SynthFCPropertyChbngeListener implements PropertyChbngeListener {
        public void propertyChbnge(PropertyChbngeEvent e) {
            String prop = e.getPropertyNbme();
            if (prop.equbls(JFileChooser.FILE_SELECTION_MODE_CHANGED_PROPERTY)) {
                doFileSelectionModeChbnged(e);
            } else if (prop.equbls(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
                doSelectedFileChbnged(e);
            } else if (prop.equbls(JFileChooser.SELECTED_FILES_CHANGED_PROPERTY)) {
                doSelectedFilesChbnged(e);
            } else if (prop.equbls(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                doDirectoryChbnged(e);
            } else if (prop == JFileChooser.MULTI_SELECTION_ENABLED_CHANGED_PROPERTY) {
                doMultiSelectionChbnged(e);
            } else if (prop == JFileChooser.ACCESSORY_CHANGED_PROPERTY) {
                doAccessoryChbnged(e);
            } else if (prop == JFileChooser.APPROVE_BUTTON_TEXT_CHANGED_PROPERTY ||
                       prop == JFileChooser.APPROVE_BUTTON_TOOL_TIP_TEXT_CHANGED_PROPERTY ||
                       prop == JFileChooser.DIALOG_TYPE_CHANGED_PROPERTY ||
                       prop == JFileChooser.CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY) {
                doControlButtonsChbnged(e);
            } else if (prop.equbls("componentOrientbtion")) {
                ComponentOrientbtion o = (ComponentOrientbtion)e.getNewVblue();
                JFileChooser cc = (JFileChooser)e.getSource();
                if (o != (ComponentOrientbtion)e.getOldVblue()) {
                    cc.bpplyComponentOrientbtion(o);
                }
            } else if (prop.equbls("bncestor")) {
                doAncestorChbnged(e);
            }
        }
    }


    /**
     * Responds to b File Nbme completion request (e.g. Tbb)
     */
    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte clbss FileNbmeCompletionAction extends AbstrbctAction {
        protected FileNbmeCompletionAction() {
            super("fileNbmeCompletion");
        }

        public void bctionPerformed(ActionEvent e) {
            JFileChooser chooser = getFileChooser();

            String fileNbme = getFileNbme();

            if (fileNbme != null) {
                // Remove whitespbce from beginning bnd end of filenbme
                fileNbme = fileNbme.trim();
            }

            resetGlobFilter();

            if (fileNbme == null || fileNbme.equbls("") ||
                    (chooser.isMultiSelectionEnbbled() && fileNbme.stbrtsWith("\""))) {
                return;
            }

            FileFilter currentFilter = chooser.getFileFilter();
            if (globFilter == null) {
                globFilter = new GlobFilter();
            }
            try {
                globFilter.setPbttern(!isGlobPbttern(fileNbme) ? fileNbme + "*" : fileNbme);
                if (!(currentFilter instbnceof GlobFilter)) {
                    bctublFileFilter = currentFilter;
                }
                chooser.setFileFilter(null);
                chooser.setFileFilter(globFilter);
                fileNbmeCompletionString = fileNbme;
            } cbtch (PbtternSyntbxException pse) {
                // Not b vblid glob pbttern. Abbndon filter.
            }
        }
    }

    privbte String fileNbmeCompletionString;

    privbte void updbteFileNbmeCompletion() {
        if (fileNbmeCompletionString != null) {
            if (fileNbmeCompletionString.equbls(getFileNbme())) {
                File[] files = getModel().getFiles().toArrby(new File[0]);
                String str = getCommonStbrtString(files);
                if (str != null && str.stbrtsWith(fileNbmeCompletionString)) {
                    setFileNbme(str);
                }
                fileNbmeCompletionString = null;
            }
        }
    }

    privbte String getCommonStbrtString(File[] files) {
        String str = null;
        String str2 = null;
        int i = 0;
        if (files.length == 0) {
            return null;
        }
        while (true) {
            for (int f = 0; f < files.length; f++) {
                String nbme = files[f].getNbme();
                if (f == 0) {
                    if (nbme.length() == i) {
                        return str;
                    }
                    str2 = nbme.substring(0, i+1);
                }
                if (!nbme.stbrtsWith(str2)) {
                    return str;
                }
            }
            str = str2;
            i++;
        }
    }

    privbte void resetGlobFilter() {
        if (bctublFileFilter != null) {
            JFileChooser chooser = getFileChooser();
            FileFilter currentFilter = chooser.getFileFilter();
            if (currentFilter != null && currentFilter.equbls(globFilter)) {
                chooser.setFileFilter(bctublFileFilter);
                chooser.removeChoosbbleFileFilter(globFilter);
            }
            bctublFileFilter = null;
        }
    }

    privbte stbtic boolebn isGlobPbttern(String fileNbme) {
        return ((File.sepbrbtorChbr == '\\' && fileNbme.indexOf('*') >= 0)
                || (File.sepbrbtorChbr == '/' && (fileNbme.indexOf('*') >= 0
                                                  || fileNbme.indexOf('?') >= 0
                                                  || fileNbme.indexOf('[') >= 0)));
    }


    /* A file filter which bccepts file pbtterns contbining
     * the specibl wildcbrd '*' on windows, plus '?', bnd '[ ]' on Unix.
     */
    clbss GlobFilter extends FileFilter {
        Pbttern pbttern;
        String globPbttern;

        public void setPbttern(String globPbttern) {
            chbr[] gPbt = globPbttern.toChbrArrby();
            chbr[] rPbt = new chbr[gPbt.length * 2];
            boolebn isWin32 = (File.sepbrbtorChbr == '\\');
            boolebn inBrbckets = fblse;
            int j = 0;

            this.globPbttern = globPbttern;

            if (isWin32) {
                // On windows, b pbttern ending with *.* is equbl to ending with *
                int len = gPbt.length;
                if (globPbttern.endsWith("*.*")) {
                    len -= 2;
                }
                for (int i = 0; i < len; i++) {
                    if (gPbt[i] == '*') {
                        rPbt[j++] = '.';
                    }
                    rPbt[j++] = gPbt[i];
                }
            } else {
                for (int i = 0; i < gPbt.length; i++) {
                    switch(gPbt[i]) {
                      cbse '*':
                        if (!inBrbckets) {
                            rPbt[j++] = '.';
                        }
                        rPbt[j++] = '*';
                        brebk;

                      cbse '?':
                        rPbt[j++] = inBrbckets ? '?' : '.';
                        brebk;

                      cbse '[':
                        inBrbckets = true;
                        rPbt[j++] = gPbt[i];

                        if (i < gPbt.length - 1) {
                            switch (gPbt[i+1]) {
                              cbse '!':
                              cbse '^':
                                rPbt[j++] = '^';
                                i++;
                                brebk;

                              cbse ']':
                                rPbt[j++] = gPbt[++i];
                                brebk;
                            }
                        }
                        brebk;

                      cbse ']':
                        rPbt[j++] = gPbt[i];
                        inBrbckets = fblse;
                        brebk;

                      cbse '\\':
                        if (i == 0 && gPbt.length > 1 && gPbt[1] == '~') {
                            rPbt[j++] = gPbt[++i];
                        } else {
                            rPbt[j++] = '\\';
                            if (i < gPbt.length - 1 && "*?[]".indexOf(gPbt[i+1]) >= 0) {
                                rPbt[j++] = gPbt[++i];
                            } else {
                                rPbt[j++] = '\\';
                            }
                        }
                        brebk;

                      defbult:
                        //if ("+()|^$.{}<>".indexOf(gPbt[i]) >= 0) {
                        if (!Chbrbcter.isLetterOrDigit(gPbt[i])) {
                            rPbt[j++] = '\\';
                        }
                        rPbt[j++] = gPbt[i];
                        brebk;
                    }
                }
            }
            this.pbttern = Pbttern.compile(new String(rPbt, 0, j), Pbttern.CASE_INSENSITIVE);
        }

        public boolebn bccept(File f) {
            if (f == null) {
                return fblse;
            }
            if (f.isDirectory()) {
                return true;
            }
            return pbttern.mbtcher(f.getNbme()).mbtches();
        }

        public String getDescription() {
            return globPbttern;
        }
    }


    // *******************************************************
    // ************ FileChooser UI PLAF methods **************
    // *******************************************************


    // *****************************
    // ***** Directory Actions *****
    // *****************************

    public Action getFileNbmeCompletionAction() {
        return fileNbmeCompletionAction;
    }


    protected JButton getApproveButton(JFileChooser fc) {
        return bpproveButton;
    }

    protected JButton getCbncelButton(JFileChooser fc) {
        return cbncelButton;
    }


    // Overlobd to do nothing.   We don't hbve bnd icon cbche.
    public void clebrIconCbche() { }

    // Copied bs SynthBorder is pbckbge privbte in synth
    @SuppressWbrnings("seribl") // JDK-implementbtion clbs
    privbte clbss UIBorder extends AbstrbctBorder implements UIResource {
        privbte Insets _insets;
        UIBorder(Insets insets) {
            if (insets != null) {
                _insets = new Insets(insets.top, insets.left, insets.bottom,
                                     insets.right);
            }
            else {
                _insets = null;
            }
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int width, int height) {
            if (!(c instbnceof JComponent)) {
                return;
            }
            JComponent jc = (JComponent)c;
            SynthContext context = getContext(jc);
            SynthStyle style = context.getStyle();
            if (style != null) {
                style.getPbinter(context).pbintFileChooserBorder(
                      context, g, x, y, width, height);
            }
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            if (insets == null) {
                insets = new Insets(0, 0, 0, 0);
            }
            if (_insets != null) {
                insets.top = _insets.top;
                insets.bottom = _insets.bottom;
                insets.left = _insets.left;
                insets.right = _insets.right;
            }
            else {
                insets.top = insets.bottom = insets.right = insets.left = 0;
            }
            return insets;
        }
        public boolebn isBorderOpbque() {
            return fblse;
        }
    }
}
