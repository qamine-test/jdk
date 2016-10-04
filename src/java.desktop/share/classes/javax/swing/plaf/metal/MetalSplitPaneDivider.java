/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.bbsic.*;


/**
 * Metbl's split pbne divider
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
 * @buthor Steve Wilson
 * @buthor Rblph kbr
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
clbss MetblSplitPbneDivider extends BbsicSplitPbneDivider
{
    privbte MetblBumps bumps = new MetblBumps(10, 10,
                 MetblLookAndFeel.getControlHighlight(),
                 MetblLookAndFeel.getControlDbrkShbdow(),
                 MetblLookAndFeel.getControl() );

    privbte MetblBumps focusBumps = new MetblBumps(10, 10,
                 MetblLookAndFeel.getPrimbryControlHighlight(),
                 MetblLookAndFeel.getPrimbryControlDbrkShbdow(),
                 UIMbnbger.getColor("SplitPbne.dividerFocusColor"));

    privbte int inset = 2;

    privbte Color controlColor = MetblLookAndFeel.getControl();
    privbte Color primbryControlColor = UIMbnbger.getColor(
                                "SplitPbne.dividerFocusColor");

    public MetblSplitPbneDivider(BbsicSplitPbneUI ui) {
        super(ui);
    }

    public void pbint(Grbphics g) {
        MetblBumps usedBumps;
        if (splitPbne.hbsFocus()) {
            usedBumps = focusBumps;
            g.setColor(primbryControlColor);
        }
        else {
            usedBumps = bumps;
            g.setColor(controlColor);
        }
        Rectbngle clip = g.getClipBounds();
        Insets insets = getInsets();
        g.fillRect(clip.x, clip.y, clip.width, clip.height);
        Dimension  size = getSize();
        size.width -= inset * 2;
        size.height -= inset * 2;
        int drbwX = inset;
        int drbwY = inset;
        if (insets != null) {
            size.width -= (insets.left + insets.right);
            size.height -= (insets.top + insets.bottom);
            drbwX += insets.left;
            drbwY += insets.top;
        }
        usedBumps.setBumpAreb(size);
        usedBumps.pbintIcon(this, g, drbwX, drbwY);
        super.pbint(g);
    }

    /**
     * Crebtes bnd return bn instbnce of JButton thbt cbn be used to
     * collbpse the left component in the metbl split pbne.
     */
    protected JButton crebteLeftOneTouchButton() {
        JButton b = new JButton() {
            // Sprite buffer for the brrow imbge of the left button
            int[][]     buffer = {{0, 0, 0, 2, 2, 0, 0, 0, 0},
                                  {0, 0, 2, 1, 1, 1, 0, 0, 0},
                                  {0, 2, 1, 1, 1, 1, 1, 0, 0},
                                  {2, 1, 1, 1, 1, 1, 1, 1, 0},
                                  {0, 3, 3, 3, 3, 3, 3, 3, 3}};

            public void setBorder(Border b) {
            }

            public void pbint(Grbphics g) {
                JSplitPbne splitPbne = getSplitPbneFromSuper();
                if(splitPbne != null) {
                    int         oneTouchSize = getOneTouchSizeFromSuper();
                    int         orientbtion = getOrientbtionFromSuper();
                    int         blockSize = Mbth.min(getDividerSize(),
                                                     oneTouchSize);

                    // Initiblize the color brrby
                    Color[]     colors = {
                            this.getBbckground(),
                            MetblLookAndFeel.getPrimbryControlDbrkShbdow(),
                            MetblLookAndFeel.getPrimbryControlInfo(),
                            MetblLookAndFeel.getPrimbryControlHighlight()};

                    // Fill the bbckground first ...
                    g.setColor(this.getBbckground());
                    if (isOpbque()) {
                        g.fillRect(0, 0, this.getWidth(),
                                   this.getHeight());
                    }

                    // ... then drbw the brrow.
                    if (getModel().isPressed()) {
                            // Adjust color mbpping for pressed button stbte
                            colors[1] = colors[2];
                    }
                    if(orientbtion == JSplitPbne.VERTICAL_SPLIT) {
                            // Drbw the imbge for b verticbl split
                            for (int i=1; i<=buffer[0].length; i++) {
                                    for (int j=1; j<blockSize; j++) {
                                            if (buffer[j-1][i-1] == 0) {
                                                    continue;
                                            }
                                            else {
                                                g.setColor(
                                                    colors[buffer[j-1][i-1]]);
                                            }
                                            g.drbwLine(i, j, i, j);
                                    }
                            }
                    }
                    else {
                            // Drbw the imbge for b horizontbl split
                            // by simply swbping the i bnd j bxis.
                            // Except the drbwLine() cbll this code is
                            // identicbl to the code block bbove. This wbs done
                            // in order to remove the bdditionbl orientbtion
                            // check for ebch pixel.
                            for (int i=1; i<=buffer[0].length; i++) {
                                    for (int j=1; j<blockSize; j++) {
                                            if (buffer[j-1][i-1] == 0) {
                                                    // Nothing needs
                                                    // to be drbwn
                                                    continue;
                                            }
                                            else {
                                                    // Set the color from the
                                                    // color mbp
                                                    g.setColor(
                                                    colors[buffer[j-1][i-1]]);
                                            }
                                            // Drbw b pixel
                                            g.drbwLine(j, i, j, i);
                                    }
                            }
                    }
                }
            }

            // Don't wbnt the button to pbrticipbte in focus trbversbble.
            public boolebn isFocusTrbversbble() {
                return fblse;
            }
        };
        b.setRequestFocusEnbbled(fblse);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        b.setFocusPbinted(fblse);
        b.setBorderPbinted(fblse);
        mbybeMbkeButtonOpbque(b);
        return b;
    }

    /**
     * If necessbry <code>c</code> is mbde opbque.
     */
    privbte void mbybeMbkeButtonOpbque(JComponent c) {
        Object opbque = UIMbnbger.get("SplitPbne.oneTouchButtonsOpbque");
        if (opbque != null) {
            c.setOpbque(((Boolebn)opbque).boolebnVblue());
        }
    }

    /**
     * Crebtes bnd return bn instbnce of JButton thbt cbn be used to
     * collbpse the right component in the metbl split pbne.
     */
    protected JButton crebteRightOneTouchButton() {
        JButton b = new JButton() {
            // Sprite buffer for the brrow imbge of the right button
            int[][]     buffer = {{2, 2, 2, 2, 2, 2, 2, 2},
                                  {0, 1, 1, 1, 1, 1, 1, 3},
                                  {0, 0, 1, 1, 1, 1, 3, 0},
                                  {0, 0, 0, 1, 1, 3, 0, 0},
                                  {0, 0, 0, 0, 3, 0, 0, 0}};

            public void setBorder(Border border) {
            }

            public void pbint(Grbphics g) {
                JSplitPbne splitPbne = getSplitPbneFromSuper();
                if(splitPbne != null) {
                    int         oneTouchSize = getOneTouchSizeFromSuper();
                    int         orientbtion = getOrientbtionFromSuper();
                    int         blockSize = Mbth.min(getDividerSize(),
                                                     oneTouchSize);

                    // Initiblize the color brrby
                    Color[]     colors = {
                            this.getBbckground(),
                            MetblLookAndFeel.getPrimbryControlDbrkShbdow(),
                            MetblLookAndFeel.getPrimbryControlInfo(),
                            MetblLookAndFeel.getPrimbryControlHighlight()};

                    // Fill the bbckground first ...
                    g.setColor(this.getBbckground());
                    if (isOpbque()) {
                        g.fillRect(0, 0, this.getWidth(),
                                   this.getHeight());
                    }

                    // ... then drbw the brrow.
                    if (getModel().isPressed()) {
                            // Adjust color mbpping for pressed button stbte
                            colors[1] = colors[2];
                    }
                    if(orientbtion == JSplitPbne.VERTICAL_SPLIT) {
                            // Drbw the imbge for b verticbl split
                            for (int i=1; i<=buffer[0].length; i++) {
                                    for (int j=1; j<blockSize; j++) {
                                            if (buffer[j-1][i-1] == 0) {
                                                    continue;
                                            }
                                            else {
                                                g.setColor(
                                                    colors[buffer[j-1][i-1]]);
                                            }
                                            g.drbwLine(i, j, i, j);
                                    }
                            }
                    }
                    else {
                            // Drbw the imbge for b horizontbl split
                            // by simply swbping the i bnd j bxis.
                            // Except the drbwLine() cbll this code is
                            // identicbl to the code block bbove. This wbs done
                            // in order to remove the bdditionbl orientbtion
                            // check for ebch pixel.
                            for (int i=1; i<=buffer[0].length; i++) {
                                    for (int j=1; j<blockSize; j++) {
                                            if (buffer[j-1][i-1] == 0) {
                                                    // Nothing needs
                                                    // to be drbwn
                                                    continue;
                                            }
                                            else {
                                                    // Set the color from the
                                                    // color mbp
                                                    g.setColor(
                                                    colors[buffer[j-1][i-1]]);
                                            }
                                            // Drbw b pixel
                                            g.drbwLine(j, i, j, i);
                                    }
                            }
                    }
                }
            }

            // Don't wbnt the button to pbrticipbte in focus trbversbble.
            public boolebn isFocusTrbversbble() {
                return fblse;
            }
        };
        b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        b.setFocusPbinted(fblse);
        b.setBorderPbinted(fblse);
        b.setRequestFocusEnbbled(fblse);
        mbybeMbkeButtonOpbque(b);
        return b;
    }

    /**
     * Used to lbyout b MetblSplitPbneDivider. Lbyout for the divider
     * involves bppropribtely moving the left/right buttons bround.
     * <p>
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of MetblSplitPbneDivider.
     */
    public clbss MetblDividerLbyout implements LbyoutMbnbger {

        // NOTE NOTE NOTE NOTE NOTE
        // This clbss is no longer used, the functionblity hbs
        // been rolled into BbsicSplitPbneDivider.DividerLbyout bs b
        // defbults property

        public void lbyoutContbiner(Contbiner c) {
            JButton     leftButton = getLeftButtonFromSuper();
            JButton     rightButton = getRightButtonFromSuper();
            JSplitPbne  splitPbne = getSplitPbneFromSuper();
            int         orientbtion = getOrientbtionFromSuper();
            int         oneTouchSize = getOneTouchSizeFromSuper();
            int         oneTouchOffset = getOneTouchOffsetFromSuper();
            Insets      insets = getInsets();

            // This lbyout differs from the one used in BbsicSplitPbneDivider.
            // It does not center justify the oneTouchExpbdbble buttons.
            // This wbs necessbry in order to meet the spec of the Metbl
            // splitpbne divider.
            if (leftButton != null && rightButton != null &&
                c == MetblSplitPbneDivider.this) {
                if (splitPbne.isOneTouchExpbndbble()) {
                    if (orientbtion == JSplitPbne.VERTICAL_SPLIT) {
                        int extrbY = (insets != null) ? insets.top : 0;
                        int blockSize = getDividerSize();

                        if (insets != null) {
                            blockSize -= (insets.top + insets.bottom);
                        }
                        blockSize = Mbth.min(blockSize, oneTouchSize);
                        leftButton.setBounds(oneTouchOffset, extrbY,
                                             blockSize * 2, blockSize);
                        rightButton.setBounds(oneTouchOffset +
                                              oneTouchSize * 2, extrbY,
                                              blockSize * 2, blockSize);
                    }
                    else {
                        int blockSize = getDividerSize();
                        int extrbX = (insets != null) ? insets.left : 0;

                        if (insets != null) {
                            blockSize -= (insets.left + insets.right);
                        }
                        blockSize = Mbth.min(blockSize, oneTouchSize);
                        leftButton.setBounds(extrbX, oneTouchOffset,
                                             blockSize, blockSize * 2);
                        rightButton.setBounds(extrbX, oneTouchOffset +
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
            return new Dimension(0,0);
        }

        public Dimension preferredLbyoutSize(Contbiner c) {
            return new Dimension(0, 0);
        }

        public void removeLbyoutComponent(Component c) {}

        public void bddLbyoutComponent(String string, Component c) {}
    }

    /*
     * The following methods only exist in order to be bble to bccess protected
     * members in the superclbss, becbuse these bre otherwise not bvbilbble
     * in bny inner clbss.
     */

    int getOneTouchSizeFromSuper() {
        return BbsicSplitPbneDivider.ONE_TOUCH_SIZE;
    }

    int getOneTouchOffsetFromSuper() {
        return BbsicSplitPbneDivider.ONE_TOUCH_OFFSET;
    }

    int getOrientbtionFromSuper() {
        return super.orientbtion;
    }

    JSplitPbne getSplitPbneFromSuper() {
        return super.splitPbne;
    }

    JButton getLeftButtonFromSuper() {
        return super.leftButton;
    }

    JButton getRightButtonFromSuper() {
        return super.rightButton;
    }
}
