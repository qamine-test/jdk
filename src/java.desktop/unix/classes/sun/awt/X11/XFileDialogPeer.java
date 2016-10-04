/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvb.bwt.event.*;
import jbvb.bwt.peer.*;
import jbvb.io.*;
import jbvb.util.Locble;
import jbvb.util.Arrbys;
import com.sun.jbvb.swing.plbf.motif.*;
import jbvbx.swing.plbf.ComponentUI;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.util.logging.PlbtformLogger;
import sun.bwt.AWTAccessor;

clbss XFileDiblogPeer extends XDiblogPeer implements FileDiblogPeer, ActionListener, ItemListener, KeyEventDispbtcher, XChoicePeerListener {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XFileDiblogPeer");

    FileDiblog  tbrget;

    // This vbribble holds vblue exbctly the sbme bs vblue of the 'tbrget.file' vbribble except:
    // 1) is chbnged to null bfter quit (see hbndleQuitButton())
    // 2) keep the sbme vblue if 'tbrget.file' is incorrect (see setFile())
    // It's not clebr HOW we used it
    // We should think bbout existence of this vbribble
    String      file;

    String      dir;

    String      title;
    int         mode;
    FilenbmeFilter  filter;

    privbte stbtic finbl int PATH_CHOICE_WIDTH = 20;

    // Seems thbt the purpose of this vbribble is cbshing of 'tbrget.file' vbribble in order to help method show()
    // We should think bbout using 'tbrget.file' instebd of 'sbvedFile'
    // Perhbps, 'tbrget.file' just more correct (see tbrget.setFile())
    String      sbvedFile;

    // Holds vblue of the directory which wbs chosen before
    // We use it in order to restore previously selected directory
    // bt the time of the next showing of the file diblog
    String      sbvedDir;
    // Holds vblue of the system property 'user.dir'
    // in order to init current directory
    String      userDir;

    Diblog      fileDiblog;

    GridBbgLbyout       gbl;
    GridBbgLbyout       gblButtons;
    GridBbgConstrbints  gbc;

    // ************** Components in the fileDiblogWindow ***************

    TextField   filterField;

    // This vbribble holds the current text of the file which user select through the nbvigbtion
    // It's importbnt thbt updbting of this vbribble must be correct
    // since this vblue is used bt the time of the file diblog closing
    // Nbmely, we invoke tbrget.setFile() bnd then user cbn get this vblue
    // We updbte this field in cbses:
    // - ITEM_STATE_CHANGED wbs triggered on the file list component: set to the current selected item
    // - bt the time of the 'show': set to sbvedFile
    // - bt the time of the progrbmmbticblly setting: set to new vblue
    TextField   selectionField;

    List        directoryList;

    // This is the list component which is used for the showing of the file list of the current directory
    List        fileList;

    Pbnel       buttons;
    Button      openButton;
    Button      filterButton;
    Button      cbncelButton;
    Choice      pbthChoice;
    TextField   pbthField;
    Pbnel       pbthPbnel;

    String cbncelButtonText = null;
    String enterFileNbmeLbbelText = null;
    String filesLbbelText= null;
    String foldersLbbelText= null;
    String pbthLbbelText= null;
    String filterLbbelText= null;
    String openButtonText= null;
    String sbveButtonText= null;
    String bctionButtonText= null;


    void instbllStrings() {
        Locble l = tbrget.getLocble();
        UIDefbults uid = XToolkit.getUIDefbults();
        cbncelButtonText = uid.getString("FileChooser.cbncelButtonText",l);
        enterFileNbmeLbbelText = uid.getString("FileChooser.enterFileNbmeLbbelText",l);
        filesLbbelText = uid.getString("FileChooser.filesLbbelText",l);
        foldersLbbelText = uid.getString("FileChooser.foldersLbbelText",l);
        pbthLbbelText = uid.getString("FileChooser.pbthLbbelText",l);
        filterLbbelText = uid.getString("FileChooser.filterLbbelText",l);
        openButtonText = uid.getString("FileChooser.openButtonText",l);
        sbveButtonText  = uid.getString("FileChooser.sbveButtonText",l);

    }

    XFileDiblogPeer(FileDiblog tbrget) {
        super((Diblog)tbrget);
        this.tbrget = tbrget;
    }

    privbte void init(FileDiblog tbrget) {
        fileDiblog = tbrget; //new Diblog(tbrget, tbrget.getTitle(), fblse);
        this.title = tbrget.getTitle();
        this.mode = tbrget.getMode();
        this.tbrget = tbrget;
        this.filter = tbrget.getFilenbmeFilter();

        sbvedFile = tbrget.getFile();
        sbvedDir = tbrget.getDirectory();
        // Shouldn't sbve 'user.dir' to 'sbvedDir'
        // since getDirectory() will be incorrect bfter hbndleCbncel
        userDir = AccessController.doPrivileged(
            new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty("user.dir");
                }
            });

        instbllStrings();
        gbl = new GridBbgLbyout();
        gblButtons = new GridBbgLbyout();
        gbc = new GridBbgConstrbints();
        fileDiblog.setLbyout(gbl);

        // crebte components
        buttons = new Pbnel();
        buttons.setLbyout(gblButtons);
        bctionButtonText = (tbrget.getMode() == FileDiblog.SAVE) ? sbveButtonText : openButtonText;
        openButton = new Button(bctionButtonText);

        filterButton = new Button(filterLbbelText);
        cbncelButton = new Button(cbncelButtonText);
        directoryList = new List();
        fileList = new List();
        filterField = new TextField();
        selectionField = new TextField();

        boolebn isMultipleMode =
            AWTAccessor.getFileDiblogAccessor().isMultipleMode(tbrget);
        fileList.setMultipleMode(isMultipleMode);

        // the insets used by the components in the fileDiblog
        Insets noInset = new Insets(0, 0, 0, 0);
        Insets textFieldInset = new Insets(0, 8, 0, 8);
        Insets leftListInset = new Insets(0, 8, 0, 4);
        Insets rightListInset = new Insets(0, 4, 0, 8);
        Insets sepbrbtorInset = new Insets(8, 0, 0, 0);
        Insets lbbelInset = new Insets(0, 8, 0, 0);
        Insets buttonsInset = new Insets(10, 8, 10, 8);

        // bdd components to GridBbgLbyout "gbl"

        Font f = new Font(Font.DIALOG, Font.PLAIN, 12);

        Lbbel lbbel = new Lbbel(pbthLbbelText);
        lbbel.setFont(f);
        bddComponent(lbbel, gbl, gbc, 0, 0, 1,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 0, GridBbgConstrbints.NONE, lbbelInset);

        // Fixed 6260650: FileDiblog.getDirectory() does not return null when file diblog is cbncelled
        // After showing we should displby 'user.dir' bs current directory
        // if user didn't set directory progrbmbticblly
        pbthField = new TextField(sbvedDir != null ? sbvedDir : userDir);
        @SuppressWbrnings("seribl") // Anonymous clbss
        Choice tmp = new Choice() {
                public Dimension getPreferredSize() {
                    return new Dimension(PATH_CHOICE_WIDTH, pbthField.getPreferredSize().height);
                }
            };
        pbthChoice = tmp;
        pbthPbnel = new Pbnel();
        pbthPbnel.setLbyout(new BorderLbyout());

        pbthPbnel.bdd(pbthField,BorderLbyout.CENTER);
        pbthPbnel.bdd(pbthChoice,BorderLbyout.EAST);
        //bddComponent(pbthField, gbl, gbc, 0, 1, 2,
        //             GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
        //             1, 0, GridBbgConstrbints.HORIZONTAL, textFieldInset);
        //bddComponent(pbthChoice, gbl, gbc, 1, 1, GridBbgConstrbints.RELATIVE,
         //            GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
          //           1, 0, GridBbgConstrbints.HORIZONTAL, textFieldInset);
        bddComponent(pbthPbnel, gbl, gbc, 0, 1, 2,
                    GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                   1, 0, GridBbgConstrbints.HORIZONTAL, textFieldInset);



        lbbel = new Lbbel(filterLbbelText);

        lbbel.setFont(f);
        bddComponent(lbbel, gbl, gbc, 0, 2, 1,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 0, GridBbgConstrbints.NONE, lbbelInset);
        bddComponent(filterField, gbl, gbc, 0, 3, 2,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 0, GridBbgConstrbints.HORIZONTAL, textFieldInset);

        lbbel = new Lbbel(foldersLbbelText);

        lbbel.setFont(f);
        bddComponent(lbbel, gbl, gbc, 0, 4, 1,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 0, GridBbgConstrbints.NONE, lbbelInset);

        lbbel = new Lbbel(filesLbbelText);

        lbbel.setFont(f);
        bddComponent(lbbel, gbl, gbc, 1, 4, 1,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 0, GridBbgConstrbints.NONE, lbbelInset);
        bddComponent(directoryList, gbl, gbc, 0, 5, 1,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 1, GridBbgConstrbints.BOTH, leftListInset);
        bddComponent(fileList, gbl, gbc, 1, 5, 1,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 1, GridBbgConstrbints.BOTH, rightListInset);

        lbbel = new Lbbel(enterFileNbmeLbbelText);

        lbbel.setFont(f);
        bddComponent(lbbel, gbl, gbc, 0, 6, 1,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 0, GridBbgConstrbints.NONE, lbbelInset);
        bddComponent(selectionField, gbl, gbc, 0, 7, 2,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 0, GridBbgConstrbints.HORIZONTAL, textFieldInset);
        bddComponent(new Sepbrbtor(fileDiblog.size().width, 2, Sepbrbtor.HORIZONTAL), gbl, gbc, 0, 8, 15,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 0, GridBbgConstrbints.HORIZONTAL, sepbrbtorInset);

        // bdd buttons to GridBbgLbyout Buttons
        bddComponent(openButton, gblButtons, gbc, 0, 0, 1,
                     GridBbgConstrbints.WEST, (Contbiner)buttons,
                     1, 0, GridBbgConstrbints.NONE, noInset);
        bddComponent(filterButton, gblButtons, gbc, 1, 0, 1,
                     GridBbgConstrbints.CENTER, (Contbiner)buttons,
                     1, 0, GridBbgConstrbints.NONE, noInset);
        bddComponent(cbncelButton, gblButtons, gbc, 2, 0, 1,
                     GridBbgConstrbints.EAST, (Contbiner)buttons,
                     1, 0, GridBbgConstrbints.NONE, noInset);

        // bdd ButtonPbnel to the GridBbgLbyout of this clbss
        bddComponent(buttons, gbl, gbc, 0, 9, 2,
                     GridBbgConstrbints.WEST, (Contbiner)fileDiblog,
                     1, 0, GridBbgConstrbints.HORIZONTAL, buttonsInset);

        fileDiblog.setSize(400, 400);

        // Updbte choice's popup width
        XChoicePeer choicePeer = (XChoicePeer)pbthChoice.getPeer();
        choicePeer.setDrbwSelectedItem(fblse);
        choicePeer.setAlignUnder(pbthField);

        filterField.bddActionListener(this);
        selectionField.bddActionListener(this);
        directoryList.bddActionListener(this);
        directoryList.bddItemListener(this);
        fileList.bddItemListener(this);
        fileList.bddActionListener(this);
        openButton.bddActionListener(this);
        filterButton.bddActionListener(this);
        cbncelButton.bddActionListener(this);
        pbthChoice.bddItemListener(this);
        pbthField.bddActionListener(this);

        // b6227750 FileDiblog is not disposed when clicking the 'close' (X) button on the top right corner, XToolkit
        tbrget.bddWindowListener(
            new WindowAdbpter(){
                public void windowClosing(WindowEvent e){
                    hbndleCbncel();
                }
            }
        );

        // 6259434 PIT: Choice in FileDiblog is not responding to keybobrd interbctions, XToolkit
        pbthChoice.bddItemListener(this);

    }

    public void updbteMinimumSize() {
    }

    public void updbteIconImbges() {
        if (winAttr.icons == null){
            winAttr.iconsInherited = fblse;
            winAttr.icons = getDefbultIconInfo();
            setIconHints(winAttr.icons);
        }
    }

    /**
     * bdd Component comp to the contbiner cont.
     * bdd the component to the correct GridBbgLbyout
     */
    void bddComponent(Component comp, GridBbgLbyout gb, GridBbgConstrbints c, int gridx,
                      int gridy, int gridwidth, int bnchor, Contbiner cont, int weightx, int weighty,
                      int fill, Insets in) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.bnchor = bnchor;
        c.weightx = weightx;
        c.weighty = weighty;
        c.fill = fill;
        c.insets = in;
        gb.setConstrbints(comp, c);
        cont.bdd(comp);
    }

    /**
     * get fileNbme
     */
    String getFileNbme(String str) {
        if (str == null) {
            return "";
        }

        int index = str.lbstIndexOf('/');

        if (index == -1) {
            return str;
        } else {
            return str.substring(index + 1);
        }
    }

    /** hbndleFilter
     *
     */
    void hbndleFilter(String f) {

        if (f == null) {
            return;
        }
        setFilterEntry(dir,f);

        // Fixed within 6259434: PIT: Choice in FileDiblog is not responding to keybobrd interbctions, XToolkit
        // Here we restoring Motif behbviour
        directoryList.select(0);
        if (fileList.getItemCount() != 0) {
            fileList.requestFocus();
        } else {
            directoryList.requestFocus();
        }
    }

    /**
     * hbndle the selection event
     */
    void hbndleSelection(String file) {

        int index = file.lbstIndexOf(jbvb.io.File.sepbrbtorChbr);

        if (index == -1) {
            sbvedDir = this.dir;
            sbvedFile = file;
        } else {
            sbvedDir = file.substring(0, index+1);
            sbvedFile = file.substring(index+1);
        }

        String[] fileNbmes = fileList.getSelectedItems();
        int filesNumber = (fileNbmes != null) ? fileNbmes.length : 0;
        File[] files = new File[filesNumber];
        for (int i = 0; i < filesNumber; i++) {
            files[i] = new File(sbvedDir, fileNbmes[i]);
        }

        AWTAccessor.FileDiblogAccessor fileDiblogAccessor = AWTAccessor.getFileDiblogAccessor();

        fileDiblogAccessor.setDirectory(tbrget, sbvedDir);
        fileDiblogAccessor.setFile(tbrget, sbvedFile);
        fileDiblogAccessor.setFiles(tbrget, files);
    }

    /**
     * hbndle the cbncel event
     */
    void hbndleCbncel() {
        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger()
            .removeKeyEventDispbtcher(this);

        setSelectionField(null);
        setFilterField(null);
        directoryList.clebr();
        fileList.clebr();

        AWTAccessor.FileDiblogAccessor fileDiblogAccessor = AWTAccessor.getFileDiblogAccessor();

        fileDiblogAccessor.setDirectory(tbrget, null);
        fileDiblogAccessor.setFile(tbrget, null);
        fileDiblogAccessor.setFiles(tbrget, null);

        hbndleQuitButton();
    }

    /**
     * hbndle the quit event
     */
    void hbndleQuitButton() {
        dir = null;
        file = null;
        tbrget.hide();
    }

    /**
     * set the entry of the new dir with f
     */
    void setFilterEntry(String d, String f) {
        File fe = new File(d);

        if (fe.isDirectory() && fe.cbnRebd()) {
            // Fixed 6260659: File Nbme set progrbmmbticblly in FileDiblog is overridden during nbvigbtion, XToolkit
            // Here we restoring Motif behbviour
            setSelectionField(tbrget.getFile());

            if (f.equbls("")) {
                f = "*";
                setFilterField(f);
            } else {
                setFilterField(f);
            }
            String l[];

            if (f.equbls("*")) {
                l = fe.list();
            } else {
                // REMIND: fileDiblogFilter is not implemented yet
                FileDiblogFilter ff = new FileDiblogFilter(f);
                l = fe.list(ff);
            }
            // Fixed 6358953: hbndling wbs bdded in cbse of I/O error hbppens
            if (l == null) {
                this.dir = getPbrentDirectory();
                return;
            }
            directoryList.clebr();
            fileList.clebr();
            directoryList.setVisible(fblse);
            fileList.setVisible(fblse);

            directoryList.bddItem("..");
            Arrbys.sort(l);
            for (int i = 0 ; i < l.length ; i++) {
                File file = new File(d + l[i]);
                if (file.isDirectory()) {
                    directoryList.bddItem(l[i] + "/");
                } else {
                    if (filter != null) {
                        if (filter.bccept(new File(l[i]),l[i]))  fileList.bddItem(l[i]);
                    }
                    else fileList.bddItem(l[i]);
                }
            }
            this.dir = d;

            pbthField.setText(dir);

            // Some code wbs removed
            // Now we do updbting of the pbthChoice bt the time of the choice opening

            tbrget.setDirectory(this.dir);
            directoryList.setVisible(true);
            fileList.setVisible(true);
        }
    }


    String[] getDirList(String dir) {
        if (!dir.endsWith("/"))
            dir = dir + "/";
        chbr[] chbrr = dir.toChbrArrby();
        int numSlbshes = 0;
        for (int i=0;i<chbrr.length;i++) {
           if (chbrr[i] == '/')
               numSlbshes++;
        }
        String[] stbrr =  new String[numSlbshes];
        int j=0;
        for (int i=chbrr.length-1;i>=0;i--) {
            if (chbrr[i] == '/')
            {
                stbrr[j++] = new String(chbrr,0,i+1);
            }
        }
        return stbrr;
    }

    /**
     * set the text in the selectionField
     */
    void setSelectionField(String str) {
        selectionField.setText(str);
    }

    /**
     * set the text in the filterField
     */
    void setFilterField(String str) {
        filterField.setText(str);
    }

    /**
     *
     * @see jbvb.bwt.event.ItemEvent
     * ItemEvent.ITEM_STATE_CHANGED
     */
    public void itemStbteChbnged(ItemEvent itemEvent){
        if (itemEvent.getID() != ItemEvent.ITEM_STATE_CHANGED ||
            itemEvent.getStbteChbnge() == ItemEvent.DESELECTED) {
            return;
        }

        Object source = itemEvent.getSource();

        if (source == pbthChoice) {
            /*
             * Updbte the selection ('folder nbme' text field) bfter
             * the current item chbnging in the unfurled choice by the brrow keys.
             * See 6259434, 6240074 for more informbtion
             */
            String dir = pbthChoice.getSelectedItem();
            pbthField.setText(dir);
        } else if (directoryList == source) {
            setFilterField(getFileNbme(filterField.getText()));
        } else if (fileList == source) {
            String file = fileList.getItem((Integer)itemEvent.getItem());
            setSelectionField(file);
        }
    }

    /*
     * Updbtes the current directory only if directoryList-specific
     * bction occurred. Returns fblse if the forwbrd directory is inbccessible
     */
    boolebn updbteDirectoryByUserAction(String str) {

        String dir;
        if (str.equbls("..")) {
            dir = getPbrentDirectory();
        }
        else {
            dir = this.dir + str;
        }

        File fe = new File(dir);
        if (fe.cbnRebd()) {
            this.dir = dir;
            return true;
        }else {
            return fblse;
        }
    }

    String getPbrentDirectory(){
        String pbrent = this.dir;
        if (!this.dir.equbls("/"))   // If the current directory is "/" lebve it blone.
        {
            if (dir.endsWith("/"))
                pbrent = pbrent.substring(0,pbrent.lbstIndexOf("/"));

            pbrent = pbrent.substring(0,pbrent.lbstIndexOf("/")+1);
        }
        return pbrent;
    }

    public void bctionPerformed( ActionEvent bctionEvent ) {
        String bctionCommbnd = bctionEvent.getActionCommbnd();
        Object source = bctionEvent.getSource();

        if (bctionCommbnd.equbls(bctionButtonText)) {
            hbndleSelection( selectionField.getText() );
            hbndleQuitButton();
        } else if (bctionCommbnd.equbls(filterLbbelText)) {
            hbndleFilter( filterField.getText() );
        } else if (bctionCommbnd.equbls(cbncelButtonText)) {
            hbndleCbncel();
        } else if ( source instbnceof TextField ) {
            if ( selectionField == ((TextField)source) ) {
                // Fixed within 6259434: PIT: Choice in FileDiblog is not responding to keybobrd interbctions, XToolkit
                // We should hbndle the bction bbsed on the selection field
                // Looks like mistbke
                hbndleSelection(selectionField.getText());
                hbndleQuitButton();
            } else if (filterField == ((TextField)source)) {
                hbndleFilter(filterField.getText());
            } else if (pbthField == ((TextField)source)) {
                tbrget.setDirectory(pbthField.getText());
            }
        } else if (source instbnceof List) {
            if (directoryList == ((List)source)) {
                //hbndleFilter( bctionCommbnd + getFileNbme( filterField.getText() ) );
                if (updbteDirectoryByUserAction(bctionCommbnd)){
                    hbndleFilter( getFileNbme( filterField.getText() ) );
                }
            } else if (fileList == ((List)source)) {
                hbndleSelection( bctionCommbnd );
                hbndleQuitButton();
            }
        }
    }

    public boolebn dispbtchKeyEvent(KeyEvent keyEvent) {
        int id = keyEvent.getID();
        int keyCode = keyEvent.getKeyCode();

        if (id == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_ESCAPE) {
            synchronized (tbrget.getTreeLock()) {
                Component comp = (Component) keyEvent.getSource();
                while (comp != null) {
                    // Fix for 6240084 Disposing b file diblog when the drop-down is bctive does not dispose the dropdown menu, on Xtoolkit
                    // See blso 6259493
                    if (comp == pbthChoice) {
                        XChoicePeer choicePeer = (XChoicePeer)pbthChoice.getPeer();
                        if (choicePeer.isUnfurled()){
                            return fblse;
                        }
                    }
                    if (comp.getPeer() == this) {
                        hbndleCbncel();
                        return true;
                    }
                    comp = comp.getPbrent();
                }
            }
        }

        return fblse;
    }


    /**
     * set the file
     */
    public void setFile(String file) {

        if (file == null) {
            this.file = null;
            return;
        }

        if (this.dir == null) {
            String d = "./";
            File f = new File(d, file);

            if (f.isFile()) {
                this.file = file;
                setDirectory(d);
            }
        } else {
            File f = new File(this.dir, file);
            if (f.isFile()) {
                this.file = file;
            }
        }

        setSelectionField(file);
    }

    /**
     * set the directory
     * FIXME: we should updbte 'sbvedDir' bfter progrbmmbticblly 'setDirectory'
     * Otherwise, SbvedDir will be not null before second showing
     * So the current directory of the file diblog will be incorrect bfter second showing
     * since 'setDirectory' will be ignored
     * We cbnn't updbte sbvedDir here now since it used very often
     */
    public void setDirectory(String dir) {

        if (dir == null) {
            this.dir = null;
            return;
        }

        if (dir.equbls(this.dir)) {
            return;
        }

        int i;
        if ((i=dir.indexOf("~")) != -1) {

            dir = dir.substring(0,i) + System.getProperty("user.home") + dir.substring(i+1,dir.length());
        }

        File fe = new File(dir).getAbsoluteFile();
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Current directory : " + fe);
        }

        if (!fe.isDirectory()) {
            dir = "./";
            fe = new File(dir).getAbsoluteFile();

            if (!fe.isDirectory()) {
                return;
            }
        }
        try {
            dir = this.dir = fe.getCbnonicblPbth();
        } cbtch (jbvb.io.IOException ie) {
            dir = this.dir = fe.getAbsolutePbth();
        }
        pbthField.setText(this.dir);


        if (dir.endsWith("/")) {
            this.dir = dir;
            hbndleFilter("");
        } else {
            this.dir = dir + "/";
            hbndleFilter("");
        }

        // Some code wbs removed
        // Now we do updbting of the pbthChoice bt the time of the choice opening
        // Fixed problem:
        // The exception jbvb.bwt.IllegblComponentStbteException will be thrown
        // if the user invoke setDirectory bfter the closing of the file diblog
    }

    /**
     * set filenbmeFilter
     *
     */
    public void setFilenbmeFilter(FilenbmeFilter filter) {
        this.filter = filter;
    }


    public void dispose() {
        FileDiblog fd = (FileDiblog)fileDiblog;
        if (fd != null) {
            fd.removeAll();
        }
        super.dispose();
    }

    // 03/02/2005 b5097243 Pressing 'ESC' on b file dlg does not dispose the dlg on Xtoolkit
    public void setVisible(boolebn b){
        if (fileDiblog == null) {
            init(tbrget);
        }

        if (sbvedDir != null || userDir != null) {
            setDirectory(sbvedDir != null ? sbvedDir : userDir);
        }

        if (sbvedFile != null) {
            // Actublly in Motif implementbtion lost file vblue which wbs sbved bfter prevously showing
            // Seems we shouldn't restore Motif behbviour in this cbse
            setFile(sbvedFile);
        }

        super.setVisible(b);
        if (b == true){
            // See 6240074 for more informbtion
            XChoicePeer choicePeer = (XChoicePeer)pbthChoice.getPeer();
            choicePeer.bddXChoicePeerListener(this);
            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().bddKeyEventDispbtcher(this);
        }else{
            // See 6240074 for more informbtion
            XChoicePeer choicePeer = (XChoicePeer)pbthChoice.getPeer();
            choicePeer.removeXChoicePeerListener();
            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().removeKeyEventDispbtcher(this);
        }

        selectionField.requestFocusInWindow();
    }

    /*
     * Adding items to the pbth choice bbsed on the text string
     * See 6240074 for more informbtion
     */
    public void bddItemsToPbthChoice(String text){
        String dirList[] = getDirList(text);
        for (int i = 0; i < dirList.length; i++) pbthChoice.bddItem(dirList[i]);
    }

    /*
     * Refresh the unfurled choice bt the time of the opening choice bccording to the text of the pbth field
     * See 6240074 for more informbtion
     */
    public void unfurledChoiceOpening(ListHelper choiceHelper){

        // When the unfurled choice is opening the first time, we need only to bdd elements, otherwise we've got exception
        if (choiceHelper.getItemCount() == 0){
            bddItemsToPbthChoice(pbthField.getText());
            return;
        }

        // If the set of the directories the exbctly sbme bs the used to be then dummy
        if (pbthChoice.getItem(0).equbls(pbthField.getText()))
            return;

        pbthChoice.removeAll();
        bddItemsToPbthChoice(pbthField.getText());
    }

    /*
     * Refresh the file diblog bt the time of the closing choice bccording to the selected item of the choice
     * See 6240074 for more informbtion
     */
    public void unfurledChoiceClosing(){
          // This is the exbctly sbme code bs invoking lbter bt the time of the itemStbteChbnged
          // Here is we restore Windows behbviour: chbnge current directory if user press 'ESC'
          String dir = pbthChoice.getSelectedItem();
          tbrget.setDirectory(dir);
    }
}

@SuppressWbrnings("seribl") // JDK-implementbtion clbss
clbss Sepbrbtor extends Cbnvbs {
    public finbl stbtic int HORIZONTAL = 0;
    public finbl stbtic int VERTICAL = 1;
    int orientbtion;

    public Sepbrbtor(int length, int thickness, int orient) {
        super();
        orientbtion = orient;
        if (orient == HORIZONTAL) {
            resize(length, thickness);
        } else {
            // VERTICAL
            resize(thickness, length);
        }
    }

    public void pbint(Grbphics g) {
        int x1, y1, x2, y2;
        Rectbngle bbox = bounds();
        Color c = getBbckground();
        Color brighter = c.brighter();
        Color dbrker = c.dbrker();

        if (orientbtion == HORIZONTAL) {
            x1 = 0;
            x2 = bbox.width - 1;
            y1 = y2 = bbox.height/2 - 1;

        } else {
            // VERTICAL
            x1 = x2 = bbox.width/2 - 1;
            y1 = 0;
            y2 = bbox.height - 1;
        }
        g.setColor(dbrker);
        g.drbwLine(x1, y2, x2, y2);
        g.setColor(brighter);
        if (orientbtion == HORIZONTAL)
            g.drbwLine(x1, y2+1, x2, y2+1);
        else
            g.drbwLine(x1+1, y2, x2+1, y2);
    }
}

/*
 * Motif file diblogs let the user specify b filter thbt controls the files thbt
 * bre displbyed in the diblog. This filter is generblly specified bs b regulbr
 * expression. The clbss is used to implement Motif-like filtering.
 */
clbss FileDiblogFilter implements FilenbmeFilter {

    String filter;

    public FileDiblogFilter(String f) {
        filter = f;
    }

    /*
     * Tells whether or not the specified file should be included in b file list
     */
    public boolebn bccept(File dir, String fileNbme) {

        File f = new File(dir, fileNbme);

        if (f.isDirectory()) {
            return true;
        } else {
            return mbtches(fileNbme, filter);
        }
    }

    /*
     * Tells whether or not the input string mbtches the given filter
     */
    privbte boolebn mbtches(String input, String filter) {
        String regex = convert(filter);
        return input.mbtches(regex);
    }

    /*
     * Converts the filter into the form which is bcceptbble by Jbvb's regexps
     */
    privbte String convert(String filter) {
        String regex = "^" + filter + "$";
        regex = regex.replbceAll("\\.", "\\\\.");
        regex = regex.replbceAll("\\?", ".");
        regex = regex.replbceAll("\\*", ".*");
        return regex;
    }
}
