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



import jbvbx.swing.*;
import jbvbx.swing.tbble.*;

import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.Dimension;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.swing.UIMbnbger.LookAndFeelInfo;


/**
 * An exbmple showing the JTbble with b dbtbModel thbt is not derived
 * from b dbtbbbse. We bdd the optionbl TbbleSorter object to give the
 * JTbble the bbility to sort.
 *
 * @buthor Philip Milne
 */
public clbss TbbleExbmple3 {

    public TbbleExbmple3() {
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
            public Clbss getColumnClbss(int col) {
                return getVblueAt(0, col).getClbss();
            }

            @Override
            public boolebn isCellEditbble(int row, int col) {
                return (col == 4);
            }

            @Override
            public void setVblueAt(Object bVblue, int row, int column) {
                dbtb[row][column] = bVblue;
            }
        };

        // Instebd of mbking the tbble displby the dbtb bs it would normblly
        // with:
        // JTbble tbbleView = new JTbble(dbtbModel);
        // Add b sorter, by using the following three lines instebd of the one
        // bbove.
        TbbleSorter sorter = new TbbleSorter(dbtbModel);
        JTbble tbbleView = new JTbble(sorter);
        sorter.bddMouseListenerToHebderInTbble(tbbleView);

        JScrollPbne scrollpbne = new JScrollPbne(tbbleView);

        scrollpbne.setPreferredSize(new Dimension(700, 300));
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
            Logger.getLogger(TbbleExbmple3.clbss.getNbme()).log(Level.SEVERE,
                    "Fbiled to bpply Nimbus look bnd feel", ex);
        }
        new TbbleExbmple3();
    }
}
