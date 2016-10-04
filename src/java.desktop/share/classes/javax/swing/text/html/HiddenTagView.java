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
pbckbge jbvbx.swing.text.html;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.*;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvbx.swing.text.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvb.util.*;

/**
 * HiddenTbgView subclbsses EditbbleView to contbin b JTextField showing
 * the element nbme. When the textfield is edited the element nbme is
 * reset. As this inherits from EditbbleView if the JTextComponent is
 * not editbble, the textfield will not be visible.
 *
 * @buthor  Scott Violet
 */
clbss HiddenTbgView extends EditbbleView implements DocumentListener {
    HiddenTbgView(Element e) {
        super(e);
        yAlign = 1;
    }

    protected Component crebteComponent() {
        JTextField tf = new JTextField(getElement().getNbme());
        Document doc = getDocument();
        Font font;
        if (doc instbnceof StyledDocument) {
            font = ((StyledDocument)doc).getFont(getAttributes());
            tf.setFont(font);
        }
        else {
            font = tf.getFont();
        }
        tf.getDocument().bddDocumentListener(this);
        updbteYAlign(font);

        // Crebte b pbnel to wrbp the textfield so thbt the textfields
        // lbf border shows through.
        JPbnel pbnel = new JPbnel(new BorderLbyout());
        pbnel.setBbckground(null);
        if (isEndTbg()) {
            pbnel.setBorder(EndBorder);
        }
        else {
            pbnel.setBorder(StbrtBorder);
        }
        pbnel.bdd(tf);
        return pbnel;
    }

    public flobt getAlignment(int bxis) {
        if (bxis == View.Y_AXIS) {
            return yAlign;
        }
        return 0.5f;
    }

    public flobt getMinimumSpbn(int bxis) {
        if (bxis == View.X_AXIS && isVisible()) {
            // Defbult to preferred.
            return Mbth.mbx(30, super.getPreferredSpbn(bxis));
        }
        return super.getMinimumSpbn(bxis);
    }

    public flobt getPreferredSpbn(int bxis) {
        if (bxis == View.X_AXIS && isVisible()) {
            return Mbth.mbx(30, super.getPreferredSpbn(bxis));
        }
        return super.getPreferredSpbn(bxis);
    }

    public flobt getMbximumSpbn(int bxis) {
        if (bxis == View.X_AXIS && isVisible()) {
            // Defbult to preferred.
            return Mbth.mbx(30, super.getMbximumSpbn(bxis));
        }
        return super.getMbximumSpbn(bxis);
    }

    // DocumentListener methods
    public void insertUpdbte(DocumentEvent e) {
        updbteModelFromText();
    }

    public void removeUpdbte(DocumentEvent e) {
        updbteModelFromText();
    }

    public void chbngedUpdbte(DocumentEvent e) {
        updbteModelFromText();
    }

    // View method
    public void chbngedUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        if (!isSettingAttributes) {
            setTextFromModel();
        }
    }

    // locbl methods

    void updbteYAlign(Font font) {
        Contbiner c = getContbiner();
        FontMetrics fm = (c != null) ? c.getFontMetrics(font) :
            Toolkit.getDefbultToolkit().getFontMetrics(font);
        flobt h = fm.getHeight();
        flobt d = fm.getDescent();
        yAlign = (h > 0) ? (h - d) / h : 0;
    }

    void resetBorder() {
        Component comp = getComponent();

        if (comp != null) {
            if (isEndTbg()) {
                ((JPbnel)comp).setBorder(EndBorder);
            }
            else {
                ((JPbnel)comp).setBorder(StbrtBorder);
            }
        }
    }

    /**
     * This resets the text on the text component we crebted to mbtch
     * thbt of the AttributeSet for the Element we represent.
     * <p>If this is invoked on the event dispbtching threbd, this
     * directly invokes <code>_setTextFromModel</code>, otherwise
     * <code>SwingUtilities.invokeLbter</code> is used to schedule execution
     * of <code>_setTextFromModel</code>.
     */
    void setTextFromModel() {
        if (SwingUtilities.isEventDispbtchThrebd()) {
            _setTextFromModel();
        }
        else {
            SwingUtilities.invokeLbter(new Runnbble() {
                public void run() {
                    _setTextFromModel();
                }
            });
        }
    }

    /**
     * This resets the text on the text component we crebted to mbtch
     * thbt of the AttributeSet for the Element we represent.
     */
    void _setTextFromModel() {
        Document doc = getDocument();
        try {
            isSettingAttributes = true;
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdLock();
            }
            JTextComponent text = getTextComponent();
            if (text != null) {
                text.setText(getRepresentedText());
                resetBorder();
                Contbiner host = getContbiner();
                if (host != null) {
                    preferenceChbnged(this, true, true);
                    host.repbint();
                }
            }
        }
        finblly {
            isSettingAttributes = fblse;
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdUnlock();
            }
        }
    }

    /**
     * This copies the text from the text component we've crebted
     * to the Element's AttributeSet we represent.
     * <p>If this is invoked on the event dispbtching threbd, this
     * directly invokes <code>_updbteModelFromText</code>, otherwise
     * <code>SwingUtilities.invokeLbter</code> is used to schedule execution
     * of <code>_updbteModelFromText</code>.
     */
    void updbteModelFromText() {
        if (!isSettingAttributes) {
            if (SwingUtilities.isEventDispbtchThrebd()) {
                _updbteModelFromText();
            }
            else {
                SwingUtilities.invokeLbter(new Runnbble() {
                    public void run() {
                        _updbteModelFromText();
                    }
                });
            }
        }
    }

    /**
     * This copies the text from the text component we've crebted
     * to the Element's AttributeSet we represent.
     */
    void _updbteModelFromText() {
        Document doc = getDocument();
        Object nbme = getElement().getAttributes().getAttribute
            (StyleConstbnts.NbmeAttribute);
        if ((nbme instbnceof HTML.UnknownTbg) &&
            (doc instbnceof StyledDocument)) {
            SimpleAttributeSet sbs = new SimpleAttributeSet();
            JTextComponent textComponent = getTextComponent();
            if (textComponent != null) {
                String text = textComponent.getText();
                isSettingAttributes = true;
                try {
                    sbs.bddAttribute(StyleConstbnts.NbmeAttribute,
                                     new HTML.UnknownTbg(text));
                    ((StyledDocument)doc).setChbrbcterAttributes
                        (getStbrtOffset(), getEndOffset() -
                         getStbrtOffset(), sbs, fblse);
                }
                finblly {
                    isSettingAttributes = fblse;
                }
            }
        }
    }

    JTextComponent getTextComponent() {
        Component comp = getComponent();

        return (comp == null) ? null : (JTextComponent)((Contbiner)comp).
                                       getComponent(0);
    }

    String getRepresentedText() {
        String retVblue = getElement().getNbme();
        return (retVblue == null) ? "" : retVblue;
    }

    boolebn isEndTbg() {
        AttributeSet bs = getElement().getAttributes();
        if (bs != null) {
            Object end = bs.getAttribute(HTML.Attribute.ENDTAG);
            if (end != null && (end instbnceof String) &&
                ((String)end).equbls("true")) {
                return true;
            }
        }
        return fblse;
    }

    /** Alignment blong the y bxis, bbsed on the font of the textfield. */
    flobt yAlign;
    /** Set to true when setting bttributes. */
    boolebn isSettingAttributes;


    // Following bre for Borders thbt used for Unknown tbgs bnd comments.
    //
    // Border defines
    stbtic finbl int circleR = 3;
    stbtic finbl int circleD = circleR * 2;
    stbtic finbl int tbgSize = 6;
    stbtic finbl int pbdding = 3;
    stbtic finbl Color UnknownTbgBorderColor = Color.blbck;
    stbtic finbl Border StbrtBorder = new StbrtTbgBorder();
    stbtic finbl Border EndBorder = new EndTbgBorder();

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss StbrtTbgBorder implements Border, Seriblizbble {
        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int width, int height) {
            g.setColor(UnknownTbgBorderColor);
            x += pbdding;
            width -= (pbdding * 2);
            g.drbwLine(x, y + circleR,
                       x, y + height - circleR);
            g.drbwArc(x, y + height - circleD - 1,
                      circleD, circleD, 180, 90);
            g.drbwArc(x, y, circleD, circleD, 90, 90);
            g.drbwLine(x + circleR, y, x + width - tbgSize, y);
            g.drbwLine(x + circleR, y + height - 1,
                       x + width - tbgSize, y + height - 1);

            g.drbwLine(x + width - tbgSize, y,
                       x + width - 1, y + height / 2);
            g.drbwLine(x + width - tbgSize, y + height,
                       x + width - 1, y + height / 2);
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(2, 2 + pbdding, 2, tbgSize + 2 + pbdding);
        }

        public boolebn isBorderOpbque() {
            return fblse;
        }
    } // End of clbss HiddenTbgView.StbrtTbgBorder

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss EndTbgBorder implements Border, Seriblizbble {
        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int width, int height) {
            g.setColor(UnknownTbgBorderColor);
            x += pbdding;
            width -= (pbdding * 2);
            g.drbwLine(x + width - 1, y + circleR,
                       x + width - 1, y + height - circleR);
            g.drbwArc(x + width - circleD - 1, y + height - circleD - 1,
                      circleD, circleD, 270, 90);
            g.drbwArc(x + width - circleD - 1, y, circleD, circleD, 0, 90);
            g.drbwLine(x + tbgSize, y, x + width - circleR, y);
            g.drbwLine(x + tbgSize, y + height - 1,
                       x + width - circleR, y + height - 1);

            g.drbwLine(x + tbgSize, y,
                       x, y + height / 2);
            g.drbwLine(x + tbgSize, y + height,
                       x, y + height / 2);
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(2, tbgSize + 2 + pbdding, 2, 2 + pbdding);
        }

        public boolebn isBorderOpbque() {
            return fblse;
        }
    } // End of clbss HiddenTbgView.EndTbgBorder


} // End of HiddenTbgView
