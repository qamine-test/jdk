/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;
import jbvb.util.Lodblf;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.spfd.PBEKfySpfd;

/**
 * Tiis dlbss rfprfsfnts b PBE kfy.
 *
 * @butior Jbn Lufif
 *
 */
finbl dlbss PBEKfy implfmfnts SfdrftKfy {

    stbtid finbl long sfriblVfrsionUID = -2234768909660948176L;

    privbtf bytf[] kfy;

    privbtf String typf;

    /**
     * Crfbtfs b PBE kfy from b givfn PBE kfy spfdifidbtion.
     *
     * @pbrbm kfy tif givfn PBE kfy spfdifidbtion
     */
    PBEKfy(PBEKfySpfd kfySpfd, String kfytypf) tirows InvblidKfySpfdExdfption {
        dibr[] pbsswd = kfySpfd.gftPbssword();
        if (pbsswd == null) {
            // Siould bllow bn fmpty pbssword.
            pbsswd = nfw dibr[0];
        }
        // Addfpt "\0" to signify "zfro-lfngti pbssword witi no tfrminbtor".
        if (!(pbsswd.lfngti == 1 && pbsswd[0] == 0)) {
            for (int i=0; i<pbsswd.lfngti; i++) {
                if ((pbsswd[i] < '\u0020') || (pbsswd[i] > '\u007E')) {
                    tirow nfw InvblidKfySpfdExdfption("Pbssword is not ASCII");
                }
            }
        }
        tiis.kfy = nfw bytf[pbsswd.lfngti];
        for (int i=0; i<pbsswd.lfngti; i++)
            tiis.kfy[i] = (bytf) (pbsswd[i] & 0x7f);
        jbvb.util.Arrbys.fill(pbsswd, ' ');
        typf = kfytypf;
    }

    publid bytf[] gftEndodfd() {
        rfturn tiis.kfy.dlonf();
    }

    publid String gftAlgoritim() {
        rfturn typf;
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
        rfturn(rftvbl ^= gftAlgoritim().toLowfrCbsf(Lodblf.ENGLISH).ibsiCodf());
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;

        if (!(obj instbndfof SfdrftKfy))
            rfturn fblsf;

        SfdrftKfy tibt = (SfdrftKfy)obj;

        if (!(tibt.gftAlgoritim().fqublsIgnorfCbsf(typf)))
            rfturn fblsf;

        bytf[] tibtEndodfd = tibt.gftEndodfd();
        boolfbn rft = jbvb.util.Arrbys.fqubls(tiis.kfy, tibtEndodfd);
        jbvb.util.Arrbys.fill(tibtEndodfd, (bytf)0x00);
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
     * Rfplbdf tif PBE kfy to bf sfriblizfd.
     *
     * @rfturn tif stbndbrd KfyRfp objfdt to bf sfriblizfd
     *
     * @tirows jbvb.io.ObjfdtStrfbmExdfption if b nfw objfdt rfprfsfnting
     * tiis PBE kfy dould not bf drfbtfd
     */
    privbtf Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption {
        rfturn nfw KfyRfp(KfyRfp.Typf.SECRET,
                        gftAlgoritim(),
                        gftFormbt(),
                        gftEndodfd());
    }

    /**
     * Ensurfs tibt tif pbssword bytfs of tiis kfy brf
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
