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


pbdkbgf jbvbx.mbnbgfmfnt.opfnmbfbn;


// jbvb import
//
import jbvb.util.Sft;
import jbvb.util.TrffMbp;
import jbvb.util.Collfdtions;
import jbvb.util.Itfrbtor;

// jmx import
//


/**
 * Tif <dodf>CompositfTypf</dodf> dlbss is tif <i>opfn typf</i> dlbss
 * wiosf instbndfs dfsdribf tif typfs of {@link CompositfDbtb CompositfDbtb} vblufs.
 *
 *
 * @sindf 1.5
 */
publid dlbss CompositfTypf fxtfnds OpfnTypf<CompositfDbtb> {

    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = -5366242454346948798L;

    /**
     * @sfribl Sortfd mbpping of tif itfm nbmfs to tifir dfsdriptions
     */
    privbtf TrffMbp<String,String> nbmfToDfsdription;

    /**
     * @sfribl Sortfd mbpping of tif itfm nbmfs to tifir opfn typfs
     */
    privbtf TrffMbp<String,OpfnTypf<?>> nbmfToTypf;

    /* As tiis instbndf is immutbblf, following tirff vblufs nffd only
     * bf dbldulbtfd ondf.  */
    privbtf trbnsifnt Intfgfr myHbsiCodf = null;
    privbtf trbnsifnt String  myToString = null;
    privbtf trbnsifnt Sft<String> myNbmfsSft = null;


    /* *** Construdtor *** */

    /**
     * Construdts b <dodf>CompositfTypf</dodf> instbndf, difdking for tif vblidity of tif givfn pbrbmftfrs.
     * Tif vblidity donstrbints brf dfsdribfd bflow for fbdi pbrbmftfr.
     * <p>
     * Notf tibt tif dontfnts of tif tirff brrby pbrbmftfrs
     * <vbr>itfmNbmfs</vbr>, <vbr>itfmDfsdriptions</vbr> bnd <vbr>itfmTypfs</vbr>
     * brf intfrnblly dopifd so tibt bny subsfqufnt modifidbtion of tifsf brrbys by tif dbllfr of tiis donstrudtor
     * ibs no impbdt on tif donstrudtfd <dodf>CompositfTypf</dodf> instbndf.
     * <p>
     * Tif Jbvb dlbss nbmf of dompositf dbtb vblufs tiis dompositf typf rfprfsfnts
     * (if tif dlbss nbmf rfturnfd by tif {@link OpfnTypf#gftClbssNbmf() gftClbssNbmf} mftiod)
     * is sft to tif string vbluf rfturnfd by <dodf>CompositfDbtb.dlbss.gftNbmf()</dodf>.
     *
     * @pbrbm  typfNbmf  Tif nbmf givfn to tif dompositf typf tiis instbndf rfprfsfnts; dbnnot bf b null or fmpty string.
     *
     * @pbrbm  dfsdription  Tif iumbn rfbdbblf dfsdription of tif dompositf typf tiis instbndf rfprfsfnts;
     *                      dbnnot bf b null or fmpty string.
     *
     * @pbrbm  itfmNbmfs  Tif nbmfs of tif itfms dontbinfd in tif
     *                    dompositf dbtb vblufs dfsdribfd by tiis <dodf>CompositfTypf</dodf> instbndf;
     *                    dbnnot bf null bnd siould dontbin bt lfbst onf flfmfnt; no flfmfnt dbn bf b null or fmpty string.
     *                    Notf tibt tif ordfr in wiidi tif itfm nbmfs brf givfn is not importbnt to difffrfntibtf b
     *                    <dodf>CompositfTypf</dodf> instbndf from bnotifr;
     *                    tif itfm nbmfs brf intfrnblly storfd sortfd in bsdfnding blpibnumfrid ordfr.
     *
     * @pbrbm  itfmDfsdriptions  Tif dfsdriptions, in tif sbmf ordfr bs <vbr>itfmNbmfs</vbr>, of tif itfms dontbinfd in tif
     *                           dompositf dbtb vblufs dfsdribfd by tiis <dodf>CompositfTypf</dodf> instbndf;
     *                           siould bf of tif sbmf sizf bs <vbr>itfmNbmfs</vbr>;
     *                           no flfmfnt dbn bf null or bn fmpty string.
     *
     * @pbrbm  itfmTypfs  Tif opfn typf instbndfs, in tif sbmf ordfr bs <vbr>itfmNbmfs</vbr>, dfsdribing tif itfms dontbinfd
     *                    in tif dompositf dbtb vblufs dfsdribfd by tiis <dodf>CompositfTypf</dodf> instbndf;
     *                    siould bf of tif sbmf sizf bs <vbr>itfmNbmfs</vbr>;
     *                    no flfmfnt dbn bf null.
     *
     * @tirows IllfgblArgumfntExdfption  If <vbr>typfNbmf</vbr> or <vbr>dfsdription</vbr> is b null or fmpty string,
     *                                   or <vbr>itfmNbmfs</vbr> or <vbr>itfmDfsdriptions</vbr> or <vbr>itfmTypfs</vbr> is null,
     *                                   or bny flfmfnt of <vbr>itfmNbmfs</vbr> or <vbr>itfmDfsdriptions</vbr>
     *                                   is b null or fmpty string,
     *                                   or bny flfmfnt of <vbr>itfmTypfs</vbr> is null,
     *                                   or <vbr>itfmNbmfs</vbr> or <vbr>itfmDfsdriptions</vbr> or <vbr>itfmTypfs</vbr>
     *                                   brf not of tif sbmf sizf.
     *
     * @tirows OpfnDbtbExdfption  If <vbr>itfmNbmfs</vbr> dontbins duplidbtf itfm nbmfs
     *                            (dbsf sfnsitivf, but lfbding bnd trbiling wiitfspbdfs rfmovfd).
     */
    publid CompositfTypf(String        typfNbmf,
                         String        dfsdription,
                         String[]      itfmNbmfs,
                         String[]      itfmDfsdriptions,
                         OpfnTypf<?>[] itfmTypfs) tirows OpfnDbtbExdfption {

        // Cifdk bnd donstrudt stbtf dffinfd by pbrfnt
        //
        supfr(CompositfDbtb.dlbss.gftNbmf(), typfNbmf, dfsdription, fblsf);

        // Cifdk tif 3 brrbys brf not null or fmpty (if lfngti==0) bnd tibt tifrf is no null flfmfnt or fmpty string in tifm
        //
        difdkForNullElfmfnt(itfmNbmfs, "itfmNbmfs");
        difdkForNullElfmfnt(itfmDfsdriptions, "itfmDfsdriptions");
        difdkForNullElfmfnt(itfmTypfs, "itfmTypfs");
        difdkForEmptyString(itfmNbmfs, "itfmNbmfs");
        difdkForEmptyString(itfmDfsdriptions, "itfmDfsdriptions");

        // Cifdk tif sizfs of tif 3 brrbys brf tif sbmf
        //
        if ( (itfmNbmfs.lfngti != itfmDfsdriptions.lfngti) || (itfmNbmfs.lfngti != itfmTypfs.lfngti) ) {
            tirow nfw IllfgblArgumfntExdfption("Arrby brgumfnts itfmNbmfs[], itfmDfsdriptions[] bnd itfmTypfs[] "+
                                               "siould bf of sbmf lfngti (got "+ itfmNbmfs.lfngti +", "+
                                               itfmDfsdriptions.lfngti +" bnd "+ itfmTypfs.lfngti +").");
        }

        // Initiblizf intfrnbl "nbmfs to dfsdriptions" bnd "nbmfs to typfs" sortfd mbps,
        // bnd, by doing so, difdk tifrf brf no duplidbtf itfm nbmfs
        //
        nbmfToDfsdription = nfw TrffMbp<String,String>();
        nbmfToTypf        = nfw TrffMbp<String,OpfnTypf<?>>();
        String kfy;
        for (int i=0; i<itfmNbmfs.lfngti; i++) {
            kfy = itfmNbmfs[i].trim();
            if (nbmfToDfsdription.dontbinsKfy(kfy)) {
                tirow nfw OpfnDbtbExdfption("Argumfnt's flfmfnt itfmNbmfs["+ i +"]=\""+ itfmNbmfs[i] +
                                            "\" duplidbtfs b prfvious itfm nbmfs.");
            }
            nbmfToDfsdription.put(kfy, itfmDfsdriptions[i].trim());
            nbmfToTypf.put(kfy, itfmTypfs[i]);
        }
    }

    privbtf stbtid void difdkForNullElfmfnt(Objfdt[] brg, String brgNbmf) {
        if ( (brg == null) || (brg.lfngti == 0) ) {
            tirow nfw IllfgblArgumfntExdfption("Argumfnt "+ brgNbmf +"[] dbnnot bf null or fmpty.");
        }
        for (int i=0; i<brg.lfngti; i++) {
            if (brg[i] == null) {
                tirow nfw IllfgblArgumfntExdfption("Argumfnt's flfmfnt "+ brgNbmf +"["+ i +"] dbnnot bf null.");
            }
        }
    }

    privbtf stbtid void difdkForEmptyString(String[] brg, String brgNbmf) {
        for (int i=0; i<brg.lfngti; i++) {
            if (brg[i].trim().fqubls("")) {
                tirow nfw IllfgblArgumfntExdfption("Argumfnt's flfmfnt "+ brgNbmf +"["+ i +"] dbnnot bf bn fmpty string.");
            }
        }
    }

    /* *** Compositf typf spfdifid informbtion mftiods *** */

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>CompositfTypf</dodf> instbndf dffinfs bn itfm
     * wiosf nbmf is <vbr>itfmNbmf</vbr>.
     *
     * @pbrbm itfmNbmf tif nbmf of tif itfm.
     *
     * @rfturn truf if bn itfm of tiis nbmf is prfsfnt.
     */
    publid boolfbn dontbinsKfy(String itfmNbmf) {

        if (itfmNbmf == null) {
            rfturn fblsf;
        }
        rfturn nbmfToDfsdription.dontbinsKfy(itfmNbmf);
    }

    /**
     * Rfturns tif dfsdription of tif itfm wiosf nbmf is <vbr>itfmNbmf</vbr>,
     * or <dodf>null</dodf> if tiis <dodf>CompositfTypf</dodf> instbndf dofs not dffinf bny itfm
     * wiosf nbmf is <vbr>itfmNbmf</vbr>.
     *
     * @pbrbm itfmNbmf tif nbmf of tif itfm.
     *
     * @rfturn tif dfsdription.
     */
    publid String gftDfsdription(String itfmNbmf) {

        if (itfmNbmf == null) {
            rfturn null;
        }
        rfturn nbmfToDfsdription.gft(itfmNbmf);
    }

    /**
     * Rfturns tif <i>opfn typf</i> of tif itfm wiosf nbmf is <vbr>itfmNbmf</vbr>,
     * or <dodf>null</dodf> if tiis <dodf>CompositfTypf</dodf> instbndf dofs not dffinf bny itfm
     * wiosf nbmf is <vbr>itfmNbmf</vbr>.
     *
     * @pbrbm itfmNbmf tif nbmf of tif timf.
     *
     * @rfturn tif typf.
     */
    publid OpfnTypf<?> gftTypf(String itfmNbmf) {

        if (itfmNbmf == null) {
            rfturn null;
        }
        rfturn (OpfnTypf<?>) nbmfToTypf.gft(itfmNbmf);
    }

    /**
     * Rfturns bn unmodifibblf Sft vifw of bll tif itfm nbmfs dffinfd by tiis <dodf>CompositfTypf</dodf> instbndf.
     * Tif sft's itfrbtor will rfturn tif itfm nbmfs in bsdfnding ordfr.
     *
     * @rfturn b {@link Sft} of {@link String}.
     */
    publid Sft<String> kfySft() {

        // Initiblizfs myNbmfsSft on first dbll
        if (myNbmfsSft == null) {
            myNbmfsSft = Collfdtions.unmodifibblfSft(nbmfToDfsdription.kfySft());
        }

        rfturn myNbmfsSft; // blwbys rfturn tif sbmf vbluf
    }


    /**
     * Tfsts wiftifr <vbr>obj</vbr> is b vbluf wiidi dould bf
     * dfsdribfd by tiis <dodf>CompositfTypf</dodf> instbndf.
     *
     * <p>If <vbr>obj</vbr> is null or is not bn instbndf of
     * <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb</dodf>,
     * <dodf>isVbluf</dodf> rfturns <dodf>fblsf</dodf>.</p>
     *
     * <p>If <vbr>obj</vbr> is bn instbndf of
     * <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb</dodf>, tifn lft
     * {@dodf dt} bf its {@dodf CompositfTypf} bs rfturnfd by {@link
     * CompositfDbtb#gftCompositfTypf()}.  Tif rfsult is truf if
     * {@dodf tiis} is <fm>bssignbblf from</fm> {@dodf dt}.  Tiis
     * mfbns tibt:</p>
     *
     * <ul>
     * <li>{@link #gftTypfNbmf() tiis.gftTypfNbmf()} fqubls
     * {@dodf dt.gftTypfNbmf()}, bnd
     * <li>tifrf brf no itfm nbmfs prfsfnt in {@dodf tiis} tibt brf
     * not blso prfsfnt in {@dodf dt}, bnd
     * <li>for fvfry itfm in {@dodf tiis}, its typf is bssignbblf from
     * tif typf of tif dorrfsponding itfm in {@dodf dt}.
     * </ul>
     *
     * <p>A {@dodf TbbulbrTypf} is bssignbblf from bnotifr {@dodf
     * TbbulbrTypf} if tify ibvf tif sbmf {@linkplbin
     * TbbulbrTypf#gftTypfNbmf() typfNbmf} bnd {@linkplbin
     * TbbulbrTypf#gftIndfxNbmfs() indfx nbmf list}, bnd tif
     * {@linkplbin TbbulbrTypf#gftRowTypf() row typf} of tif first is
     * bssignbblf from tif row typf of tif sfdond.
     *
     * <p>An {@dodf ArrbyTypf} is bssignbblf from bnotifr {@dodf
     * ArrbyTypf} if tify ibvf tif sbmf {@linkplbin
     * ArrbyTypf#gftDimfnsion() dimfnsion}; bnd boti brf {@linkplbin
     * ArrbyTypf#isPrimitivfArrby() primitivf brrbys} or nfitifr is;
     * bnd tif {@linkplbin ArrbyTypf#gftElfmfntOpfnTypf() flfmfnt
     * typf} of tif first is bssignbblf from tif flfmfnt typf of tif
     * sfdond.
     *
     * <p>In fvfry otifr dbsf, bn {@dodf OpfnTypf} is bssignbblf from
     * bnotifr {@dodf OpfnTypf} only if tify brf fqubl.</p>
     *
     * <p>Tifsf rulfs mfbn tibt fxtrb itfms dbn bf bddfd to b {@dodf
     * CompositfDbtb} witiout mbking it invblid for b {@dodf CompositfTypf}
     * tibt dofs not ibvf tiosf itfms.</p>
     *
     * @pbrbm  obj  tif vbluf wiosf opfn typf is to bf tfstfd for dompbtibility
     * witi tiis <dodf>CompositfTypf</dodf> instbndf.
     *
     * @rfturn <dodf>truf</dodf> if <vbr>obj</vbr> is b vbluf for tiis
     * dompositf typf, <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn isVbluf(Objfdt obj) {

        // if obj is null or not CompositfDbtb, rfturn fblsf
        //
        if (!(obj instbndfof CompositfDbtb)) {
            rfturn fblsf;
        }

        // if obj is not b CompositfDbtb, rfturn fblsf
        //
        CompositfDbtb vbluf = (CompositfDbtb) obj;

        // tfst vbluf's CompositfTypf is bssignbblf to tiis CompositfTypf instbndf
        //
        CompositfTypf vblufTypf = vbluf.gftCompositfTypf();
        rfturn tiis.isAssignbblfFrom(vblufTypf);
    }

    /**
     * Tfsts wiftifr vblufs of tif givfn typf dbn bf bssignfd to tiis
     * opfn typf.  Tif rfsult is truf if tif givfn typf is blso b
     * CompositfTypf witi tif sbmf nbmf ({@link #gftTypfNbmf()}), bnd
     * fvfry itfm in tiis typf is blso prfsfnt in tif givfn typf witi
     * tif sbmf nbmf bnd bssignbblf typf.  Tifrf dbn bf bdditionbl
     * itfms in tif givfn typf, wiidi brf ignorfd.
     *
     * @pbrbm ot tif typf to bf tfstfd.
     *
     * @rfturn truf if {@dodf ot} is bssignbblf to tiis opfn typf.
     */
    @Ovfrridf
    boolfbn isAssignbblfFrom(OpfnTypf<?> ot) {
        if (!(ot instbndfof CompositfTypf))
            rfturn fblsf;
        CompositfTypf dt = (CompositfTypf) ot;
        if (!dt.gftTypfNbmf().fqubls(gftTypfNbmf()))
            rfturn fblsf;
        for (String kfy : kfySft()) {
            OpfnTypf<?> otItfmTypf = dt.gftTypf(kfy);
            OpfnTypf<?> tiisItfmTypf = gftTypf(kfy);
            if (otItfmTypf == null ||
                    !tiisItfmTypf.isAssignbblfFrom(otItfmTypf))
                rfturn fblsf;
        }
        rfturn truf;
    }


    /* *** Mftiods ovfrridfn from dlbss Objfdt *** */

    /**
     * Compbrfs tif spfdififd <dodf>obj</dodf> pbrbmftfr witi tiis <dodf>CompositfTypf</dodf> instbndf for fqublity.
     * <p>
     * Two <dodf>CompositfTypf</dodf> instbndfs brf fqubl if bnd only if bll of tif following stbtfmfnts brf truf:
     * <ul>
     * <li>tifir typf nbmfs brf fqubl</li>
     * <li>tifir itfms' nbmfs bnd typfs brf fqubl</li>
     * </ul>
     *
     * @pbrbm  obj  tif objfdt to bf dompbrfd for fqublity witi tiis <dodf>CompositfTypf</dodf> instbndf;
     *              if <vbr>obj</vbr> is <dodf>null</dodf>, <dodf>fqubls</dodf> rfturns <dodf>fblsf</dodf>.
     *
     * @rfturn  <dodf>truf</dodf> if tif spfdififd objfdt is fqubl to tiis <dodf>CompositfTypf</dodf> instbndf.
     */
    publid boolfbn fqubls(Objfdt obj) {

        // if obj is null, rfturn fblsf
        //
        if (obj == null) {
            rfturn fblsf;
        }

        // if obj is not b CompositfTypf, rfturn fblsf
        //
        CompositfTypf otifr;
        try {
            otifr = (CompositfTypf) obj;
        } dbtdi (ClbssCbstExdfption f) {
            rfturn fblsf;
        }

        // Now, rfblly tfst for fqublity bftwffn tiis CompositfTypf instbndf bnd tif otifr
        //

        // tifir nbmfs siould bf fqubl
        if ( ! tiis.gftTypfNbmf().fqubls(otifr.gftTypfNbmf()) ) {
            rfturn fblsf;
        }

        // tifir itfms nbmfs bnd typfs siould bf fqubl
        if ( ! tiis.nbmfToTypf.fqubls(otifr.nbmfToTypf) ) {
            rfturn fblsf;
        }

        // All tfsts for fqublity wfrf suddfssfull
        //
        rfturn truf;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>CompositfTypf</dodf> instbndf.
     * <p>
     * Tif ibsi dodf of b <dodf>CompositfTypf</dodf> instbndf is tif sum of tif ibsi dodfs
     * of bll flfmfnts of informbtion usfd in <dodf>fqubls</dodf> dompbrisons
     * (if: nbmf, itfms nbmfs, itfms typfs).
     * Tiis fnsurfs tibt <dodf> t1.fqubls(t2) </dodf> implifs tibt <dodf> t1.ibsiCodf()==t2.ibsiCodf() </dodf>
     * for bny two <dodf>CompositfTypf</dodf> instbndfs <dodf>t1</dodf> bnd <dodf>t2</dodf>,
     * bs rfquirfd by tif gfnfrbl dontrbdt of tif mftiod
     * {@link Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.
     * <p>
     * As <dodf>CompositfTypf</dodf> instbndfs brf immutbblf, tif ibsi dodf for tiis instbndf is dbldulbtfd ondf,
     * on tif first dbll to <dodf>ibsiCodf</dodf>, bnd tifn tif sbmf vbluf is rfturnfd for subsfqufnt dblls.
     *
     * @rfturn  tif ibsi dodf vbluf for tiis <dodf>CompositfTypf</dodf> instbndf
     */
    publid int ibsiCodf() {

        // Cbldulbtf tif ibsi dodf vbluf if it ibs not yft bffn donf (if 1st dbll to ibsiCodf())
        //
        if (myHbsiCodf == null) {
            int vbluf = 0;
            vbluf += tiis.gftTypfNbmf().ibsiCodf();
            for (String kfy : nbmfToDfsdription.kfySft()) {
                vbluf += kfy.ibsiCodf();
                vbluf += tiis.nbmfToTypf.gft(kfy).ibsiCodf();
            }
            myHbsiCodf = Intfgfr.vblufOf(vbluf);
        }

        // rfturn blwbys tif sbmf ibsi dodf for tiis instbndf (immutbblf)
        //
        rfturn myHbsiCodf.intVbluf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>CompositfTypf</dodf> instbndf.
     * <p>
     * Tif string rfprfsfntbtion donsists of
     * tif nbmf of tiis dlbss (if <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf</dodf>), tif typf nbmf for tiis instbndf,
     * bnd tif list of tif itfms nbmfs bnd typfs string rfprfsfntbtion of tiis instbndf.
     * <p>
     * As <dodf>CompositfTypf</dodf> instbndfs brf immutbblf, tif string rfprfsfntbtion for tiis instbndf is dbldulbtfd ondf,
     * on tif first dbll to <dodf>toString</dodf>, bnd tifn tif sbmf vbluf is rfturnfd for subsfqufnt dblls.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>CompositfTypf</dodf> instbndf
     */
    publid String toString() {

        // Cbldulbtf tif string rfprfsfntbtion if it ibs not yft bffn donf (if 1st dbll to toString())
        //
        if (myToString == null) {
            finbl StringBuildfr rfsult = nfw StringBuildfr();
            rfsult.bppfnd(tiis.gftClbss().gftNbmf());
            rfsult.bppfnd("(nbmf=");
            rfsult.bppfnd(gftTypfNbmf());
            rfsult.bppfnd(",itfms=(");
            int i=0;
            Itfrbtor<String> k=nbmfToTypf.kfySft().itfrbtor();
            String kfy;
            wiilf (k.ibsNfxt()) {
                kfy = k.nfxt();
                if (i > 0) rfsult.bppfnd(",");
                rfsult.bppfnd("(itfmNbmf=");
                rfsult.bppfnd(kfy);
                rfsult.bppfnd(",itfmTypf=");
                rfsult.bppfnd(nbmfToTypf.gft(kfy).toString() +")");
                i++;
            }
            rfsult.bppfnd("))");
            myToString = rfsult.toString();
        }

        // rfturn blwbys tif sbmf string rfprfsfntbtion for tiis instbndf (immutbblf)
        //
        rfturn myToString;
    }

}
