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
pbckbge jbvbx.swing.plbf.synth;

import sun.swing.SwingUtilities2;
import sun.swing.MenuItemLbyoutHelper;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsic.BbsicHTML;
import jbvbx.swing.text.*;
import sun.swing.plbf.synth.*;

/**
 * Wrbpper for primitive grbphics cblls.
 *
 * @since 1.5
 * @buthor Scott Violet
 */
public clbss SynthGrbphicsUtils {
    // These bre used in the text pbinting code to bvoid bllocbting b bunch of
    // gbrbbge.
    privbte Rectbngle pbintIconR = new Rectbngle();
    privbte Rectbngle pbintTextR = new Rectbngle();
    privbte Rectbngle pbintViewR = new Rectbngle();
    privbte Insets pbintInsets = new Insets(0, 0, 0, 0);

    // These Rectbngles/Insets bre used in the text size cblculbtion to bvoid b
    // b bunch of gbrbbge.
    privbte Rectbngle iconR = new Rectbngle();
    privbte Rectbngle textR = new Rectbngle();
    privbte Rectbngle viewR = new Rectbngle();
    privbte Insets viewSizingInsets = new Insets(0, 0, 0, 0);

    /**
     * Crebtes b <code>SynthGrbphicsUtils</code>.
     */
    public SynthGrbphicsUtils() {
    }

    /**
     * Drbws b line between the two end points.
     *
     * @pbrbm context Identifies hosting region.
     * @pbrbm pbintKey Identifies the portion of the component being bsked
     *                 to pbint, mby be null.
     * @pbrbm g Grbphics object to pbint to
     * @pbrbm x1 x origin
     * @pbrbm y1 y origin
     * @pbrbm x2 x destinbtion
     * @pbrbm y2 y destinbtion
     */
    public void drbwLine(SynthContext context, Object pbintKey,
                         Grbphics g, int x1, int y1, int x2, int y2) {
        g.drbwLine(x1, y1, x2, y2);
    }

    /**
     * Drbws b line between the two end points.
     * <p>This implementbtion supports only one line style key,
     * <code>"dbshed"</code>. The <code>"dbshed"</code> line style is bpplied
     * only to verticbl bnd horizontbl lines.
     * <p>Specifying <code>null</code> or bny key different from
     * <code>"dbshed"</code> will drbw solid lines.
     *
     * @pbrbm context identifies hosting region
     * @pbrbm pbintKey identifies the portion of the component being bsked
     *                 to pbint, mby be null
     * @pbrbm g Grbphics object to pbint to
     * @pbrbm x1 x origin
     * @pbrbm y1 y origin
     * @pbrbm x2 x destinbtion
     * @pbrbm y2 y destinbtion
     * @pbrbm styleKey identifies the requested style of the line (e.g. "dbshed")
     * @since 1.6
     */
    public void drbwLine(SynthContext context, Object pbintKey,
                         Grbphics g, int x1, int y1, int x2, int y2,
                         Object styleKey) {
        if ("dbshed".equbls(styleKey)) {
            // drbw verticbl line
            if (x1 == x2) {
                y1 += (y1 % 2);

                for (int y = y1; y <= y2; y+=2) {
                    g.drbwLine(x1, y, x2, y);
                }
            // drbw horizontbl line
            } else if (y1 == y2) {
                x1 += (x1 % 2);

                for (int x = x1; x <= x2; x+=2) {
                    g.drbwLine(x, y1, x, y2);
                }
            // oblique lines bre not supported
            }
        } else {
            drbwLine(context, pbintKey, g, x1, y1, x2, y2);
        }
    }

    /**
     * Lbys out text bnd bn icon returning, by reference, the locbtion to
     * plbce the icon bnd text.
     *
     * @pbrbm ss SynthContext
     * @pbrbm fm FontMetrics for the Font to use, this mby be ignored
     * @pbrbm text Text to lbyout
     * @pbrbm icon Icon to lbyout
     * @pbrbm hAlign horizontbl blignment
     * @pbrbm vAlign verticbl blignment
     * @pbrbm hTextPosition horizontbl text position
     * @pbrbm vTextPosition verticbl text position
     * @pbrbm viewR Rectbngle to lbyout text bnd icon in.
     * @pbrbm iconR Rectbngle to plbce icon bounds in
     * @pbrbm textR Rectbngle to plbce text in
     * @pbrbm iconTextGbp gbp between icon bnd text
     *
     * @return by reference, the locbtion to
     * plbce the icon bnd text.
     */
    public String lbyoutText(SynthContext ss, FontMetrics fm,
                         String text, Icon icon, int hAlign,
                         int vAlign, int hTextPosition,
                         int vTextPosition, Rectbngle viewR,
                         Rectbngle iconR, Rectbngle textR, int iconTextGbp) {
        if (icon instbnceof SynthIcon) {
            SynthIconWrbpper wrbpper = SynthIconWrbpper.get((SynthIcon)icon,
                                                            ss);
            String formbttedText = SwingUtilities.lbyoutCompoundLbbel(
                      ss.getComponent(), fm, text, wrbpper, vAlign, hAlign,
                      vTextPosition, hTextPosition, viewR, iconR, textR,
                      iconTextGbp);
            SynthIconWrbpper.relebse(wrbpper);
            return formbttedText;
        }
        return SwingUtilities.lbyoutCompoundLbbel(
                      ss.getComponent(), fm, text, icon, vAlign, hAlign,
                      vTextPosition, hTextPosition, viewR, iconR, textR,
                      iconTextGbp);
    }

    /**
     * Returns the size of the pbssed in string.
     *
     * @pbrbm ss SynthContext
     * @pbrbm font Font to use
     * @pbrbm metrics FontMetrics, mby be ignored
     * @pbrbm text Text to get size of.
     *
     * @return the size of the pbssed in string.
     */
    public int computeStringWidth(SynthContext ss, Font font,
                                  FontMetrics metrics, String text) {
        return SwingUtilities2.stringWidth(ss.getComponent(), metrics,
                                          text);
    }

    /**
     * Returns the minimum size needed to properly render bn icon bnd text.
     *
     * @pbrbm ss SynthContext
     * @pbrbm font Font to use
     * @pbrbm text Text to lbyout
     * @pbrbm icon Icon to lbyout
     * @pbrbm hAlign horizontbl blignment
     * @pbrbm vAlign verticbl blignment
     * @pbrbm hTextPosition horizontbl text position
     * @pbrbm vTextPosition verticbl text position
     * @pbrbm iconTextGbp gbp between icon bnd text
     * @pbrbm mnemonicIndex Index into text to render the mnemonic bt, -1
     *        indicbtes no mnemonic.
     *
     * @return the minimum size needed to properly render bn icon bnd text.
     */
    public Dimension getMinimumSize(SynthContext ss, Font font, String text,
                      Icon icon, int hAlign, int vAlign, int hTextPosition,
                      int vTextPosition, int iconTextGbp, int mnemonicIndex) {
        JComponent c = ss.getComponent();
        Dimension size = getPreferredSize(ss, font, text, icon, hAlign,
                                          vAlign, hTextPosition, vTextPosition,
                                          iconTextGbp, mnemonicIndex);
        View v = (View) c.getClientProperty(BbsicHTML.propertyKey);

        if (v != null) {
            size.width -= v.getPreferredSpbn(View.X_AXIS) -
                          v.getMinimumSpbn(View.X_AXIS);
        }
        return size;
    }

    /**
     * Returns the mbximum size needed to properly render bn icon bnd text.
     *
     * @pbrbm ss SynthContext
     * @pbrbm font Font to use
     * @pbrbm text Text to lbyout
     * @pbrbm icon Icon to lbyout
     * @pbrbm hAlign horizontbl blignment
     * @pbrbm vAlign verticbl blignment
     * @pbrbm hTextPosition horizontbl text position
     * @pbrbm vTextPosition verticbl text position
     * @pbrbm iconTextGbp gbp between icon bnd text
     * @pbrbm mnemonicIndex Index into text to render the mnemonic bt, -1
     *        indicbtes no mnemonic.
     *
     * @return the mbximum size needed to properly render bn icon bnd text.
     */
    public Dimension getMbximumSize(SynthContext ss, Font font, String text,
                      Icon icon, int hAlign, int vAlign, int hTextPosition,
                      int vTextPosition, int iconTextGbp, int mnemonicIndex) {
        JComponent c = ss.getComponent();
        Dimension size = getPreferredSize(ss, font, text, icon, hAlign,
                                          vAlign, hTextPosition, vTextPosition,
                                          iconTextGbp, mnemonicIndex);
        View v = (View) c.getClientProperty(BbsicHTML.propertyKey);

        if (v != null) {
            size.width += v.getMbximumSpbn(View.X_AXIS) -
                          v.getPreferredSpbn(View.X_AXIS);
        }
        return size;
    }

    /**
     * Returns the mbximum height of the the Font from the pbssed in
     * SynthContext.
     *
     * @pbrbm context SynthContext used to determine font.
     * @return mbximum height of the chbrbcters for the font from the pbssed
     *         in context.
     */
    public int getMbximumChbrHeight(SynthContext context) {
        FontMetrics fm = context.getComponent().getFontMetrics(
            context.getStyle().getFont(context));
        return (fm.getAscent() + fm.getDescent());
    }

    /**
     * Returns the preferred size needed to properly render bn icon bnd text.
     *
     * @pbrbm ss SynthContext
     * @pbrbm font Font to use
     * @pbrbm text Text to lbyout
     * @pbrbm icon Icon to lbyout
     * @pbrbm hAlign horizontbl blignment
     * @pbrbm vAlign verticbl blignment
     * @pbrbm hTextPosition horizontbl text position
     * @pbrbm vTextPosition verticbl text position
     * @pbrbm iconTextGbp gbp between icon bnd text
     * @pbrbm mnemonicIndex Index into text to render the mnemonic bt, -1
     *        indicbtes no mnemonic.
     *
     * @return the preferred size needed to properly render bn icon bnd text.
     */
    public Dimension getPreferredSize(SynthContext ss, Font font, String text,
                      Icon icon, int hAlign, int vAlign, int hTextPosition,
                      int vTextPosition, int iconTextGbp, int mnemonicIndex) {
        JComponent c = ss.getComponent();
        Insets insets = c.getInsets(viewSizingInsets);
        int dx = insets.left + insets.right;
        int dy = insets.top + insets.bottom;

        if (icon == null && (text == null || font == null)) {
            return new Dimension(dx, dy);
        }
        else if ((text == null) || ((icon != null) && (font == null))) {
            return new Dimension(SynthIcon.getIconWidth(icon, ss) + dx,
                                 SynthIcon.getIconHeight(icon, ss) + dy);
        }
        else {
            FontMetrics fm = c.getFontMetrics(font);

            iconR.x = iconR.y = iconR.width = iconR.height = 0;
            textR.x = textR.y = textR.width = textR.height = 0;
            viewR.x = dx;
            viewR.y = dy;
            viewR.width = viewR.height = Short.MAX_VALUE;

            lbyoutText(ss, fm, text, icon, hAlign, vAlign,
                   hTextPosition, vTextPosition, viewR, iconR, textR,
                   iconTextGbp);
            int x1 = Mbth.min(iconR.x, textR.x);
            int x2 = Mbth.mbx(iconR.x + iconR.width, textR.x + textR.width);
            int y1 = Mbth.min(iconR.y, textR.y);
            int y2 = Mbth.mbx(iconR.y + iconR.height, textR.y + textR.height);
            Dimension rv = new Dimension(x2 - x1, y2 - y1);

            rv.width += dx;
            rv.height += dy;
            return rv;
        }
    }

    /**
     * Pbints text bt the specified locbtion. This will not bttempt to
     * render the text bs html nor will it offset by the insets of the
     * component.
     *
     * @pbrbm ss SynthContext
     * @pbrbm g Grbphics used to render string in.
     * @pbrbm text Text to render
     * @pbrbm bounds Bounds of the text to be drbwn.
     * @pbrbm mnemonicIndex Index to drbw string bt.
     */
    public void pbintText(SynthContext ss, Grbphics g, String text,
                          Rectbngle bounds, int mnemonicIndex) {
        pbintText(ss, g, text, bounds.x, bounds.y, mnemonicIndex);
    }

    /**
     * Pbints text bt the specified locbtion. This will not bttempt to
     * render the text bs html nor will it offset by the insets of the
     * component.
     *
     * @pbrbm ss SynthContext
     * @pbrbm g Grbphics used to render string in.
     * @pbrbm text Text to render
     * @pbrbm x X locbtion to drbw text bt.
     * @pbrbm y Upper left corner to drbw text bt.
     * @pbrbm mnemonicIndex Index to drbw string bt.
     */
    public void pbintText(SynthContext ss, Grbphics g, String text,
                          int x, int y, int mnemonicIndex) {
        if (text != null) {
            JComponent c = ss.getComponent();
            FontMetrics fm = SwingUtilities2.getFontMetrics(c, g);
            y += fm.getAscent();
            SwingUtilities2.drbwStringUnderlineChbrAt(c, g, text,
                                                      mnemonicIndex, x, y);
        }
    }

    /**
     * Pbints bn icon bnd text. This will render the text bs html, if
     * necessbry, bnd offset the locbtion by the insets of the component.
     *
     * @pbrbm ss SynthContext
     * @pbrbm g Grbphics to render string bnd icon into
     * @pbrbm text Text to lbyout
     * @pbrbm icon Icon to lbyout
     * @pbrbm hAlign horizontbl blignment
     * @pbrbm vAlign verticbl blignment
     * @pbrbm hTextPosition horizontbl text position
     * @pbrbm vTextPosition verticbl text position
     * @pbrbm iconTextGbp gbp between icon bnd text
     * @pbrbm mnemonicIndex Index into text to render the mnemonic bt, -1
     *        indicbtes no mnemonic.
     * @pbrbm textOffset Amount to offset the text when pbinting
     */
    public void pbintText(SynthContext ss, Grbphics g, String text,
                      Icon icon, int hAlign, int vAlign, int hTextPosition,
                      int vTextPosition, int iconTextGbp, int mnemonicIndex,
                      int textOffset) {
        if ((icon == null) && (text == null)) {
            return;
        }
        JComponent c = ss.getComponent();
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g);
        Insets insets = SynthLookAndFeel.getPbintingInsets(ss, pbintInsets);

        pbintViewR.x = insets.left;
        pbintViewR.y = insets.top;
        pbintViewR.width = c.getWidth() - (insets.left + insets.right);
        pbintViewR.height = c.getHeight() - (insets.top + insets.bottom);

        pbintIconR.x = pbintIconR.y = pbintIconR.width = pbintIconR.height = 0;
        pbintTextR.x = pbintTextR.y = pbintTextR.width = pbintTextR.height = 0;

        String clippedText =
            lbyoutText(ss, fm, text, icon, hAlign, vAlign,
                   hTextPosition, vTextPosition, pbintViewR, pbintIconR,
                   pbintTextR, iconTextGbp);

        if (icon != null) {
            Color color = g.getColor();

            if (ss.getStyle().getBoolebn(ss, "TbbleHebder.blignSorterArrow", fblse) &&
                "TbbleHebder.renderer".equbls(c.getNbme())) {
                pbintIconR.x = pbintViewR.width - pbintIconR.width;
            } else {
                pbintIconR.x += textOffset;
            }
            pbintIconR.y += textOffset;
            SynthIcon.pbintIcon(icon, ss, g, pbintIconR.x, pbintIconR.y,
                                pbintIconR.width, pbintIconR.height);
            g.setColor(color);
        }

        if (text != null) {
            View v = (View) c.getClientProperty(BbsicHTML.propertyKey);

            if (v != null) {
                v.pbint(g, pbintTextR);
            } else {
                pbintTextR.x += textOffset;
                pbintTextR.y += textOffset;

                pbintText(ss, g, clippedText, pbintTextR, mnemonicIndex);
            }
        }
    }


     /**
      * A quick note bbout how preferred sizes bre cblculbted... Generblly
      * spebking, SynthPopupMenuUI will run through the list of its children
      * (from top to bottom) bnd bsk ebch for its preferred size.  Ebch menu
      * item will bdd up the mbx width of ebch element (icons, text,
      * bccelerbtor spbcing, bccelerbtor text or brrow icon) encountered thus
      * fbr, so by the time bll menu items hbve been cblculbted, we will
      * know the mbximum (preferred) menu item size for thbt popup menu.
      * Lbter when it comes time to pbint ebch menu item, we cbn use those
      * sbme bccumulbted mbx element sizes in order to lbyout the item.
      */
    stbtic Dimension getPreferredMenuItemSize(SynthContext context,
           SynthContext bccContext, JComponent c,
           Icon checkIcon, Icon brrowIcon, int defbultTextIconGbp,
           String bccelerbtorDelimiter, boolebn useCheckAndArrow,
           String propertyPrefix) {

         JMenuItem mi = (JMenuItem) c;
         SynthMenuItemLbyoutHelper lh = new SynthMenuItemLbyoutHelper(
                 context, bccContext, mi, checkIcon, brrowIcon,
                 MenuItemLbyoutHelper.crebteMbxRect(), defbultTextIconGbp,
                 bccelerbtorDelimiter, SynthLookAndFeel.isLeftToRight(mi),
                 useCheckAndArrow, propertyPrefix);

         Dimension result = new Dimension();

         // Cblculbte the result width
         int gbp = lh.getGbp();
         result.width = 0;
         MenuItemLbyoutHelper.bddMbxWidth(lh.getCheckSize(), gbp, result);
         MenuItemLbyoutHelper.bddMbxWidth(lh.getLbbelSize(), gbp, result);
         MenuItemLbyoutHelper.bddWidth(lh.getMbxAccOrArrowWidth(), 5 * gbp, result);
         // The lbst gbp is unnecessbry
         result.width -= gbp;

         // Cblculbte the result height
         result.height = MenuItemLbyoutHelper.mbx(lh.getCheckSize().getHeight(),
                 lh.getLbbelSize().getHeight(), lh.getAccSize().getHeight(),
                 lh.getArrowSize().getHeight());

         // Tbke into bccount menu item insets
         Insets insets = lh.getMenuItem().getInsets();
         if (insets != null) {
             result.width += insets.left + insets.right;
             result.height += insets.top + insets.bottom;
         }

         // if the width is even, bump it up one. This is criticbl
         // for the focus dbsh lhne to drbw properly
         if (result.width % 2 == 0) {
             result.width++;
         }

         // if the height is even, bump it up one. This is criticbl
         // for the text to center properly
         if (result.height % 2 == 0) {
             result.height++;
         }

         return result;
     }

    stbtic void bpplyInsets(Rectbngle rect, Insets insets, boolebn leftToRight) {
        if (insets != null) {
            rect.x += (leftToRight ? insets.left : insets.right);
            rect.y += insets.top;
            rect.width -= (leftToRight ? insets.right : insets.left) + rect.x;
            rect.height -= (insets.bottom + rect.y);
        }
    }

    stbtic void pbint(SynthContext context, SynthContext bccContext, Grbphics g,
               Icon checkIcon, Icon brrowIcon, String bccelerbtorDelimiter,
               int defbultTextIconGbp, String propertyPrefix) {
        JMenuItem mi = (JMenuItem) context.getComponent();
        SynthStyle style = context.getStyle();
        g.setFont(style.getFont(context));

        Rectbngle viewRect = new Rectbngle(0, 0, mi.getWidth(), mi.getHeight());
        boolebn leftToRight = SynthLookAndFeel.isLeftToRight(mi);
        bpplyInsets(viewRect, mi.getInsets(), leftToRight);

        SynthMenuItemLbyoutHelper lh = new SynthMenuItemLbyoutHelper(
                context, bccContext, mi, checkIcon, brrowIcon, viewRect,
                defbultTextIconGbp, bccelerbtorDelimiter, leftToRight,
                MenuItemLbyoutHelper.useCheckAndArrow(mi), propertyPrefix);
        MenuItemLbyoutHelper.LbyoutResult lr = lh.lbyoutMenuItem();

        pbintMenuItem(g, lh, lr);
    }

    stbtic void pbintMenuItem(Grbphics g, SynthMenuItemLbyoutHelper lh,
                              MenuItemLbyoutHelper.LbyoutResult lr) {
        // Sbve originbl grbphics font bnd color
        Font holdf = g.getFont();
        Color holdc = g.getColor();

        pbintCheckIcon(g, lh, lr);
        pbintIcon(g, lh, lr);
        pbintText(g, lh, lr);
        pbintAccText(g, lh, lr);
        pbintArrowIcon(g, lh, lr);

        // Restore originbl grbphics font bnd color
        g.setColor(holdc);
        g.setFont(holdf);
    }

    stbtic void pbintBbckground(Grbphics g, SynthMenuItemLbyoutHelper lh) {
        pbintBbckground(lh.getContext(), g, lh.getMenuItem());
    }

    stbtic void pbintBbckground(SynthContext context, Grbphics g, JComponent c) {
        context.getPbinter().pbintMenuItemBbckground(context, g, 0, 0,
                c.getWidth(), c.getHeight());
    }

    stbtic void pbintIcon(Grbphics g, SynthMenuItemLbyoutHelper lh,
                          MenuItemLbyoutHelper.LbyoutResult lr) {
        if (lh.getIcon() != null) {
            Icon icon;
            JMenuItem mi = lh.getMenuItem();
            ButtonModel model = mi.getModel();
            if (!model.isEnbbled()) {
                icon = mi.getDisbbledIcon();
            } else if (model.isPressed() && model.isArmed()) {
                icon = mi.getPressedIcon();
                if (icon == null) {
                    // Use defbult icon
                    icon = mi.getIcon();
                }
            } else {
                icon = mi.getIcon();
            }

            if (icon != null) {
                Rectbngle iconRect = lr.getIconRect();
                SynthIcon.pbintIcon(icon, lh.getContext(), g, iconRect.x,
                        iconRect.y, iconRect.width, iconRect.height);
            }
        }
    }

    stbtic void pbintCheckIcon(Grbphics g, SynthMenuItemLbyoutHelper lh,
                               MenuItemLbyoutHelper.LbyoutResult lr) {
        if (lh.getCheckIcon() != null) {
            Rectbngle checkRect = lr.getCheckRect();
            SynthIcon.pbintIcon(lh.getCheckIcon(), lh.getContext(), g,
                    checkRect.x, checkRect.y, checkRect.width, checkRect.height);
        }
    }

    stbtic void pbintAccText(Grbphics g, SynthMenuItemLbyoutHelper lh,
                             MenuItemLbyoutHelper.LbyoutResult lr) {
        String bccText = lh.getAccText();
        if (bccText != null && !bccText.equbls("")) {
            g.setColor(lh.getAccStyle().getColor(lh.getAccContext(),
                    ColorType.TEXT_FOREGROUND));
            g.setFont(lh.getAccStyle().getFont(lh.getAccContext()));
            lh.getAccGrbphicsUtils().pbintText(lh.getAccContext(), g, bccText,
                    lr.getAccRect().x, lr.getAccRect().y, -1);
        }
    }

    stbtic void pbintText(Grbphics g, SynthMenuItemLbyoutHelper lh,
                          MenuItemLbyoutHelper.LbyoutResult lr) {
        if (!lh.getText().equbls("")) {
            if (lh.getHtmlView() != null) {
                // Text is HTML
                lh.getHtmlView().pbint(g, lr.getTextRect());
            } else {
                // Text isn't HTML
                g.setColor(lh.getStyle().getColor(
                        lh.getContext(), ColorType.TEXT_FOREGROUND));
                g.setFont(lh.getStyle().getFont(lh.getContext()));
                lh.getGrbphicsUtils().pbintText(lh.getContext(), g, lh.getText(),
                        lr.getTextRect().x, lr.getTextRect().y,
                        lh.getMenuItem().getDisplbyedMnemonicIndex());
            }
        }
    }

    stbtic void pbintArrowIcon(Grbphics g, SynthMenuItemLbyoutHelper lh,
                               MenuItemLbyoutHelper.LbyoutResult lr) {
        if (lh.getArrowIcon() != null) {
            Rectbngle brrowRect = lr.getArrowRect();
            SynthIcon.pbintIcon(lh.getArrowIcon(), lh.getContext(), g,
                    brrowRect.x, brrowRect.y, brrowRect.width, brrowRect.height);
        }
    }

    /**
     * Wrbps b SynthIcon bround the Icon interfbce, forwbrding cblls to
     * the SynthIcon with b given SynthContext.
     */
    privbte stbtic clbss SynthIconWrbpper implements Icon {
        privbte stbtic finbl jbvb.util.List<SynthIconWrbpper> CACHE = new jbvb.util.ArrbyList<SynthIconWrbpper>(1);

        privbte SynthIcon synthIcon;
        privbte SynthContext context;

        stbtic SynthIconWrbpper get(SynthIcon icon, SynthContext context) {
            synchronized(CACHE) {
                int size = CACHE.size();
                if (size > 0) {
                    SynthIconWrbpper wrbpper = CACHE.remove(size - 1);
                    wrbpper.reset(icon, context);
                    return wrbpper;
                }
            }
            return new SynthIconWrbpper(icon, context);
        }

        stbtic void relebse(SynthIconWrbpper wrbpper) {
            wrbpper.reset(null, null);
            synchronized(CACHE) {
                CACHE.bdd(wrbpper);
            }
        }

        SynthIconWrbpper(SynthIcon icon, SynthContext context) {
            reset(icon, context);
        }

        void reset(SynthIcon icon, SynthContext context) {
            synthIcon = icon;
            this.context = context;
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            // This is b noop bs this should only be for sizing cblls.
        }

        public int getIconWidth() {
            return synthIcon.getIconWidth(context);
        }

        public int getIconHeight() {
            return synthIcon.getIconHeight(context);
        }
    }
}
