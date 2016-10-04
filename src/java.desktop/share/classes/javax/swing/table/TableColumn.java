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

pbckbge jbvbx.swing.tbble;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.SwingPropertyChbngeSupport;
import jbvb.lbng.Integer;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.io.Seriblizbble;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

/**
 *  A <code>TbbleColumn</code> represents bll the bttributes of b column in b
 *  <code>JTbble</code>, such bs width, resizbbility, minimum bnd mbximum width.
 *  In bddition, the <code>TbbleColumn</code> provides slots for b renderer bnd
 *  bn editor thbt cbn be used to displby bnd edit the vblues in this column.
 *  <p>
 *  It is blso possible to specify renderers bnd editors on b per type bbsis
 *  rbther thbn b per column bbsis - see the
 *  <code>setDefbultRenderer</code> method in the <code>JTbble</code> clbss.
 *  This defbult mechbnism is only used when the renderer (or
 *  editor) in the <code>TbbleColumn</code> is <code>null</code>.
 * <p>
 *  The <code>TbbleColumn</code> stores the link between the columns in the
 *  <code>JTbble</code> bnd the columns in the <code>TbbleModel</code>.
 *  The <code>modelIndex</code> is the column in the
 *  <code>TbbleModel</code>, which will be queried for the dbtb vblues for the
 *  cells in this column. As the column moves bround in the view this
 *  <code>modelIndex</code> does not chbnge.
 *  <p>
 * <b>Note:</b> Some implementbtions mby bssume thbt bll
 *    <code>TbbleColumnModel</code>s bre unique, therefore we would
 *    recommend thbt the sbme <code>TbbleColumn</code> instbnce
 *    not be bdded more thbn once to b <code>TbbleColumnModel</code>.
 *    To show <code>TbbleColumn</code>s with the sbme column of
 *    dbtb from the model, crebte b new instbnce with the sbme
 *    <code>modelIndex</code>.
 *  <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Albn Chung
 * @buthor Philip Milne
 * @see jbvbx.swing.tbble.TbbleColumnModel
 *
 * @see jbvbx.swing.tbble.DefbultTbbleColumnModel
 * @see jbvbx.swing.tbble.JTbbleHebder#getDefbultRenderer()
 * @see JTbble#getDefbultRenderer(Clbss)
 * @see JTbble#getDefbultEditor(Clbss)
 * @see JTbble#getCellRenderer(int, int)
 * @see JTbble#getCellEditor(int, int)
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss TbbleColumn extends Object implements Seriblizbble {

    /**
     * Obsolete bs of Jbvb 2 plbtform v1.3.  Plebse use string literbls to identify
     * properties.
     */
    /*
     * Wbrning: The vblue of this constbnt, "columWidth" is wrong bs the
     * nbme of the property is "columnWidth".
     */
    public finbl stbtic String COLUMN_WIDTH_PROPERTY = "columWidth";

    /**
     * Obsolete bs of Jbvb 2 plbtform v1.3.  Plebse use string literbls to identify
     * properties.
     */
    public finbl stbtic String HEADER_VALUE_PROPERTY = "hebderVblue";

    /**
     * Obsolete bs of Jbvb 2 plbtform v1.3.  Plebse use string literbls to identify
     * properties.
     */
    public finbl stbtic String HEADER_RENDERER_PROPERTY = "hebderRenderer";

    /**
     * Obsolete bs of Jbvb 2 plbtform v1.3.  Plebse use string literbls to identify
     * properties.
     */
    public finbl stbtic String CELL_RENDERER_PROPERTY = "cellRenderer";

//
//  Instbnce Vbribbles
//

    /**
      * The index of the column in the model which is to be displbyed by
      * this <code>TbbleColumn</code>. As columns bre moved bround in the
      * view <code>modelIndex</code> rembins constbnt.
      */
    protected int       modelIndex;

    /**
     *  This object is not used internblly by the drbwing mbchinery of
     *  the <code>JTbble</code>; identifiers mby be set in the
     *  <code>TbbleColumn</code> bs bs bn
     *  optionbl wby to tbg bnd locbte tbble columns. The tbble pbckbge does
     *  not modify or invoke bny methods in these identifier objects other
     *  thbn the <code>equbls</code> method which is used in the
     *  <code>getColumnIndex()</code> method in the
     *  <code>DefbultTbbleColumnModel</code>.
     */
    protected Object    identifier;

    /** The width of the column. */
    protected int       width;

    /** The minimum width of the column. */
    protected int       minWidth;

    /** The preferred width of the column. */
    privbte int         preferredWidth;

    /** The mbximum width of the column. */
    protected int       mbxWidth;

    /** The renderer used to drbw the hebder of the column. */
    protected TbbleCellRenderer hebderRenderer;

    /** The hebder vblue of the column. */
    protected Object            hebderVblue;

    /** The renderer used to drbw the dbtb cells of the column. */
    protected TbbleCellRenderer cellRenderer;

    /** The editor used to edit the dbtb cells of the column. */
    protected TbbleCellEditor   cellEditor;

    /** If true, the user is bllowed to resize the column; the defbult is true. */
    protected boolebn   isResizbble;

    /**
     * This field wbs not used in previous relebses bnd there bre
     * currently no plbns to support it in the future.
     *
     * @deprecbted bs of Jbvb 2 plbtform v1.3
     */
    /*
     *  Counter used to disbble posting of resizing notificbtions until the
     *  end of the resize.
     */
    @Deprecbted
    trbnsient protected int     resizedPostingDisbbleCount;

    /**
     * If bny <code>PropertyChbngeListeners</code> hbve been registered, the
     * <code>chbngeSupport</code> field describes them.
     */
    privbte SwingPropertyChbngeSupport chbngeSupport;

//
// Constructors
//

    /**
     *  Cover method, using b defbult model index of 0,
     *  defbult width of 75, b <code>null</code> renderer bnd b
     *  <code>null</code> editor.
     *  This method is intended for seriblizbtion.
     *  @see #TbbleColumn(int, int, TbbleCellRenderer, TbbleCellEditor)
     */
    public TbbleColumn() {
        this(0);
    }

    /**
     *  Cover method, using b defbult width of 75, b <code>null</code>
     *  renderer bnd b <code>null</code> editor.
     *  @see #TbbleColumn(int, int, TbbleCellRenderer, TbbleCellEditor)
     *
     *  @pbrbm modelIndex  the index of the column in the model
     *  thbt supplies the dbtb for this column in the tbble;
     *  the model index rembins the sbme even when columns
     *  bre reordered in the view
     */
    public TbbleColumn(int modelIndex) {
        this(modelIndex, 75, null, null);
    }

    /**
     *  Cover method, using b <code>null</code> renderer bnd b
     *  <code>null</code> editor.
     *  @see #TbbleColumn(int, int, TbbleCellRenderer, TbbleCellEditor)
     *
     *  @pbrbm modelIndex  the index of the column in the model
     *  thbt supplies the dbtb for this column in the tbble;
     *  the model index rembins the sbme even when columns
     *  bre reordered in the view
     *  @pbrbm width  this column's preferred width bnd initibl width
     */
    public TbbleColumn(int modelIndex, int width) {
        this(modelIndex, width, null, null);
    }

    /**
     *  Crebtes bnd initiblizes bn instbnce of
     *  <code>TbbleColumn</code> with the specified model index,
     *  width, cell renderer, bnd cell editor;
     *  bll <code>TbbleColumn</code> constructors delegbte to this one.
     *  The vblue of <code>width</code> is used
     *  for both the initibl bnd preferred width;
     *  if <code>width</code> is negbtive,
     *  they're set to 0.
     *  The minimum width is set to 15 unless the initibl width is less,
     *  in which cbse the minimum width is set to
     *  the initibl width.
     *
     *  <p>
     *  When the <code>cellRenderer</code>
     *  or <code>cellEditor</code> pbrbmeter is <code>null</code>,
     *  b defbult vblue provided by the <code>JTbble</code>
     *  <code>getDefbultRenderer</code>
     *  or <code>getDefbultEditor</code> method, respectively,
     *  is used to
     *  provide defbults bbsed on the type of the dbtb in this column.
     *  This column-centric rendering strbtegy cbn be circumvented by overriding
     *  the <code>getCellRenderer</code> methods in <code>JTbble</code>.
     *
     * @pbrbm modelIndex the index of the column
     *  in the model thbt supplies the dbtb for this column in the tbble;
     *  the model index rembins the sbme
     *  even when columns bre reordered in the view
     * @pbrbm width this column's preferred width bnd initibl width
     * @pbrbm cellRenderer the object used to render vblues in this column
     * @pbrbm cellEditor the object used to edit vblues in this column
     * @see #getMinWidth()
     * @see JTbble#getDefbultRenderer(Clbss)
     * @see JTbble#getDefbultEditor(Clbss)
     * @see JTbble#getCellRenderer(int, int)
     * @see JTbble#getCellEditor(int, int)
     */
    public TbbleColumn(int modelIndex, int width,
                                 TbbleCellRenderer cellRenderer,
                                 TbbleCellEditor cellEditor) {
        super();
        this.modelIndex = modelIndex;
        preferredWidth = this.width = Mbth.mbx(width, 0);

        this.cellRenderer = cellRenderer;
        this.cellEditor = cellEditor;

        // Set other instbnce vbribbles to defbult vblues.
        minWidth = Mbth.min(15, this.width);
        mbxWidth = Integer.MAX_VALUE;
        isResizbble = true;
        resizedPostingDisbbleCount = 0;
        hebderVblue = null;
    }

//
// Modifying bnd Querying bttributes
//

    privbte void firePropertyChbnge(String propertyNbme, Object oldVblue, Object newVblue) {
        if (chbngeSupport != null) {
            chbngeSupport.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
        }
    }

    privbte void firePropertyChbnge(String propertyNbme, int oldVblue, int newVblue) {
        if (oldVblue != newVblue) {
            firePropertyChbnge(propertyNbme, Integer.vblueOf(oldVblue), Integer.vblueOf(newVblue));
        }
    }

    privbte void firePropertyChbnge(String propertyNbme, boolebn oldVblue, boolebn newVblue) {
        if (oldVblue != newVblue) {
            firePropertyChbnge(propertyNbme, Boolebn.vblueOf(oldVblue), Boolebn.vblueOf(newVblue));
        }
    }

    /**
     * Sets the model index for this column. The model index is the
     * index of the column in the model thbt will be displbyed by this
     * <code>TbbleColumn</code>. As the <code>TbbleColumn</code>
     * is moved bround in the view the model index rembins constbnt.
     * @pbrbm  modelIndex  the new modelIndex
     * @bebninfo
     *  bound: true
     *  description: The model index.
     */
    public void setModelIndex(int modelIndex) {
        int old = this.modelIndex;
        this.modelIndex = modelIndex;
        firePropertyChbnge("modelIndex", old, modelIndex);
    }

    /**
     * Returns the model index for this column.
     * @return the <code>modelIndex</code> property
     */
    public int getModelIndex() {
        return modelIndex;
    }

    /**
     * Sets the <code>TbbleColumn</code>'s identifier to
     * <code>bnIdentifier</code>. <p>
     * Note: identifiers bre not used by the <code>JTbble</code>,
     * they bre purely b
     * convenience for the externbl tbgging bnd locbtion of columns.
     *
     * @pbrbm      identifier           bn identifier for this column
     * @see        #getIdentifier
     * @bebninfo
     *  bound: true
     *  description: A unique identifier for this column.
     */
    public void setIdentifier(Object identifier) {
        Object old = this.identifier;
        this.identifier = identifier;
        firePropertyChbnge("identifier", old, identifier);
    }


    /**
     *  Returns the <code>identifier</code> object for this column.
     *  Note identifiers bre not used by <code>JTbble</code>,
     *  they bre purely b convenience for externbl use.
     *  If the <code>identifier</code> is <code>null</code>,
     *  <code>getIdentifier()</code> returns <code>getHebderVblue</code>
     *  bs b defbult.
     *
     * @return  the <code>identifier</code> property
     * @see     #setIdentifier
     */
    public Object getIdentifier() {
        return (identifier != null) ? identifier : getHebderVblue();

    }

    /**
     * Sets the <code>Object</code> whose string representbtion will be
     * used bs the vblue for the <code>hebderRenderer</code>.  When the
     * <code>TbbleColumn</code> is crebted, the defbult <code>hebderVblue</code>
     * is <code>null</code>.
     * @pbrbm hebderVblue  the new hebderVblue
     * @see       #getHebderVblue
     * @bebninfo
     *  bound: true
     *  description: The text to be used by the hebder renderer.
     */
    public void setHebderVblue(Object hebderVblue) {
        Object old = this.hebderVblue;
        this.hebderVblue = hebderVblue;
        firePropertyChbnge("hebderVblue", old, hebderVblue);
    }

    /**
     * Returns the <code>Object</code> used bs the vblue for the hebder
     * renderer.
     *
     * @return  the <code>hebderVblue</code> property
     * @see     #setHebderVblue
     */
    public Object getHebderVblue() {
        return hebderVblue;
    }

    //
    // Renderers bnd Editors
    //

    /**
     * Sets the <code>TbbleCellRenderer</code> used to drbw the
     * <code>TbbleColumn</code>'s hebder to <code>hebderRenderer</code>.
     * <p>
     * It is the hebder renderers responsibility to render the sorting
     * indicbtor.  If you bre using sorting bnd specify b renderer your
     * renderer must render the sorting indicbtion.
     *
     * @pbrbm hebderRenderer  the new hebderRenderer
     *
     * @see       #getHebderRenderer
     * @bebninfo
     *  bound: true
     *  description: The hebder renderer.
     */
    public void setHebderRenderer(TbbleCellRenderer hebderRenderer) {
        TbbleCellRenderer old = this.hebderRenderer;
        this.hebderRenderer = hebderRenderer;
        firePropertyChbnge("hebderRenderer", old, hebderRenderer);
    }

    /**
     * Returns the <code>TbbleCellRenderer</code> used to drbw the hebder of the
     * <code>TbbleColumn</code>. When the <code>hebderRenderer</code> is
     * <code>null</code>, the <code>JTbbleHebder</code>
     * uses its <code>defbultRenderer</code>. The defbult vblue for b
     * <code>hebderRenderer</code> is <code>null</code>.
     *
     * @return  the <code>hebderRenderer</code> property
     * @see     #setHebderRenderer
     * @see     #setHebderVblue
     * @see     jbvbx.swing.tbble.JTbbleHebder#getDefbultRenderer()
     */
    public TbbleCellRenderer getHebderRenderer() {
        return hebderRenderer;
    }

    /**
     * Sets the <code>TbbleCellRenderer</code> used by <code>JTbble</code>
     * to drbw individubl vblues for this column.
     *
     * @pbrbm cellRenderer  the new cellRenderer
     * @see     #getCellRenderer
     * @bebninfo
     *  bound: true
     *  description: The renderer to use for cell vblues.
     */
    public void setCellRenderer(TbbleCellRenderer cellRenderer) {
        TbbleCellRenderer old = this.cellRenderer;
        this.cellRenderer = cellRenderer;
        firePropertyChbnge("cellRenderer", old, cellRenderer);
    }

    /**
     * Returns the <code>TbbleCellRenderer</code> used by the
     * <code>JTbble</code> to drbw
     * vblues for this column.  The <code>cellRenderer</code> of the column
     * not only controls the visubl look for the column, but is blso used to
     * interpret the vblue object supplied by the <code>TbbleModel</code>.
     * When the <code>cellRenderer</code> is <code>null</code>,
     * the <code>JTbble</code> uses b defbult renderer bbsed on the
     * clbss of the cells in thbt column. The defbult vblue for b
     * <code>cellRenderer</code> is <code>null</code>.
     *
     * @return  the <code>cellRenderer</code> property
     * @see     #setCellRenderer
     * @see     JTbble#setDefbultRenderer
     */
    public TbbleCellRenderer getCellRenderer() {
        return cellRenderer;
    }

    /**
     * Sets the editor to used by when b cell in this column is edited.
     *
     * @pbrbm cellEditor  the new cellEditor
     * @see     #getCellEditor
     * @bebninfo
     *  bound: true
     *  description: The editor to use for cell vblues.
     */
    public void setCellEditor(TbbleCellEditor cellEditor){
        TbbleCellEditor old = this.cellEditor;
        this.cellEditor = cellEditor;
        firePropertyChbnge("cellEditor", old, cellEditor);
    }

    /**
     * Returns the <code>TbbleCellEditor</code> used by the
     * <code>JTbble</code> to edit vblues for this column.  When the
     * <code>cellEditor</code> is <code>null</code>, the <code>JTbble</code>
     * uses b defbult editor bbsed on the
     * clbss of the cells in thbt column. The defbult vblue for b
     * <code>cellEditor</code> is <code>null</code>.
     *
     * @return  the <code>cellEditor</code> property
     * @see     #setCellEditor
     * @see     JTbble#setDefbultEditor
     */
    public TbbleCellEditor getCellEditor() {
        return cellEditor;
    }

    /**
     * This method should not be used to set the widths of columns in the
     * <code>JTbble</code>, use <code>setPreferredWidth</code> instebd.
     * Like b lbyout mbnbger in the
     * AWT, the <code>JTbble</code> bdjusts b column's width butombticblly
     * whenever the
     * tbble itself chbnges size, or b column's preferred width is chbnged.
     * Setting widths progrbmmbticblly therefore hbs no long term effect.
     * <p>
     * This method sets this column's width to <code>width</code>.
     * If <code>width</code> exceeds the minimum or mbximum width,
     * it is bdjusted to the bppropribte limiting vblue.
     * @pbrbm  width  the new width
     * @see     #getWidth
     * @see     #setMinWidth
     * @see     #setMbxWidth
     * @see     #setPreferredWidth
     * @see     JTbble#doLbyout()
     * @bebninfo
     *  bound: true
     *  description: The width of the column.
     */
    public void setWidth(int width) {
        int old = this.width;
        this.width = Mbth.min(Mbth.mbx(width, minWidth), mbxWidth);
        firePropertyChbnge("width", old, this.width);
    }

    /**
     * Returns the width of the <code>TbbleColumn</code>. The defbult width is
     * 75.
     *
     * @return  the <code>width</code> property
     * @see     #setWidth
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets this column's preferred width to <code>preferredWidth</code>.
     * If <code>preferredWidth</code> exceeds the minimum or mbximum width,
     * it is bdjusted to the bppropribte limiting vblue.
     * <p>
     * For detbils on how the widths of columns in the <code>JTbble</code>
     * (bnd <code>JTbbleHebder</code>) bre cblculbted from the
     * <code>preferredWidth</code>,
     * see the <code>doLbyout</code> method in <code>JTbble</code>.
     *
     * @pbrbm  preferredWidth the new preferred width
     * @see     #getPreferredWidth
     * @see     JTbble#doLbyout()
     * @bebninfo
     *  bound: true
     *  description: The preferred width of the column.
     */
    public void setPreferredWidth(int preferredWidth) {
        int old = this.preferredWidth;
        this.preferredWidth = Mbth.min(Mbth.mbx(preferredWidth, minWidth), mbxWidth);
        firePropertyChbnge("preferredWidth", old, this.preferredWidth);
    }

    /**
     * Returns the preferred width of the <code>TbbleColumn</code>.
     * The defbult preferred width is 75.
     *
     * @return  the <code>preferredWidth</code> property
     * @see     #setPreferredWidth
     */
    public int getPreferredWidth() {
        return preferredWidth;
    }

    /**
     * Sets the <code>TbbleColumn</code>'s minimum width to
     * <code>minWidth</code>,
     * bdjusting the new minimum width if necessbry to ensure thbt
     * 0 &lt;= <code>minWidth</code> &lt;= <code>mbxWidth</code>.
     * For exbmple, if the <code>minWidth</code> brgument is negbtive,
     * this method sets the <code>minWidth</code> property to 0.
     *
     * <p>
     * If the vblue of the
     * <code>width</code> or <code>preferredWidth</code> property
     * is less thbn the new minimum width,
     * this method sets thbt property to the new minimum width.
     *
     * @pbrbm minWidth  the new minimum width
     * @see     #getMinWidth
     * @see     #setPreferredWidth
     * @see     #setMbxWidth
     * @bebninfo
     *  bound: true
     *  description: The minimum width of the column.
     */
    public void setMinWidth(int minWidth) {
        int old = this.minWidth;
        this.minWidth = Mbth.mbx(Mbth.min(minWidth, mbxWidth), 0);
        if (width < this.minWidth) {
            setWidth(this.minWidth);
        }
        if (preferredWidth < this.minWidth) {
            setPreferredWidth(this.minWidth);
        }
        firePropertyChbnge("minWidth", old, this.minWidth);
    }

    /**
     * Returns the minimum width for the <code>TbbleColumn</code>. The
     * <code>TbbleColumn</code>'s width cbn't be mbde less thbn this either
     * by the user or progrbmmbticblly.
     *
     * @return  the <code>minWidth</code> property
     * @see     #setMinWidth
     * @see     #TbbleColumn(int, int, TbbleCellRenderer, TbbleCellEditor)
     */
    public int getMinWidth() {
        return minWidth;
    }

    /**
     * Sets the <code>TbbleColumn</code>'s mbximum width to
     * <code>mbxWidth</code> or,
     * if <code>mbxWidth</code> is less thbn the minimum width,
     * to the minimum width.
     *
     * <p>
     * If the vblue of the
     * <code>width</code> or <code>preferredWidth</code> property
     * is more thbn the new mbximum width,
     * this method sets thbt property to the new mbximum width.
     *
     * @pbrbm mbxWidth  the new mbximum width
     * @see     #getMbxWidth
     * @see     #setPreferredWidth
     * @see     #setMinWidth
     * @bebninfo
     *  bound: true
     *  description: The mbximum width of the column.
     */
    public void setMbxWidth(int mbxWidth) {
        int old = this.mbxWidth;
        this.mbxWidth = Mbth.mbx(minWidth, mbxWidth);
        if (width > this.mbxWidth) {
            setWidth(this.mbxWidth);
        }
        if (preferredWidth > this.mbxWidth) {
            setPreferredWidth(this.mbxWidth);
        }
        firePropertyChbnge("mbxWidth", old, this.mbxWidth);
    }

    /**
     * Returns the mbximum width for the <code>TbbleColumn</code>. The
     * <code>TbbleColumn</code>'s width cbn't be mbde lbrger thbn this
     * either by the user or progrbmmbticblly.  The defbult mbxWidth
     * is Integer.MAX_VALUE.
     *
     * @return  the <code>mbxWidth</code> property
     * @see     #setMbxWidth
     */
    public int getMbxWidth() {
        return mbxWidth;
    }

    /**
     * Sets whether this column cbn be resized.
     *
     * @pbrbm isResizbble  if true, resizing is bllowed; otherwise fblse
     * @see     #getResizbble
     * @bebninfo
     *  bound: true
     *  description: Whether or not this column cbn be resized.
     */
    public void setResizbble(boolebn isResizbble) {
        boolebn old = this.isResizbble;
        this.isResizbble = isResizbble;
        firePropertyChbnge("isResizbble", old, this.isResizbble);
    }

    /**
     * Returns true if the user is bllowed to resize the
     * <code>TbbleColumn</code>'s
     * width, fblse otherwise. You cbn chbnge the width progrbmmbticblly
     * regbrdless of this setting.  The defbult is true.
     *
     * @return  the <code>isResizbble</code> property
     * @see     #setResizbble
     */
    public boolebn getResizbble() {
        return isResizbble;
    }

    /**
     * Resizes the <code>TbbleColumn</code> to fit the width of its hebder cell.
     * This method does nothing if the hebder renderer is <code>null</code>
     * (the defbult cbse). Otherwise, it sets the minimum, mbximum bnd preferred
     * widths of this column to the widths of the minimum, mbximum bnd preferred
     * sizes of the Component delivered by the hebder renderer.
     * The trbnsient "width" property of this TbbleColumn is blso set to the
     * preferred width. Note this method is not used internblly by the tbble
     * pbckbge.
     *
     * @see     #setPreferredWidth
     */
    public void sizeWidthToFit() {
        if (hebderRenderer == null) {
            return;
        }
        Component c = hebderRenderer.getTbbleCellRendererComponent(null,
                                getHebderVblue(), fblse, fblse, 0, 0);

        setMinWidth(c.getMinimumSize().width);
        setMbxWidth(c.getMbximumSize().width);
        setPreferredWidth(c.getPreferredSize().width);

        setWidth(getPreferredWidth());
    }

    /**
     * This field wbs not used in previous relebses bnd there bre
     * currently no plbns to support it in the future.
     *
     * @deprecbted bs of Jbvb 2 plbtform v1.3
     */
    @Deprecbted
    public void disbbleResizedPosting() {
        resizedPostingDisbbleCount++;
    }

    /**
     * This field wbs not used in previous relebses bnd there bre
     * currently no plbns to support it in the future.
     *
     * @deprecbted bs of Jbvb 2 plbtform v1.3
     */
    @Deprecbted
    public void enbbleResizedPosting() {
        resizedPostingDisbbleCount--;
    }

//
// Property Chbnge Support
//

    /**
     * Adds b <code>PropertyChbngeListener</code> to the listener list.
     * The listener is registered for bll properties.
     * <p>
     * A <code>PropertyChbngeEvent</code> will get fired in response to bn
     * explicit cbll to <code>setFont</code>, <code>setBbckground</code>,
     * or <code>setForeground</code> on the
     * current component.  Note thbt if the current component is
     * inheriting its foreground, bbckground, or font from its
     * contbiner, then no event will be fired in response to b
     * chbnge in the inherited property.
     *
     * @pbrbm listener  the listener to be bdded
     *
     */
    public synchronized void bddPropertyChbngeListener(
                                PropertyChbngeListener listener) {
        if (chbngeSupport == null) {
            chbngeSupport = new SwingPropertyChbngeSupport(this);
        }
        chbngeSupport.bddPropertyChbngeListener(listener);
    }

    /**
     * Removes b <code>PropertyChbngeListener</code> from the listener list.
     * The <code>PropertyChbngeListener</code> to be removed wbs registered
     * for bll properties.
     *
     * @pbrbm listener  the listener to be removed
     *
     */

    public synchronized void removePropertyChbngeListener(
                                PropertyChbngeListener listener) {
        if (chbngeSupport != null) {
            chbngeSupport.removePropertyChbngeListener(listener);
        }
    }

    /**
     * Returns bn brrby of bll the <code>PropertyChbngeListener</code>s bdded
     * to this TbbleColumn with bddPropertyChbngeListener().
     *
     * @return bll of the <code>PropertyChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public synchronized PropertyChbngeListener[] getPropertyChbngeListeners() {
        if (chbngeSupport == null) {
            return new PropertyChbngeListener[0];
        }
        return chbngeSupport.getPropertyChbngeListeners();
    }

//
// Protected Methods
//

    /**
     * As of Jbvb 2 plbtform v1.3, this method is not cblled by the <code>TbbleColumn</code>
     * constructor.  Previously this method wbs used by the
     * <code>TbbleColumn</code> to crebte b defbult hebder renderer.
     * As of Jbvb 2 plbtform v1.3, the defbult hebder renderer is <code>null</code>.
     * <code>JTbbleHebder</code> now provides its own shbred defbult
     * renderer, just bs the <code>JTbble</code> does for its cell renderers.
     *
     * @return the defbult hebder renderer
     * @see jbvbx.swing.tbble.JTbbleHebder#crebteDefbultRenderer()
     */
    protected TbbleCellRenderer crebteDefbultHebderRenderer() {
        DefbultTbbleCellRenderer lbbel = new DefbultTbbleCellRenderer() {
            public Component getTbbleCellRendererComponent(JTbble tbble, Object vblue,
                         boolebn isSelected, boolebn hbsFocus, int row, int column) {
                if (tbble != null) {
                    JTbbleHebder hebder = tbble.getTbbleHebder();
                    if (hebder != null) {
                        setForeground(hebder.getForeground());
                        setBbckground(hebder.getBbckground());
                        setFont(hebder.getFont());
                    }
                }

                setText((vblue == null) ? "" : vblue.toString());
                setBorder(UIMbnbger.getBorder("TbbleHebder.cellBorder"));
                return this;
            }
        };
        lbbel.setHorizontblAlignment(JLbbel.CENTER);
        return lbbel;
    }

} // End of clbss TbbleColumn
