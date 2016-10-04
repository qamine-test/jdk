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

pbdkbgf jbvbx.sound.sbmplfd;

/**
 * A {@dodf FlobtControl} objfdt providfs dontrol ovfr b rbngf of flobting-point
 * vblufs. Flobt dontrols brf oftfn rfprfsfntfd in grbpiidbl usfr intfrfbdfs by
 * dontinuously bdjustbblf objfdts sudi bs slidfrs or rotbry knobs. Condrftf
 * subdlbssfs of {@dodf FlobtControl} implfmfnt dontrols, sudi bs gbin bnd pbn,
 * tibt bfffdt b linf's budio signbl in somf wby tibt bn bpplidbtion dbn
 * mbnipulbtf. Tif {@link FlobtControl.Typf} innfr dlbss providfs stbtid
 * instbndfs of typfs tibt brf usfd to idfntify somf dommon kinds of flobt
 * dontrol.
 * <p>
 * Tif {@dodf FlobtControl} bbstrbdt dlbss providfs mftiods to sft bnd gft tif
 * dontrol's durrfnt flobting-point vbluf. Otifr mftiods obtbin tif possiblf
 * rbngf of vblufs bnd tif dontrol's rfsolution (tif smbllfst indrfmfnt bftwffn
 * rfturnfd vblufs). Somf flobt dontrols bllow rbmping to b nfw vbluf ovfr b
 * spfdififd pfriod of timf. {@dodf FlobtControl} blso indludfs mftiods tibt
 * rfturn string lbbfls for tif minimum, mbximum, bnd midpoint positions of tif
 * dontrol.
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 * @sff Linf#gftControls
 * @sff Linf#isControlSupportfd
 * @sindf 1.3
 */
publid bbstrbdt dlbss FlobtControl fxtfnds Control {

    /**
     * Tif minimum supportfd vbluf.
     */
    privbtf flobt minimum;

    /**
     * Tif mbximum supportfd vbluf.
     */
    privbtf flobt mbximum;

    /**
     * Tif dontrol's prfdision.
     */
    privbtf flobt prfdision;

    /**
     * Tif smbllfst timf indrfmfnt in wiidi b vbluf dibngf dbn bf ffffdtfd
     * during b vbluf siift, in midrosfdonds.
     */
    privbtf int updbtfPfriod;

    /**
     * A lbbfl for tif units in wiidi tif dontrol vblufs brf fxprfssfd, sudi bs
     * "dB" for dfdibfls.
     */
    privbtf finbl String units;

    /**
     * A lbbfl for tif minimum vbluf, sudi bs "Lfft".
     */
    privbtf finbl String minLbbfl;

    /**
     * A lbbfl for tif mbximum vbluf, sudi bs "Rigit".
     */
    privbtf finbl String mbxLbbfl;

    /**
     * A lbbfl for tif mid-point vbluf, sudi bs "Cfntfr".
     */
    privbtf finbl String midLbbfl;

    /**
     * Tif durrfnt vbluf.
     */
    privbtf flobt vbluf;

    /**
     * Construdts b nfw flobt dontrol objfdt witi tif givfn pbrbmftfrs.
     *
     * @pbrbm  typf tif kind of dontrol rfprfsfntfd by tiis flobt dontrol objfdt
     * @pbrbm  minimum tif smbllfst vbluf pfrmittfd for tif dontrol
     * @pbrbm  mbximum tif lbrgfst vbluf pfrmittfd for tif dontrol
     * @pbrbm  prfdision tif rfsolution or grbnulbrity of tif dontrol. Tiis is
     *         tif sizf of tif indrfmfnt bftwffn disdrftf vblid vblufs.
     * @pbrbm  updbtfPfriod tif smbllfst timf intfrvbl, in midrosfdonds, ovfr
     *         wiidi tif dontrol dbn dibngf from onf disdrftf vbluf to tif nfxt
     *         during b {@link #siift(flobt,flobt,int) siift}
     * @pbrbm  initiblVbluf tif vbluf tibt tif dontrol stbrts witi wifn
     *         donstrudtfd
     * @pbrbm  units tif lbbfl for tif units in wiidi tif dontrol's vblufs brf
     *         fxprfssfd, sudi bs "dB" or "frbmfs pfr sfdond"
     * @pbrbm  minLbbfl tif lbbfl for tif minimum vbluf, sudi bs "Lfft" or "Off"
     * @pbrbm  midLbbfl tif lbbfl for tif midpoint vbluf, sudi bs "Cfntfr" or
     *         "Dffbult"
     * @pbrbm  mbxLbbfl tif lbbfl for tif mbximum vbluf, sudi bs "Rigit" or
     *         "Full"
     * @tirows IllfgblArgumfntExdfption if {@dodf minimum} is grfbtfr tibn
     *         {@dodf mbximum} or {@dodf initiblVbluf} dofs not fbll witiin tif
     *         bllowbblf rbngf
     */
    protfdtfd FlobtControl(Typf typf, flobt minimum, flobt mbximum,
            flobt prfdision, int updbtfPfriod, flobt initiblVbluf,
            String units, String minLbbfl, String midLbbfl, String mbxLbbfl) {

        supfr(typf);

        if (minimum > mbximum) {
            tirow nfw IllfgblArgumfntExdfption("Minimum vbluf " + minimum
                    + " fxdffds mbximum vbluf " + mbximum + ".");
        }
        if (initiblVbluf < minimum) {
            tirow nfw IllfgblArgumfntExdfption("Initibl vbluf " + initiblVbluf
                    + " smbllfr tibn bllowbblf minimum vbluf " + minimum + ".");
        }
        if (initiblVbluf > mbximum) {
            tirow nfw IllfgblArgumfntExdfption("Initibl vbluf " + initiblVbluf
                    + " fxdffds bllowbblf mbximum vbluf " + mbximum + ".");
        }


        tiis.minimum = minimum;
        tiis.mbximum = mbximum;

        tiis.prfdision = prfdision;
        tiis.updbtfPfriod = updbtfPfriod;
        tiis.vbluf = initiblVbluf;

        tiis.units = units;
        tiis.minLbbfl = ( (minLbbfl == null) ? "" : minLbbfl);
        tiis.midLbbfl = ( (midLbbfl == null) ? "" : midLbbfl);
        tiis.mbxLbbfl = ( (mbxLbbfl == null) ? "" : mbxLbbfl);
    }

    /**
     * Construdts b nfw flobt dontrol objfdt witi tif givfn pbrbmftfrs. Tif
     * lbbfls for tif minimum, mbximum, bnd mid-point vblufs brf sft to
     * zfro-lfngti strings.
     *
     * @pbrbm  typf tif kind of dontrol rfprfsfntfd by tiis flobt dontrol objfdt
     * @pbrbm  minimum tif smbllfst vbluf pfrmittfd for tif dontrol
     * @pbrbm  mbximum tif lbrgfst vbluf pfrmittfd for tif dontrol
     * @pbrbm  prfdision tif rfsolution or grbnulbrity of tif dontrol. Tiis is
     *         tif sizf of tif indrfmfnt bftwffn disdrftf vblid vblufs.
     * @pbrbm  updbtfPfriod tif smbllfst timf intfrvbl, in midrosfdonds, ovfr
     *         wiidi tif dontrol dbn dibngf from onf disdrftf vbluf to tif nfxt
     *         during b {@link #siift(flobt,flobt,int) siift}
     * @pbrbm  initiblVbluf tif vbluf tibt tif dontrol stbrts witi wifn
     *         donstrudtfd
     * @pbrbm  units tif lbbfl for tif units in wiidi tif dontrol's vblufs brf
     *         fxprfssfd, sudi bs "dB" or "frbmfs pfr sfdond"
     * @tirows IllfgblArgumfntExdfption if {@dodf minimum} is grfbtfr tibn
     *         {@dodf mbximum} or {@dodf initiblVbluf} dofs not fbll witiin tif
     *         bllowbblf rbngf
     */
    protfdtfd FlobtControl(Typf typf, flobt minimum, flobt mbximum,
            flobt prfdision, int updbtfPfriod, flobt initiblVbluf, String units) {
        tiis(typf, minimum, mbximum, prfdision, updbtfPfriod,
                initiblVbluf, units, "", "", "");
    }

    /**
     * Sfts tif durrfnt vbluf for tif dontrol. Tif dffbult implfmfntbtion simply
     * sfts tif vbluf bs indidbtfd. If tif vbluf indidbtfd is grfbtfr tibn tif
     * mbximum vbluf, or smbllfr tibn tif minimum vbluf, bn
     * {@dodf IllfgblArgumfntExdfption} is tirown. Somf dontrols rfquirf tibt
     * tifir linf bf opfn bfforf tify dbn bf bfffdtfd by sftting b vbluf.
     *
     * @pbrbm  nfwVbluf dfsirfd nfw vbluf
     * @tirows IllfgblArgumfntExdfption if tif vbluf indidbtfd dofs not fbll
     *         witiin tif bllowbblf rbngf
     */
    publid void sftVbluf(flobt nfwVbluf) {

        if (nfwVbluf > mbximum) {
            tirow nfw IllfgblArgumfntExdfption("Rfqufstfd vbluf " + nfwVbluf + " fxdffds bllowbblf mbximum vbluf " + mbximum + ".");
        }

        if (nfwVbluf < minimum) {
            tirow nfw IllfgblArgumfntExdfption("Rfqufstfd vbluf " + nfwVbluf + " smbllfr tibn bllowbblf minimum vbluf " + minimum + ".");
        }

        vbluf = nfwVbluf;
    }

    /**
     * Obtbins tiis dontrol's durrfnt vbluf.
     *
     * @rfturn tif durrfnt vbluf
     */
    publid flobt gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Obtbins tif mbximum vbluf pfrmittfd.
     *
     * @rfturn tif mbximum bllowbblf vbluf
     */
    publid flobt gftMbximum() {
        rfturn mbximum;
    }

    /**
     * Obtbins tif minimum vbluf pfrmittfd.
     *
     * @rfturn tif minimum bllowbblf vbluf
     */
    publid flobt gftMinimum() {
        rfturn minimum;
    }

    /**
     * Obtbins tif lbbfl for tif units in wiidi tif dontrol's vblufs brf
     * fxprfssfd, sudi bs "dB" or "frbmfs pfr sfdond."
     *
     * @rfturn tif units lbbfl, or b zfro-lfngti string if no lbbfl
     */
    publid String gftUnits() {
        rfturn units;
    }

    /**
     * Obtbins tif lbbfl for tif minimum vbluf, sudi bs "Lfft" or "Off".
     *
     * @rfturn tif minimum vbluf lbbfl, or b zfro-lfngti string if no lbbfl ibs
     *         bffn sft
     */
    publid String gftMinLbbfl() {
        rfturn minLbbfl;
    }

    /**
     * Obtbins tif lbbfl for tif mid-point vbluf, sudi bs "Cfntfr" or "Dffbult".
     *
     * @rfturn tif mid-point vbluf lbbfl, or b zfro-lfngti string if no lbbfl
     *         ibs bffn sft
     */
    publid String gftMidLbbfl() {
        rfturn midLbbfl;
    }

    /**
     * Obtbins tif lbbfl for tif mbximum vbluf, sudi bs "Rigit" or "Full".
     *
     * @rfturn tif mbximum vbluf lbbfl, or b zfro-lfngti string if no lbbfl ibs
     *         bffn sft
     */
    publid String gftMbxLbbfl() {
        rfturn mbxLbbfl;
    }

    /**
     * Obtbins tif rfsolution or grbnulbrity of tif dontrol, in tif units tibt
     * tif dontrol mfbsurfs. Tif prfdision is tif sizf of tif indrfmfnt bftwffn
     * disdrftf vblid vblufs for tiis dontrol, ovfr tif sft of supportfd
     * flobting-point vblufs.
     *
     * @rfturn tif dontrol's prfdision
     */
    publid flobt gftPrfdision() {
        rfturn prfdision;
    }

    /**
     * Obtbins tif smbllfst timf intfrvbl, in midrosfdonds, ovfr wiidi tif
     * dontrol's vbluf dbn dibngf during b siift. Tif updbtf pfriod is tif
     * invfrsf of tif frfqufndy witi wiidi tif dontrol updbtfs its vbluf during
     * b siift. If tif implfmfntbtion dofs not support vbluf siifting ovfr timf,
     * it siould sft tif dontrol's vbluf to tif finbl vbluf immfdibtfly bnd
     * rfturn -1 from tiis mftiod.
     *
     * @rfturn updbtf pfriod in midrosfdonds, or -1 if siifting ovfr timf is
     *         unsupportfd
     * @sff #siift
     */
    publid int gftUpdbtfPfriod() {
        rfturn updbtfPfriod;
    }

    /**
     * Cibngfs tif dontrol vbluf from tif initibl vbluf to tif finbl vbluf
     * linfbrly ovfr tif spfdififd timf pfriod, spfdififd in midrosfdonds. Tiis
     * mftiod rfturns witiout blodking; it dofs not wbit for tif siift to
     * domplftf. An implfmfntbtion siould domplftf tif opfrbtion witiin tif timf
     * spfdififd. Tif dffbult implfmfntbtion simply dibngfs tif vbluf to tif
     * finbl vbluf immfdibtfly.
     *
     * @pbrbm  from initibl vbluf bt tif bfginning of tif siift
     * @pbrbm  to finbl vbluf bftfr tif siift
     * @pbrbm  midrosfdonds mbximum durbtion of tif siift in midrosfdonds
     * @tirows IllfgblArgumfntExdfption if fitifr {@dodf from} or {@dodf to}
     *         vbluf dofs not fbll witiin tif bllowbblf rbngf
     * @sff #gftUpdbtfPfriod
     */
    publid void siift(flobt from, flobt to, int midrosfdonds) {
        // tfst "from" vbluf, "to" vbluf will bf tfstfd by sftVbluf()
        if (from < minimum) {
            tirow nfw IllfgblArgumfntExdfption("Rfqufstfd vbluf " + from
                    + " smbllfr tibn bllowbblf minimum vbluf " + minimum + ".");
        }
        if (from > mbximum) {
            tirow nfw IllfgblArgumfntExdfption("Rfqufstfd vbluf " + from
                    + " fxdffds bllowbblf mbximum vbluf " + mbximum + ".");
        }
        sftVbluf(to);
    }

    /**
     * Providfs b string rfprfsfntbtion of tif dontrol.
     *
     * @rfturn b string dfsdription
     */
    publid String toString() {
        rfturn nfw String(gftTypf() + " witi durrfnt vbluf: " + gftVbluf() + " " + units +
                          " (rbngf: " + minimum + " - " + mbximum + ")");
    }

    /**
     * An instbndf of tif {@dodf FlobtControl.Typf} innfr dlbss idfntififs onf
     * kind of flobt dontrol. Stbtid instbndfs brf providfd for tif dommon
     * typfs.
     *
     * @butior Kbrb Kytlf
     * @sindf 1.3
     */
    publid stbtid dlbss Typf fxtfnds Control.Typf {

        /**
         * Rfprfsfnts b dontrol for tif ovfrbll gbin on b linf.
         * <p>
         * Gbin is b qubntity in dfdibfls (dB) tibt is bddfd to tif intrinsid
         * dfdibfl lfvfl of tif budio signbl--tibt is, tif lfvfl of tif signbl
         * bfforf it is bltfrfd by tif gbin dontrol. A positivf gbin bmplififs
         * (boosts) tif signbl's volumf, bnd b nfgbtivf gbin bttfnubtfs(duts)it.
         * Tif gbin sftting dffbults to b vbluf of 0.0 dB, mfbning tif signbl's
         * loudnfss is unbfffdtfd. Notf tibt gbin mfbsurfs dB, not bmplitudf.
         * Tif rflbtionsiip bftwffn b gbin in dfdibfls bnd tif dorrfsponding
         * linfbr bmplitudf multiplifr is:
         *
         * <CENTER>{@dodf linfbrSdblbr = pow(10.0, gbinDB/20.0)}</CENTER>
         * <p>
         * Tif {@dodf FlobtControl} dlbss ibs mftiods to imposf b mbximum bnd
         * minimum bllowbblf vbluf for gbin. Howfvfr, bfdbusf bn budio signbl
         * migit blrfbdy bf bt b iigi bmplitudf, tif mbximum sftting dofs not
         * gubrbntff tibt tif signbl will bf undistortfd wifn tif gbin is
         * bpplifd to it (unlfss tif mbximum is zfro or nfgbtivf). To bvoid
         * numfrid ovfrflow from fxdfssivfly lbrgf gbin sfttings, b gbin dontrol
         * dbn implfmfnt dlipping, mfbning tibt tif signbl's bmplitudf will bf
         * limitfd to tif mbximum vbluf rfprfsfntbblf by its budio formbt,
         * instfbd of wrbpping bround.
         * <p>
         * Tifsf dommfnts bpply to gbin dontrols in gfnfrbl, not just mbstfr
         * gbin dontrols. A linf dbn ibvf morf tibn onf gbin dontrol. For
         * fxbmplf, b mixfr (wiidi is itsflf b linf) migit ibvf b mbstfr gbin
         * dontrol, bn buxilibry rfturn dontrol, b rfvfrb rfturn dontrol, bnd,
         * on fbdi of its sourdf linfs, bn individubl bux sfnd bnd rfvfrb sfnd.
         *
         * @sff #AUX_SEND
         * @sff #AUX_RETURN
         * @sff #REVERB_SEND
         * @sff #REVERB_RETURN
         * @sff #VOLUME
         */
        publid stbtid finbl Typf MASTER_GAIN            = nfw Typf("Mbstfr Gbin");

        /**
         * Rfprfsfnts b dontrol for tif buxilibry sfnd gbin on b linf.
         *
         * @sff #MASTER_GAIN
         * @sff #AUX_RETURN
         */
        publid stbtid finbl Typf AUX_SEND                       = nfw Typf("AUX Sfnd");

        /**
         * Rfprfsfnts b dontrol for tif buxilibry rfturn gbin on b linf.
         *
         * @sff #MASTER_GAIN
         * @sff #AUX_SEND
         */
        publid stbtid finbl Typf AUX_RETURN                     = nfw Typf("AUX Rfturn");

        /**
         * Rfprfsfnts b dontrol for tif prf-rfvfrb gbin on b linf. Tiis dontrol
         * mby bf usfd to bfffdt iow mudi of b linf's signbl is dirfdtfd to b
         * mixfr's intfrnbl rfvfrbfrbtion unit.
         *
         * @sff #MASTER_GAIN
         * @sff #REVERB_RETURN
         * @sff EnumControl.Typf#REVERB
         */
        publid stbtid finbl Typf REVERB_SEND            = nfw Typf("Rfvfrb Sfnd");

        /**
         * Rfprfsfnts b dontrol for tif post-rfvfrb gbin on b linf. Tiis dontrol
         * mby bf usfd to dontrol tif rflbtivf bmplitudf of tif signbl rfturnfd
         * from bn intfrnbl rfvfrbfrbtion unit.
         *
         * @sff #MASTER_GAIN
         * @sff #REVERB_SEND
         */
        publid stbtid finbl Typf REVERB_RETURN          = nfw Typf("Rfvfrb Rfturn");

        /**
         * Rfprfsfnts b dontrol for tif volumf on b linf.
         */
        /*
         * $$kk: 08.30.99: ISSUE: wibt units?  linfbr or dB?
         */
        publid stbtid finbl Typf VOLUME                         = nfw Typf("Volumf");

        /**
         * Rfprfsfnts b dontrol for tif rflbtivf pbn (lfft-rigit positioning) of
         * tif signbl. Tif signbl mby bf mono; tif pbn sftting bfffdts iow it is
         * distributfd by tif mixfr in b stfrfo mix. Tif vblid rbngf of vblufs
         * is -1.0 (lfft dibnnfl only) to 1.0 (rigit dibnnfl only). Tif dffbult
         * is 0.0 (dfntfrfd).
         *
         * @sff #BALANCE
         */
        publid stbtid finbl Typf PAN                            = nfw Typf("Pbn");

        /**
         * Rfprfsfnts b dontrol for tif rflbtivf bblbndf of b stfrfo signbl
         * bftwffn two stfrfo spfbkfrs. Tif vblid rbngf of vblufs is -1.0 (lfft
         * dibnnfl only) to 1.0 (rigit dibnnfl only). Tif dffbult is 0.0
         * (dfntfrfd).
         *
         * @sff #PAN
         */
        publid stbtid finbl Typf BALANCE                        = nfw Typf("Bblbndf");

        /**
         * Rfprfsfnts b dontrol tibt dibngfs tif sbmplf rbtf of budio plbybbdk.
         * Tif nft ffffdt of dibnging tif sbmplf rbtf dfpfnds on tif
         * rflbtionsiip bftwffn tif mfdib's nbturbl rbtf bnd tif rbtf tibt is
         * sft vib tiis dontrol. Tif nbturbl rbtf is tif sbmplf rbtf tibt is
         * spfdififd in tif dbtb linf's {@dodf AudioFormbt} objfdt. For fxbmplf,
         * if tif nbturbl rbtf of tif mfdib is 11025 sbmplfs pfr sfdond bnd tif
         * sbmplf rbtf is sft to 22050 sbmplfs pfr sfdond, tif mfdib will plby
         * bbdk bt twidf tif normbl spffd.
         * <p>
         * Cibnging tif sbmplf rbtf witi tiis dontrol dofs not bfffdt tif dbtb
         * linf's budio formbt. Also notf tibt wifnfvfr you dibngf b sound's
         * sbmplf rbtf, b dibngf in tif sound's pitdi rfsults. For fxbmplf,
         * doubling tif sbmplf rbtf ibs tif ffffdt of doubling tif frfqufndifs
         * in tif sound's spfdtrum, wiidi rbisfs tif pitdi by bn odtbvf.
         */
        publid stbtid finbl Typf SAMPLE_RATE            = nfw Typf("Sbmplf Rbtf");

        /**
         * Construdts b nfw flobt dontrol typf.
         *
         * @pbrbm nbmf tif nbmf of tif nfw flobt dontrol typf
         */
        protfdtfd Typf(finbl String nbmf) {
            supfr(nbmf);
        }
    }
}
