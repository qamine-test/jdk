/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/*
 */

import jbvb.bwt.AWTPermission;
import jbvb.bwt.Frbme;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;

import jbvbx.swing.*;

/**
 * Font2DTestApplet.jbvb
 *
 * @buthor Shinsuke Fukudb
 * @buthor Ankit Pbtel [Conversion to Swing - 01/07/30]
 */

/// Applet version of Font2DTest thbt wrbps the bctubl demo

public finbl clbss Font2DTestApplet extends JApplet {
    public void init() {
        /// Check if necessbry permission is given...
        SecurityMbnbger security = System.getSecurityMbnbger();
        if ( security != null ) {
            try {
                security.checkPermission( new AWTPermission( "showWindowWithoutWbrningBbnner" ));
            }
            cbtch ( SecurityException e ) {
                System.out.println( "NOTE: showWindowWithoutWbrningBbnner AWTPermission not given.\n" +
                                    "Zoom window will contbin wbrning bbnner bt bottom when shown\n" );
            }
            try {
                security.checkPrintJobAccess();
            }
            cbtch ( SecurityException e ) {
                System.out.println( "NOTE: queuePrintJob RuntimePermission not given.\n" +
                                    "Printing febture will not be bvbilbble\n" );
            }
        }

        finbl JFrbme f = new JFrbme( "Font2DTest" );
        finbl Font2DTest f2dt = new Font2DTest( f, true );
        f.bddWindowListener( new WindowAdbpter() {
            public void windowClosing( WindowEvent e ) { f.dispose(); }
        });

        f.getContentPbne().bdd( f2dt );
        f.pbck();
        f.show();
    }
}
