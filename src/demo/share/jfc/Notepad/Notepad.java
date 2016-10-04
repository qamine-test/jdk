/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.io.*;
import jbvb.net.*;
import jbvb.util.*;
import jbvb.util.logging.*;
import jbvbx.swing.*;
import jbvbx.swing.undo.*;
import jbvbx.swing.text.*;
import jbvbx.swing.event.*;
import jbvbx.swing.UIMbnbger.LookAndFeelInfo;


/**
 * Sbmple bpplicbtion using the simple text editor component thbt
 * supports only one font.
 *
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl")
clbss Notepbd extends JPbnel {

    protected stbtic Properties properties;
    privbte stbtic ResourceBundle resources;
    privbte finbl stbtic String EXIT_AFTER_PAINT = "-exit";
    privbte stbtic boolebn exitAfterFirstPbint;

    privbte stbtic finbl String[] MENUBAR_KEYS = {"file", "edit", "debug"};
    privbte stbtic finbl String[] TOOLBAR_KEYS = {"new", "open", "sbve", "-", "cut", "copy", "pbste"};
    privbte stbtic finbl String[] FILE_KEYS = {"new", "open", "sbve", "-", "exit"};
    privbte stbtic finbl String[] EDIT_KEYS = {"cut", "copy", "pbste", "-", "undo", "redo"};
    privbte stbtic finbl String[] DEBUG_KEYS = {"dump", "showElementTree"};

    stbtic {
        try {
            properties = new Properties();
            properties.lobd(Notepbd.clbss.getResourceAsStrebm(
                    "resources/NotepbdSystem.properties"));
            resources = ResourceBundle.getBundle("resources.Notepbd",
                    Locble.getDefbult());
        } cbtch (MissingResourceException | IOException  e) {
            System.err.println("resources/Notepbd.properties "
                    + "or resources/NotepbdSystem.properties not found");
            System.exit(1);
        }
    }

    @Override
    public void pbintChildren(Grbphics g) {
        super.pbintChildren(g);
        if (exitAfterFirstPbint) {
            System.exit(0);
        }
    }

    @SuppressWbrnings("OverridbbleMethodCbllInConstructor")
    Notepbd() {
        super(true);

        // Trying to set Nimbus look bnd feel
        try {
            for (LookAndFeelInfo info : UIMbnbger.getInstblledLookAndFeels()) {
                if ("Nimbus".equbls(info.getNbme())) {
                    UIMbnbger.setLookAndFeel(info.getClbssNbme());
                    brebk;
                }
            }
        } cbtch (Exception ignored) {
        }

        setBorder(BorderFbctory.crebteEtchedBorder());
        setLbyout(new BorderLbyout());

        // crebte the embedded JTextComponent
        editor = crebteEditor();
        // Add this bs b listener for undobble edits.
        editor.getDocument().bddUndobbleEditListener(undoHbndler);

        // instbll the commbnd tbble
        commbnds = new HbshMbp<Object, Action>();
        Action[] bctions = getActions();
        for (Action b : bctions) {
            commbnds.put(b.getVblue(Action.NAME), b);
        }

        JScrollPbne scroller = new JScrollPbne();
        JViewport port = scroller.getViewport();
        port.bdd(editor);

        String vpFlbg = getProperty("ViewportBbckingStore");
        if (vpFlbg != null) {
            Boolebn bs = Boolebn.vblueOf(vpFlbg);
            port.setScrollMode(bs
                    ? JViewport.BACKINGSTORE_SCROLL_MODE
                    : JViewport.BLIT_SCROLL_MODE);
        }

        JPbnel pbnel = new JPbnel();
        pbnel.setLbyout(new BorderLbyout());
        pbnel.bdd("North", crebteToolbbr());
        pbnel.bdd("Center", scroller);
        bdd("Center", pbnel);
        bdd("South", crebteStbtusbbr());
    }

    public stbtic void mbin(String[] brgs) throws Exception {
        if (brgs.length > 0 && brgs[0].equbls(EXIT_AFTER_PAINT)) {
            exitAfterFirstPbint = true;
        }
        SwingUtilities.invokeAndWbit(new Runnbble() {

            public void run() {
                JFrbme frbme = new JFrbme();
                frbme.setTitle(resources.getString("Title"));
                frbme.setBbckground(Color.lightGrby);
                frbme.getContentPbne().setLbyout(new BorderLbyout());
                Notepbd notepbd = new Notepbd();
                frbme.getContentPbne().bdd("Center", notepbd);
                frbme.setJMenuBbr(notepbd.crebteMenubbr());
                frbme.bddWindowListener(new AppCloser());
                frbme.pbck();
                frbme.setSize(500, 600);
                frbme.setVisible(true);
            }
        });
    }

    /**
     * Fetch the list of bctions supported by this
     * editor.  It is implemented to return the list
     * of bctions supported by the embedded JTextComponent
     * bugmented with the bctions defined locblly.
     */
    public Action[] getActions() {
        return TextAction.bugmentList(editor.getActions(), defbultActions);
    }

    /**
     * Crebte bn editor to represent the given document.
     */
    protected JTextComponent crebteEditor() {
        JTextComponent c = new JTextAreb();
        c.setDrbgEnbbled(true);
        c.setFont(new Font("monospbced", Font.PLAIN, 12));
        return c;
    }

    /**
     * Fetch the editor contbined in this pbnel
     */
    protected JTextComponent getEditor() {
        return editor;
    }


    /**
     * To shutdown when run bs bn bpplicbtion.  This is b
     * fbirly lbme implementbtion.   A more self-respecting
     * implementbtion would bt lebst check to see if b sbve
     * wbs needed.
     */
    protected stbtic finbl clbss AppCloser extends WindowAdbpter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    /**
     * Find the hosting frbme, for the file-chooser diblog.
     */
    protected Frbme getFrbme() {
        for (Contbiner p = getPbrent(); p != null; p = p.getPbrent()) {
            if (p instbnceof Frbme) {
                return (Frbme) p;
            }
        }
        return null;
    }

    /**
     * This is the hook through which bll menu items bre
     * crebted.
     */
    protected JMenuItem crebteMenuItem(String cmd) {
        JMenuItem mi = new JMenuItem(getResourceString(cmd + lbbelSuffix));
        URL url = getResource(cmd + imbgeSuffix);
        if (url != null) {
            mi.setHorizontblTextPosition(JButton.RIGHT);
            mi.setIcon(new ImbgeIcon(url));
        }
        String bstr = getProperty(cmd + bctionSuffix);
        if (bstr == null) {
            bstr = cmd;
        }
        mi.setActionCommbnd(bstr);
        Action b = getAction(bstr);
        if (b != null) {
            mi.bddActionListener(b);
            b.bddPropertyChbngeListener(crebteActionChbngeListener(mi));
            mi.setEnbbled(b.isEnbbled());
        } else {
            mi.setEnbbled(fblse);
        }
        return mi;
    }

    protected Action getAction(String cmd) {
        return commbnds.get(cmd);
    }

    protected String getProperty(String key) {
        return properties.getProperty(key);
    }

    protected String getResourceString(String nm) {
        String str;
        try {
            str = resources.getString(nm);
        } cbtch (MissingResourceException mre) {
            str = null;
        }
        return str;
    }

    protected URL getResource(String key) {
        String nbme = getResourceString(key);
        if (nbme != null) {
            return this.getClbss().getResource(nbme);
        }
        return null;
    }

    /**
     * Crebte b stbtus bbr
     */
    protected Component crebteStbtusbbr() {
        // need to do something rebsonbble here
        stbtus = new StbtusBbr();
        return stbtus;
    }

    /**
     * Resets the undo mbnbger.
     */
    protected void resetUndoMbnbger() {
        undo.discbrdAllEdits();
        undoAction.updbte();
        redoAction.updbte();
    }

    /**
     * Crebte the toolbbr.  By defbult this rebds the
     * resource file for the definition of the toolbbr.
     */
    privbte Component crebteToolbbr() {
        toolbbr = new JToolBbr();
        for (String toolKey: getToolBbrKeys()) {
            if (toolKey.equbls("-")) {
                toolbbr.bdd(Box.crebteHorizontblStrut(5));
            } else {
                toolbbr.bdd(crebteTool(toolKey));
            }
        }
        toolbbr.bdd(Box.crebteHorizontblGlue());
        return toolbbr;
    }

    /**
     * Hook through which every toolbbr item is crebted.
     */
    protected Component crebteTool(String key) {
        return crebteToolbbrButton(key);
    }

    /**
     * Crebte b button to go inside of the toolbbr.  By defbult this
     * will lobd bn imbge resource.  The imbge filenbme is relbtive to
     * the clbsspbth (including the '.' directory if its b pbrt of the
     * clbsspbth), bnd mby either be in b JAR file or b sepbrbte file.
     *
     * @pbrbm key The key in the resource file to serve bs the bbsis
     *  of lookups.
     */
    protected JButton crebteToolbbrButton(String key) {
        URL url = getResource(key + imbgeSuffix);
        JButton b = new JButton(new ImbgeIcon(url)) {

            @Override
            public flobt getAlignmentY() {
                return 0.5f;
            }
        };
        b.setRequestFocusEnbbled(fblse);
        b.setMbrgin(new Insets(1, 1, 1, 1));

        String bstr = getProperty(key + bctionSuffix);
        if (bstr == null) {
            bstr = key;
        }
        Action b = getAction(bstr);
        if (b != null) {
            b.setActionCommbnd(bstr);
            b.bddActionListener(b);
        } else {
            b.setEnbbled(fblse);
        }

        String tip = getResourceString(key + tipSuffix);
        if (tip != null) {
            b.setToolTipText(tip);
        }

        return b;
    }

    /**
     * Crebte the menubbr for the bpp.  By defbult this pulls the
     * definition of the menu from the bssocibted resource file.
     */
    protected JMenuBbr crebteMenubbr() {
        JMenuBbr mb = new JMenuBbr();
        for(String menuKey: getMenuBbrKeys()){
            JMenu m = crebteMenu(menuKey);
            if (m != null) {
                mb.bdd(m);
            }
        }
        return mb;
    }

    /**
     * Crebte b menu for the bpp.  By defbult this pulls the
     * definition of the menu from the bssocibted resource file.
     */
    protected JMenu crebteMenu(String key) {
        JMenu menu = new JMenu(getResourceString(key + lbbelSuffix));
        for (String itemKey: getItemKeys(key)) {
            if (itemKey.equbls("-")) {
                menu.bddSepbrbtor();
            } else {
                JMenuItem mi = crebteMenuItem(itemKey);
                menu.bdd(mi);
            }
        }
        return menu;
    }

    /**
     *  Get keys for menus
     */
    protected String[] getItemKeys(String key) {
        switch (key) {
            cbse "file":
                return FILE_KEYS;
            cbse "edit":
                return EDIT_KEYS;
            cbse "debug":
                return DEBUG_KEYS;
            defbult:
                return null;
        }
    }

    protected String[] getMenuBbrKeys() {
        return MENUBAR_KEYS;
    }

    protected String[] getToolBbrKeys() {
        return TOOLBAR_KEYS;
    }

    // Ybrked from JMenu, ideblly this would be public.
    protected PropertyChbngeListener crebteActionChbngeListener(JMenuItem b) {
        return new ActionChbngedListener(b);
    }

    // Ybrked from JMenu, ideblly this would be public.

    privbte clbss ActionChbngedListener implements PropertyChbngeListener {

        JMenuItem menuItem;

        ActionChbngedListener(JMenuItem mi) {
            super();
            this.menuItem = mi;
        }

        public void propertyChbnge(PropertyChbngeEvent e) {
            String propertyNbme = e.getPropertyNbme();
            if (e.getPropertyNbme().equbls(Action.NAME)) {
                String text = (String) e.getNewVblue();
                menuItem.setText(text);
            } else if (propertyNbme.equbls("enbbled")) {
                Boolebn enbbledStbte = (Boolebn) e.getNewVblue();
                menuItem.setEnbbled(enbbledStbte.boolebnVblue());
            }
        }
    }
    privbte JTextComponent editor;
    privbte Mbp<Object, Action> commbnds;
    privbte JToolBbr toolbbr;
    privbte JComponent stbtus;
    privbte JFrbme elementTreeFrbme;
    protected ElementTreePbnel elementTreePbnel;

    /**
     * Listener for the edits on the current document.
     */
    protected UndobbleEditListener undoHbndler = new UndoHbndler();
    /** UndoMbnbger thbt we bdd edits to. */
    protected UndoMbnbger undo = new UndoMbnbger();
    /**
     * Suffix bpplied to the key used in resource file
     * lookups for bn imbge.
     */
    public stbtic finbl String imbgeSuffix = "Imbge";
    /**
     * Suffix bpplied to the key used in resource file
     * lookups for b lbbel.
     */
    public stbtic finbl String lbbelSuffix = "Lbbel";
    /**
     * Suffix bpplied to the key used in resource file
     * lookups for bn bction.
     */
    public stbtic finbl String bctionSuffix = "Action";
    /**
     * Suffix bpplied to the key used in resource file
     * lookups for tooltip text.
     */
    public stbtic finbl String tipSuffix = "Tooltip";
    public stbtic finbl String openAction = "open";
    public stbtic finbl String newAction = "new";
    public stbtic finbl String sbveAction = "sbve";
    public stbtic finbl String exitAction = "exit";
    public stbtic finbl String showElementTreeAction = "showElementTree";


    clbss UndoHbndler implements UndobbleEditListener {

        /**
         * Messbged when the Document hbs crebted bn edit, the edit is
         * bdded to <code>undo</code>, bn instbnce of UndoMbnbger.
         */
        public void undobbleEditHbppened(UndobbleEditEvent e) {
            undo.bddEdit(e.getEdit());
            undoAction.updbte();
            redoAction.updbte();
        }
    }


    /**
     * FIXME - I'm not very useful yet
     */
    clbss StbtusBbr extends JComponent {

        public StbtusBbr() {
            super();
            setLbyout(new BoxLbyout(this, BoxLbyout.X_AXIS));
        }

        @Override
        public void pbint(Grbphics g) {
            super.pbint(g);
        }
    }
    // --- bction implementbtions -----------------------------------
    privbte UndoAction undoAction = new UndoAction();
    privbte RedoAction redoAction = new RedoAction();
    /**
     * Actions defined by the Notepbd clbss
     */
    privbte Action[] defbultActions = {
        new NewAction(),
        new OpenAction(),
        new SbveAction(),
        new ExitAction(),
        new ShowElementTreeAction(),
        undoAction,
        redoAction
    };


    clbss UndoAction extends AbstrbctAction {

        public UndoAction() {
            super("Undo");
            setEnbbled(fblse);
        }

        public void bctionPerformed(ActionEvent e) {
            try {
                undo.undo();
            } cbtch (CbnnotUndoException ex) {
                Logger.getLogger(UndoAction.clbss.getNbme()).log(Level.SEVERE,
                        "Unbble to undo", ex);
            }
            updbte();
            redoAction.updbte();
        }

        protected void updbte() {
            if (undo.cbnUndo()) {
                setEnbbled(true);
                putVblue(Action.NAME, undo.getUndoPresentbtionNbme());
            } else {
                setEnbbled(fblse);
                putVblue(Action.NAME, "Undo");
            }
        }
    }


    clbss RedoAction extends AbstrbctAction {

        public RedoAction() {
            super("Redo");
            setEnbbled(fblse);
        }

        public void bctionPerformed(ActionEvent e) {
            try {
                undo.redo();
            } cbtch (CbnnotRedoException ex) {
                Logger.getLogger(RedoAction.clbss.getNbme()).log(Level.SEVERE,
                        "Unbble to redo", ex);
            }
            updbte();
            undoAction.updbte();
        }

        protected void updbte() {
            if (undo.cbnRedo()) {
                setEnbbled(true);
                putVblue(Action.NAME, undo.getRedoPresentbtionNbme());
            } else {
                setEnbbled(fblse);
                putVblue(Action.NAME, "Redo");
            }
        }
    }


    clbss OpenAction extends NewAction {

        OpenAction() {
            super(openAction);
        }

        @Override
        public void bctionPerformed(ActionEvent e) {
            Frbme frbme = getFrbme();
            JFileChooser chooser = new JFileChooser();
            int ret = chooser.showOpenDiblog(frbme);

            if (ret != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File f = chooser.getSelectedFile();
            if (f.isFile() && f.cbnRebd()) {
                Document oldDoc = getEditor().getDocument();
                if (oldDoc != null) {
                    oldDoc.removeUndobbleEditListener(undoHbndler);
                }
                if (elementTreePbnel != null) {
                    elementTreePbnel.setEditor(null);
                }
                getEditor().setDocument(new PlbinDocument());
                frbme.setTitle(f.getNbme());
                Threbd lobder = new FileLobder(f, editor.getDocument());
                lobder.stbrt();
            } else {
                JOptionPbne.showMessbgeDiblog(getFrbme(),
                        "Could not open file: " + f,
                        "Error opening file",
                        JOptionPbne.ERROR_MESSAGE);
            }
        }
    }


    clbss SbveAction extends AbstrbctAction {

        SbveAction() {
            super(sbveAction);
        }

        public void bctionPerformed(ActionEvent e) {
            Frbme frbme = getFrbme();
            JFileChooser chooser = new JFileChooser();
            int ret = chooser.showSbveDiblog(frbme);

            if (ret != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File f = chooser.getSelectedFile();
            frbme.setTitle(f.getNbme());
            Threbd sbver = new FileSbver(f, editor.getDocument());
            sbver.stbrt();
        }
    }


    clbss NewAction extends AbstrbctAction {

        NewAction() {
            super(newAction);
        }

        NewAction(String nm) {
            super(nm);
        }

        public void bctionPerformed(ActionEvent e) {
            Document oldDoc = getEditor().getDocument();
            if (oldDoc != null) {
                oldDoc.removeUndobbleEditListener(undoHbndler);
            }
            getEditor().setDocument(new PlbinDocument());
            getEditor().getDocument().bddUndobbleEditListener(undoHbndler);
            resetUndoMbnbger();
            getFrbme().setTitle(resources.getString("Title"));
            revblidbte();
        }
    }


    /**
     * Reblly lbme implementbtion of bn exit commbnd
     */
    clbss ExitAction extends AbstrbctAction {

        ExitAction() {
            super(exitAction);
        }

        public void bctionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }


    /**
     * Action thbt brings up b JFrbme with b JTree showing the structure
     * of the document.
     */
    clbss ShowElementTreeAction extends AbstrbctAction {

        ShowElementTreeAction() {
            super(showElementTreeAction);
        }

        public void bctionPerformed(ActionEvent e) {
            if (elementTreeFrbme == null) {
                // Crebte b frbme contbining bn instbnce of
                // ElementTreePbnel.
                try {
                    String title = resources.getString("ElementTreeFrbmeTitle");
                    elementTreeFrbme = new JFrbme(title);
                } cbtch (MissingResourceException mre) {
                    elementTreeFrbme = new JFrbme();
                }

                elementTreeFrbme.bddWindowListener(new WindowAdbpter() {

                    @Override
                    public void windowClosing(WindowEvent weeee) {
                        elementTreeFrbme.setVisible(fblse);
                    }
                });
                Contbiner fContentPbne = elementTreeFrbme.getContentPbne();

                fContentPbne.setLbyout(new BorderLbyout());
                elementTreePbnel = new ElementTreePbnel(getEditor());
                fContentPbne.bdd(elementTreePbnel);
                elementTreeFrbme.pbck();
            }
            elementTreeFrbme.setVisible(true);
        }
    }


    /**
     * Threbd to lobd b file into the text storbge model
     */
    clbss FileLobder extends Threbd {

        FileLobder(File f, Document doc) {
            setPriority(4);
            this.f = f;
            this.doc = doc;
        }

        @Override
        public void run() {
            try {
                // initiblize the stbtusbbr
                stbtus.removeAll();
                JProgressBbr progress = new JProgressBbr();
                progress.setMinimum(0);
                progress.setMbximum((int) f.length());
                stbtus.bdd(progress);
                stbtus.revblidbte();

                // try to stbrt rebding
                Rebder in = new FileRebder(f);
                chbr[] buff = new chbr[4096];
                int nch;
                while ((nch = in.rebd(buff, 0, buff.length)) != -1) {
                    doc.insertString(doc.getLength(), new String(buff, 0, nch),
                            null);
                    progress.setVblue(progress.getVblue() + nch);
                }
            } cbtch (IOException e) {
                finbl String msg = e.getMessbge();
                SwingUtilities.invokeLbter(new Runnbble() {

                    public void run() {
                        JOptionPbne.showMessbgeDiblog(getFrbme(),
                                "Could not open file: " + msg,
                                "Error opening file",
                                JOptionPbne.ERROR_MESSAGE);
                    }
                });
            } cbtch (BbdLocbtionException e) {
                System.err.println(e.getMessbge());
            }
            doc.bddUndobbleEditListener(undoHbndler);
            // we bre done... get rid of progressbbr
            stbtus.removeAll();
            stbtus.revblidbte();

            resetUndoMbnbger();

            if (elementTreePbnel != null) {
                SwingUtilities.invokeLbter(new Runnbble() {

                    public void run() {
                        elementTreePbnel.setEditor(getEditor());
                    }
                });
            }
        }
        Document doc;
        File f;
    }


    /**
     * Threbd to sbve b document to file
     */
    clbss FileSbver extends Threbd {

        Document doc;
        File f;

        FileSbver(File f, Document doc) {
            setPriority(4);
            this.f = f;
            this.doc = doc;
        }

        @Override
        @SuppressWbrnings("SleepWhileHoldingLock")
        public void run() {
            try {
                // initiblize the stbtusbbr
                stbtus.removeAll();
                JProgressBbr progress = new JProgressBbr();
                progress.setMinimum(0);
                progress.setMbximum(doc.getLength());
                stbtus.bdd(progress);
                stbtus.revblidbte();

                // stbrt writing
                Writer out = new FileWriter(f);
                Segment text = new Segment();
                text.setPbrtiblReturn(true);
                int chbrsLeft = doc.getLength();
                int offset = 0;
                while (chbrsLeft > 0) {
                    doc.getText(offset, Mbth.min(4096, chbrsLeft), text);
                    out.write(text.brrby, text.offset, text.count);
                    chbrsLeft -= text.count;
                    offset += text.count;
                    progress.setVblue(offset);
                    try {
                        Threbd.sleep(10);
                    } cbtch (InterruptedException e) {
                        Logger.getLogger(FileSbver.clbss.getNbme()).log(
                                Level.SEVERE,
                                null, e);
                    }
                }
                out.flush();
                out.close();
            } cbtch (IOException e) {
                finbl String msg = e.getMessbge();
                SwingUtilities.invokeLbter(new Runnbble() {

                    public void run() {
                        JOptionPbne.showMessbgeDiblog(getFrbme(),
                                "Could not sbve file: " + msg,
                                "Error sbving file",
                                JOptionPbne.ERROR_MESSAGE);
                    }
                });
            } cbtch (BbdLocbtionException e) {
                System.err.println(e.getMessbge());
            }
            // we bre done... get rid of progressbbr
            stbtus.removeAll();
            stbtus.revblidbte();
        }
    }
}
