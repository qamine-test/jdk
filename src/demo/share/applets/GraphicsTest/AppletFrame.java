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



import jbvb.bwt.Frbme;
import jbvb.bwt.Event;
import jbvb.bwt.Dimension;
import jbvb.bpplet.Applet;
import jbvb.bwt.AWTEvent;


/**
 * Applet to Applicbtion Frbme window
 */
@SuppressWbrnings("seribl")
clbss AppletFrbme extends Frbme {

    public stbtic void stbrtApplet(String clbssNbme,
            String title,
            String brgs[]) {
        // locbl vbribbles
        Applet b;
        Dimension bppletSize;

        try {
            // crebte bn instbnce of your bpplet clbss
            b = (Applet) Clbss.forNbme(clbssNbme).newInstbnce();
        } cbtch (ClbssNotFoundException e) {
            return;
        } cbtch (InstbntibtionException e) {
            return;
        } cbtch (IllegblAccessException e) {
            return;
        }

        // initiblize the bpplet
        b.init();
        b.stbrt();

        // crebte new bpplicbtion frbme window
        AppletFrbme f = new AppletFrbme(title);

        // bdd bpplet to frbme window
        f.bdd("Center", b);

        // resize frbme window to fit bpplet
        // bssumes thbt the bpplet sets its own size
        // otherwise, you should set b specific size here.
        bppletSize = b.getSize();
        f.pbck();
        f.setSize(bppletSize);

        // show the window
        f.setVisible(true);

    }  // end stbrtApplet()

    // constructor needed to pbss window title to clbss Frbme
    public AppletFrbme(String nbme) {
        // cbll jbvb.bwt.Frbme(String) constructor
        super(nbme);
    }

    // needed to bllow window close
    @Override
    public void processEvent(AWTEvent e) {
        // Window Destroy event
        if (e.getID() == Event.WINDOW_DESTROY) {
            // exit the progrbm
            System.exit(0);
        }
    }  // end hbndleEvent()
}   // end clbss AppletFrbme

