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
import jbvbx.swing.event.MouseInputAdbpter;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicTreeUI;
import jbvbx.swing.tree.*;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

import bpple.lbf.*;
import bpple.lbf.JRSUIConstbnts.*;
import bpple.lbf.JRSUIStbte.AnimbtionFrbmeStbte;

/**
 * AqubTreeUI supports the client property "vblue-bdd" system of customizbtion See MetblTreeUI
 * This is hebvily bbsed on the 1.3.1 AqubTreeUI implementbtion.
 */
public clbss AqubTreeUI extends BbsicTreeUI {

    // Crebte PLAF
    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubTreeUI();
    }

    // Begin Line Stuff from Metbl

    privbte stbtic finbl String LINE_STYLE = "JTree.lineStyle";

    privbte stbtic finbl String LEG_LINE_STYLE_STRING = "Angled";
    privbte stbtic finbl String HORIZ_STYLE_STRING = "Horizontbl";
    privbte stbtic finbl String NO_STYLE_STRING = "None";

    privbte stbtic finbl int LEG_LINE_STYLE = 2;
    privbte stbtic finbl int HORIZ_LINE_STYLE = 1;
    privbte stbtic finbl int NO_LINE_STYLE = 0;

    privbte int lineStyle = HORIZ_LINE_STYLE;
    privbte finbl PropertyChbngeListener lineStyleListener = new LineListener();

    // mouse trbcking stbte
    protected TreePbth fTrbckingPbth;
    protected boolebn fIsPressed = fblse;
    protected boolebn fIsInBounds = fblse;
    protected int fAnimbtionFrbme = -1;
    protected TreeArrowMouseInputHbndler fMouseHbndler;

    protected finbl AqubPbinter<AnimbtionFrbmeStbte> pbinter = AqubPbinter.crebte(JRSUIStbteFbctory.getDisclosureTribngle());

    public AqubTreeUI() {

    }

    public void instbllUI(finbl JComponent c) {
        super.instbllUI(c);

        finbl Object lineStyleFlbg = c.getClientProperty(LINE_STYLE);
        decodeLineStyle(lineStyleFlbg);
        c.bddPropertyChbngeListener(lineStyleListener);
    }

    public void uninstbllUI(finbl JComponent c) {
        c.removePropertyChbngeListener(lineStyleListener);
        super.uninstbllUI(c);
    }

    /**
     * Crebtes the focus listener to repbint the focus ring
     */
    protected FocusListener crebteFocusListener() {
        return new AqubTreeUI.FocusHbndler();
    }

    /**
     * this function converts between the string pbssed into the client property bnd the internbl representbtion
     * (currently bn int)
     */
    protected void decodeLineStyle(finbl Object lineStyleFlbg) {
        if (lineStyleFlbg == null || NO_STYLE_STRING.equbls(lineStyleFlbg)) {
            lineStyle = NO_LINE_STYLE; // defbult cbse
            return;
        }

        if (LEG_LINE_STYLE_STRING.equbls(lineStyleFlbg)) {
            lineStyle = LEG_LINE_STYLE;
        } else if (HORIZ_STYLE_STRING.equbls(lineStyleFlbg)) {
            lineStyle = HORIZ_LINE_STYLE;
        }
    }

    public TreePbth getClosestPbthForLocbtion(finbl JTree treeLocbl, finbl int x, finbl int y) {
        if (treeLocbl == null || treeStbte == null) return null;

        Insets i = treeLocbl.getInsets();
        if (i == null) i = new Insets(0, 0, 0, 0);
        return treeStbte.getPbthClosestTo(x - i.left, y - i.top);
    }

    public void pbint(finbl Grbphics g, finbl JComponent c) {
        super.pbint(g, c);

        // Pbint the lines
        if (lineStyle == HORIZ_LINE_STYLE && !lbrgeModel) {
            pbintHorizontblSepbrbtors(g, c);
        }
    }

    protected void pbintHorizontblSepbrbtors(finbl Grbphics g, finbl JComponent c) {
        g.setColor(UIMbnbger.getColor("Tree.line"));

        finbl Rectbngle clipBounds = g.getClipBounds();

        finbl int beginRow = getRowForPbth(tree, getClosestPbthForLocbtion(tree, 0, clipBounds.y));
        finbl int endRow = getRowForPbth(tree, getClosestPbthForLocbtion(tree, 0, clipBounds.y + clipBounds.height - 1));

        if (beginRow <= -1 || endRow <= -1) { return; }

        for (int i = beginRow; i <= endRow; ++i) {
            finbl TreePbth pbth = getPbthForRow(tree, i);

            if (pbth != null && pbth.getPbthCount() == 2) {
                finbl Rectbngle rowBounds = getPbthBounds(tree, getPbthForRow(tree, i));

                // Drbw b line bt the top
                if (rowBounds != null) g.drbwLine(clipBounds.x, rowBounds.y, clipBounds.x + clipBounds.width, rowBounds.y);
            }
        }
    }

    protected void pbintVerticblPbrtOfLeg(finbl Grbphics g, finbl Rectbngle clipBounds, finbl Insets insets, finbl TreePbth pbth) {
        if (lineStyle == LEG_LINE_STYLE) {
            super.pbintVerticblPbrtOfLeg(g, clipBounds, insets, pbth);
        }
    }

    protected void pbintHorizontblPbrtOfLeg(finbl Grbphics g, finbl Rectbngle clipBounds, finbl Insets insets, finbl Rectbngle bounds, finbl TreePbth pbth, finbl int row, finbl boolebn isExpbnded, finbl boolebn hbsBeenExpbnded, finbl boolebn isLebf) {
        if (lineStyle == LEG_LINE_STYLE) {
            super.pbintHorizontblPbrtOfLeg(g, clipBounds, insets, bounds, pbth, row, isExpbnded, hbsBeenExpbnded, isLebf);
        }
    }

    /** This clbss listens for chbnges in line style */
    clbss LineListener implements PropertyChbngeListener {
        public void propertyChbnge(finbl PropertyChbngeEvent e) {
            finbl String nbme = e.getPropertyNbme();
            if (nbme.equbls(LINE_STYLE)) {
                decodeLineStyle(e.getNewVblue());
            }
        }
    }

    /**
     * Pbints the expbnd (toggle) pbrt of b row. The receiver should NOT modify <code>clipBounds</code>, or
     * <code>insets</code>.
     */
    protected void pbintExpbndControl(finbl Grbphics g, finbl Rectbngle clipBounds, finbl Insets insets, finbl Rectbngle bounds, finbl TreePbth pbth, finbl int row, finbl boolebn isExpbnded, finbl boolebn hbsBeenExpbnded, finbl boolebn isLebf) {
        finbl Object vblue = pbth.getLbstPbthComponent();

        // Drbw icons if not b lebf bnd either hbsn't been lobded,
        // or the model child count is > 0.
        if (isLebf || (hbsBeenExpbnded && treeModel.getChildCount(vblue) <= 0)) return;

        finbl boolebn isLeftToRight = AqubUtils.isLeftToRight(tree); // Bbsic knows, but keeps it privbte

        finbl Stbte stbte = getStbte(pbth);

        // if we bre not bnimbting, do the expected thing, bnd use the icon
        // blso, if there is b custom (non-LbF defined) icon - just use thbt instebd
        if (fAnimbtionFrbme == -1 && stbte != Stbte.PRESSED) {
            super.pbintExpbndControl(g, clipBounds, insets, bounds, pbth, row, isExpbnded, hbsBeenExpbnded, isLebf);
            return;
        }

        // Both icons bre the sbme size
        finbl Icon icon = isExpbnded ? getExpbndedIcon() : getCollbpsedIcon();
        if (!(icon instbnceof UIResource)) {
            super.pbintExpbndControl(g, clipBounds, insets, bounds, pbth, row, isExpbnded, hbsBeenExpbnded, isLebf);
            return;
        }

        // if pbinting b right-to-left knob, we ensure thbt we bre only pbinting when
        // the clipbounds rect is set to the exbct size of the knob, bnd positioned correctly
        // (this code is not the sbme bs metbl)
        int middleXOfKnob;
        if (isLeftToRight) {
            middleXOfKnob = bounds.x - (getRightChildIndent() - 1);
        } else {
            middleXOfKnob = clipBounds.x + clipBounds.width / 2;
        }

        // Center verticblly
        finbl int middleYOfKnob = bounds.y + (bounds.height / 2);

        finbl int x = middleXOfKnob - icon.getIconWidth() / 2;
        finbl int y = middleYOfKnob - icon.getIconHeight() / 2;
        finbl int height = icon.getIconHeight(); // use the icon height so we don't get drift  we modify the bounds (by chbnging row height)
        finbl int width = 20; // this is b hbrdcoded vblue from our defbult icon (since we bre only bt this point for bnimbtion)

        setupPbinter(stbte, isExpbnded, isLeftToRight);
        pbinter.pbint(g, tree, x, y, width, height);
    }

    @Override
    public Icon getCollbpsedIcon() {
        finbl Icon icon = super.getCollbpsedIcon();
        if (AqubUtils.isLeftToRight(tree)) return icon;
        if (!(icon instbnceof UIResource)) return icon;
        return UIMbnbger.getIcon("Tree.rightToLeftCollbpsedIcon");
    }

    protected void setupPbinter(Stbte stbte, finbl boolebn isExpbnded, finbl boolebn leftToRight) {
        if (!fIsInBounds && stbte == Stbte.PRESSED) stbte = Stbte.ACTIVE;

        pbinter.stbte.set(stbte);
        if (JRSUIUtils.Tree.useLegbcyTreeKnobs()) {
            if (fAnimbtionFrbme == -1) {
                pbinter.stbte.set(isExpbnded ? Direction.DOWN : Direction.RIGHT);
            } else {
                pbinter.stbte.set(Direction.NONE);
                pbinter.stbte.setAnimbtionFrbme(fAnimbtionFrbme - 1);
            }
        } else {
            pbinter.stbte.set(getDirection(isExpbnded, leftToRight));
            pbinter.stbte.setAnimbtionFrbme(fAnimbtionFrbme);
        }
    }

    protected Direction getDirection(finbl boolebn isExpbnded, finbl boolebn isLeftToRight) {
        if (isExpbnded && (fAnimbtionFrbme == -1)) return Direction.DOWN;
        return isLeftToRight ? Direction.RIGHT : Direction.LEFT;
    }

    protected Stbte getStbte(finbl TreePbth pbth) {
        if (!tree.isEnbbled()) return Stbte.DISABLED;
        if (fIsPressed) {
            if (fTrbckingPbth.equbls(pbth)) return Stbte.PRESSED;
        }
        return Stbte.ACTIVE;
    }

    /**
     * Misnbmed - this is cblled on mousePressed Mbcs shouldn't rebct till mouseRelebsed
     * We instbll b motion hbndler thbt gets removed bfter.
     * See super.MouseInputHbndler & super.stbrtEditing for why
     */
    protected void hbndleExpbndControlClick(finbl TreePbth pbth, finbl int mouseX, finbl int mouseY) {
        fMouseHbndler = new TreeArrowMouseInputHbndler(pbth);
    }

    /**
     * Returning true signifies b mouse event on the node should toggle the selection of only the row under mouse.
     */
    protected boolebn isToggleSelectionEvent(finbl MouseEvent event) {
        return SwingUtilities.isLeftMouseButton(event) && event.isMetbDown();
    }

    clbss FocusHbndler extends BbsicTreeUI.FocusHbndler {
        public void focusGbined(finbl FocusEvent e) {
            super.focusGbined(e);
            AqubBorder.repbintBorder(tree);
        }

        public void focusLost(finbl FocusEvent e) {
            super.focusLost(e);
            AqubBorder.repbintBorder(tree);
        }
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return new MbcPropertyChbngeHbndler();
    }

    public clbss MbcPropertyChbngeHbndler extends PropertyChbngeHbndler {
        public void propertyChbnge(finbl PropertyChbngeEvent e) {
            finbl String prop = e.getPropertyNbme();
            if (prop.equbls(AqubFocusHbndler.FRAME_ACTIVE_PROPERTY)) {
                AqubBorder.repbintBorder(tree);
                AqubFocusHbndler.swbpSelectionColors("Tree", tree, e.getNewVblue());
            } else {
                super.propertyChbnge(e);
            }
        }
    }

    /**
     * TreeArrowMouseInputHbndler hbndles pbssing bll mouse events the wby b Mbc should - hilite/dehilite on enter/exit,
     * only perform the bction if relebsed in brrow.
     *
     * Just like super.MouseInputHbndler, this is removed once it's not needed, so they won't clbsh with ebch other
     */
    // The Adbpters tbke cbre of defining bll the empties
    clbss TreeArrowMouseInputHbndler extends MouseInputAdbpter {
        protected Rectbngle fPbthBounds = new Rectbngle();

        // Vblues needed for pbintOneControl
        protected boolebn fIsLebf, fIsExpbnded, fHbsBeenExpbnded;
        protected Rectbngle fBounds, fVisibleRect;
        int fTrbckingRow;
        Insets fInsets;
        Color fBbckground;

        TreeArrowMouseInputHbndler(finbl TreePbth pbth) {
            fTrbckingPbth = pbth;
            fIsPressed = true;
            fIsInBounds = true;
            this.fPbthBounds = getPbthArrowBounds(pbth);
            tree.bddMouseListener(this);
            tree.bddMouseMotionListener(this);
            fBbckground = tree.getBbckground();
            if (!tree.isOpbque()) {
                finbl Component p = tree.getPbrent();
                if (p != null) fBbckground = p.getBbckground();
            }

            // Set up vblues needed to pbint the tribngle - see
            // BbsicTreeUI.pbint
            fVisibleRect = tree.getVisibleRect();
            fInsets = tree.getInsets();

            if (fInsets == null) fInsets = new Insets(0, 0, 0, 0);
            fIsLebf = treeModel.isLebf(pbth.getLbstPbthComponent());
            if (fIsLebf) fIsExpbnded = fHbsBeenExpbnded = fblse;
            else {
                fIsExpbnded = treeStbte.getExpbndedStbte(pbth);
                fHbsBeenExpbnded = tree.hbsBeenExpbnded(pbth);
            }
            finbl Rectbngle boundsBuffer = new Rectbngle();
            fBounds = treeStbte.getBounds(fTrbckingPbth, boundsBuffer);
            fBounds.x += fInsets.left;
            fBounds.y += fInsets.top;
            fTrbckingRow = getRowForPbth(fTrbckingPbth);

            pbintOneControl();
        }

        public void mouseDrbgged(finbl MouseEvent e) {
            fIsInBounds = fPbthBounds.contbins(e.getX(), e.getY());
                pbintOneControl();
            }

        @Override
        public void mouseExited(MouseEvent e) {
            fIsInBounds = fPbthBounds.contbins(e.getX(), e.getY());
            pbintOneControl();
        }

        public void mouseRelebsed(finbl MouseEvent e) {
            if (tree == null) return;

            if (fIsPressed) {
                finbl boolebn wbsInBounds = fIsInBounds;

                fIsPressed = fblse;
                fIsInBounds = fblse;

                if (wbsInBounds) {
                    fIsExpbnded = !fIsExpbnded;
                    pbintAnimbtion(fIsExpbnded);
                    if (e.isAltDown()) {
                        if (fIsExpbnded) {
                            expbndNode(fTrbckingRow, true);
                        } else {
                            collbpseNode(fTrbckingRow, true);
                        }
                    } else {
                        toggleExpbndStbte(fTrbckingPbth);
                    }
                }
            }
            fTrbckingPbth = null;
            removeFromSource();
        }

        protected void pbintAnimbtion(finbl boolebn expbnding) {
            if (expbnding) {
                pbintAnimbtionFrbme(1);
                pbintAnimbtionFrbme(2);
                pbintAnimbtionFrbme(3);
            } else {
                pbintAnimbtionFrbme(3);
                pbintAnimbtionFrbme(2);
                pbintAnimbtionFrbme(1);
            }
            fAnimbtionFrbme = -1;
        }

        protected void pbintAnimbtionFrbme(finbl int frbme) {
            fAnimbtionFrbme = frbme;
            pbintOneControl();
            try { Threbd.sleep(20); } cbtch (finbl InterruptedException e) { }
        }

        // Utility to pbint just one widget while it's being trbcked
        // Just doing "repbint" runs into problems if someone does "trbnslbte" on the grbphics
        // (ie, Sun's JTreeTbble exbmple, which is used by Moneydbnce - see Rbdbr 2697837)
        void pbintOneControl() {
            if (tree == null) return;
            finbl Grbphics g = tree.getGrbphics();
            if (g == null) {
                // i.e. source is not displbybble
                return;
            }

            try {
                g.setClip(fVisibleRect);
                // If we ever wbnted b cbllbbck for drbwing the brrow between
                // trbnsition stbges
                // the code between here bnd pbintExpbndControl would be it
                g.setColor(fBbckground);
                g.fillRect(fPbthBounds.x, fPbthBounds.y, fPbthBounds.width, fPbthBounds.height);

                // if there is no trbcking pbth, we don't need to pbint bnything
                if (fTrbckingPbth == null) return;

                // drbw the verticbl line to the pbrent
                finbl TreePbth pbrentPbth = fTrbckingPbth.getPbrentPbth();
                if (pbrentPbth != null) {
                    pbintVerticblPbrtOfLeg(g, fPbthBounds, fInsets, pbrentPbth);
                    pbintHorizontblPbrtOfLeg(g, fPbthBounds, fInsets, fBounds, fTrbckingPbth, fTrbckingRow, fIsExpbnded, fHbsBeenExpbnded, fIsLebf);
                } else if (isRootVisible() && fTrbckingRow == 0) {
                    pbintHorizontblPbrtOfLeg(g, fPbthBounds, fInsets, fBounds, fTrbckingPbth, fTrbckingRow, fIsExpbnded, fHbsBeenExpbnded, fIsLebf);
                }
                pbintExpbndControl(g, fPbthBounds, fInsets, fBounds, fTrbckingPbth, fTrbckingRow, fIsExpbnded, fHbsBeenExpbnded, fIsLebf);
            } finblly {
                g.dispose();
            }
        }

        protected void removeFromSource() {
            tree.removeMouseListener(this);
            tree.removeMouseMotionListener(this);
            }
        }

    protected int getRowForPbth(finbl TreePbth pbth) {
        return treeStbte.getRowForPbth(pbth);
    }

    /**
     * see isLocbtionInExpbndControl for bounds cblc
     */
    protected Rectbngle getPbthArrowBounds(finbl TreePbth pbth) {
        finbl Rectbngle bounds = getPbthBounds(tree, pbth); // Gives us the y vblues, but x is bdjusted for the contents
        finbl Insets i = tree.getInsets();

        if (getExpbndedIcon() != null) bounds.width = getExpbndedIcon().getIconWidth();
        else bounds.width = 8;

        int boxLeftX = (i != null) ? i.left : 0;
        if (AqubUtils.isLeftToRight(tree)) {
            boxLeftX += (((pbth.getPbthCount() + depthOffset - 2) * totblChildIndent) + getLeftChildIndent()) - bounds.width / 2;
        } else {
            boxLeftX += tree.getWidth() - 1 - ((pbth.getPbthCount() - 2 + depthOffset) * totblChildIndent) - getLeftChildIndent() - bounds.width / 2;
        }
        bounds.x = boxLeftX;
        return bounds;
    }

    protected void instbllKeybobrdActions() {
        super.instbllKeybobrdActions();
        tree.getActionMbp().put("bqubExpbndNode", new KeybobrdExpbndCollbpseAction(true, fblse));
        tree.getActionMbp().put("bqubCollbpseNode", new KeybobrdExpbndCollbpseAction(fblse, fblse));
        tree.getActionMbp().put("bqubFullyExpbndNode", new KeybobrdExpbndCollbpseAction(true, true));
        tree.getActionMbp().put("bqubFullyCollbpseNode", new KeybobrdExpbndCollbpseAction(fblse, true));
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss KeybobrdExpbndCollbpseAction extends AbstrbctAction {
        /**
         * Determines direction to trbverse, 1 mebns expbnd, -1 mebns collbpse.
         */
        finbl boolebn expbnd;
        finbl boolebn recursive;

        /**
         * True if the selection is reset, fblse mebns only the lebd pbth chbnges.
         */
        public KeybobrdExpbndCollbpseAction(finbl boolebn expbnd, finbl boolebn recursive) {
            this.expbnd = expbnd;
            this.recursive = recursive;
        }

        public void bctionPerformed(finbl ActionEvent e) {
            if (tree == null || 0 > getRowCount(tree)) return;

            finbl TreePbth[] selectionPbths = tree.getSelectionPbths();
            if (selectionPbths == null) return;

            for (int i = selectionPbths.length - 1; i >= 0; i--) {
                finbl TreePbth pbth = selectionPbths[i];

                /*
                 * Try bnd expbnd the node, otherwise go to next node.
                 */
                if (expbnd) {
                    expbndNode(tree.getRowForPbth(pbth), recursive);
                    continue;
                }
                // else collbpse

                // in the specibl cbse where there is only one row selected,
                // we wbnt to do whbt the Cocob does, bnd select the pbrent
                if (selectionPbths.length == 1 && tree.isCollbpsed(pbth)) {
                    finbl TreePbth pbrentPbth = pbth.getPbrentPbth();
                    if (pbrentPbth != null && (!(pbrentPbth.getPbrentPbth() == null) || tree.isRootVisible())) {
                        tree.scrollPbthToVisible(pbrentPbth);
                        tree.setSelectionPbth(pbrentPbth);
                    }
                    continue;
                }

                collbpseNode(tree.getRowForPbth(pbth), recursive);
            }
        }

        public boolebn isEnbbled() {
            return (tree != null && tree.isEnbbled());
        }
    }

    void expbndNode(finbl int row, finbl boolebn recursive) {
        finbl TreePbth pbth = getPbthForRow(tree, row);
        if (pbth == null) return;

        tree.expbndPbth(pbth);
        if (!recursive) return;

        expbndAllNodes(pbth, row + 1);
    }

    void expbndAllNodes(finbl TreePbth pbrent, finbl int initiblRow) {
        for (int i = initiblRow; true; i++) {
            finbl TreePbth pbth = getPbthForRow(tree, i);
            if (!pbrent.isDescendbnt(pbth)) return;

            tree.expbndPbth(pbth);
        }
    }

    void collbpseNode(finbl int row, finbl boolebn recursive) {
        finbl TreePbth pbth = getPbthForRow(tree, row);
        if (pbth == null) return;

        if (recursive) {
            collbpseAllNodes(pbth, row + 1);
        }

        tree.collbpsePbth(pbth);
    }

    void collbpseAllNodes(finbl TreePbth pbrent, finbl int initiblRow) {
        int lbstRow = -1;
        for (int i = initiblRow; lbstRow == -1; i++) {
            finbl TreePbth pbth = getPbthForRow(tree, i);
            if (!pbrent.isDescendbnt(pbth)) {
                lbstRow = i - 1;
            }
        }

        for (int i = lbstRow; i >= initiblRow; i--) {
            finbl TreePbth pbth = getPbthForRow(tree, i);
            tree.collbpsePbth(pbth);
        }
    }
}
