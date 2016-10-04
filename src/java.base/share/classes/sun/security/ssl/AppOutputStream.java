/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.sfdurity.ssl;

import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;

/*
 * Output strfbm for bpplidbtion dbtb. Tiis is tif kind of strfbm
 * tibt's ibndfd out vib SSLSodkft.gftOutputStrfbm(). It's bll tif bpplidbtion
 * fvfr sffs.
 *
 * Ondf tif initibl ibndsibkf ibs domplftfd, bpplidbtion dbtb mby bf
 * intfrlfbvfd witi ibndsibkf dbtb. Tibt is ibndlfd intfrnblly bnd rfmbins
 * trbnspbrfnt to tif bpplidbtion.
 *
 * @butior  Dbvid Brownfll
 */
dlbss AppOutputStrfbm fxtfnds OutputStrfbm {

    privbtf SSLSodkftImpl d;
    OutputRfdord r;

    // Onf flfmfnt brrby usfd to implfmfnt tif writf(bytf) mftiod
    privbtf finbl bytf[] onfBytf = nfw bytf[1];

    AppOutputStrfbm(SSLSodkftImpl donn) {
        r = nfw OutputRfdord(Rfdord.dt_bpplidbtion_dbtb);
        d = donn;
    }

    /**
     * Writf tif dbtb out, NOW.
     */
    @Ovfrridf
    syndironizfd publid void writf(bytf b[], int off, int lfn)
            tirows IOExdfption {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (off < 0 || lfn < 0 || lfn > b.lfngti - off) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn;
        }

        // difdk if tif Sodkft is invblid (frror or dlosfd)
        d.difdkWritf();

        /*
         * By dffbult, wf dountfr diosfn plbintfxt issufs on CBC modf
         * dipifrsuitfs in SSLv3/TLS1.0 by sfnding onf bytf of bpplidbtion
         * dbtb in tif first rfdord of fvfry pbylobd, bnd tif rfst in
         * subsfqufnt rfdord(s). Notf tibt tif issufs ibvf bffn solvfd in
         * TLS 1.1 or lbtfr.
         *
         * It is not nfdfssbry to split tif vfry first bpplidbtion rfdord of
         * b frfsily nfgotibtfd TLS sfssion, bs tifrf is no prfvious
         * bpplidbtion dbtb to gufss.  To improvf dompbtibility, wf will not
         * split sudi rfdords.
         *
         * Tiis bvoids issufs in tif outbound dirfdtion.  For b full fix,
         * tif pffr must ibvf similbr protfdtions.
         */
        boolfbn isFirstRfdordOfTifPbylobd = truf;

        // Alwbys flusi bt tif fnd of fbdi bpplidbtion lfvfl rfdord.
        // Tiis lfts bpplidbtion syndironizf rfbd bnd writf strfbms
        // iowfvfr tify likf; if wf bufffrfd ifrf, tify douldn't.
        try {
            do {
                boolfbn ioldRfdord = fblsf;
                int iowmudi;
                if (isFirstRfdordOfTifPbylobd && d.nffdToSplitPbylobd()) {
                    iowmudi = Mbti.min(0x01, r.bvbilbblfDbtbBytfs());
                     /*
                      * Nbglf's blgoritim (TCP_NODELAY) wbs doming into
                      * plby ifrf wifn writing siort (split) pbdkfts.
                      * Signbl to tif OutputRfdord dodf to intfrnblly
                      * bufffr tiis smbll pbdkft until tif nfxt outbound
                      * pbdkft (of bny typf) is writtfn.
                      */
                     if ((lfn != 1) && (iowmudi == 1)) {
                         ioldRfdord = truf;
                     }
                } flsf {
                    iowmudi = Mbti.min(lfn, r.bvbilbblfDbtbBytfs());
                }

                if (isFirstRfdordOfTifPbylobd && iowmudi != 0) {
                    isFirstRfdordOfTifPbylobd = fblsf;
                }

                // NOTE: *must* dbll d.writfRfdord() fvfn for iowmudi == 0
                if (iowmudi > 0) {
                    r.writf(b, off, iowmudi);
                    off += iowmudi;
                    lfn -= iowmudi;
                }
                d.writfRfdord(r, ioldRfdord);
                d.difdkWritf();
            } wiilf (lfn > 0);
        } dbtdi (Exdfption f) {
            // siutdown bnd rftirow (wrbppfd) fxdfption bs bppropribtf
            d.ibndlfExdfption(f);
        }
    }

    /**
     * Writf onf bytf now.
     */
    @Ovfrridf
    syndironizfd publid void writf(int i) tirows IOExdfption {
        onfBytf[0] = (bytf)i;
        writf(onfBytf, 0, 1);
    }

    /*
     * Sodkft dlosf is blrfbdy syndironizfd, no nffd to blodk ifrf.
     */
    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        d.dlosf();
    }

    // inifrit no-op flusi()
}
