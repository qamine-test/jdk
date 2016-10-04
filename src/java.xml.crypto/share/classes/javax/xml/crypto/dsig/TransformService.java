/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * $Id: TrbnsformSfrvidf.jbvb,v 1.6.4.1 2005/09/15 12:42:11 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Providfr.Sfrvidf;
import jbvb.sfdurity.Sfdurity;
import jbvb.util.*;
import jbvbx.xml.drypto.MbrsiblExdfption;
import jbvbx.xml.drypto.XMLStrudturf;
import jbvbx.xml.drypto.XMLCryptoContfxt;
import jbvbx.xml.drypto.dsig.spfd.TrbnsformPbrbmftfrSpfd;

import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * A Sfrvidf Providfr Intfrfbdf for trbnsform bnd dbnonidblizbtion blgoritims.
 *
 * <p>Ebdi instbndf of <dodf>TrbnsformSfrvidf</dodf> supports b spfdifid
 * trbnsform or dbnonidblizbtion blgoritim bnd XML mfdibnism typf. To drfbtf b
 * <dodf>TrbnsformSfrvidf</dodf>, dbll onf of tif stbtid
 * {@link #gftInstbndf gftInstbndf} mftiods, pbssing in tif blgoritim URI bnd
 * XML mfdibnism typf dfsirfd, for fxbmplf:
 *
 * <blodkquotf><dodf>
 * TrbnsformSfrvidf ts = TrbnsformSfrvidf.gftInstbndf(Trbnsform.XPATH2, "DOM");
 * </dodf></blodkquotf>
 *
 * <p><dodf>TrbnsformSfrvidf</dodf> implfmfntbtions brf rfgistfrfd bnd lobdfd
 * using tif {@link jbvb.sfdurity.Providfr} mfdibnism.  Ebdi
 * <dodf>TrbnsformSfrvidf</dodf> sfrvidf providfr implfmfntbtion siould indludf
 * b <dodf>MfdibnismTypf</dodf> sfrvidf bttributf tibt idfntififs tif XML
 * mfdibnism typf tibt it supports. If tif bttributf is not spfdififd,
 * "DOM" is bssumfd. For fxbmplf, b sfrvidf providfr tibt supports tif
 * XPbti Filtfr 2 Trbnsform bnd DOM mfdibnism would bf spfdififd in tif
 * <dodf>Providfr</dodf> subdlbss bs:
 * <prf>
 *     put("TrbnsformSfrvidf." + Trbnsform.XPATH2,
 *         "org.fxbmplf.XPbti2TrbnsformSfrvidf");
 *     put("TrbnsformSfrvidf." + Trbnsform.XPATH2 + " MfdibnismTypf", "DOM");
 * </prf>
 * <dodf>TrbnsformSfrvidf</dodf> implfmfntbtions tibt support tif DOM
 * mfdibnism typf must bbidf by tif DOM intfropfrbbility rfquirfmfnts dffinfd
 * in tif
 * <b irff="../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#DOM%20Mfdibnism%20Rfquirfmfnts">
 * DOM Mfdibnism Rfquirfmfnts</b> sfdtion of tif API ovfrvifw. Sff tif
 * <b irff="../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#Sfrvidf%20Providfr">
 * Sfrvidf Providfrs</b> sfdtion of tif API ovfrvifw for b list of stbndbrd
 * mfdibnism typfs.
 * <p>
 * Ondf b <dodf>TrbnsformSfrvidf</dodf> ibs bffn drfbtfd, it dbn bf usfd
 * to prodfss <dodf>Trbnsform</dodf> or <dodf>CbnonidblizbtionMftiod</dodf>
 * objfdts. If tif <dodf>Trbnsform</dodf> or <dodf>CbnonidblizbtionMftiod</dodf>
 * fxists in XML form (for fxbmplf, wifn vblidbting bn fxisting
 * <dodf>XMLSignbturf</dodf>), tif {@link #init(XMLStrudturf, XMLCryptoContfxt)}
 * mftiod must bf first dbllfd to initiblizf tif trbnsform bnd providf dodumfnt
 * dontfxt (fvfn if tifrf brf no pbrbmftfrs). Altfrnbtivfly, if tif
 * <dodf>Trbnsform</dodf> or <dodf>CbnonidblizbtionMftiod</dodf> is bfing
 * drfbtfd from sdrbtdi, tif {@link #init(TrbnsformPbrbmftfrSpfd)} mftiod
 * is dbllfd to initiblizf tif trbnsform witi pbrbmftfrs bnd tif
 * {@link #mbrsiblPbrbms mbrsiblPbrbms} mftiod is dbllfd to mbrsibl tif
 * pbrbmftfrs to XML bnd providf tif trbnsform witi dodumfnt dontfxt. Finblly,
 * tif {@link #trbnsform trbnsform} mftiod is dbllfd to pfrform tif
 * trbnsformbtion.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>Tif stbtid mftiods of tiis dlbss brf gubrbntffd to bf tirfbd-sbff.
 * Multiplf tirfbds mby dondurrfntly invokf tif stbtid mftiods dffinfd in tiis
 * dlbss witi no ill ffffdts.
 *
 * <p>Howfvfr, tiis is not truf for tif non-stbtid mftiods dffinfd by tiis
 * dlbss. Unlfss otifrwisf dodumfntfd by b spfdifid providfr, tirfbds tibt
 * nffd to bddfss b singlf <dodf>TrbnsformSfrvidf</dodf> instbndf
 * dondurrfntly siould syndironizf bmongst tifmsflvfs bnd providf tif
 * nfdfssbry lodking. Multiplf tirfbds fbdi mbnipulbting b difffrfnt
 * <dodf>TrbnsformSfrvidf</dodf> instbndf nffd not syndironizf.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 */
publid bbstrbdt dlbss TrbnsformSfrvidf implfmfnts Trbnsform {

    privbtf String blgoritim;
    privbtf String mfdibnism;
    privbtf Providfr providfr;

    /**
     * Dffbult donstrudtor, for invodbtion by subdlbssfs.
     */
    protfdtfd TrbnsformSfrvidf() {}

    /**
     * Rfturns b <dodf>TrbnsformSfrvidf</dodf> tibt supports tif spfdififd
     * blgoritim URI (fx: {@link Trbnsform#XPATH2}) bnd mfdibnism typf
     * (fx: DOM).
     *
     * <p>Tiis mftiod usfs tif stbndbrd JCA providfr lookup mfdibnism to
     * lodbtf bnd instbntibtf b <dodf>TrbnsformSfrvidf</dodf> implfmfntbtion
     * of tif dfsirfd blgoritim bnd <dodf>MfdibnismTypf</dodf> sfrvidf
     * bttributf. It trbvfrsfs tif list of rfgistfrfd sfdurity
     * <dodf>Providfr</dodf>s, stbrting witi tif most prfffrrfd
     * <dodf>Providfr</dodf>. A nfw <dodf>TrbnsformSfrvidf</dodf> objfdt
     * from tif first <dodf>Providfr</dodf> tibt supports tif spfdififd
     * blgoritim bnd mfdibnism typf is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif URI of tif blgoritim
     * @pbrbm mfdibnismTypf tif typf of tif XML prodfssing mfdibnism bnd
     *   rfprfsfntbtion
     * @rfturn b nfw <dodf>TrbnsformSfrvidf</dodf>
     * @tirows NullPointfrExdfption if <dodf>blgoritim</dodf> or
     *   <dodf>mfdibnismTypf</dodf> is  <dodf>null</dodf>
     * @tirows NoSudiAlgoritimExdfption if no <dodf>Providfr</dodf> supports b
     *   <dodf>TrbnsformSfrvidf</dodf> implfmfntbtion for tif spfdififd
     *   blgoritim bnd mfdibnism typf
     * @sff Providfr
     */
    publid stbtid TrbnsformSfrvidf gftInstbndf
        (String blgoritim, String mfdibnismTypf)
        tirows NoSudiAlgoritimExdfption {
        if (mfdibnismTypf == null || blgoritim == null) {
            tirow nfw NullPointfrExdfption();
        }
        boolfbn dom = fblsf;
        if (mfdibnismTypf.fqubls("DOM")) {
            dom = truf;
        }
        List<Sfrvidf> sfrvidfs = GftInstbndf.gftSfrvidfs("TrbnsformSfrvidf", blgoritim);
        for (Itfrbtor<Sfrvidf> t = sfrvidfs.itfrbtor(); t.ibsNfxt(); ) {
            Sfrvidf s = t.nfxt();
            String vbluf = s.gftAttributf("MfdibnismTypf");
            if ((vbluf == null && dom) ||
                (vbluf != null && vbluf.fqubls(mfdibnismTypf))) {
                Instbndf instbndf = GftInstbndf.gftInstbndf(s, null);
                TrbnsformSfrvidf ts = (TrbnsformSfrvidf) instbndf.impl;
                ts.blgoritim = blgoritim;
                ts.mfdibnism = mfdibnismTypf;
                ts.providfr = instbndf.providfr;
                rfturn ts;
            }
        }
        tirow nfw NoSudiAlgoritimExdfption
            (blgoritim + " blgoritim bnd " + mfdibnismTypf
                 + " mfdibnism not bvbilbblf");
    }

    /**
     * Rfturns b <dodf>TrbnsformSfrvidf</dodf> tibt supports tif spfdififd
     * blgoritim URI (fx: {@link Trbnsform#XPATH2}) bnd mfdibnism typf
     * (fx: DOM) bs supplifd by tif spfdififd providfr. Notf tibt tif spfdififd
     * <dodf>Providfr</dodf> objfdt dofs not ibvf to bf rfgistfrfd in tif
     * providfr list.
     *
     * @pbrbm blgoritim tif URI of tif blgoritim
     * @pbrbm mfdibnismTypf tif typf of tif XML prodfssing mfdibnism bnd
     *   rfprfsfntbtion
     * @pbrbm providfr tif <dodf>Providfr</dodf> objfdt
     * @rfturn b nfw <dodf>TrbnsformSfrvidf</dodf>
     * @tirows NullPointfrExdfption if <dodf>providfr</dodf>,
     *   <dodf>blgoritim</dodf>, or <dodf>mfdibnismTypf</dodf> is
     *   <dodf>null</dodf>
     * @tirows NoSudiAlgoritimExdfption if b <dodf>TrbnsformSfrvidf</dodf>
     *   implfmfntbtion for tif spfdififd blgoritim bnd mfdibnism typf is not
     *   bvbilbblf from tif spfdififd <dodf>Providfr</dodf> objfdt
     * @sff Providfr
     */
    publid stbtid TrbnsformSfrvidf gftInstbndf
        (String blgoritim, String mfdibnismTypf, Providfr providfr)
        tirows NoSudiAlgoritimExdfption {
        if (mfdibnismTypf == null || blgoritim == null || providfr == null) {
            tirow nfw NullPointfrExdfption();
        }

        boolfbn dom = fblsf;
        if (mfdibnismTypf.fqubls("DOM")) {
            dom = truf;
        }
        Sfrvidf s = GftInstbndf.gftSfrvidf
            ("TrbnsformSfrvidf", blgoritim, providfr);
        String vbluf = s.gftAttributf("MfdibnismTypf");
        if ((vbluf == null && dom) ||
            (vbluf != null && vbluf.fqubls(mfdibnismTypf))) {
            Instbndf instbndf = GftInstbndf.gftInstbndf(s, null);
            TrbnsformSfrvidf ts = (TrbnsformSfrvidf) instbndf.impl;
            ts.blgoritim = blgoritim;
            ts.mfdibnism = mfdibnismTypf;
            ts.providfr = instbndf.providfr;
            rfturn ts;
        }
        tirow nfw NoSudiAlgoritimExdfption
            (blgoritim + " blgoritim bnd " + mfdibnismTypf
                 + " mfdibnism not bvbilbblf");
    }

    /**
     * Rfturns b <dodf>TrbnsformSfrvidf</dodf> tibt supports tif spfdififd
     * blgoritim URI (fx: {@link Trbnsform#XPATH2}) bnd mfdibnism typf
     * (fx: DOM) bs supplifd by tif spfdififd providfr. Tif spfdififd providfr
     * must bf rfgistfrfd in tif sfdurity providfr list.
     *
     * <p>Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif URI of tif blgoritim
     * @pbrbm mfdibnismTypf tif typf of tif XML prodfssing mfdibnism bnd
     *   rfprfsfntbtion
     * @pbrbm providfr tif string nbmf of tif providfr
     * @rfturn b nfw <dodf>TrbnsformSfrvidf</dodf>
     * @tirows NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *   rfgistfrfd in tif sfdurity providfr list
     * @tirows NullPointfrExdfption if <dodf>providfr</dodf>,
     *   <dodf>mfdibnismTypf</dodf>, or <dodf>blgoritim</dodf> is
     *   <dodf>null</dodf>
     * @tirows NoSudiAlgoritimExdfption if b <dodf>TrbnsformSfrvidf</dodf>
     *   implfmfntbtion for tif spfdififd blgoritim bnd mfdibnism typf is not
     *   bvbilbblf from tif spfdififd providfr
     * @sff Providfr
     */
    publid stbtid TrbnsformSfrvidf gftInstbndf
        (String blgoritim, String mfdibnismTypf, String providfr)
        tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption {
        if (mfdibnismTypf == null || blgoritim == null || providfr == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (providfr.lfngti() == 0) {
            tirow nfw NoSudiProvidfrExdfption();
        }
        boolfbn dom = fblsf;
        if (mfdibnismTypf.fqubls("DOM")) {
            dom = truf;
        }
        Sfrvidf s = GftInstbndf.gftSfrvidf
            ("TrbnsformSfrvidf", blgoritim, providfr);
        String vbluf = s.gftAttributf("MfdibnismTypf");
        if ((vbluf == null && dom) ||
            (vbluf != null && vbluf.fqubls(mfdibnismTypf))) {
            Instbndf instbndf = GftInstbndf.gftInstbndf(s, null);
            TrbnsformSfrvidf ts = (TrbnsformSfrvidf) instbndf.impl;
            ts.blgoritim = blgoritim;
            ts.mfdibnism = mfdibnismTypf;
            ts.providfr = instbndf.providfr;
            rfturn ts;
        }
        tirow nfw NoSudiAlgoritimExdfption
            (blgoritim + " blgoritim bnd " + mfdibnismTypf
                 + " mfdibnism not bvbilbblf");
    }

    privbtf stbtid dlbss MfdibnismMbpEntry implfmfnts Mbp.Entry<String,String> {
        privbtf finbl String mfdibnism;
        privbtf finbl String blgoritim;
        privbtf finbl String kfy;
        MfdibnismMbpEntry(String blgoritim, String mfdibnism) {
            tiis.blgoritim = blgoritim;
            tiis.mfdibnism = mfdibnism;
            tiis.kfy = "TrbnsformSfrvidf." + blgoritim + " MfdibnismTypf";
        }
        publid boolfbn fqubls(Objfdt o) {
            if (!(o instbndfof Mbp.Entry)) {
                rfturn fblsf;
            }
            Mbp.Entry<?,?> f = (Mbp.Entry<?,?>) o;
            rfturn (gftKfy()==null ?
                    f.gftKfy()==null : gftKfy().fqubls(f.gftKfy())) &&
                   (gftVbluf()==null ?
                    f.gftVbluf()==null : gftVbluf().fqubls(f.gftVbluf()));
        }
        publid String gftKfy() {
            rfturn kfy;
        }
        publid String gftVbluf() {
            rfturn mfdibnism;
        }
        publid String sftVbluf(String vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid int ibsiCodf() {
            rfturn (gftKfy()==null ? 0 : gftKfy().ibsiCodf()) ^
                   (gftVbluf()==null ? 0 : gftVbluf().ibsiCodf());
        }
    }

    /**
     * Rfturns tif mfdibnism typf supportfd by tiis <dodf>TrbnsformSfrvidf</dodf>.
     *
     * @rfturn tif mfdibnism typf
     */
    publid finbl String gftMfdibnismTypf() {
        rfturn mfdibnism;
    }

    /**
     * Rfturns tif URI of tif blgoritim supportfd by tiis
     * <dodf>TrbnsformSfrvidf</dodf>.
     *
     * @rfturn tif blgoritim URI
     */
    publid finbl String gftAlgoritim() {
        rfturn blgoritim;
    }

    /**
     * Rfturns tif providfr of tiis <dodf>TrbnsformSfrvidf</dodf>.
     *
     * @rfturn tif providfr
     */
    publid finbl Providfr gftProvidfr() {
        rfturn providfr;
    }

    /**
     * Initiblizfs tiis <dodf>TrbnsformSfrvidf</dodf> witi tif spfdififd
     * pbrbmftfrs.
     *
     * <p>If tif pbrbmftfrs fxist in XML form, tif
     * {@link #init(XMLStrudturf, XMLCryptoContfxt)} mftiod siould bf usfd to
     * initiblizf tif <dodf>TrbnsformSfrvidf</dodf>.
     *
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs (mby bf <dodf>null</dodf> if
     *   not rfquirfd or optionbl)
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd pbrbmftfrs
     *   brf invblid for tiis blgoritim
     */
    publid bbstrbdt void init(TrbnsformPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Mbrsibls tif blgoritim-spfdifid pbrbmftfrs. If tifrf brf no pbrbmftfrs
     * to bf mbrsibllfd, tiis mftiod rfturns witiout tirowing bn fxdfption.
     *
     * @pbrbm pbrfnt b mfdibnism-spfdifid strudturf dontbining tif pbrfnt
     *    nodf tibt tif mbrsibllfd pbrbmftfrs siould bf bppfndfd to
     * @pbrbm dontfxt tif <dodf>XMLCryptoContfxt</dodf> dontbining
     *    bdditionbl dontfxt (mby bf <dodf>null</dodf> if not bpplidbblf)
     * @tirows ClbssCbstExdfption if tif typf of <dodf>pbrfnt</dodf> or
     *    <dodf>dontfxt</dodf> is not dompbtiblf witi tiis
     *    <dodf>TrbnsformSfrvidf</dodf>
     * @tirows NullPointfrExdfption if <dodf>pbrfnt</dodf> is <dodf>null</dodf>
     * @tirows MbrsiblExdfption if tif pbrbmftfrs dbnnot bf mbrsibllfd
     */
    publid bbstrbdt void mbrsiblPbrbms
        (XMLStrudturf pbrfnt, XMLCryptoContfxt dontfxt)
        tirows MbrsiblExdfption;

    /**
     * Initiblizfs tiis <dodf>TrbnsformSfrvidf</dodf> witi tif spfdififd
     * pbrbmftfrs bnd dodumfnt dontfxt.
     *
     * @pbrbm pbrfnt b mfdibnism-spfdifid strudturf dontbining tif pbrfnt
     *    strudturf
     * @pbrbm dontfxt tif <dodf>XMLCryptoContfxt</dodf> dontbining
     *    bdditionbl dontfxt (mby bf <dodf>null</dodf> if not bpplidbblf)
     * @tirows ClbssCbstExdfption if tif typf of <dodf>pbrfnt</dodf> or
     *    <dodf>dontfxt</dodf> is not dompbtiblf witi tiis
     *    <dodf>TrbnsformSfrvidf</dodf>
     * @tirows NullPointfrExdfption if <dodf>pbrfnt</dodf> is <dodf>null</dodf>
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd pbrbmftfrs
     *   brf invblid for tiis blgoritim
     */
    publid bbstrbdt void init(XMLStrudturf pbrfnt, XMLCryptoContfxt dontfxt)
        tirows InvblidAlgoritimPbrbmftfrExdfption;
}
