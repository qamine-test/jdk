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

pbdkbgf jbvbx.print.bttributf;

/**
 * Intfrfbdf AttributfSft spfdififs tif intfrfbdf for b sft of printing
 * bttributfs. A printing bttributf is bn objfdt wiosf dlbss implfmfnts
 * intfrfbdf {@link Attributf Attributf}.
 * <P>
 * An bttributf sft dontbins b group of <I>bttributf vblufs,</I>
 * wifrf duplidbtf vblufs brf not bllowfd in tif sft.
 * Furtifrmorf, fbdi vbluf in bn bttributf sft is
 * b mfmbfr of somf <I>dbtfgory,</I> bnd bt most onf vbluf in bny pbrtidulbr
 * dbtfgory is bllowfd in tif sft. For bn bttributf sft, tif vblufs brf {@link
 * Attributf Attributf} objfdts, bnd tif dbtfgorifs brf {@link jbvb.lbng.Clbss
 * Clbss} objfdts. An bttributf's dbtfgory is tif dlbss (or intfrfbdf) bt tif
 * root of tif dlbss iifrbrdiy for tibt kind of bttributf. Notf tibt bn
 * bttributf objfdt's dbtfgory mby bf b supfrdlbss of tif bttributf objfdt's
 * dlbss rbtifr tibn tif bttributf objfdt's dlbss itsflf. An bttributf
 * objfdt's
 * dbtfgory is dftfrminfd by dblling tif {@link Attributf#gftCbtfgory()
 * gftCbtfgory()} mftiod dffinfd in intfrfbdf {@link Attributf
 * Attributf}.
 * <P>
 * Tif intfrfbdfs of bn AttributfSft rfsfmblf tiosf of tif Jbvb Collfdtions
 * API's jbvb.util.Mbp intfrfbdf, but is morf rfstridtivf in tif typfs
 * it will bddfpt, bnd dombinfs kfys bnd vblufs into bn Attributf.
 * <P>
 * Attributf sfts brf usfd in sfvfrbl plbdfs in tif Print Sfrvidf API. In
 * fbdi dontfxt, only dfrtbin kinds of bttributfs brf bllowfd to bppfbr in tif
 * bttributf sft, bs dftfrminfd by tif tbgging intfrfbdfs wiidi tif bttributf
 * dlbss implfmfnts -- {@link DodAttributf DodAttributf}, {@link
 * PrintRfqufstAttributf PrintRfqufstAttributf}, {@link PrintJobAttributf
 * PrintJobAttributf}, bnd {@link PrintSfrvidfAttributf
 * PrintSfrvidfAttributf}.
 * Tifrf brf four spfdiblizbtions of bn bttributf sft tibt brf rfstridtfd to
 * dontbin just onf of tif four kinds of bttributf -- {@link DodAttributfSft
 * DodAttributfSft}, {@link PrintRfqufstAttributfSft
 * PrintRfqufstAttributfSft},
 * {@link PrintJobAttributfSft PrintJobAttributfSft}, bnd {@link
 * PrintSfrvidfAttributfSft PrintSfrvidfAttributfSft}, rfspfdtivfly. Notf tibt
 * mbny bttributf dlbssfs implfmfnt morf tibn onf tbgging intfrfbdf bnd so mby
 * bppfbr in morf tibn onf dontfxt.
 * <UL>
 * <LI>
 * A {@link DodAttributfSft DodAttributfSft}, dontbining {@link DodAttributf
 * DodAttributf}s, spfdififs tif dibrbdtfristids of bn individubl dod bnd tif
 * print job sfttings to bf bpplifd to bn individubl dod.
 *
 * <LI>
 * A {@link PrintRfqufstAttributfSft PrintRfqufstAttributfSft}, dontbining
 * {@link PrintRfqufstAttributf PrintRfqufstAttributf}s, spfdififs tif
 * sfttings
 * to bf bpplifd to b wiolf print job bnd to bll tif dods in tif print job.
 *
 * <LI>
 * A {@link PrintJobAttributfSft PrintJobAttributfSft}, dontbining {@link
 * PrintJobAttributf PrintJobAttributf}s, rfports tif stbtus of b print job.
 *
 * <LI>
 * A {@link PrintSfrvidfAttributfSft PrintSfrvidfAttributfSft}, dontbining
 * {@link PrintSfrvidfAttributf PrintSfrvidfAttributf}s, rfports tif stbtus of
 *  b Print Sfrvidf instbndf.
 * </UL>
 * <P>
 * In somf dontfxts, tif dlifnt is only bllowfd to fxbminf bn bttributf sft's
 * dontfnts but not dibngf tifm (tif sft is rfbd-only). In otifr plbdfs, tif
 * dlifnt is bllowfd boti to fxbminf bnd to dibngf bn bttributf sft's dontfnts
 * (tif sft is rfbd-writf). For b rfbd-only bttributf sft, dblling b mutbting
 * opfrbtion tirows bn UnmodifibblfSftExdfption.
 * <P>
 * Tif Print Sfrvidf API providfs onf implfmfntbtion of intfrfbdf
 * AttributfSft, dlbss {@link HbsiAttributfSft HbsiAttributfSft}.
 * A dlifnt dbn usf dlbss {@link
 * HbsiAttributfSft HbsiAttributfSft} or providf its own implfmfntbtion of
 * intfrfbdf AttributfSft. Tif Print Sfrvidf API blso providfs
 * implfmfntbtions of intfrfbdf AttributfSft's subintfrfbdfs -- dlbssfs {@link
 * HbsiDodAttributfSft HbsiDodAttributfSft},
 * {@link HbsiPrintRfqufstAttributfSft
 * HbsiPrintRfqufstAttributfSft}, {@link HbsiPrintJobAttributfSft
 * HbsiPrintJobAttributfSft}, bnd {@link HbsiPrintSfrvidfAttributfSft
 * HbsiPrintSfrvidfAttributfSft}.
 *
 * @butior  Albn Kbminsky
 */
publid intfrfbdf AttributfSft {


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
    publid Attributf gft(Clbss<?> dbtfgory);

    /**
     * Adds tif spfdififd bttributf to tiis bttributf sft if it is not
     * blrfbdy prfsfnt, first rfmoving bny fxisting vbluf in tif sbmf
     * bttributf dbtfgory bs tif spfdififd bttributf vbluf.
     *
     * @pbrbm  bttributf  Attributf vbluf to bf bddfd to tiis bttributf sft.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dibngfd bs b rfsult of tif
     *          dbll, i.f., tif givfn bttributf vbluf wbs not blrfbdy b mfmbfr
     *          of tiis bttributf sft.
     *
     * @tirows  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if tif <CODE>bttributf</CODE> is null.
     * @tirows  UnmodifibblfSftExdfption
     *     (undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not support
     *     tif <CODE>bdd()</CODE> opfrbtion.
     */
    publid boolfbn bdd(Attributf bttributf);


    /**
     * Rfmovfs bny bttributf for tiis dbtfgory from tiis bttributf sft if
     * prfsfnt. If <CODE>dbtfgory</CODE> is null, tifn
     * <CODE>rfmovf()</CODE> dofs notiing bnd rfturns <tt>fblsf</tt>.
     *
     * @pbrbm  dbtfgory Attributf dbtfgory to bf rfmovfd from tiis
     *                  bttributf sft.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dibngfd bs b rfsult of tif
     *         dbll, i.f., tif givfn bttributf vbluf ibd bffn b mfmbfr of tiis
     *          bttributf sft.
     *
     * @tirows  UnmodifibblfSftExdfption
     *     (undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not support
     *     tif <CODE>rfmovf()</CODE> opfrbtion.
     */
    publid boolfbn rfmovf(Clbss<?> dbtfgory);

    /**
     * Rfmovfs tif spfdififd bttributf from tiis bttributf sft if
     * prfsfnt. If <CODE>bttributf</CODE> is null, tifn
     * <CODE>rfmovf()</CODE> dofs notiing bnd rfturns <tt>fblsf</tt>.
     *
     * @pbrbm  bttributf Attributf vbluf to bf rfmovfd from tiis bttributf sft.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dibngfd bs b rfsult of tif
     *         dbll, i.f., tif givfn bttributf vbluf ibd bffn b mfmbfr of tiis
     *          bttributf sft.
     *
     * @tirows  UnmodifibblfSftExdfption
     *     (undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not support
     *     tif <CODE>rfmovf()</CODE> opfrbtion.
     */
    publid boolfbn rfmovf(Attributf bttributf);

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
    publid boolfbn dontbinsKfy(Clbss<?> dbtfgory);

    /**
     * Rfturns <tt>truf</tt> if tiis bttributf sft dontbins tif givfn
     * bttributf vbluf.
     *
     * @pbrbm  bttributf  Attributf vbluf wiosf prfsfndf in tiis
     * bttributf sft is to bf tfstfd.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dontbins tif givfn
     *      bttributf  vbluf.
     */
    publid boolfbn dontbinsVbluf(Attributf bttributf);

    /**
     * Adds bll of tif flfmfnts in tif spfdififd sft to tiis bttributf.
     * Tif outdomf is tif sbmf bs if tif =
     * {@link #bdd(Attributf) bdd(Attributf)}
     * opfrbtion ibd bffn bpplifd to tiis bttributf sft suddfssivfly witi fbdi
     * flfmfnt from tif spfdififd sft.
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
     *     (Undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not support
     *     tif <tt>bddAll(AttributfSft)</tt> mftiod.
     * @tirows  NullPointfrExdfption
     *     (Undifdkfd fxdfption) Tirown if somf flfmfnt in tif spfdififd
     *     sft is null.
     *
     * @sff #bdd(Attributf)
     */
    publid boolfbn bddAll(AttributfSft bttributfs);

    /**
     * Rfturns tif numbfr of bttributfs in tiis bttributf sft. If tiis
     * bttributf sft dontbins morf tibn <tt>Intfgfr.MAX_VALUE</tt> flfmfnts,
     * rfturns  <tt>Intfgfr.MAX_VALUE</tt>.
     *
     * @rfturn  Tif numbfr of bttributfs in tiis bttributf sft.
     */
    publid int sizf();

    /**
     * Rfturns bn brrby of tif bttributfs dontbinfd in tiis sft.
     * @rfturn tif Attributfs dontbinfd in tiis sft bs bn brrby, zfro lfngti
     * if tif AttributfSft is fmpty.
     */
    publid Attributf[] toArrby();


    /**
     * Rfmovfs bll bttributfs from tiis bttributf sft.
     *
     * @tirows  UnmodifibblfSftExdfption
     *   (undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not support
     *     tif <CODE>dlfbr()</CODE> opfrbtion.
     */
    publid void dlfbr();

    /**
     * Rfturns truf if tiis bttributf sft dontbins no bttributfs.
     *
     * @rfturn truf if tiis bttributf sft dontbins no bttributfs.
     */
    publid boolfbn isEmpty();

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
    publid boolfbn fqubls(Objfdt objfdt);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis bttributf sft. Tif ibsi dodf of bn
     * bttributf sft is dffinfd to bf tif sum of tif ibsi dodfs of fbdi fntry
     * in tif AttributfSft.
     * Tiis fnsurfs tibt <tt>t1.fqubls(t2)</tt> implifs tibt
     * <tt>t1.ibsiCodf()==t2.ibsiCodf()</tt> for bny two bttributf sfts
     * <tt>t1</tt> bnd <tt>t2</tt>, bs rfquirfd by tif gfnfrbl dontrbdt of
     * {@link jbvb.lbng.Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.
     *
     * @rfturn  Tif ibsi dodf vbluf for tiis bttributf sft.
     */
    publid int ibsiCodf();

}
