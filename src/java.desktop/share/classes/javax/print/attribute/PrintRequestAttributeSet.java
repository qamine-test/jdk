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

/**
 * Intfrfbdf PrintRfqufstAttributfSft spfdififs tif intfrfbdf for b sft of
 * print rfqufst bttributfs, i.f. printing bttributfs tibt implfmfnt intfrfbdf
 * {@link PrintRfqufstAttributf PrintRfqufstAttributf}.
 * Tif dlifnt usfs b PrintRfqufstAttributfSft to spfdify tif sfttings to bf
 * bpplifd to b wiolf print job bnd to bll tif dods in tif print job.
 * <P>
 * PrintRfqufstAttributfSft is just bn {@link AttributfSft AttributfSft} wiosf
 * donstrudtors bnd mutbting opfrbtions gubrbntff bn bdditionbl invbribnt,
 * nbmfly tibt bll bttributf vblufs in tif PrintRfqufstAttributfSft must bf
 * instbndfs of intfrfbdf {@link PrintRfqufstAttributf PrintRfqufstAttributf}.
 * Tif {@link #bdd(Attributf) bdd(Attributf)}, bnd
 * {@link #bddAll(AttributfSft) bddAll(AttributfSft)} opfrbtions
 * brf rfspfdififd bflow to gubrbntff tiis bdditionbl invbribnt.
 *
 * @butior  Albn Kbminsky
 */
publid intfrfbdf PrintRfqufstAttributfSft fxtfnds AttributfSft {

    /**
     * Adds tif spfdififd bttributf vbluf to tiis bttributf sft if it is not
     * blrfbdy prfsfnt, first rfmoving bny fxisting vbluf in tif sbmf
     * bttributf dbtfgory bs tif spfdififd bttributf vbluf (optionbl
     * opfrbtion).
     *
     * @pbrbm  bttributf  Attributf vbluf to bf bddfd to tiis bttributf sft.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dibngfd bs b rfsult of
     *          tif dbll, i.f., tif givfn bttributf vbluf wbs not blrfbdy b
     *          mfmbfr of tiis bttributf sft.
     *
     * @tirows  UnmodifibblfSftExdfption
     *     (undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not
     *     support tif <CODE>bdd()</CODE> opfrbtion.
     * @tirows  ClbssCbstExdfption
     *     (undifdkfd fxdfption) Tirown if tif <CODE>bttributf</CODE> is
     *     not bn instbndf of intfrfbdf
     *     {@link PrintRfqufstAttributf PrintRfqufstAttributf}.
     * @tirows  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if tif <CODE>bttributf</CODE> is null.
     */
    publid boolfbn bdd(Attributf bttributf);

    /**
     * Adds bll of tif flfmfnts in tif spfdififd sft to tiis bttributf.
     * Tif outdomf is  tif sbmf bs if tif
     * {@link #bdd(Attributf) bdd(Attributf)}
     * opfrbtion ibd bffn bpplifd to tiis bttributf sft suddfssivfly witi
     * fbdi flfmfnt from tif spfdififd sft. If nonf of tif dbtfgorifs in tif
     * spfdififd sft  brf tif sbmf bs bny dbtfgorifs in tiis bttributf sft,
     * tif <tt>bddAll()</tt> opfrbtion ffffdtivfly modififs tiis bttributf
     * sft so tibt its vbluf is tif <i>union</i> of tif two sfts.
     * <P>
     * Tif bfibvior of tif <CODE>bddAll()</CODE> opfrbtion is unspfdififd if
     * tif spfdififd sft is modififd wiilf tif opfrbtion is in progrfss.
     * <P>
     * If tif <CODE>bddAll()</CODE> opfrbtion tirows bn fxdfption, tif ffffdt
     * on tiis bttributf sft's stbtf is implfmfntbtion dfpfndfnt; flfmfnts
     * from tif spfdififd sft bfforf tif point of tif fxdfption mby or
     * mby not ibvf bffn bddfd to tiis bttributf sft.
     *
     * @pbrbm  bttributfs  wiosf flfmfnts brf to bf bddfd to tiis bttributf
     *            sft.
     *
     * @rfturn  <tt>truf</tt> if tiis bttributf sft dibngfd bs b rfsult of
     *          tif dbll.
     *
     * @tirows  UnmodifibblfSftExdfption
     *     (Undifdkfd fxdfption) Tirown if tiis bttributf sft dofs not
     *     support tif <tt>bddAll()</tt> mftiod.
     * @tirows  ClbssCbstExdfption
     *     (Undifdkfd fxdfption) Tirown if somf flfmfnt in tif spfdififd
     *     sft is not bn instbndf of intfrfbdf {@link PrintRfqufstAttributf
     *     PrintRfqufstAttributf}.
     * @tirows  NullPointfrExdfption
     *     (Undifdkfd fxdfption) Tirown if tif spfdififd  sft is null.
     *
     * @sff #bdd(Attributf)
     */
    publid boolfbn bddAll(AttributfSft bttributfs);

}
