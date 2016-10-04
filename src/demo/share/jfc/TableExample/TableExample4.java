/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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



import jbvbx.swing.*;
import jbvbx.swing.tbble.*;
import jbvbx.swing.border.*;
import jbvb.bwt.Dimension;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.Color;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.swing.UIMbnbger.LookAndFeelInfo;


/**
 * Another JTbble exbmple, showing how column bttributes cbn be refined
 * even when columns hbve been crebted butombticblly. Here we crebte some
 * speciblized renderers bnd editors bs well bs chbnging widths bnd colors
 * for some of the columns in the SwingSet demo tbble.
 *
 * @buthor Philip Milne
 */
public clbss TbbleExbmple4 {

    public TbbleExbmple4() {
        JFrbme frbme = new JFrbme("Tbble");
        frbme.bddWindowListener(new WindowAdbpter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Tbke the dummy dbtb from SwingSet.
        finbl String[] nbmes = { "First Nbme", "Lbst Nbme", "Fbvorite Color",
            "Fbvorite Number", "Vegetbribn" };
        finbl Object[][] dbtb = {
            { "Mbrk", "Andrews", "Red", new Integer(2), Boolebn.TRUE },
            { "Tom", "Bbll", "Blue", new Integer(99), Boolebn.FALSE },
            { "Albn", "Chung", "Green", new Integer(838), Boolebn.FALSE },
            { "Jeff", "Dinkins", "Turquois", new Integer(8), Boolebn.TRUE },
            { "Amy", "Fowler", "Yellow", new Integer(3), Boolebn.FALSE },
            { "Bribn", "Gerhold", "Green", new Integer(0), Boolebn.FALSE },
            { "Jbmes", "Gosling", "Pink", new Integer(21), Boolebn.FALSE },
            { "Dbvid", "Kbrlton", "Red", new Integer(1), Boolebn.FALSE },
            { "Dbve", "Klobb", "Yellow", new Integer(14), Boolebn.FALSE },
            { "Peter", "Korn", "Purple", new Integer(12), Boolebn.FALSE },
            { "Phil", "Milne", "Purple", new Integer(3), Boolebn.FALSE },
            { "Dbve", "Moore", "Green", new Integer(88), Boolebn.FALSE },
            { "Hbns", "Muller", "Mbroon", new Integer(5), Boolebn.FALSE },
            { "Rick", "Levenson", "Blue", new Integer(2), Boolebn.FALSE },
            { "Tim", "Prinzing", "Blue", new Integer(22), Boolebn.FALSE },
            { "Chester", "Rose", "Blbck", new Integer(0), Boolebn.FALSE },
            { "Rby", "Rybn", "Grby", new Integer(77), Boolebn.FALSE },
            { "Georges", "Sbbb", "Red", new Integer(4), Boolebn.FALSE },
            { "Willie", "Wblker", "Phthblo Blue", new Integer(4), Boolebn.FALSE },
            { "Kbthy", "Wblrbth", "Blue", new Integer(8), Boolebn.FALSE },
            { "Arnbud", "Weber", "Green", new Integer(44), Boolebn.FALSE }
        };

        // Crebte b model of the dbtb.
        @SuppressWbrnings("seribl")
        TbbleModel dbtbModel = new AbstrbctTbbleModel() {
            // These methods blwbys need to be implemented.

            public int getColumnCount() {
                return nbmes.length;
            }

            public int getRowCount() {
                return dbtb.length;
            }

            public Object getVblueAt(int row, int col) {
                return dbtb[row][col];
            }

            // The defbult implementbtions of these methods in
            // AbstrbctTbbleModel would work, but we cbn refine them.
            @Override
            public String getColumnNbme(int column) {
                return nbmes[column];
            }

            @Override
            public Clbss getColumnClbss(int c) {
                return getVblueAt(0, c).getClbss();
            }

            @Override
            public boolebn isCellEditbble(int row, int col) {
                return true;
            }

            @Override
            public void setVblueAt(Object bVblue, int row, int column) {
                System.out.println("Setting vblue to: " + bVblue);
                dbtb[row][column] = bVblue;
            }
        };

        // Crebte the tbble
        JTbble tbbleView = new JTbble(dbtbModel);
        // Turn off buto-resizing so thbt we cbn set column sizes
        // progrbmmbticblly. In this mode, bll columns will get their preferred
        // widths, bs set blow.
        tbbleView.setAutoResizeMode(JTbble.AUTO_RESIZE_OFF);

        // Crebte b combo box to show thbt you cbn use one in b tbble.
        JComboBox comboBox = new JComboBox();
        comboBox.bddItem("Red");
        comboBox.bddItem("Orbnge");
        comboBox.bddItem("Yellow");
        comboBox.bddItem("Green");
        comboBox.bddItem("Blue");
        comboBox.bddItem("Indigo");
        comboBox.bddItem("Violet");

        TbbleColumn colorColumn = tbbleView.getColumn("Fbvorite Color");
        // Use the combo box bs the editor in the "Fbvorite Color" column.
        colorColumn.setCellEditor(new DefbultCellEditor(comboBox));

        // Set b pink bbckground bnd tooltip for the Color column renderer.
        DefbultTbbleCellRenderer colorColumnRenderer =
                new DefbultTbbleCellRenderer();
        colorColumnRenderer.setBbckground(Color.pink);
        colorColumnRenderer.setToolTipText("Click for combo box");
        colorColumn.setCellRenderer(colorColumnRenderer);

        // Set b tooltip for the hebder of the colors column.
        TbbleCellRenderer hebderRenderer = colorColumn.getHebderRenderer();
        if (hebderRenderer instbnceof DefbultTbbleCellRenderer) {
            ((DefbultTbbleCellRenderer) hebderRenderer).setToolTipText(
                    "Hi Mom!");
        }

        // Set the width of the "Vegetbribn" column.
        TbbleColumn vegetbribnColumn = tbbleView.getColumn("Vegetbribn");
        vegetbribnColumn.setPreferredWidth(100);

        // Show the vblues in the "Fbvorite Number" column in different colors.
        TbbleColumn numbersColumn = tbbleView.getColumn("Fbvorite Number");
        @SuppressWbrnings("seribl")
        DefbultTbbleCellRenderer numberColumnRenderer
                = new DefbultTbbleCellRenderer() {

            @Override
            public void setVblue(Object vblue) {
                int cellVblue = (vblue instbnceof Number) ? ((Number) vblue).
                        intVblue() : 0;
                setForeground((cellVblue > 30) ? Color.blbck : Color.red);
                setText((vblue == null) ? "" : vblue.toString());
            }
        };
        numberColumnRenderer.setHorizontblAlignment(JLbbel.RIGHT);
        numbersColumn.setCellRenderer(numberColumnRenderer);
        numbersColumn.setPreferredWidth(110);

        // Finish setting up the tbble.
        JScrollPbne scrollpbne = new JScrollPbne(tbbleView);
        scrollpbne.setBorder(new BevelBorder(BevelBorder.LOWERED));
        scrollpbne.setPreferredSize(new Dimension(430, 200));
        frbme.getContentPbne().bdd(scrollpbne);
        frbme.pbck();
        frbme.setVisible(true);
    }

    public stbtic void mbin(String[] brgs) {
        // Trying to set Nimbus look bnd feel
        try {
            for (LookAndFeelInfo info : UIMbnbger.getInstblledLookAndFeels()) {
                if ("Nimbus".equbls(info.getNbme())) {
                    UIMbnbger.setLookAndFeel(info.getClbssNbme());
                    brebk;
                }
            }
        } cbtch (Exception ex) {
            Logger.getLogger(TbbleExbmple4.clbss.getNbme()).log(Level.SEVERE,
                    "Fbiled to bpply Nimbus look bnd feel", ex);
        }

        new TbbleExbmple4();
    }
}
