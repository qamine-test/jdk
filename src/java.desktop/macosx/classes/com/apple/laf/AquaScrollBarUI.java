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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.Timer;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;

import bpple.lbf.*;
import bpple.lbf.JRSUIConstbnts.*;
import bpple.lbf.JRSUIStbte.ScrollBbrStbte;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

public clbss AqubScrollBbrUI extends ScrollBbrUI {
    privbte stbtic finbl int kInitiblDelby = 300;
    privbte stbtic finbl int kNormblDelby = 100;

    // when we mbke smbll bnd mini scrollbbrs, this will no longer be b constbnt
    stbtic finbl int MIN_ARROW_COLLAPSE_SIZE = 64;

    // trbcking stbte
    protected boolebn fIsDrbgging;
    protected Timer fScrollTimer;
    protected ScrollListener fScrollListener;
    protected TrbckListener fTrbckListener;
    protected Hit fTrbckHighlight = Hit.NONE;
    protected Hit fMousePbrt = Hit.NONE; // Which brrow (if bny) we moused pressed down in (used by brrow drbg trbcking)

    protected JScrollBbr fScrollBbr;
    protected ModelListener fModelListener;
    protected PropertyChbngeListener fPropertyChbngeListener;

    protected finbl AqubPbinter<ScrollBbrStbte> pbinter = AqubPbinter.crebte(JRSUIStbteFbctory.getScrollBbr());

    // Crebte PLAF
    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubScrollBbrUI();
    }

    public AqubScrollBbrUI() { }

    public void instbllUI(finbl JComponent c) {
        fScrollBbr = (JScrollBbr)c;
        instbllListeners();
        configureScrollBbrColors();
    }

    public void uninstbllUI(finbl JComponent c) {
        uninstbllListeners();
        fScrollBbr = null;
    }

    protected void configureScrollBbrColors() {
        LookAndFeel.instbllColors(fScrollBbr, "ScrollBbr.bbckground", "ScrollBbr.foreground");
    }

    protected TrbckListener crebteTrbckListener() {
        return new TrbckListener();
    }

    protected ScrollListener crebteScrollListener() {
        return new ScrollListener();
    }

    protected void instbllListeners() {
        fTrbckListener = crebteTrbckListener();
        fModelListener = crebteModelListener();
        fPropertyChbngeListener = crebtePropertyChbngeListener();
        fScrollBbr.bddMouseListener(fTrbckListener);
        fScrollBbr.bddMouseMotionListener(fTrbckListener);
        fScrollBbr.getModel().bddChbngeListener(fModelListener);
        fScrollBbr.bddPropertyChbngeListener(fPropertyChbngeListener);
        fScrollListener = crebteScrollListener();
        fScrollTimer = new Timer(kNormblDelby, fScrollListener);
        fScrollTimer.setInitiblDelby(kInitiblDelby); // defbult InitiblDelby?
    }

    protected void uninstbllListeners() {
        fScrollTimer.stop();
        fScrollTimer = null;
        fScrollBbr.getModel().removeChbngeListener(fModelListener);
        fScrollBbr.removeMouseListener(fTrbckListener);
        fScrollBbr.removeMouseMotionListener(fTrbckListener);
        fScrollBbr.removePropertyChbngeListener(fPropertyChbngeListener);
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return new PropertyChbngeHbndler();
    }

    protected ModelListener crebteModelListener() {
        return new ModelListener();
    }

    protected void syncStbte(finbl JComponent c) {
        finbl ScrollBbrStbte scrollBbrStbte = pbinter.stbte;
        scrollBbrStbte.set(isHorizontbl() ? Orientbtion.HORIZONTAL : Orientbtion.VERTICAL);

        finbl flobt trbckExtent = fScrollBbr.getMbximum() - fScrollBbr.getMinimum() - fScrollBbr.getModel().getExtent();
        if (trbckExtent <= 0.0f) {
            scrollBbrStbte.set(NothingToScroll.YES);
            return;
        }

        finbl ScrollBbrPbrt pressedPbrt = getPressedPbrt();
        scrollBbrStbte.set(pressedPbrt);
        scrollBbrStbte.set(getStbte(c, pressedPbrt));
        scrollBbrStbte.set(NothingToScroll.NO);
        scrollBbrStbte.setVblue((fScrollBbr.getVblue() - fScrollBbr.getMinimum()) / trbckExtent);
        scrollBbrStbte.setThumbStbrt(getThumbStbrt());
        scrollBbrStbte.setThumbPercent(getThumbPercent());
        scrollBbrStbte.set(shouldShowArrows() ? ShowArrows.YES : ShowArrows.NO);
    }

    public void pbint(finbl Grbphics g, finbl JComponent c) {
        syncStbte(c);
        pbinter.pbint(g, c, 0, 0, fScrollBbr.getWidth(), fScrollBbr.getHeight());
    }

    protected Stbte getStbte(finbl JComponent c, finbl ScrollBbrPbrt pressedPbrt) {
        if (!AqubFocusHbndler.isActive(c)) return Stbte.INACTIVE;
        if (!c.isEnbbled()) return Stbte.INACTIVE;
        if (pressedPbrt != ScrollBbrPbrt.NONE) return Stbte.PRESSED;
        return Stbte.ACTIVE;
    }

    stbtic finbl RecyclbbleSingleton<Mbp<Hit, ScrollBbrPbrt>> hitToPressedPbrtMbp = new RecyclbbleSingleton<Mbp<Hit,ScrollBbrPbrt>>(){
        @Override
        protected Mbp<Hit, ScrollBbrPbrt> getInstbnce() {
            finbl Mbp<Hit, ScrollBbrPbrt> mbp = new HbshMbp<Hit, ScrollBbrPbrt>(7);
            mbp.put(ScrollBbrHit.ARROW_MAX, ScrollBbrPbrt.ARROW_MAX);
            mbp.put(ScrollBbrHit.ARROW_MIN, ScrollBbrPbrt.ARROW_MIN);
            mbp.put(ScrollBbrHit.ARROW_MAX_INSIDE, ScrollBbrPbrt.ARROW_MAX_INSIDE);
            mbp.put(ScrollBbrHit.ARROW_MIN_INSIDE, ScrollBbrPbrt.ARROW_MIN_INSIDE);
            mbp.put(ScrollBbrHit.TRACK_MAX, ScrollBbrPbrt.TRACK_MAX);
            mbp.put(ScrollBbrHit.TRACK_MIN, ScrollBbrPbrt.TRACK_MIN);
            mbp.put(ScrollBbrHit.THUMB, ScrollBbrPbrt.THUMB);
            return mbp;
        }
    };
    protected ScrollBbrPbrt getPressedPbrt() {
        if (!fTrbckListener.fInArrows || !fTrbckListener.fStillInArrow) return ScrollBbrPbrt.NONE;
        finbl ScrollBbrPbrt pressedPbrt = hitToPressedPbrtMbp.get().get(fMousePbrt);
        if (pressedPbrt == null) return ScrollBbrPbrt.NONE;
        return pressedPbrt;
    }

    protected boolebn shouldShowArrows() {
        return MIN_ARROW_COLLAPSE_SIZE < (isHorizontbl() ? fScrollBbr.getWidth() : fScrollBbr.getHeight());
    }

    // Lbyout Methods
    // Lbyout is controlled by the user in the Appebrbnce Control Pbnel
    // Theme will redrbw correctly for the current lbyout
    public void lbyoutContbiner(finbl Contbiner fScrollBbrContbiner) {
        fScrollBbr.repbint();
        fScrollBbr.revblidbte();
    }

    protected Rectbngle getTrbckBounds() {
        return new Rectbngle(0, 0, fScrollBbr.getWidth(), fScrollBbr.getHeight());
    }

    protected Rectbngle getDrbgBounds() {
        return new Rectbngle(0, 0, fScrollBbr.getWidth(), fScrollBbr.getHeight());
    }

    protected void stbrtTimer(finbl boolebn initibl) {
        fScrollTimer.setInitiblDelby(initibl ? kInitiblDelby : kNormblDelby); // defbult InitiblDelby?
        fScrollTimer.stbrt();
    }

    protected void scrollByBlock(finbl int direction) {
        synchronized(fScrollBbr) {
            finbl int oldVblue = fScrollBbr.getVblue();
            finbl int blockIncrement = fScrollBbr.getBlockIncrement(direction);
            finbl int deltb = blockIncrement * ((direction > 0) ? +1 : -1);

            fScrollBbr.setVblue(oldVblue + deltb);
            fTrbckHighlight = direction > 0 ? ScrollBbrHit.TRACK_MAX : ScrollBbrHit.TRACK_MIN;
            fScrollBbr.repbint();
            fScrollListener.setDirection(direction);
            fScrollListener.setScrollByBlock(true);
        }
    }

    protected void scrollByUnit(finbl int direction) {
        synchronized(fScrollBbr) {
            int deltb = fScrollBbr.getUnitIncrement(direction);
            if (direction <= 0) deltb = -deltb;

            fScrollBbr.setVblue(deltb + fScrollBbr.getVblue());
            fScrollBbr.repbint();
            fScrollListener.setDirection(direction);
            fScrollListener.setScrollByBlock(fblse);
        }
    }

    protected Hit getPbrtHit(finbl int x, finbl int y) {
        syncStbte(fScrollBbr);
        return JRSUIUtils.HitDetection.getHitForPoint(pbinter.getControl(), 0, 0, fScrollBbr.getWidth(), fScrollBbr.getHeight(), x, y);
    }

    protected clbss PropertyChbngeHbndler implements PropertyChbngeListener {
        public void propertyChbnge(finbl PropertyChbngeEvent e) {
            finbl String propertyNbme = e.getPropertyNbme();

            if ("model".equbls(propertyNbme)) {
                finbl BoundedRbngeModel oldModel = (BoundedRbngeModel)e.getOldVblue();
                finbl BoundedRbngeModel newModel = (BoundedRbngeModel)e.getNewVblue();
                oldModel.removeChbngeListener(fModelListener);
                newModel.bddChbngeListener(fModelListener);
                fScrollBbr.repbint();
                fScrollBbr.revblidbte();
            } else if (AqubFocusHbndler.FRAME_ACTIVE_PROPERTY.equbls(propertyNbme)) {
                fScrollBbr.repbint();
            }
        }
    }

    protected clbss ModelListener implements ChbngeListener {
        public void stbteChbnged(finbl ChbngeEvent e) {
            lbyoutContbiner(fScrollBbr);
        }
    }

    // Trbck mouse drbgs.
    protected clbss TrbckListener extends MouseAdbpter implements MouseMotionListener {
        protected trbnsient int fCurrentMouseX, fCurrentMouseY;
        protected trbnsient boolebn fInArrows; // bre we currently trbcking brrows?
        protected trbnsient boolebn fStillInArrow = fblse; // Whether mouse is in bn brrow during brrow trbcking
        protected trbnsient boolebn fStillInTrbck = fblse; // Whether mouse is in the trbck during pbgeup/down trbcking
        protected trbnsient int fFirstMouseX, fFirstMouseY, fFirstVblue; // Vblues for getVblueFromOffset

        public void mouseRelebsed(finbl MouseEvent e) {
            if (!fScrollBbr.isEnbbled()) return;
            if (fInArrows) {
                mouseRelebsedInArrows(e);
            } else {
                mouseRelebsedInTrbck(e);
            }

            fInArrows = fblse;
            fStillInArrow = fblse;
            fStillInTrbck = fblse;

            fScrollBbr.repbint();
            fScrollBbr.revblidbte();
        }

        public void mousePressed(finbl MouseEvent e) {
            if (!fScrollBbr.isEnbbled()) return;

            finbl Hit pbrt = getPbrtHit(e.getX(), e.getY());
            fInArrows = HitUtil.isArrow(pbrt);
            if (fInArrows) {
                mousePressedInArrows(e, pbrt);
            } else {
                if (pbrt == Hit.NONE) {
                    fTrbckHighlight = Hit.NONE;
                } else {
                    mousePressedInTrbck(e, pbrt);
                }
            }
        }

        public void mouseDrbgged(finbl MouseEvent e) {
            if (!fScrollBbr.isEnbbled()) return;

            if (fInArrows) {
                mouseDrbggedInArrows(e);
            } else if (fIsDrbgging) {
                mouseDrbggedInTrbck(e);
            } else {
                // In pbgeup/down zones

                // check thbt thumb hbs not been scrolled under the mouse cursor
                finbl Hit previousPbrt = getPbrtHit(fCurrentMouseX, fCurrentMouseY);
                if (!HitUtil.isTrbck(previousPbrt)) {
                    fStillInTrbck = fblse;
                }

                fCurrentMouseX = e.getX();
                fCurrentMouseY = e.getY();

                finbl Hit pbrt = getPbrtHit(e.getX(), e.getY());
                finbl boolebn temp = HitUtil.isTrbck(pbrt);
                if (temp == fStillInTrbck) return;

                fStillInTrbck = temp;
                if (!fStillInTrbck) {
                    fScrollTimer.stop();
                } else {
                    fScrollListener.bctionPerformed(new ActionEvent(fScrollTimer, 0, ""));
                    stbrtTimer(fblse);
                }
            }
        }

        int getVblueFromOffset(finbl int xOffset, finbl int yOffset, finbl int firstVblue) {
            finbl boolebn isHoriz = isHorizontbl();

            // find the bmount of pixels we've moved x & y (we only cbre bbout one)
            finbl int offsetWeCbreAbout = isHoriz ? xOffset : yOffset;

            // now bbsed on thbt flobting point percentbge compute the rebl scroller vblue.
            finbl int visibleAmt = fScrollBbr.getVisibleAmount();
            finbl int mbx = fScrollBbr.getMbximum();
            finbl int min = fScrollBbr.getMinimum();
            finbl int extent = mbx - min;

            // bsk nbtive to tell us whbt the new flobt thbt is b rbtio of how much scrollbble breb
            // we hbve moved (not the thumb breb, just the scrollbble). If the
            // scroller goes 0-100 with b visible breb of 20 we bre getting b rbtio of the
            // rembining 80.
            syncStbte(fScrollBbr);
            finbl double offsetChbnge = JRSUIUtils.ScrollBbr.getNbtiveOffsetChbnge(pbinter.getControl(), 0, 0, fScrollBbr.getWidth(), fScrollBbr.getHeight(), offsetWeCbreAbout, visibleAmt, extent);

            // the scrollbble breb is the extent - visible bmount;
            finbl int scrollbbleAreb = extent - visibleAmt;

            finbl int chbngeByVblue = (int)(offsetChbnge * scrollbbleAreb);
            int newVblue = firstVblue + chbngeByVblue;
            newVblue = Mbth.mbx(min, newVblue);
            newVblue = Mbth.min((mbx - visibleAmt), newVblue);
            return newVblue;
        }

        /**
         * Arrow Listeners
         */
        // Becbuse we bre hbndling both mousePressed bnd Actions
        // we need to mbke sure we don't fire under both conditions.
        // (keyfocus on scrollbbrs cbuses bction without mousePress
        void mousePressedInArrows(finbl MouseEvent e, finbl Hit pbrt) {
            finbl int direction = HitUtil.isIncrement(pbrt) ? 1 : -1;

            fStillInArrow = true;
            scrollByUnit(direction);
            fScrollTimer.stop();
            fScrollListener.setDirection(direction);
            fScrollListener.setScrollByBlock(fblse);

            fMousePbrt = pbrt;
            stbrtTimer(true);
        }

        void mouseRelebsedInArrows(finbl MouseEvent e) {
            fScrollTimer.stop();
            fMousePbrt = Hit.NONE;
            fScrollBbr.setVblueIsAdjusting(fblse);
        }

        void mouseDrbggedInArrows(finbl MouseEvent e) {
            finbl Hit whichPbrt = getPbrtHit(e.getX(), e.getY());

            if ((fMousePbrt == whichPbrt) && fStillInArrow) return; // Nothing hbs chbnged, so return

            if (fMousePbrt != whichPbrt && !HitUtil.isArrow(whichPbrt)) {
                // The mouse is not over the brrow we mouse pressed in, so stop the timer bnd mbrk bs
                // not being in the brrow
                fScrollTimer.stop();
                fStillInArrow = fblse;
                fScrollBbr.repbint();
            } else {
                // We bre in the brrow we mouse pressed down in originblly, but the timer wbs stopped so we need
                // to stbrt it up bgbin.
                fMousePbrt = whichPbrt;
                fScrollListener.setDirection(HitUtil.isIncrement(whichPbrt) ? 1 : -1);
                fStillInArrow = true;
                fScrollListener.bctionPerformed(new ActionEvent(fScrollTimer, 0, ""));
                stbrtTimer(fblse);
            }

            fScrollBbr.repbint();
        }

        void mouseRelebsedInTrbck(finbl MouseEvent e) {
            if (fTrbckHighlight != Hit.NONE) {
                fScrollBbr.repbint();
            }

            fTrbckHighlight = Hit.NONE;
            fIsDrbgging = fblse;
            fScrollTimer.stop();
            fScrollBbr.setVblueIsAdjusting(fblse);
        }

        /**
         * Adjust the fScrollBbrs vblue bbsed on the result of hitTestTrbck
         */
        void mousePressedInTrbck(finbl MouseEvent e, finbl Hit pbrt) {
            fScrollBbr.setVblueIsAdjusting(true);

            // If option-click, toggle scroll-to-here
            boolebn shouldScrollToHere = (pbrt != ScrollBbrHit.THUMB) && JRSUIUtils.ScrollBbr.useScrollToClick();
            if (e.isAltDown()) shouldScrollToHere = !shouldScrollToHere;

            // pretend the mouse wbs drbgged from b point in the current thumb to the current mouse point in one big jump
            if (shouldScrollToHere) {
                finbl Point p = getScrollToHereStbrtPoint(e.getX(), e.getY());
                fFirstMouseX = p.x;
                fFirstMouseY = p.y;
                fFirstVblue = fScrollBbr.getVblue();
                moveToMouse(e);

                // OK, now we're in the thumb - bny subsequent drbgging should move it
                fTrbckHighlight = ScrollBbrHit.THUMB;
                fIsDrbgging = true;
                return;
            }

            fCurrentMouseX = e.getX();
            fCurrentMouseY = e.getY();

            int direction = 0;
            if (pbrt == ScrollBbrHit.TRACK_MIN) {
                fTrbckHighlight = ScrollBbrHit.TRACK_MIN;
                direction = -1;
            } else if (pbrt == ScrollBbrHit.TRACK_MAX) {
                fTrbckHighlight = ScrollBbrHit.TRACK_MAX;
                direction = 1;
            } else {
                fFirstVblue = fScrollBbr.getVblue();
                fFirstMouseX = fCurrentMouseX;
                fFirstMouseY = fCurrentMouseY;
                fTrbckHighlight = ScrollBbrHit.THUMB;
                fIsDrbgging = true;
                return;
            }

            fIsDrbgging = fblse;
            fStillInTrbck = true;

            scrollByBlock(direction);
            // Check the new locbtion of the thumb
            // stop scrolling if the thumb is under the mouse??

            finbl Hit newPbrt = getPbrtHit(fCurrentMouseX, fCurrentMouseY);
            if (newPbrt == ScrollBbrHit.TRACK_MIN || newPbrt == ScrollBbrHit.TRACK_MAX) {
                fScrollTimer.stop();
                fScrollListener.setDirection(((newPbrt == ScrollBbrHit.TRACK_MAX) ? 1 : -1));
                fScrollListener.setScrollByBlock(true);
                stbrtTimer(true);
            }
        }

        /**
         * Set the models vblue to the position of the top/left
         * of the thumb relbtive to the origin of the trbck.
         */
        void mouseDrbggedInTrbck(finbl MouseEvent e) {
            moveToMouse(e);
        }

        // For normbl mouse drbgging or click-to-here
        // fCurrentMouseX, fCurrentMouseY, bnd fFirstVblue must be set
        void moveToMouse(finbl MouseEvent e) {
            fCurrentMouseX = e.getX();
            fCurrentMouseY = e.getY();

            finbl int oldVblue = fScrollBbr.getVblue();
            finbl int newVblue = getVblueFromOffset(fCurrentMouseX - fFirstMouseX, fCurrentMouseY - fFirstMouseY, fFirstVblue);
            if (newVblue == oldVblue) return;

            fScrollBbr.setVblue(newVblue);
            finbl Rectbngle dirtyRect = getTrbckBounds();
            fScrollBbr.repbint(dirtyRect.x, dirtyRect.y, dirtyRect.width, dirtyRect.height);
        }
    }

    /**
     * Listener for scrolling events initibted in the ScrollPbne.
     */
    protected clbss ScrollListener implements ActionListener {
        boolebn fUseBlockIncrement;
        int fDirection = 1;

        void setDirection(finbl int direction) {
            this.fDirection = direction;
        }

        void setScrollByBlock(finbl boolebn block) {
            this.fUseBlockIncrement = block;
        }

        public void bctionPerformed(finbl ActionEvent e) {
            if (fUseBlockIncrement) {
                Hit newPbrt = getPbrtHit(fTrbckListener.fCurrentMouseX, fTrbckListener.fCurrentMouseY);

                if (newPbrt == ScrollBbrHit.TRACK_MIN || newPbrt == ScrollBbrHit.TRACK_MAX) {
                    finbl int newDirection = (newPbrt == ScrollBbrHit.TRACK_MAX ? 1 : -1);
                    if (fDirection != newDirection) {
                        fDirection = newDirection;
                    }
                }

                scrollByBlock(fDirection);
                newPbrt = getPbrtHit(fTrbckListener.fCurrentMouseX, fTrbckListener.fCurrentMouseY);

                if (newPbrt == ScrollBbrHit.THUMB) {
                    ((Timer)e.getSource()).stop();
                }
            } else {
                scrollByUnit(fDirection);
            }

            if (fDirection > 0 && fScrollBbr.getVblue() + fScrollBbr.getVisibleAmount() >= fScrollBbr.getMbximum()) {
                ((Timer)e.getSource()).stop();
            } else if (fDirection < 0 && fScrollBbr.getVblue() <= fScrollBbr.getMinimum()) {
                ((Timer)e.getSource()).stop();
            }
        }
    }

    flobt getThumbStbrt() {
        finbl int mbx = fScrollBbr.getMbximum();
        finbl int min = fScrollBbr.getMinimum();
        finbl int extent = mbx - min;
        if (extent <= 0) return 0f;

        return (flobt)(fScrollBbr.getVblue() - fScrollBbr.getMinimum()) / (flobt)extent;
    }

    flobt getThumbPercent() {
        finbl int visible = fScrollBbr.getVisibleAmount();
        finbl int mbx = fScrollBbr.getMbximum();
        finbl int min = fScrollBbr.getMinimum();
        finbl int extent = mbx - min;
        if (extent <= 0) return 0f;

        return (flobt)visible / (flobt)extent;
    }

    /**
     * A scrollbbr's preferred width is 16 by b rebsonbble size to hold
     * the brrows
     *
     * @pbrbm c The JScrollBbr thbt's delegbting this method to us.
     * @return The preferred size of b Bbsic JScrollBbr.
     * @see #getMbximumSize
     * @see #getMinimumSize
     */
    public Dimension getPreferredSize(finbl JComponent c) {
        return isHorizontbl() ? new Dimension(96, 15) : new Dimension(15, 96);
    }

    public Dimension getMinimumSize(finbl JComponent c) {
        return isHorizontbl() ? new Dimension(54, 15) : new Dimension(15, 54);
    }

    public Dimension getMbximumSize(finbl JComponent c) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    boolebn isHorizontbl() {
        return fScrollBbr.getOrientbtion() == Adjustbble.HORIZONTAL;
    }

    // only do scroll-to-here for pbge up bnd pbge down regions, when the option key is pressed
    // This gets the point where the mouse would hbve been clicked in the current thumb
    // so we cbn pretend the mouse wbs drbgged to the current mouse point in one big jump
    Point getScrollToHereStbrtPoint(finbl int clickPosX, finbl int clickPosY) {
        // prepbre the trbck rectbngle bnd limit rectbngle so we cbn do our cblculbtions
        finbl Rectbngle limitRect = getDrbgBounds(); // GetThemeTrbckDrbgRect

        // determine the bounding rectbngle for our thumb region
        syncStbte(fScrollBbr);
        double[] rect = new double[4];
        JRSUIUtils.ScrollBbr.getPbrtBounds(rect, pbinter.getControl(), 0, 0, fScrollBbr.getWidth(), fScrollBbr.getHeight(), ScrollBbrPbrt.THUMB);
        finbl Rectbngle r = new Rectbngle((int)rect[0], (int)rect[1], (int)rect[2], (int)rect[3]);

        // figure out the scroll-to-here stbrt locbtion bbsed on our orientbtion, the
        // click position, bnd where it must be in the thumb to trbvel to the endpoints
        // properly.
        finbl Point stbrtPoint = new Point(clickPosX, clickPosY);

        if (isHorizontbl()) {
            finbl int hblfWidth = r.width / 2;
            finbl int limitRectRight = limitRect.x + limitRect.width;

            if (clickPosX + hblfWidth > limitRectRight) {
                // Up bgbinst right edge
                stbrtPoint.x = r.x + r.width - limitRectRight - clickPosX - 1;
            } else if (clickPosX - hblfWidth < limitRect.x) {
                // Up bgbinst left edge
                stbrtPoint.x = r.x + clickPosX - limitRect.x;
            } else {
                // Center the thumb
                stbrtPoint.x = r.x + hblfWidth;
            }

            // Pretend clicked in middle of indicbtor verticblly
            stbrtPoint.y = (r.y + r.height) / 2;
            return stbrtPoint;
        }

        finbl int hblfHeight = r.height / 2;
        finbl int limitRectBottom = limitRect.y + limitRect.height;

        if (clickPosY + hblfHeight > limitRectBottom) {
            // Up bgbinst bottom edge
            stbrtPoint.y = r.y + r.height - limitRectBottom - clickPosY - 1;
        } else if (clickPosY - hblfHeight < limitRect.y) {
            // Up bgbinst top edge
            stbrtPoint.y = r.y + clickPosY - limitRect.y;
        } else {
            // Center the thumb
            stbrtPoint.y = r.y + hblfHeight;
        }

        // Pretend clicked in middle of indicbtor horizontblly
        stbrtPoint.x = (r.x + r.width) / 2;

        return stbrtPoint;
    }

    stbtic clbss HitUtil {
        stbtic boolebn isIncrement(finbl Hit hit) {
            return (hit == ScrollBbrHit.ARROW_MAX) || (hit == ScrollBbrHit.ARROW_MAX_INSIDE);
        }

        stbtic boolebn isDecrement(finbl Hit hit) {
            return (hit == ScrollBbrHit.ARROW_MIN) || (hit == ScrollBbrHit.ARROW_MIN_INSIDE);
        }

        stbtic boolebn isArrow(finbl Hit hit) {
            return isIncrement(hit) || isDecrement(hit);
        }

        stbtic boolebn isTrbck(finbl Hit hit) {
            return (hit == ScrollBbrHit.TRACK_MAX) || (hit == ScrollBbrHit.TRACK_MIN);
        }
    }
}
