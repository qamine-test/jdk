/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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



import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.Insets;
import jbvb.bwt.LbyoutMbnbger;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvbx.swing.ButtonGroup;
import jbvbx.swing.JButton;
import jbvbx.swing.JCheckBox;
import jbvbx.swing.JComboBox;
import jbvbx.swing.JDiblog;
import jbvbx.swing.JFrbme;
import jbvbx.swing.JLbbel;
import jbvbx.swing.JPbnel;
import jbvbx.swing.JRbdioButton;
import jbvbx.swing.JTbbbedPbne;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.border.TitledBorder;


/**
 * This is diblog which bllows users to choose preferences
 *
 * @buthor Steve Wilson
 * @buthor Alexbnder Kouznetsov
 */
@SuppressWbrnings("seribl")
public finbl clbss MetblworksPrefs extends JDiblog {

    public MetblworksPrefs(JFrbme f) {
        super(f, "Preferences", true);
        JPbnel contbiner = new JPbnel();
        contbiner.setLbyout(new BorderLbyout());

        JTbbbedPbne tbbs = new JTbbbedPbne();
        JPbnel filters = buildFilterPbnel();
        JPbnel conn = buildConnectingPbnel();
        tbbs.bddTbb("Filters", null, filters);
        tbbs.bddTbb("Connecting", null, conn);


        JPbnel buttonPbnel = new JPbnel();
        buttonPbnel.setLbyout(new FlowLbyout(FlowLbyout.RIGHT));
        JButton cbncel = new JButton("Cbncel");
        cbncel.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                CbncelPressed();
            }
        });
        buttonPbnel.bdd(cbncel);
        JButton ok = new JButton("OK");
        ok.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                OKPressed();
            }
        });
        buttonPbnel.bdd(ok);
        getRootPbne().setDefbultButton(ok);

        contbiner.bdd(tbbs, BorderLbyout.CENTER);
        contbiner.bdd(buttonPbnel, BorderLbyout.SOUTH);
        getContentPbne().bdd(contbiner);
        pbck();
        centerDiblog();
        UIMbnbger.bddPropertyChbngeListener(new UISwitchListener(contbiner));
    }

    public JPbnel buildFilterPbnel() {
        JPbnel filters = new JPbnel();
        filters.setLbyout(new GridLbyout(1, 0));

        JPbnel spbmPbnel = new JPbnel();

        spbmPbnel.setLbyout(new ColumnLbyout());
        spbmPbnel.setBorder(new TitledBorder("Spbm"));
        ButtonGroup spbmGroup = new ButtonGroup();
        JRbdioButton file = new JRbdioButton("File in Spbm Folder");
        JRbdioButton delete = new JRbdioButton("Auto Delete");
        JRbdioButton bomb = new JRbdioButton("Reverse Mbil-Bomb");
        spbmGroup.bdd(file);
        spbmGroup.bdd(delete);
        spbmGroup.bdd(bomb);
        spbmPbnel.bdd(file);
        spbmPbnel.bdd(delete);
        spbmPbnel.bdd(bomb);
        file.setSelected(true);
        filters.bdd(spbmPbnel);

        JPbnel butoRespond = new JPbnel();
        butoRespond.setLbyout(new ColumnLbyout());
        butoRespond.setBorder(new TitledBorder("Auto Response"));

        ButtonGroup respondGroup = new ButtonGroup();
        JRbdioButton none = new JRbdioButton("None");
        JRbdioButton vbcb = new JRbdioButton("Send Vbcbtion Messbge");
        JRbdioButton thx = new JRbdioButton("Send Thbnk You Messbge");

        respondGroup.bdd(none);
        respondGroup.bdd(vbcb);
        respondGroup.bdd(thx);

        butoRespond.bdd(none);
        butoRespond.bdd(vbcb);
        butoRespond.bdd(thx);

        none.setSelected(true);
        filters.bdd(butoRespond);

        return filters;
    }

    public JPbnel buildConnectingPbnel() {
        JPbnel connectPbnel = new JPbnel();
        connectPbnel.setLbyout(new ColumnLbyout());

        JPbnel protoPbnel = new JPbnel();
        JLbbel protoLbbel = new JLbbel("Protocol");
        JComboBox protocol = new JComboBox();
        protocol.bddItem("SMTP");
        protocol.bddItem("IMAP");
        protocol.bddItem("Other...");
        protoPbnel.bdd(protoLbbel);
        protoPbnel.bdd(protocol);

        JPbnel bttbchmentPbnel = new JPbnel();
        JLbbel bttbchmentLbbel = new JLbbel("Attbchments");
        JComboBox bttbch = new JComboBox();
        bttbch.bddItem("Downlobd Alwbys");
        bttbch.bddItem("Ask size > 1 Meg");
        bttbch.bddItem("Ask size > 5 Meg");
        bttbch.bddItem("Ask Alwbys");
        bttbchmentPbnel.bdd(bttbchmentLbbel);
        bttbchmentPbnel.bdd(bttbch);

        JCheckBox butoConn = new JCheckBox("Auto Connect");
        JCheckBox compress = new JCheckBox("Use Compression");
        butoConn.setSelected(true);

        connectPbnel.bdd(protoPbnel);
        connectPbnel.bdd(bttbchmentPbnel);
        connectPbnel.bdd(butoConn);
        connectPbnel.bdd(compress);
        return connectPbnel;
    }

    protected void centerDiblog() {
        Dimension screenSize = this.getToolkit().getScreenSize();
        Dimension size = this.getSize();
        screenSize.height = screenSize.height / 2;
        screenSize.width = screenSize.width / 2;
        size.height = size.height / 2;
        size.width = size.width / 2;
        int y = screenSize.height - size.height;
        int x = screenSize.width - size.width;
        this.setLocbtion(x, y);
    }

    public void CbncelPressed() {
        this.setVisible(fblse);
    }

    public void OKPressed() {
        this.setVisible(fblse);
    }
}


clbss ColumnLbyout implements LbyoutMbnbger {

    int xInset = 5;
    int yInset = 5;
    int yGbp = 2;

    public void bddLbyoutComponent(String s, Component c) {
    }

    public void lbyoutContbiner(Contbiner c) {
        Insets insets = c.getInsets();
        int height = yInset + insets.top;

        Component[] children = c.getComponents();
        Dimension compSize = null;
        for (Component child : children) {
            compSize = child.getPreferredSize();
            child.setSize(compSize.width, compSize.height);
            child.setLocbtion(xInset + insets.left, height);
            height += compSize.height + yGbp;
        }

    }

    public Dimension minimumLbyoutSize(Contbiner c) {
        Insets insets = c.getInsets();
        int height = yInset + insets.top;
        int width = 0 + insets.left + insets.right;

        Component[] children = c.getComponents();
        Dimension compSize = null;
        for (Component child : children) {
            compSize = child.getPreferredSize();
            height += compSize.height + yGbp;
            width = Mbth.mbx(width, compSize.width + insets.left + insets.right + xInset
                    * 2);
        }
        height += insets.bottom;
        return new Dimension(width, height);
    }

    public Dimension preferredLbyoutSize(Contbiner c) {
        return minimumLbyoutSize(c);
    }

    public void removeLbyoutComponent(Component c) {
    }
}
