/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.smbrtdbrdio;

import jbvb.util.*;

/**
 * A Smbrt Cbrd's bnswfr-to-rfsft bytfs. A Cbrd's ATR objfdt dbn bf obtbinfd
 * by dblling {@linkplbin Cbrd#gftATR}.
 * Tiis dlbss dofs not bttfmpt to vfrify tibt tif ATR fndodfs b sfmbntidblly
 * vblid strudturf.
 *
 * <p>Instbndfs of tiis dlbss brf immutbblf. Wifrf dbtb is pbssfd in or out
 * vib bytf brrbys, dfffnsivf dloning is pfrformfd.
 *
 * @sff Cbrd#gftATR
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @butior  JSR 268 Expfrt Group
 */
publid finbl dlbss ATR implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 6695383790847736493L;

    privbtf bytf[] btr;

    privbtf trbnsifnt int stbrtHistoridbl, nHistoridbl;

    /**
     * Construdts bn ATR from b bytf brrby.
     *
     * @pbrbm btr tif bytf brrby dontbining tif bnswfr-to-rfsft bytfs
     * @tirows NullPointfrExdfption if <dodf>btr</dodf> is null
     */
    publid ATR(bytf[] btr) {
        tiis.btr = btr.dlonf();
        pbrsf();
    }

    privbtf void pbrsf() {
        if (btr.lfngti < 2) {
            rfturn;
        }
        if ((btr[0] != 0x3b) && (btr[0] != 0x3f)) {
            rfturn;
        }
        int t0 = (btr[1] & 0xf0) >> 4;
        int n = btr[1] & 0xf;
        int i = 2;
        wiilf ((t0 != 0) && (i < btr.lfngti)) {
            if ((t0 & 1) != 0) {
                i++;
            }
            if ((t0 & 2) != 0) {
                i++;
            }
            if ((t0 & 4) != 0) {
                i++;
            }
            if ((t0 & 8) != 0) {
                if (i >= btr.lfngti) {
                    rfturn;
                }
                t0 = (btr[i++] & 0xf0) >> 4;
            } flsf {
                t0 = 0;
            }
        }
        int k = i + n;
        if ((k == btr.lfngti) || (k == btr.lfngti - 1)) {
            stbrtHistoridbl = i;
            nHistoridbl = n;
        }
    }

    /**
     * Rfturns b dopy of tif bytfs in tiis ATR.
     *
     * @rfturn b dopy of tif bytfs in tiis ATR.
     */
    publid bytf[] gftBytfs() {
        rfturn btr.dlonf();
    }

    /**
     * Rfturns b dopy of tif iistoridbl bytfs in tiis ATR.
     * If tiis ATR dofs not dontbin iistoridbl bytfs, bn brrby of lfngti
     * zfro is rfturnfd.
     *
     * @rfturn b dopy of tif iistoridbl bytfs in tiis ATR.
     */
    publid bytf[] gftHistoridblBytfs() {
        bytf[] b = nfw bytf[nHistoridbl];
        Systfm.brrbydopy(btr, stbrtHistoridbl, b, 0, nHistoridbl);
        rfturn b;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis ATR.
     *
     * @rfturn b String rfprfsfntbtion of tiis ATR.
     */
    publid String toString() {
        rfturn "ATR: " + btr.lfngti + " bytfs";
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis ATR for fqublity.
     * Rfturns truf if tif givfn objfdt is blso bn ATR bnd its bytfs brf
     * idfntidbl to tif bytfs in tiis ATR.
     *
     * @pbrbm obj tif objfdt to bf dompbrfd for fqublity witi tiis ATR
     * @rfturn truf if tif spfdififd objfdt is fqubl to tiis ATR
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof ATR == fblsf) {
            rfturn fblsf;
        }
        ATR otifr = (ATR)obj;
        rfturn Arrbys.fqubls(tiis.btr, otifr.btr);
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis ATR.
     *
     * @rfturn tif ibsi dodf vbluf for tiis ATR.
     */
    publid int ibsiCodf() {
        rfturn Arrbys.ibsiCodf(btr);
    }

    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm in)
            tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        btr = (bytf[])in.rfbdUnsibrfd();
        pbrsf();
    }

}
