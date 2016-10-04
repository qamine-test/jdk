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
import jbvb.io.OutputStrfbm;
import jbvb.io.FiltfrOutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;

/**
 * A trbnspbrfnt strfbm tibt updbtfs tif bssodibtfd mfssbgf digfst using
 * tif bits going tirougi tif strfbm.
 *
 * <p>To domplftf tif mfssbgf digfst domputbtion, dbll onf of tif
 * {@dodf digfst} mftiods on tif bssodibtfd mfssbgf
 * digfst bftfr your dblls to onf of tiis digfst output strfbm's
 * {@link #writf(int) writf} mftiods.
 *
 * <p>It is possiblf to turn tiis strfbm on or off (sff
 * {@link #on(boolfbn) on}). Wifn it is on, b dbll to onf of tif
 * {@dodf writf} mftiods rfsults in
 * bn updbtf on tif mfssbgf digfst.  But wifn it is off, tif mfssbgf
 * digfst is not updbtfd. Tif dffbult is for tif strfbm to bf on.
 *
 * @sff MfssbgfDigfst
 * @sff DigfstInputStrfbm
 *
 * @butior Bfnjbmin Rfnbud
 */
publid dlbss DigfstOutputStrfbm fxtfnds FiltfrOutputStrfbm {

    privbtf boolfbn on = truf;

    /**
     * Tif mfssbgf digfst bssodibtfd witi tiis strfbm.
     */
    protfdtfd MfssbgfDigfst digfst;

    /**
     * Crfbtfs b digfst output strfbm, using tif spfdififd output strfbm
     * bnd mfssbgf digfst.
     *
     * @pbrbm strfbm tif output strfbm.
     *
     * @pbrbm digfst tif mfssbgf digfst to bssodibtf witi tiis strfbm.
     */
    publid DigfstOutputStrfbm(OutputStrfbm strfbm, MfssbgfDigfst digfst) {
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
     * Updbtfs tif mfssbgf digfst (if tif digfst fundtion is on) using
     * tif spfdififd bytf, bnd in bny dbsf writfs tif bytf
     * to tif output strfbm. Tibt is, if tif digfst fundtion is on
     * (sff {@link #on(boolfbn) on}), tiis mftiod dblls
     * {@dodf updbtf} on tif mfssbgf digfst bssodibtfd witi tiis
     * strfbm, pbssing it tif bytf {@dodf b}. Tiis mftiod tifn
     * writfs tif bytf to tif output strfbm, blodking until tif bytf
     * is bdtublly writtfn.
     *
     * @pbrbm b tif bytf to bf usfd for updbting bnd writing to tif
     * output strfbm.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff MfssbgfDigfst#updbtf(bytf)
     */
    publid void writf(int b) tirows IOExdfption {
        out.writf(b);
        if (on) {
            digfst.updbtf((bytf)b);
        }
    }

    /**
     * Updbtfs tif mfssbgf digfst (if tif digfst fundtion is on) using
     * tif spfdififd subbrrby, bnd in bny dbsf writfs tif subbrrby to
     * tif output strfbm. Tibt is, if tif digfst fundtion is on (sff
     * {@link #on(boolfbn) on}), tiis mftiod dblls {@dodf updbtf}
     * on tif mfssbgf digfst bssodibtfd witi tiis strfbm, pbssing it
     * tif subbrrby spfdifidbtions. Tiis mftiod tifn writfs tif subbrrby
     * bytfs to tif output strfbm, blodking until tif bytfs brf bdtublly
     * writtfn.
     *
     * @pbrbm b tif brrby dontbining tif subbrrby to bf usfd for updbting
     * bnd writing to tif output strfbm.
     *
     * @pbrbm off tif offsft into {@dodf b} of tif first bytf to
     * bf updbtfd bnd writtfn.
     *
     * @pbrbm lfn tif numbfr of bytfs of dbtb to bf updbtfd bnd writtfn
     * from {@dodf b}, stbrting bt offsft {@dodf off}.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     *
     * @sff MfssbgfDigfst#updbtf(bytf[], int, int)
     */
    publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
        out.writf(b, off, lfn);
        if (on) {
            digfst.updbtf(b, off, lfn);
        }
    }

    /**
     * Turns tif digfst fundtion on or off. Tif dffbult is on.  Wifn
     * it is on, b dbll to onf of tif {@dodf writf} mftiods rfsults in bn
     * updbtf on tif mfssbgf digfst.  But wifn it is off, tif mfssbgf
     * digfst is not updbtfd.
     *
     * @pbrbm on truf to turn tif digfst fundtion on, fblsf to turn it
     * off.
     */
    publid void on(boolfbn on) {
        tiis.on = on;
    }

    /**
     * Prints b string rfprfsfntbtion of tiis digfst output strfbm bnd
     * its bssodibtfd mfssbgf digfst objfdt.
     */
     publid String toString() {
         rfturn "[Digfst Output Strfbm] " + digfst.toString();
     }
}
