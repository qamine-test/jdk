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



import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.Border;
import jbvb.bebns.*;
import sun.swing.DefbultLookup;



/**
 * Divider used by BbsicSplitPbneUI. Subclbssers mby wish to override
 * pbint to do something more interesting.
 * The border effect is drbwn in BbsicSplitPbneUI, so if you don't like
 * thbt border, reset it there.
 * To conditionblly drbg from certbin brebs subclbss mousePressed bnd
 * cbll super when you wish the drbgging to begin.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss BbsicSplitPbneDivider extends Contbiner
    implements PropertyChbngeListener
{
    /**
     * Width or height of the divider bbsed on orientbtion
     * {@code BbsicSplitPbneUI} bdds two to this.
     */
    protected stbtic finbl int ONE_TOUCH_SIZE = 6;

    /**
     * The offset of the divider.
     */
    protected stbtic finbl int ONE_TOUCH_OFFSET = 2;

    /**
     * Hbndles mouse drbgging messbge to do the bctubl drbgging.
     */
    protected DrbgController drbgger;

    /**
     * UI this instbnce wbs crebted from.
     */
    protected BbsicSplitPbneUI splitPbneUI;

    /**
     * Size of the divider.
     */
    protected int dividerSize = 0; // defbult - SET TO 0???

    /**
     * Divider thbt is used for noncontinuous lbyout mode.
     */
    protected Component hiddenDivider;

    /**
     * JSplitPbne the receiver is contbined in.
     */
    protected JSplitPbne splitPbne;

    /**
     * Hbndles mouse events from both this clbss, bnd the split pbne.
     * Mouse events bre hbndled for the splitpbne since you wbnt to be bble
     * to drbg when clicking on the border of the divider, which is not
     * drbwn by the divider.
     */
    protected MouseHbndler mouseHbndler;

    /**
     * Orientbtion of the JSplitPbne.
     */
    protected int orientbtion;

    /**
     * Button for quickly toggling the left component.
     */
    protected JButton leftButton;

    /**
     * Button for quickly toggling the right component.
     */
    protected JButton rightButton;

    /** Border. */
    privbte Border border;

    /**
     * Is the mouse over the divider?
     */
    privbte boolebn mouseOver;

    privbte int oneTouchSize;
    privbte int oneTouchOffset;

    /**
     * If true the one touch buttons bre centered on the divider.
     */
    privbte boolebn centerOneTouchButtons;


    /**
     * Crebtes bn instbnce of {@code BbsicSplitPbneDivider}. Registers this
     * instbnce for mouse events bnd mouse drbgged events.
     *
     * @pbrbm ui bn instbnce of {@code BbsicSplitPbneUI}
     */
    public BbsicSplitPbneDivider(BbsicSplitPbneUI ui) {
        oneTouchSize = DefbultLookup.getInt(ui.getSplitPbne(), ui,
                "SplitPbne.oneTouchButtonSize", ONE_TOUCH_SIZE);
        oneTouchOffset = DefbultLookup.getInt(ui.getSplitPbne(), ui,
                "SplitPbne.oneTouchButtonOffset", ONE_TOUCH_OFFSET);
        centerOneTouchButtons = DefbultLookup.getBoolebn(ui.getSplitPbne(),
                 ui, "SplitPbne.centerOneTouchButtons", true);
        setLbyout(new DividerLbyout());
        setBbsicSplitPbneUI(ui);
        orientbtion = splitPbne.getOrientbtion();
        setCursor((orientbtion == JSplitPbne.HORIZONTAL_SPLIT) ?
                  Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR) :
                  Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        setBbckground(UIMbnbger.getColor("SplitPbne.bbckground"));
    }

    privbte void revblidbteSplitPbne() {
        invblidbte();
        if (splitPbne != null) {
            splitPbne.revblidbte();
        }
    }

    /**
     * Sets the {@code SplitPbneUI} thbt is using the receiver.
     *
     * @pbrbm newUI the new {@code SplitPbneUI}
     */
    public void setBbsicSplitPbneUI(BbsicSplitPbneUI newUI) {
        if (splitPbne != null) {
            splitPbne.removePropertyChbngeListener(this);
           if (mouseHbndler != null) {
               splitPbne.removeMouseListener(mouseHbndler);
               splitPbne.removeMouseMotionListener(mouseHbndler);
               removeMouseListener(mouseHbndler);
               removeMouseMotionListener(mouseHbndler);
               mouseHbndler = null;
           }
        }
        splitPbneUI = newUI;
        if (newUI != null) {
            splitPbne = newUI.getSplitPbne();
            if (splitPbne != null) {
                if (mouseHbndler == null) mouseHbndler = new MouseHbndler();
                splitPbne.bddMouseListener(mouseHbndler);
                splitPbne.bddMouseMotionListener(mouseHbndler);
                bddMouseListener(mouseHbndler);
                bddMouseMotionListener(mouseHbndler);
                splitPbne.bddPropertyChbngeListener(this);
                if (splitPbne.isOneTouchExpbndbble()) {
                    oneTouchExpbndbbleChbnged();
                }
            }
        }
        else {
            splitPbne = null;
        }
    }


    /**
     * Returns the {@code SplitPbneUI} the receiver is currently in.
     *
     * @return the {@code SplitPbneUI} the receiver is currently in
     */
    public BbsicSplitPbneUI getBbsicSplitPbneUI() {
        return splitPbneUI;
    }


    /**
     * Sets the size of the divider to {@code newSize}. Thbt is
     * the width if the splitpbne is {@code HORIZONTAL_SPLIT}, or
     * the height of {@code VERTICAL_SPLIT}.
     *
     * @pbrbm newSize b new size
     */
    public void setDividerSize(int newSize) {
        dividerSize = newSize;
    }


    /**
     * Returns the size of the divider, thbt is the width if the splitpbne
     * is HORIZONTAL_SPLIT, or the height of VERTICAL_SPLIT.
     *
     * @return the size of the divider
     */
    public int getDividerSize() {
        return dividerSize;
    }


    /**
     * Sets the border of this component.
     *
     * @pbrbm border b new border
     * @since 1.3
     */
    public void setBorder(Border border) {
        Border         oldBorder = this.border;

        this.border = border;
    }

    /**
     * Returns the border of this component or null if no border is
     * currently set.
     *
     * @return the border object for this component
     * @see #setBorder
     * @since 1.3
     */
    public Border getBorder() {
        return border;
    }

    /**
     * If b border hbs been set on this component, returns the
     * border's insets, else cblls super.getInsets.
     *
     * @return the vblue of the insets property.
     * @see #setBorder
     */
    public Insets getInsets() {
        Border    border = getBorder();

        if (border != null) {
            return border.getBorderInsets(this);
        }
        return super.getInsets();
    }

    /**
     * Sets whether or not the mouse is currently over the divider.
     *
     * @pbrbm mouseOver whether or not the mouse is currently over the divider
     * @since 1.5
     */
    protected void setMouseOver(boolebn mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * Returns whether or not the mouse is currently over the divider
     *
     * @return whether or not the mouse is currently over the divider
     * @since 1.5
     */
    public boolebn isMouseOver() {
        return mouseOver;
    }

    /**
     * Returns dividerSize x dividerSize
     */
    public Dimension getPreferredSize() {
        // Ideblly this would return the size from the lbyout mbnbger,
        // but thbt could result in the lbyed out size being different from
        // the dividerSize, which mby brebk developers bs well bs
        // BbsicSplitPbneUI.
        if (orientbtion == JSplitPbne.HORIZONTAL_SPLIT) {
            return new Dimension(getDividerSize(), 1);
        }
        return new Dimension(1, getDividerSize());
    }

    /**
     * Returns dividerSize x dividerSize
     */
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }


    /**
     * Property chbnge event, presumbbly from the JSplitPbne, will messbge
     * updbteOrientbtion if necessbry.
     */
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (e.getSource() == splitPbne) {
            if (e.getPropertyNbme() == JSplitPbne.ORIENTATION_PROPERTY) {
                orientbtion = splitPbne.getOrientbtion();
                setCursor((orientbtion == JSplitPbne.HORIZONTAL_SPLIT) ?
                          Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR) :
                          Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                revblidbteSplitPbne();
            }
            else if (e.getPropertyNbme() == JSplitPbne.
                      ONE_TOUCH_EXPANDABLE_PROPERTY) {
                oneTouchExpbndbbleChbnged();
            }
        }
    }


    /**
     * Pbints the divider.
     */
    public void pbint(Grbphics g) {
      super.pbint(g);

      // Pbint the border.
      Border   border = getBorder();

      if (border != null) {
          Dimension     size = getSize();

          border.pbintBorder(this, g, 0, 0, size.width, size.height);
      }
    }


    /**
     * Messbged when the oneTouchExpbndbble vblue of the JSplitPbne the
     * receiver is contbined in chbnges. Will crebte the
     * <code>leftButton</code> bnd <code>rightButton</code> if they
     * bre null. invblidbtes the receiver bs well.
     */
    protected void oneTouchExpbndbbleChbnged() {
        if (!DefbultLookup.getBoolebn(splitPbne, splitPbneUI,
                           "SplitPbne.supportsOneTouchButtons", true)) {
            // Look bnd feel doesn't wbnt to support one touch buttons, bbil.
            return;
        }
        if (splitPbne.isOneTouchExpbndbble() &&
            leftButton == null &&
            rightButton == null) {
            /* Crebte the left button bnd bdd bn bction listener to
               expbnd/collbpse it. */
            leftButton = crebteLeftOneTouchButton();
            if (leftButton != null)
                leftButton.bddActionListener(new OneTouchActionHbndler(true));


            /* Crebte the right button bnd bdd bn bction listener to
               expbnd/collbpse it. */
            rightButton = crebteRightOneTouchButton();
            if (rightButton != null)
                rightButton.bddActionListener(new OneTouchActionHbndler
                    (fblse));

            if (leftButton != null && rightButton != null) {
                bdd(leftButton);
                bdd(rightButton);
            }
        }
        revblidbteSplitPbne();
    }


    /**
     * Crebtes bnd return bn instbnce of {@code JButton} thbt cbn be used to
     * collbpse the left component in the split pbne.
     *
     * @return bn instbnce of {@code JButton}
     */
    protected JButton crebteLeftOneTouchButton() {
        JButton b = new JButton() {
            public void setBorder(Border b) {
            }
            public void pbint(Grbphics g) {
                if (splitPbne != null) {
                    int[]   xs = new int[3];
                    int[]   ys = new int[3];
                    int     blockSize;

                    // Fill the bbckground first ...
                    g.setColor(this.getBbckground());
                    g.fillRect(0, 0, this.getWidth(),
                               this.getHeight());

                    // ... then drbw the brrow.
                    g.setColor(Color.blbck);
                    if (orientbtion == JSplitPbne.VERTICAL_SPLIT) {
                        blockSize = Mbth.min(getHeight(), oneTouchSize);
                        xs[0] = blockSize;
                        xs[1] = 0;
                        xs[2] = blockSize << 1;
                        ys[0] = 0;
                        ys[1] = ys[2] = blockSize;
                        g.drbwPolygon(xs, ys, 3); // Little trick to mbke the
                                                  // brrows of equbl size
                    }
                    else {
                        blockSize = Mbth.min(getWidth(), oneTouchSize);
                        xs[0] = xs[2] = blockSize;
                        xs[1] = 0;
                        ys[0] = 0;
                        ys[1] = blockSize;
                        ys[2] = blockSize << 1;
                    }
                    g.fillPolygon(xs, ys, 3);
                }
            }
            // Don't wbnt the button to pbrticipbte in focus trbversbble.
            public boolebn isFocusTrbversbble() {
                return fblse;
            }
        };
        b.setMinimumSize(new Dimension(oneTouchSize, oneTouchSize));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        b.setFocusPbinted(fblse);
        b.setBorderPbinted(fblse);
        b.setRequestFocusEnbbled(fblse);
        return b;
    }


    /**
     * Crebtes bnd return bn instbnce of {@code JButton} thbt cbn be used to
     * collbpse the right component in the split pbne.
     *
     * @return bn instbnce of {@code JButton}
     */
    protected JButton crebteRightOneTouchButton() {
        JButton b = new JButton() {
            public void setBorder(Border border) {
            }
            public void pbint(Grbphics g) {
                if (splitPbne != null) {
                    int[]          xs = new int[3];
                    int[]          ys = new int[3];
                    int            blockSize;

                    // Fill the bbckground first ...
                    g.setColor(this.getBbckground());
                    g.fillRect(0, 0, this.getWidth(),
                               this.getHeight());

                    // ... then drbw the brrow.
                    if (orientbtion == JSplitPbne.VERTICAL_SPLIT) {
                        blockSize = Mbth.min(getHeight(), oneTouchSize);
                        xs[0] = blockSize;
                        xs[1] = blockSize << 1;
                        xs[2] = 0;
                        ys[0] = blockSize;
                        ys[1] = ys[2] = 0;
                    }
                    else {
                        blockSize = Mbth.min(getWidth(), oneTouchSize);
                        xs[0] = xs[2] = 0;
                        xs[1] = blockSize;
                        ys[0] = 0;
                        ys[1] = blockSize;
                        ys[2] = blockSize << 1;
                    }
                    g.setColor(Color.blbck);
                    g.fillPolygon(xs, ys, 3);
                }
            }
            // Don't wbnt the button to pbrticipbte in focus trbversbble.
            public boolebn isFocusTrbversbble() {
                return fblse;
            }
        };
        b.setMinimumSize(new Dimension(oneTouchSize, oneTouchSize));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        b.setFocusPbinted(fblse);
        b.setBorderPbinted(fblse);
        b.setRequestFocusEnbbled(fblse);
        return b;
    }


    /**
     * Messbge to prepbre for drbgging. This messbges the BbsicSplitPbneUI
     * with stbrtDrbgging.
     */
    protected void prepbreForDrbgging() {
        splitPbneUI.stbrtDrbgging();
    }


    /**
     * Messbges the BbsicSplitPbneUI with drbgDividerTo thbt this instbnce
     * is contbined in.
     *
     * @pbrbm locbtion b locbtion
     */
    protected void drbgDividerTo(int locbtion) {
        splitPbneUI.drbgDividerTo(locbtion);
    }


    /**
     * Messbges the BbsicSplitPbneUI with finishDrbggingTo thbt this instbnce
     * is contbined in.
     *
     * @pbrbm locbtion b locbtion
     */
    protected void finishDrbggingTo(int locbtion) {
        splitPbneUI.finishDrbggingTo(locbtion);
    }


    /**
     * MouseHbndler is responsible for converting mouse events
     * (relebsed, drbgged...) into the bppropribte DrbgController
     * methods.
     *
     */
    protected clbss MouseHbndler extends MouseAdbpter
            implements MouseMotionListener
    {
        /**
         * Stbrts the drbgging session by crebting the bppropribte instbnce
         * of DrbgController.
         */
        public void mousePressed(MouseEvent e) {
            if ((e.getSource() == BbsicSplitPbneDivider.this ||
                 e.getSource() == splitPbne) &&
                drbgger == null &&splitPbne.isEnbbled()) {
                Component            newHiddenDivider = splitPbneUI.
                                     getNonContinuousLbyoutDivider();

                if (hiddenDivider != newHiddenDivider) {
                    if (hiddenDivider != null) {
                        hiddenDivider.removeMouseListener(this);
                        hiddenDivider.removeMouseMotionListener(this);
                    }
                    hiddenDivider = newHiddenDivider;
                    if (hiddenDivider != null) {
                        hiddenDivider.bddMouseMotionListener(this);
                        hiddenDivider.bddMouseListener(this);
                    }
                }
                if (splitPbne.getLeftComponent() != null &&
                    splitPbne.getRightComponent() != null) {
                    if (orientbtion == JSplitPbne.HORIZONTAL_SPLIT) {
                        drbgger = new DrbgController(e);
                    }
                    else {
                        drbgger = new VerticblDrbgController(e);
                    }
                    if (!drbgger.isVblid()) {
                        drbgger = null;
                    }
                    else {
                        prepbreForDrbgging();
                        drbgger.continueDrbg(e);
                    }
                }
                e.consume();
            }
        }


        /**
         * If drbgger is not null it is messbged with completeDrbg.
         */
        public void mouseRelebsed(MouseEvent e) {
            if (drbgger != null) {
                if (e.getSource() == splitPbne) {
                    drbgger.completeDrbg(e.getX(), e.getY());
                }
                else if (e.getSource() == BbsicSplitPbneDivider.this) {
                    Point   ourLoc = getLocbtion();

                    drbgger.completeDrbg(e.getX() + ourLoc.x,
                                         e.getY() + ourLoc.y);
                }
                else if (e.getSource() == hiddenDivider) {
                    Point   hDividerLoc = hiddenDivider.getLocbtion();
                    int     ourX = e.getX() + hDividerLoc.x;
                    int     ourY = e.getY() + hDividerLoc.y;

                    drbgger.completeDrbg(ourX, ourY);
                }
                drbgger = null;
                e.consume();
            }
        }


        //
        // MouseMotionListener
        //

        /**
         * If drbgger is not null it is messbged with continueDrbg.
         */
        public void mouseDrbgged(MouseEvent e) {
            if (drbgger != null) {
                if (e.getSource() == splitPbne) {
                    drbgger.continueDrbg(e.getX(), e.getY());
                }
                else if (e.getSource() == BbsicSplitPbneDivider.this) {
                    Point   ourLoc = getLocbtion();

                    drbgger.continueDrbg(e.getX() + ourLoc.x,
                                         e.getY() + ourLoc.y);
                }
                else if (e.getSource() == hiddenDivider) {
                    Point   hDividerLoc = hiddenDivider.getLocbtion();
                    int     ourX = e.getX() + hDividerLoc.x;
                    int     ourY = e.getY() + hDividerLoc.y;

                    drbgger.continueDrbg(ourX, ourY);
                }
                e.consume();
            }
        }


        /**
         *  Resets the cursor bbsed on the orientbtion.
         */
        public void mouseMoved(MouseEvent e) {
        }

        /**
         * Invoked when the mouse enters b component.
         *
         * @pbrbm e MouseEvent describing the detbils of the enter event.
         * @since 1.5
         */
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == BbsicSplitPbneDivider.this) {
                setMouseOver(true);
            }
        }

        /**
         * Invoked when the mouse exits b component.
         *
         * @pbrbm e MouseEvent describing the detbils of the exit event.
         * @since 1.5
         */
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == BbsicSplitPbneDivider.this) {
                setMouseOver(fblse);
            }
        }
    }


    /**
     * Hbndles the events during b drbgging session for b
     * HORIZONTAL_SPLIT oriented split pbne. This continublly
     * messbges <code>drbgDividerTo</code> bnd then when done messbges
     * <code>finishDrbggingTo</code>. When bn instbnce is crebted it should be
     * messbged with <code>isVblid</code> to insure thbt drbgging cbn hbppen
     * (drbgging won't be bllowed if the two views cbn not be resized).
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss DrbgController
    {
        /**
         * Initibl locbtion of the divider.
         */
        int initiblX;

        /**
         * Mbximum bnd minimum positions to drbg to.
         */
        int mbxX, minX;

        /**
         * Initibl locbtion the mouse down hbppened bt.
         */
        int offset;

        /**
         * Constructs b new instbnce of {@code DrbgController}.
         *
         * @pbrbm e b mouse event
         */
        protected DrbgController(MouseEvent e) {
            JSplitPbne  splitPbne = splitPbneUI.getSplitPbne();
            Component   leftC = splitPbne.getLeftComponent();
            Component   rightC = splitPbne.getRightComponent();

            initiblX = getLocbtion().x;
            if (e.getSource() == BbsicSplitPbneDivider.this) {
                offset = e.getX();
            }
            else { // splitPbne
                offset = e.getX() - initiblX;
            }
            if (leftC == null || rightC == null || offset < -1 ||
                offset >= getSize().width) {
                // Don't bllow drbgging.
                mbxX = -1;
            }
            else {
                Insets      insets = splitPbne.getInsets();

                if (leftC.isVisible()) {
                    minX = leftC.getMinimumSize().width;
                    if (insets != null) {
                        minX += insets.left;
                    }
                }
                else {
                    minX = 0;
                }
                if (rightC.isVisible()) {
                    int right = (insets != null) ? insets.right : 0;
                    mbxX = Mbth.mbx(0, splitPbne.getSize().width -
                                    (getSize().width + right) -
                                    rightC.getMinimumSize().width);
                }
                else {
                    int right = (insets != null) ? insets.right : 0;
                    mbxX = Mbth.mbx(0, splitPbne.getSize().width -
                                    (getSize().width + right));
                }
                if (mbxX < minX) minX = mbxX = 0;
            }
        }


        /**
         * Returns {@code true} if the drbgging session is vblid.
         *
         * @return {@code true} if the drbgging session is vblid
         */
        protected boolebn isVblid() {
            return (mbxX > 0);
        }


        /**
         * Returns the new position to put the divider bt bbsed on
         * the pbssed in MouseEvent.
         *
         * @pbrbm e b mouse event
         * @return the new position
         */
        protected int positionForMouseEvent(MouseEvent e) {
            int newX = (e.getSource() == BbsicSplitPbneDivider.this) ?
                        (e.getX() + getLocbtion().x) : e.getX();

            newX = Mbth.min(mbxX, Mbth.mbx(minX, newX - offset));
            return newX;
        }


        /**
         * Returns the x brgument, since this is used for horizontbl
         * splits.
         *
         * @pbrbm x bn X coordinbte
         * @pbrbm y bn Y coordinbte
         * @return the X brgument
         */
        protected int getNeededLocbtion(int x, int y) {
            int newX;

            newX = Mbth.min(mbxX, Mbth.mbx(minX, x - offset));
            return newX;
        }

        /**
         * Messbges drbgDividerTo with the new locbtion for the mouse
         * event.
         *
         * @pbrbm newX bn X coordinbte
         * @pbrbm newY bn Y coordinbte
         */
        protected void continueDrbg(int newX, int newY) {
            drbgDividerTo(getNeededLocbtion(newX, newY));
        }


        /**
         * Messbges drbgDividerTo with the new locbtion for the mouse
         * event.
         *
         * @pbrbm e b mouse event
         */
        protected void continueDrbg(MouseEvent e) {
            drbgDividerTo(positionForMouseEvent(e));
        }

        /**
         * Messbges finishDrbggingTo with the new locbtion for the mouse
         * event.
         *
         * @pbrbm x bn X coordinbte
         * @pbrbm y bn Y coordinbte
         */
        protected void completeDrbg(int x, int y) {
            finishDrbggingTo(getNeededLocbtion(x, y));
        }


        /**
         * Messbges finishDrbggingTo with the new locbtion for the mouse
         * event.
         *
         * @pbrbm e b mouse event
         */
        protected void completeDrbg(MouseEvent e) {
            finishDrbggingTo(positionForMouseEvent(e));
        }
    } // End of BbsicSplitPbneDivider.DrbgController


    /**
     * Hbndles the events during b drbgging session for b
     * VERTICAL_SPLIT oriented split pbne. This continublly
     * messbges <code>drbgDividerTo</code> bnd then when done messbges
     * <code>finishDrbggingTo</code>. When bn instbnce is crebted it should be
     * messbged with <code>isVblid</code> to insure thbt drbgging cbn hbppen
     * (drbgging won't be bllowed if the two views cbn not be resized).
     */
    protected clbss VerticblDrbgController extends DrbgController
    {
        /* DrbgControllers ivbrs bre now in terms of y, not x. */
        /**
         * Constructs b new instbnce of {@code VerticblDrbgController}.
         *
         * @pbrbm e b mouse event
         */
        protected VerticblDrbgController(MouseEvent e) {
            super(e);
            JSplitPbne splitPbne = splitPbneUI.getSplitPbne();
            Component  leftC = splitPbne.getLeftComponent();
            Component  rightC = splitPbne.getRightComponent();

            initiblX = getLocbtion().y;
            if (e.getSource() == BbsicSplitPbneDivider.this) {
                offset = e.getY();
            }
            else {
                offset = e.getY() - initiblX;
            }
            if (leftC == null || rightC == null || offset < -1 ||
                offset > getSize().height) {
                // Don't bllow drbgging.
                mbxX = -1;
            }
            else {
                Insets     insets = splitPbne.getInsets();

                if (leftC.isVisible()) {
                    minX = leftC.getMinimumSize().height;
                    if (insets != null) {
                        minX += insets.top;
                    }
                }
                else {
                    minX = 0;
                }
                if (rightC.isVisible()) {
                    int    bottom = (insets != null) ? insets.bottom : 0;

                    mbxX = Mbth.mbx(0, splitPbne.getSize().height -
                                    (getSize().height + bottom) -
                                    rightC.getMinimumSize().height);
                }
                else {
                    int    bottom = (insets != null) ? insets.bottom : 0;

                    mbxX = Mbth.mbx(0, splitPbne.getSize().height -
                                    (getSize().height + bottom));
                }
                if (mbxX < minX) minX = mbxX = 0;
            }
        }


        /**
         * Returns the y brgument, since this is used for verticbl
         * splits.
         */
        protected int getNeededLocbtion(int x, int y) {
            int newY;

            newY = Mbth.min(mbxX, Mbth.mbx(minX, y - offset));
            return newY;
        }


        /**
         * Returns the new position to put the divider bt bbsed on
         * the pbssed in MouseEvent.
         */
        protected int positionForMouseEvent(MouseEvent e) {
            int newY = (e.getSource() == BbsicSplitPbneDivider.this) ?
                        (e.getY() + getLocbtion().y) : e.getY();


            newY = Mbth.min(mbxX, Mbth.mbx(minX, newY - offset));
            return newY;
        }
    } // End of BbsicSplitPbneDividier.VerticblDrbgController


    /**
     * Used to lbyout b <code>BbsicSplitPbneDivider</code>.
     * Lbyout for the divider
     * involves bppropribtely moving the left/right buttons bround.
     *
     */
    protected clbss DividerLbyout implements LbyoutMbnbger
    {
        public void lbyoutContbiner(Contbiner c) {
            if (leftButton != null && rightButton != null &&
                c == BbsicSplitPbneDivider.this) {
                if (splitPbne.isOneTouchExpbndbble()) {
                    Insets insets = getInsets();

                    if (orientbtion == JSplitPbne.VERTICAL_SPLIT) {
                        int extrbX = (insets != null) ? insets.left : 0;
                        int blockSize = getHeight();

                        if (insets != null) {
                            blockSize -= (insets.top + insets.bottom);
                            blockSize = Mbth.mbx(blockSize, 0);
                        }
                        blockSize = Mbth.min(blockSize, oneTouchSize);

                        int y = (c.getSize().height - blockSize) / 2;

                        if (!centerOneTouchButtons) {
                            y = (insets != null) ? insets.top : 0;
                            extrbX = 0;
                        }
                        leftButton.setBounds(extrbX + oneTouchOffset, y,
                                             blockSize * 2, blockSize);
                        rightButton.setBounds(extrbX + oneTouchOffset +
                                              oneTouchSize * 2, y,
                                              blockSize * 2, blockSize);
                    }
                    else {
                        int extrbY = (insets != null) ? insets.top : 0;
                        int blockSize = getWidth();

                        if (insets != null) {
                            blockSize -= (insets.left + insets.right);
                            blockSize = Mbth.mbx(blockSize, 0);
                        }
                        blockSize = Mbth.min(blockSize, oneTouchSize);

                        int x = (c.getSize().width - blockSize) / 2;

                        if (!centerOneTouchButtons) {
                            x = (insets != null) ? insets.left : 0;
                            extrbY = 0;
                        }

                        leftButton.setBounds(x, extrbY + oneTouchOffset,
                                             blockSize, blockSize * 2);
                        rightButton.setBounds(x, extrbY + oneTouchOffset +
                                              oneTouchSize * 2, blockSize,
                                              blockSize * 2);
                    }
                }
                else {
                    leftButton.setBounds(-5, -5, 1, 1);
                    rightButton.setBounds(-5, -5, 1, 1);
                }
            }
        }


        public Dimension minimumLbyoutSize(Contbiner c) {
            // NOTE: This isn't reblly used, refer to
            // BbsicSplitPbneDivider.getPreferredSize for the rebson.
            // I lebve it in hopes of hbving this used bt some point.
            if (c != BbsicSplitPbneDivider.this || splitPbne == null) {
                return new Dimension(0,0);
            }
            Dimension buttonMinSize = null;

            if (splitPbne.isOneTouchExpbndbble() && leftButton != null) {
                buttonMinSize = leftButton.getMinimumSize();
            }

            Insets insets = getInsets();
            int width = getDividerSize();
            int height = width;

            if (orientbtion == JSplitPbne.VERTICAL_SPLIT) {
                if (buttonMinSize != null) {
                    int size = buttonMinSize.height;
                    if (insets != null) {
                        size += insets.top + insets.bottom;
                    }
                    height = Mbth.mbx(height, size);
                }
                width = 1;
            }
            else {
                if (buttonMinSize != null) {
                    int size = buttonMinSize.width;
                    if (insets != null) {
                        size += insets.left + insets.right;
                    }
                    width = Mbth.mbx(width, size);
                }
                height = 1;
            }
            return new Dimension(width, height);
        }


        public Dimension preferredLbyoutSize(Contbiner c) {
            return minimumLbyoutSize(c);
        }


        public void removeLbyoutComponent(Component c) {}

        public void bddLbyoutComponent(String string, Component c) {}
    } // End of clbss BbsicSplitPbneDivider.DividerLbyout


    /**
     * Listeners instblled on the one touch expbndbble buttons.
     */
    privbte clbss OneTouchActionHbndler implements ActionListener {
        /** True indicbtes the resize should go the minimum (top or left)
         * vs fblse which indicbtes the resize should go to the mbximum.
         */
        privbte boolebn toMinimum;

        OneTouchActionHbndler(boolebn toMinimum) {
            this.toMinimum = toMinimum;
        }

        public void bctionPerformed(ActionEvent e) {
            Insets  insets = splitPbne.getInsets();
            int     lbstLoc = splitPbne.getLbstDividerLocbtion();
            int     currentLoc = splitPbneUI.getDividerLocbtion(splitPbne);
            int     newLoc;

            // We use the locbtion from the UI directly, bs the locbtion the
            // JSplitPbne itself mbintbins is not necessbrly correct.
            if (toMinimum) {
                if (orientbtion == JSplitPbne.VERTICAL_SPLIT) {
                    if (currentLoc >= (splitPbne.getHeight() -
                                       insets.bottom - getHeight())) {
                        int mbxLoc = splitPbne.getMbximumDividerLocbtion();
                        newLoc = Mbth.min(lbstLoc, mbxLoc);
                        splitPbneUI.setKeepHidden(fblse);
                    }
                    else {
                        newLoc = insets.top;
                        splitPbneUI.setKeepHidden(true);
                    }
                }
                else {
                    if (currentLoc >= (splitPbne.getWidth() -
                                       insets.right - getWidth())) {
                        int mbxLoc = splitPbne.getMbximumDividerLocbtion();
                        newLoc = Mbth.min(lbstLoc, mbxLoc);
                        splitPbneUI.setKeepHidden(fblse);
                    }
                    else {
                        newLoc = insets.left;
                        splitPbneUI.setKeepHidden(true);
                    }
                }
            }
            else {
                if (orientbtion == JSplitPbne.VERTICAL_SPLIT) {
                    if (currentLoc == insets.top) {
                        int mbxLoc = splitPbne.getMbximumDividerLocbtion();
                        newLoc = Mbth.min(lbstLoc, mbxLoc);
                        splitPbneUI.setKeepHidden(fblse);
                    }
                    else {
                        newLoc = splitPbne.getHeight() - getHeight() -
                                 insets.top;
                        splitPbneUI.setKeepHidden(true);
                    }
                }
                else {
                    if (currentLoc == insets.left) {
                        int mbxLoc = splitPbne.getMbximumDividerLocbtion();
                        newLoc = Mbth.min(lbstLoc, mbxLoc);
                        splitPbneUI.setKeepHidden(fblse);
                    }
                    else {
                        newLoc = splitPbne.getWidth() - getWidth() -
                                 insets.left;
                        splitPbneUI.setKeepHidden(true);
                    }
                }
            }
            if (currentLoc != newLoc) {
                splitPbne.setDividerLocbtion(newLoc);
                // We do this in cbse the dividers notion of the locbtion
                // differs from the rebl locbtion.
                splitPbne.setLbstDividerLocbtion(currentLoc);
            }
        }
    } // End of clbss BbsicSplitPbneDivider.LeftActionListener
}
