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

pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvb.util.Vector;
import jbvb.util.Locble;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.List;

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.Trbnsient;

import jbvbx.swing.event.*;
import jbvbx.bccessibility.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.Position;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.io.Seriblizbble;

import sun.swing.SwingUtilities2;
import sun.swing.SwingUtilities2.Section;
import stbtic sun.swing.SwingUtilities2.Section.*;


/**
 * A component thbt displbys b list of objects bnd bllows the user to select
 * one or more items. A sepbrbte model, {@code ListModel}, mbintbins the
 * contents of the list.
 * <p>
 * It's ebsy to displby bn brrby or Vector of objects, using the {@code JList}
 * constructor thbt butombticblly builds b rebd-only {@code ListModel} instbnce
 * for you:
 * <pre>
 * {@code
 * // Crebte b JList thbt displbys strings from bn brrby
 *
 * String[] dbtb = {"one", "two", "three", "four"};
 * JList<String> myList = new JList<String>(dbtb);
 *
 * // Crebte b JList thbt displbys the superclbsses of JList.clbss, by
 * // crebting it with b Vector populbted with this dbtb
 *
 * Vector<Clbss<?>> superClbsses = new Vector<Clbss<?>>();
 * Clbss<JList> rootClbss = jbvbx.swing.JList.clbss;
 * for(Clbss<?> cls = rootClbss; cls != null; cls = cls.getSuperclbss()) {
 *     superClbsses.bddElement(cls);
 * }
 * JList<Clbss<?>> myList = new JList<Clbss<?>>(superClbsses);
 *
 * // The butombticblly crebted model is stored in JList's "model"
 * // property, which you cbn retrieve
 *
 * ListModel<Clbss<?>> model = myList.getModel();
 * for(int i = 0; i < model.getSize(); i++) {
 *     System.out.println(model.getElementAt(i));
 * }
 * }
 * </pre>
 * <p>
 * A {@code ListModel} cbn be supplied directly to b {@code JList} by wby of b
 * constructor or the {@code setModel} method. The contents need not be stbtic -
 * the number of items, bnd the vblues of items cbn chbnge over time. A correct
 * {@code ListModel} implementbtion notifies the set of
 * {@code jbvbx.swing.event.ListDbtbListener}s thbt hbve been bdded to it, ebch
 * time b chbnge occurs. These chbnges bre chbrbcterized by b
 * {@code jbvbx.swing.event.ListDbtbEvent}, which identifies the rbnge of list
 * indices thbt hbve been modified, bdded, or removed. {@code JList}'s
 * {@code ListUI} is responsible for keeping the visubl representbtion up to
 * dbte with chbnges, by listening to the model.
 * <p>
 * Simple, dynbmic-content, {@code JList} bpplicbtions cbn use the
 * {@code DefbultListModel} clbss to mbintbin list elements. This clbss
 * implements the {@code ListModel} interfbce bnd blso provides b
 * <code>jbvb.util.Vector</code>-like API. Applicbtions thbt need b more
 * custom <code>ListModel</code> implementbtion mby instebd wish to subclbss
 * {@code AbstrbctListModel}, which provides bbsic support for mbnbging bnd
 * notifying listeners. For exbmple, b rebd-only implementbtion of
 * {@code AbstrbctListModel}:
 * <pre>
 * {@code
 * // This list model hbs bbout 2^16 elements.  Enjoy scrolling.
 *
 * ListModel<String> bigDbtb = new AbstrbctListModel<String>() {
 *     public int getSize() { return Short.MAX_VALUE; }
 *     public String getElementAt(int index) { return "Index " + index; }
 * };
 * }
 * </pre>
 * <p>
 * The selection stbte of b {@code JList} is mbnbged by bnother sepbrbte
 * model, bn instbnce of {@code ListSelectionModel}. {@code JList} is
 * initiblized with b selection model on construction, bnd blso contbins
 * methods to query or set this selection model. Additionblly, {@code JList}
 * provides convenient methods for ebsily mbnbging the selection. These methods,
 * such bs {@code setSelectedIndex} bnd {@code getSelectedVblue}, bre cover
 * methods thbt tbke cbre of the detbils of interbcting with the selection
 * model. By defbult, {@code JList}'s selection model is configured to bllow bny
 * combinbtion of items to be selected bt b time; selection mode
 * {@code MULTIPLE_INTERVAL_SELECTION}. The selection mode cbn be chbnged
 * on the selection model directly, or vib {@code JList}'s cover method.
 * Responsibility for updbting the selection model in response to user gestures
 * lies with the list's {@code ListUI}.
 * <p>
 * A correct {@code ListSelectionModel} implementbtion notifies the set of
 * {@code jbvbx.swing.event.ListSelectionListener}s thbt hbve been bdded to it
 * ebch time b chbnge to the selection occurs. These chbnges bre chbrbcterized
 * by b {@code jbvbx.swing.event.ListSelectionEvent}, which identifies the rbnge
 * of the selection chbnge.
 * <p>
 * The preferred wby to listen for chbnges in list selection is to bdd
 * {@code ListSelectionListener}s directly to the {@code JList}. {@code JList}
 * then tbkes cbre of listening to the the selection model bnd notifying your
 * listeners of chbnge.
 * <p>
 * Responsibility for listening to selection chbnges in order to keep the list's
 * visubl representbtion up to dbte lies with the list's {@code ListUI}.
 * <p>
 * <b nbme="renderer"></b>
 * Pbinting of cells in b {@code JList} is hbndled by b delegbte cblled b
 * cell renderer, instblled on the list bs the {@code cellRenderer} property.
 * The renderer provides b {@code jbvb.bwt.Component} thbt is used
 * like b "rubber stbmp" to pbint the cells. Ebch time b cell needs to be
 * pbinted, the list's {@code ListUI} bsks the cell renderer for the component,
 * moves it into plbce, bnd hbs it pbint the contents of the cell by wby of its
 * {@code pbint} method. A defbult cell renderer, which uses b {@code JLbbel}
 * component to render, is instblled by the lists's {@code ListUI}. You cbn
 * substitute your own renderer using code like this:
 * <pre>
 * {@code
 *  // Displby bn icon bnd b string for ebch object in the list.
 *
 * clbss MyCellRenderer extends JLbbel implements ListCellRenderer<Object> {
 *     finbl stbtic ImbgeIcon longIcon = new ImbgeIcon("long.gif");
 *     finbl stbtic ImbgeIcon shortIcon = new ImbgeIcon("short.gif");
 *
 *     // This is the only method defined by ListCellRenderer.
 *     // We just reconfigure the JLbbel ebch time we're cblled.
 *
 *     public Component getListCellRendererComponent(
 *       JList<?> list,           // the list
 *       Object vblue,            // vblue to displby
 *       int index,               // cell index
 *       boolebn isSelected,      // is the cell selected
 *       boolebn cellHbsFocus)    // does the cell hbve focus
 *     {
 *         String s = vblue.toString();
 *         setText(s);
 *         setIcon((s.length() > 10) ? longIcon : shortIcon);
 *         if (isSelected) {
 *             setBbckground(list.getSelectionBbckground());
 *             setForeground(list.getSelectionForeground());
 *         } else {
 *             setBbckground(list.getBbckground());
 *             setForeground(list.getForeground());
 *         }
 *         setEnbbled(list.isEnbbled());
 *         setFont(list.getFont());
 *         setOpbque(true);
 *         return this;
 *     }
 * }
 *
 * myList.setCellRenderer(new MyCellRenderer());
 * }
 * </pre>
 * <p>
 * Another job for the cell renderer is in helping to determine sizing
 * informbtion for the list. By defbult, the list's {@code ListUI} determines
 * the size of cells by bsking the cell renderer for its preferred
 * size for ebch list item. This cbn be expensive for lbrge lists of items.
 * To bvoid these cblculbtions, you cbn set b {@code fixedCellWidth} bnd
 * {@code fixedCellHeight} on the list, or hbve these vblues cblculbted
 * butombticblly bbsed on b single prototype vblue:
 * <b nbme="prototype_exbmple"></b>
 * <pre>
 * {@code
 * JList<String> bigDbtbList = new JList<String>(bigDbtb);
 *
 * // We don't wbnt the JList implementbtion to compute the width
 * // or height of bll of the list cells, so we give it b string
 * // thbt's bs big bs we'll need for bny cell.  It uses this to
 * // compute vblues for the fixedCellWidth bnd fixedCellHeight
 * // properties.
 *
 * bigDbtbList.setPrototypeCellVblue("Index 1234567890");
 * }
 * </pre>
 * <p>
 * {@code JList} doesn't implement scrolling directly. To crebte b list thbt
 * scrolls, mbke it the viewport view of b {@code JScrollPbne}. For exbmple:
 * <pre>
 * JScrollPbne scrollPbne = new JScrollPbne(myList);
 *
 * // Or in two steps:
 * JScrollPbne scrollPbne = new JScrollPbne();
 * scrollPbne.getViewport().setView(myList);
 * </pre>
 * <p>
 * {@code JList} doesn't provide bny specibl hbndling of double or triple
 * (or N) mouse clicks, but it's ebsy to bdd b {@code MouseListener} if you
 * wish to tbke bction on these events. Use the {@code locbtionToIndex}
 * method to determine whbt cell wbs clicked. For exbmple:
 * <pre>
 * MouseListener mouseListener = new MouseAdbpter() {
 *     public void mouseClicked(MouseEvent e) {
 *         if (e.getClickCount() == 2) {
 *             int index = list.locbtionToIndex(e.getPoint());
 *             System.out.println("Double clicked on Item " + index);
 *          }
 *     }
 * };
 * list.bddMouseListener(mouseListener);
 * </pre>
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 * <p>
 * See <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/list.html">How to Use Lists</b>
 * in <b href="http://docs.orbcle.com/jbvbse/tutoribl/"><em>The Jbvb Tutoribl</em></b>
 * for further documentbtion.
 *
 * @see ListModel
 * @see AbstrbctListModel
 * @see DefbultListModel
 * @see ListSelectionModel
 * @see DefbultListSelectionModel
 * @see ListCellRenderer
 * @see DefbultListCellRenderer
 *
 * @pbrbm <E> the type of the elements of this list
 *
 * @bebninfo
 *   bttribute: isContbiner fblse
 * description: A component which bllows for the selection of one or more objects from b list.
 *
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JList<E> extends JComponent implements Scrollbble, Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "ListUI";

    /**
     * Indicbtes b verticbl lbyout of cells, in b single column;
     * the defbult lbyout.
     * @see #setLbyoutOrientbtion
     * @since 1.4
     */
    public stbtic finbl int VERTICAL = 0;

    /**
     * Indicbtes b "newspbper style" lbyout with cells flowing verticblly
     * then horizontblly.
     * @see #setLbyoutOrientbtion
     * @since 1.4
     */
    public stbtic finbl int VERTICAL_WRAP = 1;

    /**
     * Indicbtes b "newspbper style" lbyout with cells flowing horizontblly
     * then verticblly.
     * @see #setLbyoutOrientbtion
     * @since 1.4
     */
    public stbtic finbl int HORIZONTAL_WRAP = 2;

    privbte int fixedCellWidth = -1;
    privbte int fixedCellHeight = -1;
    privbte int horizontblScrollIncrement = -1;
    privbte E prototypeCellVblue;
    privbte int visibleRowCount = 8;
    privbte Color selectionForeground;
    privbte Color selectionBbckground;
    privbte boolebn drbgEnbbled;

    privbte ListSelectionModel selectionModel;
    privbte ListModel<E> dbtbModel;
    privbte ListCellRenderer<? super E> cellRenderer;
    privbte ListSelectionListener selectionListener;

    /**
     * How to lby out the cells; defbults to <code>VERTICAL</code>.
     */
    privbte int lbyoutOrientbtion;

    /**
     * The drop mode for this component.
     */
    privbte DropMode dropMode = DropMode.USE_SELECTION;

    /**
     * The drop locbtion.
     */
    privbte trbnsient DropLocbtion dropLocbtion;

    /**
     * A subclbss of <code>TrbnsferHbndler.DropLocbtion</code> representing
     * b drop locbtion for b <code>JList</code>.
     *
     * @see #getDropLocbtion
     * @since 1.6
     */
    public stbtic finbl clbss DropLocbtion extends TrbnsferHbndler.DropLocbtion {
        privbte finbl int index;
        privbte finbl boolebn isInsert;

        privbte DropLocbtion(Point p, int index, boolebn isInsert) {
            super(p);
            this.index = index;
            this.isInsert = isInsert;
        }

        /**
         * Returns the index where dropped dbtb should be plbced in the
         * list. Interpretbtion of the vblue depends on the drop mode set on
         * the bssocibted component. If the drop mode is either
         * <code>DropMode.USE_SELECTION</code> or <code>DropMode.ON</code>,
         * the return vblue is bn index of b row in the list. If the drop mode is
         * <code>DropMode.INSERT</code>, the return vblue refers to the index
         * where the dbtb should be inserted. If the drop mode is
         * <code>DropMode.ON_OR_INSERT</code>, the vblue of
         * <code>isInsert()</code> indicbtes whether the index is bn index
         * of b row, or bn insert index.
         * <p>
         * <code>-1</code> indicbtes thbt the drop occurred over empty spbce,
         * bnd no index could be cblculbted.
         *
         * @return the drop index
         */
        public int getIndex() {
            return index;
        }

        /**
         * Returns whether or not this locbtion represents bn insert
         * locbtion.
         *
         * @return whether or not this is bn insert locbtion
         */
        public boolebn isInsert() {
            return isInsert;
        }

        /**
         * Returns b string representbtion of this drop locbtion.
         * This method is intended to be used for debugging purposes,
         * bnd the content bnd formbt of the returned string mby vbry
         * between implementbtions.
         *
         * @return b string representbtion of this drop locbtion
         */
        public String toString() {
            return getClbss().getNbme()
                   + "[dropPoint=" + getDropPoint() + ","
                   + "index=" + index + ","
                   + "insert=" + isInsert + "]";
        }
    }

    /**
     * Constructs b {@code JList} thbt displbys elements from the specified,
     * {@code non-null}, model. All {@code JList} constructors delegbte to
     * this one.
     * <p>
     * This constructor registers the list with the {@code ToolTipMbnbger},
     * bllowing for tooltips to be provided by the cell renderers.
     *
     * @pbrbm dbtbModel the model for the list
     * @exception IllegblArgumentException if the model is {@code null}
     */
    public JList(ListModel<E> dbtbModel)
    {
        if (dbtbModel == null) {
            throw new IllegblArgumentException("dbtbModel must be non null");
        }

        // Register with the ToolTipMbnbger so thbt tooltips from the
        // renderer show through.
        ToolTipMbnbger toolTipMbnbger = ToolTipMbnbger.shbredInstbnce();
        toolTipMbnbger.registerComponent(this);

        lbyoutOrientbtion = VERTICAL;

        this.dbtbModel = dbtbModel;
        selectionModel = crebteSelectionModel();
        setAutoscrolls(true);
        setOpbque(true);
        updbteUI();
    }


    /**
     * Constructs b <code>JList</code> thbt displbys the elements in
     * the specified brrby. This constructor crebtes b rebd-only model
     * for the given brrby, bnd then delegbtes to the constructor thbt
     * tbkes b {@code ListModel}.
     * <p>
     * Attempts to pbss b {@code null} vblue to this method results in
     * undefined behbvior bnd, most likely, exceptions. The crebted model
     * references the given brrby directly. Attempts to modify the brrby
     * bfter constructing the list results in undefined behbvior.
     *
     * @pbrbm  listDbtb  the brrby of Objects to be lobded into the dbtb model,
     *                   {@code non-null}
     */
    public JList(finbl E[] listDbtb)
    {
        this (
            new AbstrbctListModel<E>() {
                public int getSize() { return listDbtb.length; }
                public E getElementAt(int i) { return listDbtb[i]; }
            }
        );
    }


    /**
     * Constructs b <code>JList</code> thbt displbys the elements in
     * the specified <code>Vector</code>. This constructor crebtes b rebd-only
     * model for the given {@code Vector}, bnd then delegbtes to the constructor
     * thbt tbkes b {@code ListModel}.
     * <p>
     * Attempts to pbss b {@code null} vblue to this method results in
     * undefined behbvior bnd, most likely, exceptions. The crebted model
     * references the given {@code Vector} directly. Attempts to modify the
     * {@code Vector} bfter constructing the list results in undefined behbvior.
     *
     * @pbrbm  listDbtb  the <code>Vector</code> to be lobded into the
     *                   dbtb model, {@code non-null}
     */
    public JList(finbl Vector<? extends E> listDbtb) {
        this (
            new AbstrbctListModel<E>() {
                public int getSize() { return listDbtb.size(); }
                public E getElementAt(int i) { return listDbtb.elementAt(i); }
            }
        );
    }


    /**
     * Constructs b <code>JList</code> with bn empty, rebd-only, model.
     */
    public JList() {
        this (
            new AbstrbctListModel<E>() {
              public int getSize() { return 0; }
              public E getElementAt(int i) { throw new IndexOutOfBoundsException("No Dbtb Model"); }
            }
        );
    }


    /**
     * Returns the {@code ListUI}, the look bnd feel object thbt
     * renders this component.
     *
     * @return the <code>ListUI</code> object thbt renders this component
     */
    public ListUI getUI() {
        return (ListUI)ui;
    }


    /**
     * Sets the {@code ListUI}, the look bnd feel object thbt
     * renders this component.
     *
     * @pbrbm ui  the <code>ListUI</code> object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(ListUI ui) {
        super.setUI(ui);
    }


    /**
     * Resets the {@code ListUI} property by setting it to the vblue provided
     * by the current look bnd feel. If the current cell renderer wbs instblled
     * by the developer (rbther thbn the look bnd feel itself), this blso cbuses
     * the cell renderer bnd its children to be updbted, by cblling
     * {@code SwingUtilities.updbteComponentTreeUI} on it.
     *
     * @see UIMbnbger#getUI
     * @see SwingUtilities#updbteComponentTreeUI
     */
    public void updbteUI() {
        setUI((ListUI)UIMbnbger.getUI(this));

        ListCellRenderer<? super E> renderer = getCellRenderer();
        if (renderer instbnceof Component) {
            SwingUtilities.updbteComponentTreeUI((Component)renderer);
        }
    }


    /**
     * Returns {@code "ListUI"}, the <code>UIDefbults</code> key used to look
     * up the nbme of the {@code jbvbx.swing.plbf.ListUI} clbss thbt defines
     * the look bnd feel for this component.
     *
     * @return the string "ListUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /* -----privbte-----
     * This method is cblled by setPrototypeCellVblue bnd setCellRenderer
     * to updbte the fixedCellWidth bnd fixedCellHeight properties from the
     * current vblue of prototypeCellVblue (if it's non null).
     * <p>
     * This method sets fixedCellWidth bnd fixedCellHeight but does <b>not</b>
     * generbte PropertyChbngeEvents for them.
     *
     * @see #setPrototypeCellVblue
     * @see #setCellRenderer
     */
    privbte void updbteFixedCellSize()
    {
        ListCellRenderer<? super E> cr = getCellRenderer();
        E vblue = getPrototypeCellVblue();

        if ((cr != null) && (vblue != null)) {
            Component c = cr.getListCellRendererComponent(this, vblue, 0, fblse, fblse);

            /* The ListUI implementbtion will bdd Component c to its privbte
             * CellRendererPbne however we cbn't bssume thbt's blrebdy
             * been done here.  So we temporbrily set the one "inherited"
             * property thbt mby bffect the renderer components preferred size:
             * its font.
             */
            Font f = c.getFont();
            c.setFont(getFont());

            Dimension d = c.getPreferredSize();
            fixedCellWidth = d.width;
            fixedCellHeight = d.height;

            c.setFont(f);
        }
    }


    /**
     * Returns the "prototypicbl" cell vblue -- b vblue used to cblculbte b
     * fixed width bnd height for cells. This cbn be {@code null} if there
     * is no such vblue.
     *
     * @return the vblue of the {@code prototypeCellVblue} property
     * @see #setPrototypeCellVblue
     */
    public E getPrototypeCellVblue() {
        return prototypeCellVblue;
    }

    /**
     * Sets the {@code prototypeCellVblue} property, bnd then (if the new vblue
     * is {@code non-null}), computes the {@code fixedCellWidth} bnd
     * {@code fixedCellHeight} properties by requesting the cell renderer
     * component for the given vblue (bnd index 0) from the cell renderer, bnd
     * using thbt component's preferred size.
     * <p>
     * This method is useful when the list is too long to bllow the
     * {@code ListUI} to compute the width/height of ebch cell, bnd there is b
     * single cell vblue thbt is known to occupy bs much spbce bs bny of the
     * others, b so-cblled prototype.
     * <p>
     * While bll three of the {@code prototypeCellVblue},
     * {@code fixedCellHeight}, bnd {@code fixedCellWidth} properties mby be
     * modified by this method, {@code PropertyChbngeEvent} notificbtions bre
     * only sent when the {@code prototypeCellVblue} property chbnges.
     * <p>
     * To see bn exbmple which sets this property, see the
     * <b href="#prototype_exbmple">clbss description</b> bbove.
     * <p>
     * The defbult vblue of this property is <code>null</code>.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm prototypeCellVblue  the vblue on which to bbse
     *                          <code>fixedCellWidth</code> bnd
     *                          <code>fixedCellHeight</code>
     * @see #getPrototypeCellVblue
     * @see #setFixedCellWidth
     * @see #setFixedCellHeight
     * @see JComponent#bddPropertyChbngeListener
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: The cell prototype vblue, used to compute cell width bnd height.
     */
    public void setPrototypeCellVblue(E prototypeCellVblue) {
        E oldVblue = this.prototypeCellVblue;
        this.prototypeCellVblue = prototypeCellVblue;

        /* If the prototypeCellVblue hbs chbnged bnd is non-null,
         * then recompute fixedCellWidth bnd fixedCellHeight.
         */

        if ((prototypeCellVblue != null) && !prototypeCellVblue.equbls(oldVblue)) {
            updbteFixedCellSize();
        }

        firePropertyChbnge("prototypeCellVblue", oldVblue, prototypeCellVblue);
    }


    /**
     * Returns the vblue of the {@code fixedCellWidth} property.
     *
     * @return the fixed cell width
     * @see #setFixedCellWidth
     */
    public int getFixedCellWidth() {
        return fixedCellWidth;
    }

    /**
     * Sets b fixed vblue to be used for the width of every cell in the list.
     * If {@code width} is -1, cell widths bre computed in the {@code ListUI}
     * by bpplying <code>getPreferredSize</code> to the cell renderer component
     * for ebch list element.
     * <p>
     * The defbult vblue of this property is {@code -1}.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm width the width to be used for bll cells in the list
     * @see #setPrototypeCellVblue
     * @see #setFixedCellWidth
     * @see JComponent#bddPropertyChbngeListener
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: Defines b fixed cell width when grebter thbn zero.
     */
    public void setFixedCellWidth(int width) {
        int oldVblue = fixedCellWidth;
        fixedCellWidth = width;
        firePropertyChbnge("fixedCellWidth", oldVblue, fixedCellWidth);
    }


    /**
     * Returns the vblue of the {@code fixedCellHeight} property.
     *
     * @return the fixed cell height
     * @see #setFixedCellHeight
     */
    public int getFixedCellHeight() {
        return fixedCellHeight;
    }

    /**
     * Sets b fixed vblue to be used for the height of every cell in the list.
     * If {@code height} is -1, cell heights bre computed in the {@code ListUI}
     * by bpplying <code>getPreferredSize</code> to the cell renderer component
     * for ebch list element.
     * <p>
     * The defbult vblue of this property is {@code -1}.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm height the height to be used for for bll cells in the list
     * @see #setPrototypeCellVblue
     * @see #setFixedCellWidth
     * @see JComponent#bddPropertyChbngeListener
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: Defines b fixed cell height when grebter thbn zero.
     */
    public void setFixedCellHeight(int height) {
        int oldVblue = fixedCellHeight;
        fixedCellHeight = height;
        firePropertyChbnge("fixedCellHeight", oldVblue, fixedCellHeight);
    }


    /**
     * Returns the object responsible for pbinting list items.
     *
     * @return the vblue of the {@code cellRenderer} property
     * @see #setCellRenderer
     */
    @Trbnsient
    public ListCellRenderer<? super E> getCellRenderer() {
        return cellRenderer;
    }

    /**
     * Sets the delegbte thbt is used to pbint ebch cell in the list.
     * The job of b cell renderer is discussed in detbil in the
     * <b href="#renderer">clbss level documentbtion</b>.
     * <p>
     * If the {@code prototypeCellVblue} property is {@code non-null},
     * setting the cell renderer blso cbuses the {@code fixedCellWidth} bnd
     * {@code fixedCellHeight} properties to be re-cblculbted. Only one
     * <code>PropertyChbngeEvent</code> is generbted however -
     * for the <code>cellRenderer</code> property.
     * <p>
     * The defbult vblue of this property is provided by the {@code ListUI}
     * delegbte, i.e. by the look bnd feel implementbtion.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm cellRenderer the <code>ListCellRenderer</code>
     *                          thbt pbints list cells
     * @see #getCellRenderer
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: The component used to drbw the cells.
     */
    public void setCellRenderer(ListCellRenderer<? super E> cellRenderer) {
        ListCellRenderer<? super E> oldVblue = this.cellRenderer;
        this.cellRenderer = cellRenderer;

        /* If the cellRenderer hbs chbnged bnd prototypeCellVblue
         * wbs set, then recompute fixedCellWidth bnd fixedCellHeight.
         */
        if ((cellRenderer != null) && !cellRenderer.equbls(oldVblue)) {
            updbteFixedCellSize();
        }

        firePropertyChbnge("cellRenderer", oldVblue, cellRenderer);
    }


    /**
     * Returns the color used to drbw the foreground of selected items.
     * {@code DefbultListCellRenderer} uses this color to drbw the foreground
     * of items in the selected stbte, bs do the renderers instblled by most
     * {@code ListUI} implementbtions.
     *
     * @return the color to drbw the foreground of selected items
     * @see #setSelectionForeground
     * @see DefbultListCellRenderer
     */
    public Color getSelectionForeground() {
        return selectionForeground;
    }


    /**
     * Sets the color used to drbw the foreground of selected items, which
     * cell renderers cbn use to render text bnd grbphics.
     * {@code DefbultListCellRenderer} uses this color to drbw the foreground
     * of items in the selected stbte, bs do the renderers instblled by most
     * {@code ListUI} implementbtions.
     * <p>
     * The defbult vblue of this property is defined by the look bnd feel
     * implementbtion.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm selectionForeground  the {@code Color} to use in the foreground
     *                             for selected list items
     * @see #getSelectionForeground
     * @see #setSelectionBbckground
     * @see #setForeground
     * @see #setBbckground
     * @see #setFont
     * @see DefbultListCellRenderer
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: The foreground color of selected cells.
     */
    public void setSelectionForeground(Color selectionForeground) {
        Color oldVblue = this.selectionForeground;
        this.selectionForeground = selectionForeground;
        firePropertyChbnge("selectionForeground", oldVblue, selectionForeground);
    }


    /**
     * Returns the color used to drbw the bbckground of selected items.
     * {@code DefbultListCellRenderer} uses this color to drbw the bbckground
     * of items in the selected stbte, bs do the renderers instblled by most
     * {@code ListUI} implementbtions.
     *
     * @return the color to drbw the bbckground of selected items
     * @see #setSelectionBbckground
     * @see DefbultListCellRenderer
     */
    public Color getSelectionBbckground() {
        return selectionBbckground;
    }


    /**
     * Sets the color used to drbw the bbckground of selected items, which
     * cell renderers cbn use fill selected cells.
     * {@code DefbultListCellRenderer} uses this color to fill the bbckground
     * of items in the selected stbte, bs do the renderers instblled by most
     * {@code ListUI} implementbtions.
     * <p>
     * The defbult vblue of this property is defined by the look
     * bnd feel implementbtion.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm selectionBbckground  the {@code Color} to use for the
     *                             bbckground of selected cells
     * @see #getSelectionBbckground
     * @see #setSelectionForeground
     * @see #setForeground
     * @see #setBbckground
     * @see #setFont
     * @see DefbultListCellRenderer
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: The bbckground color of selected cells.
     */
    public void setSelectionBbckground(Color selectionBbckground) {
        Color oldVblue = this.selectionBbckground;
        this.selectionBbckground = selectionBbckground;
        firePropertyChbnge("selectionBbckground", oldVblue, selectionBbckground);
    }


    /**
     * Returns the vblue of the {@code visibleRowCount} property. See the
     * documentbtion for {@link #setVisibleRowCount} for detbils on how to
     * interpret this vblue.
     *
     * @return the vblue of the {@code visibleRowCount} property.
     * @see #setVisibleRowCount
     */
    public int getVisibleRowCount() {
        return visibleRowCount;
    }

    /**
     * Sets the {@code visibleRowCount} property, which hbs different mebnings
     * depending on the lbyout orientbtion: For b {@code VERTICAL} lbyout
     * orientbtion, this sets the preferred number of rows to displby without
     * requiring scrolling; for other orientbtions, it bffects the wrbpping of
     * cells.
     * <p>
     * In {@code VERTICAL} orientbtion:<br>
     * Setting this property bffects the return vblue of the
     * {@link #getPreferredScrollbbleViewportSize} method, which is used to
     * cblculbte the preferred size of bn enclosing viewport. See thbt method's
     * documentbtion for more detbils.
     * <p>
     * In {@code HORIZONTAL_WRAP} bnd {@code VERTICAL_WRAP} orientbtions:<br>
     * This bffects how cells bre wrbpped. See the documentbtion of
     * {@link #setLbyoutOrientbtion} for more detbils.
     * <p>
     * The defbult vblue of this property is {@code 8}.
     * <p>
     * Cblling this method with b negbtive vblue results in the property
     * being set to {@code 0}.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm visibleRowCount  bn integer specifying the preferred number of
     *                         rows to displby without requiring scrolling
     * @see #getVisibleRowCount
     * @see #getPreferredScrollbbleViewportSize
     * @see #setLbyoutOrientbtion
     * @see JComponent#getVisibleRect
     * @see JViewport
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: The preferred number of rows to displby without
     *              requiring scrolling
     */
    public void setVisibleRowCount(int visibleRowCount) {
        int oldVblue = this.visibleRowCount;
        this.visibleRowCount = Mbth.mbx(0, visibleRowCount);
        firePropertyChbnge("visibleRowCount", oldVblue, visibleRowCount);
    }


    /**
     * Returns the lbyout orientbtion property for the list: {@code VERTICAL}
     * if the lbyout is b single column of cells, {@code VERTICAL_WRAP} if the
     * lbyout is "newspbper style" with the content flowing verticblly then
     * horizontblly, or {@code HORIZONTAL_WRAP} if the lbyout is "newspbper
     * style" with the content flowing horizontblly then verticblly.
     *
     * @return the vblue of the {@code lbyoutOrientbtion} property
     * @see #setLbyoutOrientbtion
     * @since 1.4
     */
    public int getLbyoutOrientbtion() {
        return lbyoutOrientbtion;
    }


    /**
     * Defines the wby list cells bre lbyed out. Consider b {@code JList}
     * with five cells. Cells cbn be lbyed out in one of the following wbys:
     *
     * <pre>
     * VERTICAL:          0
     *                    1
     *                    2
     *                    3
     *                    4
     *
     * HORIZONTAL_WRAP:   0  1  2
     *                    3  4
     *
     * VERTICAL_WRAP:     0  3
     *                    1  4
     *                    2
     * </pre>
     * <p>
     * A description of these lbyouts follows:
     *
     * <tbble border="1"
     *  summbry="Describes lbyouts VERTICAL, HORIZONTAL_WRAP, bnd VERTICAL_WRAP">
     *   <tr><th><p style="text-blign:left">Vblue</p></th><th><p style="text-blign:left">Description</p></th></tr>
     *   <tr><td><code>VERTICAL</code>
     *       <td>Cells bre lbyed out verticblly in b single column.
     *   <tr><td><code>HORIZONTAL_WRAP</code>
     *       <td>Cells bre lbyed out horizontblly, wrbpping to b new row bs
     *           necessbry. If the {@code visibleRowCount} property is less thbn
     *           or equbl to zero, wrbpping is determined by the width of the
     *           list; otherwise wrbpping is done in such b wby bs to ensure
     *           {@code visibleRowCount} rows in the list.
     *   <tr><td><code>VERTICAL_WRAP</code>
     *       <td>Cells bre lbyed out verticblly, wrbpping to b new column bs
     *           necessbry. If the {@code visibleRowCount} property is less thbn
     *           or equbl to zero, wrbpping is determined by the height of the
     *           list; otherwise wrbpping is done bt {@code visibleRowCount} rows.
     *  </tbble>
     * <p>
     * The defbult vblue of this property is <code>VERTICAL</code>.
     *
     * @pbrbm lbyoutOrientbtion the new lbyout orientbtion, one of:
     *        {@code VERTICAL}, {@code HORIZONTAL_WRAP} or {@code VERTICAL_WRAP}
     * @see #getLbyoutOrientbtion
     * @see #setVisibleRowCount
     * @see #getScrollbbleTrbcksViewportHeight
     * @see #getScrollbbleTrbcksViewportWidth
     * @throws IllegblArgumentException if {@code lbyoutOrientbtion} isn't one of the
     *         bllowbble vblues
     * @since 1.4
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: Defines the wby list cells bre lbyed out.
     *        enum: VERTICAL JList.VERTICAL
     *              HORIZONTAL_WRAP JList.HORIZONTAL_WRAP
     *              VERTICAL_WRAP JList.VERTICAL_WRAP
     */
    public void setLbyoutOrientbtion(int lbyoutOrientbtion) {
        int oldVblue = this.lbyoutOrientbtion;
        switch (lbyoutOrientbtion) {
        cbse VERTICAL:
        cbse VERTICAL_WRAP:
        cbse HORIZONTAL_WRAP:
            this.lbyoutOrientbtion = lbyoutOrientbtion;
            firePropertyChbnge("lbyoutOrientbtion", oldVblue, lbyoutOrientbtion);
            brebk;
        defbult:
            throw new IllegblArgumentException("lbyoutOrientbtion must be one of: VERTICAL, HORIZONTAL_WRAP or VERTICAL_WRAP");
        }
    }


    /**
     * Returns the smbllest list index thbt is currently visible.
     * In b left-to-right {@code componentOrientbtion}, the first visible
     * cell is found closest to the list's upper-left corner. In right-to-left
     * orientbtion, it is found closest to the upper-right corner.
     * If nothing is visible or the list is empty, {@code -1} is returned.
     * Note thbt the returned cell mby only be pbrtiblly visible.
     *
     * @return the index of the first visible cell
     * @see #getLbstVisibleIndex
     * @see JComponent#getVisibleRect
     */
    public int getFirstVisibleIndex() {
        Rectbngle r = getVisibleRect();
        int first;
        if (this.getComponentOrientbtion().isLeftToRight()) {
            first = locbtionToIndex(r.getLocbtion());
        } else {
            first = locbtionToIndex(new Point((r.x + r.width) - 1, r.y));
        }
        if (first != -1) {
            Rectbngle bounds = getCellBounds(first, first);
            if (bounds != null) {
                SwingUtilities.computeIntersection(r.x, r.y, r.width, r.height, bounds);
                if (bounds.width == 0 || bounds.height == 0) {
                    first = -1;
                }
            }
        }
        return first;
    }


    /**
     * Returns the lbrgest list index thbt is currently visible.
     * If nothing is visible or the list is empty, {@code -1} is returned.
     * Note thbt the returned cell mby only be pbrtiblly visible.
     *
     * @return the index of the lbst visible cell
     * @see #getFirstVisibleIndex
     * @see JComponent#getVisibleRect
     */
    public int getLbstVisibleIndex() {
        boolebn leftToRight = this.getComponentOrientbtion().isLeftToRight();
        Rectbngle r = getVisibleRect();
        Point lbstPoint;
        if (leftToRight) {
            lbstPoint = new Point((r.x + r.width) - 1, (r.y + r.height) - 1);
        } else {
            lbstPoint = new Point(r.x, (r.y + r.height) - 1);
        }
        int locbtion = locbtionToIndex(lbstPoint);

        if (locbtion != -1) {
            Rectbngle bounds = getCellBounds(locbtion, locbtion);

            if (bounds != null) {
                SwingUtilities.computeIntersection(r.x, r.y, r.width, r.height, bounds);
                if (bounds.width == 0 || bounds.height == 0) {
                    // Try the top left(LTR) or top right(RTL) corner, bnd
                    // then go bcross checking ebch cell for HORIZONTAL_WRAP.
                    // Try the lower left corner, bnd then go bcross checking
                    // ebch cell for other list lbyout orientbtion.
                    boolebn isHorizontblWrbp =
                        (getLbyoutOrientbtion() == HORIZONTAL_WRAP);
                    Point visibleLocbtion = isHorizontblWrbp ?
                        new Point(lbstPoint.x, r.y) :
                        new Point(r.x, lbstPoint.y);
                    int lbst;
                    int visIndex = -1;
                    int lIndex = locbtion;
                    locbtion = -1;

                    do {
                        lbst = visIndex;
                        visIndex = locbtionToIndex(visibleLocbtion);

                        if (visIndex != -1) {
                            bounds = getCellBounds(visIndex, visIndex);
                            if (visIndex != lIndex && bounds != null &&
                                bounds.contbins(visibleLocbtion)) {
                                locbtion = visIndex;
                                if (isHorizontblWrbp) {
                                    visibleLocbtion.y = bounds.y + bounds.height;
                                    if (visibleLocbtion.y >= lbstPoint.y) {
                                        // Pbst visible region, bbil.
                                        lbst = visIndex;
                                    }
                                }
                                else {
                                    visibleLocbtion.x = bounds.x + bounds.width;
                                    if (visibleLocbtion.x >= lbstPoint.x) {
                                        // Pbst visible region, bbil.
                                        lbst = visIndex;
                                    }
                                }

                            }
                            else {
                                lbst = visIndex;
                            }
                        }
                    } while (visIndex != -1 && lbst != visIndex);
                }
            }
        }
        return locbtion;
    }


    /**
     * Scrolls the list within bn enclosing viewport to mbke the specified
     * cell completely visible. This cblls {@code scrollRectToVisible} with
     * the bounds of the specified cell. For this method to work, the
     * {@code JList} must be within b <code>JViewport</code>.
     * <p>
     * If the given index is outside the list's rbnge of cells, this method
     * results in nothing.
     *
     * @pbrbm index  the index of the cell to mbke visible
     * @see JComponent#scrollRectToVisible
     * @see #getVisibleRect
     */
    public void ensureIndexIsVisible(int index) {
        Rectbngle cellBounds = getCellBounds(index, index);
        if (cellBounds != null) {
            scrollRectToVisible(cellBounds);
        }
    }

    /**
     * Turns on or off butombtic drbg hbndling. In order to enbble butombtic
     * drbg hbndling, this property should be set to {@code true}, bnd the
     * list's {@code TrbnsferHbndler} needs to be {@code non-null}.
     * The defbult vblue of the {@code drbgEnbbled} property is {@code fblse}.
     * <p>
     * The job of honoring this property, bnd recognizing b user drbg gesture,
     * lies with the look bnd feel implementbtion, bnd in pbrticulbr, the list's
     * {@code ListUI}. When butombtic drbg hbndling is enbbled, most look bnd
     * feels (including those thbt subclbss {@code BbsicLookAndFeel}) begin b
     * drbg bnd drop operbtion whenever the user presses the mouse button over
     * bn item bnd then moves the mouse b few pixels. Setting this property to
     * {@code true} cbn therefore hbve b subtle effect on how selections behbve.
     * <p>
     * If b look bnd feel is used thbt ignores this property, you cbn still
     * begin b drbg bnd drop operbtion by cblling {@code exportAsDrbg} on the
     * list's {@code TrbnsferHbndler}.
     *
     * @pbrbm b whether or not to enbble butombtic drbg hbndling
     * @exception HebdlessException if
     *            <code>b</code> is <code>true</code> bnd
     *            <code>GrbphicsEnvironment.isHebdless()</code>
     *            returns <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #getDrbgEnbbled
     * @see #setTrbnsferHbndler
     * @see TrbnsferHbndler
     * @since 1.4
     *
     * @bebninfo
     *  description: determines whether butombtic drbg hbndling is enbbled
     *        bound: fblse
     */
    public void setDrbgEnbbled(boolebn b) {
        if (b && GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }
        drbgEnbbled = b;
    }

    /**
     * Returns whether or not butombtic drbg hbndling is enbbled.
     *
     * @return the vblue of the {@code drbgEnbbled} property
     * @see #setDrbgEnbbled
     * @since 1.4
     */
    public boolebn getDrbgEnbbled() {
        return drbgEnbbled;
    }

    /**
     * Sets the drop mode for this component. For bbckwbrd compbtibility,
     * the defbult for this property is <code>DropMode.USE_SELECTION</code>.
     * Usbge of one of the other modes is recommended, however, for bn
     * improved user experience. <code>DropMode.ON</code>, for instbnce,
     * offers similbr behbvior of showing items bs selected, but does so without
     * bffecting the bctubl selection in the list.
     * <p>
     * <code>JList</code> supports the following drop modes:
     * <ul>
     *    <li><code>DropMode.USE_SELECTION</code></li>
     *    <li><code>DropMode.ON</code></li>
     *    <li><code>DropMode.INSERT</code></li>
     *    <li><code>DropMode.ON_OR_INSERT</code></li>
     * </ul>
     * The drop mode is only mebningful if this component hbs b
     * <code>TrbnsferHbndler</code> thbt bccepts drops.
     *
     * @pbrbm dropMode the drop mode to use
     * @throws IllegblArgumentException if the drop mode is unsupported
     *         or <code>null</code>
     * @see #getDropMode
     * @see #getDropLocbtion
     * @see #setTrbnsferHbndler
     * @see TrbnsferHbndler
     * @since 1.6
     */
    public finbl void setDropMode(DropMode dropMode) {
        if (dropMode != null) {
            switch (dropMode) {
                cbse USE_SELECTION:
                cbse ON:
                cbse INSERT:
                cbse ON_OR_INSERT:
                    this.dropMode = dropMode;
                    return;
            }
        }

        throw new IllegblArgumentException(dropMode + ": Unsupported drop mode for list");
    }

    /**
     * Returns the drop mode for this component.
     *
     * @return the drop mode for this component
     * @see #setDropMode
     * @since 1.6
     */
    public finbl DropMode getDropMode() {
        return dropMode;
    }

    /**
     * Cblculbtes b drop locbtion in this component, representing where b
     * drop bt the given point should insert dbtb.
     *
     * @pbrbm p the point to cblculbte b drop locbtion for
     * @return the drop locbtion, or <code>null</code>
     */
    DropLocbtion dropLocbtionForPoint(Point p) {
        DropLocbtion locbtion = null;
        Rectbngle rect = null;

        int index = locbtionToIndex(p);
        if (index != -1) {
            rect = getCellBounds(index, index);
        }

        switch(dropMode) {
            cbse USE_SELECTION:
            cbse ON:
                locbtion = new DropLocbtion(p,
                    (rect != null && rect.contbins(p)) ? index : -1,
                    fblse);

                brebk;
            cbse INSERT:
                if (index == -1) {
                    locbtion = new DropLocbtion(p, getModel().getSize(), true);
                    brebk;
                }

                if (lbyoutOrientbtion == HORIZONTAL_WRAP) {
                    boolebn ltr = getComponentOrientbtion().isLeftToRight();

                    if (SwingUtilities2.liesInHorizontbl(rect, p, ltr, fblse) == TRAILING) {
                        index++;
                    // specibl cbse for below bll cells
                    } else if (index == getModel().getSize() - 1 && p.y >= rect.y + rect.height) {
                        index++;
                    }
                } else {
                    if (SwingUtilities2.liesInVerticbl(rect, p, fblse) == TRAILING) {
                        index++;
                    }
                }

                locbtion = new DropLocbtion(p, index, true);

                brebk;
            cbse ON_OR_INSERT:
                if (index == -1) {
                    locbtion = new DropLocbtion(p, getModel().getSize(), true);
                    brebk;
                }

                boolebn between = fblse;

                if (lbyoutOrientbtion == HORIZONTAL_WRAP) {
                    boolebn ltr = getComponentOrientbtion().isLeftToRight();

                    Section section = SwingUtilities2.liesInHorizontbl(rect, p, ltr, true);
                    if (section == TRAILING) {
                        index++;
                        between = true;
                    // specibl cbse for below bll cells
                    } else if (index == getModel().getSize() - 1 && p.y >= rect.y + rect.height) {
                        index++;
                        between = true;
                    } else if (section == LEADING) {
                        between = true;
                    }
                } else {
                    Section section = SwingUtilities2.liesInVerticbl(rect, p, true);
                    if (section == LEADING) {
                        between = true;
                    } else if (section == TRAILING) {
                        index++;
                        between = true;
                    }
                }

                locbtion = new DropLocbtion(p, index, between);

                brebk;
            defbult:
                bssert fblse : "Unexpected drop mode";
        }

        return locbtion;
    }

    /**
     * Cblled to set or clebr the drop locbtion during b DnD operbtion.
     * In some cbses, the component mby need to use it's internbl selection
     * temporbrily to indicbte the drop locbtion. To help fbcilitbte this,
     * this method returns bnd bccepts bs b pbrbmeter b stbte object.
     * This stbte object cbn be used to store, bnd lbter restore, the selection
     * stbte. Whbtever this method returns will be pbssed bbck to it in
     * future cblls, bs the stbte pbrbmeter. If it wbnts the DnD system to
     * continue storing the sbme stbte, it must pbss it bbck every time.
     * Here's how this is used:
     * <p>
     * Let's sby thbt on the first cbll to this method the component decides
     * to sbve some stbte (becbuse it is bbout to use the selection to show
     * b drop index). It cbn return b stbte object to the cbller encbpsulbting
     * bny sbved selection stbte. On b second cbll, let's sby the drop locbtion
     * is being chbnged to something else. The component doesn't need to
     * restore bnything yet, so it simply pbsses bbck the sbme stbte object
     * to hbve the DnD system continue storing it. Finblly, let's sby this
     * method is messbged with <code>null</code>. This mebns DnD
     * is finished with this component for now, mebning it should restore
     * stbte. At this point, it cbn use the stbte pbrbmeter to restore
     * sbid stbte, bnd of course return <code>null</code> since there's
     * no longer bnything to store.
     *
     * @pbrbm locbtion the drop locbtion (bs cblculbted by
     *        <code>dropLocbtionForPoint</code>) or <code>null</code>
     *        if there's no longer b vblid drop locbtion
     * @pbrbm stbte the stbte object sbved ebrlier for this component,
     *        or <code>null</code>
     * @pbrbm forDrop whether or not the method is being cblled becbuse bn
     *        bctubl drop occurred
     * @return bny sbved stbte for this component, or <code>null</code> if none
     */
    Object setDropLocbtion(TrbnsferHbndler.DropLocbtion locbtion,
                           Object stbte,
                           boolebn forDrop) {

        Object retVbl = null;
        DropLocbtion listLocbtion = (DropLocbtion)locbtion;

        if (dropMode == DropMode.USE_SELECTION) {
            if (listLocbtion == null) {
                if (!forDrop && stbte != null) {
                    setSelectedIndices(((int[][])stbte)[0]);

                    int bnchor = ((int[][])stbte)[1][0];
                    int lebd = ((int[][])stbte)[1][1];

                    SwingUtilities2.setLebdAnchorWithoutSelection(
                            getSelectionModel(), lebd, bnchor);
                }
            } else {
                if (dropLocbtion == null) {
                    int[] inds = getSelectedIndices();
                    retVbl = new int[][] {inds, {getAnchorSelectionIndex(),
                                                 getLebdSelectionIndex()}};
                } else {
                    retVbl = stbte;
                }

                int index = listLocbtion.getIndex();
                if (index == -1) {
                    clebrSelection();
                    getSelectionModel().setAnchorSelectionIndex(-1);
                    getSelectionModel().setLebdSelectionIndex(-1);
                } else {
                    setSelectionIntervbl(index, index);
                }
            }
        }

        DropLocbtion old = dropLocbtion;
        dropLocbtion = listLocbtion;
        firePropertyChbnge("dropLocbtion", old, dropLocbtion);

        return retVbl;
    }

    /**
     * Returns the locbtion thbt this component should visublly indicbte
     * bs the drop locbtion during b DnD operbtion over the component,
     * or {@code null} if no locbtion is to currently be shown.
     * <p>
     * This method is not mebnt for querying the drop locbtion
     * from b {@code TrbnsferHbndler}, bs the drop locbtion is only
     * set bfter the {@code TrbnsferHbndler}'s <code>cbnImport</code>
     * hbs returned bnd hbs bllowed for the locbtion to be shown.
     * <p>
     * When this property chbnges, b property chbnge event with
     * nbme "dropLocbtion" is fired by the component.
     * <p>
     * By defbult, responsibility for listening for chbnges to this property
     * bnd indicbting the drop locbtion visublly lies with the list's
     * {@code ListUI}, which mby pbint it directly bnd/or instbll b cell
     * renderer to do so. Developers wishing to implement custom drop locbtion
     * pbinting bnd/or replbce the defbult cell renderer, mby need to honor
     * this property.
     *
     * @return the drop locbtion
     * @see #setDropMode
     * @see TrbnsferHbndler#cbnImport(TrbnsferHbndler.TrbnsferSupport)
     * @since 1.6
     */
    public finbl DropLocbtion getDropLocbtion() {
        return dropLocbtion;
    }

    /**
     * Returns the next list element whose {@code toString} vblue
     * stbrts with the given prefix.
     *
     * @pbrbm prefix the string to test for b mbtch
     * @pbrbm stbrtIndex the index for stbrting the sebrch
     * @pbrbm bibs the sebrch direction, either
     * Position.Bibs.Forwbrd or Position.Bibs.Bbckwbrd.
     * @return the index of the next list element thbt
     * stbrts with the prefix; otherwise {@code -1}
     * @exception IllegblArgumentException if prefix is {@code null}
     * or stbrtIndex is out of bounds
     * @since 1.4
     */
    public int getNextMbtch(String prefix, int stbrtIndex, Position.Bibs bibs) {
        ListModel<E> model = getModel();
        int mbx = model.getSize();
        if (prefix == null) {
            throw new IllegblArgumentException();
        }
        if (stbrtIndex < 0 || stbrtIndex >= mbx) {
            throw new IllegblArgumentException();
        }
        prefix = prefix.toUpperCbse();

        // stbrt sebrch from the next element bfter the selected element
        int increment = (bibs == Position.Bibs.Forwbrd) ? 1 : -1;
        int index = stbrtIndex;
        do {
            E element = model.getElementAt(index);

            if (element != null) {
                String string;

                if (element instbnceof String) {
                    string = ((String)element).toUpperCbse();
                }
                else {
                    string = element.toString();
                    if (string != null) {
                        string = string.toUpperCbse();
                    }
                }

                if (string != null && string.stbrtsWith(prefix)) {
                    return index;
                }
            }
            index = (index + increment + mbx) % mbx;
        } while (index != stbrtIndex);
        return -1;
    }

    /**
     * Returns the tooltip text to be used for the given event. This overrides
     * {@code JComponent}'s {@code getToolTipText} to first check the cell
     * renderer component for the cell over which the event occurred, returning
     * its tooltip text, if bny. This implementbtion bllows you to specify
     * tooltip text on the cell level, by using {@code setToolTipText} on your
     * cell renderer component.
     * <p>
     * <strong>Note:</strong> For <code>JList</code> to properly displby the
     * tooltips of its renderers in this mbnner, <code>JList</code> must be b
     * registered component with the <code>ToolTipMbnbger</code>. This registrbtion
     * is done butombticblly in the constructor. However, if bt b lbter point
     * <code>JList</code> is unregistered, by wby of b cbll to
     * {@code setToolTipText(null)}, tips from the renderers will no longer displby.
     *
     * @pbrbm event the {@code MouseEvent} to fetch the tooltip text for
     * @see JComponent#setToolTipText
     * @see JComponent#getToolTipText
     */
    public String getToolTipText(MouseEvent event) {
        if(event != null) {
            Point p = event.getPoint();
            int index = locbtionToIndex(p);
            ListCellRenderer<? super E> r = getCellRenderer();
            Rectbngle cellBounds;

            if (index != -1 && r != null && (cellBounds =
                               getCellBounds(index, index)) != null &&
                               cellBounds.contbins(p.x, p.y)) {
                ListSelectionModel lsm = getSelectionModel();
                Component rComponent = r.getListCellRendererComponent(
                           this, getModel().getElementAt(index), index,
                           lsm.isSelectedIndex(index),
                           (hbsFocus() && (lsm.getLebdSelectionIndex() ==
                                           index)));

                if(rComponent instbnceof JComponent) {
                    MouseEvent      newEvent;

                    p.trbnslbte(-cellBounds.x, -cellBounds.y);
                    newEvent = new MouseEvent(rComponent, event.getID(),
                                              event.getWhen(),
                                              event.getModifiers(),
                                              p.x, p.y,
                                              event.getXOnScreen(),
                                              event.getYOnScreen(),
                                              event.getClickCount(),
                                              event.isPopupTrigger(),
                                              MouseEvent.NOBUTTON);

                    String tip = ((JComponent)rComponent).getToolTipText(
                                              newEvent);

                    if (tip != null) {
                        return tip;
                    }
                }
            }
        }
        return super.getToolTipText();
    }

    /**
     * --- ListUI Delegbtions ---
     */


    /**
     * Returns the cell index closest to the given locbtion in the list's
     * coordinbte system. To determine if the cell bctublly contbins the
     * specified locbtion, compbre the point bgbinst the cell's bounds,
     * bs provided by {@code getCellBounds}. This method returns {@code -1}
     * if the model is empty
     * <p>
     * This is b cover method thbt delegbtes to the method of the sbme nbme
     * in the list's {@code ListUI}. It returns {@code -1} if the list hbs
     * no {@code ListUI}.
     *
     * @pbrbm locbtion the coordinbtes of the point
     * @return the cell index closest to the given locbtion, or {@code -1}
     */
    public int locbtionToIndex(Point locbtion) {
        ListUI ui = getUI();
        return (ui != null) ? ui.locbtionToIndex(this, locbtion) : -1;
    }


    /**
     * Returns the origin of the specified item in the list's coordinbte
     * system. This method returns {@code null} if the index isn't vblid.
     * <p>
     * This is b cover method thbt delegbtes to the method of the sbme nbme
     * in the list's {@code ListUI}. It returns {@code null} if the list hbs
     * no {@code ListUI}.
     *
     * @pbrbm index the cell index
     * @return the origin of the cell, or {@code null}
     */
    public Point indexToLocbtion(int index) {
        ListUI ui = getUI();
        return (ui != null) ? ui.indexToLocbtion(this, index) : null;
    }


    /**
     * Returns the bounding rectbngle, in the list's coordinbte system,
     * for the rbnge of cells specified by the two indices.
     * These indices cbn be supplied in bny order.
     * <p>
     * If the smbller index is outside the list's rbnge of cells, this method
     * returns {@code null}. If the smbller index is vblid, but the lbrger
     * index is outside the list's rbnge, the bounds of just the first index
     * is returned. Otherwise, the bounds of the vblid rbnge is returned.
     * <p>
     * This is b cover method thbt delegbtes to the method of the sbme nbme
     * in the list's {@code ListUI}. It returns {@code null} if the list hbs
     * no {@code ListUI}.
     *
     * @pbrbm index0 the first index in the rbnge
     * @pbrbm index1 the second index in the rbnge
     * @return the bounding rectbngle for the rbnge of cells, or {@code null}
     */
    public Rectbngle getCellBounds(int index0, int index1) {
        ListUI ui = getUI();
        return (ui != null) ? ui.getCellBounds(this, index0, index1) : null;
    }


    /**
     * --- ListModel Support ---
     */


    /**
     * Returns the dbtb model thbt holds the list of items displbyed
     * by the <code>JList</code> component.
     *
     * @return the <code>ListModel</code> thbt provides the displbyed
     *                          list of items
     * @see #setModel
     */
    public ListModel<E> getModel() {
        return dbtbModel;
    }

    /**
     * Sets the model thbt represents the contents or "vblue" of the
     * list, notifies property chbnge listeners, bnd then clebrs the
     * list's selection.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm model  the <code>ListModel</code> thbt provides the
     *                                          list of items for displby
     * @exception IllegblArgumentException  if <code>model</code> is
     *                                          <code>null</code>
     * @see #getModel
     * @see #clebrSelection
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: The object thbt contbins the dbtb to be drbwn by this JList.
     */
    public void setModel(ListModel<E> model) {
        if (model == null) {
            throw new IllegblArgumentException("model must be non null");
        }
        ListModel<E> oldVblue = dbtbModel;
        dbtbModel = model;
        firePropertyChbnge("model", oldVblue, dbtbModel);
        clebrSelection();
    }


    /**
     * Constructs b rebd-only <code>ListModel</code> from bn brrby of items,
     * bnd cblls {@code setModel} with this model.
     * <p>
     * Attempts to pbss b {@code null} vblue to this method results in
     * undefined behbvior bnd, most likely, exceptions. The crebted model
     * references the given brrby directly. Attempts to modify the brrby
     * bfter invoking this method results in undefined behbvior.
     *
     * @pbrbm listDbtb bn brrby of {@code E} contbining the items to
     *        displby in the list
     * @see #setModel
     */
    public void setListDbtb(finbl E[] listDbtb) {
        setModel (
            new AbstrbctListModel<E>() {
                public int getSize() { return listDbtb.length; }
                public E getElementAt(int i) { return listDbtb[i]; }
            }
        );
    }


    /**
     * Constructs b rebd-only <code>ListModel</code> from b <code>Vector</code>
     * bnd cblls {@code setModel} with this model.
     * <p>
     * Attempts to pbss b {@code null} vblue to this method results in
     * undefined behbvior bnd, most likely, exceptions. The crebted model
     * references the given {@code Vector} directly. Attempts to modify the
     * {@code Vector} bfter invoking this method results in undefined behbvior.
     *
     * @pbrbm listDbtb b <code>Vector</code> contbining the items to
     *                                          displby in the list
     * @see #setModel
     */
    public void setListDbtb(finbl Vector<? extends E> listDbtb) {
        setModel (
            new AbstrbctListModel<E>() {
                public int getSize() { return listDbtb.size(); }
                public E getElementAt(int i) { return listDbtb.elementAt(i); }
            }
        );
    }


    /**
     * --- ListSelectionModel delegbtions bnd extensions ---
     */


    /**
     * Returns bn instbnce of {@code DefbultListSelectionModel}; cblled
     * during construction to initiblize the list's selection model
     * property.
     *
     * @return b {@code DefbultListSelecitonModel}, used to initiblize
     *         the list's selection model property during construction
     * @see #setSelectionModel
     * @see DefbultListSelectionModel
     */
    protected ListSelectionModel crebteSelectionModel() {
        return new DefbultListSelectionModel();
    }


    /**
     * Returns the current selection model. The selection model mbintbins the
     * selection stbte of the list. See the clbss level documentbtion for more
     * detbils.
     *
     * @return the <code>ListSelectionModel</code> thbt mbintbins the
     *         list's selections
     *
     * @see #setSelectionModel
     * @see ListSelectionModel
     */
    public ListSelectionModel getSelectionModel() {
        return selectionModel;
    }


    /**
     * Notifies {@code ListSelectionListener}s bdded directly to the list
     * of selection chbnges mbde to the selection model. {@code JList}
     * listens for chbnges mbde to the selection in the selection model,
     * bnd forwbrds notificbtion to listeners bdded to the list directly,
     * by cblling this method.
     * <p>
     * This method constructs b {@code ListSelectionEvent} with this list
     * bs the source, bnd the specified brguments, bnd sends it to the
     * registered {@code ListSelectionListeners}.
     *
     * @pbrbm firstIndex the first index in the rbnge, {@code <= lbstIndex}
     * @pbrbm lbstIndex the lbst index in the rbnge, {@code >= firstIndex}
     * @pbrbm isAdjusting whether or not this is one in b series of
     *        multiple events, where chbnges bre still being mbde
     *
     * @see #bddListSelectionListener
     * @see #removeListSelectionListener
     * @see jbvbx.swing.event.ListSelectionEvent
     * @see EventListenerList
     */
    protected void fireSelectionVblueChbnged(int firstIndex, int lbstIndex,
                                             boolebn isAdjusting)
    {
        Object[] listeners = listenerList.getListenerList();
        ListSelectionEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListSelectionListener.clbss) {
                if (e == null) {
                    e = new ListSelectionEvent(this, firstIndex, lbstIndex,
                                               isAdjusting);
                }
                ((ListSelectionListener)listeners[i+1]).vblueChbnged(e);
            }
        }
    }


    /* A ListSelectionListener thbt forwbrds ListSelectionEvents from
     * the selectionModel to the JList ListSelectionListeners.  The
     * forwbrded events only differ from the originbls in thbt their
     * source is the JList instebd of the selectionModel itself.
     */
    privbte clbss ListSelectionHbndler implements ListSelectionListener, Seriblizbble
    {
        public void vblueChbnged(ListSelectionEvent e) {
            fireSelectionVblueChbnged(e.getFirstIndex(),
                                      e.getLbstIndex(),
                                      e.getVblueIsAdjusting());
        }
    }


    /**
     * Adds b listener to the list, to be notified ebch time b chbnge to the
     * selection occurs; the preferred wby of listening for selection stbte
     * chbnges. {@code JList} tbkes cbre of listening for selection stbte
     * chbnges in the selection model, bnd notifies the given listener of
     * ebch chbnge. {@code ListSelectionEvent}s sent to the listener hbve b
     * {@code source} property set to this list.
     *
     * @pbrbm listener the {@code ListSelectionListener} to bdd
     * @see #getSelectionModel
     * @see #getListSelectionListeners
     */
    public void bddListSelectionListener(ListSelectionListener listener)
    {
        if (selectionListener == null) {
            selectionListener = new ListSelectionHbndler();
            getSelectionModel().bddListSelectionListener(selectionListener);
        }

        listenerList.bdd(ListSelectionListener.clbss, listener);
    }


    /**
     * Removes b selection listener from the list.
     *
     * @pbrbm listener the {@code ListSelectionListener} to remove
     * @see #bddListSelectionListener
     * @see #getSelectionModel
     */
    public void removeListSelectionListener(ListSelectionListener listener) {
        listenerList.remove(ListSelectionListener.clbss, listener);
    }


    /**
     * Returns bn brrby of bll the {@code ListSelectionListener}s bdded
     * to this {@code JList} by wby of {@code bddListSelectionListener}.
     *
     * @return bll of the {@code ListSelectionListener}s on this list, or
     *         bn empty brrby if no listeners hbve been bdded
     * @see #bddListSelectionListener
     * @since 1.4
     */
    public ListSelectionListener[] getListSelectionListeners() {
        return listenerList.getListeners(ListSelectionListener.clbss);
    }


    /**
     * Sets the <code>selectionModel</code> for the list to b
     * non-<code>null</code> <code>ListSelectionModel</code>
     * implementbtion. The selection model hbndles the tbsk of mbking single
     * selections, selections of contiguous rbnges, bnd non-contiguous
     * selections.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm selectionModel  the <code>ListSelectionModel</code> thbt
     *                          implements the selections
     * @exception IllegblArgumentException   if <code>selectionModel</code>
     *                                          is <code>null</code>
     * @see #getSelectionModel
     * @bebninfo
     *       bound: true
     * description: The selection model, recording which cells bre selected.
     */
    public void setSelectionModel(ListSelectionModel selectionModel) {
        if (selectionModel == null) {
            throw new IllegblArgumentException("selectionModel must be non null");
        }

        /* Remove the forwbrding ListSelectionListener from the old
         * selectionModel, bnd bdd it to the new one, if necessbry.
         */
        if (selectionListener != null) {
            this.selectionModel.removeListSelectionListener(selectionListener);
            selectionModel.bddListSelectionListener(selectionListener);
        }

        ListSelectionModel oldVblue = this.selectionModel;
        this.selectionModel = selectionModel;
        firePropertyChbnge("selectionModel", oldVblue, selectionModel);
    }


    /**
     * Sets the selection mode for the list. This is b cover method thbt sets
     * the selection mode directly on the selection model.
     * <p>
     * The following list describes the bccepted selection modes:
     * <ul>
     * <li>{@code ListSelectionModel.SINGLE_SELECTION} -
     *   Only one list index cbn be selected bt b time. In this mode,
     *   {@code setSelectionIntervbl} bnd {@code bddSelectionIntervbl} bre
     *   equivblent, both replbcing the current selection with the index
     *   represented by the second brgument (the "lebd").
     * <li>{@code ListSelectionModel.SINGLE_INTERVAL_SELECTION} -
     *   Only one contiguous intervbl cbn be selected bt b time.
     *   In this mode, {@code bddSelectionIntervbl} behbves like
     *   {@code setSelectionIntervbl} (replbcing the current selection},
     *   unless the given intervbl is immedibtely bdjbcent to or overlbps
     *   the existing selection, bnd cbn be used to grow the selection.
     * <li>{@code ListSelectionModel.MULTIPLE_INTERVAL_SELECTION} -
     *   In this mode, there's no restriction on whbt cbn be selected.
     *   This mode is the defbult.
     * </ul>
     *
     * @pbrbm selectionMode the selection mode
     * @see #getSelectionMode
     * @throws IllegblArgumentException if the selection mode isn't
     *         one of those bllowed
     * @bebninfo
     * description: The selection mode.
     *        enum: SINGLE_SELECTION            ListSelectionModel.SINGLE_SELECTION
     *              SINGLE_INTERVAL_SELECTION   ListSelectionModel.SINGLE_INTERVAL_SELECTION
     *              MULTIPLE_INTERVAL_SELECTION ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
     */
    public void setSelectionMode(int selectionMode) {
        getSelectionModel().setSelectionMode(selectionMode);
    }

    /**
     * Returns the current selection mode for the list. This is b cover
     * method thbt delegbtes to the method of the sbme nbme on the
     * list's selection model.
     *
     * @return the current selection mode
     * @see #setSelectionMode
     */
    public int getSelectionMode() {
        return getSelectionModel().getSelectionMode();
    }


    /**
     * Returns the bnchor selection index. This is b cover method thbt
     * delegbtes to the method of the sbme nbme on the list's selection model.
     *
     * @return the bnchor selection index
     * @see ListSelectionModel#getAnchorSelectionIndex
     */
    public int getAnchorSelectionIndex() {
        return getSelectionModel().getAnchorSelectionIndex();
    }


    /**
     * Returns the lebd selection index. This is b cover method thbt
     * delegbtes to the method of the sbme nbme on the list's selection model.
     *
     * @return the lebd selection index
     * @see ListSelectionModel#getLebdSelectionIndex
     * @bebninfo
     * description: The lebd selection index.
     */
    public int getLebdSelectionIndex() {
        return getSelectionModel().getLebdSelectionIndex();
    }


    /**
     * Returns the smbllest selected cell index, or {@code -1} if the selection
     * is empty. This is b cover method thbt delegbtes to the method of the sbme
     * nbme on the list's selection model.
     *
     * @return the smbllest selected cell index, or {@code -1}
     * @see ListSelectionModel#getMinSelectionIndex
     */
    public int getMinSelectionIndex() {
        return getSelectionModel().getMinSelectionIndex();
    }


    /**
     * Returns the lbrgest selected cell index, or {@code -1} if the selection
     * is empty. This is b cover method thbt delegbtes to the method of the sbme
     * nbme on the list's selection model.
     *
     * @return the lbrgest selected cell index
     * @see ListSelectionModel#getMbxSelectionIndex
     */
    public int getMbxSelectionIndex() {
        return getSelectionModel().getMbxSelectionIndex();
    }


    /**
     * Returns {@code true} if the specified index is selected,
     * else {@code fblse}. This is b cover method thbt delegbtes to the method
     * of the sbme nbme on the list's selection model.
     *
     * @pbrbm index index to be queried for selection stbte
     * @return {@code true} if the specified index is selected,
     *         else {@code fblse}
     * @see ListSelectionModel#isSelectedIndex
     * @see #setSelectedIndex
     */
    public boolebn isSelectedIndex(int index) {
        return getSelectionModel().isSelectedIndex(index);
    }


    /**
     * Returns {@code true} if nothing is selected, else {@code fblse}.
     * This is b cover method thbt delegbtes to the method of the sbme
     * nbme on the list's selection model.
     *
     * @return {@code true} if nothing is selected, else {@code fblse}
     * @see ListSelectionModel#isSelectionEmpty
     * @see #clebrSelection
     */
    public boolebn isSelectionEmpty() {
        return getSelectionModel().isSelectionEmpty();
    }


    /**
     * Clebrs the selection; bfter cblling this method, {@code isSelectionEmpty}
     * will return {@code true}. This is b cover method thbt delegbtes to the
     * method of the sbme nbme on the list's selection model.
     *
     * @see ListSelectionModel#clebrSelection
     * @see #isSelectionEmpty
     */
    public void clebrSelection() {
        getSelectionModel().clebrSelection();
    }


    /**
     * Selects the specified intervbl. Both {@code bnchor} bnd {@code lebd}
     * indices bre included. {@code bnchor} doesn't hbve to be less thbn or
     * equbl to {@code lebd}. This is b cover method thbt delegbtes to the
     * method of the sbme nbme on the list's selection model.
     * <p>
     * Refer to the documentbtion of the selection model clbss being used
     * for detbils on how vblues less thbn {@code 0} bre hbndled.
     *
     * @pbrbm bnchor the first index to select
     * @pbrbm lebd the lbst index to select
     * @see ListSelectionModel#setSelectionIntervbl
     * @see DefbultListSelectionModel#setSelectionIntervbl
     * @see #crebteSelectionModel
     * @see #bddSelectionIntervbl
     * @see #removeSelectionIntervbl
     */
    public void setSelectionIntervbl(int bnchor, int lebd) {
        getSelectionModel().setSelectionIntervbl(bnchor, lebd);
    }


    /**
     * Sets the selection to be the union of the specified intervbl with current
     * selection. Both the {@code bnchor} bnd {@code lebd} indices bre
     * included. {@code bnchor} doesn't hbve to be less thbn or
     * equbl to {@code lebd}. This is b cover method thbt delegbtes to the
     * method of the sbme nbme on the list's selection model.
     * <p>
     * Refer to the documentbtion of the selection model clbss being used
     * for detbils on how vblues less thbn {@code 0} bre hbndled.
     *
     * @pbrbm bnchor the first index to bdd to the selection
     * @pbrbm lebd the lbst index to bdd to the selection
     * @see ListSelectionModel#bddSelectionIntervbl
     * @see DefbultListSelectionModel#bddSelectionIntervbl
     * @see #crebteSelectionModel
     * @see #setSelectionIntervbl
     * @see #removeSelectionIntervbl
     */
    public void bddSelectionIntervbl(int bnchor, int lebd) {
        getSelectionModel().bddSelectionIntervbl(bnchor, lebd);
    }


    /**
     * Sets the selection to be the set difference of the specified intervbl
     * bnd the current selection. Both the {@code index0} bnd {@code index1}
     * indices bre removed. {@code index0} doesn't hbve to be less thbn or
     * equbl to {@code index1}. This is b cover method thbt delegbtes to the
     * method of the sbme nbme on the list's selection model.
     * <p>
     * Refer to the documentbtion of the selection model clbss being used
     * for detbils on how vblues less thbn {@code 0} bre hbndled.
     *
     * @pbrbm index0 the first index to remove from the selection
     * @pbrbm index1 the lbst index to remove from the selection
     * @see ListSelectionModel#removeSelectionIntervbl
     * @see DefbultListSelectionModel#removeSelectionIntervbl
     * @see #crebteSelectionModel
     * @see #setSelectionIntervbl
     * @see #bddSelectionIntervbl
     */
    public void removeSelectionIntervbl(int index0, int index1) {
        getSelectionModel().removeSelectionIntervbl(index0, index1);
    }


    /**
     * Sets the selection model's {@code vblueIsAdjusting} property. When
     * {@code true}, upcoming chbnges to selection should be considered pbrt
     * of b single chbnge. This property is used internblly bnd developers
     * typicblly need not cbll this method. For exbmple, when the model is being
     * updbted in response to b user drbg, the vblue of the property is set
     * to {@code true} when the drbg is initibted bnd set to {@code fblse}
     * when the drbg is finished. This bllows listeners to updbte only
     * when b chbnge hbs been finblized, rbther thbn hbndling bll of the
     * intermedibte vblues.
     * <p>
     * You mby wbnt to use this directly if mbking b series of chbnges
     * thbt should be considered pbrt of b single chbnge.
     * <p>
     * This is b cover method thbt delegbtes to the method of the sbme nbme on
     * the list's selection model. See the documentbtion for
     * {@link jbvbx.swing.ListSelectionModel#setVblueIsAdjusting} for
     * more detbils.
     *
     * @pbrbm b the new vblue for the property
     * @see ListSelectionModel#setVblueIsAdjusting
     * @see jbvbx.swing.event.ListSelectionEvent#getVblueIsAdjusting
     * @see #getVblueIsAdjusting
     */
    public void setVblueIsAdjusting(boolebn b) {
        getSelectionModel().setVblueIsAdjusting(b);
    }


    /**
     * Returns the vblue of the selection model's {@code isAdjusting} property.
     * <p>
     * This is b cover method thbt delegbtes to the method of the sbme nbme on
     * the list's selection model.
     *
     * @return the vblue of the selection model's {@code isAdjusting} property.
     *
     * @see #setVblueIsAdjusting
     * @see ListSelectionModel#getVblueIsAdjusting
     */
    public boolebn getVblueIsAdjusting() {
        return getSelectionModel().getVblueIsAdjusting();
    }


    /**
     * Returns bn brrby of bll of the selected indices, in increbsing
     * order.
     *
     * @return bll of the selected indices, in increbsing order,
     *         or bn empty brrby if nothing is selected
     * @see #removeSelectionIntervbl
     * @see #bddListSelectionListener
     */
    @Trbnsient
    public int[] getSelectedIndices() {
        ListSelectionModel sm = getSelectionModel();
        int iMin = sm.getMinSelectionIndex();
        int iMbx = sm.getMbxSelectionIndex();

        if ((iMin < 0) || (iMbx < 0)) {
            return new int[0];
        }

        int[] rvTmp = new int[1+ (iMbx - iMin)];
        int n = 0;
        for(int i = iMin; i <= iMbx; i++) {
            if (sm.isSelectedIndex(i)) {
                rvTmp[n++] = i;
            }
        }
        int[] rv = new int[n];
        System.brrbycopy(rvTmp, 0, rv, 0, n);
        return rv;
    }


    /**
     * Selects b single cell. Does nothing if the given index is grebter
     * thbn or equbl to the model size. This is b convenience method thbt uses
     * {@code setSelectionIntervbl} on the selection model. Refer to the
     * documentbtion for the selection model clbss being used for detbils on
     * how vblues less thbn {@code 0} bre hbndled.
     *
     * @pbrbm index the index of the cell to select
     * @see ListSelectionModel#setSelectionIntervbl
     * @see #isSelectedIndex
     * @see #bddListSelectionListener
     * @bebninfo
     * description: The index of the selected cell.
     */
    public void setSelectedIndex(int index) {
        if (index >= getModel().getSize()) {
            return;
        }
        getSelectionModel().setSelectionIntervbl(index, index);
    }


    /**
     * Chbnges the selection to be the set of indices specified by the given
     * brrby. Indices grebter thbn or equbl to the model size bre ignored.
     * This is b convenience method thbt clebrs the selection bnd then uses
     * {@code bddSelectionIntervbl} on the selection model to bdd the indices.
     * Refer to the documentbtion of the selection model clbss being used for
     * detbils on how vblues less thbn {@code 0} bre hbndled.
     *
     * @pbrbm indices bn brrby of the indices of the cells to select,
     *                {@code non-null}
     * @see ListSelectionModel#bddSelectionIntervbl
     * @see #isSelectedIndex
     * @see #bddListSelectionListener
     * @throws NullPointerException if the given brrby is {@code null}
     */
    public void setSelectedIndices(int[] indices) {
        ListSelectionModel sm = getSelectionModel();
        sm.clebrSelection();
        int size = getModel().getSize();
        for (int i : indices) {
            if (i < size) {
                sm.bddSelectionIntervbl(i, i);
            }
        }
    }


    /**
     * Returns bn brrby of bll the selected vblues, in increbsing order bbsed
     * on their indices in the list.
     *
     * @return the selected vblues, or bn empty brrby if nothing is selected
     * @see #isSelectedIndex
     * @see #getModel
     * @see #bddListSelectionListener
     *
     * @deprecbted As of JDK 1.7, replbced by {@link #getSelectedVbluesList()}
     */
    @Deprecbted
    public Object[] getSelectedVblues() {
        ListSelectionModel sm = getSelectionModel();
        ListModel<E> dm = getModel();

        int iMin = sm.getMinSelectionIndex();
        int iMbx = sm.getMbxSelectionIndex();

        if ((iMin < 0) || (iMbx < 0)) {
            return new Object[0];
        }

        Object[] rvTmp = new Object[1+ (iMbx - iMin)];
        int n = 0;
        for(int i = iMin; i <= iMbx; i++) {
            if (sm.isSelectedIndex(i)) {
                rvTmp[n++] = dm.getElementAt(i);
            }
        }
        Object[] rv = new Object[n];
        System.brrbycopy(rvTmp, 0, rv, 0, n);
        return rv;
    }

    /**
     * Returns b list of bll the selected items, in increbsing order bbsed
     * on their indices in the list.
     *
     * @return the selected items, or bn empty list if nothing is selected
     * @see #isSelectedIndex
     * @see #getModel
     * @see #bddListSelectionListener
     *
     * @since 1.7
     */
    public List<E> getSelectedVbluesList() {
        ListSelectionModel sm = getSelectionModel();
        ListModel<E> dm = getModel();

        int iMin = sm.getMinSelectionIndex();
        int iMbx = sm.getMbxSelectionIndex();

        if ((iMin < 0) || (iMbx < 0)) {
            return Collections.emptyList();
        }

        List<E> selectedItems = new ArrbyList<E>();
        for(int i = iMin; i <= iMbx; i++) {
            if (sm.isSelectedIndex(i)) {
                selectedItems.bdd(dm.getElementAt(i));
            }
        }
        return selectedItems;
    }


    /**
     * Returns the smbllest selected cell index; <i>the selection</i> when only
     * b single item is selected in the list. When multiple items bre selected,
     * it is simply the smbllest selected index. Returns {@code -1} if there is
     * no selection.
     * <p>
     * This method is b cover thbt delegbtes to {@code getMinSelectionIndex}.
     *
     * @return the smbllest selected cell index
     * @see #getMinSelectionIndex
     * @see #bddListSelectionListener
     */
    public int getSelectedIndex() {
        return getMinSelectionIndex();
    }


    /**
     * Returns the vblue for the smbllest selected cell index;
     * <i>the selected vblue</i> when only b single item is selected in the
     * list. When multiple items bre selected, it is simply the vblue for the
     * smbllest selected index. Returns {@code null} if there is no selection.
     * <p>
     * This is b convenience method thbt simply returns the model vblue for
     * {@code getMinSelectionIndex}.
     *
     * @return the first selected vblue
     * @see #getMinSelectionIndex
     * @see #getModel
     * @see #bddListSelectionListener
     */
    public E getSelectedVblue() {
        int i = getMinSelectionIndex();
        return (i == -1) ? null : getModel().getElementAt(i);
    }


    /**
     * Selects the specified object from the list.
     *
     * @pbrbm bnObject      the object to select
     * @pbrbm shouldScroll  {@code true} if the list should scroll to displby
     *                      the selected object, if one exists; otherwise {@code fblse}
     */
    public void setSelectedVblue(Object bnObject,boolebn shouldScroll) {
        if(bnObject == null)
            setSelectedIndex(-1);
        else if(!bnObject.equbls(getSelectedVblue())) {
            int i,c;
            ListModel<E> dm = getModel();
            for(i=0,c=dm.getSize();i<c;i++)
                if(bnObject.equbls(dm.getElementAt(i))){
                    setSelectedIndex(i);
                    if(shouldScroll)
                        ensureIndexIsVisible(i);
                    repbint();  /** FIX-ME setSelectedIndex does not redrbw bll the time with the bbsic l&f**/
                    return;
                }
            setSelectedIndex(-1);
        }
        repbint(); /** FIX-ME setSelectedIndex does not redrbw bll the time with the bbsic l&f**/
    }



    /**
     * --- The Scrollbble Implementbtion ---
     */

    privbte void checkScrollbblePbrbmeters(Rectbngle visibleRect, int orientbtion) {
        if (visibleRect == null) {
            throw new IllegblArgumentException("visibleRect must be non-null");
        }
        switch (orientbtion) {
        cbse SwingConstbnts.VERTICAL:
        cbse SwingConstbnts.HORIZONTAL:
            brebk;
        defbult:
            throw new IllegblArgumentException("orientbtion must be one of: VERTICAL, HORIZONTAL");
        }
    }


    /**
     * Computes the size of viewport needed to displby {@code visibleRowCount}
     * rows. The vblue returned by this method depends on the lbyout
     * orientbtion:
     * <p>
     * <b>{@code VERTICAL}:</b>
     * <br>
     * This is trivibl if both {@code fixedCellWidth} bnd {@code fixedCellHeight}
     * hbve been set (either explicitly or by specifying b prototype cell vblue).
     * The width is simply the {@code fixedCellWidth} plus the list's horizontbl
     * insets. The height is the {@code fixedCellHeight} multiplied by the
     * {@code visibleRowCount}, plus the list's verticbl insets.
     * <p>
     * If either {@code fixedCellWidth} or {@code fixedCellHeight} hbven't been
     * specified, heuristics bre used. If the model is empty, the width is
     * the {@code fixedCellWidth}, if grebter thbn {@code 0}, or b hbrd-coded
     * vblue of {@code 256}. The height is the {@code fixedCellHeight} multiplied
     * by {@code visibleRowCount}, if {@code fixedCellHeight} is grebter thbn
     * {@code 0}, otherwise it is b hbrd-coded vblue of {@code 16} multiplied by
     * {@code visibleRowCount}.
     * <p>
     * If the model isn't empty, the width is the preferred size's width,
     * typicblly the width of the widest list element. The height is the
     * {@code fixedCellHeight} multiplied by the {@code visibleRowCount},
     * plus the list's verticbl insets.
     * <p>
     * <b>{@code VERTICAL_WRAP} or {@code HORIZONTAL_WRAP}:</b>
     * <br>
     * This method simply returns the vblue from {@code getPreferredSize}.
     * The list's {@code ListUI} is expected to override {@code getPreferredSize}
     * to return bn bppropribte vblue.
     *
     * @return b dimension contbining the size of the viewport needed
     *          to displby {@code visibleRowCount} rows
     * @see #getPreferredScrollbbleViewportSize
     * @see #setPrototypeCellVblue
     */
    public Dimension getPreferredScrollbbleViewportSize()
    {
        if (getLbyoutOrientbtion() != VERTICAL) {
            return getPreferredSize();
        }
        Insets insets = getInsets();
        int dx = insets.left + insets.right;
        int dy = insets.top + insets.bottom;

        int visibleRowCount = getVisibleRowCount();
        int fixedCellWidth = getFixedCellWidth();
        int fixedCellHeight = getFixedCellHeight();

        if ((fixedCellWidth > 0) && (fixedCellHeight > 0)) {
            int width = fixedCellWidth + dx;
            int height = (visibleRowCount * fixedCellHeight) + dy;
            return new Dimension(width, height);
        }
        else if (getModel().getSize() > 0) {
            int width = getPreferredSize().width;
            int height;
            Rectbngle r = getCellBounds(0, 0);
            if (r != null) {
                height = (visibleRowCount * r.height) + dy;
            }
            else {
                // Will only hbppen if UI null, shouldn't mbtter whbt we return
                height = 1;
            }
            return new Dimension(width, height);
        }
        else {
            fixedCellWidth = (fixedCellWidth > 0) ? fixedCellWidth : 256;
            fixedCellHeight = (fixedCellHeight > 0) ? fixedCellHeight : 16;
            return new Dimension(fixedCellWidth, fixedCellHeight * visibleRowCount);
        }
    }


    /**
     * Returns the distbnce to scroll to expose the next or previous
     * row (for verticbl scrolling) or column (for horizontbl scrolling).
     * <p>
     * For horizontbl scrolling, if the lbyout orientbtion is {@code VERTICAL},
     * then the list's font size is returned (or {@code 1} if the font is
     * {@code null}).
     *
     * @pbrbm visibleRect the view breb visible within the viewport
     * @pbrbm orientbtion {@code SwingConstbnts.HORIZONTAL} or
     *                    {@code SwingConstbnts.VERTICAL}
     * @pbrbm direction less or equbl to zero to scroll up/bbck,
     *                  grebter thbn zero for down/forwbrd
     * @return the "unit" increment for scrolling in the specified direction;
     *         blwbys positive
     * @see #getScrollbbleBlockIncrement
     * @see Scrollbble#getScrollbbleUnitIncrement
     * @throws IllegblArgumentException if {@code visibleRect} is {@code null}, or
     *         {@code orientbtion} isn't one of {@code SwingConstbnts.VERTICAL} or
     *         {@code SwingConstbnts.HORIZONTAL}
     */
    public int getScrollbbleUnitIncrement(Rectbngle visibleRect, int orientbtion, int direction)
    {
        checkScrollbblePbrbmeters(visibleRect, orientbtion);

        if (orientbtion == SwingConstbnts.VERTICAL) {
            int row = locbtionToIndex(visibleRect.getLocbtion());

            if (row == -1) {
                return 0;
            }
            else {
                /* Scroll Down */
                if (direction > 0) {
                    Rectbngle r = getCellBounds(row, row);
                    return (r == null) ? 0 : r.height - (visibleRect.y - r.y);
                }
                /* Scroll Up */
                else {
                    Rectbngle r = getCellBounds(row, row);

                    /* The first row is completely visible bnd it's row 0.
                     * We're done.
                     */
                    if ((r.y == visibleRect.y) && (row == 0))  {
                        return 0;
                    }
                    /* The first row is completely visible, return the
                     * height of the previous row or 0 if the first row
                     * is the top row of the list.
                     */
                    else if (r.y == visibleRect.y) {
                        Point loc = r.getLocbtion();
                        loc.y--;
                        int prevIndex = locbtionToIndex(loc);
                        Rectbngle prevR = getCellBounds(prevIndex, prevIndex);

                        if (prevR == null || prevR.y >= r.y) {
                            return 0;
                        }
                        return prevR.height;
                    }
                    /* The first row is pbrtiblly visible, return the
                     * height of hidden pbrt.
                     */
                    else {
                        return visibleRect.y - r.y;
                    }
                }
            }
        } else if (orientbtion == SwingConstbnts.HORIZONTAL &&
                           getLbyoutOrientbtion() != JList.VERTICAL) {
            boolebn leftToRight = getComponentOrientbtion().isLeftToRight();
            int index;
            Point lebdingPoint;

            if (leftToRight) {
                lebdingPoint = visibleRect.getLocbtion();
            }
            else {
                lebdingPoint = new Point(visibleRect.x + visibleRect.width -1,
                                         visibleRect.y);
            }
            index = locbtionToIndex(lebdingPoint);

            if (index != -1) {
                Rectbngle cellBounds = getCellBounds(index, index);
                if (cellBounds != null && cellBounds.contbins(lebdingPoint)) {
                    int lebdingVisibleEdge;
                    int lebdingCellEdge;

                    if (leftToRight) {
                        lebdingVisibleEdge = visibleRect.x;
                        lebdingCellEdge = cellBounds.x;
                    }
                    else {
                        lebdingVisibleEdge = visibleRect.x + visibleRect.width;
                        lebdingCellEdge = cellBounds.x + cellBounds.width;
                    }

                    if (lebdingCellEdge != lebdingVisibleEdge) {
                        if (direction < 0) {
                            // Show rembinder of lebding cell
                            return Mbth.bbs(lebdingVisibleEdge - lebdingCellEdge);

                        }
                        else if (leftToRight) {
                            // Hide rest of lebding cell
                            return lebdingCellEdge + cellBounds.width - lebdingVisibleEdge;
                        }
                        else {
                            // Hide rest of lebding cell
                            return lebdingVisibleEdge - cellBounds.x;
                        }
                    }
                    // ASSUME: All cells bre the sbme width
                    return cellBounds.width;
                }
            }
        }
        Font f = getFont();
        return (f != null) ? f.getSize() : 1;
    }


    /**
     * Returns the distbnce to scroll to expose the next or previous block.
     * <p>
     * For verticbl scrolling, the following rules bre used:
     * <ul>
     * <li>if scrolling down, returns the distbnce to scroll so thbt the lbst
     * visible element becomes the first completely visible element
     * <li>if scrolling up, returns the distbnce to scroll so thbt the first
     * visible element becomes the lbst completely visible element
     * <li>returns {@code visibleRect.height} if the list is empty
     * </ul>
     * <p>
     * For horizontbl scrolling, when the lbyout orientbtion is either
     * {@code VERTICAL_WRAP} or {@code HORIZONTAL_WRAP}:
     * <ul>
     * <li>if scrolling right, returns the distbnce to scroll so thbt the
     * lbst visible element becomes
     * the first completely visible element
     * <li>if scrolling left, returns the distbnce to scroll so thbt the first
     * visible element becomes the lbst completely visible element
     * <li>returns {@code visibleRect.width} if the list is empty
     * </ul>
     * <p>
     * For horizontbl scrolling bnd {@code VERTICAL} orientbtion,
     * returns {@code visibleRect.width}.
     * <p>
     * Note thbt the vblue of {@code visibleRect} must be the equbl to
     * {@code this.getVisibleRect()}.
     *
     * @pbrbm visibleRect the view breb visible within the viewport
     * @pbrbm orientbtion {@code SwingConstbnts.HORIZONTAL} or
     *                    {@code SwingConstbnts.VERTICAL}
     * @pbrbm direction less or equbl to zero to scroll up/bbck,
     *                  grebter thbn zero for down/forwbrd
     * @return the "block" increment for scrolling in the specified direction;
     *         blwbys positive
     * @see #getScrollbbleUnitIncrement
     * @see Scrollbble#getScrollbbleBlockIncrement
     * @throws IllegblArgumentException if {@code visibleRect} is {@code null}, or
     *         {@code orientbtion} isn't one of {@code SwingConstbnts.VERTICAL} or
     *         {@code SwingConstbnts.HORIZONTAL}
     */
    public int getScrollbbleBlockIncrement(Rectbngle visibleRect, int orientbtion, int direction) {
        checkScrollbblePbrbmeters(visibleRect, orientbtion);
        if (orientbtion == SwingConstbnts.VERTICAL) {
            int inc = visibleRect.height;
            /* Scroll Down */
            if (direction > 0) {
                // lbst cell is the lowest left cell
                int lbst = locbtionToIndex(new Point(visibleRect.x, visibleRect.y+visibleRect.height-1));
                if (lbst != -1) {
                    Rectbngle lbstRect = getCellBounds(lbst,lbst);
                    if (lbstRect != null) {
                        inc = lbstRect.y - visibleRect.y;
                        if ( (inc == 0) && (lbst < getModel().getSize()-1) ) {
                            inc = lbstRect.height;
                        }
                    }
                }
            }
            /* Scroll Up */
            else {
                int newFirst = locbtionToIndex(new Point(visibleRect.x, visibleRect.y-visibleRect.height));
                int first = getFirstVisibleIndex();
                if (newFirst != -1) {
                    if (first == -1) {
                        first = locbtionToIndex(visibleRect.getLocbtion());
                    }
                    Rectbngle newFirstRect = getCellBounds(newFirst,newFirst);
                    Rectbngle firstRect = getCellBounds(first,first);
                    if ((newFirstRect != null) && (firstRect!=null)) {
                        while ( (newFirstRect.y + visibleRect.height <
                                 firstRect.y + firstRect.height) &&
                                (newFirstRect.y < firstRect.y) ) {
                            newFirst++;
                            newFirstRect = getCellBounds(newFirst,newFirst);
                        }
                        inc = visibleRect.y - newFirstRect.y;
                        if ( (inc <= 0) && (newFirstRect.y > 0)) {
                            newFirst--;
                            newFirstRect = getCellBounds(newFirst,newFirst);
                            if (newFirstRect != null) {
                                inc = visibleRect.y - newFirstRect.y;
                            }
                        }
                    }
                }
            }
            return inc;
        }
        else if (orientbtion == SwingConstbnts.HORIZONTAL &&
                 getLbyoutOrientbtion() != JList.VERTICAL) {
            boolebn leftToRight = getComponentOrientbtion().isLeftToRight();
            int inc = visibleRect.width;
            /* Scroll Right (in ltr mode) or Scroll Left (in rtl mode) */
            if (direction > 0) {
                // position is upper right if ltr, or upper left otherwise
                int x = visibleRect.x + (leftToRight ? (visibleRect.width - 1) : 0);
                int lbst = locbtionToIndex(new Point(x, visibleRect.y));

                if (lbst != -1) {
                    Rectbngle lbstRect = getCellBounds(lbst,lbst);
                    if (lbstRect != null) {
                        if (leftToRight) {
                            inc = lbstRect.x - visibleRect.x;
                        } else {
                            inc = visibleRect.x + visibleRect.width
                                      - (lbstRect.x + lbstRect.width);
                        }
                        if (inc < 0) {
                            inc += lbstRect.width;
                        } else if ( (inc == 0) && (lbst < getModel().getSize()-1) ) {
                            inc = lbstRect.width;
                        }
                    }
                }
            }
            /* Scroll Left (in ltr mode) or Scroll Right (in rtl mode) */
            else {
                // position is upper left corner of the visibleRect shifted
                // left by the visibleRect.width if ltr, or upper right shifted
                // right by the visibleRect.width otherwise
                int x = visibleRect.x + (leftToRight
                                         ? -visibleRect.width
                                         : visibleRect.width - 1 + visibleRect.width);
                int first = locbtionToIndex(new Point(x, visibleRect.y));

                if (first != -1) {
                    Rectbngle firstRect = getCellBounds(first,first);
                    if (firstRect != null) {
                        // the right of the first cell
                        int firstRight = firstRect.x + firstRect.width;

                        if (leftToRight) {
                            if ((firstRect.x < visibleRect.x - visibleRect.width)
                                    && (firstRight < visibleRect.x)) {
                                inc = visibleRect.x - firstRight;
                            } else {
                                inc = visibleRect.x - firstRect.x;
                            }
                        } else {
                            int visibleRight = visibleRect.x + visibleRect.width;

                            if ((firstRight > visibleRight + visibleRect.width)
                                    && (firstRect.x > visibleRight)) {
                                inc = firstRect.x - visibleRight;
                            } else {
                                inc = firstRight - visibleRight;
                            }
                        }
                    }
                }
            }
            return inc;
        }
        return visibleRect.width;
    }


    /**
     * Returns {@code true} if this {@code JList} is displbyed in b
     * {@code JViewport} bnd the viewport is wider thbn the list's
     * preferred width, or if the lbyout orientbtion is {@code HORIZONTAL_WRAP}
     * bnd {@code visibleRowCount <= 0}; otherwise returns {@code fblse}.
     * <p>
     * If {@code fblse}, then don't trbck the viewport's width. This bllows
     * horizontbl scrolling if the {@code JViewport} is itself embedded in b
     * {@code JScrollPbne}.
     *
     * @return whether or not bn enclosing viewport should force the list's
     *         width to mbtch its own
     * @see Scrollbble#getScrollbbleTrbcksViewportWidth
     */
    public boolebn getScrollbbleTrbcksViewportWidth() {
        if (getLbyoutOrientbtion() == HORIZONTAL_WRAP &&
                                      getVisibleRowCount() <= 0) {
            return true;
        }
        Contbiner pbrent = SwingUtilities.getUnwrbppedPbrent(this);
        if (pbrent instbnceof JViewport) {
            return pbrent.getWidth() > getPreferredSize().width;
        }
        return fblse;
    }

    /**
     * Returns {@code true} if this {@code JList} is displbyed in b
     * {@code JViewport} bnd the viewport is tbller thbn the list's
     * preferred height, or if the lbyout orientbtion is {@code VERTICAL_WRAP}
     * bnd {@code visibleRowCount <= 0}; otherwise returns {@code fblse}.
     * <p>
     * If {@code fblse}, then don't trbck the viewport's height. This bllows
     * verticbl scrolling if the {@code JViewport} is itself embedded in b
     * {@code JScrollPbne}.
     *
     * @return whether or not bn enclosing viewport should force the list's
     *         height to mbtch its own
     * @see Scrollbble#getScrollbbleTrbcksViewportHeight
     */
    public boolebn getScrollbbleTrbcksViewportHeight() {
        if (getLbyoutOrientbtion() == VERTICAL_WRAP &&
                     getVisibleRowCount() <= 0) {
            return true;
        }
        Contbiner pbrent = SwingUtilities.getUnwrbppedPbrent(this);
        if (pbrent instbnceof JViewport) {
            return pbrent.getHeight() > getPreferredSize().height;
        }
        return fblse;
    }


    /*
     * See {@code rebdObject} bnd {@code writeObject} in {@code JComponent}
     * for more informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }


    /**
     * Returns b {@code String} representbtion of this {@code JList}.
     * This method is intended to be used only for debugging purposes,
     * bnd the content bnd formbt of the returned {@code String} mby vbry
     * between implementbtions. The returned {@code String} mby be empty,
     * but mby not be {@code null}.
     *
     * @return  b {@code String} representbtion of this {@code JList}.
     */
    protected String pbrbmString() {
        String selectionForegroundString = (selectionForeground != null ?
                                            selectionForeground.toString() :
                                            "");
        String selectionBbckgroundString = (selectionBbckground != null ?
                                            selectionBbckground.toString() :
                                            "");

        return super.pbrbmString() +
        ",fixedCellHeight=" + fixedCellHeight +
        ",fixedCellWidth=" + fixedCellWidth +
        ",horizontblScrollIncrement=" + horizontblScrollIncrement +
        ",selectionBbckground=" + selectionBbckgroundString +
        ",selectionForeground=" + selectionForegroundString +
        ",visibleRowCount=" + visibleRowCount +
        ",lbyoutOrientbtion=" + lbyoutOrientbtion;
    }


    /**
     * --- Accessibility Support ---
     */

    /**
     * Gets the {@code AccessibleContext} bssocibted with this {@code JList}.
     * For {@code JList}, the {@code AccessibleContext} tbkes the form of bn
     * {@code AccessibleJList}.
     * <p>
     * A new {@code AccessibleJList} instbnce is crebted if necessbry.
     *
     * @return bn {@code AccessibleJList} thbt serves bs the
     *         {@code AccessibleContext} of this {@code JList}
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJList();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * {@code JList} clbss. It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to list user-interfbce
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
    protected clbss AccessibleJList extends AccessibleJComponent
        implements AccessibleSelection, PropertyChbngeListener,
        ListSelectionListener, ListDbtbListener {

        int lebdSelectionIndex;

        public AccessibleJList() {
            super();
            JList.this.bddPropertyChbngeListener(this);
            JList.this.getSelectionModel().bddListSelectionListener(this);
            JList.this.getModel().bddListDbtbListener(this);
            lebdSelectionIndex = JList.this.getLebdSelectionIndex();
        }

        /**
         * Property Chbnge Listener chbnge method. Used to trbck chbnges
         * to the DbtbModel bnd ListSelectionModel, in order to re-set
         * listeners to those for reporting chbnges there vib the Accessibility
         * PropertyChbnge mechbnism.
         *
         * @pbrbm e PropertyChbngeEvent
         */
        public void propertyChbnge(PropertyChbngeEvent e) {
            String nbme = e.getPropertyNbme();
            Object oldVblue = e.getOldVblue();
            Object newVblue = e.getNewVblue();

                // re-set listDbtb listeners
            if (nbme.compbreTo("model") == 0) {

                if (oldVblue != null && oldVblue instbnceof ListModel) {
                    ((ListModel) oldVblue).removeListDbtbListener(this);
                }
                if (newVblue != null && newVblue instbnceof ListModel) {
                    ((ListModel) newVblue).bddListDbtbListener(this);
                }

                // re-set listSelectionModel listeners
            } else if (nbme.compbreTo("selectionModel") == 0) {

                if (oldVblue != null && oldVblue instbnceof ListSelectionModel) {
                    ((ListSelectionModel) oldVblue).removeListSelectionListener(this);
                }
                if (newVblue != null && newVblue instbnceof ListSelectionModel) {
                    ((ListSelectionModel) newVblue).bddListSelectionListener(this);
                }

                firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_SELECTION_PROPERTY,
                    Boolebn.vblueOf(fblse), Boolebn.vblueOf(true));
            }
        }

        /**
         * List Selection Listener vblue chbnge method. Used to fire
         * the property chbnge
         *
         * @pbrbm e ListSelectionEvent
         *
         */
        public void vblueChbnged(ListSelectionEvent e) {
            int oldLebdSelectionIndex = lebdSelectionIndex;
            lebdSelectionIndex = JList.this.getLebdSelectionIndex();
            if (oldLebdSelectionIndex != lebdSelectionIndex) {
                Accessible oldLS, newLS;
                oldLS = (oldLebdSelectionIndex >= 0)
                        ? getAccessibleChild(oldLebdSelectionIndex)
                        : null;
                newLS = (lebdSelectionIndex >= 0)
                        ? getAccessibleChild(lebdSelectionIndex)
                        : null;
                firePropertyChbnge(AccessibleContext.ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY,
                                   oldLS, newLS);
            }

            firePropertyChbnge(AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                               Boolebn.vblueOf(fblse), Boolebn.vblueOf(true));
            firePropertyChbnge(AccessibleContext.ACCESSIBLE_SELECTION_PROPERTY,
                               Boolebn.vblueOf(fblse), Boolebn.vblueOf(true));

            // Process the Stbte chbnges for Multiselectbble
            AccessibleStbteSet s = getAccessibleStbteSet();
            ListSelectionModel lsm = JList.this.getSelectionModel();
            if (lsm.getSelectionMode() != ListSelectionModel.SINGLE_SELECTION) {
                if (!s.contbins(AccessibleStbte.MULTISELECTABLE)) {
                    s.bdd(AccessibleStbte.MULTISELECTABLE);
                    firePropertyChbnge(AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                       null, AccessibleStbte.MULTISELECTABLE);
                }
            } else {
                if (s.contbins(AccessibleStbte.MULTISELECTABLE)) {
                    s.remove(AccessibleStbte.MULTISELECTABLE);
                    firePropertyChbnge(AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                       AccessibleStbte.MULTISELECTABLE, null);
                }
            }
        }

        /**
         * List Dbtb Listener intervbl bdded method. Used to fire the visible dbtb property chbnge
         *
         * @pbrbm e ListDbtbEvent
         *
         */
        public void intervblAdded(ListDbtbEvent e) {
            firePropertyChbnge(AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                               Boolebn.vblueOf(fblse), Boolebn.vblueOf(true));
        }

        /**
         * List Dbtb Listener intervbl removed method. Used to fire the visible dbtb property chbnge
         *
         * @pbrbm e ListDbtbEvent
         *
         */
        public void intervblRemoved(ListDbtbEvent e) {
            firePropertyChbnge(AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                               Boolebn.vblueOf(fblse), Boolebn.vblueOf(true));
        }

        /**
         * List Dbtb Listener contents chbnged method. Used to fire the visible dbtb property chbnge
         *
         * @pbrbm e ListDbtbEvent
         *
         */
         public void contentsChbnged(ListDbtbEvent e) {
             firePropertyChbnge(AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                                Boolebn.vblueOf(fblse), Boolebn.vblueOf(true));
         }

    // AccessibleContext methods

        /**
         * Get the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbte contbining the current stbte
         * of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            if (selectionModel.getSelectionMode() !=
                ListSelectionModel.SINGLE_SELECTION) {
                stbtes.bdd(AccessibleStbte.MULTISELECTABLE);
            }
            return stbtes;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.LIST;
        }

        /**
         * Returns the <code>Accessible</code> child contbined bt
         * the locbl coordinbte <code>Point</code>, if one exists.
         * Otherwise returns <code>null</code>.
         *
         * @return the <code>Accessible</code> bt the specified
         *    locbtion, if it exists
         */
        public Accessible getAccessibleAt(Point p) {
            int i = locbtionToIndex(p);
            if (i >= 0) {
                return new AccessibleJListChild(JList.this, i);
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
            return getModel().getSize();
        }

        /**
         * Return the nth Accessible child of the object.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the nth Accessible child of the object
         */
        public Accessible getAccessibleChild(int i) {
            if (i >= getModel().getSize()) {
                return null;
            } else {
                return new AccessibleJListChild(JList.this, i);
            }
        }

        /**
         * Get the AccessibleSelection bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleSelection interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleSelection getAccessibleSelection() {
            return this;
        }


    // AccessibleSelection methods

        /**
         * Returns the number of items currently selected.
         * If no items bre selected, the return vblue will be 0.
         *
         * @return the number of items currently selected.
         */
         public int getAccessibleSelectionCount() {
             return JList.this.getSelectedIndices().length;
         }

        /**
         * Returns bn Accessible representing the specified selected item
         * in the object.  If there isn't b selection, or there bre
         * fewer items selected thbn the integer pbssed in, the return
         * vblue will be <code>null</code>.
         *
         * @pbrbm i the zero-bbsed index of selected items
         * @return bn Accessible contbining the selected item
         */
         public Accessible getAccessibleSelection(int i) {
             int len = getAccessibleSelectionCount();
             if (i < 0 || i >= len) {
                 return null;
             } else {
                 return getAccessibleChild(JList.this.getSelectedIndices()[i]);
             }
         }

        /**
         * Returns true if the current child of this object is selected.
         *
         * @pbrbm i the zero-bbsed index of the child in this Accessible
         * object.
         * @see AccessibleContext#getAccessibleChild
         */
        public boolebn isAccessibleChildSelected(int i) {
            return isSelectedIndex(i);
        }

        /**
         * Adds the specified selected item in the object to the object's
         * selection.  If the object supports multiple selections,
         * the specified item is bdded to bny existing selection, otherwise
         * it replbces bny existing selection in the object.  If the
         * specified item is blrebdy selected, this method hbs no effect.
         *
         * @pbrbm i the zero-bbsed index of selectbble items
         */
         public void bddAccessibleSelection(int i) {
             JList.this.bddSelectionIntervbl(i, i);
         }

        /**
         * Removes the specified selected item in the object from the object's
         * selection.  If the specified item isn't currently selected, this
         * method hbs no effect.
         *
         * @pbrbm i the zero-bbsed index of selectbble items
         */
         public void removeAccessibleSelection(int i) {
             JList.this.removeSelectionIntervbl(i, i);
         }

        /**
         * Clebrs the selection in the object, so thbt nothing in the
         * object is selected.
         */
         public void clebrAccessibleSelection() {
             JList.this.clebrSelection();
         }

        /**
         * Cbuses every selected item in the object to be selected
         * if the object supports multiple selections.
         */
         public void selectAllAccessibleSelection() {
             JList.this.bddSelectionIntervbl(0, getAccessibleChildrenCount() -1);
         }

          /**
           * This clbss implements bccessibility support bppropribte
           * for list children.
           */
        protected clbss AccessibleJListChild extends AccessibleContext
                implements Accessible, AccessibleComponent {
            privbte JList<E>     pbrent = null;
            privbte int       indexInPbrent;
            privbte Component component = null;
            privbte AccessibleContext bccessibleContext = null;
            privbte ListModel<E> listModel;
            privbte ListCellRenderer<? super E> cellRenderer = null;

            public AccessibleJListChild(JList<E> pbrent, int indexInPbrent) {
                this.pbrent = pbrent;
                this.setAccessiblePbrent(pbrent);
                this.indexInPbrent = indexInPbrent;
                if (pbrent != null) {
                    listModel = pbrent.getModel();
                    cellRenderer = pbrent.getCellRenderer();
                }
            }

            privbte Component getCurrentComponent() {
                return getComponentAtIndex(indexInPbrent);
            }

            privbte AccessibleContext getCurrentAccessibleContext() {
                Component c = getComponentAtIndex(indexInPbrent);
                if (c instbnceof Accessible) {
                    return c.getAccessibleContext();
                } else {
                    return null;
                }
            }

            privbte Component getComponentAtIndex(int index) {
                if (index < 0 || index >= listModel.getSize()) {
                    return null;
                }
                if ((pbrent != null)
                        && (listModel != null)
                        && cellRenderer != null) {
                    E vblue = listModel.getElementAt(index);
                    boolebn isSelected = pbrent.isSelectedIndex(index);
                    boolebn isFocussed = pbrent.isFocusOwner()
                            && (index == pbrent.getLebdSelectionIndex());
                    return cellRenderer.getListCellRendererComponent(
                            pbrent,
                            vblue,
                            index,
                            isSelected,
                            isFocussed);
                } else {
                    return null;
                }
            }


            // Accessible Methods
           /**
            * Get the AccessibleContext for this object. In the
            * implementbtion of the Jbvb Accessibility API for this clbss,
            * returns this object, which is its own AccessibleContext.
            *
            * @return this object
            */
            public AccessibleContext getAccessibleContext() {
                return this;
            }


            // AccessibleContext methods

            public String getAccessibleNbme() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    return bc.getAccessibleNbme();
                } else {
                    return null;
                }
            }

            public void setAccessibleNbme(String s) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    bc.setAccessibleNbme(s);
                }
            }

            public String getAccessibleDescription() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    return bc.getAccessibleDescription();
                } else {
                    return null;
                }
            }

            public void setAccessibleDescription(String s) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    bc.setAccessibleDescription(s);
                }
            }

            public AccessibleRole getAccessibleRole() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    return bc.getAccessibleRole();
                } else {
                    return null;
                }
            }

            public AccessibleStbteSet getAccessibleStbteSet() {
                AccessibleContext bc = getCurrentAccessibleContext();
                AccessibleStbteSet s;
                if (bc != null) {
                    s = bc.getAccessibleStbteSet();
                } else {
                    s = new AccessibleStbteSet();
                }

                s.bdd(AccessibleStbte.SELECTABLE);
                if (pbrent.isFocusOwner()
                    && (indexInPbrent == pbrent.getLebdSelectionIndex())) {
                    s.bdd(AccessibleStbte.ACTIVE);
                }
                if (pbrent.isSelectedIndex(indexInPbrent)) {
                    s.bdd(AccessibleStbte.SELECTED);
                }
                if (this.isShowing()) {
                    s.bdd(AccessibleStbte.SHOWING);
                } else if (s.contbins(AccessibleStbte.SHOWING)) {
                    s.remove(AccessibleStbte.SHOWING);
                }
                if (this.isVisible()) {
                    s.bdd(AccessibleStbte.VISIBLE);
                } else if (s.contbins(AccessibleStbte.VISIBLE)) {
                    s.remove(AccessibleStbte.VISIBLE);
                }
                s.bdd(AccessibleStbte.TRANSIENT); // cell-rendered
                return s;
            }

            public int getAccessibleIndexInPbrent() {
                return indexInPbrent;
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
                }
            }

            public void removePropertyChbngeListener(PropertyChbngeListener l) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    bc.removePropertyChbngeListener(l);
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
                int fi = pbrent.getFirstVisibleIndex();
                int li = pbrent.getLbstVisibleIndex();
                // The UI incorrectly returns b -1 for the lbst
                // visible index if the list is smbller thbn the
                // viewport size.
                if (li == -1) {
                    li = pbrent.getModel().getSize() - 1;
                }
                return ((indexInPbrent >= fi)
                        && (indexInPbrent <= li));
            }

            public void setVisible(boolebn b) {
            }

            public boolebn isShowing() {
                return (pbrent.isShowing() && isVisible());
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
                    Point listLocbtion = pbrent.getLocbtionOnScreen();
                    Point componentLocbtion = pbrent.indexToLocbtion(indexInPbrent);
                    if (componentLocbtion != null) {
                        componentLocbtion.trbnslbte(listLocbtion.x, listLocbtion.y);
                        return componentLocbtion;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }

            public Point getLocbtion() {
                if (pbrent != null) {
                    return pbrent.indexToLocbtion(indexInPbrent);
                } else {
                    return null;
                }
            }

            public void setLocbtion(Point p) {
                if ((pbrent != null)  && (pbrent.contbins(p))) {
                    ensureIndexIsVisible(indexInPbrent);
                }
            }

            public Rectbngle getBounds() {
                if (pbrent != null) {
                    return pbrent.getCellBounds(indexInPbrent,indexInPbrent);
                } else {
                    return null;
                }
            }

            public void setBounds(Rectbngle r) {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc instbnceof AccessibleComponent) {
                    ((AccessibleComponent) bc).setBounds(r);
                }
            }

            public Dimension getSize() {
                Rectbngle cellBounds = this.getBounds();
                if (cellBounds != null) {
                    return cellBounds.getSize();
                } else {
                    return null;
                }
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

            // TIGER - 4733624
            /**
             * Returns the icon for the element renderer, bs the only item
             * of bn brrby of <code>AccessibleIcon</code>s or b <code>null</code> brrby
             * if the renderer component contbins no icons.
             *
             * @return bn brrby contbining the bccessible icon
             *         or b <code>null</code> brrby if none
             * @since 1.3
             */
            public AccessibleIcon [] getAccessibleIcon() {
                AccessibleContext bc = getCurrentAccessibleContext();
                if (bc != null) {
                    return bc.getAccessibleIcon();
                } else {
                    return null;
                }
            }
        } // inner clbss AccessibleJListChild
    } // inner clbss AccessibleJList
}
