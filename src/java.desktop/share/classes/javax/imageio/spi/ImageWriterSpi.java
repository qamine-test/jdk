/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.spi;

import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.io.IOExdfption;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.ImbgfWritfr;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;

/**
 * Tif sfrvidf providfr intfrfbdf (SPI) for <dodf>ImbgfWritfr</dodf>s.
 * For morf informbtion on sfrvidf providfr dlbssfs, sff tif dlbss dommfnt
 * for tif <dodf>IIORfgistry</dodf> dlbss.
 *
 * <p> Ebdi <dodf>ImbgfWritfrSpi</dodf> providfs sfvfrbl typfs of informbtion
 * bbout tif <dodf>ImbgfWritfr</dodf> dlbss witi wiidi it is bssodibtfd.
 *
 * <p> Tif nbmf of tif vfndor wio dffinfd tif SPI dlbss bnd b
 * briff dfsdription of tif dlbss brf bvbilbblf vib tif
 * <dodf>gftVfndorNbmf</dodf>, <dodf>gftDfsdription</dodf>,
 * bnd <dodf>gftVfrsion</dodf> mftiods.
 * Tifsf mftiods mby bf intfrnbtionblizfd to providf lodblf-spfdifid
 * output.  Tifsf mftiods brf intfndfd mbinly to providf siort,
 * iumbn-writbblf informbtion tibt migit bf usfd to orgbnizf b pop-up
 * mfnu or otifr list.
 *
 * <p> Lists of formbt nbmfs, filf suffixfs, bnd MIME typfs bssodibtfd
 * witi tif sfrvidf mby bf obtbinfd by mfbns of tif
 * <dodf>gftFormbtNbmfs</dodf>, <dodf>gftFilfSuffixfs</dodf>, bnd
 * <dodf>gftMIMETypf</dodf> mftiods.  Tifsf mftiods mby bf usfd to
 * idfntify dbndidbtf <dodf>ImbgfWritfr</dodf>s for writing b
 * pbrtidulbr filf or strfbm bbsfd on mbnubl formbt sflfdtion, filf
 * nbming, or MIME bssodibtions.
 *
 * <p> A morf rflibblf wby to dftfrminf wiidi <dodf>ImbgfWritfr</dodf>s
 * brf likfly to bf bblf to pbrsf b pbrtidulbr dbtb strfbm is providfd
 * by tif <dodf>dbnEndodfImbgf</dodf> mftiod.  Tiis mftiods bllows tif
 * sfrvidf providfr to inspfdt tif bdtubl imbgf dontfnts.
 *
 * <p> Finblly, bn instbndf of tif <dodf>ImbgfWritfr</dodf> dlbss
 * bssodibtfd witi tiis sfrvidf providfr mby bf obtbinfd by dblling
 * tif <dodf>drfbtfWritfrInstbndf</dodf> mftiod.  Any ifbvywfigit
 * initiblizbtion, sudi bs tif lobding of nbtivf librbrifs or drfbtion
 * of lbrgf tbblfs, siould bf dfffrrfd bt lfbst until tif first
 * invodbtion of tiis mftiod.
 *
 * @sff IIORfgistry
 * @sff jbvbx.imbgfio.ImbgfTypfSpfdififr
 * @sff jbvbx.imbgfio.ImbgfWritfr
 *
 */
publid bbstrbdt dlbss ImbgfWritfrSpi fxtfnds ImbgfRfbdfrWritfrSpi {

    /**
     * A singlf-flfmfnt brrby, initiblly dontbining
     * <dodf>ImbgfOutputStrfbm.dlbss</dodf>, to bf rfturnfd from
     * <dodf>gftOutputTypfs</dodf>.
     * @dfprfdbtfd Instfbd of using tiis fifld, dirfdtly drfbtf
     * tif fquivblfnt brrby <dodf>{ ImbgfOutputStrfbm.dlbss }</dodf>.
     */
    @Dfprfdbtfd
    publid stbtid finbl Clbss<?>[] STANDARD_OUTPUT_TYPE =
        { ImbgfOutputStrfbm.dlbss };

    /**
     * An brrby of <dodf>Clbss</dodf> objfdts to bf rfturnfd from
     * <dodf>gftOutputTypfs</dodf>, initiblly <dodf>null</dodf>.
     */
    protfdtfd Clbss<?>[] outputTypfs = null;

    /**
     * An brrby of strings to bf rfturnfd from
     * <dodf>gftImbgfRfbdfrSpiNbmfs</dodf>, initiblly
     * <dodf>null</dodf>.
     */
    protfdtfd String[] rfbdfrSpiNbmfs = null;

    /**
     * Tif <dodf>Clbss</dodf> of tif writfr, initiblly
     * <dodf>null</dodf>.
     */
    privbtf Clbss<?> writfrClbss = null;

    /**
     * Construdts b blbnk <dodf>ImbgfWritfrSpi</dodf>.  It is up to
     * tif subdlbss to initiblizf instbndf vbribblfs bnd/or ovfrridf
     * mftiod implfmfntbtions in ordfr to providf working vfrsions of
     * bll mftiods.
     */
    protfdtfd ImbgfWritfrSpi() {
    }

    /**
     * Construdts bn <dodf>ImbgfWritfrSpi</dodf> witi b givfn
     * sft of vblufs.
     *
     * @pbrbm vfndorNbmf tif vfndor nbmf, bs b non-<dodf>null</dodf>
     * <dodf>String</dodf>.
     * @pbrbm vfrsion b vfrsion idfntififr, bs b non-<dodf>null</dodf>
     * <dodf>String</dodf>.
     * @pbrbm nbmfs b non-<dodf>null</dodf> brrby of
     * <dodf>String</dodf>s indidbting tif formbt nbmfs.  At lfbst onf
     * fntry must bf prfsfnt.
     * @pbrbm suffixfs bn brrby of <dodf>String</dodf>s indidbting tif
     * dommon filf suffixfs.  If no suffixfs brf dffinfd,
     * <dodf>null</dodf> siould bf supplifd.  An brrby of lfngti 0
     * will bf normblizfd to <dodf>null</dodf>.
     * @pbrbm MIMETypfs bn brrby of <dodf>String</dodf>s indidbting
     * tif formbt's MIME typfs.  If no suffixfs brf dffinfd,
     * <dodf>null</dodf> siould bf supplifd.  An brrby of lfngti 0
     * will bf normblizfd to <dodf>null</dodf>.
     * @pbrbm writfrClbssNbmf tif fully-qublififd nbmf of tif
     * bssodibtfd <dodf>ImbgfWritfrSpi</dodf> dlbss, bs b
     * non-<dodf>null</dodf> <dodf>String</dodf>.
     * @pbrbm outputTypfs bn brrby of <dodf>Clbss</dodf> objfdts of
     * lfngti bt lfbst 1 indidbting tif lfgbl output typfs.
     * @pbrbm rfbdfrSpiNbmfs bn brrby <dodf>String</dodf>s of lfngti
     * bt lfbst 1 nbming tif dlbssfs of bll bssodibtfd
     * <dodf>ImbgfRfbdfr</dodf>s, or <dodf>null</dodf>.  An brrby of
     * lfngti 0 is normblizfd to <dodf>null</dodf>.
     * @pbrbm supportsStbndbrdStrfbmMftbdbtbFormbt b
     * <dodf>boolfbn</dodf> tibt indidbtfs wiftifr b strfbm mftbdbtb
     * objfdt dbn usf trffs dfsdribfd by tif stbndbrd mftbdbtb formbt.
     * @pbrbm nbtivfStrfbmMftbdbtbFormbtNbmf b
     * <dodf>String</dodf>, or <dodf>null</dodf>, to bf rfturnfd from
     * <dodf>gftNbtivfStrfbmMftbdbtbFormbtNbmf</dodf>.
     * @pbrbm nbtivfStrfbmMftbdbtbFormbtClbssNbmf b
     * <dodf>String</dodf>, or <dodf>null</dodf>, to bf usfd to instbntibtf
     * b mftbdbtb formbt objfdt to bf rfturnfd from
     * <dodf>gftNbtivfStrfbmMftbdbtbFormbt</dodf>.
     * @pbrbm fxtrbStrfbmMftbdbtbFormbtNbmfs bn brrby of
     * <dodf>String</dodf>s, or <dodf>null</dodf>, to bf rfturnfd from
     * <dodf>gftExtrbStrfbmMftbdbtbFormbtNbmfs</dodf>.  An brrby of lfngti
     * 0 is normblizfd to <dodf>null</dodf>.
     * @pbrbm fxtrbStrfbmMftbdbtbFormbtClbssNbmfs bn brrby of
     * <dodf>String</dodf>s, or <dodf>null</dodf>, to bf usfd to instbntibtf
     * b mftbdbtb formbt objfdt to bf rfturnfd from
     * <dodf>gftStrfbmMftbdbtbFormbt</dodf>.  An brrby of lfngti
     * 0 is normblizfd to <dodf>null</dodf>.
     * @pbrbm supportsStbndbrdImbgfMftbdbtbFormbt b
     * <dodf>boolfbn</dodf> tibt indidbtfs wiftifr bn imbgf mftbdbtb
     * objfdt dbn usf trffs dfsdribfd by tif stbndbrd mftbdbtb formbt.
     * @pbrbm nbtivfImbgfMftbdbtbFormbtNbmf b
     * <dodf>String</dodf>, or <dodf>null</dodf>, to bf rfturnfd from
     * <dodf>gftNbtivfImbgfMftbdbtbFormbtNbmf</dodf>.
     * @pbrbm nbtivfImbgfMftbdbtbFormbtClbssNbmf b
     * <dodf>String</dodf>, or <dodf>null</dodf>, to bf usfd to instbntibtf
     * b mftbdbtb formbt objfdt to bf rfturnfd from
     * <dodf>gftNbtivfImbgfMftbdbtbFormbt</dodf>.
     * @pbrbm fxtrbImbgfMftbdbtbFormbtNbmfs bn brrby of
     * <dodf>String</dodf>s to bf rfturnfd from
     * <dodf>gftExtrbImbgfMftbdbtbFormbtNbmfs</dodf>.  An brrby of lfngti 0
     * is normblizfd to <dodf>null</dodf>.
     * @pbrbm fxtrbImbgfMftbdbtbFormbtClbssNbmfs bn brrby of
     * <dodf>String</dodf>s, or <dodf>null</dodf>, to bf usfd to instbntibtf
     * b mftbdbtb formbt objfdt to bf rfturnfd from
     * <dodf>gftImbgfMftbdbtbFormbt</dodf>.  An brrby of lfngti
     * 0 is normblizfd to <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>vfndorNbmf</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>vfrsion</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>nbmfs</dodf>
     * is <dodf>null</dodf> or ibs lfngti 0.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>writfrClbssNbmf</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>outputTypfs</dodf>
     * is <dodf>null</dodf> or ibs lfngti 0.
     */
    publid ImbgfWritfrSpi(String vfndorNbmf,
                          String vfrsion,
                          String[] nbmfs,
                          String[] suffixfs,
                          String[] MIMETypfs,
                          String writfrClbssNbmf,
                          Clbss<?>[] outputTypfs,
                          String[] rfbdfrSpiNbmfs,
                          boolfbn supportsStbndbrdStrfbmMftbdbtbFormbt,
                          String nbtivfStrfbmMftbdbtbFormbtNbmf,
                          String nbtivfStrfbmMftbdbtbFormbtClbssNbmf,
                          String[] fxtrbStrfbmMftbdbtbFormbtNbmfs,
                          String[] fxtrbStrfbmMftbdbtbFormbtClbssNbmfs,
                          boolfbn supportsStbndbrdImbgfMftbdbtbFormbt,
                          String nbtivfImbgfMftbdbtbFormbtNbmf,
                          String nbtivfImbgfMftbdbtbFormbtClbssNbmf,
                          String[] fxtrbImbgfMftbdbtbFormbtNbmfs,
                          String[] fxtrbImbgfMftbdbtbFormbtClbssNbmfs) {
        supfr(vfndorNbmf, vfrsion,
              nbmfs, suffixfs, MIMETypfs, writfrClbssNbmf,
              supportsStbndbrdStrfbmMftbdbtbFormbt,
              nbtivfStrfbmMftbdbtbFormbtNbmf,
              nbtivfStrfbmMftbdbtbFormbtClbssNbmf,
              fxtrbStrfbmMftbdbtbFormbtNbmfs,
              fxtrbStrfbmMftbdbtbFormbtClbssNbmfs,
              supportsStbndbrdImbgfMftbdbtbFormbt,
              nbtivfImbgfMftbdbtbFormbtNbmf,
              nbtivfImbgfMftbdbtbFormbtClbssNbmf,
              fxtrbImbgfMftbdbtbFormbtNbmfs,
              fxtrbImbgfMftbdbtbFormbtClbssNbmfs);

        if (outputTypfs == null) {
            tirow nfw IllfgblArgumfntExdfption
                ("outputTypfs == null!");
        }
        if (outputTypfs.lfngti == 0) {
            tirow nfw IllfgblArgumfntExdfption
                ("outputTypfs.lfngti == 0!");
        }

        tiis.outputTypfs = (outputTypfs == STANDARD_OUTPUT_TYPE) ?
            nfw Clbss<?>[] { ImbgfOutputStrfbm.dlbss } :
            outputTypfs.dlonf();

        // If lfngti == 0, lfbvf it null
        if (rfbdfrSpiNbmfs != null && rfbdfrSpiNbmfs.lfngti > 0) {
            tiis.rfbdfrSpiNbmfs = rfbdfrSpiNbmfs.dlonf();
        }
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif formbt tibt tiis writfr
     * outputs prfsfrvfs pixfl dbtb bit-bddurbtfly.  Tif dffbult
     * implfmfntbtion rfturns <dodf>truf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif formbt prfsfrvfs full pixfl
     * bddurbdy.
     */
    publid boolfbn isFormbtLosslfss() {
        rfturn truf;
    }

    /**
     * Rfturns bn brrby of <dodf>Clbss</dodf> objfdts indidbting wibt
     * typfs of objfdts mby bf usfd bs brgumfnts to tif writfr's
     * <dodf>sftOutput</dodf> mftiod.
     *
     * <p> For most writfrs, wiidi only output to bn
     * <dodf>ImbgfOutputStrfbm</dodf>, b singlf-flfmfnt brrby
     * dontbining <dodf>ImbgfOutputStrfbm.dlbss</dodf> siould bf
     * rfturnfd.
     *
     * @rfturn b non-<dodf>null</dodf> brrby of
     * <dodf>Clbss</dodf> objfdts of lfngti bt lfbst 1.
     */
    publid Clbss<?>[] gftOutputTypfs() {
        rfturn outputTypfs.dlonf();
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif <dodf>ImbgfWritfr</dodf>
     * implfmfntbtion bssodibtfd witi tiis sfrvidf providfr is bblf to
     * fndodf bn imbgf witi tif givfn lbyout.  Tif lbyout
     * (<i>i.f.</i>, tif imbgf's <dodf>SbmplfModfl</dodf> bnd
     * <dodf>ColorModfl</dodf>) is dfsdribfd by bn
     * <dodf>ImbgfTypfSpfdififr</dodf> objfdt.
     *
     * <p> A rfturn vbluf of <dodf>truf</dodf> is not bn bbsolutf
     * gubrbntff of suddfssful fndoding; tif fndoding prodfss mby still
     * produdf frrors duf to fbdtors sudi bs I/O frrors, indonsistfnt
     * or mblformfd dbtb strudturfs, ftd.  Tif intfnt is tibt b
     * rfbsonbblf inspfdtion of tif bbsid strudturf of tif imbgf bf
     * pfrformfd in ordfr to dftfrminf if it is witiin tif sdopf of
     * tif fndoding formbt.  For fxbmplf, b sfrvidf providfr for b
     * formbt tibt dbn only fndodf grfysdblf would rfturn
     * <dodf>fblsf</dodf> if ibndfd bn RGB <dodf>BufffrfdImbgf</dodf>.
     * Similbrly, b sfrvidf providfr for b formbt tibt dbn fndodf
     * 8-bit RGB imbgfry migit rffusf to fndodf bn imbgf witi bn
     * bssodibtfd blpib dibnnfl.
     *
     * <p> Difffrfnt <dodf>ImbgfWritfr</dodf>s, bnd tius sfrvidf
     * providfrs, mby dioosf to bf morf or lfss stridt.  For fxbmplf,
     * tify migit bddfpt bn imbgf witi prfmultiplifd blpib fvfn tiougi
     * it will ibvf to bf dividfd out of fbdi pixfl, bt somf loss of
     * prfdision, in ordfr to bf storfd.
     *
     * @pbrbm typf bn <dodf>ImbgfTypfSpfdififr</dodf> spfdifying tif
     * lbyout of tif imbgf to bf writtfn.
     *
     * @rfturn <dodf>truf</dodf> if tiis writfr is likfly to bf bblf
     * to fndodf imbgfs witi tif givfn lbyout.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>typf</dodf>
     * is <dodf>null</dodf>.
     */
    publid bbstrbdt boolfbn dbnEndodfImbgf(ImbgfTypfSpfdififr typf);

    /**
     * Rfturns <dodf>truf</dodf> if tif <dodf>ImbgfWritfr</dodf>
     * implfmfntbtion bssodibtfd witi tiis sfrvidf providfr is bblf to
     * fndodf tif givfn <dodf>RfndfrfdImbgf</dodf> instbndf.  Notf
     * tibt tiis indludfs instbndfs of
     * <dodf>jbvb.bwt.imbgf.BufffrfdImbgf</dodf>.
     *
     * <p> Sff tif disdussion for
     * <dodf>dbnEndodfImbgf(ImbgfTypfSpfdififr)</dodf> for informbtion
     * on tif sfmbntids of tiis mftiod.
     *
     * @pbrbm im bn instbndf of <dodf>RfndfrfdImbgf</dodf> to bf fndodfd.
     *
     * @rfturn <dodf>truf</dodf> if tiis writfr is likfly to bf bblf
     * to fndodf tiis imbgf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>im</dodf>
     * is <dodf>null</dodf>.
     */
    publid boolfbn dbnEndodfImbgf(RfndfrfdImbgf im) {
        rfturn dbnEndodfImbgf(ImbgfTypfSpfdififr.drfbtfFromRfndfrfdImbgf(im));
    }

    /**
     * Rfturns bn instbndf of tif <dodf>ImbgfWritfr</dodf>
     * implfmfntbtion bssodibtfd witi tiis sfrvidf providfr.
     * Tif rfturnfd objfdt will initiblly bf in bn initibl stbtf bs if
     * its <dodf>rfsft</dodf> mftiod ibd bffn dbllfd.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns
     * <dodf>drfbtfWritfrInstbndf(null)</dodf>.
     *
     * @rfturn bn <dodf>ImbgfWritfr</dodf> instbndf.
     *
     * @fxdfption IOExdfption if bn frror oddurs during lobding,
     * or initiblizbtion of tif writfr dlbss, or during instbntibtion
     * or initiblizbtion of tif writfr objfdt.
     */
    publid ImbgfWritfr drfbtfWritfrInstbndf() tirows IOExdfption {
        rfturn drfbtfWritfrInstbndf(null);
    }

    /**
     * Rfturns bn instbndf of tif <dodf>ImbgfWritfr</dodf>
     * implfmfntbtion bssodibtfd witi tiis sfrvidf providfr.
     * Tif rfturnfd objfdt will initiblly bf in bn initibl stbtf
     * bs if its <dodf>rfsft</dodf> mftiod ibd bffn dbllfd.
     *
     * <p> An <dodf>Objfdt</dodf> mby bf supplifd to tif plug-in bt
     * donstrudtion timf.  Tif nbturf of tif objfdt is fntirfly
     * plug-in spfdifid.
     *
     * <p> Typidblly, b plug-in will implfmfnt tiis mftiod using dodf
     * sudi bs <dodf>rfturn nfw MyImbgfWritfr(tiis)</dodf>.
     *
     * @pbrbm fxtfnsion b plug-in spfdifid fxtfnsion objfdt, wiidi mby
     * bf <dodf>null</dodf>.
     *
     * @rfturn bn <dodf>ImbgfWritfr</dodf> instbndf.
     *
     * @fxdfption IOExdfption if tif bttfmpt to instbntibtf
     * tif writfr fbils.
     * @fxdfption IllfgblArgumfntExdfption if tif
     * <dodf>ImbgfWritfr</dodf>'s donstrudtor tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> to indidbtf tibt tif
     * fxtfnsion objfdt is unsuitbblf.
     */
    publid bbstrbdt ImbgfWritfr drfbtfWritfrInstbndf(Objfdt fxtfnsion)
        tirows IOExdfption;

    /**
     * Rfturns <dodf>truf</dodf> if tif <dodf>ImbgfWritfr</dodf> objfdt
     * pbssfd in is bn instbndf of tif <dodf>ImbgfWritfr</dodf>
     * bssodibtfd witi tiis sfrvidf providfr.
     *
     * @pbrbm writfr bn <dodf>ImbgfWritfr</dodf> instbndf.
     *
     * @rfturn <dodf>truf</dodf> if <dodf>writfr</dodf> is rfdognizfd
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>writfr</dodf> is
     * <dodf>null</dodf>.
     */
    publid boolfbn isOwnWritfr(ImbgfWritfr writfr) {
        if (writfr == null) {
            tirow nfw IllfgblArgumfntExdfption("writfr == null!");
        }
        String nbmf = writfr.gftClbss().gftNbmf();
        rfturn nbmf.fqubls(pluginClbssNbmf);
    }

    /**
     * Rfturns bn brrby of <dodf>String</dodf>s dontbining bll tif
     * fully qublififd nbmfs of bll tif <dodf>ImbgfRfbdfrSpi</dodf>
     * dlbssfs tibt dbn undfrstbnd tif intfrnbl mftbdbtb
     * rfprfsfntbtion usfd by tif <dodf>ImbgfWritfr</dodf> bssodibtfd
     * witi tiis sfrvidf providfr, or <dodf>null</dodf> if tifrf brf
     * no sudi <dodf>ImbgfRfbdfrs</dodf> spfdififd.  If b
     * non-<dodf>null</dodf> vbluf is rfturnfd, it must ibvf non-zfro
     * lfngti.
     *
     * <p> Tif first itfm in tif brrby must bf tif nbmf of tif sfrvidf
     * providfr for tif "prfffrrfd" rfbdfr, bs it will bf usfd to
     * instbntibtf tif <dodf>ImbgfRfbdfr</dodf> rfturnfd by
     * <dodf>ImbgfIO.gftImbgfRfbdfr(ImbgfWritfr)</dodf>.
     *
     * <p> Tiis mfdibnism mby bf usfd to obtbin
     * <dodf>ImbgfRfbdfrs</dodf> tibt will gfnfrbtfd non-pixfl
     * mftb-dbtb (sff <dodf>IIOExtrbDbtbInfo</dodf>) in b strudturf
     * undfrstood by bn <dodf>ImbgfWritfr</dodf>.  By rfbding tif
     * imbgf bnd obtbining tiis dbtb from onf of tif
     * <dodf>ImbgfRfbdfrs</dodf> obtbinfd witi tiis mftiod bnd pbssing
     * it on to tif <dodf>ImbgfWritfr</dodf>, b dlifnt progrbm dbn
     * rfbd bn imbgf, modify it in somf wby, bnd writf it bbdk out
     * prfsfrving bll mftb-dbtb, witiout ibving to undfrstbnd bnytiing
     * bbout tif intfrnbl strudturf of tif mftb-dbtb, or fvfn bbout
     * tif imbgf formbt.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s of lfngti bt lfbst 1
     * dontbining nbmfs of <dodf>ImbgfRfbdfrSpi</dodf>s, or
     * <dodf>null</dodf>.
     *
     * @sff jbvbx.imbgfio.ImbgfIO#gftImbgfRfbdfr(ImbgfWritfr)
     * @sff ImbgfRfbdfrSpi#gftImbgfWritfrSpiNbmfs()
     */
    publid String[] gftImbgfRfbdfrSpiNbmfs() {
        rfturn rfbdfrSpiNbmfs == null ?
            null : rfbdfrSpiNbmfs.dlonf();
    }
}
