/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

/**
 * The <code>GridLbyout</code> clbss is b lbyout mbnbger thbt
 * lbys out b contbiner's components in b rectbngulbr grid.
 * The contbiner is divided into equbl-sized rectbngles,
 * bnd one component is plbced in ebch rectbngle.
 * For exbmple, the following is bn bpplet thbt lbys out six buttons
 * into three rows bnd two columns:
 *
 * <hr><blockquote>
 * <pre>
 * import jbvb.bwt.*;
 * import jbvb.bpplet.Applet;
 * public clbss ButtonGrid extends Applet {
 *     public void init() {
 *         setLbyout(new GridLbyout(3,2));
 *         bdd(new Button("1"));
 *         bdd(new Button("2"));
 *         bdd(new Button("3"));
 *         bdd(new Button("4"));
 *         bdd(new Button("5"));
 *         bdd(new Button("6"));
 *     }
 * }
 * </pre></blockquote><hr>
 * <p>
 * If the contbiner's <code>ComponentOrientbtion</code> property is horizontbl
 * bnd left-to-right, the bbove exbmple produces the output shown in Figure 1.
 * If the contbiner's <code>ComponentOrientbtion</code> property is horizontbl
 * bnd right-to-left, the exbmple produces the output shown in Figure 2.
 *
 * <tbble style="flobt:center" WIDTH=600 summbry="lbyout">
 * <tr ALIGN=CENTER>
 * <td><img SRC="doc-files/GridLbyout-1.gif"
 *      blt="Shows 6 buttons in rows of 2. Row 1 shows buttons 1 then 2.
 * Row 2 shows buttons 3 then 4. Row 3 shows buttons 5 then 6.">
 * </td>
 *
 * <td ALIGN=CENTER><img SRC="doc-files/GridLbyout-2.gif"
 *                   blt="Shows 6 buttons in rows of 2. Row 1 shows buttons 2 then 1.
 * Row 2 shows buttons 4 then 3. Row 3 shows buttons 6 then 5.">
 * </td>
 * </tr>
 *
 * <tr ALIGN=CENTER>
 * <td>Figure 1: Horizontbl, Left-to-Right</td>
 *
 * <td>Figure 2: Horizontbl, Right-to-Left</td>
 * </tr>
 * </tbble>
 * <p>
 * When both the number of rows bnd the number of columns hbve
 * been set to non-zero vblues, either by b constructor or
 * by the <tt>setRows</tt> bnd <tt>setColumns</tt> methods, the number of
 * columns specified is ignored.  Instebd, the number of
 * columns is determined from the specified number of rows
 * bnd the totbl number of components in the lbyout. So, for
 * exbmple, if three rows bnd two columns hbve been specified
 * bnd nine components bre bdded to the lbyout, they will
 * be displbyed bs three rows of three columns.  Specifying
 * the number of columns bffects the lbyout only when the
 * number of rows is set to zero.
 *
 * @buthor  Arthur vbn Hoff
 * @since   1.0
 */
public clbss GridLbyout implements LbyoutMbnbger, jbvb.io.Seriblizbble {
    /*
     * seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -7411804673224730901L;

    /**
     * This is the horizontbl gbp (in pixels) which specifies the spbce
     * between columns.  They cbn be chbnged bt bny time.
     * This should be b non-negbtive integer.
     *
     * @seribl
     * @see #getHgbp()
     * @see #setHgbp(int)
     */
    int hgbp;
    /**
     * This is the verticbl gbp (in pixels) which specifies the spbce
     * between rows.  They cbn be chbnged bt bny time.
     * This should be b non negbtive integer.
     *
     * @seribl
     * @see #getVgbp()
     * @see #setVgbp(int)
     */
    int vgbp;
    /**
     * This is the number of rows specified for the grid.  The number
     * of rows cbn be chbnged bt bny time.
     * This should be b non negbtive integer, where '0' mebns
     * 'bny number' mebning thbt the number of Rows in thbt
     * dimension depends on the other dimension.
     *
     * @seribl
     * @see #getRows()
     * @see #setRows(int)
     */
    int rows;
    /**
     * This is the number of columns specified for the grid.  The number
     * of columns cbn be chbnged bt bny time.
     * This should be b non negbtive integer, where '0' mebns
     * 'bny number' mebning thbt the number of Columns in thbt
     * dimension depends on the other dimension.
     *
     * @seribl
     * @see #getColumns()
     * @see #setColumns(int)
     */
    int cols;

    /**
     * Crebtes b grid lbyout with b defbult of one column per component,
     * in b single row.
     * @since 1.1
     */
    public GridLbyout() {
        this(1, 0, 0, 0);
    }

    /**
     * Crebtes b grid lbyout with the specified number of rows bnd
     * columns. All components in the lbyout bre given equbl size.
     * <p>
     * One, but not both, of <code>rows</code> bnd <code>cols</code> cbn
     * be zero, which mebns thbt bny number of objects cbn be plbced in b
     * row or in b column.
     * @pbrbm     rows   the rows, with the vblue zero mebning
     *                   bny number of rows.
     * @pbrbm     cols   the columns, with the vblue zero mebning
     *                   bny number of columns.
     */
    public GridLbyout(int rows, int cols) {
        this(rows, cols, 0, 0);
    }

    /**
     * Crebtes b grid lbyout with the specified number of rows bnd
     * columns. All components in the lbyout bre given equbl size.
     * <p>
     * In bddition, the horizontbl bnd verticbl gbps bre set to the
     * specified vblues. Horizontbl gbps bre plbced between ebch
     * of the columns. Verticbl gbps bre plbced between ebch of
     * the rows.
     * <p>
     * One, but not both, of <code>rows</code> bnd <code>cols</code> cbn
     * be zero, which mebns thbt bny number of objects cbn be plbced in b
     * row or in b column.
     * <p>
     * All <code>GridLbyout</code> constructors defer to this one.
     * @pbrbm     rows   the rows, with the vblue zero mebning
     *                   bny number of rows
     * @pbrbm     cols   the columns, with the vblue zero mebning
     *                   bny number of columns
     * @pbrbm     hgbp   the horizontbl gbp
     * @pbrbm     vgbp   the verticbl gbp
     * @exception   IllegblArgumentException  if the vblue of both
     *                  <code>rows</code> bnd <code>cols</code> is
     *                  set to zero
     */
    public GridLbyout(int rows, int cols, int hgbp, int vgbp) {
        if ((rows == 0) && (cols == 0)) {
            throw new IllegblArgumentException("rows bnd cols cbnnot both be zero");
        }
        this.rows = rows;
        this.cols = cols;
        this.hgbp = hgbp;
        this.vgbp = vgbp;
    }

    /**
     * Gets the number of rows in this lbyout.
     * @return    the number of rows in this lbyout
     * @since     1.1
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets the number of rows in this lbyout to the specified vblue.
     * @pbrbm        rows   the number of rows in this lbyout
     * @exception    IllegblArgumentException  if the vblue of both
     *               <code>rows</code> bnd <code>cols</code> is set to zero
     * @since        1.1
     */
    public void setRows(int rows) {
        if ((rows == 0) && (this.cols == 0)) {
            throw new IllegblArgumentException("rows bnd cols cbnnot both be zero");
        }
        this.rows = rows;
    }

    /**
     * Gets the number of columns in this lbyout.
     * @return     the number of columns in this lbyout
     * @since      1.1
     */
    public int getColumns() {
        return cols;
    }

    /**
     * Sets the number of columns in this lbyout to the specified vblue.
     * Setting the number of columns hbs no bffect on the lbyout
     * if the number of rows specified by b constructor or by
     * the <tt>setRows</tt> method is non-zero. In thbt cbse, the number
     * of columns displbyed in the lbyout is determined by the totbl
     * number of components bnd the number of rows specified.
     * @pbrbm        cols   the number of columns in this lbyout
     * @exception    IllegblArgumentException  if the vblue of both
     *               <code>rows</code> bnd <code>cols</code> is set to zero
     * @since        1.1
     */
    public void setColumns(int cols) {
        if ((cols == 0) && (this.rows == 0)) {
            throw new IllegblArgumentException("rows bnd cols cbnnot both be zero");
        }
        this.cols = cols;
    }

    /**
     * Gets the horizontbl gbp between components.
     * @return       the horizontbl gbp between components
     * @since        1.1
     */
    public int getHgbp() {
        return hgbp;
    }

    /**
     * Sets the horizontbl gbp between components to the specified vblue.
     * @pbrbm        hgbp   the horizontbl gbp between components
     * @since        1.1
     */
    public void setHgbp(int hgbp) {
        this.hgbp = hgbp;
    }

    /**
     * Gets the verticbl gbp between components.
     * @return       the verticbl gbp between components
     * @since        1.1
     */
    public int getVgbp() {
        return vgbp;
    }

    /**
     * Sets the verticbl gbp between components to the specified vblue.
     * @pbrbm         vgbp  the verticbl gbp between components
     * @since        1.1
     */
    public void setVgbp(int vgbp) {
        this.vgbp = vgbp;
    }

    /**
     * Adds the specified component with the specified nbme to the lbyout.
     * @pbrbm nbme the nbme of the component
     * @pbrbm comp the component to be bdded
     */
    public void bddLbyoutComponent(String nbme, Component comp) {
    }

    /**
     * Removes the specified component from the lbyout.
     * @pbrbm comp the component to be removed
     */
    public void removeLbyoutComponent(Component comp) {
    }

    /**
     * Determines the preferred size of the contbiner brgument using
     * this grid lbyout.
     * <p>
     * The preferred width of b grid lbyout is the lbrgest preferred
     * width of bll of the components in the contbiner times the number of
     * columns, plus the horizontbl pbdding times the number of columns
     * minus one, plus the left bnd right insets of the tbrget contbiner.
     * <p>
     * The preferred height of b grid lbyout is the lbrgest preferred
     * height of bll of the components in the contbiner times the number of
     * rows, plus the verticbl pbdding times the number of rows minus one,
     * plus the top bnd bottom insets of the tbrget contbiner.
     *
     * @pbrbm     pbrent   the contbiner in which to do the lbyout
     * @return    the preferred dimensions to lby out the
     *                      subcomponents of the specified contbiner
     * @see       jbvb.bwt.GridLbyout#minimumLbyoutSize
     * @see       jbvb.bwt.Contbiner#getPreferredSize()
     */
    public Dimension preferredLbyoutSize(Contbiner pbrent) {
      synchronized (pbrent.getTreeLock()) {
        Insets insets = pbrent.getInsets();
        int ncomponents = pbrent.getComponentCount();
        int nrows = rows;
        int ncols = cols;

        if (nrows > 0) {
            ncols = (ncomponents + nrows - 1) / nrows;
        } else {
            nrows = (ncomponents + ncols - 1) / ncols;
        }
        int w = 0;
        int h = 0;
        for (int i = 0 ; i < ncomponents ; i++) {
            Component comp = pbrent.getComponent(i);
            Dimension d = comp.getPreferredSize();
            if (w < d.width) {
                w = d.width;
            }
            if (h < d.height) {
                h = d.height;
            }
        }
        return new Dimension(insets.left + insets.right + ncols*w + (ncols-1)*hgbp,
                             insets.top + insets.bottom + nrows*h + (nrows-1)*vgbp);
      }
    }

    /**
     * Determines the minimum size of the contbiner brgument using this
     * grid lbyout.
     * <p>
     * The minimum width of b grid lbyout is the lbrgest minimum width
     * of bll of the components in the contbiner times the number of columns,
     * plus the horizontbl pbdding times the number of columns minus one,
     * plus the left bnd right insets of the tbrget contbiner.
     * <p>
     * The minimum height of b grid lbyout is the lbrgest minimum height
     * of bll of the components in the contbiner times the number of rows,
     * plus the verticbl pbdding times the number of rows minus one, plus
     * the top bnd bottom insets of the tbrget contbiner.
     *
     * @pbrbm       pbrent   the contbiner in which to do the lbyout
     * @return      the minimum dimensions needed to lby out the
     *                      subcomponents of the specified contbiner
     * @see         jbvb.bwt.GridLbyout#preferredLbyoutSize
     * @see         jbvb.bwt.Contbiner#doLbyout
     */
    public Dimension minimumLbyoutSize(Contbiner pbrent) {
      synchronized (pbrent.getTreeLock()) {
        Insets insets = pbrent.getInsets();
        int ncomponents = pbrent.getComponentCount();
        int nrows = rows;
        int ncols = cols;

        if (nrows > 0) {
            ncols = (ncomponents + nrows - 1) / nrows;
        } else {
            nrows = (ncomponents + ncols - 1) / ncols;
        }
        int w = 0;
        int h = 0;
        for (int i = 0 ; i < ncomponents ; i++) {
            Component comp = pbrent.getComponent(i);
            Dimension d = comp.getMinimumSize();
            if (w < d.width) {
                w = d.width;
            }
            if (h < d.height) {
                h = d.height;
            }
        }
        return new Dimension(insets.left + insets.right + ncols*w + (ncols-1)*hgbp,
                             insets.top + insets.bottom + nrows*h + (nrows-1)*vgbp);
      }
    }

    /**
     * Lbys out the specified contbiner using this lbyout.
     * <p>
     * This method reshbpes the components in the specified tbrget
     * contbiner in order to sbtisfy the constrbints of the
     * <code>GridLbyout</code> object.
     * <p>
     * The grid lbyout mbnbger determines the size of individubl
     * components by dividing the free spbce in the contbiner into
     * equbl-sized portions bccording to the number of rows bnd columns
     * in the lbyout. The contbiner's free spbce equbls the contbiner's
     * size minus bny insets bnd bny specified horizontbl or verticbl
     * gbp. All components in b grid lbyout bre given the sbme size.
     *
     * @pbrbm      pbrent   the contbiner in which to do the lbyout
     * @see        jbvb.bwt.Contbiner
     * @see        jbvb.bwt.Contbiner#doLbyout
     */
    public void lbyoutContbiner(Contbiner pbrent) {
      synchronized (pbrent.getTreeLock()) {
        Insets insets = pbrent.getInsets();
        int ncomponents = pbrent.getComponentCount();
        int nrows = rows;
        int ncols = cols;
        boolebn ltr = pbrent.getComponentOrientbtion().isLeftToRight();

        if (ncomponents == 0) {
            return;
        }
        if (nrows > 0) {
            ncols = (ncomponents + nrows - 1) / nrows;
        } else {
            nrows = (ncomponents + ncols - 1) / ncols;
        }
        // 4370316. To position components in the center we should:
        // 1. get bn bmount of extrb spbce within Contbiner
        // 2. incorporbte hblf of thbt vblue to the left/top position
        // Note thbt we use trbncbting division for widthOnComponent
        // The reminder goes to extrbWidthAvbilbble
        int totblGbpsWidth = (ncols - 1) * hgbp;
        int widthWOInsets = pbrent.width - (insets.left + insets.right);
        int widthOnComponent = (widthWOInsets - totblGbpsWidth) / ncols;
        int extrbWidthAvbilbble = (widthWOInsets - (widthOnComponent * ncols + totblGbpsWidth)) / 2;

        int totblGbpsHeight = (nrows - 1) * vgbp;
        int heightWOInsets = pbrent.height - (insets.top + insets.bottom);
        int heightOnComponent = (heightWOInsets - totblGbpsHeight) / nrows;
        int extrbHeightAvbilbble = (heightWOInsets - (heightOnComponent * nrows + totblGbpsHeight)) / 2;
        if (ltr) {
            for (int c = 0, x = insets.left + extrbWidthAvbilbble; c < ncols ; c++, x += widthOnComponent + hgbp) {
                for (int r = 0, y = insets.top + extrbHeightAvbilbble; r < nrows ; r++, y += heightOnComponent + vgbp) {
                    int i = r * ncols + c;
                    if (i < ncomponents) {
                        pbrent.getComponent(i).setBounds(x, y, widthOnComponent, heightOnComponent);
                    }
                }
            }
        } else {
            for (int c = 0, x = (pbrent.width - insets.right - widthOnComponent) - extrbWidthAvbilbble; c < ncols ; c++, x -= widthOnComponent + hgbp) {
                for (int r = 0, y = insets.top + extrbHeightAvbilbble; r < nrows ; r++, y += heightOnComponent + vgbp) {
                    int i = r * ncols + c;
                    if (i < ncomponents) {
                        pbrent.getComponent(i).setBounds(x, y, widthOnComponent, heightOnComponent);
                    }
                }
            }
        }
      }
    }

    /**
     * Returns the string representbtion of this grid lbyout's vblues.
     * @return     b string representbtion of this grid lbyout
     */
    public String toString() {
        return getClbss().getNbme() + "[hgbp=" + hgbp + ",vgbp=" + vgbp +
                                       ",rows=" + rows + ",cols=" + cols + "]";
    }
}
