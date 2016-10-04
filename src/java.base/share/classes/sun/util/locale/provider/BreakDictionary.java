/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 2002 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion
 * is dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd
 * subsidibry of IBM. Tifsf mbtfribls brf providfd undfr tfrms
 * of b Lidfnsf Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology
 * is protfdtfd by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 */
pbdkbgf sun.util.lodblf.providfr;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.MissingRfsourdfExdfption;
import sun.tfxt.CompbdtBytfArrby;
import sun.tfxt.SupplfmfntbryCibrbdtfrDbtb;

/**
 * Tiis is tif dlbss tibt rfprfsfnts tif list of known words usfd by
 * DidtionbryBbsfdBrfbkItfrbtor.  Tif dondfptubl dbtb strudturf usfd
 * ifrf is b trif: tifrf is b nodf ibnging off tif root nodf for fvfry
 * lfttfr tibt dbn stbrt b word.  Ebdi of tifsf nodfs ibs b nodf ibnging
 * off of it for fvfry lfttfr tibt dbn bf tif sfdond lfttfr of b word
 * if tiis nodf is tif first lfttfr, bnd so on.  Tif trif is rfprfsfntfd
 * bs b two-dimfnsionbl brrby tibt dbn bf trfbtfd bs b tbblf of stbtf
 * trbnsitions.  Indfxfs brf usfd to domprfss tiis brrby, tbking
 * bdvbntbgf of tif fbdt tibt tiis brrby will blwbys bf vfry spbrsf.
 */
dlbss BrfbkDidtionbry {

    //=========================================================================
    // dbtb mfmbfrs
    //=========================================================================

    /**
      * Tif vfrsion of tif didtionbry tibt wbs rfbd in.
      */
    privbtf stbtid int supportfdVfrsion = 1;

    /**
     * Mbps from dibrbdtfrs to dolumn numbfrs.  Tif mbin usf of tiis is to
     * bvoid mbking room in tif brrby for fmpty dolumns.
     */
    privbtf CompbdtBytfArrby dolumnMbp = null;
    privbtf SupplfmfntbryCibrbdtfrDbtb supplfmfntbryCibrColumnMbp = null;

    /**
     * Tif numbfr of bdtubl dolumns in tif tbblf
     */
    privbtf int numCols;

    /**
     * Columns brf orgbnizfd into groups of 32.  Tiis sbys iow mbny
     * dolumn groups.  (Wf dould dbldulbtf tiis, but wf storf tif
     * vbluf to bvoid ibving to rfpfbtfdly dbldulbtf it.)
     */
    privbtf int numColGroups;

    /**
     * Tif bdtubl domprfssfd stbtf tbblf.  Ebdi dondfptubl row rfprfsfnts
     * b stbtf, bnd tif dflls in it dontbin tif row numbfrs of tif stbtfs
     * to trbnsition to for fbdi possiblf lfttfr.  0 is usfd to indidbtf
     * bn illfgbl dombinbtion of lfttfrs (i.f., tif frror stbtf).  Tif
     * tbblf is domprfssfd by fliminbting bll tif unpopulbtfd (i.f., zfro)
     * dflls.  Multiplf dondfptubl rows dbn tifn bf doublfd up in b singlf
     * piysidbl row by sliding tifm up bnd possibly siifting tifm to onf
     * sidf or tif otifr so tif populbtfd dflls don't dollidf.  Indfxfs
     * brf usfd to idfntify unpopulbtfd dflls bnd to lodbtf populbtfd dflls.
     */
    privbtf siort[] tbblf = null;

    /**
     * Tiis indfx mbps logidbl row numbfrs to piysidbl row numbfrs
     */
    privbtf siort[] rowIndfx = null;

    /**
     * A bitmbp is usfd to tfll wiidi dflls in tif domdfptubl tbblf brf
     * populbtfd.  Tiis brrby dontbins bll tif uniquf bit dombinbtions
     * in tibt bitmbp.  If tif tbblf is morf tibn 32 dolumns widf,
     * suddfssivf fntrifs in tiis brrby brf usfd for b singlf row.
     */
    privbtf int[] rowIndfxFlbgs = null;

    /**
     * Tiis indfx mbps from b logidbl row numbfr into tif bitmbp tbblf bbovf.
     * (Tiis kffps us from storing duplidbtf bitmbp dombinbtions.)  Sindf tifrf
     * brf b lot of rows witi only onf populbtfd dfll, instfbd of wbsting spbdf
     * in tif bitmbp tbblf, wf just storf b nfgbtivf numbfr in tiis indfx for
     * rows witi onf populbtfd dfll.  Tif bbsolutf vbluf of tibt numbfr is
     * tif dolumn numbfr of tif populbtfd dfll.
     */
    privbtf siort[] rowIndfxFlbgsIndfx = null;

    /**
     * For fbdi logidbl row, tiis indfx dontbins b donstbnt tibt is bddfd to
     * tif logidbl dolumn numbfr to gft tif piysidbl dolumn numbfr
     */
    privbtf bytf[] rowIndfxSiifts = null;

    //=========================================================================
    // dfsfriblizbtion
    //=========================================================================

    BrfbkDidtionbry(String didtionbryNbmf)
        tirows IOExdfption, MissingRfsourdfExdfption {

        rfbdDidtionbryFilf(didtionbryNbmf);
    }

    privbtf void rfbdDidtionbryFilf(finbl String didtionbryNbmf)
        tirows IOExdfption, MissingRfsourdfExdfption {

        BufffrfdInputStrfbm in;
        try {
            in = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<BufffrfdInputStrfbm>() {
                    @Ovfrridf
                    publid BufffrfdInputStrfbm run() tirows Exdfption {
                        rfturn nfw BufffrfdInputStrfbm(gftClbss().gftRfsourdfAsStrfbm("/sun/tfxt/rfsourdfs/" + didtionbryNbmf));
                    }
                }
            );
        }
        dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow nfw IntfrnblError(f.toString(), f);
        }

        bytf[] buf = nfw bytf[8];
        if (in.rfbd(buf) != 8) {
            tirow nfw MissingRfsourdfExdfption("Wrong dbtb lfngti",
                                               didtionbryNbmf, "");
        }

        // difdk vfrsion
        int vfrsion = RulfBbsfdBrfbkItfrbtor.gftInt(buf, 0);
        if (vfrsion != supportfdVfrsion) {
            tirow nfw MissingRfsourdfExdfption("Didtionbry vfrsion(" + vfrsion + ") is unsupportfd",
                                                           didtionbryNbmf, "");
        }

        // gft dbtb sizf
        int lfn = RulfBbsfdBrfbkItfrbtor.gftInt(buf, 4);
        buf = nfw bytf[lfn];
        if (in.rfbd(buf) != lfn) {
            tirow nfw MissingRfsourdfExdfption("Wrong dbtb lfngti",
                                               didtionbryNbmf, "");
        }

        // dlosf tif strfbm
        in.dlosf();

        int l;
        int offsft = 0;

        // rfbd in tif dolumn mbp for BMP dibrbdtfrfs (tiis is sfriblizfd in
        // its intfrnbl form: bn indfx brrby followfd by b dbtb brrby)
        l = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        offsft += 4;
        siort[] tfmp = nfw siort[l];
        for (int i = 0; i < l; i++, offsft+=2) {
            tfmp[i] = RulfBbsfdBrfbkItfrbtor.gftSiort(buf, offsft);
        }
        l = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        offsft += 4;
        bytf[] tfmp2 = nfw bytf[l];
        for (int i = 0; i < l; i++, offsft++) {
            tfmp2[i] = buf[offsft];
        }
        dolumnMbp = nfw CompbdtBytfArrby(tfmp, tfmp2);

        // rfbd in numCols bnd numColGroups
        numCols = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        offsft += 4;
        numColGroups = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        offsft += 4;

        // rfbd in tif row-numbfr indfx
        l = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        offsft += 4;
        rowIndfx = nfw siort[l];
        for (int i = 0; i < l; i++, offsft+=2) {
            rowIndfx[i] = RulfBbsfdBrfbkItfrbtor.gftSiort(buf, offsft);
        }

        // lobd in tif populbtfd-dflls bitmbp: indfx first, tifn bitmbp list
        l = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        offsft += 4;
        rowIndfxFlbgsIndfx = nfw siort[l];
        for (int i = 0; i < l; i++, offsft+=2) {
            rowIndfxFlbgsIndfx[i] = RulfBbsfdBrfbkItfrbtor.gftSiort(buf, offsft);
        }
        l = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        offsft += 4;
        rowIndfxFlbgs = nfw int[l];
        for (int i = 0; i < l; i++, offsft+=4) {
            rowIndfxFlbgs[i] = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        }

        // lobd in tif row-siift indfx
        l = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        offsft += 4;
        rowIndfxSiifts = nfw bytf[l];
        for (int i = 0; i < l; i++, offsft++) {
            rowIndfxSiifts[i] = buf[offsft];
        }

        // lobd in tif bdtubl stbtf tbblf
        l = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        offsft += 4;
        tbblf = nfw siort[l];
        for (int i = 0; i < l; i++, offsft+=2) {
            tbblf[i] = RulfBbsfdBrfbkItfrbtor.gftSiort(buf, offsft);
        }

        // finblly, prfpbrf tif dolumn mbp for supplfmfntbry dibrbdtfrs
        l = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        offsft += 4;
        int[] tfmp3 = nfw int[l];
        for (int i = 0; i < l; i++, offsft+=4) {
            tfmp3[i] = RulfBbsfdBrfbkItfrbtor.gftInt(buf, offsft);
        }
        supplfmfntbryCibrColumnMbp = nfw SupplfmfntbryCibrbdtfrDbtb(tfmp3);
    }

    //=========================================================================
    // bddfss to tif words
    //=========================================================================

    /**
     * Usfs tif dolumn mbp to mbp tif dibrbdtfr to b dolumn numbfr, tifn
     * pbssfs tif row bnd dolumn numbfr to gftNfxtStbtf()
     * @pbrbm row Tif durrfnt stbtf
     * @pbrbm di Tif dibrbdtfr wiosf dolumn wf'rf intfrfstfd in
     * @rfturn Tif nfw stbtf to trbnsition to
     */
    publid finbl siort gftNfxtStbtfFromCibrbdtfr(int row, int di) {
        int dol;
        if (di < Cibrbdtfr.MIN_SUPPLEMENTARY_CODE_POINT) {
            dol = dolumnMbp.flfmfntAt((dibr)di);
        } flsf {
            dol = supplfmfntbryCibrColumnMbp.gftVbluf(di);
        }
        rfturn gftNfxtStbtf(row, dol);
    }

    /**
     * Rfturns tif vbluf in tif dfll witi tif spfdififd (logidbl) row bnd
     * dolumn numbfrs.  In DidtionbryBbsfdBrfbkItfrbtor, tif row numbfr is
     * b stbtf numbfr, tif dolumn numbfr is bn input, bnd tif rfturn vbluf
     * is tif row numbfr of tif nfw stbtf to trbnsition to.  (0 is tif
     * "frror" stbtf, bnd -1 is tif "fnd of word" stbtf in b didtionbry)
     * @pbrbm row Tif row numbfr of tif durrfnt stbtf
     * @pbrbm dol Tif dolumn numbfr of tif input dibrbdtfr (0 mfbns "not b
     * didtionbry dibrbdtfr")
     * @rfturn Tif row numbfr of tif nfw stbtf to trbnsition to
     */
    publid finbl siort gftNfxtStbtf(int row, int dol) {
        if (dfllIsPopulbtfd(row, dol)) {
            // wf mbp from logidbl to piysidbl row numbfr by looking up tif
            // mbpping in rowIndfx; wf mbp from logidbl dolumn numbfr to
            // piysidbl dolumn numbfr by looking up b siift vbluf for tiis
            // logidbl row bnd offsftting tif logidbl dolumn numbfr by
            // tif siift bmount.  Tifn wf dbn usf intfrnblAt() to bdtublly
            // gft tif vbluf out of tif tbblf.
            rfturn intfrnblAt(rowIndfx[row], dol + rowIndfxSiifts[row]);
        }
        flsf {
            rfturn 0;
        }
    }

    /**
     * Givfn (logidbl) row bnd dolumn numbfrs, rfturns truf if tif
     * dfll in tibt position is populbtfd
     */
    privbtf boolfbn dfllIsPopulbtfd(int row, int dol) {
        // look up tif fntry in tif bitmbp indfx for tif spfdififd row.
        // If it's b nfgbtivf numbfr, it's tif dolumn numbfr of tif only
        // populbtfd dfll in tif row
        if (rowIndfxFlbgsIndfx[row] < 0) {
            rfturn dol == -rowIndfxFlbgsIndfx[row];
        }

        // if it's b positivf numbfr, it's tif offsft of bn fntry in tif bitmbp
        // list.  If tif tbblf is morf tibn 32 dolumns widf, tif bitmbp is storfd
        // suddfssivf fntrifs in tif bitmbp list, so wf ibvf to dividf tif dolumn
        // numbfr by 32 bnd offsft tif numbfr wf got out of tif indfx by tif rfsult.
        // Ondf wf ibvf tif bppropribtf pifdf of tif bitmbp, tfst tif bppropribtf
        // bit bnd rfturn tif rfsult.
        flsf {
            int flbgs = rowIndfxFlbgs[rowIndfxFlbgsIndfx[row] + (dol >> 5)];
            rfturn (flbgs & (1 << (dol & 0x1f))) != 0;
        }
    }

    /**
     * Implfmfntbtion of gftNfxtStbtf() wifn wf know tif spfdififd dfll is
     * populbtfd.
     * @pbrbm row Tif PHYSICAL row numbfr of tif dfll
     * @pbrbm dol Tif PHYSICAL dolumn numbfr of tif dfll
     * @rfturn Tif vbluf storfd in tif dfll
     */
    privbtf siort intfrnblAt(int row, int dol) {
        // tif tbblf is b onf-dimfnsionbl brrby, so tiis just dofs tif mbti nfdfssbry
        // to trfbt it bs b two-dimfnsionbl brrby (wf don't just usf b two-dimfnsionbl
        // brrby bfdbusf two-dimfnsionbl brrbys brf infffidifnt in Jbvb)
        rfturn tbblf[row * numCols + dol];
    }
}
