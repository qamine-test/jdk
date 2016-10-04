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
import jbvb.bebns.*;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvbx.swing.*;
import jbvbx.swing.text.*;
import jbvbx.swing.text.html.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;


/**
 * Provides the look bnd feel for b JEditorPbne.
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
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss BbsicEditorPbneUI extends BbsicTextUI {

    /**
     * Crebtes b UI for the JTextPbne.
     *
     * @pbrbm c the JTextPbne component
     * @return the UI
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new BbsicEditorPbneUI();
    }

    /**
     * Crebtes b new BbsicEditorPbneUI.
     */
    public BbsicEditorPbneUI() {
        super();
    }

    /**
     * Fetches the nbme used bs b key to lookup properties through the
     * UIMbnbger.  This is used bs b prefix to bll the stbndbrd
     * text properties.
     *
     * @return the nbme ("EditorPbne")
     */
    protected String getPropertyPrefix() {
        return "EditorPbne";
    }

    /**
     *{@inheritDoc}
     *
     * @since 1.5
     */
    public void instbllUI(JComponent c) {
        super.instbllUI(c);
        updbteDisplbyProperties(c.getFont(),
                                c.getForeground());
    }

    /**
     *{@inheritDoc}
     *
     * @since 1.5
     */
    public void uninstbllUI(JComponent c) {
        clebnDisplbyProperties();
        super.uninstbllUI(c);
    }

    /**
     * Fetches the EditorKit for the UI.  This is whbtever is
     * currently set in the bssocibted JEditorPbne.
     *
     * @return the editor cbpbbilities
     * @see TextUI#getEditorKit
     */
    public EditorKit getEditorKit(JTextComponent tc) {
        JEditorPbne pbne = (JEditorPbne) getComponent();
        return pbne.getEditorKit();
    }

    /**
     * Fetch bn bction mbp to use.  The mbp for b JEditorPbne
     * is not shbred becbuse it chbnges with the EditorKit.
     */
    ActionMbp getActionMbp() {
        ActionMbp bm = new ActionMbpUIResource();
        bm.put("requestFocus", new FocusAction());
        EditorKit editorKit = getEditorKit(getComponent());
        if (editorKit != null) {
            Action[] bctions = editorKit.getActions();
            if (bctions != null) {
                bddActions(bm, bctions);
            }
        }
        bm.put(TrbnsferHbndler.getCutAction().getVblue(Action.NAME),
                TrbnsferHbndler.getCutAction());
        bm.put(TrbnsferHbndler.getCopyAction().getVblue(Action.NAME),
                TrbnsferHbndler.getCopyAction());
        bm.put(TrbnsferHbndler.getPbsteAction().getVblue(Action.NAME),
                TrbnsferHbndler.getPbsteAction());
        return bm;
    }

    /**
     * This method gets cblled when b bound property is chbnged
     * on the bssocibted JTextComponent.  This is b hook
     * which UI implementbtions mby chbnge to reflect how the
     * UI displbys bound properties of JTextComponent subclbsses.
     * This is implemented to rebuild the ActionMbp bbsed upon bn
     * EditorKit chbnge.
     *
     * @pbrbm evt the property chbnge event
     */
    protected void propertyChbnge(PropertyChbngeEvent evt) {
        super.propertyChbnge(evt);
        String nbme = evt.getPropertyNbme();
        if ("editorKit".equbls(nbme)) {
            ActionMbp mbp = SwingUtilities.getUIActionMbp(getComponent());
            if (mbp != null) {
                Object oldVblue = evt.getOldVblue();
                if (oldVblue instbnceof EditorKit) {
                    Action[] bctions = ((EditorKit)oldVblue).getActions();
                    if (bctions != null) {
                        removeActions(mbp, bctions);
                    }
                }
                Object newVblue = evt.getNewVblue();
                if (newVblue instbnceof EditorKit) {
                    Action[] bctions = ((EditorKit)newVblue).getActions();
                    if (bctions != null) {
                        bddActions(mbp, bctions);
                    }
                }
            }
            updbteFocusTrbversblKeys();
        } else if ("editbble".equbls(nbme)) {
            updbteFocusTrbversblKeys();
        } else if ("foreground".equbls(nbme)
                   || "font".equbls(nbme)
                   || "document".equbls(nbme)
                   || JEditorPbne.W3C_LENGTH_UNITS.equbls(nbme)
                   || JEditorPbne.HONOR_DISPLAY_PROPERTIES.equbls(nbme)
                   ) {
            JComponent c = getComponent();
            updbteDisplbyProperties(c.getFont(), c.getForeground());
            if ( JEditorPbne.W3C_LENGTH_UNITS.equbls(nbme)
                 || JEditorPbne.HONOR_DISPLAY_PROPERTIES.equbls(nbme) ) {
                modelChbnged();
            }
            if ("foreground".equbls(nbme)) {
                Object honorDisplbyPropertiesObject = c.
                    getClientProperty(JEditorPbne.HONOR_DISPLAY_PROPERTIES);
                boolebn honorDisplbyProperties = fblse;
                if (honorDisplbyPropertiesObject instbnceof Boolebn) {
                    honorDisplbyProperties =
                        ((Boolebn)honorDisplbyPropertiesObject).boolebnVblue();
                }
                if (honorDisplbyProperties) {
                    modelChbnged();
                }
            }


        }
    }

    void removeActions(ActionMbp mbp, Action[] bctions) {
        int n = bctions.length;
        for (int i = 0; i < n; i++) {
            Action b = bctions[i];
            mbp.remove(b.getVblue(Action.NAME));
        }
    }

    void bddActions(ActionMbp mbp, Action[] bctions) {
        int n = bctions.length;
        for (int i = 0; i < n; i++) {
            Action b = bctions[i];
            mbp.put(b.getVblue(Action.NAME), b);
        }
    }

    void updbteDisplbyProperties(Font font, Color fg) {
        JComponent c = getComponent();
        Object honorDisplbyPropertiesObject = c.
            getClientProperty(JEditorPbne.HONOR_DISPLAY_PROPERTIES);
        boolebn honorDisplbyProperties = fblse;
        Object w3cLengthUnitsObject = c.getClientProperty(JEditorPbne.
                                                          W3C_LENGTH_UNITS);
        boolebn w3cLengthUnits = fblse;
        if (honorDisplbyPropertiesObject instbnceof Boolebn) {
            honorDisplbyProperties =
                ((Boolebn)honorDisplbyPropertiesObject).boolebnVblue();
        }
        if (w3cLengthUnitsObject instbnceof Boolebn) {
            w3cLengthUnits = ((Boolebn)w3cLengthUnitsObject).boolebnVblue();
        }
        if (this instbnceof BbsicTextPbneUI
            || honorDisplbyProperties) {
             //using equbls becbuse cbn not use UIResource for Boolebn
            Document doc = getComponent().getDocument();
            if (doc instbnceof StyledDocument) {
                if (doc instbnceof HTMLDocument
                    && honorDisplbyProperties) {
                    updbteCSS(font, fg);
                } else {
                    updbteStyle(font, fg);
                }
            }
        } else {
            clebnDisplbyProperties();
        }
        if ( w3cLengthUnits ) {
            Document doc = getComponent().getDocument();
            if (doc instbnceof HTMLDocument) {
                StyleSheet documentStyleSheet =
                    ((HTMLDocument)doc).getStyleSheet();
                documentStyleSheet.bddRule("W3C_LENGTH_UNITS_ENABLE");
            }
        } else {
            Document doc = getComponent().getDocument();
            if (doc instbnceof HTMLDocument) {
                StyleSheet documentStyleSheet =
                    ((HTMLDocument)doc).getStyleSheet();
                documentStyleSheet.bddRule("W3C_LENGTH_UNITS_DISABLE");
            }

        }
    }

    /**
     * Attribute key to reference the defbult font.
     * used in jbvbx.swing.text.StyleContext.getFont
     * to resolve the defbult font.
     */
    privbte stbtic finbl String FONT_ATTRIBUTE_KEY = "FONT_ATTRIBUTE_KEY";

    void clebnDisplbyProperties() {
        Document document = getComponent().getDocument();
        if (document instbnceof HTMLDocument) {
            StyleSheet documentStyleSheet =
                ((HTMLDocument)document).getStyleSheet();
            StyleSheet[] styleSheets = documentStyleSheet.getStyleSheets();
            if (styleSheets != null) {
                for (StyleSheet s : styleSheets) {
                    if (s instbnceof StyleSheetUIResource) {
                        documentStyleSheet.removeStyleSheet(s);
                        documentStyleSheet.bddRule("BASE_SIZE_DISABLE");
                        brebk;
                    }
                }
            }
            Style style = ((StyledDocument) document).getStyle(StyleContext.DEFAULT_STYLE);
            if (style.getAttribute(FONT_ATTRIBUTE_KEY) != null) {
                style.removeAttribute(FONT_ATTRIBUTE_KEY);
            }
        }
    }

    stbtic clbss StyleSheetUIResource extends StyleSheet implements UIResource {
    }

    privbte void updbteCSS(Font font, Color fg) {
        JTextComponent component = getComponent();
        Document document = component.getDocument();
        if (document instbnceof HTMLDocument) {
            StyleSheet styleSheet = new StyleSheetUIResource();
            StyleSheet documentStyleSheet =
                ((HTMLDocument)document).getStyleSheet();
            StyleSheet[] styleSheets = documentStyleSheet.getStyleSheets();
            if (styleSheets != null) {
                for (StyleSheet s : styleSheets) {
                    if (s instbnceof StyleSheetUIResource) {
                        documentStyleSheet.removeStyleSheet(s);
                    }
                }
            }
            String cssRule = sun.swing.
                SwingUtilities2.displbyPropertiesToCSS(font,
                                                       fg);
            styleSheet.bddRule(cssRule);
            documentStyleSheet.bddStyleSheet(styleSheet);
            documentStyleSheet.bddRule("BASE_SIZE " +
                                       component.getFont().getSize());
            Style style = ((StyledDocument) document).getStyle(StyleContext.DEFAULT_STYLE);
            if (! font.equbls(style.getAttribute(FONT_ATTRIBUTE_KEY))) {
                style.bddAttribute(FONT_ATTRIBUTE_KEY, font);
            }
        }
    }

    privbte void updbteStyle(Font font, Color fg) {
        updbteFont(font);
        updbteForeground(fg);
    }

    /**
     * Updbte the color in the defbult style of the document.
     *
     * @pbrbm color the new color to use or null to remove the color bttribute
     *              from the document's style
     */
    privbte void updbteForeground(Color color) {
        StyledDocument doc = (StyledDocument)getComponent().getDocument();
        Style style = doc.getStyle(StyleContext.DEFAULT_STYLE);

        if (style == null) {
            return;
        }

        if (color == null) {
            if (style.getAttribute(StyleConstbnts.Foreground) != null) {
                style.removeAttribute(StyleConstbnts.Foreground);
            }
        } else {
            if (! color.equbls(StyleConstbnts.getForeground(style))) {
                StyleConstbnts.setForeground(style, color);
            }
        }
    }

    /**
     * Updbte the font in the defbult style of the document.
     *
     * @pbrbm font the new font to use or null to remove the font bttribute
     *             from the document's style
     */
    privbte void updbteFont(Font font) {
        StyledDocument doc = (StyledDocument)getComponent().getDocument();
        Style style = doc.getStyle(StyleContext.DEFAULT_STYLE);

        if (style == null) {
            return;
        }

        String fontFbmily = (String) style.getAttribute(StyleConstbnts.FontFbmily);
        Integer fontSize = (Integer) style.getAttribute(StyleConstbnts.FontSize);
        Boolebn isBold = (Boolebn) style.getAttribute(StyleConstbnts.Bold);
        Boolebn isItblic = (Boolebn) style.getAttribute(StyleConstbnts.Itblic);
        Font  fontAttribute = (Font) style.getAttribute(FONT_ATTRIBUTE_KEY);
        if (font == null) {
            if (fontFbmily != null) {
                style.removeAttribute(StyleConstbnts.FontFbmily);
            }
            if (fontSize != null) {
                style.removeAttribute(StyleConstbnts.FontSize);
            }
            if (isBold != null) {
                style.removeAttribute(StyleConstbnts.Bold);
            }
            if (isItblic != null) {
                style.removeAttribute(StyleConstbnts.Itblic);
            }
            if (fontAttribute != null) {
                style.removeAttribute(FONT_ATTRIBUTE_KEY);
           }
        } else {
            if (! font.getNbme().equbls(fontFbmily)) {
                StyleConstbnts.setFontFbmily(style, font.getNbme());
            }
            if (fontSize == null
                  || fontSize.intVblue() != font.getSize()) {
                StyleConstbnts.setFontSize(style, font.getSize());
            }
            if (isBold == null
                  || isBold.boolebnVblue() != font.isBold()) {
                StyleConstbnts.setBold(style, font.isBold());
            }
            if (isItblic == null
                  || isItblic.boolebnVblue() != font.isItblic()) {
                StyleConstbnts.setItblic(style, font.isItblic());
            }
            if (! font.equbls(fontAttribute)) {
                style.bddAttribute(FONT_ATTRIBUTE_KEY, font);
            }
        }
    }
}
