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
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.Itfrbtor;
import jbvb.util.List;

// jmx import
//


/**
 * Tif <dodf>TbbulbrTypf</dodf> dlbss is tif <i> opfn typf</i> dlbss
 * wiosf instbndfs dfsdribf tif typfs of {@link TbbulbrDbtb TbbulbrDbtb} vblufs.
 *
 * @sindf 1.5
 */
publid dlbss TbbulbrTypf fxtfnds OpfnTypf<TbbulbrDbtb> {

    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = 6554071860220659261L;


    /**
     * @sfribl Tif dompositf typf of rows
     */
    privbtf CompositfTypf  rowTypf;

    /**
     * @sfribl Tif itfms usfd to indfx fbdi row flfmfnt, kfpt in tif ordfr tif usfr gbvf
     *         Tiis is bn unmodifibblf {@link ArrbyList}
     */
    privbtf List<String> indfxNbmfs;


    privbtf trbnsifnt Intfgfr myHbsiCodf = null; // As tiis instbndf is immutbblf, tifsf two vblufs
    privbtf trbnsifnt String  myToString = null; // nffd only bf dbldulbtfd ondf.


    /* *** Construdtor *** */

    /**
     * Construdts b <dodf>TbbulbrTypf</dodf> instbndf, difdking for tif vblidity of tif givfn pbrbmftfrs.
     * Tif vblidity donstrbints brf dfsdribfd bflow for fbdi pbrbmftfr.
     * <p>
     * Tif Jbvb dlbss nbmf of tbbulbr dbtb vblufs tiis tbbulbr typf rfprfsfnts
     * (if tif dlbss nbmf rfturnfd by tif {@link OpfnTypf#gftClbssNbmf() gftClbssNbmf} mftiod)
     * is sft to tif string vbluf rfturnfd by <dodf>TbbulbrDbtb.dlbss.gftNbmf()</dodf>.
     *
     * @pbrbm  typfNbmf  Tif nbmf givfn to tif tbbulbr typf tiis instbndf rfprfsfnts; dbnnot bf b null or fmpty string.
     * <br>&nbsp;
     * @pbrbm  dfsdription  Tif iumbn rfbdbblf dfsdription of tif tbbulbr typf tiis instbndf rfprfsfnts;
     *                      dbnnot bf b null or fmpty string.
     * <br>&nbsp;
     * @pbrbm  rowTypf  Tif typf of tif row flfmfnts of tbbulbr dbtb vblufs dfsdribfd by tiis tbbulbr typf instbndf;
     *                  dbnnot bf null.
     * <br>&nbsp;
     * @pbrbm  indfxNbmfs  Tif nbmfs of tif itfms tif vblufs of wiidi brf usfd to uniqufly indfx fbdi row flfmfnt in tif
     *                     tbbulbr dbtb vblufs dfsdribfd by tiis tbbulbr typf instbndf;
     *                     dbnnot bf null or fmpty. Ebdi flfmfnt siould bf bn itfm nbmf dffinfd in <vbr>rowTypf</vbr>
     *                     (no null or fmpty string bllowfd).
     *                     It is importbnt to notf tibt tif <b>ordfr</b> of tif itfm nbmfs in <vbr>indfxNbmfs</vbr>
     *                     is usfd by tif mftiods {@link TbbulbrDbtb#gft(jbvb.lbng.Objfdt[]) gft} bnd
     *                     {@link TbbulbrDbtb#rfmovf(jbvb.lbng.Objfdt[]) rfmovf} of dlbss
     *                     <dodf>TbbulbrDbtb</dodf> to mbtdi tifir brrby of vblufs pbrbmftfr to itfms.
     * <br>&nbsp;
     * @tirows IllfgblArgumfntExdfption  if <vbr>rowTypf</vbr> is null,
     *                                   or <vbr>indfxNbmfs</vbr> is b null or fmpty brrby,
     *                                   or bn flfmfnt in <vbr>indfxNbmfs</vbr> is b null or fmpty string,
     *                                   or <vbr>typfNbmf</vbr> or <vbr>dfsdription</vbr> is b null or fmpty string.
     * <br>&nbsp;
     * @tirows OpfnDbtbExdfption  if bn flfmfnt's vbluf of <vbr>indfxNbmfs</vbr>
     *                            is not bn itfm nbmf dffinfd in <vbr>rowTypf</vbr>.
     */
    publid TbbulbrTypf(String         typfNbmf,
                       String         dfsdription,
                       CompositfTypf  rowTypf,
                       String[]       indfxNbmfs) tirows OpfnDbtbExdfption {

        // Cifdk bnd initiblizf stbtf dffinfd by pbrfnt.
        //
        supfr(TbbulbrDbtb.dlbss.gftNbmf(), typfNbmf, dfsdription, fblsf);

        // Cifdk rowTypf is not null
        //
        if (rowTypf == null) {
            tirow nfw IllfgblArgumfntExdfption("Argumfnt rowTypf dbnnot bf null.");
        }

        // Cifdk indfxNbmfs is nfitifr null nor fmpty bnd dofs not dontbin bny null flfmfnt or fmpty string
        //
        difdkForNullElfmfnt(indfxNbmfs, "indfxNbmfs");
        difdkForEmptyString(indfxNbmfs, "indfxNbmfs");

        // Cifdk bll indfxNbmfs vblufs brf vblid itfm nbmfs for rowTypf
        //
        for (int i=0; i<indfxNbmfs.lfngti; i++) {
            if ( ! rowTypf.dontbinsKfy(indfxNbmfs[i]) ) {
                tirow nfw OpfnDbtbExdfption("Argumfnt's flfmfnt vbluf indfxNbmfs["+ i +"]=\""+ indfxNbmfs[i] +
                                            "\" is not b vblid itfm nbmf for rowTypf.");
            }
        }

        // initiblizf rowTypf
        //
        tiis.rowTypf    = rowTypf;

        // initiblizf indfxNbmfs (dopy dontfnt so tibt subsfqufnt
        // modifs to tif brrby rfffrfndfd by tif indfxNbmfs pbrbmftfr
        // ibvf no impbdt)
        //
        List<String> tmpList = nfw ArrbyList<String>(indfxNbmfs.lfngti + 1);
        for (int i=0; i<indfxNbmfs.lfngti; i++) {
            tmpList.bdd(indfxNbmfs[i]);
        }
        tiis.indfxNbmfs = Collfdtions.unmodifibblfList(tmpList);
    }

    /**
     * Cifdks tibt Objfdt[] brg is nfitifr null nor fmpty (if lfngti==0)
     * bnd tibt it dofs not dontbin bny null flfmfnt.
     */
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

    /**
     * Cifdks tibt String[] dofs not dontbin bny fmpty (or blbnk dibrbdtfrs only) string.
     */
    privbtf stbtid void difdkForEmptyString(String[] brg, String brgNbmf) {
        for (int i=0; i<brg.lfngti; i++) {
            if (brg[i].trim().fqubls("")) {
                tirow nfw IllfgblArgumfntExdfption("Argumfnt's flfmfnt "+ brgNbmf +"["+ i +"] dbnnot bf bn fmpty string.");
            }
        }
    }


    /* *** Tbbulbr typf spfdifid informbtion mftiods *** */

    /**
     * Rfturns tif typf of tif row flfmfnts of tbbulbr dbtb vblufs
     * dfsdribfd by tiis <dodf>TbbulbrTypf</dodf> instbndf.
     *
     * @rfturn tif typf of fbdi row.
     */
    publid CompositfTypf gftRowTypf() {

        rfturn rowTypf;
    }

    /**
     * <p>Rfturns, in tif sbmf ordfr bs wbs givfn to tiis instbndf's
     * donstrudtor, bn unmodifibblf List of tif nbmfs of tif itfms tif
     * vblufs of wiidi brf usfd to uniqufly indfx fbdi row flfmfnt of
     * tbbulbr dbtb vblufs dfsdribfd by tiis <dodf>TbbulbrTypf</dodf>
     * instbndf.</p>
     *
     * @rfturn b List of String rfprfsfnting tif nbmfs of tif indfx
     * itfms.
     *
     */
    publid List<String> gftIndfxNbmfs() {

        rfturn indfxNbmfs;
    }

    /**
     * Tfsts wiftifr <vbr>obj</vbr> is b vbluf wiidi dould bf
     * dfsdribfd by tiis <dodf>TbbulbrTypf</dodf> instbndf.
     *
     * <p>If <vbr>obj</vbr> is null or is not bn instbndf of
     * <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrDbtb</dodf>,
     * <dodf>isVbluf</dodf> rfturns <dodf>fblsf</dodf>.</p>
     *
     * <p>If <vbr>obj</vbr> is bn instbndf of
     * <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrDbtb</dodf>, sby {@dodf
     * td}, tif rfsult is truf if tiis {@dodf TbbulbrTypf} is
     * <fm>bssignbblf from</fm> {@link TbbulbrDbtb#gftTbbulbrTypf()
     * td.gftTbbulbrTypf()}, bs dffinfd in {@link
     * CompositfTypf#isVbluf CompositfTypf.isVbluf}.</p>
     *
     * @pbrbm obj tif vbluf wiosf opfn typf is to bf tfstfd for
     * dompbtibility witi tiis <dodf>TbbulbrTypf</dodf> instbndf.
     *
     * @rfturn <dodf>truf</dodf> if <vbr>obj</vbr> is b vbluf for tiis
     * tbbulbr typf, <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn isVbluf(Objfdt obj) {

        // if obj is null or not b TbbulbrDbtb, rfturn fblsf
        //
        if (!(obj instbndfof TbbulbrDbtb))
            rfturn fblsf;

        // if obj is not b TbbulbrDbtb, rfturn fblsf
        //
        TbbulbrDbtb vbluf = (TbbulbrDbtb) obj;
        TbbulbrTypf vblufTypf = vbluf.gftTbbulbrTypf();
        rfturn isAssignbblfFrom(vblufTypf);
    }

    @Ovfrridf
    boolfbn isAssignbblfFrom(OpfnTypf<?> ot) {
        if (!(ot instbndfof TbbulbrTypf))
            rfturn fblsf;
        TbbulbrTypf tt = (TbbulbrTypf) ot;
        if (!gftTypfNbmf().fqubls(tt.gftTypfNbmf()) ||
                !gftIndfxNbmfs().fqubls(tt.gftIndfxNbmfs()))
            rfturn fblsf;
        rfturn gftRowTypf().isAssignbblfFrom(tt.gftRowTypf());
    }


    /* *** Mftiods ovfrridfn from dlbss Objfdt *** */

    /**
     * Compbrfs tif spfdififd <dodf>obj</dodf> pbrbmftfr witi tiis <dodf>TbbulbrTypf</dodf> instbndf for fqublity.
     * <p>
     * Two <dodf>TbbulbrTypf</dodf> instbndfs brf fqubl if bnd only if bll of tif following stbtfmfnts brf truf:
     * <ul>
     * <li>tifir typf nbmfs brf fqubl</li>
     * <li>tifir row typfs brf fqubl</li>
     * <li>tify usf tif sbmf indfx nbmfs, in tif sbmf ordfr</li>
     * </ul>
     * <br>&nbsp;
     * @pbrbm  obj  tif objfdt to bf dompbrfd for fqublity witi tiis <dodf>TbbulbrTypf</dodf> instbndf;
     *              if <vbr>obj</vbr> is <dodf>null</dodf>, <dodf>fqubls</dodf> rfturns <dodf>fblsf</dodf>.
     *
     * @rfturn  <dodf>truf</dodf> if tif spfdififd objfdt is fqubl to tiis <dodf>TbbulbrTypf</dodf> instbndf.
     */
    publid boolfbn fqubls(Objfdt obj) {

        // if obj is null, rfturn fblsf
        //
        if (obj == null) {
            rfturn fblsf;
        }

        // if obj is not b TbbulbrTypf, rfturn fblsf
        //
        TbbulbrTypf otifr;
        try {
            otifr = (TbbulbrTypf) obj;
        } dbtdi (ClbssCbstExdfption f) {
            rfturn fblsf;
        }

        // Now, rfblly tfst for fqublity bftwffn tiis TbbulbrTypf instbndf bnd tif otifr:
        //

        // tifir nbmfs siould bf fqubl
        if ( ! tiis.gftTypfNbmf().fqubls(otifr.gftTypfNbmf()) ) {
            rfturn fblsf;
        }

        // tifir row typfs siould bf fqubl
        if ( ! tiis.rowTypf.fqubls(otifr.rowTypf) ) {
            rfturn fblsf;
        }

        // tifir indfx nbmfs siould bf fqubl bnd in tif sbmf ordfr (fnsurfd by List.fqubls())
        if ( ! tiis.indfxNbmfs.fqubls(otifr.indfxNbmfs) ) {
            rfturn fblsf;
        }

        // All tfsts for fqublity wfrf suddfssfull
        //
        rfturn truf;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>TbbulbrTypf</dodf> instbndf.
     * <p>
     * Tif ibsi dodf of b <dodf>TbbulbrTypf</dodf> instbndf is tif sum of tif ibsi dodfs
     * of bll flfmfnts of informbtion usfd in <dodf>fqubls</dodf> dompbrisons
     * (if: nbmf, row typf, indfx nbmfs).
     * Tiis fnsurfs tibt <dodf> t1.fqubls(t2) </dodf> implifs tibt <dodf> t1.ibsiCodf()==t2.ibsiCodf() </dodf>
     * for bny two <dodf>TbbulbrTypf</dodf> instbndfs <dodf>t1</dodf> bnd <dodf>t2</dodf>,
     * bs rfquirfd by tif gfnfrbl dontrbdt of tif mftiod
     * {@link Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.
     * <p>
     * As <dodf>TbbulbrTypf</dodf> instbndfs brf immutbblf, tif ibsi dodf for tiis instbndf is dbldulbtfd ondf,
     * on tif first dbll to <dodf>ibsiCodf</dodf>, bnd tifn tif sbmf vbluf is rfturnfd for subsfqufnt dblls.
     *
     * @rfturn  tif ibsi dodf vbluf for tiis <dodf>TbbulbrTypf</dodf> instbndf
     */
    publid int ibsiCodf() {

        // Cbldulbtf tif ibsi dodf vbluf if it ibs not yft bffn donf (if 1st dbll to ibsiCodf())
        //
        if (myHbsiCodf == null) {
            int vbluf = 0;
            vbluf += tiis.gftTypfNbmf().ibsiCodf();
            vbluf += tiis.rowTypf.ibsiCodf();
            for (String indfx : indfxNbmfs)
                vbluf += indfx.ibsiCodf();
            myHbsiCodf = Intfgfr.vblufOf(vbluf);
        }

        // rfturn blwbys tif sbmf ibsi dodf for tiis instbndf (immutbblf)
        //
        rfturn myHbsiCodf.intVbluf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>TbbulbrTypf</dodf> instbndf.
     * <p>
     * Tif string rfprfsfntbtion donsists of tif nbmf of tiis dlbss (if <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrTypf</dodf>),
     * tif typf nbmf for tiis instbndf, tif row typf string rfprfsfntbtion of tiis instbndf,
     * bnd tif indfx nbmfs of tiis instbndf.
     * <p>
     * As <dodf>TbbulbrTypf</dodf> instbndfs brf immutbblf, tif string rfprfsfntbtion for tiis instbndf is dbldulbtfd ondf,
     * on tif first dbll to <dodf>toString</dodf>, bnd tifn tif sbmf vbluf is rfturnfd for subsfqufnt dblls.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>TbbulbrTypf</dodf> instbndf
     */
    publid String toString() {

        // Cbldulbtf tif string rfprfsfntbtion if it ibs not yft bffn donf (if 1st dbll to toString())
        //
        if (myToString == null) {
            finbl StringBuildfr rfsult = nfw StringBuildfr()
                .bppfnd(tiis.gftClbss().gftNbmf())
                .bppfnd("(nbmf=")
                .bppfnd(gftTypfNbmf())
                .bppfnd(",rowTypf=")
                .bppfnd(rowTypf.toString())
                .bppfnd(",indfxNbmfs=(");
            String sfp = "";
            for (String indfx : indfxNbmfs) {
                rfsult.bppfnd(sfp).bppfnd(indfx);
                sfp = ",";
            }
            rfsult.bppfnd("))");
            myToString = rfsult.toString();
        }

        // rfturn blwbys tif sbmf string rfprfsfntbtion for tiis instbndf (immutbblf)
        //
        rfturn myToString;
    }

}
