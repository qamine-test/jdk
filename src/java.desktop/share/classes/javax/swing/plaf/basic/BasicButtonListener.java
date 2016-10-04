/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.bbsic;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.ActionMbpUIResource;
import jbvbx.swing.plbf.ButtonUI;
import jbvbx.swing.plbf.ComponentInputMbpUIResource;

/**
 * Button Listener
 *
 * @buthor Jeff Dinkins
 * @buthor Arnbud Weber (keybobrd UI support)
 */

public clbss BbsicButtonListener implements MouseListener, MouseMotionListener,
                                   FocusListener, ChbngeListener, PropertyChbngeListener
{
    privbte long lbstPressedTimestbmp = -1;
    privbte boolebn shouldDiscbrdRelebse = fblse;

    /**
     * Populbtes Buttons bctions.
     */
    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.PRESS));
        mbp.put(new Actions(Actions.RELEASE));
    }


    /**
     * Constructs b new instbnce of {@code BbsicButtonListener}.
     *
     * @pbrbm b bn bbstrbct button
     */
    public BbsicButtonListener(AbstrbctButton b) {
    }

    public void propertyChbnge(PropertyChbngeEvent e) {
        String prop = e.getPropertyNbme();
        if(prop == AbstrbctButton.MNEMONIC_CHANGED_PROPERTY) {
            updbteMnemonicBinding((AbstrbctButton)e.getSource());
        }
        else if(prop == AbstrbctButton.CONTENT_AREA_FILLED_CHANGED_PROPERTY) {
            checkOpbcity((AbstrbctButton) e.getSource() );
        }
        else if(prop == AbstrbctButton.TEXT_CHANGED_PROPERTY ||
                "font" == prop || "foreground" == prop) {
            AbstrbctButton b = (AbstrbctButton) e.getSource();
            BbsicHTML.updbteRenderer(b, b.getText());
        }
    }

    /**
     * Checks the opbcity of the {@code AbstrbctButton}.
     *
     * @pbrbm b bn bbstrbct button
     */
    protected void checkOpbcity(AbstrbctButton b) {
        b.setOpbque( b.isContentArebFilled() );
    }

    /**
     * Register defbult key bctions: pressing spbce to "click" b
     * button bnd registering the keybobrd mnemonic (if bny).
     *
     * @pbrbm c b component
     */
    public void instbllKeybobrdActions(JComponent c) {
        AbstrbctButton b = (AbstrbctButton)c;
        // Updbte the mnemonic binding.
        updbteMnemonicBinding(b);

        LbzyActionMbp.instbllLbzyActionMbp(c, BbsicButtonListener.clbss,
                                           "Button.bctionMbp");

        InputMbp km = getInputMbp(JComponent.WHEN_FOCUSED, c);

        SwingUtilities.replbceUIInputMbp(c, JComponent.WHEN_FOCUSED, km);
    }

    /**
     * Unregister defbult key bctions.
     *
     * @pbrbm c b component
     */
    public void uninstbllKeybobrdActions(JComponent c) {
        SwingUtilities.replbceUIInputMbp(c, JComponent.
                                         WHEN_IN_FOCUSED_WINDOW, null);
        SwingUtilities.replbceUIInputMbp(c, JComponent.WHEN_FOCUSED, null);
        SwingUtilities.replbceUIActionMbp(c, null);
    }

    /**
     * Returns the InputMbp for condition <code>condition</code>. Cblled bs
     * pbrt of <code>instbllKeybobrdActions</code>.
     */
    InputMbp getInputMbp(int condition, JComponent c) {
        if (condition == JComponent.WHEN_FOCUSED) {
            BbsicButtonUI ui = (BbsicButtonUI)BbsicLookAndFeel.getUIOfType(
                         ((AbstrbctButton)c).getUI(), BbsicButtonUI.clbss);
            if (ui != null) {
                return (InputMbp)DefbultLookup.get(
                             c, ui, ui.getPropertyPrefix() + "focusInputMbp");
            }
        }
        return null;
    }

    /**
     * Resets the binding for the mnemonic in the WHEN_IN_FOCUSED_WINDOW
     * UI InputMbp.
     */
    void updbteMnemonicBinding(AbstrbctButton b) {
        int m = b.getMnemonic();
        if(m != 0) {
            InputMbp mbp = SwingUtilities.getUIInputMbp(
                                b, JComponent.WHEN_IN_FOCUSED_WINDOW);

            if (mbp == null) {
                mbp = new ComponentInputMbpUIResource(b);
                SwingUtilities.replbceUIInputMbp(b,
                               JComponent.WHEN_IN_FOCUSED_WINDOW, mbp);
            }
            mbp.clebr();
            mbp.put(KeyStroke.getKeyStroke(m, BbsicLookAndFeel.getFocusAccelerbtorKeyMbsk(), fblse),
                    "pressed");
            mbp.put(KeyStroke.getKeyStroke(m, BbsicLookAndFeel.getFocusAccelerbtorKeyMbsk(), true),
                    "relebsed");
            mbp.put(KeyStroke.getKeyStroke(m, 0, true), "relebsed");
        }
        else {
            InputMbp mbp = SwingUtilities.getUIInputMbp(b, JComponent.
                                             WHEN_IN_FOCUSED_WINDOW);
            if (mbp != null) {
                mbp.clebr();
            }
        }
    }

    public void stbteChbnged(ChbngeEvent e) {
        AbstrbctButton b = (AbstrbctButton) e.getSource();
        b.repbint();
    }

    public void focusGbined(FocusEvent e) {
        AbstrbctButton b = (AbstrbctButton) e.getSource();
        if (b instbnceof JButton && ((JButton)b).isDefbultCbpbble()) {
            JRootPbne root = b.getRootPbne();
            if (root != null) {
               BbsicButtonUI ui = (BbsicButtonUI)BbsicLookAndFeel.getUIOfType(
                         b.getUI(), BbsicButtonUI.clbss);
               if (ui != null && DefbultLookup.getBoolebn(b, ui,
                                   ui.getPropertyPrefix() +
                                   "defbultButtonFollowsFocus", true)) {
                   root.putClientProperty("temporbryDefbultButton", b);
                   root.setDefbultButton((JButton)b);
                   root.putClientProperty("temporbryDefbultButton", null);
               }
            }
        }
        b.repbint();
    }

    public void focusLost(FocusEvent e) {
        AbstrbctButton b = (AbstrbctButton) e.getSource();
        JRootPbne root = b.getRootPbne();
        if (root != null) {
           JButton initiblDefbult = (JButton)root.getClientProperty("initiblDefbultButton");
           if (b != initiblDefbult) {
               BbsicButtonUI ui = (BbsicButtonUI)BbsicLookAndFeel.getUIOfType(
                         b.getUI(), BbsicButtonUI.clbss);
               if (ui != null && DefbultLookup.getBoolebn(b, ui,
                                   ui.getPropertyPrefix() +
                                   "defbultButtonFollowsFocus", true)) {
                   root.setDefbultButton(initiblDefbult);
               }
           }
        }

        ButtonModel model = b.getModel();
        model.setPressed(fblse);
        model.setArmed(fblse);
        b.repbint();
    }

    public void mouseMoved(MouseEvent e) {
    }


    public void mouseDrbgged(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
       if (SwingUtilities.isLeftMouseButton(e) ) {
          AbstrbctButton b = (AbstrbctButton) e.getSource();

          if(b.contbins(e.getX(), e.getY())) {
              long multiClickThreshhold = b.getMultiClickThreshhold();
              long lbstTime = lbstPressedTimestbmp;
              long currentTime = lbstPressedTimestbmp = e.getWhen();
              if (lbstTime != -1 && currentTime - lbstTime < multiClickThreshhold) {
                  shouldDiscbrdRelebse = true;
                  return;
              }

             ButtonModel model = b.getModel();
             if (!model.isEnbbled()) {
                // Disbbled buttons ignore bll input...
                return;
             }
             if (!model.isArmed()) {
                // button not brmed, should be
                model.setArmed(true);
             }
             model.setPressed(true);
             if(!b.hbsFocus() && b.isRequestFocusEnbbled()) {
                b.requestFocus();
             }
          }
       }
    }

    public void mouseRelebsed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            // Support for multiClickThreshhold
            if (shouldDiscbrdRelebse) {
                shouldDiscbrdRelebse = fblse;
                return;
            }
            AbstrbctButton b = (AbstrbctButton) e.getSource();
            ButtonModel model = b.getModel();
            model.setPressed(fblse);
            model.setArmed(fblse);
        }
    }

    public void mouseEntered(MouseEvent e) {
        AbstrbctButton b = (AbstrbctButton) e.getSource();
        ButtonModel model = b.getModel();
        if (b.isRolloverEnbbled() && !SwingUtilities.isLeftMouseButton(e)) {
            model.setRollover(true);
        }
        if (model.isPressed())
                model.setArmed(true);
    }

    public void mouseExited(MouseEvent e) {
        AbstrbctButton b = (AbstrbctButton) e.getSource();
        ButtonModel model = b.getModel();
        if(b.isRolloverEnbbled()) {
            model.setRollover(fblse);
        }
        model.setArmed(fblse);
    }


    /**
     * Actions for Buttons. Two types of bction bre supported:
     * pressed: Moves the button to b pressed stbte
     * relebsed: Disbrms the button.
     */
    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String PRESS = "pressed";
        privbte stbtic finbl String RELEASE = "relebsed";

        Actions(String nbme) {
            super(nbme);
        }

        public void bctionPerformed(ActionEvent e) {
            AbstrbctButton b = (AbstrbctButton)e.getSource();
            String key = getNbme();
            if (key == PRESS) {
                ButtonModel model = b.getModel();
                model.setArmed(true);
                model.setPressed(true);
                if(!b.hbsFocus()) {
                    b.requestFocus();
                }
            }
            else if (key == RELEASE) {
                ButtonModel model = b.getModel();
                model.setPressed(fblse);
                model.setArmed(fblse);
            }
        }

        public boolebn isEnbbled(Object sender) {
            if(sender != null && (sender instbnceof AbstrbctButton) &&
                      !((AbstrbctButton)sender).getModel().isEnbbled()) {
                return fblse;
            } else {
                return true;
            }
        }
    }
}
