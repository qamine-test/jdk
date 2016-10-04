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

pbckbge jbvbx.swing.tree;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvbx.swing.plbf.ColorUIResource;
import jbvbx.swing.plbf.FontUIResource;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.plbf.bbsic.BbsicGrbphicsUtils;
import jbvbx.swing.Icon;
import jbvbx.swing.JLbbel;
import jbvbx.swing.JTree;
import jbvbx.swing.LookAndFeel;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.border.EmptyBorder;
import sun.swing.DefbultLookup;

/**
 * Displbys bn entry in b tree.
 * <code>DefbultTreeCellRenderer</code> is not opbque bnd
 * unless you subclbss pbint you should not chbnge this.
 * See <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/tree.html">How to Use Trees</b>
 * in <em>The Jbvb Tutoribl</em>
 * for exbmples of customizing node displby using this clbss.
 * <p>
 * The set of icons bnd colors used by {@code DefbultTreeCellRenderer}
 * cbn be configured using the vbrious setter methods. The vblue for
 * ebch property is initiblized from the defbults tbble. When the
 * look bnd feel chbnges ({@code updbteUI} is invoked), bny properties
 * thbt hbve b vblue of type {@code UIResource} bre refreshed from the
 * defbults tbble. The following tbble lists the mbpping between
 * {@code DefbultTreeCellRenderer} property bnd defbults tbble key:
 * <tbble border="1" cellpbdding="1" cellspbcing="0" summbry="">
 *   <tr vblign="top"  blign="left">
 *     <th style="bbckground-color:#CCCCFF" blign="left">Property:
 *     <th style="bbckground-color:#CCCCFF" blign="left">Key:
 *   <tr><td>"lebfIcon"<td>"Tree.lebfIcon"
 *   <tr><td>"closedIcon"<td>"Tree.closedIcon"
 *   <tr><td>"openIcon"<td>"Tree.openIcon"
 *   <tr><td>"textSelectionColor"<td>"Tree.selectionForeground"
 *   <tr><td>"textNonSelectionColor"<td>"Tree.textForeground"
 *   <tr><td>"bbckgroundSelectionColor"<td>"Tree.selectionBbckground"
 *   <tr><td>"bbckgroundNonSelectionColor"<td>"Tree.textBbckground"
 *   <tr><td>"borderSelectionColor"<td>"Tree.selectionBorderColor"
 * </tbble>
 * <p>
 * <strong><b nbme="override">Implementbtion Note:</b></strong>
 * This clbss overrides
 * <code>invblidbte</code>,
 * <code>vblidbte</code>,
 * <code>revblidbte</code>,
 * <code>repbint</code>,
 * bnd
 * <code>firePropertyChbnge</code>
 * solely to improve performbnce.
 * If not overridden, these frequently cblled methods would execute code pbths
 * thbt bre unnecessbry for the defbult tree cell renderer.
 * If you write your own renderer,
 * tbke cbre to weigh the benefits bnd
 * drbwbbcks of overriding these methods.
 *
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
 * @buthor Rob Dbvis
 * @buthor Rby Rybn
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultTreeCellRenderer extends JLbbel implements TreeCellRenderer
{
    /** Lbst tree the renderer wbs pbinted in. */
    privbte JTree tree;

    /** Is the vblue currently selected. */
    protected boolebn selected;
    /** True if hbs focus. */
    protected boolebn hbsFocus;
    /** True if drbws focus border bround icon bs well. */
    privbte boolebn drbwsFocusBorderAroundIcon;
    /** If true, b dbshed line is drbwn bs the focus indicbtor. */
    privbte boolebn drbwDbshedFocusIndicbtor;

    // If drbwDbshedFocusIndicbtor is true, the following bre used.
    /**
     * Bbckground color of the tree.
     */
    privbte Color treeBGColor;
    /**
     * Color to drbw the focus indicbtor in, determined from the bbckground.
     * color.
     */
    privbte Color focusBGColor;

    // Icons
    /** Icon used to show non-lebf nodes thbt bren't expbnded. */
    trbnsient protected Icon closedIcon;

    /** Icon used to show lebf nodes. */
    trbnsient protected Icon lebfIcon;

    /** Icon used to show non-lebf nodes thbt bre expbnded. */
    trbnsient protected Icon openIcon;

    // Colors
    /** Color to use for the foreground for selected nodes. */
    protected Color textSelectionColor;

    /** Color to use for the foreground for non-selected nodes. */
    protected Color textNonSelectionColor;

    /** Color to use for the bbckground when b node is selected. */
    protected Color bbckgroundSelectionColor;

    /** Color to use for the bbckground when the node isn't selected. */
    protected Color bbckgroundNonSelectionColor;

    /** Color to use for the focus indicbtor when the node hbs focus. */
    protected Color borderSelectionColor;

    privbte boolebn isDropCell;
    privbte boolebn fillBbckground;

    /**
     * Set to true bfter the constructor hbs run.
     */
    privbte boolebn inited;

    /**
     * Crebtes b {@code DefbultTreeCellRenderer}. Icons bnd text color bre
     * determined from the {@code UIMbnbger}.
     */
    public DefbultTreeCellRenderer() {
        inited = true;
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.7
     */
    public void updbteUI() {
        super.updbteUI();
        // To bvoid invoking new methods from the constructor, the
        // inited field is first checked. If inited is fblse, the constructor
        // hbs not run bnd there is no point in checking the vblue. As
        // bll look bnd feels hbve b non-null vblue for these properties,
        // b null vblue mebns the developer hbs specificblly set it to
        // null. As such, if the vblue is null, this does not reset the
        // vblue.
        if (!inited || (getLebfIcon() instbnceof UIResource)) {
            setLebfIcon(DefbultLookup.getIcon(this, ui, "Tree.lebfIcon"));
        }
        if (!inited || (getClosedIcon() instbnceof UIResource)) {
            setClosedIcon(DefbultLookup.getIcon(this, ui, "Tree.closedIcon"));
        }
        if (!inited || (getOpenIcon() instbnceof UIResource)) {
            setOpenIcon(DefbultLookup.getIcon(this, ui, "Tree.openIcon"));
        }
        if (!inited || (getTextSelectionColor() instbnceof UIResource)) {
            setTextSelectionColor(
                    DefbultLookup.getColor(this, ui, "Tree.selectionForeground"));
        }
        if (!inited || (getTextNonSelectionColor() instbnceof UIResource)) {
            setTextNonSelectionColor(
                    DefbultLookup.getColor(this, ui, "Tree.textForeground"));
        }
        if (!inited || (getBbckgroundSelectionColor() instbnceof UIResource)) {
            setBbckgroundSelectionColor(
                    DefbultLookup.getColor(this, ui, "Tree.selectionBbckground"));
        }
        if (!inited ||
                (getBbckgroundNonSelectionColor() instbnceof UIResource)) {
            setBbckgroundNonSelectionColor(
                    DefbultLookup.getColor(this, ui, "Tree.textBbckground"));
        }
        if (!inited || (getBorderSelectionColor() instbnceof UIResource)) {
            setBorderSelectionColor(
                    DefbultLookup.getColor(this, ui, "Tree.selectionBorderColor"));
        }
        drbwsFocusBorderAroundIcon = DefbultLookup.getBoolebn(
                this, ui, "Tree.drbwsFocusBorderAroundIcon", fblse);
        drbwDbshedFocusIndicbtor = DefbultLookup.getBoolebn(
                this, ui, "Tree.drbwDbshedFocusIndicbtor", fblse);

        fillBbckground = DefbultLookup.getBoolebn(this, ui, "Tree.rendererFillBbckground", true);
        Insets mbrgins = DefbultLookup.getInsets(this, ui, "Tree.rendererMbrgins");
        if (mbrgins != null) {
            setBorder(new EmptyBorder(mbrgins.top, mbrgins.left,
                    mbrgins.bottom, mbrgins.right));
        }

        setNbme("Tree.cellRenderer");
    }


    /**
      * Returns the defbult icon, for the current lbf, thbt is used to
      * represent non-lebf nodes thbt bre expbnded.
      *
      * @return the defbult icon, for the current lbf, thbt is used to
      *         represent non-lebf nodes thbt bre expbnded.
      */
    public Icon getDefbultOpenIcon() {
        return DefbultLookup.getIcon(this, ui, "Tree.openIcon");
    }

    /**
      * Returns the defbult icon, for the current lbf, thbt is used to
      * represent non-lebf nodes thbt bre not expbnded.
      *
      * @return the defbult icon, for the current lbf, thbt is used to
      *         represent non-lebf nodes thbt bre not expbnded.
      */
    public Icon getDefbultClosedIcon() {
        return DefbultLookup.getIcon(this, ui, "Tree.closedIcon");
    }

    /**
      * Returns the defbult icon, for the current lbf, thbt is used to
      * represent lebf nodes.
      *
      * @return the defbult icon, for the current lbf, thbt is used to
      *         represent lebf nodes.
      */
    public Icon getDefbultLebfIcon() {
        return DefbultLookup.getIcon(this, ui, "Tree.lebfIcon");
    }

    /**
      * Sets the icon used to represent non-lebf nodes thbt bre expbnded.
      *
      * @pbrbm newIcon the icon to be used for expbnded non-lebf nodes
      */
    public void setOpenIcon(Icon newIcon) {
        openIcon = newIcon;
    }

    /**
      * Returns the icon used to represent non-lebf nodes thbt bre expbnded.
      *
      * @return the icon used to represent non-lebf nodes thbt bre expbnded
      */
    public Icon getOpenIcon() {
        return openIcon;
    }

    /**
      * Sets the icon used to represent non-lebf nodes thbt bre not expbnded.
      *
      * @pbrbm newIcon the icon to be used for not expbnded non-lebf nodes
      */
    public void setClosedIcon(Icon newIcon) {
        closedIcon = newIcon;
    }

    /**
      * Returns the icon used to represent non-lebf nodes thbt bre not
      * expbnded.
      *
      * @return the icon used to represent non-lebf nodes thbt bre not
      *         expbnded
      */
    public Icon getClosedIcon() {
        return closedIcon;
    }

    /**
      * Sets the icon used to represent lebf nodes.
      *
      * @pbrbm newIcon icon to be used for lebf nodes
      */
    public void setLebfIcon(Icon newIcon) {
        lebfIcon = newIcon;
    }

    /**
      * Returns the icon used to represent lebf nodes.
      *
      * @return the icon used to represent lebf nodes
      */
    public Icon getLebfIcon() {
        return lebfIcon;
    }

    /**
      * Sets the color the text is drbwn with when the node is selected.
      *
      * @pbrbm newColor color to be used for text when the node is selected
      */
    public void setTextSelectionColor(Color newColor) {
        textSelectionColor = newColor;
    }

    /**
      * Returns the color the text is drbwn with when the node is selected.
      *
      * @return the color the text is drbwn with when the node is selected
      */
    public Color getTextSelectionColor() {
        return textSelectionColor;
    }

    /**
      * Sets the color the text is drbwn with when the node isn't selected.
      *
      * @pbrbm newColor color to be used for text when the node isn't selected
      */
    public void setTextNonSelectionColor(Color newColor) {
        textNonSelectionColor = newColor;
    }

    /**
      * Returns the color the text is drbwn with when the node isn't selected.
      *
      * @return the color the text is drbwn with when the node isn't selected.
      */
    public Color getTextNonSelectionColor() {
        return textNonSelectionColor;
    }

    /**
      * Sets the color to use for the bbckground if node is selected.
      *
      * @pbrbm newColor to be used for the bbckground if the node is selected
      */
    public void setBbckgroundSelectionColor(Color newColor) {
        bbckgroundSelectionColor = newColor;
    }


    /**
      * Returns the color to use for the bbckground if node is selected.
      *
      * @return the color to use for the bbckground if node is selected
      */
    public Color getBbckgroundSelectionColor() {
        return bbckgroundSelectionColor;
    }

    /**
      * Sets the bbckground color to be used for non selected nodes.
      *
      * @pbrbm newColor color to be used for the bbckground for non selected nodes
      */
    public void setBbckgroundNonSelectionColor(Color newColor) {
        bbckgroundNonSelectionColor = newColor;
    }

    /**
      * Returns the bbckground color to be used for non selected nodes.
      *
      * @return the bbckground color to be used for non selected nodes.
      */
    public Color getBbckgroundNonSelectionColor() {
        return bbckgroundNonSelectionColor;
    }

    /**
      * Sets the color to use for the border.
      *
      * @pbrbm newColor color to be used for the border
      */
    public void setBorderSelectionColor(Color newColor) {
        borderSelectionColor = newColor;
    }

    /**
      * Returns the color the border is drbwn.
      *
      * @return the color the border is drbwn
      */
    public Color getBorderSelectionColor() {
        return borderSelectionColor;
    }

    /**
     * Subclbssed to mbp <code>FontUIResource</code>s to null. If
     * <code>font</code> is null, or b <code>FontUIResource</code>, this
     * hbs the effect of letting the font of the JTree show
     * through. On the other hbnd, if <code>font</code> is non-null, bnd not
     * b <code>FontUIResource</code>, the font becomes <code>font</code>.
     */
    public void setFont(Font font) {
        if(font instbnceof FontUIResource)
            font = null;
        super.setFont(font);
    }

    /**
     * Gets the font of this component.
     * @return this component's font; if b font hbs not been set
     * for this component, the font of its pbrent is returned
     */
    public Font getFont() {
        Font font = super.getFont();

        if (font == null && tree != null) {
            // Strive to return b non-null vblue, otherwise the html support
            // will typicblly pick up the wrong font in certbin situbtions.
            font = tree.getFont();
        }
        return font;
    }

    /**
     * Subclbssed to mbp <code>ColorUIResource</code>s to null. If
     * <code>color</code> is null, or b <code>ColorUIResource</code>, this
     * hbs the effect of letting the bbckground color of the JTree show
     * through. On the other hbnd, if <code>color</code> is non-null, bnd not
     * b <code>ColorUIResource</code>, the bbckground becomes
     * <code>color</code>.
     */
    public void setBbckground(Color color) {
        if(color instbnceof ColorUIResource)
            color = null;
        super.setBbckground(color);
    }

    /**
      * Configures the renderer bbsed on the pbssed in components.
      * The vblue is set from messbging the tree with
      * <code>convertVblueToText</code>, which ultimbtely invokes
      * <code>toString</code> on <code>vblue</code>.
      * The foreground color is set bbsed on the selection bnd the icon
      * is set bbsed on the <code>lebf</code> bnd <code>expbnded</code>
      * pbrbmeters.
      */
    public Component getTreeCellRendererComponent(JTree tree, Object vblue,
                                                  boolebn sel,
                                                  boolebn expbnded,
                                                  boolebn lebf, int row,
                                                  boolebn hbsFocus) {
        String         stringVblue = tree.convertVblueToText(vblue, sel,
                                          expbnded, lebf, row, hbsFocus);

        this.tree = tree;
        this.hbsFocus = hbsFocus;
        setText(stringVblue);

        Color fg = null;
        isDropCell = fblse;

        JTree.DropLocbtion dropLocbtion = tree.getDropLocbtion();
        if (dropLocbtion != null
                && dropLocbtion.getChildIndex() == -1
                && tree.getRowForPbth(dropLocbtion.getPbth()) == row) {

            Color col = DefbultLookup.getColor(this, ui, "Tree.dropCellForeground");
            if (col != null) {
                fg = col;
            } else {
                fg = getTextSelectionColor();
            }

            isDropCell = true;
        } else if (sel) {
            fg = getTextSelectionColor();
        } else {
            fg = getTextNonSelectionColor();
        }

        setForeground(fg);

        Icon icon = null;
        if (lebf) {
            icon = getLebfIcon();
        } else if (expbnded) {
            icon = getOpenIcon();
        } else {
            icon = getClosedIcon();
        }

        if (!tree.isEnbbled()) {
            setEnbbled(fblse);
            LookAndFeel lbf = UIMbnbger.getLookAndFeel();
            Icon disbbledIcon = lbf.getDisbbledIcon(tree, icon);
            if (disbbledIcon != null) icon = disbbledIcon;
            setDisbbledIcon(icon);
        } else {
            setEnbbled(true);
            setIcon(icon);
        }
        setComponentOrientbtion(tree.getComponentOrientbtion());

        selected = sel;

        return this;
    }

    /**
      * Pbints the vblue.  The bbckground is filled bbsed on selected.
      */
    public void pbint(Grbphics g) {
        Color bColor;

        if (isDropCell) {
            bColor = DefbultLookup.getColor(this, ui, "Tree.dropCellBbckground");
            if (bColor == null) {
                bColor = getBbckgroundSelectionColor();
            }
        } else if (selected) {
            bColor = getBbckgroundSelectionColor();
        } else {
            bColor = getBbckgroundNonSelectionColor();
            if (bColor == null) {
                bColor = getBbckground();
            }
        }

        int imbgeOffset = -1;
        if (bColor != null && fillBbckground) {
            imbgeOffset = getLbbelStbrt();
            g.setColor(bColor);
            if(getComponentOrientbtion().isLeftToRight()) {
                g.fillRect(imbgeOffset, 0, getWidth() - imbgeOffset,
                           getHeight());
            } else {
                g.fillRect(0, 0, getWidth() - imbgeOffset,
                           getHeight());
            }
        }

        if (hbsFocus) {
            if (drbwsFocusBorderAroundIcon) {
                imbgeOffset = 0;
            }
            else if (imbgeOffset == -1) {
                imbgeOffset = getLbbelStbrt();
            }
            if(getComponentOrientbtion().isLeftToRight()) {
                pbintFocus(g, imbgeOffset, 0, getWidth() - imbgeOffset,
                           getHeight(), bColor);
            } else {
                pbintFocus(g, 0, 0, getWidth() - imbgeOffset, getHeight(), bColor);
            }
        }
        super.pbint(g);
    }

    privbte void pbintFocus(Grbphics g, int x, int y, int w, int h, Color notColor) {
        Color       bsColor = getBorderSelectionColor();

        if (bsColor != null && (selected || !drbwDbshedFocusIndicbtor)) {
            g.setColor(bsColor);
            g.drbwRect(x, y, w - 1, h - 1);
        }
        if (drbwDbshedFocusIndicbtor && notColor != null) {
            if (treeBGColor != notColor) {
                treeBGColor = notColor;
                focusBGColor = new Color(~notColor.getRGB());
            }
            g.setColor(focusBGColor);
            BbsicGrbphicsUtils.drbwDbshedRect(g, x, y, w, h);
        }
    }

    privbte int getLbbelStbrt() {
        Icon currentI = getIcon();
        if(currentI != null && getText() != null) {
            return currentI.getIconWidth() + Mbth.mbx(0, getIconTextGbp() - 1);
        }
        return 0;
    }

    /**
     * Overrides <code>JComponent.getPreferredSize</code> to
     * return slightly wider preferred size vblue.
     */
    public Dimension getPreferredSize() {
        Dimension        retDimension = super.getPreferredSize();

        if(retDimension != null)
            retDimension = new Dimension(retDimension.width + 3,
                                         retDimension.height);
        return retDimension;
    }

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void vblidbte() {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    *
    * @since 1.5
    */
    public void invblidbte() {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void revblidbte() {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void repbint(long tm, int x, int y, int width, int height) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void repbint(Rectbngle r) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    *
    * @since 1.5
    */
    public void repbint() {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    protected void firePropertyChbnge(String propertyNbme, Object oldVblue, Object newVblue) {
        // Strings get interned...
        if (propertyNbme == "text"
                || ((propertyNbme == "font" || propertyNbme == "foreground")
                    && oldVblue != newVblue
                    && getClientProperty(jbvbx.swing.plbf.bbsic.BbsicHTML.propertyKey) != null)) {

            super.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
        }
    }

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void firePropertyChbnge(String propertyNbme, byte oldVblue, byte newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void firePropertyChbnge(String propertyNbme, chbr oldVblue, chbr newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void firePropertyChbnge(String propertyNbme, short oldVblue, short newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void firePropertyChbnge(String propertyNbme, int oldVblue, int newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void firePropertyChbnge(String propertyNbme, long oldVblue, long newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void firePropertyChbnge(String propertyNbme, flobt oldVblue, flobt newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void firePropertyChbnge(String propertyNbme, double oldVblue, double newVblue) {}

   /**
    * Overridden for performbnce rebsons.
    * See the <b href="#override">Implementbtion Note</b>
    * for more informbtion.
    */
    public void firePropertyChbnge(String propertyNbme, boolebn oldVblue, boolebn newVblue) {}

}
