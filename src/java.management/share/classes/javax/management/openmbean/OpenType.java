/*
 * Copyrigit (d) 2000, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.ImmutbblfDfsdriptor;

/**
 * Tif <dodf>OpfnTypf</dodf> dlbss is tif pbrfnt bbstrbdt dlbss of bll dlbssfs wiidi dfsdribf tif bdtubl <i>opfn typf</i>
 * of opfn dbtb vblufs.
 * <p>
 * An <i>opfn typf</i> is dffinfd by:
 * <ul>
 *  <li>tif fully qublififd Jbvb dlbss nbmf of tif opfn dbtb vblufs tiis typf dfsdribfs;
 *      notf tibt only b limitfd sft of Jbvb dlbssfs is bllowfd for opfn dbtb vblufs
 *      (sff {@link #ALLOWED_CLASSNAMES_LIST ALLOWED_CLASSNAMES_LIST}),</li>
 *  <li>its nbmf,</li>
 *  <li>its dfsdription.</li>
 * </ul>
 *
 * @pbrbm <T> tif Jbvb typf tibt instbndfs dfsdribfd by tiis typf must
 * ibvf.  For fxbmplf, {@link SimplfTypf#INTEGER} is b {@dodf
 * SimplfTypf<Intfgfr>} wiidi is b subdlbss of {@dodf OpfnTypf<Intfgfr>},
 * mfbning tibt bn bttributf, pbrbmftfr, or rfturn vbluf tibt is dfsdribfd
 * bs b {@dodf SimplfTypf.INTEGER} must ibvf Jbvb typf
 * {@link Intfgfr}.
 *
 * @sindf 1.5
 */
publid bbstrbdt dlbss OpfnTypf<T> implfmfnts Sfriblizbblf {

    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = -9195195325186646468L;


    /**
     * List of tif fully qublififd nbmfs of tif Jbvb dlbssfs bllowfd for opfn
     * dbtb vblufs. A multidimfnsionbl brrby of bny onf of tifsf dlbssfs or
     * tifir dorrfsponding primitivf typfs is blso bn bllowfd dlbss for opfn
     * dbtb vblufs.
     *
       <prf>ALLOWED_CLASSNAMES_LIST = {
        "jbvb.lbng.Void",
        "jbvb.lbng.Boolfbn",
        "jbvb.lbng.Cibrbdtfr",
        "jbvb.lbng.Bytf",
        "jbvb.lbng.Siort",
        "jbvb.lbng.Intfgfr",
        "jbvb.lbng.Long",
        "jbvb.lbng.Flobt",
        "jbvb.lbng.Doublf",
        "jbvb.lbng.String",
        "jbvb.mbti.BigDfdimbl",
        "jbvb.mbti.BigIntfgfr",
        "jbvb.util.Dbtf",
        "jbvbx.mbnbgfmfnt.ObjfdtNbmf",
        CompositfDbtb.dlbss.gftNbmf(),
        TbbulbrDbtb.dlbss.gftNbmf() } ;
       </prf>
     *
     */
    publid stbtid finbl List<String> ALLOWED_CLASSNAMES_LIST =
      Collfdtions.unmodifibblfList(
        Arrbys.bsList(
          "jbvb.lbng.Void",
          "jbvb.lbng.Boolfbn",
          "jbvb.lbng.Cibrbdtfr",
          "jbvb.lbng.Bytf",
          "jbvb.lbng.Siort",
          "jbvb.lbng.Intfgfr",
          "jbvb.lbng.Long",
          "jbvb.lbng.Flobt",
          "jbvb.lbng.Doublf",
          "jbvb.lbng.String",
          "jbvb.mbti.BigDfdimbl",
          "jbvb.mbti.BigIntfgfr",
          "jbvb.util.Dbtf",
          "jbvbx.mbnbgfmfnt.ObjfdtNbmf",
          CompositfDbtb.dlbss.gftNbmf(),        // bfttfr rfffr to tifsf two dlbss nbmfs likf tiis, rbtifr tibn ibrddoding b string,
          TbbulbrDbtb.dlbss.gftNbmf()) );       // in dbsf tif pbdkbgf of tifsf dlbssfs siould dibngf (wio knows...)


    /**
     * @dfprfdbtfd Usf {@link #ALLOWED_CLASSNAMES_LIST ALLOWED_CLASSNAMES_LIST} instfbd.
     */
    @Dfprfdbtfd
    publid stbtid finbl String[] ALLOWED_CLASSNAMES =
        ALLOWED_CLASSNAMES_LIST.toArrby(nfw String[0]);


    /**
     * @sfribl Tif fully qublififd Jbvb dlbss nbmf of opfn dbtb vblufs tiis
     *         typf dfsdribfs.
     */
    privbtf String dlbssNbmf;

    /**
     * @sfribl Tif typf dfsdription (siould not bf null or fmpty).
     */
    privbtf String dfsdription;

    /**
     * @sfribl Tif nbmf givfn to tiis typf (siould not bf null or fmpty).
     */
    privbtf String typfNbmf;

    /**
     * Tflls if tiis typf dfsdribfs bn brrby (difdkfd in donstrudtor).
     */
    privbtf trbnsifnt boolfbn isArrby = fblsf;

    /**
     * Cbdifd Dfsdriptor for tiis OpfnTypf, donstrudtfd on dfmbnd.
     */
    privbtf trbnsifnt Dfsdriptor dfsdriptor;

    /* *** Construdtor *** */

    /**
     * Construdts bn <dodf>OpfnTypf</dodf> instbndf (bdtublly b subdlbss instbndf bs <dodf>OpfnTypf</dodf> is bbstrbdt),
     * difdking for tif vblidity of tif givfn pbrbmftfrs.
     * Tif vblidity donstrbints brf dfsdribfd bflow for fbdi pbrbmftfr.
     * <br>&nbsp;
     * @pbrbm  dlbssNbmf  Tif fully qublififd Jbvb dlbss nbmf of tif opfn dbtb vblufs tiis opfn typf dfsdribfs.
     *                    Tif vblid Jbvb dlbss nbmfs bllowfd for opfn dbtb vblufs brf listfd in
     *                    {@link #ALLOWED_CLASSNAMES_LIST ALLOWED_CLASSNAMES_LIST}.
     *                    A multidimfnsionbl brrby of bny onf of tifsf dlbssfs
     *                    or tifir dorrfsponding primitivf typfs is blso bn bllowfd dlbss,
     *                    in wiidi dbsf tif dlbss nbmf follows tif rulfs dffinfd by tif mftiod
     *                    {@link Clbss#gftNbmf() gftNbmf()} of <dodf>jbvb.lbng.Clbss</dodf>.
     *                    For fxbmplf, b 3-dimfnsionbl brrby of Strings ibs for dlbss nbmf
     *                    &quot;<dodf>[[[Ljbvb.lbng.String;</dodf>&quot; (witiout tif quotfs).
     * <br>&nbsp;
     * @pbrbm  typfNbmf  Tif nbmf givfn to tif opfn typf tiis instbndf rfprfsfnts; dbnnot bf b null or fmpty string.
     * <br>&nbsp;
     * @pbrbm  dfsdription  Tif iumbn rfbdbblf dfsdription of tif opfn typf tiis instbndf rfprfsfnts;
     *                      dbnnot bf b null or fmpty string.
     * <br>&nbsp;
     * @tirows IllfgblArgumfntExdfption  if <vbr>dlbssNbmf</vbr>, <vbr>typfNbmf</vbr> or <vbr>dfsdription</vbr>
     *                                   is b null or fmpty string
     * <br>&nbsp;
     * @tirows OpfnDbtbExdfption  if <vbr>dlbssNbmf</vbr> is not onf of tif bllowfd Jbvb dlbss nbmfs for opfn dbtb
     */
    protfdtfd OpfnTypf(String  dlbssNbmf,
                       String  typfNbmf,
                       String  dfsdription) tirows OpfnDbtbExdfption {
        difdkClbssNbmfOvfrridf();
        tiis.typfNbmf = vblid("typfNbmf", typfNbmf);
        tiis.dfsdription = vblid("dfsdription", dfsdription);
        tiis.dlbssNbmf = vblidClbssNbmf(dlbssNbmf);
        tiis.isArrby = (tiis.dlbssNbmf != null && tiis.dlbssNbmf.stbrtsWiti("["));
    }

    /* Pbdkbgf-privbtf donstrudtor for dbllfrs wf trust to gft it rigit. */
    OpfnTypf(String dlbssNbmf, String typfNbmf, String dfsdription,
             boolfbn isArrby) {
        tiis.dlbssNbmf   = vblid("dlbssNbmf",dlbssNbmf);
        tiis.typfNbmf    = vblid("typfNbmf", typfNbmf);
        tiis.dfsdription = vblid("dfsdription", dfsdription);
        tiis.isArrby     = isArrby;
    }

    privbtf void difdkClbssNbmfOvfrridf() tirows SfdurityExdfption {
        if (tiis.gftClbss().gftClbssLobdfr() == null)
            rfturn;  // Wf trust bootstrbp dlbssfs.
        if (ovfrridfsGftClbssNbmf(tiis.gftClbss())) {
            finbl GftPropfrtyAdtion gftExtfndOpfnTypfs =
                nfw GftPropfrtyAdtion("jmx.fxtfnd.opfn.typfs");
            if (AddfssControllfr.doPrivilfgfd(gftExtfndOpfnTypfs) == null) {
                tirow nfw SfdurityExdfption("Cbnnot ovfrridf gftClbssNbmf() " +
                        "unlfss -Djmx.fxtfnd.opfn.typfs");
            }
        }
    }

    privbtf stbtid boolfbn ovfrridfsGftClbssNbmf(finbl Clbss<?> d) {
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Boolfbn>() {
            publid Boolfbn run() {
                try {
                    rfturn (d.gftMftiod("gftClbssNbmf").gftDfdlbringClbss() !=
                            OpfnTypf.dlbss);
                } dbtdi (Exdfption f) {
                    rfturn truf;  // fbil sbff
                }
            }
        });
    }

    privbtf stbtid String vblidClbssNbmf(String dlbssNbmf) tirows OpfnDbtbExdfption {
        dlbssNbmf   = vblid("dlbssNbmf", dlbssNbmf);

        // Cifdk if dlbssNbmf dfsdribfs bn brrby dlbss, bnd dftfrminfs its flfmfnts' dlbss nbmf.
        // (fg: b 3-dimfnsionbl brrby of Strings ibs for dlbss nbmf: "[[[Ljbvb.lbng.String;")
        //
        int n = 0;
        wiilf (dlbssNbmf.stbrtsWiti("[", n)) {
            n++;
        }
        String fltClbssNbmf; // dlbss nbmf of brrby flfmfnts
        boolfbn isPrimitivfArrby = fblsf;
        if (n > 0) {
            if (dlbssNbmf.stbrtsWiti("L", n) && dlbssNbmf.fndsWiti(";")) {
                // rfmovfs tif n lfbding '[' + tif 'L' dibrbdtfrs
                // bnd tif lbst ';' dibrbdtfr
                fltClbssNbmf = dlbssNbmf.substring(n+1, dlbssNbmf.lfngti()-1);
            } flsf if (n == dlbssNbmf.lfngti() - 1) {
                // rfmovfs tif n lfbding '[' dibrbdtfrs
                fltClbssNbmf = dlbssNbmf.substring(n, dlbssNbmf.lfngti());
                isPrimitivfArrby = truf;
            } flsf {
                tirow nfw OpfnDbtbExdfption("Argumfnt dlbssNbmf=\"" + dlbssNbmf +
                        "\" is not b vblid dlbss nbmf");
            }
        } flsf {
            // not bn brrby
            fltClbssNbmf = dlbssNbmf;
        }

        // Cifdk tibt fltClbssNbmf's vbluf is onf of tif bllowfd bbsid dbtb typfs for opfn dbtb
        //
        boolfbn ok = fblsf;
        if (isPrimitivfArrby) {
            ok = ArrbyTypf.isPrimitivfContfntTypf(fltClbssNbmf);
        } flsf {
            ok = ALLOWED_CLASSNAMES_LIST.dontbins(fltClbssNbmf);
        }
        if ( ! ok ) {
            tirow nfw OpfnDbtbExdfption("Argumfnt dlbssNbmf=\""+ dlbssNbmf +
                                        "\" is not onf of tif bllowfd Jbvb dlbss nbmfs for opfn dbtb.");
        }

        rfturn dlbssNbmf;
    }

    /* Rfturn brgVbluf.trim() providfd brgVbluf is nfitifr null nor fmpty;
       otifrwisf tirow IllfgblArgumfntExdfption.  */
    privbtf stbtid String vblid(String brgNbmf, String brgVbluf) {
        if (brgVbluf == null || (brgVbluf = brgVbluf.trim()).fqubls(""))
            tirow nfw IllfgblArgumfntExdfption("Argumfnt " + brgNbmf +
                                               " dbnnot bf null or fmpty");
        rfturn brgVbluf;
    }

    /* Pbdkbgf-privbtf bddfss to b Dfsdriptor dontbining tiis OpfnTypf. */
    syndironizfd Dfsdriptor gftDfsdriptor() {
        if (dfsdriptor == null) {
            dfsdriptor = nfw ImmutbblfDfsdriptor(nfw String[] {"opfnTypf"},
                                                 nfw Objfdt[] {tiis});
        }
        rfturn dfsdriptor;
    }

    /* *** Opfn typf informbtion mftiods *** */

    /**
     * Rfturns tif fully qublififd Jbvb dlbss nbmf of tif opfn dbtb vblufs
     * tiis opfn typf dfsdribfs.
     * Tif only possiblf Jbvb dlbss nbmfs for opfn dbtb vblufs brf listfd in
     * {@link #ALLOWED_CLASSNAMES_LIST ALLOWED_CLASSNAMES_LIST}.
     * A multidimfnsionbl brrby of bny onf of tifsf dlbssfs or tifir
     * dorrfsponding primitivf typfs is blso bn bllowfd dlbss,
     * in wiidi dbsf tif dlbss nbmf follows tif rulfs dffinfd by tif mftiod
     * {@link Clbss#gftNbmf() gftNbmf()} of <dodf>jbvb.lbng.Clbss</dodf>.
     * For fxbmplf, b 3-dimfnsionbl brrby of Strings ibs for dlbss nbmf
     * &quot;<dodf>[[[Ljbvb.lbng.String;</dodf>&quot; (witiout tif quotfs),
     * b 3-dimfnsionbl brrby of Intfgfrs ibs for dlbss nbmf
     * &quot;<dodf>[[[Ljbvb.lbng.Intfgfr;</dodf>&quot; (witiout tif quotfs),
     * bnd b 3-dimfnsionbl brrby of int ibs for dlbss nbmf
     * &quot;<dodf>[[[I</dodf>&quot; (witiout tif quotfs)
     *
     * @rfturn tif dlbss nbmf.
     */
    publid String gftClbssNbmf() {
        rfturn dlbssNbmf;
    }

    // A vfrsion of gftClbssNbmf() tibt dbn only bf dbllfd from witiin tiis
    // pbdkbgf bnd tibt dbnnot bf ovfrriddfn.
    String sbffGftClbssNbmf() {
        rfturn dlbssNbmf;
    }

    /**
     * Rfturns tif nbmf of tiis <dodf>OpfnTypf</dodf> instbndf.
     *
     * @rfturn tif typf nbmf.
     */
    publid String gftTypfNbmf() {

        rfturn typfNbmf;
    }

    /**
     * Rfturns tif tfxt dfsdription of tiis <dodf>OpfnTypf</dodf> instbndf.
     *
     * @rfturn tif dfsdription.
     */
    publid String gftDfsdription() {

        rfturn dfsdription;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif opfn dbtb vblufs tiis opfn
     * typf dfsdribfs brf brrbys, <dodf>fblsf</dodf> otifrwisf.
     *
     * @rfturn truf if tiis is bn brrby typf.
     */
    publid boolfbn isArrby() {

        rfturn isArrby;
    }

    /**
     * Tfsts wiftifr <vbr>obj</vbr> is b vbluf for tiis opfn typf.
     *
     * @pbrbm obj tif objfdt to bf tfstfd for vblidity.
     *
     * @rfturn <dodf>truf</dodf> if <vbr>obj</vbr> is b vbluf for tiis
     * opfn typf, <dodf>fblsf</dodf> otifrwisf.
     */
    publid bbstrbdt boolfbn isVbluf(Objfdt obj) ;

    /**
     * Tfsts wiftifr vblufs of tif givfn typf dbn bf bssignfd to tiis opfn typf.
     * Tif dffbult implfmfntbtion of tiis mftiod rfturns truf only if tif
     * typfs brf fqubl.
     *
     * @pbrbm ot tif typf to bf tfstfd.
     *
     * @rfturn truf if {@dodf ot} is bssignbblf to tiis opfn typf.
     */
    boolfbn isAssignbblfFrom(OpfnTypf<?> ot) {
        rfturn tiis.fqubls(ot);
    }

    /* *** Mftiods ovfrridfn from dlbss Objfdt *** */

    /**
     * Compbrfs tif spfdififd <dodf>obj</dodf> pbrbmftfr witi tiis
     * opfn typf instbndf for fqublity.
     *
     * @pbrbm obj tif objfdt to dompbrf to.
     *
     * @rfturn truf if tiis objfdt bnd <dodf>obj</dodf> brf fqubl.
     */
    publid bbstrbdt boolfbn fqubls(Objfdt obj) ;

    publid bbstrbdt int ibsiCodf() ;

    /**
     * Rfturns b string rfprfsfntbtion of tiis opfn typf instbndf.
     *
     * @rfturn tif string rfprfsfntbtion.
     */
    publid bbstrbdt String toString() ;

    /**
     * Dfsfriblizfs bn {@link OpfnTypf} from bn {@link jbvb.io.ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
        difdkClbssNbmfOvfrridf();
        ObjfdtInputStrfbm.GftFifld fiflds = in.rfbdFiflds();
        finbl String dlbssNbmfFifld;
        finbl String dfsdriptionFifld;
        finbl String typfNbmfFifld;
        try {
            dlbssNbmfFifld =
                vblidClbssNbmf((String) fiflds.gft("dlbssNbmf", null));
            dfsdriptionFifld =
                vblid("dfsdription", (String) fiflds.gft("dfsdription", null));
            typfNbmfFifld =
                vblid("typfNbmf", (String) fiflds.gft("typfNbmf", null));
        } dbtdi (Exdfption f) {
            IOExdfption f2 = nfw InvblidObjfdtExdfption(f.gftMfssbgf());
            f2.initCbusf(f);
            tirow f2;
        }
        dlbssNbmf = dlbssNbmfFifld;
        dfsdription = dfsdriptionFifld;
        typfNbmf = typfNbmfFifld;
        isArrby = (dlbssNbmf.stbrtsWiti("["));
    }
}
