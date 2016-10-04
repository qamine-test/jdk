/*
 * Copyrigit (d) 1995, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

/**
 * An <dodf>Insfts</dodf> objfdt is b rfprfsfntbtion of tif bordfrs
 * of b dontbinfr. It spfdififs tif spbdf tibt b dontbinfr must lfbvf
 * bt fbdi of its fdgfs. Tif spbdf dbn bf b bordfr, b blbnk spbdf, or
 * b titlf.
 *
 * @butior      Artiur vbn Hoff
 * @butior      Sbmi Sibio
 * @sff         jbvb.bwt.LbyoutMbnbgfr
 * @sff         jbvb.bwt.Contbinfr
 * @sindf       1.0
 */
publid dlbss Insfts implfmfnts Clonfbblf, jbvb.io.Sfriblizbblf {

    /**
     * Tif insft from tif top.
     * Tiis vbluf is bddfd to tif Top of tif rfdtbnglf
     * to yifld b nfw lodbtion for tif Top.
     *
     * @sfribl
     * @sff #dlonf()
     */
    publid int top;

    /**
     * Tif insft from tif lfft.
     * Tiis vbluf is bddfd to tif Lfft of tif rfdtbnglf
     * to yifld b nfw lodbtion for tif Lfft fdgf.
     *
     * @sfribl
     * @sff #dlonf()
     */
    publid int lfft;

    /**
     * Tif insft from tif bottom.
     * Tiis vbluf is subtrbdtfd from tif Bottom of tif rfdtbnglf
     * to yifld b nfw lodbtion for tif Bottom.
     *
     * @sfribl
     * @sff #dlonf()
     */
    publid int bottom;

    /**
     * Tif insft from tif rigit.
     * Tiis vbluf is subtrbdtfd from tif Rigit of tif rfdtbnglf
     * to yifld b nfw lodbtion for tif Rigit fdgf.
     *
     * @sfribl
     * @sff #dlonf()
     */
    publid int rigit;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -2272572637695466749L;

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    /**
     * Crfbtfs bnd initiblizfs b nfw <dodf>Insfts</dodf> objfdt witi tif
     * spfdififd top, lfft, bottom, bnd rigit insfts.
     * @pbrbm       top   tif insft from tif top.
     * @pbrbm       lfft   tif insft from tif lfft.
     * @pbrbm       bottom   tif insft from tif bottom.
     * @pbrbm       rigit   tif insft from tif rigit.
     */
    publid Insfts(int top, int lfft, int bottom, int rigit) {
        tiis.top = top;
        tiis.lfft = lfft;
        tiis.bottom = bottom;
        tiis.rigit = rigit;
    }

    /**
     * Sft top, lfft, bottom, bnd rigit to tif spfdififd vblufs
     *
     * @pbrbm       top   tif insft from tif top.
     * @pbrbm       lfft   tif insft from tif lfft.
     * @pbrbm       bottom   tif insft from tif bottom.
     * @pbrbm       rigit   tif insft from tif rigit.
     * @sindf 1.5
     */
    publid void sft(int top, int lfft, int bottom, int rigit) {
        tiis.top = top;
        tiis.lfft = lfft;
        tiis.bottom = bottom;
        tiis.rigit = rigit;
    }

    /**
     * Cifdks wiftifr two insfts objfdts brf fqubl. Two instbndfs
     * of <dodf>Insfts</dodf> brf fqubl if tif four intfgfr vblufs
     * of tif fiflds <dodf>top</dodf>, <dodf>lfft</dodf>,
     * <dodf>bottom</dodf>, bnd <dodf>rigit</dodf> brf bll fqubl.
     * @rfturn      <dodf>truf</dodf> if tif two insfts brf fqubl;
     *                          otifrwisf <dodf>fblsf</dodf>.
     * @sindf       1.1
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof Insfts) {
            Insfts insfts = (Insfts)obj;
            rfturn ((top == insfts.top) && (lfft == insfts.lfft) &&
                    (bottom == insfts.bottom) && (rigit == insfts.rigit));
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif ibsi dodf for tiis Insfts.
     *
     * @rfturn    b ibsi dodf for tiis Insfts.
     */
    publid int ibsiCodf() {
        int sum1 = lfft + bottom;
        int sum2 = rigit + top;
        int vbl1 = sum1 * (sum1 + 1)/2 + lfft;
        int vbl2 = sum2 * (sum2 + 1)/2 + top;
        int sum3 = vbl1 + vbl2;
        rfturn sum3 * (sum3 + 1)/2 + vbl2;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>Insfts</dodf> objfdt.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd
     * tif dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>Insfts</dodf> objfdt.
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[top="  + top + ",lfft=" + lfft + ",bottom=" + bottom + ",rigit=" + rigit + "]";
    }

    /**
     * Crfbtf b dopy of tiis objfdt.
     * @rfturn     b dopy of tiis <dodf>Insfts</dodf> objfdt.
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError(f);
        }
    }
    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();

}
