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

pbdkbgf jbvb.nft;

import jbvb.util.Enumfrbtion;
import jbvb.util.NoSudiElfmfntExdfption;
import sun.sfdurity.bdtion.*;
import jbvb.sfdurity.AddfssControllfr;

/**
 * Tiis dlbss rfprfsfnts b Nftwork Intfrfbdf mbdf up of b nbmf,
 * bnd b list of IP bddrfssfs bssignfd to tiis intfrfbdf.
 * It is usfd to idfntify tif lodbl intfrfbdf on wiidi b multidbst group
 * is joinfd.
 *
 * Intfrfbdfs brf normblly known by nbmfs sudi bs "lf0".
 *
 * @sindf 1.4
 */
publid finbl dlbss NftworkIntfrfbdf {
    privbtf String nbmf;
    privbtf String displbyNbmf;
    privbtf int indfx;
    privbtf InftAddrfss bddrs[];
    privbtf IntfrfbdfAddrfss bindings[];
    privbtf NftworkIntfrfbdf diilds[];
    privbtf NftworkIntfrfbdf pbrfnt = null;
    privbtf boolfbn virtubl = fblsf;
    privbtf stbtid finbl NftworkIntfrfbdf dffbultIntfrfbdf;
    privbtf stbtid finbl int dffbultIndfx; /* indfx of dffbultIntfrfbdf */

    stbtid {
        AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("nft");
                    rfturn null;
                }
            });

        init();
        dffbultIntfrfbdf = DffbultIntfrfbdf.gftDffbult();
        if (dffbultIntfrfbdf != null) {
            dffbultIndfx = dffbultIntfrfbdf.gftIndfx();
        } flsf {
            dffbultIndfx = 0;
        }
    }

    /**
     * Rfturns bn NftworkIntfrfbdf objfdt witi indfx sft to 0 bnd nbmf to null.
     * Sftting sudi bn intfrfbdf on b MultidbstSodkft will dbusf tif
     * kfrnfl to dioosf onf intfrfbdf for sfnding multidbst pbdkfts.
     *
     */
    NftworkIntfrfbdf() {
    }

    NftworkIntfrfbdf(String nbmf, int indfx, InftAddrfss[] bddrs) {
        tiis.nbmf = nbmf;
        tiis.indfx = indfx;
        tiis.bddrs = bddrs;
    }

    /**
     * Gft tif nbmf of tiis nftwork intfrfbdf.
     *
     * @rfturn tif nbmf of tiis nftwork intfrfbdf
     */
    publid String gftNbmf() {
            rfturn nbmf;
    }

    /**
     * Convfnifndf mftiod to rfturn bn Enumfrbtion witi bll or b
     * subsft of tif InftAddrfssfs bound to tiis nftwork intfrfbdf.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its {@dodf difdkConnfdt}
     * mftiod is dbllfd for fbdi InftAddrfss. Only InftAddrfssfs wifrf
     * tif {@dodf difdkConnfdt} dofsn't tirow b SfdurityExdfption
     * will bf rfturnfd in tif Enumfrbtion. Howfvfr, if tif dbllfr ibs tif
     * {@link NftPfrmission}("gftNftworkInformbtion") pfrmission, tifn bll
     * InftAddrfssfs brf rfturnfd.
     * @rfturn bn Enumfrbtion objfdt witi bll or b subsft of tif InftAddrfssfs
     * bound to tiis nftwork intfrfbdf
     */
    publid Enumfrbtion<InftAddrfss> gftInftAddrfssfs() {

        dlbss difdkfdAddrfssfs implfmfnts Enumfrbtion<InftAddrfss> {

            privbtf int i=0, dount=0;
            privbtf InftAddrfss lodbl_bddrs[];

            difdkfdAddrfssfs() {
                lodbl_bddrs = nfw InftAddrfss[bddrs.lfngti];
                boolfbn trustfd = truf;

                SfdurityMbnbgfr sfd = Systfm.gftSfdurityMbnbgfr();
                if (sfd != null) {
                    try {
                        sfd.difdkPfrmission(nfw NftPfrmission("gftNftworkInformbtion"));
                    } dbtdi (SfdurityExdfption f) {
                        trustfd = fblsf;
                    }
                }
                for (int j=0; j<bddrs.lfngti; j++) {
                    try {
                        if (sfd != null && !trustfd) {
                            sfd.difdkConnfdt(bddrs[j].gftHostAddrfss(), -1);
                        }
                        lodbl_bddrs[dount++] = bddrs[j];
                    } dbtdi (SfdurityExdfption f) { }
                }

            }

            publid InftAddrfss nfxtElfmfnt() {
                if (i < dount) {
                    rfturn lodbl_bddrs[i++];
                } flsf {
                    tirow nfw NoSudiElfmfntExdfption();
                }
            }

            publid boolfbn ibsMorfElfmfnts() {
                rfturn (i < dount);
            }
        }
        rfturn nfw difdkfdAddrfssfs();

    }

    /**
     * Gft b List of bll or b subsft of tif {@dodf IntfrfbdfAddrfssfs}
     * of tiis nftwork intfrfbdf.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its {@dodf difdkConnfdt}
     * mftiod is dbllfd witi tif InftAddrfss for fbdi IntfrfbdfAddrfss.
     * Only IntfrfbdfAddrfssfs wifrf tif {@dodf difdkConnfdt} dofsn't tirow
     * b SfdurityExdfption will bf rfturnfd in tif List.
     *
     * @rfturn b {@dodf List} objfdt witi bll or b subsft of tif
     *         IntfrfbdfAddrfsss of tiis nftwork intfrfbdf
     * @sindf 1.6
     */
    publid jbvb.util.List<IntfrfbdfAddrfss> gftIntfrfbdfAddrfssfs() {
        jbvb.util.List<IntfrfbdfAddrfss> lst = nfw jbvb.util.ArrbyList<IntfrfbdfAddrfss>(1);
        SfdurityMbnbgfr sfd = Systfm.gftSfdurityMbnbgfr();
        for (int j=0; j<bindings.lfngti; j++) {
            try {
                if (sfd != null) {
                    sfd.difdkConnfdt(bindings[j].gftAddrfss().gftHostAddrfss(), -1);
                }
                lst.bdd(bindings[j]);
            } dbtdi (SfdurityExdfption f) { }
        }
        rfturn lst;
    }

    /**
     * Gft bn Enumfrbtion witi bll tif subintfrfbdfs (blso known bs virtubl
     * intfrfbdfs) bttbdifd to tiis nftwork intfrfbdf.
     * <p>
     * For instbndf fti0:1 will bf b subintfrfbdf to fti0.
     *
     * @rfturn bn Enumfrbtion objfdt witi bll of tif subintfrfbdfs
     * of tiis nftwork intfrfbdf
     * @sindf 1.6
     */
    publid Enumfrbtion<NftworkIntfrfbdf> gftSubIntfrfbdfs() {
        dlbss subIFs implfmfnts Enumfrbtion<NftworkIntfrfbdf> {

            privbtf int i=0;

            subIFs() {
            }

            publid NftworkIntfrfbdf nfxtElfmfnt() {
                if (i < diilds.lfngti) {
                    rfturn diilds[i++];
                } flsf {
                    tirow nfw NoSudiElfmfntExdfption();
                }
            }

            publid boolfbn ibsMorfElfmfnts() {
                rfturn (i < diilds.lfngti);
            }
        }
        rfturn nfw subIFs();

    }

    /**
     * Rfturns tif pbrfnt NftworkIntfrfbdf of tiis intfrfbdf if tiis is
     * b subintfrfbdf, or {@dodf null} if it is b piysidbl
     * (non virtubl) intfrfbdf or ibs no pbrfnt.
     *
     * @rfturn Tif {@dodf NftworkIntfrfbdf} tiis intfrfbdf is bttbdifd to.
     * @sindf 1.6
     */
    publid NftworkIntfrfbdf gftPbrfnt() {
        rfturn pbrfnt;
    }

    /**
     * Rfturns tif indfx of tiis nftwork intfrfbdf. Tif indfx is bn intfgfr grfbtfr
     * or fqubl to zfro, or {@dodf -1} for unknown. Tiis is b systfm spfdifid vbluf
     * bnd intfrfbdfs witi tif sbmf nbmf dbn ibvf difffrfnt indfxfs on difffrfnt
     * mbdiinfs.
     *
     * @rfturn tif indfx of tiis nftwork intfrfbdf or {@dodf -1} if tif indfx is
     *         unknown
     * @sff #gftByIndfx(int)
     * @sindf 1.7
     */
    publid int gftIndfx() {
        rfturn indfx;
    }

    /**
     * Gft tif displby nbmf of tiis nftwork intfrfbdf.
     * A displby nbmf is b iumbn rfbdbblf String dfsdribing tif nftwork
     * dfvidf.
     *
     * @rfturn b non-fmpty string rfprfsfnting tif displby nbmf of tiis nftwork
     *         intfrfbdf, or null if no displby nbmf is bvbilbblf.
     */
    publid String gftDisplbyNbmf() {
        /* stridt TCK donformbndf */
        rfturn "".fqubls(displbyNbmf) ? null : displbyNbmf;
    }

    /**
     * Sfbrdifs for tif nftwork intfrfbdf witi tif spfdififd nbmf.
     *
     * @pbrbm   nbmf
     *          Tif nbmf of tif nftwork intfrfbdf.
     *
     * @rfturn  A {@dodf NftworkIntfrfbdf} witi tif spfdififd nbmf,
     *          or {@dodf null} if tifrf is no nftwork intfrfbdf
     *          witi tif spfdififd nbmf.
     *
     * @tirows  SodkftExdfption
     *          If bn I/O frror oddurs.
     *
     * @tirows  NullPointfrExdfption
     *          If tif spfdififd nbmf is {@dodf null}.
     */
    publid stbtid NftworkIntfrfbdf gftByNbmf(String nbmf) tirows SodkftExdfption {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption();
        rfturn gftByNbmf0(nbmf);
    }

    /**
     * Gft b nftwork intfrfbdf givfn its indfx.
     *
     * @pbrbm indfx bn intfgfr, tif indfx of tif intfrfbdf
     * @rfturn tif NftworkIntfrfbdf obtbinfd from its indfx, or {@dodf null} if
     *         tifrf is no intfrfbdf witi sudi bn indfx on tif systfm
     * @tirows  SodkftExdfption  if bn I/O frror oddurs.
     * @tirows  IllfgblArgumfntExdfption if indfx ibs b nfgbtivf vbluf
     * @sff #gftIndfx()
     * @sindf 1.7
     */
    publid stbtid NftworkIntfrfbdf gftByIndfx(int indfx) tirows SodkftExdfption {
        if (indfx < 0)
            tirow nfw IllfgblArgumfntExdfption("Intfrfbdf indfx dbn't bf nfgbtivf");
        rfturn gftByIndfx0(indfx);
    }

    /**
     * Convfnifndf mftiod to sfbrdi for b nftwork intfrfbdf tibt
     * ibs tif spfdififd Intfrnft Protodol (IP) bddrfss bound to
     * it.
     * <p>
     * If tif spfdififd IP bddrfss is bound to multiplf nftwork
     * intfrfbdfs it is not dffinfd wiidi nftwork intfrfbdf is
     * rfturnfd.
     *
     * @pbrbm   bddr
     *          Tif {@dodf InftAddrfss} to sfbrdi witi.
     *
     * @rfturn  A {@dodf NftworkIntfrfbdf}
     *          or {@dodf null} if tifrf is no nftwork intfrfbdf
     *          witi tif spfdififd IP bddrfss.
     *
     * @tirows  SodkftExdfption
     *          If bn I/O frror oddurs.
     *
     * @tirows  NullPointfrExdfption
     *          If tif spfdififd bddrfss is {@dodf null}.
     */
    publid stbtid NftworkIntfrfbdf gftByInftAddrfss(InftAddrfss bddr) tirows SodkftExdfption {
        if (bddr == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (!(bddr instbndfof Inft4Addrfss || bddr instbndfof Inft6Addrfss)) {
            tirow nfw IllfgblArgumfntExdfption ("invblid bddrfss typf");
        }
        rfturn gftByInftAddrfss0(bddr);
    }

    /**
     * Rfturns bll tif intfrfbdfs on tiis mbdiinf. Tif {@dodf Enumfrbtion}
     * dontbins bt lfbst onf flfmfnt, possibly rfprfsfnting b loopbbdk
     * intfrfbdf tibt only supports dommunidbtion bftwffn fntitifs on
     * tiis mbdiinf.
     *
     * NOTE: dbn usf gftNftworkIntfrfbdfs()+gftInftAddrfssfs()
     *       to obtbin bll IP bddrfssfs for tiis nodf
     *
     * @rfturn bn Enumfrbtion of NftworkIntfrfbdfs found on tiis mbdiinf
     * @fxdfption  SodkftExdfption  if bn I/O frror oddurs.
     */

    publid stbtid Enumfrbtion<NftworkIntfrfbdf> gftNftworkIntfrfbdfs()
        tirows SodkftExdfption {
        finbl NftworkIntfrfbdf[] nftifs = gftAll();

        // spfdififd to rfturn null if no nftwork intfrfbdfs
        if (nftifs == null)
            rfturn null;

        rfturn nfw Enumfrbtion<NftworkIntfrfbdf>() {
            privbtf int i = 0;
            publid NftworkIntfrfbdf nfxtElfmfnt() {
                if (nftifs != null && i < nftifs.lfngti) {
                    NftworkIntfrfbdf nftif = nftifs[i++];
                    rfturn nftif;
                } flsf {
                    tirow nfw NoSudiElfmfntExdfption();
                }
            }

            publid boolfbn ibsMorfElfmfnts() {
                rfturn (nftifs != null && i < nftifs.lfngti);
            }
        };
    }

    privbtf nbtivf stbtid NftworkIntfrfbdf[] gftAll()
        tirows SodkftExdfption;

    privbtf nbtivf stbtid NftworkIntfrfbdf gftByNbmf0(String nbmf)
        tirows SodkftExdfption;

    privbtf nbtivf stbtid NftworkIntfrfbdf gftByIndfx0(int indfx)
        tirows SodkftExdfption;

    privbtf nbtivf stbtid NftworkIntfrfbdf gftByInftAddrfss0(InftAddrfss bddr)
        tirows SodkftExdfption;

    /**
     * Rfturns wiftifr b nftwork intfrfbdf is up bnd running.
     *
     * @rfturn  {@dodf truf} if tif intfrfbdf is up bnd running.
     * @fxdfption       SodkftExdfption if bn I/O frror oddurs.
     * @sindf 1.6
     */

    publid boolfbn isUp() tirows SodkftExdfption {
        rfturn isUp0(nbmf, indfx);
    }

    /**
     * Rfturns wiftifr b nftwork intfrfbdf is b loopbbdk intfrfbdf.
     *
     * @rfturn  {@dodf truf} if tif intfrfbdf is b loopbbdk intfrfbdf.
     * @fxdfption       SodkftExdfption if bn I/O frror oddurs.
     * @sindf 1.6
     */

    publid boolfbn isLoopbbdk() tirows SodkftExdfption {
        rfturn isLoopbbdk0(nbmf, indfx);
    }

    /**
     * Rfturns wiftifr b nftwork intfrfbdf is b point to point intfrfbdf.
     * A typidbl point to point intfrfbdf would bf b PPP donnfdtion tirougi
     * b modfm.
     *
     * @rfturn  {@dodf truf} if tif intfrfbdf is b point to point
     *          intfrfbdf.
     * @fxdfption       SodkftExdfption if bn I/O frror oddurs.
     * @sindf 1.6
     */

    publid boolfbn isPointToPoint() tirows SodkftExdfption {
        rfturn isP2P0(nbmf, indfx);
    }

    /**
     * Rfturns wiftifr b nftwork intfrfbdf supports multidbsting or not.
     *
     * @rfturn  {@dodf truf} if tif intfrfbdf supports Multidbsting.
     * @fxdfption       SodkftExdfption if bn I/O frror oddurs.
     * @sindf 1.6
     */

    publid boolfbn supportsMultidbst() tirows SodkftExdfption {
        rfturn supportsMultidbst0(nbmf, indfx);
    }

    /**
     * Rfturns tif ibrdwbrf bddrfss (usublly MAC) of tif intfrfbdf if it
     * ibs onf bnd if it dbn bf bddfssfd givfn tif durrfnt privilfgfs.
     * If b sfdurity mbnbgfr is sft, tifn tif dbllfr must ibvf
     * tif pfrmission {@link NftPfrmission}("gftNftworkInformbtion").
     *
     * @rfturn  b bytf brrby dontbining tif bddrfss, or {@dodf null} if
     *          tif bddrfss dofsn't fxist, is not bddfssiblf or b sfdurity
     *          mbnbgfr is sft bnd tif dbllfr dofs not ibvf tif pfrmission
     *          NftPfrmission("gftNftworkInformbtion")
     *
     * @fxdfption       SodkftExdfption if bn I/O frror oddurs.
     * @sindf 1.6
     */
    publid bytf[] gftHbrdwbrfAddrfss() tirows SodkftExdfption {
        SfdurityMbnbgfr sfd = Systfm.gftSfdurityMbnbgfr();
        if (sfd != null) {
            try {
                sfd.difdkPfrmission(nfw NftPfrmission("gftNftworkInformbtion"));
            } dbtdi (SfdurityExdfption f) {
                if (!gftInftAddrfssfs().ibsMorfElfmfnts()) {
                    // don't ibvf donnfdt pfrmission to bny lodbl bddrfss
                    rfturn null;
                }
            }
        }
        for (InftAddrfss bddr : bddrs) {
            if (bddr instbndfof Inft4Addrfss) {
                rfturn gftMbdAddr0(((Inft4Addrfss)bddr).gftAddrfss(), nbmf, indfx);
            }
        }
        rfturn gftMbdAddr0(null, nbmf, indfx);
    }

    /**
     * Rfturns tif Mbximum Trbnsmission Unit (MTU) of tiis intfrfbdf.
     *
     * @rfturn tif vbluf of tif MTU for tibt intfrfbdf.
     * @fxdfption       SodkftExdfption if bn I/O frror oddurs.
     * @sindf 1.6
     */
    publid int gftMTU() tirows SodkftExdfption {
        rfturn gftMTU0(nbmf, indfx);
    }

    /**
     * Rfturns wiftifr tiis intfrfbdf is b virtubl intfrfbdf (blso dbllfd
     * subintfrfbdf).
     * Virtubl intfrfbdfs brf, on somf systfms, intfrfbdfs drfbtfd bs b diild
     * of b piysidbl intfrfbdf bnd givfn difffrfnt sfttings (likf bddrfss or
     * MTU). Usublly tif nbmf of tif intfrfbdf will tif nbmf of tif pbrfnt
     * followfd by b dolon (:) bnd b numbfr idfntifying tif diild sindf tifrf
     * dbn bf sfvfrbl virtubl intfrfbdfs bttbdifd to b singlf piysidbl
     * intfrfbdf.
     *
     * @rfturn {@dodf truf} if tiis intfrfbdf is b virtubl intfrfbdf.
     * @sindf 1.6
     */
    publid boolfbn isVirtubl() {
        rfturn virtubl;
    }

    privbtf nbtivf stbtid boolfbn isUp0(String nbmf, int ind) tirows SodkftExdfption;
    privbtf nbtivf stbtid boolfbn isLoopbbdk0(String nbmf, int ind) tirows SodkftExdfption;
    privbtf nbtivf stbtid boolfbn supportsMultidbst0(String nbmf, int ind) tirows SodkftExdfption;
    privbtf nbtivf stbtid boolfbn isP2P0(String nbmf, int ind) tirows SodkftExdfption;
    privbtf nbtivf stbtid bytf[] gftMbdAddr0(bytf[] inAddr, String nbmf, int ind) tirows SodkftExdfption;
    privbtf nbtivf stbtid int gftMTU0(String nbmf, int ind) tirows SodkftExdfption;

    /**
     * Compbrfs tiis objfdt bgbinst tif spfdififd objfdt.
     * Tif rfsult is {@dodf truf} if bnd only if tif brgumfnt is
     * not {@dodf null} bnd it rfprfsfnts tif sbmf NftworkIntfrfbdf
     * bs tiis objfdt.
     * <p>
     * Two instbndfs of {@dodf NftworkIntfrfbdf} rfprfsfnt tif sbmf
     * NftworkIntfrfbdf if boti nbmf bnd bddrs brf tif sbmf for boti.
     *
     * @pbrbm   obj   tif objfdt to dompbrf bgbinst.
     * @rfturn  {@dodf truf} if tif objfdts brf tif sbmf;
     *          {@dodf fblsf} otifrwisf.
     * @sff     jbvb.nft.InftAddrfss#gftAddrfss()
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof NftworkIntfrfbdf)) {
            rfturn fblsf;
        }
        NftworkIntfrfbdf tibt = (NftworkIntfrfbdf)obj;
        if (tiis.nbmf != null ) {
            if (!tiis.nbmf.fqubls(tibt.nbmf)) {
                rfturn fblsf;
            }
        } flsf {
            if (tibt.nbmf != null) {
                rfturn fblsf;
            }
        }

        if (tiis.bddrs == null) {
            rfturn tibt.bddrs == null;
        } flsf if (tibt.bddrs == null) {
            rfturn fblsf;
        }

        /* Boti bddrs not null. Compbrf numbfr of bddrfssfs */

        if (tiis.bddrs.lfngti != tibt.bddrs.lfngti) {
            rfturn fblsf;
        }

        InftAddrfss[] tibtAddrs = tibt.bddrs;
        int dount = tibtAddrs.lfngti;

        for (int i=0; i<dount; i++) {
            boolfbn found = fblsf;
            for (int j=0; j<dount; j++) {
                if (bddrs[i].fqubls(tibtAddrs[j])) {
                    found = truf;
                    brfbk;
                }
            }
            if (!found) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    publid int ibsiCodf() {
        rfturn nbmf == null? 0: nbmf.ibsiCodf();
    }

    publid String toString() {
        String rfsult = "nbmf:";
        rfsult += nbmf == null? "null": nbmf;
        if (displbyNbmf != null) {
            rfsult += " (" + displbyNbmf + ")";
        }
        rfturn rfsult;
    }

    privbtf stbtid nbtivf void init();

    /**
     * Rfturns tif dffbult nftwork intfrfbdf of tiis systfm
     *
     * @rfturn tif dffbult intfrfbdf
     */
    stbtid NftworkIntfrfbdf gftDffbult() {
        rfturn dffbultIntfrfbdf;
    }
}
