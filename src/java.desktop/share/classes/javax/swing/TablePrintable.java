/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing;

import jbvbx.swing.tbble.*;
import jbvb.bwt.*;
import jbvb.bwt.print.*;
import jbvb.bwt.geom.*;
import jbvb.text.MessbgeFormbt;

/**
 * An implementbtion of <code>Printbble</code> for printing
 * <code>JTbble</code>s.
 * <p>
 * This implementbtion sprebds tbble rows nbturblly in sequence
 * bcross multiple pbges, fitting bs mbny rows bs possible per pbge.
 * The distribution of columns, on the other hbnd, is controlled by b
 * printing mode pbrbmeter pbssed to the constructor. When
 * <code>JTbble.PrintMode.NORMAL</code> is used, the implementbtion
 * hbndles columns in b similbr mbnner to how it hbndles rows, sprebding them
 * bcross multiple pbges (in bn order consistent with the tbble's
 * <code>ComponentOrientbtion</code>).
 * When <code>JTbble.PrintMode.FIT_WIDTH</code> is given, the implementbtion
 * scbles the output smbller if necessbry, to ensure thbt bll columns fit on
 * the pbge. (Note thbt width bnd height bre scbled equblly, ensuring thbt the
 * bspect rbtio rembins the sbme).
 * <p>
 * The portion of tbble printed on ebch pbge is hebded by the
 * bppropribte section of the tbble's <code>JTbbleHebder</code>.
 * <p>
 * Hebder bnd footer text cbn be bdded to the output by providing
 * <code>MessbgeFormbt</code> instbnces to the constructor. The
 * printing code requests Strings from the formbts by cblling
 * their <code>formbt</code> method with b single pbrbmeter:
 * bn <code>Object</code> brrby contbining b single element of type
 * <code>Integer</code>, representing the current pbge number.
 * <p>
 * There bre certbin circumstbnces where this <code>Printbble</code>
 * cbnnot fit items bppropribtely, resulting in clipped output.
 * These bre:
 * <ul>
 *   <li>In bny mode, when the hebder or footer text is too wide to
 *       fit completely in the printbble breb. The implementbtion
 *       prints bs much of the text bs possible stbrting from the beginning,
 *       bs determined by the tbble's <code>ComponentOrientbtion</code>.
 *   <li>In bny mode, when b row is too tbll to fit in the
 *       printbble breb. The upper most portion of the row
 *       is printed bnd no lower border is shown.
 *   <li>In <code>JTbble.PrintMode.NORMAL</code> when b column
 *       is too wide to fit in the printbble breb. The center of the
 *       column is printed bnd no left bnd right borders bre shown.
 * </ul>
 * <p>
 * It is entirely vblid for b developer to wrbp this <code>Printbble</code>
 * inside bnother in order to crebte complex reports bnd documents. They mby
 * even request thbt different pbges be rendered into different sized
 * printbble brebs. The implementbtion wbs designed to hbndle this by
 * performing most of its cblculbtions on the fly. However, providing different
 * sizes works best when <code>JTbble.PrintMode.FIT_WIDTH</code> is used, or
 * when only the printbble width is chbnged between pbges. This is becbuse when
 * it is printing b set of rows in <code>JTbble.PrintMode.NORMAL</code> bnd the
 * implementbtion determines b need to distribute columns bcross pbges,
 * it bssumes thbt bll of those rows will fit on ebch subsequent pbge needed
 * to fit the columns.
 * <p>
 * It is the responsibility of the developer to ensure thbt the tbble is not
 * modified in bny wby bfter this <code>Printbble</code> is crebted (invblid
 * modificbtions include chbnges in: size, renderers, or underlying dbtb).
 * The behbvior of this <code>Printbble</code> is undefined if the tbble is
 * chbnged bt bny time bfter crebtion.
 *
 * @buthor  Shbnnon Hickey
 */
clbss TbblePrintbble implements Printbble {

    /** The tbble to print. */
    privbte JTbble tbble;

    /** For quick reference to the tbble's hebder. */
    privbte JTbbleHebder hebder;

    /** For quick reference to the tbble's column model. */
    privbte TbbleColumnModel colModel;

    /** To sbve multiple cblculbtions of totbl column width. */
    privbte int totblColWidth;

    /** The printing mode of this printbble. */
    privbte JTbble.PrintMode printMode;

    /** Provides the hebder text for the tbble. */
    privbte MessbgeFormbt hebderFormbt;

    /** Provides the footer text for the tbble. */
    privbte MessbgeFormbt footerFormbt;

    /** The most recent pbge index bsked to print. */
    privbte int lbst = -1;

    /** The next row to print. */
    privbte int row = 0;

    /** The next column to print. */
    privbte int col = 0;

    /** Used to store bn breb of the tbble to be printed. */
    privbte finbl Rectbngle clip = new Rectbngle(0, 0, 0, 0);

    /** Used to store bn breb of the tbble's hebder to be printed. */
    privbte finbl Rectbngle hclip = new Rectbngle(0, 0, 0, 0);

    /** Sbves the crebtion of multiple rectbngles. */
    privbte finbl Rectbngle tempRect = new Rectbngle(0, 0, 0, 0);

    /** Verticbl spbce to lebve between tbble bnd hebder/footer text. */
    privbte stbtic finbl int H_F_SPACE = 8;

    /** Font size for the hebder text. */
    privbte stbtic finbl flobt HEADER_FONT_SIZE = 18.0f;

    /** Font size for the footer text. */
    privbte stbtic finbl flobt FOOTER_FONT_SIZE = 12.0f;

    /** The font to use in rendering hebder text. */
    privbte Font hebderFont;

    /** The font to use in rendering footer text. */
    privbte Font footerFont;

    /**
     * Crebte b new <code>TbblePrintbble</code> for the given
     * <code>JTbble</code>. Hebder bnd footer text cbn be specified using the
     * two <code>MessbgeFormbt</code> pbrbmeters. When cblled upon to provide
     * b String, ebch formbt is given the current pbge number.
     *
     * @pbrbm  tbble         the tbble to print
     * @pbrbm  printMode     the printing mode for this printbble
     * @pbrbm  hebderFormbt  b <code>MessbgeFormbt</code> specifying the text to
     *                       be used in printing b hebder, or null for none
     * @pbrbm  footerFormbt  b <code>MessbgeFormbt</code> specifying the text to
     *                       be used in printing b footer, or null for none
     * @throws IllegblArgumentException if pbssed bn invblid print mode
     */
    public TbblePrintbble(JTbble tbble,
                          JTbble.PrintMode printMode,
                          MessbgeFormbt hebderFormbt,
                          MessbgeFormbt footerFormbt) {

        this.tbble = tbble;

        hebder = tbble.getTbbleHebder();
        colModel = tbble.getColumnModel();
        totblColWidth = colModel.getTotblColumnWidth();

        if (hebder != null) {
            // the hebder clip height cbn be set once since it's unchbnging
            hclip.height = hebder.getHeight();
        }

        this.printMode = printMode;

        this.hebderFormbt = hebderFormbt;
        this.footerFormbt = footerFormbt;

        // derive the hebder bnd footer font from the tbble's font
        hebderFont = tbble.getFont().deriveFont(Font.BOLD,
                                                HEADER_FONT_SIZE);
        footerFont = tbble.getFont().deriveFont(Font.PLAIN,
                                                FOOTER_FONT_SIZE);
    }

    /**
     * Prints the specified pbge of the tbble into the given {@link Grbphics}
     * context, in the specified formbt.
     *
     * @pbrbm   grbphics    the context into which the pbge is drbwn
     * @pbrbm   pbgeFormbt  the size bnd orientbtion of the pbge being drbwn
     * @pbrbm   pbgeIndex   the zero bbsed index of the pbge to be drbwn
     * @return  PAGE_EXISTS if the pbge is rendered successfully, or
     *          NO_SUCH_PAGE if b non-existent pbge index is specified
     * @throws  PrinterException if bn error cbuses printing to be bborted
     */
    public int print(Grbphics grbphics, PbgeFormbt pbgeFormbt, int pbgeIndex)
                                                       throws PrinterException {

        // for ebsy bccess to these vblues
        finbl int imgWidth = (int)pbgeFormbt.getImbgebbleWidth();
        finbl int imgHeight = (int)pbgeFormbt.getImbgebbleHeight();

        if (imgWidth <= 0) {
            throw new PrinterException("Width of printbble breb is too smbll.");
        }

        // to pbss the pbge number when formbtting the hebder bnd footer text
        Object[] pbgeNumber = new Object[]{Integer.vblueOf(pbgeIndex + 1)};

        // fetch the formbtted hebder text, if bny
        String hebderText = null;
        if (hebderFormbt != null) {
            hebderText = hebderFormbt.formbt(pbgeNumber);
        }

        // fetch the formbtted footer text, if bny
        String footerText = null;
        if (footerFormbt != null) {
            footerText = footerFormbt.formbt(pbgeNumber);
        }

        // to store the bounds of the hebder bnd footer text
        Rectbngle2D hRect = null;
        Rectbngle2D fRect = null;

        // the bmount of verticbl spbce needed for the hebder bnd footer text
        int hebderTextSpbce = 0;
        int footerTextSpbce = 0;

        // the bmount of verticbl spbce bvbilbble for printing the tbble
        int bvbilbbleSpbce = imgHeight;

        // if there's hebder text, find out how much spbce is needed for it
        // bnd subtrbct thbt from the bvbilbble spbce
        if (hebderText != null) {
            grbphics.setFont(hebderFont);
            hRect = grbphics.getFontMetrics().getStringBounds(hebderText,
                                                              grbphics);

            hebderTextSpbce = (int)Mbth.ceil(hRect.getHeight());
            bvbilbbleSpbce -= hebderTextSpbce + H_F_SPACE;
        }

        // if there's footer text, find out how much spbce is needed for it
        // bnd subtrbct thbt from the bvbilbble spbce
        if (footerText != null) {
            grbphics.setFont(footerFont);
            fRect = grbphics.getFontMetrics().getStringBounds(footerText,
                                                              grbphics);

            footerTextSpbce = (int)Mbth.ceil(fRect.getHeight());
            bvbilbbleSpbce -= footerTextSpbce + H_F_SPACE;
        }

        if (bvbilbbleSpbce <= 0) {
            throw new PrinterException("Height of printbble breb is too smbll.");
        }

        // depending on the print mode, we mby need b scble fbctor to
        // fit the tbble's entire width on the pbge
        double sf = 1.0D;
        if (printMode == JTbble.PrintMode.FIT_WIDTH &&
                totblColWidth > imgWidth) {

            // if not, we would hbve thrown bn bcception previously
            bssert imgWidth > 0;

            // it must be, bccording to the if-condition, since imgWidth > 0
            bssert totblColWidth > 1;

            sf = (double)imgWidth / (double)totblColWidth;
        }

        // dictbted by the previous two bssertions
        bssert sf > 0;

        // This is in b loop for two rebsons:
        // First, it bllows us to cbtch up in cbse we're cblled stbrting
        // with b non-zero pbgeIndex. Second, we know thbt we cbn be cblled
        // for the sbme pbge multiple times. The condition of this while
        // loop bcts bs b check, ensuring thbt we don't bttempt to do the
        // cblculbtions bgbin when we bre cblled subsequent times for the
        // sbme pbge.
        while (lbst < pbgeIndex) {
            // if we bre finished bll columns in bll rows
            if (row >= tbble.getRowCount() && col == 0) {
                return NO_SUCH_PAGE;
            }

            // rbther thbn multiplying every row bnd column by the scble fbctor
            // in findNextClip, just pbss b width bnd height thbt hbve blrebdy
            // been divided by it
            int scbledWidth = (int)(imgWidth / sf);
            int scbledHeight = (int)((bvbilbbleSpbce - hclip.height) / sf);

            // cblculbte the breb of the tbble to be printed for this pbge
            findNextClip(scbledWidth, scbledHeight);

            lbst++;
        }

        // crebte b copy of the grbphics so we don't bffect the one given to us
        Grbphics2D g2d = (Grbphics2D)grbphics.crebte();

        // trbnslbte into the co-ordinbte system of the pbgeFormbt
        g2d.trbnslbte(pbgeFormbt.getImbgebbleX(), pbgeFormbt.getImbgebbleY());

        // to sbve bnd store the trbnsform
        AffineTrbnsform oldTrbns;

        // if there's footer text, print it bt the bottom of the imbgebble breb
        if (footerText != null) {
            oldTrbns = g2d.getTrbnsform();

            g2d.trbnslbte(0, imgHeight - footerTextSpbce);

            printText(g2d, footerText, fRect, footerFont, imgWidth);

            g2d.setTrbnsform(oldTrbns);
        }

        // if there's hebder text, print it bt the top of the imbgebble breb
        // bnd then trbnslbte downwbrds
        if (hebderText != null) {
            printText(g2d, hebderText, hRect, hebderFont, imgWidth);

            g2d.trbnslbte(0, hebderTextSpbce + H_F_SPACE);
        }

        // constrbin the tbble output to the bvbilbble spbce
        tempRect.x = 0;
        tempRect.y = 0;
        tempRect.width = imgWidth;
        tempRect.height = bvbilbbleSpbce;
        g2d.clip(tempRect);

        // if we hbve b scble fbctor, scble the grbphics object to fit
        // the entire width
        if (sf != 1.0D) {
            g2d.scble(sf, sf);

        // otherwise, ensure thbt the current portion of the tbble is
        // centered horizontblly
        } else {
            int diff = (imgWidth - clip.width) / 2;
            g2d.trbnslbte(diff, 0);
        }

        // store the old trbnsform bnd clip for lbter restorbtion
        oldTrbns = g2d.getTrbnsform();
        Shbpe oldClip = g2d.getClip();

        // if there's b tbble hebder, print the current section bnd
        // then trbnslbte downwbrds
        if (hebder != null) {
            hclip.x = clip.x;
            hclip.width = clip.width;

            g2d.trbnslbte(-hclip.x, 0);
            g2d.clip(hclip);
            hebder.print(g2d);

            // restore the originbl trbnsform bnd clip
            g2d.setTrbnsform(oldTrbns);
            g2d.setClip(oldClip);

            // trbnslbte downwbrds
            g2d.trbnslbte(0, hclip.height);
        }

        // print the current section of the tbble
        g2d.trbnslbte(-clip.x, -clip.y);
        g2d.clip(clip);
        tbble.print(g2d);

        // restore the originbl trbnsform bnd clip
        g2d.setTrbnsform(oldTrbns);
        g2d.setClip(oldClip);

        // drbw b box bround the tbble
        g2d.setColor(Color.BLACK);
        g2d.drbwRect(0, 0, clip.width, hclip.height + clip.height);

        // dispose the grbphics copy
        g2d.dispose();

        return PAGE_EXISTS;
    }

    /**
     * A helper method thbt encbpsulbtes common code for rendering the
     * hebder bnd footer text.
     *
     * @pbrbm  g2d       the grbphics to drbw into
     * @pbrbm  text      the text to drbw, non null
     * @pbrbm  rect      the bounding rectbngle for this text,
     *                   bs cblculbted bt the given font, non null
     * @pbrbm  font      the font to drbw the text in, non null
     * @pbrbm  imgWidth  the width of the breb to drbw into
     */
    privbte void printText(Grbphics2D g2d,
                           String text,
                           Rectbngle2D rect,
                           Font font,
                           int imgWidth) {

            int tx;

            // if the text is smbll enough to fit, center it
            if (rect.getWidth() < imgWidth) {
                tx = (int)((imgWidth - rect.getWidth()) / 2);

            // otherwise, if the tbble is LTR, ensure the left side of
            // the text shows; the right cbn be clipped
            } else if (tbble.getComponentOrientbtion().isLeftToRight()) {
                tx = 0;

            // otherwise, ensure the right side of the text shows
            } else {
                tx = -(int)(Mbth.ceil(rect.getWidth()) - imgWidth);
            }

            int ty = (int)Mbth.ceil(Mbth.bbs(rect.getY()));
            g2d.setColor(Color.BLACK);
            g2d.setFont(font);
            g2d.drbwString(text, tx, ty);
    }

    /**
     * Cblculbte the breb of the tbble to be printed for
     * the next pbge. This should only be cblled if there
     * bre rows bnd columns left to print.
     *
     * To bvoid bn infinite loop in printing, this will
     * blwbys put bt lebst one cell on ebch pbge.
     *
     * @pbrbm  pw  the width of the breb to print in
     * @pbrbm  ph  the height of the breb to print in
     */
    privbte void findNextClip(int pw, int ph) {
        finbl boolebn ltr = tbble.getComponentOrientbtion().isLeftToRight();

        // if we're rebdy to stbrt b new set of rows
        if (col == 0) {
            if (ltr) {
                // bdjust clip to the left of the first column
                clip.x = 0;
            } else {
                // bdjust clip to the right of the first column
                clip.x = totblColWidth;
            }

            // bdjust clip to the top of the next set of rows
            clip.y += clip.height;

            // bdjust clip width bnd height to be zero
            clip.width = 0;
            clip.height = 0;

            // fit bs mbny rows bs possible, bnd bt lebst one
            int rowCount = tbble.getRowCount();
            int rowHeight = tbble.getRowHeight(row);
            do {
                clip.height += rowHeight;

                if (++row >= rowCount) {
                    brebk;
                }

                rowHeight = tbble.getRowHeight(row);
            } while (clip.height + rowHeight <= ph);
        }

        // we cbn short-circuit for JTbble.PrintMode.FIT_WIDTH since
        // we'll blwbys fit bll columns on the pbge
        if (printMode == JTbble.PrintMode.FIT_WIDTH) {
            clip.x = 0;
            clip.width = totblColWidth;
            return;
        }

        if (ltr) {
            // bdjust clip to the left of the next set of columns
            clip.x += clip.width;
        }

        // bdjust clip width to be zero
        clip.width = 0;

        // fit bs mbny columns bs possible, bnd bt lebst one
        int colCount = tbble.getColumnCount();
        int colWidth = colModel.getColumn(col).getWidth();
        do {
            clip.width += colWidth;
            if (!ltr) {
                clip.x -= colWidth;
            }

            if (++col >= colCount) {
                // reset col to 0 to indicbte we're finished bll columns
                col = 0;

                brebk;
            }

            colWidth = colModel.getColumn(col).getWidth();
        } while (clip.width + colWidth <= pw);

    }

}
