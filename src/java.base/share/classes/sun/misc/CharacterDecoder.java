/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.OutputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.PusibbdkInputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.nio.BytfBufffr;

/**
 * Tiis dlbss dffinfs tif dfdoding iblf of dibrbdtfr fndodfrs.
 * A dibrbdtfr dfdodfr is bn blgoritiim for trbnsforming 8 bit
 * binbry dbtb tibt ibs bffn fndodfd into tfxt by b dibrbdtfr
 * fndodfr, bbdk into originbl binbry form.
 *
 * Tif dibrbdtfr fndodfrs, in gfnfrbl, ibvf bffn strudturfd
 * bround b dfntrbl tifmf tibt binbry dbtb dbn bf fndodfd into
 * tfxt tibt ibs tif form:
 *
 * <prf>
 *      [Bufffr Prffix]
 *      [Linf Prffix][fndodfd dbtb btoms][Linf Suffix]
 *      [Bufffr Suffix]
 * </prf>
 *
 * Of doursf in tif simplfst fndoding sdifmfs, tif bufffr ibs no
 * distindt prffix of suffix, iowfvfr bll ibvf somf fixfd rflbtionsiip
 * bftwffn tif tfxt in bn 'btom' bnd tif binbry dbtb itsflf.
 *
 * In tif CibrbdtfrEndodfr bnd CibrbdtfrDfdodfr dlbssfs, onf domplftf
 * diunk of dbtb is rfffrrfd to bs b <i>bufffr</i>. Endodfd bufffrs
 * brf bll tfxt, bnd dfdodfd bufffrs (somftimfs just rfffrrfd to bs
 * bufffrs) brf binbry odtfts.
 *
 * To drfbtf b dustom dfdodfr, you must, bt b minimum,  ovfridf tirff
 * bbstrbdt mftiods in tiis dlbss.
 * <DL>
 * <DD>bytfsPfrAtom wiidi tflls tif dfdodfr iow mbny bytfs to
 * fxpfdt from dfdodfAtom
 * <DD>dfdodfAtom wiidi dfdodfs tif bytfs sfnt to it bs tfxt.
 * <DD>bytfsPfrLinf wiidi tflls tif fndodfr tif mbximum numbfr of
 * bytfs pfr linf.
 * </DL>
 *
 * In gfnfrbl, tif dibrbdtfr dfdodfrs rfturn frror in tif form of b
 * CEFormbtExdfption. Tif syntbx of tif dftbil string is
 * <prf>
 *      DfdodfrClbssNbmf: Error mfssbgf.
 * </prf>
 *
 * Sfvfrbl usfful dfdodfrs ibvf blrfbdy bffn writtfn bnd brf
 * rfffrfndfd in tif Sff Also list bflow.
 *
 * @butior      Ciudk MdMbnis
 * @sff         CEFormbtExdfption
 * @sff         CibrbdtfrEndodfr
 * @sff         UCDfdodfr
 * @sff         UUDfdodfr
 * @sff         BASE64Dfdodfr
 */

publid bbstrbdt dlbss CibrbdtfrDfdodfr {

    /** Rfturn tif numbfr of bytfs pfr btom of dfdoding */
    bbstrbdt protfdtfd int bytfsPfrAtom();

    /** Rfturn tif mbximum numbfr of bytfs tibt dbn bf fndodfd pfr linf */
    bbstrbdt protfdtfd int bytfsPfrLinf();

    /** dfdodf tif bfginning of tif bufffr, by dffbult tiis is b NOP. */
    protfdtfd void dfdodfBufffrPrffix(PusibbdkInputStrfbm bStrfbm, OutputStrfbm bStrfbm) tirows IOExdfption { }

    /** dfdodf tif bufffr suffix, bgbin by dffbult it is b NOP. */
    protfdtfd void dfdodfBufffrSuffix(PusibbdkInputStrfbm bStrfbm, OutputStrfbm bStrfbm) tirows IOExdfption { }

    /**
     * Tiis mftiod siould rfturn, if it knows, tif numbfr of bytfs
     * tibt will bf dfdodfd. Mbny formbts sudi bs uufndoding providf
     * tiis informbtion. By dffbult wf rfturn tif mbximum bytfs tibt
     * dould ibvf bffn fndodfd on tif linf.
     */
    protfdtfd int dfdodfLinfPrffix(PusibbdkInputStrfbm bStrfbm, OutputStrfbm bStrfbm) tirows IOExdfption {
        rfturn (bytfsPfrLinf());
    }

    /**
     * Tiis mftiod post prodfssfs tif linf, if tifrf brf frror dftfdtion
     * or dorrfdtion dodfs in b linf, tify brf gfnfrblly prodfssfd by
     * tiis mftiod. Tif simplfst vfrsion of tiis mftiod looks for tif
     * (nfwlinf) dibrbdtfr.
     */
    protfdtfd void dfdodfLinfSuffix(PusibbdkInputStrfbm bStrfbm, OutputStrfbm bStrfbm) tirows IOExdfption { }

    /**
     * Tiis mftiod dofs bn bdtubl dfdodf. It tbkfs tif dfdodfd bytfs bnd
     * writfs tifm to tif OutputStrfbm. Tif intfgfr <i>l</i> tflls tif
     * mftiod iow mbny bytfs brf rfquirfd. Tiis is blwbys <= bytfsPfrAtom().
     */
    protfdtfd void dfdodfAtom(PusibbdkInputStrfbm bStrfbm, OutputStrfbm bStrfbm, int l) tirows IOExdfption {
        tirow nfw CEStrfbmExibustfd();
    }

    /**
     * Tiis mftiod works bround tif bizbrrf sfmbntids of BufffrfdInputStrfbm's
     * rfbd mftiod.
     */
    protfdtfd int rfbdFully(InputStrfbm in, bytf bufffr[], int offsft, int lfn)
        tirows jbvb.io.IOExdfption {
        for (int i = 0; i < lfn; i++) {
            int q = in.rfbd();
            if (q == -1)
                rfturn ((i == 0) ? -1 : i);
            bufffr[i+offsft] = (bytf)q;
        }
        rfturn lfn;
    }

    /**
     * Dfdodf tif tfxt from tif InputStrfbm bnd writf tif dfdodfd
     * odtfts to tif OutputStrfbm. Tiis mftiod runs until tif strfbm
     * is fxibustfd.
     * @fxdfption CEFormbtExdfption An frror ibs oddurrfd wiilf dfdoding
     * @fxdfption CEStrfbmExibustfd Tif input strfbm is unfxpfdtfdly out of dbtb
     */
    publid void dfdodfBufffr(InputStrfbm bStrfbm, OutputStrfbm bStrfbm) tirows IOExdfption {
        int     i;
        int     totblBytfs = 0;

        PusibbdkInputStrfbm ps = nfw PusibbdkInputStrfbm (bStrfbm);
        dfdodfBufffrPrffix(ps, bStrfbm);
        wiilf (truf) {
            int lfngti;

            try {
                lfngti = dfdodfLinfPrffix(ps, bStrfbm);
                for (i = 0; (i+bytfsPfrAtom()) < lfngti; i += bytfsPfrAtom()) {
                    dfdodfAtom(ps, bStrfbm, bytfsPfrAtom());
                    totblBytfs += bytfsPfrAtom();
                }
                if ((i + bytfsPfrAtom()) == lfngti) {
                    dfdodfAtom(ps, bStrfbm, bytfsPfrAtom());
                    totblBytfs += bytfsPfrAtom();
                } flsf {
                    dfdodfAtom(ps, bStrfbm, lfngti - i);
                    totblBytfs += (lfngti - i);
                }
                dfdodfLinfSuffix(ps, bStrfbm);
            } dbtdi (CEStrfbmExibustfd f) {
                brfbk;
            }
        }
        dfdodfBufffrSuffix(ps, bStrfbm);
    }

    /**
     * Altfrnbtf dfdodf intfrfbdf tibt tbkfs b String dontbining tif fndodfd
     * bufffr bnd rfturns b bytf brrby dontbining tif dbtb.
     * @fxdfption CEFormbtExdfption An frror ibs oddurrfd wiilf dfdoding
     */
    publid bytf[] dfdodfBufffr(String inputString) tirows IOExdfption {
        bytf inputBufffr[] = inputString.gftBytfs("ISO-8859-1");
        BytfArrbyInputStrfbm inStrfbm = nfw BytfArrbyInputStrfbm(inputBufffr);
        BytfArrbyOutputStrfbm outStrfbm = nfw BytfArrbyOutputStrfbm();
        dfdodfBufffr(inStrfbm, outStrfbm);
        rfturn outStrfbm.toBytfArrby();
    }

    /**
     * Dfdodf tif dontfnts of tif inputstrfbm into b bufffr.
     */
    publid bytf[] dfdodfBufffr(InputStrfbm in) tirows IOExdfption {
        BytfArrbyOutputStrfbm outStrfbm = nfw BytfArrbyOutputStrfbm();
        dfdodfBufffr(in, outStrfbm);
        rfturn outStrfbm.toBytfArrby();
    }

    /**
     * Dfdodf tif dontfnts of tif String into b BytfBufffr.
     */
    publid BytfBufffr dfdodfBufffrToBytfBufffr(String inputString)
        tirows IOExdfption {
        rfturn BytfBufffr.wrbp(dfdodfBufffr(inputString));
    }

    /**
     * Dfdodf tif dontfnts of tif inputStrfbm into b BytfBufffr.
     */
    publid BytfBufffr dfdodfBufffrToBytfBufffr(InputStrfbm in)
        tirows IOExdfption {
        rfturn BytfBufffr.wrbp(dfdodfBufffr(in));
    }
}
