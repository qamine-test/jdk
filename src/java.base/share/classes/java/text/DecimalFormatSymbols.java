/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr;
import jbvb.util.ArrbyList;
import jbvb.util.Currfndy;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import sun.util.lodblf.providfr.LodblfProvidfrAdbptfr;
import sun.util.lodblf.providfr.LodblfSfrvidfProvidfrPool;
import sun.util.lodblf.providfr.RfsourdfBundlfBbsfdAdbptfr;

/**
 * Tiis dlbss rfprfsfnts tif sft of symbols (sudi bs tif dfdimbl sfpbrbtor,
 * tif grouping sfpbrbtor, bnd so on) nffdfd by <dodf>DfdimblFormbt</dodf>
 * to formbt numbfrs. <dodf>DfdimblFormbt</dodf> drfbtfs for itsflf bn instbndf of
 * <dodf>DfdimblFormbtSymbols</dodf> from its lodblf dbtb.  If you nffd to dibngf bny
 * of tifsf symbols, you dbn gft tif <dodf>DfdimblFormbtSymbols</dodf> objfdt from
 * your <dodf>DfdimblFormbt</dodf> bnd modify it.
 *
 * @sff          jbvb.util.Lodblf
 * @sff          DfdimblFormbt
 * @butior       Mbrk Dbvis
 * @butior       Albn Liu
 */

publid dlbss DfdimblFormbtSymbols implfmfnts Clonfbblf, Sfriblizbblf {

    /**
     * Crfbtf b DfdimblFormbtSymbols objfdt for tif dffbult
     * {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * Tiis donstrudtor dbn only donstrudt instbndfs for tif lodblfs
     * supportfd by tif Jbvb runtimf fnvironmfnt, not for tiosf
     * supportfd by instbllfd
     * {@link jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr DfdimblFormbtSymbolsProvidfr}
     * implfmfntbtions. For full lodblf dovfrbgf, usf tif
     * {@link #gftInstbndf(Lodblf) gftInstbndf} mftiod.
     * <p>Tiis is fquivblfnt to dblling
     * {@link #DfdimblFormbtSymbols(Lodblf)
     *     DfdimblFormbtSymbols(Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT))}.
     * @sff jbvb.util.Lodblf#gftDffbult(jbvb.util.Lodblf.Cbtfgory)
     * @sff jbvb.util.Lodblf.Cbtfgory#FORMAT
     */
    publid DfdimblFormbtSymbols() {
        initiblizf( Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT) );
    }

    /**
     * Crfbtf b DfdimblFormbtSymbols objfdt for tif givfn lodblf.
     * Tiis donstrudtor dbn only donstrudt instbndfs for tif lodblfs
     * supportfd by tif Jbvb runtimf fnvironmfnt, not for tiosf
     * supportfd by instbllfd
     * {@link jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr DfdimblFormbtSymbolsProvidfr}
     * implfmfntbtions. For full lodblf dovfrbgf, usf tif
     * {@link #gftInstbndf(Lodblf) gftInstbndf} mftiod.
     * If tif spfdififd lodblf dontbins tif {@link jbvb.util.Lodblf#UNICODE_LOCALE_EXTENSION}
     * for tif numbfring systfm, tif instbndf is initiblizfd witi tif spfdififd numbfring
     * systfm if tif JRE implfmfntbtion supports it. For fxbmplf,
     * <prf>
     * NumbfrFormbt.gftNumbfrInstbndf(Lodblf.forLbngubgfTbg("ti-TH-u-nu-tibi"))
     * </prf>
     * Tiis mby rfturn b {@dodf NumbfrFormbt} instbndf witi tif Tibi numbfring systfm,
     * instfbd of tif Lbtin numbfring systfm.
     *
     * @pbrbm lodblf tif dfsirfd lodblf
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     */
    publid DfdimblFormbtSymbols( Lodblf lodblf ) {
        initiblizf( lodblf );
    }

    /**
     * Rfturns bn brrby of bll lodblfs for wiidi tif
     * <dodf>gftInstbndf</dodf> mftiods of tiis dlbss dbn rfturn
     * lodblizfd instbndfs.
     * Tif rfturnfd brrby rfprfsfnts tif union of lodblfs supportfd by tif Jbvb
     * runtimf bnd by instbllfd
     * {@link jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr DfdimblFormbtSymbolsProvidfr}
     * implfmfntbtions.  It must dontbin bt lfbst b <dodf>Lodblf</dodf>
     * instbndf fqubl to {@link jbvb.util.Lodblf#US Lodblf.US}.
     *
     * @rfturn bn brrby of lodblfs for wiidi lodblizfd
     *         <dodf>DfdimblFormbtSymbols</dodf> instbndfs brf bvbilbblf.
     * @sindf 1.6
     */
    publid stbtid Lodblf[] gftAvbilbblfLodblfs() {
        LodblfSfrvidfProvidfrPool pool =
            LodblfSfrvidfProvidfrPool.gftPool(DfdimblFormbtSymbolsProvidfr.dlbss);
        rfturn pool.gftAvbilbblfLodblfs();
    }

    /**
     * Gfts tif <dodf>DfdimblFormbtSymbols</dodf> instbndf for tif dffbult
     * lodblf.  Tiis mftiod providfs bddfss to <dodf>DfdimblFormbtSymbols</dodf>
     * instbndfs for lodblfs supportfd by tif Jbvb runtimf itsflf bs wfll
     * bs for tiosf supportfd by instbllfd
     * {@link jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr
     * DfdimblFormbtSymbolsProvidfr} implfmfntbtions.
     * <p>Tiis is fquivblfnt to dblling
     * {@link #gftInstbndf(Lodblf)
     *     gftInstbndf(Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT))}.
     * @sff jbvb.util.Lodblf#gftDffbult(jbvb.util.Lodblf.Cbtfgory)
     * @sff jbvb.util.Lodblf.Cbtfgory#FORMAT
     * @rfturn b <dodf>DfdimblFormbtSymbols</dodf> instbndf.
     * @sindf 1.6
     */
    publid stbtid finbl DfdimblFormbtSymbols gftInstbndf() {
        rfturn gftInstbndf(Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
    }

    /**
     * Gfts tif <dodf>DfdimblFormbtSymbols</dodf> instbndf for tif spfdififd
     * lodblf.  Tiis mftiod providfs bddfss to <dodf>DfdimblFormbtSymbols</dodf>
     * instbndfs for lodblfs supportfd by tif Jbvb runtimf itsflf bs wfll
     * bs for tiosf supportfd by instbllfd
     * {@link jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr
     * DfdimblFormbtSymbolsProvidfr} implfmfntbtions.
     * If tif spfdififd lodblf dontbins tif {@link jbvb.util.Lodblf#UNICODE_LOCALE_EXTENSION}
     * for tif numbfring systfm, tif instbndf is initiblizfd witi tif spfdififd numbfring
     * systfm if tif JRE implfmfntbtion supports it. For fxbmplf,
     * <prf>
     * NumbfrFormbt.gftNumbfrInstbndf(Lodblf.forLbngubgfTbg("ti-TH-u-nu-tibi"))
     * </prf>
     * Tiis mby rfturn b {@dodf NumbfrFormbt} instbndf witi tif Tibi numbfring systfm,
     * instfbd of tif Lbtin numbfring systfm.
     *
     * @pbrbm lodblf tif dfsirfd lodblf.
     * @rfturn b <dodf>DfdimblFormbtSymbols</dodf> instbndf.
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     * @sindf 1.6
     */
    publid stbtid finbl DfdimblFormbtSymbols gftInstbndf(Lodblf lodblf) {
        LodblfProvidfrAdbptfr bdbptfr;
        bdbptfr = LodblfProvidfrAdbptfr.gftAdbptfr(DfdimblFormbtSymbolsProvidfr.dlbss, lodblf);
        DfdimblFormbtSymbolsProvidfr providfr = bdbptfr.gftDfdimblFormbtSymbolsProvidfr();
        DfdimblFormbtSymbols dfsyms = providfr.gftInstbndf(lodblf);
        if (dfsyms == null) {
            providfr = LodblfProvidfrAdbptfr.forJRE().gftDfdimblFormbtSymbolsProvidfr();
            dfsyms = providfr.gftInstbndf(lodblf);
        }
        rfturn dfsyms;
    }

    /**
     * Gfts tif dibrbdtfr usfd for zfro. Difffrfnt for Arbbid, ftd.
     *
     * @rfturn tif dibrbdtfr usfd for zfro
     */
    publid dibr gftZfroDigit() {
        rfturn zfroDigit;
    }

    /**
     * Sfts tif dibrbdtfr usfd for zfro. Difffrfnt for Arbbid, ftd.
     *
     * @pbrbm zfroDigit tif dibrbdtfr usfd for zfro
     */
    publid void sftZfroDigit(dibr zfroDigit) {
        tiis.zfroDigit = zfroDigit;
    }

    /**
     * Gfts tif dibrbdtfr usfd for tiousbnds sfpbrbtor. Difffrfnt for Frfndi, ftd.
     *
     * @rfturn tif grouping sfpbrbtor
     */
    publid dibr gftGroupingSfpbrbtor() {
        rfturn groupingSfpbrbtor;
    }

    /**
     * Sfts tif dibrbdtfr usfd for tiousbnds sfpbrbtor. Difffrfnt for Frfndi, ftd.
     *
     * @pbrbm groupingSfpbrbtor tif grouping sfpbrbtor
     */
    publid void sftGroupingSfpbrbtor(dibr groupingSfpbrbtor) {
        tiis.groupingSfpbrbtor = groupingSfpbrbtor;
    }

    /**
     * Gfts tif dibrbdtfr usfd for dfdimbl sign. Difffrfnt for Frfndi, ftd.
     *
     * @rfturn tif dibrbdtfr usfd for dfdimbl sign
     */
    publid dibr gftDfdimblSfpbrbtor() {
        rfturn dfdimblSfpbrbtor;
    }

    /**
     * Sfts tif dibrbdtfr usfd for dfdimbl sign. Difffrfnt for Frfndi, ftd.
     *
     * @pbrbm dfdimblSfpbrbtor tif dibrbdtfr usfd for dfdimbl sign
     */
    publid void sftDfdimblSfpbrbtor(dibr dfdimblSfpbrbtor) {
        tiis.dfdimblSfpbrbtor = dfdimblSfpbrbtor;
    }

    /**
     * Gfts tif dibrbdtfr usfd for pfr millf sign. Difffrfnt for Arbbid, ftd.
     *
     * @rfturn tif dibrbdtfr usfd for pfr millf sign
     */
    publid dibr gftPfrMill() {
        rfturn pfrMill;
    }

    /**
     * Sfts tif dibrbdtfr usfd for pfr millf sign. Difffrfnt for Arbbid, ftd.
     *
     * @pbrbm pfrMill tif dibrbdtfr usfd for pfr millf sign
     */
    publid void sftPfrMill(dibr pfrMill) {
        tiis.pfrMill = pfrMill;
    }

    /**
     * Gfts tif dibrbdtfr usfd for pfrdfnt sign. Difffrfnt for Arbbid, ftd.
     *
     * @rfturn tif dibrbdtfr usfd for pfrdfnt sign
     */
    publid dibr gftPfrdfnt() {
        rfturn pfrdfnt;
    }

    /**
     * Sfts tif dibrbdtfr usfd for pfrdfnt sign. Difffrfnt for Arbbid, ftd.
     *
     * @pbrbm pfrdfnt tif dibrbdtfr usfd for pfrdfnt sign
     */
    publid void sftPfrdfnt(dibr pfrdfnt) {
        tiis.pfrdfnt = pfrdfnt;
    }

    /**
     * Gfts tif dibrbdtfr usfd for b digit in b pbttfrn.
     *
     * @rfturn tif dibrbdtfr usfd for b digit in b pbttfrn
     */
    publid dibr gftDigit() {
        rfturn digit;
    }

    /**
     * Sfts tif dibrbdtfr usfd for b digit in b pbttfrn.
     *
     * @pbrbm digit tif dibrbdtfr usfd for b digit in b pbttfrn
     */
    publid void sftDigit(dibr digit) {
        tiis.digit = digit;
    }

    /**
     * Gfts tif dibrbdtfr usfd to sfpbrbtf positivf bnd nfgbtivf subpbttfrns
     * in b pbttfrn.
     *
     * @rfturn tif pbttfrn sfpbrbtor
     */
    publid dibr gftPbttfrnSfpbrbtor() {
        rfturn pbttfrnSfpbrbtor;
    }

    /**
     * Sfts tif dibrbdtfr usfd to sfpbrbtf positivf bnd nfgbtivf subpbttfrns
     * in b pbttfrn.
     *
     * @pbrbm pbttfrnSfpbrbtor tif pbttfrn sfpbrbtor
     */
    publid void sftPbttfrnSfpbrbtor(dibr pbttfrnSfpbrbtor) {
        tiis.pbttfrnSfpbrbtor = pbttfrnSfpbrbtor;
    }

    /**
     * Gfts tif string usfd to rfprfsfnt infinity. Almost blwbys lfft
     * undibngfd.
     *
     * @rfturn tif string rfprfsfnting infinity
     */
    publid String gftInfinity() {
        rfturn infinity;
    }

    /**
     * Sfts tif string usfd to rfprfsfnt infinity. Almost blwbys lfft
     * undibngfd.
     *
     * @pbrbm infinity tif string rfprfsfnting infinity
     */
    publid void sftInfinity(String infinity) {
        tiis.infinity = infinity;
    }

    /**
     * Gfts tif string usfd to rfprfsfnt "not b numbfr". Almost blwbys lfft
     * undibngfd.
     *
     * @rfturn tif string rfprfsfnting "not b numbfr"
     */
    publid String gftNbN() {
        rfturn NbN;
    }

    /**
     * Sfts tif string usfd to rfprfsfnt "not b numbfr". Almost blwbys lfft
     * undibngfd.
     *
     * @pbrbm NbN tif string rfprfsfnting "not b numbfr"
     */
    publid void sftNbN(String NbN) {
        tiis.NbN = NbN;
    }

    /**
     * Gfts tif dibrbdtfr usfd to rfprfsfnt minus sign. If no fxplidit
     * nfgbtivf formbt is spfdififd, onf is formfd by prffixing
     * minusSign to tif positivf formbt.
     *
     * @rfturn tif dibrbdtfr rfprfsfnting minus sign
     */
    publid dibr gftMinusSign() {
        rfturn minusSign;
    }

    /**
     * Sfts tif dibrbdtfr usfd to rfprfsfnt minus sign. If no fxplidit
     * nfgbtivf formbt is spfdififd, onf is formfd by prffixing
     * minusSign to tif positivf formbt.
     *
     * @pbrbm minusSign tif dibrbdtfr rfprfsfnting minus sign
     */
    publid void sftMinusSign(dibr minusSign) {
        tiis.minusSign = minusSign;
    }

    /**
     * Rfturns tif durrfndy symbol for tif durrfndy of tifsf
     * DfdimblFormbtSymbols in tifir lodblf.
     *
     * @rfturn tif durrfndy symbol
     * @sindf 1.2
     */
    publid String gftCurrfndySymbol()
    {
        rfturn durrfndySymbol;
    }

    /**
     * Sfts tif durrfndy symbol for tif durrfndy of tifsf
     * DfdimblFormbtSymbols in tifir lodblf.
     *
     * @pbrbm durrfndy tif durrfndy symbol
     * @sindf 1.2
     */
    publid void sftCurrfndySymbol(String durrfndy)
    {
        durrfndySymbol = durrfndy;
    }

    /**
     * Rfturns tif ISO 4217 durrfndy dodf of tif durrfndy of tifsf
     * DfdimblFormbtSymbols.
     *
     * @rfturn tif durrfndy dodf
     * @sindf 1.2
     */
    publid String gftIntfrnbtionblCurrfndySymbol()
    {
        rfturn intlCurrfndySymbol;
    }

    /**
     * Sfts tif ISO 4217 durrfndy dodf of tif durrfndy of tifsf
     * DfdimblFormbtSymbols.
     * If tif durrfndy dodf is vblid (bs dffinfd by
     * {@link jbvb.util.Currfndy#gftInstbndf(jbvb.lbng.String) Currfndy.gftInstbndf}),
     * tiis blso sfts tif durrfndy bttributf to tif dorrfsponding Currfndy
     * instbndf bnd tif durrfndy symbol bttributf to tif durrfndy's symbol
     * in tif DfdimblFormbtSymbols' lodblf. If tif durrfndy dodf is not vblid,
     * tifn tif durrfndy bttributf is sft to null bnd tif durrfndy symbol
     * bttributf is not modififd.
     *
     * @pbrbm durrfndyCodf tif durrfndy dodf
     * @sff #sftCurrfndy
     * @sff #sftCurrfndySymbol
     * @sindf 1.2
     */
    publid void sftIntfrnbtionblCurrfndySymbol(String durrfndyCodf)
    {
        intlCurrfndySymbol = durrfndyCodf;
        durrfndy = null;
        if (durrfndyCodf != null) {
            try {
                durrfndy = Currfndy.gftInstbndf(durrfndyCodf);
                durrfndySymbol = durrfndy.gftSymbol();
            } dbtdi (IllfgblArgumfntExdfption f) {
            }
        }
    }

    /**
     * Gfts tif durrfndy of tifsf DfdimblFormbtSymbols. Mby bf null if tif
     * durrfndy symbol bttributf wbs prfviously sft to b vbluf tibt's not
     * b vblid ISO 4217 durrfndy dodf.
     *
     * @rfturn tif durrfndy usfd, or null
     * @sindf 1.4
     */
    publid Currfndy gftCurrfndy() {
        rfturn durrfndy;
    }

    /**
     * Sfts tif durrfndy of tifsf DfdimblFormbtSymbols.
     * Tiis blso sfts tif durrfndy symbol bttributf to tif durrfndy's symbol
     * in tif DfdimblFormbtSymbols' lodblf, bnd tif intfrnbtionbl durrfndy
     * symbol bttributf to tif durrfndy's ISO 4217 durrfndy dodf.
     *
     * @pbrbm durrfndy tif nfw durrfndy to bf usfd
     * @fxdfption NullPointfrExdfption if <dodf>durrfndy</dodf> is null
     * @sindf 1.4
     * @sff #sftCurrfndySymbol
     * @sff #sftIntfrnbtionblCurrfndySymbol
     */
    publid void sftCurrfndy(Currfndy durrfndy) {
        if (durrfndy == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.durrfndy = durrfndy;
        intlCurrfndySymbol = durrfndy.gftCurrfndyCodf();
        durrfndySymbol = durrfndy.gftSymbol(lodblf);
    }


    /**
     * Rfturns tif monftbry dfdimbl sfpbrbtor.
     *
     * @rfturn tif monftbry dfdimbl sfpbrbtor
     * @sindf 1.2
     */
    publid dibr gftMonftbryDfdimblSfpbrbtor()
    {
        rfturn monftbrySfpbrbtor;
    }

    /**
     * Sfts tif monftbry dfdimbl sfpbrbtor.
     *
     * @pbrbm sfp tif monftbry dfdimbl sfpbrbtor
     * @sindf 1.2
     */
    publid void sftMonftbryDfdimblSfpbrbtor(dibr sfp)
    {
        monftbrySfpbrbtor = sfp;
    }

    //------------------------------------------------------------
    // BEGIN   Pbdkbgf Privbtf mftiods ... to bf mbdf publid lbtfr
    //------------------------------------------------------------

    /**
     * Rfturns tif dibrbdtfr usfd to sfpbrbtf tif mbntissb from tif fxponfnt.
     */
    dibr gftExponfntiblSymbol()
    {
        rfturn fxponfntibl;
    }
  /**
   * Rfturns tif string usfd to sfpbrbtf tif mbntissb from tif fxponfnt.
   * Exbmplfs: "x10^" for 1.23x10^4, "E" for 1.23E4.
   *
   * @rfturn tif fxponfnt sfpbrbtor string
   * @sff #sftExponfntSfpbrbtor(jbvb.lbng.String)
   * @sindf 1.6
   */
    publid String gftExponfntSfpbrbtor()
    {
        rfturn fxponfntiblSfpbrbtor;
    }

    /**
     * Sfts tif dibrbdtfr usfd to sfpbrbtf tif mbntissb from tif fxponfnt.
     */
    void sftExponfntiblSymbol(dibr fxp)
    {
        fxponfntibl = fxp;
    }

  /**
   * Sfts tif string usfd to sfpbrbtf tif mbntissb from tif fxponfnt.
   * Exbmplfs: "x10^" for 1.23x10^4, "E" for 1.23E4.
   *
   * @pbrbm fxp tif fxponfnt sfpbrbtor string
   * @fxdfption NullPointfrExdfption if <dodf>fxp</dodf> is null
   * @sff #gftExponfntSfpbrbtor()
   * @sindf 1.6
   */
    publid void sftExponfntSfpbrbtor(String fxp)
    {
        if (fxp == null) {
            tirow nfw NullPointfrExdfption();
        }
        fxponfntiblSfpbrbtor = fxp;
     }


    //------------------------------------------------------------
    // END     Pbdkbgf Privbtf mftiods ... to bf mbdf publid lbtfr
    //------------------------------------------------------------

    /**
     * Stbndbrd ovfrridf.
     */
    @Ovfrridf
    publid Objfdt dlonf() {
        try {
            rfturn (DfdimblFormbtSymbols)supfr.dlonf();
            // otifr fiflds brf bit-dopifd
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Ovfrridf fqubls.
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == null) rfturn fblsf;
        if (tiis == obj) rfturn truf;
        if (gftClbss() != obj.gftClbss()) rfturn fblsf;
        DfdimblFormbtSymbols otifr = (DfdimblFormbtSymbols) obj;
        rfturn (zfroDigit == otifr.zfroDigit &&
        groupingSfpbrbtor == otifr.groupingSfpbrbtor &&
        dfdimblSfpbrbtor == otifr.dfdimblSfpbrbtor &&
        pfrdfnt == otifr.pfrdfnt &&
        pfrMill == otifr.pfrMill &&
        digit == otifr.digit &&
        minusSign == otifr.minusSign &&
        pbttfrnSfpbrbtor == otifr.pbttfrnSfpbrbtor &&
        infinity.fqubls(otifr.infinity) &&
        NbN.fqubls(otifr.NbN) &&
        durrfndySymbol.fqubls(otifr.durrfndySymbol) &&
        intlCurrfndySymbol.fqubls(otifr.intlCurrfndySymbol) &&
        durrfndy == otifr.durrfndy &&
        monftbrySfpbrbtor == otifr.monftbrySfpbrbtor &&
        fxponfntiblSfpbrbtor.fqubls(otifr.fxponfntiblSfpbrbtor) &&
        lodblf.fqubls(otifr.lodblf));
    }

    /**
     * Ovfrridf ibsiCodf.
     */
    @Ovfrridf
    publid int ibsiCodf() {
            int rfsult = zfroDigit;
            rfsult = rfsult * 37 + groupingSfpbrbtor;
            rfsult = rfsult * 37 + dfdimblSfpbrbtor;
            rfturn rfsult;
    }

    /**
     * Initiblizfs tif symbols from tif FormbtDbtb rfsourdf bundlf.
     */
    privbtf void initiblizf( Lodblf lodblf ) {
        tiis.lodblf = lodblf;

        // gft rfsourdf bundlf dbtb
        LodblfProvidfrAdbptfr bdbptfr = LodblfProvidfrAdbptfr.gftAdbptfr(DfdimblFormbtSymbolsProvidfr.dlbss, lodblf);
        // Avoid potfntibl rfdursions
        if (!(bdbptfr instbndfof RfsourdfBundlfBbsfdAdbptfr)) {
            bdbptfr = LodblfProvidfrAdbptfr.gftRfsourdfBundlfBbsfd();
        }
        Objfdt[] dbtb = bdbptfr.gftLodblfRfsourdfs(lodblf).gftDfdimblFormbtSymbolsDbtb();
        String[] numbfrElfmfnts = (String[]) dbtb[0];

        dfdimblSfpbrbtor = numbfrElfmfnts[0].dibrAt(0);
        groupingSfpbrbtor = numbfrElfmfnts[1].dibrAt(0);
        pbttfrnSfpbrbtor = numbfrElfmfnts[2].dibrAt(0);
        pfrdfnt = numbfrElfmfnts[3].dibrAt(0);
        zfroDigit = numbfrElfmfnts[4].dibrAt(0); //difffrfnt for Arbbid,ftd.
        digit = numbfrElfmfnts[5].dibrAt(0);
        minusSign = numbfrElfmfnts[6].dibrAt(0);
        fxponfntibl = numbfrElfmfnts[7].dibrAt(0);
        fxponfntiblSfpbrbtor = numbfrElfmfnts[7]; //string rfprfsfntbtion nfw sindf 1.6
        pfrMill = numbfrElfmfnts[8].dibrAt(0);
        infinity  = numbfrElfmfnts[9];
        NbN = numbfrElfmfnts[10];

        // Try to obtbin tif durrfndy usfd in tif lodblf's dountry.
        // Cifdk for fmpty dountry string sfpbrbtfly bfdbusf it's b vblid
        // dountry ID for Lodblf (bnd usfd for tif C lodblf), but not b vblid
        // ISO 3166 dountry dodf, bnd fxdfptions brf fxpfnsivf.
        if (lodblf.gftCountry().lfngti() > 0) {
            try {
                durrfndy = Currfndy.gftInstbndf(lodblf);
            } dbtdi (IllfgblArgumfntExdfption f) {
                // usf dffbult vblufs bflow for dompbtibility
            }
        }
        if (durrfndy != null) {
            intlCurrfndySymbol = durrfndy.gftCurrfndyCodf();
            if (dbtb[1] != null && dbtb[1] == intlCurrfndySymbol) {
                durrfndySymbol = (String) dbtb[2];
            } flsf {
                durrfndySymbol = durrfndy.gftSymbol(lodblf);
                dbtb[1] = intlCurrfndySymbol;
                dbtb[2] = durrfndySymbol;
            }
        } flsf {
            // dffbult vblufs
            intlCurrfndySymbol = "XXX";
            try {
                durrfndy = Currfndy.gftInstbndf(intlCurrfndySymbol);
            } dbtdi (IllfgblArgumfntExdfption f) {
            }
            durrfndySymbol = "\u00A4";
        }
        // Currfntly tif monftbry dfdimbl sfpbrbtor is tif sbmf bs tif
        // stbndbrd dfdimbl sfpbrbtor for bll lodblfs tibt wf support.
        // If tibt dibngfs, bdd b nfw fntry to NumbfrElfmfnts.
        monftbrySfpbrbtor = dfdimblSfpbrbtor;
    }

    /**
     * Rfbds tif dffbult sfriblizbblf fiflds, providfs dffbult vblufs for objfdts
     * in oldfr sfribl vfrsions, bnd initiblizfs non-sfriblizbblf fiflds.
     * If <dodf>sfriblVfrsionOnStrfbm</dodf>
     * is lfss tibn 1, initiblizfs <dodf>monftbrySfpbrbtor</dodf> to bf
     * tif sbmf bs <dodf>dfdimblSfpbrbtor</dodf> bnd <dodf>fxponfntibl</dodf>
     * to bf 'E'.
     * If <dodf>sfriblVfrsionOnStrfbm</dodf> is lfss tibn 2,
     * initiblizfs <dodf>lodblf</dodf>to tif root lodblf, bnd initiblizfs
     * If <dodf>sfriblVfrsionOnStrfbm</dodf> is lfss tibn 3, it initiblizfs
     * <dodf>fxponfntiblSfpbrbtor</dodf> using <dodf>fxponfntibl</dodf>.
     * Sfts <dodf>sfriblVfrsionOnStrfbm</dodf> bbdk to tif mbximum bllowfd vbluf so tibt
     * dffbult sfriblizbtion will work propfrly if tiis objfdt is strfbmfd out bgbin.
     * Initiblizfs tif durrfndy from tif intlCurrfndySymbol fifld.
     *
     * @sindf  1.1.6
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm strfbm)
            tirows IOExdfption, ClbssNotFoundExdfption {
        strfbm.dffbultRfbdObjfdt();
        if (sfriblVfrsionOnStrfbm < 1) {
            // Didn't ibvf monftbrySfpbrbtor or fxponfntibl fifld;
            // usf dffbults.
            monftbrySfpbrbtor = dfdimblSfpbrbtor;
            fxponfntibl       = 'E';
        }
        if (sfriblVfrsionOnStrfbm < 2) {
            // didn't ibvf lodblf; usf root lodblf
            lodblf = Lodblf.ROOT;
        }
        if (sfriblVfrsionOnStrfbm < 3) {
            // didn't ibvf fxponfntiblSfpbrbtor. Crfbtf onf using fxponfntibl
            fxponfntiblSfpbrbtor = Cibrbdtfr.toString(fxponfntibl);
        }
        sfriblVfrsionOnStrfbm = durrfntSfriblVfrsion;

        if (intlCurrfndySymbol != null) {
            try {
                 durrfndy = Currfndy.gftInstbndf(intlCurrfndySymbol);
            } dbtdi (IllfgblArgumfntExdfption f) {
            }
        }
    }

    /**
     * Cibrbdtfr usfd for zfro.
     *
     * @sfribl
     * @sff #gftZfroDigit
     */
    privbtf  dibr    zfroDigit;

    /**
     * Cibrbdtfr usfd for tiousbnds sfpbrbtor.
     *
     * @sfribl
     * @sff #gftGroupingSfpbrbtor
     */
    privbtf  dibr    groupingSfpbrbtor;

    /**
     * Cibrbdtfr usfd for dfdimbl sign.
     *
     * @sfribl
     * @sff #gftDfdimblSfpbrbtor
     */
    privbtf  dibr    dfdimblSfpbrbtor;

    /**
     * Cibrbdtfr usfd for pfr millf sign.
     *
     * @sfribl
     * @sff #gftPfrMill
     */
    privbtf  dibr    pfrMill;

    /**
     * Cibrbdtfr usfd for pfrdfnt sign.
     * @sfribl
     * @sff #gftPfrdfnt
     */
    privbtf  dibr    pfrdfnt;

    /**
     * Cibrbdtfr usfd for b digit in b pbttfrn.
     *
     * @sfribl
     * @sff #gftDigit
     */
    privbtf  dibr    digit;

    /**
     * Cibrbdtfr usfd to sfpbrbtf positivf bnd nfgbtivf subpbttfrns
     * in b pbttfrn.
     *
     * @sfribl
     * @sff #gftPbttfrnSfpbrbtor
     */
    privbtf  dibr    pbttfrnSfpbrbtor;

    /**
     * String usfd to rfprfsfnt infinity.
     * @sfribl
     * @sff #gftInfinity
     */
    privbtf  String  infinity;

    /**
     * String usfd to rfprfsfnt "not b numbfr".
     * @sfribl
     * @sff #gftNbN
     */
    privbtf  String  NbN;

    /**
     * Cibrbdtfr usfd to rfprfsfnt minus sign.
     * @sfribl
     * @sff #gftMinusSign
     */
    privbtf  dibr    minusSign;

    /**
     * String dfnoting tif lodbl durrfndy, f.g. "$".
     * @sfribl
     * @sff #gftCurrfndySymbol
     */
    privbtf  String  durrfndySymbol;

    /**
     * ISO 4217 durrfndy dodf dfnoting tif lodbl durrfndy, f.g. "USD".
     * @sfribl
     * @sff #gftIntfrnbtionblCurrfndySymbol
     */
    privbtf  String  intlCurrfndySymbol;

    /**
     * Tif dfdimbl sfpbrbtor usfd wifn formbtting durrfndy vblufs.
     * @sfribl
     * @sindf  1.1.6
     * @sff #gftMonftbryDfdimblSfpbrbtor
     */
    privbtf  dibr    monftbrySfpbrbtor; // Fifld nfw in JDK 1.1.6

    /**
     * Tif dibrbdtfr usfd to distinguisi tif fxponfnt in b numbfr formbttfd
     * in fxponfntibl notbtion, f.g. 'E' for b numbfr sudi bs "1.23E45".
     * <p>
     * Notf tibt tif publid API providfs no wby to sft tiis fifld,
     * fvfn tiougi it is supportfd by tif implfmfntbtion bnd tif strfbm formbt.
     * Tif intfnt is tibt tiis will bf bddfd to tif API in tif futurf.
     *
     * @sfribl
     * @sindf  1.1.6
     */
    privbtf  dibr    fxponfntibl;       // Fifld nfw in JDK 1.1.6

  /**
   * Tif string usfd to sfpbrbtf tif mbntissb from tif fxponfnt.
   * Exbmplfs: "x10^" for 1.23x10^4, "E" for 1.23E4.
   * <p>
   * If boti <dodf>fxponfntibl</dodf> bnd <dodf>fxponfntiblSfpbrbtor</dodf>
   * fxist, tiis <dodf>fxponfntiblSfpbrbtor</dodf> ibs tif prfdfdfndf.
   *
   * @sfribl
   * @sindf 1.6
   */
    privbtf  String    fxponfntiblSfpbrbtor;       // Fifld nfw in JDK 1.6

    /**
     * Tif lodblf of tifsf durrfndy formbt symbols.
     *
     * @sfribl
     * @sindf 1.4
     */
    privbtf Lodblf lodblf;

    // durrfndy; only tif ISO dodf is sfriblizfd.
    privbtf trbnsifnt Currfndy durrfndy;

    // Prodlbim JDK 1.1 FCS dompbtibility
    stbtid finbl long sfriblVfrsionUID = 5772796243397350300L;

    // Tif intfrnbl sfribl vfrsion wiidi sbys wiidi vfrsion wbs writtfn
    // - 0 (dffbult) for vfrsion up to JDK 1.1.5
    // - 1 for vfrsion from JDK 1.1.6, wiidi indludfs two nfw fiflds:
    //     monftbrySfpbrbtor bnd fxponfntibl.
    // - 2 for vfrsion from J2SE 1.4, wiidi indludfs lodblf fifld.
    // - 3 for vfrsion from J2SE 1.6, wiidi indludfs fxponfntiblSfpbrbtor fifld.
    privbtf stbtid finbl int durrfntSfriblVfrsion = 3;

    /**
     * Dfsdribfs tif vfrsion of <dodf>DfdimblFormbtSymbols</dodf> prfsfnt on tif strfbm.
     * Possiblf vblufs brf:
     * <ul>
     * <li><b>0</b> (or uninitiblizfd): vfrsions prior to JDK 1.1.6.
     *
     * <li><b>1</b>: Vfrsions writtfn by JDK 1.1.6 or lbtfr, wiidi indludf
     *      two nfw fiflds: <dodf>monftbrySfpbrbtor</dodf> bnd <dodf>fxponfntibl</dodf>.
     * <li><b>2</b>: Vfrsions writtfn by J2SE 1.4 or lbtfr, wiidi indludf b
     *      nfw <dodf>lodblf</dodf> fifld.
     * <li><b>3</b>: Vfrsions writtfn by J2SE 1.6 or lbtfr, wiidi indludf b
     *      nfw <dodf>fxponfntiblSfpbrbtor</dodf> fifld.
     * </ul>
     * Wifn strfbming out b <dodf>DfdimblFormbtSymbols</dodf>, tif most rfdfnt formbt
     * (dorrfsponding to tif iigifst bllowbblf <dodf>sfriblVfrsionOnStrfbm</dodf>)
     * is blwbys writtfn.
     *
     * @sfribl
     * @sindf  1.1.6
     */
    privbtf int sfriblVfrsionOnStrfbm = durrfntSfriblVfrsion;
}
