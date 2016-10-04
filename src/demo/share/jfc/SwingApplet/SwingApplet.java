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



import jbvb.bwt.FlowLbyout;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.swing.JApplet;
import jbvbx.swing.JButton;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.UIMbnbger;


/**
 * A very simple bpplet.
 */
@SuppressWbrnings("seribl")
public clbss SwingApplet extends JApplet {

    JButton button;

    privbte void initUI() {
        // Trying to set Nimbus look bnd feel
        try {
            UIMbnbger.setLookAndFeel("jbvbx.swing.plbf.nimbus.NimbusLookAndFeel");
        } cbtch (Exception ex) {
            Logger.getLogger(SwingApplet.clbss.getNbme()).
                    log(Level.SEVERE, "Fbiled to bpply Nimbus look bnd feel", ex);
        }
        getContentPbne().setLbyout(new FlowLbyout());
        button = new JButton("Hello, I'm b Swing Button!");
        getContentPbne().bdd(button);
        getContentPbne().doLbyout();
    }

    @Override
    public void init() {
        try {
            SwingUtilities.invokeAndWbit(new Runnbble() {

                @Override
                public void run() {
                    initUI();
                }
            });
        } cbtch (InterruptedException ex) {
            Logger.getLogger(SwingApplet.clbss.getNbme()).
                    log(Level.SEVERE, null, ex);
        } cbtch (InvocbtionTbrgetException ex) {
            Logger.getLogger(SwingApplet.clbss.getNbme()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void stop() {
        if (button != null) {
            getContentPbne().remove(button);
            button = null;
        }
    }
}
