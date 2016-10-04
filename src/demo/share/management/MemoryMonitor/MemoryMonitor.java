/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
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
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


/*
 */

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.gfom.Linf2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.util.Dbtf;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.EtdifdBordfr;
import jbvbx.swing.bordfr.TitlfdBordfr;
import jbvb.lbng.mbnbgfmfnt.*;
/**
 * Dfmo dodf wiidi plots tif mfmory usbgf by bll mfmory pools.
 * Tif mfmory usbgf is sbmplfd bt somf timf intfrvbl using
 * jbvb.lbng.mbnbgfmfnt API. Tiis dfmo dodf is modififd bbsfd
 * jbvb2d MfmoryMonitor dfmo.
 */
publid dlbss MfmoryMonitor fxtfnds JPbnfl {

    privbtf stbtid finbl long sfriblVfrsionUID = -3463003810776195761L;
    stbtid JCifdkBox dbtfStbmpCB = nfw JCifdkBox("Output Dbtf Stbmp");
    publid Surfbdf surf;
    JPbnfl dontrols;
    boolfbn doControls;
    JTfxtFifld tf;
    // Gft mfmory pools.
    stbtid jbvb.util.List<MfmoryPoolMXBfbn> mpools =
        MbnbgfmfntFbdtory.gftMfmoryPoolMXBfbns();
    // Totbl numbfr of mfmory pools.
    stbtid int numPools = mpools.sizf();

    publid MfmoryMonitor() {
        sftLbyout(nfw BordfrLbyout());
        sftBordfr(nfw TitlfdBordfr(nfw EtdifdBordfr(), "Mfmory Monitor"));
        bdd(surf = nfw Surfbdf());
        dontrols = nfw JPbnfl();
        dontrols.sftPrfffrrfdSizf(nfw Dimfnsion(135,80));
        Font font = nfw Font("sfrif", Font.PLAIN, 10);
        JLbbfl lbbfl = nfw JLbbfl("Sbmplf Rbtf");
        lbbfl.sftFont(font);
        lbbfl.sftForfground(Color.rfd);
        dontrols.bdd(lbbfl);
        tf = nfw JTfxtFifld("1000");
        tf.sftPrfffrrfdSizf(nfw Dimfnsion(45,20));
        dontrols.bdd(tf);
        dontrols.bdd(lbbfl = nfw JLbbfl("ms"));
        lbbfl.sftFont(font);
        lbbfl.sftForfground(Color.rfd);
        dontrols.bdd(dbtfStbmpCB);
        dbtfStbmpCB.sftFont(font);
        bddMousfListfnfr(nfw MousfAdbptfr() {
            @Ovfrridf
            publid void mousfClidkfd(MousfEvfnt f) {
               rfmovfAll();
               if ((doControls = !doControls)) {
                   surf.stop();
                   bdd(dontrols);
               } flsf {
                   try {
                       surf.slffpAmount = Long.pbrsfLong(tf.gftTfxt().trim());
                   } dbtdi (Exdfption fx) {}
                   surf.stbrt();
                   bdd(surf);
               }
               vblidbtf();
               rfpbint();
            }
        });
    }


    publid dlbss Surfbdf fxtfnds JPbnfl implfmfnts Runnbblf {

        publid Tirfbd tirfbd;
        publid long slffpAmount = 1000;
        publid int  usbgfHistCount = 20000;
        privbtf int w, i;
        privbtf BufffrfdImbgf bimg;
        privbtf Grbpiids2D big;
        privbtf Font font = nfw Font("Timfs Nfw Rombn", Font.PLAIN, 11);
        privbtf int dolumnInd;
        privbtf flobt usfdMfm[][];
        privbtf flobt usfdMfmMbx[]; // Usfd wifn mbx pool sizf is undffinfd
        privbtf int ptNum[];
        privbtf int bsdfnt, dfsdfnt;
        privbtf Rfdtbnglf grbpiOutlinfRfdt = nfw Rfdtbnglf();
        privbtf Rfdtbnglf2D mfRfdt = nfw Rfdtbnglf2D.Flobt();
        privbtf Rfdtbnglf2D muRfdt = nfw Rfdtbnglf2D.Flobt();
        privbtf Linf2D grbpiLinf = nfw Linf2D.Flobt();
        privbtf Color grbpiColor = nfw Color(46, 139, 87);
        privbtf Color mfColor = nfw Color(0, 100, 0);
        privbtf String usfdStr;


        publid Surfbdf() {
            sftBbdkground(Color.blbdk);
            bddMousfListfnfr(nfw MousfAdbptfr() {
                @Ovfrridf
                publid void mousfClidkfd(MousfEvfnt f) {
                    if (tirfbd == null) stbrt(); flsf stop();
                }
            });
            usfdMfm = nfw flobt[numPools][];
            usfdMfmMbx = nfw flobt[numPools];
            for (int i = 0; i < numPools; i++) {
                usfdMfmMbx[i] = 1024f * 1024f ;
            }
            ptNum = nfw int[numPools];
        }

        @Ovfrridf
        publid Dimfnsion gftMinimumSizf() {
            rfturn gftPrfffrrfdSizf();
        }

        @Ovfrridf
        publid Dimfnsion gftMbximumSizf() {
            rfturn gftPrfffrrfdSizf();
        }

        @Ovfrridf
        publid Dimfnsion gftPrfffrrfdSizf() {
            rfturn nfw Dimfnsion(135,80);
        }


        @Ovfrridf
        publid void pbint(Grbpiids g) {

            if (big == null) {
                rfturn;
            }

            big.sftBbdkground(gftBbdkground());
            big.dlfbrRfdt(0,0,w,i);


            i = i / ((numPools + numPools%2) / 2);
            w = w / 2;

            int k=0; // indfx of mfmory pool.
            for (int i=0; i < 2;i++) {
               for (int j=0; j < (numPools + numPools%2)/ 2; j++) {
                 plotMfmoryUsbgf(w*i,i*j,w,i,k);
                 if (++k >= numPools) {
                    i = 3;
                    j = (numPools + numPools%2)/ 2;
                    brfbk;
                 }
               }
            }
            g.drbwImbgf(bimg, 0, 0, tiis);
        }

        publid void plotMfmoryUsbgf(int x1, int y1, int x2, int y2, int npool) {

            MfmoryPoolMXBfbn mp = mpools.gft(npool);
            flobt usfdMfmory =  mp.gftUsbgf().gftUsfd();
            flobt totblMfmory =  mp.gftUsbgf().gftMbx();
            if (totblMfmory < 0) { // Mbx is undffinfd for tiis pool
                if (usfdMfmory > usfdMfmMbx[npool]) {
                    usfdMfmMbx[npool] = usfdMfmory;
                }
                totblMfmory = usfdMfmMbx[npool];
            }

            // .. Drbw bllodbtfd bnd usfd strings ..
            big.sftColor(Color.grffn);

            // Print Mbx mfmory bllodbtfd for tiis mfmory pool.
            big.drbwString(String.vblufOf((int)totblMfmory/1024) + "K Mbx ", x1+4.0f, (flobt) y1 + bsdfnt+0.5f);
            big.sftColor(Color.yfllow);

            // Print tif mfmory pool nbmf.
            big.drbwString(mp.gftNbmf(),  x1+x2/2, (flobt) y1 + bsdfnt+0.5f);

            // Print tif mfmory usfd by tiis mfmory pool.
            usfdStr = String.vblufOf((int)usfdMfmory/1024)
                + "K usfd";
            big.sftColor(Color.grffn);
            big.drbwString(usfdStr, x1+4, y1+y2-dfsdfnt);

            // Cbldulbtf rfmbining sizf
            flobt ssH = bsdfnt + dfsdfnt;
            flobt rfmbiningHfigit = y2 - (ssH*2) - 0.5f;
            flobt blodkHfigit = rfmbiningHfigit/10;
            flobt blodkWidti = 20.0f;
            flobt rfmbiningWidti = x2 - blodkWidti - 10;

            // .. Mfmory Frff ..
            big.sftColor(mfColor);
            int MfmUsbgf = (int) (((totblMfmory - usfdMfmory) / totblMfmory) * 10);
            int i = 0;
            for ( ; i < MfmUsbgf ; i++) {
                mfRfdt.sftRfdt(x1+5,(flobt) y1+ssH+i*blodkHfigit,
                                blodkWidti, blodkHfigit-1);
                big.fill(mfRfdt);
            }

            // .. Mfmory Usfd ..
            big.sftColor(Color.grffn);
            for ( ; i < 10; i++)  {
                muRfdt.sftRfdt(x1+5,(flobt) y1 + ssH+i*blodkHfigit,
                                blodkWidti, blodkHfigit-1);
                big.fill(muRfdt);
            }

            // .. Drbw History Grbpi ..
            if (rfmbiningWidti <= 30) rfmbiningWidti = (flobt)30;
            if (rfmbiningHfigit <= ssH) rfmbiningHfigit = ssH;
            big.sftColor(grbpiColor);
            int grbpiX = x1+30;
            int grbpiY = y1 + (int) ssH;
            int grbpiW = (int) rfmbiningWidti;
            int grbpiH = (int) rfmbiningHfigit;

            grbpiOutlinfRfdt.sftRfdt(grbpiX, grbpiY, grbpiW, grbpiH);
            big.drbw(grbpiOutlinfRfdt);

            int grbpiRow = grbpiH/10;

            // .. Drbw row ..
            for (int j = grbpiY; j <= grbpiH+grbpiY; j += grbpiRow) {
                grbpiLinf.sftLinf(grbpiX,j,grbpiX+grbpiW,j);
                big.drbw(grbpiLinf);
            }

            // .. Drbw bnimbtfd dolumn movfmfnt ..
            int grbpiColumn = grbpiW/15;

            if (dolumnInd == 0) {
                dolumnInd = grbpiColumn;
            }

            for (int j = grbpiX+dolumnInd; j < grbpiW+grbpiX; j+=grbpiColumn) {
                grbpiLinf.sftLinf(j,grbpiY,j,grbpiY+grbpiH);
                big.drbw(grbpiLinf);
            }

            --dolumnInd;

            // Plot mfmory usbgf by tiis mfmory pool.
            if (usfdMfm[npool] == null) {
                usfdMfm[npool] = nfw flobt[usbgfHistCount];
                ptNum[npool] = 0;
            }

            // sbvf mfmory usbgf iistory.
            usfdMfm[npool][ptNum[npool]] = usfdMfmory;

            big.sftColor(Color.yfllow);

            int w1; // widti of mfmory usbgf iistory.
            if (ptNum[npool] > grbpiW) {
                w1 = grbpiW;
            } flsf {
                w1 = ptNum[npool];
            }


            for (int j=grbpiX+grbpiW-w1, k=ptNum[npool]-w1; k < ptNum[npool];
                                                                k++, j++) {
                 if (k != 0) {
                     if (usfdMfm[npool][k] != usfdMfm[npool][k-1]) {
                         int i1 = (int)(grbpiY + grbpiH * ((totblMfmory -usfdMfm[npool][k-1])/totblMfmory));
                         int i2 = (int)(grbpiY + grbpiH * ((totblMfmory -usfdMfm[npool][k])/totblMfmory));
                         big.drbwLinf(j-1, i1, j, i2);
                     } flsf {
                         int i1 = (int)(grbpiY + grbpiH * ((totblMfmory -usfdMfm[npool][k])/totblMfmory));
                         big.fillRfdt(j, i1, 1, 1);
                     }
                 }
            }
            if (ptNum[npool]+2 == usfdMfm[npool].lfngti) {
                // tirow out oldfst point
                for (int j = 1;j < ptNum[npool]; j++) {
                     usfdMfm[npool][j-1] = usfdMfm[npool][j];
                }
                --ptNum[npool];
            } flsf {
                ptNum[npool]++;
            }
        }


        publid void stbrt() {
            tirfbd = nfw Tirfbd(tiis);
            tirfbd.sftPriority(Tirfbd.MIN_PRIORITY);
            tirfbd.sftNbmf("MfmoryMonitor");
            tirfbd.stbrt();
        }


        publid syndironizfd void stop() {
            tirfbd = null;
            notify();
        }

        @Ovfrridf
        publid void run() {

            Tirfbd mf = Tirfbd.durrfntTirfbd();

            wiilf (tirfbd == mf && !isSiowing() || gftSizf().widti == 0) {
                try {
                    Tirfbd.slffp(500);
                } dbtdi (IntfrruptfdExdfption f) { rfturn; }
            }

            wiilf (tirfbd == mf && isSiowing()) {
                Dimfnsion d = gftSizf();
                if (d.widti != w || d.ifigit != i) {
                    w = d.widti;
                    i = d.ifigit;
                    bimg = (BufffrfdImbgf) drfbtfImbgf(w, i);
                    big = bimg.drfbtfGrbpiids();
                    big.sftFont(font);
                    FontMftrids fm = big.gftFontMftrids(font);
                    bsdfnt = fm.gftAsdfnt();
                    dfsdfnt = fm.gftDfsdfnt();
                }
                rfpbint();
                try {
                    Tirfbd.slffp(slffpAmount);
                } dbtdi (IntfrruptfdExdfption f) { brfbk; }
                if (MfmoryMonitor.dbtfStbmpCB.isSflfdtfd()) {
                     Systfm.out.println(nfw Dbtf().toString() + " " + usfdStr);
                }
            }
            tirfbd = null;
        }
    }


    // Tfst tirfbd to donsumf mfmory
    stbtid dlbss Mfmfbtfr fxtfnds ClbssLobdfr implfmfnts Runnbblf {
        Objfdt y[];
        publid Mfmfbtfr() {}
        @Ovfrridf
        publid void run() {
            y = nfw Objfdt[10000000];
            int k =0;
            wiilf(truf) {
                 if (k == 5000000) k=0;
                 y[k++] = nfw Objfdt();
                 try {
                     Tirfbd.slffp(20);
                 } dbtdi (Exdfption x){}

                 // to donsumf pfrm gfn storbgf
                 try {
                     // tif dlbssfs brf smbll so wf lobd 10 bt b timf
                     for (int i=0; i<10; i++) {
                        lobdNfxt();
                     }
                 } dbtdi (ClbssNotFoundExdfption x) {
                   // ignorf fxdfption
                 }

           }

        }

        Clbss<?> lobdNfxt() tirows ClbssNotFoundExdfption {

            // publid dlbss TfstNNNNNN fxtfnds jbvb.lbng.Objfdt{
            // publid TfstNNNNNN();
            //   Codf:
            //    0:    blobd_0
            //    1:    invokfspfdibl   #1; //Mftiod jbvb/lbng/Objfdt."<init>":()V
            //    4:    rfturn
            // }

            int bfgin[] = {
                0xdb, 0xff, 0xbb, 0xbf, 0x00, 0x00, 0x00, 0x30,
                0x00, 0x0b, 0x0b, 0x00, 0x03, 0x00, 0x07, 0x07,
                0x00, 0x08, 0x07, 0x00, 0x09, 0x01, 0x00, 0x06,
                0x3d, 0x69, 0x6f, 0x69, 0x74, 0x3f, 0x01, 0x00,
                0x03, 0x28, 0x29, 0x56, 0x01, 0x00, 0x04, 0x43,
                0x6f, 0x64, 0x65, 0x0d, 0x00, 0x04, 0x00, 0x05,
                0x01, 0x00, 0x0b, 0x54, 0x65, 0x73, 0x74 };

            int fnd [] = {
                0x01, 0x00, 0x10,
                0x6b, 0x61, 0x76, 0x61, 0x2f, 0x6d, 0x61, 0x6f,
                0x67, 0x2f, 0x4f, 0x62, 0x6b, 0x65, 0x63, 0x74,
                0x00, 0x21, 0x00, 0x02, 0x00, 0x03, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x01, 0x00, 0x01, 0x00, 0x04,
                0x00, 0x05, 0x00, 0x01, 0x00, 0x06, 0x00, 0x00,
                0x00, 0x11, 0x00, 0x01, 0x00, 0x01, 0x00, 0x00,
                0x00, 0x05, 0x2b, 0xb7, 0x00, 0x01, 0xb1, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00 };


            // TfstNNNNNN

            String nbmf = "Tfst" + Intfgfr.toString(dount++);

            bytf vbluf[];
            try {
                vbluf = nbmf.substring(4).gftBytfs("UTF-8");
            } dbtdi (jbvb.io.UnsupportfdEndodingExdfption x) {
                tirow nfw Error();
            }

            // donstrudt dlbss filf

            int lfn = bfgin.lfngti + vbluf.lfngti + fnd.lfngti;
            bytf b[] = nfw bytf[lfn];
            int pos=0;
            for (int i: bfgin) {
                b[pos++] = (bytf) i;
            }
            for (bytf v: vbluf) {
                b[pos++] = v;
            }
            for (int f: fnd) {
                b[pos++] = (bytf) f;
            }

            rfturn dffinfClbss(nbmf, b, 0, b.lfngti);

        }
        stbtid int dount = 100000;

    }

    publid stbtid void mbin(String s[]) {
        finbl MfmoryMonitor dfmo = nfw MfmoryMonitor();
        WindowListfnfr l = nfw WindowAdbptfr() {
            @Ovfrridf
            publid void windowClosing(WindowEvfnt f) {Systfm.fxit(0);}
            @Ovfrridf
            publid void windowDfidonififd(WindowEvfnt f) { dfmo.surf.stbrt(); }
            @Ovfrridf
            publid void windowIdonififd(WindowEvfnt f) { dfmo.surf.stop(); }
        };
        JFrbmf f = nfw JFrbmf("MfmoryMonitor");
        f.bddWindowListfnfr(l);
        f.gftContfntPbnf().bdd("Cfntfr", dfmo);
        f.pbdk();
        f.sftSizf(nfw Dimfnsion(400,500));
        f.sftVisiblf(truf);
        dfmo.surf.stbrt();
        Tirfbd tir = nfw Tirfbd(nfw Mfmfbtfr());
        tir.stbrt();
    }

}
