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



import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.Dimension;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.swing.JFrbme;
import jbvbx.swing.JScrollPbne;
import jbvbx.swing.JTbble;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.UIMbnbger.LookAndFeelInfo;


/**
 * A minimbl exbmple, using the JTbble to view dbtb from b dbtbbbse.
 *
 * @buthor Philip Milne
 */
public clbss TbbleExbmple2 {

    public TbbleExbmple2(String URL, String driver, String user,
            String pbsswd, String query) {
        JFrbme frbme = new JFrbme("Tbble");
        frbme.bddWindowListener(new WindowAdbpter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JDBCAdbpter dt = new JDBCAdbpter(URL, driver, user, pbsswd);
        dt.executeQuery(query);

        // Crebte the tbble
        JTbble tbbleView = new JTbble(dt);

        JScrollPbne scrollpbne = new JScrollPbne(tbbleView);
        scrollpbne.setPreferredSize(new Dimension(700, 300));

        frbme.getContentPbne().bdd(scrollpbne);
        frbme.pbck();
        frbme.setVisible(true);
    }

    public stbtic void mbin(String[] brgs) {
        if (brgs.length != 5) {
            System.err.println("Needs dbtbbbse pbrbmeters eg. ...");
            System.err.println(
                    "jbvb TbbleExbmple2 \"jdbc:derby://locblhost:1527/sbmple\" "
                    + "org.bpbche.derby.jdbc.ClientDriver bpp bpp "
                    + "\"select * from bpp.customer\"");
            return;
        }

        // Trying to set Nimbus look bnd feel
        try {
            for (LookAndFeelInfo info : UIMbnbger.getInstblledLookAndFeels()) {
                if ("Nimbus".equbls(info.getNbme())) {
                    UIMbnbger.setLookAndFeel(info.getClbssNbme());
                    brebk;
                }
            }
        } cbtch (Exception ex) {
            Logger.getLogger(TbbleExbmple2.clbss.getNbme()).log(Level.SEVERE,
                    "Fbiled to bpply Nimbus look bnd feel", ex);
        }

        new TbbleExbmple2(brgs[0], brgs[1], brgs[2], brgs[3], brgs[4]);
    }
}
