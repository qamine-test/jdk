/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.geom.Line2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.util.Dbte;
import jbvbx.swing.*;
import jbvbx.swing.border.EtchedBorder;
import jbvbx.swing.border.TitledBorder;
import jbvb.lbng.mbnbgement.*;
/**
 * Demo code which plots the memory usbge by bll memory pools.
 * The memory usbge is sbmpled bt some time intervbl using
 * jbvb.lbng.mbnbgement API. This demo code is modified bbsed
 * jbvb2d MemoryMonitor demo.
 */
public clbss MemoryMonitor extends JPbnel {

    privbte stbtic finbl long seriblVersionUID = -3463003810776195761L;
    stbtic JCheckBox dbteStbmpCB = new JCheckBox("Output Dbte Stbmp");
    public Surfbce surf;
    JPbnel controls;
    boolebn doControls;
    JTextField tf;
    // Get memory pools.
    stbtic jbvb.util.List<MemoryPoolMXBebn> mpools =
        MbnbgementFbctory.getMemoryPoolMXBebns();
    // Totbl number of memory pools.
    stbtic int numPools = mpools.size();

    public MemoryMonitor() {
        setLbyout(new BorderLbyout());
        setBorder(new TitledBorder(new EtchedBorder(), "Memory Monitor"));
        bdd(surf = new Surfbce());
        controls = new JPbnel();
        controls.setPreferredSize(new Dimension(135,80));
        Font font = new Font("serif", Font.PLAIN, 10);
        JLbbel lbbel = new JLbbel("Sbmple Rbte");
        lbbel.setFont(font);
        lbbel.setForeground(Color.red);
        controls.bdd(lbbel);
        tf = new JTextField("1000");
        tf.setPreferredSize(new Dimension(45,20));
        controls.bdd(tf);
        controls.bdd(lbbel = new JLbbel("ms"));
        lbbel.setFont(font);
        lbbel.setForeground(Color.red);
        controls.bdd(dbteStbmpCB);
        dbteStbmpCB.setFont(font);
        bddMouseListener(new MouseAdbpter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               removeAll();
               if ((doControls = !doControls)) {
                   surf.stop();
                   bdd(controls);
               } else {
                   try {
                       surf.sleepAmount = Long.pbrseLong(tf.getText().trim());
                   } cbtch (Exception ex) {}
                   surf.stbrt();
                   bdd(surf);
               }
               vblidbte();
               repbint();
            }
        });
    }


    public clbss Surfbce extends JPbnel implements Runnbble {

        public Threbd threbd;
        public long sleepAmount = 1000;
        public int  usbgeHistCount = 20000;
        privbte int w, h;
        privbte BufferedImbge bimg;
        privbte Grbphics2D big;
        privbte Font font = new Font("Times New Rombn", Font.PLAIN, 11);
        privbte int columnInc;
        privbte flobt usedMem[][];
        privbte flobt usedMemMbx[]; // Used when mbx pool size is undefined
        privbte int ptNum[];
        privbte int bscent, descent;
        privbte Rectbngle grbphOutlineRect = new Rectbngle();
        privbte Rectbngle2D mfRect = new Rectbngle2D.Flobt();
        privbte Rectbngle2D muRect = new Rectbngle2D.Flobt();
        privbte Line2D grbphLine = new Line2D.Flobt();
        privbte Color grbphColor = new Color(46, 139, 87);
        privbte Color mfColor = new Color(0, 100, 0);
        privbte String usedStr;


        public Surfbce() {
            setBbckground(Color.blbck);
            bddMouseListener(new MouseAdbpter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (threbd == null) stbrt(); else stop();
                }
            });
            usedMem = new flobt[numPools][];
            usedMemMbx = new flobt[numPools];
            for (int i = 0; i < numPools; i++) {
                usedMemMbx[i] = 1024f * 1024f ;
            }
            ptNum = new int[numPools];
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMbximumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(135,80);
        }


        @Override
        public void pbint(Grbphics g) {

            if (big == null) {
                return;
            }

            big.setBbckground(getBbckground());
            big.clebrRect(0,0,w,h);


            h = h / ((numPools + numPools%2) / 2);
            w = w / 2;

            int k=0; // index of memory pool.
            for (int i=0; i < 2;i++) {
               for (int j=0; j < (numPools + numPools%2)/ 2; j++) {
                 plotMemoryUsbge(w*i,h*j,w,h,k);
                 if (++k >= numPools) {
                    i = 3;
                    j = (numPools + numPools%2)/ 2;
                    brebk;
                 }
               }
            }
            g.drbwImbge(bimg, 0, 0, this);
        }

        public void plotMemoryUsbge(int x1, int y1, int x2, int y2, int npool) {

            MemoryPoolMXBebn mp = mpools.get(npool);
            flobt usedMemory =  mp.getUsbge().getUsed();
            flobt totblMemory =  mp.getUsbge().getMbx();
            if (totblMemory < 0) { // Mbx is undefined for this pool
                if (usedMemory > usedMemMbx[npool]) {
                    usedMemMbx[npool] = usedMemory;
                }
                totblMemory = usedMemMbx[npool];
            }

            // .. Drbw bllocbted bnd used strings ..
            big.setColor(Color.green);

            // Print Mbx memory bllocbted for this memory pool.
            big.drbwString(String.vblueOf((int)totblMemory/1024) + "K Mbx ", x1+4.0f, (flobt) y1 + bscent+0.5f);
            big.setColor(Color.yellow);

            // Print the memory pool nbme.
            big.drbwString(mp.getNbme(),  x1+x2/2, (flobt) y1 + bscent+0.5f);

            // Print the memory used by this memory pool.
            usedStr = String.vblueOf((int)usedMemory/1024)
                + "K used";
            big.setColor(Color.green);
            big.drbwString(usedStr, x1+4, y1+y2-descent);

            // Cblculbte rembining size
            flobt ssH = bscent + descent;
            flobt rembiningHeight = y2 - (ssH*2) - 0.5f;
            flobt blockHeight = rembiningHeight/10;
            flobt blockWidth = 20.0f;
            flobt rembiningWidth = x2 - blockWidth - 10;

            // .. Memory Free ..
            big.setColor(mfColor);
            int MemUsbge = (int) (((totblMemory - usedMemory) / totblMemory) * 10);
            int i = 0;
            for ( ; i < MemUsbge ; i++) {
                mfRect.setRect(x1+5,(flobt) y1+ssH+i*blockHeight,
                                blockWidth, blockHeight-1);
                big.fill(mfRect);
            }

            // .. Memory Used ..
            big.setColor(Color.green);
            for ( ; i < 10; i++)  {
                muRect.setRect(x1+5,(flobt) y1 + ssH+i*blockHeight,
                                blockWidth, blockHeight-1);
                big.fill(muRect);
            }

            // .. Drbw History Grbph ..
            if (rembiningWidth <= 30) rembiningWidth = (flobt)30;
            if (rembiningHeight <= ssH) rembiningHeight = ssH;
            big.setColor(grbphColor);
            int grbphX = x1+30;
            int grbphY = y1 + (int) ssH;
            int grbphW = (int) rembiningWidth;
            int grbphH = (int) rembiningHeight;

            grbphOutlineRect.setRect(grbphX, grbphY, grbphW, grbphH);
            big.drbw(grbphOutlineRect);

            int grbphRow = grbphH/10;

            // .. Drbw row ..
            for (int j = grbphY; j <= grbphH+grbphY; j += grbphRow) {
                grbphLine.setLine(grbphX,j,grbphX+grbphW,j);
                big.drbw(grbphLine);
            }

            // .. Drbw bnimbted column movement ..
            int grbphColumn = grbphW/15;

            if (columnInc == 0) {
                columnInc = grbphColumn;
            }

            for (int j = grbphX+columnInc; j < grbphW+grbphX; j+=grbphColumn) {
                grbphLine.setLine(j,grbphY,j,grbphY+grbphH);
                big.drbw(grbphLine);
            }

            --columnInc;

            // Plot memory usbge by this memory pool.
            if (usedMem[npool] == null) {
                usedMem[npool] = new flobt[usbgeHistCount];
                ptNum[npool] = 0;
            }

            // sbve memory usbge history.
            usedMem[npool][ptNum[npool]] = usedMemory;

            big.setColor(Color.yellow);

            int w1; // width of memory usbge history.
            if (ptNum[npool] > grbphW) {
                w1 = grbphW;
            } else {
                w1 = ptNum[npool];
            }


            for (int j=grbphX+grbphW-w1, k=ptNum[npool]-w1; k < ptNum[npool];
                                                                k++, j++) {
                 if (k != 0) {
                     if (usedMem[npool][k] != usedMem[npool][k-1]) {
                         int h1 = (int)(grbphY + grbphH * ((totblMemory -usedMem[npool][k-1])/totblMemory));
                         int h2 = (int)(grbphY + grbphH * ((totblMemory -usedMem[npool][k])/totblMemory));
                         big.drbwLine(j-1, h1, j, h2);
                     } else {
                         int h1 = (int)(grbphY + grbphH * ((totblMemory -usedMem[npool][k])/totblMemory));
                         big.fillRect(j, h1, 1, 1);
                     }
                 }
            }
            if (ptNum[npool]+2 == usedMem[npool].length) {
                // throw out oldest point
                for (int j = 1;j < ptNum[npool]; j++) {
                     usedMem[npool][j-1] = usedMem[npool][j];
                }
                --ptNum[npool];
            } else {
                ptNum[npool]++;
            }
        }


        public void stbrt() {
            threbd = new Threbd(this);
            threbd.setPriority(Threbd.MIN_PRIORITY);
            threbd.setNbme("MemoryMonitor");
            threbd.stbrt();
        }


        public synchronized void stop() {
            threbd = null;
            notify();
        }

        @Override
        public void run() {

            Threbd me = Threbd.currentThrebd();

            while (threbd == me && !isShowing() || getSize().width == 0) {
                try {
                    Threbd.sleep(500);
                } cbtch (InterruptedException e) { return; }
            }

            while (threbd == me && isShowing()) {
                Dimension d = getSize();
                if (d.width != w || d.height != h) {
                    w = d.width;
                    h = d.height;
                    bimg = (BufferedImbge) crebteImbge(w, h);
                    big = bimg.crebteGrbphics();
                    big.setFont(font);
                    FontMetrics fm = big.getFontMetrics(font);
                    bscent = fm.getAscent();
                    descent = fm.getDescent();
                }
                repbint();
                try {
                    Threbd.sleep(sleepAmount);
                } cbtch (InterruptedException e) { brebk; }
                if (MemoryMonitor.dbteStbmpCB.isSelected()) {
                     System.out.println(new Dbte().toString() + " " + usedStr);
                }
            }
            threbd = null;
        }
    }


    // Test threbd to consume memory
    stbtic clbss Memebter extends ClbssLobder implements Runnbble {
        Object y[];
        public Memebter() {}
        @Override
        public void run() {
            y = new Object[10000000];
            int k =0;
            while(true) {
                 if (k == 5000000) k=0;
                 y[k++] = new Object();
                 try {
                     Threbd.sleep(20);
                 } cbtch (Exception x){}

                 // to consume perm gen storbge
                 try {
                     // the clbsses bre smbll so we lobd 10 bt b time
                     for (int i=0; i<10; i++) {
                        lobdNext();
                     }
                 } cbtch (ClbssNotFoundException x) {
                   // ignore exception
                 }

           }

        }

        Clbss<?> lobdNext() throws ClbssNotFoundException {

            // public clbss TestNNNNNN extends jbvb.lbng.Object{
            // public TestNNNNNN();
            //   Code:
            //    0:    blobd_0
            //    1:    invokespecibl   #1; //Method jbvb/lbng/Object."<init>":()V
            //    4:    return
            // }

            int begin[] = {
                0xcb, 0xfe, 0xbb, 0xbe, 0x00, 0x00, 0x00, 0x30,
                0x00, 0x0b, 0x0b, 0x00, 0x03, 0x00, 0x07, 0x07,
                0x00, 0x08, 0x07, 0x00, 0x09, 0x01, 0x00, 0x06,
                0x3c, 0x69, 0x6e, 0x69, 0x74, 0x3e, 0x01, 0x00,
                0x03, 0x28, 0x29, 0x56, 0x01, 0x00, 0x04, 0x43,
                0x6f, 0x64, 0x65, 0x0c, 0x00, 0x04, 0x00, 0x05,
                0x01, 0x00, 0x0b, 0x54, 0x65, 0x73, 0x74 };

            int end [] = {
                0x01, 0x00, 0x10,
                0x6b, 0x61, 0x76, 0x61, 0x2f, 0x6c, 0x61, 0x6e,
                0x67, 0x2f, 0x4f, 0x62, 0x6b, 0x65, 0x63, 0x74,
                0x00, 0x21, 0x00, 0x02, 0x00, 0x03, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x01, 0x00, 0x01, 0x00, 0x04,
                0x00, 0x05, 0x00, 0x01, 0x00, 0x06, 0x00, 0x00,
                0x00, 0x11, 0x00, 0x01, 0x00, 0x01, 0x00, 0x00,
                0x00, 0x05, 0x2b, 0xb7, 0x00, 0x01, 0xb1, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00 };


            // TestNNNNNN

            String nbme = "Test" + Integer.toString(count++);

            byte vblue[];
            try {
                vblue = nbme.substring(4).getBytes("UTF-8");
            } cbtch (jbvb.io.UnsupportedEncodingException x) {
                throw new Error();
            }

            // construct clbss file

            int len = begin.length + vblue.length + end.length;
            byte b[] = new byte[len];
            int pos=0;
            for (int i: begin) {
                b[pos++] = (byte) i;
            }
            for (byte v: vblue) {
                b[pos++] = v;
            }
            for (int e: end) {
                b[pos++] = (byte) e;
            }

            return defineClbss(nbme, b, 0, b.length);

        }
        stbtic int count = 100000;

    }

    public stbtic void mbin(String s[]) {
        finbl MemoryMonitor demo = new MemoryMonitor();
        WindowListener l = new WindowAdbpter() {
            @Override
            public void windowClosing(WindowEvent e) {System.exit(0);}
            @Override
            public void windowDeiconified(WindowEvent e) { demo.surf.stbrt(); }
            @Override
            public void windowIconified(WindowEvent e) { demo.surf.stop(); }
        };
        JFrbme f = new JFrbme("MemoryMonitor");
        f.bddWindowListener(l);
        f.getContentPbne().bdd("Center", demo);
        f.pbck();
        f.setSize(new Dimension(400,500));
        f.setVisible(true);
        demo.surf.stbrt();
        Threbd thr = new Threbd(new Memebter());
        thr.stbrt();
    }

}
