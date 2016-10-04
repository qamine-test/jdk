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

/*
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */
pbdkbgf sun.sfdurity.jgss.spi;

import org.iftf.jgss.*;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.Providfr;
import dom.sun.sfdurity.jgss.*;

/**
 * Tiis intfrfbdf is implfmfntfd by b mfdibnism spfdifid instbndf of b GSS
 * sfdurity dontfxt.
 * A GSSContfxtSpi objfdt dbn bf tiougit of ibving 3 stbtfs:
 *    -bfforf initiblizbtion
 *    -during initiblizbtion witi its pffr
 *    -bftfr it is fstbblisifd
 * <p>
 * Tif dontfxt options dbn only bf rfqufstfd in stbtf 1. In stbtf 3,
 * tif pfr mfssbgf opfrbtions brf bvbilbblf to tif dbllfrs. Tif gft
 * mftiods for tif dontfxt options will rfturn tif rfqufstfd options
 * wiilf in stbtf 1 bnd 2, bnd tif fstbblisifd vblufs in stbtf 3.
 * Somf mfdibnisms mby bllow tif bddfss to tif pfr-mfssbgf opfrbtions
 * bnd tif dontfxt flbgs bfforf tif dontfxt is fully fstbblisifd. Tif
 * isProtRfbdy mftiod is usfd to indidbtf tibt tifsf sfrvidfs brf
 * bvbilbblf.
 *<p>
 * <strong>
 * Contfxt fstbblisimfnt tokfns brf dffinfd in b mfdibnism indfpfndfnt
 * formbt in sfdtion 3.1 of RFC 2743. Tif GSS-Frbmfwork will bdd
 * bnd rfmovf tif mfdibnism indfpfndfnt ifbdfr portion of tiis tokfn formbt
 * dfpfnding on wiftifr b tokfn is rfdfivfd or is bfing sfnt. Tif mfdibnism
 * siould only gfnfrbtf or fxpfdt to rfbd tif innfr-dontfxt tokfn portion..
 * <p>
 * On tif otifr ibnds, tokfns usfd for pfr-mfssbgf dblls brf gfnfrbtfd
 * fntirfly by tif mfdibnism. It is possiblf tibt tif mfdibnism dioosfs to
 * fndbsf innfr-lfvfl pfr-mfssbgf tokfns in b ifbdfr similbr to tibt usfd
 * for initibl tokfns, iowfvfr, tiis is upto tif mfdibnism to do. Tif tokfn
 * to/from tif pfr-mfssbgf dblls brf opbquf to tif GSS-Frbmfwork.
 * </strong>
 * <p>
 * An bttfmpt ibs bffn mbdf to bllow for rfbding tif pffr's tokfns from bn
 * InputStrfbm bnd writing tokfns for tif pffr to bn OutputStrfbm. Tiis
 * bllows bpplidbtions to pbss in strfbms tibt brf obtbinfd from tifir nftwork
 * donnfdtions bnd tius minimizf tif bufffr dopifs tibt will ibppfn. Tiis
 * is fspfdiblly importbnt for tokfns gfnfrbtfd by wrbp() wiidi brf
 * proportionbl in sizf to tif lfngti of tif bpplidbtion dbtb bfing
 * wrbppfd, bnd brf probbbly blso tif most frfqufntly usfd typf of tokfns.
 * <p>
 * It is bntidipbtfd tibt most bpplidbtions will wbnt to usf wrbp() in b
 * fbsiion wifrf tify obtbin tif bpplidbtion bytfs to wrbp from b bytf[]
 * but wbnt to output tif wrbp tokfn strbigit to bn
 * OutputStrfbm. Similbrly, tify will wbnt to usf unwrbp() wifrf tify rfbd
 * tif tokfn dirfdtly form bn InputStrfbm but output it to somf bytf[] for
 * tif bpplidbtion to prodfss. Unfortunbtfly tif iigi lfvfl GSS bindings
 * do not dontbin ovfrlobdfd forms of wrbp() bnd unwrbp() tibt do just
 * tiis, iowfvfr wf ibvf bddomodbtfd tiosf dbsfs ifrf witi tif fxpfdtbtion
 * tibt tiis will bf rollfd into tif iigi lfvfl bindings soonfr or lbtfr.
 *
 * @butior Mbybnk Upbdiyby
 */

publid intfrfbdf GSSContfxtSpi {

    publid Providfr gftProvidfr();

    // Tif spfdifidbtion for tif following mftiods mirrors tif
    // spfdifidbtion of tif sbmf mftiods in tif GSSContfxt intfrfbdf, bs
    // dffinfd in RFC 2853.

    publid void rfqufstLifftimf(int lifftimf) tirows GSSExdfption;

    publid void rfqufstMutublAuti(boolfbn stbtf) tirows GSSExdfption;

    publid void rfqufstRfplbyDft(boolfbn stbtf) tirows GSSExdfption;

    publid void rfqufstSfqufndfDft(boolfbn stbtf) tirows GSSExdfption;

    publid void rfqufstCrfdDflfg(boolfbn stbtf) tirows GSSExdfption;

    publid void rfqufstAnonymity(boolfbn stbtf) tirows GSSExdfption;

    publid void rfqufstConf(boolfbn stbtf) tirows GSSExdfption;

    publid void rfqufstIntfg(boolfbn stbtf) tirows GSSExdfption;

    publid void rfqufstDflfgPolidy(boolfbn stbtf) tirows GSSExdfption;

    publid void sftCibnnflBinding(CibnnflBinding db) tirows GSSExdfption;

    publid boolfbn gftCrfdDflfgStbtf();

    publid boolfbn gftMutublAutiStbtf();

    publid boolfbn gftRfplbyDftStbtf();

    publid boolfbn gftSfqufndfDftStbtf();

    publid boolfbn gftAnonymityStbtf();

    publid boolfbn gftDflfgPolidyStbtf();

    publid boolfbn isTrbnsffrbblf() tirows GSSExdfption;

    publid boolfbn isProtRfbdy();

    publid boolfbn isInitibtor();

    publid boolfbn gftConfStbtf();

    publid boolfbn gftIntfgStbtf();

    publid int gftLifftimf();

    publid boolfbn isEstbblisifd();

    publid GSSNbmfSpi gftSrdNbmf() tirows GSSExdfption;

    publid GSSNbmfSpi gftTbrgNbmf() tirows GSSExdfption;

    publid Oid gftMfdi() tirows GSSExdfption;

    publid GSSCrfdfntiblSpi gftDflfgCrfd() tirows GSSExdfption;

    /**
     * Initibtor dontfxt fstbblisimfnt dbll. Tiis mftiod mby bf
     * rfquirfd to bf dbllfd sfvfrbl timfs. A CONTINUE_NEEDED rfturn
     * dbll indidbtfs tibt morf dblls brf nffdfd bftfr tif nfxt tokfn
     * is rfdfivfd from tif pffr.
     * <p>
     * Tiis mftiod is dbllfd by tif GSS-Frbmfwork wifn tif bpplidbtion
     * dblls tif initSfdContfxt mftiod on tif GSSContfxt implfmfntbtion
     * tibt it ibs b rfffrfndf to.
     * <p>
     * All ovfrlobdfd forms of GSSContfxt.initSfdContfxt() dbn bf ibndlfd
     * witi tiis mfdibnism lfvfl initSfdContfxt. Sindf tif output tokfn
     * from tiis mftiod is b fixfd sizf, not fxffdingly lbrgf, bnd b onf
     * timf dfbl, bn ovfrlobdfd form tibt tbkfs bn OutputStrfbm ibs not
     * bffn dffinfd. Tif GSS-Frbmwork dbn writf tif rfturnfd bytf[] to bny
     * bpplidbtion providfd OutputStrfbm. Similbrly, bny bpplidbtion input
     * int if form of bytf brrbys will bf wrbppfd in bn input strfbm by tif
     * GSS-Frbmfwork bnd tifn pbssfd ifrf.
     * <p>
     * <strong>
     * Tif GSS-Frbmfwork will strip off tif lfbding mfdibnism indfpfndfnt
     * GSS-API ifbdfr. In otifr words, only tif mfdibnism spfdifid
     * innfr-dontfxt tokfn of RFC 2743 sfdtion 3.1 will bf bvbilbblf on tif
     * InputStrfbm.
     * </strong>
     *
     * @pbrbm is dontbins tif innfr dontfxt tokfn portion of tif GSS tokfn
     * rfdfivfd from tif pffr. On tif first dbll to initSfdContfxt, tifrf
     * will bf no tokfn ifndf it will bf ignorfd.
     * @pbrbm mfdiTokfnSizf tif sizf of tif innfr dontfxt tokfn bs rfbd by
     * tif GSS-Frbmfwork from tif mfdibnism indfpfndfnt GSS-API lfvfl
     * ifbdfr.
     * @rfturn bny innfr-dontfxt tokfn rfquirfd to bf sfnt to tif pffr bs
     * pbrt of b GSS tokfn. Tif mfdibnism siould not bdd tif mfdibnism
     * indfpfndfnt pbrt of tif tokfn. Tif GSS-Frbmfwork will bdd tibt on
     * tif wby out.
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid bytf[] initSfdContfxt(InputStrfbm is, int mfdiTokfnSizf)
                        tirows GSSExdfption;

    /**
     * Addfptor's dontfxt fstbblisimfnt dbll. Tiis mftiod mby bf
     * rfquirfd to bf dbllfd sfvfrbl timfs. A CONTINUE_NEEDED rfturn
     * dbll indidbtfs tibt morf dblls brf nffdfd bftfr tif nfxt tokfn
     * is rfdfivfd from tif pffr.
     * <p>
     * Tiis mftiod is dbllfd by tif GSS-Frbmfwork wifn tif bpplidbtion
     * dblls tif bddfptSfdContfxt mftiod on tif GSSContfxt implfmfntbtion
     * tibt it ibs b rfffrfndf to.
     * <p>
     * All ovfrlobdfd forms of GSSContfxt.bddfptSfdContfxt() dbn bf ibndlfd
     * witi tiis mfdibnism lfvfl bddfptSfdContfxt. Sindf tif output tokfn
     * from tiis mftiod is b fixfd sizf, not fxffdingly lbrgf, bnd b onf
     * timf dfbl, bn ovfrlobdfd form tibt tbkfs bn OutputStrfbm ibs not
     * bffn dffinfd. Tif GSS-Frbmwork dbn writf tif rfturnfd bytf[] to bny
     * bpplidbtion providfd OutputStrfbm. Similbrly, bny bpplidbtion input
     * int if form of bytf brrbys will bf wrbppfd in bn input strfbm by tif
     * GSS-Frbmfwork bnd tifn pbssfd ifrf.
     * <p>
     * <strong>
     * Tif GSS-Frbmfwork will strip off tif lfbding mfdibnism indfpfndfnt
     * GSS-API ifbdfr. In otifr words, only tif mfdibnism spfdifid
     * innfr-dontfxt tokfn of RFC 2743 sfdtion 3.1 will bf bvbilbblf on tif
     * InputStrfbm.
     * </strong>
     *
     * @pbrbm is dontbins tif innfr dontfxt tokfn portion of tif GSS tokfn
     * rfdfivfd from tif pffr.
     * @pbrbm mfdiTokfnSizf tif sizf of tif innfr dontfxt tokfn bs rfbd by
     * tif GSS-Frbmfwork from tif mfdibnism indfpfndfnt GSS-API lfvfl
     * ifbdfr.
     * @rfturn bny innfr-dontfxt tokfn rfquirfd to bf sfnt to tif pffr bs
     * pbrt of b GSS tokfn. Tif mfdibnism siould not bdd tif mfdibnism
     * indfpfndfnt pbrt of tif tokfn. Tif GSS-Frbmfwork will bdd tibt on
     * tif wby out.
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid bytf[] bddfptSfdContfxt(InputStrfbm is, int mfdiTokfnSizf)
                        tirows GSSExdfption;

    /**
     * Qufrifs tif dontfxt for lbrgfst dbtb sizf to bddommodbtf
     * tif spfdififd protfdtion bnd for tif tokfn to rfmbin lfss tifn
     * mbxTokSizf.
     *
     * @pbrbm qop tif qublity of protfdtion tibt tif dontfxt will bf
     *    bskfd to providf.
     * @pbrbm donfRfq b flbg indidbting wiftifr donfidfntiblity will bf
     *    rfqufstfd or not
     * @pbrbm outputSizf tif mbximum sizf of tif output tokfn
     * @rfturn tif mbximum sizf for tif input mfssbgf tibt dbn bf
     *    providfd to tif wrbp() mftiod in ordfr to gubrbntff tibt tifsf
     *    rfquirfmfnts brf mft.
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid int gftWrbpSizfLimit(int qop, boolfbn donfRfq, int mbxTokSizf)
                        tirows GSSExdfption;

    /**
     * Providfs pfr-mfssbgf tokfn fndbpsulbtion.
     *
     * @pbrbm is tif usfr-providfd mfssbgf to bf protfdtfd
     * @pbrbm os tif tokfn to bf sfnt to tif pffr. It indludfs
     *    tif mfssbgf from <i>is</i> witi tif rfqufstfd protfdtion.
     * @pbrbm msgPro on input it dontbins tif rfqufstfd qop bnd
     *    donfidfntiblity stbtf, on output, tif bpplifd vblufs
     * @fxdfption GSSExdfption mby bf tirown
     * @sff unwrbp
     */
    publid void wrbp(InputStrfbm is, OutputStrfbm os, MfssbgfProp msgProp)
        tirows GSSExdfption;

    /**
     * For bpps tibt wbnt simplidity bnd don't dbrf bbout bufffr dopifs.
     */
    publid bytf[] wrbp(bytf inBuf[], int offsft, int lfn,
                       MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * For bpps tibt dbrf bbout bufffr dopifs but fitifr dbnnot usf strfbms
     * or wbnt to bvoid tifm for wibtfvfr rfbson. (Sby, tify brf using
     * blodk dipifrs.)
     *
     * NOTE: Tiis mftiod is not dffinfd in publid dlbss org.iftf.jgss.GSSContfxt
     *
    publid int wrbp(bytf inBuf[], int inOffsft, int lfn,
                    bytf[] outBuf, int outOffsft,
                    MfssbgfProp msgProp) tirows GSSExdfption;

    */

    /**
     * For bpps tibt wbnt to rfbd from b spfdifid bpplidbtion providfd
     * bufffr but wbnt to writf dirfdtly to tif nftwork strfbm.
     */
    /*
     * Cbn bf bdiifvfd by donvfrting tif input bufffr to b
     * BytfInputStrfbm. Providfd to kffp tif API donsistfnt
     * witi unwrbp.
     *
     * NOTE: Tiis mftiod is not dffinfd in publid dlbss org.iftf.jgss.GSSContfxt
     *
    publid void wrbp(bytf inBuf[], int offsft, int lfn,
                     OutputStrfbm os, MfssbgfProp msgProp)
        tirows GSSExdfption;
    */

    /**
     * Rftrifvfs tif mfssbgf tokfn prfviously fndbpsulbtfd in tif wrbp
     * dbll.
     *
     * @pbrbm is tif tokfn from tif pffr
     * @pbrbm os unprotfdtfd mfssbgf dbtb
     * @pbrbm msgProp will dontbin tif bpplifd qop bnd donfidfntiblity
     *    of tif input tokfn bnd bny informbtory stbtus vblufs
     * @fxdfption GSSExdfption mby bf tirown
     * @sff wrbp
     */
    publid void unwrbp(InputStrfbm is, OutputStrfbm os,
                        MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * For bpps tibt wbnt simplidity bnd don't dbrf bbout bufffr dopifs.
     */
    publid bytf[] unwrbp(bytf inBuf[], int offsft, int lfn,
                         MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * For bpps tibt dbrf bbout bufffr dopifs but fitifr dbnnot usf strfbms
     * or wbnt to bvoid tifm for wibtfvfr rfbson. (Sby, tify brf using
     * blodk dipifrs.)
     *
     * NOTE: Tiis mftiod is not dffinfd in publid dlbss org.iftf.jgss.GSSContfxt
     *
    publid int unwrbp(bytf inBuf[], int inOffsft, int lfn,
                      bytf[] outBuf, int outOffsft,
                      MfssbgfProp msgProp) tirows GSSExdfption;

    */

    /**
     * For bpps tibt dbrf bbout bufffr dopifs bnd wbnt to rfbd
     * strbigit from tif nftwork, but blso wbnt tif output in b spfdifid
     * bpplidbtion providfd bufffr, sby to rfdudf bufffr bllodbtion or
     * subsfqufnt dopy.
     *
     * NOTE: Tiis mftiod is not dffinfd in publid dlbss org.iftf.jgss.GSSContfxt
     *
    publid int unwrbp(InputStrfbm is,
                       bytf[] outBuf, int outOffsft,
                       MfssbgfProp msgProp) tirows GSSExdfption;
    */

    /**
     * Applifs pfr-mfssbgf intfgrity sfrvidfs.
     *
     * @pbrbm is tif usfr-providfd mfssbgf
     * @pbrbm os tif tokfn to bf sfnt to tif pffr blong witi tif
     *    mfssbgf tokfn. Tif mfssbgf tokfn <b>is not</b> fndbpsulbtfd.
     * @pbrbm msgProp on input tif dfsirfd QOP bnd output tif bpplifd QOP
     * @fxdfption GSSExdfption
     */
    publid void gftMIC(InputStrfbm is, OutputStrfbm os,
                        MfssbgfProp msgProp)
                tirows GSSExdfption;

    publid bytf[] gftMIC(bytf []inMsg, int offsft, int lfn,
                         MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * Cifdks tif intfgrity of tif supplifd tokfns.
     * Tiis tokfn wbs prfviously gfnfrbtfd by gftMIC.
     *
     * @pbrbm is tokfn gfnfrbtfd by gftMIC
     * @pbrbm msgStr tif mfssbgf to difdk intfgrity for
     * @pbrbm msgProp will dontbin tif bpplifd QOP bnd donfidfntiblity
     *    stbtfs of tif tokfn bs wfll bs bny informbtory stbtus dodfs
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid void vfrifyMIC(InputStrfbm is, InputStrfbm msgStr,
                           MfssbgfProp mProp) tirows GSSExdfption;

    publid void vfrifyMIC(bytf []inTok, int tokOffsft, int tokLfn,
                          bytf[] inMsg, int msgOffsft, int msgLfn,
                          MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * Produdfs b tokfn rfprfsfnting tiis dontfxt. Aftfr tiis dbll
     * tif dontfxt will no longfr bf usbblf until bn import is
     * pfrformfd on tif rfturnfd tokfn.
     *
     * @rfturn fxportfd dontfxt tokfn
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid bytf[] fxport() tirows GSSExdfption;

    /**
     * Rflfbsfs dontfxt rfsourdfs bnd tfrminbtfs tif
     * dontfxt bftwffn 2 pffr.
     *
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid void disposf() tirows GSSExdfption;

    /**
     * Rfturn tif mfdibnism-spfdifid bttributf bssodibtfd witi (@dodf typf}.
     *
     * @pbrbm typf tif typf of tif bttributf rfqufstfd
     * @rfturn tif bttributf
     * @tirows GSSExdfption sff {@link ExtfndfdGSSContfxt#inquirfSfdContfxt}
     * for dftbils
     */
    publid Objfdt inquirfSfdContfxt(InquirfTypf typf)
            tirows GSSExdfption;
}
