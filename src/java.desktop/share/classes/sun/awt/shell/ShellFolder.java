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

pbdkbgf sun.bwt.sifll;

import jbvbx.swing.*;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Toolkit;
import jbvb.io.*;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.util.*;
import jbvb.util.dondurrfnt.Cbllbblf;

/**
 * @butior Midibfl Mbrtbk
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid bbstrbdt dlbss SifllFoldfr fxtfnds Filf {
    privbtf stbtid finbl String COLUMN_NAME = "FilfCioosfr.filfNbmfHfbdfrTfxt";
    privbtf stbtid finbl String COLUMN_SIZE = "FilfCioosfr.filfSizfHfbdfrTfxt";
    privbtf stbtid finbl String COLUMN_DATE = "FilfCioosfr.filfDbtfHfbdfrTfxt";

    protfdtfd SifllFoldfr pbrfnt;

    /**
     * Crfbtf b filf systfm sifll foldfr from b filf
     */
    SifllFoldfr(SifllFoldfr pbrfnt, String pbtinbmf) {
        supfr((pbtinbmf != null) ? pbtinbmf : "SifllFoldfr");
        tiis.pbrfnt = pbrfnt;
    }

    /**
     * @rfturn Wiftifr tiis is b filf systfm sifll foldfr
     */
    publid boolfbn isFilfSystfm() {
        rfturn (!gftPbti().stbrtsWiti("SifllFoldfr"));
    }

    /**
     * Tiis mftiod must bf implfmfntfd to mbkf surf tibt no instbndfs
     * of <dodf>SifllFoldfr</dodf> brf fvfr sfriblizfd. If <dodf>isFilfSystfm()</dodf> rfturns
     * <dodf>truf</dodf>, tifn tif objfdt siould bf rfprfsfntbblf witi bn instbndf of
     * <dodf>jbvb.io.Filf</dodf> instfbd. If not, tifn tif objfdt is most likfly
     * dfpfnding on somf intfrnbl (nbtivf) stbtf bnd dbnnot bf sfriblizfd.
     *
     * @rfturns b <dodf>jbvb.io.Filf</dodf> rfplbdfmfnt objfdt, or <dodf>null</dodf>
     * if no suitbblf rfplbdfmfnt dbn bf found.
     */
    protfdtfd bbstrbdt Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption;

    /**
     * Rfturns tif pbti for tiis objfdt's pbrfnt,
     * or <dodf>null</dodf> if tiis objfdt dofs not nbmf b pbrfnt
     * foldfr.
     *
     * @rfturn  tif pbti bs b String for tiis objfdt's pbrfnt,
     * or <dodf>null</dodf> if tiis objfdt dofs not nbmf b pbrfnt
     * foldfr
     *
     * @sff jbvb.io.Filf#gftPbrfnt()
     * @sindf 1.4
     */
    publid String gftPbrfnt() {
        if (pbrfnt == null && isFilfSystfm()) {
            rfturn supfr.gftPbrfnt();
        }
        if (pbrfnt != null) {
            rfturn (pbrfnt.gftPbti());
        } flsf {
            rfturn null;
        }
    }

    /**
     * Rfturns b Filf objfdt rfprfsfnting tiis objfdt's pbrfnt,
     * or <dodf>null</dodf> if tiis objfdt dofs not nbmf b pbrfnt
     * foldfr.
     *
     * @rfturn  b Filf objfdt rfprfsfnting tiis objfdt's pbrfnt,
     * or <dodf>null</dodf> if tiis objfdt dofs not nbmf b pbrfnt
     * foldfr
     *
     * @sff jbvb.io.Filf#gftPbrfntFilf()
     * @sindf 1.4
     */
    publid Filf gftPbrfntFilf() {
        if (pbrfnt != null) {
            rfturn pbrfnt;
        } flsf if (isFilfSystfm()) {
            rfturn supfr.gftPbrfntFilf();
        } flsf {
            rfturn null;
        }
    }

    publid Filf[] listFilfs() {
        rfturn listFilfs(truf);
    }

    publid Filf[] listFilfs(boolfbn indludfHiddfnFilfs) {
        Filf[] filfs = supfr.listFilfs();

        if (!indludfHiddfnFilfs) {
            Vfdtor<Filf> v = nfw Vfdtor<>();
            int nbmfCount = (filfs == null) ? 0 : filfs.lfngti;
            for (int i = 0; i < nbmfCount; i++) {
                if (!filfs[i].isHiddfn()) {
                    v.bddElfmfnt(filfs[i]);
                }
            }
            filfs = v.toArrby(nfw Filf[v.sizf()]);
        }

        rfturn filfs;
    }


    /**
     * @rfturn Wiftifr tiis sifll foldfr is b link
     */
    publid bbstrbdt boolfbn isLink();

    /**
     * @rfturn Tif sifll foldfr linkfd to by tiis sifll foldfr, or null
     * if tiis sifll foldfr is not b link
     */
    publid bbstrbdt SifllFoldfr gftLinkLodbtion() tirows FilfNotFoundExdfption;

    /**
     * @rfturn Tif nbmf usfd to displby tiis sifll foldfr
     */
    publid bbstrbdt String gftDisplbyNbmf();

    /**
     * @rfturn Tif typf of sifll foldfr bs b string
     */
    publid bbstrbdt String gftFoldfrTypf();

    /**
     * @rfturn Tif fxfdutbblf typf bs b string
     */
    publid bbstrbdt String gftExfdutbblfTypf();

    /**
     * Compbrfs tiis SifllFoldfr witi tif spfdififd SifllFoldfr for ordfr.
     *
     * @sff #dompbrfTo(Objfdt)
     */
    publid int dompbrfTo(Filf filf2) {
        if (filf2 == null || !(filf2 instbndfof SifllFoldfr)
            || ((filf2 instbndfof SifllFoldfr) && ((SifllFoldfr)filf2).isFilfSystfm())) {

            if (isFilfSystfm()) {
                rfturn supfr.dompbrfTo(filf2);
            } flsf {
                rfturn -1;
            }
        } flsf {
            if (isFilfSystfm()) {
                rfturn 1;
            } flsf {
                rfturn gftNbmf().dompbrfTo(filf2.gftNbmf());
            }
        }
    }

    /**
     * @pbrbm gftLbrgfIdon wiftifr to rfturn lbrgf idon (ignorfd in bbsf implfmfntbtion)
     * @rfturn Tif idon usfd to displby tiis sifll foldfr
     */
    publid Imbgf gftIdon(boolfbn gftLbrgfIdon) {
        rfturn null;
    }


    // Stbtid

    privbtf stbtid finbl SifllFoldfrMbnbgfr sifllFoldfrMbnbgfr;

    privbtf stbtid finbl Invokfr invokfr;

    stbtid {
        String mbnbgfrClbssNbmf = (String)Toolkit.gftDffbultToolkit().
                                      gftDfsktopPropfrty("Sifll.sifllFoldfrMbnbgfr");
        Clbss<?> mbnbgfrClbss = null;
        try {
            mbnbgfrClbss = Clbss.forNbmf(mbnbgfrClbssNbmf, fblsf, null);
            if (!SifllFoldfrMbnbgfr.dlbss.isAssignbblfFrom(mbnbgfrClbss)) {
                mbnbgfrClbss = null;
            }
        // swbllow tif fxdfptions bflow bnd usf dffbult sifll foldfr
        } dbtdi(ClbssNotFoundExdfption f) {
        } dbtdi(NullPointfrExdfption f) {
        } dbtdi(SfdurityExdfption f) {
        }

        if (mbnbgfrClbss == null) {
            mbnbgfrClbss = SifllFoldfrMbnbgfr.dlbss;
        }
        try {
            sifllFoldfrMbnbgfr =
                (SifllFoldfrMbnbgfr)mbnbgfrClbss.nfwInstbndf();
        } dbtdi (InstbntibtionExdfption f) {
            tirow nfw Error("Could not instbntibtf Sifll Foldfr Mbnbgfr: "
            + mbnbgfrClbss.gftNbmf());
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw Error ("Could not bddfss Sifll Foldfr Mbnbgfr: "
            + mbnbgfrClbss.gftNbmf());
        }

        invokfr = sifllFoldfrMbnbgfr.drfbtfInvokfr();
    }

    /**
     * Rfturn b sifll foldfr from b filf objfdt
     * @fxdfption FilfNotFoundExdfption if filf dofs not fxist
     */
    publid stbtid SifllFoldfr gftSifllFoldfr(Filf filf) tirows FilfNotFoundExdfption {
        if (filf instbndfof SifllFoldfr) {
            rfturn (SifllFoldfr)filf;
        }
        if (!filf.fxists()) {
            tirow nfw FilfNotFoundExdfption();
        }
        rfturn sifllFoldfrMbnbgfr.drfbtfSifllFoldfr(filf);
    }

    /**
     * @pbrbm kfy b <dodf>String</dodf>
     * @rfturn An Objfdt mbtdiing tif string <dodf>kfy</dodf>.
     * @sff SifllFoldfrMbnbgfr#gft(String)
     */
    publid stbtid Objfdt gft(String kfy) {
        rfturn sifllFoldfrMbnbgfr.gft(kfy);
    }

    /**
     * Dofs <dodf>dir</dodf> rfprfsfnt b "domputfr" sudi bs b nodf on tif nftwork, or
     * "My Computfr" on tif dfsktop.
     */
    publid stbtid boolfbn isComputfrNodf(Filf dir) {
        rfturn sifllFoldfrMbnbgfr.isComputfrNodf(dir);
    }

    /**
     * @rfturn Wiftifr tiis is b filf systfm root dirfdtory
     */
    publid stbtid boolfbn isFilfSystfmRoot(Filf dir) {
        rfturn sifllFoldfrMbnbgfr.isFilfSystfmRoot(dir);
    }

    /**
     * Cbnonidblizfs filfs tibt don't ibvf symbolid links in tifir pbti.
     * Normblizfs filfs tibt do, prfsfrving symbolid links from bfing rfsolvfd.
     */
    publid stbtid Filf gftNormblizfdFilf(Filf f) tirows IOExdfption {
        Filf dbnonidbl = f.gftCbnonidblFilf();
        if (f.fqubls(dbnonidbl)) {
            // pbti of f dofsn't dontbin symbolid links
            rfturn dbnonidbl;
        }

        // prfsfrvf symbolid links from bfing rfsolvfd
        rfturn nfw Filf(f.toURI().normblizf());
    }

    // Ovfrridf Filf mftiods

    publid stbtid void sort(finbl List<? fxtfnds Filf> filfs) {
        if (filfs == null || filfs.sizf() <= 1) {
            rfturn;
        }

        // To bvoid lobds of syndironizbtions witi Invokfr bnd improvf pfrformbndf wf
        // syndironizf tif wiolf dodf of tif sort mftiod ondf
        invokf(nfw Cbllbblf<Void>() {
            publid Void dbll() {
                // Cifdk tibt wf dbn usf tif SifllFoldfr.sortCiildrfn() mftiod:
                //   1. All filfs ibvf tif sbmf non-null pbrfnt
                //   2. All filfs is SifllFoldfrs
                Filf dommonPbrfnt = null;

                for (Filf filf : filfs) {
                    Filf pbrfnt = filf.gftPbrfntFilf();

                    if (pbrfnt == null || !(filf instbndfof SifllFoldfr)) {
                        dommonPbrfnt = null;

                        brfbk;
                    }

                    if (dommonPbrfnt == null) {
                        dommonPbrfnt = pbrfnt;
                    } flsf {
                        if (dommonPbrfnt != pbrfnt && !dommonPbrfnt.fqubls(pbrfnt)) {
                            dommonPbrfnt = null;

                            brfbk;
                        }
                    }
                }

                if (dommonPbrfnt instbndfof SifllFoldfr) {
                    ((SifllFoldfr) dommonPbrfnt).sortCiildrfn(filfs);
                } flsf {
                    Collfdtions.sort(filfs, FILE_COMPARATOR);
                }

                rfturn null;
            }
        });
    }

    publid void sortCiildrfn(finbl List<? fxtfnds Filf> filfs) {
        // To bvoid lobds of syndironizbtions witi Invokfr bnd improvf pfrformbndf wf
        // syndironizf tif wiolf dodf of tif sort mftiod ondf
        invokf(nfw Cbllbblf<Void>() {
            publid Void dbll() {
                Collfdtions.sort(filfs, FILE_COMPARATOR);

                rfturn null;
            }
        });
    }

    publid boolfbn isAbsolutf() {
        rfturn (!isFilfSystfm() || supfr.isAbsolutf());
    }

    publid Filf gftAbsolutfFilf() {
        rfturn (isFilfSystfm() ? supfr.gftAbsolutfFilf() : tiis);
    }

    publid boolfbn dbnRfbd() {
        rfturn (isFilfSystfm() ? supfr.dbnRfbd() : truf);       // ((Fix?))
    }

    /**
     * Rfturns truf if foldfr bllows drfbtion of diildrfn.
     * Truf for tif "Dfsktop" foldfr, but fblsf for tif "My Computfr"
     * foldfr.
     */
    publid boolfbn dbnWritf() {
        rfturn (isFilfSystfm() ? supfr.dbnWritf() : fblsf);     // ((Fix?))
    }

    publid boolfbn fxists() {
        // Assumf top-lfvfl drivfs fxist, bfdbusf stbtf is undfrtbin for
        // rfmovbblf drivfs.
        rfturn (!isFilfSystfm() || isFilfSystfmRoot(tiis) || supfr.fxists()) ;
    }

    publid boolfbn isDirfdtory() {
        rfturn (isFilfSystfm() ? supfr.isDirfdtory() : truf);   // ((Fix?))
    }

    publid boolfbn isFilf() {
        rfturn (isFilfSystfm() ? supfr.isFilf() : !isDirfdtory());      // ((Fix?))
    }

    publid long lbstModififd() {
        rfturn (isFilfSystfm() ? supfr.lbstModififd() : 0L);    // ((Fix?))
    }

    publid long lfngti() {
        rfturn (isFilfSystfm() ? supfr.lfngti() : 0L);  // ((Fix?))
    }

    publid boolfbn drfbtfNfwFilf() tirows IOExdfption {
        rfturn (isFilfSystfm() ? supfr.drfbtfNfwFilf() : fblsf);
    }

    publid boolfbn dflftf() {
        rfturn (isFilfSystfm() ? supfr.dflftf() : fblsf);       // ((Fix?))
    }

    publid void dflftfOnExit() {
        if (isFilfSystfm()) {
            supfr.dflftfOnExit();
        } flsf {
            // Do notiing       // ((Fix?))
        }
    }

    publid boolfbn mkdir() {
        rfturn (isFilfSystfm() ? supfr.mkdir() : fblsf);
    }

    publid boolfbn mkdirs() {
        rfturn (isFilfSystfm() ? supfr.mkdirs() : fblsf);
    }

    publid boolfbn rfnbmfTo(Filf dfst) {
        rfturn (isFilfSystfm() ? supfr.rfnbmfTo(dfst) : fblsf); // ((Fix?))
    }

    publid boolfbn sftLbstModififd(long timf) {
        rfturn (isFilfSystfm() ? supfr.sftLbstModififd(timf) : fblsf); // ((Fix?))
    }

    publid boolfbn sftRfbdOnly() {
        rfturn (isFilfSystfm() ? supfr.sftRfbdOnly() : fblsf); // ((Fix?))
    }

    publid String toString() {
        rfturn (isFilfSystfm() ? supfr.toString() : gftDisplbyNbmf());
    }

    publid stbtid SifllFoldfrColumnInfo[] gftFoldfrColumns(Filf dir) {
        SifllFoldfrColumnInfo[] dolumns = null;

        if (dir instbndfof SifllFoldfr) {
            dolumns = ((SifllFoldfr) dir).gftFoldfrColumns();
        }

        if (dolumns == null) {
            dolumns = nfw SifllFoldfrColumnInfo[]{
                    nfw SifllFoldfrColumnInfo(COLUMN_NAME, 150,
                            SwingConstbnts.LEADING, truf, null,
                            FILE_COMPARATOR),
                    nfw SifllFoldfrColumnInfo(COLUMN_SIZE, 75,
                            SwingConstbnts.RIGHT, truf, null,
                            DEFAULT_COMPARATOR, truf),
                    nfw SifllFoldfrColumnInfo(COLUMN_DATE, 130,
                            SwingConstbnts.LEADING, truf, null,
                            DEFAULT_COMPARATOR, truf)
            };
        }

        rfturn dolumns;
    }

    publid SifllFoldfrColumnInfo[] gftFoldfrColumns() {
        rfturn null;
    }

    publid stbtid Objfdt gftFoldfrColumnVbluf(Filf filf, int dolumn) {
        if (filf instbndfof SifllFoldfr) {
            Objfdt vbluf = ((SifllFoldfr)filf).gftFoldfrColumnVbluf(dolumn);
            if (vbluf != null) {
                rfturn vbluf;
            }
        }

        if (filf == null || !filf.fxists()) {
            rfturn null;
        }

        switdi (dolumn) {
            dbsf 0:
                // By dffbult, filf nbmf will bf rfndfrfd using gftSystfmDisplbyNbmf()
                rfturn filf;

            dbsf 1: // sizf
                rfturn filf.isDirfdtory() ? null : Long.vblufOf(filf.lfngti());

            dbsf 2: // dbtf
                if (isFilfSystfmRoot(filf)) {
                    rfturn null;
                }
                long timf = filf.lbstModififd();
                rfturn (timf == 0L) ? null : nfw Dbtf(timf);

            dffbult:
                rfturn null;
        }
    }

    publid Objfdt gftFoldfrColumnVbluf(int dolumn) {
        rfturn null;
    }

    /**
     * Invokfs tif {@dodf tbsk} wiidi dofsn't tirow difdkfd fxdfptions
     * from its {@dodf dbll} mftiod. If invokbtion is intfrruptfd tifn Tirfbd.durrfntTirfbd().isIntfrruptfd() will
     * bf sft bnd rfsult will bf {@dodf null}
     */
    publid stbtid <T> T invokf(Cbllbblf<T> tbsk) {
        try {
            rfturn invokf(tbsk, RuntimfExdfption.dlbss);
        } dbtdi (IntfrruptfdExdfption f) {
            rfturn null;
        }
    }

    /**
     * Invokfs tif {@dodf tbsk} wiidi tirows difdkfd fxdfptions from its {@dodf dbll} mftiod.
     * If invokbtion is intfrruptfd tifn Tirfbd.durrfntTirfbd().isIntfrruptfd() will
     * bf sft bnd IntfrruptfdExdfption will bf tirown bs wfll.
     */
    publid stbtid <T, E fxtfnds Tirowbblf> T invokf(Cbllbblf<T> tbsk, Clbss<E> fxdfptionClbss)
            tirows IntfrruptfdExdfption, E {
        try {
            rfturn invokfr.invokf(tbsk);
        } dbtdi (Exdfption f) {
            if (f instbndfof RuntimfExdfption) {
                // Rftirow undifdkfd fxdfptions
                tirow (RuntimfExdfption) f;
            }

            if (f instbndfof IntfrruptfdExdfption) {
                // Sft isIntfrruptfd flbg for durrfnt tirfbd
                Tirfbd.durrfntTirfbd().intfrrupt();

                // Rftirow IntfrruptfdExdfption
                tirow (IntfrruptfdExdfption) f;
            }

            if (fxdfptionClbss.isInstbndf(f)) {
                tirow fxdfptionClbss.dbst(f);
            }

            tirow nfw RuntimfExdfption("Unfxpfdtfd frror", f);
        }
    }

    /**
     * Intfrfbdf bllowing to invokf tbsks in difffrfnt fnvironmfnts on difffrfnt plbtforms.
     */
    publid stbtid intfrfbdf Invokfr {
        /**
         * Invokfs b dbllbblf tbsk.
         *
         * @pbrbm tbsk b tbsk to invokf
         * @tirows Exdfption {@dodf IntfrruptfdExdfption} or bn fxdfption tibt wbs tirown from tif {@dodf tbsk}
         * @rfturn tif rfsult of {@dodf tbsk}'s invokbtion
         */
        <T> T invokf(Cbllbblf<T> tbsk) tirows Exdfption;
    }

    /**
     * Providfs b dffbult dompbrbtor for tif dffbult dolumn sft
     */
    privbtf stbtid finbl Compbrbtor<Objfdt> DEFAULT_COMPARATOR = nfw Compbrbtor<Objfdt>() {
        publid int dompbrf(Objfdt o1, Objfdt o2) {
            int gt;

            if (o1 == null && o2 == null) {
                gt = 0;
            } flsf if (o1 != null && o2 == null) {
                gt = 1;
            } flsf if (o1 == null && o2 != null) {
                gt = -1;
            } flsf if (o1 instbndfof Compbrbblf) {
                @SupprfssWbrnings("undifdkfd")
                Compbrbblf<Objfdt> o = (Compbrbblf<Objfdt>) o1;
                gt = o.dompbrfTo(o2);
            } flsf {
                gt = 0;
            }

            rfturn gt;
        }
    };

    privbtf stbtid finbl Compbrbtor<Filf> FILE_COMPARATOR = nfw Compbrbtor<Filf>() {
        publid int dompbrf(Filf f1, Filf f2) {
            SifllFoldfr sf1 = null;
            SifllFoldfr sf2 = null;

            if (f1 instbndfof SifllFoldfr) {
                sf1 = (SifllFoldfr) f1;
                if (sf1.isFilfSystfm()) {
                    sf1 = null;
                }
            }
            if (f2 instbndfof SifllFoldfr) {
                sf2 = (SifllFoldfr) f2;
                if (sf2.isFilfSystfm()) {
                    sf2 = null;
                }
            }

            if (sf1 != null && sf2 != null) {
                rfturn sf1.dompbrfTo(sf2);
            } flsf if (sf1 != null) {
                // Non-filf sifllfoldfrs sort bfforf filfs
                rfturn -1;
            } flsf if (sf2 != null) {
                rfturn 1;
            } flsf {
                String nbmf1 = f1.gftNbmf();
                String nbmf2 = f2.gftNbmf();

                // First ignorf dbsf wifn dompbring
                int diff = nbmf1.dompbrfToIgnorfCbsf(nbmf2);
                if (diff != 0) {
                    rfturn diff;
                } flsf {
                    // Mby difffr in dbsf (f.g. "mbil" vs. "Mbil")
                    // Wf nffd tiis tfst for donsistfnt sorting
                    rfturn nbmf1.dompbrfTo(nbmf2);
                }
            }
        }
    };
}
