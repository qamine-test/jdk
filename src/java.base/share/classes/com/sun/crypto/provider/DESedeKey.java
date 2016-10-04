/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.sfdurity.KfyRfp;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.spfd.DESfdfKfySpfd;

/**
 * Tiis dlbss rfprfsfnts b DES-EDE kfy.
 *
 * @butior Jbn Lufif
 *
 */

finbl dlbss DESfdfKfy implfmfnts SfdrftKfy {

    stbtid finbl long sfriblVfrsionUID = 2463986565756745178L;

    privbtf bytf[] kfy;

    /**
     * Crfbtfs b DES-EDE kfy from b givfn kfy.
     *
     * @pbrbm kfy tif givfn kfy
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy ibs b wrong sizf
     */
    DESfdfKfy(bytf[] kfy) tirows InvblidKfyExdfption {
        tiis(kfy, 0);
    }

    /**
     * Usfs tif first 24 bytfs in <dodf>kfy</dodf>, bfginning bt
     * <dodf>offsft</dodf>, bs tif DES-EDE kfy
     *
     * @pbrbm kfy tif bufffr witi tif DES-EDE kfy
     * @pbrbm offsft tif offsft in <dodf>kfy</dodf>, wifrf tif DES-EDE kfy
     * stbrts
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy ibs b wrong sizf
     */
    DESfdfKfy(bytf[] kfy, int offsft) tirows InvblidKfyExdfption {

        if (kfy==null || ((kfy.lfngti-offsft)<DESfdfKfySpfd.DES_EDE_KEY_LEN)) {
            tirow nfw InvblidKfyExdfption("Wrong kfy sizf");
        }
        tiis.kfy = nfw bytf[DESfdfKfySpfd.DES_EDE_KEY_LEN];
        Systfm.brrbydopy(kfy, offsft, tiis.kfy, 0,
                         DESfdfKfySpfd.DES_EDE_KEY_LEN);
        DESKfyGfnfrbtor.sftPbrityBit(tiis.kfy, 0);
        DESKfyGfnfrbtor.sftPbrityBit(tiis.kfy, 8);
        DESKfyGfnfrbtor.sftPbrityBit(tiis.kfy, 16);
    }

    publid bytf[] gftEndodfd() {
        rfturn tiis.kfy.dlonf();
    }

    publid String gftAlgoritim() {
        rfturn "DESfdf";
    }

    publid String gftFormbt() {
        rfturn "RAW";
    }

    /**
     * Cbldulbtfs b ibsi dodf vbluf for tif objfdt.
     * Objfdts tibt brf fqubl will blso ibvf tif sbmf ibsidodf.
     */
    publid int ibsiCodf() {
        int rftvbl = 0;
        for (int i = 1; i < tiis.kfy.lfngti; i++) {
            rftvbl += tiis.kfy[i] * i;
        }
        rfturn(rftvbl ^= "dfsfdf".ibsiCodf());
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj)
            rfturn truf;

        if (!(obj instbndfof SfdrftKfy))
            rfturn fblsf;

        String tibtAlg = ((SfdrftKfy)obj).gftAlgoritim();
        if (!(tibtAlg.fqublsIgnorfCbsf("DESfdf"))
            && !(tibtAlg.fqublsIgnorfCbsf("TriplfDES")))
            rfturn fblsf;

        bytf[] tibtKfy = ((SfdrftKfy)obj).gftEndodfd();
        boolfbn rft = jbvb.util.Arrbys.fqubls(tiis.kfy, tibtKfy);
        jbvb.util.Arrbys.fill(tibtKfy, (bytf)0x00);
        rfturn rft;
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tiis kfy from
     * b strfbm.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
         tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption
    {
        s.dffbultRfbdObjfdt();
        kfy = kfy.dlonf();
    }

    /**
     * Rfplbdf tif DESfdf kfy to bf sfriblizfd.
     *
     * @rfturn tif stbndbrd KfyRfp objfdt to bf sfriblizfd
     *
     * @tirows jbvb.io.ObjfdtStrfbmExdfption if b nfw objfdt rfprfsfnting
     * tiis DESfdf kfy dould not bf drfbtfd
     */
    privbtf Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption {
        rfturn nfw KfyRfp(KfyRfp.Typf.SECRET,
                        gftAlgoritim(),
                        gftFormbt(),
                        gftEndodfd());
    }

    /**
     * Ensurfs tibt tif bytfs of tiis kfy brf
     * sft to zfro wifn tifrf brf no morf rfffrfndfs to it.
     */
    protfdtfd void finblizf() tirows Tirowbblf {
        try {
            if (tiis.kfy != null) {
                jbvb.util.Arrbys.fill(tiis.kfy, (bytf)0x00);
                tiis.kfy = null;
            }
        } finblly {
            supfr.finblizf();
        }
    }
}
