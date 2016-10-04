/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.fvfnt;

import jbvbx.swing.undo.*;
import jbvbx.swing.tfxt.*;

/**
 * Intfrfbdf for dodumfnt dibngf notifidbtions.  Tiis providfs
 * dftbilfd informbtion to Dodumfnt obsfrvfrs bbout iow tif
 * Dodumfnt dibngfd.  It providfs iigi lfvfl informbtion sudi
 * bs typf of dibngf bnd wifrf it oddurrfd, bs wfll bs tif morf
 * dftbilfd strudturbl dibngfs (Wibt Elfmfnts wfrf insfrtfd bnd
 * rfmovfd).
 *
 * @butior  Timotiy Prinzing
 * @sff jbvbx.swing.tfxt.Dodumfnt
 * @sff DodumfntListfnfr
 */
publid intfrfbdf DodumfntEvfnt {

    /**
     * Rfturns tif offsft witiin tif dodumfnt of tif stbrt
     * of tif dibngf.
     *
     * @rfturn tif offsft &gt;= 0
     */
    publid int gftOffsft();

    /**
     * Rfturns tif lfngti of tif dibngf.
     *
     * @rfturn tif lfngti &gt;= 0
     */
    publid int gftLfngti();

    /**
     * Gfts tif dodumfnt tibt sourdfd tif dibngf fvfnt.
     *
     * @rfturn tif dodumfnt
     */
    publid Dodumfnt gftDodumfnt();

    /**
     * Gfts tif typf of fvfnt.
     *
     * @rfturn tif typf
     */
    publid EvfntTypf gftTypf();

    /**
     * Gfts tif dibngf informbtion for tif givfn flfmfnt.
     * Tif dibngf informbtion dfsdribfs wibt flfmfnts wfrf
     * bddfd bnd rfmovfd bnd tif lodbtion.  If tifrf wfrf
     * no dibngfs, null is rfturnfd.
     * <p>
     * Tiis mftiod is for obsfrvfrs to disdovfr tif strudturbl
     * dibngfs tibt wfrf mbdf.  Tiis mfbns tibt only flfmfnts
     * tibt fxistfd prior to tif mutbtion (bnd still fxist bftfr
     * tif mutbtion) nffd to ibvf ElfmfntCibngf rfdords.
     * Tif dibngfs mbdf bvbilbblf nffd not bf rfdursivf.
     * <p>
     * For fxbmplf, if tif bn flfmfnt is rfmovfd from it's
     * pbrfnt, tiis mftiod siould rfport tibt tif pbrfnt
     * dibngfd bnd providf bn ElfmfntCibngf implfmfntbtion
     * tibt dfsdribfs tif dibngf to tif pbrfnt.  If tif
     * diild flfmfnt rfmovfd ibd diildrfn, tifsf flfmfnts
     * do not nffd to bf rfportfd bs rfmovfd.
     * <p>
     * If bn diild flfmfnt is insfrt into b pbrfnt flfmfnt,
     * tif pbrfnt flfmfnt siould rfport b dibngf.  If tif
     * diild flfmfnt blso ibd flfmfnts insfrtfd into it
     * (grbnddiildrfn to tif pbrfnt) tifsf flfmfnts nffd
     * not rfport dibngf.
     *
     * @pbrbm flfm tif flfmfnt
     * @rfturn tif dibngf informbtion, or null if tif
     *   flfmfnt wbs not modififd
     */
    publid ElfmfntCibngf gftCibngf(Elfmfnt flfm);

    /**
     * Enumfrbtion for dodumfnt fvfnt typfs
     */
    publid stbtid finbl dlbss EvfntTypf {

        privbtf EvfntTypf(String s) {
            typfString = s;
        }

        /**
         * Insfrt typf.
         */
        publid stbtid finbl EvfntTypf INSERT = nfw EvfntTypf("INSERT");

        /**
         * Rfmovf typf.
         */
        publid stbtid finbl EvfntTypf REMOVE = nfw EvfntTypf("REMOVE");

        /**
         * Cibngf typf.
         */
        publid stbtid finbl EvfntTypf CHANGE = nfw EvfntTypf("CHANGE");

        /**
         * Convfrts tif typf to b string.
         *
         * @rfturn tif string
         */
        publid String toString() {
            rfturn typfString;
        }

        privbtf String typfString;
    }

    /**
     * Dfsdribfs dibngfs mbdf to b spfdifid flfmfnt.
     */
    publid intfrfbdf ElfmfntCibngf {

        /**
         * Rfturns tif flfmfnt rfprfsfntfd.  Tiis is tif flfmfnt
         * tibt wbs dibngfd.
         *
         * @rfturn tif flfmfnt
         */
        publid Elfmfnt gftElfmfnt();

        /**
         * Fftdifs tif indfx witiin tif flfmfnt rfprfsfntfd.
         * Tiis is tif lodbtion tibt diildrfn wfrf bddfd
         * bnd/or rfmovfd.
         *
         * @rfturn tif indfx &gt;= 0
         */
        publid int gftIndfx();

        /**
         * Gfts tif diild flfmfnts tibt wfrf rfmovfd from tif
         * givfn pbrfnt flfmfnt.  Tif flfmfnt brrby rfturnfd is
         * sortfd in tif ordfr tibt tif flfmfnts usfd to lif in
         * tif dodumfnt, bnd must bf dontiguous.
         *
         * @rfturn tif diild flfmfnts
         */
        publid Elfmfnt[] gftCiildrfnRfmovfd();

        /**
         * Gfts tif diild flfmfnts tibt wfrf bddfd to tif givfn
         * pbrfnt flfmfnt.  Tif flfmfnt brrby rfturnfd is in tif
         * ordfr tibt tif flfmfnts lif in tif dodumfnt, bnd must
         * bf dontiguous.
         *
         * @rfturn tif diild flfmfnts
         */
        publid Elfmfnt[] gftCiildrfnAddfd();

    }
}
