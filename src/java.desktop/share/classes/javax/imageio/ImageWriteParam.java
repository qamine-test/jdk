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

pbdkbgf jbvbx.imbgfio;

import jbvb.bwt.Dimfnsion;
import jbvb.util.Lodblf;

/**
 * A dlbss dfsdribing iow b strfbm is to bf fndodfd.  Instbndfs of
 * tiis dlbss or its subdlbssfs brf usfd to supply prfsdriptivf
 * "iow-to" informbtion to instbndfs of <dodf>ImbgfWritfr</dodf>.
 *
 * <p> A plug-in for b spfdifid imbgf formbt mby dffinf b subdlbss of
 * tiis dlbss, bnd rfturn objfdts of tibt dlbss from tif
 * <dodf>gftDffbultWritfPbrbm</dodf> mftiod of its
 * <dodf>ImbgfWritfr</dodf> implfmfntbtion.  For fxbmplf, tif built-in
 * JPEG writfr plug-in will rfturn instbndfs of
 * <dodf>jbvbx.imbgfio.plugins.jpfg.JPEGImbgfWritfPbrbm</dodf>.
 *
 * <p> Tif rfgion of tif imbgf to bf writtfn is dftfrminfd by first
 * intfrsfdting tif bdtubl bounds of tif imbgf witi tif rfdtbnglf
 * spfdififd by <dodf>IIOPbrbm.sftSourdfRfgion</dodf>, if bny.  If tif
 * rfsulting rfdtbnglf ibs b widti or ifigit of zfro, tif writfr will
 * tirow bn <dodf>IIOExdfption</dodf>. If tif intfrsfdtion is
 * non-fmpty, writing will dommfndf witi tif first subsbmplfd pixfl
 * bnd indludf bdditionbl pixfls witiin tif intfrsfdtfd bounds
 * bddording to tif iorizontbl bnd vfrtidbl subsbmpling fbdtors
 * spfdififd by {@link IIOPbrbm#sftSourdfSubsbmpling
 * IIOPbrbm.sftSourdfSubsbmpling}.
 *
 * <p> Individubl ffbturfs sudi bs tiling, progrfssivf fndoding, bnd
 * domprfssion mby bf sft in onf of four modfs.
 * <dodf>MODE_DISABLED</dodf> disbblfs tif ffbturfs;
 * <dodf>MODE_DEFAULT</dodf> fnbblfs tif ffbturf witi
 * writfr-dontrollfd pbrbmftfr vblufs; <dodf>MODE_EXPLICIT</dodf>
 * fnbblfs tif ffbturf bnd bllows tif usf of b <dodf>sft</dodf> mftiod
 * to providf bdditionbl pbrbmftfrs; bnd
 * <dodf>MODE_COPY_FROM_METADATA</dodf> dopifs rflfvbnt pbrbmftfr
 * vblufs from tif strfbm bnd imbgf mftbdbtb objfdts pbssfd to tif
 * writfr.  Tif dffbult for bll ffbturfs is
 * <dodf>MODE_COPY_FROM_METADATA</dodf>.  Non-stbndbrd ffbturfs
 * supplifd in subdlbssfs brf fndourbgfd, but not rfquirfd to usf b
 * similbr sdifmf.
 *
 * <p> Plug-in writfrs mby fxtfnd tif fundtionblity of
 * <dodf>ImbgfWritfPbrbm</dodf> by providing b subdlbss tibt implfmfnts
 * bdditionbl, plug-in spfdifid intfrfbdfs.  It is up to tif plug-in
 * to dodumfnt wibt intfrfbdfs brf bvbilbblf bnd iow tify brf to bf
 * usfd.  Writfrs will silfntly ignorf bny fxtfndfd ffbturfs of bn
 * <dodf>ImbgfWritfPbrbm</dodf> subdlbss of wiidi tify brf not bwbrf.
 * Also, tify mby ignorf bny optionbl ffbturfs tibt tify normblly
 * disbblf wifn drfbting tifir own <dodf>ImbgfWritfPbrbm</dodf>
 * instbndfs vib <dodf>gftDffbultWritfPbrbm</dodf>.
 *
 * <p> Notf tibt unlfss b qufry mftiod fxists for b dbpbbility, it must
 * bf supportfd by bll <dodf>ImbgfWritfr</dodf> implfmfntbtions
 * (<i>f.g.</i> progrfssivf fndoding is optionbl, but subsbmpling must bf
 * supportfd).
 *
 *
 * @sff ImbgfRfbdPbrbm
 */
publid dlbss ImbgfWritfPbrbm fxtfnds IIOPbrbm {

    /**
     * A donstbnt vbluf tibt mby bf pbssfd into mftiods sudi bs
     * <dodf>sftTilingModf</dodf>, <dodf>sftProgrfssivfModf</dodf>,
     * bnd <dodf>sftComprfssionModf</dodf> to disbblf b ffbturf for
     * futurf writfs.  Tibt is, wifn tiis modf is sft tif strfbm will
     * <b>not</b> bf tilfd, progrfssivf, or domprfssfd, bnd tif
     * rflfvbnt bddfssor mftiods will tirow bn
     * <dodf>IllfgblStbtfExdfption</dodf>.
     *
     * @sff #MODE_EXPLICIT
     * @sff #MODE_COPY_FROM_METADATA
     * @sff #MODE_DEFAULT
     * @sff #sftProgrfssivfModf
     * @sff #gftProgrfssivfModf
     * @sff #sftTilingModf
     * @sff #gftTilingModf
     * @sff #sftComprfssionModf
     * @sff #gftComprfssionModf
     */
    publid stbtid finbl int MODE_DISABLED = 0;

    /**
     * A donstbnt vbluf tibt mby bf pbssfd into mftiods sudi bs
     * <dodf>sftTilingModf</dodf>,
     * <dodf>sftProgrfssivfModf</dodf>, bnd
     * <dodf>sftComprfssionModf</dodf> to fnbblf tibt ffbturf for
     * futurf writfs.  Tibt is, wifn tiis modf is fnbblfd tif strfbm
     * will bf tilfd, progrfssivf, or domprfssfd bddording to b
     * sfnsiblf dffbult diosfn intfrnblly by tif writfr in b plug-in
     * dfpfndfnt wby, bnd tif rflfvbnt bddfssor mftiods will
     * tirow bn <dodf>IllfgblStbtfExdfption</dodf>.
     *
     * @sff #MODE_DISABLED
     * @sff #MODE_EXPLICIT
     * @sff #MODE_COPY_FROM_METADATA
     * @sff #sftProgrfssivfModf
     * @sff #gftProgrfssivfModf
     * @sff #sftTilingModf
     * @sff #gftTilingModf
     * @sff #sftComprfssionModf
     * @sff #gftComprfssionModf
     */
    publid stbtid finbl int MODE_DEFAULT = 1;

    /**
     * A donstbnt vbluf tibt mby bf pbssfd into mftiods sudi bs
     * <dodf>sftTilingModf</dodf> or <dodf>sftComprfssionModf</dodf>
     * to fnbblf b ffbturf for futurf writfs. Tibt is, wifn tiis modf
     * is sft tif strfbm will bf tilfd or domprfssfd bddording to
     * bdditionbl informbtion supplifd to tif dorrfsponding
     * <dodf>sft</dodf> mftiods in tiis dlbss bnd rftrifvbblf from tif
     * dorrfsponding <dodf>gft</dodf> mftiods.  Notf tibt tiis modf is
     * not supportfd for progrfssivf output.
     *
     * @sff #MODE_DISABLED
     * @sff #MODE_COPY_FROM_METADATA
     * @sff #MODE_DEFAULT
     * @sff #sftProgrfssivfModf
     * @sff #gftProgrfssivfModf
     * @sff #sftTilingModf
     * @sff #gftTilingModf
     * @sff #sftComprfssionModf
     * @sff #gftComprfssionModf
     */
    publid stbtid finbl int MODE_EXPLICIT = 2;

    /**
     * A donstbnt vbluf tibt mby bf pbssfd into mftiods sudi bs
     * <dodf>sftTilingModf</dodf>, <dodf>sftProgrfssivfModf</dodf>, or
     * <dodf>sftComprfssionModf</dodf> to fnbblf tibt ffbturf for
     * futurf writfs.  Tibt is, wifn tiis modf is fnbblfd tif strfbm
     * will bf tilfd, progrfssivf, or domprfssfd bbsfd on tif dontfnts
     * of strfbm bnd/or imbgf mftbdbtb pbssfd into tif writf
     * opfrbtion, bnd bny rflfvbnt bddfssor mftiods will tirow bn
     * <dodf>IllfgblStbtfExdfption</dodf>.
     *
     * <p> Tiis is tif dffbult modf for bll ffbturfs, so tibt b rfbd
     * indluding mftbdbtb followfd by b writf indluding mftbdbtb will
     * prfsfrvf bs mudi informbtion bs possiblf.
     *
     * @sff #MODE_DISABLED
     * @sff #MODE_EXPLICIT
     * @sff #MODE_DEFAULT
     * @sff #sftProgrfssivfModf
     * @sff #gftProgrfssivfModf
     * @sff #sftTilingModf
     * @sff #gftTilingModf
     * @sff #sftComprfssionModf
     * @sff #gftComprfssionModf
     */
    publid stbtid finbl int MODE_COPY_FROM_METADATA = 3;

    // If morf modfs brf bddfd, tiis siould bf updbtfd.
    privbtf stbtid finbl int MAX_MODE = MODE_COPY_FROM_METADATA;

    /**
     * A <dodf>boolfbn</dodf> tibt is <dodf>truf</dodf> if tiis
     * <dodf>ImbgfWritfPbrbm</dodf> bllows tilf widti bnd tilf ifigit
     * pbrbmftfrs to bf sft.  By dffbult, tif vbluf is
     * <dodf>fblsf</dodf>.  Subdlbssfs must sft tif vbluf mbnublly.
     *
     * <p> Subdlbssfs tibt do not support writing tilfs siould fnsurf
     * tibt tiis vbluf is sft to <dodf>fblsf</dodf>.
     */
    protfdtfd boolfbn dbnWritfTilfs = fblsf;

    /**
     * Tif modf dontrolling tiling sfttings, wiidi Must bf
     * sft to onf of tif four <dodf>MODE_*</dodf> vblufs.  Tif dffbult
     * is <dodf>MODE_COPY_FROM_METADATA</dodf>.
     *
     * <p> Subdlbssfs tibt do not writing tilfs mby ignorf tiis vbluf.
     *
     * @sff #MODE_DISABLED
     * @sff #MODE_EXPLICIT
     * @sff #MODE_COPY_FROM_METADATA
     * @sff #MODE_DEFAULT
     * @sff #sftTilingModf
     * @sff #gftTilingModf
     */
    protfdtfd int tilingModf = MODE_COPY_FROM_METADATA;

    /**
     * An brrby of prfffrrfd tilf sizf rbngf pbirs.  Tif dffbult vbluf
     * is <dodf>null</dodf>, wiidi indidbtfs tibt tifrf brf no
     * prfffrrfd sizfs.  If tif vbluf is non-<dodf>null</dodf>, it
     * must ibvf bn fvfn lfngti of bt lfbst two.
     *
     * <p> Subdlbssfs tibt do not support writing tilfs mby ignorf
     * tiis vbluf.
     *
     * @sff #gftPrfffrrfdTilfSizfs
     */
    protfdtfd Dimfnsion[] prfffrrfdTilfSizfs = null;

    /**
     * A <dodf>boolfbn</dodf> tibt is <dodf>truf</dodf> if tiling
     * pbrbmftfrs ibvf bffn spfdififd.
     *
     * <p> Subdlbssfs tibt do not support writing tilfs mby ignorf
     * tiis vbluf.
     */
    protfdtfd boolfbn tilingSft = fblsf;

    /**
     * Tif widti of fbdi tilf if tiling ibs bffn sft, or 0 otifrwisf.
     *
     * <p> Subdlbssfs tibt do not support tiling mby ignorf tiis
     * vbluf.
     */
    protfdtfd int tilfWidti = 0;

    /**
     * Tif ifigit of fbdi tilf if tiling ibs bffn sft, or 0 otifrwisf.
     * Tif initibl vbluf is <dodf>0</dodf>.
     *
     * <p> Subdlbssfs tibt do not support tiling mby ignorf tiis
     * vbluf.
     */
    protfdtfd int tilfHfigit = 0;

    /**
     * A <dodf>boolfbn</dodf> tibt is <dodf>truf</dodf> if tiis
     * <dodf>ImbgfWritfPbrbm</dodf> bllows tiling grid offsft
     * pbrbmftfrs to bf sft.  By dffbult, tif vbluf is
     * <dodf>fblsf</dodf>.  Subdlbssfs must sft tif vbluf mbnublly.
     *
     * <p> Subdlbssfs tibt do not support writing tilfs, or tibt
     * support writing but not offsftting tilfs must fnsurf tibt tiis
     * vbluf is sft to <dodf>fblsf</dodf>.
     */
    protfdtfd boolfbn dbnOffsftTilfs = fblsf;

    /**
     * Tif bmount by wiidi tif tilf grid origin siould bf offsft
     * iorizontblly from tif imbgf origin if tiling ibs bffn sft,
     * or 0 otifrwisf.  Tif initibl vbluf is <dodf>0</dodf>.
     *
     * <p> Subdlbssfs tibt do not support offsftting tilfs mby ignorf
     * tiis vbluf.
     */
    protfdtfd int tilfGridXOffsft = 0;

    /**
     * Tif bmount by wiidi tif tilf grid origin siould bf offsft
     * vfrtidblly from tif imbgf origin if tiling ibs bffn sft,
     * or 0 otifrwisf.  Tif initibl vbluf is <dodf>0</dodf>.
     *
     * <p> Subdlbssfs tibt do not support offsftting tilfs mby ignorf
     * tiis vbluf.
     */
    protfdtfd int tilfGridYOffsft = 0;

    /**
     * A <dodf>boolfbn</dodf> tibt is <dodf>truf</dodf> if tiis
     * <dodf>ImbgfWritfPbrbm</dodf> bllows imbgfs to bf writtfn bs b
     * progrfssivf sfqufndf of indrfbsing qublity pbssfs.  By dffbult,
     * tif vbluf is <dodf>fblsf</dodf>.  Subdlbssfs must sft tif vbluf
     * mbnublly.
     *
     * <p> Subdlbssfs tibt do not support progrfssivf fndoding must
     * fnsurf tibt tiis vbluf is sft to <dodf>fblsf</dodf>.
     */
    protfdtfd boolfbn dbnWritfProgrfssivf = fblsf;

    /**
     * Tif modf dontrolling progrfssivf fndoding, wiidi must bf sft to
     * onf of tif four <dodf>MODE_*</dodf> vblufs, fxdfpt
     * <dodf>MODE_EXPLICIT</dodf>.  Tif dffbult is
     * <dodf>MODE_COPY_FROM_METADATA</dodf>.
     *
     * <p> Subdlbssfs tibt do not support progrfssivf fndoding mby
     * ignorf tiis vbluf.
     *
     * @sff #MODE_DISABLED
     * @sff #MODE_EXPLICIT
     * @sff #MODE_COPY_FROM_METADATA
     * @sff #MODE_DEFAULT
     * @sff #sftProgrfssivfModf
     * @sff #gftProgrfssivfModf
     */
    protfdtfd int progrfssivfModf = MODE_COPY_FROM_METADATA;

    /**
     * A <dodf>boolfbn</dodf> tibt is <dodf>truf</dodf> if tiis writfr
     * dbn writf imbgfs using domprfssion. By dffbult, tif vbluf is
     * <dodf>fblsf</dodf>.  Subdlbssfs must sft tif vbluf mbnublly.
     *
     * <p> Subdlbssfs tibt do not support domprfssion must fnsurf tibt
     * tiis vbluf is sft to <dodf>fblsf</dodf>.
     */
    protfdtfd boolfbn dbnWritfComprfssfd = fblsf;

    /**
     * Tif modf dontrolling domprfssion sfttings, wiidi must bf sft to
     * onf of tif four <dodf>MODE_*</dodf> vblufs.  Tif dffbult is
     * <dodf>MODE_COPY_FROM_METADATA</dodf>.
     *
     * <p> Subdlbssfs tibt do not support domprfssion mby ignorf tiis
     * vbluf.
     *
     * @sff #MODE_DISABLED
     * @sff #MODE_EXPLICIT
     * @sff #MODE_COPY_FROM_METADATA
     * @sff #MODE_DEFAULT
     * @sff #sftComprfssionModf
     * @sff #gftComprfssionModf
     */
    protfdtfd int domprfssionModf = MODE_COPY_FROM_METADATA;

    /**
     * An brrby of <dodf>String</dodf>s dontbining tif nbmfs of tif
     * bvbilbblf domprfssion typfs.  Subdlbssfs must sft tif vbluf
     * mbnublly.
     *
     * <p> Subdlbssfs tibt do not support domprfssion mby ignorf tiis
     * vbluf.
     */
    protfdtfd String[] domprfssionTypfs = null;

    /**
     * A <dodf>String</dodf> dontbining tif nbmf of tif durrfnt
     * domprfssion typf, or <dodf>null</dodf> if nonf is sft.
     *
     * <p> Subdlbssfs tibt do not support domprfssion mby ignorf tiis
     * vbluf.
     */
    protfdtfd String domprfssionTypf = null;

    /**
     * A <dodf>flobt</dodf> dontbining tif durrfnt domprfssion qublity
     * sftting.  Tif initibl vbluf is <dodf>1.0F</dodf>.
     *
     * <p> Subdlbssfs tibt do not support domprfssion mby ignorf tiis
     * vbluf.
     */
    protfdtfd flobt domprfssionQublity = 1.0F;

    /**
     * A <dodf>Lodblf</dodf> to bf usfd to lodblizf domprfssion typf
     * nbmfs bnd qublity dfsdriptions, or <dodf>null</dodf> to usf b
     * dffbult <dodf>Lodblf</dodf>.  Subdlbssfs must sft tif vbluf
     * mbnublly.
     */
    protfdtfd Lodblf lodblf = null;

    /**
     * Construdts bn fmpty <dodf>ImbgfWritfPbrbm</dodf>.  It is up to
     * tif subdlbss to sft up tif instbndf vbribblfs propfrly.
     */
    protfdtfd ImbgfWritfPbrbm() {}

    /**
     * Construdts bn <dodf>ImbgfWritfPbrbm</dodf> sft to usf b
     * givfn <dodf>Lodblf</dodf>.
     *
     * @pbrbm lodblf b <dodf>Lodblf</dodf> to bf usfd to lodblizf
     * domprfssion typf nbmfs bnd qublity dfsdriptions, or
     * <dodf>null</dodf>.
     */
    publid ImbgfWritfPbrbm(Lodblf lodblf) {
        tiis.lodblf = lodblf;
    }

    // Rfturn b dffp dopy of tif brrby
    privbtf stbtid Dimfnsion[] dlonfPrfffrrfdTilfSizfs(Dimfnsion[] sizfs) {
        if (sizfs == null) {
            rfturn null;
        }
        Dimfnsion[] tfmp = nfw Dimfnsion[sizfs.lfngti];
        for (int i = 0; i < sizfs.lfngti; i++) {
            tfmp[i] = nfw Dimfnsion(sizfs[i]);
        }
        rfturn tfmp;
    }

    /**
     * Rfturns tif durrfntly sft <dodf>Lodblf</dodf>, or
     * <dodf>null</dodf> if only b dffbult <dodf>Lodblf</dodf> is
     * supportfd.
     *
     * @rfturn tif durrfnt <dodf>Lodblf</dodf>, or <dodf>null</dodf>.
     */
    publid Lodblf gftLodblf() {
        rfturn lodblf;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif writfr dbn pfrform tiling
     * wiilf writing.  If tiis mftiod rfturns <dodf>fblsf</dodf>, tifn
     * <dodf>sftTiling</dodf> will tirow bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif writfr supports tiling.
     *
     * @sff #dbnOffsftTilfs()
     * @sff #sftTiling(int, int, int, int)
     */
    publid boolfbn dbnWritfTilfs() {
        rfturn dbnWritfTilfs;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif writfr dbn pfrform tiling witi
     * non-zfro grid offsfts wiilf writing.  If tiis mftiod rfturns
     * <dodf>fblsf</dodf>, tifn <dodf>sftTiling</dodf> will tirow bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf> if tif grid offsft
     * brgumfnts brf not boti zfro.  If <dodf>dbnWritfTilfs</dodf>
     * rfturns <dodf>fblsf</dodf>, tiis mftiod will rfturn
     * <dodf>fblsf</dodf> bs wfll.
     *
     * @rfturn <dodf>truf</dodf> if tif writfr supports non-zfro tilf
     * offsfts.
     *
     * @sff #dbnWritfTilfs()
     * @sff #sftTiling(int, int, int, int)
     */
    publid boolfbn dbnOffsftTilfs() {
        rfturn dbnOffsftTilfs;
    }

    /**
     * Dftfrminfs wiftifr tif imbgf will bf tilfd in tif output
     * strfbm bnd, if it will, iow tif tiling pbrbmftfrs will bf
     * dftfrminfd.  Tif modfs brf intfrprftfd bs follows:
     *
     * <ul>
     *
     * <li><dodf>MODE_DISABLED</dodf> - Tif imbgf will not bf tilfd.
     * <dodf>sftTiling</dodf> will tirow bn
     * <dodf>IllfgblStbtfExdfption</dodf>.
     *
     * <li><dodf>MODE_DEFAULT</dodf> - Tif imbgf will bf tilfd using
     * dffbult pbrbmftfrs.  <dodf>sftTiling</dodf> will tirow bn
     * <dodf>IllfgblStbtfExdfption</dodf>.
     *
     * <li><dodf>MODE_EXPLICIT</dodf> - Tif imbgf will bf tilfd
     * bddording to pbrbmftfrs givfn in tif {@link #sftTiling sftTiling}
     * mftiod.  Any prfviously sft tiling pbrbmftfrs brf disdbrdfd.
     *
     * <li><dodf>MODE_COPY_FROM_METADATA</dodf> - Tif imbgf will
     * donform to tif mftbdbtb objfdt pbssfd in to b writf.
     * <dodf>sftTiling</dodf> will tirow bn
     * <dodf>IllfgblStbtfExdfption</dodf>.
     *
     * </ul>
     *
     * @pbrbm modf Tif modf to usf for tiling.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if
     * <dodf>dbnWritfTilfs</dodf> rfturns <dodf>fblsf</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>modf</dodf> is not
     * onf of tif modfs listfd bbovf.
     *
     * @sff #sftTiling
     * @sff #gftTilingModf
     */
    publid void sftTilingModf(int modf) {
        if (dbnWritfTilfs() == fblsf) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiling not supportfd!");
        }
        if (modf < MODE_DISABLED || modf > MAX_MODE) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl vbluf for modf!");
        }
        tiis.tilingModf = modf;
        if (modf == MODE_EXPLICIT) {
            unsftTiling();
        }
    }

    /**
     * Rfturns tif durrfnt tiling modf, if tiling is supportfd.
     * Otifrwisf tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     *
     * @rfturn tif durrfnt tiling modf.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if
     * <dodf>dbnWritfTilfs</dodf> rfturns <dodf>fblsf</dodf>.
     *
     * @sff #sftTilingModf
     */
    publid int gftTilingModf() {
        if (!dbnWritfTilfs()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiling not supportfd");
        }
        rfturn tilingModf;
    }

    /**
     * Rfturns bn brrby of <dodf>Dimfnsion</dodf>s indidbting tif
     * lfgbl sizf rbngfs for tilfs bs tify will bf fndodfd in tif
     * output filf or strfbm.  Tif rfturnfd brrby is b dopy.
     *
     * <p> Tif informbtion is rfturnfd bs b sft of pbirs; tif first
     * flfmfnt of b pbir dontbins bn (indlusivf) minimum widti bnd
     * ifigit, bnd tif sfdond flfmfnt dontbins bn (indlusivf) mbximum
     * widti bnd ifigit.  Togftifr, fbdi pbir dffinfs b vblid rbngf of
     * sizfs.  To spfdify b fixfd sizf, usf tif sbmf widti bnd ifigit
     * for boti flfmfnts.  To spfdify bn brbitrbry rbngf, b vbluf of
     * <dodf>null</dodf> is usfd in plbdf of bn bdtubl brrby of
     * <dodf>Dimfnsion</dodf>s.
     *
     * <p> If no brrby is spfdififd on tif donstrudtor, but tiling is
     * bllowfd, tifn tiis mftiod rfturns <dodf>null</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif plug-in dofs
     * not support tiling.
     *
     * @rfturn bn brrby of <dodf>Dimfnsion</dodf>s witi bn fvfn lfngti
     * of bt lfbst two, or <dodf>null</dodf>.
     */
    publid Dimfnsion[] gftPrfffrrfdTilfSizfs() {
        if (!dbnWritfTilfs()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiling not supportfd");
        }
        rfturn dlonfPrfffrrfdTilfSizfs(prfffrrfdTilfSizfs);
    }

    /**
     * Spfdififs tibt tif imbgf siould bf tilfd in tif output strfbm.
     * Tif <dodf>tilfWidti</dodf> bnd <dodf>tilfHfigit</dodf>
     * pbrbmftfrs spfdify tif widti bnd ifigit of tif tilfs in tif
     * filf.  If tif tilf widti or ifigit is grfbtfr tibn tif widti or
     * ifigit of tif imbgf, tif imbgf is not tilfd in tibt dimfnsion.
     *
     * <p> If <dodf>dbnOffsftTilfs</dodf> rfturns <dodf>fblsf</dodf>,
     * tifn tif <dodf>tilfGridXOffsft</dodf> bnd
     * <dodf>tilfGridYOffsft</dodf> pbrbmftfrs must bf zfro.
     *
     * @pbrbm tilfWidti tif widti of fbdi tilf.
     * @pbrbm tilfHfigit tif ifigit of fbdi tilf.
     * @pbrbm tilfGridXOffsft tif iorizontbl offsft of tif tilf grid.
     * @pbrbm tilfGridYOffsft tif vfrtidbl offsft of tif tilf grid.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif plug-in dofs not
     * support tiling.
     * @fxdfption IllfgblStbtfExdfption if tif tiling modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif plug-in dofs not
     * support grid offsfts, bnd tif grid offsfts brf not boti zfro.
     * @fxdfption IllfgblArgumfntExdfption if tif tilf sizf is not
     * witiin onf of tif bllowbblf rbngfs rfturnfd by
     * <dodf>gftPrfffrrfdTilfSizfs</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>tilfWidti</dodf>
     * or <dodf>tilfHfigit</dodf> is lfss tibn or fqubl to 0.
     *
     * @sff #dbnWritfTilfs
     * @sff #dbnOffsftTilfs
     * @sff #gftTilfWidti()
     * @sff #gftTilfHfigit()
     * @sff #gftTilfGridXOffsft()
     * @sff #gftTilfGridYOffsft()
     */
    publid void sftTiling(int tilfWidti,
                          int tilfHfigit,
                          int tilfGridXOffsft,
                          int tilfGridYOffsft) {
        if (!dbnWritfTilfs()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiling not supportfd!");
        }
        if (gftTilingModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption("Tiling modf not MODE_EXPLICIT!");
        }
        if (tilfWidti <= 0 || tilfHfigit <= 0) {
            tirow nfw IllfgblArgumfntExdfption
                ("tilf dimfnsions brf non-positivf!");
        }
        boolfbn tilfsOffsft = (tilfGridXOffsft != 0) || (tilfGridYOffsft != 0);
        if (!dbnOffsftTilfs() && tilfsOffsft) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Cbn't offsft tilfs!");
        }
        if (prfffrrfdTilfSizfs != null) {
            boolfbn ok = truf;
            for (int i = 0; i < prfffrrfdTilfSizfs.lfngti; i += 2) {
                Dimfnsion min = prfffrrfdTilfSizfs[i];
                Dimfnsion mbx = prfffrrfdTilfSizfs[i+1];
                if ((tilfWidti < min.widti) ||
                    (tilfWidti > mbx.widti) ||
                    (tilfHfigit < min.ifigit) ||
                    (tilfHfigit > mbx.ifigit)) {
                    ok = fblsf;
                    brfbk;
                }
            }
            if (!ok) {
                tirow nfw IllfgblArgumfntExdfption("Illfgbl tilf sizf!");
            }
        }

        tiis.tilingSft = truf;
        tiis.tilfWidti = tilfWidti;
        tiis.tilfHfigit = tilfHfigit;
        tiis.tilfGridXOffsft = tilfGridXOffsft;
        tiis.tilfGridYOffsft = tilfGridYOffsft;
    }

    /**
     * Rfmovfs bny prfvious tilf grid pbrbmftfrs spfdififd by dblls to
     * <dodf>sftTiling</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion sfts tif instbndf vbribblfs
     * <dodf>tilfWidti</dodf>, <dodf>tilfHfigit</dodf>,
     * <dodf>tilfGridXOffsft</dodf>, bnd
     * <dodf>tilfGridYOffsft</dodf> to <dodf>0</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif plug-in dofs not
     * support tiling.
     * @fxdfption IllfgblStbtfExdfption if tif tiling modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     *
     * @sff #sftTiling(int, int, int, int)
     */
    publid void unsftTiling() {
        if (!dbnWritfTilfs()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiling not supportfd!");
        }
        if (gftTilingModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption("Tiling modf not MODE_EXPLICIT!");
        }
        tiis.tilingSft = fblsf;
        tiis.tilfWidti = 0;
        tiis.tilfHfigit = 0;
        tiis.tilfGridXOffsft = 0;
        tiis.tilfGridYOffsft = 0;
    }

    /**
     * Rfturns tif widti of fbdi tilf in bn imbgf bs it will bf
     * writtfn to tif output strfbm.  If tiling pbrbmftfrs ibvf not
     * bffn sft, bn <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     *
     * @rfturn tif tilf widti to bf usfd for fndoding.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif plug-in dofs not
     * support tiling.
     * @fxdfption IllfgblStbtfExdfption if tif tiling modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if tif tiling pbrbmftfrs ibvf
     * not bffn sft.
     *
     * @sff #sftTiling(int, int, int, int)
     * @sff #gftTilfHfigit()
     */
    publid int gftTilfWidti() {
        if (!dbnWritfTilfs()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiling not supportfd!");
        }
        if (gftTilingModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption("Tiling modf not MODE_EXPLICIT!");
        }
        if (!tilingSft) {
            tirow nfw IllfgblStbtfExdfption("Tiling pbrbmftfrs not sft!");
        }
        rfturn tilfWidti;
    }

    /**
     * Rfturns tif ifigit of fbdi tilf in bn imbgf bs it will bf writtfn to
     * tif output strfbm.  If tiling pbrbmftfrs ibvf not
     * bffn sft, bn <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     *
     * @rfturn tif tilf ifigit to bf usfd for fndoding.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif plug-in dofs not
     * support tiling.
     * @fxdfption IllfgblStbtfExdfption if tif tiling modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if tif tiling pbrbmftfrs ibvf
     * not bffn sft.
     *
     * @sff #sftTiling(int, int, int, int)
     * @sff #gftTilfWidti()
     */
    publid int gftTilfHfigit() {
        if (!dbnWritfTilfs()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiling not supportfd!");
        }
        if (gftTilingModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption("Tiling modf not MODE_EXPLICIT!");
        }
        if (!tilingSft) {
            tirow nfw IllfgblStbtfExdfption("Tiling pbrbmftfrs not sft!");
        }
        rfturn tilfHfigit;
    }

    /**
     * Rfturns tif iorizontbl tilf grid offsft of bn imbgf bs it will
     * bf writtfn to tif output strfbm.  If tiling pbrbmftfrs ibvf not
     * bffn sft, bn <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     *
     * @rfturn tif tilf grid X offsft to bf usfd for fndoding.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif plug-in dofs not
     * support tiling.
     * @fxdfption IllfgblStbtfExdfption if tif tiling modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if tif tiling pbrbmftfrs ibvf
     * not bffn sft.
     *
     * @sff #sftTiling(int, int, int, int)
     * @sff #gftTilfGridYOffsft()
     */
    publid int gftTilfGridXOffsft() {
        if (!dbnWritfTilfs()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiling not supportfd!");
        }
        if (gftTilingModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption("Tiling modf not MODE_EXPLICIT!");
        }
        if (!tilingSft) {
            tirow nfw IllfgblStbtfExdfption("Tiling pbrbmftfrs not sft!");
        }
        rfturn tilfGridXOffsft;
    }

    /**
     * Rfturns tif vfrtidbl tilf grid offsft of bn imbgf bs it will
     * bf writtfn to tif output strfbm.  If tiling pbrbmftfrs ibvf not
     * bffn sft, bn <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     *
     * @rfturn tif tilf grid Y offsft to bf usfd for fndoding.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif plug-in dofs not
     * support tiling.
     * @fxdfption IllfgblStbtfExdfption if tif tiling modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if tif tiling pbrbmftfrs ibvf
     * not bffn sft.
     *
     * @sff #sftTiling(int, int, int, int)
     * @sff #gftTilfGridXOffsft()
     */
    publid int gftTilfGridYOffsft() {
        if (!dbnWritfTilfs()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiling not supportfd!");
        }
        if (gftTilingModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption("Tiling modf not MODE_EXPLICIT!");
        }
        if (!tilingSft) {
            tirow nfw IllfgblStbtfExdfption("Tiling pbrbmftfrs not sft!");
        }
        rfturn tilfGridYOffsft;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif writfr dbn writf out imbgfs
     * bs b sfrifs of pbssfs of progrfssivfly indrfbsing qublity.
     *
     * @rfturn <dodf>truf</dodf> if tif writfr supports progrfssivf
     * fndoding.
     *
     * @sff #sftProgrfssivfModf
     * @sff #gftProgrfssivfModf
     */
    publid boolfbn dbnWritfProgrfssivf() {
        rfturn dbnWritfProgrfssivf;
    }

    /**
     * Spfdififs tibt tif writfr is to writf tif imbgf out in b
     * progrfssivf modf sudi tibt tif strfbm will dontbin b sfrifs of
     * sdbns of indrfbsing qublity.  If progrfssivf fndoding is not
     * supportfd, bn <dodf>UnsupportfdOpfrbtionExdfption</dodf> will
     * bf tirown.
     *
     * <p>  Tif modf brgumfnt dftfrminfs iow
     * tif progrfssion pbrbmftfrs brf diosfn, bnd must bf fitifr
     * <dodf>MODE_DISABLED</dodf>,
     * <dodf>MODE_COPY_FROM_METADATA</dodf>, or
     * <dodf>MODE_DEFAULT</dodf>.  Otifrwisf bn
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     *
     * <p> Tif modfs brf intfrprftfd bs follows:
     *
     * <ul>
     *   <li><dodf>MODE_DISABLED</dodf> - No progrfssion.  Usf tiis to
     *   turn off progrfssion.
     *
     *   <li><dodf>MODE_COPY_FROM_METADATA</dodf> - Tif output imbgf
     *   will usf wibtfvfr progrfssion pbrbmftfrs brf found in tif
     *   mftbdbtb objfdts pbssfd into tif writfr.
     *
     *   <li><dodf>MODE_DEFAULT</dodf> - Tif imbgf will bf writtfn
     *   progrfssivfly, witi pbrbmftfrs diosfn by tif writfr.
     * </ul>
     *
     * <p> Tif dffbult is <dodf>MODE_COPY_FROM_METADATA</dodf>.
     *
     * @pbrbm modf Tif modf for sftting progrfssion in tif output
     * strfbm.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support progrfssivf fndoding.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>modf</dodf> is not
     * onf of tif modfs listfd bbovf.
     *
     * @sff #gftProgrfssivfModf
     */
    publid void sftProgrfssivfModf(int modf) {
        if (!dbnWritfProgrfssivf()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Progrfssivf output not supportfd");
        }
        if (modf < MODE_DISABLED || modf > MAX_MODE) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl vbluf for modf!");
        }
        if (modf == MODE_EXPLICIT) {
            tirow nfw IllfgblArgumfntExdfption(
                "MODE_EXPLICIT not supportfd for progrfssivf output");
        }
        tiis.progrfssivfModf = modf;
    }

    /**
     * Rfturns tif durrfnt modf for writing tif strfbm in b
     * progrfssivf mbnnfr.
     *
     * @rfturn tif durrfnt modf for progrfssivf fndoding.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support progrfssivf fndoding.
     *
     * @sff #sftProgrfssivfModf
     */
    publid int gftProgrfssivfModf() {
        if (!dbnWritfProgrfssivf()) {
            tirow nfw UnsupportfdOpfrbtionExdfption
                ("Progrfssivf output not supportfd");
        }
        rfturn progrfssivfModf;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis writfr supports domprfssion.
     *
     * @rfturn <dodf>truf</dodf> if tif writfr supports domprfssion.
     */
    publid boolfbn dbnWritfComprfssfd() {
        rfturn dbnWritfComprfssfd;
    }

    /**
     * Spfdififs wiftifr domprfssion is to bf pfrformfd, bnd if so iow
     * domprfssion pbrbmftfrs brf to bf dftfrminfd.  Tif <dodf>modf</dodf>
     * brgumfnt must bf onf of tif four modfs, intfrprftfd bs follows:
     *
     * <ul>
     *   <li><dodf>MODE_DISABLED</dodf> - If tif modf is sft to
     *   <dodf>MODE_DISABLED</dodf>, mftiods tibt qufry or modify tif
     *   domprfssion typf or pbrbmftfrs will tirow bn
     *   <dodf>IllfgblStbtfExdfption</dodf> (if domprfssion is
     *   normblly supportfd by tif plug-in). Somf writfrs, sudi bs JPEG,
     *   do not normblly offfr undomprfssfd output. In tiis dbsf, bttfmpting
     *   to sft tif modf to <dodf>MODE_DISABLED</dodf> will tirow bn
     *   <dodf>UnsupportfdOpfrbtionExdfption</dodf> bnd tif modf will not bf
     *   dibngfd.
     *
     *   <li><dodf>MODE_EXPLICIT</dodf> - Comprfss using tif
     *   domprfssion typf bnd qublity sfttings spfdififd in tiis
     *   <dodf>ImbgfWritfPbrbm</dodf>.  Any prfviously sft domprfssion
     *   pbrbmftfrs brf disdbrdfd.
     *
     *   <li><dodf>MODE_COPY_FROM_METADATA</dodf> - Usf wibtfvfr
     *   domprfssion pbrbmftfrs brf spfdififd in mftbdbtb objfdts
     *   pbssfd in to tif writfr.
     *
     *   <li><dodf>MODE_DEFAULT</dodf> - Usf dffbult domprfssion
     *   pbrbmftfrs.
     * </ul>
     *
     * <p> Tif dffbult is <dodf>MODE_COPY_FROM_METADATA</dodf>.
     *
     * @pbrbm modf Tif modf for sftting domprfssion in tif output
     * strfbm.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion, or dofs not support tif rfqufstfd modf.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>modf</dodf> is not
     * onf of tif modfs listfd bbovf.
     *
     * @sff #gftComprfssionModf
     */
    publid void sftComprfssionModf(int modf) {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd.");
        }
        if (modf < MODE_DISABLED || modf > MAX_MODE) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl vbluf for modf!");
        }
        tiis.domprfssionModf = modf;
        if (modf == MODE_EXPLICIT) {
            unsftComprfssion();
        }
    }

    /**
     * Rfturns tif durrfnt domprfssion modf, if domprfssion is
     * supportfd.
     *
     * @rfturn tif durrfnt domprfssion modf.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     *
     * @sff #sftComprfssionModf
     */
    publid int gftComprfssionModf() {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd.");
        }
        rfturn domprfssionModf;
    }

    /**
     * Rfturns b list of bvbilbblf domprfssion typfs, bs bn brrby or
     * <dodf>String</dodf>s, or <dodf>null</dodf> if b domprfssion
     * typf mby not bf diosfn using tifsf intfrfbdfs.  Tif brrby
     * rfturnfd is b dopy.
     *
     * <p> If tif writfr only offfrs b singlf, mbndbtory form of
     * domprfssion, it is not nfdfssbry to providf bny nbmfd
     * domprfssion typfs.  Nbmfd domprfssion typfs siould only bf
     * usfd wifrf tif usfr is bblf to mbkf b mfbningful dioidf
     * bftwffn difffrfnt sdifmfs.
     *
     * <p> Tif dffbult implfmfntbtion difdks if domprfssion is
     * supportfd bnd tirows bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf> if not.  Otifrwisf,
     * it rfturns b dlonf of tif <dodf>domprfssionTypfs</dodf>
     * instbndf vbribblf if it is non-<dodf>null</dodf>, or flsf
     * rfturns <dodf>null</dodf>.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s dontbining tif
     * (non-lodblizfd) nbmfs of bvbilbblf domprfssion typfs, or
     * <dodf>null</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     */
    publid String[] gftComprfssionTypfs() {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd");
        }
        if (domprfssionTypfs == null) {
            rfturn null;
        }
        rfturn domprfssionTypfs.dlonf();
    }

    /**
     * Sfts tif domprfssion typf to onf of tif vblufs indidbtfd by
     * <dodf>gftComprfssionTypfs</dodf>.  If b vbluf of
     * <dodf>null</dodf> is pbssfd in, bny prfvious sftting is
     * rfmovfd.
     *
     * <p> Tif dffbult implfmfntbtion difdks wiftifr domprfssion is
     * supportfd bnd tif domprfssion modf is
     * <dodf>MODE_EXPLICIT</dodf>.  If so, it dblls
     * <dodf>gftComprfssionTypfs</dodf> bnd difdks if
     * <dodf>domprfssionTypf</dodf> is onf of tif lfgbl vblufs.  If it
     * is, tif <dodf>domprfssionTypf</dodf> instbndf vbribblf is sft.
     * If <dodf>domprfssionTypf</dodf> is <dodf>null</dodf>, tif
     * instbndf vbribblf is sft witiout pfrforming bny difdking.
     *
     * @pbrbm domprfssionTypf onf of tif <dodf>String</dodf>s rfturnfd
     * by <dodf>gftComprfssionTypfs</dodf>, or <dodf>null</dodf> to
     * rfmovf bny prfvious sftting.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption UnsupportfdOpfrbtionExdfption if tifrf brf no
     * sfttbblf domprfssion typfs.
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>domprfssionTypf</dodf> is non-<dodf>null</dodf> but is not
     * onf of tif vblufs rfturnfd by <dodf>gftComprfssionTypfs</dodf>.
     *
     * @sff #gftComprfssionTypfs
     * @sff #gftComprfssionTypf
     * @sff #unsftComprfssion
     */
    publid void sftComprfssionTypf(String domprfssionTypf) {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd");
        }
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        String[] lfgblTypfs = gftComprfssionTypfs();
        if (lfgblTypfs == null) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "No sfttbblf domprfssion typfs");
        }
        if (domprfssionTypf != null) {
            boolfbn found = fblsf;
            if (lfgblTypfs != null) {
                for (int i = 0; i < lfgblTypfs.lfngti; i++) {
                    if (domprfssionTypf.fqubls(lfgblTypfs[i])) {
                        found = truf;
                        brfbk;
                    }
                }
            }
            if (!found) {
                tirow nfw IllfgblArgumfntExdfption("Unknown domprfssion typf!");
            }
        }
        tiis.domprfssionTypf = domprfssionTypf;
    }

    /**
     * Rfturns tif durrfntly sft domprfssion typf, or
     * <dodf>null</dodf> if nonf ibs bffn sft.  Tif typf is rfturnfd
     * bs b <dodf>String</dodf> from bmong tiosf rfturnfd by
     * <dodf>gftComprfssionTypfs</dodf>.
     * If no domprfssion typf ibs bffn sft, <dodf>null</dodf> is
     * rfturnfd.
     *
     * <p> Tif dffbult implfmfntbtion difdks wiftifr domprfssion is
     * supportfd bnd tif domprfssion modf is
     * <dodf>MODE_EXPLICIT</dodf>.  If so, it rfturns tif vbluf of tif
     * <dodf>domprfssionTypf</dodf> instbndf vbribblf.
     *
     * @rfturn tif durrfnt domprfssion typf bs b <dodf>String</dodf>,
     * or <dodf>null</dodf> if no typf is sft.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     *
     * @sff #sftComprfssionTypf
     */
    publid String gftComprfssionTypf() {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd.");
        }
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        rfturn domprfssionTypf;
    }

    /**
     * Rfmovfs bny prfvious domprfssion typf bnd qublity sfttings.
     *
     * <p> Tif dffbult implfmfntbtion sfts tif instbndf vbribblf
     * <dodf>domprfssionTypf</dodf> to <dodf>null</dodf>, bnd tif
     * instbndf vbribblf <dodf>domprfssionQublity</dodf> to
     * <dodf>1.0F</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif plug-in dofs not
     * support domprfssion.
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     *
     * @sff #sftComprfssionTypf
     * @sff #sftComprfssionQublity
     */
    publid void unsftComprfssion() {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd");
        }
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        tiis.domprfssionTypf = null;
        tiis.domprfssionQublity = 1.0F;
    }

    /**
     * Rfturns b lodblizfd vfrsion of tif nbmf of tif durrfnt
     * domprfssion typf, using tif <dodf>Lodblf</dodf> rfturnfd by
     * <dodf>gftLodblf</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion difdks wiftifr domprfssion is
     * supportfd bnd tif domprfssion modf is
     * <dodf>MODE_EXPLICIT</dodf>.  If so, if
     * <dodf>domprfssionTypf</dodf> is <dodf>non-null</dodf> tif vbluf
     * of <dodf>gftComprfssionTypf</dodf> is rfturnfd bs b
     * donvfnifndf.
     *
     * @rfturn b <dodf>String</dodf> dontbining b lodblizfd vfrsion of
     * tif nbmf of tif durrfnt domprfssion typf.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if no domprfssion typf is sft.
     */
    publid String gftLodblizfdComprfssionTypfNbmf() {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd.");
        }
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        if (gftComprfssionTypf() == null) {
            tirow nfw IllfgblStbtfExdfption("No domprfssion typf sft!");
        }
        rfturn gftComprfssionTypf();
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif durrfnt domprfssion typf
     * providfs losslfss domprfssion.  If b plug-in providfs only
     * onf mbndbtory domprfssion typf, tifn tiis mftiod mby bf
     * dbllfd witiout dblling <dodf>sftComprfssionTypf</dodf> first.
     *
     * <p> If tifrf brf multiplf domprfssion typfs but nonf ibs
     * bffn sft, bn <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     *
     * <p> Tif dffbult implfmfntbtion difdks wiftifr domprfssion is
     * supportfd bnd tif domprfssion modf is
     * <dodf>MODE_EXPLICIT</dodf>.  If so, if
     * <dodf>gftComprfssionTypfs()</dodf> is <dodf>null</dodf> or
     * <dodf>gftComprfssionTypf()</dodf> is non-<dodf>null</dodf>
     * <dodf>truf</dodf> is rfturnfd bs b donvfnifndf.
     *
     * @rfturn <dodf>truf</dodf> if tif durrfnt domprfssion typf is
     * losslfss.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if tif sft of lfgbl
     * domprfssion typfs is non-<dodf>null</dodf> bnd tif durrfnt
     * domprfssion typf is <dodf>null</dodf>.
     */
    publid boolfbn isComprfssionLosslfss() {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd");
        }
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        if ((gftComprfssionTypfs() != null) &&
            (gftComprfssionTypf() == null)) {
            tirow nfw IllfgblStbtfExdfption("No domprfssion typf sft!");
        }
        rfturn truf;
    }

    /**
     * Sfts tif domprfssion qublity to b vbluf bftwffn <dodf>0</dodf>
     * bnd <dodf>1</dodf>.  Only b singlf domprfssion qublity sftting
     * is supportfd by dffbult; writfrs dbn providf fxtfndfd vfrsions
     * of <dodf>ImbgfWritfPbrbm</dodf> tibt offfr morf dontrol.  For
     * lossy domprfssion sdifmfs, tif domprfssion qublity siould
     * dontrol tif trbdfoff bftwffn filf sizf bnd imbgf qublity (for
     * fxbmplf, by dioosing qubntizbtion tbblfs wifn writing JPEG
     * imbgfs).  For losslfss sdifmfs, tif domprfssion qublity mby bf
     * usfd to dontrol tif trbdfoff bftwffn filf sizf bnd timf tbkfn
     * to pfrform tif domprfssion (for fxbmplf, by optimizing row
     * filtfrs bnd sftting tif ZLIB domprfssion lfvfl wifn writing
     * PNG imbgfs).
     *
     * <p> A domprfssion qublity sftting of 0.0 is most gfnfridblly
     * intfrprftfd bs "iigi domprfssion is importbnt," wiilf b sftting of
     * 1.0 is most gfnfridblly intfrprftfd bs "iigi imbgf qublity is
     * importbnt."
     *
     * <p> If tifrf brf multiplf domprfssion typfs but nonf ibs bffn
     * sft, bn <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     *
     * <p> Tif dffbult implfmfntbtion difdks tibt domprfssion is
     * supportfd, bnd tibt tif domprfssion modf is
     * <dodf>MODE_EXPLICIT</dodf>.  If so, if
     * <dodf>gftComprfssionTypfs()</dodf> rfturns <dodf>null</dodf> or
     * <dodf>domprfssionTypf</dodf> is non-<dodf>null</dodf> it sfts
     * tif <dodf>domprfssionQublity</dodf> instbndf vbribblf.
     *
     * @pbrbm qublity b <dodf>flobt</dodf> bftwffn <dodf>0</dodf>bnd
     * <dodf>1</dodf> indidbting tif dfsirfd qublity lfvfl.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if tif sft of lfgbl
     * domprfssion typfs is non-<dodf>null</dodf> bnd tif durrfnt
     * domprfssion typf is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>qublity</dodf> is
     * not bftwffn <dodf>0</dodf>bnd <dodf>1</dodf>, indlusivf.
     *
     * @sff #gftComprfssionQublity
     */
    publid void sftComprfssionQublity(flobt qublity) {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd");
        }
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        if (gftComprfssionTypfs() != null && gftComprfssionTypf() == null) {
            tirow nfw IllfgblStbtfExdfption("No domprfssion typf sft!");
        }
        if (qublity < 0.0F || qublity > 1.0F) {
            tirow nfw IllfgblArgumfntExdfption("Qublity out-of-bounds!");
        }
        tiis.domprfssionQublity = qublity;
    }

    /**
     * Rfturns tif durrfnt domprfssion qublity sftting.
     *
     * <p> If tifrf brf multiplf domprfssion typfs but nonf ibs bffn
     * sft, bn <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     *
     * <p> Tif dffbult implfmfntbtion difdks tibt domprfssion is
     * supportfd bnd tibt tif domprfssion modf is
     * <dodf>MODE_EXPLICIT</dodf>.  If so, if
     * <dodf>gftComprfssionTypfs()</dodf> is <dodf>null</dodf> or
     * <dodf>gftComprfssionTypf()</dodf> is non-<dodf>null</dodf>, it
     * rfturns tif vbluf of tif <dodf>domprfssionQublity</dodf>
     * instbndf vbribblf.
     *
     * @rfturn tif durrfnt domprfssion qublity sftting.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if tif sft of lfgbl
     * domprfssion typfs is non-<dodf>null</dodf> bnd tif durrfnt
     * domprfssion typf is <dodf>null</dodf>.
     *
     * @sff #sftComprfssionQublity
     */
    publid flobt gftComprfssionQublity() {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd.");
        }
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        if ((gftComprfssionTypfs() != null) &&
            (gftComprfssionTypf() == null)) {
            tirow nfw IllfgblStbtfExdfption("No domprfssion typf sft!");
        }
        rfturn domprfssionQublity;
    }


    /**
     * Rfturns b <dodf>flobt</dodf> indidbting bn fstimbtf of tif
     * numbfr of bits of output dbtb for fbdi bit of input imbgf dbtb
     * bt tif givfn qublity lfvfl.  Tif vbluf will typidblly lif
     * bftwffn <dodf>0</dodf> bnd <dodf>1</dodf>, witi smbllfr vblufs
     * indidbting morf domprfssion.  A spfdibl vbluf of
     * <dodf>-1.0F</dodf> is usfd to indidbtf tibt no fstimbtf is
     * bvbilbblf.
     *
     * <p> If tifrf brf multiplf domprfssion typfs but nonf ibs bffn sft,
     * bn <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     *
     * <p> Tif dffbult implfmfntbtion difdks tibt domprfssion is
     * supportfd bnd tif domprfssion modf is
     * <dodf>MODE_EXPLICIT</dodf>.  If so, if
     * <dodf>gftComprfssionTypfs()</dodf> is <dodf>null</dodf> or
     * <dodf>gftComprfssionTypf()</dodf> is non-<dodf>null</dodf>, bnd
     * <dodf>qublity</dodf> is witiin bounds, it rfturns
     * <dodf>-1.0</dodf>.
     *
     * @pbrbm qublity tif qublity sftting wiosf bit rbtf is to bf
     * qufrifd.
     *
     * @rfturn bn fstimbtf of tif domprfssfd bit rbtf, or
     * <dodf>-1.0F</dodf> if no fstimbtf is bvbilbblf.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if tif sft of lfgbl
     * domprfssion typfs is non-<dodf>null</dodf> bnd tif durrfnt
     * domprfssion typf is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>qublity</dodf> is
     * not bftwffn <dodf>0</dodf>bnd <dodf>1</dodf>, indlusivf.
     */
    publid flobt gftBitRbtf(flobt qublity) {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd.");
        }
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        if ((gftComprfssionTypfs() != null) &&
            (gftComprfssionTypf() == null)) {
            tirow nfw IllfgblStbtfExdfption("No domprfssion typf sft!");
        }
        if (qublity < 0.0F || qublity > 1.0F) {
            tirow nfw IllfgblArgumfntExdfption("Qublity out-of-bounds!");
        }
        rfturn -1.0F;
    }

    /**
     * Rfturns bn brrby of <dodf>String</dodf>s tibt mby bf usfd blong
     * witi <dodf>gftComprfssionQublityVblufs</dodf> bs pbrt of b usfr
     * intfrfbdf for sftting or displbying tif domprfssion qublity
     * lfvfl.  Tif <dodf>String</dodf> witi indfx <dodf>i</dodf>
     * providfs b dfsdription of tif rbngf of qublity lfvfls bftwffn
     * <dodf>gftComprfssionQublityVblufs[i]</dodf> bnd
     * <dodf>gftComprfssionQublityVblufs[i + 1]</dodf>.  Notf tibt tif
     * lfngti of tif brrby rfturnfd from
     * <dodf>gftComprfssionQublityVblufs</dodf> will blwbys bf onf
     * grfbtfr tibn tibt rfturnfd from
     * <dodf>gftComprfssionQublityDfsdriptions</dodf>.
     *
     * <p> As bn fxbmplf, tif strings "Good", "Bfttfr", bnd "Bfst"
     * dould bf bssodibtfd witi tif rbngfs <dodf>[0, .33)</dodf>,
     * <dodf>[.33, .66)</dodf>, bnd <dodf>[.66, 1.0]</dodf>.  In tiis
     * dbsf, <dodf>gftComprfssionQublityDfsdriptions</dodf> would
     * rfturn <dodf>{ "Good", "Bfttfr", "Bfst" }</dodf> bnd
     * <dodf>gftComprfssionQublityVblufs</dodf> would rfturn
     * <dodf>{ 0.0F, .33F, .66F, 1.0F }</dodf>.
     *
     * <p> If no dfsdriptions brf bvbilbblf, <dodf>null</dodf> is
     * rfturnfd.  If <dodf>null</dodf> is rfturnfd from
     * <dodf>gftComprfssionQublityVblufs</dodf>, tiis mftiod must blso
     * rfturn <dodf>null</dodf>.
     *
     * <p> Tif dfsdriptions siould bf lodblizfd for tif
     * <dodf>Lodblf</dodf> rfturnfd by <dodf>gftLodblf</dodf>, if it
     * is non-<dodf>null</dodf>.
     *
     * <p> If tifrf brf multiplf domprfssion typfs but nonf ibs bffn sft,
     * bn <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     *
     * <p> Tif dffbult implfmfntbtion difdks tibt domprfssion is
     * supportfd bnd tibt tif domprfssion modf is
     * <dodf>MODE_EXPLICIT</dodf>.  If so, if
     * <dodf>gftComprfssionTypfs()</dodf> is <dodf>null</dodf> or
     * <dodf>gftComprfssionTypf()</dodf> is non-<dodf>null</dodf>, it
     * rfturns <dodf>null</dodf>.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s dontbining lodblizfd
     * dfsdriptions of tif domprfssion qublity lfvfls.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if tif sft of lfgbl
     * domprfssion typfs is non-<dodf>null</dodf> bnd tif durrfnt
     * domprfssion typf is <dodf>null</dodf>.
     *
     * @sff #gftComprfssionQublityVblufs
     */
    publid String[] gftComprfssionQublityDfsdriptions() {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd.");
        }
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        if ((gftComprfssionTypfs() != null) &&
            (gftComprfssionTypf() == null)) {
            tirow nfw IllfgblStbtfExdfption("No domprfssion typf sft!");
        }
        rfturn null;
    }

    /**
     * Rfturns bn brrby of <dodf>flobt</dodf>s tibt mby bf usfd blong
     * witi <dodf>gftComprfssionQublityDfsdriptions</dodf> bs pbrt of b usfr
     * intfrfbdf for sftting or displbying tif domprfssion qublity
     * lfvfl.  Sff {@link #gftComprfssionQublityDfsdriptions
     * gftComprfssionQublityDfsdriptions} for morf informbtion.
     *
     * <p> If no dfsdriptions brf bvbilbblf, <dodf>null</dodf> is
     * rfturnfd.  If <dodf>null</dodf> is rfturnfd from
     * <dodf>gftComprfssionQublityDfsdriptions</dodf>, tiis mftiod
     * must blso rfturn <dodf>null</dodf>.
     *
     * <p> If tifrf brf multiplf domprfssion typfs but nonf ibs bffn sft,
     * bn <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     *
     * <p> Tif dffbult implfmfntbtion difdks tibt domprfssion is
     * supportfd bnd tibt tif domprfssion modf is
     * <dodf>MODE_EXPLICIT</dodf>.  If so, if
     * <dodf>gftComprfssionTypfs()</dodf> is <dodf>null</dodf> or
     * <dodf>gftComprfssionTypf()</dodf> is non-<dodf>null</dodf>, it
     * rfturns <dodf>null</dodf>.
     *
     * @rfturn bn brrby of <dodf>flobt</dodf>s indidbting tif
     * boundbrifs bftwffn tif domprfssion qublity lfvfls bs dfsdribfd
     * by tif <dodf>String</dodf>s from
     * <dodf>gftComprfssionQublityDfsdriptions</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif writfr dofs not
     * support domprfssion.
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     * @fxdfption IllfgblStbtfExdfption if tif sft of lfgbl
     * domprfssion typfs is non-<dodf>null</dodf> bnd tif durrfnt
     * domprfssion typf is <dodf>null</dodf>.
     *
     * @sff #gftComprfssionQublityDfsdriptions
     */
    publid flobt[] gftComprfssionQublityVblufs() {
        if (!dbnWritfComprfssfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Comprfssion not supportfd.");
        }
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        if ((gftComprfssionTypfs() != null) &&
            (gftComprfssionTypf() == null)) {
            tirow nfw IllfgblStbtfExdfption("No domprfssion typf sft!");
        }
        rfturn null;
    }
}
