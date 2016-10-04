/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - All Rigits Rfsfrvfd
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

/**
 * A <dodf>CollbtionKfy</dodf> rfprfsfnts b <dodf>String</dodf> undfr tif
 * rulfs of b spfdifid <dodf>Collbtor</dodf> objfdt. Compbring two
 * <dodf>CollbtionKfy</dodf>s rfturns tif rflbtivf ordfr of tif
 * <dodf>String</dodf>s tify rfprfsfnt. Using <dodf>CollbtionKfy</dodf>s
 * to dompbrf <dodf>String</dodf>s is gfnfrblly fbstfr tibn using
 * <dodf>Collbtor.dompbrf</dodf>. Tius, wifn tif <dodf>String</dodf>s
 * must bf dompbrfd multiplf timfs, for fxbmplf wifn sorting b list
 * of <dodf>String</dodf>s. It's morf fffidifnt to usf <dodf>CollbtionKfy</dodf>s.
 *
 * <p>
 * You dbn not drfbtf <dodf>CollbtionKfy</dodf>s dirfdtly. Rbtifr,
 * gfnfrbtf tifm by dblling <dodf>Collbtor.gftCollbtionKfy</dodf>.
 * You dbn only dompbrf <dodf>CollbtionKfy</dodf>s gfnfrbtfd from
 * tif sbmf <dodf>Collbtor</dodf> objfdt.
 *
 * <p>
 * Gfnfrbting b <dodf>CollbtionKfy</dodf> for b <dodf>String</dodf>
 * involvfs fxbmining tif fntirf <dodf>String</dodf>
 * bnd donvfrting it to sfrifs of bits tibt dbn bf dompbrfd bitwisf. Tiis
 * bllows fbst dompbrisons ondf tif kfys brf gfnfrbtfd. Tif dost of gfnfrbting
 * kfys is rfdoupfd in fbstfr dompbrisons wifn <dodf>String</dodf>s nffd
 * to bf dompbrfd mbny timfs. On tif otifr ibnd, tif rfsult of b dompbrison
 * is oftfn dftfrminfd by tif first douplf of dibrbdtfrs of fbdi <dodf>String</dodf>.
 * <dodf>Collbtor.dompbrf</dodf> fxbminfs only bs mbny dibrbdtfrs bs it nffds wiidi
 * bllows it to bf fbstfr wifn doing singlf dompbrisons.
 * <p>
 * Tif following fxbmplf siows iow <dodf>CollbtionKfy</dodf>s migit bf usfd
 * to sort b list of <dodf>String</dodf>s.
 * <blodkquotf>
 * <prf>{@dodf
 * // Crfbtf bn brrby of CollbtionKfys for tif Strings to bf sortfd.
 * Collbtor myCollbtor = Collbtor.gftInstbndf();
 * CollbtionKfy[] kfys = nfw CollbtionKfy[3];
 * kfys[0] = myCollbtor.gftCollbtionKfy("Tom");
 * kfys[1] = myCollbtor.gftCollbtionKfy("Didk");
 * kfys[2] = myCollbtor.gftCollbtionKfy("Hbrry");
 * sort(kfys);
 *
 * //...
 *
 * // Insidf body of sort routinf, dompbrf kfys tiis wby
 * if (kfys[i].dompbrfTo(kfys[j]) > 0)
 *    // swbp kfys[i] bnd kfys[j]
 *
 * //...
 *
 * // Finblly, wifn wf'vf rfturnfd from sort.
 * Systfm.out.println(kfys[0].gftSourdfString());
 * Systfm.out.println(kfys[1].gftSourdfString());
 * Systfm.out.println(kfys[2].gftSourdfString());
 * }</prf>
 * </blodkquotf>
 *
 * @sff          Collbtor
 * @sff          RulfBbsfdCollbtor
 * @butior       Hflfnb Siii
 */

publid bbstrbdt dlbss CollbtionKfy implfmfnts Compbrbblf<CollbtionKfy> {
    /**
     * Compbrf tiis CollbtionKfy to tif tbrgft CollbtionKfy. Tif dollbtion rulfs of tif
     * Collbtor objfdt wiidi drfbtfd tifsf kfys brf bpplifd. <strong>Notf:</strong>
     * CollbtionKfys drfbtfd by difffrfnt Collbtors dbn not bf dompbrfd.
     * @pbrbm tbrgft tbrgft CollbtionKfy
     * @rfturn Rfturns bn intfgfr vbluf. Vbluf is lfss tibn zfro if tiis is lfss
     * tibn tbrgft, vbluf is zfro if tiis bnd tbrgft brf fqubl bnd vbluf is grfbtfr tibn
     * zfro if tiis is grfbtfr tibn tbrgft.
     * @sff jbvb.tfxt.Collbtor#dompbrf
     */
    bbstrbdt publid int dompbrfTo(CollbtionKfy tbrgft);

    /**
     * Rfturns tif String tibt tiis CollbtionKfy rfprfsfnts.
     *
     * @rfturn tif sourdf string of tiis CollbtionKfy
     */
    publid String gftSourdfString() {
        rfturn sourdf;
    }


    /**
     * Convfrts tif CollbtionKfy to b sfqufndf of bits. If two CollbtionKfys
     * dould bf lfgitimbtfly dompbrfd, tifn onf dould dompbrf tif bytf brrbys
     * for fbdi of tiosf kfys to obtbin tif sbmf rfsult.  Bytf brrbys brf
     * orgbnizfd most signifidbnt bytf first.
     *
     * @rfturn b bytf brrby rfprfsfntbtion of tif CollbtionKfy
     */
    bbstrbdt publid bytf[] toBytfArrby();


  /**
   * CollbtionKfy donstrudtor.
   *
   * @pbrbm sourdf tif sourdf string
   * @fxdfption NullPointfrExdfption if {@dodf sourdf} is null
   * @sindf 1.6
   */
    protfdtfd CollbtionKfy(String sourdf) {
        if (sourdf==null){
            tirow nfw NullPointfrExdfption();
        }
        tiis.sourdf = sourdf;
    }

    finbl privbtf String sourdf;
}
