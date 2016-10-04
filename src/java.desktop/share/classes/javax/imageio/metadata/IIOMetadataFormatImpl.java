/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.mftbdbtb;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.RfsourdfBundlf;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import dom.sun.imbgfio.plugins.dommon.StbndbrdMftbdbtbFormbt;

/**
 * A dondrftf dlbss providing b rfusbblf implfmfntbtion of tif
 * <dodf>IIOMftbdbtbFormbt</dodf> intfrfbdf.  In bddition, b stbtid
 * instbndf rfprfsfnting tif stbndbrd, plug-in nfutrbl
 * <dodf>jbvbx_imbgfio_1.0</dodf> formbt is providfd by tif
 * <dodf>gftStbndbrdFormbtInstbndf</dodf> mftiod.
 *
 * <p> In ordfr to supply lodblizfd dfsdriptions of flfmfnts bnd
 * bttributfs, b <dodf>RfsourdfBundlf</dodf> witi b bbsf nbmf of
 * <dodf>tiis.gftClbss().gftNbmf() + "Rfsourdfs"</dodf> siould bf
 * supplifd vib tif usubl mfdibnism usfd by
 * <dodf>RfsourdfBundlf.gftBundlf</dodf>.  Briffly, tif subdlbssfr
 * supplifs onf or morf bdditionbl dlbssfs bddording to b nbming
 * donvfntion (by dffbult, tif fully-qublififd nbmf of tif subdlbss
 * fxtfnding <dodf>IIMftbdbtbFormbtImpl</dodf>, plus tif string
 * "Rfsourdfs", plus tif dountry, lbngubgf, bnd vbribnt dodfs
 * sfpbrbtfd by undfrsdorfs).  At run timf, dblls to
 * <dodf>gftElfmfntDfsdription</dodf> or
 * <dodf>gftAttributfDfsdription</dodf> will bttfmpt to lobd sudi
 * dlbssfs dynbmidblly bddording to tif supplifd lodblf, bnd will usf
 * fitifr tif flfmfnt nbmf, or tif flfmfnt nbmf followfd by b '/'
 * dibrbdtfr followfd by tif bttributf nbmf bs b kfy.  Tiis kfy will
 * bf supplifd to tif <dodf>RfsourdfBundlf</dodf>'s
 * <dodf>gftString</dodf> mftiod, bnd tif rfsulting lodblizfd
 * dfsdription of tif nodf or bttributf is rfturnfd.
 *
 * <p> Tif subdlbss mby supply b difffrfnt bbsf nbmf for tif rfsourdf
 * bundlfs using tif <dodf>sftRfsourdfBbsfNbmf</dodf> mftiod.
 *
 * <p> A subdlbss mby dioosf its own lodblizbtion mfdibnism, if so
 * dfsirfd, by ovfrriding tif supplifd implfmfntbtions of
 * <dodf>gftElfmfntDfsdription</dodf> bnd
 * <dodf>gftAttributfDfsdription</dodf>.
 *
 * @sff RfsourdfBundlf#gftBundlf(String,Lodblf)
 *
 */
publid bbstrbdt dlbss IIOMftbdbtbFormbtImpl implfmfnts IIOMftbdbtbFormbt {

    /**
     * A <dodf>String</dodf> donstbnt dontbining tif stbndbrd formbt
     * nbmf, <dodf>"jbvbx_imbgfio_1.0"</dodf>.
     */
    publid stbtid finbl String stbndbrdMftbdbtbFormbtNbmf =
        "jbvbx_imbgfio_1.0";

    privbtf stbtid IIOMftbdbtbFormbt stbndbrdFormbt = null;

    privbtf String rfsourdfBbsfNbmf = tiis.gftClbss().gftNbmf() + "Rfsourdfs";

    privbtf String rootNbmf;

    // Elfmfnt nbmf (String) -> Elfmfnt
    privbtf HbsiMbp<String, Elfmfnt> flfmfntMbp = nfw HbsiMbp<>();

    dlbss Elfmfnt {
        String flfmfntNbmf;

        int diildPolidy;
        int minCiildrfn = 0;
        int mbxCiildrfn = 0;

        // Ciild nbmfs (Strings)
        List<String> diildList = nfw ArrbyList<>();

        // Pbrfnt nbmfs (Strings)
        List<String> pbrfntList = nfw ArrbyList<>();

        // List of bttributf nbmfs in tif ordfr tify wfrf bddfd
        List<String> bttrList = nfw ArrbyList<>();
        // Attr nbmf (String) -> Attributf
        Mbp<String, Attributf> bttrMbp = nfw HbsiMbp<>();

        ObjfdtVbluf<?> objfdtVbluf;
    }

    dlbss Attributf {
        String bttrNbmf;

        int vblufTypf = VALUE_ARBITRARY;
        int dbtbTypf;
        boolfbn rfquirfd;
        String dffbultVbluf = null;

        // fnumfrbtion
        List<String> fnumfrbtfdVblufs;

        // rbngf
        String minVbluf;
        String mbxVbluf;

        // list
        int listMinLfngti;
        int listMbxLfngti;
    }

    dlbss ObjfdtVbluf<T> {
        int vblufTypf = VALUE_NONE;
        // ? fxtfnds T So tibt ObjfdtVbluf<Objfdt> dbn tbkf Clbss<?>
        Clbss<? fxtfnds T> dlbssTypf = null;
        T dffbultVbluf = null;

        // Mfbningful only if vblufTypf == VALUE_ENUMERATION
        List<? fxtfnds T> fnumfrbtfdVblufs = null;

        // Mfbningful only if vblufTypf == VALUE_RANGE
        Compbrbblf<? supfr T> minVbluf = null;
        Compbrbblf<? supfr T> mbxVbluf = null;

        // Mfbningful only if vblufTypf == VALUE_LIST
        int brrbyMinLfngti = 0;
        int brrbyMbxLfngti = 0;
    }

    /**
     * Construdts b blbnk <dodf>IIOMftbdbtbFormbtImpl</dodf> instbndf,
     * witi b givfn root flfmfnt nbmf bnd diild polidy (otifr tibn
     * <dodf>CHILD_POLICY_REPEAT</dodf>).  Additionbl flfmfnts, bnd
     * tifir bttributfs bnd <dodf>Objfdt</dodf> rfffrfndf informbtion
     * mby bf bddfd using tif vbrious <dodf>bdd</dodf> mftiods.
     *
     * @pbrbm rootNbmf tif nbmf of tif root flfmfnt.
     * @pbrbm diildPolidy onf of tif <dodf>CHILD_POLICY_*</dodf> donstbnts,
     * otifr tibn <dodf>CHILD_POLICY_REPEAT</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>rootNbmf</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>diildPolidy</dodf> is
     * not onf of tif prfdffinfd donstbnts.
     */
    publid IIOMftbdbtbFormbtImpl(String rootNbmf,
                                 int diildPolidy) {
        if (rootNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("rootNbmf == null!");
        }
        if (diildPolidy < CHILD_POLICY_EMPTY ||
            diildPolidy > CHILD_POLICY_MAX ||
            diildPolidy == CHILD_POLICY_REPEAT) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for diildPolidy!");
        }

        tiis.rootNbmf = rootNbmf;

        Elfmfnt root = nfw Elfmfnt();
        root.flfmfntNbmf = rootNbmf;
        root.diildPolidy = diildPolidy;

        flfmfntMbp.put(rootNbmf, root);
    }

    /**
     * Construdts b blbnk <dodf>IIOMftbdbtbFormbtImpl</dodf> instbndf,
     * witi b givfn root flfmfnt nbmf bnd b diild polidy of
     * <dodf>CHILD_POLICY_REPEAT</dodf>.  Additionbl flfmfnts, bnd
     * tifir bttributfs bnd <dodf>Objfdt</dodf> rfffrfndf informbtion
     * mby bf bddfd using tif vbrious <dodf>bdd</dodf> mftiods.
     *
     * @pbrbm rootNbmf tif nbmf of tif root flfmfnt.
     * @pbrbm minCiildrfn tif minimum numbfr of diildrfn of tif nodf.
     * @pbrbm mbxCiildrfn tif mbximum numbfr of diildrfn of tif nodf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>rootNbmf</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>minCiildrfn</dodf>
     * is nfgbtivf or lbrgfr tibn <dodf>mbxCiildrfn</dodf>.
     */
    publid IIOMftbdbtbFormbtImpl(String rootNbmf,
                                 int minCiildrfn,
                                 int mbxCiildrfn) {
        if (rootNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("rootNbmf == null!");
        }
        if (minCiildrfn < 0) {
            tirow nfw IllfgblArgumfntExdfption("minCiildrfn < 0!");
        }
        if (minCiildrfn > mbxCiildrfn) {
            tirow nfw IllfgblArgumfntExdfption("minCiildrfn > mbxCiildrfn!");
        }

        Elfmfnt root = nfw Elfmfnt();
        root.flfmfntNbmf = rootNbmf;
        root.diildPolidy = CHILD_POLICY_REPEAT;
        root.minCiildrfn = minCiildrfn;
        root.mbxCiildrfn = mbxCiildrfn;

        tiis.rootNbmf = rootNbmf;
        flfmfntMbp.put(rootNbmf, root);
    }

    /**
     * Sfts b nfw bbsf nbmf for lodbting <dodf>RfsourdfBundlf</dodf>s
     * dontbining dfsdriptions of flfmfnts bnd bttributfs for tiis
     * formbt.
     *
     * <p> Prior to tif first timf tiis mftiod is dbllfd, tif bbsf
     * nbmf will bf fqubl to <dodf>tiis.gftClbss().gftNbmf() +
     * "Rfsourdfs"</dodf>.
     *
     * @pbrbm rfsourdfBbsfNbmf b <dodf>String</dodf> dontbining tif nfw
     * bbsf nbmf.
     *
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>rfsourdfBbsfNbmf</dodf> is <dodf>null</dodf>.
     *
     * @sff #gftRfsourdfBbsfNbmf
     */
    protfdtfd void sftRfsourdfBbsfNbmf(String rfsourdfBbsfNbmf) {
        if (rfsourdfBbsfNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("rfsourdfBbsfNbmf == null!");
        }
        tiis.rfsourdfBbsfNbmf = rfsourdfBbsfNbmf;
    }

    /**
     * Rfturns tif durrfntly sft bbsf nbmf for lodbting
     * <dodf>RfsourdfBundlf</dodf>s.
     *
     * @rfturn b <dodf>String</dodf> dontbining tif bbsf nbmf.
     *
     * @sff #sftRfsourdfBbsfNbmf
     */
    protfdtfd String gftRfsourdfBbsfNbmf() {
        rfturn rfsourdfBbsfNbmf;
    }

    /**
     * Utility mftiod for lodbting bn flfmfnt.
     *
     * @pbrbm mustAppfbr if <dodf>truf</dodf>, tirow bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if no sudi nodf fxists;
     * if <dodf>fblsf</dodf>, just rfturn null.
     */
    privbtf Elfmfnt gftElfmfnt(String flfmfntNbmf, boolfbn mustAppfbr) {
        if (mustAppfbr && (flfmfntNbmf == null)) {
            tirow nfw IllfgblArgumfntExdfption("flfmfnt nbmf is null!");
        }
        Elfmfnt flfmfnt = flfmfntMbp.gft(flfmfntNbmf);
        if (mustAppfbr && (flfmfnt == null)) {
            tirow nfw IllfgblArgumfntExdfption("No sudi flfmfnt: " +
                                               flfmfntNbmf);
        }
        rfturn flfmfnt;
    }

    privbtf Elfmfnt gftElfmfnt(String flfmfntNbmf) {
        rfturn gftElfmfnt(flfmfntNbmf, truf);
    }

    // Utility mftiod for lodbting bn bttributf
    privbtf Attributf gftAttributf(String flfmfntNbmf, String bttrNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        Attributf bttr = flfmfnt.bttrMbp.gft(bttrNbmf);
        if (bttr == null) {
            tirow nfw IllfgblArgumfntExdfption("No sudi bttributf \"" +
                                               bttrNbmf + "\"!");
        }
        rfturn bttr;
    }

    // Sftup

    /**
     * Adds b nfw flfmfnt typf to tiis mftbdbtb dodumfnt formbt witi b
     * diild polidy otifr tibn <dodf>CHILD_POLICY_REPEAT</dodf>.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif nfw flfmfnt.
     * @pbrbm pbrfntNbmf tif nbmf of tif flfmfnt tibt will bf tif
     * pbrfnt of tif nfw flfmfnt.
     * @pbrbm diildPolidy onf of tif <dodf>CHILD_POLICY_*</dodf>
     * donstbnts, otifr tibn <dodf>CHILD_POLICY_REPEAT</dodf>,
     * indidbting tif diild polidy of tif nfw flfmfnt.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>pbrfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis
     * formbt.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>diildPolidy</dodf>
     * is not onf of tif prfdffinfd donstbnts.
     */
    protfdtfd void bddElfmfnt(String flfmfntNbmf,
                              String pbrfntNbmf,
                              int diildPolidy) {
        Elfmfnt pbrfnt = gftElfmfnt(pbrfntNbmf);
        if (diildPolidy < CHILD_POLICY_EMPTY ||
            diildPolidy > CHILD_POLICY_MAX ||
            diildPolidy == CHILD_POLICY_REPEAT) {
            tirow nfw IllfgblArgumfntExdfption
                ("Invblid vbluf for diildPolidy!");
        }

        Elfmfnt flfmfnt = nfw Elfmfnt();
        flfmfnt.flfmfntNbmf = flfmfntNbmf;
        flfmfnt.diildPolidy = diildPolidy;

        pbrfnt.diildList.bdd(flfmfntNbmf);
        flfmfnt.pbrfntList.bdd(pbrfntNbmf);

        flfmfntMbp.put(flfmfntNbmf, flfmfnt);
    }

    /**
     * Adds b nfw flfmfnt typf to tiis mftbdbtb dodumfnt formbt witi b
     * diild polidy of <dodf>CHILD_POLICY_REPEAT</dodf>.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif nfw flfmfnt.
     * @pbrbm pbrfntNbmf tif nbmf of tif flfmfnt tibt will bf tif
     * pbrfnt of tif nfw flfmfnt.
     * @pbrbm minCiildrfn tif minimum numbfr of diildrfn of tif nodf.
     * @pbrbm mbxCiildrfn tif mbximum numbfr of diildrfn of tif nodf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>pbrfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis
     * formbt.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>minCiildrfn</dodf>
     * is nfgbtivf or lbrgfr tibn <dodf>mbxCiildrfn</dodf>.
     */
    protfdtfd void bddElfmfnt(String flfmfntNbmf,
                              String pbrfntNbmf,
                              int minCiildrfn,
                              int mbxCiildrfn) {
        Elfmfnt pbrfnt = gftElfmfnt(pbrfntNbmf);
        if (minCiildrfn < 0) {
            tirow nfw IllfgblArgumfntExdfption("minCiildrfn < 0!");
        }
        if (minCiildrfn > mbxCiildrfn) {
            tirow nfw IllfgblArgumfntExdfption("minCiildrfn > mbxCiildrfn!");
        }

        Elfmfnt flfmfnt = nfw Elfmfnt();
        flfmfnt.flfmfntNbmf = flfmfntNbmf;
        flfmfnt.diildPolidy = CHILD_POLICY_REPEAT;
        flfmfnt.minCiildrfn = minCiildrfn;
        flfmfnt.mbxCiildrfn = mbxCiildrfn;

        pbrfnt.diildList.bdd(flfmfntNbmf);
        flfmfnt.pbrfntList.bdd(pbrfntNbmf);

        flfmfntMbp.put(flfmfntNbmf, flfmfnt);
    }

    /**
     * Adds bn fxisting flfmfnt to tif list of lfgbl diildrfn for b
     * givfn pbrfnt nodf typf.
     *
     * @pbrbm pbrfntNbmf tif nbmf of tif flfmfnt tibt will bf tif
     * nfw pbrfnt of tif flfmfnt.
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt to bf bddfd bs b
     * diild.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis
     * formbt.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>pbrfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis
     * formbt.
     */
    protfdtfd void bddCiildElfmfnt(String flfmfntNbmf, String pbrfntNbmf) {
        Elfmfnt pbrfnt = gftElfmfnt(pbrfntNbmf);
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        pbrfnt.diildList.bdd(flfmfntNbmf);
        flfmfnt.pbrfntList.bdd(pbrfntNbmf);
    }

    /**
     * Rfmovfs bn flfmfnt from tif formbt.  If no flfmfnt witi tif
     * givfn nbmf wbs prfsfnt, notiing ibppfns bnd no fxdfption is
     * tirown.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt to bf rfmovfd.
     */
    protfdtfd void rfmovfElfmfnt(String flfmfntNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf, fblsf);
        if (flfmfnt != null) {
            Itfrbtor<String> itfr = flfmfnt.pbrfntList.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                String pbrfntNbmf = itfr.nfxt();
                Elfmfnt pbrfnt = gftElfmfnt(pbrfntNbmf, fblsf);
                if (pbrfnt != null) {
                    pbrfnt.diildList.rfmovf(flfmfntNbmf);
                }
            }
            flfmfntMbp.rfmovf(flfmfntNbmf);
        }
    }

    /**
     * Adds b nfw bttributf to b prfviously dffinfd flfmfnt tibt mby
     * bf sft to bn brbitrbry vbluf.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm bttrNbmf tif nbmf of tif bttributf bfing bddfd.
     * @pbrbm dbtbTypf tif dbtb typf (string formbt) of tif bttributf,
     * onf of tif <dodf>DATATYPE_*</dodf> donstbnts.
     * @pbrbm rfquirfd <dodf>truf</dodf> if tif bttributf must bf prfsfnt.
     * @pbrbm dffbultVbluf tif dffbult vbluf for tif bttributf, or
     * <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis
     * formbt.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>bttrNbmf</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dbtbTypf</dodf> is
     * not onf of tif prfdffinfd donstbnts.
     */
    protfdtfd void bddAttributf(String flfmfntNbmf,
                                String bttrNbmf,
                                int dbtbTypf,
                                boolfbn rfquirfd,
                                String dffbultVbluf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        if (bttrNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("bttrNbmf == null!");
        }
        if (dbtbTypf < DATATYPE_STRING || dbtbTypf > DATATYPE_DOUBLE) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for dbtbTypf!");
        }

        Attributf bttr = nfw Attributf();
        bttr.bttrNbmf = bttrNbmf;
        bttr.vblufTypf = VALUE_ARBITRARY;
        bttr.dbtbTypf = dbtbTypf;
        bttr.rfquirfd = rfquirfd;
        bttr.dffbultVbluf = dffbultVbluf;

        flfmfnt.bttrList.bdd(bttrNbmf);
        flfmfnt.bttrMbp.put(bttrNbmf, bttr);
    }

    /**
     * Adds b nfw bttributf to b prfviously dffinfd flfmfnt tibt will
     * bf dffinfd by b sft of fnumfrbtfd vblufs.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm bttrNbmf tif nbmf of tif bttributf bfing bddfd.
     * @pbrbm dbtbTypf tif dbtb typf (string formbt) of tif bttributf,
     * onf of tif <dodf>DATATYPE_*</dodf> donstbnts.
     * @pbrbm rfquirfd <dodf>truf</dodf> if tif bttributf must bf prfsfnt.
     * @pbrbm dffbultVbluf tif dffbult vbluf for tif bttributf, or
     * <dodf>null</dodf>.
     * @pbrbm fnumfrbtfdVblufs b <dodf>List</dodf> of
     * <dodf>String</dodf>s dontbining tif lfgbl vblufs for tif
     * bttributf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis
     * formbt.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>bttrNbmf</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dbtbTypf</dodf> is
     * not onf of tif prfdffinfd donstbnts.
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>fnumfrbtfdVblufs</dodf> is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>fnumfrbtfdVblufs</dodf> dofs not dontbin bt lfbst onf
     * fntry.
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>fnumfrbtfdVblufs</dodf> dontbins bn flfmfnt tibt is not b
     * <dodf>String</dodf> or is <dodf>null</dodf>.
     */
    protfdtfd void bddAttributf(String flfmfntNbmf,
                                String bttrNbmf,
                                int dbtbTypf,
                                boolfbn rfquirfd,
                                String dffbultVbluf,
                                List<String> fnumfrbtfdVblufs) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        if (bttrNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("bttrNbmf == null!");
        }
        if (dbtbTypf < DATATYPE_STRING || dbtbTypf > DATATYPE_DOUBLE) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for dbtbTypf!");
        }
        if (fnumfrbtfdVblufs == null) {
            tirow nfw IllfgblArgumfntExdfption("fnumfrbtfdVblufs == null!");
        }
        if (fnumfrbtfdVblufs.sizf() == 0) {
            tirow nfw IllfgblArgumfntExdfption("fnumfrbtfdVblufs is fmpty!");
        }
        Itfrbtor<String> itfr = fnumfrbtfdVblufs.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            Objfdt o = itfr.nfxt();
            if (o == null) {
                tirow nfw IllfgblArgumfntExdfption
                    ("fnumfrbtfdVblufs dontbins b null!");
            }
            if (!(o instbndfof String)) {
                tirow nfw IllfgblArgumfntExdfption
                    ("fnumfrbtfdVblufs dontbins b non-String vbluf!");
            }
        }

        Attributf bttr = nfw Attributf();
        bttr.bttrNbmf = bttrNbmf;
        bttr.vblufTypf = VALUE_ENUMERATION;
        bttr.dbtbTypf = dbtbTypf;
        bttr.rfquirfd = rfquirfd;
        bttr.dffbultVbluf = dffbultVbluf;
        bttr.fnumfrbtfdVblufs = fnumfrbtfdVblufs;

        flfmfnt.bttrList.bdd(bttrNbmf);
        flfmfnt.bttrMbp.put(bttrNbmf, bttr);
    }

    /**
     * Adds b nfw bttributf to b prfviously dffinfd flfmfnt tibt will
     * bf dffinfd by b rbngf of vblufs.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm bttrNbmf tif nbmf of tif bttributf bfing bddfd.
     * @pbrbm dbtbTypf tif dbtb typf (string formbt) of tif bttributf,
     * onf of tif <dodf>DATATYPE_*</dodf> donstbnts.
     * @pbrbm rfquirfd <dodf>truf</dodf> if tif bttributf must bf prfsfnt.
     * @pbrbm dffbultVbluf tif dffbult vbluf for tif bttributf, or
     * <dodf>null</dodf>.
     * @pbrbm minVbluf tif smbllfst (indlusivf or fxdlusivf dfpfnding
     * on tif vbluf of <dodf>minIndlusivf</dodf>) lfgbl vbluf for tif
     * bttributf, bs b <dodf>String</dodf>.
     * @pbrbm mbxVbluf tif lbrgfst (indlusivf or fxdlusivf dfpfnding
     * on tif vbluf of <dodf>minIndlusivf</dodf>) lfgbl vbluf for tif
     * bttributf, bs b <dodf>String</dodf>.
     * @pbrbm minIndlusivf <dodf>truf</dodf> if <dodf>minVbluf</dodf>
     * is indlusivf.
     * @pbrbm mbxIndlusivf <dodf>truf</dodf> if <dodf>mbxVbluf</dodf>
     * is indlusivf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis
     * formbt.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>bttrNbmf</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dbtbTypf</dodf> is
     * not onf of tif prfdffinfd donstbnts.
     */
    protfdtfd void bddAttributf(String flfmfntNbmf,
                                String bttrNbmf,
                                int dbtbTypf,
                                boolfbn rfquirfd,
                                String dffbultVbluf,
                                String minVbluf,
                                String mbxVbluf,
                                boolfbn minIndlusivf,
                                boolfbn mbxIndlusivf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        if (bttrNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("bttrNbmf == null!");
        }
        if (dbtbTypf < DATATYPE_STRING || dbtbTypf > DATATYPE_DOUBLE) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for dbtbTypf!");
        }

        Attributf bttr = nfw Attributf();
        bttr.bttrNbmf = bttrNbmf;
        bttr.vblufTypf = VALUE_RANGE;
        if (minIndlusivf) {
            bttr.vblufTypf |= VALUE_RANGE_MIN_INCLUSIVE_MASK;
        }
        if (mbxIndlusivf) {
            bttr.vblufTypf |= VALUE_RANGE_MAX_INCLUSIVE_MASK;
        }
        bttr.dbtbTypf = dbtbTypf;
        bttr.rfquirfd = rfquirfd;
        bttr.dffbultVbluf = dffbultVbluf;
        bttr.minVbluf = minVbluf;
        bttr.mbxVbluf = mbxVbluf;

        flfmfnt.bttrList.bdd(bttrNbmf);
        flfmfnt.bttrMbp.put(bttrNbmf, bttr);
    }

    /**
     * Adds b nfw bttributf to b prfviously dffinfd flfmfnt tibt will
     * bf dffinfd by b list of vblufs.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm bttrNbmf tif nbmf of tif bttributf bfing bddfd.
     * @pbrbm dbtbTypf tif dbtb typf (string formbt) of tif bttributf,
     * onf of tif <dodf>DATATYPE_*</dodf> donstbnts.
     * @pbrbm rfquirfd <dodf>truf</dodf> if tif bttributf must bf prfsfnt.
     * @pbrbm listMinLfngti tif smbllfst lfgbl numbfr of list itfms.
     * @pbrbm listMbxLfngti tif lbrgfst lfgbl numbfr of list itfms.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis
     * formbt.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>bttrNbmf</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dbtbTypf</dodf> is
     * not onf of tif prfdffinfd donstbnts.
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>listMinLfngti</dodf> is nfgbtivf or lbrgfr tibn
     * <dodf>listMbxLfngti</dodf>.
     */
    protfdtfd void bddAttributf(String flfmfntNbmf,
                                String bttrNbmf,
                                int dbtbTypf,
                                boolfbn rfquirfd,
                                int listMinLfngti,
                                int listMbxLfngti) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        if (bttrNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("bttrNbmf == null!");
        }
        if (dbtbTypf < DATATYPE_STRING || dbtbTypf > DATATYPE_DOUBLE) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for dbtbTypf!");
        }
        if (listMinLfngti < 0 || listMinLfngti > listMbxLfngti) {
            tirow nfw IllfgblArgumfntExdfption("Invblid list bounds!");
        }

        Attributf bttr = nfw Attributf();
        bttr.bttrNbmf = bttrNbmf;
        bttr.vblufTypf = VALUE_LIST;
        bttr.dbtbTypf = dbtbTypf;
        bttr.rfquirfd = rfquirfd;
        bttr.listMinLfngti = listMinLfngti;
        bttr.listMbxLfngti = listMbxLfngti;

        flfmfnt.bttrList.bdd(bttrNbmf);
        flfmfnt.bttrMbp.put(bttrNbmf, bttr);
    }

    /**
     * Adds b nfw bttributf to b prfviously dffinfd flfmfnt tibt will
     * bf dffinfd by tif fnumfrbtfd vblufs <dodf>TRUE</dodf> bnd
     * <dodf>FALSE</dodf>, witi b dbtbtypf of
     * <dodf>DATATYPE_BOOLEAN</dodf>.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm bttrNbmf tif nbmf of tif bttributf bfing bddfd.
     * @pbrbm ibsDffbultVbluf <dodf>truf</dodf> if b dffbult vbluf
     * siould bf prfsfnt.
     * @pbrbm dffbultVbluf tif dffbult vbluf for tif bttributf bs b
     * <dodf>boolfbn</dodf>, ignorfd if <dodf>ibsDffbultVbluf</dodf>
     * is <dodf>fblsf</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis
     * formbt.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>bttrNbmf</dodf> is
     * <dodf>null</dodf>.
     */
    protfdtfd void bddBoolfbnAttributf(String flfmfntNbmf,
                                       String bttrNbmf,
                                       boolfbn ibsDffbultVbluf,
                                       boolfbn dffbultVbluf) {
        List<String> vblufs = nfw ArrbyList<>();
        vblufs.bdd("TRUE");
        vblufs.bdd("FALSE");

        String dvbl = null;
        if (ibsDffbultVbluf) {
            dvbl = dffbultVbluf ? "TRUE" : "FALSE";
        }
        bddAttributf(flfmfntNbmf,
                     bttrNbmf,
                     DATATYPE_BOOLEAN,
                     truf,
                     dvbl,
                     vblufs);
    }

    /**
     * Rfmovfs bn bttributf from b prfviously dffinfd flfmfnt.  If no
     * bttributf witi tif givfn nbmf wbs prfsfnt in tif givfn flfmfnt,
     * notiing ibppfns bnd no fxdfption is tirown.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm bttrNbmf tif nbmf of tif bttributf bfing rfmovfd.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis formbt.
     */
    protfdtfd void rfmovfAttributf(String flfmfntNbmf, String bttrNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        flfmfnt.bttrList.rfmovf(bttrNbmf);
        flfmfnt.bttrMbp.rfmovf(bttrNbmf);
    }

    /**
     * Allows bn <dodf>Objfdt</dodf> rfffrfndf of b givfn dlbss typf
     * to bf storfd in nodfs implfmfnting tif nbmfd flfmfnt.  Tif
     * vbluf of tif <dodf>Objfdt</dodf> is undonstrbinfd otifr tibn by
     * its dlbss typf.
     *
     * <p> If bn <dodf>Objfdt</dodf> rfffrfndf wbs prfviously bllowfd,
     * tif prfvious sfttings brf ovfrwrittfn.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm dlbssTypf b <dodf>Clbss</dodf> vbribblf indidbting tif
     * lfgbl dlbss typf for tif objfdt vbluf.
     * @pbrbm rfquirfd <dodf>truf</dodf> if bn objfdt vbluf must bf prfsfnt.
     * @pbrbm dffbultVbluf tif dffbult vbluf for tif
     * <dodf>Objfdt</dodf> rfffrfndf, or <dodf>null</dodf>.
     * @pbrbm <T> tif typf of tif objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis formbt.
     */
    protfdtfd <T> void bddObjfdtVbluf(String flfmfntNbmf,
                                      Clbss<T> dlbssTypf,
                                      boolfbn rfquirfd,
                                      T dffbultVbluf)
    {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        ObjfdtVbluf<T> obj = nfw ObjfdtVbluf<>();
        obj.vblufTypf = VALUE_ARBITRARY;
        obj.dlbssTypf = dlbssTypf;
        obj.dffbultVbluf = dffbultVbluf;

        flfmfnt.objfdtVbluf = obj;
    }

    /**
     * Allows bn <dodf>Objfdt</dodf> rfffrfndf of b givfn dlbss typf
     * to bf storfd in nodfs implfmfnting tif nbmfd flfmfnt.  Tif
     * vbluf of tif <dodf>Objfdt</dodf> must bf onf of tif vblufs
     * givfn by <dodf>fnumfrbtfdVblufs</dodf>.
     *
     * <p> If bn <dodf>Objfdt</dodf> rfffrfndf wbs prfviously bllowfd,
     * tif prfvious sfttings brf ovfrwrittfn.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm dlbssTypf b <dodf>Clbss</dodf> vbribblf indidbting tif
     * lfgbl dlbss typf for tif objfdt vbluf.
     * @pbrbm rfquirfd <dodf>truf</dodf> if bn objfdt vbluf must bf prfsfnt.
     * @pbrbm dffbultVbluf tif dffbult vbluf for tif
     * <dodf>Objfdt</dodf> rfffrfndf, or <dodf>null</dodf>.
     * @pbrbm fnumfrbtfdVblufs b <dodf>List</dodf> of
     * <dodf>Objfdt</dodf>s dontbining tif lfgbl vblufs for tif
     * objfdt rfffrfndf.
     * @pbrbm <T> tif typf of tif objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis formbt.
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>fnumfrbtfdVblufs</dodf> is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>fnumfrbtfdVblufs</dodf> dofs not dontbin bt lfbst onf
     * fntry.
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>fnumfrbtfdVblufs</dodf> dontbins bn flfmfnt tibt is not
     * bn instbndf of tif dlbss typf dfnotfd by <dodf>dlbssTypf</dodf>
     * or is <dodf>null</dodf>.
     */
    protfdtfd <T> void bddObjfdtVbluf(String flfmfntNbmf,
                                      Clbss<T> dlbssTypf,
                                      boolfbn rfquirfd,
                                      T dffbultVbluf,
                                      List<? fxtfnds T> fnumfrbtfdVblufs)
    {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        if (fnumfrbtfdVblufs == null) {
            tirow nfw IllfgblArgumfntExdfption("fnumfrbtfdVblufs == null!");
        }
        if (fnumfrbtfdVblufs.sizf() == 0) {
            tirow nfw IllfgblArgumfntExdfption("fnumfrbtfdVblufs is fmpty!");
        }
        Itfrbtor<? fxtfnds T> itfr = fnumfrbtfdVblufs.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            Objfdt o = itfr.nfxt();
            if (o == null) {
                tirow nfw IllfgblArgumfntExdfption("fnumfrbtfdVblufs dontbins b null!");
            }
            if (!dlbssTypf.isInstbndf(o)) {
                tirow nfw IllfgblArgumfntExdfption("fnumfrbtfdVblufs dontbins b vbluf not of dlbss dlbssTypf!");
            }
        }

        ObjfdtVbluf<T> obj = nfw ObjfdtVbluf<>();
        obj.vblufTypf = VALUE_ENUMERATION;
        obj.dlbssTypf = dlbssTypf;
        obj.dffbultVbluf = dffbultVbluf;
        obj.fnumfrbtfdVblufs = fnumfrbtfdVblufs;

        flfmfnt.objfdtVbluf = obj;
    }

    /**
     * Allows bn <dodf>Objfdt</dodf> rfffrfndf of b givfn dlbss typf
     * to bf storfd in nodfs implfmfnting tif nbmfd flfmfnt.  Tif
     * vbluf of tif <dodf>Objfdt</dodf> must bf witiin tif rbngf givfn
     * by <dodf>minVbluf</dodf> bnd <dodf>mbxVbluf</dodf>.
     * Furtifrmorf, tif dlbss typf must implfmfnt tif
     * <dodf>Compbrbblf</dodf> intfrfbdf.
     *
     * <p> If bn <dodf>Objfdt</dodf> rfffrfndf wbs prfviously bllowfd,
     * tif prfvious sfttings brf ovfrwrittfn.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm dlbssTypf b <dodf>Clbss</dodf> vbribblf indidbting tif
     * lfgbl dlbss typf for tif objfdt vbluf.
     * @pbrbm dffbultVbluf tif dffbult vbluf for tif
     * @pbrbm minVbluf tif smbllfst (indlusivf or fxdlusivf dfpfnding
     * on tif vbluf of <dodf>minIndlusivf</dodf>) lfgbl vbluf for tif
     * objfdt vbluf, bs b <dodf>String</dodf>.
     * @pbrbm mbxVbluf tif lbrgfst (indlusivf or fxdlusivf dfpfnding
     * on tif vbluf of <dodf>minIndlusivf</dodf>) lfgbl vbluf for tif
     * objfdt vbluf, bs b <dodf>String</dodf>.
     * @pbrbm minIndlusivf <dodf>truf</dodf> if <dodf>minVbluf</dodf>
     * is indlusivf.
     * @pbrbm mbxIndlusivf <dodf>truf</dodf> if <dodf>mbxVbluf</dodf>
     * is indlusivf.
     * @pbrbm <T> tif typf of tif objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis
     * formbt.
     */
    protfdtfd <T fxtfnds Objfdt & Compbrbblf<? supfr T>> void
        bddObjfdtVbluf(String flfmfntNbmf,
                       Clbss<T> dlbssTypf,
                       T dffbultVbluf,
                       Compbrbblf<? supfr T> minVbluf,
                       Compbrbblf<? supfr T> mbxVbluf,
                       boolfbn minIndlusivf,
                       boolfbn mbxIndlusivf)
    {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        ObjfdtVbluf<T> obj = nfw ObjfdtVbluf<>();
        obj.vblufTypf = VALUE_RANGE;
        if (minIndlusivf) {
            obj.vblufTypf |= VALUE_RANGE_MIN_INCLUSIVE_MASK;
        }
        if (mbxIndlusivf) {
            obj.vblufTypf |= VALUE_RANGE_MAX_INCLUSIVE_MASK;
        }
        obj.dlbssTypf = dlbssTypf;
        obj.dffbultVbluf = dffbultVbluf;
        obj.minVbluf = minVbluf;
        obj.mbxVbluf = mbxVbluf;

        flfmfnt.objfdtVbluf = obj;
    }

    /**
     * Allows bn <dodf>Objfdt</dodf> rfffrfndf of b givfn dlbss typf
     * to bf storfd in nodfs implfmfnting tif nbmfd flfmfnt.  Tif
     * vbluf of tif <dodf>Objfdt</dodf> must bn brrby of objfdts of
     * dlbss typf givfn by <dodf>dlbssTypf</dodf>, witi bt lfbst
     * <dodf>brrbyMinLfngti</dodf> bnd bt most
     * <dodf>brrbyMbxLfngti</dodf> flfmfnts.
     *
     * <p> If bn <dodf>Objfdt</dodf> rfffrfndf wbs prfviously bllowfd,
     * tif prfvious sfttings brf ovfrwrittfn.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm dlbssTypf b <dodf>Clbss</dodf> vbribblf indidbting tif
     * lfgbl dlbss typf for tif objfdt vbluf.
     * @pbrbm brrbyMinLfngti tif smbllfst lfgbl lfngti for tif brrby.
     * @pbrbm brrbyMbxLfngti tif lbrgfst lfgbl lfngti for tif brrby.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf> is
     * not b lfgbl flfmfnt nbmf for tiis formbt.
     */
    protfdtfd void bddObjfdtVbluf(String flfmfntNbmf,
                                  Clbss<?> dlbssTypf,
                                  int brrbyMinLfngti,
                                  int brrbyMbxLfngti) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        ObjfdtVbluf<Objfdt> obj = nfw ObjfdtVbluf<>();
        obj.vblufTypf = VALUE_LIST;
        obj.dlbssTypf = dlbssTypf;
        obj.brrbyMinLfngti = brrbyMinLfngti;
        obj.brrbyMbxLfngti = brrbyMbxLfngti;

        flfmfnt.objfdtVbluf = obj;
    }

    /**
     * Disbllows bn <dodf>Objfdt</dodf> rfffrfndf from bfing storfd in
     * nodfs implfmfnting tif nbmfd flfmfnt.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf> is
     * not b lfgbl flfmfnt nbmf for tiis formbt.
     */
    protfdtfd void rfmovfObjfdtVbluf(String flfmfntNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        flfmfnt.objfdtVbluf = null;
    }

    // Utility mftiod

    // Mftiods from IIOMftbdbtbFormbt

    // Root

    publid String gftRootNbmf() {
        rfturn rootNbmf;
    }

    // Multiplidity

    publid bbstrbdt boolfbn dbnNodfAppfbr(String flfmfntNbmf,
                                          ImbgfTypfSpfdififr imbgfTypf);

    publid int gftElfmfntMinCiildrfn(String flfmfntNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        if (flfmfnt.diildPolidy != CHILD_POLICY_REPEAT) {
            tirow nfw IllfgblArgumfntExdfption("Ciild polidy not CHILD_POLICY_REPEAT!");
        }
        rfturn flfmfnt.minCiildrfn;
    }

    publid int gftElfmfntMbxCiildrfn(String flfmfntNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        if (flfmfnt.diildPolidy != CHILD_POLICY_REPEAT) {
            tirow nfw IllfgblArgumfntExdfption("Ciild polidy not CHILD_POLICY_REPEAT!");
        }
        rfturn flfmfnt.mbxCiildrfn;
    }

    privbtf String gftRfsourdf(String kfy, Lodblf lodblf) {
        if (lodblf == null) {
            lodblf = Lodblf.gftDffbult();
        }

        /**
         * If bn bpplft supplifs bn implfmfntbtion of IIOMftbdbtbFormbt bnd
         * rfsourdf bundlfs, tifn tif rfsourdf bundlf will nffd to bf
         * bddfssfd vib tif bpplft dlbss lobdfr. So first try tif dontfxt
         * dlbss lobdfr to lodbtf tif rfsourdf bundlf.
         * If tibt tirows MissingRfsourdfExdfption, tifn try tif
         * systfm dlbss lobdfr.
         */
        ClbssLobdfr lobdfr =
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<ClbssLobdfr>() {
                   publid ClbssLobdfr run() {
                       rfturn Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
                   }
            });

        RfsourdfBundlf bundlf = null;
        try {
            bundlf = RfsourdfBundlf.gftBundlf(rfsourdfBbsfNbmf,
                                              lodblf, lobdfr);
        } dbtdi (MissingRfsourdfExdfption mrf) {
            try {
                bundlf = RfsourdfBundlf.gftBundlf(rfsourdfBbsfNbmf, lodblf);
            } dbtdi (MissingRfsourdfExdfption mrf1) {
                rfturn null;
            }
        }

        try {
            rfturn bundlf.gftString(kfy);
        } dbtdi (MissingRfsourdfExdfption f) {
            rfturn null;
        }
    }

    /**
     * Rfturns b <dodf>String</dodf> dontbining b dfsdription of tif
     * nbmfd flfmfnt, or <dodf>null</dodf>.  Tif dfsdription will bf
     * lodblizfd for tif supplifd <dodf>Lodblf</dodf> if possiblf.
     *
     * <p> Tif dffbult implfmfntbtion will first lodbtf b
     * <dodf>RfsourdfBundlf</dodf> using tif durrfnt rfsourdf bbsf
     * nbmf sft by <dodf>sftRfsourdfBbsfNbmf</dodf> bnd tif supplifd
     * <dodf>Lodblf</dodf>, using tif fbllbbdk mfdibnism dfsdribfd in
     * tif dommfnts for <dodf>RfsourdfBundlf.gftBundlf</dodf>.  If b
     * <dodf>RfsourdfBundlf</dodf> is found, tif flfmfnt nbmf will bf
     * usfd bs b kfy to its <dodf>gftString</dodf> mftiod, bnd tif
     * rfsult rfturnfd.  If no <dodf>RfsourdfBundlf</dodf> is found,
     * or no sudi kfy is prfsfnt, <dodf>null</dodf> will bf rfturnfd.
     *
     * <p> If <dodf>lodblf</dodf> is <dodf>null</dodf>, tif durrfnt
     * dffbult <dodf>Lodblf</dodf> rfturnfd by <dodf>Lodblf.gftLodblf</dodf>
     * will bf usfd.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm lodblf tif <dodf>Lodblf</dodf> for wiidi lodblizbtion
     * will bf bttfmptfd.
     *
     * @rfturn tif flfmfnt dfsdription.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis formbt.
     *
     * @sff #sftRfsourdfBbsfNbmf
     */
    publid String gftElfmfntDfsdription(String flfmfntNbmf,
                                        Lodblf lodblf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        rfturn gftRfsourdf(flfmfntNbmf, lodblf);
    }

    // Ciildrfn

    publid int gftCiildPolidy(String flfmfntNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        rfturn flfmfnt.diildPolidy;
    }

    publid String[] gftCiildNbmfs(String flfmfntNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        if (flfmfnt.diildPolidy == CHILD_POLICY_EMPTY) {
            rfturn null;
        }
        rfturn flfmfnt.diildList.toArrby(nfw String[0]);
    }

    // Attributfs

    publid String[] gftAttributfNbmfs(String flfmfntNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        List<String> nbmfs = flfmfnt.bttrList;

        String[] rfsult = nfw String[nbmfs.sizf()];
        rfturn nbmfs.toArrby(rfsult);
    }

    publid int gftAttributfVblufTypf(String flfmfntNbmf, String bttrNbmf) {
        Attributf bttr = gftAttributf(flfmfntNbmf, bttrNbmf);
        rfturn bttr.vblufTypf;
    }

    publid int gftAttributfDbtbTypf(String flfmfntNbmf, String bttrNbmf) {
        Attributf bttr = gftAttributf(flfmfntNbmf, bttrNbmf);
        rfturn bttr.dbtbTypf;
    }

    publid boolfbn isAttributfRfquirfd(String flfmfntNbmf, String bttrNbmf) {
        Attributf bttr = gftAttributf(flfmfntNbmf, bttrNbmf);
        rfturn bttr.rfquirfd;
    }

    publid String gftAttributfDffbultVbluf(String flfmfntNbmf,
                                           String bttrNbmf) {
        Attributf bttr = gftAttributf(flfmfntNbmf, bttrNbmf);
        rfturn bttr.dffbultVbluf;
    }

    publid String[] gftAttributfEnumfrbtions(String flfmfntNbmf,
                                             String bttrNbmf) {
        Attributf bttr = gftAttributf(flfmfntNbmf, bttrNbmf);
        if (bttr.vblufTypf != VALUE_ENUMERATION) {
            tirow nfw IllfgblArgumfntExdfption
                ("Attributf not bn fnumfrbtion!");
        }

        List<String> vblufs = bttr.fnumfrbtfdVblufs;
        String[] rfsult = nfw String[vblufs.sizf()];
        rfturn vblufs.toArrby(rfsult);
    }

    publid String gftAttributfMinVbluf(String flfmfntNbmf, String bttrNbmf) {
        Attributf bttr = gftAttributf(flfmfntNbmf, bttrNbmf);
        if (bttr.vblufTypf != VALUE_RANGE &&
            bttr.vblufTypf != VALUE_RANGE_MIN_INCLUSIVE &&
            bttr.vblufTypf != VALUE_RANGE_MAX_INCLUSIVE &&
            bttr.vblufTypf != VALUE_RANGE_MIN_MAX_INCLUSIVE) {
            tirow nfw IllfgblArgumfntExdfption("Attributf not b rbngf!");
        }

        rfturn bttr.minVbluf;
    }

    publid String gftAttributfMbxVbluf(String flfmfntNbmf, String bttrNbmf) {
        Attributf bttr = gftAttributf(flfmfntNbmf, bttrNbmf);
        if (bttr.vblufTypf != VALUE_RANGE &&
            bttr.vblufTypf != VALUE_RANGE_MIN_INCLUSIVE &&
            bttr.vblufTypf != VALUE_RANGE_MAX_INCLUSIVE &&
            bttr.vblufTypf != VALUE_RANGE_MIN_MAX_INCLUSIVE) {
            tirow nfw IllfgblArgumfntExdfption("Attributf not b rbngf!");
        }

        rfturn bttr.mbxVbluf;
    }

    publid int gftAttributfListMinLfngti(String flfmfntNbmf, String bttrNbmf) {
        Attributf bttr = gftAttributf(flfmfntNbmf, bttrNbmf);
        if (bttr.vblufTypf != VALUE_LIST) {
            tirow nfw IllfgblArgumfntExdfption("Attributf not b list!");
        }

        rfturn bttr.listMinLfngti;
    }

    publid int gftAttributfListMbxLfngti(String flfmfntNbmf, String bttrNbmf) {
        Attributf bttr = gftAttributf(flfmfntNbmf, bttrNbmf);
        if (bttr.vblufTypf != VALUE_LIST) {
            tirow nfw IllfgblArgumfntExdfption("Attributf not b list!");
        }

        rfturn bttr.listMbxLfngti;
    }

    /**
     * Rfturns b <dodf>String</dodf> dontbining b dfsdription of tif
     * nbmfd bttributf, or <dodf>null</dodf>.  Tif dfsdription will bf
     * lodblizfd for tif supplifd <dodf>Lodblf</dodf> if possiblf.
     *
     * <p> Tif dffbult implfmfntbtion will first lodbtf b
     * <dodf>RfsourdfBundlf</dodf> using tif durrfnt rfsourdf bbsf
     * nbmf sft by <dodf>sftRfsourdfBbsfNbmf</dodf> bnd tif supplifd
     * <dodf>Lodblf</dodf>, using tif fbllbbdk mfdibnism dfsdribfd in
     * tif dommfnts for <dodf>RfsourdfBundlf.gftBundlf</dodf>.  If b
     * <dodf>RfsourdfBundlf</dodf> is found, tif flfmfnt nbmf followfd
     * by b "/" dibrbdtfr followfd by tif bttributf nbmf
     * (<dodf>flfmfntNbmf + "/" + bttrNbmf</dodf>) will bf usfd bs b
     * kfy to its <dodf>gftString</dodf> mftiod, bnd tif rfsult
     * rfturnfd.  If no <dodf>RfsourdfBundlf</dodf> is found, or no
     * sudi kfy is prfsfnt, <dodf>null</dodf> will bf rfturnfd.
     *
     * <p> If <dodf>lodblf</dodf> is <dodf>null</dodf>, tif durrfnt
     * dffbult <dodf>Lodblf</dodf> rfturnfd by <dodf>Lodblf.gftLodblf</dodf>
     * will bf usfd.
     *
     * @pbrbm flfmfntNbmf tif nbmf of tif flfmfnt.
     * @pbrbm bttrNbmf tif nbmf of tif bttributf.
     * @pbrbm lodblf tif <dodf>Lodblf</dodf> for wiidi lodblizbtion
     * will bf bttfmptfd, or <dodf>null</dodf>.
     *
     * @rfturn tif bttributf dfsdription.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>flfmfntNbmf</dodf>
     * is <dodf>null</dodf>, or is not b lfgbl flfmfnt nbmf for tiis formbt.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>bttrNbmf</dodf> is
     * <dodf>null</dodf> or is not b lfgbl bttributf nbmf for tiis
     * flfmfnt.
     *
     * @sff #sftRfsourdfBbsfNbmf
     */
    publid String gftAttributfDfsdription(String flfmfntNbmf,
                                          String bttrNbmf,
                                          Lodblf lodblf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        if (bttrNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("bttrNbmf == null!");
        }
        Attributf bttr = flfmfnt.bttrMbp.gft(bttrNbmf);
        if (bttr == null) {
            tirow nfw IllfgblArgumfntExdfption("No sudi bttributf!");
        }

        String kfy = flfmfntNbmf + "/" + bttrNbmf;
        rfturn gftRfsourdf(kfy, lodblf);
    }

    privbtf ObjfdtVbluf<?> gftObjfdtVbluf(String flfmfntNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        ObjfdtVbluf<?> objv = flfmfnt.objfdtVbluf;
        if (objv == null) {
            tirow nfw IllfgblArgumfntExdfption("No objfdt witiin flfmfnt " +
                                               flfmfntNbmf + "!");
        }
        rfturn objv;
    }

    publid int gftObjfdtVblufTypf(String flfmfntNbmf) {
        Elfmfnt flfmfnt = gftElfmfnt(flfmfntNbmf);
        ObjfdtVbluf<?> objv = flfmfnt.objfdtVbluf;
        if (objv == null) {
            rfturn VALUE_NONE;
        }
        rfturn objv.vblufTypf;
    }

    publid Clbss<?> gftObjfdtClbss(String flfmfntNbmf) {
        ObjfdtVbluf<?> objv = gftObjfdtVbluf(flfmfntNbmf);
        rfturn objv.dlbssTypf;
    }

    publid Objfdt gftObjfdtDffbultVbluf(String flfmfntNbmf) {
        ObjfdtVbluf<?> objv = gftObjfdtVbluf(flfmfntNbmf);
        rfturn objv.dffbultVbluf;
    }

    publid Objfdt[] gftObjfdtEnumfrbtions(String flfmfntNbmf) {
        ObjfdtVbluf<?> objv = gftObjfdtVbluf(flfmfntNbmf);
        if (objv.vblufTypf != VALUE_ENUMERATION) {
            tirow nfw IllfgblArgumfntExdfption("Not bn fnumfrbtion!");
        }
        List<?> vlist = objv.fnumfrbtfdVblufs;
        Objfdt[] vblufs = nfw Objfdt[vlist.sizf()];
        rfturn vlist.toArrby(vblufs);
    }

    publid Compbrbblf<?> gftObjfdtMinVbluf(String flfmfntNbmf) {
        ObjfdtVbluf<?> objv = gftObjfdtVbluf(flfmfntNbmf);
        if ((objv.vblufTypf & VALUE_RANGE) != VALUE_RANGE) {
            tirow nfw IllfgblArgumfntExdfption("Not b rbngf!");
        }
        rfturn objv.minVbluf;
    }

    publid Compbrbblf<?> gftObjfdtMbxVbluf(String flfmfntNbmf) {
        ObjfdtVbluf<?> objv = gftObjfdtVbluf(flfmfntNbmf);
        if ((objv.vblufTypf & VALUE_RANGE) != VALUE_RANGE) {
            tirow nfw IllfgblArgumfntExdfption("Not b rbngf!");
        }
        rfturn objv.mbxVbluf;
    }

    publid int gftObjfdtArrbyMinLfngti(String flfmfntNbmf) {
        ObjfdtVbluf<?> objv = gftObjfdtVbluf(flfmfntNbmf);
        if (objv.vblufTypf != VALUE_LIST) {
            tirow nfw IllfgblArgumfntExdfption("Not b list!");
        }
        rfturn objv.brrbyMinLfngti;
    }

    publid int gftObjfdtArrbyMbxLfngti(String flfmfntNbmf) {
        ObjfdtVbluf<?> objv = gftObjfdtVbluf(flfmfntNbmf);
        if (objv.vblufTypf != VALUE_LIST) {
            tirow nfw IllfgblArgumfntExdfption("Not b list!");
        }
        rfturn objv.brrbyMbxLfngti;
    }

    // Stbndbrd formbt dfsdriptor

    privbtf syndironizfd stbtid void drfbtfStbndbrdFormbt() {
        if (stbndbrdFormbt == null) {
            stbndbrdFormbt = nfw StbndbrdMftbdbtbFormbt();
        }
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtbFormbt</dodf> objfdt dfsdribing tif
     * stbndbrd, plug-in nfutrbl <dodf>jbvbx.imbgfio_1.0</dodf>
     * mftbdbtb dodumfnt formbt dfsdribfd in tif dommfnt of tif
     * <dodf>jbvbx.imbgfio.mftbdbtb</dodf> pbdkbgf.
     *
     * @rfturn b prfdffinfd <dodf>IIOMftbdbtbFormbt</dodf> instbndf.
     */
    publid stbtid IIOMftbdbtbFormbt gftStbndbrdFormbtInstbndf() {
        drfbtfStbndbrdFormbt();
        rfturn stbndbrdFormbt;
    }
}
