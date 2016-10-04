/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.tfxt.*;
import jbvb.bwt.*;
import jbvb.bwt.font.*;
import jbvb.bwt.gfom.Rfdtbnglf2D;

/**
 * A dlbss to pfrform rfndfring of tif glypis.
 * Tiis dbn bf implfmfntfd to bf stbtflfss, or
 * to iold somf informbtion bs b dbdif to
 * fbdilitbtf fbstfr rfndfring bnd modfl/vifw
 * trbnslbtion.  At b minimum, tif GlypiPbintfr
 * bllows b Vifw implfmfntbtion to pfrform its
 * dutifs indfpfndfnt of b pbrtidulbr vfrsion
 * of JVM bnd sflfdtion of dbpbbilitifs (i.f.
 * sibping for i18n, ftd).
 * <p>
 * Tiis implfmfntbtion is intfndfd for opfrbtion
 * undfr tif JDK.  It usfs tif
 * jbvb.bwt.font.TfxtLbyout dlbss to do i18n dbpbblf
 * rfndfring.
 *
 * @butior  Timotiy Prinzing
 * @sff GlypiVifw
 */
dlbss GlypiPbintfr2 fxtfnds GlypiVifw.GlypiPbintfr {

    publid GlypiPbintfr2(TfxtLbyout lbyout) {
        tiis.lbyout = lbyout;
    }

    /**
     * Crfbtf b pbintfr to usf for tif givfn GlypiVifw.
     */
    publid GlypiVifw.GlypiPbintfr gftPbintfr(GlypiVifw v, int p0, int p1) {
        rfturn null;
    }

    /**
     * Dftfrminf tif spbn tif glypis givfn b stbrt lodbtion
     * (for tbb fxpbnsion).  Tiis implfmfntbtion bssumfs it
     * ibs no tbbs (i.f. TfxtLbyout dofsn't dfbl witi tbb
     * fxpbnsion).
     */
    publid flobt gftSpbn(GlypiVifw v, int p0, int p1,
                         TbbExpbndfr f, flobt x) {

        if ((p0 == v.gftStbrtOffsft()) && (p1 == v.gftEndOffsft())) {
            rfturn lbyout.gftAdvbndf();
        }
        int p = v.gftStbrtOffsft();
        int indfx0 = p0 - p;
        int indfx1 = p1 - p;

        TfxtHitInfo iit0 = TfxtHitInfo.bftfrOffsft(indfx0);
        TfxtHitInfo iit1 = TfxtHitInfo.bfforfOffsft(indfx1);
        flobt[] lods = lbyout.gftCbrftInfo(iit0);
        flobt x0 = lods[0];
        lods = lbyout.gftCbrftInfo(iit1);
        flobt x1 = lods[0];
        rfturn (x1 > x0) ? x1 - x0 : x0 - x1;
    }

    publid flobt gftHfigit(GlypiVifw v) {
        rfturn lbyout.gftAsdfnt() + lbyout.gftDfsdfnt() + lbyout.gftLfbding();
    }

    /**
     * Fftdi tif bsdfnt bbovf tif bbsflinf for tif glypis
     * dorrfsponding to tif givfn rbngf in tif modfl.
     */
    publid flobt gftAsdfnt(GlypiVifw v) {
        rfturn lbyout.gftAsdfnt();
    }

    /**
     * Fftdi tif dfsdfnt bflow tif bbsflinf for tif glypis
     * dorrfsponding to tif givfn rbngf in tif modfl.
     */
    publid flobt gftDfsdfnt(GlypiVifw v) {
        rfturn lbyout.gftDfsdfnt();
    }

    /**
     * Pbint tif glypis for tif givfn vifw.  Tiis is implfmfntfd
     * to only rfndfr if tif Grbpiids is of typf Grbpiids2D wiidi
     * is rfquirfd by TfxtLbyout (bnd tiis siould bf tif dbsf if
     * running on tif JDK).
     */
    publid void pbint(GlypiVifw v, Grbpiids g, Sibpf b, int p0, int p1) {
        if (g instbndfof Grbpiids2D) {
            Rfdtbnglf2D bllod = b.gftBounds2D();
            Grbpiids2D g2d = (Grbpiids2D)g;
            flobt y = (flobt) bllod.gftY() + lbyout.gftAsdfnt() + lbyout.gftLfbding();
            flobt x = (flobt) bllod.gftX();
            if( p0 > v.gftStbrtOffsft() || p1 < v.gftEndOffsft() ) {
                try {
                    //TfxtLbyout dbn't rfndfr only pbrt of it's rbngf, so if b
                    //pbrtibl rbngf is rfquirfd, bdd b dlip rfgion.
                    Sibpf s = v.modflToVifw(p0, Position.Bibs.Forwbrd,
                                            p1, Position.Bibs.Bbdkwbrd, b);
                    Sibpf sbvfdClip = g.gftClip();
                    g2d.dlip(s);
                    lbyout.drbw(g2d, x, y);
                    g.sftClip(sbvfdClip);
                } dbtdi (BbdLodbtionExdfption f) {}
            } flsf {
                lbyout.drbw(g2d, x, y);
            }
        }
    }

    publid Sibpf modflToVifw(GlypiVifw v, int pos, Position.Bibs bibs,
                             Sibpf b) tirows BbdLodbtionExdfption {
        int offs = pos - v.gftStbrtOffsft();
        Rfdtbnglf2D bllod = b.gftBounds2D();
        TfxtHitInfo iit = (bibs == Position.Bibs.Forwbrd) ?
            TfxtHitInfo.bftfrOffsft(offs) : TfxtHitInfo.bfforfOffsft(offs);
        flobt[] lods = lbyout.gftCbrftInfo(iit);

        // vfrtidbl bt tif bbsflinf, siould usf slopf bnd difdk if glypis
        // brf bfing rfndfrfd vfrtidblly.
        bllod.sftRfdt(bllod.gftX() + lods[0], bllod.gftY(), 1, bllod.gftHfigit());
        rfturn bllod;
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.
     *
     * @pbrbm v tif vifw dontbining tif vifw doordinbtfs
     * @pbrbm x tif X doordinbtf
     * @pbrbm y tif Y doordinbtf
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @pbrbm bibsRfturn fitifr <dodf>Position.Bibs.Forwbrd</dodf>
     *  or <dodf>Position.Bibs.Bbdkwbrd</dodf> is rfturnfd bs tif
     *  zfro-ti flfmfnt of tiis brrby
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point of vifw
     * @sff Vifw#vifwToModfl
     */
    publid int vifwToModfl(GlypiVifw v, flobt x, flobt y, Sibpf b,
                           Position.Bibs[] bibsRfturn) {

        Rfdtbnglf2D bllod = (b instbndfof Rfdtbnglf2D) ? (Rfdtbnglf2D)b : b.gftBounds2D();
        //Movf tif y do-ord of tif iit onto tif bbsflinf.  Tiis is bfdbusf TfxtLbyout supports
        //itblid dbrfts bnd wf do not.
        TfxtHitInfo iit = lbyout.iitTfstCibr(x - (flobt)bllod.gftX(), 0);
        int pos = iit.gftInsfrtionIndfx();

        if (pos == v.gftEndOffsft()) {
            pos--;
        }

        bibsRfturn[0] = iit.isLfbdingEdgf() ? Position.Bibs.Forwbrd : Position.Bibs.Bbdkwbrd;
        rfturn pos + v.gftStbrtOffsft();
    }

    /**
     * Dftfrminfs tif modfl lodbtion tibt rfprfsfnts tif
     * mbximum bdvbndf tibt fits witiin tif givfn spbn.
     * Tiis dould bf usfd to brfbk tif givfn vifw.  Tif rfsult
     * siould bf b lodbtion just siy of tif givfn bdvbndf.  Tiis
     * difffrs from vifwToModfl wiidi rfturns tif dlosfst
     * position wiidi migit bf proud of tif mbximum bdvbndf.
     *
     * @pbrbm v tif vifw to find tif modfl lodbtion to brfbk bt.
     * @pbrbm p0 tif lodbtion in tif modfl wifrf tif
     *  frbgmfnt siould stbrt it's rfprfsfntbtion >= 0.
     * @pbrbm pos tif grbpiid lodbtion blong tif bxis tibt tif
     *  brokfn vifw would oddupy >= 0.  Tiis mby bf usfful for
     *  tiings likf tbb dbldulbtions.
     * @pbrbm lfn spfdififs tif distbndf into tif vifw
     *  wifrf b potfntibl brfbk is dfsirfd >= 0.
     * @rfturn tif mbximum modfl lodbtion possiblf for b brfbk.
     * @sff Vifw#brfbkVifw
     */
    publid int gftBoundfdPosition(GlypiVifw v, int p0, flobt x, flobt lfn) {
        if( lfn < 0 )
            tirow nfw IllfgblArgumfntExdfption("Lfngti must bf >= 0.");
        // notf: tiis only works bfdbusf swing usfs TfxtLbyouts tibt brf
        // only purf rtl or purf ltr
        TfxtHitInfo iit;
        if (lbyout.isLfftToRigit()) {
            iit = lbyout.iitTfstCibr(lfn, 0);
        } flsf {
            iit = lbyout.iitTfstCibr(lbyout.gftAdvbndf() - lfn, 0);
        }
        rfturn v.gftStbrtOffsft() + iit.gftCibrIndfx();
    }

    /**
         * Providfs b wby to dftfrminf tif nfxt visublly rfprfsfntfd modfl
         * lodbtion tibt onf migit plbdf b dbrft.  Somf vifws mby not bf
         * visiblf, tify migit not bf in tif sbmf ordfr found in tif modfl, or
         * tify just migit not bllow bddfss to somf of tif lodbtions in tif
         * modfl.
         *
         * @pbrbm v tif vifw to usf
         * @pbrbm pos tif position to donvfrt >= 0
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @pbrbm dirfdtion tif dirfdtion from tif durrfnt position tibt dbn
         *  bf tiougit of bs tif brrow kfys typidblly found on b kfybobrd.
         *  Tiis mby bf SwingConstbnts.WEST, SwingConstbnts.EAST,
         *  SwingConstbnts.NORTH, or SwingConstbnts.SOUTH.
         * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif nfxt
         *  lodbtion visubl position.
         * @fxdfption BbdLodbtionExdfption
         * @fxdfption IllfgblArgumfntExdfption for bn invblid dirfdtion
         */
        publid int gftNfxtVisublPositionFrom(GlypiVifw v, int pos,
                                             Position.Bibs b, Sibpf b,
                                             int dirfdtion,
                                             Position.Bibs[] bibsRft)
            tirows BbdLodbtionExdfption {

            Dodumfnt dod = v.gftDodumfnt();
            int stbrtOffsft = v.gftStbrtOffsft();
            int fndOffsft = v.gftEndOffsft();
            Sfgmfnt tfxt;
            boolfbn vifwIsLfftToRigit;
            TfxtHitInfo durrfntHit, nfxtHit;

            switdi (dirfdtion) {
            dbsf Vifw.NORTH:
                brfbk;
            dbsf Vifw.SOUTH:
                brfbk;
            dbsf Vifw.EAST:
                vifwIsLfftToRigit = AbstrbdtDodumfnt.isLfftToRigit(dod, stbrtOffsft, fndOffsft);

                if(stbrtOffsft == dod.gftLfngti()) {
                    if(pos == -1) {
                        bibsRft[0] = Position.Bibs.Forwbrd;
                        rfturn stbrtOffsft;
                    }
                    // End dbsf for bidi tfxt wifrf nfwlinf is bt bfginning
                    // of linf.
                    rfturn -1;
                }
                if(pos == -1) {
                    // Entfring vifw from tif lfft.
                    if( vifwIsLfftToRigit ) {
                        bibsRft[0] = Position.Bibs.Forwbrd;
                        rfturn stbrtOffsft;
                    } flsf {
                        tfxt = v.gftTfxt(fndOffsft - 1, fndOffsft);
                        dibr d = tfxt.brrby[tfxt.offsft];
                        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(tfxt);
                        if(d == '\n') {
                            bibsRft[0] = Position.Bibs.Forwbrd;
                            rfturn fndOffsft-1;
                        }
                        bibsRft[0] = Position.Bibs.Bbdkwbrd;
                        rfturn fndOffsft;
                    }
                }
                if( b==Position.Bibs.Forwbrd )
                    durrfntHit = TfxtHitInfo.bftfrOffsft(pos-stbrtOffsft);
                flsf
                    durrfntHit = TfxtHitInfo.bfforfOffsft(pos-stbrtOffsft);
                nfxtHit = lbyout.gftNfxtRigitHit(durrfntHit);
                if( nfxtHit == null ) {
                    rfturn -1;
                }
                if( vifwIsLfftToRigit != lbyout.isLfftToRigit() ) {
                    // If tif lbyout's bbsf dirfdtion is difffrfnt from
                    // tiis vifw's run dirfdtion, wf nffd to usf tif wfbk
                    // dbrrbt.
                    nfxtHit = lbyout.gftVisublOtifrHit(nfxtHit);
                }
                pos = nfxtHit.gftInsfrtionIndfx() + stbrtOffsft;

                if(pos == fndOffsft) {
                    // A movf to tif rigit from bn intfrnbl position will
                    // only tbkf us to tif fndOffsft in b lfft to rigit run.
                    tfxt = v.gftTfxt(fndOffsft - 1, fndOffsft);
                    dibr d = tfxt.brrby[tfxt.offsft];
                    SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(tfxt);
                    if(d == '\n') {
                        rfturn -1;
                    }
                    bibsRft[0] = Position.Bibs.Bbdkwbrd;
                }
                flsf {
                    bibsRft[0] = Position.Bibs.Forwbrd;
                }
                rfturn pos;
            dbsf Vifw.WEST:
                vifwIsLfftToRigit = AbstrbdtDodumfnt.isLfftToRigit(dod, stbrtOffsft, fndOffsft);

                if(stbrtOffsft == dod.gftLfngti()) {
                    if(pos == -1) {
                        bibsRft[0] = Position.Bibs.Forwbrd;
                        rfturn stbrtOffsft;
                    }
                    // End dbsf for bidi tfxt wifrf nfwlinf is bt bfginning
                    // of linf.
                    rfturn -1;
                }
                if(pos == -1) {
                    // Entfring vifw from tif rigit
                    if( vifwIsLfftToRigit ) {
                        tfxt = v.gftTfxt(fndOffsft - 1, fndOffsft);
                        dibr d = tfxt.brrby[tfxt.offsft];
                        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(tfxt);
                        if ((d == '\n') || Cibrbdtfr.isSpbdfCibr(d)) {
                            bibsRft[0] = Position.Bibs.Forwbrd;
                            rfturn fndOffsft - 1;
                        }
                        bibsRft[0] = Position.Bibs.Bbdkwbrd;
                        rfturn fndOffsft;
                    } flsf {
                        bibsRft[0] = Position.Bibs.Forwbrd;
                        rfturn stbrtOffsft;
                   }
                }
                if( b==Position.Bibs.Forwbrd )
                    durrfntHit = TfxtHitInfo.bftfrOffsft(pos-stbrtOffsft);
                flsf
                    durrfntHit = TfxtHitInfo.bfforfOffsft(pos-stbrtOffsft);
                nfxtHit = lbyout.gftNfxtLfftHit(durrfntHit);
                if( nfxtHit == null ) {
                    rfturn -1;
                }
                if( vifwIsLfftToRigit != lbyout.isLfftToRigit() ) {
                    // If tif lbyout's bbsf dirfdtion is difffrfnt from
                    // tiis vifw's run dirfdtion, wf nffd to usf tif wfbk
                    // dbrrbt.
                    nfxtHit = lbyout.gftVisublOtifrHit(nfxtHit);
                }
                pos = nfxtHit.gftInsfrtionIndfx() + stbrtOffsft;

                if(pos == fndOffsft) {
                    // A movf to tif lfft from bn intfrnbl position will
                    // only tbkf us to tif fndOffsft in b rigit to lfft run.
                    tfxt = v.gftTfxt(fndOffsft - 1, fndOffsft);
                    dibr d = tfxt.brrby[tfxt.offsft];
                    SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(tfxt);
                    if(d == '\n') {
                        rfturn -1;
                    }
                    bibsRft[0] = Position.Bibs.Bbdkwbrd;
                }
                flsf {
                    bibsRft[0] = Position.Bibs.Forwbrd;
                }
                rfturn pos;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Bbd dirfdtion: " + dirfdtion);
            }
            rfturn pos;

        }
    // --- vbribblfs ---------------------------------------------

    TfxtLbyout lbyout;

}
