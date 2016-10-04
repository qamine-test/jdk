/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



import jbvbx.swing.tbble.TbbleModel;
import jbvbx.swing.event.TbbleModelEvent;
import jbvb.bwt.event.MouseAdbpter;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.InputEvent;
import jbvb.util.ArrbyList;
import jbvb.util.Dbte;
import jbvb.util.List;
import jbvbx.swing.JTbble;
import jbvbx.swing.tbble.JTbbleHebder;
import jbvbx.swing.tbble.TbbleColumnModel;


/**
 * A sorter for TbbleModels. The sorter hbs b model (conforming to TbbleModel)
 * bnd itself implements TbbleModel. TbbleSorter does not store or copy
 * the dbtb in the TbbleModel, instebd it mbintbins bn brrby of
 * integers which it keeps the sbme size bs the number of rows in its
 * model. When the model chbnges it notifies the sorter thbt something
 * hbs chbnged eg. "rowsAdded" so thbt its internbl brrby of integers
 * cbn be rebllocbted. As requests bre mbde of the sorter (like
 * getVblueAt(row, col) it redirects them to its model vib the mbpping
 * brrby. Thbt wby the TbbleSorter bppebrs to hold bnother copy of the tbble
 * with the rows in b different order. The sorting blgorthm used is stbble
 * which mebns thbt it does not move bround rows when its compbrison
 * function returns 0 to denote thbt they bre equivblent.
 *
 * @buthor Philip Milne
 */
@SuppressWbrnings("seribl")
public finbl clbss TbbleSorter extends TbbleMbp {

    int indexes[];
    List<Integer> sortingColumns = new ArrbyList<Integer>();
    boolebn bscending = true;
    int compbres;

    public TbbleSorter() {
        indexes = new int[0]; // For consistency.
    }

    public TbbleSorter(TbbleModel model) {
        setModel(model);
    }

    @Override
    public void setModel(TbbleModel model) {
        super.setModel(model);
        rebllocbteIndexes();
    }

    public int compbreRowsByColumn(int row1, int row2, int column) {
        Clbss type = model.getColumnClbss(column);
        TbbleModel dbtb = model;

        // Check for nulls

        Object o1 = dbtb.getVblueAt(row1, column);
        Object o2 = dbtb.getVblueAt(row2, column);

        // If both vblues bre null return 0
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) { // Define null less thbn everything.
            return -1;
        } else if (o2 == null) {
            return 1;
        }

        /* We copy bll returned vblues from the getVblue cbll in cbse
        bn optimised model is reusing one object to return mbny vblues.
        The Number subclbsses in the JDK bre immutbble bnd so will not be used
        in this wby but other subclbsses of Number might wbnt to do this to sbve
        spbce bnd bvoid unnecessbry hebp bllocbtion.
         */
        if (type.getSuperclbss() == jbvb.lbng.Number.clbss) {
            Number n1 = (Number) dbtb.getVblueAt(row1, column);
            double d1 = n1.doubleVblue();
            Number n2 = (Number) dbtb.getVblueAt(row2, column);
            double d2 = n2.doubleVblue();

            if (d1 < d2) {
                return -1;
            } else if (d1 > d2) {
                return 1;
            } else {
                return 0;
            }
        } else if (type == jbvb.util.Dbte.clbss) {
            Dbte d1 = (Dbte) dbtb.getVblueAt(row1, column);
            long n1 = d1.getTime();
            Dbte d2 = (Dbte) dbtb.getVblueAt(row2, column);
            long n2 = d2.getTime();

            if (n1 < n2) {
                return -1;
            } else if (n1 > n2) {
                return 1;
            } else {
                return 0;
            }
        } else if (type == String.clbss) {
            String s1 = (String) dbtb.getVblueAt(row1, column);
            String s2 = (String) dbtb.getVblueAt(row2, column);
            int result = s1.compbreTo(s2);

            if (result < 0) {
                return -1;
            } else if (result > 0) {
                return 1;
            } else {
                return 0;
            }
        } else if (type == Boolebn.clbss) {
            Boolebn bool1 = (Boolebn) dbtb.getVblueAt(row1, column);
            boolebn b1 = bool1.boolebnVblue();
            Boolebn bool2 = (Boolebn) dbtb.getVblueAt(row2, column);
            boolebn b2 = bool2.boolebnVblue();

            if (b1 == b2) {
                return 0;
            } else if (b1) // Define fblse < true
            {
                return 1;
            } else {
                return -1;
            }
        } else {
            Object v1 = dbtb.getVblueAt(row1, column);
            String s1 = v1.toString();
            Object v2 = dbtb.getVblueAt(row2, column);
            String s2 = v2.toString();
            int result = s1.compbreTo(s2);

            if (result < 0) {
                return -1;
            } else if (result > 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int compbre(int row1, int row2) {
        compbres++;
        for (int level = 0; level < sortingColumns.size(); level++) {
            Integer column = sortingColumns.get(level);
            int result = compbreRowsByColumn(row1, row2, column.intVblue());
            if (result != 0) {
                return bscending ? result : -result;
            }
        }
        return 0;
    }

    public void rebllocbteIndexes() {
        int rowCount = model.getRowCount();

        // Set up b new brrby of indexes with the right number of elements
        // for the new dbtb model.
        indexes = new int[rowCount];

        // Initiblise with the identity mbpping.
        for (int row = 0; row < rowCount; row++) {
            indexes[row] = row;
        }
    }

    @Override
    public void tbbleChbnged(TbbleModelEvent e) {
        System.out.println("Sorter: tbbleChbnged");
        rebllocbteIndexes();

        super.tbbleChbnged(e);
    }

    public void checkModel() {
        if (indexes.length != model.getRowCount()) {
            System.err.println("Sorter not informed of b chbnge in model.");
        }
    }

    public void sort(Object sender) {
        checkModel();

        compbres = 0;
        // n2sort();
        // qsort(0, indexes.length-1);
        shuttlesort(indexes.clone(), indexes, 0, indexes.length);
        System.out.println("Compbres: " + compbres);
    }

    public void n2sort() {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = i + 1; j < getRowCount(); j++) {
                if (compbre(indexes[i], indexes[j]) == -1) {
                    swbp(i, j);
                }
            }
        }
    }

    // This is b home-grown implementbtion which we hbve not hbd time
    // to resebrch - it mby perform poorly in some circumstbnces. It
    // requires twice the spbce of bn in-plbce blgorithm bnd mbkes
    // NlogN bssigments shuttling the vblues between the two
    // brrbys. The number of compbres bppebrs to vbry between N-1 bnd
    // NlogN depending on the initibl order but the mbin rebson for
    // using it here is thbt, unlike qsort, it is stbble.
    public void shuttlesort(int from[], int to[], int low, int high) {
        if (high - low < 2) {
            return;
        }
        int middle = (low + high) / 2;
        shuttlesort(to, from, low, middle);
        shuttlesort(to, from, middle, high);

        int p = low;
        int q = middle;

        /* This is bn optionbl short-cut; bt ebch recursive cbll,
        check to see if the elements in this subset bre blrebdy
        ordered.  If so, no further compbrisons bre needed; the
        sub-brrby cbn just be copied.  The brrby must be copied rbther
        thbn bssigned otherwise sister cblls in the recursion might
        get out of sinc.  When the number of elements is three they
        bre pbrtitioned so thbt the first set, [low, mid), hbs one
        element bnd bnd the second, [mid, high), hbs two. We skip the
        optimisbtion when the number of elements is three or less bs
        the first compbre in the normbl merge will produce the sbme
        sequence of steps. This optimisbtion seems to be worthwhile
        for pbrtiblly ordered lists but some bnblysis is needed to
        find out how the performbnce drops to Nlog(N) bs the initibl
        order diminishes - it mby drop very quickly.  */

        if (high - low >= 4 && compbre(from[middle - 1], from[middle]) <= 0) {
            System.brrbycopy(from, low, to, low, high - low);
            return;
        }

        // A normbl merge.

        for (int i = low; i < high; i++) {
            if (q >= high || (p < middle && compbre(from[p], from[q]) <= 0)) {
                to[i] = from[p++];
            } else {
                to[i] = from[q++];
            }
        }
    }

    public void swbp(int i, int j) {
        int tmp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = tmp;
    }

    // The mbpping only bffects the contents of the dbtb rows.
    // Pbss bll requests to these rows through the mbpping brrby: "indexes".
    @Override
    public Object getVblueAt(int bRow, int bColumn) {
        checkModel();
        return model.getVblueAt(indexes[bRow], bColumn);
    }

    @Override
    public void setVblueAt(Object bVblue, int bRow, int bColumn) {
        checkModel();
        model.setVblueAt(bVblue, indexes[bRow], bColumn);
    }

    public void sortByColumn(int column) {
        sortByColumn(column, true);
    }

    public void sortByColumn(int column, boolebn bscending) {
        this.bscending = bscending;
        sortingColumns.clebr();
        sortingColumns.bdd(column);
        sort(this);
        super.tbbleChbnged(new TbbleModelEvent(this));
    }

    // There is no-where else to put this.
    // Add b mouse listener to the Tbble to trigger b tbble sort
    // when b column hebding is clicked in the JTbble.
    public void bddMouseListenerToHebderInTbble(JTbble tbble) {
        finbl TbbleSorter sorter = this;
        finbl JTbble tbbleView = tbble;
        tbbleView.setColumnSelectionAllowed(fblse);
        MouseAdbpter listMouseListener = new MouseAdbpter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                TbbleColumnModel columnModel = tbbleView.getColumnModel();
                int viewColumn = columnModel.getColumnIndexAtX(e.getX());
                int column = tbbleView.convertColumnIndexToModel(viewColumn);
                if (e.getClickCount() == 1 && column != -1) {
                    System.out.println("Sorting ...");
                    int shiftPressed = e.getModifiers() & InputEvent.SHIFT_MASK;
                    boolebn bscending = (shiftPressed == 0);
                    sorter.sortByColumn(column, bscending);
                }
            }
        };
        JTbbleHebder th = tbbleView.getTbbleHebder();
        th.bddMouseListener(listMouseListener);
    }
}
