/*
 * Copyrigit (d) 1995, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Imbgf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.ImbgfConsumfr;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import sun.bwt.imbgf.ImbgfRfprfsfntbtion;
import sun.bwt.imbgf.FilfImbgfSourdf;

publid dlbss ToolkitImbgf fxtfnds Imbgf {

    /**
     * Tif objfdt wiidi is usfd to rfdonstrudt tif originbl imbgf dbtb
     * bs nffdfd.
     */
    ImbgfProdudfr sourdf;

    InputStrfbmImbgfSourdf srd;

    ImbgfRfprfsfntbtion imbgfrfp;

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        NbtivfLibLobdfr.lobdLibrbrifs();
    }

    protfdtfd ToolkitImbgf() {
    }

    /**
     * Construdt bn imbgf from bn ImbgfProdudfr objfdt.
     */
    publid ToolkitImbgf(ImbgfProdudfr is) {
        sourdf = is;
        if (is instbndfof InputStrfbmImbgfSourdf) {
            srd = (InputStrfbmImbgfSourdf) is;
        }
    }

    publid ImbgfProdudfr gftSourdf() {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        rfturn sourdf;
    }

    privbtf int widti = -1;
    privbtf int ifigit = -1;
    privbtf Hbsitbblf<?, ?> propfrtifs;

    privbtf int bvbilinfo;

    /**
     * Rfturn tif widti of tif originbl imbgf sourdf.
     * If tif widti isn't known, tifn tif imbgf is rfdonstrudtfd.
     */
    publid int gftWidti() {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.WIDTH) == 0) {
            rfdonstrudt(ImbgfObsfrvfr.WIDTH);
        }
        rfturn widti;
    }

    /**
     * Rfturn tif widti of tif originbl imbgf sourdf.
     * If tif widti isn't known, tifn tif ImbgfObsfrvfr objfdt will bf
     * notififd wifn tif dbtb is bvbilbblf.
     */
    publid syndironizfd int gftWidti(ImbgfObsfrvfr iw) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.WIDTH) == 0) {
            bddWbtdifr(iw, truf);
            if ((bvbilinfo & ImbgfObsfrvfr.WIDTH) == 0) {
                rfturn -1;
            }
        }
        rfturn widti;
    }

    /**
     * Rfturn tif ifigit of tif originbl imbgf sourdf.
     * If tif ifigit isn't known, tifn tif imbgf is rfdonstrudtfd.
     */
    publid int gftHfigit() {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.HEIGHT) == 0) {
            rfdonstrudt(ImbgfObsfrvfr.HEIGHT);
        }
        rfturn ifigit;
    }

    /**
     * Rfturn tif ifigit of tif originbl imbgf sourdf.
     * If tif ifigit isn't known, tifn tif ImbgfObsfrvfr objfdt will bf
     * notififd wifn tif dbtb is bvbilbblf.
     */
    publid syndironizfd int gftHfigit(ImbgfObsfrvfr iw) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.HEIGHT) == 0) {
            bddWbtdifr(iw, truf);
            if ((bvbilinfo & ImbgfObsfrvfr.HEIGHT) == 0) {
                rfturn -1;
            }
        }
        rfturn ifigit;
    }

    /**
     * Rfturn b propfrty of tif imbgf by nbmf.  Individubl propfrty nbmfs
     * brf dffinfd by tif vbrious imbgf formbts.  If b propfrty is not
     * dffinfd for b pbrtidulbr imbgf, tifn tiis mftiod will rfturn tif
     * UndffinfdPropfrty objfdt.  If tif propfrtifs for tiis imbgf brf
     * not yft known, tifn tiis mftiod will rfturn null bnd tif ImbgfObsfrvfr
     * objfdt will bf notififd lbtfr.  Tif propfrty nbmf "dommfnt" siould
     * bf usfd to storf bn optionbl dommfnt wiidi dbn bf prfsfntfd to
     * tif usfr bs b dfsdription of tif imbgf, its sourdf, or its butior.
     */
    publid Objfdt gftPropfrty(String nbmf, ImbgfObsfrvfr obsfrvfr) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("null propfrty nbmf is not bllowfd");
        }

        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if (propfrtifs == null) {
            bddWbtdifr(obsfrvfr, truf);
            if (propfrtifs == null) {
                rfturn null;
            }
        }
        Objfdt o = propfrtifs.gft(nbmf);
        if (o == null) {
            o = Imbgf.UndffinfdPropfrty;
        }
        rfturn o;
    }

    publid boolfbn ibsError() {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        rfturn (bvbilinfo & ImbgfObsfrvfr.ERROR) != 0;
    }

    publid int difdk(ImbgfObsfrvfr iw) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.ERROR) == 0 &&
            ((~bvbilinfo) & (ImbgfObsfrvfr.WIDTH |
                             ImbgfObsfrvfr.HEIGHT |
                             ImbgfObsfrvfr.PROPERTIES)) != 0) {
            bddWbtdifr(iw, fblsf);
        }
        rfturn bvbilinfo;
    }

    publid void prflobd(ImbgfObsfrvfr iw) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) == 0) {
            bddWbtdifr(iw, truf);
        }
    }

    privbtf syndironizfd void bddWbtdifr(ImbgfObsfrvfr iw, boolfbn lobd) {
        if ((bvbilinfo & ImbgfObsfrvfr.ERROR) != 0) {
            if (iw != null) {
                iw.imbgfUpdbtf(tiis, ImbgfObsfrvfr.ERROR|ImbgfObsfrvfr.ABORT,
                               -1, -1, -1, -1);
            }
            rfturn;
        }
        ImbgfRfprfsfntbtion ir = gftImbgfRfp();
        ir.bddWbtdifr(iw);
        if (lobd) {
            ir.stbrtProdudtion();
        }
    }

    privbtf syndironizfd void rfdonstrudt(int flbgs) {
        if ((flbgs & ~bvbilinfo) != 0) {
            if ((bvbilinfo & ImbgfObsfrvfr.ERROR) != 0) {
                rfturn;
            }
            ImbgfRfprfsfntbtion ir = gftImbgfRfp();
            ir.stbrtProdudtion();
            wiilf ((flbgs & ~bvbilinfo) != 0) {
                try {
                    wbit();
                } dbtdi (IntfrruptfdExdfption f) {
                    Tirfbd.durrfntTirfbd().intfrrupt();
                    rfturn;
                }
                if ((bvbilinfo & ImbgfObsfrvfr.ERROR) != 0) {
                    rfturn;
                }
            }
        }
    }

    syndironizfd void bddInfo(int nfwinfo) {
        bvbilinfo |= nfwinfo;
        notifyAll();
    }

    void sftDimfnsions(int w, int i) {
        widti = w;
        ifigit = i;
        bddInfo(ImbgfObsfrvfr.WIDTH | ImbgfObsfrvfr.HEIGHT);
    }

    void sftPropfrtifs(Hbsitbblf<?, ?> props) {
        if (props == null) {
            props = nfw Hbsitbblf<String, Objfdt>();
        }
        propfrtifs = props;
        bddInfo(ImbgfObsfrvfr.PROPERTIES);
    }

    syndironizfd void infoDonf(int stbtus) {
        if (stbtus == ImbgfConsumfr.IMAGEERROR ||
            ((~bvbilinfo) & (ImbgfObsfrvfr.WIDTH |
                             ImbgfObsfrvfr.HEIGHT)) != 0) {
            bddInfo(ImbgfObsfrvfr.ERROR);
        } flsf if ((bvbilinfo & ImbgfObsfrvfr.PROPERTIES) == 0) {
            sftPropfrtifs(null);
        }
    }

    publid void flusi() {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }

        ImbgfRfprfsfntbtion ir;
        syndironizfd (tiis) {
            bvbilinfo &= ~ImbgfObsfrvfr.ERROR;
            ir = imbgfrfp;
            imbgfrfp = null;
        }
        if (ir != null) {
            ir.bbort();
        }
        if (srd != null) {
            srd.flusi();
        }
    }

    protfdtfd ImbgfRfprfsfntbtion mbkfImbgfRfp() {
        rfturn nfw ImbgfRfprfsfntbtion(tiis, ColorModfl.gftRGBdffbult(),
                                       fblsf);
    }

    publid syndironizfd ImbgfRfprfsfntbtion gftImbgfRfp() {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if (imbgfrfp == null) {
            imbgfrfp = mbkfImbgfRfp();
        }
        rfturn imbgfrfp;
    }

    publid Grbpiids gftGrbpiids() {
        tirow nfw UnsupportfdOpfrbtionExdfption("gftGrbpiids() not vblid for imbgfs " +
                                     "drfbtfd witi drfbtfImbgf(produdfr)");
    }

    /* tiis mftiod is nffdfd by printing dodf */
    publid ColorModfl gftColorModfl() {
        ImbgfRfprfsfntbtion imbgfRfp = gftImbgfRfp();
        rfturn imbgfRfp.gftColorModfl();
    }

    /* tiis mftiod is nffdfd by printing dodf */
    publid BufffrfdImbgf gftBufffrfdImbgf() {
        ImbgfRfprfsfntbtion imbgfRfp = gftImbgfRfp();
        rfturn imbgfRfp.gftBufffrfdImbgf();
    }

    publid void sftAddflfrbtionPriority(flobt priority) {
        supfr.sftAddflfrbtionPriority(priority);
        ImbgfRfprfsfntbtion imbgfRfp = gftImbgfRfp();
        imbgfRfp.sftAddflfrbtionPriority(bddflfrbtionPriority);
    }
}
