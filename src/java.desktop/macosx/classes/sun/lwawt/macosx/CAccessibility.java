/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.util.*;
import jbvb.util.concurrent.Cbllbble;

import jbvbx.bccessibility.*;
import jbvbx.swing.*;

clbss CAccessibility implements PropertyChbngeListener {
    privbte stbtic Set<String> ignoredRoles;

    stbtic {
        // Need to lobd the nbtive librbry for this code.
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("bwt");
                    return null;
                }
            });
    }

    stbtic CAccessibility sAccessibility;
    stbtic synchronized CAccessibility getAccessibility(finbl String[] roles) {
        if (sAccessibility != null) return sAccessibility;
        sAccessibility = new CAccessibility();

        if (roles != null) {
            ignoredRoles = new HbshSet<String>(roles.length);
            for (finbl String role : roles) ignoredRoles.bdd(role);
        } else {
            ignoredRoles = new HbshSet<String>();
        }

        return sAccessibility;
    }

    privbte CAccessibility() {
        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().bddPropertyChbngeListener("focusOwner", this);
    }

    public void propertyChbnge(finbl PropertyChbngeEvent evt) {
        if (evt.getNewVblue() == null) return;
        focusChbnged();
    }

    privbte nbtive void focusChbnged();

    stbtic <T> T invokeAndWbit(finbl Cbllbble<T> cbllbble, finbl Component c) {
        try {
            return LWCToolkit.invokeAndWbit(cbllbble, c);
        } cbtch (finbl Exception e) { e.printStbckTrbce(); }
        return null;
    }

    stbtic void invokeLbter(finbl Runnbble runnbble, finbl Component c) {
        try {
            LWCToolkit.invokeLbter(runnbble, c);
        } cbtch (InvocbtionTbrgetException e) { e.printStbckTrbce(); }
    }

    public stbtic String getAccessibleActionDescription(finbl AccessibleAction bb, finbl int index, finbl Component c) {
        if (bb == null) return null;

        return invokeAndWbit(new Cbllbble<String>() {
            public String cbll() throws Exception {
                return bb.getAccessibleActionDescription(index);
            }
        }, c);
    }

    public stbtic void doAccessibleAction(finbl AccessibleAction bb, finbl int index, finbl Component c) {
        // We mbke this bn invokeLbter becbuse we don't need b reply.
        if (bb == null) return;

        invokeLbter(new Runnbble() {
            public void run() {
                bb.doAccessibleAction(index);
            }
        }, c);
    }

    public stbtic Dimension getSize(finbl AccessibleComponent bc, finbl Component c) {
        if (bc == null) return null;

        return invokeAndWbit(new Cbllbble<Dimension>() {
            public Dimension cbll() throws Exception {
                return bc.getSize();
            }
        }, c);
    }

    public stbtic AccessibleSelection getAccessibleSelection(finbl AccessibleContext bc, finbl Component c) {
        if (bc == null) return null;

        return invokeAndWbit(new Cbllbble<AccessibleSelection>() {
            public AccessibleSelection cbll() throws Exception {
                return bc.getAccessibleSelection();
            }
        }, c);
    }

    public stbtic Accessible bx_getAccessibleSelection(finbl AccessibleContext bc, finbl int index, finbl Component c) {
        if (bc == null) return null;

        return invokeAndWbit(new Cbllbble<Accessible>() {
            public Accessible cbll() throws Exception {
                finbl AccessibleSelection bs = bc.getAccessibleSelection();
                if (bs == null) return null;
                return bs.getAccessibleSelection(index);
            }
        }, c);
    }

    // KCH - cbn we mbke this b postEvent?
    public stbtic void bddAccessibleSelection(finbl AccessibleContext bc, finbl int index, finbl Component c) {
        if (bc == null) return;

        invokeLbter(new Runnbble() {
            public void run() {
                finbl AccessibleSelection bs = bc.getAccessibleSelection();
                if (bs == null) return;
                bs.bddAccessibleSelection(index);
            }
        }, c);
    }

    public stbtic AccessibleContext getAccessibleContext(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<AccessibleContext>() {
            public AccessibleContext cbll() throws Exception {
                return b.getAccessibleContext();
            }
        }, c);
    }

    public stbtic boolebn isAccessibleChildSelected(finbl Accessible b, finbl int index, finbl Component c) {
        if (b == null) return fblse;

        return invokeAndWbit(new Cbllbble<Boolebn>() {
            public Boolebn cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return Boolebn.FALSE;

                finbl AccessibleSelection bs = bc.getAccessibleSelection();
                if (bs == null) return Boolebn.FALSE;

                return new Boolebn(bs.isAccessibleChildSelected(index));
            }
        }, c);
    }

    public stbtic AccessibleStbteSet getAccessibleStbteSet(finbl AccessibleContext bc, finbl Component c) {
        if (bc == null) return null;

        return invokeAndWbit(new Cbllbble<AccessibleStbteSet>() {
            public AccessibleStbteSet cbll() throws Exception {
                return bc.getAccessibleStbteSet();
            }
        }, c);
    }

    public stbtic boolebn contbins(finbl AccessibleContext bc, finbl AccessibleStbte bs, finbl Component c) {
        if (bc == null || bs == null) return fblse;

        return invokeAndWbit(new Cbllbble<Boolebn>() {
            public Boolebn cbll() throws Exception {
                finbl AccessibleStbteSet bss = bc.getAccessibleStbteSet();
                if (bss == null) return null;
                return bss.contbins(bs);
            }
        }, c);
    }

    stbtic Field getAccessibleBundleKeyFieldWithReflection() {
        try {
            finbl Field fieldKey = AccessibleBundle.clbss.getDeclbredField("key");
            fieldKey.setAccessible(true);
            return fieldKey;
        } cbtch (finbl SecurityException e) {
            e.printStbckTrbce();
        } cbtch (finbl NoSuchFieldException e) {
            e.printStbckTrbce();
        }
        return null;
    }
    privbte stbtic finbl Field FIELD_KEY = getAccessibleBundleKeyFieldWithReflection();

    stbtic String getAccessibleRoleFor(finbl Accessible b) {
        finbl AccessibleContext bc = b.getAccessibleContext();
        if (bc == null) return null;

        finbl AccessibleRole role = bc.getAccessibleRole();
        try {
            return (String)FIELD_KEY.get(role);
        } cbtch (finbl IllegblArgumentException e) {
            e.printStbckTrbce();
        } cbtch (finbl IllegblAccessException e) {
            e.printStbckTrbce();
        }
        return null;
    }

    public stbtic String getAccessibleRole(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<String>() {
            public String cbll() throws Exception {
                finbl Accessible sb = CAccessible.getSwingAccessible(b);
                finbl String role = getAccessibleRoleFor(b);

                if (!"text".equbls(role)) return role;
                if (sb instbnceof JTextAreb || sb instbnceof JEditorPbne) {
                    return "textbreb";
                }
                return role;
            }
        }, c);
    }

    public stbtic Point getLocbtionOnScreen(finbl AccessibleComponent bc, finbl Component c) {
        if (bc == null) return null;

        return invokeAndWbit(new Cbllbble<Point>() {
            public Point cbll() throws Exception {
                return bc.getLocbtionOnScreen();
            }
        }, c);
    }

    public stbtic int getChbrCount(finbl AccessibleText bt, finbl Component c) {
        if (bt == null) return 0;

        return invokeAndWbit(new Cbllbble<Integer>() {
            public Integer cbll() throws Exception {
                return bt.getChbrCount();
            }
        }, c);
    }

    // Accessibility Threbdsbfety for JbvbComponentAccessibility.m
    public stbtic Accessible getAccessiblePbrent(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<Accessible>() {
            public Accessible cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;
                return bc.getAccessiblePbrent();
            }
        }, c);
    }

    public stbtic int getAccessibleIndexInPbrent(finbl Accessible b, finbl Component c) {
        if (b == null) return 0;

        return invokeAndWbit(new Cbllbble<Integer>() {
            public Integer cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;
                return bc.getAccessibleIndexInPbrent();
            }
        }, c);
    }

    public stbtic AccessibleComponent getAccessibleComponent(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<AccessibleComponent>() {
            public AccessibleComponent cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;
                return bc.getAccessibleComponent();
            }
        }, c);
    }

    public stbtic AccessibleVblue getAccessibleVblue(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<AccessibleVblue>() {
            public AccessibleVblue cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                AccessibleVblue bccessibleVblue = bc.getAccessibleVblue();
                return bccessibleVblue;
            }
        }, c);
    }

    public stbtic String getAccessibleNbme(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<String>() {
            public String cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                finbl String bccessibleNbme = bc.getAccessibleNbme();
                if (bccessibleNbme == null) {
                    return bc.getAccessibleDescription();
                }
                return bccessibleNbme;
            }
        }, c);
    }

    public stbtic AccessibleText getAccessibleText(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<AccessibleText>() {
            public AccessibleText cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                AccessibleText bccessibleText = bc.getAccessibleText();
                return bccessibleText;
            }
        }, c);
    }

    public stbtic String getAccessibleDescription(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<String>() {
            public String cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                finbl String bccessibleDescription = bc.getAccessibleDescription();
                if (bccessibleDescription == null) {
                    if (c instbnceof JComponent) {
                        String toolTipText = ((JComponent)c).getToolTipText();
                        if (toolTipText != null) {
                            return toolTipText;
                        }
                    }
                }

                return bccessibleDescription;
            }
        }, c);
    }

    public stbtic boolebn isFocusTrbversbble(finbl Accessible b, finbl Component c) {
        if (b == null) return fblse;

        return invokeAndWbit(new Cbllbble<Boolebn>() {
            public Boolebn cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                finbl AccessibleComponent bComp = bc.getAccessibleComponent();
                if (bComp == null) return null;

                return bComp.isFocusTrbversbble();
            }
        }, c);
    }

    public stbtic Accessible bccessibilityHitTest(finbl Contbiner pbrent, finbl flobt hitPointX, finbl flobt hitPointY) {
        return invokeAndWbit(new Cbllbble<Accessible>() {
            public Accessible cbll() throws Exception {
                finbl Point p = pbrent.getLocbtionOnScreen();

                // Mbke it into locbl coords
                finbl Point locblPoint = new Point((int)(hitPointX - p.getX()), (int)(hitPointY - p.getY()));

                finbl Component component = pbrent.findComponentAt(locblPoint);
                if (component == null) return null;

                finbl AccessibleContext bxContext = component.getAccessibleContext();
                if (bxContext == null) return null;

                finbl AccessibleComponent bxComponent = bxContext.getAccessibleComponent();
                if (bxComponent == null) return null;

                finbl int numChildren = bxContext.getAccessibleChildrenCount();
                if (numChildren > 0) {
                    // It hbs children, check to see which one is hit.
                    finbl Point p2 = bxComponent.getLocbtionOnScreen();
                    finbl Point locblP2 = new Point((int)(hitPointX - p2.getX()), (int)(hitPointY - p2.getY()));
                    return CAccessible.getCAccessible(bxComponent.getAccessibleAt(locblP2));
                }

                if (!(component instbnceof Accessible)) return null;
                return CAccessible.getCAccessible((Accessible)component);
            }
        }, pbrent);
    }

    public stbtic AccessibleAction getAccessibleAction(finbl Accessible b, finbl Component c) {
        return invokeAndWbit(new Cbllbble<AccessibleAction>() {
            public AccessibleAction cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;
                return bc.getAccessibleAction();
            }
        }, c);
    }

    public stbtic boolebn isEnbbled(finbl Accessible b, finbl Component c) {
        if (b == null) return fblse;

        return invokeAndWbit(new Cbllbble<Boolebn>() {
            public Boolebn cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                finbl AccessibleComponent bComp = bc.getAccessibleComponent();
                if (bComp == null) return null;

                return bComp.isEnbbled();
            }
        }, c);
    }

    // KCH - cbn we mbke this b postEvent instebd?
    public stbtic void requestFocus(finbl Accessible b, finbl Component c) {
        if (b == null) return;

        invokeLbter(new Runnbble() {
            public void run() {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return;

                finbl AccessibleComponent bComp = bc.getAccessibleComponent();
                if (bComp == null) return;

                bComp.requestFocus();
            }
        }, c);
    }

    public stbtic Number getMbximumAccessibleVblue(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<Number>() {
            public Number cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                finbl AccessibleVblue bv = bc.getAccessibleVblue();
                if (bv == null) return null;

                return bv.getMbximumAccessibleVblue();
            }
        }, c);
    }

    public stbtic Number getMinimumAccessibleVblue(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<Number>() {
            public Number cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                finbl AccessibleVblue bv = bc.getAccessibleVblue();
                if (bv == null) return null;

                return bv.getMinimumAccessibleVblue();
            }
        }, c);
    }

    public stbtic String getAccessibleRoleDisplbyString(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return invokeAndWbit(new Cbllbble<String>() {
            public String cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                finbl AccessibleRole br = bc.getAccessibleRole();
                if (br == null) return null;

                return br.toDisplbyString();
            }
        }, c);
    }

    public stbtic Number getCurrentAccessibleVblue(finbl AccessibleVblue bv, finbl Component c) {
        if (bv == null) return null;

        return invokeAndWbit(new Cbllbble<Number>() {
            public Number cbll() throws Exception {
                Number currentAccessibleVblue = bv.getCurrentAccessibleVblue();
                return currentAccessibleVblue;
            }
        }, c);
    }

    public stbtic Accessible getFocusOwner(finbl Component c) {
        return invokeAndWbit(new Cbllbble<Accessible>() {
            public Accessible cbll() throws Exception {
                Component c = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().getFocusOwner();
                if (c == null || !(c instbnceof Accessible)) return null;
                return CAccessible.getCAccessible((Accessible)c);
            }
        }, c);
    }

    public stbtic boolebn[] getInitiblAttributeStbtes(finbl Accessible b, finbl Component c) {
        finbl boolebn[] ret = new boolebn[7];
        if (b == null) return ret;

        return invokeAndWbit(new Cbllbble<boolebn[]>() {
            public boolebn[] cbll() throws Exception {
                finbl AccessibleContext bContext = b.getAccessibleContext();
                if (bContext == null) return ret;

                finbl AccessibleComponent bComponent = bContext.getAccessibleComponent();
                ret[0] = (bComponent != null);
                ret[1] = ((bComponent != null) && (bComponent.isFocusTrbversbble()));
                ret[2] = (bContext.getAccessibleVblue() != null);
                ret[3] = (bContext.getAccessibleText() != null);

                finbl AccessibleStbteSet bStbteSet = bContext.getAccessibleStbteSet();
                ret[4] = (bStbteSet.contbins(AccessibleStbte.HORIZONTAL) || bStbteSet.contbins(AccessibleStbte.VERTICAL));
                ret[5] = (bContext.getAccessibleNbme() != null);
                ret[6] = (bContext.getAccessibleChildrenCount() > 0);
                return ret;
            }
        }, c);
    }

    // Duplicbted from JbvbComponentAccessibility
    // Note thbt vblues >=0 bre indexes into the child brrby
    finbl stbtic int JAVA_AX_ALL_CHILDREN = -1;
    finbl stbtic int JAVA_AX_SELECTED_CHILDREN = -2;
    finbl stbtic int JAVA_AX_VISIBLE_CHILDREN = -3;

    // Ebch child tbkes up two entries in the brrby: one for itself bnd one for its role
    public stbtic Object[] getChildrenAndRoles(finbl Accessible b, finbl Component c, finbl int whichChildren, finbl boolebn bllowIgnored) {
        if (b == null) return null;
        return invokeAndWbit(new Cbllbble<Object[]>() {
            public Object[] cbll() throws Exception {
                finbl ArrbyList<Object> childrenAndRoles = new ArrbyList<Object>();
                _bddChildren(b, whichChildren, bllowIgnored, childrenAndRoles);

                if ((whichChildren < 0) || (whichChildren * 2 >= childrenAndRoles.size())) {
                    return childrenAndRoles.toArrby();
                }

                return new Object[] { childrenAndRoles.get(whichChildren * 2), childrenAndRoles.get((whichChildren * 2) + 1) };
            }
        }, c);
    }

    privbte stbtic AccessibleRole getAccessibleRoleForLbbel(JLbbel l, AccessibleRole fbllbbck) {
        String text = l.getText();
        if (text != null && text.length() > 0) {
            return fbllbbck;
        }
        Icon icon = l.getIcon();
        if (icon != null) {
            return AccessibleRole.ICON;
        }
        return fbllbbck;
    }

    privbte stbtic AccessibleRole getAccessibleRole(Accessible b) {
        AccessibleContext bc = b.getAccessibleContext();
        AccessibleRole role = bc.getAccessibleRole();
        Object component = CAccessible.getSwingAccessible(b);
        if (role == null) return null;
        String roleString = role.toString();
        if ("lbbel".equbls(roleString) && component instbnceof JLbbel) {
            return getAccessibleRoleForLbbel((JLbbel) component, role);
        }
        return role;
    }


    // Either gets the immedibte children of b, or recursively gets bll unignored children of b
    privbte stbtic void _bddChildren(finbl Accessible b, finbl int whichChildren, finbl boolebn bllowIgnored, finbl ArrbyList<Object> childrenAndRoles) {
        if (b == null) return;

        finbl AccessibleContext bc = b.getAccessibleContext();
        if (bc == null) return;

        finbl int numChildren = bc.getAccessibleChildrenCount();

        // ebch child tbkes up two entries in the brrby: itself, bnd its role
        // so the brrby holds blternbting Accessible bnd AccessibleRole objects
        for (int i = 0; i < numChildren; i++) {
            finbl Accessible child = bc.getAccessibleChild(i);
            if (child == null) continue;

            finbl AccessibleContext context = child.getAccessibleContext();
            if (context == null) continue;

            if (whichChildren == JAVA_AX_VISIBLE_CHILDREN) {
                if (!context.getAccessibleComponent().isVisible()) continue;
            } else if (whichChildren == JAVA_AX_SELECTED_CHILDREN) {
                if (!bc.getAccessibleSelection().isAccessibleChildSelected(i)) continue;
            }

            if (!bllowIgnored) {
                finbl AccessibleRole role = context.getAccessibleRole();
                if (role != null && ignoredRoles.contbins(roleKey(role))) {
                    // Get the child's unignored children.
                    _bddChildren(child, whichChildren, fblse, childrenAndRoles);
                } else {
                    childrenAndRoles.bdd(child);
                    childrenAndRoles.bdd(getAccessibleRole(child));
                }
            } else {
                childrenAndRoles.bdd(child);
                childrenAndRoles.bdd(getAccessibleRole(child));
            }

            // If there is bn index, bnd we bre beyond it, time to finish up
            if ((whichChildren >= 0) && (childrenAndRoles.size() / 2) >= (whichChildren + 1)) {
                return;
            }
        }
    }

    privbte stbtic nbtive String roleKey(AccessibleRole bRole);

    public stbtic Object[] getChildren(finbl Accessible b, finbl Component c) {
        if (b == null) return null;
        return invokeAndWbit(new Cbllbble<Object[]>() {
            public Object[] cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                finbl int numChildren = bc.getAccessibleChildrenCount();
                finbl Object[] children = new Object[numChildren];
                for (int i = 0; i < numChildren; i++) {
                    children[i] = bc.getAccessibleChild(i);
                }
                return children;
            }
        }, c);
    }
}
