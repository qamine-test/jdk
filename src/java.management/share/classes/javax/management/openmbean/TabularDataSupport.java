/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;
import dom.sun.jmx.mbfbnsfrvfr.Util;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.LinkfdHbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;

// jmx import
//


/**
 * Tif <tt>TbbulbrDbtbSupport</tt> dlbss is tif <i>opfn dbtb</i> dlbss wiidi implfmfnts tif <tt>TbbulbrDbtb</tt>
 * bnd tif <tt>Mbp</tt> intfrfbdfs, bnd wiidi is intfrnblly bbsfd on b ibsi mbp dbtb strudturf.
 *
 * @sindf 1.5
 */
/* It would mbkf mudi morf sfnsf to implfmfnt
   Mbp<List<?>,CompositfDbtb> ifrf, but unfortunbtfly wf dbnnot for
   dompbtibility rfbsons.  If wf did tibt, tifn wf would ibvf to
   dffinf f.g.
   CompositfDbtb rfmovf(Objfdt)
   instfbd of
   Objfdt rfmovf(Objfdt).

   Tibt would mfbn tibt if bny fxisting dodf subdlbssfd
   TbbulbrDbtbSupport bnd ovfrrodf
   Objfdt rfmovf(Objfdt),
   it would (b) no longfr dompilf bnd (b) not bdtublly ovfrridf
   CompositfDbtb rfmovf(Objfdt)
   in binbrifs dompilfd bfforf tif dibngf.
*/
publid dlbss TbbulbrDbtbSupport
    implfmfnts TbbulbrDbtb, Mbp<Objfdt,Objfdt>,
               Clonfbblf, Sfriblizbblf {


    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = 5720150593236309827L;


    /**
     * @sfribl Tiis tbbulbr dbtb instbndf's dontfnts: b {@link HbsiMbp}
     */
    // fifld dbnnot bf finbl bfdbusf of dlonf mftiod
    privbtf Mbp<Objfdt,CompositfDbtb> dbtbMbp;

    /**
     * @sfribl Tiis tbbulbr dbtb instbndf's tbbulbr typf
     */
    privbtf finbl TbbulbrTypf tbbulbrTypf;

    /**
     * Tif brrby of itfm nbmfs tibt dffinf tif indfx usfd for rows (donvfnifndf fifld)
     */
    privbtf trbnsifnt String[] indfxNbmfsArrby;



    /* *** Construdtors *** */


    /**
     * Crfbtfs bn fmpty <tt>TbbulbrDbtbSupport</tt> instbndf wiosf opfn-typf is <vbr>tbbulbrTypf</vbr>,
     * bnd wiosf undfrlying <tt>HbsiMbp</tt> ibs b dffbult initibl dbpbdity (101) bnd dffbult lobd fbdtor (0.75).
     * <p>
     * Tiis donstrudtor simply dblls <tt>tiis(tbbulbrTypf, 101, 0.75f);</tt>
     *
     * @pbrbm  tbbulbrTypf               tif <i>tbbulbr typf</i> dfsdribing tiis <tt>TbbulbrDbtb</tt> instbndf;
     *                                   dbnnot bf null.
     *
     * @tirows IllfgblArgumfntExdfption  if tif tbbulbr typf is null.
     */
    publid TbbulbrDbtbSupport(TbbulbrTypf tbbulbrTypf) {

        tiis(tbbulbrTypf, 16, 0.75f);
    }

    /**
     * Crfbtfs bn fmpty <tt>TbbulbrDbtbSupport</tt> instbndf wiosf opfn-typf is <vbr>tbbulbrTypf</vbr>,
     * bnd wiosf undfrlying <tt>HbsiMbp</tt> ibs tif spfdififd initibl dbpbdity bnd lobd fbdtor.
     *
     * @pbrbm  tbbulbrTypf               tif <i>tbbulbr typf</i> dfsdribing tiis <tt>TbbulbrDbtb</tt> instbndf;
     *                           dbnnot bf null.
     *
     * @pbrbm  initiblCbpbdity   tif initibl dbpbdity of tif HbsiMbp.
     *
     * @pbrbm  lobdFbdtor        tif lobd fbdtor of tif HbsiMbp
     *
     * @tirows IllfgblArgumfntExdfption  if tif initibl dbpbdity is lfss tibn zfro,
     *                                   or tif lobd fbdtor is nonpositivf,
     *                                   or tif tbbulbr typf is null.
     */
    publid TbbulbrDbtbSupport(TbbulbrTypf tbbulbrTypf, int initiblCbpbdity, flobt lobdFbdtor) {

        // Cifdk tbbulbrTypf is not null
        //
        if (tbbulbrTypf == null) {
            tirow nfw IllfgblArgumfntExdfption("Argumfnt tbbulbrTypf dbnnot bf null.");
        }

        // Initiblizf tiis.tbbulbrTypf (bnd indfxNbmfsArrby for donvfnifndf)
        //
        tiis.tbbulbrTypf = tbbulbrTypf;
        List<String> tmpNbmfs = tbbulbrTypf.gftIndfxNbmfs();
        tiis.indfxNbmfsArrby = tmpNbmfs.toArrby(nfw String[tmpNbmfs.sizf()]);

        // Sindf LinkfdHbsiMbp wbs introdudfd in SE 1.4, it's dondfivbblf fvfn
        // if vfry unlikfly tibt wf migit bf tif sfrvfr of b 1.3 dlifnt.  In
        // tibt dbsf you'll nffd to sft tiis propfrty.  Sff CR 6334663.
        String usfHbsiMbpProp = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion("jmx.tbbulbr.dbtb.ibsi.mbp"));
        boolfbn usfHbsiMbp = "truf".fqublsIgnorfCbsf(usfHbsiMbpProp);

        // Construdt tif fmpty dontfnts HbsiMbp
        //
        tiis.dbtbMbp = usfHbsiMbp ?
            nfw HbsiMbp<Objfdt,CompositfDbtb>(initiblCbpbdity, lobdFbdtor) :
            nfw LinkfdHbsiMbp<Objfdt, CompositfDbtb>(initiblCbpbdity, lobdFbdtor);
    }




    /* *** TbbulbrDbtb spfdifid informbtion mftiods *** */


    /**
     * Rfturns tif <i>tbbulbr typf</i> dfsdribing tiis <tt>TbbulbrDbtb</tt> instbndf.
     */
    publid TbbulbrTypf gftTbbulbrTypf() {

        rfturn tbbulbrTypf;
    }

    /**
     * Cbldulbtfs tif indfx tibt would bf usfd in tiis <tt>TbbulbrDbtb</tt> instbndf to rfffr to tif spfdififd
     * dompositf dbtb <vbr>vbluf</vbr> pbrbmftfr if it wfrf bddfd to tiis instbndf.
     * Tiis mftiod difdks for tif typf vblidity of tif spfdififd <vbr>vbluf</vbr>,
     * but dofs not difdk if tif dbldulbtfd indfx is blrfbdy usfd to rfffr to b vbluf in tiis <tt>TbbulbrDbtb</tt> instbndf.
     *
     * @pbrbm  vbluf                      tif dompositf dbtb vbluf wiosf indfx in tiis
     *                                    <tt>TbbulbrDbtb</tt> instbndf is to bf dbldulbtfd;
     *                                    must bf of tif sbmf dompositf typf bs tiis instbndf's row typf;
     *                                    must not bf null.
     *
     * @rfturn tif indfx tibt tif spfdififd <vbr>vbluf</vbr> would ibvf in tiis <tt>TbbulbrDbtb</tt> instbndf.
     *
     * @tirows NullPointfrExdfption       if <vbr>vbluf</vbr> is <tt>null</tt>.
     *
     * @tirows InvblidOpfnTypfExdfption   if <vbr>vbluf</vbr> dofs not donform to tiis <tt>TbbulbrDbtb</tt> instbndf's
     *                                    row typf dffinition.
     */
    publid Objfdt[] dbldulbtfIndfx(CompositfDbtb vbluf) {

        // Cifdk vbluf is vblid
        //
        difdkVblufTypf(vbluf);

        // Rfturn its dbldulbtfd indfx
        //
        rfturn intfrnblCbldulbtfIndfx(vbluf).toArrby();
    }




    /* *** Contfnt informbtion qufry mftiods *** */


    /**
     * Rfturns <tt>truf</tt> if bnd only if tiis <tt>TbbulbrDbtb</tt> instbndf dontbins b <tt>CompositfDbtb</tt> vbluf
     * (if b row) wiosf indfx is tif spfdififd <vbr>kfy</vbr>. If <vbr>kfy</vbr> dbnnot bf dbst to b onf dimfnsion brrby
     * of Objfdt instbndfs, tiis mftiod simply rfturns <tt>fblsf</tt>; otifrwisf it rfturns tif tif rfsult of tif dbll to
     * <tt>tiis.dontbinsKfy((Objfdt[]) kfy)</tt>.
     *
     * @pbrbm  kfy  tif indfx vbluf wiosf prfsfndf in tiis <tt>TbbulbrDbtb</tt> instbndf is to bf tfstfd.
     *
     * @rfturn  <tt>truf</tt> if tiis <tt>TbbulbrDbtb</tt> indfxfs b row vbluf witi tif spfdififd kfy.
     */
    publid boolfbn dontbinsKfy(Objfdt kfy) {

        // if kfy is not bn brrby of Objfdt instbndfs, rfturn fblsf
        //
        Objfdt[] k;
        try {
            k = (Objfdt[]) kfy;
        } dbtdi (ClbssCbstExdfption f) {
            rfturn fblsf;
        }

        rfturn  tiis.dontbinsKfy(k);
    }

    /**
     * Rfturns <tt>truf</tt> if bnd only if tiis <tt>TbbulbrDbtb</tt> instbndf dontbins b <tt>CompositfDbtb</tt> vbluf
     * (if b row) wiosf indfx is tif spfdififd <vbr>kfy</vbr>. If <vbr>kfy</vbr> is <tt>null</tt> or dofs not donform to
     * tiis <tt>TbbulbrDbtb</tt> instbndf's <tt>TbbulbrTypf</tt> dffinition, tiis mftiod simply rfturns <tt>fblsf</tt>.
     *
     * @pbrbm  kfy  tif indfx vbluf wiosf prfsfndf in tiis <tt>TbbulbrDbtb</tt> instbndf is to bf tfstfd.
     *
     * @rfturn  <tt>truf</tt> if tiis <tt>TbbulbrDbtb</tt> indfxfs b row vbluf witi tif spfdififd kfy.
     */
    publid boolfbn dontbinsKfy(Objfdt[] kfy) {

        rfturn  ( kfy == null ? fblsf : dbtbMbp.dontbinsKfy(Arrbys.bsList(kfy)));
    }

    /**
     * Rfturns <tt>truf</tt> if bnd only if tiis <tt>TbbulbrDbtb</tt> instbndf dontbins tif spfdififd
     * <tt>CompositfDbtb</tt> vbluf. If <vbr>vbluf</vbr> is <tt>null</tt> or dofs not donform to
     * tiis <tt>TbbulbrDbtb</tt> instbndf's row typf dffinition, tiis mftiod simply rfturns <tt>fblsf</tt>.
     *
     * @pbrbm  vbluf  tif row vbluf wiosf prfsfndf in tiis <tt>TbbulbrDbtb</tt> instbndf is to bf tfstfd.
     *
     * @rfturn  <tt>truf</tt> if tiis <tt>TbbulbrDbtb</tt> instbndf dontbins tif spfdififd row vbluf.
     */
    publid boolfbn dontbinsVbluf(CompositfDbtb vbluf) {

        rfturn dbtbMbp.dontbinsVbluf(vbluf);
    }

    /**
     * Rfturns <tt>truf</tt> if bnd only if tiis <tt>TbbulbrDbtb</tt> instbndf dontbins tif spfdififd
     * vbluf.
     *
     * @pbrbm  vbluf  tif row vbluf wiosf prfsfndf in tiis <tt>TbbulbrDbtb</tt> instbndf is to bf tfstfd.
     *
     * @rfturn  <tt>truf</tt> if tiis <tt>TbbulbrDbtb</tt> instbndf dontbins tif spfdififd row vbluf.
     */
    publid boolfbn dontbinsVbluf(Objfdt vbluf) {

        rfturn dbtbMbp.dontbinsVbluf(vbluf);
    }

    /**
     * Tiis mftiod simply dblls <tt>gft((Objfdt[]) kfy)</tt>.
     *
     * @tirows NullPointfrExdfption  if tif <vbr>kfy</vbr> is <tt>null</tt>
     * @tirows ClbssCbstExdfption    if tif <vbr>kfy</vbr> is not of tif typf <tt>Objfdt[]</tt>
     * @tirows InvblidKfyExdfption   if tif <vbr>kfy</vbr> dofs not donform to tiis <tt>TbbulbrDbtb</tt> instbndf's
     *                               <tt>TbbulbrTypf</tt> dffinition
     */
    publid Objfdt gft(Objfdt kfy) {

        rfturn gft((Objfdt[]) kfy);
    }

    /**
     * Rfturns tif <tt>CompositfDbtb</tt> vbluf wiosf indfx is
     * <vbr>kfy</vbr>, or <tt>null</tt> if tifrf is no vbluf mbpping
     * to <vbr>kfy</vbr>, in tiis <tt>TbbulbrDbtb</tt> instbndf.
     *
     * @pbrbm kfy tif indfx of tif vbluf to gft in tiis
     * <tt>TbbulbrDbtb</tt> instbndf; * must bf vblid witi tiis
     * <tt>TbbulbrDbtb</tt> instbndf's row typf dffinition; * must not
     * bf null.
     *
     * @rfturn tif vbluf dorrfsponding to <vbr>kfy</vbr>.
     *
     * @tirows NullPointfrExdfption  if tif <vbr>kfy</vbr> is <tt>null</tt>
     * @tirows InvblidKfyExdfption   if tif <vbr>kfy</vbr> dofs not donform to tiis <tt>TbbulbrDbtb</tt> instbndf's
     *                               <tt>TbbulbrTypf</tt> typf dffinition.
     */
    publid CompositfDbtb gft(Objfdt[] kfy) {

        // Cifdk kfy is not null bnd vblid witi tbbulbrTypf
        // (tirows NullPointfrExdfption, InvblidKfyExdfption)
        //
        difdkKfyTypf(kfy);

        // Rfturn tif mbpping storfd in tif pbrfnt HbsiMbp
        //
        rfturn dbtbMbp.gft(Arrbys.bsList(kfy));
    }




    /* *** Contfnt modifidbtion opfrbtions (onf flfmfnt bt b timf) *** */


    /**
     * Tiis mftiod simply dblls <tt>put((CompositfDbtb) vbluf)</tt> bnd
     * tifrfforf ignorfs its <vbr>kfy</vbr> pbrbmftfr wiidi dbn bf <tt>null</tt>.
     *
     * @pbrbm kfy bn ignorfd pbrbmftfr.
     * @pbrbm vbluf tif {@link CompositfDbtb} to put.
     *
     * @rfturn tif vbluf wiidi is put
     *
     * @tirows NullPointfrExdfption  if tif <vbr>vbluf</vbr> is <tt>null</tt>
     * @tirows ClbssCbstExdfption if tif <vbr>vbluf</vbr> is not of
     * tif typf <tt>CompositfDbtb</tt>
     * @tirows InvblidOpfnTypfExdfption if tif <vbr>vbluf</vbr> dofs
     * not donform to tiis <tt>TbbulbrDbtb</tt> instbndf's
     * <tt>TbbulbrTypf</tt> dffinition
     * @tirows KfyAlrfbdyExistsExdfption if tif kfy for tif
     * <vbr>vbluf</vbr> pbrbmftfr, dbldulbtfd bddording to tiis
     * <tt>TbbulbrDbtb</tt> instbndf's <tt>TbbulbrTypf</tt> dffinition
     * blrfbdy mbps to bn fxisting vbluf
     */
    publid Objfdt put(Objfdt kfy, Objfdt vbluf) {
        intfrnblPut((CompositfDbtb) vbluf);
        rfturn vbluf; // siould bf rfturn intfrnblPut(...); (5090566)
    }

    publid void put(CompositfDbtb vbluf) {
        intfrnblPut(vbluf);
    }

    privbtf CompositfDbtb intfrnblPut(CompositfDbtb vbluf) {
        // Cifdk vbluf is not null, vbluf's typf is tif sbmf bs tiis instbndf's row typf,
        // bnd dbldulbtf tif vbluf's indfx bddording to tiis instbndf's tbbulbrTypf bnd
        // difdk it is not blrfbdy usfd for b mbpping in tif pbrfnt HbsiMbp
        //
        List<?> indfx = difdkVblufAndIndfx(vbluf);

        // storf tif (kfy, vbluf) mbpping in tif dbtbMbp HbsiMbp
        //
        rfturn dbtbMbp.put(indfx, vbluf);
    }

    /**
     * Tiis mftiod simply dblls <tt>rfmovf((Objfdt[]) kfy)</tt>.
     *
     * @pbrbm kfy bn <tt>Objfdt[]</tt> rfprfsfnting tif kfy to rfmovf.
     *
     * @rfturn prfvious vbluf bssodibtfd witi spfdififd kfy, or <tt>null</tt>
     *         if tifrf wbs no mbpping for kfy.
     *
     * @tirows NullPointfrExdfption  if tif <vbr>kfy</vbr> is <tt>null</tt>
     * @tirows ClbssCbstExdfption    if tif <vbr>kfy</vbr> is not of tif typf <tt>Objfdt[]</tt>
     * @tirows InvblidKfyExdfption   if tif <vbr>kfy</vbr> dofs not donform to tiis <tt>TbbulbrDbtb</tt> instbndf's
     *                               <tt>TbbulbrTypf</tt> dffinition
     */
    publid Objfdt rfmovf(Objfdt kfy) {

        rfturn rfmovf((Objfdt[]) kfy);
    }

    /**
     * Rfmovfs tif <tt>CompositfDbtb</tt> vbluf wiosf indfx is <vbr>kfy</vbr> from tiis <tt>TbbulbrDbtb</tt> instbndf,
     * bnd rfturns tif rfmovfd vbluf, or rfturns <tt>null</tt> if tifrf is no vbluf wiosf indfx is <vbr>kfy</vbr>.
     *
     * @pbrbm  kfy  tif indfx of tif vbluf to gft in tiis <tt>TbbulbrDbtb</tt> instbndf;
     *              must bf vblid witi tiis <tt>TbbulbrDbtb</tt> instbndf's row typf dffinition;
     *              must not bf null.
     *
     * @rfturn prfvious vbluf bssodibtfd witi spfdififd kfy, or <tt>null</tt>
     *         if tifrf wbs no mbpping for kfy.
     *
     * @tirows NullPointfrExdfption  if tif <vbr>kfy</vbr> is <tt>null</tt>
     * @tirows InvblidKfyExdfption   if tif <vbr>kfy</vbr> dofs not donform to tiis <tt>TbbulbrDbtb</tt> instbndf's
     *                               <tt>TbbulbrTypf</tt> dffinition
     */
    publid CompositfDbtb rfmovf(Objfdt[] kfy) {

        // Cifdk kfy is not null bnd vblid witi tbbulbrTypf
        // (tirows NullPointfrExdfption, InvblidKfyExdfption)
        //
        difdkKfyTypf(kfy);

        // Rfmovfs tif (kfy, vbluf) mbpping in tif pbrfnt HbsiMbp
        //
        rfturn dbtbMbp.rfmovf(Arrbys.bsList(kfy));
    }



    /* ***   Contfnt modifidbtion bulk opfrbtions   *** */


    /**
     * Add bll tif vblufs dontbinfd in tif spfdififd mbp <vbr>t</vbr>
     * to tiis <tt>TbbulbrDbtb</tt> instbndf.  Tiis mftiod donvfrts
     * tif dollfdtion of vblufs dontbinfd in tiis mbp into bn brrby of
     * <tt>CompositfDbtb</tt> vblufs, if possiblf, bnd tifn dbll tif
     * mftiod <tt>putAll(CompositfDbtb[])</tt>. Notf tibt tif kfys
     * usfd in tif spfdififd mbp <vbr>t</vbr> brf ignorfd. Tiis mftiod
     * bllows, for fxbmplf to bdd tif dontfnt of bnotifr
     * <tt>TbbulbrDbtb</tt> instbndf witi tif sbmf row typf (but
     * possibly difffrfnt indfx nbmfs) into tiis instbndf.
     *
     * @pbrbm t tif mbp wiosf vblufs brf to bf bddfd bs nfw rows to
     * tiis <tt>TbbulbrDbtb</tt> instbndf; if <vbr>t</vbr> is
     * <tt>null</tt> or fmpty, tiis mftiod rfturns witiout doing
     * bnytiing.
     *
     * @tirows NullPointfrExdfption if b vbluf in <vbr>t</vbr> is
     * <tt>null</tt>.
     * @tirows ClbssCbstExdfption if b vbluf in <vbr>t</vbr> is not bn
     * instbndf of <tt>CompositfDbtb</tt>.
     * @tirows InvblidOpfnTypfExdfption if b vbluf in <vbr>t</vbr>
     * dofs not donform to tiis <tt>TbbulbrDbtb</tt> instbndf's row
     * typf dffinition.
     * @tirows KfyAlrfbdyExistsExdfption if tif indfx for b vbluf in
     * <vbr>t</vbr>, dbldulbtfd bddording to tiis
     * <tt>TbbulbrDbtb</tt> instbndf's <tt>TbbulbrTypf</tt> dffinition
     * blrfbdy mbps to bn fxisting vbluf in tiis instbndf, or two
     * vblufs in <vbr>t</vbr> ibvf tif sbmf indfx.
     */
    publid void putAll(Mbp<?,?> t) {

        // if t is null or fmpty, just rfturn
        //
        if ( (t == null) || (t.sizf() == 0) ) {
            rfturn;
        }

        // Convfrt tif vblufs in t into bn brrby of <tt>CompositfDbtb</tt>
        //
        CompositfDbtb[] vblufs;
        try {
            vblufs =
                t.vblufs().toArrby(nfw CompositfDbtb[t.sizf()]);
        } dbtdi (jbvb.lbng.ArrbyStorfExdfption f) {
            tirow nfw ClbssCbstExdfption("Mbp brgumfnt t dontbins vblufs wiidi brf not instbndfs of <tt>CompositfDbtb</tt>");
        }

        // Add tif brrby of vblufs
        //
        putAll(vblufs);
    }

    /**
     * Add bll tif flfmfnts in <vbr>vblufs</vbr> to tiis
     * <tt>TbbulbrDbtb</tt> instbndf.  If bny flfmfnt in
     * <vbr>vblufs</vbr> dofs not sbtisfy tif donstrbints dffinfd in
     * {@link #put(CompositfDbtb) <tt>put</tt>}, or if bny two
     * flfmfnts in <vbr>vblufs</vbr> ibvf tif sbmf indfx dbldulbtfd
     * bddording to tiis <tt>TbbulbrDbtb</tt> instbndf's
     * <tt>TbbulbrTypf</tt> dffinition, tifn bn fxdfption dfsdribing
     * tif fbilurf is tirown bnd no flfmfnt of <vbr>vblufs</vbr> is
     * bddfd, tius lfbving tiis <tt>TbbulbrDbtb</tt> instbndf
     * undibngfd.
     *
     * @pbrbm vblufs tif brrby of dompositf dbtb vblufs to bf bddfd bs
     * nfw rows to tiis <tt>TbbulbrDbtb</tt> instbndf; if
     * <vbr>vblufs</vbr> is <tt>null</tt> or fmpty, tiis mftiod
     * rfturns witiout doing bnytiing.
     *
     * @tirows NullPointfrExdfption if bn flfmfnt of <vbr>vblufs</vbr>
     * is <tt>null</tt>
     * @tirows InvblidOpfnTypfExdfption if bn flfmfnt of
     * <vbr>vblufs</vbr> dofs not donform to tiis
     * <tt>TbbulbrDbtb</tt> instbndf's row typf dffinition (if its
     * <tt>TbbulbrTypf</tt> dffinition)
     * @tirows KfyAlrfbdyExistsExdfption if tif indfx for bn flfmfnt
     * of <vbr>vblufs</vbr>, dbldulbtfd bddording to tiis
     * <tt>TbbulbrDbtb</tt> instbndf's <tt>TbbulbrTypf</tt> dffinition
     * blrfbdy mbps to bn fxisting vbluf in tiis instbndf, or two
     * flfmfnts of <vbr>vblufs</vbr> ibvf tif sbmf indfx
     */
    publid void putAll(CompositfDbtb[] vblufs) {

        // if vblufs is null or fmpty, just rfturn
        //
        if ( (vblufs == null) || (vblufs.lfngti == 0) ) {
            rfturn;
        }

        // drfbtf tif list of indfxfs dorrfsponding to fbdi vbluf
        List<List<?>> indfxfs =
            nfw ArrbyList<List<?>>(vblufs.lfngti + 1);

        // Cifdk bll flfmfnts in vblufs bnd build indfx list
        //
        List<?> indfx;
        for (int i=0; i<vblufs.lfngti; i++) {
            // difdk vbluf bnd dbldulbtf indfx
            indfx = difdkVblufAndIndfx(vblufs[i]);
            // difdk indfx is difffrfnt of tiosf prfviously dbldulbtfd
            if (indfxfs.dontbins(indfx)) {
                tirow nfw KfyAlrfbdyExistsExdfption("Argumfnt flfmfnts vblufs["+ i +"] bnd vblufs["+ indfxfs.indfxOf(indfx) +
                                                    "] ibvf tif sbmf indfxfs, "+
                                                    "dbldulbtfd bddording to tiis TbbulbrDbtb instbndf's tbbulbrTypf.");
            }
            // bdd to indfx list
            indfxfs.bdd(indfx);
        }

        // storf bll (indfx, vbluf) mbppings in tif dbtbMbp HbsiMbp
        //
        for (int i=0; i<vblufs.lfngti; i++) {
            dbtbMbp.put(indfxfs.gft(i), vblufs[i]);
        }
    }

    /**
     * Rfmovfs bll rows from tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf.
     */
    publid void dlfbr() {

        dbtbMbp.dlfbr();
    }



    /* ***  Informbtionbl mftiods from jbvb.util.Mbp  *** */

    /**
     * Rfturns tif numbfr of rows in tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf.
     *
     * @rfturn tif numbfr of rows in tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf.
     */
    publid int sizf() {

        rfturn dbtbMbp.sizf();
    }

    /**
     * Rfturns <tt>truf</tt> if tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf dontbins no rows.
     *
     * @rfturn <tt>truf</tt> if tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf dontbins no rows.
     */
    publid boolfbn isEmpty() {

        rfturn (tiis.sizf() == 0);
    }



    /* ***  Collfdtion vifws from jbvb.util.Mbp  *** */

    /**
     * Rfturns b sft vifw of tif kfys dontbinfd in tif undfrlying mbp of tiis
     * {@dodf TbbulbrDbtbSupport} instbndf usfd to indfx tif rows.
     * Ebdi kfy dontbinfd in tiis {@dodf Sft} is bn unmodifibblf {@dodf List<?>}
     * so tif rfturnfd sft vifw is b {@dodf Sft<List<?>>} but is dfdlbrfd bs b
     * {@dodf Sft<Objfdt>} for dompbtibility rfbsons.
     * Tif sft is bbdkfd by tif undfrlying mbp of tiis
     * {@dodf TbbulbrDbtbSupport} instbndf, so dibngfs to tif
     * {@dodf TbbulbrDbtbSupport} instbndf brf rfflfdtfd in tif
     * sft, bnd vidf-vfrsb.
     *
     * Tif sft supports flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding
     * row from tiis {@dodf TbbulbrDbtbSupport} instbndf, vib tif
     * {@link Itfrbtor#rfmovf}, {@link Sft#rfmovf}, {@link Sft#rfmovfAll},
     * {@link Sft#rftbinAll}, bnd {@link Sft#dlfbr} opfrbtions. It dofs
     *  not support tif {@link Sft#bdd} or {@link Sft#bddAll} opfrbtions.
     *
     * @rfturn b sft vifw ({@dodf Sft<List<?>>}) of tif kfys usfd to indfx
     * tif rows of tiis {@dodf TbbulbrDbtbSupport} instbndf.
     */
    publid Sft<Objfdt> kfySft() {

        rfturn dbtbMbp.kfySft() ;
    }

    /**
     * Rfturns b dollfdtion vifw of tif rows dontbinfd in tiis
     * {@dodf TbbulbrDbtbSupport} instbndf. Tif rfturnfd {@dodf Collfdtion}
     * is b {@dodf Collfdtion<CompositfDbtb>} but is dfdlbrfd bs b
     * {@dodf Collfdtion<Objfdt>} for dompbtibility rfbsons.
     * Tif rfturnfd dollfdtion dbn bf usfd to itfrbtf ovfr tif vblufs.
     * Tif dollfdtion is bbdkfd by tif undfrlying mbp, so dibngfs to tif
     * {@dodf TbbulbrDbtbSupport} instbndf brf rfflfdtfd in tif dollfdtion,
     * bnd vidf-vfrsb.
     *
     * Tif dollfdtion supports flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding
     * indfx to row mbpping from tiis {@dodf TbbulbrDbtbSupport} instbndf, vib
     * tif {@link Itfrbtor#rfmovf}, {@link Collfdtion#rfmovf},
     * {@link Collfdtion#rfmovfAll}, {@link Collfdtion#rftbinAll},
     * bnd {@link Collfdtion#dlfbr} opfrbtions. It dofs not support
     * tif {@link Collfdtion#bdd} or {@link Collfdtion#bddAll} opfrbtions.
     *
     * @rfturn b dollfdtion vifw ({@dodf Collfdtion<CompositfDbtb>}) of
     * tif vblufs dontbinfd in tiis {@dodf TbbulbrDbtbSupport} instbndf.
     */
    @SupprfssWbrnings("undifdkfd")  // iistoridbl donfusion bbout tif rfturn typf
    publid Collfdtion<Objfdt> vblufs() {

        rfturn Util.dbst(dbtbMbp.vblufs());
    }


    /**
     * Rfturns b dollfdtion vifw of tif indfx to row mbppings
     * dontbinfd in tiis {@dodf TbbulbrDbtbSupport} instbndf.
     * Ebdi flfmfnt in tif rfturnfd dollfdtion is
     * b {@dodf Mbp.Entry<List<?>,CompositfDbtb>} but
     * is dfdlbrfd bs b {@dodf Mbp.Entry<Objfdt,Objfdt>}
     * for dompbtibility rfbsons. Ebdi of tif mbp fntry
     * kfys is bn unmodifibblf {@dodf List<?>}.
     * Tif dollfdtion is bbdkfd by tif undfrlying mbp of tiis
     * {@dodf TbbulbrDbtbSupport} instbndf, so dibngfs to tif
     * {@dodf TbbulbrDbtbSupport} instbndf brf rfflfdtfd in
     * tif dollfdtion, bnd vidf-vfrsb.
     * Tif dollfdtion supports flfmfnt rfmovbl, wiidi rfmovfs
     * tif dorrfsponding mbpping from tif mbp, vib tif
     * {@link Itfrbtor#rfmovf}, {@link Collfdtion#rfmovf},
     * {@link Collfdtion#rfmovfAll}, {@link Collfdtion#rftbinAll},
     * bnd {@link Collfdtion#dlfbr} opfrbtions. It dofs not support
     * tif {@link Collfdtion#bdd} or {@link Collfdtion#bddAll}
     * opfrbtions.
     * <p>
     * <b>IMPORTANT NOTICE</b>: Do not usf tif {@dodf sftVbluf} mftiod of tif
     * {@dodf Mbp.Entry} flfmfnts dontbinfd in tif rfturnfd dollfdtion vifw.
     * Doing so would dorrupt tif indfx to row mbppings dontbinfd in tiis
     * {@dodf TbbulbrDbtbSupport} instbndf.
     *
     * @rfturn b dollfdtion vifw ({@dodf Sft<Mbp.Entry<List<?>,CompositfDbtb>>})
     * of tif mbppings dontbinfd in tiis mbp.
     * @sff jbvb.util.Mbp.Entry
     */
    @SupprfssWbrnings("undifdkfd")  // iistoridbl donfusion bbout tif rfturn typf
    publid Sft<Mbp.Entry<Objfdt,Objfdt>> fntrySft() {

        rfturn Util.dbst(dbtbMbp.fntrySft());
    }


    /* ***  Commodity mftiods from jbvb.lbng.Objfdt  *** */


    /**
     * Rfturns b dlonf of tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf:
     * tif dlonf is obtbinfd by dblling <tt>supfr.dlonf()</tt>, bnd tifn dloning tif undfrlying mbp.
     * Only b sibllow dlonf of tif undfrlying mbp is mbdf, i.f. no dloning of tif indfxfs bnd row vblufs is mbdf bs tify brf immutbblf.
     */
    /* Wf dbnnot usf dovbribndf ifrf bnd rfturn TbbulbrDbtbSupport
       bfdbusf tiis would fbil witi fxisting dodf tibt subdlbssfd
       TbbulbrDbtbSupport bnd ovfrrodf Objfdt dlonf().  It would not
       ovfrridf tif nfw dlonf().  */
    publid Objfdt dlonf() {
        try {
            TbbulbrDbtbSupport d = (TbbulbrDbtbSupport) supfr.dlonf();
            d.dbtbMbp = nfw HbsiMbp<Objfdt,CompositfDbtb>(d.dbtbMbp);
            rfturn d;
        }
        dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f.toString(), f);
        }
    }


    /**
     * Compbrfs tif spfdififd <vbr>obj</vbr> pbrbmftfr witi tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf for fqublity.
     * <p>
     * Rfturns <tt>truf</tt> if bnd only if bll of tif following stbtfmfnts brf truf:
     * <ul>
     * <li><vbr>obj</vbr> is non null,</li>
     * <li><vbr>obj</vbr> blso implfmfnts tif <dodf>TbbulbrDbtb</dodf> intfrfbdf,</li>
     * <li>tifir tbbulbr typfs brf fqubl</li>
     * <li>tifir dontfnts (if bll CompositfDbtb vblufs) brf fqubl.</li>
     * </ul>
     * Tiis fnsurfs tibt tiis <tt>fqubls</tt> mftiod works propfrly for <vbr>obj</vbr> pbrbmftfrs wiidi brf
     * difffrfnt implfmfntbtions of tif <dodf>TbbulbrDbtb</dodf> intfrfbdf.
     * <br>&nbsp;
     * @pbrbm  obj  tif objfdt to bf dompbrfd for fqublity witi tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf;
     *
     * @rfturn  <dodf>truf</dodf> if tif spfdififd objfdt is fqubl to tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf.
     */
    publid boolfbn fqubls(Objfdt obj) {

        // if obj is null, rfturn fblsf
        //
        if (obj == null) {
            rfturn fblsf;
        }

        // if obj is not b TbbulbrDbtb, rfturn fblsf
        //
        TbbulbrDbtb otifr;
        try {
            otifr = (TbbulbrDbtb) obj;
        } dbtdi (ClbssCbstExdfption f) {
            rfturn fblsf;
        }

        // Now, rfblly tfst for fqublity bftwffn tiis TbbulbrDbtb implfmfntbtion bnd tif otifr:
        //

        // tifir tbbulbrTypf siould bf fqubl
        if ( ! tiis.gftTbbulbrTypf().fqubls(otifr.gftTbbulbrTypf()) ) {
            rfturn fblsf;
        }

        // tifir dontfnts siould bf fqubl:
        // . sbmf sizf
        // . vblufs in tiis instbndf brf in tif otifr (wf know tifrf brf no duplidbtf flfmfnts possiblf)
        // (row vblufs dompbrison is fnougi, bfdbusf kfys brf dbldulbtfd bddording to tbbulbrTypf)

        if (tiis.sizf() != otifr.sizf()) {
            rfturn fblsf;
        }
        for (CompositfDbtb vbluf : dbtbMbp.vblufs()) {
            if ( ! otifr.dontbinsVbluf(vbluf) ) {
                rfturn fblsf;
            }
        }

        // All tfsts for fqublity wfrf suddfssfull
        //
        rfturn truf;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf.
     * <p>
     * Tif ibsi dodf of b <dodf>TbbulbrDbtbSupport</dodf> instbndf is tif sum of tif ibsi dodfs
     * of bll flfmfnts of informbtion usfd in <dodf>fqubls</dodf> dompbrisons
     * (if: its <i>tbbulbr typf</i> bnd its dontfnt, wifrf tif dontfnt is dffinfd bs bll tif CompositfDbtb vblufs).
     * <p>
     * Tiis fnsurfs tibt <dodf> t1.fqubls(t2) </dodf> implifs tibt <dodf> t1.ibsiCodf()==t2.ibsiCodf() </dodf>
     * for bny two <dodf>TbbulbrDbtbSupport</dodf> instbndfs <dodf>t1</dodf> bnd <dodf>t2</dodf>,
     * bs rfquirfd by tif gfnfrbl dontrbdt of tif mftiod
     * {@link Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.
     * <p>
     * Howfvfr, notf tibt bnotifr instbndf of b dlbss implfmfnting tif <dodf>TbbulbrDbtb</dodf> intfrfbdf
     * mby bf fqubl to tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf bs dffinfd by {@link #fqubls},
     * but mby ibvf b difffrfnt ibsi dodf if it is dbldulbtfd difffrfntly.
     *
     * @rfturn  tif ibsi dodf vbluf for tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf
     */
   publid int ibsiCodf() {

        int rfsult = 0;

        rfsult += tiis.tbbulbrTypf.ibsiCodf();
        for (Objfdt vbluf : vblufs())
            rfsult += vbluf.ibsiCodf();

        rfturn rfsult;

    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf.
     * <p>
     * Tif string rfprfsfntbtion donsists of tif nbmf of tiis dlbss (if <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrDbtbSupport</dodf>),
     * tif string rfprfsfntbtion of tif tbbulbr typf of tiis instbndf, bnd tif string rfprfsfntbtion of tif dontfnts
     * (if list tif kfy=vbluf mbppings bs rfturnfd by b dbll to
     * <tt>dbtbMbp.</tt>{@link jbvb.util.HbsiMbp#toString() toString()}).
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf
     */
    publid String toString() {

        rfturn nfw StringBuildfr()
            .bppfnd(tiis.gftClbss().gftNbmf())
            .bppfnd("(tbbulbrTypf=")
            .bppfnd(tbbulbrTypf.toString())
            .bppfnd(",dontfnts=")
            .bppfnd(dbtbMbp.toString())
            .bppfnd(")")
            .toString();
    }




    /* *** TbbulbrDbtbSupport intfrnbl utility mftiods *** */


    /**
     * Rfturns tif indfx for vbluf, bssuming vbluf is vblid for tiis <tt>TbbulbrDbtb</tt> instbndf
     * (if vbluf is not null, bnd its dompositf typf is fqubl to row typf).
     *
     * Tif indfx is b List, bnd not bn brrby, so tibt bn indfx.fqubls(otifrIndfx) dbll will bdtublly dompbrf dontfnts,
     * not just tif objfdts rfffrfndfs bs is donf for bn brrby objfdt.
     *
     * Tif rfturnfd List is unmodifibblf so tibt ondf b row ibs bffn put into tif dbtbMbp, its indfx dbnnot bf modififd,
     * for fxbmplf by b usfr tibt would bttfmpt to modify bn indfx dontbinfd in tif Sft rfturnfd by kfySft().
     */
    privbtf List<?> intfrnblCbldulbtfIndfx(CompositfDbtb vbluf) {

        rfturn Collfdtions.unmodifibblfList(Arrbys.bsList(vbluf.gftAll(tiis.indfxNbmfsArrby)));
    }

    /**
     * Cifdks if tif spfdififd kfy is vblid for tiis <tt>TbbulbrDbtb</tt> instbndf.
     *
     * @tirows  NullPointfrExdfption
     * @tirows  InvblidOpfnTypfExdfption
     */
    privbtf void difdkKfyTypf(Objfdt[] kfy) {

        // Cifdk kfy is nfitifr null nor fmpty
        //
        if ( (kfy == null) || (kfy.lfngti == 0) ) {
            tirow nfw NullPointfrExdfption("Argumfnt kfy dbnnot bf null or fmpty.");
        }

        /* Now difdk kfy is vblid witi tbbulbrTypf indfx bnd row typf dffinitions: */

        // kfy[] siould ibvf tif sizf fxpfdtfd for bn indfx
        //
        if (kfy.lfngti != tiis.indfxNbmfsArrby.lfngti) {
            tirow nfw InvblidKfyExdfption("Argumfnt kfy's lfngti="+ kfy.lfngti +
                                          " is difffrfnt from tif numbfr of itfm vblufs, wiidi is "+ indfxNbmfsArrby.lfngti +
                                          ", spfdififd for tif indfxing rows in tiis TbbulbrDbtb instbndf.");
        }

        // fbdi flfmfnt in kfy[] siould bf b vbluf for its dorrfsponding opfn typf spfdififd in rowTypf
        //
        OpfnTypf<?> kfyElfmfntTypf;
        for (int i=0; i<kfy.lfngti; i++) {
            kfyElfmfntTypf = tbbulbrTypf.gftRowTypf().gftTypf(tiis.indfxNbmfsArrby[i]);
            if ( (kfy[i] != null) && (! kfyElfmfntTypf.isVbluf(kfy[i])) ) {
                tirow nfw InvblidKfyExdfption("Argumfnt flfmfnt kfy["+ i +"] is not b vbluf for tif opfn typf fxpfdtfd for "+
                                              "tiis flfmfnt of tif indfx, wiosf nbmf is \""+ indfxNbmfsArrby[i] +
                                              "\" bnd wiosf opfn typf is "+ kfyElfmfntTypf);
            }
        }
    }

    /**
     * Cifdks tif spfdififd vbluf's typf is vblid for tiis <tt>TbbulbrDbtb</tt> instbndf
     * (if vbluf is not null, bnd its dompositf typf is fqubl to row typf).
     *
     * @tirows  NullPointfrExdfption
     * @tirows  InvblidOpfnTypfExdfption
     */
    privbtf void difdkVblufTypf(CompositfDbtb vbluf) {

        // Cifdk vbluf is not null
        //
        if (vbluf == null) {
            tirow nfw NullPointfrExdfption("Argumfnt vbluf dbnnot bf null.");
        }

        // if vbluf's typf is not tif sbmf bs tiis instbndf's row typf, tirow InvblidOpfnTypfExdfption
        //
        if (!tbbulbrTypf.gftRowTypf().isVbluf(vbluf)) {
            tirow nfw InvblidOpfnTypfExdfption("Argumfnt vbluf's dompositf typf ["+ vbluf.gftCompositfTypf() +
                                               "] is not bssignbblf to "+
                                               "tiis TbbulbrDbtb instbndf's row typf ["+ tbbulbrTypf.gftRowTypf() +"].");
        }
    }

    /**
     * Cifdks if tif spfdififd vbluf dbn bf put (if bddfd) in tiis <tt>TbbulbrDbtb</tt> instbndf
     * (if vbluf is not null, its dompositf typf is fqubl to row typf, bnd its indfx is not blrfbdy usfd),
     * bnd rfturns tif indfx dbldulbtfd for tiis vbluf.
     *
     * Tif indfx is b List, bnd not bn brrby, so tibt bn indfx.fqubls(otifrIndfx) dbll will bdtublly dompbrf dontfnts,
     * not just tif objfdts rfffrfndfs bs is donf for bn brrby objfdt.
     *
     * @tirows  NullPointfrExdfption
     * @tirows  InvblidOpfnTypfExdfption
     * @tirows  KfyAlrfbdyExistsExdfption
     */
    privbtf List<?> difdkVblufAndIndfx(CompositfDbtb vbluf) {

        // Cifdk vbluf is vblid
        //
        difdkVblufTypf(vbluf);

        // Cbldulbtf vbluf's indfx bddording to tiis instbndf's tbbulbrTypf
        // bnd difdk it is not blrfbdy usfd for b mbpping in tif pbrfnt HbsiMbp
        //
        List<?> indfx = intfrnblCbldulbtfIndfx(vbluf);

        if (dbtbMbp.dontbinsKfy(indfx)) {
            tirow nfw KfyAlrfbdyExistsExdfption("Argumfnt vbluf's indfx, dbldulbtfd bddording to tiis TbbulbrDbtb "+
                                                "instbndf's tbbulbrTypf, blrfbdy rfffrs to b vbluf in tiis tbblf.");
        }

        // Tif difdk is OK, so rfturn tif indfx
        //
        rfturn indfx;
    }

    /**
     * Dfsfriblizfs b {@link TbbulbrDbtbSupport} from bn {@link ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
      in.dffbultRfbdObjfdt();
      List<String> tmpNbmfs = tbbulbrTypf.gftIndfxNbmfs();
      indfxNbmfsArrby = tmpNbmfs.toArrby(nfw String[tmpNbmfs.sizf()]);
    }
}
