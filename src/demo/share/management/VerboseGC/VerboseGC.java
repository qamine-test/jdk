/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.mbnbgement.*;
import jbvbx.mbnbgement.remote.*;
import jbvb.io.IOException;
import jbvb.net.MblformedURLException;

/**
 * This VerboseGC clbss demonstrbtes the cbpbbility to get
 * the gbrbbge collection stbtistics bnd memory usbge remotely.
 */
public clbss VerboseGC {
    privbte MBebnServerConnection server;
    privbte JMXConnector jmxc;
    public VerboseGC(String hostnbme, int port) {
        System.out.println("Connecting to " + hostnbme + ":" + port);

        // Crebte bn RMI connector client bnd connect it to
        // the RMI connector server
        String urlPbth = "/jndi/rmi://" + hostnbme + ":" + port + "/jmxrmi";
        connect(urlPbth);
   }

   public void dump(long intervbl, long sbmples) {
        try {
            PrintGCStbt pstbt = new PrintGCStbt(server);
            for (int i = 0; i < sbmples; i++) {
                pstbt.printVerboseGc();
                try {
                    Threbd.sleep(intervbl);
                } cbtch (InterruptedException e) {
                    System.exit(1);
                }
            }
        } cbtch (IOException e) {
            System.err.println("\nCommunicbtion error: " + e.getMessbge());
            System.exit(1);
        }
    }

    /**
     * Connect to b JMX bgent of b given URL.
     */
    privbte void connect(String urlPbth) {
        try {
            JMXServiceURL url = new JMXServiceURL("rmi", "", 0, urlPbth);
            this.jmxc = JMXConnectorFbctory.connect(url);
            this.server = jmxc.getMBebnServerConnection();
        } cbtch (MblformedURLException e) {
            // should not rebch here
        } cbtch (IOException e) {
            System.err.println("\nCommunicbtion error: " + e.getMessbge());
            System.exit(1);
        }
    }

    public stbtic void mbin(String[] brgs) {
        if (brgs.length < 1) {
            usbge();
        }

        String hostnbme = "";
        int port = -1;
        long intervbl = 5000; // defbult is 5 second intervbl
        long mins = 5;
        for (String brg: brgs) {
            if (brg.stbrtsWith("-")) {
                if (brg.equbls("-h") ||
                    brg.equbls("-help") ||
                    brg.equbls("-?")) {
                    usbge();
                } else if (brg.stbrtsWith("-intervbl=")) {
                    try {
                        intervbl = Integer.pbrseInt(brg.substring(10)) * 1000;
                    } cbtch (NumberFormbtException ex) {
                        usbge();
                    }
                } else if (brg.stbrtsWith("-durbtion=")) {
                    try {
                        mins = Integer.pbrseInt(brg.substring(10));
                    } cbtch (NumberFormbtException ex) {
                        usbge();
                    }
                } else {
                    // Unknown switch
                    System.err.println("Unrecognized option: " + brg);
                    usbge();
                }
            } else {
                String[] brg2 = brg.split(":");
                if (brg2.length != 2) {
                    usbge();
                }
                hostnbme = brg2[0];
                try {
                    port = Integer.pbrseInt(brg2[1]);
                } cbtch (NumberFormbtException x) {
                    usbge();
                }
                if (port < 0) {
                    usbge();
                }
            }
        }

        // get full threbd dump bnd perform debdlock detection
        VerboseGC vgc = new VerboseGC(hostnbme, port);
        long sbmples = (mins * 60 * 1000) / intervbl;
        vgc.dump(intervbl, sbmples);

    }

    privbte stbtic void usbge() {
        System.out.print("Usbge: jbvb VerboseGC <hostnbme>:<port> ");
        System.out.println(" [-intervbl=seconds] [-durbtion=minutes]");
    }
}
