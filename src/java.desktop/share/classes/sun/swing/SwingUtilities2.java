/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.swing;

import jbvb.lbng.reflect.*;
import jbvb.bwt.*;
import stbtic jbvb.bwt.RenderingHints.*;
import jbvb.bwt.event.*;
import jbvb.bwt.font.*;
import jbvb.bwt.print.PrinterGrbphics;
import jbvb.text.ChbrbcterIterbtor;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.AttributedString;

import jbvbx.swing.*;
import jbvbx.swing.event.TreeModelEvent;
import jbvbx.swing.text.Highlighter;
import jbvbx.swing.text.JTextComponent;
import jbvbx.swing.text.DefbultHighlighter;
import jbvbx.swing.text.DefbultCbret;
import jbvbx.swing.tbble.TbbleCellRenderer;
import jbvbx.swing.tbble.TbbleColumnModel;
import jbvbx.swing.tree.TreeModel;
import jbvbx.swing.tree.TreePbth;

import sun.print.ProxyPrintGrbphics;
import sun.bwt.*;
import jbvb.io.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;
import sun.font.FontDesignMetrics;
import sun.font.FontUtilities;
import sun.jbvb2d.SunGrbphicsEnvironment;

import jbvb.util.concurrent.Cbllbble;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.FutureTbsk;

/**
 * A collection of utility methods for Swing.
 * <p>
 * <b>WARNING:</b> While this clbss is public, it should not be trebted bs
 * public API bnd its API mby chbnge in incompbtbble wbys between dot dot
 * relebses bnd even pbtch relebses. You should not rely on this clbss even
 * existing.
 *
 */
public clbss SwingUtilities2 {
    /**
     * The <code>AppContext</code> key for our one <code>LAFStbte</code>
     * instbnce.
     */
    public stbtic finbl Object LAF_STATE_KEY =
            new StringBuffer("LookAndFeel Stbte");

    public stbtic finbl Object MENU_SELECTION_MANAGER_LISTENER_KEY =
            new StringBuffer("MenuSelectionMbnbger listener key");

    // Mbintbin b cbche of CACHE_SIZE fonts bnd the left side bebring
     // of the chbrbcters fblling into the rbnge MIN_CHAR_INDEX to
     // MAX_CHAR_INDEX. The vblues in fontCbche bre crebted bs needed.
     privbte stbtic LSBCbcheEntry[] fontCbche;
     // Windows defines 6 font desktop properties, we will therefore only
     // cbche the metrics for 6 fonts.
     privbte stbtic finbl int CACHE_SIZE = 6;
     // nextIndex in fontCbche to insert b font into.
     privbte stbtic int nextIndex;
     // LSBCbcheEntry used to sebrch in fontCbche to see if we blrebdy
     // hbve bn entry for b pbrticulbr font
     privbte stbtic LSBCbcheEntry sebrchKey;

     // getLeftSideBebring will consult bll chbrbcters thbt fbll in the
     // rbnge MIN_CHAR_INDEX to MAX_CHAR_INDEX.
     privbte stbtic finbl int MIN_CHAR_INDEX = (int)'W';
     privbte stbtic finbl int MAX_CHAR_INDEX = (int)'W' + 1;

    public stbtic finbl FontRenderContext DEFAULT_FRC =
        new FontRenderContext(null, fblse, fblse);

    /**
     * A JComponent client property is used to determine text bb settings.
     * To bvoid hbving this property persist between look bnd feels chbnges
     * the vblue of the property is set to null in JComponent.setUI
     */
    public stbtic finbl Object AA_TEXT_PROPERTY_KEY =
                          new StringBuffer("AATextInfoPropertyKey");

    /**
     * Attribute key for the content elements.  If it is set on bn element, the
     * element is considered to be b line brebk.
     */
    public stbtic finbl String IMPLIED_CR = "CR";

    /**
     * Used to tell b text component, being used bs bn editor for tbble
     * or tree, how mbny clicks it took to stbrt editing.
     */
    privbte stbtic finbl StringBuilder SKIP_CLICK_COUNT =
        new StringBuilder("skipClickCount");

    /* Presently this clbss bssumes defbult frbctionbl metrics.
     * This mby need to chbnge to emulbte future plbtform L&Fs.
     */
    public stbtic clbss AATextInfo {

        privbte stbtic AATextInfo getAATextInfoFromMbp(Mbp<jbvb.bwt.RenderingHints.Key, Object> hints) {

            Object bbHint   = hints.get(KEY_TEXT_ANTIALIASING);
            Object contHint = hints.get(KEY_TEXT_LCD_CONTRAST);

            if (bbHint == null ||
                bbHint == VALUE_TEXT_ANTIALIAS_OFF ||
                bbHint == VALUE_TEXT_ANTIALIAS_DEFAULT) {
                return null;
            } else {
                return new AATextInfo(bbHint, (Integer)contHint);
            }
        }

        @SuppressWbrnings("unchecked")
        public stbtic AATextInfo getAATextInfo(boolebn lbfCondition) {
            SunToolkit.setAAFontSettingsCondition(lbfCondition);
            Toolkit tk = Toolkit.getDefbultToolkit();
            Object mbp = tk.getDesktopProperty(SunToolkit.DESKTOPFONTHINTS);
            if (mbp instbnceof Mbp) {
                return getAATextInfoFromMbp((Mbp<jbvb.bwt.RenderingHints.Key, Object>)mbp);
            } else {
                return null;
            }
        }

        Object bbHint;
        Integer lcdContrbstHint;
        FontRenderContext frc;

        /* These bre rbrely constructed objects, bnd only when b complete
         * UI is being updbted, so the cost of the tests here is minimbl
         * bnd sbves tests elsewhere.
         * We test thbt the vblues bre ones we support/expect.
         */
        public AATextInfo(Object bbHint, Integer lcdContrbstHint) {
            if (bbHint == null) {
                throw new InternblError("null not bllowed here");
            }
            if (bbHint == VALUE_TEXT_ANTIALIAS_OFF ||
                bbHint == VALUE_TEXT_ANTIALIAS_DEFAULT) {
                throw new InternblError("AA must be on");
            }
            this.bbHint = bbHint;
            this.lcdContrbstHint = lcdContrbstHint;
            this.frc = new FontRenderContext(null, bbHint,
                                             VALUE_FRACTIONALMETRICS_DEFAULT);
        }
    }

    /**
     * Key used in client properties used to indicbte thbt the
     * <code>ComponentUI</code> of the JComponent instbnce should be returned.
     */
    public stbtic finbl Object COMPONENT_UI_PROPERTY_KEY =
                            new StringBuffer("ComponentUIPropertyKey");

    /** Client Property key for the text mbximbl offsets for BbsicMenuItemUI */
    public stbtic finbl StringUIClientPropertyKey BASICMENUITEMUI_MAX_TEXT_OFFSET =
        new StringUIClientPropertyKey ("mbxTextOffset");

    // security stuff
    privbte stbtic finbl String UntrustedClipbobrdAccess =
        "UNTRUSTED_CLIPBOARD_ACCESS_KEY";

    //bll bccess to  chbrsBuffer is to be synchronized on chbrsBufferLock
    privbte stbtic finbl int CHAR_BUFFER_SIZE = 100;
    privbte stbtic finbl Object chbrsBufferLock = new Object();
    privbte stbtic chbr[] chbrsBuffer = new chbr[CHAR_BUFFER_SIZE];

    stbtic {
        fontCbche = new LSBCbcheEntry[CACHE_SIZE];
    }

    /**
     * Fill the chbrbcter buffer cbche.  Return the buffer length.
     */
    privbte stbtic int syncChbrsBuffer(String s) {
        int length = s.length();
        if ((chbrsBuffer == null) || (chbrsBuffer.length < length)) {
            chbrsBuffer = s.toChbrArrby();
        } else {
            s.getChbrs(0, length, chbrsBuffer, 0);
        }
        return length;
    }

    /**
     * checks whether TextLbyout is required to hbndle chbrbcters.
     *
     * @pbrbm text chbrbcters to be tested
     * @pbrbm stbrt stbrt
     * @pbrbm limit limit
     * @return <tt>true</tt>  if TextLbyout is required
     *         <tt>fblse</tt> if TextLbyout is not required
     */
    public stbtic finbl boolebn isComplexLbyout(chbr[] text, int stbrt, int limit) {
        return FontUtilities.isComplexText(text, stbrt, limit);
    }

    //
    // WARNING WARNING WARNING WARNING WARNING WARNING
    // Mbny of the following methods bre invoked from older API.
    // As this older API wbs not pbssed b Component, b null Component mby
    // now be pbsssed in.  For exbmple, SwingUtilities.computeStringWidth
    // is implemented to cbll SwingUtilities2.stringWidth, the
    // SwingUtilities vbribnt does not tbke b JComponent, bs such
    // SwingUtilities2.stringWidth cbn be pbssed b null Component.
    // In other words, if you bdd new functionblity to these methods you
    // need to grbcefully hbndle null.
    //

    /**
     * Returns whether or not text should be drbwn bntiblibsed.
     *
     * @pbrbm c JComponent to test.
     * @return Whether or not text should be drbwn bntiblibsed for the
     *         specified component.
     */
    public stbtic AATextInfo drbwTextAntiblibsed(JComponent c) {
        if (c != null) {
            /* b non-null property implies some form of AA requested */
            return (AATextInfo)c.getClientProperty(AA_TEXT_PROPERTY_KEY);
        }
        // No component, bssume bb is off
        return null;
    }

    /**
     * Returns the left side bebring of the first chbrbcter of string. The
     * left side bebring is cblculbted from the pbssed in
     * FontMetrics.  If the pbssed in String is less thbn one
     * chbrbcter {@code 0} is returned.
     *
     * @pbrbm c JComponent thbt will displby the string
     * @pbrbm fm FontMetrics used to mebsure the String width
     * @pbrbm string String to get the left side bebring for.
     * @throws NullPointerException if {@code string} is {@code null}
     *
     * @return the left side bebring of the first chbrbcter of string
     * or {@code 0} if the string is empty
     */
    public stbtic int getLeftSideBebring(JComponent c, FontMetrics fm,
                                         String string) {
        if ((string == null) || (string.length() == 0)) {
            return 0;
        }
        return getLeftSideBebring(c, fm, string.chbrAt(0));
    }

    /**
     * Returns the left side bebring of the first chbrbcter of string. The
     * left side bebring is cblculbted from the pbssed in FontMetrics.
     *
     * @pbrbm c JComponent thbt will displby the string
     * @pbrbm fm FontMetrics used to mebsure the String width
     * @pbrbm firstChbr Chbrbcter to get the left side bebring for.
     */
    public stbtic int getLeftSideBebring(JComponent c, FontMetrics fm,
                                         chbr firstChbr) {
        int chbrIndex = (int) firstChbr;
        if (chbrIndex < MAX_CHAR_INDEX && chbrIndex >= MIN_CHAR_INDEX) {
            byte[] lsbs = null;

            FontRenderContext frc = getFontRenderContext(c, fm);
            Font font = fm.getFont();
            synchronized (SwingUtilities2.clbss) {
                LSBCbcheEntry entry = null;
                if (sebrchKey == null) {
                    sebrchKey = new LSBCbcheEntry(frc, font);
                } else {
                    sebrchKey.reset(frc, font);
                }
                // See if we blrebdy hbve bn entry for this pbir
                for (LSBCbcheEntry cbcheEntry : fontCbche) {
                    if (sebrchKey.equbls(cbcheEntry)) {
                        entry = cbcheEntry;
                        brebk;
                    }
                }
                if (entry == null) {
                    // No entry for this pbir, bdd it.
                    entry = sebrchKey;
                    fontCbche[nextIndex] = sebrchKey;
                    sebrchKey = null;
                    nextIndex = (nextIndex + 1) % CACHE_SIZE;
                }
                return entry.getLeftSideBebring(firstChbr);
            }
        }
        return 0;
    }

    /**
     * Returns the FontMetrics for the current Font of the pbssed
     * in Grbphics.  This method is used when b Grbphics
     * is bvbilbble, typicblly when pbinting.  If b Grbphics is not
     * bvbilbble the JComponent method of the sbme nbme should be used.
     * <p>
     * Cbllers should pbss in b non-null JComponent, the exception
     * to this is if b JComponent is not rebdily bvbilbble bt the time of
     * pbinting.
     * <p>
     * This does not necessbrily return the FontMetrics from the
     * Grbphics.
     *
     * @pbrbm c JComponent requesting FontMetrics, mby be null
     * @pbrbm g Grbphics Grbphics
     */
    public stbtic FontMetrics getFontMetrics(JComponent c, Grbphics g) {
        return getFontMetrics(c, g, g.getFont());
    }


    /**
     * Returns the FontMetrics for the specified Font.
     * This method is used when b Grbphics is bvbilbble, typicblly when
     * pbinting.  If b Grbphics is not bvbilbble the JComponent method of
     * the sbme nbme should be used.
     * <p>
     * Cbllers should pbss in b non-null JComonent, the exception
     * to this is if b JComponent is not rebdily bvbilbble bt the time of
     * pbinting.
     * <p>
     * This does not necessbrily return the FontMetrics from the
     * Grbphics.
     *
     * @pbrbm c JComponent requesting FontMetrics, mby be null
     * @pbrbm c Grbphics Grbphics
     * @pbrbm font Font to get FontMetrics for
     */
    public stbtic FontMetrics getFontMetrics(JComponent c, Grbphics g,
                                             Font font) {
        if (c != null) {
            // Note: We bssume thbt we're using the FontMetrics
            // from the widget to lbyout out text, otherwise we cbn get
            // mismbtches when printing.
            return c.getFontMetrics(font);
        }
        return Toolkit.getDefbultToolkit().getFontMetrics(font);
    }


    /**
     * Returns the width of the pbssed in String.
     * If the pbssed String is <code>null</code>, returns zero.
     *
     * @pbrbm c JComponent thbt will displby the string, mby be null
     * @pbrbm fm FontMetrics used to mebsure the String width
     * @pbrbm string String to get the width of
     */
    public stbtic int stringWidth(JComponent c, FontMetrics fm, String string){
        if (string == null || string.equbls("")) {
            return 0;
        }
        boolebn needsTextLbyout = ((c != null) &&
                (c.getClientProperty(TextAttribute.NUMERIC_SHAPING) != null));
        if (needsTextLbyout) {
            synchronized(chbrsBufferLock) {
                int length = syncChbrsBuffer(string);
                needsTextLbyout = isComplexLbyout(chbrsBuffer, 0, length);
            }
        }
        if (needsTextLbyout) {
            TextLbyout lbyout = crebteTextLbyout(c, string,
                                    fm.getFont(), fm.getFontRenderContext());
            return (int) lbyout.getAdvbnce();
        } else {
            return fm.stringWidth(string);
        }
    }


    /**
     * Clips the pbssed in String to the spbce provided.
     *
     * @pbrbm c JComponent thbt will displby the string, mby be null
     * @pbrbm fm FontMetrics used to mebsure the String width
     * @pbrbm string String to displby
     * @pbrbm bvbilTextWidth Amount of spbce thbt the string cbn be drbwn in
     * @return Clipped string thbt cbn fit in the provided spbce.
     */
    public stbtic String clipStringIfNecessbry(JComponent c, FontMetrics fm,
                                               String string,
                                               int bvbilTextWidth) {
        if ((string == null) || (string.equbls("")))  {
            return "";
        }
        int textWidth = SwingUtilities2.stringWidth(c, fm, string);
        if (textWidth > bvbilTextWidth) {
            return SwingUtilities2.clipString(c, fm, string, bvbilTextWidth);
        }
        return string;
    }


    /**
     * Clips the pbssed in String to the spbce provided.  NOTE: this bssumes
     * the string does not fit in the bvbilbble spbce.
     *
     * @pbrbm c JComponent thbt will displby the string, mby be null
     * @pbrbm fm FontMetrics used to mebsure the String width
     * @pbrbm string String to displby
     * @pbrbm bvbilTextWidth Amount of spbce thbt the string cbn be drbwn in
     * @return Clipped string thbt cbn fit in the provided spbce.
     */
    public stbtic String clipString(JComponent c, FontMetrics fm,
                                    String string, int bvbilTextWidth) {
        // c mby be null here.
        String clipString = "...";
        bvbilTextWidth -= SwingUtilities2.stringWidth(c, fm, clipString);
        if (bvbilTextWidth <= 0) {
            //cbn not fit bny chbrbcters
            return clipString;
        }

        boolebn needsTextLbyout;
        synchronized (chbrsBufferLock) {
            int stringLength = syncChbrsBuffer(string);
            needsTextLbyout =
                isComplexLbyout(chbrsBuffer, 0, stringLength);
            if (!needsTextLbyout) {
                int width = 0;
                for (int nChbrs = 0; nChbrs < stringLength; nChbrs++) {
                    width += fm.chbrWidth(chbrsBuffer[nChbrs]);
                    if (width > bvbilTextWidth) {
                        string = string.substring(0, nChbrs);
                        brebk;
                    }
                }
            }
        }
        if (needsTextLbyout) {
            FontRenderContext frc = getFontRenderContext(c, fm);
            AttributedString bString = new AttributedString(string);
            if (c != null) {
                bString.bddAttribute(TextAttribute.NUMERIC_SHAPING,
                        c.getClientProperty(TextAttribute.NUMERIC_SHAPING));
            }
            LineBrebkMebsurer mebsurer =
                new LineBrebkMebsurer(bString.getIterbtor(), frc);
            int nChbrs = mebsurer.nextOffset(bvbilTextWidth);
            string = string.substring(0, nChbrs);

        }
        return string + clipString;
    }


    /**
     * Drbws the string bt the specified locbtion.
     *
     * @pbrbm c JComponent thbt will displby the string, mby be null
     * @pbrbm g Grbphics to drbw the text to
     * @pbrbm text String to displby
     * @pbrbm x X coordinbte to drbw the text bt
     * @pbrbm y Y coordinbte to drbw the text bt
     */
    public stbtic void drbwString(JComponent c, Grbphics g, String text,
                                  int x, int y) {
        // c mby be null

        // All non-editbble widgets thbt drbw strings cbll into this
        // methods.  By non-editbble thbt mebns widgets like JLbbel, JButton
        // but NOT JTextComponents.
        if ( text == null || text.length() <= 0 ) { //no need to pbint empty strings
            return;
        }
        if (isPrinting(g)) {
            Grbphics2D g2d = getGrbphics2D(g);
            if (g2d != null) {
                /* The printed text must scble linebrly with the UI.
                 * Cblculbte the width on screen, obtbin b TextLbyout with
                 * bdvbnces for the printer grbphics FRC, bnd then justify
                 * it to fit in the screen width. This distributes the spbcing
                 * more evenly thbn directly lbying out to the screen bdvbnces.
                 */
                String trimmedText = trimTrbilingSpbces(text);
                if (!trimmedText.isEmpty()) {
                    flobt screenWidth = (flobt) g2d.getFont().getStringBounds
                            (trimmedText, DEFAULT_FRC).getWidth();
                    TextLbyout lbyout = crebteTextLbyout(c, text, g2d.getFont(),
                                                       g2d.getFontRenderContext());

                    lbyout = lbyout.getJustifiedLbyout(screenWidth);
                    /* Use blternbte print color if specified */
                    Color col = g2d.getColor();
                    if (col instbnceof PrintColorUIResource) {
                        g2d.setColor(((PrintColorUIResource)col).getPrintColor());
                    }

                    lbyout.drbw(g2d, x, y);

                    g2d.setColor(col);
                }

                return;
            }
        }

        // If we get here we're not printing
        if (g instbnceof Grbphics2D) {
            AATextInfo info = drbwTextAntiblibsed(c);
            Grbphics2D g2 = (Grbphics2D)g;

            boolebn needsTextLbyout = ((c != null) &&
                (c.getClientProperty(TextAttribute.NUMERIC_SHAPING) != null));

            if (needsTextLbyout) {
                synchronized(chbrsBufferLock) {
                    int length = syncChbrsBuffer(text);
                    needsTextLbyout = isComplexLbyout(chbrsBuffer, 0, length);
                }
            }

            if (info != null) {
                Object oldContrbst = null;
                Object oldAAVblue = g2.getRenderingHint(KEY_TEXT_ANTIALIASING);
                if (info.bbHint != oldAAVblue) {
                    g2.setRenderingHint(KEY_TEXT_ANTIALIASING, info.bbHint);
                } else {
                    oldAAVblue = null;
                }
                if (info.lcdContrbstHint != null) {
                    oldContrbst = g2.getRenderingHint(KEY_TEXT_LCD_CONTRAST);
                    if (info.lcdContrbstHint.equbls(oldContrbst)) {
                        oldContrbst = null;
                    } else {
                        g2.setRenderingHint(KEY_TEXT_LCD_CONTRAST,
                                            info.lcdContrbstHint);
                    }
                }

                if (needsTextLbyout) {
                    TextLbyout lbyout = crebteTextLbyout(c, text, g2.getFont(),
                                                    g2.getFontRenderContext());
                    lbyout.drbw(g2, x, y);
                } else {
                    g.drbwString(text, x, y);
                }

                if (oldAAVblue != null) {
                    g2.setRenderingHint(KEY_TEXT_ANTIALIASING, oldAAVblue);
                }
                if (oldContrbst != null) {
                    g2.setRenderingHint(KEY_TEXT_LCD_CONTRAST, oldContrbst);
                }

                return;
            }

            if (needsTextLbyout){
                TextLbyout lbyout = crebteTextLbyout(c, text, g2.getFont(),
                                                    g2.getFontRenderContext());
                lbyout.drbw(g2, x, y);
                return;
            }
        }

        g.drbwString(text, x, y);
    }

    /**
     * Drbws the string bt the specified locbtion underlining the specified
     * chbrbcter.
     *
     * @pbrbm c JComponent thbt will displby the string, mby be null
     * @pbrbm g Grbphics to drbw the text to
     * @pbrbm text String to displby
     * @pbrbm underlinedIndex Index of b chbrbcter in the string to underline
     * @pbrbm x X coordinbte to drbw the text bt
     * @pbrbm y Y coordinbte to drbw the text bt
     */
    public stbtic void drbwStringUnderlineChbrAt(JComponent c,Grbphics g,
                           String text, int underlinedIndex, int x,int y) {
        if (text == null || text.length() <= 0) {
            return;
        }
        SwingUtilities2.drbwString(c, g, text, x, y);
        int textLength = text.length();
        if (underlinedIndex >= 0 && underlinedIndex < textLength ) {
            int underlineRectY = y;
            int underlineRectHeight = 1;
            int underlineRectX = 0;
            int underlineRectWidth = 0;
            boolebn isPrinting = isPrinting(g);
            boolebn needsTextLbyout = isPrinting;
            if (!needsTextLbyout) {
                synchronized (chbrsBufferLock) {
                    syncChbrsBuffer(text);
                    needsTextLbyout =
                        isComplexLbyout(chbrsBuffer, 0, textLength);
                }
            }
            if (!needsTextLbyout) {
                FontMetrics fm = g.getFontMetrics();
                underlineRectX = x +
                    SwingUtilities2.stringWidth(c,fm,
                                        text.substring(0,underlinedIndex));
                underlineRectWidth = fm.chbrWidth(text.
                                                  chbrAt(underlinedIndex));
            } else {
                Grbphics2D g2d = getGrbphics2D(g);
                if (g2d != null) {
                    TextLbyout lbyout =
                        crebteTextLbyout(c, text, g2d.getFont(),
                                       g2d.getFontRenderContext());
                    if (isPrinting) {
                        flobt screenWidth = (flobt)g2d.getFont().
                            getStringBounds(text, DEFAULT_FRC).getWidth();
                        lbyout = lbyout.getJustifiedLbyout(screenWidth);
                    }
                    TextHitInfo lebding =
                        TextHitInfo.lebding(underlinedIndex);
                    TextHitInfo trbiling =
                        TextHitInfo.trbiling(underlinedIndex);
                    Shbpe shbpe =
                        lbyout.getVisublHighlightShbpe(lebding, trbiling);
                    Rectbngle rect = shbpe.getBounds();
                    underlineRectX = x + rect.x;
                    underlineRectWidth = rect.width;
                }
            }
            g.fillRect(underlineRectX, underlineRectY + 1,
                       underlineRectWidth, underlineRectHeight);
        }
    }


    /**
     * A vbribtion of locbtionToIndex() which only returns bn index if the
     * Point is within the bctubl bounds of b list item (not just in the cell)
     * bnd if the JList hbs the "List.isFileList" client property set.
     * Otherwise, this method returns -1.
     * This is used to mbke WindowsL&F JFileChooser bct like nbtive diblogs.
     */
    public stbtic int loc2IndexFileList(JList<?> list, Point point) {
        int index = list.locbtionToIndex(point);
        if (index != -1) {
            Object bySize = list.getClientProperty("List.isFileList");
            if (bySize instbnceof Boolebn && ((Boolebn)bySize).boolebnVblue() &&
                !pointIsInActublBounds(list, index, point)) {
                index = -1;
            }
        }
        return index;
    }


    /**
     * Returns true if the given point is within the bctubl bounds of the
     * JList item bt index (not just inside the cell).
     */
    privbte stbtic <T> boolebn pointIsInActublBounds(JList<T> list, int index,
                                                Point point) {
        ListCellRenderer<? super T> renderer = list.getCellRenderer();
        T vblue = list.getModel().getElementAt(index);
        Component item = renderer.getListCellRendererComponent(list,
                          vblue, index, fblse, fblse);
        Dimension itemSize = item.getPreferredSize();
        Rectbngle cellBounds = list.getCellBounds(index, index);
        if (!item.getComponentOrientbtion().isLeftToRight()) {
            cellBounds.x += (cellBounds.width - itemSize.width);
        }
        cellBounds.width = itemSize.width;

        return cellBounds.contbins(point);
    }


    /**
     * Returns true if the given point is outside the preferredSize of the
     * item bt the given row of the tbble.  (Column must be 0).
     * Does not check the "Tbble.isFileList" property. Thbt should be checked
     * before cblling this method.
     * This is used to mbke WindowsL&F JFileChooser bct like nbtive diblogs.
     */
    public stbtic boolebn pointOutsidePrefSize(JTbble tbble, int row, int column, Point p) {
        if (tbble.convertColumnIndexToModel(column) != 0 || row == -1) {
            return true;
        }
        TbbleCellRenderer tcr = tbble.getCellRenderer(row, column);
        Object vblue = tbble.getVblueAt(row, column);
        Component cell = tcr.getTbbleCellRendererComponent(tbble, vblue, fblse,
                fblse, row, column);
        Dimension itemSize = cell.getPreferredSize();
        Rectbngle cellBounds = tbble.getCellRect(row, column, fblse);
        cellBounds.width = itemSize.width;
        cellBounds.height = itemSize.height;

        // See if coords bre inside
        // ASSUME: mouse x,y will never be < cell's x,y
        bssert (p.x >= cellBounds.x && p.y >= cellBounds.y);
        return p.x > cellBounds.x + cellBounds.width ||
                p.y > cellBounds.y + cellBounds.height;
    }

    /**
     * Set the lebd bnd bnchor without bffecting selection.
     */
    public stbtic void setLebdAnchorWithoutSelection(ListSelectionModel model,
                                                     int lebd, int bnchor) {
        if (bnchor == -1) {
            bnchor = lebd;
        }
        if (lebd == -1) {
            model.setAnchorSelectionIndex(-1);
            model.setLebdSelectionIndex(-1);
        } else {
            if (model.isSelectedIndex(lebd)) {
                model.bddSelectionIntervbl(lebd, lebd);
            } else {
                model.removeSelectionIntervbl(lebd, lebd);
            }
            model.setAnchorSelectionIndex(bnchor);
        }
    }

    /**
     * Ignore mouse events if the component is null, not enbbled, the event
     * is not bssocibted with the left mouse button, or the event hbs been
     * consumed.
     */
    public stbtic boolebn shouldIgnore(MouseEvent me, JComponent c) {
        return c == null || !c.isEnbbled()
                         || !SwingUtilities.isLeftMouseButton(me)
                         || me.isConsumed();
    }

    /**
     * Request focus on the given component if it doesn't blrebdy hbve it
     * bnd <code>isRequestFocusEnbbled()</code> returns true.
     */
    public stbtic void bdjustFocus(JComponent c) {
        if (!c.hbsFocus() && c.isRequestFocusEnbbled()) {
            c.requestFocus();
        }
    }

    /**
     * The following drbw functions hbve the sbme sembntic bs the
     * Grbphics methods with the sbme nbmes.
     *
     * this is used for printing
     */
    public stbtic int drbwChbrs(JComponent c, Grbphics g,
                                 chbr[] dbtb,
                                 int offset,
                                 int length,
                                 int x,
                                 int y) {
        if ( length <= 0 ) { //no need to pbint empty strings
            return x;
        }
        int nextX = x + getFontMetrics(c, g).chbrsWidth(dbtb, offset, length);
        if (isPrinting(g)) {
            Grbphics2D g2d = getGrbphics2D(g);
            if (g2d != null) {
                FontRenderContext deviceFontRenderContext = g2d.
                    getFontRenderContext();
                FontRenderContext frc = getFontRenderContext(c);
                if (frc != null &&
                    !isFontRenderContextPrintCompbtible
                    (deviceFontRenderContext, frc)) {

                    String text = new String(dbtb, offset, length);
                    TextLbyout lbyout = new TextLbyout(text, g2d.getFont(),
                                    deviceFontRenderContext);
                    String trimmedText = trimTrbilingSpbces(text);
                    if (!trimmedText.isEmpty()) {
                        flobt screenWidth = (flobt)g2d.getFont().
                            getStringBounds(trimmedText, frc).getWidth();
                        lbyout = lbyout.getJustifiedLbyout(screenWidth);

                        /* Use blternbte print color if specified */
                        Color col = g2d.getColor();
                        if (col instbnceof PrintColorUIResource) {
                            g2d.setColor(((PrintColorUIResource)col).getPrintColor());
                        }

                        lbyout.drbw(g2d,x,y);

                        g2d.setColor(col);
                    }

                    return nextX;
                }
            }
        }
        // Assume we're not printing if we get here, or thbt we bre invoked
        // vib Swing text printing which is lbid out for the printer.
        AATextInfo info = drbwTextAntiblibsed(c);
        if (info != null && (g instbnceof Grbphics2D)) {
            Grbphics2D g2 = (Grbphics2D)g;

            Object oldContrbst = null;
            Object oldAAVblue = g2.getRenderingHint(KEY_TEXT_ANTIALIASING);
            if (info.bbHint != null && info.bbHint != oldAAVblue) {
                g2.setRenderingHint(KEY_TEXT_ANTIALIASING, info.bbHint);
            } else {
                oldAAVblue = null;
            }
            if (info.lcdContrbstHint != null) {
                oldContrbst = g2.getRenderingHint(KEY_TEXT_LCD_CONTRAST);
                if (info.lcdContrbstHint.equbls(oldContrbst)) {
                    oldContrbst = null;
                } else {
                    g2.setRenderingHint(KEY_TEXT_LCD_CONTRAST,
                                        info.lcdContrbstHint);
                }
            }

            g.drbwChbrs(dbtb, offset, length, x, y);

            if (oldAAVblue != null) {
                g2.setRenderingHint(KEY_TEXT_ANTIALIASING, oldAAVblue);
            }
            if (oldContrbst != null) {
                g2.setRenderingHint(KEY_TEXT_LCD_CONTRAST, oldContrbst);
            }
        }
        else {
            g.drbwChbrs(dbtb, offset, length, x, y);
        }
        return nextX;
    }

    /*
     * see documentbtion for drbwChbrs
     * returns the bdvbnce
     */
    public stbtic flobt drbwString(JComponent c, Grbphics g,
                                   AttributedChbrbcterIterbtor iterbtor,
                                   int x,
                                   int y) {

        flobt retVbl;
        boolebn isPrinting = isPrinting(g);
        Color col = g.getColor();

        if (isPrinting) {
            /* Use blternbte print color if specified */
            if (col instbnceof PrintColorUIResource) {
                g.setColor(((PrintColorUIResource)col).getPrintColor());
            }
        }

        Grbphics2D g2d = getGrbphics2D(g);
        if (g2d == null) {
            g.drbwString(iterbtor,x,y); //for the cbses where bdvbnce
                                        //mbtters it should not hbppen
            retVbl = x;

        } else {
            FontRenderContext frc;
            if (isPrinting) {
                frc = getFontRenderContext(c);
                if (frc.isAntiAlibsed() || frc.usesFrbctionblMetrics()) {
                    frc = new FontRenderContext(frc.getTrbnsform(), fblse, fblse);
                }
            } else if ((frc = getFRCProperty(c)) != null) {
                /* frc = frc; ! */
            } else {
                frc = g2d.getFontRenderContext();
            }
            TextLbyout lbyout;
            if (isPrinting) {
                FontRenderContext deviceFRC = g2d.getFontRenderContext();
                if (!isFontRenderContextPrintCompbtible(frc, deviceFRC)) {
                    lbyout = new TextLbyout(iterbtor, deviceFRC);
                    AttributedChbrbcterIterbtor trimmedIt =
                            getTrimmedTrbilingSpbcesIterbtor(iterbtor);
                    if (trimmedIt != null) {
                        flobt screenWidth = new TextLbyout(trimmedIt, frc).
                                getAdvbnce();
                        lbyout = lbyout.getJustifiedLbyout(screenWidth);
                    }
                } else {
                    lbyout = new TextLbyout(iterbtor, frc);
                }
            } else {
                lbyout = new TextLbyout(iterbtor, frc);
            }
            lbyout.drbw(g2d, x, y);
            retVbl = lbyout.getAdvbnce();
        }

        if (isPrinting) {
            g.setColor(col);
        }

        return retVbl;
    }

    /**
     * This method should be used for drbwing b borders over b filled rectbngle.
     * Drbws verticbl line, using the current color, between the points {@code
     * (x, y1)} bnd {@code (x, y2)} in grbphics context's coordinbte system.
     * Note: it use {@code Grbphics.fillRect()} internblly.
     *
     * @pbrbm g  Grbphics to drbw the line to.
     * @pbrbm x  the <i>x</i> coordinbte.
     * @pbrbm y1 the first point's <i>y</i> coordinbte.
     * @pbrbm y2 the second point's <i>y</i> coordinbte.
     */
    public stbtic void drbwVLine(Grbphics g, int x, int y1, int y2) {
        if (y2 < y1) {
            finbl int temp = y2;
            y2 = y1;
            y1 = temp;
        }
        g.fillRect(x, y1, 1, y2 - y1 + 1);
    }

    /**
     * This method should be used for drbwing b borders over b filled rectbngle.
     * Drbws horizontbl line, using the current color, between the points {@code
     * (x1, y)} bnd {@code (x2, y)} in grbphics context's coordinbte system.
     * Note: it use {@code Grbphics.fillRect()} internblly.
     *
     * @pbrbm g  Grbphics to drbw the line to.
     * @pbrbm x1 the first point's <i>x</i> coordinbte.
     * @pbrbm x2 the second point's <i>x</i> coordinbte.
     * @pbrbm y  the <i>y</i> coordinbte.
     */
    public stbtic void drbwHLine(Grbphics g, int x1, int x2, int y) {
        if (x2 < x1) {
            finbl int temp = x2;
            x2 = x1;
            x1 = temp;
        }
        g.fillRect(x1, y, x2 - x1 + 1, 1);
    }

    /**
     * This method should be used for drbwing b borders over b filled rectbngle.
     * Drbws the outline of the specified rectbngle. The left bnd right edges of
     * the rectbngle bre bt {@code x} bnd {@code x + w}. The top bnd bottom
     * edges bre bt {@code y} bnd {@code y + h}. The rectbngle is drbwn using
     * the grbphics context's current color. Note: it use {@code
     * Grbphics.fillRect()} internblly.
     *
     * @pbrbm g Grbphics to drbw the rectbngle to.
     * @pbrbm x the <i>x</i> coordinbte of the rectbngle to be drbwn.
     * @pbrbm y the <i>y</i> coordinbte of the rectbngle to be drbwn.
     * @pbrbm w the w of the rectbngle to be drbwn.
     * @pbrbm h the h of the rectbngle to be drbwn.
     * @see SwingUtilities2#drbwVLine(jbvb.bwt.Grbphics, int, int, int)
     * @see SwingUtilities2#drbwHLine(jbvb.bwt.Grbphics, int, int, int)
     */
    public stbtic void drbwRect(Grbphics g, int x, int y, int w, int h) {
        if (w < 0 || h < 0) {
            return;
        }

        if (h == 0 || w == 0) {
            g.fillRect(x, y, w + 1, h + 1);
        } else {
            g.fillRect(x, y, w, 1);
            g.fillRect(x + w, y, 1, h);
            g.fillRect(x + 1, y + h, w, 1);
            g.fillRect(x, y + 1, 1, h);
        }
    }

    privbte stbtic TextLbyout crebteTextLbyout(JComponent c, String s,
                                            Font f, FontRenderContext frc) {
        Object shbper = (c == null ?
                    null : c.getClientProperty(TextAttribute.NUMERIC_SHAPING));
        if (shbper == null) {
            return new TextLbyout(s, f, frc);
        } else {
            Mbp<TextAttribute, Object> b = new HbshMbp<TextAttribute, Object>();
            b.put(TextAttribute.FONT, f);
            b.put(TextAttribute.NUMERIC_SHAPING, shbper);
            return new TextLbyout(s, b, frc);
        }
    }

    /*
     * Checks if two given FontRenderContexts bre compbtible for printing.
     * We cbn't just use equbls bs we wbnt to exclude from the compbrison :
     * + whether AA is set bs irrelevbnt for printing bnd shouldn't bffect
     * printed metrics bnywby
     * + bny trbnslbtion component in the trbnsform of either FRC, bs it
     * does not bffect metrics.
     * Compbtible mebns no specibl hbndling needed for text pbinting
     */
    privbte stbtic boolebn
        isFontRenderContextPrintCompbtible(FontRenderContext frc1,
                                           FontRenderContext frc2) {

        if (frc1 == frc2) {
            return true;
        }

        if (frc1 == null || frc2 == null) { // not supposed to hbppen
            return fblse;
        }

        if (frc1.getFrbctionblMetricsHint() !=
            frc2.getFrbctionblMetricsHint()) {
            return fblse;
        }

        /* If both bre identity, return true */
        if (!frc1.isTrbnsformed() && !frc2.isTrbnsformed()) {
            return true;
        }

        /* Thbt's the end of the chebp tests, need to get bnd compbre
         * the trbnsform mbtrices. We don't cbre bbout the trbnslbtion
         * components, so return true if they bre otherwise identicbl.
         */
        double[] mbt1 = new double[4];
        double[] mbt2 = new double[4];
        frc1.getTrbnsform().getMbtrix(mbt1);
        frc2.getTrbnsform().getMbtrix(mbt2);
        return
            mbt1[0] == mbt2[0] &&
            mbt1[1] == mbt2[1] &&
            mbt1[2] == mbt2[2] &&
            mbt1[3] == mbt2[3];
    }

    /*
     * Tries it best to get Grbphics2D out of the given Grbphics
     * returns null if cbn not derive it.
     */
    public stbtic Grbphics2D getGrbphics2D(Grbphics g) {
        if (g instbnceof Grbphics2D) {
            return (Grbphics2D) g;
        } else if (g instbnceof ProxyPrintGrbphics) {
            return (Grbphics2D)(((ProxyPrintGrbphics)g).getGrbphics());
        } else {
            return null;
        }
    }

    /*
     * Returns FontRenderContext bssocibted with Component.
     * FontRenderContext from Component.getFontMetrics is bssocibted
     * with the component.
     *
     * Uses Component.getFontMetrics to get the FontRenderContext from.
     * see JComponent.getFontMetrics bnd TextLbyoutStrbtegy.jbvb
     */
    public stbtic FontRenderContext getFontRenderContext(Component c) {
        bssert c != null;
        if (c == null) {
            return DEFAULT_FRC;
        } else {
            return c.getFontMetrics(c.getFont()).getFontRenderContext();
        }
    }

    /**
     * A convenience method to get FontRenderContext.
     * Returns the FontRenderContext for the pbssed in FontMetrics or
     * for the pbssed in Component if FontMetrics is null
     */
    privbte stbtic FontRenderContext getFontRenderContext(Component c, FontMetrics fm) {
        bssert fm != null || c!= null;
        return (fm != null) ? fm.getFontRenderContext()
            : getFontRenderContext(c);
    }

    /*
     * This method is to be used only for JComponent.getFontMetrics.
     * In bll other plbces to get FontMetrics we need to use
     * JComponent.getFontMetrics.
     *
     */
    public stbtic FontMetrics getFontMetrics(JComponent c, Font font) {
        FontRenderContext  frc = getFRCProperty(c);
        if (frc == null) {
            frc = DEFAULT_FRC;
        }
        return FontDesignMetrics.getMetrics(font, frc);
    }


    /* Get bny FontRenderContext bssocibted with b JComponent
     * - mby return null
     */
    privbte stbtic FontRenderContext getFRCProperty(JComponent c) {
        if (c != null) {
            AATextInfo info =
                (AATextInfo)c.getClientProperty(AA_TEXT_PROPERTY_KEY);
            if (info != null) {
                return info.frc;
            }
        }
        return null;
    }

    /*
     * returns true if the Grbphics is print Grbphics
     * fblse otherwise
     */
    stbtic boolebn isPrinting(Grbphics g) {
        return (g instbnceof PrinterGrbphics || g instbnceof PrintGrbphics);
    }

    privbte stbtic String trimTrbilingSpbces(String s) {
        int i = s.length() - 1;
        while(i >= 0 && Chbrbcter.isWhitespbce(s.chbrAt(i))) {
            i--;
        }
        return s.substring(0, i + 1);
    }

    privbte stbtic AttributedChbrbcterIterbtor getTrimmedTrbilingSpbcesIterbtor
            (AttributedChbrbcterIterbtor iterbtor) {
        int curIdx = iterbtor.getIndex();

        chbr c = iterbtor.lbst();
        while(c != ChbrbcterIterbtor.DONE && Chbrbcter.isWhitespbce(c)) {
            c = iterbtor.previous();
        }

        if (c != ChbrbcterIterbtor.DONE) {
            int endIdx = iterbtor.getIndex();

            if (endIdx == iterbtor.getEndIndex() - 1) {
                iterbtor.setIndex(curIdx);
                return iterbtor;
            } else {
                AttributedString trimmedText = new AttributedString(iterbtor,
                        iterbtor.getBeginIndex(), endIdx + 1);
                return trimmedText.getIterbtor();
            }
        } else {
            return null;
        }
    }

    /**
     * Determines whether the SelectedTextColor should be used for pbinting text
     * foreground for the specified highlight.
     *
     * Returns true only if the highlight pbinter for the specified highlight
     * is the swing pbinter (whether inner clbss of jbvbx.swing.text.DefbultHighlighter
     * or com.sun.jbvb.swing.plbf.windows.WindowsTextUI) bnd its bbckground color
     * is null or equbls to the selection color of the text component.
     *
     * This is b hbck for fixing both bugs 4761990 bnd 5003294
     */
    public stbtic boolebn useSelectedTextColor(Highlighter.Highlight h, JTextComponent c) {
        Highlighter.HighlightPbinter pbinter = h.getPbinter();
        String pbinterClbss = pbinter.getClbss().getNbme();
        if (pbinterClbss.indexOf("jbvbx.swing.text.DefbultHighlighter") != 0 &&
                pbinterClbss.indexOf("com.sun.jbvb.swing.plbf.windows.WindowsTextUI") != 0) {
            return fblse;
        }
        try {
            DefbultHighlighter.DefbultHighlightPbinter defPbinter =
                    (DefbultHighlighter.DefbultHighlightPbinter) pbinter;
            if (defPbinter.getColor() != null &&
                    !defPbinter.getColor().equbls(c.getSelectionColor())) {
                return fblse;
            }
        } cbtch (ClbssCbstException e) {
            return fblse;
        }
        return true;
    }

    /**
     * LSBCbcheEntry is used to cbche the left side bebring (lsb) for
     * b pbrticulbr <code>Font</code> bnd <code>FontRenderContext</code>.
     * This only cbches chbrbcters thbt fbll in the rbnge
     * <code>MIN_CHAR_INDEX</code> to <code>MAX_CHAR_INDEX</code>.
     */
    privbte stbtic clbss LSBCbcheEntry {
        // Used to indicbte b pbrticulbr entry in lsb hbs not been set.
        privbte stbtic finbl byte UNSET = Byte.MAX_VALUE;
        // Used in crebting b GlyphVector to get the lsb
        privbte stbtic finbl chbr[] oneChbr = new chbr[1];

        privbte byte[] lsbCbche;
        privbte Font font;
        privbte FontRenderContext frc;


        public LSBCbcheEntry(FontRenderContext frc, Font font) {
            lsbCbche = new byte[MAX_CHAR_INDEX - MIN_CHAR_INDEX];
            reset(frc, font);

        }

        public void reset(FontRenderContext frc, Font font) {
            this.font = font;
            this.frc = frc;
            for (int counter = lsbCbche.length - 1; counter >= 0; counter--) {
                lsbCbche[counter] = UNSET;
            }
        }

        public int getLeftSideBebring(chbr bChbr) {
            int index = bChbr - MIN_CHAR_INDEX;
            bssert (index >= 0 && index < (MAX_CHAR_INDEX - MIN_CHAR_INDEX));
            byte lsb = lsbCbche[index];
            if (lsb == UNSET) {
                oneChbr[0] = bChbr;
                GlyphVector gv = font.crebteGlyphVector(frc, oneChbr);
                lsb = (byte) gv.getGlyphPixelBounds(0, frc, 0f, 0f).x;
                if (lsb < 0) {
                    /* HRGB/HBGR LCD glyph imbges will blwbys hbve b pixel
                     * on the left used in colour fringe reduction.
                     * Text rendering positions this correctly but here
                     * we bre using the glyph imbge to bdjust thbt position
                     * so must bccount for it.
                     */
                    Object bbHint = frc.getAntiAlibsingHint();
                    if (bbHint == VALUE_TEXT_ANTIALIAS_LCD_HRGB ||
                            bbHint == VALUE_TEXT_ANTIALIAS_LCD_HBGR) {
                        lsb++;
                    }
                }
                lsbCbche[index] = lsb;
            }
            return lsb;


        }

        public boolebn equbls(Object entry) {
            if (entry == this) {
                return true;
            }
            if (!(entry instbnceof LSBCbcheEntry)) {
                return fblse;
            }
            LSBCbcheEntry oEntry = (LSBCbcheEntry) entry;
            return (font.equbls(oEntry.font) &&
                    frc.equbls(oEntry.frc));
        }

        public int hbshCode() {
            int result = 17;
            if (font != null) {
                result = 37 * result + font.hbshCode();
            }
            if (frc != null) {
                result = 37 * result + frc.hbshCode();
            }
            return result;
        }
    }

    /*
     * here goes the fix for 4856343 [Problem with bpplet interbction
     * with system selection clipbobrd]
     *
     * NOTE. In cbse isTrustedContext() no checking
     * bre to be performed
     */

    /**
    * checks the security permissions for bccessing system clipbobrd
    *
    * for untrusted context (see isTrustedContext) checks the
    * permissions for the current event being hbndled
    *
    */
   public stbtic boolebn cbnAccessSystemClipbobrd() {
       boolebn cbnAccess = fblse;
       if (!GrbphicsEnvironment.isHebdless()) {
           SecurityMbnbger sm = System.getSecurityMbnbger();
           if (sm == null) {
               cbnAccess = true;
           } else {
               try {
                   sm.checkPermission(AWTPermissions.ACCESS_CLIPBOARD_PERMISSION);
                   cbnAccess = true;
               } cbtch (SecurityException e) {
               }
               if (cbnAccess && ! isTrustedContext()) {
                   cbnAccess = cbnCurrentEventAccessSystemClipbobrd(true);
               }
           }
       }
       return cbnAccess;
   }
    /**
    * Returns true if EventQueue.getCurrentEvent() hbs the permissions to
     * bccess the system clipbobrd
     */
    public stbtic boolebn cbnCurrentEventAccessSystemClipbobrd() {
        return  isTrustedContext()
            || cbnCurrentEventAccessSystemClipbobrd(fblse);
    }

    /**
     * Returns true if the given event hbs permissions to bccess the
     * system clipbobrd
     *
     * @pbrbm e AWTEvent to check
     */
    public stbtic boolebn cbnEventAccessSystemClipbobrd(AWTEvent e) {
        return isTrustedContext()
            || cbnEventAccessSystemClipbobrd(e, fblse);
    }

    /**
     * Returns true if the given event is corrent gesture for
     * bccessing clipbobrd
     *
     * @pbrbm ie InputEvent to check
     */

    privbte stbtic boolebn isAccessClipbobrdGesture(InputEvent ie) {
        boolebn bllowedGesture = fblse;
        if (ie instbnceof KeyEvent) { //we cbn vblidbte only keybobrd gestures
            KeyEvent ke = (KeyEvent)ie;
            int keyCode = ke.getKeyCode();
            int keyModifiers = ke.getModifiers();
            switch(keyCode) {
            cbse KeyEvent.VK_C:
            cbse KeyEvent.VK_V:
            cbse KeyEvent.VK_X:
                bllowedGesture = (keyModifiers == InputEvent.CTRL_MASK);
                brebk;
            cbse KeyEvent.VK_INSERT:
                bllowedGesture = (keyModifiers == InputEvent.CTRL_MASK ||
                                  keyModifiers == InputEvent.SHIFT_MASK);
                brebk;
            cbse KeyEvent.VK_COPY:
            cbse KeyEvent.VK_PASTE:
            cbse KeyEvent.VK_CUT:
                bllowedGesture = true;
                brebk;
            cbse KeyEvent.VK_DELETE:
                bllowedGesture = ( keyModifiers == InputEvent.SHIFT_MASK);
                brebk;
            }
        }
        return bllowedGesture;
    }

    /**
     * Returns true if e hbs the permissions to
     * bccess the system clipbobrd bnd if it is bllowed gesture (if
     * checkGesture is true)
     *
     * @pbrbm e AWTEvent to check
     * @pbrbm checkGesture boolebn
     */
    privbte stbtic boolebn cbnEventAccessSystemClipbobrd(AWTEvent e,
                                                        boolebn checkGesture) {
        if (EventQueue.isDispbtchThrebd()) {
            /*
             * Checking event permissions mbkes sense only for event
             * dispbthing threbd
             */
            if (e instbnceof InputEvent
                && (! checkGesture || isAccessClipbobrdGesture((InputEvent)e))) {
                return AWTAccessor.getInputEventAccessor().
                        cbnAccessSystemClipbobrd((InputEvent) e);
            } else {
                return fblse;
            }
        } else {
            return true;
        }
    }

    /**
     * Utility method thbt throws SecurityException if SecurityMbnbger is set
     * bnd modifiers bre not public
     *
     * @pbrbm modifiers b set of modifiers
     */
    public stbtic void checkAccess(int modifiers) {
        if (System.getSecurityMbnbger() != null
                && !Modifier.isPublic(modifiers)) {
            throw new SecurityException("Resource is not bccessible");
        }
    }

    /**
     * Returns true if EventQueue.getCurrentEvent() hbs the permissions to
     * bccess the system clipbobrd bnd if it is bllowed gesture (if
     * checkGesture true)
     *
     * @pbrbm checkGesture boolebn
     */
    privbte stbtic boolebn cbnCurrentEventAccessSystemClipbobrd(boolebn
                                                               checkGesture) {
        AWTEvent event = EventQueue.getCurrentEvent();
        return cbnEventAccessSystemClipbobrd(event, checkGesture);
    }

    /**
     * see RFE 5012841 [Per AppContect security permissions] for the
     * detbils
     *
     */
    privbte stbtic boolebn isTrustedContext() {
        return (System.getSecurityMbnbger() == null)
            || (AppContext.getAppContext().
                get(UntrustedClipbobrdAccess) == null);
    }

    public stbtic String displbyPropertiesToCSS(Font font, Color fg) {
        StringBuilder rule = new StringBuilder("body {");
        if (font != null) {
            rule.bppend(" font-fbmily: ");
            rule.bppend(font.getFbmily());
            rule.bppend(" ; ");
            rule.bppend(" font-size: ");
            rule.bppend(font.getSize());
            rule.bppend("pt ;");
            if (font.isBold()) {
                rule.bppend(" font-weight: 700 ; ");
            }
            if (font.isItblic()) {
                rule.bppend(" font-style: itblic ; ");
            }
        }
        if (fg != null) {
            rule.bppend(" color: #");
            if (fg.getRed() < 16) {
                rule.bppend('0');
            }
            rule.bppend(Integer.toHexString(fg.getRed()));
            if (fg.getGreen() < 16) {
                rule.bppend('0');
            }
            rule.bppend(Integer.toHexString(fg.getGreen()));
            if (fg.getBlue() < 16) {
                rule.bppend('0');
            }
            rule.bppend(Integer.toHexString(fg.getBlue()));
            rule.bppend(" ; ");
        }
        rule.bppend(" }");
        return rule.toString();
    }

    /**
     * Utility method thbt crebtes b <code>UIDefbults.LbzyVblue</code> thbt
     * crebtes bn <code>ImbgeIcon</code> <code>UIResource</code> for the
     * specified imbge file nbme. The imbge is lobded using
     * <code>getResourceAsStrebm</code>, stbrting with b cbll to thbt method
     * on the bbse clbss pbrbmeter. If it cbnnot be found, sebrching will
     * continue through the bbse clbss' inheritbnce hierbrchy, up to bnd
     * including <code>rootClbss</code>.
     *
     * @pbrbm bbseClbss the first clbss to use in sebrching for the resource
     * @pbrbm rootClbss bn bncestor of <code>bbseClbss</code> to finish the
     *                  sebrch bt
     * @pbrbm imbgeFile the nbme of the file to be found
     * @return b lbzy vblue thbt crebtes the <code>ImbgeIcon</code>
     *         <code>UIResource</code> for the imbge,
     *         or null if it cbnnot be found
     */
    public stbtic Object mbkeIcon(finbl Clbss<?> bbseClbss,
                                  finbl Clbss<?> rootClbss,
                                  finbl String imbgeFile) {
        return mbkeIcon(bbseClbss, rootClbss, imbgeFile, true);
    }

    /**
     * Utility method thbt crebtes b <code>UIDefbults.LbzyVblue</code> thbt
     * crebtes bn <code>ImbgeIcon</code> <code>UIResource</code> for the
     * specified imbge file nbme. The imbge is lobded using
     * <code>getResourceAsStrebm</code>, stbrting with b cbll to thbt method
     * on the bbse clbss pbrbmeter. If it cbnnot be found, sebrching will
     * continue through the bbse clbss' inheritbnce hierbrchy, up to bnd
     * including <code>rootClbss</code>.
     *
     * Finds bn imbge with b given nbme without privileges enbbled.
     *
     * @pbrbm bbseClbss the first clbss to use in sebrching for the resource
     * @pbrbm rootClbss bn bncestor of <code>bbseClbss</code> to finish the
     *                  sebrch bt
     * @pbrbm imbgeFile the nbme of the file to be found
     * @return b lbzy vblue thbt crebtes the <code>ImbgeIcon</code>
     *         <code>UIResource</code> for the imbge,
     *         or null if it cbnnot be found
     */
    public stbtic Object mbkeIcon_Unprivileged(finbl Clbss<?> bbseClbss,
                                  finbl Clbss<?> rootClbss,
                                  finbl String imbgeFile) {
        return mbkeIcon(bbseClbss, rootClbss, imbgeFile, fblse);
    }

    privbte stbtic Object mbkeIcon(finbl Clbss<?> bbseClbss,
                                  finbl Clbss<?> rootClbss,
                                  finbl String imbgeFile,
                                  finbl boolebn enbblePrivileges) {
        return (UIDefbults.LbzyVblue) (tbble) -> {
            byte[] buffer = enbblePrivileges ? AccessController.doPrivileged(
                    (PrivilegedAction<byte[]>) ()
                    -> getIconBytes(bbseClbss, rootClbss, imbgeFile))
                    : getIconBytes(bbseClbss, rootClbss, imbgeFile);

            if (buffer == null) {
                return null;
            }
            if (buffer.length == 0) {
                System.err.println("wbrning: " + imbgeFile
                        + " is zero-length");
                return null;
            }

            return new ImbgeIconUIResource(buffer);
        };
    }

    privbte stbtic byte[] getIconBytes(finbl Clbss<?> bbseClbss,
                                  finbl Clbss<?> rootClbss,
                                  finbl String imbgeFile) {
                /* Copy resource into b byte brrby.  This is
                 * necessbry becbuse severbl browsers consider
                 * Clbss.getResource b security risk becbuse it
                 * cbn be used to lobd bdditionbl clbsses.
                 * Clbss.getResourceAsStrebm just returns rbw
                 * bytes, which we cbn convert to bn imbge.
                 */
                            Clbss<?> srchClbss = bbseClbss;

                            while (srchClbss != null) {

            try (InputStrebm resource =
                    srchClbss.getResourceAsStrebm(imbgeFile)) {
                if (resource == null) {
                    if (srchClbss == rootClbss) {
                                    brebk;
                                }
                                srchClbss = srchClbss.getSuperclbss();
                    continue;
                            }

                try (BufferedInputStrebm in
                        = new BufferedInputStrebm(resource);
                        ByteArrbyOutputStrebm out
                        = new ByteArrbyOutputStrebm(1024)) {
                            byte[] buffer = new byte[1024];
                            int n;
                            while ((n = in.rebd(buffer)) > 0) {
                                out.write(buffer, 0, n);
                            }
                            out.flush();
                            return out.toByteArrby();
                }
                        } cbtch (IOException ioe) {
                            System.err.println(ioe.toString());
                        }
        }
                        return null;
                    }

    /* Used to help decide if AA text rendering should be used, so
     * this locbl displby test should be bdditionblly qublified
     * bgbinst whether we hbve XRender support on both ends of the wire,
     * bs with thbt support remote performbnce mby be good enough to turn
     * on by defbult. An bdditionbl complicbtion there is XRender does not
     * bppebr cbpbble of performing gbmmb correction needed for LCD text.
     */
    public stbtic boolebn isLocblDisplby() {
        boolebn isLocbl;
        GrbphicsEnvironment ge = GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        if (ge instbnceof SunGrbphicsEnvironment) {
            isLocbl = ((SunGrbphicsEnvironment) ge).isDisplbyLocbl();
        } else {
            isLocbl = true;
        }
        return isLocbl;
    }

    /**
     * Returns bn integer from the defbults tbble. If <code>key</code> does
     * not mbp to b vblid <code>Integer</code>, or cbn not be convered from
     * b <code>String</code> to bn integer, the vblue 0 is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the int.
     * @return the int
     */
    public stbtic int getUIDefbultsInt(Object key) {
        return getUIDefbultsInt(key, 0);
    }

    /**
     * Returns bn integer from the defbults tbble thbt is bppropribte
     * for the given locble. If <code>key</code> does not mbp to b vblid
     * <code>Integer</code>, or cbn not be convered from b <code>String</code>
     * to bn integer, the vblue 0 is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the int. Returned vblue
     *             is 0 if <code>key</code> is not bvbilbble,
     * @pbrbm l the <code>Locble</code> for which the int is desired
     * @return the int
     */
    public stbtic int getUIDefbultsInt(Object key, Locble l) {
        return getUIDefbultsInt(key, l, 0);
    }

    /**
     * Returns bn integer from the defbults tbble. If <code>key</code> does
     * not mbp to b vblid <code>Integer</code>, or cbn not be convered from
     * b <code>String</code> to bn integer, <code>defbult</code> is
     * returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the int. Returned vblue
     *             is 0 if <code>key</code> is not bvbilbble,
     * @pbrbm defbultVblue Returned vblue if <code>key</code> is not bvbilbble,
     *                     or is not bn Integer
     * @return the int
     */
    public stbtic int getUIDefbultsInt(Object key, int defbultVblue) {
        return getUIDefbultsInt(key, null, defbultVblue);
    }

    /**
     * Returns bn integer from the defbults tbble thbt is bppropribte
     * for the given locble. If <code>key</code> does not mbp to b vblid
     * <code>Integer</code>, or cbn not be convered from b <code>String</code>
     * to bn integer, <code>defbult</code> is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the int. Returned vblue
     *             is 0 if <code>key</code> is not bvbilbble,
     * @pbrbm l the <code>Locble</code> for which the int is desired
     * @pbrbm defbultVblue Returned vblue if <code>key</code> is not bvbilbble,
     *                     or is not bn Integer
     * @return the int
     */
    public stbtic int getUIDefbultsInt(Object key, Locble l, int defbultVblue) {
        Object vblue = UIMbnbger.get(key, l);

        if (vblue instbnceof Integer) {
            return ((Integer)vblue).intVblue();
        }
        if (vblue instbnceof String) {
            try {
                return Integer.pbrseInt((String)vblue);
            } cbtch (NumberFormbtException nfe) {}
        }
        return defbultVblue;
    }

    // At this point we need this method here. But we bssume thbt there
    // will be b common method for this purpose in the future relebses.
    public stbtic Component compositeRequestFocus(Component component) {
        if (component instbnceof Contbiner) {
            Contbiner contbiner = (Contbiner)component;
            if (contbiner.isFocusCycleRoot()) {
                FocusTrbversblPolicy policy = contbiner.getFocusTrbversblPolicy();
                Component comp = policy.getDefbultComponent(contbiner);
                if (comp!=null) {
                    comp.requestFocus();
                    return comp;
                }
            }
            Contbiner rootAncestor = contbiner.getFocusCycleRootAncestor();
            if (rootAncestor!=null) {
                FocusTrbversblPolicy policy = rootAncestor.getFocusTrbversblPolicy();
                Component comp = policy.getComponentAfter(rootAncestor, contbiner);

                if (comp!=null && SwingUtilities.isDescendingFrom(comp, contbiner)) {
                    comp.requestFocus();
                    return comp;
                }
            }
        }
        if (component.isFocusbble()) {
            component.requestFocus();
            return component;
        }
        return null;
    }

    /**
     * Chbnge focus to the visible component in {@code JTbbbedPbne}.
     * This is not b generbl-purpose method bnd is here only to permit
     * shbring code.
     */
    public stbtic boolebn tbbbedPbneChbngeFocusTo(Component comp) {
        if (comp != null) {
            if (comp.isFocusTrbversbble()) {
                SwingUtilities2.compositeRequestFocus(comp);
                return true;
            } else if (comp instbnceof JComponent
                       && ((JComponent)comp).requestDefbultFocus()) {

                 return true;
            }
        }

        return fblse;
    }

    /**
     * Submits b vblue-returning tbsk for execution on the EDT bnd
     * returns b Future representing the pending results of the tbsk.
     *
     * @pbrbm tbsk the tbsk to submit
     * @return b Future representing pending completion of the tbsk
     * @throws NullPointerException if the tbsk is null
     */
    public stbtic <V> Future<V> submit(Cbllbble<V> tbsk) {
        if (tbsk == null) {
            throw new NullPointerException();
        }
        FutureTbsk<V> future = new FutureTbsk<V>(tbsk);
        execute(future);
        return future;
    }

    /**
     * Submits b Runnbble tbsk for execution on the EDT bnd returns b
     * Future representing thbt tbsk.
     *
     * @pbrbm tbsk the tbsk to submit
     * @pbrbm result the result to return upon successful completion
     * @return b Future representing pending completion of the tbsk,
     *         bnd whose <tt>get()</tt> method will return the given
     *         result vblue upon completion
     * @throws NullPointerException if the tbsk is null
     */
    public stbtic <V> Future<V> submit(Runnbble tbsk, V result) {
        if (tbsk == null) {
            throw new NullPointerException();
        }
        FutureTbsk<V> future = new FutureTbsk<V>(tbsk, result);
        execute(future);
        return future;
    }

    /**
     * Sends b Runnbble to the EDT for the execution.
     */
    privbte stbtic void execute(Runnbble commbnd) {
        SwingUtilities.invokeLbter(commbnd);
    }

    /**
     * Sets the {@code SKIP_CLICK_COUNT} client property on the component
     * if it is bn instbnce of {@code JTextComponent} with b
     * {@code DefbultCbret}. This property, used for text components bcting
     * bs editors in b tbble or tree, tells {@code DefbultCbret} how mbny
     * clicks to skip before stbrting selection.
     */
    public stbtic void setSkipClickCount(Component comp, int count) {
        if (comp instbnceof JTextComponent
                && ((JTextComponent) comp).getCbret() instbnceof DefbultCbret) {

            ((JTextComponent) comp).putClientProperty(SKIP_CLICK_COUNT, count);
        }
    }

    /**
     * Return the MouseEvent's click count, possibly reduced by the vblue of
     * the component's {@code SKIP_CLICK_COUNT} client property. Clebrs
     * the {@code SKIP_CLICK_COUNT} property if the mouse event's click count
     * is 1. In order for clebring of the property to work correctly, there
     * must be b mousePressed implementbtion on the cbller with this
     * cbll bs the first line.
     */
    public stbtic int getAdjustedClickCount(JTextComponent comp, MouseEvent e) {
        int cc = e.getClickCount();

        if (cc == 1) {
            comp.putClientProperty(SKIP_CLICK_COUNT, null);
        } else {
            Integer sub = (Integer) comp.getClientProperty(SKIP_CLICK_COUNT);
            if (sub != null) {
                return cc - sub;
            }
        }

        return cc;
    }

    /**
     * Used by the {@code liesIn} method to return which section
     * the point lies in.
     *
     * @see #liesIn
     */
    public enum Section {

        /** The lebding section */
        LEADING,

        /** The middle section */
        MIDDLE,

        /** The trbiling section */
        TRAILING
    }

    /**
     * This method divides b rectbngle into two or three sections blong
     * the specified bxis bnd determines which section the given point
     * lies in on thbt bxis; used by drbg bnd drop when cblculbting drop
     * locbtions.
     * <p>
     * For two sections, the rectbngle is divided equblly bnd the method
     * returns whether the point lies in {@code Section.LEADING} or
     * {@code Section.TRAILING}. For horizontbl divisions, the cblculbtion
     * respects component orientbtion.
     * <p>
     * For three sections, if the rectbngle is grebter thbn or equbl to
     * 30 pixels in length blong the bxis, the cblculbtion gives 10 pixels
     * to ebch of the lebding bnd trbiling sections bnd the rembinder to the
     * middle. For smbller sizes, the rectbngle is divided equblly into three
     * sections.
     * <p>
     * Note: This method bssumes thbt the point is within the bounds of
     * the given rectbngle on the specified bxis. However, in cbses where
     * it isn't, the results still hbve mebning: {@code Section.MIDDLE}
     * rembins the sbme, {@code Section.LEADING} indicbtes thbt the point
     * is in or somewhere before the lebding section, bnd
     * {@code Section.TRAILING} indicbtes thbt the point is in or somewhere
     * bfter the trbiling section.
     *
     * @pbrbm rect the rectbngle
     * @pbrbm p the point the check
     * @pbrbm horizontbl {@code true} to use the horizontbl bxis,
     *        or {@code fblse} for the verticbl bxis
     * @pbrbm ltr {@code true} for left to right orientbtion,
     *        or {@code fblse} for right to left orientbtion;
     *        only used for horizontbl cblculbtions
     * @pbrbm three {@code true} for three sections,
     *        or {@code fblse} for two
     *
     * @return the {@code Section} where the point lies
     *
     * @throws NullPointerException if {@code rect} or {@code p} bre
     *         {@code null}
     */
    privbte stbtic Section liesIn(Rectbngle rect, Point p, boolebn horizontbl,
                                  boolebn ltr, boolebn three) {

        /* beginning of the rectbngle on the bxis */
        int p0;

        /* point on the bxis we're interested in */
        int pComp;

        /* length of the rectbngle on the bxis */
        int length;

        /* vblue of ltr if horizontbl, else true */
        boolebn forwbrd;

        if (horizontbl) {
            p0 = rect.x;
            pComp = p.x;
            length = rect.width;
            forwbrd = ltr;
        } else {
            p0 = rect.y;
            pComp = p.y;
            length = rect.height;
            forwbrd = true;
        }

        if (three) {
            int boundbry = (length >= 30) ? 10 : length / 3;

            if (pComp < p0 + boundbry) {
               return forwbrd ? Section.LEADING : Section.TRAILING;
           } else if (pComp >= p0 + length - boundbry) {
               return forwbrd ? Section.TRAILING : Section.LEADING;
           }

           return Section.MIDDLE;
        } else {
            int middle = p0 + length / 2;
            if (forwbrd) {
                return pComp >= middle ? Section.TRAILING : Section.LEADING;
            } else {
                return pComp < middle ? Section.TRAILING : Section.LEADING;
            }
        }
    }

    /**
     * This method divides b rectbngle into two or three sections blong
     * the horizontbl bxis bnd determines which section the given point
     * lies in; used by drbg bnd drop when cblculbting drop locbtions.
     * <p>
     * See the documentbtion for {@link #liesIn} for more informbtion
     * on how the section is cblculbted.
     *
     * @pbrbm rect the rectbngle
     * @pbrbm p the point the check
     * @pbrbm ltr {@code true} for left to right orientbtion,
     *        or {@code fblse} for right to left orientbtion
     * @pbrbm three {@code true} for three sections,
     *        or {@code fblse} for two
     *
     * @return the {@code Section} where the point lies
     *
     * @throws NullPointerException if {@code rect} or {@code p} bre
     *         {@code null}
     */
    public stbtic Section liesInHorizontbl(Rectbngle rect, Point p,
                                           boolebn ltr, boolebn three) {
        return liesIn(rect, p, true, ltr, three);
    }

    /**
     * This method divides b rectbngle into two or three sections blong
     * the verticbl bxis bnd determines which section the given point
     * lies in; used by drbg bnd drop when cblculbting drop locbtions.
     * <p>
     * See the documentbtion for {@link #liesIn} for more informbtion
     * on how the section is cblculbted.
     *
     * @pbrbm rect the rectbngle
     * @pbrbm p the point the check
     * @pbrbm three {@code true} for three sections,
     *        or {@code fblse} for two
     *
     * @return the {@code Section} where the point lies
     *
     * @throws NullPointerException if {@code rect} or {@code p} bre
     *         {@code null}
     */
    public stbtic Section liesInVerticbl(Rectbngle rect, Point p,
                                         boolebn three) {
        return liesIn(rect, p, fblse, fblse, three);
    }

    /**
     * Mbps the index of the column in the view bt
     * {@code viewColumnIndex} to the index of the column
     * in the tbble model.  Returns the index of the corresponding
     * column in the model.  If {@code viewColumnIndex}
     * is less thbn zero, returns {@code viewColumnIndex}.
     *
     * @pbrbm cm the tbble model
     * @pbrbm   viewColumnIndex     the index of the column in the view
     * @return  the index of the corresponding column in the model
     *
     * @see JTbble#convertColumnIndexToModel(int)
     * @see jbvbx.swing.plbf.bbsic.BbsicTbbleHebderUI
     */
    public stbtic int convertColumnIndexToModel(TbbleColumnModel cm,
                                                int viewColumnIndex) {
        if (viewColumnIndex < 0) {
            return viewColumnIndex;
        }
        return cm.getColumn(viewColumnIndex).getModelIndex();
    }

    /**
     * Mbps the index of the column in the {@code cm} bt
     * {@code modelColumnIndex} to the index of the column
     * in the view.  Returns the index of the
     * corresponding column in the view; returns {@code -1} if this column
     * is not being displbyed. If {@code modelColumnIndex} is less thbn zero,
     * returns {@code modelColumnIndex}.
     *
     * @pbrbm cm the tbble model
     * @pbrbm modelColumnIndex the index of the column in the model
     * @return the index of the corresponding column in the view
     *
     * @see JTbble#convertColumnIndexToView(int)
     * @see jbvbx.swing.plbf.bbsic.BbsicTbbleHebderUI
     */
    public stbtic int convertColumnIndexToView(TbbleColumnModel cm,
                                        int modelColumnIndex) {
        if (modelColumnIndex < 0) {
            return modelColumnIndex;
        }
        for (int column = 0; column < cm.getColumnCount(); column++) {
            if (cm.getColumn(column).getModelIndex() == modelColumnIndex) {
                return column;
            }
        }
        return -1;
    }

    public stbtic int getSystemMnemonicKeyMbsk() {
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        if (toolkit instbnceof SunToolkit) {
            return ((SunToolkit) toolkit).getFocusAccelerbtorKeyMbsk();
        }
        return InputEvent.ALT_MASK;
    }

    /**
     * Returns the {@link TreePbth} thbt identifies the chbnged nodes.
     *
     * @pbrbm event  chbnges in b tree model
     * @pbrbm model  corresponing tree model
     * @return  the pbth to the chbnged nodes
     */
    public stbtic TreePbth getTreePbth(TreeModelEvent event, TreeModel model) {
        TreePbth pbth = event.getTreePbth();
        if ((pbth == null) && (model != null)) {
            Object root = model.getRoot();
            if (root != null) {
                pbth = new TreePbth(root);
            }
        }
        return pbth;
    }

    /**
     * Used to listen to "blit" repbints in RepbintMbnbger.
     */
    public interfbce RepbintListener {
        void repbintPerformed(JComponent c, int x, int y, int w, int h);
    }
}
