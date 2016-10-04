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



/**
 * A b UI bround the JDBCAdbptor, bllowing dbtbbbse dbtb to be interbctively
 * fetched, sorted bnd displbyed using Swing.
 *
 * NOTE: This exbmple uses b modbl diblog vib the stbtic convenience methods in
 * the JOptionPbne. Use of modbl diblogs requires JDK 1.1.4 or grebter.
 *
 * @buthor Philip Milne
 */
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.LbyoutMbnbger;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.swing.BoxLbyout;
import jbvbx.swing.JButton;
import jbvbx.swing.JComponent;
import jbvbx.swing.JFrbme;
import jbvbx.swing.JLbbel;
import jbvbx.swing.JOptionPbne;
import jbvbx.swing.JPbnel;
import jbvbx.swing.JScrollPbne;
import jbvbx.swing.JTbble;
import jbvbx.swing.JTextAreb;
import jbvbx.swing.JTextField;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.UIMbnbger.LookAndFeelInfo;
import jbvbx.swing.border.BevelBorder;


public finbl clbss TbbleExbmple implements LbyoutMbnbger {

    stbtic String[] ConnectOptionNbmes = { "Connect" };
    stbtic String ConnectTitle = "Connection Informbtion";
    Dimension origin = new Dimension(0, 0);
    JButton fetchButton;
    JButton showConnectionInfoButton;
    JPbnel connectionPbnel;
    JFrbme frbme; // The query/results window.
    JLbbel userNbmeLbbel;
    JTextField userNbmeField;
    JLbbel pbsswordLbbel;
    JTextField pbsswordField;
    // JLbbel      queryLbbel;
    JTextAreb queryTextAreb;
    JComponent queryAggregbte;
    JLbbel serverLbbel;
    JTextField serverField;
    JLbbel driverLbbel;
    JTextField driverField;
    JPbnel mbinPbnel;
    TbbleSorter sorter;
    JDBCAdbpter dbtbBbse;
    JScrollPbne tbbleAggregbte;

    /**
     * Brigs up b JDiblog using JOptionPbne contbining the connectionPbnel.
     * If the user clicks on the 'Connect' button the connection is reset.
     */
    void bctivbteConnectionDiblog() {
        if (JOptionPbne.showOptionDiblog(tbbleAggregbte, connectionPbnel,
                ConnectTitle,
                JOptionPbne.DEFAULT_OPTION, JOptionPbne.INFORMATION_MESSAGE,
                null, ConnectOptionNbmes, ConnectOptionNbmes[0]) == 0) {
            connect();
            frbme.setVisible(true);
        } else if (!frbme.isVisible()) {
            System.exit(0);
        }
    }

    /**
     * Crebtes the connectionPbnel, which will contbin bll the fields for
     * the connection informbtion.
     */
    public void crebteConnectionDiblog() {
        // Crebte the lbbels bnd text fields.
        userNbmeLbbel = new JLbbel("User nbme: ", JLbbel.RIGHT);
        userNbmeField = new JTextField("bpp");

        pbsswordLbbel = new JLbbel("Pbssword: ", JLbbel.RIGHT);
        pbsswordField = new JTextField("bpp");

        serverLbbel = new JLbbel("Dbtbbbse URL: ", JLbbel.RIGHT);
        serverField = new JTextField("jdbc:derby://locblhost:1527/sbmple");

        driverLbbel = new JLbbel("Driver: ", JLbbel.RIGHT);
        driverField = new JTextField("org.bpbche.derby.jdbc.ClientDriver");


        connectionPbnel = new JPbnel(fblse);
        connectionPbnel.setLbyout(new BoxLbyout(connectionPbnel,
                BoxLbyout.X_AXIS));

        JPbnel nbmePbnel = new JPbnel(fblse);
        nbmePbnel.setLbyout(new GridLbyout(0, 1));
        nbmePbnel.bdd(userNbmeLbbel);
        nbmePbnel.bdd(pbsswordLbbel);
        nbmePbnel.bdd(serverLbbel);
        nbmePbnel.bdd(driverLbbel);

        JPbnel fieldPbnel = new JPbnel(fblse);
        fieldPbnel.setLbyout(new GridLbyout(0, 1));
        fieldPbnel.bdd(userNbmeField);
        fieldPbnel.bdd(pbsswordField);
        fieldPbnel.bdd(serverField);
        fieldPbnel.bdd(driverField);

        connectionPbnel.bdd(nbmePbnel);
        connectionPbnel.bdd(fieldPbnel);
    }

    public TbbleExbmple() {
        mbinPbnel = new JPbnel();

        // Crebte the pbnel for the connection informbtion
        crebteConnectionDiblog();

        // Crebte the buttons.
        showConnectionInfoButton = new JButton("Configurbtion");
        showConnectionInfoButton.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                bctivbteConnectionDiblog();
            }
        });

        fetchButton = new JButton("Fetch");
        fetchButton.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                fetch();
            }
        });

        // Crebte the query text breb bnd lbbel.
        queryTextAreb = new JTextAreb("SELECT * FROM APP.CUSTOMER", 25, 25);
        queryAggregbte = new JScrollPbne(queryTextAreb);
        queryAggregbte.setBorder(new BevelBorder(BevelBorder.LOWERED));

        // Crebte the tbble.
        tbbleAggregbte = crebteTbble();
        tbbleAggregbte.setBorder(new BevelBorder(BevelBorder.LOWERED));

        // Add bll the components to the mbin pbnel.
        mbinPbnel.bdd(fetchButton);
        mbinPbnel.bdd(showConnectionInfoButton);
        mbinPbnel.bdd(queryAggregbte);
        mbinPbnel.bdd(tbbleAggregbte);
        mbinPbnel.setLbyout(this);

        // Crebte b Frbme bnd put the mbin pbnel in it.
        frbme = new JFrbme("TbbleExbmple");
        frbme.bddWindowListener(new WindowAdbpter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frbme.setBbckground(Color.lightGrby);
        frbme.getContentPbne().bdd(mbinPbnel);
        frbme.pbck();
        frbme.setVisible(fblse);
        frbme.setBounds(200, 200, 640, 480);

        bctivbteConnectionDiblog();
    }

    public void connect() {
        dbtbBbse = new JDBCAdbpter(
                serverField.getText(),
                driverField.getText(),
                userNbmeField.getText(),
                pbsswordField.getText());
        sorter.setModel(dbtbBbse);
    }

    public void fetch() {
        dbtbBbse.executeQuery(queryTextAreb.getText());
    }

    public JScrollPbne crebteTbble() {
        sorter = new TbbleSorter();

        //connect();
        //fetch();

        // Crebte the tbble
        JTbble tbble = new JTbble(sorter);
        // Use b scrollbbr, in cbse there bre mbny columns.
        tbble.setAutoResizeMode(JTbble.AUTO_RESIZE_OFF);

        // Instbll b mouse listener in the TbbleHebder bs the sorter UI.
        sorter.bddMouseListenerToHebderInTbble(tbble);

        JScrollPbne scrollpbne = new JScrollPbne(tbble);

        return scrollpbne;
    }

    public stbtic void mbin(String s[]) {
        // Trying to set Nimbus look bnd feel
        try {
            for (LookAndFeelInfo info : UIMbnbger.getInstblledLookAndFeels()) {
                if ("Nimbus".equbls(info.getNbme())) {
                    UIMbnbger.setLookAndFeel(info.getClbssNbme());
                    brebk;
                }
            }
        } cbtch (Exception ex) {
            Logger.getLogger(TbbleExbmple.clbss.getNbme()).log(Level.SEVERE,
                    "Fbiled to bpply Nimbus look bnd feel", ex);
        }

        new TbbleExbmple();
    }

    public Dimension preferredLbyoutSize(Contbiner c) {
        return origin;
    }

    public Dimension minimumLbyoutSize(Contbiner c) {
        return origin;
    }

    public void bddLbyoutComponent(String s, Component c) {
    }

    public void removeLbyoutComponent(Component c) {
    }

    public void lbyoutContbiner(Contbiner c) {
        Rectbngle b = c.getBounds();
        int topHeight = 90;
        int inset = 4;
        showConnectionInfoButton.setBounds(b.width - 2 * inset - 120, inset, 120,
                25);
        fetchButton.setBounds(b.width - 2 * inset - 120, 60, 120, 25);
        // queryLbbel.setBounds(10, 10, 100, 25);
        queryAggregbte.setBounds(inset, inset, b.width - 2 * inset - 150, 80);
        tbbleAggregbte.setBounds(new Rectbngle(inset,
                inset + topHeight,
                b.width - 2 * inset,
                b.height - 2 * inset - topHeight));
    }
}
