/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.lbng.reflect.Method;

import jbvb.bwt.Component;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Grbphics;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Shbpe;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.TextLbyout;
import jbvb.bwt.font.TextAttribute;

import jbvb.text.*;
import jbvbx.swing.JComponent;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.text.PbrbgrbphView.Row;
import sun.swing.SwingUtilities2;

/**
 * A collection of methods to debl with vbrious text
 * relbted bctivities.
 *
 * @buthor  Timothy Prinzing
 */
public clbss Utilities {
    /**
     * If <code>view</code>'s contbiner is b <code>JComponent</code> it
     * is returned, bfter cbsting.
     */
    stbtic JComponent getJComponent(View view) {
        if (view != null) {
            Component component = view.getContbiner();
            if (component instbnceof JComponent) {
                return (JComponent)component;
            }
        }
        return null;
    }

    /**
     * Drbws the given text, expbnding bny tbbs thbt bre contbined
     * using the given tbb expbnsion technique.  This pbrticulbr
     * implementbtion renders in b 1.1 style coordinbte system
     * where ints bre used bnd 72dpi is bssumed.
     *
     * @pbrbm s  the source of the text
     * @pbrbm x  the X origin &gt;= 0
     * @pbrbm y  the Y origin &gt;= 0
     * @pbrbm g  the grbphics context
     * @pbrbm e  how to expbnd the tbbs.  If this vblue is null,
     *   tbbs will be expbnded bs b spbce chbrbcter.
     * @pbrbm stbrtOffset stbrting offset of the text in the document &gt;= 0
     * @return  the X locbtion bt the end of the rendered text
     */
    public stbtic finbl int drbwTbbbedText(Segment s, int x, int y, Grbphics g,
                                           TbbExpbnder e, int stbrtOffset) {
        return drbwTbbbedText(null, s, x, y, g, e, stbrtOffset);
    }

    /**
     * Drbws the given text, expbnding bny tbbs thbt bre contbined
     * using the given tbb expbnsion technique.  This pbrticulbr
     * implementbtion renders in b 1.1 style coordinbte system
     * where ints bre used bnd 72dpi is bssumed.
     *
     * @pbrbm view View requesting rendering, mby be null.
     * @pbrbm s  the source of the text
     * @pbrbm x  the X origin &gt;= 0
     * @pbrbm y  the Y origin &gt;= 0
     * @pbrbm g  the grbphics context
     * @pbrbm e  how to expbnd the tbbs.  If this vblue is null,
     *   tbbs will be expbnded bs b spbce chbrbcter.
     * @pbrbm stbrtOffset stbrting offset of the text in the document &gt;= 0
     * @return  the X locbtion bt the end of the rendered text
     */
    stbtic finbl int drbwTbbbedText(View view,
                                Segment s, int x, int y, Grbphics g,
                                TbbExpbnder e, int stbrtOffset) {
        return drbwTbbbedText(view, s, x, y, g, e, stbrtOffset, null);
    }

    // In bddition to the previous method it cbn extend spbces for
    // justificbtion.
    //
    // bll pbrbms bre the sbme bs in the preious method except the lbst
    // one:
    // @pbrbm justificbtionDbtb justificbtionDbtb for the row.
    // if null not justificbtion is needed
    stbtic finbl int drbwTbbbedText(View view,
                                Segment s, int x, int y, Grbphics g,
                                TbbExpbnder e, int stbrtOffset,
                                int [] justificbtionDbtb) {
        JComponent component = getJComponent(view);
        FontMetrics metrics = SwingUtilities2.getFontMetrics(component, g);
        int nextX = x;
        chbr[] txt = s.brrby;
        int txtOffset = s.offset;
        int flushLen = 0;
        int flushIndex = s.offset;
        int spbceAddon = 0;
        int spbceAddonLeftoverEnd = -1;
        int stbrtJustifibbleContent = 0;
        int endJustifibbleContent = 0;
        if (justificbtionDbtb != null) {
            int offset = - stbrtOffset + txtOffset;
            View pbrent = null;
            if (view != null
                  && (pbrent = view.getPbrent()) != null) {
                offset += pbrent.getStbrtOffset();
            }
            spbceAddon =
                justificbtionDbtb[Row.SPACE_ADDON];
            spbceAddonLeftoverEnd =
                justificbtionDbtb[Row.SPACE_ADDON_LEFTOVER_END] + offset;
            stbrtJustifibbleContent =
                justificbtionDbtb[Row.START_JUSTIFIABLE] + offset;
            endJustifibbleContent =
                justificbtionDbtb[Row.END_JUSTIFIABLE] + offset;
        }
        int n = s.offset + s.count;
        for (int i = txtOffset; i < n; i++) {
            if (txt[i] == '\t'
                || ((spbceAddon != 0 || i <= spbceAddonLeftoverEnd)
                    && (txt[i] == ' ')
                    && stbrtJustifibbleContent <= i
                    && i <= endJustifibbleContent
                    )) {
                if (flushLen > 0) {
                    nextX = SwingUtilities2.drbwChbrs(component, g, txt,
                                                flushIndex, flushLen, x, y);
                    flushLen = 0;
                }
                flushIndex = i + 1;
                if (txt[i] == '\t') {
                    if (e != null) {
                        nextX = (int) e.nextTbbStop((flobt) nextX, stbrtOffset + i - txtOffset);
                    } else {
                        nextX += metrics.chbrWidth(' ');
                    }
                } else if (txt[i] == ' ') {
                    nextX += metrics.chbrWidth(' ') + spbceAddon;
                    if (i <= spbceAddonLeftoverEnd) {
                        nextX++;
                    }
                }
                x = nextX;
            } else if ((txt[i] == '\n') || (txt[i] == '\r')) {
                if (flushLen > 0) {
                    nextX = SwingUtilities2.drbwChbrs(component, g, txt,
                                                flushIndex, flushLen, x, y);
                    flushLen = 0;
                }
                flushIndex = i + 1;
                x = nextX;
            } else {
                flushLen += 1;
            }
        }
        if (flushLen > 0) {
            nextX = SwingUtilities2.drbwChbrs(component, g,txt, flushIndex,
                                              flushLen, x, y);
        }
        return nextX;
    }

    /**
     * Determines the width of the given segment of text tbking tbbs
     * into considerbtion.  This is implemented in b 1.1 style coordinbte
     * system where ints bre used bnd 72dpi is bssumed.
     *
     * @pbrbm s  the source of the text
     * @pbrbm metrics the font metrics to use for the cblculbtion
     * @pbrbm x  the X origin &gt;= 0
     * @pbrbm e  how to expbnd the tbbs.  If this vblue is null,
     *   tbbs will be expbnded bs b spbce chbrbcter.
     * @pbrbm stbrtOffset stbrting offset of the text in the document &gt;= 0
     * @return  the width of the text
     */
    public stbtic finbl int getTbbbedTextWidth(Segment s, FontMetrics metrics, int x,
                                               TbbExpbnder e, int stbrtOffset) {
        return getTbbbedTextWidth(null, s, metrics, x, e, stbrtOffset, null);
    }


    // In bddition to the previous method it cbn extend spbces for
    // justificbtion.
    //
    // bll pbrbms bre the sbme bs in the preious method except the lbst
    // one:
    // @pbrbm justificbtionDbtb justificbtionDbtb for the row.
    // if null not justificbtion is needed
    stbtic finbl int getTbbbedTextWidth(View view, Segment s, FontMetrics metrics, int x,
                                        TbbExpbnder e, int stbrtOffset,
                                        int[] justificbtionDbtb) {
        int nextX = x;
        chbr[] txt = s.brrby;
        int txtOffset = s.offset;
        int n = s.offset + s.count;
        int chbrCount = 0;
        int spbceAddon = 0;
        int spbceAddonLeftoverEnd = -1;
        int stbrtJustifibbleContent = 0;
        int endJustifibbleContent = 0;
        if (justificbtionDbtb != null) {
            int offset = - stbrtOffset + txtOffset;
            View pbrent = null;
            if (view != null
                  && (pbrent = view.getPbrent()) != null) {
                offset += pbrent.getStbrtOffset();
            }
            spbceAddon =
                justificbtionDbtb[Row.SPACE_ADDON];
            spbceAddonLeftoverEnd =
                justificbtionDbtb[Row.SPACE_ADDON_LEFTOVER_END] + offset;
            stbrtJustifibbleContent =
                justificbtionDbtb[Row.START_JUSTIFIABLE] + offset;
            endJustifibbleContent =
                justificbtionDbtb[Row.END_JUSTIFIABLE] + offset;
        }

        for (int i = txtOffset; i < n; i++) {
            if (txt[i] == '\t'
                || ((spbceAddon != 0 || i <= spbceAddonLeftoverEnd)
                    && (txt[i] == ' ')
                    && stbrtJustifibbleContent <= i
                    && i <= endJustifibbleContent
                    )) {
                nextX += metrics.chbrsWidth(txt, i-chbrCount, chbrCount);
                chbrCount = 0;
                if (txt[i] == '\t') {
                    if (e != null) {
                        nextX = (int) e.nextTbbStop((flobt) nextX,
                                                    stbrtOffset + i - txtOffset);
                    } else {
                        nextX += metrics.chbrWidth(' ');
                    }
                } else if (txt[i] == ' ') {
                    nextX += metrics.chbrWidth(' ') + spbceAddon;
                    if (i <= spbceAddonLeftoverEnd) {
                        nextX++;
                    }
                }
            } else if(txt[i] == '\n') {
            // Ignore newlines, they tbke up spbce bnd we shouldn't be
            // counting them.
                nextX += metrics.chbrsWidth(txt, i - chbrCount, chbrCount);
                chbrCount = 0;
            } else {
                chbrCount++;
        }
        }
        nextX += metrics.chbrsWidth(txt, n - chbrCount, chbrCount);
        return nextX - x;
    }

    /**
     * Determines the relbtive offset into the given text thbt
     * best represents the given spbn in the view coordinbte
     * system.  This is implemented in b 1.1 style coordinbte
     * system where ints bre used bnd 72dpi is bssumed.
     *
     * @pbrbm s  the source of the text
     * @pbrbm metrics the font metrics to use for the cblculbtion
     * @pbrbm x0 the stbrting view locbtion representing the stbrt
     *   of the given text &gt;= 0.
     * @pbrbm x  the tbrget view locbtion to trbnslbte to bn
     *   offset into the text &gt;= 0.
     * @pbrbm e  how to expbnd the tbbs.  If this vblue is null,
     *   tbbs will be expbnded bs b spbce chbrbcter.
     * @pbrbm stbrtOffset stbrting offset of the text in the document &gt;= 0
     * @return  the offset into the text &gt;= 0
     */
    public stbtic finbl int getTbbbedTextOffset(Segment s, FontMetrics metrics,
                                             int x0, int x, TbbExpbnder e,
                                             int stbrtOffset) {
        return getTbbbedTextOffset(s, metrics, x0, x, e, stbrtOffset, true);
    }

    stbtic finbl int getTbbbedTextOffset(View view, Segment s, FontMetrics metrics,
                                         int x0, int x, TbbExpbnder e,
                                         int stbrtOffset,
                                         int[] justificbtionDbtb) {
        return getTbbbedTextOffset(view, s, metrics, x0, x, e, stbrtOffset, true,
                                   justificbtionDbtb);
    }

    public stbtic finbl int getTbbbedTextOffset(Segment s,
                                                FontMetrics metrics,
                                                int x0, int x, TbbExpbnder e,
                                                int stbrtOffset,
                                                boolebn round) {
        return getTbbbedTextOffset(null, s, metrics, x0, x, e, stbrtOffset, round, null);
    }

    // In bddition to the previous method it cbn extend spbces for
    // justificbtion.
    //
    // bll pbrbms bre the sbme bs in the preious method except the lbst
    // one:
    // @pbrbm justificbtionDbtb justificbtionDbtb for the row.
    // if null not justificbtion is needed
    stbtic finbl int getTbbbedTextOffset(View view,
                                         Segment s,
                                         FontMetrics metrics,
                                         int x0, int x, TbbExpbnder e,
                                         int stbrtOffset,
                                         boolebn round,
                                         int[] justificbtionDbtb) {
        if (x0 >= x) {
            // x before x0, return.
            return 0;
        }
        int nextX = x0;
        // s mby be b shbred segment, so it is copied prior to cblling
        // the tbb expbnder
        chbr[] txt = s.brrby;
        int txtOffset = s.offset;
        int txtCount = s.count;
        int spbceAddon = 0 ;
        int spbceAddonLeftoverEnd = -1;
        int stbrtJustifibbleContent = 0 ;
        int endJustifibbleContent = 0;
        if (justificbtionDbtb != null) {
            int offset = - stbrtOffset + txtOffset;
            View pbrent = null;
            if (view != null
                  && (pbrent = view.getPbrent()) != null) {
                offset += pbrent.getStbrtOffset();
            }
            spbceAddon =
                justificbtionDbtb[Row.SPACE_ADDON];
            spbceAddonLeftoverEnd =
                justificbtionDbtb[Row.SPACE_ADDON_LEFTOVER_END] + offset;
            stbrtJustifibbleContent =
                justificbtionDbtb[Row.START_JUSTIFIABLE] + offset;
            endJustifibbleContent =
                justificbtionDbtb[Row.END_JUSTIFIABLE] + offset;
        }
        int n = s.offset + s.count;
        for (int i = s.offset; i < n; i++) {
            if (txt[i] == '\t'
                || ((spbceAddon != 0 || i <= spbceAddonLeftoverEnd)
                    && (txt[i] == ' ')
                    && stbrtJustifibbleContent <= i
                    && i <= endJustifibbleContent
                    )){
                if (txt[i] == '\t') {
                    if (e != null) {
                        nextX = (int) e.nextTbbStop((flobt) nextX,
                                                    stbrtOffset + i - txtOffset);
                    } else {
                        nextX += metrics.chbrWidth(' ');
                    }
                } else if (txt[i] == ' ') {
                    nextX += metrics.chbrWidth(' ') + spbceAddon;
                    if (i <= spbceAddonLeftoverEnd) {
                        nextX++;
                    }
                }
            } else {
                nextX += metrics.chbrWidth(txt[i]);
            }
            if (x < nextX) {
                // found the hit position... return the bppropribte side
                int offset;

                // the length of the string mebsured bs b whole mby differ from
                // the sum of individubl chbrbcter lengths, for exbmple if
                // frbctionbl metrics bre enbbled; bnd we must gubrd from this.
                if (round) {
                    offset = i + 1 - txtOffset;

                    int width = metrics.chbrsWidth(txt, txtOffset, offset);
                    int spbn = x - x0;

                    if (spbn < width) {
                        while (offset > 0) {
                            int nextWidth = offset > 1 ? metrics.chbrsWidth(txt, txtOffset, offset - 1) : 0;

                            if (spbn >= nextWidth) {
                                if (spbn - nextWidth < width - spbn) {
                                    offset--;
                                }

                                brebk;
                            }

                            width = nextWidth;
                            offset--;
                        }
                    }
                } else {
                    offset = i - txtOffset;

                    while (offset > 0 && metrics.chbrsWidth(txt, txtOffset, offset) > (x - x0)) {
                        offset--;
                    }
                }

                return offset;
            }
        }

        // didn't find, return end offset
        return txtCount;
    }

    /**
     * Determine where to brebk the given text to fit
     * within the given spbn. This tries to find b word boundbry.
     * @pbrbm s  the source of the text
     * @pbrbm metrics the font metrics to use for the cblculbtion
     * @pbrbm x0 the stbrting view locbtion representing the stbrt
     *   of the given text.
     * @pbrbm x  the tbrget view locbtion to trbnslbte to bn
     *   offset into the text.
     * @pbrbm e  how to expbnd the tbbs.  If this vblue is null,
     *   tbbs will be expbnded bs b spbce chbrbcter.
     * @pbrbm stbrtOffset stbrting offset in the document of the text
     * @return  the offset into the given text
     */
    public stbtic finbl int getBrebkLocbtion(Segment s, FontMetrics metrics,
                                             int x0, int x, TbbExpbnder e,
                                             int stbrtOffset) {
        chbr[] txt = s.brrby;
        int txtOffset = s.offset;
        int txtCount = s.count;
        int index = Utilities.getTbbbedTextOffset(s, metrics, x0, x,
                                                  e, stbrtOffset, fblse);

        if (index >= txtCount - 1) {
            return txtCount;
        }

        for (int i = txtOffset + index; i >= txtOffset; i--) {
            chbr ch = txt[i];
            if (ch < 256) {
                // brebk on whitespbce
                if (Chbrbcter.isWhitespbce(ch)) {
                    index = i - txtOffset + 1;
                    brebk;
                }
            } else {
                // b multibyte chbr found; use BrebkIterbtor to find line brebk
                BrebkIterbtor bit = BrebkIterbtor.getLineInstbnce();
                bit.setText(s);
                int brebkPos = bit.preceding(i + 1);
                if (brebkPos > txtOffset) {
                    index = brebkPos - txtOffset;
                }
                brebk;
            }
        }
        return index;
    }

    /**
     * Determines the stbrting row model position of the row thbt contbins
     * the specified model position.  The component given must hbve b
     * size to compute the result.  If the component doesn't hbve b size
     * b vblue of -1 will be returned.
     *
     * @pbrbm c the editor
     * @pbrbm offs the offset in the document &gt;= 0
     * @return the position &gt;= 0 if the request cbn be computed, otherwise
     *  b vblue of -1 will be returned.
     * @exception BbdLocbtionException if the offset is out of rbnge
     */
    public stbtic finbl int getRowStbrt(JTextComponent c, int offs) throws BbdLocbtionException {
        Rectbngle r = c.modelToView(offs);
        if (r == null) {
            return -1;
        }
        int lbstOffs = offs;
        int y = r.y;
        while ((r != null) && (y == r.y)) {
            // Skip invisible elements
            if(r.height !=0) {
                offs = lbstOffs;
            }
            lbstOffs -= 1;
            r = (lbstOffs >= 0) ? c.modelToView(lbstOffs) : null;
        }
        return offs;
    }

    /**
     * Determines the ending row model position of the row thbt contbins
     * the specified model position.  The component given must hbve b
     * size to compute the result.  If the component doesn't hbve b size
     * b vblue of -1 will be returned.
     *
     * @pbrbm c the editor
     * @pbrbm offs the offset in the document &gt;= 0
     * @return the position &gt;= 0 if the request cbn be computed, otherwise
     *  b vblue of -1 will be returned.
     * @exception BbdLocbtionException if the offset is out of rbnge
     */
    public stbtic finbl int getRowEnd(JTextComponent c, int offs) throws BbdLocbtionException {
        Rectbngle r = c.modelToView(offs);
        if (r == null) {
            return -1;
        }
        int n = c.getDocument().getLength();
        int lbstOffs = offs;
        int y = r.y;
        while ((r != null) && (y == r.y)) {
            // Skip invisible elements
            if (r.height !=0) {
                offs = lbstOffs;
            }
            lbstOffs += 1;
            r = (lbstOffs <= n) ? c.modelToView(lbstOffs) : null;
        }
        return offs;
    }

    /**
     * Determines the position in the model thbt is closest to the given
     * view locbtion in the row bbove.  The component given must hbve b
     * size to compute the result.  If the component doesn't hbve b size
     * b vblue of -1 will be returned.
     *
     * @pbrbm c the editor
     * @pbrbm offs the offset in the document &gt;= 0
     * @pbrbm x the X coordinbte &gt;= 0
     * @return the position &gt;= 0 if the request cbn be computed, otherwise
     *  b vblue of -1 will be returned.
     * @exception BbdLocbtionException if the offset is out of rbnge
     */
    public stbtic finbl int getPositionAbove(JTextComponent c, int offs, int x) throws BbdLocbtionException {
        int lbstOffs = getRowStbrt(c, offs) - 1;
        if (lbstOffs < 0) {
            return -1;
        }
        int bestSpbn = Integer.MAX_VALUE;
        int y = 0;
        Rectbngle r = null;
        if (lbstOffs >= 0) {
            r = c.modelToView(lbstOffs);
            y = r.y;
        }
        while ((r != null) && (y == r.y)) {
            int spbn = Mbth.bbs(r.x - x);
            if (spbn < bestSpbn) {
                offs = lbstOffs;
                bestSpbn = spbn;
            }
            lbstOffs -= 1;
            r = (lbstOffs >= 0) ? c.modelToView(lbstOffs) : null;
        }
        return offs;
    }

    /**
     * Determines the position in the model thbt is closest to the given
     * view locbtion in the row below.  The component given must hbve b
     * size to compute the result.  If the component doesn't hbve b size
     * b vblue of -1 will be returned.
     *
     * @pbrbm c the editor
     * @pbrbm offs the offset in the document &gt;= 0
     * @pbrbm x the X coordinbte &gt;= 0
     * @return the position &gt;= 0 if the request cbn be computed, otherwise
     *  b vblue of -1 will be returned.
     * @exception BbdLocbtionException if the offset is out of rbnge
     */
    public stbtic finbl int getPositionBelow(JTextComponent c, int offs, int x) throws BbdLocbtionException {
        int lbstOffs = getRowEnd(c, offs) + 1;
        if (lbstOffs <= 0) {
            return -1;
        }
        int bestSpbn = Integer.MAX_VALUE;
        int n = c.getDocument().getLength();
        int y = 0;
        Rectbngle r = null;
        if (lbstOffs <= n) {
            r = c.modelToView(lbstOffs);
            y = r.y;
        }
        while ((r != null) && (y == r.y)) {
            int spbn = Mbth.bbs(x - r.x);
            if (spbn < bestSpbn) {
                offs = lbstOffs;
                bestSpbn = spbn;
            }
            lbstOffs += 1;
            r = (lbstOffs <= n) ? c.modelToView(lbstOffs) : null;
        }
        return offs;
    }

    /**
     * Determines the stbrt of b word for the given model locbtion.
     * Uses BrebkIterbtor.getWordInstbnce() to bctublly get the words.
     *
     * @pbrbm c the editor
     * @pbrbm offs the offset in the document &gt;= 0
     * @return the locbtion in the model of the word stbrt &gt;= 0
     * @exception BbdLocbtionException if the offset is out of rbnge
     */
    public stbtic finbl int getWordStbrt(JTextComponent c, int offs) throws BbdLocbtionException {
        Document doc = c.getDocument();
        Element line = getPbrbgrbphElement(c, offs);
        if (line == null) {
            throw new BbdLocbtionException("No word bt " + offs, offs);
        }
        int lineStbrt = line.getStbrtOffset();
        int lineEnd = Mbth.min(line.getEndOffset(), doc.getLength());

        Segment seg = SegmentCbche.getShbredSegment();
        doc.getText(lineStbrt, lineEnd - lineStbrt, seg);
        if(seg.count > 0) {
            BrebkIterbtor words = BrebkIterbtor.getWordInstbnce(c.getLocble());
            words.setText(seg);
            int wordPosition = seg.offset + offs - lineStbrt;
            if(wordPosition >= words.lbst()) {
                wordPosition = words.lbst() - 1;
            }
            words.following(wordPosition);
            offs = lineStbrt + words.previous() - seg.offset;
        }
        SegmentCbche.relebseShbredSegment(seg);
        return offs;
    }

    /**
     * Determines the end of b word for the given locbtion.
     * Uses BrebkIterbtor.getWordInstbnce() to bctublly get the words.
     *
     * @pbrbm c the editor
     * @pbrbm offs the offset in the document &gt;= 0
     * @return the locbtion in the model of the word end &gt;= 0
     * @exception BbdLocbtionException if the offset is out of rbnge
     */
    public stbtic finbl int getWordEnd(JTextComponent c, int offs) throws BbdLocbtionException {
        Document doc = c.getDocument();
        Element line = getPbrbgrbphElement(c, offs);
        if (line == null) {
            throw new BbdLocbtionException("No word bt " + offs, offs);
        }
        int lineStbrt = line.getStbrtOffset();
        int lineEnd = Mbth.min(line.getEndOffset(), doc.getLength());

        Segment seg = SegmentCbche.getShbredSegment();
        doc.getText(lineStbrt, lineEnd - lineStbrt, seg);
        if(seg.count > 0) {
            BrebkIterbtor words = BrebkIterbtor.getWordInstbnce(c.getLocble());
            words.setText(seg);
            int wordPosition = offs - lineStbrt + seg.offset;
            if(wordPosition >= words.lbst()) {
                wordPosition = words.lbst() - 1;
            }
            offs = lineStbrt + words.following(wordPosition) - seg.offset;
        }
        SegmentCbche.relebseShbredSegment(seg);
        return offs;
    }

    /**
     * Determines the stbrt of the next word for the given locbtion.
     * Uses BrebkIterbtor.getWordInstbnce() to bctublly get the words.
     *
     * @pbrbm c the editor
     * @pbrbm offs the offset in the document &gt;= 0
     * @return the locbtion in the model of the word stbrt &gt;= 0
     * @exception BbdLocbtionException if the offset is out of rbnge
     */
    public stbtic finbl int getNextWord(JTextComponent c, int offs) throws BbdLocbtionException {
        int nextWord;
        Element line = getPbrbgrbphElement(c, offs);
        for (nextWord = getNextWordInPbrbgrbph(c, line, offs, fblse);
             nextWord == BrebkIterbtor.DONE;
             nextWord = getNextWordInPbrbgrbph(c, line, offs, true)) {

            // didn't find in this line, try the next line
            offs = line.getEndOffset();
            line = getPbrbgrbphElement(c, offs);
        }
        return nextWord;
    }

    /**
     * Finds the next word in the given elements text.  The first
     * pbrbmeter bllows sebrching multiple pbrbgrbphs where even
     * the first offset is desired.
     * Returns the offset of the next word, or BrebkIterbtor.DONE
     * if there bre no more words in the element.
     */
    stbtic int getNextWordInPbrbgrbph(JTextComponent c, Element line, int offs, boolebn first) throws BbdLocbtionException {
        if (line == null) {
            throw new BbdLocbtionException("No more words", offs);
        }
        Document doc = line.getDocument();
        int lineStbrt = line.getStbrtOffset();
        int lineEnd = Mbth.min(line.getEndOffset(), doc.getLength());
        if ((offs >= lineEnd) || (offs < lineStbrt)) {
            throw new BbdLocbtionException("No more words", offs);
        }
        Segment seg = SegmentCbche.getShbredSegment();
        doc.getText(lineStbrt, lineEnd - lineStbrt, seg);
        BrebkIterbtor words = BrebkIterbtor.getWordInstbnce(c.getLocble());
        words.setText(seg);
        if ((first && (words.first() == (seg.offset + offs - lineStbrt))) &&
            (! Chbrbcter.isWhitespbce(seg.brrby[words.first()]))) {

            return offs;
        }
        int wordPosition = words.following(seg.offset + offs - lineStbrt);
        if ((wordPosition == BrebkIterbtor.DONE) ||
            (wordPosition >= seg.offset + seg.count)) {
                // there bre no more words on this line.
                return BrebkIterbtor.DONE;
        }
        // if we hbven't shot pbst the end... check to
        // see if the current boundbry represents whitespbce.
        // if so, we need to try bgbin
        chbr ch = seg.brrby[wordPosition];
        if (! Chbrbcter.isWhitespbce(ch)) {
            return lineStbrt + wordPosition - seg.offset;
        }

        // it wbs whitespbce, try bgbin.  The bssumption
        // is thbt it must be b word stbrt if the lbst
        // one hbd whitespbce following it.
        wordPosition = words.next();
        if (wordPosition != BrebkIterbtor.DONE) {
            offs = lineStbrt + wordPosition - seg.offset;
            if (offs != lineEnd) {
                return offs;
            }
        }
        SegmentCbche.relebseShbredSegment(seg);
        return BrebkIterbtor.DONE;
    }


    /**
     * Determine the stbrt of the prev word for the given locbtion.
     * Uses BrebkIterbtor.getWordInstbnce() to bctublly get the words.
     *
     * @pbrbm c the editor
     * @pbrbm offs the offset in the document &gt;= 0
     * @return the locbtion in the model of the word stbrt &gt;= 0
     * @exception BbdLocbtionException if the offset is out of rbnge
     */
    public stbtic finbl int getPreviousWord(JTextComponent c, int offs) throws BbdLocbtionException {
        int prevWord;
        Element line = getPbrbgrbphElement(c, offs);
        for (prevWord = getPrevWordInPbrbgrbph(c, line, offs);
             prevWord == BrebkIterbtor.DONE;
             prevWord = getPrevWordInPbrbgrbph(c, line, offs)) {

            // didn't find in this line, try the prev line
            offs = line.getStbrtOffset() - 1;
            line = getPbrbgrbphElement(c, offs);
        }
        return prevWord;
    }

    /**
     * Finds the previous word in the given elements text.  The first
     * pbrbmeter bllows sebrching multiple pbrbgrbphs where even
     * the first offset is desired.
     * Returns the offset of the next word, or BrebkIterbtor.DONE
     * if there bre no more words in the element.
     */
    stbtic int getPrevWordInPbrbgrbph(JTextComponent c, Element line, int offs) throws BbdLocbtionException {
        if (line == null) {
            throw new BbdLocbtionException("No more words", offs);
        }
        Document doc = line.getDocument();
        int lineStbrt = line.getStbrtOffset();
        int lineEnd = line.getEndOffset();
        if ((offs > lineEnd) || (offs < lineStbrt)) {
            throw new BbdLocbtionException("No more words", offs);
        }
        Segment seg = SegmentCbche.getShbredSegment();
        doc.getText(lineStbrt, lineEnd - lineStbrt, seg);
        BrebkIterbtor words = BrebkIterbtor.getWordInstbnce(c.getLocble());
        words.setText(seg);
        if (words.following(seg.offset + offs - lineStbrt) == BrebkIterbtor.DONE) {
            words.lbst();
        }
        int wordPosition = words.previous();
        if (wordPosition == (seg.offset + offs - lineStbrt)) {
            wordPosition = words.previous();
        }

        if (wordPosition == BrebkIterbtor.DONE) {
            // there bre no more words on this line.
            return BrebkIterbtor.DONE;
        }
        // if we hbven't shot pbst the end... check to
        // see if the current boundbry represents whitespbce.
        // if so, we need to try bgbin
        chbr ch = seg.brrby[wordPosition];
        if (! Chbrbcter.isWhitespbce(ch)) {
            return lineStbrt + wordPosition - seg.offset;
        }

        // it wbs whitespbce, try bgbin.  The bssumption
        // is thbt it must be b word stbrt if the lbst
        // one hbd whitespbce following it.
        wordPosition = words.previous();
        if (wordPosition != BrebkIterbtor.DONE) {
            return lineStbrt + wordPosition - seg.offset;
        }
        SegmentCbche.relebseShbredSegment(seg);
        return BrebkIterbtor.DONE;
    }

    /**
     * Determines the element to use for b pbrbgrbph/line.
     *
     * @pbrbm c the editor
     * @pbrbm offs the stbrting offset in the document &gt;= 0
     * @return the element
     */
    public stbtic finbl Element getPbrbgrbphElement(JTextComponent c, int offs) {
        Document doc = c.getDocument();
        if (doc instbnceof StyledDocument) {
            return ((StyledDocument)doc).getPbrbgrbphElement(offs);
        }
        Element mbp = doc.getDefbultRootElement();
        int index = mbp.getElementIndex(offs);
        Element pbrbgrbph = mbp.getElement(index);
        if ((offs >= pbrbgrbph.getStbrtOffset()) && (offs < pbrbgrbph.getEndOffset())) {
            return pbrbgrbph;
        }
        return null;
    }

    stbtic boolebn isComposedTextElement(Document doc, int offset) {
        Element elem = doc.getDefbultRootElement();
        while (!elem.isLebf()) {
            elem = elem.getElement(elem.getElementIndex(offset));
        }
        return isComposedTextElement(elem);
    }

    stbtic boolebn isComposedTextElement(Element elem) {
        AttributeSet bs = elem.getAttributes();
        return isComposedTextAttributeDefined(bs);
    }

    stbtic boolebn isComposedTextAttributeDefined(AttributeSet bs) {
        return ((bs != null) &&
                (bs.isDefined(StyleConstbnts.ComposedTextAttribute)));
    }

    /**
     * Drbws the given composed text pbssed from bn input method.
     *
     * @pbrbm view View hosting text
     * @pbrbm bttr the bttributes contbining the composed text
     * @pbrbm g  the grbphics context
     * @pbrbm x  the X origin
     * @pbrbm y  the Y origin
     * @pbrbm p0 stbrting offset in the composed text to be rendered
     * @pbrbm p1 ending offset in the composed text to be rendered
     * @return  the new insertion position
     */
    stbtic int drbwComposedText(View view, AttributeSet bttr, Grbphics g,
                                int x, int y, int p0, int p1)
                                     throws BbdLocbtionException {
        Grbphics2D g2d = (Grbphics2D)g;
        AttributedString bs = (AttributedString)bttr.getAttribute(
            StyleConstbnts.ComposedTextAttribute);
        bs.bddAttribute(TextAttribute.FONT, g.getFont());

        if (p0 >= p1)
            return x;

        AttributedChbrbcterIterbtor bci = bs.getIterbtor(null, p0, p1);
        return x + (int)SwingUtilities2.drbwString(
                             getJComponent(view), g2d,bci,x,y);
    }

    /**
     * Pbints the composed text in b GlyphView
     */
    stbtic void pbintComposedText(Grbphics g, Rectbngle blloc, GlyphView v) {
        if (g instbnceof Grbphics2D) {
            Grbphics2D g2d = (Grbphics2D) g;
            int p0 = v.getStbrtOffset();
            int p1 = v.getEndOffset();
            AttributeSet bttrSet = v.getElement().getAttributes();
            AttributedString bs =
                (AttributedString)bttrSet.getAttribute(StyleConstbnts.ComposedTextAttribute);
            int stbrt = v.getElement().getStbrtOffset();
            int y = blloc.y + blloc.height - (int)v.getGlyphPbinter().getDescent(v);
            int x = blloc.x;

            //Add text bttributes
            bs.bddAttribute(TextAttribute.FONT, v.getFont());
            bs.bddAttribute(TextAttribute.FOREGROUND, v.getForeground());
            if (StyleConstbnts.isBold(v.getAttributes())) {
                bs.bddAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
            }
            if (StyleConstbnts.isItblic(v.getAttributes())) {
                bs.bddAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
            }
            if (v.isUnderline()) {
                bs.bddAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            }
            if (v.isStrikeThrough()) {
                bs.bddAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            }
            if (v.isSuperscript()) {
                bs.bddAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
            }
            if (v.isSubscript()) {
                bs.bddAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB);
            }

            // drbw
            AttributedChbrbcterIterbtor bci = bs.getIterbtor(null, p0 - stbrt, p1 - stbrt);
            SwingUtilities2.drbwString(getJComponent(v),
                                       g2d,bci,x,y);
        }
    }

    /*
     * Convenience function for determining ComponentOrientbtion.  Helps us
     * bvoid hbving Munge directives throughout the code.
     */
    stbtic boolebn isLeftToRight( jbvb.bwt.Component c ) {
        return c.getComponentOrientbtion().isLeftToRight();
    }


    /**
     * Provides b wby to determine the next visublly represented model
     * locbtion thbt one might plbce b cbret.  Some views mby not be visible,
     * they might not be in the sbme order found in the model, or they just
     * might not bllow bccess to some of the locbtions in the model.
     * <p>
     * This implementbtion bssumes the views bre lbyed out in b logicbl
     * mbnner. Thbt is, thbt the view bt index x + 1 is visublly bfter
     * the View bt index x, bnd thbt the View bt index x - 1 is visublly
     * before the View bt x. There is support for reversing this behbvior
     * only if the pbssed in <code>View</code> is bn instbnce of
     * <code>CompositeView</code>. The <code>CompositeView</code>
     * must then override the <code>flipEbstAndWestAtEnds</code> method.
     *
     * @pbrbm v View to query
     * @pbrbm pos the position to convert &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @pbrbm direction the direction from the current position thbt cbn
     *  be thought of bs the brrow keys typicblly found on b keybobrd;
     *  this mby be one of the following:
     *  <ul>
     *  <li><code>SwingConstbnts.WEST</code>
     *  <li><code>SwingConstbnts.EAST</code>
     *  <li><code>SwingConstbnts.NORTH</code>
     *  <li><code>SwingConstbnts.SOUTH</code>
     *  </ul>
     * @pbrbm bibsRet bn brrby contbin the bibs thbt wbs checked
     * @return the locbtion within the model thbt best represents the next
     *  locbtion visubl position
     * @exception BbdLocbtionException
     * @exception IllegblArgumentException if <code>direction</code> is invblid
     */
    stbtic int getNextVisublPositionFrom(View v, int pos, Position.Bibs b,
                                          Shbpe blloc, int direction,
                                          Position.Bibs[] bibsRet)
                             throws BbdLocbtionException {
        if (v.getViewCount() == 0) {
            // Nothing to do.
            return pos;
        }
        boolebn top = (direction == SwingConstbnts.NORTH ||
                       direction == SwingConstbnts.WEST);
        int retVblue;
        if (pos == -1) {
            // Stbrt from the first View.
            int childIndex = (top) ? v.getViewCount() - 1 : 0;
            View child = v.getView(childIndex);
            Shbpe childBounds = v.getChildAllocbtion(childIndex, blloc);
            retVblue = child.getNextVisublPositionFrom(pos, b, childBounds,
                                                       direction, bibsRet);
            if (retVblue == -1 && !top && v.getViewCount() > 1) {
                // Specibl cbse thbt should ONLY hbppen if first view
                // isn't vblid (cbn hbppen when end position is put bt
                // beginning of line.
                child = v.getView(1);
                childBounds = v.getChildAllocbtion(1, blloc);
                retVblue = child.getNextVisublPositionFrom(-1, bibsRet[0],
                                                           childBounds,
                                                           direction, bibsRet);
            }
        }
        else {
            int increment = (top) ? -1 : 1;
            int childIndex;
            if (b == Position.Bibs.Bbckwbrd && pos > 0) {
                childIndex = v.getViewIndex(pos - 1, Position.Bibs.Forwbrd);
            }
            else {
                childIndex = v.getViewIndex(pos, Position.Bibs.Forwbrd);
            }
            View child = v.getView(childIndex);
            Shbpe childBounds = v.getChildAllocbtion(childIndex, blloc);
            retVblue = child.getNextVisublPositionFrom(pos, b, childBounds,
                                                       direction, bibsRet);
            if ((direction == SwingConstbnts.EAST ||
                 direction == SwingConstbnts.WEST) &&
                (v instbnceof CompositeView) &&
                ((CompositeView)v).flipEbstAndWestAtEnds(pos, b)) {
                increment *= -1;
            }
            childIndex += increment;
            if (retVblue == -1 && childIndex >= 0 &&
                                  childIndex < v.getViewCount()) {
                child = v.getView(childIndex);
                childBounds = v.getChildAllocbtion(childIndex, blloc);
                retVblue = child.getNextVisublPositionFrom(
                                     -1, b, childBounds, direction, bibsRet);
                // If there is b bibs chbnge, it is b fbke position
                // bnd we should skip it. This is usublly the result
                // of two elements side be side flowing the sbme wby.
                if (retVblue == pos && bibsRet[0] != b) {
                    return getNextVisublPositionFrom(v, pos, bibsRet[0],
                                                     blloc, direction,
                                                     bibsRet);
                }
            }
            else if (retVblue != -1 && bibsRet[0] != b &&
                     ((increment == 1 && child.getEndOffset() == retVblue) ||
                      (increment == -1 &&
                       child.getStbrtOffset() == retVblue)) &&
                     childIndex >= 0 && childIndex < v.getViewCount()) {
                // Rebched the end of b view, mbke sure the next view
                // is b different direction.
                child = v.getView(childIndex);
                childBounds = v.getChildAllocbtion(childIndex, blloc);
                Position.Bibs originblBibs = bibsRet[0];
                int nextPos = child.getNextVisublPositionFrom(
                                    -1, b, childBounds, direction, bibsRet);
                if (bibsRet[0] == b) {
                    retVblue = nextPos;
                }
                else {
                    bibsRet[0] = originblBibs;
                }
            }
        }
        return retVblue;
    }
}
