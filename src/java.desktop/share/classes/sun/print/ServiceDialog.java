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

pbckbge sun.print;

import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Diblog;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.Frbme;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GridBbgLbyout;
import jbvb.bwt.GridBbgConstrbints;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.Insets;
import jbvb.bwt.Toolkit;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.FocusListener;
import jbvb.bwt.event.ItemEvent;
import jbvb.bwt.event.ItemListener;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.print.PrinterJob;
import jbvb.io.File;
import jbvb.io.FilePermission;
import jbvb.io.IOException;
import jbvb.net.URI;
import jbvb.net.URL;
import jbvb.text.DecimblFormbt;
import jbvb.util.Locble;
import jbvb.util.ResourceBundle;
import jbvb.util.Vector;
import jbvbx.print.*;
import jbvbx.print.bttribute.*;
import jbvbx.print.bttribute.stbndbrd.*;
import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.border.EmptyBorder;
import jbvbx.swing.border.TitledBorder;
import jbvbx.swing.event.ChbngeEvent;
import jbvbx.swing.event.ChbngeListener;
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.event.DocumentListener;
import jbvbx.swing.event.PopupMenuEvent;
import jbvbx.swing.event.PopupMenuListener;
import jbvbx.swing.text.NumberFormbtter;
import sun.print.SunPbgeSelection;
import jbvb.bwt.event.KeyEvent;
import jbvb.net.URISyntbxException;
import jbvb.lbng.reflect.Field;


/**
 * A clbss which implements b cross-plbtform print diblog.
 *
 * @buthor  Chris Cbmpbell
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss ServiceDiblog extends JDiblog implements ActionListener {

    /**
     * Wbiting print stbtus (user response pending).
     */
    public finbl stbtic int WAITING = 0;

    /**
     * Approve print stbtus (user bctivbted "Print" or "OK").
     */
    public finbl stbtic int APPROVE = 1;

    /**
     * Cbncel print stbtus (user bctivbted "Cbncel");
     */
    public finbl stbtic int CANCEL = 2;

    privbte stbtic finbl String strBundle = "sun.print.resources.serviceui";
    privbte stbtic finbl Insets pbnelInsets = new Insets(6, 6, 6, 6);
    privbte stbtic finbl Insets compInsets = new Insets(3, 6, 3, 6);

    privbte stbtic ResourceBundle messbgeRB;
    privbte JTbbbedPbne tpTbbs;
    privbte JButton btnCbncel, btnApprove;
    privbte PrintService[] services;
    privbte int defbultServiceIndex;
    privbte PrintRequestAttributeSet bsOriginbl;
    privbte HbshPrintRequestAttributeSet bsCurrent;
    privbte PrintService psCurrent;
    privbte DocFlbvor docFlbvor;
    privbte int stbtus;

    privbte VblidbtingFileChooser jfc;

    privbte GenerblPbnel pnlGenerbl;
    privbte PbgeSetupPbnel pnlPbgeSetup;
    privbte AppebrbncePbnel pnlAppebrbnce;

    privbte boolebn isAWT = fblse;
    stbtic {
        initResource();
    }


    /**
     * Constructor for the "stbndbrd" print diblog (contbining bll relevbnt
     * tbbs)
     */
    public ServiceDiblog(GrbphicsConfigurbtion gc,
                         int x, int y,
                         PrintService[] services,
                         int defbultServiceIndex,
                         DocFlbvor flbvor,
                         PrintRequestAttributeSet bttributes,
                         Diblog diblog)
    {
        super(diblog, getMsg("diblog.printtitle"), true, gc);
        initPrintDiblog(x, y, services, defbultServiceIndex,
                        flbvor, bttributes);
    }



    /**
     * Constructor for the "stbndbrd" print diblog (contbining bll relevbnt
     * tbbs)
     */
    public ServiceDiblog(GrbphicsConfigurbtion gc,
                         int x, int y,
                         PrintService[] services,
                         int defbultServiceIndex,
                         DocFlbvor flbvor,
                         PrintRequestAttributeSet bttributes,
                         Frbme frbme)
    {
        super(frbme, getMsg("diblog.printtitle"), true, gc);
        initPrintDiblog(x, y, services, defbultServiceIndex,
                        flbvor, bttributes);
    }


    /**
     * Initiblize print diblog.
     */
    void initPrintDiblog(int x, int y,
                         PrintService[] services,
                         int defbultServiceIndex,
                         DocFlbvor flbvor,
                         PrintRequestAttributeSet bttributes)
    {
        this.services = services;
        this.defbultServiceIndex = defbultServiceIndex;
        this.bsOriginbl = bttributes;
        this.bsCurrent = new HbshPrintRequestAttributeSet(bttributes);
        this.psCurrent = services[defbultServiceIndex];
        this.docFlbvor = flbvor;
        SunPbgeSelection pbges =
            (SunPbgeSelection)bttributes.get(SunPbgeSelection.clbss);
        if (pbges != null) {
            isAWT = true;
        }

        Contbiner c = getContentPbne();
        c.setLbyout(new BorderLbyout());

        tpTbbs = new JTbbbedPbne();
        tpTbbs.setBorder(new EmptyBorder(5, 5, 5, 5));

        String gkey = getMsg("tbb.generbl");
        int gmnemonic = getVKMnemonic("tbb.generbl");
        pnlGenerbl = new GenerblPbnel();
        tpTbbs.bdd(gkey, pnlGenerbl);
        tpTbbs.setMnemonicAt(0, gmnemonic);

        String pkey = getMsg("tbb.pbgesetup");
        int pmnemonic = getVKMnemonic("tbb.pbgesetup");
        pnlPbgeSetup = new PbgeSetupPbnel();
        tpTbbs.bdd(pkey, pnlPbgeSetup);
        tpTbbs.setMnemonicAt(1, pmnemonic);

        String bkey = getMsg("tbb.bppebrbnce");
        int bmnemonic = getVKMnemonic("tbb.bppebrbnce");
        pnlAppebrbnce = new AppebrbncePbnel();
        tpTbbs.bdd(bkey, pnlAppebrbnce);
        tpTbbs.setMnemonicAt(2, bmnemonic);

        c.bdd(tpTbbs, BorderLbyout.CENTER);

        updbtePbnels();

        JPbnel pnlSouth = new JPbnel(new FlowLbyout(FlowLbyout.TRAILING));
        btnApprove = crebteExitButton("button.print", this);
        pnlSouth.bdd(btnApprove);
        getRootPbne().setDefbultButton(btnApprove);
        btnCbncel = crebteExitButton("button.cbncel", this);
        hbndleEscKey(btnCbncel);
        pnlSouth.bdd(btnCbncel);
        c.bdd(pnlSouth, BorderLbyout.SOUTH);

        bddWindowListener(new WindowAdbpter() {
            public void windowClosing(WindowEvent event) {
                dispose(CANCEL);
            }
        });

        getAccessibleContext().setAccessibleDescription(getMsg("diblog.printtitle"));
        setResizbble(fblse);
        setLocbtion(x, y);
        pbck();
    }

   /**
     * Constructor for the solitbry "pbge setup" diblog
     */
    public ServiceDiblog(GrbphicsConfigurbtion gc,
                         int x, int y,
                         PrintService ps,
                         DocFlbvor flbvor,
                         PrintRequestAttributeSet bttributes,
                         Diblog diblog)
    {
        super(diblog, getMsg("diblog.pstitle"), true, gc);
        initPbgeDiblog(x, y, ps, flbvor, bttributes);
    }

    /**
     * Constructor for the solitbry "pbge setup" diblog
     */
    public ServiceDiblog(GrbphicsConfigurbtion gc,
                         int x, int y,
                         PrintService ps,
                         DocFlbvor flbvor,
                         PrintRequestAttributeSet bttributes,
                         Frbme frbme)
    {
        super(frbme, getMsg("diblog.pstitle"), true, gc);
        initPbgeDiblog(x, y, ps, flbvor, bttributes);
    }


    /**
     * Initiblize "pbge setup" diblog
     */
    void initPbgeDiblog(int x, int y,
                         PrintService ps,
                         DocFlbvor flbvor,
                         PrintRequestAttributeSet bttributes)
    {
        this.psCurrent = ps;
        this.docFlbvor = flbvor;
        this.bsOriginbl = bttributes;
        this.bsCurrent = new HbshPrintRequestAttributeSet(bttributes);

        Contbiner c = getContentPbne();
        c.setLbyout(new BorderLbyout());

        pnlPbgeSetup = new PbgeSetupPbnel();
        c.bdd(pnlPbgeSetup, BorderLbyout.CENTER);

        pnlPbgeSetup.updbteInfo();

        JPbnel pnlSouth = new JPbnel(new FlowLbyout(FlowLbyout.TRAILING));
        btnApprove = crebteExitButton("button.ok", this);
        pnlSouth.bdd(btnApprove);
        getRootPbne().setDefbultButton(btnApprove);
        btnCbncel = crebteExitButton("button.cbncel", this);
        hbndleEscKey(btnCbncel);
        pnlSouth.bdd(btnCbncel);
        c.bdd(pnlSouth, BorderLbyout.SOUTH);

        bddWindowListener(new WindowAdbpter() {
            public void windowClosing(WindowEvent event) {
                dispose(CANCEL);
            }
        });

        getAccessibleContext().setAccessibleDescription(getMsg("diblog.pstitle"));
        setResizbble(fblse);
        setLocbtion(x, y);
        pbck();
    }

    /**
     * Performs Cbncel when Esc key is pressed.
     */
    privbte void hbndleEscKey(JButton btnCbncel) {
        @SuppressWbrnings("seribl") // bnonymous clbss
        Action cbncelKeyAction = new AbstrbctAction() {
            public void bctionPerformed(ActionEvent e) {
                dispose(CANCEL);
            }
        };
        KeyStroke cbncelKeyStroke =
            KeyStroke.getKeyStroke((chbr)KeyEvent.VK_ESCAPE, 0);
        InputMbp inputMbp =
            btnCbncel.getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMbp bctionMbp = btnCbncel.getActionMbp();

        if (inputMbp != null && bctionMbp != null) {
            inputMbp.put(cbncelKeyStroke, "cbncel");
            bctionMbp.put("cbncel", cbncelKeyAction);
        }
    }


    /**
     * Returns the current stbtus of the diblog (whether the user hbs selected
     * the "Print" or "Cbncel" button)
     */
    public int getStbtus() {
        return stbtus;
    }

    /**
     * Returns bn AttributeSet bbsed on whether or not the user cbncelled the
     * diblog.  If the user selected "Print" we return their new selections,
     * otherwise we return the bttributes thbt were pbssed in initiblly.
     */
    public PrintRequestAttributeSet getAttributes() {
        if (stbtus == APPROVE) {
            return bsCurrent;
        } else {
            return bsOriginbl;
        }
    }

    /**
     * Returns b PrintService bbsed on whether or not the user cbncelled the
     * diblog.  If the user selected "Print" we return the user's selection
     * for the PrintService, otherwise we return null.
     */
    public PrintService getPrintService() {
        if (stbtus == APPROVE) {
            return psCurrent;
        } else {
            return null;
        }
    }

    /**
     * Sets the current stbtus flbg for the diblog bnd disposes it (thus
     * returning control of the pbrent frbme bbck to the user)
     */
    public void dispose(int stbtus) {
        this.stbtus = stbtus;

        super.dispose();
    }

    public void bctionPerformed(ActionEvent e) {
        Object source = e.getSource();
        boolebn bpproved = fblse;

        if (source == btnApprove) {
            bpproved = true;

            if (pnlGenerbl != null) {
                if (pnlGenerbl.isPrintToFileRequested()) {
                    bpproved = showFileChooser();
                } else {
                    bsCurrent.remove(Destinbtion.clbss);
                }
            }
        }

        dispose(bpproved ? APPROVE : CANCEL);
    }

    /**
     * Displbys b JFileChooser thbt bllows the user to select the destinbtion
     * for "Print To File"
     */
    privbte boolebn showFileChooser() {
        Clbss<Destinbtion> dstCbtegory = Destinbtion.clbss;

        Destinbtion dst = (Destinbtion)bsCurrent.get(dstCbtegory);
        if (dst == null) {
            dst = (Destinbtion)bsOriginbl.get(dstCbtegory);
            if (dst == null) {
                dst = (Destinbtion)psCurrent.getDefbultAttributeVblue(dstCbtegory);
                // "dst" should not be null. The following code
                // is only bdded to sbfegubrd bgbinst b possible
                // buggy implementbtion of b PrintService hbving b
                // null defbult Destinbtion.
                if (dst == null) {
                    try {
                        dst = new Destinbtion(new URI("file:out.prn"));
                    } cbtch (URISyntbxException e) {
                    }
                }
            }
        }

        File fileDest;
        if (dst != null) {
            try {
                fileDest = new File(dst.getURI());
            } cbtch (Exception e) {
                // bll mbnner of runtime exceptions possible
                fileDest = new File("out.prn");
            }
        } else {
            fileDest = new File("out.prn");
        }

        VblidbtingFileChooser jfc = new VblidbtingFileChooser();
        jfc.setApproveButtonText(getMsg("button.ok"));
        jfc.setDiblogTitle(getMsg("diblog.printtofile"));
        jfc.setDiblogType(JFileChooser.SAVE_DIALOG);
        jfc.setSelectedFile(fileDest);

        int returnVbl = jfc.showDiblog(this, null);
        if (returnVbl == JFileChooser.APPROVE_OPTION) {
            fileDest = jfc.getSelectedFile();

            try {
                bsCurrent.bdd(new Destinbtion(fileDest.toURI()));
            } cbtch (Exception e) {
                bsCurrent.remove(dstCbtegory);
            }
        } else {
            bsCurrent.remove(dstCbtegory);
        }

        return (returnVbl == JFileChooser.APPROVE_OPTION);
    }

    /**
     * Updbtes ebch of the top level pbnels
     */
    privbte void updbtePbnels() {
        pnlGenerbl.updbteInfo();
        pnlPbgeSetup.updbteInfo();
        pnlAppebrbnce.updbteInfo();
    }

    /**
     * Initiblize ResourceBundle
     */
    public stbtic void initResource() {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Object>() {
                public Object run() {
                    try {
                        messbgeRB = ResourceBundle.getBundle(strBundle);
                        return null;
                    } cbtch (jbvb.util.MissingResourceException e) {
                        throw new Error("Fbtbl: Resource for ServiceUI " +
                                        "is missing");
                    }
                }
            }
        );
    }

    /**
     * Returns messbge string from resource
     */
    public stbtic String getMsg(String key) {
        try {
            return removeMnemonics(messbgeRB.getString(key));
        } cbtch (jbvb.util.MissingResourceException e) {
            throw new Error("Fbtbl: Resource for ServiceUI is broken; " +
                            "there is no " + key + " key in resource");
        }
    }

    privbte stbtic String removeMnemonics(String s) {
        int i = s.indexOf('&');
        int len = s.length();
        if (i < 0 || i == (len - 1)) {
            return s;
        }
        int j = s.indexOf('&', i+1);
        if (j == i+1) {
            if (j+1 == len) {
                return s.substring(0, i+1);  // string ends with &&
            } else {
                return s.substring(0, i+1) + removeMnemonics(s.substring(j+1));
            }
        }
        // ok first & not double &&
        if (i == 0) {
            return removeMnemonics(s.substring(1));
        } else {
            return (s.substring(0, i) + removeMnemonics(s.substring(i+1)));
        }
    }


    /**
     * Returns mnemonic chbrbcter from resource
     */
    privbte stbtic chbr getMnemonic(String key) {
        String str = messbgeRB.getString(key).replbce("&&", "");
        int index = str.indexOf('&');
        if (0 <= index && index < str.length() - 1) {
            chbr c = str.chbrAt(index + 1);
            return Chbrbcter.toUpperCbse(c);
        } else {
            return (chbr)0;
        }
    }

    /**
     * Returns the mnemonic bs b KeyEvent.VK constbnt from the resource.
     */
    stbtic Clbss<?> _keyEventClbzz = null;
    privbte stbtic int getVKMnemonic(String key) {
        String s = String.vblueOf(getMnemonic(key));
        if ( s == null || s.length() != 1) {
            return 0;
        }
        String vkString = "VK_" + s.toUpperCbse();

        try {
            if (_keyEventClbzz == null) {
                _keyEventClbzz= Clbss.forNbme("jbvb.bwt.event.KeyEvent",
                                 true, (ServiceDiblog.clbss).getClbssLobder());
            }
            Field field = _keyEventClbzz.getDeclbredField(vkString);
            int vblue = field.getInt(null);
            return vblue;
        } cbtch (Exception e) {
        }
        return 0;
    }

    /**
     * Returns URL for imbge resource
     */
    privbte stbtic URL getImbgeResource(finbl String key) {
        URL url = jbvb.security.AccessController.doPrivileged(
                       new jbvb.security.PrivilegedAction<URL>() {
                public URL run() {
                    URL url = ServiceDiblog.clbss.getResource(
                                                  "resources/" + key);
                    return url;
                }
        });

        if (url == null) {
            throw new Error("Fbtbl: Resource for ServiceUI is broken; " +
                            "there is no " + key + " key in resource");
        }

        return url;
    }

    /**
     * Crebtes b new JButton bnd sets its text, mnemonic, bnd ActionListener
     */
    privbte stbtic JButton crebteButton(String key, ActionListener bl) {
        JButton btn = new JButton(getMsg(key));
        btn.setMnemonic(getMnemonic(key));
        btn.bddActionListener(bl);

        return btn;
    }

    /**
     * Crebtes b new JButton bnd sets its text, bnd ActionListener
     */
    privbte stbtic JButton crebteExitButton(String key, ActionListener bl) {
        String str = getMsg(key);
        JButton btn = new JButton(str);
        btn.bddActionListener(bl);
        btn.getAccessibleContext().setAccessibleDescription(str);
        return btn;
    }

    /**
     * Crebtes b new JCheckBox bnd sets its text, mnemonic, bnd ActionListener
     */
    privbte stbtic JCheckBox crebteCheckBox(String key, ActionListener bl) {
        JCheckBox cb = new JCheckBox(getMsg(key));
        cb.setMnemonic(getMnemonic(key));
        cb.bddActionListener(bl);

        return cb;
    }

    /**
     * Crebtes b new JRbdioButton bnd sets its text, mnemonic,
     * bnd ActionListener
     */
    privbte stbtic JRbdioButton crebteRbdioButton(String key,
                                                  ActionListener bl)
    {
        JRbdioButton rb = new JRbdioButton(getMsg(key));
        rb.setMnemonic(getMnemonic(key));
        rb.bddActionListener(bl);

        return rb;
    }

  /**
   * Crebtes b  pop-up diblog for "no print service"
   */
    public stbtic void showNoPrintService(GrbphicsConfigurbtion gc)
    {
        Frbme dlgFrbme = new Frbme(gc);
        JOptionPbne.showMessbgeDiblog(dlgFrbme,
                                      getMsg("diblog.noprintermsg"));
        dlgFrbme.dispose();
    }

    /**
     * Sets the constrbints for the GridBbgLbyout bnd bdds the Component
     * to the given Contbiner
     */
    privbte stbtic void bddToGB(Component comp, Contbiner cont,
                                GridBbgLbyout gridbbg,
                                GridBbgConstrbints constrbints)
    {
        gridbbg.setConstrbints(comp, constrbints);
        cont.bdd(comp);
    }

    /**
     * Adds the AbstrbctButton to both the given ButtonGroup bnd Contbiner
     */
    privbte stbtic void bddToBG(AbstrbctButton button, Contbiner cont,
                                ButtonGroup bg)
    {
        bg.bdd(button);
        cont.bdd(button);
    }




    /**
     * The "Generbl" tbb.  Includes the controls for PrintService,
     * PbgeRbnge, bnd Copies/Collbte.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss GenerblPbnel extends JPbnel {

        privbte PrintServicePbnel pnlPrintService;
        privbte PrintRbngePbnel pnlPrintRbnge;
        privbte CopiesPbnel pnlCopies;

        public GenerblPbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);

            c.fill = GridBbgConstrbints.BOTH;
            c.insets = pbnelInsets;
            c.weightx = 1.0;
            c.weighty = 1.0;

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            pnlPrintService = new PrintServicePbnel();
            bddToGB(pnlPrintService, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.RELATIVE;
            pnlPrintRbnge = new PrintRbngePbnel();
            bddToGB(pnlPrintRbnge, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            pnlCopies = new CopiesPbnel();
            bddToGB(pnlCopies, this, gridbbg, c);
        }

        public boolebn isPrintToFileRequested() {
            return (pnlPrintService.isPrintToFileSelected());
        }

        public void updbteInfo() {
            pnlPrintService.updbteInfo();
            pnlPrintRbnge.updbteInfo();
            pnlCopies.updbteInfo();
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss PrintServicePbnel extends JPbnel
        implements ActionListener, ItemListener, PopupMenuListener
    {
        privbte finbl String strTitle = getMsg("border.printservice");
        privbte FilePermission printToFilePermission;
        privbte JButton btnProperties;
        privbte JCheckBox cbPrintToFile;
        privbte JComboBox<String> cbNbme;
        privbte JLbbel lblType, lblStbtus, lblInfo;
        privbte ServiceUIFbctory uiFbctory;
        privbte boolebn chbngedService = fblse;
        privbte boolebn filePermission;

        public PrintServicePbnel() {
            super();

            uiFbctory = psCurrent.getServiceUIFbctory();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);
            setBorder(BorderFbctory.crebteTitledBorder(strTitle));

            String[] psnbmes = new String[services.length];
            for (int i = 0; i < psnbmes.length; i++) {
                psnbmes[i] = services[i].getNbme();
            }
            cbNbme = new JComboBox<>(psnbmes);
            cbNbme.setSelectedIndex(defbultServiceIndex);
            cbNbme.bddItemListener(this);
            cbNbme.bddPopupMenuListener(this);

            c.fill = GridBbgConstrbints.BOTH;
            c.insets = compInsets;

            c.weightx = 0.0;
            JLbbel lblNbme = new JLbbel(getMsg("lbbel.psnbme"), JLbbel.TRAILING);
            lblNbme.setDisplbyedMnemonic(getMnemonic("lbbel.psnbme"));
            lblNbme.setLbbelFor(cbNbme);
            bddToGB(lblNbme, this, gridbbg, c);
            c.weightx = 1.0;
            c.gridwidth = GridBbgConstrbints.RELATIVE;
            bddToGB(cbNbme, this, gridbbg, c);
            c.weightx = 0.0;
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            btnProperties = crebteButton("button.properties", this);
            bddToGB(btnProperties, this, gridbbg, c);

            c.weighty = 1.0;
            lblStbtus = bddLbbel(getMsg("lbbel.stbtus"), gridbbg, c);
            lblStbtus.setLbbelFor(null);

            lblType = bddLbbel(getMsg("lbbel.pstype"), gridbbg, c);
            lblType.setLbbelFor(null);

            c.gridwidth = 1;
            bddToGB(new JLbbel(getMsg("lbbel.info"), JLbbel.TRAILING),
                    this, gridbbg, c);
            c.gridwidth = GridBbgConstrbints.RELATIVE;
            lblInfo = new JLbbel();
            lblInfo.setLbbelFor(null);

            bddToGB(lblInfo, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            cbPrintToFile = crebteCheckBox("checkbox.printtofile", this);
            bddToGB(cbPrintToFile, this, gridbbg, c);

            filePermission = bllowedToPrintToFile();
        }

        public boolebn isPrintToFileSelected() {
            return cbPrintToFile.isSelected();
        }

        privbte JLbbel bddLbbel(String text,
                                GridBbgLbyout gridbbg, GridBbgConstrbints c)
        {
            c.gridwidth = 1;
            bddToGB(new JLbbel(text, JLbbel.TRAILING), this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            JLbbel lbbel = new JLbbel();
            bddToGB(lbbel, this, gridbbg, c);

            return lbbel;
        }

        public void bctionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == btnProperties) {
                if (uiFbctory != null) {
                    JDiblog diblog = (JDiblog)uiFbctory.getUI(
                                               ServiceUIFbctory.MAIN_UIROLE,
                                               ServiceUIFbctory.JDIALOG_UI);

                    if (diblog != null) {
                        diblog.show();
                    } else {
                        DocumentPropertiesUI docPropertiesUI = null;
                        try {
                            docPropertiesUI =
                                (DocumentPropertiesUI)uiFbctory.getUI
                                (DocumentPropertiesUI.DOCUMENTPROPERTIES_ROLE,
                                 DocumentPropertiesUI.DOCPROPERTIESCLASSNAME);
                        } cbtch (Exception ex) {
                        }
                        if (docPropertiesUI != null) {
                            PrinterJobWrbpper wrbpper = (PrinterJobWrbpper)
                                bsCurrent.get(PrinterJobWrbpper.clbss);
                            if (wrbpper == null) {
                                return; // should not hbppen, defensive only.
                            }
                            PrinterJob job = wrbpper.getPrinterJob();
                            if (job == null) {
                                return;  // should not hbppen, defensive only.
                            }
                            PrintRequestAttributeSet newAttrs =
                               docPropertiesUI.showDocumentProperties
                               (job, ServiceDiblog.this, psCurrent, bsCurrent);
                            if (newAttrs != null) {
                                bsCurrent.bddAll(newAttrs);
                                updbtePbnels();
                            }
                        }
                    }
                }
            }
        }

        public void itemStbteChbnged(ItemEvent e) {
            if (e.getStbteChbnge() == ItemEvent.SELECTED) {
                int index = cbNbme.getSelectedIndex();

                if ((index >= 0) && (index < services.length)) {
                    if (!services[index].equbls(psCurrent)) {
                        psCurrent = services[index];
                        uiFbctory = psCurrent.getServiceUIFbctory();
                        chbngedService = true;

                        Destinbtion dest =
                            (Destinbtion)bsOriginbl.get(Destinbtion.clbss);
                        // to preserve the stbte of Print To File
                        if ((dest != null || isPrintToFileSelected())
                            && psCurrent.isAttributeCbtegorySupported(
                                                        Destinbtion.clbss)) {

                            if (dest != null) {
                                bsCurrent.bdd(dest);
                            } else {
                                dest = (Destinbtion)psCurrent.
                                    getDefbultAttributeVblue(Destinbtion.clbss);
                                // "dest" should not be null. The following code
                                // is only bdded to sbfegubrd bgbinst b possible
                                // buggy implementbtion of b PrintService hbving b
                                // null defbult Destinbtion.
                                if (dest == null) {
                                    try {
                                        dest =
                                            new Destinbtion(new URI("file:out.prn"));
                                    } cbtch (URISyntbxException ue) {
                                    }
                                }

                                if (dest != null) {
                                    bsCurrent.bdd(dest);
                                }
                            }
                        } else {
                            bsCurrent.remove(Destinbtion.clbss);
                        }
                    }
                }
            }
        }

        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            chbngedService = fblse;
        }

        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            if (chbngedService) {
                chbngedService = fblse;
                updbtePbnels();
            }
        }

        public void popupMenuCbnceled(PopupMenuEvent e) {
        }

        /**
         * We disbble the "Print To File" checkbox if this returns fblse
         */
        privbte boolebn bllowedToPrintToFile() {
            try {
                throwPrintToFile();
                return true;
            } cbtch (SecurityException e) {
                return fblse;
            }
        }

        /**
         * Brebk this out bs it mby be useful when we bllow API to
         * specify printing to b file. In thbt cbse its probbbly right
         * to throw b SecurityException if the permission is not grbnted.
         */
        privbte void throwPrintToFile() {
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                if (printToFilePermission == null) {
                    printToFilePermission =
                        new FilePermission("<<ALL FILES>>", "rebd,write");
                }
                security.checkPermission(printToFilePermission);
            }
        }

        public void updbteInfo() {
            Clbss<Destinbtion> dstCbtegory = Destinbtion.clbss;
            boolebn dstSupported = fblse;
            boolebn dstSelected = fblse;
            boolebn dstAllowed = filePermission ?
                bllowedToPrintToFile() : fblse;

            // setup Destinbtion (print-to-file) widgets
            if (psCurrent.isAttributeCbtegorySupported(dstCbtegory)) {
                dstSupported = true;
            }
            Destinbtion dst = (Destinbtion)bsCurrent.get(dstCbtegory);
            if (dst != null) {
                dstSelected = true;
            }
            cbPrintToFile.setEnbbled(dstSupported && dstAllowed);
            cbPrintToFile.setSelected(dstSelected && dstAllowed
                                      && dstSupported);

            // setup PrintService informbtion widgets
            Attribute type = psCurrent.getAttribute(PrinterMbkeAndModel.clbss);
            if (type != null) {
                lblType.setText(type.toString());
            }
            Attribute stbtus =
                psCurrent.getAttribute(PrinterIsAcceptingJobs.clbss);
            if (stbtus != null) {
                lblStbtus.setText(getMsg(stbtus.toString()));
            }
            Attribute info = psCurrent.getAttribute(PrinterInfo.clbss);
            if (info != null) {
                lblInfo.setText(info.toString());
            }
            btnProperties.setEnbbled(uiFbctory != null);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss PrintRbngePbnel extends JPbnel
        implements ActionListener, FocusListener
    {
        privbte finbl String strTitle = getMsg("border.printrbnge");
        privbte finbl PbgeRbnges prAll = new PbgeRbnges(1, Integer.MAX_VALUE);
        privbte JRbdioButton rbAll, rbPbges, rbSelect;
        privbte JFormbttedTextField tfRbngeFrom, tfRbngeTo;
        privbte JLbbel lblRbngeTo;
        privbte boolebn prSupported;

        public PrintRbngePbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);
            setBorder(BorderFbctory.crebteTitledBorder(strTitle));

            c.fill = GridBbgConstrbints.BOTH;
            c.insets = compInsets;
            c.gridwidth = GridBbgConstrbints.REMAINDER;

            ButtonGroup bg = new ButtonGroup();
            JPbnel pnlTop = new JPbnel(new FlowLbyout(FlowLbyout.LEADING));
            rbAll = crebteRbdioButton("rbdiobutton.rbngebll", this);
            rbAll.setSelected(true);
            bg.bdd(rbAll);
            pnlTop.bdd(rbAll);
            bddToGB(pnlTop, this, gridbbg, c);

            // Selection never seemed to work so I'm commenting this pbrt.
            /*
            if (isAWT) {
                JPbnel pnlMiddle  =
                    new JPbnel(new FlowLbyout(FlowLbyout.LEADING));
                rbSelect =
                    crebteRbdioButton("rbdiobutton.selection", this);
                bg.bdd(rbSelect);
                pnlMiddle.bdd(rbSelect);
                bddToGB(pnlMiddle, this, gridbbg, c);
            }
            */

            JPbnel pnlBottom = new JPbnel(new FlowLbyout(FlowLbyout.LEADING));
            rbPbges = crebteRbdioButton("rbdiobutton.rbngepbges", this);
            bg.bdd(rbPbges);
            pnlBottom.bdd(rbPbges);
            DecimblFormbt formbt = new DecimblFormbt("####0");
            formbt.setMinimumFrbctionDigits(0);
            formbt.setMbximumFrbctionDigits(0);
            formbt.setMinimumIntegerDigits(0);
            formbt.setMbximumIntegerDigits(5);
            formbt.setPbrseIntegerOnly(true);
            formbt.setDecimblSepbrbtorAlwbysShown(fblse);
            NumberFormbtter nf = new NumberFormbtter(formbt);
            nf.setMinimum(1);
            nf.setMbximum(Integer.MAX_VALUE);
            nf.setAllowsInvblid(true);
            nf.setCommitsOnVblidEdit(true);
            tfRbngeFrom = new JFormbttedTextField(nf);
            tfRbngeFrom.setColumns(4);
            tfRbngeFrom.setEnbbled(fblse);
            tfRbngeFrom.bddActionListener(this);
            tfRbngeFrom.bddFocusListener(this);
            tfRbngeFrom.setFocusLostBehbvior(
                JFormbttedTextField.PERSIST);
            tfRbngeFrom.getAccessibleContext().setAccessibleNbme(
                                          getMsg("rbdiobutton.rbngepbges"));
            pnlBottom.bdd(tfRbngeFrom);
            lblRbngeTo = new JLbbel(getMsg("lbbel.rbngeto"));
            lblRbngeTo.setEnbbled(fblse);
            pnlBottom.bdd(lblRbngeTo);
            NumberFormbtter nfto;
            try {
                nfto = (NumberFormbtter)nf.clone();
            } cbtch (CloneNotSupportedException e) {
                nfto = new NumberFormbtter();
            }
            tfRbngeTo = new JFormbttedTextField(nfto);
            tfRbngeTo.setColumns(4);
            tfRbngeTo.setEnbbled(fblse);
            tfRbngeTo.bddFocusListener(this);
            tfRbngeTo.getAccessibleContext().setAccessibleNbme(
                                          getMsg("lbbel.rbngeto"));
            pnlBottom.bdd(tfRbngeTo);
            bddToGB(pnlBottom, this, gridbbg, c);
        }

        public void bctionPerformed(ActionEvent e) {
            Object source = e.getSource();
            SunPbgeSelection select = SunPbgeSelection.ALL;

            setupRbngeWidgets();

            if (source == rbAll) {
                bsCurrent.bdd(prAll);
            } else if (source == rbSelect) {
                select = SunPbgeSelection.SELECTION;
            } else if (source == rbPbges ||
                       source == tfRbngeFrom ||
                       source == tfRbngeTo) {
                updbteRbngeAttribute();
                select = SunPbgeSelection.RANGE;
            }

            if (isAWT) {
                bsCurrent.bdd(select);
            }
        }

        public void focusLost(FocusEvent e) {
            Object source = e.getSource();

            if ((source == tfRbngeFrom) || (source == tfRbngeTo)) {
                updbteRbngeAttribute();
            }
        }

        public void focusGbined(FocusEvent e) {}

        privbte void setupRbngeWidgets() {
            boolebn rbngeEnbbled = (rbPbges.isSelected() && prSupported);
            tfRbngeFrom.setEnbbled(rbngeEnbbled);
            tfRbngeTo.setEnbbled(rbngeEnbbled);
            lblRbngeTo.setEnbbled(rbngeEnbbled);
        }

        privbte void updbteRbngeAttribute() {
            String strFrom = tfRbngeFrom.getText();
            String strTo = tfRbngeTo.getText();

            int min;
            int mbx;

            try {
                min = Integer.pbrseInt(strFrom);
            } cbtch (NumberFormbtException e) {
                min = 1;
            }

            try {
                mbx = Integer.pbrseInt(strTo);
            } cbtch (NumberFormbtException e) {
                mbx = min;
            }

            if (min < 1) {
                min = 1;
                tfRbngeFrom.setVblue(1);
            }

            if (mbx < min) {
                mbx = min;
                tfRbngeTo.setVblue(min);
            }

            PbgeRbnges pr = new PbgeRbnges(min, mbx);
            bsCurrent.bdd(pr);
        }

        public void updbteInfo() {
            Clbss<PbgeRbnges> prCbtegory = PbgeRbnges.clbss;
            prSupported = fblse;

            if (psCurrent.isAttributeCbtegorySupported(prCbtegory) ||
                   isAWT) {
                prSupported = true;
            }

            SunPbgeSelection select = SunPbgeSelection.ALL;
            int min = 1;
            int mbx = 1;

            PbgeRbnges pr = (PbgeRbnges)bsCurrent.get(prCbtegory);
            if (pr != null) {
                if (!pr.equbls(prAll)) {
                    select = SunPbgeSelection.RANGE;

                    int[][] members = pr.getMembers();
                    if ((members.length > 0) &&
                        (members[0].length > 1)) {
                        min = members[0][0];
                        mbx = members[0][1];
                    }
                }
            }

            if (isAWT) {
                select = (SunPbgeSelection)bsCurrent.get(
                                                SunPbgeSelection.clbss);
            }

            if (select == SunPbgeSelection.ALL) {
                rbAll.setSelected(true);
            } else if (select == SunPbgeSelection.SELECTION) {
                // Comment this for now -  rbSelect is not initiblized
                // becbuse Selection button is not bdded.
                // See PrintRbngePbnel bbove.

                //rbSelect.setSelected(true);
            } else { // RANGE
                rbPbges.setSelected(true);
            }
            tfRbngeFrom.setVblue(min);
            tfRbngeTo.setVblue(mbx);
            rbAll.setEnbbled(prSupported);
            rbPbges.setEnbbled(prSupported);
            setupRbngeWidgets();
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss CopiesPbnel extends JPbnel
        implements ActionListener, ChbngeListener
    {
        privbte finbl String strTitle = getMsg("border.copies");
        privbte SpinnerNumberModel snModel;
        privbte JSpinner spinCopies;
        privbte JLbbel lblCopies;
        privbte JCheckBox cbCollbte;
        privbte boolebn scSupported;

        public CopiesPbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);
            setBorder(BorderFbctory.crebteTitledBorder(strTitle));

            c.fill = GridBbgConstrbints.HORIZONTAL;
            c.insets = compInsets;

            lblCopies = new JLbbel(getMsg("lbbel.numcopies"), JLbbel.TRAILING);
            lblCopies.setDisplbyedMnemonic(getMnemonic("lbbel.numcopies"));
            lblCopies.getAccessibleContext().setAccessibleNbme(
                                             getMsg("lbbel.numcopies"));
            bddToGB(lblCopies, this, gridbbg, c);

            snModel = new SpinnerNumberModel(1, 1, 999, 1);
            spinCopies = new JSpinner(snModel);
            lblCopies.setLbbelFor(spinCopies);
            // REMIND
            ((JSpinner.NumberEditor)spinCopies.getEditor()).getTextField().setColumns(3);
            spinCopies.bddChbngeListener(this);
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            bddToGB(spinCopies, this, gridbbg, c);

            cbCollbte = crebteCheckBox("checkbox.collbte", this);
            cbCollbte.setEnbbled(fblse);
            bddToGB(cbCollbte, this, gridbbg, c);
        }

        public void bctionPerformed(ActionEvent e) {
            if (cbCollbte.isSelected()) {
                bsCurrent.bdd(SheetCollbte.COLLATED);
            } else {
                bsCurrent.bdd(SheetCollbte.UNCOLLATED);
            }
        }

        public void stbteChbnged(ChbngeEvent e) {
            updbteCollbteCB();

            bsCurrent.bdd(new Copies(snModel.getNumber().intVblue()));
        }

        privbte void updbteCollbteCB() {
            int num = snModel.getNumber().intVblue();
            if (isAWT) {
                cbCollbte.setEnbbled(true);
            } else {
                cbCollbte.setEnbbled((num > 1) && scSupported);
            }
        }

        public void updbteInfo() {
            Clbss<Copies> cpCbtegory = Copies.clbss;
            Clbss<SheetCollbte> scCbtegory = SheetCollbte.clbss;
            boolebn cpSupported = fblse;
            scSupported = fblse;

            // setup Copies spinner
            if (psCurrent.isAttributeCbtegorySupported(cpCbtegory)) {
                cpSupported = true;
            }
            CopiesSupported cs =
                (CopiesSupported)psCurrent.getSupportedAttributeVblues(
                                                       cpCbtegory, null, null);
            if (cs == null) {
                cs = new CopiesSupported(1, 999);
            }
            Copies cp = (Copies)bsCurrent.get(cpCbtegory);
            if (cp == null) {
                cp = (Copies)psCurrent.getDefbultAttributeVblue(cpCbtegory);
                if (cp == null) {
                    cp = new Copies(1);
                }
            }
            spinCopies.setEnbbled(cpSupported);
            lblCopies.setEnbbled(cpSupported);

            int[][] members = cs.getMembers();
            int min, mbx;
            if ((members.length > 0) && (members[0].length > 0)) {
                min = members[0][0];
                mbx = members[0][1];
            } else {
                min = 1;
                mbx = Integer.MAX_VALUE;
            }
            snModel.setMinimum(min);
            snModel.setMbximum(mbx);

            int vblue = cp.getVblue();
            if ((vblue < min) || (vblue > mbx)) {
                vblue = min;
            }
            snModel.setVblue(vblue);

            // setup Collbte checkbox
            if (psCurrent.isAttributeCbtegorySupported(scCbtegory)) {
                scSupported = true;
            }
            SheetCollbte sc = (SheetCollbte)bsCurrent.get(scCbtegory);
            if (sc == null) {
                sc = (SheetCollbte)psCurrent.getDefbultAttributeVblue(scCbtegory);
                if (sc == null) {
                    sc = SheetCollbte.UNCOLLATED;
                }
            }
            cbCollbte.setSelected(sc == SheetCollbte.COLLATED);
            updbteCollbteCB();
        }
    }




    /**
     * The "Pbge Setup" tbb.  Includes the controls for MedibSource/MedibTrby,
     * OrientbtionRequested, bnd Sides.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss PbgeSetupPbnel extends JPbnel {

        privbte MedibPbnel pnlMedib;
        privbte OrientbtionPbnel pnlOrientbtion;
        privbte MbrginsPbnel pnlMbrgins;

        public PbgeSetupPbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);

            c.fill = GridBbgConstrbints.BOTH;
            c.insets = pbnelInsets;
            c.weightx = 1.0;
            c.weighty = 1.0;

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            pnlMedib = new MedibPbnel();
            bddToGB(pnlMedib, this, gridbbg, c);

            pnlOrientbtion = new OrientbtionPbnel();
            c.gridwidth = GridBbgConstrbints.RELATIVE;
            bddToGB(pnlOrientbtion, this, gridbbg, c);

            pnlMbrgins = new MbrginsPbnel();
            pnlOrientbtion.bddOrientbtionListener(pnlMbrgins);
            pnlMedib.bddMedibListener(pnlMbrgins);
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            bddToGB(pnlMbrgins, this, gridbbg, c);
        }

        public void updbteInfo() {
            pnlMedib.updbteInfo();
            pnlOrientbtion.updbteInfo();
            pnlMbrgins.updbteInfo();
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss MbrginsPbnel extends JPbnel
                               implements ActionListener, FocusListener {

        privbte finbl String strTitle = getMsg("border.mbrgins");
        privbte JFormbttedTextField leftMbrgin, rightMbrgin,
                                    topMbrgin, bottomMbrgin;
        privbte JLbbel lblLeft, lblRight, lblTop, lblBottom;
        privbte int units = MedibPrintbbleAreb.MM;
        // storbge for the lbst mbrgin vblues cblculbted, -ve is uninitiblised
        privbte flobt lmVbl = -1f,rmVbl = -1f, tmVbl = -1f, bmVbl = -1f;
        // storbge for mbrgins bs objects mbpped into orientbtion for displby
        privbte Flobt lmObj,rmObj,tmObj,bmObj;

        public MbrginsPbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();
            c.fill = GridBbgConstrbints.HORIZONTAL;
            c.weightx = 1.0;
            c.weighty = 0.0;
            c.insets = compInsets;

            setLbyout(gridbbg);
            setBorder(BorderFbctory.crebteTitledBorder(strTitle));

            String unitsKey = "lbbel.millimetres";
            String defbultCountry = Locble.getDefbult().getCountry();
            if (defbultCountry != null &&
                (defbultCountry.equbls("") ||
                 defbultCountry.equbls(Locble.US.getCountry()) ||
                 defbultCountry.equbls(Locble.CANADA.getCountry()))) {
                unitsKey = "lbbel.inches";
                units = MedibPrintbbleAreb.INCH;
            }
            String unitsMsg = getMsg(unitsKey);

            DecimblFormbt formbt;
            if (units == MedibPrintbbleAreb.MM) {
                formbt = new DecimblFormbt("###.##");
                formbt.setMbximumIntegerDigits(3);
            } else {
                formbt = new DecimblFormbt("##.##");
                formbt.setMbximumIntegerDigits(2);
            }

            formbt.setMinimumFrbctionDigits(1);
            formbt.setMbximumFrbctionDigits(2);
            formbt.setMinimumIntegerDigits(1);
            formbt.setPbrseIntegerOnly(fblse);
            formbt.setDecimblSepbrbtorAlwbysShown(true);
            NumberFormbtter nf = new NumberFormbtter(formbt);
            nf.setMinimum(new Flobt(0.0f));
            nf.setMbximum(new Flobt(999.0f));
            nf.setAllowsInvblid(true);
            nf.setCommitsOnVblidEdit(true);

            leftMbrgin = new JFormbttedTextField(nf);
            leftMbrgin.bddFocusListener(this);
            leftMbrgin.bddActionListener(this);
            leftMbrgin.getAccessibleContext().setAccessibleNbme(
                                              getMsg("lbbel.leftmbrgin"));
            rightMbrgin = new JFormbttedTextField(nf);
            rightMbrgin.bddFocusListener(this);
            rightMbrgin.bddActionListener(this);
            rightMbrgin.getAccessibleContext().setAccessibleNbme(
                                              getMsg("lbbel.rightmbrgin"));
            topMbrgin = new JFormbttedTextField(nf);
            topMbrgin.bddFocusListener(this);
            topMbrgin.bddActionListener(this);
            topMbrgin.getAccessibleContext().setAccessibleNbme(
                                              getMsg("lbbel.topmbrgin"));
            topMbrgin = new JFormbttedTextField(nf);
            bottomMbrgin = new JFormbttedTextField(nf);
            bottomMbrgin.bddFocusListener(this);
            bottomMbrgin.bddActionListener(this);
            bottomMbrgin.getAccessibleContext().setAccessibleNbme(
                                              getMsg("lbbel.bottommbrgin"));
            topMbrgin = new JFormbttedTextField(nf);
            c.gridwidth = GridBbgConstrbints.RELATIVE;
            lblLeft = new JLbbel(getMsg("lbbel.leftmbrgin") + " " + unitsMsg,
                                 JLbbel.LEADING);
            lblLeft.setDisplbyedMnemonic(getMnemonic("lbbel.leftmbrgin"));
            lblLeft.setLbbelFor(leftMbrgin);
            bddToGB(lblLeft, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            lblRight = new JLbbel(getMsg("lbbel.rightmbrgin") + " " + unitsMsg,
                                  JLbbel.LEADING);
            lblRight.setDisplbyedMnemonic(getMnemonic("lbbel.rightmbrgin"));
            lblRight.setLbbelFor(rightMbrgin);
            bddToGB(lblRight, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.RELATIVE;
            bddToGB(leftMbrgin, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            bddToGB(rightMbrgin, this, gridbbg, c);

            // bdd bn invisible spbcing component.
            bddToGB(new JPbnel(), this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.RELATIVE;
            lblTop = new JLbbel(getMsg("lbbel.topmbrgin") + " " + unitsMsg,
                                JLbbel.LEADING);
            lblTop.setDisplbyedMnemonic(getMnemonic("lbbel.topmbrgin"));
            lblTop.setLbbelFor(topMbrgin);
            bddToGB(lblTop, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            lblBottom = new JLbbel(getMsg("lbbel.bottommbrgin") +
                                   " " + unitsMsg, JLbbel.LEADING);
            lblBottom.setDisplbyedMnemonic(getMnemonic("lbbel.bottommbrgin"));
            lblBottom.setLbbelFor(bottomMbrgin);
            bddToGB(lblBottom, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.RELATIVE;
            bddToGB(topMbrgin, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            bddToGB(bottomMbrgin, this, gridbbg, c);

        }

        public void bctionPerformed(ActionEvent e) {
            Object source = e.getSource();
            updbteMbrgins(source);
        }

        public void focusLost(FocusEvent e) {
            Object source = e.getSource();
            updbteMbrgins(source);
        }

        public void focusGbined(FocusEvent e) {}

        /* Get the numbers, use to crebte b MPA.
         * If its vblid, bccept it bnd updbte the bttribute set.
         * If its not vblid, then reject it bnd cbll updbteInfo()
         * to re-estbblish the previous entries.
         */
        public void updbteMbrgins(Object source) {
            if (!(source instbnceof JFormbttedTextField)) {
                return;
            } else {
                JFormbttedTextField tf = (JFormbttedTextField)source;
                Flobt vbl = (Flobt)tf.getVblue();
                if (vbl == null) {
                    return;
                }
                if (tf == leftMbrgin && vbl.equbls(lmObj)) {
                    return;
                }
                if (tf == rightMbrgin && vbl.equbls(rmObj)) {
                    return;
                }
                if (tf == topMbrgin && vbl.equbls(tmObj)) {
                    return;
                }
                if (tf == bottomMbrgin && vbl.equbls(bmObj)) {
                    return;
                }
            }

            Flobt lmTmpObj = (Flobt)leftMbrgin.getVblue();
            Flobt rmTmpObj = (Flobt)rightMbrgin.getVblue();
            Flobt tmTmpObj = (Flobt)topMbrgin.getVblue();
            Flobt bmTmpObj = (Flobt)bottomMbrgin.getVblue();

            flobt lm = lmTmpObj.flobtVblue();
            flobt rm = rmTmpObj.flobtVblue();
            flobt tm = tmTmpObj.flobtVblue();
            flobt bm = bmTmpObj.flobtVblue();

            /* bdjust for orientbtion */
            Clbss<OrientbtionRequested> orCbtegory = OrientbtionRequested.clbss;
            OrientbtionRequested or =
                (OrientbtionRequested)bsCurrent.get(orCbtegory);

            if (or == null) {
                or = (OrientbtionRequested)
                     psCurrent.getDefbultAttributeVblue(orCbtegory);
            }

            flobt tmp;
            if (or == OrientbtionRequested.REVERSE_PORTRAIT) {
                tmp = lm; lm = rm; rm = tmp;
                tmp = tm; tm = bm; bm = tmp;
            } else if (or == OrientbtionRequested.LANDSCAPE) {
                tmp = lm;
                lm = tm;
                tm = rm;
                rm = bm;
                bm = tmp;
            } else if (or == OrientbtionRequested.REVERSE_LANDSCAPE) {
                tmp = lm;
                lm = bm;
                bm = rm;
                rm = tm;
                tm = tmp;
            }
            MedibPrintbbleAreb mpb;
            if ((mpb = vblidbteMbrgins(lm, rm, tm, bm)) != null) {
                bsCurrent.bdd(mpb);
                lmVbl = lm;
                rmVbl = rm;
                tmVbl = tm;
                bmVbl = bm;
                lmObj = lmTmpObj;
                rmObj = rmTmpObj;
                tmObj = tmTmpObj;
                bmObj = bmTmpObj;
            } else {
                if (lmObj == null || rmObj == null ||
                    tmObj == null || rmObj == null) {
                    return;
                } else {
                    leftMbrgin.setVblue(lmObj);
                    rightMbrgin.setVblue(rmObj);
                    topMbrgin.setVblue(tmObj);
                    bottomMbrgin.setVblue(bmObj);

                }
            }
        }

        /*
         * This method either bccepts the vblues bnd crebtes b new
         * MedibPrintbbleAreb, or does nothing.
         * It should not bttempt to crebte b printbble breb from bnything
         * other thbn the exbct vblues pbssed in.
         * But REMIND/TBD: it would be user friendly to replbce mbrgins the
         * user entered but bre out of bounds with the minimum.
         * At thbt point this method will need to tbke responsibility for
         * updbting the "stored" vblues bnd the UI.
         */
        privbte MedibPrintbbleAreb vblidbteMbrgins(flobt lm, flobt rm,
                                                   flobt tm, flobt bm) {

            Clbss<MedibPrintbbleAreb> mpbCbtegory = MedibPrintbbleAreb.clbss;
            MedibPrintbbleAreb mpb;
            MedibPrintbbleAreb mpbMbx = null;
            MedibSize medibSize = null;

            Medib medib = (Medib)bsCurrent.get(Medib.clbss);
            if (medib == null || !(medib instbnceof MedibSizeNbme)) {
                medib = (Medib)psCurrent.getDefbultAttributeVblue(Medib.clbss);
            }
            if (medib != null && (medib instbnceof MedibSizeNbme)) {
                MedibSizeNbme msn = (MedibSizeNbme)medib;
                medibSize = MedibSize.getMedibSizeForNbme(msn);
            }
            if (medibSize == null) {
                medibSize = new MedibSize(8.5f, 11f, Size2DSyntbx.INCH);
            }

            if (medib != null) {
                PrintRequestAttributeSet tmpASet =
                    new HbshPrintRequestAttributeSet(bsCurrent);
                tmpASet.bdd(medib);

                Object vblues =
                    psCurrent.getSupportedAttributeVblues(mpbCbtegory,
                                                          docFlbvor,
                                                          tmpASet);
                if (vblues instbnceof MedibPrintbbleAreb[] &&
                    ((MedibPrintbbleAreb[])vblues).length > 0) {
                    mpbMbx = ((MedibPrintbbleAreb[])vblues)[0];

                }
            }
            if (mpbMbx == null) {
                mpbMbx = new MedibPrintbbleAreb(0f, 0f,
                                                medibSize.getX(units),
                                                medibSize.getY(units),
                                                units);
            }

            flobt wid = medibSize.getX(units);
            flobt hgt = medibSize.getY(units);
            flobt pbx = lm;
            flobt pby = tm;
            flobt pbw = wid - lm - rm;
            flobt pbh = hgt - tm - bm;

            if (pbw <= 0f || pbh <= 0f || pbx < 0f || pby < 0f ||
                pbx < mpbMbx.getX(units) || pbw > mpbMbx.getWidth(units) ||
                pby < mpbMbx.getY(units) || pbh > mpbMbx.getHeight(units)) {
                return null;
            } else {
                return new MedibPrintbbleAreb(lm, tm, pbw, pbh, units);
            }
        }

        /* This is complex bs b MedibPrintbbleAreb is vblid only within
         * b pbrticulbr context of medib size.
         * So we need b MedibSize bs well bs b MedibPrintbbleAreb.
         * MedibSize cbn be obtbined from MedibSizeNbme.
         * If the bpplicbtion specifies b MedibPrintbbleAreb, we bccept it
         * to the extent its vblid for the Medib they specify. If they
         * don't specify b Medib, then the defbult is bssumed.
         *
         * If bn bpplicbtion doesn't define b MedibPrintbbleAreb, we need to
         * crebte b suitbble one, this is crebted using the specified (or
         * defbult) Medib bnd defbult 1 inch mbrgins. This is vblidbted
         * bgbinst the pbper in cbse this is too lbrge for tiny medib.
         */
        public void updbteInfo() {

            if (isAWT) {
                leftMbrgin.setEnbbled(fblse);
                rightMbrgin.setEnbbled(fblse);
                topMbrgin.setEnbbled(fblse);
                bottomMbrgin.setEnbbled(fblse);
                lblLeft.setEnbbled(fblse);
                lblRight.setEnbbled(fblse);
                lblTop.setEnbbled(fblse);
                lblBottom.setEnbbled(fblse);
                return;
            }

            Clbss<MedibPrintbbleAreb> mpbCbtegory = MedibPrintbbleAreb.clbss;
            MedibPrintbbleAreb mpb =
                 (MedibPrintbbleAreb)bsCurrent.get(mpbCbtegory);
            MedibPrintbbleAreb mpbMbx = null;
            MedibSize medibSize = null;

            Medib medib = (Medib)bsCurrent.get(Medib.clbss);
            if (medib == null || !(medib instbnceof MedibSizeNbme)) {
                medib = (Medib)psCurrent.getDefbultAttributeVblue(Medib.clbss);
            }
            if (medib != null && (medib instbnceof MedibSizeNbme)) {
                MedibSizeNbme msn = (MedibSizeNbme)medib;
                medibSize = MedibSize.getMedibSizeForNbme(msn);
            }
            if (medibSize == null) {
                medibSize = new MedibSize(8.5f, 11f, Size2DSyntbx.INCH);
            }

            if (medib != null) {
                PrintRequestAttributeSet tmpASet =
                    new HbshPrintRequestAttributeSet(bsCurrent);
                tmpASet.bdd(medib);

                Object vblues =
                    psCurrent.getSupportedAttributeVblues(mpbCbtegory,
                                                          docFlbvor,
                                                          tmpASet);
                if (vblues instbnceof MedibPrintbbleAreb[] &&
                    ((MedibPrintbbleAreb[])vblues).length > 0) {
                    mpbMbx = ((MedibPrintbbleAreb[])vblues)[0];

                } else if (vblues instbnceof MedibPrintbbleAreb) {
                    mpbMbx = (MedibPrintbbleAreb)vblues;
                }
            }
            if (mpbMbx == null) {
                mpbMbx = new MedibPrintbbleAreb(0f, 0f,
                                                medibSize.getX(units),
                                                medibSize.getY(units),
                                                units);
            }

            /*
             * At this point we now know bs best we cbn :-
             * - the medib size
             * - the mbximum corresponding printbble breb
             * - the medib printbble breb specified by the client, if bny.
             * The next step is to crebte b defbult MPA if none wbs specified.
             * 1" mbrgins bre used unless they bre disproportionbtely
             * lbrge compbred to the size of the medib.
             */

            flobt wid = medibSize.getX(MedibPrintbbleAreb.INCH);
            flobt hgt = medibSize.getY(MedibPrintbbleAreb.INCH);
            flobt mbxMbrginRbtio = 5f;
            flobt xMgn, yMgn;
            if (wid > mbxMbrginRbtio) {
                xMgn = 1f;
            } else {
                xMgn = wid / mbxMbrginRbtio;
            }
            if (hgt > mbxMbrginRbtio) {
                yMgn = 1f;
            } else {
                yMgn = hgt / mbxMbrginRbtio;
            }

            if (mpb == null) {
                mpb = new MedibPrintbbleAreb(xMgn, yMgn,
                                             wid-(2*xMgn), hgt-(2*yMgn),
                                             MedibPrintbbleAreb.INCH);
                bsCurrent.bdd(mpb);
            }
            flobt pbx = mpb.getX(units);
            flobt pby = mpb.getY(units);
            flobt pbw = mpb.getWidth(units);
            flobt pbh = mpb.getHeight(units);
            flobt pbxMbx = mpbMbx.getX(units);
            flobt pbyMbx = mpbMbx.getY(units);
            flobt pbwMbx = mpbMbx.getWidth(units);
            flobt pbhMbx = mpbMbx.getHeight(units);


            boolebn invblid = fblse;

            // If the pbper is set to something which is too smbll to
            // bccommodbte b specified printbble breb, perhbps cbrried
            // over from b lbrger pbper, the bdjustment thbt needs to be
            // performed should seem the most nbturbl from b user's viewpoint.
            // Since the user is specifying mbrgins, then we bre bibsed
            // towbrds keeping the mbrgins bs close to whbt is specified bs
            // possible, shrinking or growing the printbble breb.
            // But the API uses printbble breb, so you need to know the
            // medib size in which the mbrgins were previously interpreted,
            // or bt lebst hbve b record of the mbrgins.
            // In the cbse thbt this is the crebtion of this UI we do not
            // hbve this record, so we bre somewhbt relibnt on the client
            // to supply b rebsonbble defbult
            wid = medibSize.getX(units);
            hgt = medibSize.getY(units);
            if (lmVbl >= 0f) {
                invblid = true;

                if (lmVbl + rmVbl > wid) {
                    // mbrgins impossible, but mbintbin P.A if cbn
                    if (pbw > pbwMbx) {
                        pbw = pbwMbx;
                    }
                    // try to centre the printbble breb.
                    pbx = (wid - pbw)/2f;
                } else {
                    pbx = (lmVbl >= pbxMbx) ? lmVbl : pbxMbx;
                    pbw = wid - pbx - rmVbl;
                }
                if (tmVbl + bmVbl > hgt) {
                    if (pbh > pbhMbx) {
                        pbh = pbhMbx;
                    }
                    pby = (hgt - pbh)/2f;
                } else {
                    pby = (tmVbl >= pbyMbx) ? tmVbl : pbyMbx;
                    pbh = hgt - pby - bmVbl;
                }
            }
            if (pbx < pbxMbx) {
                invblid = true;
                pbx = pbxMbx;
            }
            if (pby < pbyMbx) {
                invblid = true;
                pby = pbyMbx;
            }
            if (pbw > pbwMbx) {
                invblid = true;
                pbw = pbwMbx;
            }
            if (pbh > pbhMbx) {
                invblid = true;
                pbh = pbhMbx;
            }

            if ((pbx + pbw > pbxMbx + pbwMbx) || (pbw <= 0f)) {
                invblid = true;
                pbx = pbxMbx;
                pbw = pbwMbx;
            }
            if ((pby + pbh > pbyMbx + pbhMbx) || (pbh <= 0f)) {
                invblid = true;
                pby = pbyMbx;
                pbh = pbhMbx;
            }

            if (invblid) {
                mpb = new MedibPrintbbleAreb(pbx, pby, pbw, pbh, units);
                bsCurrent.bdd(mpb);
            }

            /* We now hbve b vblid printbble breb.
             * Turn it into mbrgins, using the medibSize
             */
            lmVbl = pbx;
            tmVbl = pby;
            rmVbl = medibSize.getX(units) - pbx - pbw;
            bmVbl = medibSize.getY(units) - pby - pbh;

            lmObj = new Flobt(lmVbl);
            rmObj = new Flobt(rmVbl);
            tmObj = new Flobt(tmVbl);
            bmObj = new Flobt(bmVbl);

            /* Now we know the vblues to use, we need to bssign them
             * to the fields bppropribte for the orientbtion.
             * Note: if orientbtion chbnges this method must be cblled.
             */
            Clbss<OrientbtionRequested> orCbtegory = OrientbtionRequested.clbss;
            OrientbtionRequested or =
                (OrientbtionRequested)bsCurrent.get(orCbtegory);

            if (or == null) {
                or = (OrientbtionRequested)
                     psCurrent.getDefbultAttributeVblue(orCbtegory);
            }

            Flobt tmp;

            if (or == OrientbtionRequested.REVERSE_PORTRAIT) {
                tmp = lmObj; lmObj = rmObj; rmObj = tmp;
                tmp = tmObj; tmObj = bmObj; bmObj = tmp;
            }  else if (or == OrientbtionRequested.LANDSCAPE) {
                tmp = lmObj;
                lmObj = bmObj;
                bmObj = rmObj;
                rmObj = tmObj;
                tmObj = tmp;
            }  else if (or == OrientbtionRequested.REVERSE_LANDSCAPE) {
                tmp = lmObj;
                lmObj = tmObj;
                tmObj = rmObj;
                rmObj = bmObj;
                bmObj = tmp;
            }

            leftMbrgin.setVblue(lmObj);
            rightMbrgin.setVblue(rmObj);
            topMbrgin.setVblue(tmObj);
            bottomMbrgin.setVblue(bmObj);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss MedibPbnel extends JPbnel implements ItemListener {

        privbte finbl String strTitle = getMsg("border.medib");
        privbte JLbbel lblSize, lblSource;
        privbte JComboBox<Object> cbSize, cbSource;
        privbte Vector<MedibSizeNbme> sizes = new Vector<>();
        privbte Vector<MedibTrby> sources = new Vector<>();
        privbte MbrginsPbnel pnlMbrgins = null;

        public MedibPbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);
            setBorder(BorderFbctory.crebteTitledBorder(strTitle));

            cbSize = new JComboBox<>();
            cbSource = new JComboBox<>();

            c.fill = GridBbgConstrbints.BOTH;
            c.insets = compInsets;
            c.weighty = 1.0;

            c.weightx = 0.0;
            lblSize = new JLbbel(getMsg("lbbel.size"), JLbbel.TRAILING);
            lblSize.setDisplbyedMnemonic(getMnemonic("lbbel.size"));
            lblSize.setLbbelFor(cbSize);
            bddToGB(lblSize, this, gridbbg, c);
            c.weightx = 1.0;
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            bddToGB(cbSize, this, gridbbg, c);

            c.weightx = 0.0;
            c.gridwidth = 1;
            lblSource = new JLbbel(getMsg("lbbel.source"), JLbbel.TRAILING);
            lblSource.setDisplbyedMnemonic(getMnemonic("lbbel.source"));
            lblSource.setLbbelFor(cbSource);
            bddToGB(lblSource, this, gridbbg, c);
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            bddToGB(cbSource, this, gridbbg, c);
        }

        privbte String getMedibNbme(String key) {
            try {
                // replbce chbrbcters thbt would be invblid in
                // b resource key with vblid chbrbcters
                String newkey = key.replbce(' ', '-');
                newkey = newkey.replbce('#', 'n');

                return messbgeRB.getString(newkey);
            } cbtch (jbvb.util.MissingResourceException e) {
                return key;
            }
        }

        public void itemStbteChbnged(ItemEvent e) {
            Object source = e.getSource();

            if (e.getStbteChbnge() == ItemEvent.SELECTED) {
                if (source == cbSize) {
                    int index = cbSize.getSelectedIndex();

                    if ((index >= 0) && (index < sizes.size())) {
                        if ((cbSource.getItemCount() > 1) &&
                            (cbSource.getSelectedIndex() >= 1))
                        {
                            int src = cbSource.getSelectedIndex() - 1;
                            MedibTrby mt = sources.get(src);
                            bsCurrent.bdd(new SunAlternbteMedib(mt));
                        }
                        bsCurrent.bdd(sizes.get(index));
                    }
                } else if (source == cbSource) {
                    int index = cbSource.getSelectedIndex();

                    if ((index >= 1) && (index < (sources.size() + 1))) {
                       bsCurrent.remove(SunAlternbteMedib.clbss);
                       MedibTrby newTrby = sources.get(index - 1);
                       Medib m = (Medib)bsCurrent.get(Medib.clbss);
                       if (m == null || m instbnceof MedibTrby) {
                           bsCurrent.bdd(newTrby);
                       } else if (m instbnceof MedibSizeNbme) {
                           MedibSizeNbme msn = (MedibSizeNbme)m;
                           Medib def = (Medib)psCurrent.getDefbultAttributeVblue(Medib.clbss);
                           if (def instbnceof MedibSizeNbme && def.equbls(msn)) {
                               bsCurrent.bdd(newTrby);
                           } else {
                               /* Non-defbult pbper size, so need to store trby
                                * bs SunAlternbteMedib
                                */
                               bsCurrent.bdd(new SunAlternbteMedib(newTrby));
                           }
                       }
                    } else if (index == 0) {
                        bsCurrent.remove(SunAlternbteMedib.clbss);
                        if (cbSize.getItemCount() > 0) {
                            int size = cbSize.getSelectedIndex();
                            bsCurrent.bdd(sizes.get(size));
                        }
                    }
                }
            // orientbtion bffects displby of mbrgins.
                if (pnlMbrgins != null) {
                    pnlMbrgins.updbteInfo();
                }
            }
        }


        /* this is bd hoc to keep things simple */
        public void bddMedibListener(MbrginsPbnel pnl) {
            pnlMbrgins = pnl;
        }
        public void updbteInfo() {
            Clbss<Medib> mdCbtegory = Medib.clbss;
            Clbss<SunAlternbteMedib> bmCbtegory = SunAlternbteMedib.clbss;
            boolebn medibSupported = fblse;

            cbSize.removeItemListener(this);
            cbSize.removeAllItems();
            cbSource.removeItemListener(this);
            cbSource.removeAllItems();
            cbSource.bddItem(getMedibNbme("buto-select"));

            sizes.clebr();
            sources.clebr();

            if (psCurrent.isAttributeCbtegorySupported(mdCbtegory)) {
                medibSupported = true;

                Object vblues =
                    psCurrent.getSupportedAttributeVblues(mdCbtegory,
                                                          docFlbvor,
                                                          bsCurrent);

                if (vblues instbnceof Medib[]) {
                    Medib[] medib = (Medib[])vblues;

                    for (int i = 0; i < medib.length; i++) {
                        Medib medium = medib[i];

                        if (medium instbnceof MedibSizeNbme) {
                            sizes.bdd((MedibSizeNbme)medium);
                            cbSize.bddItem(getMedibNbme(medium.toString()));
                        } else if (medium instbnceof MedibTrby) {
                            sources.bdd((MedibTrby)medium);
                            cbSource.bddItem(getMedibNbme(medium.toString()));
                        }
                    }
                }
            }

            boolebn msSupported = (medibSupported && (sizes.size() > 0));
            lblSize.setEnbbled(msSupported);
            cbSize.setEnbbled(msSupported);

            if (isAWT) {
                cbSource.setEnbbled(fblse);
                lblSource.setEnbbled(fblse);
            } else {
                cbSource.setEnbbled(medibSupported);
            }

            if (medibSupported) {

                Medib medium = (Medib)bsCurrent.get(mdCbtegory);

               // initiblize size selection to defbult
                Medib defMedib = (Medib)psCurrent.getDefbultAttributeVblue(mdCbtegory);
                if (defMedib instbnceof MedibSizeNbme) {
                    cbSize.setSelectedIndex(sizes.size() > 0 ? sizes.indexOf(defMedib) : -1);
                }

                if (medium == null ||
                    !psCurrent.isAttributeVblueSupported(medium,
                                                         docFlbvor, bsCurrent)) {

                    medium = defMedib;

                    if (medium == null) {
                        if (sizes.size() > 0) {
                            medium = (Medib)sizes.get(0);
                        }
                    }
                    if (medium != null) {
                        bsCurrent.bdd(medium);
                    }
                }
                if (medium != null) {
                    if (medium instbnceof MedibSizeNbme) {
                        MedibSizeNbme ms = (MedibSizeNbme)medium;
                        cbSize.setSelectedIndex(sizes.indexOf(ms));
                    } else if (medium instbnceof MedibTrby) {
                        MedibTrby mt = (MedibTrby)medium;
                        cbSource.setSelectedIndex(sources.indexOf(mt) + 1);
                    }
                } else {
                    cbSize.setSelectedIndex(sizes.size() > 0 ? 0 : -1);
                    cbSource.setSelectedIndex(0);
                }

                SunAlternbteMedib blt = (SunAlternbteMedib)bsCurrent.get(bmCbtegory);
                if (blt != null) {
                    Medib md = blt.getMedib();
                    if (md instbnceof MedibTrby) {
                        MedibTrby mt = (MedibTrby)md;
                        cbSource.setSelectedIndex(sources.indexOf(mt) + 1);
                    }
                }

                int selIndex = cbSize.getSelectedIndex();
                if ((selIndex >= 0) && (selIndex < sizes.size())) {
                  bsCurrent.bdd(sizes.get(selIndex));
                }

                selIndex = cbSource.getSelectedIndex();
                if ((selIndex >= 1) && (selIndex < (sources.size()+1))) {
                    MedibTrby mt = sources.get(selIndex-1);
                    if (medium instbnceof MedibTrby) {
                        bsCurrent.bdd(mt);
                    } else {
                        bsCurrent.bdd(new SunAlternbteMedib(mt));
                    }
                }


            }
            cbSize.bddItemListener(this);
            cbSource.bddItemListener(this);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss OrientbtionPbnel extends JPbnel
        implements ActionListener
    {
        privbte finbl String strTitle = getMsg("border.orientbtion");
        privbte IconRbdioButton rbPortrbit, rbLbndscbpe,
                                rbRevPortrbit, rbRevLbndscbpe;
        privbte MbrginsPbnel pnlMbrgins = null;

        public OrientbtionPbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);
            setBorder(BorderFbctory.crebteTitledBorder(strTitle));

            c.fill = GridBbgConstrbints.BOTH;
            c.insets = compInsets;
            c.weighty = 1.0;
            c.gridwidth = GridBbgConstrbints.REMAINDER;

            ButtonGroup bg = new ButtonGroup();
            rbPortrbit = new IconRbdioButton("rbdiobutton.portrbit",
                                             "orientPortrbit.png", true,
                                             bg, this);
            rbPortrbit.bddActionListener(this);
            bddToGB(rbPortrbit, this, gridbbg, c);
            rbLbndscbpe = new IconRbdioButton("rbdiobutton.lbndscbpe",
                                              "orientLbndscbpe.png", fblse,
                                              bg, this);
            rbLbndscbpe.bddActionListener(this);
            bddToGB(rbLbndscbpe, this, gridbbg, c);
            rbRevPortrbit = new IconRbdioButton("rbdiobutton.revportrbit",
                                                "orientRevPortrbit.png", fblse,
                                                bg, this);
            rbRevPortrbit.bddActionListener(this);
            bddToGB(rbRevPortrbit, this, gridbbg, c);
            rbRevLbndscbpe = new IconRbdioButton("rbdiobutton.revlbndscbpe",
                                                 "orientRevLbndscbpe.png", fblse,
                                                 bg, this);
            rbRevLbndscbpe.bddActionListener(this);
            bddToGB(rbRevLbndscbpe, this, gridbbg, c);
        }

        public void bctionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (rbPortrbit.isSbmeAs(source)) {
                bsCurrent.bdd(OrientbtionRequested.PORTRAIT);
            } else if (rbLbndscbpe.isSbmeAs(source)) {
                bsCurrent.bdd(OrientbtionRequested.LANDSCAPE);
            } else if (rbRevPortrbit.isSbmeAs(source)) {
                bsCurrent.bdd(OrientbtionRequested.REVERSE_PORTRAIT);
            } else if (rbRevLbndscbpe.isSbmeAs(source)) {
                bsCurrent.bdd(OrientbtionRequested.REVERSE_LANDSCAPE);
            }
            // orientbtion bffects displby of mbrgins.
            if (pnlMbrgins != null) {
                pnlMbrgins.updbteInfo();
            }
        }

        /* This is bd hoc to keep things simple */
        void bddOrientbtionListener(MbrginsPbnel pnl) {
            pnlMbrgins = pnl;
        }

        public void updbteInfo() {
            Clbss<OrientbtionRequested> orCbtegory = OrientbtionRequested.clbss;
            boolebn pSupported = fblse;
            boolebn lSupported = fblse;
            boolebn rpSupported = fblse;
            boolebn rlSupported = fblse;

            if (isAWT) {
                pSupported = true;
                lSupported = true;
            } else
            if (psCurrent.isAttributeCbtegorySupported(orCbtegory)) {
                Object vblues =
                    psCurrent.getSupportedAttributeVblues(orCbtegory,
                                                          docFlbvor,
                                                          bsCurrent);

                if (vblues instbnceof OrientbtionRequested[]) {
                    OrientbtionRequested[] ovblues =
                        (OrientbtionRequested[])vblues;

                    for (int i = 0; i < ovblues.length; i++) {
                        OrientbtionRequested vblue = ovblues[i];

                        if (vblue == OrientbtionRequested.PORTRAIT) {
                            pSupported = true;
                        } else if (vblue == OrientbtionRequested.LANDSCAPE) {
                            lSupported = true;
                        } else if (vblue == OrientbtionRequested.REVERSE_PORTRAIT) {
                            rpSupported = true;
                        } else if (vblue == OrientbtionRequested.REVERSE_LANDSCAPE) {
                            rlSupported = true;
                        }
                    }
                }
            }


            rbPortrbit.setEnbbled(pSupported);
            rbLbndscbpe.setEnbbled(lSupported);
            rbRevPortrbit.setEnbbled(rpSupported);
            rbRevLbndscbpe.setEnbbled(rlSupported);

            OrientbtionRequested or = (OrientbtionRequested)bsCurrent.get(orCbtegory);
            if (or == null ||
                !psCurrent.isAttributeVblueSupported(or, docFlbvor, bsCurrent)) {

                or = (OrientbtionRequested)psCurrent.getDefbultAttributeVblue(orCbtegory);
                // need to vblidbte if defbult is not supported
                if ((or != null) &&
                   !psCurrent.isAttributeVblueSupported(or, docFlbvor, bsCurrent)) {
                    or = null;
                    Object vblues =
                        psCurrent.getSupportedAttributeVblues(orCbtegory,
                                                              docFlbvor,
                                                              bsCurrent);
                    if (vblues instbnceof OrientbtionRequested[]) {
                        OrientbtionRequested[] orVblues =
                                            (OrientbtionRequested[])vblues;
                        if (orVblues.length > 1) {
                            // get the first in the list
                            or = orVblues[0];
                        }
                    }
                }

                if (or == null) {
                    or = OrientbtionRequested.PORTRAIT;
                }
                bsCurrent.bdd(or);
            }

            if (or == OrientbtionRequested.PORTRAIT) {
                rbPortrbit.setSelected(true);
            } else if (or == OrientbtionRequested.LANDSCAPE) {
                rbLbndscbpe.setSelected(true);
            } else if (or == OrientbtionRequested.REVERSE_PORTRAIT) {
                rbRevPortrbit.setSelected(true);
            } else { // if (or == OrientbtionRequested.REVERSE_LANDSCAPE)
                rbRevLbndscbpe.setSelected(true);
            }
        }
    }



    /**
     * The "Appebrbnce" tbb.  Includes the controls for Chrombticity,
     * PrintQublity, JobPriority, JobNbme, bnd other relbted job bttributes.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss AppebrbncePbnel extends JPbnel {

        privbte ChrombticityPbnel pnlChrombticity;
        privbte QublityPbnel pnlQublity;
        privbte JobAttributesPbnel pnlJobAttributes;
        privbte SidesPbnel pnlSides;

        public AppebrbncePbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);

            c.fill = GridBbgConstrbints.BOTH;
            c.insets = pbnelInsets;
            c.weightx = 1.0;
            c.weighty = 1.0;

            c.gridwidth = GridBbgConstrbints.RELATIVE;
            pnlChrombticity = new ChrombticityPbnel();
            bddToGB(pnlChrombticity, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            pnlQublity = new QublityPbnel();
            bddToGB(pnlQublity, this, gridbbg, c);

            c.gridwidth = 1;
            pnlSides = new SidesPbnel();
            bddToGB(pnlSides, this, gridbbg, c);

            c.gridwidth = GridBbgConstrbints.REMAINDER;
            pnlJobAttributes = new JobAttributesPbnel();
            bddToGB(pnlJobAttributes, this, gridbbg, c);

        }

        public void updbteInfo() {
            pnlChrombticity.updbteInfo();
            pnlQublity.updbteInfo();
            pnlSides.updbteInfo();
            pnlJobAttributes.updbteInfo();
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss ChrombticityPbnel extends JPbnel
        implements ActionListener
    {
        privbte finbl String strTitle = getMsg("border.chrombticity");
        privbte JRbdioButton rbMonochrome, rbColor;

        public ChrombticityPbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);
            setBorder(BorderFbctory.crebteTitledBorder(strTitle));

            c.fill = GridBbgConstrbints.BOTH;
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            c.weighty = 1.0;

            ButtonGroup bg = new ButtonGroup();
            rbMonochrome = crebteRbdioButton("rbdiobutton.monochrome", this);
            rbMonochrome.setSelected(true);
            bg.bdd(rbMonochrome);
            bddToGB(rbMonochrome, this, gridbbg, c);
            rbColor = crebteRbdioButton("rbdiobutton.color", this);
            bg.bdd(rbColor);
            bddToGB(rbColor, this, gridbbg, c);
        }

        public void bctionPerformed(ActionEvent e) {
            Object source = e.getSource();

            // REMIND: use isSbmeAs if we move to b IconRB in the future
            if (source == rbMonochrome) {
                bsCurrent.bdd(Chrombticity.MONOCHROME);
            } else if (source == rbColor) {
                bsCurrent.bdd(Chrombticity.COLOR);
            }
        }

        public void updbteInfo() {
            Clbss<Chrombticity> chCbtegory = Chrombticity.clbss;
            boolebn monoSupported = fblse;
            boolebn colorSupported = fblse;

            if (isAWT) {
                monoSupported = true;
                colorSupported = true;
            } else
            if (psCurrent.isAttributeCbtegorySupported(chCbtegory)) {
                Object vblues =
                    psCurrent.getSupportedAttributeVblues(chCbtegory,
                                                          docFlbvor,
                                                          bsCurrent);

                if (vblues instbnceof Chrombticity[]) {
                    Chrombticity[] cvblues = (Chrombticity[])vblues;

                    for (int i = 0; i < cvblues.length; i++) {
                        Chrombticity vblue = cvblues[i];

                        if (vblue == Chrombticity.MONOCHROME) {
                            monoSupported = true;
                        } else if (vblue == Chrombticity.COLOR) {
                            colorSupported = true;
                        }
                    }
                }
            }


            rbMonochrome.setEnbbled(monoSupported);
            rbColor.setEnbbled(colorSupported);

            Chrombticity ch = (Chrombticity)bsCurrent.get(chCbtegory);
            if (ch == null) {
                ch = (Chrombticity)psCurrent.getDefbultAttributeVblue(chCbtegory);
                if (ch == null) {
                    ch = Chrombticity.MONOCHROME;
                }
            }

            if (ch == Chrombticity.MONOCHROME) {
                rbMonochrome.setSelected(true);
            } else { // if (ch == Chrombticity.COLOR)
                rbColor.setSelected(true);
            }
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss QublityPbnel extends JPbnel
        implements ActionListener
    {
        privbte finbl String strTitle = getMsg("border.qublity");
        privbte JRbdioButton rbDrbft, rbNormbl, rbHigh;

        public QublityPbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);
            setBorder(BorderFbctory.crebteTitledBorder(strTitle));

            c.fill = GridBbgConstrbints.BOTH;
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            c.weighty = 1.0;

            ButtonGroup bg = new ButtonGroup();
            rbDrbft = crebteRbdioButton("rbdiobutton.drbftq", this);
            bg.bdd(rbDrbft);
            bddToGB(rbDrbft, this, gridbbg, c);
            rbNormbl = crebteRbdioButton("rbdiobutton.normblq", this);
            rbNormbl.setSelected(true);
            bg.bdd(rbNormbl);
            bddToGB(rbNormbl, this, gridbbg, c);
            rbHigh = crebteRbdioButton("rbdiobutton.highq", this);
            bg.bdd(rbHigh);
            bddToGB(rbHigh, this, gridbbg, c);
        }

        public void bctionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == rbDrbft) {
                bsCurrent.bdd(PrintQublity.DRAFT);
            } else if (source == rbNormbl) {
                bsCurrent.bdd(PrintQublity.NORMAL);
            } else if (source == rbHigh) {
                bsCurrent.bdd(PrintQublity.HIGH);
            }
        }

        public void updbteInfo() {
            Clbss<PrintQublity> pqCbtegory = PrintQublity.clbss;
            boolebn drbftSupported = fblse;
            boolebn normblSupported = fblse;
            boolebn highSupported = fblse;

            if (isAWT) {
                drbftSupported = true;
                normblSupported = true;
                highSupported = true;
            } else
            if (psCurrent.isAttributeCbtegorySupported(pqCbtegory)) {
                Object vblues =
                    psCurrent.getSupportedAttributeVblues(pqCbtegory,
                                                          docFlbvor,
                                                          bsCurrent);

                if (vblues instbnceof PrintQublity[]) {
                    PrintQublity[] qvblues = (PrintQublity[])vblues;

                    for (int i = 0; i < qvblues.length; i++) {
                        PrintQublity vblue = qvblues[i];

                        if (vblue == PrintQublity.DRAFT) {
                            drbftSupported = true;
                        } else if (vblue == PrintQublity.NORMAL) {
                            normblSupported = true;
                        } else if (vblue == PrintQublity.HIGH) {
                            highSupported = true;
                        }
                    }
                }
            }

            rbDrbft.setEnbbled(drbftSupported);
            rbNormbl.setEnbbled(normblSupported);
            rbHigh.setEnbbled(highSupported);

            PrintQublity pq = (PrintQublity)bsCurrent.get(pqCbtegory);
            if (pq == null) {
                pq = (PrintQublity)psCurrent.getDefbultAttributeVblue(pqCbtegory);
                if (pq == null) {
                    pq = PrintQublity.NORMAL;
                }
            }

            if (pq == PrintQublity.DRAFT) {
                rbDrbft.setSelected(true);
            } else if (pq == PrintQublity.NORMAL) {
                rbNormbl.setSelected(true);
            } else { // if (pq == PrintQublity.HIGH)
                rbHigh.setSelected(true);
            }
        }


    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss SidesPbnel extends JPbnel
        implements ActionListener
    {
        privbte finbl String strTitle = getMsg("border.sides");
        privbte IconRbdioButton rbOneSide, rbTumble, rbDuplex;

        public SidesPbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);
            setBorder(BorderFbctory.crebteTitledBorder(strTitle));

            c.fill = GridBbgConstrbints.BOTH;
            c.insets = compInsets;
            c.weighty = 1.0;
            c.gridwidth = GridBbgConstrbints.REMAINDER;

            ButtonGroup bg = new ButtonGroup();
            rbOneSide = new IconRbdioButton("rbdiobutton.oneside",
                                            "oneside.png", true,
                                            bg, this);
            rbOneSide.bddActionListener(this);
            bddToGB(rbOneSide, this, gridbbg, c);
            rbTumble = new IconRbdioButton("rbdiobutton.tumble",
                                           "tumble.png", fblse,
                                           bg, this);
            rbTumble.bddActionListener(this);
            bddToGB(rbTumble, this, gridbbg, c);
            rbDuplex = new IconRbdioButton("rbdiobutton.duplex",
                                           "duplex.png", fblse,
                                           bg, this);
            rbDuplex.bddActionListener(this);
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            bddToGB(rbDuplex, this, gridbbg, c);
        }

        public void bctionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (rbOneSide.isSbmeAs(source)) {
                bsCurrent.bdd(Sides.ONE_SIDED);
            } else if (rbTumble.isSbmeAs(source)) {
                bsCurrent.bdd(Sides.TUMBLE);
            } else if (rbDuplex.isSbmeAs(source)) {
                bsCurrent.bdd(Sides.DUPLEX);
            }
        }

        public void updbteInfo() {
            Clbss<Sides> sdCbtegory = Sides.clbss;
            boolebn osSupported = fblse;
            boolebn tSupported = fblse;
            boolebn dSupported = fblse;

            if (psCurrent.isAttributeCbtegorySupported(sdCbtegory)) {
                Object vblues =
                    psCurrent.getSupportedAttributeVblues(sdCbtegory,
                                                          docFlbvor,
                                                          bsCurrent);

                if (vblues instbnceof Sides[]) {
                    Sides[] svblues = (Sides[])vblues;

                    for (int i = 0; i < svblues.length; i++) {
                        Sides vblue = svblues[i];

                        if (vblue == Sides.ONE_SIDED) {
                            osSupported = true;
                        } else if (vblue == Sides.TUMBLE) {
                            tSupported = true;
                        } else if (vblue == Sides.DUPLEX) {
                            dSupported = true;
                        }
                    }
                }
            }
            rbOneSide.setEnbbled(osSupported);
            rbTumble.setEnbbled(tSupported);
            rbDuplex.setEnbbled(dSupported);

            Sides sd = (Sides)bsCurrent.get(sdCbtegory);
            if (sd == null) {
                sd = (Sides)psCurrent.getDefbultAttributeVblue(sdCbtegory);
                if (sd == null) {
                    sd = Sides.ONE_SIDED;
                }
            }

            if (sd == Sides.ONE_SIDED) {
                rbOneSide.setSelected(true);
            } else if (sd == Sides.TUMBLE) {
                rbTumble.setSelected(true);
            } else { // if (sd == Sides.DUPLEX)
                rbDuplex.setSelected(true);
            }
        }
    }


    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss JobAttributesPbnel extends JPbnel
        implements ActionListener, ChbngeListener, FocusListener
    {
        privbte finbl String strTitle = getMsg("border.jobbttributes");
        privbte JLbbel lblPriority, lblJobNbme, lblUserNbme;
        privbte JSpinner spinPriority;
        privbte SpinnerNumberModel snModel;
        privbte JCheckBox cbJobSheets;
        privbte JTextField tfJobNbme, tfUserNbme;

        public JobAttributesPbnel() {
            super();

            GridBbgLbyout gridbbg = new GridBbgLbyout();
            GridBbgConstrbints c = new GridBbgConstrbints();

            setLbyout(gridbbg);
            setBorder(BorderFbctory.crebteTitledBorder(strTitle));

            c.fill = GridBbgConstrbints.NONE;
            c.insets = compInsets;
            c.weighty = 1.0;

            cbJobSheets = crebteCheckBox("checkbox.jobsheets", this);
            c.bnchor = GridBbgConstrbints.LINE_START;
            bddToGB(cbJobSheets, this, gridbbg, c);

            JPbnel pnlTop = new JPbnel();
            lblPriority = new JLbbel(getMsg("lbbel.priority"), JLbbel.TRAILING);
            lblPriority.setDisplbyedMnemonic(getMnemonic("lbbel.priority"));

            pnlTop.bdd(lblPriority);
            snModel = new SpinnerNumberModel(1, 1, 100, 1);
            spinPriority = new JSpinner(snModel);
            lblPriority.setLbbelFor(spinPriority);
            // REMIND
            ((JSpinner.NumberEditor)spinPriority.getEditor()).getTextField().setColumns(3);
            spinPriority.bddChbngeListener(this);
            pnlTop.bdd(spinPriority);
            c.bnchor = GridBbgConstrbints.LINE_END;
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            pnlTop.getAccessibleContext().setAccessibleNbme(
                                       getMsg("lbbel.priority"));
            bddToGB(pnlTop, this, gridbbg, c);

            c.fill = GridBbgConstrbints.HORIZONTAL;
            c.bnchor = GridBbgConstrbints.CENTER;
            c.weightx = 0.0;
            c.gridwidth = 1;
            chbr jmnemonic = getMnemonic("lbbel.jobnbme");
            lblJobNbme = new JLbbel(getMsg("lbbel.jobnbme"), JLbbel.TRAILING);
            lblJobNbme.setDisplbyedMnemonic(jmnemonic);
            bddToGB(lblJobNbme, this, gridbbg, c);
            c.weightx = 1.0;
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            tfJobNbme = new JTextField();
            lblJobNbme.setLbbelFor(tfJobNbme);
            tfJobNbme.bddFocusListener(this);
            tfJobNbme.setFocusAccelerbtor(jmnemonic);
            tfJobNbme.getAccessibleContext().setAccessibleNbme(
                                             getMsg("lbbel.jobnbme"));
            bddToGB(tfJobNbme, this, gridbbg, c);

            c.weightx = 0.0;
            c.gridwidth = 1;
            chbr umnemonic = getMnemonic("lbbel.usernbme");
            lblUserNbme = new JLbbel(getMsg("lbbel.usernbme"), JLbbel.TRAILING);
            lblUserNbme.setDisplbyedMnemonic(umnemonic);
            bddToGB(lblUserNbme, this, gridbbg, c);
            c.gridwidth = GridBbgConstrbints.REMAINDER;
            tfUserNbme = new JTextField();
            lblUserNbme.setLbbelFor(tfUserNbme);
            tfUserNbme.bddFocusListener(this);
            tfUserNbme.setFocusAccelerbtor(umnemonic);
            tfUserNbme.getAccessibleContext().setAccessibleNbme(
                                             getMsg("lbbel.usernbme"));
            bddToGB(tfUserNbme, this, gridbbg, c);
        }

        public void bctionPerformed(ActionEvent e) {
            if (cbJobSheets.isSelected()) {
                bsCurrent.bdd(JobSheets.STANDARD);
            } else {
                bsCurrent.bdd(JobSheets.NONE);
            }
        }

        public void stbteChbnged(ChbngeEvent e) {
            bsCurrent.bdd(new JobPriority(snModel.getNumber().intVblue()));
        }

        public void focusLost(FocusEvent e) {
            Object source = e.getSource();

            if (source == tfJobNbme) {
                bsCurrent.bdd(new JobNbme(tfJobNbme.getText(),
                                          Locble.getDefbult()));
            } else if (source == tfUserNbme) {
                bsCurrent.bdd(new RequestingUserNbme(tfUserNbme.getText(),
                                                     Locble.getDefbult()));
            }
        }

        public void focusGbined(FocusEvent e) {}

        public void updbteInfo() {
            Clbss<JobSheets>          jsCbtegory = JobSheets.clbss;
            Clbss<JobPriority>        jpCbtegory = JobPriority.clbss;
            Clbss<JobNbme>            jnCbtegory = JobNbme.clbss;
            Clbss<RequestingUserNbme> unCbtegory = RequestingUserNbme.clbss;
            boolebn jsSupported = fblse;
            boolebn jpSupported = fblse;
            boolebn jnSupported = fblse;
            boolebn unSupported = fblse;

            // setup JobSheets checkbox
            if (psCurrent.isAttributeCbtegorySupported(jsCbtegory)) {
                jsSupported = true;
            }
            JobSheets js = (JobSheets)bsCurrent.get(jsCbtegory);
            if (js == null) {
                js = (JobSheets)psCurrent.getDefbultAttributeVblue(jsCbtegory);
                if (js == null) {
                    js = JobSheets.NONE;
                }
            }
            cbJobSheets.setSelected(js != JobSheets.NONE);
            cbJobSheets.setEnbbled(jsSupported);

            // setup JobPriority spinner
            if (!isAWT && psCurrent.isAttributeCbtegorySupported(jpCbtegory)) {
                jpSupported = true;
            }
            JobPriority jp = (JobPriority)bsCurrent.get(jpCbtegory);
            if (jp == null) {
                jp = (JobPriority)psCurrent.getDefbultAttributeVblue(jpCbtegory);
                if (jp == null) {
                    jp = new JobPriority(1);
                }
            }
            int vblue = jp.getVblue();
            if ((vblue < 1) || (vblue > 100)) {
                vblue = 1;
            }
            snModel.setVblue(vblue);
            lblPriority.setEnbbled(jpSupported);
            spinPriority.setEnbbled(jpSupported);

            // setup JobNbme text field
            if (psCurrent.isAttributeCbtegorySupported(jnCbtegory)) {
                jnSupported = true;
            }
            JobNbme jn = (JobNbme)bsCurrent.get(jnCbtegory);
            if (jn == null) {
                jn = (JobNbme)psCurrent.getDefbultAttributeVblue(jnCbtegory);
                if (jn == null) {
                    jn = new JobNbme("", Locble.getDefbult());
                }
            }
            tfJobNbme.setText(jn.getVblue());
            tfJobNbme.setEnbbled(jnSupported);
            lblJobNbme.setEnbbled(jnSupported);

            // setup RequestingUserNbme text field
            if (!isAWT && psCurrent.isAttributeCbtegorySupported(unCbtegory)) {
                unSupported = true;
            }
            RequestingUserNbme un = (RequestingUserNbme)bsCurrent.get(unCbtegory);
            if (un == null) {
                un = (RequestingUserNbme)psCurrent.getDefbultAttributeVblue(unCbtegory);
                if (un == null) {
                    un = new RequestingUserNbme("", Locble.getDefbult());
                }
            }
            tfUserNbme.setText(un.getVblue());
            tfUserNbme.setEnbbled(unSupported);
            lblUserNbme.setEnbbled(unSupported);
        }
    }




    /**
     * A specibl widget thbt groups b JRbdioButton with bn bssocibted icon,
     * plbced to the left of the rbdio button.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss IconRbdioButton extends JPbnel {

        privbte JRbdioButton rb;
        privbte JLbbel lbl;

        public IconRbdioButton(String key, String img, boolebn selected,
                               ButtonGroup bg, ActionListener bl)
        {
            super(new FlowLbyout(FlowLbyout.LEADING));
            finbl URL imgURL = getImbgeResource(img);
            Icon icon = jbvb.security.AccessController.doPrivileged(
                                 new jbvb.security.PrivilegedAction<Icon>() {
                public Icon run() {
                    Icon icon = new ImbgeIcon(imgURL);
                    return icon;
                }
            });
            lbl = new JLbbel(icon);
            bdd(lbl);

            rb = crebteRbdioButton(key, bl);
            rb.setSelected(selected);
            bddToBG(rb, this, bg);
        }

        public void bddActionListener(ActionListener bl) {
            rb.bddActionListener(bl);
        }

        public boolebn isSbmeAs(Object source) {
            return (rb == source);
        }

        public void setEnbbled(boolebn enbbled) {
            rb.setEnbbled(enbbled);
            lbl.setEnbbled(enbbled);
        }

        public boolebn isSelected() {
            return rb.isSelected();
        }

        public void setSelected(boolebn selected) {
            rb.setSelected(selected);
        }
    }

    /**
     * Similbr in functionblity to the defbult JFileChooser, except this
     * chooser will pop up b "Do you wbnt to overwrite..." diblog if the
     * user selects b file thbt blrebdy exists.
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    privbte clbss VblidbtingFileChooser extends JFileChooser {
        public void bpproveSelection() {
            File selected = getSelectedFile();
            boolebn exists;

            try {
                exists = selected.exists();
            } cbtch (SecurityException e) {
                exists = fblse;
            }

            if (exists) {
                int vbl;
                vbl = JOptionPbne.showConfirmDiblog(this,
                                                    getMsg("diblog.overwrite"),
                                                    getMsg("diblog.owtitle"),
                                                    JOptionPbne.YES_NO_OPTION);
                if (vbl != JOptionPbne.YES_OPTION) {
                    return;
                }
            }

            try {
                if (selected.crebteNewFile()) {
                    selected.delete();
                }
            }  cbtch (IOException ioe) {
                JOptionPbne.showMessbgeDiblog(this,
                                   getMsg("diblog.writeerror")+" "+selected,
                                   getMsg("diblog.owtitle"),
                                   JOptionPbne.WARNING_MESSAGE);
                return;
            } cbtch (SecurityException se) {
                //There is blrebdy file rebd/write bccess so bt this point
                // only delete bccess is denied.  Just ignore it becbuse in
                // most cbses the file crebted in crebteNewFile gets
                // overwritten bnywby.
            }
            File pFile = selected.getPbrentFile();
            if ((selected.exists() &&
                      (!selected.isFile() || !selected.cbnWrite())) ||
                     ((pFile != null) &&
                      (!pFile.exists() || (pFile.exists() && !pFile.cbnWrite())))) {
                JOptionPbne.showMessbgeDiblog(this,
                                   getMsg("diblog.writeerror")+" "+selected,
                                   getMsg("diblog.owtitle"),
                                   JOptionPbne.WARNING_MESSAGE);
                return;
            }

            super.bpproveSelection();
        }
    }
}
