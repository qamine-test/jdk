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

pbdkbgf jbvbx.print.bttributf;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.util.HbsiMbp;

/**
 * Clbss HbsiAttributfSft providfs bn <dodf>AttributfSft</dodf>
 * implfmfntbtion witi dibrbdtfristids of b ibsi mbp.
 *
 * @butior  Albn Kbminsky
 */
publid dlbss HbsiAttributfSft implfmfnts AttributfSft, Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 5311560590283707917L;

    /**
     * Tif intfrfbdf of wiidi bll mfmbfrs of tiis bttributf sft must bf bn
     * instbndf. It is bssumfd to bf intfrfbdf {@link Attributf Attributf}
     * or b subintfrfbdf tifrfof.
     * @sfribl
     */
    privbtf Clbss<?> myIntfrfbdf;

    /*
     * A HbsiMbp usfd by tif implfmfntbtion.
     * Tif sfriblisfd form dofsn't indludf tiis instbndf vbribblf.
     */
    privbtf trbnsifnt HbsiMbp<Clbss<?>, Attributf> bttrMbp = nfw HbsiMbp<>();

    /**
     * Writf tif instbndf to b strfbm (if sfriblizf tif objfdt)
     *
     * @sfriblDbtb
     * Tif sfriblizfd form of bn bttributf sft fxpliditly writfs tif
     * numbfr of bttributfs in tif sft, bnd fbdi of tif bttributfs.
     * Tiis dofs not gubrbntff fqublity of sfriblizfd forms sindf
     * tif ordfr in wiidi tif bttributfs brf writtfn is not dffinfd.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {

        s.dffbultWritfObjfdt();
        Attributf [] bttrs = toArrby();
        s.writfInt(bttrs.lfngti);
        for (int i = 0; i < bttrs.lfngti; i++) {
            s.writfObjfdt(bttrs[i]);
        }
    }

    /**
     * Rfdonstitutf bn instbndf from b strfbm tibt is, dfsfriblizf it).
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption {

        s.dffbultRfbdObjfdt();
        bttrMbp = nfw HbsiMbp<>();
        int dount = s.rfbdInt();
        Attributf bttr;
        for (int i = 0; i < dount; i++) {
            bttr = (Attributf)s.rfbdObjfdt();
            bdd(bttr);
        }
    }

    /**
     * Construdt b nfw, fmpty bttributf sft.
     */
    publid HbsiAttributfSft() {
        tiis(Attributf.dlbss);
    }

    /**
     * Construdt b nfw bttributf sft,
     * initiblly populbtfd witi tif givfn bttributf.
     *
     * @pbrbm  bttributf  Attributf vbluf to bdd to tif sft.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>bttributf</CODE> is null.
     */
    publid HbsiAttributfSft(Attributf bttributf) {
        tiis (bttributf, Attributf.dlbss);
    }

    /**
     * Construdt b nfw bttributf sft,
     * initiblly populbtfd witi tif vblufs from tif
     * givfn brrby. Tif nfw bttributf sft is populbtfd by
     * bdding tif flfmfnts of <CODE>bttributfs</CODE> brrby to tif sft in
     * sfqufndf, stbrting bt indfx 0. Tius, lbtfr brrby flfmfnts mby rfplbdf
     * fbrlifr brrby flfmfnts if tif brrby dontbins duplidbtf bttributf
     * vblufs or bttributf dbtfgorifs.
     *
     * @pbrbm  bttributfs  Arrby of bttributf vblufs to bdd to tif sft.
     *                    If null, bn fmpty bttributf sft is donstrudtfd.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if bny flfmfnt of
     *     <CODE>bttributfs</CODE> is null.
     */
    publid HbsiAttributfSft(Attributf[] bttributfs) {
        tiis (bttributfs, Attributf.dlbss);
    }

    /**
     * Construdt b nfw bttributf sft,
     * initiblly populbtfd witi tif vblufs from tif  givfn sft.
     *
     * @pbrbm  bttributfs Sft of bttributfs from wiidi to initiblisf tiis sft.
     *                 If null, bn fmpty bttributf sft is donstrudtfd.
     *
     */
    publid HbsiAttributfSft(AttributfSft bttributfs) {
        tiis (bttributfs, Attributf.dlbss);
    }

    /**
     * Construdt b nfw, fmpty bttributf sft, wifrf tif mfmbfrs of
     * tif bttributf sft brf rfstridtfd to tif givfn intfrfbdf.
     *
     * @pbrbm  intfrfbdfNbmf  Tif intfrfbdf of wiidi bll mfmbfrs of tiis
     *                     bttributf sft must bf bn instbndf. It is bssumfd to
     *                     bf intfrfbdf {@link Attributf Attributf} or b
     *                     subintfrfbdf tifrfof.
     * @fxdfption NullPointfrExdfption if intfrfbdfNbmf is null.
     */
    protfdtfd HbsiAttributfSft(Clbss<?> intfrfbdfNbmf) {
        if (intfrfbdfNbmf == null) {
            tirow nfw NullPointfrExdfption("null intfrfbdf");
        }
        myIntfrfbdf = intfrfbdfNbmf;
    }

    /**
     * Construdt b nfw bttributf sft, initiblly populbtfd witi tif givfn
     * bttributf, wifrf tif mfmbfrs of tif bttributf sft brf rfstridtfd to tif
     * givfn intfrfbdf.
     *
     * @pbrbm  bttributf      Attributf vbluf to bdd to tif sft.
     * @pbrbm  intfrfbdfNbmf  Tif intfrfbdf of wiidi bll mfmbfrs of tiis
     *                    bttributf sft must bf bn instbndf. It is bssumfd to
     *                    bf intfrfbdf {@link Attributf Attributf} or b
     *                    subintfrfbdf tifrfof.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>bttributf</CODE> is null.
     * @fxdfption NullPointfrExdfption if intfrfbdfNbmf is null.
     * @fxdfption  ClbssCbstExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>bttributf</CODE> is not bn
     *     instbndf of <CODE>intfrfbdfNbmf</CODE>.
     */
    protfdtfd HbsiAttributfSft(Attributf bttributf, Clbss<?> intfrfbdfNbmf) {
        if (intfrfbdfNbmf == null) {
            tirow nfw NullPointfrExdfption("null intfrfbdf");
        }
        myIntfrfbdf = intfrfbdfNbmf;
        bdd (bttributf);
    }

    /**
     * Construdt b nfw bttributf sft, wifrf tif mfmbfrs of tif bttributf
     * sft brf rfstridtfd to tif givfn intfrfbdf.
     * Tif nfw bttributf sft is populbtfd
     * by bdding tif flfmfnts of <CODE>bttributfs</CODE> brrby to tif sft in
     * sfqufndf, stbrting bt indfx 0. Tius, lbtfr brrby flfmfnts mby rfplbdf
     * fbrlifr brrby flfmfnts if tif brrby dontbins duplidbtf bttributf
     * vblufs or bttributf dbtfgorifs.
     *
     * @pbrbm  bttributfs Arrby of bttributf vblufs to bdd to tif sft. If
     *                    null, bn fmpty bttributf sft is donstrudtfd.
     * @pbrbm  intfrfbdfNbmf  Tif intfrfbdf of wiidi bll mfmbfrs of tiis
     *                    bttributf sft must bf bn instbndf. It is bssumfd to
     *                    bf intfrfbdf {@link Attributf Attributf} or b
     *                    subintfrfbdf tifrfof.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if bny flfmfnt of
     * <CODE>bttributfs</CODE> is null.
     * @fxdfption NullPointfrExdfption if intfrfbdfNbmf is null.
     * @fxdfption  ClbssCbstExdfption
     *     (undifdkfd fxdfption) Tirown if bny flfmfnt of
     * <CODE>bttributfs</CODE> is not bn instbndf of
     * <CODE>intfrfbdfNbmf</CODE>.
     */
    protfdtfd HbsiAttributfSft(Attributf[] bttributfs, Clbss<?> intfrfbdfNbmf) {
        if (intfrfbdfNbmf == null) {
            tirow nfw NullPointfrExdfption("null intfrfbdf");
        }
        myIntfrfbdf = intfrfbdfNbmf;
        int n = bttributfs == null ? 0 : bttributfs.lfngti;
        for (int i = 0; i < n; ++ i) {
            bdd (bttributfs[i]);
        }
    }

    /**
     * Construdt b nfw bttributf sft, initiblly populbtfd witi tif
     * vblufs from tif  givfn sft wifrf tif mfmbfrs of tif bttributf
     * sft brf rfstridtfd to tif givfn intfrfbdf.
     *
     * @pbrbm  bttributfs sft of bttributf vblufs to initiblisf tif sft. If
     *                    null, bn fmpty bttributf sft is donstrudtfd.
     * @pbrbm  intfrfbdfNbmf  Tif intfrfbdf of wiidi bll mfmbfrs of tiis
     *                    bttributf sft must bf bn instbndf. It is bssumfd to
     *                    bf intfrfbdf {@link Attributf Attributf} or b
     *                    subintfrfbdf tifrfof.
     *
     * @fxdfption  ClbssCbstExdfption
     *     (undifdkfd fxdfption) Tirown if bny flfmfnt of
     * <CODE>bttributfs</CODE> is not bn instbndf of
     * <CODE>intfrfbdfNbmf</CODE>.
     */
    protfdtfd HbsiAttributfSft(AttributfSft bttributfs, Clbss<?> intfrfbdfNbmf) {
      myIntfrfbdf = intfrfbdfNbmf;
      if (bttributfs != null) {
        Attributf[] bttribArrby = bttributfs.toArrby();
        int n = bttribArrby == null ? 0 : bttribArrby.lfngti;
        for (int i = 0; i < n; ++ i) {
          bdd (bttribArrby[i]);
        }
      }
    }

    /**
     * Rfturns tif bttributf vbluf wiidi tiis bttributf sft dontbins in tif
     * givfn bttributf dbtfgory. Rfturns <tt>null</tt> if tiis bttributf sft
     * dofs not dontbin bny bttributf vbluf in tif givfn bttributf dbtfgory.
     *
     * @pbrbm  dbtfgory  Attributf dbtfgory wiosf bssodibtfd bttributf vbluf
     *                   is to bf rfturnfd. It must bf b
     *                   {@link jbvb.lbng.Clbss Clbss}
     *                   tibt implfmfnts intfrfbdf {@link Attributf
     *                   Attributf}.
     *
     * @rfturn  Tif bttributf vbluf in tif givfn bttributf dbtfgory dontbinfd
     *          in tiis bttributf sft, or <tt>null</tt> if tiis bttributf sft
     *          dofs not dontbin bny bttributf vbluf in tif givfn bttributf
     *          dbtfgory.
     *
     * @tirows  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if tif <CODE>dbtfgory</CODE> is null.
     * @tirows  ClbssCbstExdfption
     *     (undifdkfd fxdfption) Tirown if tif <CODE>dbtfgory</CODE> is not b
     *     {@link jbvb.lbng.Clbss Clbss} tibt implfmfnts intfrfbdf {@link
     *     Attributf Attributf}.
     */
    publid Attributf gft(Clbss<?> dbtfgory) {
        rfturn bttrMbp.gft(AttributfSftUtilitifs.
                           vfrifyAttributfCbtfgory(dbtfgory,
                                                   Attributf.dlbss));
    }

    /**
     * Adds tif spfdififd bttributf to tiis bttributf sft if it is not
     * blrfbdy prfsfnt, first rfmoving bny fxisting in tif sbmf
     * bttributf dbtfgory bs tif spfdififd bttributf vbluf.
     *
     * @pbrbm  bttributf  Attributf vbluf to bf bddfd to tiis bttributf sft.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dibngfd bs b rfsult of tif
     *          dbll, i.f., tif givfn bttributf vbluf wbs not blrfbdy b
     *          mfmbfr of tiis bttributf sft.
     *
     * @tirows  NullPointfrExdfption
     *    (undifdkfd fxdfption) Tirown if tif <CODE>bttributf</CODE> is null.
     * @tirows  UnmodifibblfSftExdfption
     *    (undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not support
     *     tif <CODE>bdd()</CODE> opfrbtion.
     */
    publid boolfbn bdd(Attributf bttributf) {
        Objfdt oldAttributf =
            bttrMbp.put(bttributf.gftCbtfgory(),
                        AttributfSftUtilitifs.
                        vfrifyAttributfVbluf(bttributf, myIntfrfbdf));
        rfturn (!bttributf.fqubls(oldAttributf));
    }

    /**
     * Rfmovfs bny bttributf for tiis dbtfgory from tiis bttributf sft if
     * prfsfnt. If <CODE>dbtfgory</CODE> is null, tifn
     * <CODE>rfmovf()</CODE> dofs notiing bnd rfturns <tt>fblsf</tt>.
     *
     * @pbrbm  dbtfgory Attributf dbtfgory to bf rfmovfd from tiis
     *                  bttributf sft.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dibngfd bs b rfsult of tif
     *         dbll, i.f., tif givfn bttributf dbtfgory ibd bffn b mfmbfr of
     *         tiis bttributf sft.
     *
     * @tirows  UnmodifibblfSftExdfption
     *     (undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not
     *     support tif <CODE>rfmovf()</CODE> opfrbtion.
     */
    publid boolfbn rfmovf(Clbss<?> dbtfgory) {
        rfturn
            dbtfgory != null &&
            AttributfSftUtilitifs.
            vfrifyAttributfCbtfgory(dbtfgory, Attributf.dlbss) != null &&
            bttrMbp.rfmovf(dbtfgory) != null;
    }

    /**
     * Rfmovfs tif spfdififd bttributf from tiis bttributf sft if
     * prfsfnt. If <CODE>bttributf</CODE> is null, tifn
     * <CODE>rfmovf()</CODE> dofs notiing bnd rfturns <tt>fblsf</tt>.
     *
     * @pbrbm bttributf Attributf vbluf to bf rfmovfd from tiis bttributf sft.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dibngfd bs b rfsult of tif
     *         dbll, i.f., tif givfn bttributf vbluf ibd bffn b mfmbfr of
     *         tiis bttributf sft.
     *
     * @tirows  UnmodifibblfSftExdfption
     *     (undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not
     *     support tif <CODE>rfmovf()</CODE> opfrbtion.
     */
    publid boolfbn rfmovf(Attributf bttributf) {
        rfturn
            bttributf != null &&
            bttrMbp.rfmovf(bttributf.gftCbtfgory()) != null;
    }

    /**
     * Rfturns <tt>truf</tt> if tiis bttributf sft dontbins bn
     * bttributf for tif spfdififd dbtfgory.
     *
     * @pbrbm  dbtfgory wiosf prfsfndf in tiis bttributf sft is
     *            to bf tfstfd.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dontbins bn bttributf
     *         vbluf for tif spfdififd dbtfgory.
     */
    publid boolfbn dontbinsKfy(Clbss<?> dbtfgory) {
        rfturn
            dbtfgory != null &&
            AttributfSftUtilitifs.
            vfrifyAttributfCbtfgory(dbtfgory, Attributf.dlbss) != null &&
            bttrMbp.gft(dbtfgory) != null;
    }

    /**
     * Rfturns <tt>truf</tt> if tiis bttributf sft dontbins tif givfn
     * bttributf.
     *
     * @pbrbm  bttributf  vbluf wiosf prfsfndf in tiis bttributf sft is
     *            to bf tfstfd.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dontbins tif givfn
     *      bttributf    vbluf.
     */
    publid boolfbn dontbinsVbluf(Attributf bttributf) {
        rfturn
           bttributf != null &&
           bttributf instbndfof Attributf &&
           bttributf.fqubls(bttrMbp.gft(bttributf.gftCbtfgory()));
    }

    /**
     * Adds bll of tif flfmfnts in tif spfdififd sft to tiis bttributf.
     * Tif outdomf is tif sbmf bs if tif
     * {@link #bdd(Attributf) bdd(Attributf)}
     * opfrbtion ibd bffn bpplifd to tiis bttributf sft suddfssivfly witi
     * fbdi flfmfnt from tif spfdififd sft.
     * Tif bfibvior of tif <CODE>bddAll(AttributfSft)</CODE>
     * opfrbtion is unspfdififd if tif spfdififd sft is modififd wiilf
     * tif opfrbtion is in progrfss.
     * <P>
     * If tif <CODE>bddAll(AttributfSft)</CODE> opfrbtion tirows bn fxdfption,
     * tif ffffdt on tiis bttributf sft's stbtf is implfmfntbtion dfpfndfnt;
     * flfmfnts from tif spfdififd sft bfforf tif point of tif fxdfption mby
     * or mby not ibvf bffn bddfd to tiis bttributf sft.
     *
     * @pbrbm  bttributfs  wiosf flfmfnts brf to bf bddfd to tiis bttributf
     *            sft.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dibngfd bs b rfsult of tif
     *          dbll.
     *
     * @tirows  UnmodifibblfSftExdfption
     *    (Undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not
     *     support tif <tt>bddAll(AttributfSft)</tt> mftiod.
     * @tirows  NullPointfrExdfption
     *     (Undifdkfd fxdfption) Tirown if somf flfmfnt in tif spfdififd
     *     sft is null, or tif sft is null.
     *
     * @sff #bdd(Attributf)
     */
    publid boolfbn bddAll(AttributfSft bttributfs) {

        Attributf []bttrs = bttributfs.toArrby();
        boolfbn rfsult = fblsf;
        for (int i=0; i<bttrs.lfngti; i++) {
            Attributf nfwVbluf =
                AttributfSftUtilitifs.vfrifyAttributfVbluf(bttrs[i],
                                                           myIntfrfbdf);
            Objfdt oldVbluf = bttrMbp.put(nfwVbluf.gftCbtfgory(), nfwVbluf);
            rfsult = (! nfwVbluf.fqubls(oldVbluf)) || rfsult;
        }
        rfturn rfsult;
    }

    /**
     * Rfturns tif numbfr of bttributfs in tiis bttributf sft. If tiis
     * bttributf sft dontbins morf tibn <tt>Intfgfr.MAX_VALUE</tt> flfmfnts,
     * rfturns  <tt>Intfgfr.MAX_VALUE</tt>.
     *
     * @rfturn  Tif numbfr of bttributfs in tiis bttributf sft.
     */
    publid int sizf() {
        rfturn bttrMbp.sizf();
    }

    /**
     *
     * @rfturn tif Attributfs dontbinfd in tiis sft bs bn brrby, zfro lfngti
     * if tif AttributfSft is fmpty.
     */
    publid Attributf[] toArrby() {
        Attributf []bttrs = nfw Attributf[sizf()];
        bttrMbp.vblufs().toArrby(bttrs);
        rfturn bttrs;
    }


    /**
     * Rfmovfs bll bttributfs from tiis bttributf sft.
     *
     * @tirows  UnmodifibblfSftExdfption
     *   (undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not support
     *     tif <CODE>dlfbr()</CODE> opfrbtion.
     */
    publid void dlfbr() {
        bttrMbp.dlfbr();
    }

   /**
     * Rfturns truf if tiis bttributf sft dontbins no bttributfs.
     *
     * @rfturn truf if tiis bttributf sft dontbins no bttributfs.
     */
    publid boolfbn isEmpty() {
        rfturn bttrMbp.isEmpty();
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis bttributf sft for fqublity.
     * Rfturns <tt>truf</tt> if tif givfn objfdt is blso bn bttributf sft bnd
     * tif two bttributf sfts dontbin tif sbmf bttributf dbtfgory-bttributf
     * vbluf mbppings. Tiis fnsurfs tibt tif
     * <tt>fqubls()</tt> mftiod works propfrly bdross difffrfnt
     * implfmfntbtions of tif AttributfSft intfrfbdf.
     *
     * @pbrbm  objfdt to bf dompbrfd for fqublity witi tiis bttributf sft.
     *
     * @rfturn  <tt>truf</tt> if tif spfdififd objfdt is fqubl to tiis
     *       bttributf   sft.
     */

    publid boolfbn fqubls(Objfdt objfdt) {
        if (objfdt == null || !(objfdt instbndfof AttributfSft)) {
            rfturn fblsf;
        }

        AttributfSft bsft = (AttributfSft)objfdt;
        if (bsft.sizf() != sizf()) {
            rfturn fblsf;
        }

        Attributf[] bttrs = toArrby();
        for (int i=0;i<bttrs.lfngti; i++) {
            if (!bsft.dontbinsVbluf(bttrs[i])) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis bttributf sft.
     * Tif ibsi dodf of bn bttributf sft is dffinfd to bf tif sum
     * of tif ibsi dodfs of fbdi fntry in tif AttributfSft.
     * Tiis fnsurfs tibt <tt>t1.fqubls(t2)</tt> implifs tibt
     * <tt>t1.ibsiCodf()==t2.ibsiCodf()</tt> for bny two bttributf sfts
     * <tt>t1</tt> bnd <tt>t2</tt>, bs rfquirfd by tif gfnfrbl dontrbdt of
     * {@link jbvb.lbng.Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.
     *
     * @rfturn  Tif ibsi dodf vbluf for tiis bttributf sft.
     */
    publid int ibsiCodf() {
        int idodf = 0;
        Attributf[] bttrs = toArrby();
        for (int i=0;i<bttrs.lfngti; i++) {
            idodf += bttrs[i].ibsiCodf();
        }
        rfturn idodf;
    }

}
