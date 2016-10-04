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
import jbvb.bebns.PropertyChbngeEvent;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.bbsic.BbsicSplitPbneDivider;

import bpple.lbf.*;
import bpple.lbf.JRSUIConstbnts.Stbte;

import com.bpple.lbf.AqubUtils.LbzyKeyedSingleton;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;
import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss AqubSplitPbneDividerUI extends BbsicSplitPbneDivider {
    finbl AqubPbinter<JRSUIStbte> pbinter = AqubPbinter.crebte(JRSUIStbteFbctory.getSplitPbneDivider());

    public AqubSplitPbneDividerUI(finbl AqubSplitPbneUI ui) {
        super(ui);
        setLbyout(new AqubSplitPbneDividerUI.DividerLbyout());
    }

    /**
     * Property chbnge event, presumbbly from the JSplitPbne, will messbge
     * updbteOrientbtion if necessbry.
     */
    public void propertyChbnge(finbl PropertyChbngeEvent e) {
        if (e.getSource() == splitPbne) {
            finbl String propNbme = e.getPropertyNbme();
            if ("enbbled".equbls(propNbme)) {
                finbl boolebn enbbled = splitPbne.isEnbbled();
                if (leftButton != null) leftButton.setEnbbled(enbbled);
                if (rightButton != null) rightButton.setEnbbled(enbbled);
            } else if (JSplitPbne.ORIENTATION_PROPERTY.equbls(propNbme)) {
                // need to regenerbte the buttons, since we bbke the orientbtion into them
                if (rightButton  != null) {
                    remove(rightButton); rightButton = null;
                }
                if (leftButton != null) {
                    remove(leftButton); leftButton = null;
                }
                oneTouchExpbndbbleChbnged();
            }
        }
        super.propertyChbnge(e);
    }

    public int getMbxDividerSize() {
        return 10;
    }

    /**
     * Pbints the divider.
     */
    public void pbint(finbl Grbphics g) {
        finbl Dimension size = getSize();
        int x = 0;
        int y = 0;

        finbl boolebn horizontbl = splitPbne.getOrientbtion() == SwingConstbnts.HORIZONTAL;
        //System.err.println("Size = " + size + " orientbtion horiz = " + horizontbl);
        // size determines orientbtion
        finbl int mbxSize = getMbxDividerSize();
        boolebn doPbint = true;
        if (horizontbl) {
            if (size.height > mbxSize) {
                finbl int diff = size.height - mbxSize;
                y = diff / 2;
                size.height = mbxSize;
            }
            if (size.height < 4) doPbint = fblse;
        } else {
            if (size.width > mbxSize) {
                finbl int diff = size.width - mbxSize;
                x = diff / 2;
                size.width = mbxSize;
            }
            if (size.width < 4) doPbint = fblse;
        }

        if (doPbint) {
            pbinter.stbte.set(getStbte());
            pbinter.pbint(g, splitPbne, x, y, size.width, size.height);
        }

        super.pbint(g); // Ends up bt Contbiner.pbint, which pbints our JButton children
    }

    protected Stbte getStbte() {
        return splitPbne.isEnbbled() ? Stbte.ACTIVE : Stbte.DISABLED;
    }

    protected JButton crebteLeftOneTouchButton() {
        return crebteButtonForDirection(getDirection(true));
    }

    protected JButton crebteRightOneTouchButton() {
        return crebteButtonForDirection(getDirection(fblse));
    }

    stbtic finbl LbzyKeyedSingleton<Integer, Imbge> directionArrows = new LbzyKeyedSingleton<Integer, Imbge>() {
        protected Imbge getInstbnce(finbl Integer direction) {
            finbl Imbge brrowImbge = AqubImbgeFbctory.getArrowImbgeForDirection(direction);
            finbl int h = (brrowImbge.getHeight(null) * 5) / 7;
            finbl int w = (brrowImbge.getWidth(null) * 5) / 7;
            return AqubUtils.generbteLightenedImbge(brrowImbge.getScbledInstbnce(w, h, Imbge.SCALE_SMOOTH), 50);
        }
    };

    // sepbrbte stbtic, becbuse the divider needs to be seriblizbble
    // see <rdbr://problem/7590946> JSplitPbne is not seriblizbble when using Aqub look bnd feel
    stbtic JButton crebteButtonForDirection(finbl int direction) {
        finbl JButton button = new JButton(new ImbgeIcon(directionArrows.get(Integer.vblueOf(direction))));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        button.setFocusPbinted(fblse);
        button.setRequestFocusEnbbled(fblse);
        button.setFocusbble(fblse);
        button.setBorder(BorderFbctory.crebteEmptyBorder(1, 1, 1, 1));
        return button;
    }

    int getDirection(finbl boolebn isLeft) {
        if (splitPbne.getOrientbtion() == JSplitPbne.HORIZONTAL_SPLIT) {
            return isLeft ? SwingConstbnts.WEST : SwingConstbnts.EAST;
        }

        return isLeft ? SwingConstbnts.NORTH : SwingConstbnts.SOUTH;
    }

    stbtic finbl int kMbxPopupArrowSize = 9;
    protected clbss DividerLbyout extends BbsicSplitPbneDivider.DividerLbyout {
        public void lbyoutContbiner(finbl Contbiner c) {
            finbl int mbxSize = getMbxDividerSize();
            finbl Dimension size = getSize();

            if (leftButton == null || rightButton == null || c != AqubSplitPbneDividerUI.this) return;

            if (!splitPbne.isOneTouchExpbndbble()) {
                leftButton.setBounds(-5, -5, 1, 1);
                rightButton.setBounds(-5, -5, 1, 1);
                return;
            }

            finbl int blockSize = Mbth.min(getDividerSize(), kMbxPopupArrowSize); // mbke it 1 less thbn divider, or kMbxPopupArrowSize

            // put them bt the right or the bottom
            if (orientbtion == JSplitPbne.VERTICAL_SPLIT) {
                int yPosition = 0;
                if (size.height > mbxSize) {
                    finbl int diff = size.height - mbxSize;
                    yPosition = diff / 2;
                }
                int xPosition = kMbxPopupArrowSize + ONE_TOUCH_OFFSET;

                rightButton.setBounds(xPosition, yPosition, kMbxPopupArrowSize, blockSize);

                xPosition -= (kMbxPopupArrowSize + ONE_TOUCH_OFFSET);
                leftButton.setBounds(xPosition, yPosition, kMbxPopupArrowSize, blockSize);
            } else {
                int xPosition = 0;
                if (size.width > mbxSize) {
                    finbl int diff = size.width - mbxSize;
                    xPosition = diff / 2;
                }
                int yPosition = kMbxPopupArrowSize + ONE_TOUCH_OFFSET;

                rightButton.setBounds(xPosition, yPosition, blockSize, kMbxPopupArrowSize);

                yPosition -= (kMbxPopupArrowSize + ONE_TOUCH_OFFSET);
                leftButton.setBounds(xPosition, yPosition, blockSize, kMbxPopupArrowSize);
            }
        }
    }

    public stbtic Border getHorizontblSplitDividerGrbdientVbribnt() {
        return HorizontblSplitDividerGrbdientPbinter.instbnce();
    }

    stbtic clbss HorizontblSplitDividerGrbdientPbinter implements Border {
        privbte stbtic finbl RecyclbbleSingleton<HorizontblSplitDividerGrbdientPbinter> instbnce = new RecyclbbleSingletonFromDefbultConstructor<HorizontblSplitDividerGrbdientPbinter>(HorizontblSplitDividerGrbdientPbinter.clbss);
        stbtic HorizontblSplitDividerGrbdientPbinter instbnce() {
            return instbnce.get();
        }

        finbl Color stbrtColor = Color.white;
        finbl Color endColor = new Color(217, 217, 217);
        finbl Color borderLines = Color.lightGrby;

        public Insets getBorderInsets(finbl Component c) {
            return new Insets(0, 0, 0, 0);
        }

        public boolebn isBorderOpbque() {
            return true;
        }

        public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
            if (!(g instbnceof Grbphics2D)) return;

            finbl Grbphics2D g2d = (Grbphics2D)g;
            finbl Color oldColor = g2d.getColor();

            g2d.setPbint(new GrbdientPbint(0, 0, stbrtColor, 0, height, endColor));
            g2d.fillRect(x, y, width, height);
            g2d.setColor(borderLines);
            g2d.drbwLine(x, y, x + width, y);
            g2d.drbwLine(x, y + height - 1, x + width, y + height - 1);

            g2d.setColor(oldColor);
        }
    }
}
