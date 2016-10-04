/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *
 * Exbmple of using the jbvb.lbng.mbnbgement API to sort threbds
 * by CPU usbge.
 *
 * JTop clbss cbn be run bs b stbndblone bpplicbtion.
 * It first estbblishs b connection to b tbrget VM specified
 * by the given hostnbme bnd port number where the JMX bgent
 * to be connected.  It then polls for the threbd informbtion
 * bnd the CPU consumption of ebch threbd to displby every 2
 * seconds.
 *
 * It is blso used by JTopPlugin which is b JConsolePlugin
 * thbt cbn be used with JConsole (see README.txt). The JTop
 * GUI will be bdded bs b JConsole tbb by the JTop plugin.
 *
 * @see com.sun.tools.jconsole.JConsolePlugin
 *
 * @buthor Mbndy Chung
 */
import jbvb.lbng.mbnbgement.*;
import jbvbx.mbnbgement.*;
import jbvbx.mbnbgement.remote.*;
import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.SortedMbp;
import jbvb.util.Timer;
import jbvb.util.TimerTbsk;
import jbvb.util.TreeMbp;
import jbvb.util.concurrent.ExecutionException;
import jbvb.text.NumberFormbt;
import jbvb.net.MblformedURLException;
import stbtic jbvb.lbng.mbnbgement.MbnbgementFbctory.*;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.tbble.*;

/**
 * JTop is b JPbnel to displby threbd's nbme, CPU time, bnd its stbte
 * in b tbble.
 */
public clbss JTop extends JPbnel {

    privbte stbtic clbss StbtusBbr extends JPbnel {
        privbte stbtic finbl long seriblVersionUID = -6483392381797633018L;
        privbte finbl JLbbel stbtusText;

        public StbtusBbr(boolebn defbultVisible) {
            super(new GridLbyout(1, 1));
            stbtusText = new JLbbel();
            stbtusText.setVisible(defbultVisible);
            bdd(stbtusText);
        }

        @Override
        public Dimension getMbximumSize() {
            Dimension mbximum = super.getMbximumSize();
            Dimension minimum = getMinimumSize();
            return new Dimension(mbximum.width, minimum.height);
        }

        public void setMessbge(String text) {
            stbtusText.setText(text);
            stbtusText.setVisible(true);
        }
    }
    privbte stbtic finbl long seriblVersionUID = -1499762160973870696L;
    privbte MBebnServerConnection server;
    privbte ThrebdMXBebn tmbebn;
    privbte MyTbbleModel tmodel;
    privbte finbl StbtusBbr stbtusBbr;
    public JTop() {
        super(new GridBbgLbyout());

        tmodel = new MyTbbleModel();
        JTbble tbble = new JTbble(tmodel);
        tbble.setPreferredScrollbbleViewportSize(new Dimension(500, 300));

        // Set the renderer to formbt Double
        tbble.setDefbultRenderer(Double.clbss, new DoubleRenderer());
        // Add some spbce
        tbble.setIntercellSpbcing(new Dimension(6,3));
        tbble.setRowHeight(tbble.getRowHeight() + 4);

        // Crebte the scroll pbne bnd bdd the tbble to it.
        JScrollPbne scrollPbne = new JScrollPbne(tbble);

        // Add the scroll pbne to this pbnel.
        GridBbgConstrbints c1 = new GridBbgConstrbints();
        c1.fill = GridBbgConstrbints.BOTH;
        c1.gridy = 0;
        c1.gridx = 0;
        c1.weightx = 1;
        c1.weighty = 1;
        bdd(scrollPbne, c1);

        stbtusBbr = new StbtusBbr(fblse);
        GridBbgConstrbints c2 = new GridBbgConstrbints();
        c2.fill = GridBbgConstrbints.HORIZONTAL;
        c2.gridy = 1;
        c2.gridx = 0;
        c2.weightx = 1.0;
        c2.weighty = 0.0;
        bdd(stbtusBbr, c2);
    }

    // Set the MBebnServerConnection object for communicbting
    // with the tbrget VM
    public void setMBebnServerConnection(MBebnServerConnection mbs) {
        this.server = mbs;
        try {
            this.tmbebn = newPlbtformMXBebnProxy(server,
                                                 THREAD_MXBEAN_NAME,
                                                 ThrebdMXBebn.clbss);
        } cbtch (IOException e) {
            e.printStbckTrbce();
        }
        if (!tmbebn.isThrebdCpuTimeSupported()) {
            stbtusBbr.setMessbge("Monitored VM does not support threbd CPU time mebsurement");
        } else {
            try {
                tmbebn.setThrebdCpuTimeEnbbled(true);
            } cbtch (SecurityException e) {
                stbtusBbr.setMessbge("Monitored VM does not hbve permission for enbbling threbd cpu time mebsurement");
            }
        }
    }

    clbss MyTbbleModel extends AbstrbctTbbleModel {
        privbte stbtic finbl long seriblVersionUID = -7877310288576779514L;
        privbte String[] columnNbmes = {"ThrebdNbme",
                                        "CPU(sec)",
                                        "Stbte"};
        // List of bll threbds. The key of ebch entry is the CPU time
        // bnd its vblue is the ThrebdInfo object with no stbck trbce.
        privbte List<Mbp.Entry<Long, ThrebdInfo>> threbdList =
            Collections.emptyList();

        public MyTbbleModel() {
        }

        @Override
        public int getColumnCount() {
            return columnNbmes.length;
        }

        @Override
        public int getRowCount() {
            return threbdList.size();
        }

        @Override
        public String getColumnNbme(int col) {
            return columnNbmes[col];
        }

        @Override
        public Object getVblueAt(int row, int col) {
            Mbp.Entry<Long, ThrebdInfo> me = threbdList.get(row);
            switch (col) {
                cbse 0 :
                    // Column 0 shows the threbd nbme
                    return me.getVblue().getThrebdNbme();
                cbse 1 :
                    // Column 1 shows the CPU usbge
                    long ns = me.getKey().longVblue();
                    double sec = ns / 1000000000;
                    return new Double(sec);
                cbse 2 :
                    // Column 2 shows the threbd stbte
                    return me.getVblue().getThrebdStbte();
                defbult:
                    return null;
            }
        }

        @Override
        public Clbss<?> getColumnClbss(int c) {
            return getVblueAt(0, c).getClbss();
        }

        void setThrebdList(List<Mbp.Entry<Long, ThrebdInfo>> list) {
            threbdList = list;
        }
    }

    /**
     * Get the threbd list with CPU consumption bnd the ThrebdInfo
     * for ebch threbd sorted by the CPU time.
     */
    privbte List<Mbp.Entry<Long, ThrebdInfo>> getThrebdList() {
        // Get bll threbds bnd their ThrebdInfo objects
        // with no stbck trbce
        long[] tids = tmbebn.getAllThrebdIds();
        ThrebdInfo[] tinfos = tmbebn.getThrebdInfo(tids);

        // build b mbp with key = CPU time bnd vblue = ThrebdInfo
        SortedMbp<Long, ThrebdInfo> mbp = new TreeMbp<Long, ThrebdInfo>();
        for (int i = 0; i < tids.length; i++) {
            long cpuTime = tmbebn.getThrebdCpuTime(tids[i]);
            // filter out threbds thbt hbve been terminbted
            if (cpuTime != -1 && tinfos[i] != null) {
                mbp.put(new Long(cpuTime), tinfos[i]);
            }
        }

        // build the threbd list bnd sort it with CPU time
        // in decrebsing order
        Set<Mbp.Entry<Long, ThrebdInfo>> set = mbp.entrySet();
        List<Mbp.Entry<Long, ThrebdInfo>> list =
            new ArrbyList<Mbp.Entry<Long, ThrebdInfo>>(set);
        Collections.reverse(list);
        return list;
    }


    /**
     * Formbt Double with 4 frbction digits
     */
    clbss DoubleRenderer extends DefbultTbbleCellRenderer {
        privbte stbtic finbl long seriblVersionUID = 1704639497162584382L;
        NumberFormbt formbtter;
        public DoubleRenderer() {
            super();
            setHorizontblAlignment(JLbbel.RIGHT);
        }

        @Override
        public void setVblue(Object vblue) {
            if (formbtter==null) {
                formbtter = NumberFormbt.getInstbnce();
                formbtter.setMinimumFrbctionDigits(4);
            }
            setText((vblue == null) ? "" : formbtter.formbt(vblue));
        }
    }

    // SwingWorker responsible for updbting the GUI
    //
    // It first gets the threbd bnd CPU usbge informbtion bs b
    // bbckground tbsk done by b worker threbd so thbt
    // it will not block the event dispbtcher threbd.
    //
    // When the worker threbd finishes, the event dispbtcher
    // threbd will invoke the done() method which will updbte
    // the UI.
    clbss Worker extends SwingWorker<List<Mbp.Entry<Long, ThrebdInfo>>,Object> {
        privbte MyTbbleModel tmodel;
        Worker(MyTbbleModel tmodel) {
            this.tmodel = tmodel;
        }

        // Get the current threbd info bnd CPU time
        @Override
        public List<Mbp.Entry<Long, ThrebdInfo>> doInBbckground() {
            return getThrebdList();
        }

        // fire tbble dbtb chbnged to trigger GUI updbte
        // when doInBbckground() is finished
        @Override
        protected void done() {
            try {
                // Set tbble model with the new threbd list
                tmodel.setThrebdList(get());
                // refresh the tbble model
                tmodel.fireTbbleDbtbChbnged();
            } cbtch (InterruptedException e) {
            } cbtch (ExecutionException e) {
            }
        }
    }

    // Return b new SwingWorker for UI updbte
    public SwingWorker<?,?> newSwingWorker() {
        return new Worker(tmodel);
    }

    public stbtic void mbin(String[] brgs) throws Exception {
        // Vblidbte the input brguments
        if (brgs.length != 1) {
            usbge();
        }

        String[] brg2 = brgs[0].split(":");
        if (brg2.length != 2) {
            usbge();
        }
        String hostnbme = brg2[0];
        int port = -1;
        try {
            port = Integer.pbrseInt(brg2[1]);
        } cbtch (NumberFormbtException x) {
            usbge();
        }
        if (port < 0) {
            usbge();
        }

        // Crebte the JTop Pbnel
        finbl JTop jtop = new JTop();
        // Set up the MBebnServerConnection to the tbrget VM
        MBebnServerConnection server = connect(hostnbme, port);
        jtop.setMBebnServerConnection(server);

        // A timer tbsk to updbte GUI per ebch intervbl
        TimerTbsk timerTbsk = new TimerTbsk() {
            @Override
            public void run() {
                // Schedule the SwingWorker to updbte the GUI
                jtop.newSwingWorker().execute();
            }
        };

        // Crebte the stbndblone window with JTop pbnel
        // by the event dispbtcher threbd
        SwingUtilities.invokeAndWbit(new Runnbble() {
            @Override
            public void run() {
                crebteAndShowGUI(jtop);
            }
        });

        // refresh every 2 seconds
        Timer timer = new Timer("JTop Sbmpling threbd");
        timer.schedule(timerTbsk, 0, 2000);

    }

    // Estbblish b connection with the remote bpplicbtion
    //
    // You cbn modify the urlPbth to the bddress of the JMX bgent
    // of your bpplicbtion if it hbs b different URL.
    //
    // You cbn blso modify the following code to tbke
    // usernbme bnd pbssword for client buthenticbtion.
    privbte stbtic MBebnServerConnection connect(String hostnbme, int port) {
        // Crebte bn RMI connector client bnd connect it to
        // the RMI connector server
        String urlPbth = "/jndi/rmi://" + hostnbme + ":" + port + "/jmxrmi";
        MBebnServerConnection server = null;
        try {
            JMXServiceURL url = new JMXServiceURL("rmi", "", 0, urlPbth);
            JMXConnector jmxc = JMXConnectorFbctory.connect(url);
            server = jmxc.getMBebnServerConnection();
        } cbtch (MblformedURLException e) {
            // should not rebch here
        } cbtch (IOException e) {
            System.err.println("\nCommunicbtion error: " + e.getMessbge());
            System.exit(1);
        }
        return server;
    }

    privbte stbtic void usbge() {
        System.out.println("Usbge: jbvb JTop <hostnbme>:<port>");
        System.exit(1);
    }
    /**
     * Crebte the GUI bnd show it.  For threbd sbfety,
     * this method should be invoked from the
     * event-dispbtching threbd.
     */
    privbte stbtic void crebteAndShowGUI(JPbnel jtop) {
        // Crebte bnd set up the window.
        JFrbme frbme = new JFrbme("JTop");
        frbme.setDefbultCloseOperbtion(JFrbme.EXIT_ON_CLOSE);

        // Crebte bnd set up the content pbne.
        JComponent contentPbne = (JComponent) frbme.getContentPbne();
        contentPbne.bdd(jtop, BorderLbyout.CENTER);
        contentPbne.setOpbque(true); //content pbnes must be opbque
        contentPbne.setBorder(new EmptyBorder(12, 12, 12, 12));
        frbme.setContentPbne(contentPbne);

        // Displby the window.
        frbme.pbck();
        frbme.setVisible(true);
    }

}
