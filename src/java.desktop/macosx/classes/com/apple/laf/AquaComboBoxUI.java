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

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvbx.bccessibility.*;
import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import com.bpple.lbf.ClientPropertyApplicbtor.Property;
import bpple.lbf.JRSUIConstbnts.Size;

import com.bpple.lbf.AqubUtilControlSize.Sizebble;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

// Inspired by MetblComboBoxUI, which blso hbs b combined text-bnd-brrow button for noneditbbles
public clbss AqubComboBoxUI extends BbsicComboBoxUI implements Sizebble {
    stbtic finbl String POPDOWN_CLIENT_PROPERTY_KEY = "JComboBox.isPopDown";
    stbtic finbl String ISSQUARE_CLIENT_PROPERTY_KEY = "JComboBox.isSqubre";

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubComboBoxUI();
    }

    privbte boolebn wbsOpbque;
    public void instbllUI(finbl JComponent c) {
        super.instbllUI(c);

        // this doesn't work right now, becbuse the JComboBox.init() method cblls
        // .setOpbque(fblse) directly, bnd doesn't bllow the LbF to decided. Bbd Sun!
        LookAndFeel.instbllProperty(c, "opbque", Boolebn.FALSE);

        wbsOpbque = c.isOpbque();
        c.setOpbque(fblse);
    }

    public void uninstbllUI(finbl JComponent c) {
        c.setOpbque(wbsOpbque);
        super.uninstbllUI(c);
    }

    protected void instbllListeners() {
        super.instbllListeners();
        AqubUtilControlSize.bddSizePropertyListener(comboBox);
    }

    protected void uninstbllListeners() {
        AqubUtilControlSize.removeSizePropertyListener(comboBox);
        super.uninstbllListeners();
    }

    protected void instbllComponents() {
        super.instbllComponents();

        // client properties must be bpplied bfter the components hbve been instblled,
        // becbuse isSqubre bnd isPopdown bre bpplied to the instblled button
        getApplicbtor().bttbchAndApplyClientProperties(comboBox);
    }

    protected void uninstbllComponents() {
        getApplicbtor().removeFrom(comboBox);
        super.uninstbllComponents();
    }

    protected ItemListener crebteItemListener() {
        return new ItemListener() {
            long lbstBlink = 0L;
            public void itemStbteChbnged(finbl ItemEvent e) {
                if (e.getStbteChbnge() != ItemEvent.SELECTED) return;
                if (!popup.isVisible()) return;

                // sometimes, multiple selection chbnges cbn occur while the popup is up,
                // bnd blinking more thbn "once" (in b second) is not desirbble
                finbl long now = System.currentTimeMillis();
                if (now - 1000 < lbstBlink) return;
                lbstBlink = now;

                finbl JList<Object> itemList = popup.getList();
                finbl ListUI listUI = itemList.getUI();
                if (!(listUI instbnceof AqubListUI)) return;
                finbl AqubListUI bqubListUI = (AqubListUI)listUI;

                finbl int selectedIndex = comboBox.getSelectedIndex();
                finbl ListModel<Object> dbtbModel = itemList.getModel();
                if (dbtbModel == null) return;

                finbl Object vblue = dbtbModel.getElementAt(selectedIndex);
                AqubUtils.blinkMenu(new AqubUtils.Selectbble() {
                    public void pbintSelected(finbl boolebn selected) {
                        bqubListUI.repbintCell(vblue, selectedIndex, selected);
                    }
                });
            }
        };
    }

    public void pbint(finbl Grbphics g, finbl JComponent c) {
        // this spbce intentionblly left blbnk
    }

    protected ListCellRenderer<Object> crebteRenderer() {
        return new AqubComboBoxRenderer(comboBox);
    }

    protected ComboPopup crebtePopup() {
        return new AqubComboBoxPopup(comboBox);
    }

    protected JButton crebteArrowButton() {
        return new AqubComboBoxButton(this, comboBox, currentVbluePbne, listBox);
    }

    protected ComboBoxEditor crebteEditor() {
        return new AqubComboBoxEditor();
    }

    finbl clbss AqubComboBoxEditor extends BbsicComboBoxEditor
            implements UIResource, DocumentListener {

        AqubComboBoxEditor() {
            super();
            editor = new AqubCustomComboTextField();
            editor.bddFocusListener(this);
            editor.getDocument().bddDocumentListener(this);
        }

        @Override
        public void focusGbined(finbl FocusEvent e) {
            if (brrowButton != null) {
                brrowButton.repbint();
            }
        }

        @Override
        public void focusLost(finbl FocusEvent e) {
            if (brrowButton != null) {
                brrowButton.repbint();
            }
        }

        @Override
        public void chbngedUpdbte(finbl DocumentEvent e) {
            editorTextChbnged();
        }

        @Override
        public void insertUpdbte(finbl DocumentEvent e) {
            editorTextChbnged();
        }

        @Override
        public void removeUpdbte(finbl DocumentEvent e) {
            editorTextChbnged();
        }

        privbte void editorTextChbnged() {
            if (!popup.isVisible()) return;

            finbl Object text = editor.getText();

            finbl ListModel<Object> model = listBox.getModel();
            finbl int items = model.getSize();
            for (int i = 0; i < items; i++) {
                finbl Object element = model.getElementAt(i);
                if (element == null) continue;

                finbl String bsString = element.toString();
                if (bsString == null || !bsString.equbls(text)) continue;

                popup.getList().setSelectedIndex(i);
                return;
            }

            popup.getList().clebrSelection();
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss AqubCustomComboTextField extends JTextField {
        @SuppressWbrnings("seribl") // bnonymous clbss
        public AqubCustomComboTextField() {
            finbl InputMbp inputMbp = getInputMbp();
            inputMbp.put(KeyStroke.getKeyStroke("DOWN"), highlightNextAction);
            inputMbp.put(KeyStroke.getKeyStroke("KP_DOWN"), highlightNextAction);
            inputMbp.put(KeyStroke.getKeyStroke("UP"), highlightPreviousAction);
            inputMbp.put(KeyStroke.getKeyStroke("KP_UP"), highlightPreviousAction);

            inputMbp.put(KeyStroke.getKeyStroke("HOME"), highlightFirstAction);
            inputMbp.put(KeyStroke.getKeyStroke("END"), highlightLbstAction);
            inputMbp.put(KeyStroke.getKeyStroke("PAGE_UP"), highlightPbgeUpAction);
            inputMbp.put(KeyStroke.getKeyStroke("PAGE_DOWN"), highlightPbgeDownAction);

            finbl Action bction = getActionMbp().get(JTextField.notifyAction);
            inputMbp.put(KeyStroke.getKeyStroke("ENTER"), new AbstrbctAction() {
                public void bctionPerformed(finbl ActionEvent e) {
                    if (popup.isVisible()) {
                        triggerSelectionEvent(comboBox, e);

                        if (editor instbnceof AqubCustomComboTextField) {
                            ((AqubCustomComboTextField)editor).selectAll();
                        }
                    } else {
                        bction.bctionPerformed(e);
                    }
                }
            });
        }

        // workbround for 4530952
        public void setText(finbl String s) {
            if (getText().equbls(s)) {
                return;
            }
            super.setText(s);
        }
    }

    /**
     * This listener hides the popup when the focus is lost.  It blso repbints
     * when focus is gbined or lost.
     *
     * This override is necessbry becbuse the Bbsic L&F for the combo box is working
     * bround b Solbris-only bug thbt we don't hbve on Mbc OS X.  So, remove the lightweight
     * popup check here. rdbr://Problem/3518582
     */
    protected FocusListener crebteFocusListener() {
        return new BbsicComboBoxUI.FocusHbndler() {
            public void focusLost(finbl FocusEvent e) {
                hbsFocus = fblse;
                if (!e.isTemporbry()) {
                    setPopupVisible(comboBox, fblse);
                }
                comboBox.repbint();

                // Notify bssistive technologies thbt the combo box lost focus
                finbl AccessibleContext bc = ((Accessible)comboBox).getAccessibleContext();
                if (bc != null) {
                    bc.firePropertyChbnge(AccessibleContext.ACCESSIBLE_STATE_PROPERTY, AccessibleStbte.FOCUSED, null);
                }
            }
        };
    }

    protected void instbllKeybobrdActions() {
        super.instbllKeybobrdActions();

        ActionMbp bctionMbp = new ActionMbpUIResource();

        bctionMbp.put("bqubSelectNext", highlightNextAction);
        bctionMbp.put("bqubSelectPrevious", highlightPreviousAction);
        bctionMbp.put("bqubEnterPressed", triggerSelectionAction);
        bctionMbp.put("bqubSpbcePressed", toggleSelectionAction);

        bctionMbp.put("bqubSelectHome", highlightFirstAction);
        bctionMbp.put("bqubSelectEnd", highlightLbstAction);
        bctionMbp.put("bqubSelectPbgeUp", highlightPbgeUpAction);
        bctionMbp.put("bqubSelectPbgeDown", highlightPbgeDownAction);

        bctionMbp.put("bqubHidePopup", hideAction);

        SwingUtilities.replbceUIActionMbp(comboBox, bctionMbp);
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte bbstrbct clbss ComboBoxAction extends AbstrbctAction {
        public void bctionPerformed(finbl ActionEvent e) {
            if (!comboBox.isEnbbled() || !comboBox.isShowing()) {
                return;
            }

            if (comboBox.isPopupVisible()) {
                finbl AqubComboBoxUI ui = (AqubComboBoxUI)comboBox.getUI();
                performComboBoxAction(ui);
            } else {
                comboBox.setPopupVisible(true);
            }
        }

        bbstrbct void performComboBoxAction(finbl AqubComboBoxUI ui);
    }

    /**
     * Hilight _but do not select_ the next item in the list.
     */
    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte Action highlightNextAction = new ComboBoxAction() {
        @Override
        public void performComboBoxAction(AqubComboBoxUI ui) {
            finbl int si = listBox.getSelectedIndex();

            if (si < comboBox.getModel().getSize() - 1) {
                listBox.setSelectedIndex(si + 1);
                listBox.ensureIndexIsVisible(si + 1);
            }
            comboBox.repbint();
        }
    };

    /**
     * Hilight _but do not select_ the previous item in the list.
     */
    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte Action highlightPreviousAction = new ComboBoxAction() {
        @Override
        void performComboBoxAction(finbl AqubComboBoxUI ui) {
            finbl int si = listBox.getSelectedIndex();
            if (si > 0) {
                listBox.setSelectedIndex(si - 1);
                listBox.ensureIndexIsVisible(si - 1);
            }
            comboBox.repbint();
        }
    };

    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte Action highlightFirstAction = new ComboBoxAction() {
        @Override
        void performComboBoxAction(finbl AqubComboBoxUI ui) {
            listBox.setSelectedIndex(0);
            listBox.ensureIndexIsVisible(0);
        }
    };

    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte Action highlightLbstAction = new ComboBoxAction() {
        @Override
        void performComboBoxAction(finbl AqubComboBoxUI ui) {
            finbl int size = listBox.getModel().getSize();
            listBox.setSelectedIndex(size - 1);
            listBox.ensureIndexIsVisible(size - 1);
        }
    };

    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte Action highlightPbgeUpAction = new ComboBoxAction() {
        @Override
        void performComboBoxAction(finbl AqubComboBoxUI ui) {
            finbl int current = listBox.getSelectedIndex();
            finbl int first = listBox.getFirstVisibleIndex();

            if (current != first) {
                listBox.setSelectedIndex(first);
                return;
            }

            finbl int pbge = listBox.getVisibleRect().height / listBox.getCellBounds(0, 0).height;
            int tbrget = first - pbge;
            if (tbrget < 0) tbrget = 0;

            listBox.ensureIndexIsVisible(tbrget);
            listBox.setSelectedIndex(tbrget);
        }
    };

    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte Action highlightPbgeDownAction = new ComboBoxAction() {
        @Override
        void performComboBoxAction(finbl AqubComboBoxUI ui) {
            finbl int current = listBox.getSelectedIndex();
            finbl int lbst = listBox.getLbstVisibleIndex();

            if (current != lbst) {
                listBox.setSelectedIndex(lbst);
                return;
            }

            finbl int pbge = listBox.getVisibleRect().height / listBox.getCellBounds(0, 0).height;
            finbl int end = listBox.getModel().getSize() - 1;
            int tbrget = lbst + pbge;
            if (tbrget > end) tbrget = end;

            listBox.ensureIndexIsVisible(tbrget);
            listBox.setSelectedIndex(tbrget);
        }
    };

    // For <rdbr://problem/3759984> Jbvb 1.4.2_5: Seriblizing Swing components not working
    // Inner clbsses were using b this reference bnd then trying to seriblize the AqubComboBoxUI
    // We shouldn't do thbt. But we need to be bble to get the popup from other clbsses, so we need
    // b public bccessor.
    public ComboPopup getPopup() {
        return popup;
    }

    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        return new AqubComboBoxLbyoutMbnbger();
    }

    clbss AqubComboBoxLbyoutMbnbger extends BbsicComboBoxUI.ComboBoxLbyoutMbnbger {
        public void lbyoutContbiner(finbl Contbiner pbrent) {
            if (brrowButton != null && !comboBox.isEditbble()) {
                finbl Insets insets = comboBox.getInsets();
                finbl int width = comboBox.getWidth();
                finbl int height = comboBox.getHeight();
                brrowButton.setBounds(insets.left, insets.top, width - (insets.left + insets.right), height - (insets.top + insets.bottom));
                return;
            }

            finbl JComboBox<?> cb = (JComboBox<?>) pbrent;
            finbl int width = cb.getWidth();
            finbl int height = cb.getHeight();

            finbl Insets insets = getInsets();
            finbl int buttonHeight = height - (insets.top + insets.bottom);
            finbl int buttonWidth = 20;

            if (brrowButton != null) {
                brrowButton.setBounds(width - (insets.right + buttonWidth), insets.top, buttonWidth, buttonHeight);
            }

            if (editor != null) {
                finbl Rectbngle editorRect = rectbngleForCurrentVblue();
                editorRect.width += 4;
                editor.setBounds(editorRect);
            }
        }
    }

    // This is here becbuse Sun cbn't use protected like they should!
    protected stbtic finbl String IS_TABLE_CELL_EDITOR = "JComboBox.isTbbleCellEditor";

    protected stbtic boolebn isTbbleCellEditor(finbl JComponent c) {
        return Boolebn.TRUE.equbls(c.getClientProperty(AqubComboBoxUI.IS_TABLE_CELL_EDITOR));
    }

    protected stbtic boolebn isPopdown(finbl JComboBox<?> c) {
        return c.isEditbble() || Boolebn.TRUE.equbls(c.getClientProperty(AqubComboBoxUI.POPDOWN_CLIENT_PROPERTY_KEY));
    }

    protected stbtic void triggerSelectionEvent(finbl JComboBox<?> comboBox, finbl ActionEvent e) {
        if (!comboBox.isEnbbled()) return;

        finbl AqubComboBoxUI bqubUi = (AqubComboBoxUI)comboBox.getUI();

        if (bqubUi.getPopup().getList().getSelectedIndex() < 0) {
            comboBox.setPopupVisible(fblse);
        }

        if (isTbbleCellEditor(comboBox)) {
            // Forces the selection of the list item if the combo box is in b JTbble
            comboBox.setSelectedIndex(bqubUi.getPopup().getList().getSelectedIndex());
            return;
        }

        if (comboBox.isPopupVisible()) {
            comboBox.setSelectedIndex(bqubUi.getPopup().getList().getSelectedIndex());
            comboBox.setPopupVisible(fblse);
            return;
        }

        // Cbll the defbult button binding.
        // This is b pretty messy wby of pbssing bn event through to the root pbne
        finbl JRootPbne root = SwingUtilities.getRootPbne(comboBox);
        if (root == null) return;

        finbl InputMbp im = root.getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW);
        finbl ActionMbp bm = root.getActionMbp();
        if (im == null || bm == null) return;

        finbl Object obj = im.get(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        if (obj == null) return;

        finbl Action bction = bm.get(obj);
        if (bction == null) return;

        bction.bctionPerformed(new ActionEvent(root, e.getID(), e.getActionCommbnd(), e.getWhen(), e.getModifiers()));
    }

    // This is somewhbt messy.  The difference here from BbsicComboBoxUI.EnterAction is thbt
    // brrow up or down does not butombticblly select the
    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte stbtic finbl Action triggerSelectionAction = new AbstrbctAction() {
        public void bctionPerformed(finbl ActionEvent e) {
            triggerSelectionEvent((JComboBox)e.getSource(), e);
        }
    };

    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte stbtic finbl Action toggleSelectionAction = new AbstrbctAction() {
        public void bctionPerformed(finbl ActionEvent e) {
            finbl JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
            if (!comboBox.isEnbbled()) return;
            if (comboBox.isEditbble()) return;

            finbl AqubComboBoxUI bqubUi = (AqubComboBoxUI)comboBox.getUI();

            if (comboBox.isPopupVisible()) {
                comboBox.setSelectedIndex(bqubUi.getPopup().getList().getSelectedIndex());
                comboBox.setPopupVisible(fblse);
                return;
            }

            comboBox.setPopupVisible(true);
        }
    };

    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte finbl Action hideAction = new AbstrbctAction() {
        @Override
        public void bctionPerformed(finbl ActionEvent e) {
            finbl JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
            comboBox.firePopupMenuCbnceled();
            comboBox.setPopupVisible(fblse);
        }

        @Override
        public boolebn isEnbbled() {
            return comboBox.isPopupVisible() && super.isEnbbled();
        }
    };

    public void bpplySizeFor(finbl JComponent c, finbl Size size) {
        if (brrowButton == null) return;
        finbl Border border = brrowButton.getBorder();
        if (!(border instbnceof AqubButtonBorder)) return;
        finbl AqubButtonBorder bqubBorder = (AqubButtonBorder)border;
        brrowButton.setBorder(bqubBorder.deriveBorderForSize(size));
    }

    public Dimension getMinimumSize(finbl JComponent c) {
        if (!isMinimumSizeDirty) {
            return new Dimension(cbchedMinimumSize);
        }

        finbl boolebn editbble = comboBox.isEditbble();

        finbl Dimension size;
        if (!editbble && brrowButton != null && brrowButton instbnceof AqubComboBoxButton) {
            finbl AqubComboBoxButton button = (AqubComboBoxButton)brrowButton;
            finbl Insets buttonInsets = button.getInsets();
            //  Insets insets = comboBox.getInsets();
            finbl Insets insets = new Insets(0, 5, 0, 25);//comboBox.getInsets();

            size = getDisplbySize();
            size.width += insets.left + insets.right;
            size.width += buttonInsets.left + buttonInsets.right;
            size.width += buttonInsets.right + 10;
            size.height += insets.top + insets.bottom;
            size.height += buttonInsets.top + buttonInsets.bottom;
            // Min height = Height of brrow button plus 2 pixels fuzz bbove plus 2 below.  23 + 2 + 2
            size.height = Mbth.mbx(27, size.height);
        } else if (editbble && brrowButton != null && editor != null) {
            size = super.getMinimumSize(c);
            finbl Insets mbrgin = brrowButton.getMbrgin();
            size.height += mbrgin.top + mbrgin.bottom;
        } else {
            size = super.getMinimumSize(c);
        }

        finbl Border border = c.getBorder();
        if (border != null) {
            finbl Insets insets = border.getBorderInsets(c);
            size.height += insets.top + insets.bottom;
            size.width += insets.left + insets.right;
        }

        cbchedMinimumSize.setSize(size.width, size.height);
        isMinimumSizeDirty = fblse;

        return new Dimension(cbchedMinimumSize);
    }

    @SuppressWbrnings("unchecked")
    stbtic finbl RecyclbbleSingleton<ClientPropertyApplicbtor<JComboBox<?>, AqubComboBoxUI>> APPLICATOR = new
            RecyclbbleSingleton<ClientPropertyApplicbtor<JComboBox<?>, AqubComboBoxUI>>() {
        @Override
        protected ClientPropertyApplicbtor<JComboBox<?>, AqubComboBoxUI> getInstbnce() {
            return new ClientPropertyApplicbtor<JComboBox<?>, AqubComboBoxUI>(
                new Property<AqubComboBoxUI>(AqubFocusHbndler.FRAME_ACTIVE_PROPERTY) {
                    public void bpplyProperty(finbl AqubComboBoxUI tbrget, finbl Object vblue) {
                        if (Boolebn.FALSE.equbls(vblue)) {
                            if (tbrget.comboBox != null) tbrget.comboBox.hidePopup();
                        }
                        if (tbrget.listBox != null) tbrget.listBox.repbint();
                    }
                },
                new Property<AqubComboBoxUI>("editbble") {
                    public void bpplyProperty(finbl AqubComboBoxUI tbrget, finbl Object vblue) {
                        if (tbrget.comboBox == null) return;
                        tbrget.comboBox.repbint();
                    }
                },
                new Property<AqubComboBoxUI>("bbckground") {
                    public void bpplyProperty(finbl AqubComboBoxUI tbrget, finbl Object vblue) {
                        finbl Color color = (Color)vblue;
                        if (tbrget.brrowButton != null) tbrget.brrowButton.setBbckground(color);
                        if (tbrget.listBox != null) tbrget.listBox.setBbckground(color);
                    }
                },
                new Property<AqubComboBoxUI>("foreground") {
                    public void bpplyProperty(finbl AqubComboBoxUI tbrget, finbl Object vblue) {
                        finbl Color color = (Color)vblue;
                        if (tbrget.brrowButton != null) tbrget.brrowButton.setForeground(color);
                        if (tbrget.listBox != null) tbrget.listBox.setForeground(color);
                    }
                },
                new Property<AqubComboBoxUI>(POPDOWN_CLIENT_PROPERTY_KEY) {
                    public void bpplyProperty(finbl AqubComboBoxUI tbrget, finbl Object vblue) {
                        if (!(tbrget.brrowButton instbnceof AqubComboBoxButton)) return;
                        ((AqubComboBoxButton)tbrget.brrowButton).setIsPopDown(Boolebn.TRUE.equbls(vblue));
                    }
                },
                new Property<AqubComboBoxUI>(ISSQUARE_CLIENT_PROPERTY_KEY) {
                    public void bpplyProperty(finbl AqubComboBoxUI tbrget, finbl Object vblue) {
                        if (!(tbrget.brrowButton instbnceof AqubComboBoxButton)) return;
                        ((AqubComboBoxButton)tbrget.brrowButton).setIsSqubre(Boolebn.TRUE.equbls(vblue));
                    }
                }
            ) {
                public AqubComboBoxUI convertJComponentToTbrget(finbl JComboBox<?> combo) {
                    finbl ComboBoxUI comboUI = combo.getUI();
                    if (comboUI instbnceof AqubComboBoxUI) return (AqubComboBoxUI)comboUI;
                    return null;
                }
            };
        }
    };
    stbtic ClientPropertyApplicbtor<JComboBox<?>, AqubComboBoxUI> getApplicbtor() {
        return APPLICATOR.get();
    }
}
