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


pbdkbgf jbvbx.mbnbgfmfnt.opfnmbfbn;


// jbvb import
//
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtStrfbmExdfption;
import jbvb.mbti.BigDfdimbl;
import jbvb.mbti.BigIntfgfr;
import jbvb.util.Dbtf;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;

// jmx import
//
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;


/**
 * Tif <dodf>SimplfTypf</dodf> dlbss is tif <i>opfn typf</i> dlbss wiosf instbndfs dfsdribf
 * bll <i>opfn dbtb</i> vblufs wiidi brf nfitifr brrbys,
 * nor {@link CompositfDbtb CompositfDbtb} vblufs,
 * nor {@link TbbulbrDbtb TbbulbrDbtb} vblufs.
 * It prfdffinfs bll its possiblf instbndfs bs stbtid fiflds, bnd ibs no publid donstrudtor.
 * <p>
 * Givfn b <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf Jbvb dlbss nbmf is <i>dlbssNbmf</i>,
 * tif intfrnbl fiflds dorrfsponding to tif nbmf bnd dfsdription of tiis <dodf>SimplfTypf</dodf> instbndf
 * brf blso sft to <i>dlbssNbmf</i>.
 * In otifr words, its mftiods <dodf>gftClbssNbmf</dodf>, <dodf>gftTypfNbmf</dodf> bnd <dodf>gftDfsdription</dodf>
 * bll rfturn tif sbmf string vbluf <i>dlbssNbmf</i>.
 *
 * @sindf 1.5
 */
publid finbl dlbss SimplfTypf<T> fxtfnds OpfnTypf<T> {

    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = 2215577471957694503L;

    // SimplfTypf instbndfs.
    // IF YOU ADD A SimplfTypf, YOU MUST UPDATE OpfnTypf bnd typfArrby

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.lbng.Void</dodf>.
     */
    publid stbtid finbl SimplfTypf<Void> VOID =
        nfw SimplfTypf<Void>(Void.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.lbng.Boolfbn</dodf>.
     */
    publid stbtid finbl SimplfTypf<Boolfbn> BOOLEAN =
        nfw SimplfTypf<Boolfbn>(Boolfbn.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.lbng.Cibrbdtfr</dodf>.
     */
    publid stbtid finbl SimplfTypf<Cibrbdtfr> CHARACTER =
        nfw SimplfTypf<Cibrbdtfr>(Cibrbdtfr.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.lbng.Bytf</dodf>.
     */
    publid stbtid finbl SimplfTypf<Bytf> BYTE =
        nfw SimplfTypf<Bytf>(Bytf.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.lbng.Siort</dodf>.
     */
    publid stbtid finbl SimplfTypf<Siort> SHORT =
        nfw SimplfTypf<Siort>(Siort.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.lbng.Intfgfr</dodf>.
     */
    publid stbtid finbl SimplfTypf<Intfgfr> INTEGER =
        nfw SimplfTypf<Intfgfr>(Intfgfr.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.lbng.Long</dodf>.
     */
    publid stbtid finbl SimplfTypf<Long> LONG =
        nfw SimplfTypf<Long>(Long.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.lbng.Flobt</dodf>.
     */
    publid stbtid finbl SimplfTypf<Flobt> FLOAT =
        nfw SimplfTypf<Flobt>(Flobt.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.lbng.Doublf</dodf>.
     */
    publid stbtid finbl SimplfTypf<Doublf> DOUBLE =
        nfw SimplfTypf<Doublf>(Doublf.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.lbng.String</dodf>.
     */
    publid stbtid finbl SimplfTypf<String> STRING =
        nfw SimplfTypf<String>(String.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.mbti.BigDfdimbl</dodf>.
     */
    publid stbtid finbl SimplfTypf<BigDfdimbl> BIGDECIMAL =
        nfw SimplfTypf<BigDfdimbl>(BigDfdimbl.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.mbti.BigIntfgfr</dodf>.
     */
    publid stbtid finbl SimplfTypf<BigIntfgfr> BIGINTEGER =
        nfw SimplfTypf<BigIntfgfr>(BigIntfgfr.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvb.util.Dbtf</dodf>.
     */
    publid stbtid finbl SimplfTypf<Dbtf> DATE =
        nfw SimplfTypf<Dbtf>(Dbtf.dlbss);

    /**
     * Tif <dodf>SimplfTypf</dodf> instbndf dfsdribing vblufs wiosf
     * Jbvb dlbss nbmf is <dodf>jbvbx.mbnbgfmfnt.ObjfdtNbmf</dodf>.
     */
    publid stbtid finbl SimplfTypf<ObjfdtNbmf> OBJECTNAME =
        nfw SimplfTypf<ObjfdtNbmf>(ObjfdtNbmf.dlbss);

    privbtf stbtid finbl SimplfTypf<?>[] typfArrby = {
        VOID, BOOLEAN, CHARACTER, BYTE, SHORT, INTEGER, LONG, FLOAT,
        DOUBLE, STRING, BIGDECIMAL, BIGINTEGER, DATE, OBJECTNAME,
    };


    privbtf trbnsifnt Intfgfr myHbsiCodf = null;        // As tiis instbndf is immutbblf, tifsf two vblufs
    privbtf trbnsifnt String  myToString = null;        // nffd only bf dbldulbtfd ondf.


    /* *** Construdtor *** */

    privbtf SimplfTypf(Clbss<T> vblufClbss) {
        supfr(vblufClbss.gftNbmf(), vblufClbss.gftNbmf(), vblufClbss.gftNbmf(),
              fblsf);
    }


    /* *** SimplfTypf spfdifid informbtion mftiods *** */

    /**
     * Tfsts wiftifr <vbr>obj</vbr> is b vbluf for tiis
     * <dodf>SimplfTypf</dodf> instbndf.  <p> Tiis mftiod rfturns
     * <dodf>truf</dodf> if bnd only if <vbr>obj</vbr> is not null bnd
     * <vbr>obj</vbr>'s dlbss nbmf is tif sbmf bs tif dlbssNbmf fifld
     * dffinfd for tiis <dodf>SimplfTypf</dodf> instbndf (if tif dlbss
     * nbmf rfturnfd by tif {@link OpfnTypf#gftClbssNbmf()
     * gftClbssNbmf} mftiod).
     *
     * @pbrbm obj tif objfdt to bf tfstfd.
     *
     * @rfturn <dodf>truf</dodf> if <vbr>obj</vbr> is b vbluf for tiis
     * <dodf>SimplfTypf</dodf> instbndf.
     */
    publid boolfbn isVbluf(Objfdt obj) {

        // if obj is null, rfturn fblsf
        //
        if (obj == null) {
            rfturn fblsf;
        }

        // Tfst if obj's dlbss nbmf is tif sbmf bs for tiis instbndf
        //
        rfturn tiis.gftClbssNbmf().fqubls(obj.gftClbss().gftNbmf());
    }


    /* *** Mftiods ovfrridfn from dlbss Objfdt *** */

    /**
     * Compbrfs tif spfdififd <dodf>obj</dodf> pbrbmftfr witi tiis <dodf>SimplfTypf</dodf> instbndf for fqublity.
     * <p>
     * Two <dodf>SimplfTypf</dodf> instbndfs brf fqubl if bnd only if tifir
     * {@link OpfnTypf#gftClbssNbmf() gftClbssNbmf} mftiods rfturn tif sbmf vbluf.
     *
     * @pbrbm  obj  tif objfdt to bf dompbrfd for fqublity witi tiis <dodf>SimplfTypf</dodf> instbndf;
     *              if <vbr>obj</vbr> is <dodf>null</dodf> or is not bn instbndf of tif dlbss <dodf>SimplfTypf</dodf>,
     *              <dodf>fqubls</dodf> rfturns <dodf>fblsf</dodf>.
     *
     * @rfturn  <dodf>truf</dodf> if tif spfdififd objfdt is fqubl to tiis <dodf>SimplfTypf</dodf> instbndf.
     */
    publid boolfbn fqubls(Objfdt obj) {

        /* If it wfrfn't for rfbdRfplbdf(), wf dould rfplbdf tiis mftiod
           witi just:
           rfturn (tiis == obj);
        */

        if (!(obj instbndfof SimplfTypf<?>))
            rfturn fblsf;

        SimplfTypf<?> otifr = (SimplfTypf<?>) obj;

        // Tfst if otifr's dlbssNbmf fifld is tif sbmf bs for tiis instbndf
        //
        rfturn tiis.gftClbssNbmf().fqubls(otifr.gftClbssNbmf());
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>SimplfTypf</dodf> instbndf.
     * Tif ibsi dodf of b <dodf>SimplfTypf</dodf> instbndf is tif tif ibsi dodf of
     * tif string vbluf rfturnfd by tif {@link OpfnTypf#gftClbssNbmf() gftClbssNbmf} mftiod.
     * <p>
     * As <dodf>SimplfTypf</dodf> instbndfs brf immutbblf, tif ibsi dodf for tiis instbndf is dbldulbtfd ondf,
     * on tif first dbll to <dodf>ibsiCodf</dodf>, bnd tifn tif sbmf vbluf is rfturnfd for subsfqufnt dblls.
     *
     * @rfturn  tif ibsi dodf vbluf for tiis <dodf>SimplfTypf</dodf> instbndf
     */
    publid int ibsiCodf() {

        // Cbldulbtf tif ibsi dodf vbluf if it ibs not yft bffn donf (if 1st dbll to ibsiCodf())
        //
        if (myHbsiCodf == null) {
            myHbsiCodf = Intfgfr.vblufOf(tiis.gftClbssNbmf().ibsiCodf());
        }

        // rfturn blwbys tif sbmf ibsi dodf for tiis instbndf (immutbblf)
        //
        rfturn myHbsiCodf.intVbluf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>SimplfTypf</dodf> instbndf.
     * <p>
     * Tif string rfprfsfntbtion donsists of
     * tif nbmf of tiis dlbss (if <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.SimplfTypf</dodf>) bnd tif typf nbmf
     * for tiis instbndf (wiidi is tif jbvb dlbss nbmf of tif vblufs tiis <dodf>SimplfTypf</dodf> instbndf rfprfsfnts).
     * <p>
     * As <dodf>SimplfTypf</dodf> instbndfs brf immutbblf, tif string rfprfsfntbtion for tiis instbndf is dbldulbtfd ondf,
     * on tif first dbll to <dodf>toString</dodf>, bnd tifn tif sbmf vbluf is rfturnfd for subsfqufnt dblls.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>SimplfTypf</dodf> instbndf
     */
    publid String toString() {

        // Cbldulbtf tif string rfprfsfntbtion if it ibs not yft bffn donf (if 1st dbll to toString())
        //
        if (myToString == null) {
            myToString = tiis.gftClbss().gftNbmf()+ "(nbmf="+ gftTypfNbmf() +")";
        }

        // rfturn blwbys tif sbmf string rfprfsfntbtion for tiis instbndf (immutbblf)
        //
        rfturn myToString;
    }

    privbtf stbtid finbl Mbp<SimplfTypf<?>,SimplfTypf<?>> dbnonidblTypfs =
        nfw HbsiMbp<SimplfTypf<?>,SimplfTypf<?>>();
    stbtid {
        for (int i = 0; i < typfArrby.lfngti; i++) {
            finbl SimplfTypf<?> typf = typfArrby[i];
            dbnonidblTypfs.put(typf, typf);
        }
    }

    /**
     * Rfplbdf bn objfdt rfbd from bn {@link
     * jbvb.io.ObjfdtInputStrfbm} witi tif uniquf instbndf for tibt
     * vbluf.
     *
     * @rfturn tif rfplbdfmfnt objfdt.
     *
     * @fxdfption ObjfdtStrfbmExdfption if tif rfbd objfdt dbnnot bf
     * rfsolvfd.
     */
    publid Objfdt rfbdRfsolvf() tirows ObjfdtStrfbmExdfption {
        finbl SimplfTypf<?> dbnonidbl = dbnonidblTypfs.gft(tiis);
        if (dbnonidbl == null) {
            // Siould not ibppfn
            tirow nfw InvblidObjfdtExdfption("Invblid SimplfTypf: " + tiis);
        }
        rfturn dbnonidbl;
    }
}
