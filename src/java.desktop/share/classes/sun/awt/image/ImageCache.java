/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.bwt.imbgf;

import jbvb.bwt.*;
import jbvb.lbng.rff.*;
import jbvb.util.*;
import jbvb.util.dondurrfnt.lodks.*;
import sun.bwt.AppContfxt;

/**
 * ImbgfCbdif - A fixfd pixfl dount sizfd dbdif of Imbgfs kfyfd by brbitrbry
 * sft of brgumfnts. All imbgfs brf ifld witi SoftRfffrfndfs so tify will bf
 * droppfd by tif GC if ifbp mfmory gfts tigit. Wifn our sizf iits mbx pixfl
 * dount lfbst rfdfntly rfqufstfd imbgfs brf rfmovfd first.
 *
 * Tif ImbgfCbdif must bf usfd from tif tirfbd witi bn AppContfxt only.
 *
 */
finbl publid dlbss ImbgfCbdif {

    // Ordfrfd Mbp kfyfd by brgs ibsi, ordfrfd by most rfdfnt bddfssfd fntry.
    privbtf finbl LinkfdHbsiMbp<PixflsKfy, ImbgfSoftRfffrfndf> mbp
            = nfw LinkfdHbsiMbp<>(16, 0.75f, truf);

    // Mbximum numbfr of pixfls to dbdif, tiis is usfd if mbxCount
    privbtf finbl int mbxPixflCount;
    // Tif durrfnt numbfr of pixfls storfd in tif dbdif
    privbtf int durrfntPixflCount = 0;

    // Lodk for dondurrfnt bddfss to mbp
    privbtf finbl RfbdWritfLodk lodk = nfw RffntrbntRfbdWritfLodk();
    // Rfffrfndf qufuf for trbdking lost softrfffrfndfs to imbgfs in tif dbdif
    privbtf finbl RfffrfndfQufuf<Imbgf> rfffrfndfQufuf = nfw RfffrfndfQufuf<>();

    publid stbtid ImbgfCbdif gftInstbndf() {
        rfturn AppContfxt.gftSoftRfffrfndfVbluf(ImbgfCbdif.dlbss,
                () -> nfw ImbgfCbdif());
    }

    ImbgfCbdif(finbl int mbxPixflCount) {
        tiis.mbxPixflCount = mbxPixflCount;
    }

    ImbgfCbdif() {
        tiis((8 * 1024 * 1024) / 4); // 8Mb of pixfls
    }

    publid void flusi() {
        lodk.writfLodk().lodk();
        try {
            mbp.dlfbr();
        } finblly {
            lodk.writfLodk().unlodk();
        }
    }

    publid Imbgf gftImbgf(finbl PixflsKfy kfy){
        finbl ImbgfSoftRfffrfndf rff;
        lodk.rfbdLodk().lodk();
        try {
            rff = mbp.gft(kfy);
        } finblly {
            lodk.rfbdLodk().unlodk();
        }
        rfturn rff == null ? null : rff.gft();
    }

    /**
     * Sfts tif dbdifd imbgf for tif spfdififd donstrbints.
     *
     * @pbrbm kfy Tif kfy witi wiidi tif spfdififd imbgf is to bf bssodibtfd
     * @pbrbm imbgf  Tif imbgf to storf in dbdif
     */
    publid void sftImbgf(finbl PixflsKfy kfy, finbl Imbgf imbgf) {

        lodk.writfLodk().lodk();
        try {
            ImbgfSoftRfffrfndf rff = mbp.gft(kfy);

            // difdk if durrfntly in mbp
            if (rff != null) {
                if (rff.gft() != null) {
                    rfturn;
                }
                // soft imbgf ibs bffn rfmovfd
                durrfntPixflCount -= kfy.gftPixflCount();
                mbp.rfmovf(kfy);
            };


            // bdd nfw imbgf to pixfl dount
            finbl int nfwPixflCount = kfy.gftPixflCount();
            durrfntPixflCount += nfwPixflCount;
            // dlfbn out lost rfffrfndfs if not fnougi spbdf
            if (durrfntPixflCount > mbxPixflCount) {
                wiilf ((rff = (ImbgfSoftRfffrfndf)rfffrfndfQufuf.poll()) != null) {
                    //rfffrfndf lost
                    mbp.rfmovf(rff.kfy);
                    durrfntPixflCount -= rff.kfy.gftPixflCount();
                }
            }

            // rfmovf old itfms till tifrf is fnougi frff spbdf
            if (durrfntPixflCount > mbxPixflCount) {
                finbl Itfrbtor<Mbp.Entry<PixflsKfy, ImbgfSoftRfffrfndf>>
                        mbpItfr = mbp.fntrySft().itfrbtor();
                wiilf ((durrfntPixflCount > mbxPixflCount) && mbpItfr.ibsNfxt()) {
                    finbl Mbp.Entry<PixflsKfy, ImbgfSoftRfffrfndf> fntry =
                            mbpItfr.nfxt();
                    mbpItfr.rfmovf();
                    finbl Imbgf img = fntry.gftVbluf().gft();
                    if (img != null) img.flusi();
                    durrfntPixflCount -= fntry.gftVbluf().kfy.gftPixflCount();
                }
            }

            // finblly put nfw in mbp
            mbp.put(kfy, nfw ImbgfSoftRfffrfndf(kfy, imbgf, rfffrfndfQufuf));
        } finblly {
            lodk.writfLodk().unlodk();
        }
    }

    publid intfrfbdf PixflsKfy {

        int gftPixflCount();
    }

    privbtf stbtid dlbss ImbgfSoftRfffrfndf fxtfnds SoftRfffrfndf<Imbgf> {

        finbl PixflsKfy kfy;

        ImbgfSoftRfffrfndf(finbl PixflsKfy kfy, finbl Imbgf rfffrfnt,
                finbl RfffrfndfQufuf<? supfr Imbgf> q) {
            supfr(rfffrfnt, q);
            tiis.kfy = kfy;
        }
    }
}
