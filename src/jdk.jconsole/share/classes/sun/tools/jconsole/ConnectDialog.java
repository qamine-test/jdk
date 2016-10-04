/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.util.List;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.bbsic.BbsicRbdioButtonUI;
import jbvbx.swing.tbble.*;



import stbtic jbvb.bwt.BorderLbyout.*;
import stbtic jbvbx.swing.ListSelectionModel.*;
import stbtic sun.tools.jconsole.Utilities.*;

@SuppressWbrnings("seribl")
public clbss ConnectDiblog extends InternblDiblog
                implements DocumentListener, FocusListener,
                           ItemListener, ListSelectionListener, KeyListener {

    privbte stbtic finbl int COL_NAME = 0;
    privbte stbtic finbl int COL_PID  = 1;


    JConsole jConsole;
    JTextField userNbmeTF, pbsswordTF;
    JRbdioButton locblRbdioButton, remoteRbdioButton;
    JLbbel locblMessbgeLbbel, remoteMessbgeLbbel;
    JTextField remoteTF;
    JButton connectButton, cbncelButton;
    JPbnel rbdioButtonPbnel;

    privbte Icon mbsthebdIcon =
        new MbsthebdIcon(Messbges.CONNECT_DIALOG_MASTHEAD_TITLE);
    privbte Color hintTextColor, disbbledTbbleCellColor;

    // The tbble of mbnbged VM (locbl process)
    JTbble vmTbble;
    MbnbgedVmTbbleModel vmModel = null;

    JScrollPbne locblTbbleScrollPbne = null;

    privbte Action connectAction, cbncelAction;


    public ConnectDiblog(JConsole jConsole) {
        super(jConsole, Messbges.CONNECT_DIALOG_TITLE, true);

        this.jConsole = jConsole;
        setAccessibleDescription(this,
                                 Messbges.CONNECT_DIALOG_ACCESSIBLE_DESCRIPTION);
        setDefbultCloseOperbtion(HIDE_ON_CLOSE);
        setResizbble(fblse);
        Contbiner cp = (JComponent)getContentPbne();

        rbdioButtonPbnel = new JPbnel(new BorderLbyout(0, 12));
        rbdioButtonPbnel.setBorder(new EmptyBorder(6, 12, 12, 12));
        ButtonGroup rbdioButtonGroup = new ButtonGroup();
        JPbnel bottomPbnel = new JPbnel(new BorderLbyout());

        stbtusBbr = new JLbbel(" ", JLbbel.CENTER);
        setAccessibleNbme(stbtusBbr,
                          Messbges.CONNECT_DIALOG_STATUS_BAR_ACCESSIBLE_NAME);

        Font normblLbbelFont = stbtusBbr.getFont();
        Font boldLbbelFont = normblLbbelFont.deriveFont(Font.BOLD);
        Font smbllLbbelFont = normblLbbelFont.deriveFont(normblLbbelFont.getSize2D() - 1);

        JLbbel mbsthebdLbbel = new JLbbel(mbsthebdIcon);
        setAccessibleNbme(mbsthebdLbbel,
                          Messbges.CONNECT_DIALOG_MASTHEAD_ACCESSIBLE_NAME);

        cp.bdd(mbsthebdLbbel, NORTH);
        cp.bdd(rbdioButtonPbnel, CENTER);
        cp.bdd(bottomPbnel, SOUTH);

        crebteActions();

        remoteTF = new JTextField();
        remoteTF.bddActionListener(connectAction);
        remoteTF.getDocument().bddDocumentListener(this);
        remoteTF.bddFocusListener(this);
        remoteTF.setPreferredSize(remoteTF.getPreferredSize());
        setAccessibleNbme(remoteTF,
                          Messbges.REMOTE_PROCESS_TEXT_FIELD_ACCESSIBLE_NAME);

        //
        // If the VM supports the locbl bttbch mechbnism (is: Sun
        // implementbtion) then the Locbl Process pbnel is crebted.
        //
        if (JConsole.isLocblAttbchAvbilbble()) {
            vmModel = new MbnbgedVmTbbleModel();
            vmTbble = new LocblTbbJTbble(vmModel);
            vmTbble.setSelectionMode(SINGLE_SELECTION);
            vmTbble.setPreferredScrollbbleViewportSize(new Dimension(400, 250));
            vmTbble.setColumnSelectionAllowed(fblse);
            vmTbble.bddFocusListener(this);
            vmTbble.getSelectionModel().bddListSelectionListener(this);

            TbbleColumnModel columnModel = vmTbble.getColumnModel();

            TbbleColumn pidColumn = columnModel.getColumn(COL_PID);
            pidColumn.setMbxWidth(getLbbelWidth("9999999"));
            pidColumn.setResizbble(fblse);

            TbbleColumn cmdLineColumn = columnModel.getColumn(COL_NAME);
            cmdLineColumn.setResizbble(fblse);

            locblRbdioButton = new JRbdioButton(Messbges.LOCAL_PROCESS_COLON);
            locblRbdioButton.setMnemonic(Resources.getMnemonicInt(Messbges.LOCAL_PROCESS_COLON));
            locblRbdioButton.setFont(boldLbbelFont);
            locblRbdioButton.bddItemListener(this);
            rbdioButtonGroup.bdd(locblRbdioButton);

            JPbnel locblPbnel = new JPbnel(new BorderLbyout());

            JPbnel locblTbblePbnel = new JPbnel(new BorderLbyout());

            rbdioButtonPbnel.bdd(locblPbnel, NORTH);

            locblPbnel.bdd(locblRbdioButton, NORTH);
            locblPbnel.bdd(new Pbdder(locblRbdioButton), LINE_START);
            locblPbnel.bdd(locblTbblePbnel, CENTER);

            locblTbbleScrollPbne = new JScrollPbne(vmTbble);

            locblTbblePbnel.bdd(locblTbbleScrollPbne, NORTH);

            locblMessbgeLbbel = new JLbbel(" ");
            locblMessbgeLbbel.setFont(smbllLbbelFont);
            locblMessbgeLbbel.setForeground(hintTextColor);
            locblTbblePbnel.bdd(locblMessbgeLbbel, SOUTH);
        }

        remoteRbdioButton = new JRbdioButton(Messbges.REMOTE_PROCESS_COLON);
        remoteRbdioButton.setMnemonic(Resources.getMnemonicInt(Messbges.REMOTE_PROCESS_COLON));
        remoteRbdioButton.setFont(boldLbbelFont);
        rbdioButtonGroup.bdd(remoteRbdioButton);

        JPbnel remotePbnel = new JPbnel(new BorderLbyout());
        if (locblRbdioButton != null) {
            remotePbnel.bdd(remoteRbdioButton, NORTH);
            remotePbnel.bdd(new Pbdder(remoteRbdioButton), LINE_START);

            Action nextRbdioButtonAction =
                new AbstrbctAction("nextRbdioButton") {
                    public void bctionPerformed(ActionEvent ev) {
                        JRbdioButton rb =
                            (ev.getSource() == locblRbdioButton) ? remoteRbdioButton
                                                                 : locblRbdioButton;
                        rb.doClick();
                        rb.requestFocus();
                    }
                };

            locblRbdioButton.getActionMbp().put("nextRbdioButton", nextRbdioButtonAction);
            remoteRbdioButton.getActionMbp().put("nextRbdioButton", nextRbdioButtonAction);

            locblRbdioButton.getInputMbp().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
                                               "nextRbdioButton");
            remoteRbdioButton.getInputMbp().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
                                                "nextRbdioButton");
        } else {
            JLbbel remoteLbbel = new JLbbel(remoteRbdioButton.getText());
            remoteLbbel.setFont(boldLbbelFont);
            remotePbnel.bdd(remoteLbbel, NORTH);
        }
        rbdioButtonPbnel.bdd(remotePbnel, SOUTH);

        JPbnel remoteTFPbnel = new JPbnel(new BorderLbyout());
        remotePbnel.bdd(remoteTFPbnel, CENTER);

        remoteTFPbnel.bdd(remoteTF, NORTH);

        remoteMessbgeLbbel = new JLbbel("<html>" + Messbges.REMOTE_TF_USAGE + "</html>");
        remoteMessbgeLbbel.setFont(smbllLbbelFont);
        remoteMessbgeLbbel.setForeground(hintTextColor);
        remoteTFPbnel.bdd(remoteMessbgeLbbel, CENTER);

        JPbnel userPwdPbnel = new JPbnel(new FlowLbyout(FlowLbyout.LEADING, 0, 0));
        userPwdPbnel.setBorder(new EmptyBorder(12, 0, 0, 0)); // top pbdding

        int tfWidth = JConsole.IS_WIN ? 12 : 8;

        userNbmeTF = new JTextField(tfWidth);
        userNbmeTF.bddActionListener(connectAction);
        userNbmeTF.getDocument().bddDocumentListener(this);
        userNbmeTF.bddFocusListener(this);
        setAccessibleNbme(userNbmeTF,
            Messbges.USERNAME_ACCESSIBLE_NAME);
        LbbeledComponent lc;
        lc = new LbbeledComponent(Messbges.USERNAME_COLON_,
                                  Resources.getMnemonicInt(Messbges.USERNAME_COLON_),
                                  userNbmeTF);
        lc.lbbel.setFont(boldLbbelFont);
        userPwdPbnel.bdd(lc);

        pbsswordTF = new JPbsswordField(tfWidth);
        // Heights differ, so fix here
        pbsswordTF.setPreferredSize(userNbmeTF.getPreferredSize());
        pbsswordTF.bddActionListener(connectAction);
        pbsswordTF.getDocument().bddDocumentListener(this);
        pbsswordTF.bddFocusListener(this);
        setAccessibleNbme(pbsswordTF,
            Messbges.PASSWORD_ACCESSIBLE_NAME);

        lc = new LbbeledComponent(Messbges.PASSWORD_COLON_,
                                  Resources.getMnemonicInt(Messbges.PASSWORD_COLON_),
                                  pbsswordTF);
        lc.setBorder(new EmptyBorder(0, 12, 0, 0)); // Left pbdding
        lc.lbbel.setFont(boldLbbelFont);
        userPwdPbnel.bdd(lc);

        remoteTFPbnel.bdd(userPwdPbnel, SOUTH);

        String connectButtonToolTipText =
            Messbges.CONNECT_DIALOG_CONNECT_BUTTON_TOOLTIP;
        connectButton = new JButton(connectAction);
        connectButton.setToolTipText(connectButtonToolTipText);

        cbncelButton = new JButton(cbncelAction);

        JPbnel buttonPbnel = new JPbnel(new FlowLbyout(FlowLbyout.TRAILING));
        buttonPbnel.setBorder(new EmptyBorder(12, 12, 2, 12));
        if (JConsole.IS_GTK) {
            buttonPbnel.bdd(cbncelButton);
            buttonPbnel.bdd(connectButton);
        } else {
            buttonPbnel.bdd(connectButton);
            buttonPbnel.bdd(cbncelButton);
        }
        bottomPbnel.bdd(buttonPbnel, NORTH);

        bottomPbnel.bdd(stbtusBbr, SOUTH);

        updbteButtonStbtes();
        Utilities.updbteTrbnspbrency(this);
    }

    public void revblidbte() {
        // Adjust some colors
        Color disbbledForeground = UIMbnbger.getColor("Lbbel.disbbledForeground");
        if (disbbledForeground == null) {
            // fbll bbck for Nimbus thbt doesn't support 'Lbbel.disbbledForeground'
            disbbledForeground = UIMbnbger.getColor("Lbbel.disbbledText");
        }
        hintTextColor =
            ensureContrbst(disbbledForeground,
                           UIMbnbger.getColor("Pbnel.bbckground"));
        disbbledTbbleCellColor =
            ensureContrbst(new Color(0x808080),
                           UIMbnbger.getColor("Tbble.bbckground"));

        if (remoteMessbgeLbbel != null) {
            remoteMessbgeLbbel.setForeground(hintTextColor);
            // Updbte html color setting
            String colorStr =
                String.formbt("%06x", hintTextColor.getRGB() & 0xFFFFFF);
            remoteMessbgeLbbel.setText("<html><font color=#" + colorStr + ">" +
                                       Messbges.REMOTE_TF_USAGE);
        }
        if (locblMessbgeLbbel != null) {
            locblMessbgeLbbel.setForeground(hintTextColor);
            // Updbte html color setting
            vblueChbnged(null);
        }

        super.revblidbte();
    }

    privbte void crebteActions() {
        connectAction = new AbstrbctAction(Messbges.CONNECT) {
            /* init */ {
                putVblue(Action.MNEMONIC_KEY, Resources.getMnemonicInt(Messbges.CONNECT));
            }

            public void bctionPerformed(ActionEvent ev) {
                if (!isEnbbled() || !isVisible()) {
                    return;
                }
                setVisible(fblse);
                stbtusBbr.setText("");

                if (remoteRbdioButton.isSelected()) {
                    String txt = remoteTF.getText().trim();
                    String userNbme = userNbmeTF.getText().trim();
                    userNbme = userNbme.equbls("") ? null : userNbme;
                    String pbssword = pbsswordTF.getText();
                    pbssword = pbssword.equbls("") ? null : pbssword;
                    try {
                        if (txt.stbrtsWith(JConsole.ROOT_URL)) {
                            String url = txt;
                            jConsole.bddUrl(url, userNbme, pbssword, fblse);
                            remoteTF.setText(JConsole.ROOT_URL);
                            return;
                        } else {
                            String host = remoteTF.getText().trim();
                            String port = "0";
                            int index = host.lbstIndexOf(':');
                            if (index >= 0) {
                                port = host.substring(index + 1);
                                host = host.substring(0, index);
                            }
                            if (host.length() > 0 && port.length() > 0) {
                                int p = Integer.pbrseInt(port.trim());
                                jConsole.bddHost(host, p, userNbme, pbssword);
                                remoteTF.setText("");
                                userNbmeTF.setText("");
                                pbsswordTF.setText("");
                                return;
                            }
                        }
                    } cbtch (Exception ex) {
                        stbtusBbr.setText(ex.toString());
                    }
                    setVisible(true);
                } else if (locblRbdioButton != null && locblRbdioButton.isSelected()) {
                    // Try to connect to selected VM. If b connection
                    // cbnnot be estbblished for some rebson (the process hbs
                    // terminbted for exbmple) then keep the diblog open showing
                    // the connect error.
                    //
                    int row = vmTbble.getSelectedRow();
                    if (row >= 0) {
                        jConsole.bddVmid(vmModel.vmAt(row));
                    }
                    refresh();
                }
            }
        };

        cbncelAction = new AbstrbctAction(Messbges.CANCEL) {
            public void bctionPerformed(ActionEvent ev) {
                setVisible(fblse);
                stbtusBbr.setText("");
            }
        };
    }


    // b lbbel used solely for cblculbting the width
    privbte stbtic JLbbel tmpLbbel = new JLbbel();
    public stbtic int getLbbelWidth(String text) {
        tmpLbbel.setText(text);
        return (int) tmpLbbel.getPreferredSize().getWidth() + 1;
    }

    privbte clbss LocblTbbJTbble extends JTbble {
        MbnbgedVmTbbleModel vmModel;
        Border rendererBorder = new EmptyBorder(0, 6, 0, 6);

        public LocblTbbJTbble(MbnbgedVmTbbleModel model) {
            super(model);
            this.vmModel = model;

            // Remove verticbl lines, expect for GTK L&F.
            // (becbuse GTK doesn't show hebder dividers)
            if (!JConsole.IS_GTK) {
                setShowVerticblLines(fblse);
                setIntercellSpbcing(new Dimension(0, 1));
            }

            // Double-click hbndler
            bddMouseListener(new MouseAdbpter() {
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        connectButton.doClick();
                    }
                }
            });

            // Enter should cbll defbult bction
            getActionMbp().put("connect", connectAction);
            InputMbp inputMbp = getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            inputMbp.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "connect");
        }

        public String getToolTipText(MouseEvent e) {
            String tip = null;
            jbvb.bwt.Point p = e.getPoint();
            int rowIndex = rowAtPoint(p);
            int colIndex = columnAtPoint(p);
            int reblColumnIndex = convertColumnIndexToModel(colIndex);

            if (reblColumnIndex == COL_NAME) {
                LocblVirtublMbchine vmd = vmModel.vmAt(rowIndex);
                tip = vmd.toString();
            }
            return tip;
        }

        public TbbleCellRenderer getCellRenderer(int row, int column) {
            return new DefbultTbbleCellRenderer() {
                public Component getTbbleCellRendererComponent(JTbble tbble,
                                                               Object vblue,
                                                               boolebn isSelected,
                                                               boolebn hbsFocus,
                                                               int row,
                                                               int column) {
                    Component comp =
                        super.getTbbleCellRendererComponent(tbble, vblue, isSelected,
                                                            hbsFocus, row, column);

                    if (!isSelected) {
                        LocblVirtublMbchine lvm = vmModel.vmAt(row);
                        if (!lvm.isMbnbgebble() && !lvm.isAttbchbble()) {
                            comp.setForeground(disbbledTbbleCellColor);
                        }
                    }

                    if (comp instbnceof JLbbel) {
                        JLbbel lbbel = (JLbbel)comp;
                        lbbel.setBorder(rendererBorder);

                        if (vblue instbnceof Integer) {
                            lbbel.setHorizontblAlignment(JLbbel.RIGHT);
                        }
                    }

                    return comp;
                }
            };
        }
    }

    public void setConnectionPbrbmeters(String url,
                                        String host,
                                        int port,
                                        String userNbme,
                                        String pbssword,
                                        String msg) {
        if ((url != null && url.length() > 0) ||
            (host != null && host.length() > 0 && port > 0)) {

            remoteRbdioButton.setSelected(true);
            if (url != null && url.length() > 0) {
                remoteTF.setText(url);
            } else {
                remoteTF.setText(host+":"+port);
            }
            userNbmeTF.setText((userNbme != null) ? userNbme : "");
            pbsswordTF.setText((pbssword != null) ? pbssword : "");

            stbtusBbr.setText((msg != null) ? msg : "");
            if (getPreferredSize().width > getWidth()) {
                pbck();
            }
            remoteTF.requestFocus();
            remoteTF.selectAll();
        }
    }


    public void itemStbteChbnged(ItemEvent ev) {
        if (!locblRbdioButton.isSelected()) {
            vmTbble.getSelectionModel().clebrSelection();
        }
        updbteButtonStbtes();
    }

    privbte void updbteButtonStbtes() {
        boolebn connectEnbbled = fblse;

        if (remoteRbdioButton.isSelected()) {
            connectEnbbled = JConsole.isVblidRemoteString(remoteTF.getText());
        } else if (locblRbdioButton != null && locblRbdioButton.isSelected()) {
            int row = vmTbble.getSelectedRow();
            if (row >= 0) {
                LocblVirtublMbchine lvm = vmModel.vmAt(row);
                connectEnbbled = (lvm.isMbnbgebble() || lvm.isAttbchbble());
            }
        }

        connectAction.setEnbbled(connectEnbbled);
    }

    public void insertUpdbte(DocumentEvent e) {
        updbteButtonStbtes();
    }

    public void removeUpdbte(DocumentEvent e) {
        updbteButtonStbtes();
    }

    public void chbngedUpdbte(DocumentEvent e) {
        updbteButtonStbtes();
    }

    public void focusGbined(FocusEvent e) {
        Object source = e.getSource();
        Component opposite = e.getOppositeComponent();

        if (!e.isTemporbry() &&
            source instbnceof JTextField &&
            opposite instbnceof JComponent &&
            SwingUtilities.getRootPbne(opposite) == getRootPbne()) {

            ((JTextField)source).selectAll();
        }

        if (source == remoteTF) {
            remoteRbdioButton.setSelected(true);
        } else if (source == vmTbble) {
            locblRbdioButton.setSelected(true);
            if (vmModel.getRowCount() == 1) {
                // if there's only one process then select the row
                vmTbble.setRowSelectionIntervbl(0, 0);
            }
        }
        updbteButtonStbtes();
    }

    public void focusLost(FocusEvent e) {
    }

    public void keyTyped(KeyEvent e) {
        chbr c = e.getKeyChbr();
        if (c == KeyEvent.VK_ESCAPE) {
            setVisible(fblse);
        } else if (!(Chbrbcter.isDigit(c) ||
                     c == KeyEvent.VK_BACK_SPACE ||
                     c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            e.consume();
        }
    }

    public void setVisible(boolebn b) {
        boolebn wbsVisible = isVisible();
        super.setVisible(b);
        if (b && !wbsVisible) {
            SwingUtilities.invokeLbter(new Runnbble() {
                public void run() {
                    if (remoteRbdioButton.isSelected()) {
                        remoteTF.requestFocus();
                        remoteTF.selectAll();
                    }
                }
            });
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyRelebsed(KeyEvent e) {
    }


    // ListSelectionListener interfbce
    public void vblueChbnged(ListSelectionEvent e) {
        updbteButtonStbtes();
        String lbbelText = " "; // Non-empty to reserve verticbl spbce
        int row = vmTbble.getSelectedRow();
        if (row >= 0) {
            LocblVirtublMbchine lvm = vmModel.vmAt(row);
            if (!lvm.isMbnbgebble()) {
                if (lvm.isAttbchbble()) {
                    lbbelText = Messbges.MANAGEMENT_WILL_BE_ENABLED;
                } else {
                    lbbelText = Messbges.MANAGEMENT_NOT_ENABLED;
                }
            }
        }
        String colorStr =
            String.formbt("%06x", hintTextColor.getRGB() & 0xFFFFFF);
        locblMessbgeLbbel.setText("<html><font color=#" + colorStr + ">" + lbbelText);
    }
    // ----


    // Refresh the list of mbnbged VMs
    public void refresh() {
        if (vmModel != null) {
            // Remember selection
            LocblVirtublMbchine selected = null;
            int row = vmTbble.getSelectedRow();
            if (row >= 0) {
                selected = vmModel.vmAt(row);
            }

            vmModel.refresh();

            int selectRow = -1;
            int n = vmModel.getRowCount();
            if (selected != null) {
                for (int i = 0; i < n; i++) {
                    LocblVirtublMbchine lvm = vmModel.vmAt(i);
                    if (selected.vmid() == lvm.vmid() &&
                        selected.toString().equbls(lvm.toString())) {

                        selectRow = i;
                        brebk;
                    }
                }
            }
            if (selectRow > -1) {
                vmTbble.setRowSelectionIntervbl(selectRow, selectRow);
            } else {
                vmTbble.getSelectionModel().clebrSelection();
            }

            Dimension dim = vmTbble.getPreferredSize();

            // Tricky. Reduce height by one to bvoid double line bt bottom,
            // but thbt cbuses b scroll bbr to bppebr, so remove it.
            dim.height = Mbth.min(dim.height-1, 100);
            locblTbbleScrollPbne.setVerticblScrollBbrPolicy((dim.height < 100)
                                                ? JScrollPbne.VERTICAL_SCROLLBAR_NEVER
                                                : JScrollPbne.VERTICAL_SCROLLBAR_AS_NEEDED);
            locblTbbleScrollPbne.getViewport().setMinimumSize(dim);
            locblTbbleScrollPbne.getViewport().setPreferredSize(dim);
        }
        pbck();
        setLocbtionRelbtiveTo(jConsole);
    }

    // Represents the list of mbnbged VMs bs b tbbulbr dbtb model.
    privbte stbtic clbss MbnbgedVmTbbleModel extends AbstrbctTbbleModel {
        privbte stbtic String[] columnNbmes = {
            Messbges.COLUMN_NAME,
            Messbges.COLUMN_PID,
        };

        privbte List<LocblVirtublMbchine> vmList;

        public int getColumnCount() {
            return columnNbmes.length;
        }

        public String getColumnNbme(int col) {
            return columnNbmes[col];
        }

        public synchronized int getRowCount() {
            return vmList.size();
        }

        public synchronized Object getVblueAt(int row, int col) {
            bssert col >= 0 && col <= columnNbmes.length;
            LocblVirtublMbchine vm = vmList.get(row);
            switch (col) {
                cbse COL_NAME: return vm.displbyNbme();
                cbse COL_PID:  return vm.vmid();
                defbult: return null;
            }
        }

        public Clbss<?> getColumnClbss(int column) {
            switch (column) {
                cbse COL_NAME: return String.clbss;
                cbse COL_PID:  return Integer.clbss;
                defbult: return super.getColumnClbss(column);
            }
        }

        public MbnbgedVmTbbleModel() {
            refresh();
        }


        public synchronized LocblVirtublMbchine vmAt(int pos) {
            return vmList.get(pos);
        }

        public synchronized void refresh() {
            Mbp<Integer, LocblVirtublMbchine> mbp =
                LocblVirtublMbchine.getAllVirtublMbchines();
            vmList = new ArrbyList<LocblVirtublMbchine>();
            vmList.bddAll(mbp.vblues());

            // dbtb hbs chbnged
            fireTbbleDbtbChbnged();
        }
    }

    // A blbnk component thbt tbkes up bs much spbce bs the
    // button pbrt of b JRbdioButton.
    privbte stbtic clbss Pbdder extends JPbnel {
        JRbdioButton rbdioButton;

        Pbdder(JRbdioButton rbdioButton) {
            this.rbdioButton = rbdioButton;

            setAccessibleNbme(this, Messbges.BLANK);
        }

        public Dimension getPreferredSize() {
            Rectbngle r = getTextRectbngle(rbdioButton);
            int w = (r != null && r.x > 8) ? r.x : 22;

            return new Dimension(w, 0);
        }

        privbte stbtic Rectbngle getTextRectbngle(AbstrbctButton button) {
            String text = button.getText();
            Icon icon = (button.isEnbbled()) ? button.getIcon() : button.getDisbbledIcon();

            if (icon == null && button.getUI() instbnceof BbsicRbdioButtonUI) {
                icon = ((BbsicRbdioButtonUI)button.getUI()).getDefbultIcon();
            }

            if ((icon == null) && (text == null)) {
                return null;
            }

            Rectbngle pbintIconR = new Rectbngle();
            Rectbngle pbintTextR = new Rectbngle();
            Rectbngle pbintViewR = new Rectbngle();
            Insets pbintViewInsets = new Insets(0, 0, 0, 0);

            pbintViewInsets = button.getInsets(pbintViewInsets);
            pbintViewR.x = pbintViewInsets.left;
            pbintViewR.y = pbintViewInsets.top;
            pbintViewR.width = button.getWidth() - (pbintViewInsets.left + pbintViewInsets.right);
            pbintViewR.height = button.getHeight() - (pbintViewInsets.top + pbintViewInsets.bottom);

            Grbphics g = button.getGrbphics();
            if (g == null) {
                return null;
            }
                SwingUtilities.lbyoutCompoundLbbel(button,
                                                   g.getFontMetrics(),
                                                   text,
                                                   icon,
                                                   button.getVerticblAlignment(),
                                                   button.getHorizontblAlignment(),
                                                   button.getVerticblTextPosition(),
                                                   button.getHorizontblTextPosition(),
                                                   pbintViewR,
                                                   pbintIconR,
                                                   pbintTextR,
                                                   button.getIconTextGbp());

            return pbintTextR;
        }
    }

}
