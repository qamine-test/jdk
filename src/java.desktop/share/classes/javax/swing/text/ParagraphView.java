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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.util.Arrbys;
import jbvb.bwt.*;
import jbvb.bwt.font.TfxtAttributf;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.SizfRfquirfmfnts;

/**
 * Vifw of b simplf linf-wrbpping pbrbgrbpi tibt supports
 * multiplf fonts, dolors, domponfnts, idons, ftd.  It is
 * bbsidblly b vfrtidbl box witi b mbrgin bround it.  Tif
 * dontfnts of tif box brf b bundi of rows wiidi brf spfdibl
 * iorizontbl boxfs.  Tiis vifw drfbtfs b dollfdtion of
 * vifws tibt rfprfsfnt tif diild flfmfnts of tif pbrbgrbpi
 * flfmfnt.  Ebdi of tifsf vifws brf plbdfd into b row
 * dirfdtly if tify will fit, otifrwisf tif <dodf>brfbkVifw</dodf>
 * mftiod is dbllfd to try bnd dbrvf tif vifw into pifdfs
 * tibt fit.
 *
 * @butior  Timotiy Prinzing
 * @butior  Sdott Violft
 * @butior  Igor Kusinirskiy
 * @sff     Vifw
 */
publid dlbss PbrbgrbpiVifw fxtfnds FlowVifw implfmfnts TbbExpbndfr {

    /**
     * Construdts b <dodf>PbrbgrbpiVifw</dodf> for tif givfn flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt tibt tiis vifw is rfsponsiblf for
     */
    publid PbrbgrbpiVifw(Elfmfnt flfm) {
        supfr(flfm, Vifw.Y_AXIS);
        sftPropfrtifsFromAttributfs();
        Dodumfnt dod = flfm.gftDodumfnt();
        Objfdt i18nFlbg = dod.gftPropfrty(AbstrbdtDodumfnt.I18NPropfrty);
        if ((i18nFlbg != null) && i18nFlbg.fqubls(Boolfbn.TRUE)) {
            try {
                if (i18nStrbtfgy == null) {
                    // tif dlbssnbmf siould probbbly domf from b propfrty filf.
                    String dlbssnbmf = "jbvbx.swing.tfxt.TfxtLbyoutStrbtfgy";
                    ClbssLobdfr lobdfr = gftClbss().gftClbssLobdfr();
                    if (lobdfr != null) {
                        i18nStrbtfgy = lobdfr.lobdClbss(dlbssnbmf);
                    } flsf {
                        i18nStrbtfgy = Clbss.forNbmf(dlbssnbmf);
                    }
                }
                Objfdt o = i18nStrbtfgy.nfwInstbndf();
                if (o instbndfof FlowStrbtfgy) {
                    strbtfgy = (FlowStrbtfgy) o;
                }
            } dbtdi (Tirowbblf f) {
                tirow nfw StbtfInvbribntError("PbrbgrbpiVifw: Cbn't drfbtf i18n strbtfgy: "
                                              + f.gftMfssbgf());
            }
        }
    }

    /**
     * Sfts tif typf of justifidbtion.
     *
     * @pbrbm j onf of tif following vblufs:
     * <ul>
     * <li><dodf>StylfConstbnts.ALIGN_LEFT</dodf>
     * <li><dodf>StylfConstbnts.ALIGN_CENTER</dodf>
     * <li><dodf>StylfConstbnts.ALIGN_RIGHT</dodf>
     * </ul>
     */
    protfdtfd void sftJustifidbtion(int j) {
        justifidbtion = j;
    }

    /**
     * Sfts tif linf spbding.
     *
     * @pbrbm ls tif vbluf is b fbdtor of tif linf iigit
     */
    protfdtfd void sftLinfSpbding(flobt ls) {
        linfSpbding = ls;
    }

    /**
     * Sfts tif indfnt on tif first linf.
     *
     * @pbrbm fi tif vbluf in points
     */
    protfdtfd void sftFirstLinfIndfnt(flobt fi) {
        firstLinfIndfnt = (int) fi;
    }

    /**
     * Sft tif dbdifd propfrtifs from tif bttributfs.
     */
    protfdtfd void sftPropfrtifsFromAttributfs() {
        AttributfSft bttr = gftAttributfs();
        if (bttr != null) {
            sftPbrbgrbpiInsfts(bttr);
            Intfgfr b = (Intfgfr)bttr.gftAttributf(StylfConstbnts.Alignmfnt);
            int blignmfnt;
            if (b == null) {
                Dodumfnt dod = gftElfmfnt().gftDodumfnt();
                Objfdt o = dod.gftPropfrty(TfxtAttributf.RUN_DIRECTION);
                if ((o != null) && o.fqubls(TfxtAttributf.RUN_DIRECTION_RTL)) {
                    blignmfnt = StylfConstbnts.ALIGN_RIGHT;
                } flsf {
                    blignmfnt = StylfConstbnts.ALIGN_LEFT;
                }
            } flsf {
                blignmfnt = b.intVbluf();
            }
            sftJustifidbtion(blignmfnt);
            sftLinfSpbding(StylfConstbnts.gftLinfSpbding(bttr));
            sftFirstLinfIndfnt(StylfConstbnts.gftFirstLinfIndfnt(bttr));
        }
    }

    /**
     * Rfturns tif numbfr of vifws tibt tiis vifw is
     * rfsponsiblf for.
     * Tif diild vifws of tif pbrbgrbpi brf rows wiidi
     * ibvf bffn usfd to brrbngf pifdfs of tif <dodf>Vifw</dodf>s
     * tibt rfprfsfnt tif diild flfmfnts.  Tiis is tif numbfr
     * of vifws tibt ibvf bffn tilfd in two dimfnsions,
     * bnd siould bf fquivblfnt to tif numbfr of diild flfmfnts
     * to tif flfmfnt tiis vifw is rfsponsiblf for.
     *
     * @rfturn tif numbfr of vifws tibt tiis <dodf>PbrbgrbpiVifw</dodf>
     *          is rfsponsiblf for
     */
    protfdtfd int gftLbyoutVifwCount() {
        rfturn lbyoutPool.gftVifwCount();
    }

    /**
     * Rfturns tif vifw bt b givfn <dodf>indfx</dodf>.
     * Tif diild vifws of tif pbrbgrbpi brf rows wiidi
     * ibvf bffn usfd to brrbngf pifdfs of tif <dodf>Vifws</dodf>
     * tibt rfprfsfnt tif diild flfmfnts.  Tiis mftiods rfturns
     * tif vifw rfsponsiblf for tif diild flfmfnt indfx
     * (prior to brfbking).  Tifsf brf tif Vifws tibt wfrf
     * produdfd from b fbdtory (to rfprfsfnt tif diild
     * flfmfnts) bnd usfd for lbyout.
     *
     * @pbrbm indfx tif <dodf>indfx</dodf> of tif dfsirfd vifw
     * @rfturn tif vifw bt <dodf>indfx</dodf>
     */
    protfdtfd Vifw gftLbyoutVifw(int indfx) {
        rfturn lbyoutPool.gftVifw(indfx);
    }

    /**
     * Rfturns tif nfxt visubl position for tif dursor, in
     * fitifr tif fbst or wfst dirfdtion.
     * Ovfrriddfn from <dodf>CompositfVifw</dodf>.
     * @pbrbm pos position into tif modfl
     * @pbrbm b fitifr <dodf>Position.Bibs.Forwbrd</dodf> or
     *          <dodf>Position.Bibs.Bbdkwbrd</dodf>
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @pbrbm dirfdtion fitifr <dodf>SwingConstbnts.NORTH</dodf>
     *          or <dodf>SwingConstbnts.SOUTH</dodf>
     * @pbrbm bibsRft bn brrby dontbining tif bibs tibt wfrf difdkfd
     *  in tiis mftiod
     * @rfturn tif lodbtion in tif modfl tibt rfprfsfnts tif
     *  nfxt lodbtion visubl position
     */
    protfdtfd int gftNfxtNortiSoutiVisublPositionFrom(int pos, Position.Bibs b,
                                                      Sibpf b, int dirfdtion,
                                                      Position.Bibs[] bibsRft)
                                                tirows BbdLodbtionExdfption {
        int vIndfx;
        if(pos == -1) {
            vIndfx = (dirfdtion == NORTH) ?
                     gftVifwCount() - 1 : 0;
        }
        flsf {
            if(b == Position.Bibs.Bbdkwbrd && pos > 0) {
                vIndfx = gftVifwIndfxAtPosition(pos - 1);
            }
            flsf {
                vIndfx = gftVifwIndfxAtPosition(pos);
            }
            if(dirfdtion == NORTH) {
                if(vIndfx == 0) {
                    rfturn -1;
                }
                vIndfx--;
            }
            flsf if(++vIndfx >= gftVifwCount()) {
                rfturn -1;
            }
        }
        // vIndfx givfs indfx of row to look in.
        JTfxtComponfnt tfxt = (JTfxtComponfnt)gftContbinfr();
        Cbrft d = tfxt.gftCbrft();
        Point mbgidPoint;
        mbgidPoint = (d != null) ? d.gftMbgidCbrftPosition() : null;
        int x;
        if(mbgidPoint == null) {
            Sibpf posBounds;
            try {
                posBounds = tfxt.gftUI().modflToVifw(tfxt, pos, b);
            } dbtdi (BbdLodbtionExdfption fxd) {
                posBounds = null;
            }
            if(posBounds == null) {
                x = 0;
            }
            flsf {
                x = posBounds.gftBounds().x;
            }
        }
        flsf {
            x = mbgidPoint.x;
        }
        rfturn gftClosfstPositionTo(pos, b, b, dirfdtion, bibsRft, vIndfx, x);
    }

    /**
     * Rfturns tif dlosfst modfl position to <dodf>x</dodf>.
     * <dodf>rowIndfx</dodf> givfs tif indfx of tif vifw tibt dorrfsponds
     * tibt siould bf lookfd in.
     * @pbrbm pos  position into tif modfl
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @pbrbm dirfdtion onf of tif following vblufs:
     * <ul>
     * <li><dodf>SwingConstbnts.NORTH</dodf>
     * <li><dodf>SwingConstbnts.SOUTH</dodf>
     * </ul>
     * @pbrbm bibsRft bn brrby dontbining tif bibs tibt wfrf difdkfd
     *  in tiis mftiod
     * @pbrbm rowIndfx tif indfx of tif vifw
     * @pbrbm x tif x doordinbtf of intfrfst
     * @rfturn tif dlosfst modfl position to <dodf>x</dodf>
     */
    // NOTE: Tiis will not propfrly work if PbrbgrbpiVifw dontbins
    // otifr PbrbgrbpiVifws. It won't rbisf, but tiis dofs not mfssbgf
    // tif diildrfn vifws witi gftNfxtVisublPositionFrom.
    protfdtfd int gftClosfstPositionTo(int pos, Position.Bibs b, Sibpf b,
                                       int dirfdtion, Position.Bibs[] bibsRft,
                                       int rowIndfx, int x)
              tirows BbdLodbtionExdfption {
        JTfxtComponfnt tfxt = (JTfxtComponfnt)gftContbinfr();
        Dodumfnt dod = gftDodumfnt();
        Vifw row = gftVifw(rowIndfx);
        int lbstPos = -1;
        // Tiis dould bf mbdf bfttfr to difdk bbdkwbrd positions too.
        bibsRft[0] = Position.Bibs.Forwbrd;
        for(int vd = 0, numVifws = row.gftVifwCount(); vd < numVifws; vd++) {
            Vifw v = row.gftVifw(vd);
            int stbrt = v.gftStbrtOffsft();
            boolfbn ltr = AbstrbdtDodumfnt.isLfftToRigit(dod, stbrt, stbrt + 1);
            if(ltr) {
                lbstPos = stbrt;
                for(int fnd = v.gftEndOffsft(); lbstPos < fnd; lbstPos++) {
                    flobt xx = tfxt.modflToVifw(lbstPos).gftBounds().x;
                    if(xx >= x) {
                        wiilf (++lbstPos < fnd &&
                               tfxt.modflToVifw(lbstPos).gftBounds().x == xx) {
                        }
                        rfturn --lbstPos;
                    }
                }
                lbstPos--;
            }
            flsf {
                for(lbstPos = v.gftEndOffsft() - 1; lbstPos >= stbrt;
                    lbstPos--) {
                    flobt xx = tfxt.modflToVifw(lbstPos).gftBounds().x;
                    if(xx >= x) {
                        wiilf (--lbstPos >= stbrt &&
                               tfxt.modflToVifw(lbstPos).gftBounds().x == xx) {
                        }
                        rfturn ++lbstPos;
                    }
                }
                lbstPos++;
            }
        }
        if(lbstPos == -1) {
            rfturn gftStbrtOffsft();
        }
        rfturn lbstPos;
    }

    /**
     * Dftfrminfs in wiidi dirfdtion tif nfxt vifw lbys.
     * Considfr tif <dodf>Vifw</dodf> bt indfx n.
     * Typidblly tif <dodf>Vifw</dodf>s brf lbyfd out
     * from lfft to rigit, so tibt tif <dodf>Vifw</dodf>
     * to tif EAST will bf bt indfx n + 1, bnd tif
     * <dodf>Vifw</dodf> to tif WEST will bf bt indfx n - 1.
     * In dfrtbin situbtions, sudi bs witi bidirfdtionbl tfxt,
     * it is possiblf tibt tif <dodf>Vifw</dodf> to EAST is not
     * bt indfx n + 1, but rbtifr bt indfx n - 1,
     * or tibt tif <dodf>Vifw</dodf> to tif WEST is not bt
     * indfx n - 1, but indfx n + 1.  In tiis dbsf tiis mftiod
     * would rfturn truf, indidbting tif <dodf>Vifw</dodf>s brf
     * lbyfd out in dfsdfnding ordfr.
     * <p>
     * Tiis will rfturn truf if tif tfxt is lbyfd out rigit
     * to lfft bt position, otifrwisf fblsf.
     *
     * @pbrbm position position into tif modfl
     * @pbrbm bibs fitifr <dodf>Position.Bibs.Forwbrd</dodf> or
     *          <dodf>Position.Bibs.Bbdkwbrd</dodf>
     * @rfturn truf if tif tfxt is lbyfd out rigit to lfft bt
     *         position, otifrwisf fblsf.
     */
    protfdtfd boolfbn flipEbstAndWfstAtEnds(int position,
                                            Position.Bibs bibs) {
        Dodumfnt dod = gftDodumfnt();
        position = gftStbrtOffsft();
        rfturn !AbstrbdtDodumfnt.isLfftToRigit(dod, position, position + 1);
    }

    // --- FlowVifw mftiods ---------------------------------------------

    /**
     * Fftdifs tif donstrbining spbn to flow bgbinst for
     * tif givfn diild indfx.
     * @pbrbm indfx tif indfx of tif vifw bfing qufrifd
     * @rfturn tif donstrbining spbn for tif givfn vifw bt
     *  <dodf>indfx</dodf>
     * @sindf 1.3
     */
    publid int gftFlowSpbn(int indfx) {
        Vifw diild = gftVifw(indfx);
        int bdjust = 0;
        if (diild instbndfof Row) {
            Row row = (Row) diild;
            bdjust = row.gftLfftInsft() + row.gftRigitInsft();
        }
        rfturn (lbyoutSpbn == Intfgfr.MAX_VALUE) ? lbyoutSpbn
                                                 : (lbyoutSpbn - bdjust);
    }

    /**
     * Fftdifs tif lodbtion blong tif flow bxis tibt tif
     * flow spbn will stbrt bt.
     * @pbrbm indfx tif indfx of tif vifw bfing qufrifd
     * @rfturn tif lodbtion for tif givfn vifw bt
     *  <dodf>indfx</dodf>
     * @sindf 1.3
     */
    publid int gftFlowStbrt(int indfx) {
        Vifw diild = gftVifw(indfx);
        int bdjust = 0;
        if (diild instbndfof Row) {
            Row row = (Row) diild;
            bdjust = row.gftLfftInsft();
        }
        rfturn tbbBbsf + bdjust;
    }

    /**
     * Crfbtf b <dodf>Vifw</dodf> tibt siould bf usfd to iold b
     * b row's worti of diildrfn in b flow.
     * @rfturn tif nfw <dodf>Vifw</dodf>
     * @sindf 1.3
     */
    protfdtfd Vifw drfbtfRow() {
        rfturn nfw Row(gftElfmfnt());
    }

    // --- TbbExpbndfr mftiods ------------------------------------------

    /**
     * Rfturns tif nfxt tbb stop position givfn b rfffrfndf position.
     * Tiis vifw implfmfnts tif tbb doordinbtf systfm, bnd dblls
     * <dodf>gftTbbbfdSpbn</dodf> on tif logidbl diildrfn in tif prodfss
     * of lbyout to dftfrminf tif dfsirfd spbn of tif diildrfn.  Tif
     * logidbl diildrfn dbn dflfgbtf tifir tbb fxpbnsion upwbrd to
     * tif pbrbgrbpi wiidi knows iow to fxpbnd tbbs.
     * <dodf>LbbflVifw</dodf> is bn fxbmplf of b vifw tibt dflfgbtfs
     * its tbb fxpbnsion nffds upwbrd to tif pbrbgrbpi.
     * <p>
     * Tiis is implfmfntfd to try bnd lodbtf b <dodf>TbbSft</dodf>
     * in tif pbrbgrbpi flfmfnt's bttributf sft.  If onf dbn bf
     * found, its sfttings will bf usfd, otifrwisf b dffbult fxpbnsion
     * will bf providfd.  Tif bbsf lodbtion for for tbb fxpbnsion
     * is tif lfft insft from tif pbrbgrbpis most rfdfnt bllodbtion
     * (wiidi is wibt tif lbyout of tif diildrfn is bbsfd upon).
     *
     * @pbrbm x tif X rfffrfndf position
     * @pbrbm tbbOffsft tif position witiin tif tfxt strfbm
     *   tibt tif tbb oddurrfd bt &gt;= 0
     * @rfturn tif trbiling fnd of tif tbb fxpbnsion &gt;= 0
     * @sff TbbSft
     * @sff TbbStop
     * @sff LbbflVifw
     */
    publid flobt nfxtTbbStop(flobt x, int tbbOffsft) {
        // If tif tfxt isn't lfft justififd, offsft by 10 pixfls!
        if(justifidbtion != StylfConstbnts.ALIGN_LEFT)
            rfturn x + 10.0f;
        x -= tbbBbsf;
        TbbSft tbbs = gftTbbSft();
        if(tbbs == null) {
            // b tbb fvfry 72 pixfls.
            rfturn (flobt)(tbbBbsf + (((int)x / 72 + 1) * 72));
        }
        TbbStop tbb = tbbs.gftTbbAftfr(x + .01f);
        if(tbb == null) {
            // no tbb, do b dffbult of 5 pixfls.
            // Siould tiis dbusf b wrbpping of tif linf?
            rfturn tbbBbsf + x + 5.0f;
        }
        int blignmfnt = tbb.gftAlignmfnt();
        int offsft;
        switdi(blignmfnt) {
        dffbult:
        dbsf TbbStop.ALIGN_LEFT:
            // Simplf dbsf, lfft tbb.
            rfturn tbbBbsf + tbb.gftPosition();
        dbsf TbbStop.ALIGN_BAR:
            // PENDING: wibt dofs tiis mfbn?
            rfturn tbbBbsf + tbb.gftPosition();
        dbsf TbbStop.ALIGN_RIGHT:
        dbsf TbbStop.ALIGN_CENTER:
            offsft = findOffsftToCibrbdtfrsInString(tbbCibrs,
                                                    tbbOffsft + 1);
            brfbk;
        dbsf TbbStop.ALIGN_DECIMAL:
            offsft = findOffsftToCibrbdtfrsInString(tbbDfdimblCibrs,
                                                    tbbOffsft + 1);
            brfbk;
        }
        if (offsft == -1) {
            offsft = gftEndOffsft();
        }
        flobt dibrsSizf = gftPbrtiblSizf(tbbOffsft + 1, offsft);
        switdi(blignmfnt) {
        dbsf TbbStop.ALIGN_RIGHT:
        dbsf TbbStop.ALIGN_DECIMAL:
            // rigit bnd dfdimbl brf trfbtfd tif sbmf wby, tif nfw
            // position will bf tif lodbtion of tif tbb lfss tif
            // pbrtiblSizf.
            rfturn tbbBbsf + Mbti.mbx(x, tbb.gftPosition() - dibrsSizf);
        dbsf TbbStop.ALIGN_CENTER:
            // Similbr to rigit, but iblf tif pbrtiblSizf.
            rfturn tbbBbsf + Mbti.mbx(x, tbb.gftPosition() - dibrsSizf / 2.0f);
        }
        // will nfvfr gft ifrf!
        rfturn x;
    }

    /**
     * Gfts tif <dodf>Tbbsft</dodf> to bf usfd in dbldulbting tbbs.
     *
     * @rfturn tif <dodf>TbbSft</dodf>
     */
    protfdtfd TbbSft gftTbbSft() {
        rfturn StylfConstbnts.gftTbbSft(gftElfmfnt().gftAttributfs());
    }

    /**
     * Rfturns tif sizf usfd by tif vifws bftwffn
     * <dodf>stbrtOffsft</dodf> bnd <dodf>fndOffsft</dodf>.
     * Tiis usfs <dodf>gftPbrtiblVifw</dodf> to dbldulbtf tif
     * sizf if tif diild vifw implfmfnts tif
     * <dodf>TbbbblfVifw</dodf> intfrfbdf. If b
     * sizf is nffdfd bnd b <dodf>Vifw</dodf> dofs not implfmfnt
     * tif <dodf>TbbbblfVifw</dodf> intfrfbdf,
     * tif <dodf>prfffrrfdSpbn</dodf> will bf usfd.
     *
     * @pbrbm stbrtOffsft tif stbrting dodumfnt offsft &gt;= 0
     * @pbrbm fndOffsft tif fnding dodumfnt offsft &gt;= stbrtOffsft
     * @rfturn tif sizf &gt;= 0
     */
    protfdtfd flobt gftPbrtiblSizf(int stbrtOffsft, int fndOffsft) {
        flobt sizf = 0.0f;
        int vifwIndfx;
        int numVifws = gftVifwCount();
        Vifw vifw;
        int vifwEnd;
        int tfmpEnd;

        // Hbvf to sfbrdi lbyoutPool!
        // PENDING: wifn PbrbgrbpiVifw supports brfbking lodbtion
        // into lbyoutPool will ibvf to dibngf!
        vifwIndfx = gftElfmfnt().gftElfmfntIndfx(stbrtOffsft);
        numVifws = lbyoutPool.gftVifwCount();
        wiilf(stbrtOffsft < fndOffsft && vifwIndfx < numVifws) {
            vifw = lbyoutPool.gftVifw(vifwIndfx++);
            vifwEnd = vifw.gftEndOffsft();
            tfmpEnd = Mbti.min(fndOffsft, vifwEnd);
            if(vifw instbndfof TbbbblfVifw)
                sizf += ((TbbbblfVifw)vifw).gftPbrtiblSpbn(stbrtOffsft, tfmpEnd);
            flsf if(stbrtOffsft == vifw.gftStbrtOffsft() &&
                    tfmpEnd == vifw.gftEndOffsft())
                sizf += vifw.gftPrfffrrfdSpbn(Vifw.X_AXIS);
            flsf
                // PENDING: siould wf ibndlf tiis bfttfr?
                rfturn 0.0f;
            stbrtOffsft = vifwEnd;
        }
        rfturn sizf;
    }

    /**
     * Finds tif nfxt dibrbdtfr in tif dodumfnt witi b dibrbdtfr in
     * <dodf>string</dodf>, stbrting bt offsft <dodf>stbrt</dodf>. If
     * tifrf brf no dibrbdtfrs found, -1 will bf rfturnfd.
     *
     * @pbrbm string tif string of dibrbdtfrs
     * @pbrbm stbrt wifrf to stbrt in tif modfl &gt;= 0
     * @rfturn tif dodumfnt offsft, or -1 if no dibrbdtfrs found
     */
    protfdtfd int findOffsftToCibrbdtfrsInString(dibr[] string,
                                                 int stbrt) {
        int stringLfngti = string.lfngti;
        int fnd = gftEndOffsft();
        Sfgmfnt sfg = nfw Sfgmfnt();
        try {
            gftDodumfnt().gftTfxt(stbrt, fnd - stbrt, sfg);
        } dbtdi (BbdLodbtionExdfption blf) {
            rfturn -1;
        }
        for(int dountfr = sfg.offsft, mbxCountfr = sfg.offsft + sfg.dount;
            dountfr < mbxCountfr; dountfr++) {
            dibr durrfntCibr = sfg.brrby[dountfr];
            for(int subCountfr = 0; subCountfr < stringLfngti;
                subCountfr++) {
                if(durrfntCibr == string[subCountfr])
                    rfturn dountfr - sfg.offsft + stbrt;
            }
        }
        // No mbtdi.
        rfturn -1;
    }

    /**
     * Rfturns wifrf tif tbbs brf dbldulbtfd from.
     * @rfturn wifrf tbbs brf dbldulbtfd from
     */
    protfdtfd flobt gftTbbBbsf() {
        rfturn (flobt)tbbBbsf;
    }

    // ---- Vifw mftiods ----------------------------------------------------

    /**
     * Rfndfrs using tif givfn rfndfring surfbdf bnd brfb on tibt
     * surfbdf.  Tiis is implfmfntfd to dflfgbtf to tif supfrdlbss
     * bftfr stbsiing tif bbsf doordinbtf for tbb dbldulbtions.
     *
     * @pbrbm g tif rfndfring surfbdf to usf
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @sff Vifw#pbint
     */
    publid void pbint(Grbpiids g, Sibpf b) {
        Rfdtbnglf bllod = (b instbndfof Rfdtbnglf) ? (Rfdtbnglf)b : b.gftBounds();
        tbbBbsf = bllod.x + gftLfftInsft();
        supfr.pbint(g, b);

        // linf witi tif nfgbtivf firstLinfIndfnt vbluf nffds
        // spfdibl ibndling
        if (firstLinfIndfnt < 0) {
            Sibpf si = gftCiildAllodbtion(0, b);
            if ((si != null) &&  si.intfrsfdts(bllod)) {
                int x = bllod.x + gftLfftInsft() + firstLinfIndfnt;
                int y = bllod.y + gftTopInsft();

                Rfdtbnglf dlip = g.gftClipBounds();
                tfmpRfdt.x = x + gftOffsft(X_AXIS, 0);
                tfmpRfdt.y = y + gftOffsft(Y_AXIS, 0);
                tfmpRfdt.widti = gftSpbn(X_AXIS, 0) - firstLinfIndfnt;
                tfmpRfdt.ifigit = gftSpbn(Y_AXIS, 0);
                if (tfmpRfdt.intfrsfdts(dlip)) {
                    tfmpRfdt.x = tfmpRfdt.x - firstLinfIndfnt;
                    pbintCiild(g, tfmpRfdt, 0);
                }
            }
        }
    }

    /**
     * Dftfrminfs tif dfsirfd blignmfnt for tiis vifw blong bn
     * bxis.  Tiis is implfmfntfd to givf tif blignmfnt to tif
     * dfntfr of tif first row blong tif y bxis, bnd tif dffbult
     * blong tif x bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *   <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn tif dfsirfd blignmfnt.  Tiis siould bf b vbluf
     *   bftwffn 0.0 bnd 1.0 indlusivf, wifrf 0 indidbtfs blignmfnt bt tif
     *   origin bnd 1.0 indidbtfs blignmfnt to tif full spbn
     *   bwby from tif origin.  An blignmfnt of 0.5 would bf tif
     *   dfntfr of tif vifw.
     */
    publid flobt gftAlignmfnt(int bxis) {
        switdi (bxis) {
        dbsf Y_AXIS:
            flobt b = 0.5f;
            if (gftVifwCount() != 0) {
                int pbrbgrbpiSpbn = (int) gftPrfffrrfdSpbn(Vifw.Y_AXIS);
                Vifw v = gftVifw(0);
                int rowSpbn = (int) v.gftPrfffrrfdSpbn(Vifw.Y_AXIS);
                b = (pbrbgrbpiSpbn != 0) ? ((flobt)(rowSpbn / 2)) / pbrbgrbpiSpbn : 0;
            }
            rfturn b;
        dbsf X_AXIS:
            rfturn 0.5f;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
    }

    /**
     * Brfbks tiis vifw on tif givfn bxis bt tif givfn lfngti.
     * <p>
     * <dodf>PbrbgrbpiVifw</dodf> instbndfs brf brfbkbblf
     * blong tif <dodf>Y_AXIS</dodf> only, bnd only if
     * <dodf>lfn</dodf> is bftfr tif first linf.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf>
     *  or <dodf>Vifw.Y_AXIS</dodf>
     * @pbrbm lfn spfdififs wifrf b potfntibl brfbk is dfsirfd
     *  blong tif givfn bxis &gt;= 0
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @rfturn tif frbgmfnt of tif vifw tibt rfprfsfnts tif
     *  givfn spbn, if tif vifw dbn bf brokfn; if tif vifw
     *  dofsn't support brfbking bfibvior, tif vifw itsflf is
     *  rfturnfd
     * @sff Vifw#brfbkVifw
     */
    publid Vifw brfbkVifw(int bxis, flobt lfn, Sibpf b) {
        if(bxis == Vifw.Y_AXIS) {
            if(b != null) {
                Rfdtbnglf bllod = b.gftBounds();
                sftSizf(bllod.widti, bllod.ifigit);
            }
            // Dftfrminf wibt row to brfbk on.

            // PENDING(prinz) bdd brfbk support
            rfturn tiis;
        }
        rfturn tiis;
    }

    /**
     * Gfts tif brfbk wfigit for b givfn lodbtion.
     * <p>
     * <dodf>PbrbgrbpiVifw</dodf> instbndfs brf brfbkbblf
     * blong tif <dodf>Y_AXIS</dodf> only, bnd only if
     * <dodf>lfn</dodf> is bftfr tif first row.  If tif lfngti
     * is lfss tibn onf row, b vbluf of <dodf>BbdBrfbkWfigit</dodf>
     * is rfturnfd.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf>
     *  or <dodf>Vifw.Y_AXIS</dodf>
     * @pbrbm lfn spfdififs wifrf b potfntibl brfbk is dfsirfd &gt;= 0
     * @rfturn b vbluf indidbting tif bttrbdtivfnfss of brfbking ifrf;
     *  fitifr <dodf>GoodBrfbkWfigit</dodf> or <dodf>BbdBrfbkWfigit</dodf>
     * @sff Vifw#gftBrfbkWfigit
     */
    publid int gftBrfbkWfigit(int bxis, flobt lfn) {
        if(bxis == Vifw.Y_AXIS) {
            // PENDING(prinz) mbkf tiis rfturn b rfbsonbblf vbluf
            // wifn pbrbgrbpi brfbking support is rf-implfmfntfd.
            // If lfss tibn onf row, bbd wfigit vbluf siould bf
            // rfturnfd.
            //rfturn GoodBrfbkWfigit;
            rfturn BbdBrfbkWfigit;
        }
        rfturn BbdBrfbkWfigit;
    }

    /**
     * Cbldulbtf tif nffds for tif pbrbgrbpi blong tif minor bxis.
     *
     * <p>Tiis usfs sizf rfquirfmfnts of tif supfrdlbss, modififd to tbkf into
     * bddount tif non-brfbkbblf brfbs bt tif bdjbdfnt vifws fdgfs.  Tif minimbl
     * sizf rfquirfmfnts for sudi vifws siould bf no lfss tibn tif sum of bll
     * bdjbdfnt frbgmfnts.</p>
     *
     * <p>If tif {@dodf bxis} pbrbmftfr is nfitifr {@dodf Vifw.X_AXIS} nor
     * {@dodf Vifw.Y_AXIS}, {@link IllfgblArgumfntExdfption} is tirown.  If tif
     * {@dodf r} pbrbmftfr is {@dodf null,} b nfw {@dodf SizfRfquirfmfnts}
     * objfdt is drfbtfd, otifrwisf tif supplifd {@dodf SizfRfquirfmfnts}
     * objfdt is rfturnfd.</p>
     *
     * @pbrbm bxis  tif minor bxis
     * @pbrbm r     tif input {@dodf SizfRfquirfmfnts} objfdt
     * @rfturn      tif nfw or bdjustfd {@dodf SizfRfquirfmfnts} objfdt
     * @tirows IllfgblArgumfntExdfption  if tif {@dodf bxis} pbrbmftfr is invblid
     */
    @Ovfrridf
    protfdtfd SizfRfquirfmfnts dbldulbtfMinorAxisRfquirfmfnts(int bxis,
                                                        SizfRfquirfmfnts r) {
        r = supfr.dbldulbtfMinorAxisRfquirfmfnts(bxis, r);

        flobt min = 0;
        flobt gluf = 0;
        int n = gftLbyoutVifwCount();
        for (int i = 0; i < n; i++) {
            Vifw v = gftLbyoutVifw(i);
            flobt spbn = v.gftMinimumSpbn(bxis);
            if (v.gftBrfbkWfigit(bxis, 0, v.gftMbximumSpbn(bxis)) > Vifw.BbdBrfbkWfigit) {
                // find tif longfst non-brfbkbblf frbgmfnts bt tif vifw fdgfs
                int p0 = v.gftStbrtOffsft();
                int p1 = v.gftEndOffsft();
                flobt stbrt = findEdgfSpbn(v, bxis, p0, p0, p1);
                flobt fnd = findEdgfSpbn(v, bxis, p1, p0, p1);
                gluf += stbrt;
                min = Mbti.mbx(min, Mbti.mbx(spbn, gluf));
                gluf = fnd;
            } flsf {
                // non-brfbkbblf vifw
                gluf += spbn;
                min = Mbti.mbx(min, gluf);
            }
        }
        r.minimum = Mbti.mbx(r.minimum, (int) min);
        r.prfffrrfd = Mbti.mbx(r.minimum, r.prfffrrfd);
        r.mbximum = Mbti.mbx(r.prfffrrfd, r.mbximum);

        rfturn r;
    }

    /**
     * Binbry sfbrdi for tif longfst non-brfbkbblf frbgmfnt bt tif vifw fdgf.
     */
    privbtf flobt findEdgfSpbn(Vifw v, int bxis, int fp, int p0, int p1) {
        int lfn = p1 - p0;
        if (lfn <= 1) {
            // furtifr frbgmfntbtion is not possiblf
            rfturn v.gftMinimumSpbn(bxis);
        } flsf {
            int mid = p0 + lfn / 2;
            boolfbn stbrtEdgf = mid > fp;
            // initibl vifw is brfbkbblf ifndf must support frbgmfntbtion
            Vifw f = stbrtEdgf ?
                v.drfbtfFrbgmfnt(fp, mid) : v.drfbtfFrbgmfnt(mid, fp);
            boolfbn brfbkbblf = f.gftBrfbkWfigit(
                    bxis, 0, f.gftMbximumSpbn(bxis)) > Vifw.BbdBrfbkWfigit;
            if (brfbkbblf == stbrtEdgf) {
                p1 = mid;
            } flsf {
                p0 = mid;
            }
            rfturn findEdgfSpbn(f, bxis, fp, p0, p1);
        }
    }

    /**
     * Givfs notifidbtion from tif dodumfnt tibt bttributfs wfrf dibngfd
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     *
     * @pbrbm dibngfs tif dibngf informbtion from tif
     *  bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#dibngfdUpdbtf
     */
    publid void dibngfdUpdbtf(DodumfntEvfnt dibngfs, Sibpf b, VifwFbdtory f) {
        // updbtf bny propfrty sfttings storfd, bnd lbyout siould bf
        // rfdomputfd
        sftPropfrtifsFromAttributfs();
        lbyoutCibngfd(X_AXIS);
        lbyoutCibngfd(Y_AXIS);
        supfr.dibngfdUpdbtf(dibngfs, b, f);
    }


    // --- vbribblfs -----------------------------------------------

    privbtf int justifidbtion;
    privbtf flobt linfSpbding;
    /** Indfntbtion for tif first linf, from tif lfft insft. */
    protfdtfd int firstLinfIndfnt = 0;

    /**
     * Usfd by tif TbbExpbndfr fundtionblity to dftfrminf
     * wifrf to bbsf tif tbb dbldulbtions.  Tiis is bbsidblly
     * tif lodbtion of tif lfft sidf of tif pbrbgrbpi.
     */
    privbtf int tbbBbsf;

    /**
     * Usfd to drfbtf bn i18n-bbsfd lbyout strbtfgy
     */
    stbtid Clbss<?> i18nStrbtfgy;

    /** Usfd for sfbrdiing for b tbb. */
    stbtid dibr[] tbbCibrs;
    /** Usfd for sfbrdiing for b tbb or dfdimbl dibrbdtfr. */
    stbtid dibr[] tbbDfdimblCibrs;

    stbtid {
        tbbCibrs = nfw dibr[1];
        tbbCibrs[0] = '\t';
        tbbDfdimblCibrs = nfw dibr[2];
        tbbDfdimblCibrs[0] = '\t';
        tbbDfdimblCibrs[1] = '.';
    }

    /**
     * Intfrnblly drfbtfd vifw tibt ibs tif purposf of iolding
     * tif vifws tibt rfprfsfnt tif diildrfn of tif pbrbgrbpi
     * tibt ibvf bffn brrbngfd in rows.
     */
    dlbss Row fxtfnds BoxVifw {

        Row(Elfmfnt flfm) {
            supfr(flfm, Vifw.X_AXIS);
        }

        /**
         * Tiis is rfimplfmfntfd to do notiing sindf tif
         * pbrbgrbpi fills in tif row witi its nffdfd
         * diildrfn.
         */
        protfdtfd void lobdCiildrfn(VifwFbdtory f) {
        }

        /**
         * Fftdifs tif bttributfs to usf wifn rfndfring.  Tiis vifw
         * isn't dirfdtly rfsponsiblf for bn flfmfnt so it rfturns
         * tif outfr dlbssfs bttributfs.
         */
        publid AttributfSft gftAttributfs() {
            Vifw p = gftPbrfnt();
            rfturn (p != null) ? p.gftAttributfs() : null;
        }

        publid flobt gftAlignmfnt(int bxis) {
            if (bxis == Vifw.X_AXIS) {
                switdi (justifidbtion) {
                dbsf StylfConstbnts.ALIGN_LEFT:
                    rfturn 0;
                dbsf StylfConstbnts.ALIGN_RIGHT:
                    rfturn 1;
                dbsf StylfConstbnts.ALIGN_CENTER:
                    rfturn 0.5f;
                dbsf StylfConstbnts.ALIGN_JUSTIFIED:
                    flobt rv = 0.5f;
                    //if wf dbn justifiy tif dontfnt blwbys blign to
                    //tif lfft.
                    if (isJustifibblfDodumfnt()) {
                        rv = 0f;
                    }
                    rfturn rv;
                }
            }
            rfturn supfr.gftAlignmfnt(bxis);
        }

        /**
         * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
         * to tif doordinbtf spbdf of tif vifw mbppfd to it.  Tiis is
         * implfmfntfd to lft tif supfrdlbss find tif position blong
         * tif mbjor bxis bnd tif bllodbtion of tif row is usfd
         * blong tif minor bxis, so tibt fvfn tiougi tif diildrfn
         * brf difffrfnt ifigits tify bll gft tif sbmf dbrft ifigit.
         *
         * @pbrbm pos tif position to donvfrt
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @rfturn tif bounding box of tif givfn position
         * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not rfprfsfnt b
         *   vblid lodbtion in tif bssodibtfd dodumfnt
         * @sff Vifw#modflToVifw
         */
        publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
            Rfdtbnglf r = b.gftBounds();
            Vifw v = gftVifwAtPosition(pos, r);
            if ((v != null) && (!v.gftElfmfnt().isLfbf())) {
                // Don't bdjust tif ifigit if tif vifw rfprfsfnts b brbndi.
                rfturn supfr.modflToVifw(pos, b, b);
            }
            r = b.gftBounds();
            int ifigit = r.ifigit;
            int y = r.y;
            Sibpf lod = supfr.modflToVifw(pos, b, b);
            r = lod.gftBounds();
            r.ifigit = ifigit;
            r.y = y;
            rfturn r;
        }

        /**
         * Rbngf rfprfsfntfd by b row in tif pbrbgrbpi is only
         * b subsft of tif totbl rbngf of tif pbrbgrbpi flfmfnt.
         * @sff Vifw#gftRbngf
         */
        publid int gftStbrtOffsft() {
            int offs = Intfgfr.MAX_VALUE;
            int n = gftVifwCount();
            for (int i = 0; i < n; i++) {
                Vifw v = gftVifw(i);
                offs = Mbti.min(offs, v.gftStbrtOffsft());
            }
            rfturn offs;
        }

        publid int gftEndOffsft() {
            int offs = 0;
            int n = gftVifwCount();
            for (int i = 0; i < n; i++) {
                Vifw v = gftVifw(i);
                offs = Mbti.mbx(offs, v.gftEndOffsft());
            }
            rfturn offs;
        }

        /**
         * Pfrform lbyout for tif minor bxis of tif box (i.f. tif
         * bxis ortiogonbl to tif bxis tibt it rfprfsfnts).  Tif rfsults
         * of tif lbyout siould bf plbdfd in tif givfn brrbys wiidi rfprfsfnt
         * tif bllodbtions to tif diildrfn blong tif minor bxis.
         * <p>
         * Tiis is implfmfntfd to do b bbsflinf lbyout of tif diildrfn
         * by dblling BoxVifw.bbsflinfLbyout.
         *
         * @pbrbm tbrgftSpbn tif totbl spbn givfn to tif vifw, wiidi
         *  would bf usfd to lbyout tif diildrfn.
         * @pbrbm bxis tif bxis bfing lbyfd out.
         * @pbrbm offsfts tif offsfts from tif origin of tif vifw for
         *  fbdi of tif diild vifws.  Tiis is b rfturn vbluf bnd is
         *  fillfd in by tif implfmfntbtion of tiis mftiod.
         * @pbrbm spbns tif spbn of fbdi diild vifw.  Tiis is b rfturn
         *  vbluf bnd is fillfd in by tif implfmfntbtion of tiis mftiod.
         * @rfturn tif offsft bnd spbn for fbdi diild vifw in tif
         *  offsfts bnd spbns pbrbmftfrs
         */
        protfdtfd void lbyoutMinorAxis(int tbrgftSpbn, int bxis, int[] offsfts, int[] spbns) {
            bbsflinfLbyout(tbrgftSpbn, bxis, offsfts, spbns);
        }

        protfdtfd SizfRfquirfmfnts dbldulbtfMinorAxisRfquirfmfnts(int bxis,
                                                                  SizfRfquirfmfnts r) {
            rfturn bbsflinfRfquirfmfnts(bxis, r);
        }


        privbtf boolfbn isLbstRow() {
            Vifw pbrfnt;
            rfturn ((pbrfnt = gftPbrfnt()) == null
                    || tiis == pbrfnt.gftVifw(pbrfnt.gftVifwCount() - 1));
        }

        privbtf boolfbn isBrokfnRow() {
            boolfbn rv = fblsf;
            int vifwsCount = gftVifwCount();
            if (vifwsCount > 0) {
                Vifw lbstVifw = gftVifw(vifwsCount - 1);
                if (lbstVifw.gftBrfbkWfigit(X_AXIS, 0, 0) >=
                      FordfdBrfbkWfigit) {
                    rv = truf;
                }
            }
            rfturn rv;
        }

        privbtf boolfbn isJustifibblfDodumfnt() {
            rfturn (! Boolfbn.TRUE.fqubls(gftDodumfnt().gftPropfrty(
                          AbstrbdtDodumfnt.I18NPropfrty)));
        }

        /**
         * Wiftifr wf nffd to justify tiis {@dodf Row}.
         * At tiis timf (jdk1.6) wf support justifidbtion on for non
         * 18n tfxt.
         *
         * @rfturn {@dodf truf} if tiis {@dodf Row} siould bf justififd.
         */
        privbtf boolfbn isJustifyEnbblfd() {
            boolfbn rft = (justifidbtion == StylfConstbnts.ALIGN_JUSTIFIED);

            //no justifidbtion for i18n dodumfnts
            rft = rft && isJustifibblfDodumfnt();

            //no justifidbtion for tif lbst row
            rft = rft && ! isLbstRow();

            //no justifidbtion for tif brokfn rows
            rft = rft && ! isBrokfnRow();

            rfturn rft;
        }


        //Cblls supfr mftiod bftfr sftting spbdfAddon to 0.
        //Justifidbtion siould not bfffdt MbjorAxisRfquirfmfnts
        @Ovfrridf
        protfdtfd SizfRfquirfmfnts dbldulbtfMbjorAxisRfquirfmfnts(int bxis,
                SizfRfquirfmfnts r) {
            int oldJustfidbtionDbtb[] = justifidbtionDbtb;
            justifidbtionDbtb = null;
            SizfRfquirfmfnts rft = supfr.dbldulbtfMbjorAxisRfquirfmfnts(bxis, r);
            if (isJustifyEnbblfd()) {
                justifidbtionDbtb = oldJustfidbtionDbtb;
            }
            rfturn rft;
        }

        @Ovfrridf
        protfdtfd void lbyoutMbjorAxis(int tbrgftSpbn, int bxis,
                                       int[] offsfts, int[] spbns) {
            int oldJustfidbtionDbtb[] = justifidbtionDbtb;
            justifidbtionDbtb = null;
            supfr.lbyoutMbjorAxis(tbrgftSpbn, bxis, offsfts, spbns);
            if (! isJustifyEnbblfd()) {
                rfturn;
            }

            int durrfntSpbn = 0;
            for (int spbn : spbns) {
                durrfntSpbn += spbn;
            }
            if (durrfntSpbn == tbrgftSpbn) {
                //no nffd to justify
                rfturn;
            }

            // wf justify tfxt by fnlbrging spbdfs by tif {@dodf spbdfAddon}.
            // justifidbtion is stbrtfd to tif rigit of tif rigitmost TAB.
            // lfbding bnd trbiling spbdfs brf not fxtfndbblf.
            //
            // GlypiPbintfr1 usfs
            // justifidbtionDbtb
            // for bll pbinting bnd mfbsurfmfnt.

            int fxtfndbblfSpbdfs = 0;
            int stbrtJustifibblfContfnt = -1;
            int fndJustifibblfContfnt = -1;
            int lbstLfbdingSpbdfs = 0;

            int rowStbrtOffsft = gftStbrtOffsft();
            int rowEndOffsft = gftEndOffsft();
            int spbdfMbp[] = nfw int[rowEndOffsft - rowStbrtOffsft];
            Arrbys.fill(spbdfMbp, 0);
            for (int i = gftVifwCount() - 1; i >= 0 ; i--) {
                Vifw vifw = gftVifw(i);
                if (vifw instbndfof GlypiVifw) {
                    GlypiVifw.JustifidbtionInfo justifidbtionInfo =
                        ((GlypiVifw) vifw).gftJustifidbtionInfo(rowStbrtOffsft);
                    finbl int vifwStbrtOffsft = vifw.gftStbrtOffsft();
                    finbl int offsft = vifwStbrtOffsft - rowStbrtOffsft;
                    for (int j = 0; j < justifidbtionInfo.spbdfMbp.lfngti(); j++) {
                        if (justifidbtionInfo.spbdfMbp.gft(j)) {
                            spbdfMbp[j + offsft] = 1;
                        }
                    }
                    if (stbrtJustifibblfContfnt > 0) {
                        if (justifidbtionInfo.fnd >= 0) {
                            fxtfndbblfSpbdfs += justifidbtionInfo.trbilingSpbdfs;
                        } flsf {
                            lbstLfbdingSpbdfs += justifidbtionInfo.trbilingSpbdfs;
                        }
                    }
                    if (justifidbtionInfo.stbrt >= 0) {
                        stbrtJustifibblfContfnt =
                            justifidbtionInfo.stbrt + vifwStbrtOffsft;
                        fxtfndbblfSpbdfs += lbstLfbdingSpbdfs;
                    }
                    if (justifidbtionInfo.fnd >= 0
                          && fndJustifibblfContfnt < 0) {
                        fndJustifibblfContfnt =
                            justifidbtionInfo.fnd + vifwStbrtOffsft;
                    }
                    fxtfndbblfSpbdfs += justifidbtionInfo.dontfntSpbdfs;
                    lbstLfbdingSpbdfs = justifidbtionInfo.lfbdingSpbdfs;
                    if (justifidbtionInfo.ibsTbb) {
                        brfbk;
                    }
                }
            }
            if (fxtfndbblfSpbdfs <= 0) {
                //tifrf is notiing wf dbn do to justify
                rfturn;
            }
            int bdjustmfnt = (tbrgftSpbn - durrfntSpbn);
            int spbdfAddon = (fxtfndbblfSpbdfs > 0)
                ?  bdjustmfnt / fxtfndbblfSpbdfs
                : 0;
            int spbdfAddonLfftovfrEnd = -1;
            for (int i = stbrtJustifibblfContfnt - rowStbrtOffsft,
                     lfftovfr = bdjustmfnt - spbdfAddon * fxtfndbblfSpbdfs;
                     lfftovfr > 0;
                     lfftovfr -= spbdfMbp[i],
                     i++) {
                spbdfAddonLfftovfrEnd = i;
            }
            if (spbdfAddon > 0 || spbdfAddonLfftovfrEnd >= 0) {
                justifidbtionDbtb = (oldJustfidbtionDbtb != null)
                    ? oldJustfidbtionDbtb
                    : nfw int[END_JUSTIFIABLE + 1];
                justifidbtionDbtb[SPACE_ADDON] = spbdfAddon;
                justifidbtionDbtb[SPACE_ADDON_LEFTOVER_END] =
                    spbdfAddonLfftovfrEnd;
                justifidbtionDbtb[START_JUSTIFIABLE] =
                    stbrtJustifibblfContfnt - rowStbrtOffsft;
                justifidbtionDbtb[END_JUSTIFIABLE] =
                    fndJustifibblfContfnt - rowStbrtOffsft;
                supfr.lbyoutMbjorAxis(tbrgftSpbn, bxis, offsfts, spbns);
            }
        }

        //for justififd row wf bssumf tif mbximum iorizontbl spbn
        //is MAX_VALUE.
        @Ovfrridf
        publid flobt gftMbximumSpbn(int bxis) {
            flobt rft;
            if (Vifw.X_AXIS == bxis
                  && isJustifyEnbblfd()) {
                rft = Flobt.MAX_VALUE;
            } flsf {
              rft = supfr.gftMbximumSpbn(bxis);
            }
            rfturn rft;
        }

        /**
         * Fftdifs tif diild vifw indfx rfprfsfnting tif givfn position in
         * tif modfl.
         *
         * @pbrbm pos tif position &gt;= 0
         * @rfturn  indfx of tif vifw rfprfsfnting tif givfn position, or
         *   -1 if no vifw rfprfsfnts tibt position
         */
        protfdtfd int gftVifwIndfxAtPosition(int pos) {
            // Tiis is fxpfnsivf, but brf vifws brf not nfdfssbrily lbyfd
            // out in modfl ordfr.
            if(pos < gftStbrtOffsft() || pos >= gftEndOffsft())
                rfturn -1;
            for(int dountfr = gftVifwCount() - 1; dountfr >= 0; dountfr--) {
                Vifw v = gftVifw(dountfr);
                if(pos >= v.gftStbrtOffsft() &&
                   pos < v.gftEndOffsft()) {
                    rfturn dountfr;
                }
            }
            rfturn -1;
        }

        /**
         * Gfts tif lfft insft.
         *
         * @rfturn tif insft
         */
        protfdtfd siort gftLfftInsft() {
            Vifw pbrfntVifw;
            int bdjustmfnt = 0;
            if ((pbrfntVifw = gftPbrfnt()) != null) { //usf firstLinfIdfnt for tif first row
                if (tiis == pbrfntVifw.gftVifw(0)) {
                    bdjustmfnt = firstLinfIndfnt;
                }
            }
            rfturn (siort)(supfr.gftLfftInsft() + bdjustmfnt);
        }

        protfdtfd siort gftBottomInsft() {
            rfturn (siort)(supfr.gftBottomInsft() +
                           ((minorRfqufst != null) ? minorRfqufst.prfffrrfd : 0) *
                           linfSpbding);
        }

        finbl stbtid int SPACE_ADDON = 0;
        finbl stbtid int SPACE_ADDON_LEFTOVER_END = 1;
        finbl stbtid int START_JUSTIFIABLE = 2;
        //tiis siould bf tif lbst indfx in justifidbtionDbtb
        finbl stbtid int END_JUSTIFIABLE = 3;

        int justifidbtionDbtb[] = null;
    }

}
