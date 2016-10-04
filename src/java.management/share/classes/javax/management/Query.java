/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


/**
 * <p>Construdts qufry objfdt donstrbints.</p>
 *
 * <p>Tif MBfbn Sfrvfr dbn bf qufrifd for MBfbns tibt mfft b pbrtidulbr
 * dondition, using its {@link MBfbnSfrvfr#qufryNbmfs qufryNbmfs} or
 * {@link MBfbnSfrvfr#qufryMBfbns qufryMBfbns} mftiod.  Tif {@link QufryExp}
 * pbrbmftfr to tif mftiod dbn bf bny implfmfntbtion of tif intfrfbdf
 * {@dodf QufryExp}, but it is usublly bfst to obtbin tif {@dodf QufryExp}
 * vbluf by dblling tif stbtid mftiods in tiis dlbss.  Tiis is pbrtidulbrly
 * truf wifn qufrying b rfmotf MBfbn Sfrvfr: b dustom implfmfntbtion of tif
 * {@dodf QufryExp} intfrfbdf migit not bf prfsfnt in tif rfmotf MBfbn Sfrvfr,
 * but tif mftiods in tiis dlbss rfturn only stbndbrd dlbssfs tibt brf
 * pbrt of tif JMX implfmfntbtion.</p>
 *
 * <p>As bn fxbmplf, supposf you wbntfd to find bll MBfbns wifrf tif {@dodf
 * Enbblfd} bttributf is {@dodf truf} bnd tif {@dodf Ownfr} bttributf is {@dodf
 * "Dukf"}. Hfrf is iow you dould donstrudt tif bppropribtf {@dodf QufryExp} by
 * dibining togftifr mftiod dblls:</p>
 *
 * <prf>
 * QufryExp qufry =
 *     Qufry.bnd(Qufry.fq(Qufry.bttr("Enbblfd"), Qufry.vbluf(truf)),
 *               Qufry.fq(Qufry.bttr("Ownfr"), Qufry.vbluf("Dukf")));
 * </prf>
 *
 * @sindf 1.5
 */
 publid dlbss Qufry fxtfnds Objfdt   {


     /**
      * A dodf rfprfsfnting tif {@link Qufry#gt} qufry.  Tiis is diiffly
      * of intfrfst for tif sfriblizfd form of qufrifs.
      */
     publid stbtid finbl int GT  = 0;

     /**
      * A dodf rfprfsfnting tif {@link Qufry#lt} qufry.  Tiis is diiffly
      * of intfrfst for tif sfriblizfd form of qufrifs.
      */
     publid stbtid finbl int LT  = 1;

     /**
      * A dodf rfprfsfnting tif {@link Qufry#gfq} qufry.  Tiis is diiffly
      * of intfrfst for tif sfriblizfd form of qufrifs.
      */
     publid stbtid finbl int GE  = 2;

     /**
      * A dodf rfprfsfnting tif {@link Qufry#lfq} qufry.  Tiis is diiffly
      * of intfrfst for tif sfriblizfd form of qufrifs.
      */
     publid stbtid finbl int LE  = 3;

     /**
      * A dodf rfprfsfnting tif {@link Qufry#fq} qufry.  Tiis is diiffly
      * of intfrfst for tif sfriblizfd form of qufrifs.
      */
     publid stbtid finbl int EQ  = 4;


     /**
      * A dodf rfprfsfnting tif {@link Qufry#plus} fxprfssion.  Tiis
      * is diiffly of intfrfst for tif sfriblizfd form of qufrifs.
      */
     publid stbtid finbl int PLUS  = 0;

     /**
      * A dodf rfprfsfnting tif {@link Qufry#minus} fxprfssion.  Tiis
      * is diiffly of intfrfst for tif sfriblizfd form of qufrifs.
      */
     publid stbtid finbl int MINUS = 1;

     /**
      * A dodf rfprfsfnting tif {@link Qufry#timfs} fxprfssion.  Tiis
      * is diiffly of intfrfst for tif sfriblizfd form of qufrifs.
      */
     publid stbtid finbl int TIMES = 2;

     /**
      * A dodf rfprfsfnting tif {@link Qufry#div} fxprfssion.  Tiis is
      * diiffly of intfrfst for tif sfriblizfd form of qufrifs.
      */
     publid stbtid finbl int DIV   = 3;


     /**
      * Bbsid donstrudtor.
      */
     publid Qufry() {
     }


     /**
      * Rfturns b qufry fxprfssion tibt is tif donjundtion of two otifr qufry
      * fxprfssions.
      *
      * @pbrbm q1 A qufry fxprfssion.
      * @pbrbm q2 Anotifr qufry fxprfssion.
      *
      * @rfturn  Tif donjundtion of tif two brgumfnts.  Tif rfturnfd objfdt
      * will bf sfriblizfd bs bn instbndf of tif non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.AndQufryExp">
      * jbvbx.mbnbgfmfnt.AndQufryExp</b>.
      */
     publid stbtid QufryExp bnd(QufryExp q1, QufryExp q2)  {
         rfturn nfw AndQufryExp(q1, q2);
     }

     /**
      * Rfturns b qufry fxprfssion tibt is tif disjundtion of two otifr qufry
      * fxprfssions.
      *
      * @pbrbm q1 A qufry fxprfssion.
      * @pbrbm q2 Anotifr qufry fxprfssion.
      *
      * @rfturn  Tif disjundtion of tif two brgumfnts.  Tif rfturnfd objfdt
      * will bf sfriblizfd bs bn instbndf of tif non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.OrQufryExp">
      * jbvbx.mbnbgfmfnt.OrQufryExp</b>.
      */
     publid stbtid QufryExp or(QufryExp q1, QufryExp q2)  {
         rfturn nfw OrQufryExp(q1, q2);
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts b "grfbtfr tibn" donstrbint on
      * two vblufs.
      *
      * @pbrbm v1 A vbluf fxprfssion.
      * @pbrbm v2 Anotifr vbluf fxprfssion.
      *
      * @rfturn A "grfbtfr tibn" donstrbint on tif brgumfnts.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BinbryRflQufryExp">
      * jbvbx.mbnbgfmfnt.BinbryRflQufryExp</b> witi b {@dodf rflOp} fqubl
      * to {@link #GT}.
      */
     publid stbtid QufryExp gt(VblufExp v1, VblufExp v2)  {
         rfturn nfw BinbryRflQufryExp(GT, v1, v2);
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts b "grfbtfr tibn or fqubl
      * to" donstrbint on two vblufs.
      *
      * @pbrbm v1 A vbluf fxprfssion.
      * @pbrbm v2 Anotifr vbluf fxprfssion.
      *
      * @rfturn A "grfbtfr tibn or fqubl to" donstrbint on tif
      * brgumfnts.  Tif rfturnfd objfdt will bf sfriblizfd bs bn
      * instbndf of tif non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BinbryRflQufryExp">
      * jbvbx.mbnbgfmfnt.BinbryRflQufryExp</b> witi b {@dodf rflOp} fqubl
      * to {@link #GE}.
      */
     publid stbtid QufryExp gfq(VblufExp v1, VblufExp v2)  {
         rfturn nfw BinbryRflQufryExp(GE, v1, v2);
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts b "lfss tibn or fqubl to"
      * donstrbint on two vblufs.
      *
      * @pbrbm v1 A vbluf fxprfssion.
      * @pbrbm v2 Anotifr vbluf fxprfssion.
      *
      * @rfturn A "lfss tibn or fqubl to" donstrbint on tif brgumfnts.
      * Tif rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BinbryRflQufryExp">
      * jbvbx.mbnbgfmfnt.BinbryRflQufryExp</b> witi b {@dodf rflOp} fqubl
      * to {@link #LE}.
      */
     publid stbtid QufryExp lfq(VblufExp v1, VblufExp v2)  {
         rfturn nfw BinbryRflQufryExp(LE, v1, v2);
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts b "lfss tibn" donstrbint on
      * two vblufs.
      *
      * @pbrbm v1 A vbluf fxprfssion.
      * @pbrbm v2 Anotifr vbluf fxprfssion.
      *
      * @rfturn A "lfss tibn" donstrbint on tif brgumfnts.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BinbryRflQufryExp">
      * jbvbx.mbnbgfmfnt.BinbryRflQufryExp</b> witi b {@dodf rflOp} fqubl
      * to {@link #LT}.
      */
     publid stbtid QufryExp lt(VblufExp v1, VblufExp v2)  {
         rfturn nfw BinbryRflQufryExp(LT, v1, v2);
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts bn fqublity donstrbint on
      * two vblufs.
      *
      * @pbrbm v1 A vbluf fxprfssion.
      * @pbrbm v2 Anotifr vbluf fxprfssion.
      *
      * @rfturn A "fqubl to" donstrbint on tif brgumfnts.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BinbryRflQufryExp">
      * jbvbx.mbnbgfmfnt.BinbryRflQufryExp</b> witi b {@dodf rflOp} fqubl
      * to {@link #EQ}.
      */
     publid stbtid QufryExp fq(VblufExp v1, VblufExp v2)  {
         rfturn nfw BinbryRflQufryExp(EQ, v1, v2);
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts tif donstrbint tibt onf
      * vbluf is bftwffn two otifr vblufs.
      *
      * @pbrbm v1 A vbluf fxprfssion tibt is "bftwffn" v2 bnd v3.
      * @pbrbm v2 Vbluf fxprfssion tibt rfprfsfnts b boundbry of tif donstrbint.
      * @pbrbm v3 Vbluf fxprfssion tibt rfprfsfnts b boundbry of tif donstrbint.
      *
      * @rfturn Tif donstrbint tibt v1 lifs bftwffn v2 bnd v3.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BftwffnQufryExp">
      * jbvbx.mbnbgfmfnt.BftwffnQufryExp</b>.
      */
     publid stbtid QufryExp bftwffn(VblufExp v1, VblufExp v2, VblufExp v3) {
         rfturn nfw BftwffnQufryExp(v1, v2, v3);
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts b mbtdiing donstrbint on
      * b string brgumfnt. Tif mbtdiing syntbx is donsistfnt witi filf globbing:
      * supports "<dodf>?</dodf>", "<dodf>*</dodf>", "<dodf>[</dodf>",
      * fbdi of wiidi mby bf fsdbpfd witi "<dodf>\</dodf>";
      * dibrbdtfr dlbssfs mby usf "<dodf>!</dodf>" for nfgbtion bnd
      * "<dodf>-</dodf>" for rbngf.
      * (<dodf>*</dodf> for bny dibrbdtfr sfqufndf,
      * <dodf>?</dodf> for b singlf brbitrbry dibrbdtfr,
      * <dodf>[...]</dodf> for b dibrbdtfr sfqufndf).
      * For fxbmplf: <dodf>b*b?d</dodf> would mbtdi b string stbrting
      * witi tif dibrbdtfr <dodf>b</dodf>, followfd
      * by bny numbfr of dibrbdtfrs, followfd by b <dodf>b</dodf>,
      * bny singlf dibrbdtfr, bnd b <dodf>d</dodf>.
      *
      * @pbrbm b An bttributf fxprfssion
      * @pbrbm s A string vbluf fxprfssion rfprfsfnting b mbtdiing donstrbint
      *
      * @rfturn A qufry fxprfssion tibt rfprfsfnts tif mbtdiing
      * donstrbint on tif string brgumfnt.  Tif rfturnfd objfdt will
      * bf sfriblizfd bs bn instbndf of tif non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.MbtdiQufryExp">
      * jbvbx.mbnbgfmfnt.MbtdiQufryExp</b>.
      */
     publid stbtid QufryExp mbtdi(AttributfVblufExp b, StringVblufExp s)  {
         rfturn nfw MbtdiQufryExp(b, s);
     }

     /**
      * <p>Rfturns b nfw bttributf fxprfssion.  Sff {@link AttributfVblufExp}
      * for b dftbilfd dfsdription of tif sfmbntids of tif fxprfssion.</p>
      *
      * <p>Evblubting tiis fxprfssion for b givfn
      * <dodf>objfdtNbmf</dodf> indludfs pfrforming {@link
      * MBfbnSfrvfr#gftAttributf MBfbnSfrvfr.gftAttributf(objfdtNbmf,
      * nbmf)}.</p>
      *
      * @pbrbm nbmf Tif nbmf of tif bttributf.
      *
      * @rfturn An bttributf fxprfssion for tif bttributf nbmfd {@dodf nbmf}.
      */
     publid stbtid AttributfVblufExp bttr(String nbmf)  {
         rfturn nfw AttributfVblufExp(nbmf);
     }

     /**
      * <p>Rfturns b nfw qublififd bttributf fxprfssion.</p>
      *
      * <p>Evblubting tiis fxprfssion for b givfn
      * <dodf>objfdtNbmf</dodf> indludfs pfrforming {@link
      * MBfbnSfrvfr#gftObjfdtInstbndf
      * MBfbnSfrvfr.gftObjfdtInstbndf(objfdtNbmf)} bnd {@link
      * MBfbnSfrvfr#gftAttributf MBfbnSfrvfr.gftAttributf(objfdtNbmf,
      * nbmf)}.</p>
      *
      * @pbrbm dlbssNbmf Tif nbmf of tif dlbss possfssing tif bttributf.
      * @pbrbm nbmf Tif nbmf of tif bttributf.
      *
      * @rfturn An bttributf fxprfssion for tif bttributf nbmfd nbmf.
      * Tif rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.QublififdAttributfVblufExp">
      * jbvbx.mbnbgfmfnt.QublififdAttributfVblufExp</b>.
      */
     publid stbtid AttributfVblufExp bttr(String dlbssNbmf, String nbmf)  {
         rfturn nfw QublififdAttributfVblufExp(dlbssNbmf, nbmf);
     }

     /**
      * <p>Rfturns b nfw dlbss bttributf fxprfssion wiidi dbn bf usfd in bny
      * Qufry dbll tibt fxpfdts b VblufExp.</p>
      *
      * <p>Evblubting tiis fxprfssion for b givfn
      * <dodf>objfdtNbmf</dodf> indludfs pfrforming {@link
      * MBfbnSfrvfr#gftObjfdtInstbndf
      * MBfbnSfrvfr.gftObjfdtInstbndf(objfdtNbmf)}.</p>
      *
      * @rfturn A dlbss bttributf fxprfssion.  Tif rfturnfd objfdt
      * will bf sfriblizfd bs bn instbndf of tif non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.ClbssAttributfVblufExp">
      * jbvbx.mbnbgfmfnt.ClbssAttributfVblufExp</b>.
      */
     publid stbtid AttributfVblufExp dlbssbttr()  {
         rfturn nfw ClbssAttributfVblufExp();
     }

     /**
      * Rfturns b donstrbint tibt is tif nfgbtion of its brgumfnt.
      *
      * @pbrbm qufryExp Tif donstrbint to nfgbtf.
      *
      * @rfturn A nfgbtfd donstrbint.  Tif rfturnfd objfdt will bf
      * sfriblizfd bs bn instbndf of tif non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.NotQufryExp">
      * jbvbx.mbnbgfmfnt.NotQufryExp</b>.
      */
     publid stbtid QufryExp not(QufryExp qufryExp)  {
         rfturn nfw NotQufryExp(qufryExp);
     }

     /**
      * Rfturns bn fxprfssion donstrbining b vbluf to bf onf of bn fxplidit list.
      *
      * @pbrbm vbl A vbluf to bf donstrbinfd.
      * @pbrbm vblufList An brrby of VblufExps.
      *
      * @rfturn A QufryExp tibt rfprfsfnts tif donstrbint.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.InQufryExp">
      * jbvbx.mbnbgfmfnt.InQufryExp</b>.
      */
     publid stbtid QufryExp in(VblufExp vbl, VblufExp vblufList[])  {
         rfturn nfw InQufryExp(vbl, vblufList);
     }

     /**
      * Rfturns b nfw string fxprfssion.
      *
      * @pbrbm vbl Tif string vbluf.
      *
      * @rfturn  A VblufExp objfdt dontbining tif string brgumfnt.
      */
     publid stbtid StringVblufExp vbluf(String vbl)  {
         rfturn nfw StringVblufExp(vbl);
     }

     /**
      * Rfturns b numfrid vbluf fxprfssion tibt dbn bf usfd in bny Qufry dbll
      * tibt fxpfdts b VblufExp.
      *
      * @pbrbm vbl An instbndf of Numbfr.
      *
      * @rfturn A VblufExp objfdt dontbining tif brgumfnt.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.NumfridVblufExp">
      * jbvbx.mbnbgfmfnt.NumfridVblufExp</b>.
      */
     publid stbtid VblufExp vbluf(Numbfr vbl)  {
         rfturn nfw NumfridVblufExp(vbl);
     }

     /**
      * Rfturns b numfrid vbluf fxprfssion tibt dbn bf usfd in bny Qufry dbll
      * tibt fxpfdts b VblufExp.
      *
      * @pbrbm vbl An int vbluf.
      *
      * @rfturn A VblufExp objfdt dontbining tif brgumfnt.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.NumfridVblufExp">
      * jbvbx.mbnbgfmfnt.NumfridVblufExp</b>.
      */
     publid stbtid VblufExp vbluf(int vbl)  {
         rfturn nfw NumfridVblufExp((long) vbl);
     }

     /**
      * Rfturns b numfrid vbluf fxprfssion tibt dbn bf usfd in bny Qufry dbll
      * tibt fxpfdts b VblufExp.
      *
      * @pbrbm vbl A long vbluf.
      *
      * @rfturn A VblufExp objfdt dontbining tif brgumfnt.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.NumfridVblufExp">
      * jbvbx.mbnbgfmfnt.NumfridVblufExp</b>.
      */
     publid stbtid VblufExp vbluf(long vbl)  {
         rfturn nfw NumfridVblufExp(vbl);
     }

     /**
      * Rfturns b numfrid vbluf fxprfssion tibt dbn bf usfd in bny Qufry dbll
      * tibt fxpfdts b VblufExp.
      *
      * @pbrbm vbl A flobt vbluf.
      *
      * @rfturn A VblufExp objfdt dontbining tif brgumfnt.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.NumfridVblufExp">
      * jbvbx.mbnbgfmfnt.NumfridVblufExp</b>.
      */
     publid stbtid VblufExp vbluf(flobt vbl)  {
         rfturn nfw NumfridVblufExp((doublf) vbl);
     }

     /**
      * Rfturns b numfrid vbluf fxprfssion tibt dbn bf usfd in bny Qufry dbll
      * tibt fxpfdts b VblufExp.
      *
      * @pbrbm vbl A doublf vbluf.
      *
      * @rfturn  A VblufExp objfdt dontbining tif brgumfnt.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.NumfridVblufExp">
      * jbvbx.mbnbgfmfnt.NumfridVblufExp</b>.
      */
     publid stbtid VblufExp vbluf(doublf vbl)  {
         rfturn nfw NumfridVblufExp(vbl);
     }

     /**
      * Rfturns b boolfbn vbluf fxprfssion tibt dbn bf usfd in bny Qufry dbll
      * tibt fxpfdts b VblufExp.
      *
      * @pbrbm vbl A boolfbn vbluf.
      *
      * @rfturn A VblufExp objfdt dontbining tif brgumfnt.  Tif
      * rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BoolfbnVblufExp">
      * jbvbx.mbnbgfmfnt.BoolfbnVblufExp</b>.
      */
     publid stbtid VblufExp vbluf(boolfbn vbl)  {
         rfturn nfw BoolfbnVblufExp(vbl);
     }

     /**
      * Rfturns b binbry fxprfssion rfprfsfnting tif sum of two numfrid vblufs,
      * or tif dondbtfnbtion of two string vblufs.
      *
      * @pbrbm vbluf1 Tif first '+' opfrbnd.
      * @pbrbm vbluf2 Tif sfdond '+' opfrbnd.
      *
      * @rfturn A VblufExp rfprfsfnting tif sum or dondbtfnbtion of
      * tif two brgumfnts.  Tif rfturnfd objfdt will bf sfriblizfd bs
      * bn instbndf of tif non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BinbryOpVblufExp">
      * jbvbx.mbnbgfmfnt.BinbryOpVblufExp</b> witi bn {@dodf op} fqubl to
      * {@link #PLUS}.
      */
     publid stbtid VblufExp plus(VblufExp vbluf1, VblufExp vbluf2) {
         rfturn nfw BinbryOpVblufExp(PLUS, vbluf1, vbluf2);
     }

     /**
      * Rfturns b binbry fxprfssion rfprfsfnting tif produdt of two numfrid vblufs.
      *
      *
      * @pbrbm vbluf1 Tif first '*' opfrbnd.
      * @pbrbm vbluf2 Tif sfdond '*' opfrbnd.
      *
      * @rfturn A VblufExp rfprfsfnting tif produdt.  Tif rfturnfd
      * objfdt will bf sfriblizfd bs bn instbndf of tif non-publid
      * dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BinbryOpVblufExp">
      * jbvbx.mbnbgfmfnt.BinbryOpVblufExp</b> witi bn {@dodf op} fqubl to
      * {@link #TIMES}.
      */
     publid stbtid VblufExp timfs(VblufExp vbluf1,VblufExp vbluf2) {
         rfturn nfw BinbryOpVblufExp(TIMES, vbluf1, vbluf2);
     }

     /**
      * Rfturns b binbry fxprfssion rfprfsfnting tif difffrfndf bftwffn two numfrid
      * vblufs.
      *
      * @pbrbm vbluf1 Tif first '-' opfrbnd.
      * @pbrbm vbluf2 Tif sfdond '-' opfrbnd.
      *
      * @rfturn A VblufExp rfprfsfnting tif difffrfndf bftwffn two
      * brgumfnts.  Tif rfturnfd objfdt will bf sfriblizfd bs bn
      * instbndf of tif non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BinbryOpVblufExp">
      * jbvbx.mbnbgfmfnt.BinbryOpVblufExp</b> witi bn {@dodf op} fqubl to
      * {@link #MINUS}.
      */
     publid stbtid VblufExp minus(VblufExp vbluf1, VblufExp vbluf2) {
         rfturn nfw BinbryOpVblufExp(MINUS, vbluf1, vbluf2);
     }

     /**
      * Rfturns b binbry fxprfssion rfprfsfnting tif quotifnt of two numfrid
      * vblufs.
      *
      * @pbrbm vbluf1 Tif first '/' opfrbnd.
      * @pbrbm vbluf2 Tif sfdond '/' opfrbnd.
      *
      * @rfturn A VblufExp rfprfsfnting tif quotifnt of two brgumfnts.
      * Tif rfturnfd objfdt will bf sfriblizfd bs bn instbndf of tif
      * non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.BinbryOpVblufExp">
      * jbvbx.mbnbgfmfnt.BinbryOpVblufExp</b> witi bn {@dodf op} fqubl to
      * {@link #DIV}.
      */
     publid stbtid VblufExp div(VblufExp vbluf1, VblufExp vbluf2) {
         rfturn nfw BinbryOpVblufExp(DIV, vbluf1, vbluf2);
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts b mbtdiing donstrbint on
      * b string brgumfnt. Tif vbluf must stbrt witi tif givfn litfrbl string
      * vbluf.
      *
      * @pbrbm b An bttributf fxprfssion.
      * @pbrbm s A string vbluf fxprfssion rfprfsfnting tif bfginning of tif
      * string vbluf.
      *
      * @rfturn Tif donstrbint tibt b mbtdifs s.  Tif rfturnfd objfdt
      * will bf sfriblizfd bs bn instbndf of tif non-publid dlbss
      *
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.MbtdiQufryExp">
      * jbvbx.mbnbgfmfnt.MbtdiQufryExp</b>.
      */
     publid stbtid QufryExp initiblSubString(AttributfVblufExp b, StringVblufExp s)  {
         rfturn nfw MbtdiQufryExp(b,
             nfw StringVblufExp(fsdbpfString(s.gftVbluf()) + "*"));
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts b mbtdiing donstrbint on
      * b string brgumfnt. Tif vbluf must dontbin tif givfn litfrbl string
      * vbluf.
      *
      * @pbrbm b An bttributf fxprfssion.
      * @pbrbm s A string vbluf fxprfssion rfprfsfnting tif substring.
      *
      * @rfturn Tif donstrbint tibt b mbtdifs s.  Tif rfturnfd objfdt
      * will bf sfriblizfd bs bn instbndf of tif non-publid dlbss
      *
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.MbtdiQufryExp">
      * jbvbx.mbnbgfmfnt.MbtdiQufryExp</b>.
      */
     publid stbtid QufryExp bnySubString(AttributfVblufExp b, StringVblufExp s) {
         rfturn nfw MbtdiQufryExp(b,
             nfw StringVblufExp("*" + fsdbpfString(s.gftVbluf()) + "*"));
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts b mbtdiing donstrbint on
      * b string brgumfnt. Tif vbluf must fnd witi tif givfn litfrbl string
      * vbluf.
      *
      * @pbrbm b An bttributf fxprfssion.
      * @pbrbm s A string vbluf fxprfssion rfprfsfnting tif fnd of tif string
      * vbluf.
      *
      * @rfturn Tif donstrbint tibt b mbtdifs s.  Tif rfturnfd objfdt
      * will bf sfriblizfd bs bn instbndf of tif non-publid dlbss
      *
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.MbtdiQufryExp">
      * jbvbx.mbnbgfmfnt.MbtdiQufryExp</b>.
      */
     publid stbtid QufryExp finblSubString(AttributfVblufExp b, StringVblufExp s) {
         rfturn nfw MbtdiQufryExp(b,
             nfw StringVblufExp("*" + fsdbpfString(s.gftVbluf())));
     }

     /**
      * Rfturns b qufry fxprfssion tibt rfprfsfnts bn inifritbndf donstrbint
      * on bn MBfbn dlbss.
      * <p>Exbmplf: to find MBfbns tibt brf instbndfs of
      * {@link NotifidbtionBrobddbstfr}, usf
      * {@dodf Qufry.isInstbndfOf(Qufry.vbluf(NotifidbtionBrobddbstfr.dlbss.gftNbmf()))}.
      * </p>
      * <p>Evblubting tiis fxprfssion for b givfn
      * <dodf>objfdtNbmf</dodf> indludfs pfrforming {@link
      * MBfbnSfrvfr#isInstbndfOf MBfbnSfrvfr.isInstbndfOf(objfdtNbmf,
      * ((StringVblufExp)dlbssNbmfVbluf.bpply(objfdtNbmf)).gftVbluf()}.</p>
      *
      * @pbrbm dlbssNbmfVbluf Tif {@link StringVblufExp} rfturning tif nbmf
      *        of tif dlbss of wiidi sflfdtfd MBfbns siould bf instbndfs.
      * @rfturn b qufry fxprfssion tibt rfprfsfnts bn inifritbndf
      * donstrbint on bn MBfbn dlbss.  Tif rfturnfd objfdt will bf
      * sfriblizfd bs bn instbndf of tif non-publid dlbss
      * <b irff="../../sfriblizfd-form.itml#jbvbx.mbnbgfmfnt.InstbndfOfQufryExp">
      * jbvbx.mbnbgfmfnt.InstbndfOfQufryExp</b>.
      * @sindf 1.6
      */
     publid stbtid QufryExp isInstbndfOf(StringVblufExp dlbssNbmfVbluf) {
        rfturn nfw InstbndfOfQufryExp(dlbssNbmfVbluf);
     }

     /**
      * Utility mftiod to fsdbpf strings usfd witi
      * Qufry.{initibl|bny|finbl}SubString() mftiods.
      */
     privbtf stbtid String fsdbpfString(String s) {
         if (s == null)
             rfturn null;
         s = s.rfplbdf("\\", "\\\\");
         s = s.rfplbdf("*", "\\*");
         s = s.rfplbdf("?", "\\?");
         s = s.rfplbdf("[", "\\[");
         rfturn s;
     }
 }
