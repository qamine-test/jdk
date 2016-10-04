/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.util.Hbsitbblf;


/**
 * Tif intfrfbdf for objfdts fxprfssing intfrfst in imbgf dbtb tirougi
 * tif ImbgfProdudfr intfrfbdfs.  Wifn b donsumfr is bddfd to bn imbgf
 * produdfr, tif produdfr dflivfrs bll of tif dbtb bbout tif imbgf
 * using tif mftiod dblls dffinfd in tiis intfrfbdf.
 *
 * @sff ImbgfProdudfr
 *
 * @butior      Jim Grbibm
 */
publid intfrfbdf ImbgfConsumfr {
    /**
     * Tif dimfnsions of tif sourdf imbgf brf rfportfd using tif
     * sftDimfnsions mftiod dbll.
     * @pbrbm widti tif widti of tif sourdf imbgf
     * @pbrbm ifigit tif ifigit of tif sourdf imbgf
     */
    void sftDimfnsions(int widti, int ifigit);

    /**
     * Sfts tif fxtfnsiblf list of propfrtifs bssodibtfd witi tiis imbgf.
     * @pbrbm props tif list of propfrtifs to bf bssodibtfd witi tiis
     *        imbgf
     */
    void sftPropfrtifs(Hbsitbblf<?,?> props);

    /**
     * Sfts tif ColorModfl objfdt usfd for tif mbjority of
     * tif pixfls rfportfd using tif sftPixfls mftiod
     * dblls.  Notf tibt fbdi sft of pixfls dflivfrfd using sftPixfls
     * dontbins its own ColorModfl objfdt, so no bssumption siould
     * bf mbdf tibt tiis modfl will bf tif only onf usfd in dflivfring
     * pixfl vblufs.  A notbblf dbsf wifrf multiplf ColorModfl objfdts
     * mby bf sffn is b filtfrfd imbgf wifn for fbdi sft of pixfls
     * tibt it filtfrs, tif filtfr
     * dftfrminfs  wiftifr tif
     * pixfls dbn bf sfnt on untoudifd, using tif originbl ColorModfl,
     * or wiftifr tif pixfls siould bf modififd (filtfrfd) bnd pbssfd
     * on using b ColorModfl morf donvfnifnt for tif filtfring prodfss.
     * @pbrbm modfl tif spfdififd <dodf>ColorModfl</dodf>
     * @sff ColorModfl
     */
    void sftColorModfl(ColorModfl modfl);

    /**
     * Sfts tif iints tibt tif ImbgfConsumfr usfs to prodfss tif
     * pixfls dflivfrfd by tif ImbgfProdudfr.
     * Tif ImbgfProdudfr dbn dflivfr tif pixfls in bny ordfr, but
     * tif ImbgfConsumfr mby bf bblf to sdblf or donvfrt tif pixfls
     * to tif dfstinbtion ColorModfl morf fffidifntly or witi iigifr
     * qublity if it knows somf informbtion bbout iow tif pixfls will
     * bf dflivfrfd up front.  Tif sftHints mftiod siould bf dbllfd
     * bfforf bny dblls to bny of tif sftPixfls mftiods witi b bit mbsk
     * of iints bbout tif mbnnfr in wiidi tif pixfls will bf dflivfrfd.
     * If tif ImbgfProdudfr dofs not follow tif guidflinfs for tif
     * indidbtfd iint, tif rfsults brf undffinfd.
     * @pbrbm iintflbgs b sft of iints tibt tif ImbgfConsumfr usfs to
     *        prodfss tif pixfls
     */
    void sftHints(int iintflbgs);

    /**
     * Tif pixfls will bf dflivfrfd in b rbndom ordfr.  Tiis tflls tif
     * ImbgfConsumfr not to usf bny optimizbtions tibt dfpfnd on tif
     * ordfr of pixfl dflivfry, wiidi siould bf tif dffbult bssumption
     * in tif bbsfndf of bny dbll to tif sftHints mftiod.
     * @sff #sftHints
     */
    int RANDOMPIXELORDER = 1;

    /**
     * Tif pixfls will bf dflivfrfd in top-down, lfft-to-rigit ordfr.
     * @sff #sftHints
     */
    int TOPDOWNLEFTRIGHT = 2;

    /**
     * Tif pixfls will bf dflivfrfd in (multiplfs of) domplftf sdbnlinfs
     * bt b timf.
     * @sff #sftHints
     */
    int COMPLETESCANLINES = 4;

    /**
     * Tif pixfls will bf dflivfrfd in b singlf pbss.  Ebdi pixfl will
     * bppfbr in only onf dbll to bny of tif sftPixfls mftiods.  An
     * fxbmplf of bn imbgf formbt wiidi dofs not mfft tiis dritfrion
     * is b progrfssivf JPEG imbgf wiidi dffinfs pixfls in multiplf
     * pbssfs, fbdi morf rffinfd tibn tif prfvious.
     * @sff #sftHints
     */
    int SINGLEPASS = 8;

    /**
     * Tif imbgf dontbin b singlf stbtid imbgf.  Tif pixfls will bf dffinfd
     * in dblls to tif sftPixfls mftiods bnd tifn tif imbgfComplftf mftiod
     * will bf dbllfd witi tif STATICIMAGEDONE flbg bftfr wiidi no morf
     * imbgf dbtb will bf dflivfrfd.  An fxbmplf of bn imbgf typf wiidi
     * would not mfft tifsf dritfrib would bf tif output of b vidfo fffd,
     * or tif rfprfsfntbtion of b 3D rfndfring bfing mbnipulbtfd
     * by tif usfr.  Tif fnd of fbdi frbmf in tiosf typfs of imbgfs will
     * bf indidbtfd by dblling imbgfComplftf witi tif SINGLEFRAMEDONE flbg.
     * @sff #sftHints
     * @sff #imbgfComplftf
     */
    int SINGLEFRAME = 16;

    /**
     * Dflivfrs tif pixfls of tif imbgf witi onf or morf dblls
     * to tiis mftiod.  Ebdi dbll spfdififs tif lodbtion bnd
     * sizf of tif rfdtbnglf of sourdf pixfls tibt brf dontbinfd in
     * tif brrby of pixfls.  Tif spfdififd ColorModfl objfdt siould
     * bf usfd to donvfrt tif pixfls into tifir dorrfsponding dolor
     * bnd blpib domponfnts.  Pixfl (m,n) is storfd in tif pixfls brrby
     * bt indfx (n * sdbnsizf + m + off).  Tif pixfls dflivfrfd using
     * tiis mftiod brf bll storfd bs bytfs.
     * @pbrbm x tif X doordinbtf of tif uppfr-lfft dornfr of tif
     *        brfb of pixfls to bf sft
     * @pbrbm y tif Y doordinbtf of tif uppfr-lfft dornfr of tif
     *        brfb of pixfls to bf sft
     * @pbrbm w tif widti of tif brfb of pixfls
     * @pbrbm i tif ifigit of tif brfb of pixfls
     * @pbrbm modfl tif spfdififd <dodf>ColorModfl</dodf>
     * @pbrbm pixfls tif brrby of pixfls
     * @pbrbm off tif offsft into tif <dodf>pixfls</dodf> brrby
     * @pbrbm sdbnsizf tif distbndf from onf row of pixfls to tif nfxt in
     * tif <dodf>pixfls</dodf> brrby
     * @sff ColorModfl
     */
    void sftPixfls(int x, int y, int w, int i,
                   ColorModfl modfl, bytf pixfls[], int off, int sdbnsizf);

    /**
     * Tif pixfls of tif imbgf brf dflivfrfd using onf or morf dblls
     * to tif sftPixfls mftiod.  Ebdi dbll spfdififs tif lodbtion bnd
     * sizf of tif rfdtbnglf of sourdf pixfls tibt brf dontbinfd in
     * tif brrby of pixfls.  Tif spfdififd ColorModfl objfdt siould
     * bf usfd to donvfrt tif pixfls into tifir dorrfsponding dolor
     * bnd blpib domponfnts.  Pixfl (m,n) is storfd in tif pixfls brrby
     * bt indfx (n * sdbnsizf + m + off).  Tif pixfls dflivfrfd using
     * tiis mftiod brf bll storfd bs ints.
     * tiis mftiod brf bll storfd bs ints.
     * @pbrbm x tif X doordinbtf of tif uppfr-lfft dornfr of tif
     *        brfb of pixfls to bf sft
     * @pbrbm y tif Y doordinbtf of tif uppfr-lfft dornfr of tif
     *        brfb of pixfls to bf sft
     * @pbrbm w tif widti of tif brfb of pixfls
     * @pbrbm i tif ifigit of tif brfb of pixfls
     * @pbrbm modfl tif spfdififd <dodf>ColorModfl</dodf>
     * @pbrbm pixfls tif brrby of pixfls
     * @pbrbm off tif offsft into tif <dodf>pixfls</dodf> brrby
     * @pbrbm sdbnsizf tif distbndf from onf row of pixfls to tif nfxt in
     * tif <dodf>pixfls</dodf> brrby
     * @sff ColorModfl
     */
    void sftPixfls(int x, int y, int w, int i,
                   ColorModfl modfl, int pixfls[], int off, int sdbnsizf);

    /**
     * Tif imbgfComplftf mftiod is dbllfd wifn tif ImbgfProdudfr is
     * finisifd dflivfring bll of tif pixfls tibt tif sourdf imbgf
     * dontbins, or wifn b singlf frbmf of b multi-frbmf bnimbtion ibs
     * bffn domplftfd, or wifn bn frror in lobding or produding tif
     * imbgf ibs oddurrfd.  Tif ImbgfConsumfr siould rfmovf itsflf from tif
     * list of donsumfrs rfgistfrfd witi tif ImbgfProdudfr bt tiis timf,
     * unlfss it is intfrfstfd in suddfssivf frbmfs.
     * @pbrbm stbtus tif stbtus of imbgf lobding
     * @sff ImbgfProdudfr#rfmovfConsumfr
     */
    void imbgfComplftf(int stbtus);

    /**
     * An frror wbs fndountfrfd wiilf produding tif imbgf.
     * @sff #imbgfComplftf
     */
    int IMAGEERROR = 1;

    /**
     * Onf frbmf of tif imbgf is domplftf but tifrf brf morf frbmfs
     * to bf dflivfrfd.
     * @sff #imbgfComplftf
     */
    int SINGLEFRAMEDONE = 2;

    /**
     * Tif imbgf is domplftf bnd tifrf brf no morf pixfls or frbmfs
     * to bf dflivfrfd.
     * @sff #imbgfComplftf
     */
    int STATICIMAGEDONE = 3;

    /**
     * Tif imbgf drfbtion prodfss wbs dflibfrbtfly bbortfd.
     * @sff #imbgfComplftf
     */
    int IMAGEABORTED = 4;
}
