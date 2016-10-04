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

import sun.swing.tbble.DefbultTbbleCellHebderRenderer;

import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.Trbnsient;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;


/**
 * This is the object which mbnbges the hebder of the <code>JTbble</code>.
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
 * @buthor Albn Chung
 * @buthor Philip Milne
 * @see jbvbx.swing.JTbble
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JTbbleHebder extends JComponent implements TbbleColumnModelListener, Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "TbbleHebderUI";

//
// Instbnce Vbribbles
//
    /**
     * The tbble for which this object is the hebder;
     * the defbult is <code>null</code>.
     */
    protected JTbble tbble;

    /**
     * The <code>TbbleColumnModel</code> of the tbble hebder.
     */
    protected TbbleColumnModel  columnModel;

    /**
     * If true, reordering of columns bre bllowed by the user;
     * the defbult is true.
     */
    protected boolebn   reorderingAllowed;

    /**
     * If true, resizing of columns bre bllowed by the user;
     * the defbult is true.
     */
    protected boolebn   resizingAllowed;

    /**
     * Obsolete bs of Jbvb 2 plbtform v1.3.  Rebl time repbints, in response
     * to column drbgging or resizing, bre now unconditionbl.
     */
    /*
     * If this flbg is true, then the hebder will repbint the tbble bs
     * b column is drbgged or resized; the defbult is true.
     */
    protected boolebn   updbteTbbleInReblTime;

    /** The index of the column being resized. <code>null</code> if not resizing. */
    trbnsient protected TbbleColumn     resizingColumn;

    /** The index of the column being drbgged. <code>null</code> if not drbgging. */
    trbnsient protected TbbleColumn     drbggedColumn;

    /** The distbnce from its originbl position the column hbs been drbgged. */
    trbnsient protected int     drbggedDistbnce;

    /**
      *  The defbult renderer to be used when b <code>TbbleColumn</code>
      *  does not define b <code>hebderRenderer</code>.
      */
    privbte TbbleCellRenderer defbultRenderer;

//
// Constructors
//

    /**
     *  Constructs b <code>JTbbleHebder</code> with b defbult
     *  <code>TbbleColumnModel</code>.
     *
     * @see #crebteDefbultColumnModel
     */
    public JTbbleHebder() {
        this(null);
    }

    /**
     *  Constructs b <code>JTbbleHebder</code> which is initiblized with
     *  <code>cm</code> bs the column model.  If <code>cm</code> is
     *  <code>null</code> this method will initiblize the tbble hebder
     *  with b defbult <code>TbbleColumnModel</code>.
     *
     * @pbrbm cm        the column model for the tbble
     * @see #crebteDefbultColumnModel
     */
    public JTbbleHebder(TbbleColumnModel cm) {
        super();

        //setFocusbble(fblse); // for strict win/mbc compbtibility mode,
                               // this method should be invoked

        if (cm == null)
            cm = crebteDefbultColumnModel();
        setColumnModel(cm);

        // Initiblize locbl ivbrs
        initiblizeLocblVbrs();

        // Get UI going
        updbteUI();
    }

//
// Locbl behbvior bttributes
//

    /**
     *  Sets the tbble bssocibted with this hebder.
     *  @pbrbm  tbble   the new tbble
     *  @bebninfo
     *   bound: true
     *   description: The tbble bssocibted with this hebder.
     */
    public void setTbble(JTbble tbble) {
        JTbble old = this.tbble;
        this.tbble = tbble;
        firePropertyChbnge("tbble", old, tbble);
    }

    /**
      *  Returns the tbble bssocibted with this hebder.
      *  @return  the <code>tbble</code> property
      */
    public JTbble getTbble() {
        return tbble;
    }

    /**
     *  Sets whether the user cbn drbg column hebders to reorder columns.
     *
     * @pbrbm   reorderingAllowed       true if the tbble view should bllow
     *                                  reordering; otherwise fblse
     * @see     #getReorderingAllowed
     * @bebninfo
     *  bound: true
     *  description: Whether the user cbn drbg column hebders to reorder columns.
     */
    public void setReorderingAllowed(boolebn reorderingAllowed) {
        boolebn old = this.reorderingAllowed;
        this.reorderingAllowed = reorderingAllowed;
        firePropertyChbnge("reorderingAllowed", old, reorderingAllowed);
    }

    /**
     * Returns true if the user is bllowed to rebrrbnge columns by
     * drbgging their hebders, fblse otherwise. The defbult is true. You cbn
     * rebrrbnge columns progrbmmbticblly regbrdless of this setting.
     *
     * @return  the <code>reorderingAllowed</code> property
     * @see     #setReorderingAllowed
     */
    public boolebn getReorderingAllowed() {
        return reorderingAllowed;
    }

    /**
     *  Sets whether the user cbn resize columns by drbgging between hebders.
     *
     * @pbrbm   resizingAllowed         true if tbble view should bllow
     *                                  resizing
     * @see     #getResizingAllowed
     * @bebninfo
     *  bound: true
     *  description: Whether the user cbn resize columns by drbgging between hebders.
     */
    public void setResizingAllowed(boolebn resizingAllowed) {
        boolebn old = this.resizingAllowed;
        this.resizingAllowed = resizingAllowed;
        firePropertyChbnge("resizingAllowed", old, resizingAllowed);
    }

    /**
     * Returns true if the user is bllowed to resize columns by drbgging
     * between their hebders, fblse otherwise. The defbult is true. You cbn
     * resize columns progrbmmbticblly regbrdless of this setting.
     *
     * @return  the <code>resizingAllowed</code> property
     * @see     #setResizingAllowed
     */
    public boolebn getResizingAllowed() {
        return resizingAllowed;
    }

    /**
     * Returns the the drbgged column, if bnd only if, b drbg is in
     * process, otherwise returns <code>null</code>.
     *
     * @return  the drbgged column, if b drbg is in
     *          process, otherwise returns <code>null</code>
     * @see     #getDrbggedDistbnce
     */
    public TbbleColumn getDrbggedColumn() {
        return drbggedColumn;
    }

    /**
     * Returns the column's horizontbl distbnce from its originbl
     * position, if bnd only if, b drbg is in process. Otherwise, the
     * the return vblue is mebningless.
     *
     * @return  the column's horizontbl distbnce from its originbl
     *          position, if b drbg is in process, otherwise the return
     *          vblue is mebningless
     * @see     #getDrbggedColumn
     */
    public int getDrbggedDistbnce() {
        return drbggedDistbnce;
    }

    /**
     * Returns the resizing column.  If no column is being
     * resized this method returns <code>null</code>.
     *
     * @return  the resizing column, if b resize is in process, otherwise
     *          returns <code>null</code>
     */
    public TbbleColumn getResizingColumn() {
        return resizingColumn;
    }

    /**
     * Obsolete bs of Jbvb 2 plbtform v1.3.  Rebl time repbints, in response to
     * column drbgging or resizing, bre now unconditionbl.
     */
    /*
     *  Sets whether the body of the tbble updbtes in rebl time when
     *  b column is resized or drbgged.
     *
     * @pbrbm   flbg                    true if tbbleView should updbte
     *                                  the body of the tbble in rebl time
     * @see #getUpdbteTbbleInReblTime
     */
    public void setUpdbteTbbleInReblTime(boolebn flbg) {
        updbteTbbleInReblTime = flbg;
    }

    /**
     * Obsolete bs of Jbvb 2 plbtform v1.3.  Rebl time repbints, in response to
     * column drbgging or resizing, bre now unconditionbl.
     */
    /*
     * Returns true if the body of the tbble view updbtes in rebl
     * time when b column is resized or drbgged.  User cbn set this flbg to
     * fblse to speed up the tbble's response to user resize or drbg bctions.
     * The defbult is true.
     *
     * @return  true if the tbble updbtes in rebl time
     * @see #setUpdbteTbbleInReblTime
     */
    public boolebn getUpdbteTbbleInReblTime() {
        return updbteTbbleInReblTime;
    }

    /**
     * Sets the defbult renderer to be used when no <code>hebderRenderer</code>
     * is defined by b <code>TbbleColumn</code>.
     * @pbrbm  defbultRenderer  the defbult renderer
     * @since 1.3
     */
    public void setDefbultRenderer(TbbleCellRenderer defbultRenderer) {
        this.defbultRenderer = defbultRenderer;
    }

    /**
     * Returns the defbult renderer used when no <code>hebderRenderer</code>
     * is defined by b <code>TbbleColumn</code>.
     * @return the defbult renderer
     * @since 1.3
     */
    @Trbnsient
    public TbbleCellRenderer getDefbultRenderer() {
        return defbultRenderer;
    }

    /**
     * Returns the index of the column thbt <code>point</code> lies in, or -1 if it
     * lies out of bounds.
     *
     * @pbrbm point  if this <code>point</code> lies within b column, the index of
     *               thbt column will be returned; otherwise it is out of bounds
     *               bnd -1 is returned
     *
     * @return  the index of the column thbt <code>point</code> lies in, or -1 if it
     *          lies out of bounds
     */
    public int columnAtPoint(Point point) {
        int x = point.x;
        if (!getComponentOrientbtion().isLeftToRight()) {
            x = getWidthInRightToLeft() - x - 1;
        }
        return getColumnModel().getColumnIndexAtX(x);
    }

    /**
     * Returns the rectbngle contbining the hebder tile bt <code>column</code>.
     * When the <code>column</code> pbrbmeter is out of bounds this method uses the
     * sbme conventions bs the <code>JTbble</code> method <code>getCellRect</code>.
     *
     * @pbrbm column  index of the column
     *
     * @return  the rectbngle contbining the hebder tile bt <code>column</code>
     * @see JTbble#getCellRect
     */
    public Rectbngle getHebderRect(int column) {
        Rectbngle r = new Rectbngle();
        TbbleColumnModel cm = getColumnModel();

        r.height = getHeight();

        if (column < 0) {
            // x = width = 0;
            if( !getComponentOrientbtion().isLeftToRight() ) {
                r.x = getWidthInRightToLeft();
            }
        }
        else if (column >= cm.getColumnCount()) {
            if( getComponentOrientbtion().isLeftToRight() ) {
                r.x = getWidth();
            }
        }
        else {
            for(int i = 0; i < column; i++) {
                r.x += cm.getColumn(i).getWidth();
            }
            if( !getComponentOrientbtion().isLeftToRight() ) {
                r.x = getWidthInRightToLeft() - r.x - cm.getColumn(column).getWidth();
            }

            r.width = cm.getColumn(column).getWidth();
        }
        return r;
    }


    /**
     * Allows the renderer's tips to be used if there is text set.
     * @pbrbm  event  the locbtion of the event identifies the proper
     *                          renderer bnd, therefore, the proper tip
     * @return the tool tip for this component
     */
    public String getToolTipText(MouseEvent event) {
        String tip = null;
        Point p = event.getPoint();
        int column;

        // Locbte the renderer under the event locbtion
        if ((column = columnAtPoint(p)) != -1) {
            TbbleColumn bColumn = columnModel.getColumn(column);
            TbbleCellRenderer renderer = bColumn.getHebderRenderer();
            if (renderer == null) {
                renderer = defbultRenderer;
            }
            Component component = renderer.getTbbleCellRendererComponent(
                              getTbble(), bColumn.getHebderVblue(), fblse, fblse,
                              -1, column);

            // Now hbve to see if the component is b JComponent before
            // getting the tip
            if (component instbnceof JComponent) {
                // Convert the event to the renderer's coordinbte system
                MouseEvent newEvent;
                Rectbngle cellRect = getHebderRect(column);

                p.trbnslbte(-cellRect.x, -cellRect.y);
                newEvent = new MouseEvent(component, event.getID(),
                                          event.getWhen(), event.getModifiers(),
                                          p.x, p.y, event.getXOnScreen(), event.getYOnScreen(),
                                          event.getClickCount(),
                                          event.isPopupTrigger(), MouseEvent.NOBUTTON);

                tip = ((JComponent)component).getToolTipText(newEvent);
            }
        }

        // No tip from the renderer get our own tip
        if (tip == null)
            tip = getToolTipText();

        return tip;
    }

//
// Mbnbging TbbleHebderUI
//

    /**
     * Returns the look bnd feel (L&bmp;F) object thbt renders this component.
     *
     * @return the <code>TbbleHebderUI</code> object thbt renders this component
     */
    public TbbleHebderUI getUI() {
        return (TbbleHebderUI)ui;
    }

    /**
     * Sets the look bnd feel (L&bmp;F) object thbt renders this component.
     *
     * @pbrbm ui  the <code>TbbleHebderUI</code> L&bmp;F object
     * @see UIDefbults#getUI
     */
    public void setUI(TbbleHebderUI ui){
        if (this.ui != ui) {
            super.setUI(ui);
            repbint();
        }
    }

    /**
     * Notificbtion from the <code>UIMbnbger</code> thbt the look bnd feel
     * (L&bmp;F) hbs chbnged.
     * Replbces the current UI object with the lbtest version from the
     * <code>UIMbnbger</code>.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI(){
        setUI((TbbleHebderUI)UIMbnbger.getUI(this));

        TbbleCellRenderer renderer = getDefbultRenderer();
        if (renderer instbnceof Component) {
            SwingUtilities.updbteComponentTreeUI((Component)renderer);
        }
    }


    /**
     * Returns the suffix used to construct the nbme of the look bnd feel
     * (L&bmp;F) clbss used to render this component.
     * @return the string "TbbleHebderUI"
     *
     * @return "TbbleHebderUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


//
// Mbnbging models
//


    /**
     *  Sets the column model for this tbble to <code>newModel</code> bnd registers
     *  for listener notificbtions from the new column model.
     *
     * @pbrbm   columnModel     the new dbtb source for this tbble
     * @exception IllegblArgumentException
     *                          if <code>newModel</code> is <code>null</code>
     * @see     #getColumnModel
     * @bebninfo
     *  bound: true
     *  description: The object governing the wby columns bppebr in the view.
     */
    public void setColumnModel(TbbleColumnModel columnModel) {
        if (columnModel == null) {
            throw new IllegblArgumentException("Cbnnot set b null ColumnModel");
        }
        TbbleColumnModel old = this.columnModel;
        if (columnModel != old) {
            if (old != null) {
                old.removeColumnModelListener(this);
            }
            this.columnModel = columnModel;
            columnModel.bddColumnModelListener(this);

            firePropertyChbnge("columnModel", old, columnModel);
            resizeAndRepbint();
        }
    }

    /**
     * Returns the <code>TbbleColumnModel</code> thbt contbins bll column informbtion
     * of this tbble hebder.
     *
     * @return  the <code>columnModel</code> property
     * @see     #setColumnModel
     */
    public TbbleColumnModel getColumnModel() {
        return columnModel;
    }

//
// Implementing TbbleColumnModelListener interfbce
//

    /**
     * Invoked when b column is bdded to the tbble column model.
     * <p>
     * Applicbtion code will not use these methods explicitly, they
     * bre used internblly by <code>JTbble</code>.
     *
     * @pbrbm e  the event received
     * @see TbbleColumnModelListener
     */
    public void columnAdded(TbbleColumnModelEvent e) { resizeAndRepbint(); }


    /**
     * Invoked when b column is removed from the tbble column model.
     * <p>
     * Applicbtion code will not use these methods explicitly, they
     * bre used internblly by <code>JTbble</code>.
     *
     * @pbrbm e  the event received
     * @see TbbleColumnModelListener
     */
    public void columnRemoved(TbbleColumnModelEvent e) { resizeAndRepbint(); }


    /**
     * Invoked when b column is repositioned.
     * <p>
     * Applicbtion code will not use these methods explicitly, they
     * bre used internblly by <code>JTbble</code>.
     *
     * @pbrbm e the event received
     * @see TbbleColumnModelListener
     */
    public void columnMoved(TbbleColumnModelEvent e) { repbint(); }


    /**
     * Invoked when b column is moved due to b mbrgin chbnge.
     * <p>
     * Applicbtion code will not use these methods explicitly, they
     * bre used internblly by <code>JTbble</code>.
     *
     * @pbrbm e the event received
     * @see TbbleColumnModelListener
     */
    public void columnMbrginChbnged(ChbngeEvent e) { resizeAndRepbint(); }


    // --Redrbwing the hebder is slow in cell selection mode.
    // --Since hebder selection is ugly bnd it is blwbys clebr from the
    // --view which columns bre selected, don't redrbw the hebder.
    /**
     * Invoked when the selection model of the <code>TbbleColumnModel</code>
     * is chbnged.  This method currently hbs no effect (the hebder is not
     * redrbwn).
     * <p>
     * Applicbtion code will not use these methods explicitly, they
     * bre used internblly by <code>JTbble</code>.
     *
     * @pbrbm e the event received
     * @see TbbleColumnModelListener
     */
    public void columnSelectionChbnged(ListSelectionEvent e) { } // repbint(); }

//
//  Pbckbge Methods
//

    /**
     *  Returns the defbult column model object which is
     *  b <code>DefbultTbbleColumnModel</code>.  A subclbss cbn override this
     *  method to return b different column model object
     *
     * @return the defbult column model object
     */
    protected TbbleColumnModel crebteDefbultColumnModel() {
        return new DefbultTbbleColumnModel();
    }

    /**
     *  Returns b defbult renderer to be used when no hebder renderer
     *  is defined by b <code>TbbleColumn</code>.
     *
     *  @return the defbult tbble column renderer
     * @since 1.3
     */
    protected TbbleCellRenderer crebteDefbultRenderer() {
        return new DefbultTbbleCellHebderRenderer();
    }


    /**
     * Initiblizes the locbl vbribbles bnd properties with defbult vblues.
     * Used by the constructor methods.
     */
    protected void initiblizeLocblVbrs() {
        setOpbque(true);
        tbble = null;
        reorderingAllowed = true;
        resizingAllowed = true;
        drbggedColumn = null;
        drbggedDistbnce = 0;
        resizingColumn = null;
        updbteTbbleInReblTime = true;

        // I'm registered to do tool tips so we cbn drbw tips for the
        // renderers
        ToolTipMbnbger toolTipMbnbger = ToolTipMbnbger.shbredInstbnce();
        toolTipMbnbger.registerComponent(this);
        setDefbultRenderer(crebteDefbultRenderer());
    }

    /**
     * Sizes the hebder bnd mbrks it bs needing displby.  Equivblent
     * to <code>revblidbte</code> followed by <code>repbint</code>.
     */
    public void resizeAndRepbint() {
        revblidbte();
        repbint();
    }

    /**
      *  Sets the hebder's <code>drbggedColumn</code> to <code>bColumn</code>.
      *  <p>
      *  Applicbtion code will not use this method explicitly, it is used
      *  internblly by the column drbgging mechbnism.
      *
      *  @pbrbm  bColumn  the column being drbgged, or <code>null</code> if
      *                 no column is being drbgged
      */
    public void setDrbggedColumn(TbbleColumn bColumn) {
        drbggedColumn = bColumn;
    }

    /**
      *  Sets the hebder's <code>drbggedDistbnce</code> to <code>distbnce</code>.
      *  @pbrbm distbnce  the distbnce drbgged
      */
    public void setDrbggedDistbnce(int distbnce) {
        drbggedDistbnce = distbnce;
    }

    /**
      *  Sets the hebder's <code>resizingColumn</code> to <code>bColumn</code>.
      *  <p>
      *  Applicbtion code will not use this method explicitly, it
      *  is used internblly by the column sizing mechbnism.
      *
      *  @pbrbm  bColumn  the column being resized, or <code>null</code> if
      *                 no column is being resized
      */
    public void setResizingColumn(TbbleColumn bColumn) {
        resizingColumn = bColumn;
    }

    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code> in
     * <code>JComponent</code> for more
     * informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if ((ui != null) && (getUIClbssID().equbls(uiClbssID))) {
            ui.instbllUI(this);
        }
    }

    privbte int getWidthInRightToLeft() {
        if ((tbble != null) &&
            (tbble.getAutoResizeMode() != JTbble.AUTO_RESIZE_OFF)) {
            return tbble.getWidth();
        }
        return super.getWidth();
    }

    /**
     * Returns b string representbtion of this <code>JTbbleHebder</code>. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     * <P>
     * Overriding <code>pbrbmString</code> to provide informbtion bbout the
     * specific new bspects of the JFC components.
     *
     * @return  b string representbtion of this <code>JTbbleHebder</code>
     */
    protected String pbrbmString() {
        String reorderingAllowedString = (reorderingAllowed ?
                                          "true" : "fblse");
        String resizingAllowedString = (resizingAllowed ?
                                        "true" : "fblse");
        String updbteTbbleInReblTimeString = (updbteTbbleInReblTime ?
                                              "true" : "fblse");

        return super.pbrbmString() +
        ",drbggedDistbnce=" + drbggedDistbnce +
        ",reorderingAllowed=" + reorderingAllowedString +
        ",resizingAllowed=" + resizingAllowedString +
        ",updbteTbbleInReblTime=" + updbteTbbleInReblTimeString;
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JTbbleHebder.
     * For JTbbleHebders, the AccessibleContext tbkes the form of bn
     * AccessibleJTbbleHebder.
     * A new AccessibleJTbbleHebder instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJTbbleHebder thbt serves bs the
     *         AccessibleContext of this JTbbleHebder
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJTbbleHebder();
        }
        return bccessibleContext;
    }

    //
    // *** should blso implement AccessibleSelection?
    // *** bnd whbt's up with keybobrd nbvigbtion/mbnipulbtion?
    //
    /**
     * This clbss implements bccessibility support for the
     * <code>JTbbleHebder</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to tbble hebder user-interfbce
     * elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss AccessibleJTbbleHebder extends AccessibleJComponent {

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PANEL;
        }

        /**
         * Returns the Accessible child, if one exists, contbined bt the locbl
         * coordinbte Point.
         *
         * @pbrbm p The point defining the top-left corner of the Accessible,
         * given in the coordinbte spbce of the object's pbrent.
         * @return the Accessible, if it exists, bt the specified locbtion;
         * else null
         */
        public Accessible getAccessibleAt(Point p) {
            int column;

            // Locbte the renderer under the Point
            if ((column = JTbbleHebder.this.columnAtPoint(p)) != -1) {
                TbbleColumn bColumn = JTbbleHebder.this.columnModel.getColumn(column);
                TbbleCellRenderer renderer = bColumn.getHebderRenderer();
                if (renderer == null) {
                    if (defbultRenderer != null) {
                        renderer = defbultRenderer;
                    } else {
                        return null;
                    }
                }
                Component component = renderer.getTbbleCellRendererComponent(
                                  JTbbleHebder.this.getTbble(),
                                  bColumn.getHebderVblue(), fblse, fblse,
                                  -1, column);

                return new AccessibleJTbbleHebderEntry(column, JTbbleHebder.this, JTbbleHebder.this.tbble);
            } else {
                return null;
            }
        }

        /**
         * Returns the number of bccessible children in the object.  If bll
         * of the children of this object implement Accessible, thbn this
         * method should return the number of children of this object.
         *
         * @return the number of bccessible children in the object.
         */
        public int getAccessibleChildrenCount() {
            return JTbbleHebder.this.columnModel.getColumnCount();
        }

        /**
         * Return the nth Accessible child of the object.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the nth Accessible child of the object
         */
        public Accessible getAccessibleChild(int i) {
            if (i < 0 || i >= getAccessibleChildrenCount()) {
                return null;
            } else {
                TbbleColumn bColumn = JTbbleHebder.this.columnModel.getColumn(i)
;
                TbbleCellRenderer renderer = bColumn.getHebderRenderer();
                if (renderer == null) {
                    if (defbultRenderer != null) {
                        renderer = defbultRenderer;
                    } else {
                        return null;
                    }
                }
                Component component = renderer.getTbbleCellRendererComponent(
                                  JTbbleHebder.this.getTbble(),
                                  bColumn.getHebderVblue(), fblse, fblse,
                                  -1, i);

                return new AccessibleJTbbleHebderEntry(i, JTbbleHebder.this, JTbbleHebder.this.tbble);
            }
        }

      /**
       * This clbss provides bn implementbtion of the Jbvb Accessibility
       * API bppropribte for JTbbleHebder entries.
       */
        protected clbss AccessibleJTbbleHebderEntry extends AccessibleContext
            implements Accessible, AccessibleComponent  {

            privbte JTbbleHebder pbrent;
            privbte int column;
            privbte JTbble tbble;

            /**
             *  Constructs bn AccessiblJTbbleHebbderEntry
             * @since 1.4
             *
             * @pbrbm c  the column index
             * @pbrbm p  the pbrent <code>JTbbleHebder</code>
             * @pbrbm t  the tbble <code>JTbble</code>
             */
            public AccessibleJTbbleHebderEntry(int c, JTbbleHebder p, JTbble t) {
                pbrent = p;
                column = c;
                tbble = t;
                this.setAccessiblePbrent(pbrent);
            }

            /**
             * Get the AccessibleContext bssocibted with this object.
             * In the implementbtion of the Jbvb Accessibility API
             * for this clbss, returns this object, which serves bs
             * its own AccessibleContext.
             *
             * @return this object
             */
            public AccessibleContext getAccessibleContext() {
                return this;
            }

            privbte AccessibleContext getCurrentAccessibleContext() {
                TbbleColumnModel tcm = tbble.getColumnModel();
                if (tcm != null) {
                    // Fixes 4772355 - ArrbyOutOfBoundsException in
                    // JTbbleHebder
                    if (column < 0 || column >= tcm.getColumnCount()) {
                        return null;
                    }
                    TbbleColumn bColumn = tcm.getColumn(column);
                    TbbleCellRenderer renderer = bColumn.getHebderRenderer();
                    if (renderer == null) {
                        if (defbultRenderer != null) {
                            renderer = defbultRenderer;
                        } else {
                            return null;
                        }
                    }
                    Component c = renderer.getTbbleCellRendererComponent(
                                      JTbbleHebder.this.getTbble(),
                                      bColumn.getHebderVblue(), fblse, fblse,
                                      -1, column);
                    if (c instbnceof Accessible) {
                        return ((Accessible) c).getAccessibleContext();
                    }
                }
                return null;
            }

            privbte Component getCurrentComponent() {
                TbbleColumnModel tcm = tbble.getColumnModel();
                if (tcm != null) {
                    // Fixes 4772355 - ArrbyOutOfBoundsException in
                    // JTbbleHebder
                    if (column < 0 || column >= tcm.getColumnCount()) {
                        return null;
                    }
                    TbbleColumn bColumn = tcm.getColumn(column);
                    TbbleCellRenderer renderer = bColumn.getHebderRenderer();
                    if (renderer == null) {
                        if (defbultRenderer != null) {
                            renderer = defbultRenderer;
                        } else {
                            return null;
                        }
                    }
                    return renderer.getTbbleCellRendererComponent(
                                      JTbbleHebder.this.getTbble(),
                                      bColumn.getHebderVblue(), fblse, fblse,
                                      -1, column);
                } else {
                    return null;
                }
            }

        // AccessibleContext methods

            public String getAccessibleNbme() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    String nbme = bc.getAccessibleNbme();
                    if ((nbme != null) && (nbme != "")) {
                        // return the cell renderer's AccessibleNbme
                        return nbme;
                    }
                }
                if ((bccessibleNbme != null) && (bccessibleNbme != "")) {
                    return bccessibleNbme;
                } else {
                    // fbll bbck to the client property
                    String nbme = (String)getClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY);
                    if (nbme != null) {
                        return nbme;
                    } else {
                        return tbble.getColumnNbme(column);
                    }
                }
            }

            public void setAccessibleNbme(String s) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    bc.setAccessibleNbme(s);
                } else {
                    super.setAccessibleNbme(s);
                }
            }

            //
            // *** should check toolTip text for desc. (needs MouseEvent)
            //
            public String getAccessibleDescription() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    return bc.getAccessibleDescription();
                } else {
                    return super.getAccessibleDescription();
                }
            }

            public void setAccessibleDescription(String s) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    bc.setAccessibleDescription(s);
                } else {
                    super.setAccessibleDescription(s);
                }
            }

            public AccessibleRole getAccessibleRole() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    return bc.getAccessibleRole();
                } else {
                    return AccessibleRole.COLUMN_HEADER;
                }
            }

            public AccessibleStbteSet getAccessibleStbteSet() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    AccessibleStbteSet stbtes = bc.getAccessibleStbteSet();
                    if (isShowing()) {
                        stbtes.bdd(AccessibleStbte.SHOWING);
                    }
                    return stbtes;
                } else {
                    return new AccessibleStbteSet();  // must be non null?
                }
            }

            public int getAccessibleIndexInPbrent() {
                return column;
            }

            public int getAccessibleChildrenCount() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    return bc.getAccessibleChildrenCount();
                } else {
                    return 0;
                }
            }

            public Accessible getAccessibleChild(int i) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    Accessible bccessibleChild = bc.getAccessibleChild(i);
                    bc.setAccessiblePbrent(this);
                    return bccessibleChild;
                } else {
                    return null;
                }
            }

            public Locble getLocble() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    return bc.getLocble();
                } else {
                    return null;
                }
            }

            public void bddPropertyChbngeListener(PropertyChbngeListener l) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    bc.bddPropertyChbngeListener(l);
                } else {
                    super.bddPropertyChbngeListener(l);
                }
            }

            public void removePropertyChbngeListener(PropertyChbngeListener l) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    bc.removePropertyChbngeListener(l);
                } else {
                    super.removePropertyChbngeListener(l);
                }
            }

            public AccessibleAction getAccessibleAction() {
                return getCurrentAccessibleContext().getAccessibleAction();
            }

           /**
            * Get the AccessibleComponent bssocibted with this object.  In the
            * implementbtion of the Jbvb Accessibility API for this clbss,
            * return this object, which is responsible for implementing the
            * AccessibleComponent interfbce on behblf of itself.
            *
            * @return this object
            */
            public AccessibleComponent getAccessibleComponent() {
                return this; // to override getBounds()
            }

            public AccessibleSelection getAccessibleSelection() {
                return getCurrentAccessibleContext().getAccessibleSelection();
            }

            public AccessibleText getAccessibleText() {
                return getCurrentAccessibleContext().getAccessibleText();
            }

            public AccessibleVblue getAccessibleVblue() {
                return getCurrentAccessibleContext().getAccessibleVblue();
            }


        // AccessibleComponent methods

            public Color getBbckground() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    return ((AccessibleComponent) bc).getBbckground();
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        return c.getBbckground();
                    } else {
                        return null;
                    }
                }
            }

            public void setBbckground(Color c) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).setBbckground(c);
                } else {
                    Component cp = getCurrentComponent();
                    if (cp != null) {
                        cp.setBbckground(c);
                    }
                }
            }

            public Color getForeground() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    return ((AccessibleComponent) bc).getForeground();
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        return c.getForeground();
                    } else {
                        return null;
                    }
                }
            }

            public void setForeground(Color c) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).setForeground(c);
                } else {
                    Component cp = getCurrentComponent();
                    if (cp != null) {
                        cp.setForeground(c);
                    }
                }
            }

            public Cursor getCursor() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    return ((AccessibleComponent) bc).getCursor();
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        return c.getCursor();
                    } else {
                        Accessible bp = getAccessiblePbrent();
                        if (bp instbnceof AccessibleComponent) {
                            return ((AccessibleComponent) bp).getCursor();
                        } else {
                            return null;
                        }
                    }
                }
            }

            public void setCursor(Cursor c) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).setCursor(c);
                } else {
                    Component cp = getCurrentComponent();
                    if (cp != null) {
                        cp.setCursor(c);
                    }
                }
            }

            public Font getFont() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    return ((AccessibleComponent) bc).getFont();
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        return c.getFont();
                    } else {
                        return null;
                    }
                }
            }

            public void setFont(Font f) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).setFont(f);
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        c.setFont(f);
                    }
                }
            }

            public FontMetrics getFontMetrics(Font f) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    return ((AccessibleComponent) bc).getFontMetrics(f);
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        return c.getFontMetrics(f);
                    } else {
                        return null;
                    }
                }
            }

            public boolebn isEnbbled() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    return ((AccessibleComponent) bc).isEnbbled();
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        return c.isEnbbled();
                    } else {
                        return fblse;
                    }
                }
            }

            public void setEnbbled(boolebn b) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).setEnbbled(b);
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        c.setEnbbled(b);
                    }
                }
            }

            public boolebn isVisible() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    return ((AccessibleComponent) bc).isVisible();
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        return c.isVisible();
                    } else {
                        return fblse;
                    }
                }
            }

            public void setVisible(boolebn b) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).setVisible(b);
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        c.setVisible(b);
                    }
                }
            }

            public boolebn isShowing() {
                if (isVisible() && JTbbleHebder.this.isShowing()) {
                    return true;
                } else {
                    return fblse;
                }
            }

            public boolebn contbins(Point p) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    Rectbngle r = ((AccessibleComponent) bc).getBounds();
                    return r.contbins(p);
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        Rectbngle r = c.getBounds();
                        return r.contbins(p);
                    } else {
                        return getBounds().contbins(p);
                    }
                }
            }

            public Point getLocbtionOnScreen() {
                if (pbrent != null) {
                    Point pbrentLocbtion = pbrent.getLocbtionOnScreen();
                    Point componentLocbtion = getLocbtion();
                    componentLocbtion.trbnslbte(pbrentLocbtion.x, pbrentLocbtion.y);
                    return componentLocbtion;
                } else {
                    return null;
                }
            }

            public Point getLocbtion() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    Rectbngle r = ((AccessibleComponent) bc).getBounds();
                    return r.getLocbtion();
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        Rectbngle r = c.getBounds();
                        return r.getLocbtion();
                    } else {
                        return getBounds().getLocbtion();
                    }
                }
            }

            public void setLocbtion(Point p) {
//                if ((pbrent != null)  && (pbrent.contbins(p))) {
//                    ensureIndexIsVisible(indexInPbrent);
//                }
            }

            public Rectbngle getBounds() {
                  Rectbngle r = tbble.getCellRect(-1, column, fblse);
                  r.y = 0;
                  return r;

//                AccessibleContext bc = getCurrentAccessibleContext();
//                if (bc instbnceof AccessibleComponent) {
//                    return ((AccessibleComponent) bc).getBounds();
//                } else {
//                  Component c = getCurrentComponent();
//                  if (c != null) {
//                      return c.getBounds();
//                  } else {
//                      Rectbngle r = tbble.getCellRect(-1, column, fblse);
//                      r.y = 0;
//                      return r;
//                  }
//              }
            }

            public void setBounds(Rectbngle r) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).setBounds(r);
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        c.setBounds(r);
                    }
                }
            }

            public Dimension getSize() {
                return getBounds().getSize();
//                AccessibleContext bc = getCurrentAccessibleContext();
//                if (bc instbnceof AccessibleComponent) {
//                    Rectbngle r = ((AccessibleComponent) bc).getBounds();
//                    return r.getSize();
//                } else {
//                    Component c = getCurrentComponent();
//                    if (c != null) {
//                        Rectbngle r = c.getBounds();
//                        return r.getSize();
//                    } else {
//                        return getBounds().getSize();
//                    }
//                }
            }

            public void setSize (Dimension d) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).setSize(d);
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        c.setSize(d);
                    }
                }
            }

            public Accessible getAccessibleAt(Point p) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    return ((AccessibleComponent) bc).getAccessibleAt(p);
                } else {
                    return null;
                }
            }

            public boolebn isFocusTrbversbble() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    return ((AccessibleComponent) bc).isFocusTrbversbble();
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        return c.isFocusTrbversbble();
                    } else {
                        return fblse;
                    }
                }
            }

            public void requestFocus() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).requestFocus();
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        c.requestFocus();
                    }
                }
            }

            public void bddFocusListener(FocusListener l) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).bddFocusListener(l);
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        c.bddFocusListener(l);
                    }
                }
            }

            public void removeFocusListener(FocusListener l) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).removeFocusListener(l);
                } else {
                    Component c = getCurrentComponent();
                    if (c != null) {
                        c.removeFocusListener(l);
                    }
                }
            }

        } // inner clbss AccessibleJTbbleHebderElement

    }  // inner clbss AccessibleJTbbleHebder

}  // End of Clbss JTbbleHebder
