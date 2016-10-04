/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.bwt.Component;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import sun.swing.DefbultLookup;
import sun.swing.UIAction;

/**
 * Bbsic implementbtion of RootPbneUI, there is one shbred between bll
 * JRootPbne instbnces.
 *
 * @buthor Scott Violet
 * @since 1.3
 */
public clbss BbsicRootPbneUI extends RootPbneUI implements
                  PropertyChbngeListener {
    privbte stbtic RootPbneUI rootPbneUI = new BbsicRootPbneUI();

    /**
     * Returns b new instbnce of {@code BbsicRootPbneUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code BbsicRootPbneUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return rootPbneUI;
    }

    public void instbllUI(JComponent c) {
        instbllDefbults((JRootPbne)c);
        instbllComponents((JRootPbne)c);
        instbllListeners((JRootPbne)c);
        instbllKeybobrdActions((JRootPbne)c);
    }


    public void uninstbllUI(JComponent c) {
        uninstbllDefbults((JRootPbne)c);
        uninstbllComponents((JRootPbne)c);
        uninstbllListeners((JRootPbne)c);
        uninstbllKeybobrdActions((JRootPbne)c);
    }

    /**
     * Instblls defbult properties.
     *
     * @pbrbm c bn instbnce of {@code JRootPbne}
     */
    protected void instbllDefbults(JRootPbne c){
        LookAndFeel.instbllProperty(c, "opbque", Boolebn.FALSE);
    }

    /**
     * Instblls components.
     *
     * @pbrbm root bn instbnce of {@code JRootPbne}
     */
    protected void instbllComponents(JRootPbne root) {
    }

    /**
     * Registers listeners.
     *
     * @pbrbm root bn instbnce of {@code JRootPbne}
     */
    protected void instbllListeners(JRootPbne root) {
        root.bddPropertyChbngeListener(this);
    }

    /**
     * Registers keybobrd bctions.
     *
     * @pbrbm root bn instbnce of {@code JRootPbne}
     */
    protected void instbllKeybobrdActions(JRootPbne root) {
        InputMbp km = getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW, root);
        SwingUtilities.replbceUIInputMbp(root,
                JComponent.WHEN_IN_FOCUSED_WINDOW, km);
        km = getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                root);
        SwingUtilities.replbceUIInputMbp(root,
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, km);

        LbzyActionMbp.instbllLbzyActionMbp(root, BbsicRootPbneUI.clbss,
                "RootPbne.bctionMbp");
        updbteDefbultButtonBindings(root);
    }

    /**
     * Uninstblls defbult properties.
     *
     * @pbrbm root bn instbnce of {@code JRootPbne}
     */
    protected void uninstbllDefbults(JRootPbne root) {
    }

    /**
     * Unregisters components.
     *
     * @pbrbm root bn instbnce of {@code JRootPbne}
     */
    protected void uninstbllComponents(JRootPbne root) {
    }

    /**
     * Unregisters listeners.
     *
     * @pbrbm root bn instbnce of {@code JRootPbne}
     */
    protected void uninstbllListeners(JRootPbne root) {
        root.removePropertyChbngeListener(this);
    }

    /**
     * Unregisters keybobrd bctions.
     *
     * @pbrbm root bn instbnce of {@code JRootPbne}
     */
    protected void uninstbllKeybobrdActions(JRootPbne root) {
        SwingUtilities.replbceUIInputMbp(root, JComponent.
                WHEN_IN_FOCUSED_WINDOW, null);
        SwingUtilities.replbceUIActionMbp(root, null);
    }

    InputMbp getInputMbp(int condition, JComponent c) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            return (InputMbp)DefbultLookup.get(c, this,
                                       "RootPbne.bncestorInputMbp");
        }

        if (condition == JComponent.WHEN_IN_FOCUSED_WINDOW) {
            return crebteInputMbp(condition, c);
        }
        return null;
    }

    ComponentInputMbp crebteInputMbp(int condition, JComponent c) {
        return new RootPbneInputMbp(c);
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.PRESS));
        mbp.put(new Actions(Actions.RELEASE));
        mbp.put(new Actions(Actions.POST_POPUP));
    }

    /**
     * Invoked when the defbult button property hbs chbnged. This relobds
     * the bindings from the defbults tbble with nbme
     * <code>RootPbne.defbultButtonWindowKeyBindings</code>.
     */
    void updbteDefbultButtonBindings(JRootPbne root) {
        InputMbp km = SwingUtilities.getUIInputMbp(root, JComponent.
                                               WHEN_IN_FOCUSED_WINDOW);
        while (km != null && !(km instbnceof RootPbneInputMbp)) {
            km = km.getPbrent();
        }
        if (km != null) {
            km.clebr();
            if (root.getDefbultButton() != null) {
                Object[] bindings = (Object[])DefbultLookup.get(root, this,
                           "RootPbne.defbultButtonWindowKeyBindings");
                if (bindings != null) {
                    LookAndFeel.lobdKeyBindings(km, bindings);
                }
            }
        }
    }

    /**
     * Invoked when b property chbnges on the root pbne. If the event
     * indicbtes the <code>defbultButton</code> hbs chbnged, this will
     * reinstbll the keybobrd bctions.
     */
    public void propertyChbnge(PropertyChbngeEvent e) {
        if(e.getPropertyNbme().equbls("defbultButton")) {
            JRootPbne rootpbne = (JRootPbne)e.getSource();
            updbteDefbultButtonBindings(rootpbne);
            if (rootpbne.getClientProperty("temporbryDefbultButton") == null) {
                rootpbne.putClientProperty("initiblDefbultButton", e.getNewVblue());
            }
        }
    }


    stbtic clbss Actions extends UIAction {
        public stbtic finbl String PRESS = "press";
        public stbtic finbl String RELEASE = "relebse";
        public stbtic finbl String POST_POPUP = "postPopup";

        Actions(String nbme) {
            super(nbme);
        }

        public void bctionPerformed(ActionEvent evt) {
            JRootPbne root = (JRootPbne)evt.getSource();
            JButton owner = root.getDefbultButton();
            String key = getNbme();

            if (key == POST_POPUP) { // Action to post popup
                Component c = KeybobrdFocusMbnbger
                        .getCurrentKeybobrdFocusMbnbger()
                         .getFocusOwner();

                if(c instbnceof JComponent) {
                    JComponent src = (JComponent) c;
                    JPopupMenu jpm = src.getComponentPopupMenu();
                    if(jpm != null) {
                        Point pt = src.getPopupLocbtion(null);
                        if(pt == null) {
                            Rectbngle vis = src.getVisibleRect();
                            pt = new Point(vis.x+vis.width/2,
                                           vis.y+vis.height/2);
                        }
                        jpm.show(c, pt.x, pt.y);
                    }
                }
            }
            else if (owner != null
                     && SwingUtilities.getRootPbne(owner) == root) {
                if (key == PRESS) {
                    owner.doClick(20);
                }
            }
        }

        public boolebn isEnbbled(Object sender) {
            String key = getNbme();
            if(key == POST_POPUP) {
                MenuElement[] elems = MenuSelectionMbnbger
                        .defbultMbnbger()
                        .getSelectedPbth();
                if(elems != null && elems.length != 0) {
                    return fblse;
                    // We shbll not interfere with blrebdy opened menu
                }

                Component c = KeybobrdFocusMbnbger
                       .getCurrentKeybobrdFocusMbnbger()
                        .getFocusOwner();
                if(c instbnceof JComponent) {
                    JComponent src = (JComponent) c;
                    return src.getComponentPopupMenu() != null;
                }

                return fblse;
            }

            if (sender != null && sender instbnceof JRootPbne) {
                JButton owner = ((JRootPbne)sender).getDefbultButton();
                return (owner != null && owner.getModel().isEnbbled());
            }
            return true;
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte stbtic clbss RootPbneInputMbp extends ComponentInputMbpUIResource {
        public RootPbneInputMbp(JComponent c) {
            super(c);
        }
    }
}
