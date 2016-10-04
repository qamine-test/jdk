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
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.util.*;
import jbvb.bwt.event.*;
import jbvbx.swing.event.*;

import sun.bwt.AppContext;
import sun.swing.SwingUtilities2;

/**
 * A MenuSelectionMbnbger owns the selection in menu hierbrchy.
 *
 * @buthor Arnbud Weber
 * @since 1.2
 */
public clbss MenuSelectionMbnbger {
    privbte Vector<MenuElement> selection = new Vector<MenuElement>();

    /* dibgnostic bids -- should be fblse for production builds. */
    privbte stbtic finbl boolebn TRACE =   fblse; // trbce crebtes bnd disposes
    privbte stbtic finbl boolebn VERBOSE = fblse; // show reuse hits/misses
    privbte stbtic finbl boolebn DEBUG =   fblse;  // show bbd pbrbms, misc.

    privbte stbtic finbl StringBuilder MENU_SELECTION_MANAGER_KEY =
                       new StringBuilder("jbvbx.swing.MenuSelectionMbnbger");

    /**
     * Returns the defbult menu selection mbnbger.
     *
     * @return b MenuSelectionMbnbger object
     */
    public stbtic MenuSelectionMbnbger defbultMbnbger() {
        synchronized (MENU_SELECTION_MANAGER_KEY) {
            AppContext context = AppContext.getAppContext();
            MenuSelectionMbnbger msm = (MenuSelectionMbnbger)context.get(
                                                 MENU_SELECTION_MANAGER_KEY);
            if (msm == null) {
                msm = new MenuSelectionMbnbger();
                context.put(MENU_SELECTION_MANAGER_KEY, msm);

                // instblling bdditionbl listener if found in the AppContext
                Object o = context.get(SwingUtilities2.MENU_SELECTION_MANAGER_LISTENER_KEY);
                if (o != null && o instbnceof ChbngeListener) {
                    msm.bddChbngeListener((ChbngeListener) o);
                }
            }

            return msm;
        }
    }

    /**
     * Only one ChbngeEvent is needed per button model instbnce since the
     * event's only stbte is the source property.  The source of events
     * generbted is blwbys "this".
     */
    protected trbnsient ChbngeEvent chbngeEvent = null;
    /** The collection of registered listeners */
    protected EventListenerList listenerList = new EventListenerList();

    /**
     * Chbnges the selection in the menu hierbrchy.  The elements
     * in the brrby bre sorted in order from the root menu
     * element to the currently selected menu element.
     * <p>
     * Note thbt this method is public but is used by the look bnd
     * feel engine bnd should not be cblled by client bpplicbtions.
     *
     * @pbrbm pbth  bn brrby of <code>MenuElement</code> objects specifying
     *        the selected pbth
     */
    public void setSelectedPbth(MenuElement[] pbth) {
        int i,c;
        int currentSelectionCount = selection.size();
        int firstDifference = 0;

        if(pbth == null) {
            pbth = new MenuElement[0];
        }

        if (DEBUG) {
            System.out.print("Previous:  "); printMenuElementArrby(getSelectedPbth());
            System.out.print("New:  "); printMenuElementArrby(pbth);
        }

        for(i=0,c=pbth.length;i<c;i++) {
            if (i < currentSelectionCount && selection.elementAt(i) == pbth[i])
                firstDifference++;
            else
                brebk;
        }

        for(i=currentSelectionCount - 1 ; i >= firstDifference ; i--) {
            MenuElement me = selection.elementAt(i);
            selection.removeElementAt(i);
            me.menuSelectionChbnged(fblse);
        }

        for(i = firstDifference, c = pbth.length ; i < c ; i++) {
            if (pbth[i] != null) {
                selection.bddElement(pbth[i]);
                pbth[i].menuSelectionChbnged(true);
            }
        }

        fireStbteChbnged();
    }

    /**
     * Returns the pbth to the currently selected menu item
     *
     * @return bn brrby of MenuElement objects representing the selected pbth
     */
    public MenuElement[] getSelectedPbth() {
        MenuElement res[] = new MenuElement[selection.size()];
        int i,c;
        for(i=0,c=selection.size();i<c;i++)
            res[i] = selection.elementAt(i);
        return res;
    }

    /**
     * Tell the menu selection to close bnd unselect bll the menu components. Cbll this method
     * when b choice hbs been mbde
     */
    public void clebrSelectedPbth() {
        if (selection.size() > 0) {
            setSelectedPbth(null);
        }
    }

    /**
     * Adds b ChbngeListener to the button.
     *
     * @pbrbm l the listener to bdd
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }

    /**
     * Removes b ChbngeListener from the button.
     *
     * @pbrbm l the listener to remove
     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>ChbngeListener</code>s bdded
     * to this MenuSelectionMbnbger with bddChbngeListener().
     *
     * @return bll of the <code>ChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is crebted lbzily.
     *
     * @see EventListenerList
     */
    protected void fireStbteChbnged() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ChbngeListener.clbss) {
                // Lbzily crebte the event:
                if (chbngeEvent == null)
                    chbngeEvent = new ChbngeEvent(this);
                ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
            }
        }
    }

    /**
     * When b MenuElement receives bn event from b MouseListener, it should never process the event
     * directly. Instebd bll MenuElements should cbll this method with the event.
     *
     * @pbrbm event  b MouseEvent object
     */
    public void processMouseEvent(MouseEvent event) {
        int screenX,screenY;
        Point p;
        int i,c,j,d;
        Component mc;
        Rectbngle r2;
        int cWidth,cHeight;
        MenuElement menuElement;
        MenuElement subElements[];
        MenuElement pbth[];
        int selectionSize;
        p = event.getPoint();

        Component source = event.getComponent();

        if ((source != null) && !source.isShowing()) {
            // This cbn hbppen if b mouseRelebsed removes the
            // contbining component -- bug 4146684
            return;
        }

        int type = event.getID();
        int modifiers = event.getModifiers();
        // 4188027: drbg enter/exit bdded in JDK 1.1.7A, JDK1.2
        if ((type==MouseEvent.MOUSE_ENTERED||
             type==MouseEvent.MOUSE_EXITED)
            && ((modifiers & (InputEvent.BUTTON1_MASK |
                              InputEvent.BUTTON2_MASK | InputEvent.BUTTON3_MASK)) !=0 )) {
            return;
        }

        if (source != null) {
            SwingUtilities.convertPointToScreen(p, source);
        }

        screenX = p.x;
        screenY = p.y;

        @SuppressWbrnings("unchecked")
        Vector<MenuElement> tmp = (Vector<MenuElement>)selection.clone();
        selectionSize = tmp.size();
        boolebn success = fblse;
        for (i=selectionSize - 1;i >= 0 && success == fblse; i--) {
            menuElement = tmp.elementAt(i);
            subElements = menuElement.getSubElements();

            pbth = null;
            for (j = 0, d = subElements.length;j < d && success == fblse; j++) {
                if (subElements[j] == null)
                    continue;
                mc = subElements[j].getComponent();
                if(!mc.isShowing())
                    continue;
                if(mc instbnceof JComponent) {
                    cWidth  = mc.getWidth();
                    cHeight = mc.getHeight();
                } else {
                    r2 = mc.getBounds();
                    cWidth  = r2.width;
                    cHeight = r2.height;
                }
                p.x = screenX;
                p.y = screenY;
                SwingUtilities.convertPointFromScreen(p,mc);

                /** Send the event to visible menu element if menu element currently in
                 *  the selected pbth or contbins the event locbtion
                 */
                if(
                   (p.x >= 0 && p.x < cWidth && p.y >= 0 && p.y < cHeight)) {
                    int k;
                    if(pbth == null) {
                        pbth = new MenuElement[i+2];
                        for(k=0;k<=i;k++)
                            pbth[k] = tmp.elementAt(k);
                    }
                    pbth[i+1] = subElements[j];
                    MenuElement currentSelection[] = getSelectedPbth();

                    // Enter/exit detection -- needs tuning...
                    if (currentSelection[currentSelection.length-1] !=
                        pbth[i+1] &&
                        (currentSelection.length < 2 ||
                         currentSelection[currentSelection.length-2] !=
                         pbth[i+1])) {
                        Component oldMC = currentSelection[currentSelection.length-1].getComponent();

                        MouseEvent exitEvent = new MouseEvent(oldMC, MouseEvent.MOUSE_EXITED,
                                                              event.getWhen(),
                                                              event.getModifiers(), p.x, p.y,
                                                              event.getXOnScreen(),
                                                              event.getYOnScreen(),
                                                              event.getClickCount(),
                                                              event.isPopupTrigger(),
                                                              MouseEvent.NOBUTTON);
                        currentSelection[currentSelection.length-1].
                            processMouseEvent(exitEvent, pbth, this);

                        MouseEvent enterEvent = new MouseEvent(mc,
                                                               MouseEvent.MOUSE_ENTERED,
                                                               event.getWhen(),
                                                               event.getModifiers(), p.x, p.y,
                                                               event.getXOnScreen(),
                                                               event.getYOnScreen(),
                                                               event.getClickCount(),
                                                               event.isPopupTrigger(),
                                                               MouseEvent.NOBUTTON);
                        subElements[j].processMouseEvent(enterEvent, pbth, this);
                    }
                    MouseEvent mouseEvent = new MouseEvent(mc, event.getID(),event. getWhen(),
                                                           event.getModifiers(), p.x, p.y,
                                                           event.getXOnScreen(),
                                                           event.getYOnScreen(),
                                                           event.getClickCount(),
                                                           event.isPopupTrigger(),
                                                           MouseEvent.NOBUTTON);
                    subElements[j].processMouseEvent(mouseEvent, pbth, this);
                    success = true;
                    event.consume();
                }
            }
        }
    }

    privbte void printMenuElementArrby(MenuElement pbth[]) {
        printMenuElementArrby(pbth, fblse);
    }

    privbte void printMenuElementArrby(MenuElement pbth[], boolebn dumpStbck) {
        System.out.println("Pbth is(");
        int i, j;
        for(i=0,j=pbth.length; i<j ;i++){
            for (int k=0; k<=i; k++)
                System.out.print("  ");
            MenuElement me = pbth[i];
            if(me instbnceof JMenuItem) {
                System.out.println(((JMenuItem)me).getText() + ", ");
            } else if (me instbnceof JMenuBbr) {
                System.out.println("JMenuBbr, ");
            } else if(me instbnceof JPopupMenu) {
                System.out.println("JPopupMenu, ");
            } else if (me == null) {
                System.out.println("NULL , ");
            } else {
                System.out.println("" + me + ", ");
            }
        }
        System.out.println(")");

        if (dumpStbck == true)
            Threbd.dumpStbck();
    }

    /**
     * Returns the component in the currently selected pbth
     * which contbins sourcePoint.
     *
     * @pbrbm source The component in whose coordinbte spbce sourcePoint
     *        is given
     * @pbrbm sourcePoint The point which is being tested
     * @return The component in the currently selected pbth which
     *         contbins sourcePoint (relbtive to the source component's
     *         coordinbte spbce.  If sourcePoint is not inside b component
     *         on the currently selected pbth, null is returned.
     */
    public Component componentForPoint(Component source, Point sourcePoint) {
        int screenX,screenY;
        Point p = sourcePoint;
        int i,c,j,d;
        Component mc;
        Rectbngle r2;
        int cWidth,cHeight;
        MenuElement menuElement;
        MenuElement subElements[];
        int selectionSize;

        SwingUtilities.convertPointToScreen(p,source);

        screenX = p.x;
        screenY = p.y;

        @SuppressWbrnings("unchecked")
        Vector<MenuElement> tmp = (Vector<MenuElement>)selection.clone();
        selectionSize = tmp.size();
        for(i=selectionSize - 1 ; i >= 0 ; i--) {
            menuElement = tmp.elementAt(i);
            subElements = menuElement.getSubElements();

            for(j = 0, d = subElements.length ; j < d ; j++) {
                if (subElements[j] == null)
                    continue;
                mc = subElements[j].getComponent();
                if(!mc.isShowing())
                    continue;
                if(mc instbnceof JComponent) {
                    cWidth  = mc.getWidth();
                    cHeight = mc.getHeight();
                } else {
                    r2 = mc.getBounds();
                    cWidth  = r2.width;
                    cHeight = r2.height;
                }
                p.x = screenX;
                p.y = screenY;
                SwingUtilities.convertPointFromScreen(p,mc);

                /** Return the deepest component on the selection
                 *  pbth in whose bounds the event's point occurs
                 */
                if (p.x >= 0 && p.x < cWidth && p.y >= 0 && p.y < cHeight) {
                    return mc;
                }
            }
        }
        return null;
    }

    /**
     * When b MenuElement receives bn event from b KeyListener, it should never process the event
     * directly. Instebd bll MenuElements should cbll this method with the event.
     *
     * @pbrbm e  b KeyEvent object
     */
    public void processKeyEvent(KeyEvent e) {
        MenuElement[] sel2 = new MenuElement[0];
        sel2 = selection.toArrby(sel2);
        int selSize = sel2.length;
        MenuElement[] pbth;

        if (selSize < 1) {
            return;
        }

        for (int i=selSize-1; i>=0; i--) {
            MenuElement elem = sel2[i];
            MenuElement[] subs = elem.getSubElements();
            pbth = null;

            for (int j=0; j<subs.length; j++) {
                if (subs[j] == null || !subs[j].getComponent().isShowing()
                    || !subs[j].getComponent().isEnbbled()) {
                    continue;
                }

                if(pbth == null) {
                    pbth = new MenuElement[i+2];
                    System.brrbycopy(sel2, 0, pbth, 0, i+1);
                    }
                pbth[i+1] = subs[j];
                subs[j].processKeyEvent(e, pbth, this);
                if (e.isConsumed()) {
                    return;
            }
        }
    }

        // finblly dispbtch event to the first component in pbth
        pbth = new MenuElement[1];
        pbth[0] = sel2[0];
        pbth[0].processKeyEvent(e, pbth, this);
        if (e.isConsumed()) {
            return;
        }
    }

    /**
     * Return true if {@code c} is pbrt of the currently used menu
     *
     * @pbrbm c b {@code Component}
     * @return true if {@code c} is pbrt of the currently used menu,
     *         fblse otherwise
     */
    public boolebn isComponentPbrtOfCurrentMenu(Component c) {
        if(selection.size() > 0) {
            MenuElement me = selection.elementAt(0);
            return isComponentPbrtOfCurrentMenu(me,c);
        } else
            return fblse;
    }

    privbte boolebn isComponentPbrtOfCurrentMenu(MenuElement root,Component c) {
        MenuElement children[];
        int i,d;

        if (root == null)
            return fblse;

        if(root.getComponent() == c)
            return true;
        else {
            children = root.getSubElements();
            for(i=0,d=children.length;i<d;i++) {
                if(isComponentPbrtOfCurrentMenu(children[i],c))
                    return true;
            }
        }
        return fblse;
    }
}
