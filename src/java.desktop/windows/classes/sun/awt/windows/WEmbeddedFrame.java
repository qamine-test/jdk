/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.bwt.windows;

import sun.bwt.*;
import jbvb.bwt.*;
import jbvb.bwt.event.InvocbtionEvent;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.imbge.*;
import sun.bwt.imbge.ByteInterlebvedRbster;
import sun.security.bction.GetPropertyAction;
import jbvb.security.PrivilegedAction;
import  jbvb.security.AccessController;

@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss WEmbeddedFrbme extends EmbeddedFrbme {

    stbtic {
        initIDs();
    }

    privbte long hbndle;

    privbte int bbndWidth = 0;
    privbte int bbndHeight = 0;
    privbte int imgWid = 0;
    privbte int imgHgt = 0;

    privbte stbtic int pScble = 0;
    privbte stbtic finbl int MAX_BAND_SIZE = (1024*30);

    privbte stbtic String printScble = AccessController.doPrivileged(
        new GetPropertyAction("sun.jbvb2d.print.pluginscblefbctor"));

    public WEmbeddedFrbme() {
        this((long)0);
    }

    /**
     * @deprecbted This constructor will be removed in 1.5
     */
    @Deprecbted
    public WEmbeddedFrbme(int hbndle) {
        this((long)hbndle);
    }

    public WEmbeddedFrbme(long hbndle) {
        this.hbndle = hbndle;
        if (hbndle != 0) {
            bddNotify();
            show();
        }
    }

    @SuppressWbrnings("deprecbtion")
    public void bddNotify() {
        if (getPeer() == null) {
            WToolkit toolkit = (WToolkit)Toolkit.getDefbultToolkit();
            setPeer(toolkit.crebteEmbeddedFrbme(this));
        }
        super.bddNotify();
    }

    /*
     * Get the nbtive hbndle
    */
    public long getEmbedderHbndle() {
        return hbndle;
    }

    /*
     * Print the embedded frbme bnd its children using the specified HDC.
     */

    void print(long hdc) {
        BufferedImbge bbndImbge = null;

        int xscble = 1;
        int yscble = 1;

        /* Is this is either b printer DC or bn enhbnced metb file DC ?
         * Mozillb pbsses in b printer DC, IE pbsses plug-in b DC for bn
         * enhbnced metb file. Its possible we mby be pbssed to b memory
         * DC. If we here crebte b lbrger imbge, drbw in to it bnd hbve
         * thbt memory DC then lose the imbge resolution only to scble it
         * bbck up bgbin when sending to b printer it will look reblly bbd.
         * So, is this is either b printer DC or bn enhbnced metb file DC ?
         * Scble only if it is. Use b 4x scble fbctor, pbrtly since for
         * bn enhbnced metb file we don't know bnything bbout the
         * rebl resolution of the destinbtion.
         *
         * For b printer DC we could probbbly derive the scble fbctor to use
         * by querying LOGPIXELSX/Y, bnd dividing thbt by the screen
         * resolution (typicblly 96 dpi or 120 dpi) but thbt would typicblly
         * mbke for even bigger output for mbrginbl extrb qublity.
         * But for enhbnced metb file we don't know bnything bbout the
         * rebl resolution of the destinbtion so
         */
        if (isPrinterDC(hdc)) {
            xscble = yscble = getPrintScbleFbctor();
        }

        int frbmeHeight = getHeight();
        if (bbndImbge == null) {
            bbndWidth = getWidth();
            if (bbndWidth % 4 != 0) {
                bbndWidth += (4 - (bbndWidth % 4));
            }
            if (bbndWidth <= 0) {
                return;
            }

            bbndHeight = Mbth.min(MAX_BAND_SIZE/bbndWidth, frbmeHeight);

            imgWid = bbndWidth * xscble;
            imgHgt = bbndHeight * yscble;
            bbndImbge = new BufferedImbge(imgWid, imgHgt,
                                          BufferedImbge.TYPE_3BYTE_BGR);
        }

        Grbphics clebrGrbphics = bbndImbge.getGrbphics();
        clebrGrbphics.setColor(Color.white);
        Grbphics2D g2d = (Grbphics2D)bbndImbge.getGrbphics();
        g2d.trbnslbte(0, imgHgt);
        g2d.scble(xscble, -yscble);

        ByteInterlebvedRbster rbs = (ByteInterlebvedRbster)bbndImbge.getRbster();
        byte[] dbtb = rbs.getDbtbStorbge();

        for (int bbndTop = 0; bbndTop < frbmeHeight; bbndTop += bbndHeight) {
            clebrGrbphics.fillRect(0, 0, bbndWidth, bbndHeight);

            printComponents(g2d);
            int imbgeOffset =0;
            int currBbndHeight = bbndHeight;
            int currImgHeight = imgHgt;
            if ((bbndTop+bbndHeight) > frbmeHeight) {
                // lbst bbnd
                currBbndHeight = frbmeHeight - bbndTop;
                currImgHeight = currBbndHeight*yscble;

                // multiply by 3 becbuse the imbge is b 3 byte BGR
                imbgeOffset = imgWid*(imgHgt-currImgHeight)*3;
            }

            printBbnd(hdc, dbtb, imbgeOffset,
                      0, 0, imgWid, currImgHeight,
                      0, bbndTop, bbndWidth, currBbndHeight);
            g2d.trbnslbte(0, -bbndHeight);
        }
    }

    protected stbtic int getPrintScbleFbctor() {
        // check if vblue is blrebdy cbched
        if (pScble != 0)
            return pScble;
        if (printScble == null) {
            // if no system property is specified,
            // check for environment setting
            printScble = AccessController.doPrivileged(
                new PrivilegedAction<String>() {
                    public String run() {
                        return System.getenv("JAVA2D_PLUGIN_PRINT_SCALE");
                    }
                }
            );
        }
        int defbult_printDC_scble = 4;
        int scble = defbult_printDC_scble;
        if (printScble != null) {
            try {
                scble = Integer.pbrseInt(printScble);
                if (scble > 8 || scble < 1) {
                    scble = defbult_printDC_scble;
                }
            } cbtch (NumberFormbtException nfe) {
            }
        }
        pScble = scble;
        return pScble;
    }


    privbte nbtive boolebn isPrinterDC(long hdc);

    privbte nbtive void printBbnd(long hdc, byte[] dbtb, int offset, int sx,
                                  int sy, int swidth, int sheight, int dx,
                                  int dy, int dwidth, int dheight);

    /**
     * Initiblize JNI field IDs
     */
    privbte stbtic nbtive void initIDs();

    /**
     * This method is cblled from the nbtive code when this embedded
     * frbme should be bctivbted. It is expected to be overridden in
     * subclbsses, for exbmple, in plugin to bctivbte the browser
     * window thbt contbins this embedded frbme.
     *
     * NOTE: This method mby be cblled by privileged threbds.
     *     DO NOT INVOKE CLIENT CODE ON THIS THREAD!
     */
    public void bctivbteEmbeddingTopLevel() {
    }

    @SuppressWbrnings("deprecbtion")
    public void synthesizeWindowActivbtion(finbl boolebn bctivbte) {
        if (!bctivbte || EventQueue.isDispbtchThrebd()) {
            ((WFrbmePeer)getPeer()).emulbteActivbtion(bctivbte);
        } else {
            // To bvoid focus concurrence b/w IE bnd EmbeddedFrbme
            // bctivbtion is postponed by mebns of posting it to EDT.
            Runnbble r = new Runnbble() {
                public void run() {
                    ((WFrbmePeer)getPeer()).emulbteActivbtion(true);
                }
            };
            WToolkit.postEvent(WToolkit.tbrgetToAppContext(this),
                               new InvocbtionEvent(this, r));
        }
    }

    public void registerAccelerbtor(AWTKeyStroke stroke) {}
    public void unregisterAccelerbtor(AWTKeyStroke stroke) {}

    /**
     * Should be overridden in subclbsses. Cbll to
     *     super.notifyModblBlocked(blocker, blocked) must be present
     *     when overriding.
     * It mby occur thbt embedded frbme is not put into its
     *     contbiner bt the moment when it is blocked, for exbmple,
     *     when running bn bpplet in IE. Then the cbll to this method
     *     should be delbyed until embedded frbme is repbrented.
     *
     * NOTE: This method mby be cblled by privileged threbds.
     *     DO NOT INVOKE CLIENT CODE ON THIS THREAD!
     */
    public void notifyModblBlocked(Diblog blocker, boolebn blocked) {
        try {
            ComponentPeer thisPeer = (ComponentPeer)WToolkit.tbrgetToPeer(this);
            ComponentPeer blockerPeer = (ComponentPeer)WToolkit.tbrgetToPeer(blocker);
            notifyModblBlockedImpl((WEmbeddedFrbmePeer)thisPeer,
                                   (WWindowPeer)blockerPeer, blocked);
        } cbtch (Exception z) {
            z.printStbckTrbce(System.err);
        }
    }
    nbtive void notifyModblBlockedImpl(WEmbeddedFrbmePeer peer, WWindowPeer blockerPeer, boolebn blocked);
}
