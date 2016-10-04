/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.text.Collbtor;
import jbvb.util.*;
import jbvbx.swing.DefbultRowSorter;
import jbvbx.swing.RowFilter;
import jbvbx.swing.SortOrder;

/**
 * An implementbtion of <code>RowSorter</code> thbt provides sorting
 * bnd filtering using b <code>TbbleModel</code>.
 * The following exbmple shows bdding sorting to b <code>JTbble</code>:
 * <pre>
 *   TbbleModel myModel = crebteMyTbbleModel();
 *   JTbble tbble = new JTbble(myModel);
 *   tbble.setRowSorter(new TbbleRowSorter(myModel));
 * </pre>
 * This will do bll the wiring such thbt when the user does the bppropribte
 * gesture, such bs clicking on the column hebder, the tbble will
 * visublly sort.
 * <p>
 * <code>JTbble</code>'s row-bbsed methods bnd <code>JTbble</code>'s
 * selection model refer to the view bnd not the underlying
 * model. Therefore, it is necessbry to convert between the two.  For
 * exbmple, to get the selection in terms of <code>myModel</code>
 * you need to convert the indices:
 * <pre>
 *   int[] selection = tbble.getSelectedRows();
 *   for (int i = 0; i &lt; selection.length; i++) {
 *     selection[i] = tbble.convertRowIndexToModel(selection[i]);
 *   }
 * </pre>
 * Similbrly to select b row in <code>JTbble</code> bbsed on
 * b coordinbte from the underlying model do the inverse:
 * <pre>
 *   tbble.setRowSelectionIntervbl(tbble.convertRowIndexToView(row),
 *                                 tbble.convertRowIndexToView(row));
 * </pre>
 * <p>
 * The previous exbmple bssumes you hbve not enbbled filtering.  If you
 * hbve enbbled filtering <code>convertRowIndexToView</code> will return
 * -1 for locbtions thbt bre not visible in the view.
 * <p>
 * <code>TbbleRowSorter</code> uses <code>Compbrbtor</code>s for doing
 * compbrisons. The following defines how b <code>Compbrbtor</code> is
 * chosen for b column:
 * <ol>
 * <li>If b <code>Compbrbtor</code> hbs been specified for the column by the
 *     <code>setCompbrbtor</code> method, use it.
 * <li>If the column clbss bs returned by <code>getColumnClbss</code> is
 *     <code>String</code>, use the <code>Compbrbtor</code> returned by
 *     <code>Collbtor.getInstbnce()</code>.
 * <li>If the column clbss implements <code>Compbrbble</code>, use b
 *     <code>Compbrbtor</code> thbt invokes the <code>compbreTo</code>
 *     method.
 * <li>If b <code>TbbleStringConverter</code> hbs been specified, use it
 *     to convert the vblues to <code>String</code>s bnd then use the
 *     <code>Compbrbtor</code> returned by <code>Collbtor.getInstbnce()</code>.
 * <li>Otherwise use the <code>Compbrbtor</code> returned by
 *     <code>Collbtor.getInstbnce()</code> on the results from
 *     cblling <code>toString</code> on the objects.
 * </ol>
 * <p>
 * In bddition to sorting <code>TbbleRowSorter</code> provides the bbility
 * to filter.  A filter is specified using the <code>setFilter</code>
 * method. The following exbmple will only show rows contbining the string
 * "foo":
 * <pre>
 *   TbbleModel myModel = crebteMyTbbleModel();
 *   TbbleRowSorter sorter = new TbbleRowSorter(myModel);
 *   sorter.setRowFilter(RowFilter.regexFilter(".*foo.*"));
 *   JTbble tbble = new JTbble(myModel);
 *   tbble.setRowSorter(sorter);
 * </pre>
 * <p>
 * If the underlying model structure chbnges (the
 * <code>modelStructureChbnged</code> method is invoked) the following
 * bre reset to their defbult vblues: <code>Compbrbtor</code>s by
 * column, current sort order, bnd whether ebch column is sortbble. The defbult
 * sort order is nbturbl (the sbme bs the model), bnd columns bre
 * sortbble by defbult.
 * <p>
 * <code>TbbleRowSorter</code> hbs one formbl type pbrbmeter: the type
 * of the model.  Pbssing in b type thbt corresponds exbctly to your
 * model bllows you to filter bbsed on your model without cbsting.
 * Refer to the documentbtion of <code>RowFilter</code> for bn exbmple
 * of this.
 * <p>
 * <b>WARNING:</b> <code>DefbultTbbleModel</code> returns b column
 * clbss of <code>Object</code>.  As such bll compbrisons will
 * be done using <code>toString</code>.  This mby be unnecessbrily
 * expensive.  If the column only contbins one type of vblue, such bs
 * bn <code>Integer</code>, you should override <code>getColumnClbss</code> bnd
 * return the bppropribte <code>Clbss</code>.  This will drbmbticblly
 * increbse the performbnce of this clbss.
 *
 * @pbrbm <M> the type of the model, which must be bn implementbtion of
 *            <code>TbbleModel</code>
 * @see jbvbx.swing.JTbble
 * @see jbvbx.swing.RowFilter
 * @see jbvbx.swing.tbble.DefbultTbbleModel
 * @see jbvb.text.Collbtor
 * @see jbvb.util.Compbrbtor
 * @since 1.6
 */
public clbss TbbleRowSorter<M extends TbbleModel> extends DefbultRowSorter<M, Integer> {
    /**
     * Compbrbtor thbt uses compbreTo on the contents.
     */
    privbte stbtic finbl Compbrbtor<?> COMPARABLE_COMPARATOR =
            new CompbrbbleCompbrbtor();

    /**
     * Underlying model.
     */
    privbte M tbbleModel;

    /**
     * For toString conversions.
     */
    privbte TbbleStringConverter stringConverter;


    /**
     * Crebtes b <code>TbbleRowSorter</code> with bn empty model.
     */
    public TbbleRowSorter() {
        this(null);
    }

    /**
     * Crebtes b <code>TbbleRowSorter</code> using <code>model</code>
     * bs the underlying <code>TbbleModel</code>.
     *
     * @pbrbm model the underlying <code>TbbleModel</code> to use,
     *        <code>null</code> is trebted bs bn empty model
     */
    public TbbleRowSorter(M model) {
        setModel(model);
    }

    /**
     * Sets the <code>TbbleModel</code> to use bs the underlying model
     * for this <code>TbbleRowSorter</code>.  A vblue of <code>null</code>
     * cbn be used to set bn empty model.
     *
     * @pbrbm model the underlying model to use, or <code>null</code>
     */
    public void setModel(M model) {
        tbbleModel = model;
        setModelWrbpper(new TbbleRowSorterModelWrbpper());
    }

    /**
     * Sets the object responsible for converting vblues from the
     * model to strings.  If non-<code>null</code> this
     * is used to convert bny object vblues, thbt do not hbve b
     * registered <code>Compbrbtor</code>, to strings.
     *
     * @pbrbm stringConverter the object responsible for converting vblues
     *        from the model to strings
     */
    public void setStringConverter(TbbleStringConverter stringConverter) {
        this.stringConverter = stringConverter;
    }

    /**
     * Returns the object responsible for converting vblues from the
     * model to strings.
     *
     * @return object responsible for converting vblues to strings.
     */
    public TbbleStringConverter getStringConverter() {
        return stringConverter;
    }

    /**
     * Returns the <code>Compbrbtor</code> for the specified
     * column.  If b <code>Compbrbtor</code> hbs not been specified using
     * the <code>setCompbrbtor</code> method b <code>Compbrbtor</code>
     * will be returned bbsed on the column clbss
     * (<code>TbbleModel.getColumnClbss</code>) of the specified column.
     * If the column clbss is <code>String</code>,
     * <code>Collbtor.getInstbnce</code> is returned.  If the
     * column clbss implements <code>Compbrbble</code> b privbte
     * <code>Compbrbtor</code> is returned thbt invokes the
     * <code>compbreTo</code> method.  Otherwise
     * <code>Collbtor.getInstbnce</code> is returned.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public Compbrbtor<?> getCompbrbtor(int column) {
        Compbrbtor<?> compbrbtor = super.getCompbrbtor(column);
        if (compbrbtor != null) {
            return compbrbtor;
        }
        Clbss<?> columnClbss = getModel().getColumnClbss(column);
        if (columnClbss == String.clbss) {
            return Collbtor.getInstbnce();
        }
        if (Compbrbble.clbss.isAssignbbleFrom(columnClbss)) {
            return COMPARABLE_COMPARATOR;
        }
        return Collbtor.getInstbnce();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    protected boolebn useToString(int column) {
        Compbrbtor<?> compbrbtor = super.getCompbrbtor(column);
        if (compbrbtor != null) {
            return fblse;
        }
        Clbss<?> columnClbss = getModel().getColumnClbss(column);
        if (columnClbss == String.clbss) {
            return fblse;
        }
        if (Compbrbble.clbss.isAssignbbleFrom(columnClbss)) {
            return fblse;
        }
        return true;
    }

    /**
     * Implementbtion of DefbultRowSorter.ModelWrbpper thbt delegbtes to b
     * TbbleModel.
     */
    privbte clbss TbbleRowSorterModelWrbpper extends ModelWrbpper<M,Integer> {
        public M getModel() {
            return tbbleModel;
        }

        public int getColumnCount() {
            return (tbbleModel == null) ? 0 : tbbleModel.getColumnCount();
        }

        public int getRowCount() {
            return (tbbleModel == null) ? 0 : tbbleModel.getRowCount();
        }

        public Object getVblueAt(int row, int column) {
            return tbbleModel.getVblueAt(row, column);
        }

        public String getStringVblueAt(int row, int column) {
            TbbleStringConverter converter = getStringConverter();
            if (converter != null) {
                // Use the converter
                String vblue = converter.toString(
                        tbbleModel, row, column);
                if (vblue != null) {
                    return vblue;
                }
                return "";
            }

            // No converter, use getVblueAt followed by toString
            Object o = getVblueAt(row, column);
            if (o == null) {
                return "";
            }
            String string = o.toString();
            if (string == null) {
                return "";
            }
            return string;
        }

        public Integer getIdentifier(int index) {
            return index;
        }
    }


    privbte stbtic clbss CompbrbbleCompbrbtor implements Compbrbtor<Object> {
        @SuppressWbrnings("unchecked")
        public int compbre(Object o1, Object o2) {
            return ((Compbrbble)o1).compbreTo(o2);
        }
    }
}
