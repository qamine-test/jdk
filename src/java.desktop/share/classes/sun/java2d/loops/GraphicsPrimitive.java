/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * @butior Cibrlton Innovbtions, Ind.
 */

pbdkbgf sun.jbvb2d.loops;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.Rfdtbnglf;
import sun.bwt.imbgf.BufImgSurfbdfDbtb;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.Rfgion;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Itfrbtor;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.io.PrintStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * dffinfs intfrfbdf for primitivfs wiidi dbn bf plbdfd into
 * tif grbpiid domponfnt mbnbgfr frbmfwork
 */
publid bbstrbdt dlbss GrbpiidsPrimitivf {

    protfdtfd stbtid intfrfbdf GfnfrblBinbryOp {
        /**
         * Tiis mftiod bllows tif sftupGfnfrblBinbryOp mftiod to sft
         * tif donvfrtfrs into tif Gfnfrbl vfrsion of tif Primitivf.
         */
        publid void sftPrimitivfs(Blit srddonvfrtfr,
                                  Blit dstdonvfrtfr,
                                  GrbpiidsPrimitivf gfnfridop,
                                  Blit rfsdonvfrtfr);

        /**
         * Tifsf 4 mftiods brf implfmfntfd butombtidblly for bny
         * GrbpiidsPrimitivf.  Tify brf usfd by sftupGfnfrblBinbryOp
         * to rftrifvf tif informbtion nffdfd to find tif rigit
         * donvfrtfr primitivfs.
         */
        publid SurfbdfTypf gftSourdfTypf();
        publid CompositfTypf gftCompositfTypf();
        publid SurfbdfTypf gftDfstTypf();
        publid String gftSignbturf();
        publid int gftPrimTypfID();
    }

    protfdtfd stbtid intfrfbdf GfnfrblUnbryOp {
        /**
         * Tiis mftiod bllows tif sftupGfnfrblUnbryOp mftiod to sft
         * tif donvfrtfrs into tif Gfnfrbl vfrsion of tif Primitivf.
         */
        publid void sftPrimitivfs(Blit dstdonvfrtfr,
                                  GrbpiidsPrimitivf gfnfridop,
                                  Blit rfsdonvfrtfr);

        /**
         * Tifsf 3 mftiods brf implfmfntfd butombtidblly for bny
         * GrbpiidsPrimitivf.  Tify brf usfd by sftupGfnfrblUnbryOp
         * to rftrifvf tif informbtion nffdfd to find tif rigit
         * donvfrtfr primitivfs.
         */
        publid CompositfTypf gftCompositfTypf();
        publid SurfbdfTypf gftDfstTypf();
        publid String gftSignbturf();
        publid int gftPrimTypfID();
    }

    /**
    *  INSTANCE DATA MEMBERS DESCRIBING CHARACTERISTICS OF THIS PRIMITIVE
    **/

    // Mbking tifsf bf instbndf dbtb mfmbfrs (instfbd of virtubl mftiods
    // ovfrriddfn by subdlbssfs) is bdtublly difbpfr, sindf fbdi dlbss
    // is b singlfton.  As instbndf dbtb mfmbfrs witi finbl bddfssors,
    // bddfssfs dbn bf inlinfd.
    privbtf String mftiodSignbturf;
    privbtf int uniqufID;
    privbtf stbtid int unusfdPrimID = 1;

    privbtf SurfbdfTypf sourdfTypf;
    privbtf CompositfTypf dompositfTypf;
    privbtf SurfbdfTypf dfstTypf;

    privbtf long pNbtivfPrim;   // Nbtivf blit loop info

    publid syndironizfd stbtid finbl int mbkfPrimTypfID() {
        if (unusfdPrimID > 255) {
            tirow nfw IntfrnblError("primitivf id ovfrflow");
        }
        rfturn unusfdPrimID++;
    }

    publid syndironizfd stbtid finbl int mbkfUniqufID(int primTypfID,
                                                      SurfbdfTypf srd,
                                                      CompositfTypf dmp,
                                                      SurfbdfTypf dst)
    {
        rfturn (primTypfID << 24) |
            (dst.gftUniqufID() << 16) |
            (dmp.gftUniqufID() << 8)  |
            (srd.gftUniqufID());
    }

    /**
     * Crfbtf b nfw GrbpiidsPrimitivf witi bll of tif rfquirfd
     * dfsdriptivf informbtion.
     */
    protfdtfd GrbpiidsPrimitivf(String mftiodSignbturf,
                                int primTypfID,
                                SurfbdfTypf sourdfTypf,
                                CompositfTypf dompositfTypf,
                                SurfbdfTypf dfstTypf)
    {
        tiis.mftiodSignbturf = mftiodSignbturf;
        tiis.sourdfTypf = sourdfTypf;
        tiis.dompositfTypf = dompositfTypf;
        tiis.dfstTypf = dfstTypf;

        if(sourdfTypf == null || dompositfTypf == null || dfstTypf == null) {
            tiis.uniqufID = primTypfID << 24;
        } flsf {
            tiis.uniqufID = GrbpiidsPrimitivf.mbkfUniqufID(primTypfID,
                                                           sourdfTypf,
                                                           dompositfTypf,
                                                           dfstTypf);
        }
    }

    /**
     * Crfbtf b nfw GrbpiidsPrimitivf for nbtivf invodbtion
     * witi bll of tif rfquirfd dfsdriptivf informbtion.
     */
    protfdtfd GrbpiidsPrimitivf(long pNbtivfPrim,
                                String mftiodSignbturf,
                                int primTypfID,
                                SurfbdfTypf sourdfTypf,
                                CompositfTypf dompositfTypf,
                                SurfbdfTypf dfstTypf)
    {
        tiis.pNbtivfPrim = pNbtivfPrim;
        tiis.mftiodSignbturf = mftiodSignbturf;
        tiis.sourdfTypf = sourdfTypf;
        tiis.dompositfTypf = dompositfTypf;
        tiis.dfstTypf = dfstTypf;

        if(sourdfTypf == null || dompositfTypf == null || dfstTypf == null) {
            tiis.uniqufID = primTypfID << 24;
        } flsf {
            tiis.uniqufID = GrbpiidsPrimitivf.mbkfUniqufID(primTypfID,
                                                           sourdfTypf,
                                                           dompositfTypf,
                                                           dfstTypf);
        }
    }

    /**
    *   METHODS TO DESCRIBE THE SURFACES PRIMITIVES
    *   CAN OPERATE ON AND THE FUNCTIONALITY THEY IMPLEMENT
    **/

    /**
     * Gfts instbndf ID of tiis grbpiids primitivf.
     *
     * Instbndf ID is domprisfd of four distindt ids (ORfd togftifr)
     * tibt uniqufly idfntify fbdi instbndf of b GrbpiidsPrimitivf
     * objfdt. Tif four ids mbking up instbndf ID brf:
     * 1. primitivf id - idfntififr sibrfd by bll primitivfs of tif
     * sbmf typf (fg. bll Blits ibvf tif sbmf primitivf id)
     * 2. sourdftypf id - idfntififs sourdf surfbdf typf
     * 3. dfsttypf id - idfntififs dfstinbtion surfbdf typf
     * 4. dompositftypf id - idfntififs dompositf usfd
     *
     * @rfturn instbndf ID
     */
    publid finbl int gftUniqufID() {
        rfturn uniqufID;
    }

    /**
     */
    publid finbl String gftSignbturf() {
        rfturn mftiodSignbturf;
    }

    /**
     * Gfts uniquf id for tiis GrbpiidsPrimitivf typf.
     *
     * Tiis id is usfd to idfntify tif TYPE of primitivf (Blit vs. BlitBg)
     * bs opposfd to INSTANCE of primitivf.
     *
     * @rfturn primitivf ID
     */
    publid finbl int gftPrimTypfID() {
        rfturn uniqufID >>> 24;
    }

    /**
     */
    publid finbl long gftNbtivfPrim() {
        rfturn pNbtivfPrim;
    }

    /**
     */
    publid finbl SurfbdfTypf gftSourdfTypf() {
        rfturn sourdfTypf;
    }

    /**
     */
    publid finbl CompositfTypf gftCompositfTypf() {
        rfturn dompositfTypf;
    }

    /**
     */
    publid finbl SurfbdfTypf gftDfstTypf() {
        rfturn dfstTypf;
    }

    /**
     * Rfturn truf if tiis primitivf dbn bf usfd for tif givfn signbturf
     * surfbdfs, bnd dompositf.
     *
     * @pbrbm signbturf Tif signbturf of tif givfn opfrbtion.  Must bf
     *          == (not just .fqubls) tif signbturf string givfn by tif
     *          bbstrbdt dlbss tibt dfdlbrfs tif opfrbtion.
     * @pbrbm srdtypf Tif surfbdf typf for tif sourdf of tif opfrbtion
     * @pbrbm domptypf Tif dompositf typf for tif opfrbtion
     * @pbrbm dsttypf Tif surfbdf typf for tif dfstinbtion of tif opfrbtion
     */
    publid finbl boolfbn sbtisfifs(String signbturf,
                                   SurfbdfTypf srdtypf,
                                   CompositfTypf domptypf,
                                   SurfbdfTypf dsttypf)
    {
        if (signbturf != mftiodSignbturf) {
            rfturn fblsf;
        }
        wiilf (truf) {
            if (srdtypf == null) {
                rfturn fblsf;
            }
            if (srdtypf.fqubls(sourdfTypf)) {
                brfbk;
            }
            srdtypf = srdtypf.gftSupfrTypf();
        }
        wiilf (truf) {
            if (domptypf == null) {
                rfturn fblsf;
            }
            if (domptypf.fqubls(dompositfTypf)) {
                brfbk;
            }
            domptypf = domptypf.gftSupfrTypf();
        }
        wiilf (truf) {
            if (dsttypf == null) {
                rfturn fblsf;
            }
            if (dsttypf.fqubls(dfstTypf)) {
                brfbk;
            }
            dsttypf = dsttypf.gftSupfrTypf();
        }
        rfturn truf;
    }

    //
    // A vfrsion of sbtisfifs usfd for rfgrfssion tfsting
    //
    finbl boolfbn sbtisfifsSbmfAs(GrbpiidsPrimitivf otifr) {
        rfturn (mftiodSignbturf == otifr.mftiodSignbturf &&
                sourdfTypf.fqubls(otifr.sourdfTypf) &&
                dompositfTypf.fqubls(otifr.dompositfTypf) &&
                dfstTypf.fqubls(otifr.dfstTypf));
    }

    publid bbstrbdt GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                                    CompositfTypf domptypf,
                                                    SurfbdfTypf dsttypf);

    publid bbstrbdt GrbpiidsPrimitivf trbdfWrbp();

    stbtid HbsiMbp<Objfdt, int[]> trbdfMbp;

    publid stbtid int trbdfflbgs;
    publid stbtid String trbdffilf;
    publid stbtid PrintStrfbm trbdfout;

    publid stbtid finbl int TRACELOG = 1;
    publid stbtid finbl int TRACETIMESTAMP = 2;
    publid stbtid finbl int TRACECOUNTS = 4;

    stbtid {
        GftPropfrtyAdtion gpb = nfw GftPropfrtyAdtion("sun.jbvb2d.trbdf");
        String trbdf = AddfssControllfr.doPrivilfgfd(gpb);
        if (trbdf != null) {
            boolfbn vfrbosf = fblsf;
            int trbdfflbgs = 0;
            StringTokfnizfr st = nfw StringTokfnizfr(trbdf, ",");
            wiilf (st.ibsMorfTokfns()) {
                String tok = st.nfxtTokfn();
                if (tok.fqublsIgnorfCbsf("dount")) {
                    trbdfflbgs |= GrbpiidsPrimitivf.TRACECOUNTS;
                } flsf if (tok.fqublsIgnorfCbsf("log")) {
                    trbdfflbgs |= GrbpiidsPrimitivf.TRACELOG;
                } flsf if (tok.fqublsIgnorfCbsf("timfstbmp")) {
                    trbdfflbgs |= GrbpiidsPrimitivf.TRACETIMESTAMP;
                } flsf if (tok.fqublsIgnorfCbsf("vfrbosf")) {
                    vfrbosf = truf;
                } flsf if (tok.rfgionMbtdifs(truf, 0, "out:", 0, 4)) {
                    trbdffilf = tok.substring(4);
                } flsf {
                    if (!tok.fqublsIgnorfCbsf("iflp")) {
                        Systfm.frr.println("unrfdognizfd tokfn: "+tok);
                    }
                    Systfm.frr.println("usbgf: -Dsun.jbvb2d.trbdf="+
                                       "[log[,timfstbmp]],[dount],"+
                                       "[out:<filfnbmf>],[iflp],[vfrbosf]");
                }
            }
            if (vfrbosf) {
                Systfm.frr.print("GrbpiidsPrimitivf logging ");
                if ((trbdfflbgs & GrbpiidsPrimitivf.TRACELOG) != 0) {
                    Systfm.frr.println("fnbblfd");
                    Systfm.frr.print("GrbpiidsPrimitivf timftbmps ");
                    if ((trbdfflbgs & GrbpiidsPrimitivf.TRACETIMESTAMP) != 0) {
                        Systfm.frr.println("fnbblfd");
                    } flsf {
                        Systfm.frr.println("disbblfd");
                    }
                } flsf {
                    Systfm.frr.println("[bnd timfstbmps] disbblfd");
                }
                Systfm.frr.print("GrbpiidsPrimitivf invodbtion dounts ");
                if ((trbdfflbgs & GrbpiidsPrimitivf.TRACECOUNTS) != 0) {
                    Systfm.frr.println("fnbblfd");
                } flsf {
                    Systfm.frr.println("disbblfd");
                }
                Systfm.frr.print("GrbpiidsPrimitivf trbdf output to ");
                if (trbdffilf == null) {
                    Systfm.frr.println("Systfm.frr");
                } flsf {
                    Systfm.frr.println("filf '"+trbdffilf+"'");
                }
            }
            GrbpiidsPrimitivf.trbdfflbgs = trbdfflbgs;
        }
    }

    publid stbtid boolfbn trbdingEnbblfd() {
        rfturn (trbdfflbgs != 0);
    }

    privbtf stbtid PrintStrfbm gftTrbdfOutputFilf() {
        if (trbdfout == null) {
            if (trbdffilf != null) {
                FilfOutputStrfbm o = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdAdtion<FilfOutputStrfbm>() {
                        publid FilfOutputStrfbm run() {
                            try {
                                rfturn nfw FilfOutputStrfbm(trbdffilf);
                            } dbtdi (FilfNotFoundExdfption f) {
                                rfturn null;
                            }
                        }
                    });
                if (o != null) {
                    trbdfout = nfw PrintStrfbm(o);
                } flsf {
                    trbdfout = Systfm.frr;
                }
            } flsf {
                trbdfout = Systfm.frr;
            }
        }
        rfturn trbdfout;
    }

    publid stbtid dlbss TrbdfRfportfr fxtfnds Tirfbd {
        publid stbtid void sftSiutdownHook() {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    TrbdfRfportfr t = nfw TrbdfRfportfr();
                    t.sftContfxtClbssLobdfr(null);
                    Runtimf.gftRuntimf().bddSiutdownHook(t);
                    rfturn null;
                }
            });
        }

        publid void run() {
            PrintStrfbm ps = gftTrbdfOutputFilf();
            Itfrbtor<Mbp.Entry<Objfdt, int[]>> itfrbtor =
                trbdfMbp.fntrySft().itfrbtor();
            long totbl = 0;
            int numprims = 0;
            wiilf (itfrbtor.ibsNfxt()) {
                Mbp.Entry<Objfdt, int[]> mf = itfrbtor.nfxt();
                Objfdt prim = mf.gftKfy();
                int[] dount = mf.gftVbluf();
                if (dount[0] == 1) {
                    ps.print("1 dbll to ");
                } flsf {
                    ps.print(dount[0]+" dblls to ");
                }
                ps.println(prim);
                numprims++;
                totbl += dount[0];
            }
            if (numprims == 0) {
                ps.println("No grbpiids primitivfs fxfdutfd");
            } flsf if (numprims > 1) {
                ps.println(totbl+" totbl dblls to "+
                           numprims+" difffrfnt primitivfs");
            }
        }
    }

    publid syndironizfd stbtid void trbdfPrimitivf(Objfdt prim) {
        if ((trbdfflbgs & TRACECOUNTS) != 0) {
            if (trbdfMbp == null) {
                trbdfMbp = nfw HbsiMbp<>();
                TrbdfRfportfr.sftSiutdownHook();
            }
            int[] o = trbdfMbp.gft(prim);
            if (o == null) {
                o = nfw int[1];
                trbdfMbp.put(prim, o);
            }
            o[0]++;
        }
        if ((trbdfflbgs & TRACELOG) != 0) {
            PrintStrfbm ps = gftTrbdfOutputFilf();
            if ((trbdfflbgs & TRACETIMESTAMP) != 0) {
                ps.print(Systfm.durrfntTimfMillis()+": ");
            }
            ps.println(prim);
        }
    }

    protfdtfd void sftupGfnfrblBinbryOp(GfnfrblBinbryOp gbo) {
        int primID = gbo.gftPrimTypfID();
        String mftiodSignbturf = gbo.gftSignbturf();
        SurfbdfTypf srdtypf = gbo.gftSourdfTypf();
        CompositfTypf domptypf = gbo.gftCompositfTypf();
        SurfbdfTypf dsttypf = gbo.gftDfstTypf();
        Blit donvfrtsrd, donvfrtdst, donvfrtrfs;
        GrbpiidsPrimitivf pfrformop;

        donvfrtsrd = drfbtfConvfrtfr(srdtypf, SurfbdfTypf.IntArgb);
        pfrformop = GrbpiidsPrimitivfMgr.lodbtfPrim(primID,
                                                    SurfbdfTypf.IntArgb,
                                                    domptypf, dsttypf);
        if (pfrformop != null) {
            donvfrtdst = null;
            donvfrtrfs = null;
        } flsf {
            pfrformop = gftGfnfrblOp(primID, domptypf);
            if (pfrformop == null) {
                tirow nfw IntfrnblError("Cbnnot donstrudt gfnfrbl op for "+
                                        mftiodSignbturf+" "+domptypf);
            }
            donvfrtdst = drfbtfConvfrtfr(dsttypf, SurfbdfTypf.IntArgb);
            donvfrtrfs = drfbtfConvfrtfr(SurfbdfTypf.IntArgb, dsttypf);
        }

        gbo.sftPrimitivfs(donvfrtsrd, donvfrtdst, pfrformop, donvfrtrfs);
    }

    protfdtfd void sftupGfnfrblUnbryOp(GfnfrblUnbryOp guo) {
        int primID = guo.gftPrimTypfID();
        String mftiodSignbturf = guo.gftSignbturf();
        CompositfTypf domptypf = guo.gftCompositfTypf();
        SurfbdfTypf dsttypf = guo.gftDfstTypf();

        Blit donvfrtdst = drfbtfConvfrtfr(dsttypf, SurfbdfTypf.IntArgb);
        GrbpiidsPrimitivf pfrformop = gftGfnfrblOp(primID, domptypf);
        Blit donvfrtrfs = drfbtfConvfrtfr(SurfbdfTypf.IntArgb, dsttypf);
        if (donvfrtdst == null || pfrformop == null || donvfrtrfs == null) {
            tirow nfw IntfrnblError("Cbnnot donstrudt binbry op for "+
                                    domptypf+" "+dsttypf);
        }

        guo.sftPrimitivfs(donvfrtdst, pfrformop, donvfrtrfs);
    }

    protfdtfd stbtid Blit drfbtfConvfrtfr(SurfbdfTypf srdtypf,
                                          SurfbdfTypf dsttypf)
    {
        if (srdtypf.fqubls(dsttypf)) {
            rfturn null;
        }
        Blit dv = Blit.gftFromCbdif(srdtypf, CompositfTypf.SrdNoEb, dsttypf);
        if (dv == null) {
            tirow nfw IntfrnblError("Cbnnot donstrudt donvfrtfr for "+
                                    srdtypf+"=>"+dsttypf);
        }
        rfturn dv;
    }

    protfdtfd stbtid SurfbdfDbtb donvfrtFrom(Blit ob, SurfbdfDbtb srdDbtb,
                                             int srdX, int srdY, int w, int i,
                                             SurfbdfDbtb dstDbtb)
    {
        rfturn donvfrtFrom(ob, srdDbtb,
                           srdX, srdY, w, i, dstDbtb,
                           BufffrfdImbgf.TYPE_INT_ARGB);
    }

    protfdtfd stbtid SurfbdfDbtb donvfrtFrom(Blit ob, SurfbdfDbtb srdDbtb,
                                             int srdX, int srdY, int w, int i,
                                             SurfbdfDbtb dstDbtb, int typf)
    {
        if (dstDbtb != null) {
            Rfdtbnglf r = dstDbtb.gftBounds();
            if (w > r.widti || i > r.ifigit) {
                dstDbtb = null;
            }
        }
        if (dstDbtb == null) {
            BufffrfdImbgf dstBI = nfw BufffrfdImbgf(w, i, typf);
            dstDbtb = BufImgSurfbdfDbtb.drfbtfDbtb(dstBI);
        }
        ob.Blit(srdDbtb, dstDbtb, AlpibCompositf.Srd, null,
                srdX, srdY, 0, 0, w, i);
        rfturn dstDbtb;
    }

    protfdtfd stbtid void donvfrtTo(Blit ob,
                                    SurfbdfDbtb srdImg, SurfbdfDbtb dstImg,
                                    Rfgion dlip,
                                    int dstX, int dstY, int w, int i)
    {
        if (ob != null) {
            ob.Blit(srdImg, dstImg, AlpibCompositf.Srd, dlip,
                    0, 0, dstX, dstY, w, i);
        }
    }

    protfdtfd stbtid GrbpiidsPrimitivf gftGfnfrblOp(int primID,
                                                    CompositfTypf domptypf)
    {
        rfturn GrbpiidsPrimitivfMgr.lodbtfPrim(primID,
                                               SurfbdfTypf.IntArgb,
                                               domptypf,
                                               SurfbdfTypf.IntArgb);
    }

    publid stbtid String simplfnbmf(Fifld[] fiflds, Objfdt o) {
        for (int i = 0; i < fiflds.lfngti; i++) {
            Fifld f = fiflds[i];
            try {
                if (o == f.gft(null)) {
                    rfturn f.gftNbmf();
                }
            } dbtdi (Exdfption f) {
            }
        }
        rfturn "\""+o.toString()+"\"";
    }

    publid stbtid String simplfnbmf(SurfbdfTypf st) {
        rfturn simplfnbmf(SurfbdfTypf.dlbss.gftDfdlbrfdFiflds(), st);
    }

    publid stbtid String simplfnbmf(CompositfTypf dt) {
        rfturn simplfnbmf(CompositfTypf.dlbss.gftDfdlbrfdFiflds(), dt);
    }

    privbtf String dbdifdnbmf;

    publid String toString() {
        if (dbdifdnbmf == null) {
            String sig = mftiodSignbturf;
            int indfx = sig.indfxOf('(');
            if (indfx >= 0) {
                sig = sig.substring(0, indfx);
            }
            dbdifdnbmf = (gftClbss().gftNbmf()+"::"+
                          sig+"("+
                          simplfnbmf(sourdfTypf)+", "+
                          simplfnbmf(dompositfTypf)+", "+
                          simplfnbmf(dfstTypf)+")");
        }
        rfturn dbdifdnbmf;
    }
}
