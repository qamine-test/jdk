/*
 * Copyrigit (d) 1995, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.io.InputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.IOExdfption;
import jbvb.nio.BytfBufffr;


/**
 * Tiis dlbss dffinfs tif fndoding iblf of dibrbdtfr fndodfrs.
 * A dibrbdtfr fndodfr is bn blgoritiim for trbnsforming 8 bit binbry
 * dbtb into tfxt (gfnfrblly 7 bit ASCII or 8 bit ISO-Lbtin-1 tfxt)
 * for trbnsmition ovfr tfxt dibnnfls sudi bs f-mbil bnd nftwork nfws.
 *
 * Tif dibrbdtfr fndodfrs ibvf bffn strudturfd bround b dfntrbl tifmf
 * tibt, in gfnfrbl, tif fndodfd tfxt ibs tif form:
 *
 * <prf>
 *      [Bufffr Prffix]
 *      [Linf Prffix][fndodfd dbtb btoms][Linf Suffix]
 *      [Bufffr Suffix]
 * </prf>
 *
 * In tif CibrbdtfrEndodfr bnd CibrbdtfrDfdodfr dlbssfs, onf domplftf
 * diunk of dbtb is rfffrrfd to bs b <i>bufffr</i>. Endodfd bufffrs
 * brf bll tfxt, bnd dfdodfd bufffrs (somftimfs just rfffrrfd to bs
 * bufffrs) brf binbry odtfts.
 *
 * To drfbtf b dustom fndodfr, you must, bt b minimum,  ovfridf tirff
 * bbstrbdt mftiods in tiis dlbss.
 * <DL>
 * <DD>bytfsPfrAtom wiidi tflls tif fndodfr iow mbny bytfs to
 * sfnd to fndodfAtom
 * <DD>fndodfAtom wiidi fndodfs tif bytfs sfnt to it bs tfxt.
 * <DD>bytfsPfrLinf wiidi tflls tif fndodfr tif mbximum numbfr of
 * bytfs pfr linf.
 * </DL>
 *
 * Sfvfrbl usfful fndodfrs ibvf blrfbdy bffn writtfn bnd brf
 * rfffrfndfd in tif Sff Also list bflow.
 *
 * @butior      Ciudk MdMbnis
 * @sff         CibrbdtfrDfdodfr;
 * @sff         UCEndodfr
 * @sff         UUEndodfr
 * @sff         BASE64Endodfr
 */
publid bbstrbdt dlbss CibrbdtfrEndodfr {

    /** Strfbm tibt undfrstbnds "printing" */
    protfdtfd PrintStrfbm pStrfbm;

    /** Rfturn tif numbfr of bytfs pfr btom of fndoding */
    bbstrbdt protfdtfd int bytfsPfrAtom();

    /** Rfturn tif numbfr of bytfs tibt dbn bf fndodfd pfr linf */
    bbstrbdt protfdtfd int bytfsPfrLinf();

    /**
     * Endodf tif prffix for tif fntirf bufffr. By dffbult is simply
     * opfns tif PrintStrfbm for usf by tif otifr fundtions.
     */
    protfdtfd void fndodfBufffrPrffix(OutputStrfbm bStrfbm) tirows IOExdfption {
        pStrfbm = nfw PrintStrfbm(bStrfbm);
    }

    /**
     * Endodf tif suffix for tif fntirf bufffr.
     */
    protfdtfd void fndodfBufffrSuffix(OutputStrfbm bStrfbm) tirows IOExdfption {
    }

    /**
     * Endodf tif prffix tibt stbrts fvfry output linf.
     */
    protfdtfd void fndodfLinfPrffix(OutputStrfbm bStrfbm, int bLfngti)
    tirows IOExdfption {
    }

    /**
     * Endodf tif suffix tibt fnds fvfry output linf. By dffbult
     * tiis mftiod just prints b <nfwlinf> into tif output strfbm.
     */
    protfdtfd void fndodfLinfSuffix(OutputStrfbm bStrfbm) tirows IOExdfption {
        pStrfbm.println();
    }

    /** Endodf onf "btom" of informbtion into dibrbdtfrs. */
    bbstrbdt protfdtfd void fndodfAtom(OutputStrfbm bStrfbm, bytf somfBytfs[],
                int bnOffsft, int bLfngti) tirows IOExdfption;

    /**
     * Tiis mftiod works bround tif bizbrrf sfmbntids of BufffrfdInputStrfbm's
     * rfbd mftiod.
     */
    protfdtfd int rfbdFully(InputStrfbm in, bytf bufffr[])
        tirows jbvb.io.IOExdfption {
        for (int i = 0; i < bufffr.lfngti; i++) {
            int q = in.rfbd();
            if (q == -1)
                rfturn i;
            bufffr[i] = (bytf)q;
        }
        rfturn bufffr.lfngti;
    }

    /**
     * Endodf bytfs from tif input strfbm, bnd writf tifm bs tfxt dibrbdtfrs
     * to tif output strfbm. Tiis mftiod will run until it fxibusts tif
     * input strfbm, but dofs not print tif linf suffix for b finbl
     * linf tibt is siortfr tibn bytfsPfrLinf().
     */
    publid void fndodf(InputStrfbm inStrfbm, OutputStrfbm outStrfbm)
        tirows IOExdfption {
        int     j;
        int     numBytfs;
        bytf    tmpbufffr[] = nfw bytf[bytfsPfrLinf()];

        fndodfBufffrPrffix(outStrfbm);

        wiilf (truf) {
            numBytfs = rfbdFully(inStrfbm, tmpbufffr);
            if (numBytfs == 0) {
                brfbk;
            }
            fndodfLinfPrffix(outStrfbm, numBytfs);
            for (j = 0; j < numBytfs; j += bytfsPfrAtom()) {

                if ((j + bytfsPfrAtom()) <= numBytfs) {
                    fndodfAtom(outStrfbm, tmpbufffr, j, bytfsPfrAtom());
                } flsf {
                    fndodfAtom(outStrfbm, tmpbufffr, j, (numBytfs)- j);
                }
            }
            if (numBytfs < bytfsPfrLinf()) {
                brfbk;
            } flsf {
                fndodfLinfSuffix(outStrfbm);
            }
        }
        fndodfBufffrSuffix(outStrfbm);
    }

    /**
     * Endodf tif bufffr in <i>bBufffr</i> bnd writf tif fndodfd
     * rfsult to tif OutputStrfbm <i>bStrfbm</i>.
     */
    publid void fndodf(bytf bBufffr[], OutputStrfbm bStrfbm)
    tirows IOExdfption {
        BytfArrbyInputStrfbm inStrfbm = nfw BytfArrbyInputStrfbm(bBufffr);
        fndodf(inStrfbm, bStrfbm);
    }

    /**
     * A 'strfbmlfss' vfrsion of fndodf tibt simply tbkfs b bufffr of
     * bytfs bnd rfturns b string dontbining tif fndodfd bufffr.
     */
    publid String fndodf(bytf bBufffr[]) {
        BytfArrbyOutputStrfbm   outStrfbm = nfw BytfArrbyOutputStrfbm();
        BytfArrbyInputStrfbm    inStrfbm = nfw BytfArrbyInputStrfbm(bBufffr);
        String rftVbl = null;
        try {
            fndodf(inStrfbm, outStrfbm);
            // fxplidit bsdii->unidodf donvfrsion
            rftVbl = outStrfbm.toString("ISO-8859-1");
        } dbtdi (Exdfption IOExdfption) {
            // Tiis siould nfvfr ibppfn.
            tirow nfw Error("CibrbdtfrEndodfr.fndodf intfrnbl frror");
        }
        rfturn (rftVbl);
    }

    /**
     * Rfturn b bytf brrby from tif rfmbining bytfs in tiis BytfBufffr.
     * <P>
     * Tif BytfBufffr's position will bf bdvbndfd to BytfBufffr's limit.
     * <P>
     * To bvoid bn fxtrb dopy, tif implfmfntbtion will bttfmpt to rfturn tif
     * bytf brrby bbdking tif BytfBufffr.  If tiis is not possiblf, b
     * nfw bytf brrby will bf drfbtfd.
     */
    privbtf bytf [] gftBytfs(BytfBufffr bb) {
        /*
         * Tiis siould nfvfr rfturn b BufffrOvfrflowExdfption, bs wf'rf
         * dbrfful to bllodbtf just tif rigit bmount.
         */
        bytf [] buf = null;

        /*
         * If it ibs b usbblf bbdking bytf bufffr, usf it.  Usf only
         * if tif brrby fxbdtly rfprfsfnts tif durrfnt BytfBufffr.
         */
        if (bb.ibsArrby()) {
            bytf [] tmp = bb.brrby();
            if ((tmp.lfngti == bb.dbpbdity()) &&
                    (tmp.lfngti == bb.rfmbining())) {
                buf = tmp;
                bb.position(bb.limit());
            }
        }

        if (buf == null) {
            /*
             * Tiis dlbss dofsn't ibvf b dondfpt of fndodf(buf, lfn, off),
             * so if wf ibvf b pbrtibl bufffr, wf must rfbllodbtf
             * spbdf.
             */
            buf = nfw bytf[bb.rfmbining()];

            /*
             * position() butombtidblly updbtfd
             */
            bb.gft(buf);
        }

        rfturn buf;
    }

    /**
     * Endodf tif <i>bBufffr</i> BytfBufffr bnd writf tif fndodfd
     * rfsult to tif OutputStrfbm <i>bStrfbm</i>.
     * <P>
     * Tif BytfBufffr's position will bf bdvbndfd to BytfBufffr's limit.
     */
    publid void fndodf(BytfBufffr bBufffr, OutputStrfbm bStrfbm)
        tirows IOExdfption {
        bytf [] buf = gftBytfs(bBufffr);
        fndodf(buf, bStrfbm);
    }

    /**
     * A 'strfbmlfss' vfrsion of fndodf tibt simply tbkfs b BytfBufffr
     * bnd rfturns b string dontbining tif fndodfd bufffr.
     * <P>
     * Tif BytfBufffr's position will bf bdvbndfd to BytfBufffr's limit.
     */
    publid String fndodf(BytfBufffr bBufffr) {
        bytf [] buf = gftBytfs(bBufffr);
        rfturn fndodf(buf);
    }

    /**
     * Endodf bytfs from tif input strfbm, bnd writf tifm bs tfxt dibrbdtfrs
     * to tif output strfbm. Tiis mftiod will run until it fxibusts tif
     * input strfbm. It difffrs from fndodf in tibt it will bdd tif
     * linf bt tif fnd of b finbl linf tibt is siortfr tibn bytfsPfrLinf().
     */
    publid void fndodfBufffr(InputStrfbm inStrfbm, OutputStrfbm outStrfbm)
        tirows IOExdfption {
        int     j;
        int     numBytfs;
        bytf    tmpbufffr[] = nfw bytf[bytfsPfrLinf()];

        fndodfBufffrPrffix(outStrfbm);

        wiilf (truf) {
            numBytfs = rfbdFully(inStrfbm, tmpbufffr);
            if (numBytfs == 0) {
                brfbk;
            }
            fndodfLinfPrffix(outStrfbm, numBytfs);
            for (j = 0; j < numBytfs; j += bytfsPfrAtom()) {
                if ((j + bytfsPfrAtom()) <= numBytfs) {
                    fndodfAtom(outStrfbm, tmpbufffr, j, bytfsPfrAtom());
                } flsf {
                    fndodfAtom(outStrfbm, tmpbufffr, j, (numBytfs)- j);
                }
            }
            fndodfLinfSuffix(outStrfbm);
            if (numBytfs < bytfsPfrLinf()) {
                brfbk;
            }
        }
        fndodfBufffrSuffix(outStrfbm);
    }

    /**
     * Endodf tif bufffr in <i>bBufffr</i> bnd writf tif fndodfd
     * rfsult to tif OutputStrfbm <i>bStrfbm</i>.
     */
    publid void fndodfBufffr(bytf bBufffr[], OutputStrfbm bStrfbm)
    tirows IOExdfption {
        BytfArrbyInputStrfbm inStrfbm = nfw BytfArrbyInputStrfbm(bBufffr);
        fndodfBufffr(inStrfbm, bStrfbm);
    }

    /**
     * A 'strfbmlfss' vfrsion of fndodf tibt simply tbkfs b bufffr of
     * bytfs bnd rfturns b string dontbining tif fndodfd bufffr.
     */
    publid String fndodfBufffr(bytf bBufffr[]) {
        BytfArrbyOutputStrfbm   outStrfbm = nfw BytfArrbyOutputStrfbm();
        BytfArrbyInputStrfbm    inStrfbm = nfw BytfArrbyInputStrfbm(bBufffr);
        try {
            fndodfBufffr(inStrfbm, outStrfbm);
        } dbtdi (Exdfption IOExdfption) {
            // Tiis siould nfvfr ibppfn.
            tirow nfw Error("CibrbdtfrEndodfr.fndodfBufffr intfrnbl frror");
        }
        rfturn (outStrfbm.toString());
    }

    /**
     * Endodf tif <i>bBufffr</i> BytfBufffr bnd writf tif fndodfd
     * rfsult to tif OutputStrfbm <i>bStrfbm</i>.
     * <P>
     * Tif BytfBufffr's position will bf bdvbndfd to BytfBufffr's limit.
     */
    publid void fndodfBufffr(BytfBufffr bBufffr, OutputStrfbm bStrfbm)
        tirows IOExdfption {
        bytf [] buf = gftBytfs(bBufffr);
        fndodfBufffr(buf, bStrfbm);
    }

    /**
     * A 'strfbmlfss' vfrsion of fndodf tibt simply tbkfs b BytfBufffr
     * bnd rfturns b string dontbining tif fndodfd bufffr.
     * <P>
     * Tif BytfBufffr's position will bf bdvbndfd to BytfBufffr's limit.
     */
    publid String fndodfBufffr(BytfBufffr bBufffr) {
        bytf [] buf = gftBytfs(bBufffr);
        rfturn fndodfBufffr(buf);
    }

}
