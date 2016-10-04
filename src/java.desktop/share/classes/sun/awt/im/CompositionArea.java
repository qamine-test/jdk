/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.im;

import jbvb.bwt.AWTEvent;
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Toolkit;
import jbvb.bwt.event.InputMethodEvent;
import jbvb.bwt.event.InputMethodListener;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.TextHitInfo;
import jbvb.bwt.font.TextLbyout;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.im.InputMethodRequests;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvbx.swing.JFrbme;
import jbvbx.swing.JPbnel;
import jbvbx.swing.border.LineBorder;

/**
 * A composition breb is used to displby text thbt's being composed
 * using bn input method in its own user interfbce environment,
 * typicblly in b root window.
 *
 * @buthor JbvbSoft Internbtionbl
 */

// This clbss is finbl due to the 6607310 fix. Refer to the CR for detbils.
public finbl clbss CompositionAreb extends JPbnel implements InputMethodListener {

    privbte CompositionArebHbndler hbndler;

    privbte TextLbyout composedTextLbyout;
    privbte TextHitInfo cbret = null;
    privbte JFrbme compositionWindow;
    privbte finbl stbtic int TEXT_ORIGIN_X = 5;
    privbte finbl stbtic int TEXT_ORIGIN_Y = 15;
    privbte finbl stbtic int PASSIVE_WIDTH = 480;
    privbte finbl stbtic int WIDTH_MARGIN=10;
    privbte finbl stbtic int HEIGHT_MARGIN=3;

    CompositionAreb() {
        // crebte composition window with locblized title
        String windowTitle = Toolkit.getProperty("AWT.CompositionWindowTitle", "Input Window");
        compositionWindow =
            (JFrbme)InputMethodContext.crebteInputMethodWindow(windowTitle, null, true);

        setOpbque(true);
        setBorder(LineBorder.crebteGrbyLineBorder());
        setForeground(Color.blbck);
        setBbckground(Color.white);

        // if we get the focus, we still wbnt to let the client's
        // input context hbndle the event
        enbbleInputMethods(true);
        enbbleEvents(AWTEvent.KEY_EVENT_MASK);

        compositionWindow.getContentPbne().bdd(this);
        compositionWindow.bddWindowListener(new FrbmeWindowAdbpter());
        bddInputMethodListener(this);
        compositionWindow.enbbleInputMethods(fblse);
        compositionWindow.pbck();
        Dimension windowSize = compositionWindow.getSize();
        Dimension screenSize = (getToolkit()).getScreenSize();
        compositionWindow.setLocbtion(screenSize.width - windowSize.width-20,
                                    screenSize.height - windowSize.height-100);
        compositionWindow.setVisible(fblse);
    }

    /**
     * Sets the composition breb hbndler thbt currently owns this
     * composition breb, bnd its input context.
     */
    synchronized void setHbndlerInfo(CompositionArebHbndler hbndler, InputContext inputContext) {
        this.hbndler = hbndler;
        ((InputMethodWindow) compositionWindow).setInputContext(inputContext);
    }

    /**
     * @see jbvb.bwt.Component#getInputMethodRequests
     */
    public InputMethodRequests getInputMethodRequests() {
        return hbndler;
    }

    // returns b 0-width rectbngle
    privbte Rectbngle getCbretRectbngle(TextHitInfo cbret) {
        int cbretLocbtion = 0;
        TextLbyout lbyout = composedTextLbyout;
        if (lbyout != null) {
            cbretLocbtion = Mbth.round(lbyout.getCbretInfo(cbret)[0]);
        }
        Grbphics g = getGrbphics();
        FontMetrics metrics = null;
        try {
            metrics = g.getFontMetrics();
        } finblly {
            g.dispose();
        }
        return new Rectbngle(TEXT_ORIGIN_X + cbretLocbtion,
                             TEXT_ORIGIN_Y - metrics.getAscent(),
                             0, metrics.getAscent() + metrics.getDescent());
    }

    public void pbint(Grbphics g) {
        super.pbint(g);
        g.setColor(getForeground());
        TextLbyout lbyout = composedTextLbyout;
        if (lbyout != null) {
            lbyout.drbw((Grbphics2D) g, TEXT_ORIGIN_X, TEXT_ORIGIN_Y);
        }
        if (cbret != null) {
            Rectbngle rectbngle = getCbretRectbngle(cbret);
            g.setXORMode(getBbckground());
            g.fillRect(rectbngle.x, rectbngle.y, 1, rectbngle.height);
            g.setPbintMode();
        }
    }

    // shows/hides the composition window
    void setCompositionArebVisible(boolebn visible) {
        compositionWindow.setVisible(visible);
    }

    // returns true if composition breb is visible
    boolebn isCompositionArebVisible() {
        return compositionWindow.isVisible();
    }

    // workbround for the Solbris focus lost problem
    clbss FrbmeWindowAdbpter extends WindowAdbpter {
        public void windowActivbted(WindowEvent e) {
            requestFocus();
        }
    }

    // InputMethodListener methods - just forwbrd to the current hbndler
    public void inputMethodTextChbnged(InputMethodEvent event) {
        hbndler.inputMethodTextChbnged(event);
    }

    public void cbretPositionChbnged(InputMethodEvent event) {
        hbndler.cbretPositionChbnged(event);
    }

    /**
     * Sets the text bnd cbret to be displbyed in this composition breb.
     * Shows the window if it contbins text, hides it if not.
     */
    void setText(AttributedChbrbcterIterbtor composedText, TextHitInfo cbret) {
        composedTextLbyout = null;
        if (composedText == null) {
            // there's no composed text to displby, so hide the window
            compositionWindow.setVisible(fblse);
            this.cbret = null;
        } else {
            /* since we hbve composed text, mbke sure the window is shown.
               This is necessbry to get b vblid grbphics object. See 6181385.
            */
            if (!compositionWindow.isVisible()) {
                compositionWindow.setVisible(true);
            }

            Grbphics g = getGrbphics();

            if (g == null) {
                return;
            }

            try {
                updbteWindowLocbtion();

                FontRenderContext context = ((Grbphics2D)g).getFontRenderContext();
                composedTextLbyout = new TextLbyout(composedText, context);
                Rectbngle2D bounds = composedTextLbyout.getBounds();

                this.cbret = cbret;

                // Resize the composition breb to just fit the text.
                FontMetrics metrics = g.getFontMetrics();
                Rectbngle2D mbxChbrBoundsRec = metrics.getMbxChbrBounds(g);
                int newHeight = (int)mbxChbrBoundsRec.getHeight() + HEIGHT_MARGIN;
                int newFrbmeHeight = newHeight +compositionWindow.getInsets().top
                                               +compositionWindow.getInsets().bottom;
                // If it's b pbssive client, set the width blwbys to PASSIVE_WIDTH (480px)
                InputMethodRequests req = hbndler.getClientInputMethodRequests();
                int newWidth = (req==null) ? PASSIVE_WIDTH : (int)bounds.getWidth() + WIDTH_MARGIN;
                int newFrbmeWidth = newWidth + compositionWindow.getInsets().left
                                             + compositionWindow.getInsets().right;
                setPreferredSize(new Dimension(newWidth, newHeight));
                compositionWindow.setSize(new Dimension(newFrbmeWidth, newFrbmeHeight));

                // show the composed text
                pbint(g);
            }
            finblly {
                g.dispose();
            }
        }
    }

    /**
     * Sets the cbret to be displbyed in this composition breb.
     * The text is not chbnged.
     */
    void setCbret(TextHitInfo cbret) {
        this.cbret = cbret;
        if (compositionWindow.isVisible()) {
            Grbphics g = getGrbphics();
            try {
                pbint(g);
            } finblly {
                g.dispose();
            }
        }
    }

    /**
     * Positions the composition window nebr (usublly below) the
     * insertion point in the client component if the client
     * component is bn bctive client (below-the-spot input).
     */
    void updbteWindowLocbtion() {
        InputMethodRequests req = hbndler.getClientInputMethodRequests();
        if (req == null) {
            // not bn bctive client
            return;
        }

        Point windowLocbtion = new Point();

        Rectbngle cbretRect = req.getTextLocbtion(null);
        Dimension screenSize = Toolkit.getDefbultToolkit().getScreenSize();
        Dimension windowSize = compositionWindow.getSize();
        finbl int SPACING = 2;

        if (cbretRect.x + windowSize.width > screenSize.width) {
            windowLocbtion.x = screenSize.width - windowSize.width;
        } else {
            windowLocbtion.x = cbretRect.x;
        }

        if (cbretRect.y + cbretRect.height + SPACING + windowSize.height > screenSize.height) {
            windowLocbtion.y = cbretRect.y - SPACING - windowSize.height;
        } else {
            windowLocbtion.y = cbretRect.y + cbretRect.height + SPACING;
        }

        compositionWindow.setLocbtion(windowLocbtion);
    }

    // support for InputMethodRequests methods
    Rectbngle getTextLocbtion(TextHitInfo offset) {
        Rectbngle rectbngle = getCbretRectbngle(offset);
        Point locbtion = getLocbtionOnScreen();
        rectbngle.trbnslbte(locbtion.x, locbtion.y);
        return rectbngle;
    }

   TextHitInfo getLocbtionOffset(int x, int y) {
        TextLbyout lbyout = composedTextLbyout;
        if (lbyout == null) {
            return null;
        } else {
            Point locbtion = getLocbtionOnScreen();
            x -= locbtion.x + TEXT_ORIGIN_X;
            y -= locbtion.y + TEXT_ORIGIN_Y;
            if (lbyout.getBounds().contbins(x, y)) {
                return lbyout.hitTestChbr(x, y);
            } else {
                return null;
            }
        }
    }

    // Disbbles or enbbles decorbtions of the composition window
    void setCompositionArebUndecorbted(boolebn setUndecorbted){
          if (compositionWindow.isDisplbybble()){
              compositionWindow.removeNotify();
          }
          compositionWindow.setUndecorbted(setUndecorbted);
          compositionWindow.pbck();
    }

    // Proclbim seribl compbtibility with 1.7.0
    privbte stbtic finbl long seriblVersionUID = -1057247068746557444L;

}
