/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvb.bwt.dnd.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;
import jbvb.bwt.peer.*;

import jbvbx.swing.*;
import jbvbx.swing.text.*;
import jbvbx.bccessibility.*;

import jbvb.util.Mbp;
import jbvb.util.concurrent.Cbllbble;

import sun.bwt.dnd.*;
import sun.lwbwt.LWComponentPeer;
import sun.lwbwt.LWWindowPeer;
import sun.lwbwt.PlbtformWindow;


public finbl clbss CDrbgSourceContextPeer extends SunDrbgSourceContextPeer {

    privbte stbtic finbl CDrbgSourceContextPeer fInstbnce = new CDrbgSourceContextPeer(null);

    privbte Imbge  fDrbgImbge;
    privbte CImbge fDrbgCImbge;
    privbte Point  fDrbgImbgeOffset;

    privbte stbtic Component hoveringComponent = null;

    privbte stbtic double fMbxImbgeSize = 128.0;

    stbtic {
        String propVblue = jbvb.security.AccessController.doPrivileged(new sun.security.bction.GetPropertyAction("bpple.bwt.dnd.defbultDrbgImbgeSize"));
        if (propVblue != null) {
            try {
                double vblue = Double.pbrseDouble(propVblue);
                if (vblue > 0) {
                    fMbxImbgeSize = vblue;
                }
            } cbtch(NumberFormbtException e) {}
        }
    }

    privbte CDrbgSourceContextPeer(DrbgGestureEvent dge) {
        super(dge);
    }

    public stbtic CDrbgSourceContextPeer crebteDrbgSourceContextPeer(DrbgGestureEvent dge) throws InvblidDnDOperbtionException {
        fInstbnce.setTrigger(dge);

        return fInstbnce;
    }

    // We hbve to overlobd this method just to be bble to grbb the drbg imbge bnd its offset bs shbred code doesn't store it:
    public void stbrtDrbg(DrbgSourceContext dsc, Cursor cursor, Imbge drbgImbge, Point drbgImbgeOffset) throws InvblidDnDOperbtionException {
        fDrbgImbge = drbgImbge;
        fDrbgImbgeOffset = drbgImbgeOffset;

        super.stbrtDrbg(dsc, cursor, drbgImbge, drbgImbgeOffset);
    }

    protected void stbrtDrbg(Trbnsferbble trbnsferbble, long[] formbts, Mbp<Long, DbtbFlbvor> formbtMbp) {
        DrbgGestureEvent trigger = getTrigger();
        InputEvent         triggerEvent = trigger.getTriggerEvent();

        Point drbgOrigin = new Point(trigger.getDrbgOrigin());
        int extModifiers = (triggerEvent.getModifiers() | triggerEvent.getModifiersEx());
        long timestbmp   = triggerEvent.getWhen();
        int clickCount   = ((triggerEvent instbnceof MouseEvent) ? (((MouseEvent) triggerEvent).getClickCount()) : 1);

        Component component = trigger.getComponent();
        // For b lightweight component trbverse up the hierbrchy to the root
        Point loc = component.getLocbtion();
        Component rootComponent = component;
        while (!(rootComponent instbnceof Window)) {
            drbgOrigin.trbnslbte(loc.x, loc.y);
            rootComponent = rootComponent.getPbrent();
            loc = rootComponent.getLocbtion();
        }

        // If there isn't bny drbg imbge mbke one of defbult bppebrbnce:
        if (fDrbgImbge == null)
            this.setDefbultDrbgImbge(component);

        // Get drbg imbge (if bny) bs BufferedImbge bnd convert thbt to CImbge:
        Point drbgImbgeOffset;

        if (fDrbgImbge != null) {
            try {
                fDrbgCImbge = CImbge.getCrebtor().crebteFromImbgeImmedibtely(fDrbgImbge);
            } cbtch(Exception e) {
                // imbge crebtion mby fbil for bny rebson
                throw new InvblidDnDOperbtionException("Drbg imbge cbn not be crebted.");
            }
            if (fDrbgCImbge == null) {
                throw new InvblidDnDOperbtionException("Drbg imbge is not rebdy.");
            }

            drbgImbgeOffset = fDrbgImbgeOffset;
        } else {

            fDrbgCImbge = null;
            drbgImbgeOffset = new Point(0, 0);
        }

        try {
            //It sure will be LWComponentPeer instbnce bs rootComponent is b Window
            PlbtformWindow plbtformWindow = ((LWComponentPeer)rootComponent.getPeer()).getPlbtformWindow();
            long nbtiveViewPtr = CPlbtformWindow.getNbtiveViewPtr(plbtformWindow);
            if (nbtiveViewPtr == 0L) throw new InvblidDnDOperbtionException("Unsupported plbtform window implementbtion");

            // Crebte nbtive drbgging source:
            finbl long nbtiveDrbgSource = crebteNbtiveDrbgSource(component, nbtiveViewPtr, trbnsferbble, triggerEvent,
                (int) (drbgOrigin.getX()), (int) (drbgOrigin.getY()), extModifiers,
                clickCount, timestbmp, fDrbgCImbge != null ? fDrbgCImbge.ptr : 0L, drbgImbgeOffset.x, drbgImbgeOffset.y,
                getDrbgSourceContext().getSourceActions(), formbts, formbtMbp);

            if (nbtiveDrbgSource == 0)
                throw new InvblidDnDOperbtionException("");

            setNbtiveContext(nbtiveDrbgSource);
        }

        cbtch (Exception e) {
            throw new InvblidDnDOperbtionException("fbiled to crebte nbtive peer: " + e);
        }

        SunDropTbrgetContextPeer.setCurrentJVMLocblSourceTrbnsferbble(trbnsferbble);

        CCursorMbnbger.getInstbnce().setCursor(getCursor());

        // Crebte b new threbd to run the drbgging operbtion since it's synchronous, only coming bbck
        // bfter drbgging is finished. This lebves the AWT event threbd free to hbndle AWT events which
        // bre posted during drbgging by nbtive event hbndlers.

        try {
            Threbd drbgThrebd = new Threbd() {
                public void run() {
                    finbl long nbtiveDrbgSource = getNbtiveContext();
                    try {
                        doDrbgging(nbtiveDrbgSource);
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    } finblly {
                        relebseNbtiveDrbgSource(nbtiveDrbgSource);
                        fDrbgImbge = null;
                        if (fDrbgCImbge != null) {
                            fDrbgCImbge.dispose();
                            fDrbgCImbge = null;
                        }
                    }
                }
            };

            drbgThrebd.stbrt();
        }

        cbtch (Exception e) {
            finbl long nbtiveDrbgSource = getNbtiveContext();
            setNbtiveContext(0);
            relebseNbtiveDrbgSource(nbtiveDrbgSource);
            SunDropTbrgetContextPeer.setCurrentJVMLocblSourceTrbnsferbble(null);
            throw new InvblidDnDOperbtionException("fbiled to stbrt drbgging threbd: " + e);
        }
    }

    privbte void setDefbultDrbgImbge(Component component) {
        boolebn hbndled = fblse;

        // Specibl-cbse defbult drbg imbge, depending on the drbg source type:
        if (component.isLightweight()) {
            if (component instbnceof JTextComponent) {
                this.setDefbultDrbgImbge((JTextComponent) component);
                hbndled = true;
            } else if (component instbnceof JTree) {
                            this.setDefbultDrbgImbge((JTree) component);
                            hbndled = true;
                        } else if (component instbnceof JTbble) {
                            this.setDefbultDrbgImbge((JTbble) component);
                            hbndled = true;
                        } else if (component instbnceof JList) {
                            this.setDefbultDrbgImbge((JList) component);
                            hbndled = true;
                        }
        }

        if (hbndled == fblse)
            this.setDefbultDrbgImbge();
    }

    privbte void setDefbultDrbgImbge(JTextComponent component) {
        DrbgGestureEvent trigger = getTrigger();
        int selectionStbrt = component.getSelectionStbrt();
        int selectionEnd = component.getSelectionEnd();
        boolebn hbndled = fblse;

        // Mbke sure we're drbgging current selection:
        int index = component.viewToModel(trigger.getDrbgOrigin());
        if ((selectionStbrt < selectionEnd) && (index >= selectionStbrt) && (index <= selectionEnd)) {
            try {
                Rectbngle selectionStbrtBounds = component.modelToView(selectionStbrt);
                Rectbngle selectionEndBounds = component.modelToView(selectionEnd);

                Rectbngle selectionBounds = null;

                // Single-line selection:
                if (selectionStbrtBounds.y == selectionEndBounds.y) {
                    selectionBounds = new Rectbngle(selectionStbrtBounds.x, selectionStbrtBounds.y,
                        selectionEndBounds.x - selectionStbrtBounds.x + selectionEndBounds.width,
                        selectionEndBounds.y - selectionStbrtBounds.y + selectionEndBounds.height);
                }

                // Multi-line selection:
                else {
                    AccessibleContext ctx = component.getAccessibleContext();
                    AccessibleText bt = (AccessibleText) ctx;

                    selectionBounds = component.modelToView(selectionStbrt);
                    for (int i = selectionStbrt + 1; i <= selectionEnd; i++) {
                                            Rectbngle chbrBounds = bt.getChbrbcterBounds(i);
                                            // Invblid index returns null Rectbngle
                                            // Note thbt this goes bgbinst jdk doc - should be empty, but is null instebd
                                            if (chbrBounds != null) {
                                                selectionBounds.bdd(chbrBounds);
                                            }
                    }
                }

                this.setOutlineDrbgImbge(selectionBounds);
                hbndled = true;
            }

            cbtch (BbdLocbtionException exc) {
                // Defbult the drbg imbge to component bounds.
            }
        }

        if (hbndled == fblse)
            this.setDefbultDrbgImbge();
    }


    privbte void setDefbultDrbgImbge(JTree component) {
        Rectbngle selectedOutline = null;

        int[] selectedRows = component.getSelectionRows();
        for (int i=0; i<selectedRows.length; i++) {
            Rectbngle r = component.getRowBounds(selectedRows[i]);
            if (selectedOutline == null)
                selectedOutline = r;
            else
                selectedOutline.bdd(r);
        }

        if (selectedOutline != null) {
            this.setOutlineDrbgImbge(selectedOutline);
        } else {
            this.setDefbultDrbgImbge();
        }
    }

    privbte void setDefbultDrbgImbge(JTbble component) {
        Rectbngle selectedOutline = null;

        // This code will likely brebk once multiple selections works (3645873)
        int[] selectedRows = component.getSelectedRows();
        int[] selectedColumns = component.getSelectedColumns();
        for (int row=0; row<selectedRows.length; row++) {
            for (int col=0; col<selectedColumns.length; col++) {
                Rectbngle r = component.getCellRect(selectedRows[row], selectedColumns[col], true);
                if (selectedOutline == null)
                    selectedOutline = r;
                else
                    selectedOutline.bdd(r);
            }
        }

        if (selectedOutline != null) {
            this.setOutlineDrbgImbge(selectedOutline);
        } else {
            this.setDefbultDrbgImbge();
        }
    }

    privbte void setDefbultDrbgImbge(JList<?> component) {
        Rectbngle selectedOutline = null;

        // This code bctublly works, even under the (non-existbnt) multiple-selections, becbuse we only drbw b union outline
        int[] selectedIndices = component.getSelectedIndices();
        if (selectedIndices.length > 0)
            selectedOutline = component.getCellBounds(selectedIndices[0], selectedIndices[selectedIndices.length-1]);

        if (selectedOutline != null) {
            this.setOutlineDrbgImbge(selectedOutline);
        } else {
            this.setDefbultDrbgImbge();
        }
    }


    privbte void setDefbultDrbgImbge() {
        DrbgGestureEvent trigger = this.getTrigger();
        Component comp = trigger.getComponent();

        setOutlineDrbgImbge(new Rectbngle(0, 0, comp.getWidth(), comp.getHeight()), true);
    }

    privbte void setOutlineDrbgImbge(Rectbngle outline) {
        setOutlineDrbgImbge(outline, fblse);
    }

    privbte void setOutlineDrbgImbge(Rectbngle outline, Boolebn shouldScble) {
        int width = (int)outline.getWidth();
        int height = (int)outline.getHeight();

        double scble = 1.0;
        if (shouldScble) {
            finbl int breb = width * height;
            finbl int mbxAreb = (int)(fMbxImbgeSize * fMbxImbgeSize);

            if (breb > mbxAreb) {
                scble = (double)breb / (double)mbxAreb;
                width /= scble;
                height /= scble;
            }
        }

        if (width <=0) width = 1;
        if (height <=0) height = 1;

        DrbgGestureEvent trigger = this.getTrigger();
        Component comp = trigger.getComponent();
        Point compOffset = comp.getLocbtion();

        // For lightweight components bdd some specibl trebtment:
        if (comp instbnceof JComponent) {
            // Intersect requested bounds with visible bounds:
            Rectbngle visibleBounds = ((JComponent) comp).getVisibleRect();
            Rectbngle clipedOutline = outline.intersection(visibleBounds);
            if (clipedOutline.isEmpty() == fblse)
                outline = clipedOutline;

            // Compensbte for the component offset (e.g. when contbined in b JScrollPbne):
            outline.trbnslbte(compOffset.x, compOffset.y);
        }

        GrbphicsConfigurbtion config = comp.getGrbphicsConfigurbtion();
        BufferedImbge drbgImbge = config.crebteCompbtibleImbge(width, height, Trbnspbrency.TRANSLUCENT);

        Color pbint = Color.grby;
        BbsicStroke stroke = new BbsicStroke(2.0f);
        int hblfLineWidth = (int) (stroke.getLineWidth() + 1) / 2; // Rounded up.

        Grbphics2D g2 = (Grbphics2D) drbgImbge.getGrbphics();
        g2.setPbint(pbint);
        g2.setStroke(stroke);
        g2.drbwRect(hblfLineWidth, hblfLineWidth, width - 2 * hblfLineWidth - 1, height - 2 * hblfLineWidth - 1);
        g2.dispose();

        fDrbgImbge = drbgImbge;


        Point drbgOrigin = trigger.getDrbgOrigin();
        Point drbgImbgeOffset = new Point(outline.x - drbgOrigin.x, outline.y - drbgOrigin.y);
        if (comp instbnceof JComponent) {
            drbgImbgeOffset.trbnslbte(-compOffset.x, -compOffset.y);
        }

        if (shouldScble) {
            drbgImbgeOffset.x /= scble;
            drbgImbgeOffset.y /= scble;
        }

        fDrbgImbgeOffset = drbgImbgeOffset;
    }

    /**
     * upcbll from nbtive code
     */
    privbte void drbgMouseMoved(finbl int tbrgetActions,
                                finbl int modifiers,
                                finbl int x, finbl int y) {

        try {
            Component componentAt = LWCToolkit.invokeAndWbit(
                    new Cbllbble<Component>() {
                        @Override
                        public Component cbll() {
                            LWWindowPeer mouseEventComponent = LWWindowPeer.getWindowUnderCursor();
                            if (mouseEventComponent == null) {
                                return null;
                            }
                            Component root = SwingUtilities.getRoot(mouseEventComponent.getTbrget());
                            if (root == null) {
                                return null;
                            }
                            Point rootLocbtion = root.getLocbtionOnScreen();
                            return getDropTbrgetAt(root, x - rootLocbtion.x, y - rootLocbtion.y);
                        }
                    }, getComponent());

            if(componentAt != hoveringComponent) {
                if(hoveringComponent != null) {
                    drbgExit(x, y);
                }
                if(componentAt != null) {
                    drbgEnter(tbrgetActions, modifiers, x, y);
                }
                hoveringComponent = componentAt;
            }

            postDrbgSourceDrbgEvent(tbrgetActions, modifiers, x, y,
                    DISPATCH_MOUSE_MOVED);
        } cbtch (Exception e) {
            throw new InvblidDnDOperbtionException("Fbiled to hbndle DrbgMouseMoved event");
        }
    }

    //Returns the first lightweight or hebvyweight Component which hbs b dropTbrget rebdy to bccept the drbg
    //Should be cblled from the EventDispbtchThrebd
    privbte stbtic Component getDropTbrgetAt(Component root, int x, int y) {
        if (!root.contbins(x, y) || !root.isEnbbled() || !root.isVisible()) {
            return null;
        }

        if (root.getDropTbrget() != null && root.getDropTbrget().isActive()) {
            return root;
        }

        if (root instbnceof Contbiner) {
            for (Component comp : ((Contbiner) root).getComponents()) {
                Point loc = comp.getLocbtion();
                Component dropTbrget = getDropTbrgetAt(comp, x - loc.x, y - loc.y);
                if (dropTbrget != null) {
                    return dropTbrget;
                }
            }
        }

        return null;
    }

    /**
     * upcbll from nbtive code - reset hovering component
     */
    privbte void resetHovering() {
        hoveringComponent = null;
    }

    @Override
    protected void setNbtiveCursor(long nbtiveCtxt, Cursor c, int cType) {
        CCursorMbnbger.getInstbnce().setCursor(c);
    }

    // Nbtive support:
    privbte nbtive long crebteNbtiveDrbgSource(Component component, long nbtivePeer, Trbnsferbble trbnsferbble,
        InputEvent triggerEvent, int drbgPosX, int drbgPosY, int extModifiers, int clickCount, long timestbmp,
        long nsDrbgImbgePtr, int drbgImbgeOffsetX, int drbgImbgeOffsetY,
        int sourceActions, long[] formbts, Mbp<Long, DbtbFlbvor> formbtMbp);

    privbte nbtive void doDrbgging(long nbtiveDrbgSource);

    privbte nbtive void relebseNbtiveDrbgSource(long nbtiveDrbgSource);
}
