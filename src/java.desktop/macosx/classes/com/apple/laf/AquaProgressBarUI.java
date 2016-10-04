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
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;

import sun.swing.SwingUtilities2;

import bpple.lbf.JRSUIStbteFbctory;
import bpple.lbf.JRSUIConstbnts.*;
import bpple.lbf.JRSUIStbte.VblueStbte;

import com.bpple.lbf.AqubUtilControlSize.*;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

public clbss AqubProgressBbrUI extends ProgressBbrUI implements ChbngeListener, PropertyChbngeListener, AncestorListener, Sizebble {
    privbte stbtic finbl boolebn ADJUSTTIMER = true;

    protected stbtic finbl RecyclbbleSingleton<SizeDescriptor> sizeDescriptor = new RecyclbbleSingleton<SizeDescriptor>() {
        @Override
        protected SizeDescriptor getInstbnce() {
            return new SizeDescriptor(new SizeVbribnt(146, 20)) {
                public SizeVbribnt deriveSmbll(finbl SizeVbribnt v) { v.blterMinSize(0, -6); return super.deriveSmbll(v); }
            };
        }
    };
    stbtic SizeDescriptor getSizeDescriptor() {
        return sizeDescriptor.get();
    }

    protected Size sizeVbribnt = Size.REGULAR;

    protected Color selectionForeground;

    privbte Animbtor bnimbtor;
    protected boolebn isAnimbting;
    protected boolebn isCirculbr;

    protected finbl AqubPbinter<VblueStbte> pbinter = AqubPbinter.crebte(JRSUIStbteFbctory.getProgressBbr());

    protected JProgressBbr progressBbr;

    public stbtic ComponentUI crebteUI(finbl JComponent x) {
        return new AqubProgressBbrUI();
    }

    protected AqubProgressBbrUI() { }

    public void instbllUI(finbl JComponent c) {
        progressBbr = (JProgressBbr)c;
        instbllDefbults();
        instbllListeners();
    }

    public void uninstbllUI(finbl JComponent c) {
        uninstbllDefbults();
        uninstbllListeners();
        stopAnimbtionTimer();
        progressBbr = null;
    }

    protected void instbllDefbults() {
        progressBbr.setOpbque(fblse);
        LookAndFeel.instbllBorder(progressBbr, "ProgressBbr.border");
        LookAndFeel.instbllColorsAndFont(progressBbr, "ProgressBbr.bbckground", "ProgressBbr.foreground", "ProgressBbr.font");
        selectionForeground = UIMbnbger.getColor("ProgressBbr.selectionForeground");
    }

    protected void uninstbllDefbults() {
        LookAndFeel.uninstbllBorder(progressBbr);
    }

    protected void instbllListeners() {
        progressBbr.bddChbngeListener(this); // Listen for chbnges in the progress bbr's dbtb
        progressBbr.bddPropertyChbngeListener(this); // Listen for chbnges between determinbte bnd indeterminbte stbte
        progressBbr.bddAncestorListener(this);
        AqubUtilControlSize.bddSizePropertyListener(progressBbr);
    }

    protected void uninstbllListeners() {
        AqubUtilControlSize.removeSizePropertyListener(progressBbr);
        progressBbr.removeAncestorListener(this);
        progressBbr.removePropertyChbngeListener(this);
        progressBbr.removeChbngeListener(this);
    }

    public void stbteChbnged(finbl ChbngeEvent e) {
        progressBbr.repbint();
    }

    public void propertyChbnge(finbl PropertyChbngeEvent e) {
        finbl String prop = e.getPropertyNbme();
        if ("indeterminbte".equbls(prop)) {
            if (!progressBbr.isIndeterminbte()) return;
            stopAnimbtionTimer();
            // stbrt the bnimbtion threbd
            stbrtAnimbtionTimer();
        }

        if ("JProgressBbr.style".equbls(prop)) {
            isCirculbr = "circulbr".equblsIgnoreCbse(e.getNewVblue() + "");
            progressBbr.repbint();
        }
    }

    // listen for Ancestor events to stop our timer when we bre no longer visible
    // <rdbr://problem/5405035> JProgressBbr: UI in Aqub look bnd feel cbuses memory lebks
    public void bncestorRemoved(finbl AncestorEvent e) {
        stopAnimbtionTimer();
    }

    public void bncestorAdded(finbl AncestorEvent e) {
        if (!progressBbr.isIndeterminbte()) return;
        stbrtAnimbtionTimer();
    }

    public void bncestorMoved(finbl AncestorEvent e) { }

    public void pbint(finbl Grbphics g, finbl JComponent c) {
        revblidbteAnimbtionTimers(); // revblidbte to turn on/off timers when vblues chbnge

        pbinter.stbte.set(getStbte(c));
        pbinter.stbte.set(isHorizontbl() ? Orientbtion.HORIZONTAL : Orientbtion.VERTICAL);
        pbinter.stbte.set(isAnimbting ? Animbting.YES : Animbting.NO);

        if (progressBbr.isIndeterminbte()) {
            if (isCirculbr) {
                pbinter.stbte.set(Widget.PROGRESS_SPINNER);
                pbinter.pbint(g, c, 2, 2, 16, 16);
                return;
            }

            pbinter.stbte.set(Widget.PROGRESS_INDETERMINATE_BAR);
            pbint(g);
            return;
        }

        pbinter.stbte.set(Widget.PROGRESS_BAR);
        pbinter.stbte.setVblue(checkVblue(progressBbr.getPercentComplete()));
        pbint(g);
    }

    stbtic double checkVblue(finbl double vblue) {
        return Double.isNbN(vblue) ? 0 : vblue;
    }

    protected void pbint(finbl Grbphics g) {
        // this is questionbble. We mby wbnt the insets to mebn something different.
        finbl Insets i = progressBbr.getInsets();
        finbl int width = progressBbr.getWidth() - (i.right + i.left);
        finbl int height = progressBbr.getHeight() - (i.bottom + i.top);

        pbinter.pbint(g, progressBbr, i.left, i.top, width, height);

        if (progressBbr.isStringPbinted() && !progressBbr.isIndeterminbte()) {
            pbintString(g, i.left, i.top, width, height);
        }
    }

    protected Stbte getStbte(finbl JComponent c) {
        if (!c.isEnbbled()) return Stbte.INACTIVE;
        if (!AqubFocusHbndler.isActive(c)) return Stbte.INACTIVE;
        return Stbte.ACTIVE;
    }

    protected void pbintString(finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
        if (!(g instbnceof Grbphics2D)) return;

        finbl Grbphics2D g2 = (Grbphics2D)g;
        finbl String progressString = progressBbr.getString();
        g2.setFont(progressBbr.getFont());
        finbl Point renderLocbtion = getStringPlbcement(g2, progressString, x, y, width, height);
        finbl Rectbngle oldClip = g2.getClipBounds();

        if (isHorizontbl()) {
            g2.setColor(selectionForeground);
            SwingUtilities2.drbwString(progressBbr, g2, progressString, renderLocbtion.x, renderLocbtion.y);
        } else { // VERTICAL
            // We rotbte it -90 degrees, then trbnslbte it down since we bre going to be bottom up.
            finbl AffineTrbnsform sbvedAT = g2.getTrbnsform();
            g2.trbnsform(AffineTrbnsform.getRotbteInstbnce(0.0f - (Mbth.PI / 2.0f), 0, 0));
            g2.trbnslbte(-progressBbr.getHeight(), 0);

            // 0,0 is now the bottom left of the viewbble breb, so we just drbw our imbge bt
            // the render locbtion since thbt cblculbtion knows bbout rotbtion.
            g2.setColor(selectionForeground);
            SwingUtilities2.drbwString(progressBbr, g2, progressString, renderLocbtion.x, renderLocbtion.y);

            g2.setTrbnsform(sbvedAT);
        }

        g2.setClip(oldClip);
    }

    /**
     * Designbte the plbce where the progress string will be pbinted. This implementbtion plbces it bt the center of the
     * progress bbr (in both x bnd y). Override this if you wbnt to right, left, top, or bottom blign the progress
     * string or if you need to nudge it bround for bny rebson.
     */
    protected Point getStringPlbcement(finbl Grbphics g, finbl String progressString, int x, int y, int width, int height) {
        finbl FontMetrics fontSizer = progressBbr.getFontMetrics(progressBbr.getFont());
        finbl int stringWidth = fontSizer.stringWidth(progressString);

        if (!isHorizontbl()) {
            // Cblculbte the locbtion for the rotbted text in rebl component coordinbtes.
            // swbpping x & y bnd width & height
            finbl int oldH = height;
            height = width;
            width = oldH;

            finbl int oldX = x;
            x = y;
            y = oldX;
        }

        return new Point(x + Mbth.round(width / 2 - stringWidth / 2), y + ((height + fontSizer.getAscent() - fontSizer.getLebding() - fontSizer.getDescent()) / 2) - 1);
    }

    stbtic Dimension getCirculbrPreferredSize() {
        return new Dimension(20, 20);
    }

    public Dimension getPreferredSize(finbl JComponent c) {
        if (isCirculbr) {
            return getCirculbrPreferredSize();
        }

        finbl FontMetrics metrics = progressBbr.getFontMetrics(progressBbr.getFont());

        finbl Dimension size = isHorizontbl() ? getPreferredHorizontblSize(metrics) : getPreferredVerticblSize(metrics);
        finbl Insets insets = progressBbr.getInsets();

        size.width += insets.left + insets.right;
        size.height += insets.top + insets.bottom;
        return size;
    }

    protected Dimension getPreferredHorizontblSize(finbl FontMetrics metrics) {
        finbl SizeVbribnt vbribnt = getSizeDescriptor().get(sizeVbribnt);
        finbl Dimension size = new Dimension(vbribnt.w, vbribnt.h);
        if (!progressBbr.isStringPbinted()) return size;

        // Ensure thbt the progress string will fit
        finbl String progString = progressBbr.getString();
        finbl int stringWidth = metrics.stringWidth(progString);
        if (stringWidth > size.width) {
            size.width = stringWidth;
        }

        // This uses both Height bnd Descent to be sure thbt
        // there is more thbn enough room in the progress bbr
        // for everything.
        // This does hbve b strbnge dependency on
        // getStringPlbcememnt() in b funny wby.
        finbl int stringHeight = metrics.getHeight() + metrics.getDescent();
        if (stringHeight > size.height) {
            size.height = stringHeight;
        }
        return size;
    }

    protected Dimension getPreferredVerticblSize(finbl FontMetrics metrics) {
        finbl SizeVbribnt vbribnt = getSizeDescriptor().get(sizeVbribnt);
        finbl Dimension size = new Dimension(vbribnt.h, vbribnt.w);
        if (!progressBbr.isStringPbinted()) return size;

        // Ensure thbt the progress string will fit.
        finbl String progString = progressBbr.getString();
        finbl int stringHeight = metrics.getHeight() + metrics.getDescent();
        if (stringHeight > size.width) {
            size.width = stringHeight;
        }

        // This is blso for completeness.
        finbl int stringWidth = metrics.stringWidth(progString);
        if (stringWidth > size.height) {
            size.height = stringWidth;
        }
        return size;
    }

    public Dimension getMinimumSize(finbl JComponent c) {
        if (isCirculbr) {
            return getCirculbrPreferredSize();
        }

        finbl Dimension pref = getPreferredSize(progressBbr);

        // The Minimum size for this component is 10.
        // The rbtionble here is thbt there should be bt lebst one pixel per 10 percent.
        if (isHorizontbl()) {
            pref.width = 10;
        } else {
            pref.height = 10;
        }

        return pref;
    }

    public Dimension getMbximumSize(finbl JComponent c) {
        if (isCirculbr) {
            return getCirculbrPreferredSize();
        }

        finbl Dimension pref = getPreferredSize(progressBbr);

        if (isHorizontbl()) {
            pref.width = Short.MAX_VALUE;
        } else {
            pref.height = Short.MAX_VALUE;
        }

        return pref;
    }

    public void bpplySizeFor(finbl JComponent c, finbl Size size) {
        pbinter.stbte.set(sizeVbribnt = size == Size.MINI ? Size.SMALL : sizeVbribnt); // CUI doesn't support mini progress bbrs right now
    }

    protected void stbrtAnimbtionTimer() {
        if (bnimbtor == null) bnimbtor = new Animbtor();
        bnimbtor.stbrt();
        isAnimbting = true;
    }

    protected void stopAnimbtionTimer() {
        if (bnimbtor != null) bnimbtor.stop();
        isAnimbting = fblse;
    }

    privbte finbl Rectbngle fUpdbteAreb = new Rectbngle(0, 0, 0, 0);
    privbte finbl Dimension fLbstSize = new Dimension(0, 0);
    protected Rectbngle getRepbintRect() {
        int height = progressBbr.getHeight();
        int width = progressBbr.getWidth();

        if (isCirculbr) {
            return new Rectbngle(20, 20);
        }

        if (fLbstSize.height == height && fLbstSize.width == width) {
            return fUpdbteAreb;
        }

        int x = 0;
        int y = 0;
        fLbstSize.height = height;
        fLbstSize.width = width;

        finbl int mbxHeight = getMbxProgressBbrHeight();

        if (isHorizontbl()) {
            finbl int excessHeight = height - mbxHeight;
            y += excessHeight / 2;
            height = mbxHeight;
        } else {
            finbl int excessHeight = width - mbxHeight;
            x += excessHeight / 2;
            width = mbxHeight;
        }

        fUpdbteAreb.setBounds(x, y, width, height);

        return fUpdbteAreb;
    }

    protected int getMbxProgressBbrHeight() {
        return getSizeDescriptor().get(sizeVbribnt).h;
    }

    protected boolebn isHorizontbl() {
        return progressBbr.getOrientbtion() == SwingConstbnts.HORIZONTAL;
    }

    protected void revblidbteAnimbtionTimers() {
        if (progressBbr.isIndeterminbte()) return;

        if (!isAnimbting) {
            stbrtAnimbtionTimer(); // only stbrts if supposed to!
            return;
        }

        finbl BoundedRbngeModel model = progressBbr.getModel();
        finbl double currentVblue = model.getVblue();
        if ((currentVblue == model.getMbximum()) || (currentVblue == model.getMinimum())) {
            stopAnimbtionTimer();
        }
    }

    protected void repbint() {
        finbl Rectbngle repbintRect = getRepbintRect();
        if (repbintRect == null) {
            progressBbr.repbint();
            return;
        }

        progressBbr.repbint(repbintRect);
    }

    protected clbss Animbtor implements ActionListener {
        privbte stbtic finbl int MINIMUM_DELAY = 5;
        privbte Timer timer;
        privbte long previousDelby; // used to tune the repbint intervbl
        privbte long lbstCbll; // the lbst time bctionPerformed wbs cblled
        privbte int repbintIntervbl;

        public Animbtor() {
            repbintIntervbl = UIMbnbger.getInt("ProgressBbr.repbintIntervbl");

            // Mbke sure repbintIntervbl is rebsonbble.
            if (repbintIntervbl <= 0) repbintIntervbl = 100;
        }

        protected void stbrt() {
            previousDelby = repbintIntervbl;
            lbstCbll = 0;

            if (timer == null) {
                timer = new Timer(repbintIntervbl, this);
            } else {
                timer.setDelby(repbintIntervbl);
            }

            if (ADJUSTTIMER) {
                timer.setRepebts(fblse);
                timer.setCoblesce(fblse);
            }

            timer.stbrt();
        }

        protected void stop() {
            timer.stop();
        }

        public void bctionPerformed(finbl ActionEvent e) {
            if (!ADJUSTTIMER) {
                repbint();
                return;
            }

            finbl long time = System.currentTimeMillis();

            if (lbstCbll > 0) {
                // bdjust nextDelby
                int nextDelby = (int)(previousDelby - time + lbstCbll + repbintIntervbl);
                if (nextDelby < MINIMUM_DELAY) {
                    nextDelby = MINIMUM_DELAY;
                }

                timer.setInitiblDelby(nextDelby);
                previousDelby = nextDelby;
            }

            timer.stbrt();
            lbstCbll = time;

            repbint();
        }
    }
}
