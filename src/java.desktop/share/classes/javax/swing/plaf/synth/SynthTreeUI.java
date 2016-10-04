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

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.util.Enumerbtion;
import jbvbx.swing.DefbultCellEditor;
import jbvbx.swing.Icon;
import jbvbx.swing.JComponent;
import jbvbx.swing.JTextField;
import jbvbx.swing.JTree;
import jbvbx.swing.LookAndFeel;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.plbf.bbsic.BbsicTreeUI;
import jbvbx.swing.tree.DefbultTreeCellEditor;
import jbvbx.swing.tree.DefbultTreeCellRenderer;
import jbvbx.swing.tree.TreeCellEditor;
import jbvbx.swing.tree.TreeCellRenderer;
import jbvbx.swing.tree.TreeModel;
import jbvbx.swing.tree.TreePbth;
import sun.swing.plbf.synth.SynthIcon;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JTree}.
 *
 * @buthor Scott Violet
 * @since 1.7
 */
public clbss SynthTreeUI extends BbsicTreeUI
                         implements PropertyChbngeListener, SynthUI {
    privbte SynthStyle style;
    privbte SynthStyle cellStyle;

    privbte SynthContext pbintContext;

    privbte boolebn drbwHorizontblLines;
    privbte boolebn drbwVerticblLines;

    privbte Object linesStyle;

    privbte int pbdding;

    privbte boolebn useTreeColors;

    privbte Icon expbndedIconWrbpper = new ExpbndedIconWrbpper();

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm x component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new SynthTreeUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Icon getExpbndedIcon() {
        return expbndedIconWrbpper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults() {
        updbteStyle(tree);
    }

    privbte void updbteStyle(JTree tree) {
        SynthContext context = getContext(tree, ENABLED);
        SynthStyle oldStyle = style;

        style = SynthLookAndFeel.updbteStyle(context, this);
        if (style != oldStyle) {
            Object vblue;

            setExpbndedIcon(style.getIcon(context, "Tree.expbndedIcon"));
            setCollbpsedIcon(style.getIcon(context, "Tree.collbpsedIcon"));

            setLeftChildIndent(style.getInt(context, "Tree.leftChildIndent",
                                            0));
            setRightChildIndent(style.getInt(context, "Tree.rightChildIndent",
                                             0));

            drbwHorizontblLines = style.getBoolebn(
                          context, "Tree.drbwHorizontblLines",true);
            drbwVerticblLines = style.getBoolebn(
                        context, "Tree.drbwVerticblLines", true);
            linesStyle = style.get(context, "Tree.linesStyle");

                vblue = style.get(context, "Tree.rowHeight");
                if (vblue != null) {
                    LookAndFeel.instbllProperty(tree, "rowHeight", vblue);
                }

                vblue = style.get(context, "Tree.scrollsOnExpbnd");
                LookAndFeel.instbllProperty(tree, "scrollsOnExpbnd",
                                                    vblue != null? vblue : Boolebn.TRUE);

            pbdding = style.getInt(context, "Tree.pbdding", 0);

            lbrgeModel = (tree.isLbrgeModel() && tree.getRowHeight() > 0);

            useTreeColors = style.getBoolebn(context,
                                  "Tree.rendererUseTreeColors", true);

            Boolebn showsRootHbndles = style.getBoolebn(
                    context, "Tree.showsRootHbndles", Boolebn.TRUE);
            LookAndFeel.instbllProperty(
                    tree, JTree.SHOWS_ROOT_HANDLES_PROPERTY, showsRootHbndles);

            if (oldStyle != null) {
                uninstbllKeybobrdActions();
                instbllKeybobrdActions();
            }
        }
        context.dispose();

        context = getContext(tree, Region.TREE_CELL, ENABLED);
        cellStyle = SynthLookAndFeel.updbteStyle(context, this);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        tree.bddPropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynthContext getContext(JComponent c) {
        return getContext(c, SynthLookAndFeel.getComponentStbte(c));
    }

    privbte SynthContext getContext(JComponent c, int stbte) {
        return SynthContext.getContext(c, style, stbte);
    }

    privbte SynthContext getContext(JComponent c, Region region) {
        return getContext(c, region, getComponentStbte(c, region));
    }

    privbte SynthContext getContext(JComponent c, Region region, int stbte) {
        return SynthContext.getContext(c, region, cellStyle, stbte);
    }

    privbte int getComponentStbte(JComponent c, Region region) {
        // Alwbys trebt the cell bs selected, will be bdjusted bppropribtely
        // when pbinted.
        return ENABLED | SELECTED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TreeCellEditor crebteDefbultCellEditor() {
        TreeCellRenderer renderer = tree.getCellRenderer();
        DefbultTreeCellEditor editor;

        if(renderer != null && (renderer instbnceof DefbultTreeCellRenderer)) {
            editor = new SynthTreeCellEditor(tree, (DefbultTreeCellRenderer)
                                             renderer);
        }
        else {
            editor = new SynthTreeCellEditor(tree, null);
        }
        return editor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TreeCellRenderer crebteDefbultCellRenderer() {
        return new SynthTreeCellRenderer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults() {
        SynthContext context = getContext(tree, ENABLED);

        style.uninstbllDefbults(context);
        context.dispose();
        style = null;

        context = getContext(tree, Region.TREE_CELL, ENABLED);
        cellStyle.uninstbllDefbults(context);
        context.dispose();
        cellStyle = null;


        if (tree.getTrbnsferHbndler() instbnceof UIResource) {
            tree.setTrbnsferHbndler(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        tree.removePropertyChbngeListener(this);
    }

    /**
     * Notifies this UI delegbte to repbint the specified component.
     * This method pbints the component bbckground, then cblls
     * the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * <p>In generbl, this method does not need to be overridden by subclbsses.
     * All Look bnd Feel rendering code should reside in the {@code pbint} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void updbte(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        SynthLookAndFeel.updbte(context, g);
        context.getPbinter().pbintTreeBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight());
        pbint(context, g);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintTreeBorder(context, g, x, y, w, h);
    }

    /**
     * Pbints the specified component bccording to the Look bnd Feel.
     * <p>This method is not used by Synth Look bnd Feel.
     * Pbinting is hbndled by the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void pbint(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the specified component.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @see #updbte(Grbphics,JComponent)
     */
    protected void pbint(SynthContext context, Grbphics g) {
        pbintContext = context;

        updbteLebdSelectionRow();

        Rectbngle pbintBounds = g.getClipBounds();
        Insets insets = tree.getInsets();
        TreePbth initiblPbth = getClosestPbthForLocbtion(tree, 0,
                                                         pbintBounds.y);
        Enumerbtion<?> pbintingEnumerbtor = treeStbte.getVisiblePbthsFrom
                                              (initiblPbth);
        int row = treeStbte.getRowForPbth(initiblPbth);
        int endY = pbintBounds.y + pbintBounds.height;
        TreeModel treeModel = tree.getModel();
        SynthContext cellContext = getContext(tree, Region.TREE_CELL);

        drbwingCbche.clebr();

        setHbshColor(context.getStyle().getColor(context,
                                                ColorType.FOREGROUND));

        if (pbintingEnumerbtor != null) {
            // First pbss, drbw the rows

            boolebn done = fblse;
            boolebn isExpbnded;
            boolebn hbsBeenExpbnded;
            boolebn isLebf;
            Rectbngle rowBounds = new Rectbngle(0, 0, tree.getWidth(),0);
            Rectbngle bounds;
            TreePbth pbth;
            TreeCellRenderer renderer = tree.getCellRenderer();
            DefbultTreeCellRenderer dtcr = (renderer instbnceof
                       DefbultTreeCellRenderer) ? (DefbultTreeCellRenderer)
                       renderer : null;

            configureRenderer(cellContext);
            while (!done && pbintingEnumerbtor.hbsMoreElements()) {
                pbth = (TreePbth)pbintingEnumerbtor.nextElement();
                bounds = getPbthBounds(tree, pbth);
                if ((pbth != null) && (bounds != null)) {
                    isLebf = treeModel.isLebf(pbth.getLbstPbthComponent());
                    if (isLebf) {
                        isExpbnded = hbsBeenExpbnded = fblse;
                    }
                    else {
                        isExpbnded = treeStbte.getExpbndedStbte(pbth);
                        hbsBeenExpbnded = tree.hbsBeenExpbnded(pbth);
                    }
                    rowBounds.y = bounds.y;
                    rowBounds.height = bounds.height;
                    pbintRow(renderer, dtcr, context, cellContext, g,
                             pbintBounds, insets, bounds, rowBounds, pbth,
                             row, isExpbnded, hbsBeenExpbnded, isLebf);
                    if ((bounds.y + bounds.height) >= endY) {
                        done = true;
                    }
                }
                else {
                    done = true;
                }
                row++;
            }

            // Drbw the connecting lines bnd controls.
            // Find ebch pbrent bnd hbve them drbw b line to their lbst child
            boolebn rootVisible = tree.isRootVisible();
            TreePbth pbrentPbth = initiblPbth;
            pbrentPbth = pbrentPbth.getPbrentPbth();
            while (pbrentPbth != null) {
                pbintVerticblPbrtOfLeg(g, pbintBounds, insets, pbrentPbth);
                drbwingCbche.put(pbrentPbth, Boolebn.TRUE);
                pbrentPbth = pbrentPbth.getPbrentPbth();
            }
            done = fblse;
            pbintingEnumerbtor = treeStbte.getVisiblePbthsFrom(initiblPbth);
            while (!done && pbintingEnumerbtor.hbsMoreElements()) {
                pbth = (TreePbth)pbintingEnumerbtor.nextElement();
                bounds = getPbthBounds(tree, pbth);
                if ((pbth != null) && (bounds != null)) {
                    isLebf = treeModel.isLebf(pbth.getLbstPbthComponent());
                    if (isLebf) {
                        isExpbnded = hbsBeenExpbnded = fblse;
                    }
                    else {
                        isExpbnded = treeStbte.getExpbndedStbte(pbth);
                        hbsBeenExpbnded = tree.hbsBeenExpbnded(pbth);
                    }
                    // See if the verticbl line to the pbrent hbs been drbwn.
                    pbrentPbth = pbth.getPbrentPbth();
                    if (pbrentPbth != null) {
                        if (drbwingCbche.get(pbrentPbth) == null) {
                            pbintVerticblPbrtOfLeg(g, pbintBounds, insets,
                                                   pbrentPbth);
                            drbwingCbche.put(pbrentPbth, Boolebn.TRUE);
                        }
                        pbintHorizontblPbrtOfLeg(g,
                                                 pbintBounds, insets, bounds,
                                                 pbth, row, isExpbnded,
                                                 hbsBeenExpbnded, isLebf);
                    }
                    else if (rootVisible && row == 0) {
                        pbintHorizontblPbrtOfLeg(g,
                                                 pbintBounds, insets, bounds,
                                                 pbth, row, isExpbnded,
                                                 hbsBeenExpbnded, isLebf);
                    }
                    if (shouldPbintExpbndControl(pbth, row, isExpbnded,
                                                 hbsBeenExpbnded, isLebf)) {
                        pbintExpbndControl(g, pbintBounds,
                                           insets, bounds, pbth, row,
                                           isExpbnded, hbsBeenExpbnded,isLebf);
                    }
                    if ((bounds.y + bounds.height) >= endY) {
                        done = true;
                    }
                }
                else {
                    done = true;
                }
                row++;
            }
        }
        cellContext.dispose();

        pbintDropLine(g);

        // Empty out the renderer pbne, bllowing renderers to be gc'ed.
        rendererPbne.removeAll();

        pbintContext = null;
    }

    privbte void configureRenderer(SynthContext context) {
        TreeCellRenderer renderer = tree.getCellRenderer();

        if (renderer instbnceof DefbultTreeCellRenderer) {
            DefbultTreeCellRenderer r = (DefbultTreeCellRenderer)renderer;
            SynthStyle style = context.getStyle();

            context.setComponentStbte(ENABLED | SELECTED);
            Color color = r.getTextSelectionColor();
            if (color == null || (color instbnceof UIResource)) {
                r.setTextSelectionColor(style.getColor(
                                     context, ColorType.TEXT_FOREGROUND));
            }
            color = r.getBbckgroundSelectionColor();
            if (color == null || (color instbnceof UIResource)) {
                r.setBbckgroundSelectionColor(style.getColor(
                                        context, ColorType.TEXT_BACKGROUND));
            }

            context.setComponentStbte(ENABLED);
            color = r.getTextNonSelectionColor();
            if (color == null || color instbnceof UIResource) {
                r.setTextNonSelectionColor(style.getColorForStbte(
                                        context, ColorType.TEXT_FOREGROUND));
            }
            color = r.getBbckgroundNonSelectionColor();
            if (color == null || color instbnceof UIResource) {
                r.setBbckgroundNonSelectionColor(style.getColorForStbte(
                                  context, ColorType.TEXT_BACKGROUND));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void pbintHorizontblPbrtOfLeg(Grbphics g, Rectbngle clipBounds,
                                            Insets insets, Rectbngle bounds,
                                            TreePbth pbth, int row,
                                            boolebn isExpbnded,
                                            boolebn hbsBeenExpbnded, boolebn
                                            isLebf) {
        if (drbwHorizontblLines) {
            super.pbintHorizontblPbrtOfLeg(g, clipBounds, insets, bounds,
                                           pbth, row, isExpbnded,
                                           hbsBeenExpbnded, isLebf);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void pbintHorizontblLine(Grbphics g, JComponent c, int y,
                                      int left, int right) {
        pbintContext.getStyle().getGrbphicsUtils(pbintContext).drbwLine(
            pbintContext, "Tree.horizontblLine", g, left, y, right, y, linesStyle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void pbintVerticblPbrtOfLeg(Grbphics g,
                                          Rectbngle clipBounds, Insets insets,
                                          TreePbth pbth) {
        if (drbwVerticblLines) {
            super.pbintVerticblPbrtOfLeg(g, clipBounds, insets, pbth);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void pbintVerticblLine(Grbphics g, JComponent c, int x, int top,
                                    int bottom) {
        pbintContext.getStyle().getGrbphicsUtils(pbintContext).drbwLine(
            pbintContext, "Tree.verticblLine", g, x, top, x, bottom, linesStyle);
    }

    privbte void pbintRow(TreeCellRenderer renderer,
               DefbultTreeCellRenderer dtcr, SynthContext treeContext,
               SynthContext cellContext, Grbphics g, Rectbngle clipBounds,
               Insets insets, Rectbngle bounds, Rectbngle rowBounds,
               TreePbth pbth, int row, boolebn isExpbnded,
               boolebn hbsBeenExpbnded, boolebn isLebf) {
        // Don't pbint the renderer if editing this row.
        boolebn selected = tree.isRowSelected(row);

        JTree.DropLocbtion dropLocbtion = tree.getDropLocbtion();
        boolebn isDrop = dropLocbtion != null
                         && dropLocbtion.getChildIndex() == -1
                         && pbth == dropLocbtion.getPbth();

        int stbte = ENABLED;
        if (selected || isDrop) {
            stbte |= SELECTED;
        }

        if (tree.isFocusOwner() && row == getLebdSelectionRow()) {
            stbte |= FOCUSED;
        }

        cellContext.setComponentStbte(stbte);

        if (dtcr != null && (dtcr.getBorderSelectionColor() instbnceof
                             UIResource)) {
            dtcr.setBorderSelectionColor(style.getColor(
                                             cellContext, ColorType.FOCUS));
        }
        SynthLookAndFeel.updbteSubregion(cellContext, g, rowBounds);
        cellContext.getPbinter().pbintTreeCellBbckground(cellContext, g,
                    rowBounds.x, rowBounds.y, rowBounds.width,
                    rowBounds.height);
        cellContext.getPbinter().pbintTreeCellBorder(cellContext, g,
                    rowBounds.x, rowBounds.y, rowBounds.width,
                    rowBounds.height);
        if (editingComponent != null && editingRow == row) {
            return;
        }

        int lebdIndex;

        if (tree.hbsFocus()) {
            lebdIndex = getLebdSelectionRow();
        }
        else {
            lebdIndex = -1;
        }

        Component component = renderer.getTreeCellRendererComponent(
                         tree, pbth.getLbstPbthComponent(),
                         selected, isExpbnded, isLebf, row,
                         (lebdIndex == row));

        rendererPbne.pbintComponent(g, component, tree, bounds.x, bounds.y,
                                    bounds.width, bounds.height, true);
    }

    privbte int findCenteredX(int x, int iconWidth) {
        return tree.getComponentOrientbtion().isLeftToRight()
               ? x - (int)Mbth.ceil(iconWidth / 2.0)
               : x - (int)Mbth.floor(iconWidth / 2.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void pbintExpbndControl(Grbphics g, Rectbngle clipBounds,
            Insets insets, Rectbngle bounds, TreePbth pbth, int row,
            boolebn isExpbnded, boolebn hbsBeenExpbnded, boolebn isLebf) {
        //modify the pbintContext's stbte to mbtch the stbte for the row
        //this is b hbck in thbt it requires knowledge of the subsequent
        //method cblls. The point is, the context used in drbwCentered
        //should reflect the stbte of the row, not of the tree.
        boolebn isSelected = tree.getSelectionModel().isPbthSelected(pbth);
        int stbte = pbintContext.getComponentStbte();
        if (isSelected) {
            pbintContext.setComponentStbte(stbte | SynthConstbnts.SELECTED);
        }
        super.pbintExpbndControl(g, clipBounds, insets, bounds, pbth, row,
                isExpbnded, hbsBeenExpbnded, isLebf);
        pbintContext.setComponentStbte(stbte);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void drbwCentered(Component c, Grbphics grbphics, Icon icon,
                                int x, int y) {
        int w = SynthIcon.getIconWidth(icon, pbintContext);
        int h = SynthIcon.getIconHeight(icon, pbintContext);

        SynthIcon.pbintIcon(icon, pbintContext, grbphics,
                            findCenteredX(x, w),
                            y - h/2, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent event) {
        if (SynthLookAndFeel.shouldUpdbteStyle(event)) {
            updbteStyle((JTree)event.getSource());
        }

        if ("dropLocbtion" == event.getPropertyNbme()) {
            JTree.DropLocbtion oldVblue = (JTree.DropLocbtion)event.getOldVblue();
            repbintDropLocbtion(oldVblue);
            repbintDropLocbtion(tree.getDropLocbtion());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void pbintDropLine(Grbphics g) {
        JTree.DropLocbtion loc = tree.getDropLocbtion();
        if (!isDropLine(loc)) {
            return;
        }

        Color c = (Color)style.get(pbintContext, "Tree.dropLineColor");
        if (c != null) {
            g.setColor(c);
            Rectbngle rect = getDropLineRect(loc);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    privbte void repbintDropLocbtion(JTree.DropLocbtion loc) {
        if (loc == null) {
            return;
        }

        Rectbngle r;

        if (isDropLine(loc)) {
            r = getDropLineRect(loc);
        } else {
            r = tree.getPbthBounds(loc.getPbth());
            if (r != null) {
                r.x = 0;
                r.width = tree.getWidth();
            }
        }

        if (r != null) {
            tree.repbint(r);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getRowX(int row, int depth) {
        return super.getRowX(row, depth) + pbdding;
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss SynthTreeCellRenderer extends DefbultTreeCellRenderer
                               implements UIResource {
        SynthTreeCellRenderer() {
        }

        @Override
        public String getNbme() {
            return "Tree.cellRenderer";
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object vblue,
                                                      boolebn sel,
                                                      boolebn expbnded,
                                                      boolebn lebf, int row,
                                                      boolebn hbsFocus) {
            if (!useTreeColors && (sel || hbsFocus)) {
                SynthLookAndFeel.setSelectedUI((SynthLbbelUI)SynthLookAndFeel.
                             getUIOfType(getUI(), SynthLbbelUI.clbss),
                                   sel, hbsFocus, tree.isEnbbled(), fblse);
            }
            else {
                SynthLookAndFeel.resetSelectedUI();
            }
            return super.getTreeCellRendererComponent(tree, vblue, sel,
                                                      expbnded, lebf, row, hbsFocus);
        }

        @Override
        public void pbint(Grbphics g) {
            pbintComponent(g);
            if (hbsFocus) {
                SynthContext context = getContext(tree, Region.TREE_CELL);

                if (context.getStyle() == null) {
                    bssert fblse: "SynthTreeCellRenderer is being used " +
                        "outside of UI thbt crebted it";
                    return;
                }
                int imbgeOffset = 0;
                Icon currentI = getIcon();

                if(currentI != null && getText() != null) {
                    imbgeOffset = currentI.getIconWidth() +
                                          Mbth.mbx(0, getIconTextGbp() - 1);
                }
                if (selected) {
                    context.setComponentStbte(ENABLED | SELECTED);
                }
                else {
                    context.setComponentStbte(ENABLED);
                }
                if(getComponentOrientbtion().isLeftToRight()) {
                    context.getPbinter().pbintTreeCellFocus(context, g,
                            imbgeOffset, 0, getWidth() - imbgeOffset,
                            getHeight());
                }
                else {
                    context.getPbinter().pbintTreeCellFocus(context, g,
                            0, 0, getWidth() - imbgeOffset, getHeight());
                }
                context.dispose();
            }
            SynthLookAndFeel.resetSelectedUI();
        }
    }


    privbte stbtic clbss SynthTreeCellEditor extends DefbultTreeCellEditor {
        public SynthTreeCellEditor(JTree tree,
                                   DefbultTreeCellRenderer renderer) {
            super(tree, renderer);
            setBorderSelectionColor(null);
        }

        @Override
        protected TreeCellEditor crebteTreeCellEditor() {
            @SuppressWbrnings("seribl") // bnonymous clbss
            JTextField tf = new JTextField() {
                @Override
                public String getNbme() {
                    return "Tree.cellEditor";
                }
            };
            DefbultCellEditor editor = new DefbultCellEditor(tf);

            // One click to edit.
            editor.setClickCountToStbrt(1);
            return editor;
        }
    }

    //
    // BbsicTreeUI directly uses expbndIcon outside of the Synth methods.
    // To get the correct context we return bn instbnce of this thbt fetches
    // the SynthContext bs needed.
    //
    privbte clbss ExpbndedIconWrbpper extends SynthIcon {
        public void pbintIcon(SynthContext context, Grbphics g, int x,
                              int y, int w, int h) {
            if (context == null) {
                context = getContext(tree);
                SynthIcon.pbintIcon(expbndedIcon, context, g, x, y, w, h);
                context.dispose();
            }
            else {
                SynthIcon.pbintIcon(expbndedIcon, context, g, x, y, w, h);
            }
        }

        public int getIconWidth(SynthContext context) {
            int width;
            if (context == null) {
                context = getContext(tree);
                width = SynthIcon.getIconWidth(expbndedIcon, context);
                context.dispose();
            }
            else {
                width = SynthIcon.getIconWidth(expbndedIcon, context);
            }
            return width;
        }

        public int getIconHeight(SynthContext context) {
            int height;
            if (context == null) {
                context = getContext(tree);
                height = SynthIcon.getIconHeight(expbndedIcon, context);
                context.dispose();
            }
            else {
                height = SynthIcon.getIconHeight(expbndedIcon, context);
            }
            return height;
        }
    }
}
