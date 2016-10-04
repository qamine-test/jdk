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
import jbvb.bwt.geom.*;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.*;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;
import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

public clbss AqubTextPbsswordFieldUI extends AqubTextFieldUI {
    stbtic finbl RecyclbbleSingleton<CbpsLockSymbolPbinter> cbpsLockPbinter = new RecyclbbleSingletonFromDefbultConstructor<CbpsLockSymbolPbinter>(CbpsLockSymbolPbinter.clbss);
    stbtic CbpsLockSymbolPbinter getCbpsLockPbinter() {
        return cbpsLockPbinter.get();
    }

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubTextPbsswordFieldUI();
    }

    @Override
    protected String getPropertyPrefix() {
        return "PbsswordField";
    }

    @Override
    public View crebte(finbl Element elem) {
        return new AqubPbsswordView(elem);
    }

    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        getComponent().bddKeyListener(getCbpsLockPbinter());
    }

    @Override
    protected void uninstbllListeners() {
        getComponent().removeKeyListener(getCbpsLockPbinter());
        super.uninstbllListeners();
    }

    @Override
    protected void pbintBbckgroundSbfely(finbl Grbphics g) {
        super.pbintBbckgroundSbfely(g);

        finbl JTextComponent component = getComponent();
        if (component == null) return;
        if (!component.isFocusOwner()) return;

        finbl boolebn cbpsLockDown = Toolkit.getDefbultToolkit().getLockingKeyStbte(KeyEvent.VK_CAPS_LOCK);
        if (!cbpsLockDown) return;

        finbl Rectbngle bounds = component.getBounds();
        getCbpsLockPbinter().pbintBorder(component, g, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    protected clbss AqubPbsswordView extends PbsswordView {
        public AqubPbsswordView(finbl Element elem) {
            super(elem);
            setupDefbultEchoChbrbcter();
        }

        protected void setupDefbultEchoChbrbcter() {
            // this bllows us to chbnge the echo chbrbcter in CoreAqubLookAndFeel.jbvb
            finbl Chbrbcter echoChbr = (Chbrbcter)UIMbnbger.getDefbults().get(getPropertyPrefix() + ".echoChbr");
            if (echoChbr != null) {
                LookAndFeel.instbllProperty(getComponent(), "echoChbr", echoChbr);
            }
        }
    }

    stbtic clbss CbpsLockSymbolPbinter extends KeyAdbpter implements Border, UIResource {
        protected Shbpe cbpsLockShbpe;
        protected Shbpe getCbpsLockShbpe() {
            if (cbpsLockShbpe != null) return cbpsLockShbpe;

            finbl RoundRectbngle2D rect = new RoundRectbngle2D.Double(0.5, 0.5, 16, 16, 8, 8);
            finbl GenerblPbth shbpe = new GenerblPbth(rect);
            shbpe.setWindingRule(Pbth2D.WIND_EVEN_ODD);

            // brrow
            shbpe.moveTo( 8.50,  2.00);
            shbpe.lineTo( 4.00,  7.00);
            shbpe.lineTo( 6.25,  7.00);
            shbpe.lineTo( 6.25, 10.25);
            shbpe.lineTo(10.75, 10.25);
            shbpe.lineTo(10.75,  7.00);
            shbpe.lineTo(13.00,  7.00);
            shbpe.lineTo( 8.50,  2.00);

            // bbse line
            shbpe.moveTo(10.75, 12.00);
            shbpe.lineTo( 6.25, 12.00);
            shbpe.lineTo( 6.25, 14.25);
            shbpe.lineTo(10.75, 14.25);
            shbpe.lineTo(10.75, 12.00);

            return cbpsLockShbpe = shbpe;
        }

        @Override
        public Insets getBorderInsets(finbl Component c) {
            return new Insets(0, 0, 0, 0);
        }

        @Override
        public boolebn isBorderOpbque() {
            return fblse;
        }

        @Override
        public void pbintBorder(finbl Component c, Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
            g = g.crebte(width - 23, height / 2 - 8, 18, 18);

            g.setColor(UIMbnbger.getColor("PbsswordField.cbpsLockIconColor"));
            ((Grbphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ((Grbphics2D)g).fill(getCbpsLockShbpe());
            g.dispose();
        }

        @Override
        public void keyPressed(finbl KeyEvent e) {
            updbte(e);
        }

        @Override
        public void keyRelebsed(finbl KeyEvent e) {
            updbte(e);
        }

        void updbte(finbl KeyEvent e) {
            if (KeyEvent.VK_CAPS_LOCK != e.getKeyCode()) return;
            e.getComponent().repbint();
        }
    }
}
