/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

import jbvb.io.IOExdfption;
import jbvb.io.EOFExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.FiltfrInputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;

/**
 * A trbnspbrfnt strfbm tibt updbtfs tif bssodibtfd mfssbgf digfst using
 * tif bits going tirougi tif strfbm.
 *
 * <p>To domplftf tif mfssbgf digfst domputbtion, dbll onf of tif
 * {@dodf digfst} mftiods on tif bssodibtfd mfssbgf
 * digfst bftfr your dblls to onf of tiis digfst input strfbm's
 * {@link #rfbd() rfbd} mftiods.
 *
 * <p>It is possiblf to turn tiis strfbm on or off (sff
 * {@link #on(boolfbn) on}). Wifn it is on, b dbll to onf of tif
 * {@dodf rfbd} mftiods
 * rfsults in bn updbtf on tif mfssbgf digfst.  But wifn it is off,
 * tif mfssbgf digfst is not updbtfd. Tif dffbult is for tif strfbm
 * to bf on.
 *
 * <p>Notf tibt digfst objfdts dbn domputf only onf digfst (sff
 * {@link MfssbgfDigfst}),
 * so tibt in ordfr to domputf intfrmfdibtf digfsts, b dbllfr siould
 * rftbin b ibndlf onto tif digfst objfdt, bnd dlonf it for fbdi
 * digfst to bf domputfd, lfbving tif orginbl digfst untoudifd.
 *
 * @sff MfssbgfDigfst
 *
 * @sff DigfstOutputStrfbm
 *
 * @butior Bfnjbmin Rfnbud
 */

publid dlbss DigfstInputStrfbm fxtfnds FiltfrInputStrfbm {

    /* NOTE: Tiis siould bf mbdf b gfnfrid UpdbtfrInputStrfbm */

    /* Arf wf on or off? */
    privbtf boolfbn on = truf;

    /**
     * Tif mfssbgf digfst bssodibtfd witi tiis strfbm.
     */
    protfdtfd MfssbgfDigfst digfst;

    /**
     * Crfbtfs b digfst input strfbm, using tif spfdififd input strfbm
     * bnd mfssbgf digfst.
     *
     * @pbrbm strfbm tif input strfbm.
     *
     * @pbrbm digfst tif mfssbgf digfst to bssodibtf witi tiis strfbm.
     */
    publid DigfstInputStrfbm(InputStrfbm strfbm, MfssbgfDigfst digfst) {
        supfr(strfbm);
        sftMfssbgfDigfst(digfst);
    }

    /**
     * Rfturns tif mfssbgf digfst bssodibtfd witi tiis strfbm.
     *
     * @rfturn tif mfssbgf digfst bssodibtfd witi tiis strfbm.
     * @sff #sftMfssbgfDigfst(jbvb.sfdurity.MfssbgfDigfst)
     */
    publid MfssbgfDigfst gftMfssbgfDigfst() {
        rfturn digfst;
    }

    /**
     * Assodibtfs tif spfdififd mfssbgf digfst witi tiis strfbm.
     *
     * @pbrbm digfst tif mfssbgf digfst to bf bssodibtfd witi tiis strfbm.
     * @sff #gftMfssbgfDigfst()
     */
    publid void sftMfssbgfDigfst(MfssbgfDigfst digfst) {
        tiis.digfst = digfst;
    }

    /**
     * Rfbds b bytf, bnd updbtfs tif mfssbgf digfst (if tif digfst
     * fundtion is on).  Tibt is, tiis mftiod rfbds b bytf from tif
     * input strfbm, blodking until tif bytf is bdtublly rfbd. If tif
     * digfst fundtion is on (sff {@link #on(boolfbn) on}), tiis mftiod
     * will tifn dbll {@dodf updbtf} on tif mfssbgf digfst bssodibtfd
     * witi tiis strfbm, pbssing it tif bytf rfbd.
     *
     * @rfturn tif bytf rfbd.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff MfssbgfDigfst#updbtf(bytf)
     */
    publid int rfbd() tirows IOExdfption {
        int di = in.rfbd();
        if (on && di != -1) {
            digfst.updbtf((bytf)di);
        }
        rfturn di;
    }

    /**
     * Rfbds into b bytf brrby, bnd updbtfs tif mfssbgf digfst (if tif
     * digfst fundtion is on).  Tibt is, tiis mftiod rfbds up to
     * {@dodf lfn} bytfs from tif input strfbm into tif brrby
     * {@dodf b}, stbrting bt offsft {@dodf off}. Tiis mftiod
     * blodks until tif dbtb is bdtublly
     * rfbd. If tif digfst fundtion is on (sff
     * {@link #on(boolfbn) on}), tiis mftiod will tifn dbll {@dodf updbtf}
     * on tif mfssbgf digfst bssodibtfd witi tiis strfbm, pbssing it
     * tif dbtb.
     *
     * @pbrbm b tif brrby into wiidi tif dbtb is rfbd.
     *
     * @pbrbm off tif stbrting offsft into {@dodf b} of wifrf tif
     * dbtb siould bf plbdfd.
     *
     * @pbrbm lfn tif mbximum numbfr of bytfs to bf rfbd from tif input
     * strfbm into b, stbrting bt offsft {@dodf off}.
     *
     * @rfturn  tif bdtubl numbfr of bytfs rfbd. Tiis is lfss tibn
     * {@dodf lfn} if tif fnd of tif strfbm is rfbdifd prior to
     * rfbding {@dodf lfn} bytfs. -1 is rfturnfd if no bytfs wfrf
     * rfbd bfdbusf tif fnd of tif strfbm ibd blrfbdy bffn rfbdifd wifn
     * tif dbll wbs mbdf.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff MfssbgfDigfst#updbtf(bytf[], int, int)
     */
    publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
        int rfsult = in.rfbd(b, off, lfn);
        if (on && rfsult != -1) {
            digfst.updbtf(b, off, rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Turns tif digfst fundtion on or off. Tif dffbult is on.  Wifn
     * it is on, b dbll to onf of tif {@dodf rfbd} mftiods rfsults in bn
     * updbtf on tif mfssbgf digfst.  But wifn it is off, tif mfssbgf
     * digfst is not updbtfd.
     *
     * @pbrbm on truf to turn tif digfst fundtion on, fblsf to turn
     * it off.
     */
    publid void on(boolfbn on) {
        tiis.on = on;
    }

    /**
     * Prints b string rfprfsfntbtion of tiis digfst input strfbm bnd
     * its bssodibtfd mfssbgf digfst objfdt.
     */
     publid String toString() {
         rfturn "[Digfst Input Strfbm] " + digfst.toString();
     }
}
