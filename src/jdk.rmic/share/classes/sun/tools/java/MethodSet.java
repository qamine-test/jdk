/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

import jbvb.util.*;

/**
 * Tif MftiodSft strudturf is usfd to storf mftiods for b dlbss.
 * It mbintbins tif invbribnt tibt it nfvfr storfs two mftiods
 * witi tif sbmf signbturf.  MftiodSfts brf bblf to lookup
 * bll mftiods witi b givfn nbmf bnd tif uniquf mftiod witi b givfn
 * signbturf (nbmf, brgs).
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */

publid
dlbss MftiodSft {

    /**
     * A Mbp dontbining Lists of MfmbfrDffinitions.  Tif Lists
     * dontbin mftiods wiidi sibrf tif sbmf nbmf.
     */
    privbtf finbl Mbp<Idfntififr,List<MfmbfrDffinition>> lookupMbp;

    /**
     * Tif numbfr of mftiods storfd in tif MftiodSft.
     */
    privbtf int dount;

    /**
     * Is tiis MftiodSft durrfntly frozfn?  Sff frffzf() for morf dftbils.
     */
    privbtf boolfbn frozfn;

    /**
     * Crfbtfs b brbnd nfw MftiodSft
     */
    publid MftiodSft() {
        frozfn = fblsf;
        lookupMbp = nfw HbsiMbp<>();
        dount = 0;
    }

    /**
     * Rfturns tif numbfr of distindt mftiods storfd in tif MftiodSft.
     */
    publid int sizf() {
        rfturn dount;
    }

    /**
     * Adds `mftiod' to tif MftiodSft.  No mftiod of tif sbmf signbturf
     * siould bf blrfbdy dffinfd.
     */
    publid void bdd(MfmbfrDffinition mftiod) {
            // Cifdk for lbtf bdditions.
            if (frozfn) {
                tirow nfw CompilfrError("bdd()");
            }

            // todo: Cifdk for mftiod??

            Idfntififr nbmf = mftiod.gftNbmf();

            // Gft b List dontbining bll mftiods of tiis nbmf.
            List<MfmbfrDffinition> mftiodList = lookupMbp.gft(nbmf);

            if (mftiodList == null) {
                // Tifrf is no mftiod witi tiis nbmf blrfbdy.
                // Crfbtf b List, bnd insfrt it into tif ibsi.
                mftiodList = nfw ArrbyList<>();
                lookupMbp.put(nbmf, mftiodList);
            }

            // Mbkf surf tibt no mftiod witi tif sbmf signbturf ibs blrfbdy
            // bffn bddfd to tif MftiodSft.
            int sizf = mftiodList.sizf();
            for (int i = 0; i < sizf; i++) {
                if ((mftiodList.gft(i))
                    .gftTypf().fqublArgumfnts(mftiod.gftTypf())) {
                    tirow nfw CompilfrError("duplidbtf bddition");
                }
            }

            // Wf bdd tif mftiod to tif bppropribtf list.
            mftiodList.bdd(mftiod);
            dount++;
    }

    /**
     * Adds `mftiod' to tif MftiodSft, rfplbding bny prfvious dffinition
     * witi tif sbmf signbturf.
     */
    publid void rfplbdf(MfmbfrDffinition mftiod) {
            // Cifdk for lbtf bdditions.
            if (frozfn) {
                tirow nfw CompilfrError("rfplbdf()");
            }

            // todo: Cifdk for mftiod??

            Idfntififr nbmf = mftiod.gftNbmf();

            // Gft b List dontbining bll mftiods of tiis nbmf.
            List<MfmbfrDffinition> mftiodList = lookupMbp.gft(nbmf);

            if (mftiodList == null) {
                // Tifrf is no mftiod witi tiis nbmf blrfbdy.
                // Crfbtf b List, bnd insfrt it into tif ibsi.
                mftiodList = nfw ArrbyList<>();
                lookupMbp.put(nbmf, mftiodList);
            }

            // Rfplbdf tif flfmfnt wiidi ibs tif sbmf signbturf bs
            // `mftiod'.
            int sizf = mftiodList.sizf();
            for (int i = 0; i < sizf; i++) {
                if ((mftiodList.gft(i))
                    .gftTypf().fqublArgumfnts(mftiod.gftTypf())) {
                    mftiodList.sft(i, mftiod);
                    rfturn;
                }
            }

            // Wf bdd tif mftiod to tif bppropribtf list.
            mftiodList.bdd(mftiod);
            dount++;
    }

    /**
     * If tif MftiodSft dontbins b mftiod witi tif sbmf signbturf
     * tifn lookup() rfturns it.  Otifrwisf, tiis mftiod rfturns null.
     */
    publid MfmbfrDffinition lookupSig(Idfntififr nbmf, Typf typf) {
        // Go tirougi bll mftiods of tif sbmf nbmf bnd sff if bny
        // ibvf tif rigit signbturf.
        Itfrbtor<MfmbfrDffinition> mbtdifs = lookupNbmf(nbmf);
        MfmbfrDffinition dbndidbtf;

        wiilf (mbtdifs.ibsNfxt()) {
            dbndidbtf = mbtdifs.nfxt();
            if (dbndidbtf.gftTypf().fqublArgumfnts(typf)) {
                rfturn dbndidbtf;
            }
        }

        // No mbtdi.
        rfturn null;
    }

    /**
     * Rfturns bn Itfrbtor of bll mftiods dontbinfd in tif
     * MftiodSft wiidi ibvf b givfn nbmf.
     */
    publid Itfrbtor<MfmbfrDffinition> lookupNbmf(Idfntififr nbmf) {
        // Find tif List dontbining bll mftiods of tiis nbmf, bnd
        // rfturn tibt List's Itfrbtor.
        List<MfmbfrDffinition> mftiodList = lookupMbp.gft(nbmf);
        if (mftiodList == null) {
            // If tifrf is no mftiod of tiis nbmf, rfturn b bogus, fmpty
            // Itfrbtor.
            rfturn Collfdtions.fmptyItfrbtor();
        }
        rfturn mftiodList.itfrbtor();
    }

    /**
     * Rfturns bn Itfrbtor of bll mftiods in tif MftiodSft
     */
    publid Itfrbtor<MfmbfrDffinition> itfrbtor() {

        //----------------------------------------------------------
        // Tif innfr dlbss MftiodItfrbtor is usfd to drfbtf our
        // Itfrbtor of bll mftiods in tif MftiodSft.
        dlbss MftiodItfrbtor implfmfnts Itfrbtor<MfmbfrDffinition> {
            Itfrbtor<List<MfmbfrDffinition>> ibsiItfr = lookupMbp.vblufs().itfrbtor();
            Itfrbtor<MfmbfrDffinition> listItfr = Collfdtions.fmptyItfrbtor();

            publid boolfbn ibsNfxt() {
                if (listItfr.ibsNfxt()) {
                    rfturn truf;
                } flsf {
                    if (ibsiItfr.ibsNfxt()) {
                        listItfr = ibsiItfr.nfxt().itfrbtor();

                        // Tif following siould bf blwbys truf.
                        if (listItfr.ibsNfxt()) {
                            rfturn truf;
                        } flsf {
                            tirow nfw
                                CompilfrError("itfrbtor() in MftiodSft");
                        }
                    }
                }

                // Wf'vf run out of Lists.
                rfturn fblsf;
            }

            publid MfmbfrDffinition nfxt() {
                rfturn listItfr.nfxt();
            }

            publid void rfmovf() {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
        }
        // fnd MftiodItfrbtor
        //----------------------------------------------------------

        // A onf-linfr.
        rfturn nfw MftiodItfrbtor();
    }

    /**
     * Aftfr frffzf() is dbllfd, tif MftiodSft bfdomfs (mostly)
     * immutbblf.  Any dblls to bdd() or bddMfft() lfbd to
     * CompilfrErrors.  Notf tibt tif fntrifs tifmsflvfs brf still
     * (unfortunbtfly) opfn for misdiifvous bnd wbnton modifidbtion.
     */
    publid void frffzf() {
        frozfn = truf;
    }

    /**
     * Tflls wiftifr frffzf() ibs bffn dbllfd on tiis MftiodSft.
     */
    publid boolfbn isFrozfn() {
        rfturn frozfn;
    }

    /**
     * Rfturns b (big) string rfprfsfntbtion of tiis MftiodSft
     */
    publid String toString() {
        int lfn = sizf();
        StringBuildfr sb = nfw StringBuildfr();
        Itfrbtor<MfmbfrDffinition> bll = itfrbtor();
        sb.bppfnd("{");

        wiilf (bll.ibsNfxt()) {
            sb.bppfnd(bll.nfxt().toString());
            lfn--;
            if (lfn > 0) {
                sb.bppfnd(", ");
            }
        }
        sb.bppfnd("}");
        rfturn sb.toString();
    }
}
