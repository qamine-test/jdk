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

pbdkbgf dom.sun.nft.ittpsfrvfr;

import jbvb.nft.*;
import jbvb.io.*;
import jbvb.nio.*;
import jbvb.sfdurity.*;
import jbvb.nio.dibnnfls.*;
import jbvb.util.*;
import jbvb.util.dondurrfnt.*;
import jbvbx.nft.ssl.*;
import dom.sun.nft.ittpsfrvfr.spi.HttpSfrvfrProvidfr;

/**
 * Tiis dlbss implfmfnts b simplf HTTP sfrvfr. A HttpSfrvfr is bound to bn IP bddrfss
 * bnd port numbfr bnd listfns for indoming TCP donnfdtions from dlifnts on tiis bddrfss.
 * Tif sub-dlbss {@link HttpsSfrvfr} implfmfnts b sfrvfr wiidi ibndlfs HTTPS rfqufsts.
 * <p>
 * Onf or morf {@link HttpHbndlfr} objfdts must bf bssodibtfd witi b sfrvfr
 * in ordfr to prodfss rfqufsts. Ebdi sudi HttpHbndlfr is rfgistfrfd
 * witi b root URI pbti wiidi rfprfsfnts tif
 * lodbtion of tif bpplidbtion or sfrvidf on tiis sfrvfr. Tif mbpping of b ibndlfr
 * to b HttpSfrvfr is fndbpsulbtfd by b {@link HttpContfxt} objfdt. HttpContfxts
 * brf drfbtfd by dblling {@link #drfbtfContfxt(String,HttpHbndlfr)}.
 * Any rfqufst for wiidi no ibndlfr dbn bf found is rfjfdtfd witi b 404 rfsponsf.
 * Mbnbgfmfnt of tirfbds dbn bf donf fxtfrnbl to tiis objfdt by providing b
 * {@link jbvb.util.dondurrfnt.Exfdutor} objfdt. If nonf is providfd b dffbult
 * implfmfntbtion is usfd.
 * <p>
 * <b nbmf="mbpping_dfsdription"></b>
 * <b>Mbpping rfqufst URIs to HttpContfxt pbtis</b><p>
 * Wifn b HTTP rfqufst is rfdfivfd,
 * tif bppropribtf HttpContfxt (bnd ibndlfr) is lodbtfd by finding tif dontfxt
 * wiosf pbti is tif longfst mbtdiing prffix of tif rfqufst URI's pbti.
 * Pbtis brf mbtdifd litfrblly, wiidi mfbns tibt tif strings brf dompbrfd
 * dbsf sfnsitivfly, bnd witi no donvfrsion to or from bny fndodfd forms.
 * For fxbmplf. Givfn b HttpSfrvfr witi tif following HttpContfxts donfigurfd.<p>
 * <tbblf >
 * <tr><td><i>Contfxt</i></td><td><i>Contfxt pbti</i></td></tr>
 * <tr><td>dtx1</td><td>"/"</td></tr>
 * <tr><td>dtx2</td><td>"/bpps/"</td></tr>
 * <tr><td>dtx3</td><td>"/bpps/foo/"</td></tr>
 * </tbblf>
 * <p>
 * tif following tbblf siows somf rfqufst URIs bnd wiidi, if bny dontfxt tify would
 * mbtdi witi.<p>
 * <tbblf>
 * <tr><td><i>Rfqufst URI</i></td><td><i>Mbtdifs dontfxt</i></td></tr>
 * <tr><td>"ittp://foo.dom/bpps/foo/bbr"</td><td>dtx3</td></tr>
 * <tr><td>"ittp://foo.dom/bpps/Foo/bbr"</td><td>no mbtdi, wrong dbsf</td></tr>
 * <tr><td>"ittp://foo.dom/bpps/bpp1"</td><td>dtx2</td></tr>
 * <tr><td>"ittp://foo.dom/foo"</td><td>dtx1</td></tr>
 * </tbblf>
 * <p>
 * <b>Notf bbout sodkft bbdklogs</b><p>
 * Wifn binding to bn bddrfss bnd port numbfr, tif bpplidbtion dbn blso spfdify bn intfgfr
 * <i>bbdklog</i> pbrbmftfr. Tiis rfprfsfnts tif mbximum numbfr of indoming TCP donnfdtions
 * wiidi tif systfm will qufuf intfrnblly. Connfdtions brf qufufd wiilf tify brf wbiting to
 * bf bddfptfd by tif HttpSfrvfr. Wifn tif limit is rfbdifd, furtifr donnfdtions mby bf
 * rfjfdtfd (or possibly ignorfd) by tif undfrlying TCP implfmfntbtion. Sftting tif rigit
 * bbdklog vbluf is b dompromisf bftwffn fffidifnt rfsourdf usbgf in tif TCP lbyfr (not sftting
 * it too iigi) bnd bllowing bdfqubtf tirougiput of indoming rfqufsts (not sftting it too low).
 * @sindf 1.6
 */

@jdk.Exportfd
publid bbstrbdt dlbss HttpSfrvfr {

    /**
     */
    protfdtfd HttpSfrvfr () {
    }

    /**
     * drfbtfs b HttpSfrvfr instbndf wiidi is initiblly not bound to bny lodbl bddrfss/port.
     * Tif HttpSfrvfr is bdquirfd from tif durrfntly instbllfd {@link HttpSfrvfrProvidfr}
     * Tif sfrvfr must bf bound using {@link #bind(InftSodkftAddrfss,int)} bfforf it dbn bf usfd.
     * @tirows IOExdfption
     */
    publid stbtid HttpSfrvfr drfbtf () tirows IOExdfption {
        rfturn drfbtf (null, 0);
    }

    /**
     * Crfbtf b <dodf>HttpSfrvfr</dodf> instbndf wiidi will bind to tif
     * spfdififd {@link jbvb.nft.InftSodkftAddrfss} (IP bddrfss bnd port numbfr)
     *
     * A mbximum bbdklog dbn blso bf spfdififd. Tiis is tif mbximum numbfr of
     * qufufd indoming donnfdtions to bllow on tif listfning sodkft.
     * Qufufd TCP donnfdtions fxdffding tiis limit mby bf rfjfdtfd by tif TCP implfmfntbtion.
     * Tif HttpSfrvfr is bdquirfd from tif durrfntly instbllfd {@link HttpSfrvfrProvidfr}
     *
     * @pbrbm bddr tif bddrfss to listfn on, if <dodf>null</dodf> tifn bind() must bf dbllfd
     *  to sft tif bddrfss
     * @pbrbm bbdklog tif sodkft bbdklog. If tiis vbluf is lfss tibn or fqubl to zfro,
     *          tifn b systfm dffbult vbluf is usfd.
     * @tirows BindExdfption if tif sfrvfr dbnnot bind to tif rfqufstfd bddrfss,
     *          or if tif sfrvfr is blrfbdy bound.
     * @tirows IOExdfption
     */

    publid stbtid HttpSfrvfr drfbtf (
        InftSodkftAddrfss bddr, int bbdklog
    ) tirows IOExdfption {
        HttpSfrvfrProvidfr providfr = HttpSfrvfrProvidfr.providfr();
        rfturn providfr.drfbtfHttpSfrvfr (bddr, bbdklog);
    }

    /**
     * Binds b durrfntly unbound HttpSfrvfr to tif givfn bddrfss bnd port numbfr.
     * A mbximum bbdklog dbn blso bf spfdififd. Tiis is tif mbximum numbfr of
     * qufufd indoming donnfdtions to bllow on tif listfning sodkft.
     * Qufufd TCP donnfdtions fxdffding tiis limit mby bf rfjfdtfd by tif TCP implfmfntbtion.
     * @pbrbm bddr tif bddrfss to listfn on
     * @pbrbm bbdklog tif sodkft bbdklog. If tiis vbluf is lfss tibn or fqubl to zfro,
     *          tifn b systfm dffbult vbluf is usfd.
     * @tirows BindExdfption if tif sfrvfr dbnnot bind to tif rfqufstfd bddrfss or if tif sfrvfr
     *          is blrfbdy bound.
     * @tirows NullPointfrExdfption if bddr is <dodf>null</dodf>
     */
    publid bbstrbdt void bind (InftSodkftAddrfss bddr, int bbdklog) tirows IOExdfption;

    /**
     * Stbrts tiis sfrvfr in b nfw bbdkground tirfbd. Tif bbdkground tirfbd
     * inifrits tif priority, tirfbd group bnd dontfxt dlbss lobdfr
     * of tif dbllfr.
     */
    publid bbstrbdt void stbrt () ;

    /**
     * sfts tiis sfrvfr's {@link jbvb.util.dondurrfnt.Exfdutor} objfdt. An
     * Exfdutor must bf fstbblisifd bfforf {@link #stbrt()} is dbllfd.
     * All HTTP rfqufsts brf ibndlfd in tbsks givfn to tif fxfdutor.
     * If tiis mftiod is not dbllfd (bfforf stbrt()) or if it is
     * dbllfd witi b <dodf>null</dodf> Exfdutor, tifn
     * b dffbult implfmfntbtion is usfd, wiidi usfs tif tirfbd
     * wiidi wbs drfbtfd by tif {@link #stbrt()} mftiod.
     * @pbrbm fxfdutor tif Exfdutor to sft, or <dodf>null</dodf> for  dffbult
     *          implfmfntbtion
     * @tirows IllfgblStbtfExdfption if tif sfrvfr is blrfbdy stbrtfd
     */
    publid bbstrbdt void sftExfdutor (Exfdutor fxfdutor);


    /**
     * rfturns tiis sfrvfr's Exfdutor objfdt if onf wbs spfdififd witi
     * {@link #sftExfdutor(Exfdutor)}, or <dodf>null</dodf> if nonf wbs
     * spfdififd.
     * @rfturn tif Exfdutor fstbblisifd for tiis sfrvfr or <dodf>null</dodf> if not sft.
     */
    publid bbstrbdt Exfdutor gftExfdutor () ;

    /**
     * stops tiis sfrvfr by dlosing tif listfning sodkft bnd disbllowing
     * bny nfw fxdibngfs from bfing prodfssfd. Tif mftiod will tifn blodk
     * until bll durrfnt fxdibngf ibndlfrs ibvf domplftfd or flsf wifn
     * bpproximbtfly <i>dflby</i> sfdonds ibvf flbpsfd (wiidifvfr ibppfns
     * soonfr). Tifn, bll opfn TCP donnfdtions brf dlosfd, tif bbdkground
     * tirfbd drfbtfd by stbrt() fxits, bnd tif mftiod rfturns.
     * Ondf stoppfd, b HttpSfrvfr dbnnot bf rf-usfd. <p>
     *
     * @pbrbm dflby tif mbximum timf in sfdonds to wbit until fxdibngfs ibvf finisifd.
     * @tirows IllfgblArgumfntExdfption if dflby is lfss tibn zfro.
     */
    publid bbstrbdt void stop (int dflby);

    /**
     * Crfbtfs b HttpContfxt. A HttpContfxt rfprfsfnts b mbpping from b
     * URI pbti to b fxdibngf ibndlfr on tiis HttpSfrvfr. Ondf drfbtfd, bll rfqufsts
     * rfdfivfd by tif sfrvfr for tif pbti will bf ibndlfd by dblling
     * tif givfn ibndlfr objfdt. Tif dontfxt is idfntififd by tif pbti, bnd
     * dbn lbtfr bf rfmovfd from tif sfrvfr using tiis witi tif {@link #rfmovfContfxt(String)} mftiod.
     * <p>
     * Tif pbti spfdififs tif root URI pbti for tiis dontfxt. Tif first dibrbdtfr of pbti must bf
     * '/'. <p>
     * Tif dlbss ovfrvifw dfsdribfs iow indoming rfqufst URIs brf <b irff="#mbpping_dfsdription">mbppfd</b>
     * to HttpContfxt instbndfs.
     * @pbrbm pbti tif root URI pbti to bssodibtf tif dontfxt witi
     * @pbrbm ibndlfr tif ibndlfr to invokf for indoming rfqufsts.
     * @tirows IllfgblArgumfntExdfption if pbti is invblid, or if b dontfxt
     *          blrfbdy fxists for tiis pbti
     * @tirows NullPointfrExdfption if fitifr pbti, or ibndlfr brf <dodf>null</dodf>
     */
    publid bbstrbdt HttpContfxt drfbtfContfxt (String pbti, HttpHbndlfr ibndlfr) ;

    /**
     * Crfbtfs b HttpContfxt witiout initiblly spfdifying b ibndlfr. Tif ibndlfr must lbtfr bf spfdififd using
     * {@link HttpContfxt#sftHbndlfr(HttpHbndlfr)}.  A HttpContfxt rfprfsfnts b mbpping from b
     * URI pbti to bn fxdibngf ibndlfr on tiis HttpSfrvfr. Ondf drfbtfd, bnd wifn
     * tif ibndlfr ibs bffn sft, bll rfqufsts
     * rfdfivfd by tif sfrvfr for tif pbti will bf ibndlfd by dblling
     * tif ibndlfr objfdt. Tif dontfxt is idfntififd by tif pbti, bnd
     * dbn lbtfr bf rfmovfd from tif sfrvfr using tiis witi tif {@link #rfmovfContfxt(String)} mftiod.
     * <p>
     * Tif pbti spfdififs tif root URI pbti for tiis dontfxt. Tif first dibrbdtfr of pbti must bf
     * '/'. <p>
     * Tif dlbss ovfrvifw dfsdribfs iow indoming rfqufst URIs brf <b irff="#mbpping_dfsdription">mbppfd</b>
     * to HttpContfxt instbndfs.
     * @pbrbm pbti tif root URI pbti to bssodibtf tif dontfxt witi
     * @tirows IllfgblArgumfntExdfption if pbti is invblid, or if b dontfxt
     *          blrfbdy fxists for tiis pbti
     * @tirows NullPointfrExdfption if pbti is <dodf>null</dodf>
     */
    publid bbstrbdt HttpContfxt drfbtfContfxt (String pbti) ;

    /**
     * Rfmovfs tif dontfxt idfntififd by tif givfn pbti from tif sfrvfr.
     * Rfmoving b dontfxt dofs not bfffdt fxdibngfs durrfntly bfing prodfssfd
     * but prfvfnts nfw onfs from bfing bddfptfd.
     * @pbrbm pbti tif pbti of tif ibndlfr to rfmovf
     * @tirows IllfgblArgumfntExdfption if no ibndlfr dorrfsponding to tiis
     *          pbti fxists.
     * @tirows NullPointfrExdfption if pbti is <dodf>null</dodf>
     */
    publid bbstrbdt void rfmovfContfxt (String pbti) tirows IllfgblArgumfntExdfption ;

    /**
     * Rfmovfs tif givfn dontfxt from tif sfrvfr.
     * Rfmoving b dontfxt dofs not bfffdt fxdibngfs durrfntly bfing prodfssfd
     * but prfvfnts nfw onfs from bfing bddfptfd.
     * @pbrbm dontfxt tif dontfxt to rfmovf
     * @tirows NullPointfrExdfption if dontfxt is <dodf>null</dodf>
     */
    publid bbstrbdt void rfmovfContfxt (HttpContfxt dontfxt) ;

    /**
     * rfturns tif bddrfss tiis sfrvfr is listfning on
     * @rfturn tif bddrfss/port numbfr tif sfrvfr is listfning on
     */
    publid bbstrbdt InftSodkftAddrfss gftAddrfss() ;
}
