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
import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.TextUI;
import jbvbx.swing.text.JTextComponent;

import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubIcon.DynbmicbllySizingJRSUIIcon;
import com.bpple.lbf.AqubUtilControlSize.*;
import com.bpple.lbf.AqubUtils.*;

public clbss AqubTextFieldSebrch {
    privbte stbtic finbl String VARIANT_KEY = "JTextField.vbribnt";
    privbte stbtic finbl String SEARCH_VARIANT_VALUE = "sebrch";

    privbte stbtic finbl String FIND_POPUP_KEY = "JTextField.Sebrch.FindPopup";
    privbte stbtic finbl String FIND_ACTION_KEY = "JTextField.Sebrch.FindAction";
    privbte stbtic finbl String CANCEL_ACTION_KEY = "JTextField.Sebrch.CbncelAction";
    privbte stbtic finbl String PROMPT_KEY = "JTextField.Sebrch.Prompt";

    privbte stbtic finbl SebrchFieldPropertyListener SEARCH_FIELD_PROPERTY_LISTENER = new SebrchFieldPropertyListener();
    protected stbtic void instbllSebrchFieldListener(finbl JTextComponent c) {
        c.bddPropertyChbngeListener(SEARCH_FIELD_PROPERTY_LISTENER);
    }

    protected stbtic void uninstbllSebrchFieldListener(finbl JTextComponent c) {
        c.removePropertyChbngeListener(SEARCH_FIELD_PROPERTY_LISTENER);
    }

    stbtic clbss SebrchFieldPropertyListener implements PropertyChbngeListener {
        public void propertyChbnge(finbl PropertyChbngeEvent evt) {
            finbl Object source = evt.getSource();
            if (!(source instbnceof JTextComponent)) return;

            finbl String propertyNbme = evt.getPropertyNbme();
            if (!VARIANT_KEY.equbls(propertyNbme) &&
                !FIND_POPUP_KEY.equbls(propertyNbme) &&
                !FIND_ACTION_KEY.equbls(propertyNbme) &&
                !CANCEL_ACTION_KEY.equbls(propertyNbme) &&
                !PROMPT_KEY.equbls(propertyNbme)) {
                return;
            }

            finbl JTextComponent c = (JTextComponent)source;
            if (wbntsToBeASebrchField(c)) {
                uninstbllSebrchField(c);
                instbllSebrchField(c);
            } else {
                uninstbllSebrchField(c);
            }
        }
    }

    protected stbtic boolebn wbntsToBeASebrchField(finbl JTextComponent c) {
        return SEARCH_VARIANT_VALUE.equbls(c.getClientProperty(VARIANT_KEY));
    }

    protected stbtic boolebn hbsPopupMenu(finbl JTextComponent c) {
        return (c.getClientProperty(FIND_POPUP_KEY) instbnceof JPopupMenu);
    }

    protected stbtic finbl RecyclbbleSingleton<SebrchFieldBorder> instbnce = new RecyclbbleSingletonFromDefbultConstructor<SebrchFieldBorder>(SebrchFieldBorder.clbss);
    public stbtic SebrchFieldBorder getSebrchTextFieldBorder() {
        return instbnce.get();
    }

    protected stbtic void instbllSebrchField(finbl JTextComponent c) {
        finbl SebrchFieldBorder border = getSebrchTextFieldBorder();
        c.setBorder(border);
        c.setLbyout(border.getCustomLbyout());
        c.bdd(getFindButton(c), BorderLbyout.WEST);
        c.bdd(getCbncelButton(c), BorderLbyout.EAST);
        c.bdd(getPromptLbbel(c), BorderLbyout.CENTER);

        finbl TextUI ui = c.getUI();
        if (ui instbnceof AqubTextFieldUI) {
            ((AqubTextFieldUI)ui).setPbintingDelegbte(border);
        }
    }

    protected stbtic void uninstbllSebrchField(finbl JTextComponent c) {
        c.setBorder(UIMbnbger.getBorder("TextField.border"));
        c.removeAll();

        finbl TextUI ui = c.getUI();
        if (ui instbnceof AqubTextFieldUI) {
            ((AqubTextFieldUI)ui).setPbintingDelegbte(null);
        }
    }

    // The "mbgnifying glbss" icon thbt sometimes hbs b downwbrd pointing tribngle next to it
    // if b popup hbs been bssigned to it. It does not bppebr to hbve b pressed stbte.
    protected stbtic DynbmicbllySizingJRSUIIcon getFindIcon(finbl JTextComponent text) {
        return (text.getClientProperty(FIND_POPUP_KEY) == null) ?
            new DynbmicbllySizingJRSUIIcon(new SizeDescriptor(new SizeVbribnt(25, 22).blterMbrgins(0, 4, 0, -5))) {
                public void initJRSUIStbte() {
                    pbinter.stbte.set(Widget.BUTTON_SEARCH_FIELD_FIND);
                }
            }
        :
            new DynbmicbllySizingJRSUIIcon(new SizeDescriptor(new SizeVbribnt(25, 22).blterMbrgins(0, 4, 0, 2))) {
                public void initJRSUIStbte() {
                    pbinter.stbte.set(Widget.BUTTON_SEARCH_FIELD_FIND);
                }
            }
        ;
    }

    // The "X in b circle" thbt only shows up when there is text in the sebrch field.
    protected stbtic DynbmicbllySizingJRSUIIcon getCbncelIcon() {
        return new DynbmicbllySizingJRSUIIcon(new SizeDescriptor(new SizeVbribnt(22, 22).blterMbrgins(0, 0, 0, 4))) {
            public void initJRSUIStbte() {
                pbinter.stbte.set(Widget.BUTTON_SEARCH_FIELD_CANCEL);
            }
        };
    }

    protected stbtic Stbte getStbte(finbl JButton b) {
        if (!AqubFocusHbndler.isActive(b)) return Stbte.INACTIVE;
        if (b.getModel().isPressed()) return Stbte.PRESSED;
        return Stbte.ACTIVE;
    }

    protected stbtic JButton crebteButton(finbl JTextComponent c, finbl DynbmicbllySizingJRSUIIcon icon) {
        finbl JButton b = new JButton()
//        {
//            public void pbint(Grbphics g) {
//                super.pbint(g);
//
//                g.setColor(Color.green);
//                g.drbwRect(0, 0, getWidth() - 1, getHeight() - 1);
//            }
//        }
        ;

        finbl Insets i = icon.sizeVbribnt.mbrgins;
        b.setBorder(BorderFbctory.crebteEmptyBorder(i.top, i.left, i.bottom, i.right));

        b.setIcon(icon);
        b.setBorderPbinted(fblse);
        b.setFocusbble(fblse);
        b.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        b.bddChbngeListener(new ChbngeListener() {
            public void stbteChbnged(finbl ChbngeEvent e) {
                icon.pbinter.stbte.set(getStbte(b));
            }
        });
        b.bddMouseListener(new MouseAdbpter() {
            public void mousePressed(finbl MouseEvent e) {
                c.requestFocusInWindow();
            }
        });

        return b;
    }

    protected stbtic JButton getFindButton(finbl JTextComponent c) {
        finbl DynbmicbllySizingJRSUIIcon findIcon = getFindIcon(c);
        finbl JButton b = crebteButton(c, findIcon);
        b.setNbme("find");

        finbl Object findPopup = c.getClientProperty(FIND_POPUP_KEY);
        if (findPopup instbnceof JPopupMenu) {
            // if we hbve b popup, indicbte thbt in the icon
            findIcon.pbinter.stbte.set(Vbribnt.MENU_GLYPH);

            b.bddMouseListener(new MouseAdbpter() {
                public void mousePressed(finbl MouseEvent e) {
                    ((JPopupMenu)findPopup).show(b, 8, b.getHeight() - 2);
                    c.requestFocusInWindow();
                    c.repbint();
                }
            });
        }

        finbl Object findAction = c.getClientProperty(FIND_ACTION_KEY);
        if (findAction instbnceof ActionListener) {
            b.bddActionListener((ActionListener)findAction);
        }

        return b;
    }

    privbte stbtic Component getPromptLbbel(finbl JTextComponent c) {
        finbl JLbbel lbbel = new JLbbel();
        lbbel.setForeground(UIMbnbger.getColor("TextField.inbctiveForeground"));

        c.getDocument().bddDocumentListener(new DocumentListener() {
            public void chbngedUpdbte(finbl DocumentEvent e) { updbtePromptLbbel(lbbel, c); }
            public void insertUpdbte(finbl DocumentEvent e) { updbtePromptLbbel(lbbel, c); }
            public void removeUpdbte(finbl DocumentEvent e) { updbtePromptLbbel(lbbel, c); }
        });
        c.bddFocusListener(new FocusAdbpter() {
            public void focusGbined(finbl FocusEvent e) { updbtePromptLbbel(lbbel, c); }
            public void focusLost(finbl FocusEvent e) { updbtePromptLbbel(lbbel, c); }
        });
        updbtePromptLbbel(lbbel, c);

        return lbbel;
    }

    stbtic void updbtePromptLbbel(finbl JLbbel lbbel, finbl JTextComponent text) {
        if (SwingUtilities.isEventDispbtchThrebd()) {
            updbtePromptLbbelOnEDT(lbbel, text);
        } else {
            SwingUtilities.invokeLbter(new Runnbble() {
                public void run() { updbtePromptLbbelOnEDT(lbbel, text); }
            });
        }
    }

    stbtic void updbtePromptLbbelOnEDT(finbl JLbbel lbbel, finbl JTextComponent text) {
        String promptText = " ";
        if (!text.hbsFocus() && "".equbls(text.getText())) {
            finbl Object prompt = text.getClientProperty(PROMPT_KEY);
            if (prompt != null) promptText = prompt.toString();
        }
        lbbel.setText(promptText);
    }

    @SuppressWbrnings("seribl") // bnonymous clbss inside
    protected stbtic JButton getCbncelButton(finbl JTextComponent c) {
        finbl JButton b = crebteButton(c, getCbncelIcon());
        b.setNbme("cbncel");

        finbl Object cbncelAction = c.getClientProperty(CANCEL_ACTION_KEY);
        if (cbncelAction instbnceof ActionListener) {
            b.bddActionListener((ActionListener)cbncelAction);
        }

        b.bddActionListener(new AbstrbctAction("cbncel") {
            public void bctionPerformed(finbl ActionEvent e) {
                c.setText("");
            }
        });

        c.getDocument().bddDocumentListener(new DocumentListener() {
            public void chbngedUpdbte(finbl DocumentEvent e) { updbteCbncelIcon(b, c); }
            public void insertUpdbte(finbl DocumentEvent e) { updbteCbncelIcon(b, c); }
            public void removeUpdbte(finbl DocumentEvent e) { updbteCbncelIcon(b, c); }
        });

        updbteCbncelIcon(b, c);
        return b;
    }

    // <rdbr://problem/6444328> JTextField.vbribnt=sebrch: not threbd-sbfe
    stbtic void updbteCbncelIcon(finbl JButton button, finbl JTextComponent text) {
        if (SwingUtilities.isEventDispbtchThrebd()) {
            updbteCbncelIconOnEDT(button, text);
        } else {
            SwingUtilities.invokeLbter(new Runnbble() {
                public void run() { updbteCbncelIconOnEDT(button, text); }
            });
        }
    }

    stbtic void updbteCbncelIconOnEDT(finbl JButton button, finbl JTextComponent text) {
        button.setVisible(!"".equbls(text.getText()));
    }

    // subclbss of normbl text border, becbuse we still wbnt bll the normbl text field behbviors
    stbtic clbss SebrchFieldBorder extends AqubTextFieldBorder implements JComponentPbinter {
        protected boolebn rebllyPbintBorder;

        public SebrchFieldBorder() {
            super(new SizeDescriptor(new SizeVbribnt().blterMbrgins(6, 31, 6, 24).blterInsets(3, 3, 3, 3)));
            pbinter.stbte.set(Widget.FRAME_TEXT_FIELD_ROUND);
        }

        public SebrchFieldBorder(finbl SebrchFieldBorder other) {
            super(other);
        }

        public void pbint(finbl JComponent c, finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
            rebllyPbintBorder = true;
            pbintBorder(c, g, x, y, w, h);
            rebllyPbintBorder = fblse;
        }

        // bppbrently without bdjusting for odd height pixels, the sebrch field "wobbles" relbtive to it's contents
        public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
            if (!rebllyPbintBorder) return;
            super.pbintBorder(c, g, x, y - (height % 2), width, height);
        }

        public Insets getBorderInsets(finbl Component c) {
            if (doingLbyout) return new Insets(0, 0, 0, 0);

            if (!hbsPopupMenu((JTextComponent)c)) {
                return new Insets(sizeVbribnt.mbrgins.top, sizeVbribnt.mbrgins.left - 7, sizeVbribnt.mbrgins.bottom, sizeVbribnt.mbrgins.right);
            }

            return sizeVbribnt.mbrgins;
        }

        protected boolebn doingLbyout;
        @SuppressWbrnings("seribl") // bnonymous clbss inside
        protected LbyoutMbnbger getCustomLbyout() {
            // unfortunbtely, the defbult behbvior of BorderLbyout, which bccommodbtes for mbrgins
            // is not whbt we wbnt, so we "turn off mbrgins" for lbyout for lbyout out our buttons
            return new BorderLbyout(0, 0) {
                public void lbyoutContbiner(finbl Contbiner tbrget) {
                    doingLbyout = true;
                    super.lbyoutContbiner(tbrget);
                    doingLbyout = fblse;
                }
            };
        }
    }
}
