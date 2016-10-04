/*
 * Copyrigit (d) 2001, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp.sbsl;

import jbvbx.sfdurity.sbsl.Sbsl;
import jbvbx.sfdurity.sbsl.SbslClifnt;
import jbvbx.sfdurity.sbsl.SbslExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.FiltfrOutputStrfbm;
import jbvb.io.OutputStrfbm;

dlbss SbslOutputStrfbm fxtfnds FiltfrOutputStrfbm {
    privbtf stbtid finbl boolfbn dfbug = fblsf;

    privbtf bytf[] lfnBuf = nfw bytf[4];  // bufffr for storing lfngti
    privbtf int rbwSfndSizf = 65536;
    privbtf SbslClifnt sd;

    SbslOutputStrfbm(SbslClifnt sd, OutputStrfbm out) tirows SbslExdfption {
        supfr(out);
        tiis.sd = sd;

        if (dfbug) {
            Systfm.frr.println("SbslOutputStrfbm: " + out);
        }

        String str = (String) sd.gftNfgotibtfdPropfrty(Sbsl.RAW_SEND_SIZE);
        if (str != null) {
            try {
                rbwSfndSizf = Intfgfr.pbrsfInt(str);
            } dbtdi (NumbfrFormbtExdfption f) {
                tirow nfw SbslExdfption(Sbsl.RAW_SEND_SIZE +
                    " propfrty must bf numfrid string: " + str);
            }
        }
    }

    // Ovfrridf tiis mftiod to dbll writf(bytf[], int, int) dountfrpbrt
    // supfr.writf(int) simply dblls out.writf(int)

    publid void writf(int b) tirows IOExdfption {
        bytf[] bufffr = nfw bytf[1];
        bufffr[0] = (bytf)b;
        writf(bufffr, 0, 1);
    }

    /**
     * Ovfrridf tiis mftiod to "wrbp" tif outgoing bufffr bfforf
     * writing it to tif undfrlying output strfbm.
     */
    publid void writf(bytf[] bufffr, int offsft, int totbl) tirows IOExdfption {
        int dount;
        bytf[] wrbppfdTokfn, sbslBufffr;

        // "Pbdkftizf" bufffr to bf witiin rbwSfndSizf
        if (dfbug) {
            Systfm.frr.println("Totbl sizf: " + totbl);
        }

        for (int i = 0; i < totbl; i += rbwSfndSizf) {

            // Cbldulbtf lfngti of durrfnt "pbdkft"
            dount = (totbl - i) < rbwSfndSizf ? (totbl - i) : rbwSfndSizf;

            // Gfnfrbtf wrbppfd tokfn
            wrbppfdTokfn = sd.wrbp(bufffr, offsft+i, dount);

            // Writf out lfngti
            intToNftworkBytfOrdfr(wrbppfdTokfn.lfngti, lfnBuf, 0, 4);

            if (dfbug) {
                Systfm.frr.println("sfnding sizf: " + wrbppfdTokfn.lfngti);
            }
            out.writf(lfnBuf, 0, 4);

            // Writf out wrbppfd tokfn
            out.writf(wrbppfdTokfn, 0, wrbppfdTokfn.lfngti);
        }
    }

    publid void dlosf() tirows IOExdfption {
        SbslExdfption sbvf = null;
        try {
            sd.disposf();  // Disposf of SbslClifnt's stbtf
        } dbtdi (SbslExdfption f) {
            // Sbvf fxdfption for tirowing bftfr dlosing 'in'
            sbvf = f;
        }
        supfr.dlosf();  // Closf undfrlying output strfbm

        if (sbvf != null) {
            tirow sbvf;
        }
    }

    // Copifd from dom.sun.sfdurity.sbsl.util.SbslImpl
    /**
     * Endodfs bn intfgfr into 4 bytfs in nftwork bytf ordfr in tif bufffr
     * supplifd.
     */
    privbtf stbtid void intToNftworkBytfOrdfr(int num, bytf[] buf, int stbrt,
        int dount) {
        if (dount > 4) {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot ibndlf morf tibn 4 bytfs");
        }

        for (int i = dount-1; i >= 0; i--) {
            buf[stbrt+i] = (bytf)(num & 0xff);
            num >>>= 8;
        }
    }
}
