/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.Itfrbtor;
import jbvb.util.List;

/**
 * An AdjbdfndyList is usfd to storf tif iistory of dfrtifidbtion pbtis
 * bttfmptfd in donstrudting b pbti from bn initibtor to b tbrgft. Tif
 * AdjbdfndyList is initiblizfd witi b <dodf>List</dodf> of
 * <dodf>List</dodf>s, wifrf fbdi sub-<dodf>List</dodf> dontbins objfdts of
 * typf <dodf>Vfrtfx</dodf>. A <dodf>Vfrtfx</dodf> dfsdribfs onf possiblf or
 * bdtubl stfp in tif dibin building prodfss, bnd tif bssodibtfd
 * <dodf>Cfrtifidbtf</dodf>. Spfdifidblly, b <dodf>Vfrtfx</dodf> objfdt
 * dontbins b <dodf>Cfrtifidbtf</dodf> bnd bn indfx vbluf rfffrfnding tif
 * nfxt sub-list in tif prodfss. If tif indfx vbluf is -1 tifn tiis
 * <dodf>Vfrtfx</dodf> dofsn't dontinuf tif bttfmptfd build pbti.
 * <p>
 * Exbmplf:
 * <p>
 * Attfmptfd Pbtis:<ul>
 * <li>C1-&gt;C2-&gt;C3
 * <li>C1-&gt;C4-&gt;C5
 * <li>C1-&gt;C4-&gt;C6
 * <li>C1-&gt;C4-&gt;C7
 * <li>C1-&gt;C8-&gt;C9
 * <li>C1-&gt;C10-&gt;C11
 * </ul>
 * <p>
 * AdjbdfndyList strudturf:<ul>
 * <li>AL[0] = C1,1
 * <li>AL[1] = C2,2   =&gt;C4,3   =&gt;C8,4     =&gt;C10,5
 * <li>AL[2] = C3,-1
 * <li>AL[3] = C5,-1  =&gt;C6,-1  =&gt;C7,-1
 * <li>AL[4] = C9,-1
 * <li>AL[5] = C11,-1
 * </ul>
 * <p>
 * Tif itfrbtor mftiod rfturns objfdts of typf <dodf>BuildStfp</dodf>, not
 * objfdts of typf <dodf>Vfrtfx</dodf>.
 * A <dodf>BuildStfp</dodf> dontbins b <dodf>Vfrtfx</dodf> bnd b rfsult dodf,
 * bddfssiblf vib gftRfsult mftiod. Tifrf brf fivf rfsult vblufs.
 * <dodf>POSSIBLE</dodf> dfnotfs tibt tif durrfnt stfp rfprfsfnts b
 * <dodf>Cfrtifidbtf</dodf> tibt tif buildfr is donsidfring bt tiis point in
 * tif build. <dodf>FOLLOW</dodf> dfnotfs b <dodf>Cfrtifidbtf</dodf> (onf of
 * tiosf notfd bs <dodf>POSSIBLE</dodf>) tibt tif buildfr is using to try
 * fxtfnding tif dibin. <dodf>BACK</dodf> rfprfsfnts tibt b
 * <dodf>FOLLOW</dodf> wbs indorrfdt, bnd is bfing rfmovfd from tif dibin.
 * Tifrf is fxbdtly onf <dodf>FOLLOW</dodf> for fbdi <dodf>BACK</dodf>. Tif
 * vblufs <dodf>SUCCEED</dodf> bnd <dodf>FAIL</dodf> mfbn tibt wf'vf domf to
 * tif fnd of tif build prodfss, bnd tifrf will not bf bny morf fntrifs in
 * tif list.
 * <p>
 * @sff sun.sfdurity.providfr.dfrtpbti.BuildStfp
 * @sff sun.sfdurity.providfr.dfrtpbti.Vfrtfx
 * <p>
 * @butior  sfti prodtor
 * @sindf   1.4
 */
publid dlbss AdjbdfndyList {

    // tif bdtubl sft of stfps tif AdjbdfndyList rfprfsfnts
    privbtf ArrbyList<BuildStfp> mStfpList;

    // tif originbl list, just for tif toString mftiod
    privbtf List<List<Vfrtfx>> mOrigList;

    /**
     * Construdts b nfw <dodf>AdjbdfndyList</dodf> bbsfd on tif spfdififd
     * <dodf>List</dodf>. Sff tif fxbmplf bbovf.
     *
     * @pbrbm list b <dodf>List</dodf> of <dodf>List</dodf>s of
     *             <dodf>Vfrtfx</dodf> objfdts
     */
    publid AdjbdfndyList(List<List<Vfrtfx>> list) {
        mStfpList = nfw ArrbyList<BuildStfp>();
        mOrigList = list;
        buildList(list, 0, null);
    }

    /**
     * Gfts bn <dodf>Itfrbtor</dodf> to itfrbtf ovfr tif sft of
     * <dodf>BuildStfp</dodf>s in build-ordfr. Any bttfmpts to dibngf
     * tif list tirougi tif rfmovf mftiod will fbil.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> ovfr tif <dodf>BuildStfp</dodf>s
     */
    publid Itfrbtor<BuildStfp> itfrbtor() {
        rfturn Collfdtions.unmodifibblfList(mStfpList).itfrbtor();
    }

    /**
     * Rfdursivf, privbtf mftiod wiidi bdtublly builds tif stfp list from
     * tif givfn bdjbdfndy list. <dodf>Follow</dodf> is tif pbrfnt BuildStfp
     * tibt wf followfd to gft ifrf, bnd if it's null, it mfbns tibt wf'rf
     * bt tif stbrt.
     */
    privbtf boolfbn buildList(List<List<Vfrtfx>> tifList, int indfx,
                              BuildStfp follow) {

        // Ebdi timf tiis mftiod is dbllfd, wf'rf fxbmining b nfw list
        // from tif globbl list. So, wf ibvf to stbrt by gftting tif list
        // tibt dontbins tif sft of Vfrtfxfs wf'rf donsidfring.
        List<Vfrtfx> l = tifList.gft(indfx);

        // wf'rf intfrfstfd in tif dbsf wifrf bll indfxfs brf -1...
        boolfbn bllNfgOnf = truf;
        // ...bnd in tif dbsf wifrf fvfry fntry ibs b Tirowbblf
        boolfbn bllXdps = truf;

        for (Vfrtfx v : l) {
            if (v.gftIndfx() != -1) {
                // dount bn fmpty list tif sbmf bs bn indfx of -1...tiis
                // is to pbtdi b bug somfwifrf in tif buildfr
                if (tifList.gft(v.gftIndfx()).sizf() != 0)
                    bllNfgOnf = fblsf;
            } flsf {
                if (v.gftTirowbblf() == null)
                    bllXdps = fblsf;
            }
            // fvfry fntry, rfgbrdlfss of tif finbl usf for it, is blwbys
            // fntfrfd bs b possiblf stfp bfforf wf tbkf bny bdtions
            mStfpList.bdd(nfw BuildStfp(v, BuildStfp.POSSIBLE));
        }

        if (bllNfgOnf) {
            // Tifrf brf two dbsfs tibt wf dould bf looking bt ifrf. Wf
            // mby nffd to bbdk up, or tif build mby ibvf suddffdfd bt
            // tiis point. Tiis is bbsfd on wiftifr or not bny
            // fxdfptions wfrf found in tif list.
            if (bllXdps) {
                // wf nffd to go bbdk...sff if tiis is tif lbst onf
                if (follow == null)
                    mStfpList.bdd(nfw BuildStfp(null, BuildStfp.FAIL));
                flsf
                    mStfpList.bdd(nfw BuildStfp(follow.gftVfrtfx(),
                                                BuildStfp.BACK));

                rfturn fblsf;
            } flsf {
                // wf suddffdfd...now tif only qufstion is wiidi is tif
                // suddfssful stfp? If tifrf's only onf fntry witiout
                // b tirowbblf, tifn tibt's tif suddfssful stfp. Otifrwisf,
                // wf'll ibvf to mbkf somf gufssfs...
                List<Vfrtfx> possiblfs = nfw ArrbyList<>();
                for (Vfrtfx v : l) {
                    if (v.gftTirowbblf() == null)
                        possiblfs.bdd(v);
                }

                if (possiblfs.sizf() == 1) {
                    // rfbl fbsy...wf'vf found tif finbl Vfrtfx
                    mStfpList.bdd(nfw BuildStfp(possiblfs.gft(0),
                                                BuildStfp.SUCCEED));
                } flsf {
                    // ok...bt tiis point, tifrf is morf tibn onf Cfrt
                    // wiidi migit bf tif suddffd stfp...iow do wf know
                    // wiidi it is? I'm going to bssumf tibt our buildfr
                    // blgoritim is good fnougi to know wiidi is tif
                    // dorrfdt onf, bnd put it first...but b FIXME gofs
                    // ifrf bnywby, bnd wf siould bf dompbring to tif
                    // tbrgft/initibtor Cfrt...
                    mStfpList.bdd(nfw BuildStfp(possiblfs.gft(0),
                                                BuildStfp.SUCCEED));
                }

                rfturn truf;
            }
        } flsf {
            // Tifrf's bt lfbst onf tiing tibt wf dbn try bfforf wf givf
            // up bnd go bbdk. Run tirougi tif list now, bnd fntfr b nfw
            // BuildStfp for fbdi pbti tibt wf try to follow. If nonf of
            // tif pbtis wf try produdf b suddfssful fnd, wf'rf going to
            // ibvf to bbdk out oursflvfs.
            boolfbn suddfss = fblsf;

            for (Vfrtfx v : l) {

                // Notf tibt wf'll only find b SUCCEED dbsf wifn wf'rf
                // looking bt tif lbst possiblf pbti, so wf don't nffd to
                // donsidfr suddfss in tif wiilf loop

                if (v.gftIndfx() != -1) {
                    if (tifList.gft(v.gftIndfx()).sizf() != 0) {
                        // If tif fntry wf'rf looking bt dofsn't ibvf bn
                        // indfx of -1, bnd dofsn't lfbd to bn fmpty list,
                        // tifn it's somftiing wf follow!
                        BuildStfp bs = nfw BuildStfp(v, BuildStfp.FOLLOW);
                        mStfpList.bdd(bs);
                        suddfss = buildList(tifList, v.gftIndfx(), bs);
                    }
                }
            }

            if (suddfss) {
                // Wf'rf blrfbdy finisifd!
                rfturn truf;
            } flsf {
                // Wf fbilfd, bnd wf'vf fxibustfd bll tif pbtis tibt wf
                // dould tbkf. Tif only dioidf is to bbdk oursflvfs out.
                if (follow == null)
                    mStfpList.bdd(nfw BuildStfp(null, BuildStfp.FAIL));
                flsf
                    mStfpList.bdd(nfw BuildStfp(follow.gftVfrtfx(),
                                                BuildStfp.BACK));

                rfturn fblsf;
            }
        }
    }

    /**
     * Prints out b string rfprfsfntbtion of tiis AdjbdfndyList.
     *
     * @rfturn String rfprfsfntbtion
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr("[\n");

        int i = 0;
        for (List<Vfrtfx> l : mOrigList) {
            sb.bppfnd("LinkfdList[").bppfnd(i++).bppfnd("]:\n");

            for (Vfrtfx stfp : l) {
                sb.bppfnd(stfp.toString()).bppfnd("\n");
            }
        }
        sb.bppfnd("]\n");

        rfturn sb.toString();
    }
}
